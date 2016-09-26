package hello;

import org.springframework.validation.BindingResult;

/**
 * Created by Tom on 9/26/2016.
 */
public class InvalidRequestException extends RuntimeException
{
    private BindingResult errors;

    public InvalidRequestException(BindingResult errors)
    {
        this.errors = errors;
    }

    public BindingResult getErrors()
    {
        return errors;
    }
    public void setErrors(BindingResult errors)
    {
        this.errors = errors;
    }
}
