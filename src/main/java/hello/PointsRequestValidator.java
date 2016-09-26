package hello;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Tom on 9/26/2016.
 */
@Component
public class PointsRequestValidator implements Validator
{
    @Override
    public boolean supports(Class<?> theClass)
    {
        return PointsRequest.class.equals(theClass);
    }

    @Override
    public void validate(Object target, Errors errors)
    {
        PointsRequest pointsRequest = (PointsRequest) target;

        boolean loyaltyIdExists = pointsRequest.getLoyaltyId() != null;
        boolean loyaltyCodeExists = pointsRequest.getLoyaltyCode() != null && !pointsRequest.getLoyaltyCode().trim().isEmpty();
        boolean loyaltyDivisionNameExists = pointsRequest.getLoyaltyDivisionName() != null && !pointsRequest.getLoyaltyDivisionName().trim().isEmpty();
        boolean loyaltyCompanyNameExists = pointsRequest.getLoyaltyCompanyName() != null && !pointsRequest.getLoyaltyCompanyName().trim().isEmpty();
        boolean loyaltySiteCodeExists = pointsRequest.getLoyaltySiteCode() != null && !pointsRequest.getLoyaltySiteCode().trim().isEmpty();
        boolean sufficientLoyaltyInfoExists = loyaltyIdExists || (loyaltyCodeExists && (loyaltySiteCodeExists || (loyaltyCompanyNameExists && loyaltyDivisionNameExists)));

        boolean customerIdExists = pointsRequest.getCustomerId() != null;
        boolean customerCodeExists = pointsRequest.getCustomerCode() != null && !pointsRequest.getCustomerCode().trim().isEmpty();
        boolean customerDivisionNameExists = pointsRequest.getCustomerDivisionName() != null && !pointsRequest.getCustomerDivisionName().trim().isEmpty();
        boolean customerCompanyNameExists = pointsRequest.getCustomerCompanyName() != null && !pointsRequest.getCustomerCompanyName().trim().isEmpty();
        boolean customerSiteCodeExists = pointsRequest.getCustomerSiteCode() != null && !pointsRequest.getCustomerSiteCode().trim().isEmpty();
        boolean sufficientCustomerInfoExists = customerIdExists || (customerCodeExists && (customerSiteCodeExists || (customerCompanyNameExists && customerDivisionNameExists)));

        // make sure we have enough information to get to the loyalty account
        if (!sufficientCustomerInfoExists && !sufficientLoyaltyInfoExists)
        {
            errors.reject("point request", "Insufficient loyalty or customer information.");
        }

        // now make sure that we have a point value
        ValidationUtils.rejectIfEmpty(errors, "points", "points.required");

        //errors.reject("invalid.points", "points must exist");
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loyaltyId", "id.required");
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "role", "role.required");
    }
}
