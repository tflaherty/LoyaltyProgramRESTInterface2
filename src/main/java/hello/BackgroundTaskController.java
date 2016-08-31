package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Tom on 8/30/2016.
 */
@RestController
@RequestMapping("/workers/cron/*")
public class BackgroundTaskController
{
    @RequestMapping(value = "minute", method = RequestMethod.POST)
    public String minute()
    {
        System.out.print("minute task executed\n");
        return "blah blah blah";
    }

    @RequestMapping(value = "nightly", method = RequestMethod.POST)
    public String nightly()
    {
        System.out.print("nightly task executed\n");
        return "blah blah blah";
    }
}
