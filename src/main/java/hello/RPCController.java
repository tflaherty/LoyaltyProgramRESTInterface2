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
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.io.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Tom on 9/15/2016.
 */
@RestController
//@RequestMapping("/rpc/*")
@Transactional
public class RPCController
{
    private String rpcMethodNamePrefix = "dai.loyalty.";
    private List<String> rpcMethodNames = Arrays.asList(
            rpcMethodNamePrefix + "giftPoints",
            rpcMethodNamePrefix + "holdPoints");

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private HoldPointsRequestValidator holdPointsRequestValidator;

    @Autowired
    private JSONRPCRequestValidator jsonrpcRequestValidator;

    @Autowired
    private LoyaltyRepository loyaltyRepository;

    @Autowired
    private PointTransactionTypeRepository pointTransactionTypeRepository;

    @Autowired
    private PointTypeRepository pointTypeRepository;

    @RequestMapping(value = "/rpc", method = RequestMethod.POST, produces = "application/json")
    public String jsonRPCMethod(@Valid @RequestBody JSONRPCRequestObject requestObject, BindingResult errors)
    {
        jsonrpcRequestValidator.validate(requestObject, errors);
        if (errors.hasErrors())
        {
            throw new InvalidJSONRPCRequestException(requestObject.getId());
        }
        else
        {
            if (!rpcMethodNames.contains(requestObject.getMethod()))
            {
                throw new JSONRPCMethodNotFoundException(requestObject.getMethod(), requestObject.getId());
            }

            JSONRPCResponseObject responseObject = new JSONRPCResponseObject();
            responseObject.setSuccess(false);
            responseObject.setId(requestObject.getId());
            responseObject.getError().setCode(-32601);
            responseObject.getError().setMessage("Method no found.");
            return responseObject.toJSONString();
        }
    }

    @RequestMapping(value = "/rpc/v00001/holdPoints", method = RequestMethod.POST, produces = "application/json")
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

    @RequestMapping(value = "/rpc/v00001/giftPoints", method = RequestMethod.POST, produces = "application/json")
    public JSONRPCResponseObject giftPointsForOrderByOrderCodeLoyaltyCodeDivisionNameCompanyName(@Valid @RequestBody JSONRPCRequestObject requestObject, BindingResult errors)
// This is how you do query parameters instead of request body JSON
//                                    @Param("orderCode") String orderCode,
//                                    @Param("orderDivisionName") String orderDivisionName,
//                                    @Param("orderCompanyName") String orderCompanyName,
//                                    @Param("loyaltyCode") String loyaltyCode,
//                                    @Param("loyaltyDivisionName") String loyaltyDivisionName,
//                                    @Param("loyaltyCompanyName") String loyaltyCompanyName,
//                                    @Param("points") Long points)
    {
        jsonrpcRequestValidator.validate(requestObject, errors);
        if (errors.hasErrors())
        {
            throw new InvalidJSONRPCRequestException(requestObject.getId());
        }
        else
        {
            return addPointsForOrderByOrderCodeLoyaltyCodeDivisionNameCompanyName(requestObject, errors, PointType.availablePointType, PointTransactionType.giftedPointTransactionType);
        }
    }

    private JSONRPCResponseObject addPointsForOrderByOrderCodeLoyaltyCodeDivisionNameCompanyName(@Valid @RequestBody JSONRPCRequestObject requestObject, BindingResult errors, String pointType, String pointTransactionType)
    {
        String loyaltyCode = requestObject.getParams().get("loyaltyCode");
        String loyaltyDivisionName = requestObject.getParams().get("loyaltyDivisionName");
        String loyaltyCompanyName = requestObject.getParams().get("loyaltyCompanyName");

        if (loyaltyCode == null || loyaltyDivisionName == null || loyaltyCompanyName == null)
        {
            JSONRPCResponseObject responseObject = new JSONRPCResponseObject();
            responseObject.setSuccess(false);
            responseObject.setId(requestObject.getId());
            responseObject.getError().setCode(-32600);
            responseObject.getError().setMessage("loyaltyCode/loyaltyDivisionName/loyaltyCompanyName param(s) are missing.");
            return responseObject;
        }

        List<Loyalty> loyaltyList = loyaltyRepository.findByLoyaltyCodeDivisionNameCompanyName(loyaltyCode, loyaltyDivisionName, loyaltyCompanyName);
        if (loyaltyList.isEmpty() || loyaltyList.size() > 1)
        {
            throw new LoyaltyNotFoundException(loyaltyCode, loyaltyDivisionName, loyaltyCompanyName, requestObject.getId());
        }

        //entityManager.getTransaction().begin();

        BigDecimal dollarValue;
        try
        {
            String dollarValueString = requestObject.getParams().get("dollarValue");
            dollarValue = new BigDecimal(dollarValueString);
        }
        catch(Exception ex)
        {
            JSONRPCResponseObject responseObject = new JSONRPCResponseObject();
            responseObject.setSuccess(false);
            responseObject.setId(requestObject.getId());
            responseObject.getError().setCode(-32600);
            responseObject.getError().setMessage("dollarValue param is bad or missing.");
            return responseObject;
        }

        PointTransactionMaster ptm = new PointTransactionMaster();
        ptm.setCreatedDate(new Date());
        ptm.setDollarValue(dollarValue);
        List<PointTransactionType> pttl = pointTransactionTypeRepository.findByName(pointTransactionType);
        ptm.setPointTransactionType(pttl.get(0));
        entityManager.persist(ptm);
        //entityManager.flush();

        Integer points;
        try
        {
            String pointsString = requestObject.getParams().get("points");
            points = new Integer(pointsString);
        }
        catch(Exception ex)
        {
            JSONRPCResponseObject responseObject = new JSONRPCResponseObject();
            responseObject.setSuccess(false);
            responseObject.setId(requestObject.getId());
            responseObject.getError().setCode(-32600);
            responseObject.getError().setMessage("points param is bad or missing.");
            return responseObject;
        }

        PointTransactionDetail ptd = new PointTransactionDetail();
        ptd.setLoyalty(loyaltyList.get(0));
        ptd.setPointTransactionMaster(ptm);
        ptd.setPoints(points);
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
            ro.setId(requestObject.getId());
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

    @ExceptionHandler(LoyaltyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public JSONRPCResponseObject loyaltyNotFound(LoyaltyNotFoundException ex)
    {
        return ex.toJSONRPCResponseObject();
    }

    @ExceptionHandler(InvalidJSONRPCRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JSONRPCResponseObject invalidJSONRPCRequest(InvalidJSONRPCRequestException ex)
    {
        return ex.toJSONRPCResponseObject();
    }

    @ExceptionHandler(JSONRPCMethodNotFoundException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public JSONRPCResponseObject jsonRPCMethodNotFound(JSONRPCMethodNotFoundException ex)
    {
        return ex.toJSONRPCResponseObject();
    }

}
