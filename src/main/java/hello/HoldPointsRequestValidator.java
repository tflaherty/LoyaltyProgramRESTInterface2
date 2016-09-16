package hello;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Tom on 9/15/2016.
 */
@Component
public class HoldPointsRequestValidator implements Validator
{
    @Override
    public boolean supports(Class<?> clazz)
    {
        return HoldPointsRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors)
    {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loyaltyId", "id.required");

        HoldPointsRequest holdPointsRequest = (HoldPointsRequest) target;

        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "role", "role.required");
    }
}
