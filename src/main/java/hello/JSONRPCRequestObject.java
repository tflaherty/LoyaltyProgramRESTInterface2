package hello;

import java.util.Map;

/**
 * Created by Tom on 9/22/2016.
 */
public class JSONRPCRequestObject
{
    private String jsonrpc;
    private String method;
    private Map<String, String> params;
    private String id;

    public String getJsonrpc()
    {
        return jsonrpc;
    }
    public void setJsonrpc(String jsonrpc)
    {
        this.jsonrpc = jsonrpc;
    }

    public String getMethod()
    {
        return method;
    }
    public void setMethod(String method)
    {
        this.method = method;
    }

    public Map<String, String> getParams()
    {
        return params;
    }
    public void setParams(Map<String, String> params)
    {
        this.params = params;
    }

    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
}
