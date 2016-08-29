package hello;

import org.hibernate.annotations.NamedNativeQuery;
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
    // non-native query
    //@Query("select l from Loyalty l where l.loyaltyCode = :loyaltyCode")
    //List<Loyalty> findByLoyaltyA(@Param("loyaltyCode") String loyaltyCode);

    @Query(value = "select l from Loyalty l inner join Division d on d.id = l.division_id inner join Company c on c.id = d.company_id where l.loyalty_code = :loyaltyCode and d.name = :divisionName and c.name = :companyName", nativeQuery = true)
    List<Loyalty> findByLoyaltyCodeDivisionNameCompanyName(@Param("loyaltyCode") String loyaltyCode, @Param("divisionName") String divisionName, @Param("companyName") String companyName);

}
