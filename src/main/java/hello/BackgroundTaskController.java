package hello;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

/**
 * Created by Tom on 8/30/2016.
 */
@RestController
@RequestMapping("/workers/cron/*")
@Transactional
public class BackgroundTaskController
{
    @PersistenceContext
    EntityManager entityManager;

    @RequestMapping(value = "minute", method = RequestMethod.POST)
    public String minute()
    {
        //System.out.print("minute task executed\n");
        Loyalty loyalty = entityManager.find(Loyalty.class, (long) 1);
        Message message = new Message();
        message.setLoyalty(loyalty);
        Date now = new Date();
        message.setMessage("\'10 minute\' message auto inserted on " + now.toString());
        message.setDateReceived(now);
        entityManager.persist(message);
        return "";
    }

    @RequestMapping(value = "nightly", method = RequestMethod.POST)
    public String nightly()
    {
        //System.out.print("nightly task executed\n");
        Loyalty loyalty = entityManager.find(Loyalty.class, (long) 1);
        Message message = new Message();
        message.setLoyalty(loyalty);
        Date now = new Date();
        message.setMessage("\'nightly\' message auto inserted on " + now.toString());
        message.setDateReceived(now);
        entityManager.persist(message);
        return "";
    }
}
