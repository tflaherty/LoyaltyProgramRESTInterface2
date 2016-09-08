package hello;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by Tom on 6/27/2016.
 */

@RepositoryRestResource(collectionResourceRel = "divisions", path = "divisions")
public interface DivisionRepository extends PagingAndSortingRepository<Division, Long>
{
    List<Division> findByName(@Param("name") String name);

    // the following query didn't work so apparently you can't use an 'automatically generated' query that uses a Transient field.  Too bad!
    //List<Division> findByNameAndCompanyName(@Param("name") String name, @Param("companyName") String companyName);
    @Query(value = "select d.* from Division d inner join Company c on c.id = d.company_id where d.name = :name and c.name = :companyName", nativeQuery = true)
    List<Division> findByNameAndCompanyName(@Param("name") String name,
                                             @Param("companyName") String companyName);

    // just declare that this returns a Division instead of List<Division> to return just one Division
    List<Division> findBySiteCode(@Param("siteCode") String siteCode);
}
