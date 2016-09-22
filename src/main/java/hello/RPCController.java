package hello;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.io.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Tom on 9/15/2016.
 */
@RestController
@RequestMapping("/rpc/*")
@Transactional
public class RPCController
{
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private HoldPointsRequestValidator holdPointsRequestValidator;

    @Autowired
    private LoyaltyRepository loyaltyRepository;

    @Autowired
    private PointTransactionTypeRepository pointTransactionTypeRepository;

    @Autowired
    private PointTypeRepository pointTypeRepository;

    @RequestMapping(value = "v00001/holdPoints", method = RequestMethod.POST, produces = "application/json")
    public long holdPointsForOrderByOrderCodeLoyaltyCodeDivisionNameCompanyName(@Valid @RequestBody HoldPointsRequest hpr, BindingResult errors)
// This is how you do query parameters instead of request body JSON
//                                    @Param("orderCode") String orderCode,
//                                    @Param("orderDivisionName") String orderDivisionName,
//                                    @Param("orderCompanyName") String orderCompanyName,
//                                    @Param("loyaltyCode") String loyaltyCode,
//                                    @Param("loyaltyDivisionName") String loyaltyDivisionName,
//                                    @Param("loyaltyCompanyName") String loyaltyCompanyName,
//                                    @Param("points") Long points)
    {
        holdPointsRequestValidator.validate(hpr, errors);
        if (errors.hasErrors())
        {
            return -1000L;
        }

        List<Loyalty> loyaltyList = loyaltyRepository.findByLoyaltyCodeDivisionNameCompanyName(hpr.getLoyaltyCode(), hpr.getLoyaltyDivisionName(), hpr.getLoyaltyCompanyName());
        if (loyaltyList.isEmpty() || loyaltyList.size() > 1)
        {
            return -1L;
        }

        //entityManager.getTransaction().begin();

        PointTransactionMaster ptm = new PointTransactionMaster();
        ptm.setCreatedDate(new Date());
        ptm.setDollarValue(hpr.getDollarValue());
        List<PointTransactionType> pttl = pointTransactionTypeRepository.findByName(PointTransactionType.heldPointTransactionType);
        ptm.setPointTransactionType(pttl.get(0));
        entityManager.persist(ptm);
        //entityManager.flush();

        PointTransactionDetail sourcePointTransactionDetail = new PointTransactionDetail();
        sourcePointTransactionDetail.setLoyalty(loyaltyList.get(0));
        sourcePointTransactionDetail.setPointTransactionMaster(ptm);
        sourcePointTransactionDetail.setPoints(-1 * hpr.getPoints());
        List<PointType> ptl = pointTypeRepository.findByName(PointType.availablePointType);
        sourcePointTransactionDetail.setPointType(ptl.get(0));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(calendar.getTime());
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        Date nextWeek = calendar.getTime();
        sourcePointTransactionDetail.setAvailabilityExpirationDate(nextWeek);
        entityManager.persist(sourcePointTransactionDetail);

        PointTransactionDetail destPointTransactionDetail = new PointTransactionDetail();
        destPointTransactionDetail.setLoyalty(loyaltyList.get(0));
        destPointTransactionDetail.setPointTransactionMaster(ptm);
        destPointTransactionDetail.setPoints(-1 * hpr.getPoints());
        ptl = pointTypeRepository.findByName(PointType.heldPointType);
        destPointTransactionDetail.setPointType(ptl.get(0));
        calendar.setTime(calendar.getTime());
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        nextWeek = calendar.getTime();
        destPointTransactionDetail.setAvailabilityExpirationDate(nextWeek);
        entityManager.persist(destPointTransactionDetail);

        //entityManager.flush();

        //entityManager.getTransaction().commit();

        return ptm.getId();
    }

    @RequestMapping(value = "v00001/giftPoints", method = RequestMethod.POST, produces = "application/json")
    public String giftPointsForOrderByOrderCodeLoyaltyCodeDivisionNameCompanyName(@Valid @RequestBody HoldPointsRequest hpr, BindingResult errors)
// This is how you do query parameters instead of request body JSON
//                                    @Param("orderCode") String orderCode,
//                                    @Param("orderDivisionName") String orderDivisionName,
//                                    @Param("orderCompanyName") String orderCompanyName,
//                                    @Param("loyaltyCode") String loyaltyCode,
//                                    @Param("loyaltyDivisionName") String loyaltyDivisionName,
//                                    @Param("loyaltyCompanyName") String loyaltyCompanyName,
//                                    @Param("points") Long points)
    {
        holdPointsRequestValidator.validate(hpr, errors);
        if (errors.hasErrors())
        {
            JSONRPCResponseObject ro = new JSONRPCResponseObject();
            ro.setSuccess(false);
            ro.setId(3145L);
            ro.getError().setCode(-32600);
            ro.getError().setMessage("Invalid request.");
            return ro.toJSONString();
        }
        else
        {
            return addPointsForOrderByOrderCodeLoyaltyCodeDivisionNameCompanyName(hpr, errors, PointType.availablePointType, PointTransactionType.giftedPointTransactionType).toJSONString();
        }
    }

    private JSONRPCResponseObject addPointsForOrderByOrderCodeLoyaltyCodeDivisionNameCompanyName(@Valid @RequestBody HoldPointsRequest hpr, BindingResult errors, String pointType, String pointTransactionType)
    {
        List<Loyalty> loyaltyList = loyaltyRepository.findByLoyaltyCodeDivisionNameCompanyName(hpr.getLoyaltyCode(), hpr.getLoyaltyDivisionName(), hpr.getLoyaltyCompanyName());
        if (loyaltyList.isEmpty() || loyaltyList.size() > 1)
        {
            JSONRPCResponseObject ro = new JSONRPCResponseObject();
            ro.setSuccess(false);
            ro.setId(3145L);
            ro.getError().setCode(-32602);
            ro.getError().setMessage("Could not find loyalty account.");
            return ro;
        }

        //entityManager.getTransaction().begin();

        PointTransactionMaster ptm = new PointTransactionMaster();
        ptm.setCreatedDate(new Date());
        ptm.setDollarValue(hpr.getDollarValue());
        List<PointTransactionType> pttl = pointTransactionTypeRepository.findByName(pointTransactionType);
        ptm.setPointTransactionType(pttl.get(0));
        entityManager.persist(ptm);
        //entityManager.flush();

        PointTransactionDetail ptd = new PointTransactionDetail();
        ptd.setLoyalty(loyaltyList.get(0));
        ptd.setPointTransactionMaster(ptm);
        ptd.setPoints(hpr.getPoints());
        List<PointType> ptl = pointTypeRepository.findByName(pointType);
        ptd.setPointType(ptl.get(0));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(calendar.getTime());
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        Date nextWeek = calendar.getTime();
        ptd.setAvailabilityExpirationDate(nextWeek);
        entityManager.persist(ptd);
        //entityManager.flush();

        //entityManager.getTransaction().commit();

        JsonFactory jsonFactory = new JsonFactory();
        JsonGenerator jsonGenerator;

//        try
//        {
            /*
            StringWriter stringWriter = new StringWriter();
            jsonGenerator = jsonFactory.createGenerator(stringWriter);
            jsonGenerator.useDefaultPrettyPrinter();
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("point transaction id", ptm.getId());
            jsonGenerator.writeEndObject();
            jsonGenerator.close();
            return stringWriter.toString();
            */

            JSONRPCResponseObject ro = new JSONRPCResponseObject();
            ro.setSuccess(true);
            ro.setId(3145L);
            ro.getResult().put("point transaction id", ptm.getId());
            ro.getResult().put("loyalty id", ptd.getLoyalty().getId());
            return ro;
//        }
/*
        catch (IOException e)
        {
            JSONRPCResponseObject ro = new JSONRPCResponseObject();
            ro.setSuccess(false);
            ro.setId(3145L);
            ro.setJsonrpc("2.0");
            ro.getError().setCode(-32000);
            ro.getError().setMessage("Couldn't construct JSON RPC response object");
            return ro.toJSONString();

            //e.printStackTrace();
            //return "-2000";
        }
*/
    }
}
