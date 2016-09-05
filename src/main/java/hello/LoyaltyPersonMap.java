package hello;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Tom on 6/27/2016.
 */
@Entity
@Table(name = "loyalty_person_map")
public class LoyaltyPersonMap
{
    @SequenceGenerator(name = "loyaltyPersonMapGen", sequenceName = "loyalty_person_map_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loyaltyPersonMapGen")
    @Id
    private long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "loyalty_id")
    private Loyalty loyalty;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Transient
    private long loyaltyId;
    @Transient
    public long getLoyaltyId()
    {
        return getLoyalty().getId();
    }

    @Transient
    private long personId;
    @Transient
    public long getPersonId()
    {
        return getPerson().getId();
    }

    public long getId()
    {
        return id;
    }
    public void setId(long id)
    {
        this.id = id;
    }

    public Loyalty getLoyalty()
    {
        return loyalty;
    }
    public void setLoyalty(Loyalty loyalty)
    {
        this.loyalty = loyalty;
    }

    public Person getPerson()
    {
        return person;
    }
    public void setPerson(Person person)
    {
        this.person = person;
    }
}
