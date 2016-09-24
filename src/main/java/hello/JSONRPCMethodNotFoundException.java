package hello;

/**
 * Created by Tom on 9/24/2016.
 */
public class JSONRPCMethodNotFoundException extends RuntimeException
{
    private String jsonRPCMessageId = null;
    private String method = null;
    private String jsonRPCResponseObjectErrorMessage = "";

    public JSONRPCMethodNotFoundException(String method, String jsonRPCMessageId)
    {
        this.method = method;
        this.jsonRPCMessageId = jsonRPCMessageId;
        // if I use \" to embed a double quote inside the string it outputs as \" and not "!
        // this seems to be because somewhere in the JSON encoding to a string this gets added in.  Sigh
        jsonRPCResponseObjectErrorMessage = "JSON RPC Request with id = " +
                "'" + jsonRPCMessageId + "'" +
                " has an invalid method = " +
                "'" + method + "'";
    }

    public String getJsonRPCMessageId()
    {
        return jsonRPCMessageId;
    }
    public void setJsonRPCMessageId(String jsonRPCMessageId)
    {
        this.jsonRPCMessageId = jsonRPCMessageId;
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
        return -32601;
    }

    public String getMethod()
    {
        return method;
    }
    public void setMethod(String method)
    {
        this.method = method;
    }

    public JSONRPCResponseObject toJSONRPCResponseObject()
    {
        JSONRPCResponseObject responseObject = new JSONRPCResponseObject();
        responseObject.setSuccess(false);
        responseObject.setId(getJsonRPCMessageId());
        responseObject.getError().setCode(getJsonRPCResponseObjectErrorCode());
        responseObject.getError().setMessage(getJsonRPCResponseObjectErrorMessage());
        return responseObject;
    }

}
