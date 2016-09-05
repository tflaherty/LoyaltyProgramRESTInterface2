package hello;

/**
 * Created by Tom on 7/18/2016.
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

/**
 * Created by Tom on 6/27/2016.
 */
@Projection(name = "full", types = {Division.class})
public interface DivisionProjectionFull
{
    @Value("#{target.getCompany().getName()}")
    public String getCompanyName();

    @Value("#{target.getName()}")
    public String getName();

}
