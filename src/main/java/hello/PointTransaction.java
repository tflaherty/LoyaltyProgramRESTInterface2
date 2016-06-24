package hello;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Tom on 6/23/2016.
 */
@Entity
public class PointTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date creationDate;
    private String numberOfPoints;

    @ManyToOne
    private Loyalty loyalty;

    @ManyToOne
    private PointTransactionType pointTransactionType;

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Loyalty getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(Loyalty loyalty) {
        this.loyalty = loyalty;
    }

    public String getNumberOfPoints() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(String numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    public PointTransactionType getPointTransactionType() {
        return pointTransactionType;
    }

    public void setPointTransactionType(PointTransactionType pointTransactionType) {
        this.pointTransactionType = pointTransactionType;
    }

}
