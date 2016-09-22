package hello;

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
    public long holdPointsForOrderByOrderCodeLoyaltyCodeDivisionNameCompanyName(@Valid @RequestBody HoldPointsRequest holdPointsRequest, BindingResult errors)
// This is how you do query parameters instead of request body JSON
//                                    @Param("orderCode") String orderCode,
//                                    @Param("orderDivisionName") String orderDivisionName,
//                                    @Param("orderCompanyName") String orderCompanyName,
//                                    @Param("loyaltyCode") String loyaltyCode,
//                                    @Param("loyaltyDivisionName") String loyaltyDivisionName,
//                                    @Param("loyaltyCompanyName") String loyaltyCompanyName,
//                                    @Param("points") Long points)
    {
        holdPointsRequestValidator.validate(holdPointsRequest, errors);
        if (errors.hasErrors())
        {
            return -1L;
        }

        return 0L;
    }

    @RequestMapping(value = "v00001/giftPoints", method = RequestMethod.POST, produces = "application/json")
    public long giftPointsForOrderByOrderCodeLoyaltyCodeDivisionNameCompanyName(@Valid @RequestBody HoldPointsRequest hpr, BindingResult errors)
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
            return -1L;
        }

        if (hpr.getLoyaltyCompanyName() != null
                && !hpr.getLoyaltyCompanyName().trim().isEmpty()
                && hpr.getLoyaltyDivisionName() != null
                && !hpr.getLoyaltyDivisionName().trim().isEmpty()
                && hpr.getLoyaltyCode() != null
                && !hpr.getLoyaltyCode().trim().isEmpty()
                && hpr.getDollarValue() != null
                && hpr.getPoints() != null)
        {
            List<Loyalty> loyaltyList = loyaltyRepository.findByLoyaltyCodeDivisionNameCompanyName(hpr.getLoyaltyCode(), hpr.getLoyaltyDivisionName(), hpr.getLoyaltyCompanyName());
            if (loyaltyList.isEmpty() || loyaltyList.size() > 1)
            {
                return -2L;
            }

            //entityManager.getTransaction().begin();
            PointTransactionMaster ptm = new PointTransactionMaster();
            ptm.setCreatedDate(new Date());
            ptm.setDollarValue(hpr.getDollarValue());
            List<PointTransactionType> pttl = pointTransactionTypeRepository.findByName(PointTransactionType.giftedPointTransactionType);
            ptm.setPointTransactionType(pttl.get(0));
            entityManager.persist(ptm);
            //entityManager.flush();

            PointTransactionDetail ptd = new PointTransactionDetail();
            ptd.setLoyalty(loyaltyList.get(0));
            ptd.setPointTransactionMaster(ptm);
            ptd.setPoints(hpr.getPoints());
            List<PointType> ptl = pointTypeRepository.findByName(PointType.availablePointType);
            ptd.setPointType(ptl.get(0));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(calendar.getTime());
            calendar.add(Calendar.DAY_OF_YEAR, 7);
            Date nextWeek = calendar.getTime();
            ptd.setAvailabilityExpirationDate(nextWeek);
            entityManager.persist(ptd);
            //entityManager.flush();

            //entityManager.getTransaction().commit();

            return ptm.getId();
        }

        return -100L;

/*
        Loyalty loyalty = entityManager.find(Loyalty.class, (long) 1);
        Message message = new Message();
        message.setLoyalty(loyalty);
        Date now = new Date();
        message.setMessage("\'10 minute\' message auto inserted on " + now.toString());
        message.setDateReceived(now);
        entityManager.persist(message);

*/
    }
}
