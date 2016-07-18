package hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

/**
 * Created by Tom on 7/18/2016.
 */
@Projection(name = "nonhal", types = {Company.class})
public interface CompanyProjectionNonHAL {
    @Value("#{target.getId()}")
    public String getId();

//    @Value("#{target.getName()}")
//    public String getName();
}
