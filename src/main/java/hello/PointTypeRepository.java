package hello;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by Tom on 9/21/2016.
 */
@RepositoryRestResource(collectionResourceRel = "pointTypes", path = "pointTypes")
public interface PointTypeRepository extends PagingAndSortingRepository<PointType, Long>
{
    List<PointType> findByName(@Param("name") String name);
}
