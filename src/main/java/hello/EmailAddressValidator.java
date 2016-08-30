package hello;

import javax.persistence.Entity;
import javax.persistence.PrePersist;

/**
 * Created by Tom on 8/29/2016.
 */
public class EmailAddressValidator
{
    @PrePersist
    public void validate(EntityWithEmailAddress obj)
    {
        //if (obj.getEmailAddress().length() > 254)
        //    throw new ValidationException("Identifier out of range");
    }
}
