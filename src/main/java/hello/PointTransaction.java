package hello;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Tom on 6/23/2016.
 */
@Entity
public class PointTransaction
{

    @SequenceGenerator(name = "pointTransactionGen", sequenceName = "point_transaction_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pointTransactionGen")
    @Id
    private long id;

    @NotNull
    @Column(name = "date_created")
    private Date dateCreated;

    @NotNull
    private int points;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "loyalty_id")
    private Loyalty loyalty;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "point_transaction_type_id")
    private PointTransactionType pointTransactionType;

    @Column(name = "expiration_date")
    private Date expirationDate;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public Date getDateCreated()
    {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated)
    {
        this.dateCreated = dateCreated;
    }

    public Loyalty getLoyalty()
    {
        return loyalty;
    }

    public void setLoyalty(Loyalty loyalty)
    {
        this.loyalty = loyalty;
    }

    public int getPoints()
    {
        return points;
    }

    public void setPoints(int points)
    {
        this.points = points;
    }

    public PointTransactionType getPointTransactionType()
    {
        return pointTransactionType;
    }

    public void setPointTransactionType(PointTransactionType pointTransactionType)
    {
        this.pointTransactionType = pointTransactionType;
    }

    public Date getExpirationDate()
    {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate)
    {
        this.expirationDate = expirationDate;
    }


}
