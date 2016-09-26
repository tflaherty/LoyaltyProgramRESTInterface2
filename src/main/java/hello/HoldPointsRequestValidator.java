package hello;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
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
        HoldPointsRequest holdPointsRequest = (HoldPointsRequest) target;

        boolean orderIdExists = holdPointsRequest.getOrderId() != null;
        boolean orderCodeExists = holdPointsRequest.getOrderCode() != null && !holdPointsRequest.getOrderCode().trim().isEmpty();
        boolean orderDivisionNameExists = holdPointsRequest.getOrderDivisionName() != null && !holdPointsRequest.getOrderDivisionName().trim().isEmpty();
        boolean orderCompanyNameExists = holdPointsRequest.getOrderCompanyName() != null && !holdPointsRequest.getOrderCompanyName().trim().isEmpty();
        boolean orderSiteCodeExists = holdPointsRequest.getOrderSiteCode() != null && !holdPointsRequest.getOrderSiteCode().trim().isEmpty();
        boolean sufficientOrderInfoExists = orderIdExists || (orderCodeExists && (orderSiteCodeExists || (orderCompanyNameExists && orderDivisionNameExists)));

        super.validate(target, errors);

        // make sure we have enough information to get to the order
        if (!sufficientOrderInfoExists)
        {
            errors.reject("hold point request", "Insufficient order information.");
        }

        // now make sure that we have a dollar value
        ValidationUtils.rejectIfEmpty(errors, "dollarValue", "dollarValue.required");
    }
}
