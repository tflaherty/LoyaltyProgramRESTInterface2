package hello;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "persons", path = "persons")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long>
{

    List<Person> findByLastName(@Param("lastName") String lastName);

    List<Person> findByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    // native query
    @Query(value = "select p.* from Person p inner join Division d on d.id = p.division_id inner join Company c on c.id = d.company_id where p.customer_code = :customerCode and d.name = :divisionName and c.name = :companyName", nativeQuery = true)
    List<Person> findByCustomerCodeDivisionNameCompanyName(@Param("customerCode") String customerCode,
                                                           @Param("divisionName") String divisionName,
                                                           @Param("companyName") String companyName);

}
