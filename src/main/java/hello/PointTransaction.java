package hello;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Tom on 6/23/2016.
 */
@Entity
public class PointTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private Date dateCreated;

    @NotNull
    private int points;

    @NotNull
    @ManyToOne
    private Loyalty loyalty;

    @NotNull
    @ManyToOne
    private PointTransactionType pointTransactionType;

    public long getId()
    {
        return id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Loyalty getLoyalty() {
        return loyalty;
    }
    public void setLoyalty(Loyalty loyalty) {
        this.loyalty = loyalty;
    }

    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }

    public PointTransactionType getPointTransactionType() {
        return pointTransactionType;
    }

    public void setPointTransactionType(PointTransactionType pointTransactionType) {
        this.pointTransactionType = pointTransactionType;
    }

}
