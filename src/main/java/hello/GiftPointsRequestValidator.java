package hello;

import org.springframework.stereotype.Component;

/**
 * Created by Tom on 9/26/2016.
 */
@Component
public class GiftPointsRequestValidator extends PointsRequestValidator
{
    @Override
    public boolean supports(Class<?> theClass)
    {
        return GiftPointsRequest.class.equals(theClass);
    }

}
