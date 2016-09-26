package hello;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Tom on 9/15/2016.
 */
@Component
public class HoldPointsRequestValidator extends PointsRequestValidator
{
    @Override
    public boolean supports(Class<?> theClass)
    {
        return HoldPointsRequest.class.equals(theClass);
    }

    @Override
    public void validate(Object target, Errors errors)
    {
        super.validate(target, errors);

        //errors.reject("invalid.points", "points must exist");

        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loyaltyId", "id.required");

        //HoldPointsRequest holdPointsRequest = (HoldPointsRequest) target;

        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "role", "role.required");
    }
}
