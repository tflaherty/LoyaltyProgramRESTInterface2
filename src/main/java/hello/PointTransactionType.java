package hello;

import javax.persistence.*;

/**
 * Created by Tom on 6/23/2016.
 */
@Entity
public class PointTransactionType
{

    @SequenceGenerator(name = "pointTransactionTypeGen", sequenceName = "point_transaction_type_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pointTransactionTypeGen")
    @Id
    private long id;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    private String name;
    private String description;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

}
