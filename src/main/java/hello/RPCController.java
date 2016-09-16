package hello;

import com.fasterxml.jackson.databind.util.JSONPObject;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by Tom on 9/15/2016.
 */
@RestController
@RequestMapping("/rpc/*")
public class RPCController
{
    @Autowired
    private HoldPointsRequestValidator holdPointsRequestValidator;

    @RequestMapping(value = "v00001/holdPoints", method = RequestMethod.POST, produces = "application/json")
    public long holdPointsForOrderByOrderCodeLoyaltyCodeDivisionNameCompanyName(@Valid @RequestBody HoldPointsRequest holdPointsRequest, BindingResult errors)
// This is how you do query parameters instead of request body JSON
//                                    @Param("orderCode") String orderCode,
//                                    @Param("orderDivisionName") String orderDivisionName,
//                                    @Param("orderCompanyName") String orderCompanyName,
//                                    @Param("loyaltyCode") String loyaltyCode,
//                                    @Param("loyaltyDivisionName") String loyaltyDivisionName,
//                                    @Param("loyaltyCompanyName") String loyaltyCompanyName,
//                                    @Param("points") Long points)
    {
        holdPointsRequestValidator.validate(holdPointsRequest, errors);
        if (errors.hasErrors())
        {
            return -1L;
        }

        return 0L;
    }
}
