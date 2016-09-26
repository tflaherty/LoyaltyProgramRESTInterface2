package hello;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Tom on 9/26/2016.
 */
public class RESTAPIError
{
    private HttpStatus status;
    private String message;
    private List<String> errors;

    public RESTAPIError(HttpStatus status, String message, List<String> errors)
    {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public RESTAPIError(HttpStatus status, String message, String error)
    {
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }

    public HttpStatus getStatus()
    {
        return status;
    }
    public void setStatus(HttpStatus status)
    {
        this.status = status;
    }

    public String getMessage()
    {
        return message;
    }
    public void setMessage(String message)
    {
        this.message = message;
    }

    public List<String> getErrors()
    {
        return errors;
    }
    public void setErrors(List<String> errors)
    {
        this.errors = errors;
    }
}
