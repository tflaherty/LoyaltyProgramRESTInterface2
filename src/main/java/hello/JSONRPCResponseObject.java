package hello;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.corba.se.spi.orbutil.fsm.Guard;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by Tom on 9/22/2016.
 */
public class JSONRPCResponseObject
{
    public class ErrorObject
    {
        private int code;
        private String message;
        private ObjectNode data;

        public ErrorObject(ObjectMapper objectMapper)
        {
            data = objectMapper.createObjectNode();
        }

        public void addKeyValuePairToData(String key, int value)
        {
            data.put(key, value);
        }
        public void addKeyValuePairToData(String key, long value)
        {
            data.put(key, value);
        }
        public void addKeyValuePairToData(String key, double value)
        {
            data.put(key, value);
        }
        public void addKeyValuePairToData(String key, String value)
        {
            data.put(key, value);
        }
        public void addKeyValuePairToData(String key, boolean value)
        {
            data.put(key, value);
        }

        public int getCode()
        {
            return code;
        }
        public void setCode(int code)
        {
            this.code = code;
        }

        public String getMessage()
        {
            return message;
        }
        public void setMessage(String message)
        {
            this.message = message;
        }

        public ObjectNode getData()
        {
            return data;
        }
        public void setData(ObjectNode data)
        {
            this.data = data;
        }

        public ObjectNode toObjectNode()
        {
            ObjectNode rootNode = objectMapper.createObjectNode();
            rootNode.put("code", getCode());
            rootNode.put("message", getMessage());
            if (getData().size() != 0)
            {
                rootNode.put("data", getData());
            }

            return rootNode;
        }
    }

    private String jsonrpc;
    private long id;
    private boolean success;
    private ObjectMapper objectMapper = new ObjectMapper();
    private ObjectNode result = objectMapper.createObjectNode();
    private ErrorObject error = new ErrorObject(objectMapper);

    public String toJSONString()
    {
        JsonFactory jsonFactory = new JsonFactory();
        JsonGenerator jsonGenerator;

        StringWriter stringWriter = new StringWriter();
        try
        {
            /* alternate way to generate JSON */
            /*
            jsonGenerator = jsonFactory.createGenerator(stringWriter);
            jsonGenerator.useDefaultPrettyPrinter();

            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("jsonrpc", getJsonrpc());

            if (isSuccess())
            {
                jsonGenerator.writeStringField("result", getResult());
            }
            else
            {
                jsonGenerator.writeStringField("error", getError().toJSONString());
            }


            jsonGenerator.writeNumberField("id", getId());

            jsonGenerator.writeEndObject();
            jsonGenerator.close();
            return stringWriter.toString();
            */

            ObjectNode rootNode = objectMapper.createObjectNode();
            rootNode.put("jsonrpc", getJsonrpc());

            if (isSuccess())
            {
                rootNode.put("result", result);
            }
            else
            {
                rootNode.put("error", error.toObjectNode());
            }

            rootNode.put("id", getId());
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    public void addKeyValuePairToResult(String key, int value)
    {
        result.put(key, value);
    }
    public void addKeyValuePairToResult(String key, long value)
    {
        result.put(key, value);
    }
    public void addKeyValuePairToResult(String key, double value)
    {
        result.put(key, value);
    }
    public void addKeyValuePairToResult(String key, String value)
    {
        result.put(key, value);
    }
    public void addKeyValuePairToResult(String key, boolean value)
    {
        result.put(key, value);
    }

    public String getJsonrpc()
    {
        return "2.0";
    }

    public ObjectNode getResult()
    {
        return result;
    }
    public void setResult(ObjectNode result)
    {
        this.result = result;
    }

    public ErrorObject getError()
    {
        return error;
    }
    public void setError(ErrorObject error)
    {
        this.error = error;
    }

    public long getId()
    {
        return id;
    }
    public void setId(long id)
    {
        this.id = id;
    }

    public boolean isSuccess()
    {
        return success;
    }
    public void setSuccess(boolean success)
    {
        this.success = success;
    }
}
