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
public interface LoyaltyRepository extends PagingAndSortingRepository<Loyalty, Long>
{
    //List<Loyalty> findByLoyaltyCode(@Param("loyaltyCode") String code);

    // this works
    // non-native query
    //@Query("select l from Loyalty l where l.loyaltyCode = :loyaltyCode")
    //List<Loyalty> findByLoyaltyA(@Param("loyaltyCode") String loyaltyCode);

    // non-native query
    //@Query("select l from Loyalty l inner join l.division d inner join d.company c where l.loyaltyCode = :loyaltyCode and d.name = :divisionName and c.name = :companyName")
    //List<Loyalty> findByLoyaltyCodeDivisionNameCompanyNameNonNative(@Param("loyaltyCode") String loyaltyCode, @Param("divisionName") String divisionName, @Param("companyName") String companyName);

    // native query
    @Query(value = "select l.* from Loyalty l inner join Division d on d.id = l.division_id inner join Company c on c.id = d.company_id where l.loyalty_code = :loyaltyCode and d.name = :divisionName and c.name = :companyName", nativeQuery = true)
    List<Loyalty> findByLoyaltyCodeDivisionNameCompanyName(@Param("loyaltyCode") String loyaltyCode,
                                                           @Param("divisionName") String divisionName,
                                                           @Param("companyName") String companyName);

    @Query(value = "select l.* from Loyalty l inner join Division d on d.id = l.division_id where l.loyalty_code = :loyaltyCode and d.site_code = :siteCode", nativeQuery = true)
    List<Loyalty> findByLoyaltyCodeSiteCode(@Param("loyaltyCode") String loyaltyCode,
                                            @Param("siteCode") String siteCode);

    @Query(value = "select l.* from Loyalty l inner join Division d on d.id = l.division_id inner join Company c on c.id = d.company_id where d.name = :divisionName and c.name = :companyName and exists (select * from Person p inner join loyalty_person_map lpm on lpm.loyalty_id = l.id and p.customer_code = :customerCode)", nativeQuery = true)
    List<Loyalty> findByCustomerCodeDivisionNameCompanyName(@Param("customerCode") String customerCode,
                                                           @Param("divisionName") String divisionName,
                                                           @Param("companyName") String companyName);

    @Query(value = "select l.* from Loyalty l inner join Division d on d.id = l.division_id where d.site_code = :siteCode and exists (select p.* from Person p inner join loyalty_person_map lpm on lpm.loyalty_id = l.id and p.customer_code = :customerCode)", nativeQuery = true)
    List<Loyalty> findByCustomerCodeSiteCode(@Param("customerCode") String customerCode,
                                            @Param("siteCode") String siteCode);

}
