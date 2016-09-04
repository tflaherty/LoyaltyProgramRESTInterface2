package hello;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Tom on 8/26/2016.
 */
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String message;

    @NotNull
    @ManyToOne
    private Loyalty loyalty;

    @NotNull
    @Column(name = "date_received")
    private Date dateReceived;

    private boolean read;

    // this is really JSON data
    // figure out how to make this JSON by looking at:
    //    https://devlearnings.wordpress.com/2014/03/28/using-postgres-json/
    private String metadata;

    public long getId()
    {
        return id;
    }
    public void setId(long id)
    {
        this.id = id;
    }

    public String getMessage()
    {
        return message;
    }
    public void setMessage(String message)
    {
        this.message = message;
    }

    public Loyalty getLoyalty()
    {
        return loyalty;
    }
    public void setLoyalty(Loyalty loyalty)
    {
        this.loyalty = loyalty;
    }

    public Date getDateReceived()
    {
        return dateReceived;
    }
    public void setDateReceived(Date dateReceived)
    {
        this.dateReceived = dateReceived;
    }

    public boolean isRead()
    {
        return read;
    }
    public void setRead(boolean read)
    {
        this.read = read;
    }

    public String getMetadata()
    {
        return metadata;
    }
    public void setMetadata(String metadata)
    {
        this.metadata = metadata;
    }

}
