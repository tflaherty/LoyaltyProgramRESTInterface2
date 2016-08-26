package hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

/**
 * Created by Tom on 8/26/2016.
 */
@Projection(name = "full", types = {Message.class})
public interface MessageProjectionFull
{
    @Value("#{target.getId()}")
    public long getId();

    @Value("#{target.getMessage()}")
    public String getMessage();

    @Value("#{target.getDateReceived()}")
    public Date getDateReceived();

    @Value("#{target.isRead()}")
    public boolean isRead();

    @Value("#{target.getMetadata()}")
    public String getMetadata();

    @Value("#{target.getLoyalty().getLoyaltyCode()}")
    public String getLoyaltyCode();

    @Value("#{target.getLoyalty().getId()}")
    public long getLoyaltyId();
}
