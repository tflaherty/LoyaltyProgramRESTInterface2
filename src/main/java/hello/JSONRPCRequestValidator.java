package hello;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Tom on 9/22/2016.
 */
@Component
public class JSONRPCRequestValidator implements Validator
{
    @Override
    public boolean supports(Class<?> clazz)
    {
        return JSONRPCResponseObject.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors)
    {
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loyaltyId", "id.required");

        JSONRPCRequestObject jsonrpcRequestObject = (JSONRPCRequestObject) target;

        if (jsonrpcRequestObject.getJsonrpc() == null || !jsonrpcRequestObject.getJsonrpc().equals("2.0"))
        {
            errors.reject("invalid.jsonrpc", "jsonrpc must be 2.0");
        }

        if (jsonrpcRequestObject.getMethod() == null || !jsonrpcRequestObject.getMethod().startsWith("dai.loyalty."))
        {
            errors.reject("invalid.method", "method must exist and begin with \"dai.loyalty.\"");
        }

        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "role", "role.required");
    }

}
