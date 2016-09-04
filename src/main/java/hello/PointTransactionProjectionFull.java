package hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

/**
 * Created by Tom on 6/27/2016.
 */
@Projection(name = "full", types = {PointTransaction.class})
public interface PointTransactionProjectionFull {
    @Value("#{target.getDateCreated()}")
    public String getDateCreated();

    @Value("#{target.getLoyalty().getLoyaltyCode()}")
    public String getLoyaltyCode();

    @Value("#{target.getPoints()}")
    public String getPoints();

    @Value("#{target.getPointTransactionType().getName()}")
    public String getPointTransactionType();

    @Value("#{target.getLoyalty().getDivision().getCompany().getName()}")
    public String getCompanyName();

    @Value("#{target.getLoyalty().getDivision().getName()}")
    public String getDivisionName();

    @Value("#{target.getExpirationDate()}")
    public String getExpirationDate();


}
