package hello;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/*")
public class TestController
{
    @RequestMapping(value="foo", method=RequestMethod.GET, produces="application/json")
    public String home() {
        ObjectMapper mapper = new ObjectMapper();

        ObjectNode objectNode1 = mapper.createObjectNode();
        objectNode1.put("bookName", "Java");
        objectNode1.put("price", "100");

        try
        {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectNode1);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
            return "error";
        }
    }
}