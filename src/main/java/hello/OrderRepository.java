package hello;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by Tom on 9/26/2016.
 */
@RepositoryRestResource(collectionResourceRel = "orders", path = "orders")
public interface OrderRepository extends PagingAndSortingRepository<Order, Long>
{
    @Query(value = "select o.* from Order o inner join Division d on d.id = o.division_id inner join Company c on c.id = d.company_id where o.order_code = :orderCode and d.name = :divisionName and c.name = :companyName", nativeQuery = true)
    List<Order> findByOrderCodeDivisionNameCompanyName(@Param("orderCode") String loyaltyCode,
                                                           @Param("divisionName") String divisionName,
                                                           @Param("companyName") String companyName);

    @Query(value = "select o.* from Order o inner join Division d on d.id = o.division_id where o.order_code = :orderCode and d.site_code = :siteCode", nativeQuery = true)
    List<Order> findByOrderCodeSiteCode(@Param("orderCode") String orderCode,
                                            @Param("siteCode") String siteCode);

}