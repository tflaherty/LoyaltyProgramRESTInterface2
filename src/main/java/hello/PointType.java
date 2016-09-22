package hello;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Tom on 9/21/2016.
 */
@Entity
public class PointType
{
    @SequenceGenerator(name = "pointTypeGen", sequenceName = "point_type_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pointTypeGen")
    @Id
    private long id;

    @NotNull
    private String name;

    private String description;

    public long getId()
    {
        return id;
    }
    public void setId(long id)
    {
        this.id = id;
    }

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
