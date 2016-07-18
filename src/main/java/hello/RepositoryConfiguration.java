package hello;

/**
 * Created by Tom on 7/18/2016.
 */

/*
 * This was required to include the id in the HATEOAS output, thereby
 * letting $resource usage in angularjs to work properly!  My version
 * of angularjs/$resource was not designed to work with HATEOAS output
 */
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class RepositoryConfiguration extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Company.class);
        config.exposeIdsFor(Person.class);
        config.exposeIdsFor(Loyalty.class);
        config.exposeIdsFor(PointTransaction.class);
        config.exposeIdsFor(PointTransactionType.class);
    }
}