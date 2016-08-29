package hello;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.List;

/**
 * Created by Tom on 6/23/2016.
 */
@RepositoryRestResource(collectionResourceRel = "loyalties", path = "loyalties")
public interface LoyaltyRepository extends PagingAndSortingRepository<Loyalty, Long> {
    //List<Loyalty> findByLoyaltyCode(@Param("loyaltyCode") String code);

    // this works
    @Query("select l from Loyalty l where l.loyaltyCode = :loyaltyCode")
    List<Loyalty> findByLoyaltyA(@Param("loyaltyCode") String loyaltyCode);

    // this doesn't
    @Query("select l from Loyalty l INNER JOIN l.division d where l.loyaltyCode = :loyaltyCode and d.name = :divisionName")
    List<Loyalty> findByLoyaltyX(@Param("loyaltyCode") String loyaltyCode, @Param("divisionName") String divisionName);

    // this doesn't
    @Query("select l from Loyalty l INNER JOIN l.division d INNER JOIN d.company c where l.loyaltyCode = :loyaltyCode and d.name = :divisionName and c.name = :companyName")
    List<Loyalty> findByLoyaltyCodeDivisionNameCompanyName(@Param("loyaltyCode") String loyaltyCode, @Param("divisionName") String divisionName, @Param("companyName") String companyName);
}
