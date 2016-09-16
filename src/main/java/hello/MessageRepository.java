package hello;

import net.sourceforge.jtds.jdbc.DateTime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.List;

import hello.Message;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Created by Tom on 8/26/2016.
 */
@RepositoryRestResource(collectionResourceRel = "messages", path = "messages")
public interface MessageRepository extends PagingAndSortingRepository<Message, Long>
{
    List<Message> findByLoyalty(Loyalty loyalty);

    @Query("select m from Message m INNER JOIN m.loyalty l where l.id = :loyaltyId order by m.dateReceived")
    Collection<Message> findByLoyaltyId(@Param("loyaltyId") long loyaltyId);

    @Query("select m from Message m INNER JOIN m.loyalty l where l.loyaltyCode = :loyaltyCode order by m.dateReceived")
    Collection<Message> findByLoyaltyCode(@Param("loyaltyCode") String loyaltyCode);

    @Query("select m from Message m INNER JOIN m.loyalty l INNER JOIN l.division d INNER JOIN d.company c where l.loyaltyCode = :loyaltyCode and d.name = :divisionName and c.name = :companyName order by m.dateReceived")
    Collection<Message> findByLoyaltyCodeDivisionNameCompanyName(@Param("loyaltyCode") String loyaltyCode,
                                                                 @Param("divisionName") String divisionName,
                                                                 @Param("companyName") String companyName);

    @Query("select count(*) from Message m INNER JOIN m.loyalty l where l.id = :loyaltyId and m.dateReceived > to_timestamp(:sinceDate, 'MM/DD/YYYY')")
    long findNewMessageCountByLoyaltyIdAndSinceDate(@Param("loyaltyId") long loyaltyId, @Param("sinceDate") String sinceDate);

    /*
@Query("select s from Schedule s where s.market.marketId = :marketId and s.locale.localeId = :localeId and s.offline >= :offline order by s.placement, s.slot, s.online")
    Collection<Schedule> findByMarket(@Param("marketId") Integer marketId, @Param("localeId") Integer localeId, @Param("offline") Date offline);
     */
}
