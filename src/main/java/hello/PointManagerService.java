package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Tom on 9/29/2016.
 */
@Service
@Transactional
@Repository
public class PointManagerService
{
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PointTransactionMasterRepository pointTransactionMasterRepository;

    @Autowired
    private PointTransactionDetailRepository pointTransactionDetailRepository;

    @Autowired
    private PointTransactionTypeRepository pointTransactionTypeRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PointTypeRepository pointTypeRepository;

    @Autowired
    private PointsRepository pointsRepository;

    public PointTransactionMaster holdPoints(
            Order order,
            Loyalty loyalty,
            int points,
            BigDecimal dollarValue,
            Date availabilityExpirationDate,
            Date holdExpirationDate)
    {
        // now create the point transaction master and 2 point transaction details
        //entityManager.getTransaction().begin();

        PointTransactionMaster pointTransactionMaster = new PointTransactionMaster();
        pointTransactionMaster.setCreatedDate(new Date());
        pointTransactionMaster.setDollarValue(dollarValue);
        List<PointTransactionType> pttl = pointTransactionTypeRepository.findByName(PointTransactionType.heldPointTransactionType);
        pointTransactionMaster.setPointTransactionType(pttl.get(0));
        entityManager.persist(pointTransactionMaster);
        //entityManager.flush();

        PointTransactionDetail sourcePointTransactionDetail = new PointTransactionDetail();
        sourcePointTransactionDetail.setLoyalty(loyalty);
        sourcePointTransactionDetail.setPointTransactionMaster(pointTransactionMaster);
        sourcePointTransactionDetail.setPoints(-1 * points);
        List<PointType> ptl = pointTypeRepository.findByName(PointType.availablePointType);
        sourcePointTransactionDetail.setPointType(ptl.get(0));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(calendar.getTime());
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        Date nextWeek = calendar.getTime();
        sourcePointTransactionDetail.setAvailabilityExpirationDate(nextWeek);
        entityManager.persist(sourcePointTransactionDetail);

        PointTransactionDetail destPointTransactionDetail = new PointTransactionDetail();
        destPointTransactionDetail.setLoyalty(loyalty);
        destPointTransactionDetail.setPointTransactionMaster(pointTransactionMaster);
        destPointTransactionDetail.setPoints(points);
        ptl = pointTypeRepository.findByName(PointType.heldPointType);
        destPointTransactionDetail.setPointType(ptl.get(0));
        calendar.setTime(calendar.getTime());
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        nextWeek = calendar.getTime();
        destPointTransactionDetail.setAvailabilityExpirationDate(nextWeek);
        entityManager.persist(destPointTransactionDetail);

        return pointTransactionMaster;
    }

    public void modifyPoints(
            long loyaltyId,
            int points,
            long pointTypeId,
            Date availabilityExpirationDate,
            Date holdExpirationDate)
    {
        return;
    }

}
