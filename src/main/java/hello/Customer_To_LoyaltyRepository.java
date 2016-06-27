package hello;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by Tom on 6/27/2016.
 */
@RepositoryRestResource(collectionResourceRel = "customers", path = "customers")
public interface Customer_To_LoyaltyRepository extends PagingAndSortingRepository<Customer_To_Loyalty, Long> {
}
