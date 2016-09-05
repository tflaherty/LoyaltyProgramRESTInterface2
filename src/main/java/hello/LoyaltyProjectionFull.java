package hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

/**
 * Created by Tom on 6/27/2016.
 */
@Projection(name = "full", types = {Loyalty.class})
public interface LoyaltyProjectionFull
{
    @Value("#{target.getLoyaltyCode()}")
    public String getLoyaltyCode();

    @Value("#{target.getAvailablePoints()}")
    public String getAvailablePoints();

    @Value("#{target.getHeldPoints()}")
    public String getHeldPoints();

    @Value("#{target.getDivision().getCompany().getName()}")
    public String getCompanyName();

    @Value("#{target.getDivision().getName()}")
    public String getDivisionName();

    @Value("#{target.getDateCreated()}")
    public String getDateCreated();
}
