package hello;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Tom on 9/21/2016.
 */
@Entity
public class PointTransactionDetail
{
    @SequenceGenerator(name = "pointTransactionDetailGen", sequenceName = "point_transaction_detail_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pointTransactionDetailGen")
    @Id
    private long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "loyalty_id")
    private Loyalty loyalty;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "point_type_id")
    private PointType pointType;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "point_transaction_master_id")
    private PointTransactionMaster pointTransactionMaster;

    @Column(name = "availability_expiration_date")
    private Date availabilityExpirationDate;

    @Column(name = "hold_expiration_date")
    private Date holdExpirationDate;

    @Column(name = "points")
    private int points;

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

    public PointType getPointType()
    {
        return pointType;
    }
    public void setPointType(PointType pointType)
    {
        this.pointType = pointType;
    }

    public PointTransactionMaster getPointTransactionMaster()
    {
        return pointTransactionMaster;
    }
    public void setPointTransactionMaster(PointTransactionMaster pointTransactionMaster)
    {
        this.pointTransactionMaster = pointTransactionMaster;
    }

    public Date getAvailabilityExpirationDate()
    {
        return availabilityExpirationDate;
    }
    public void setAvailabilityExpirationDate(Date availabilityExpirationDate)
    {
        this.availabilityExpirationDate = availabilityExpirationDate;
    }

    public Date getHoldExpirationDate()
    {
        return holdExpirationDate;
    }
    public void setHoldExpirationDate(Date holdExpirationDate)
    {
        this.holdExpirationDate = holdExpirationDate;
    }

    public int getPoints()
    {
        return points;
    }
    public void setPoints(int points)
    {
        this.points = points;
    }
}
