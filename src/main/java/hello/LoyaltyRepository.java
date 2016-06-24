package hello;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by Tom on 6/23/2016.
 */
@RepositoryRestResource(collectionResourceRel = "loyalties", path = "loyalties")
public interface LoyaltyRepository extends PagingAndSortingRepository<Loyalty, Long> {
    List<Loyalty> findByLoyaltyCode(@Param("code") String code);
}
