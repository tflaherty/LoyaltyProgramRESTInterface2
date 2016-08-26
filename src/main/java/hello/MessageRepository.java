package hello;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by Tom on 8/26/2016.
 */
@RepositoryRestResource(collectionResourceRel = "messages", path = "messages")
public interface MessageRepository extends PagingAndSortingRepository<Message, Long>
{
}
