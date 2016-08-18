package hello;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/*")
public class TestController
{
    @RequestMapping(value="messages", method=RequestMethod.GET, produces="application/json")
    public String messages() {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();

        ObjectNode rootObjectNode = mapper.createObjectNode();
        ObjectNode embeddedObjectNode = mapper.createObjectNode();

        ObjectNode objectNode1 = mapper.createObjectNode();
        objectNode1.put("date received", "8/1/2016");
        objectNode1.put("message", "You have received 100 bonus points.");
        objectNode1.put("points", "100");
        arrayNode.add(objectNode1);

        ObjectNode objectNode2 = mapper.createObjectNode();
        objectNode2.put("date received", "8/5/2016");
        objectNode2.put("message", "Some of your points will be expiring soon.");
        objectNode2.put("points", "100");
        arrayNode.add(objectNode2);

        ObjectNode objectNode3 = mapper.createObjectNode();
        objectNode3.put("date received", "8/12/2016");
        objectNode3.put("message", "Your points received on 3/1/16 have expired.");
        objectNode3.put("points", "100");
        arrayNode.add(objectNode3);

        ObjectNode objectNode4 = mapper.createObjectNode();
        objectNode4.put("date received", "8/15/2016");
        objectNode4.put("message", "This weekend is a double point event!");
        objectNode4.put("points", "");
        arrayNode.add(objectNode4);

        embeddedObjectNode.put("messages", arrayNode);
        rootObjectNode.put("_embedded", embeddedObjectNode);

        try
        {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootObjectNode);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping(value="pointTransactions", method=RequestMethod.GET, produces="application/json")
    public String pointTransactions() {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();

        ObjectNode rootObjectNode = mapper.createObjectNode();
        ObjectNode embeddedObjectNode = mapper.createObjectNode();

        ObjectNode objectNode1 = mapper.createObjectNode();
        objectNode1.put("date", "8/1/2016");
        objectNode1.put("source", "DAI Bonus Points");
        objectNode1.put("transaction type", "added");
        objectNode1.put("points", "100");
        arrayNode.add(objectNode1);

        ObjectNode objectNode2 = mapper.createObjectNode();
        objectNode2.put("date", "8/12/2016");
        objectNode2.put("source", "Your points received on 3/1/16 have expired.");
        objectNode2.put("transaction type", "expired");
        objectNode2.put("points", "35");
        arrayNode.add(objectNode2);

        ObjectNode objectNode3 = mapper.createObjectNode();
        objectNode3.put("date", "8/17/2016");
        objectNode3.put("source", "Order #12345");
        objectNode3.put("transaction type", "redeemed");
        objectNode3.put("points", "17");
        arrayNode.add(objectNode3);

        ObjectNode objectNode4 = mapper.createObjectNode();
        objectNode4.put("date", "8/20/2016");
        objectNode4.put("source", "Order #12345");
        objectNode4.put("transaction type", "earned");
        objectNode4.put("points", "6");
        arrayNode.add(objectNode4);

        embeddedObjectNode.put("pointTransactions", arrayNode);
        rootObjectNode.put("_embedded", embeddedObjectNode);

        try
        {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootObjectNode);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
            return "error";
        }
    }
}