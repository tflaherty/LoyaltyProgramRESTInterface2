package hello;

/**
 * Created by Tom on 9/25/2016.
 */
public class InvalidHoldPointsRequestException extends RuntimeException
{
    private String jsonRPCResponseObjectErrorMessage = "";

    public InvalidHoldPointsRequestException(HoldPointsRequest holdPointsRequest)
    {
        // if I use \" to embed a double quote inside the string it outputs as \" and not "!
        // this seems to be because somewhere in the JSON encoding to a string this gets added in.  Sigh
        jsonRPCResponseObjectErrorMessage = "Invalid hold points request";
    }

    public String getJsonRPCResponseObjectErrorMessage()
    {
        return jsonRPCResponseObjectErrorMessage;
    }
    public void setJsonRPCResponseObjectErrorMessage(String jsonRPCResponseObjectErrorMessage)
    {
        this.jsonRPCResponseObjectErrorMessage = jsonRPCResponseObjectErrorMessage;
    }

    public int getJsonRPCResponseObjectErrorCode()
    {
        return -32600;
    }

    public JSONRPCResponseObject toJSONRPCResponseObject()
    {
        JSONRPCResponseObject responseObject = new JSONRPCResponseObject();
        responseObject.setSuccess(false);
        responseObject.getError().setCode(getJsonRPCResponseObjectErrorCode());
        responseObject.getError().setMessage(getJsonRPCResponseObjectErrorMessage());
        return responseObject;
    }
}
