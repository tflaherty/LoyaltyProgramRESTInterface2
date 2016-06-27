package hello;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by Tom on 6/27/2016.
 */

@RepositoryRestResource(collectionResourceRel = "divisions", path = "divisions")
public interface DivisionRepository extends PagingAndSortingRepository<Division, Long> {
    List<Division> findByName(@Param("name") String name);
}
