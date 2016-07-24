package hello;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by Tom on 7/24/2016.
 */
@RepositoryRestResource(collectionResourceRel = "departments", path = "departments")
public interface DepartmentRepository extends PagingAndSortingRepository<Department, Long> {
    List<Department> findByName(@Param("name") String name);
}
