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
    private String points;

    @NotNull
    @ManyToOne
    private Loyalty loyalty;

    @ManyToOne
    private Customer_To_Loyalty customer_to_loyalty;

    @NotNull
    @ManyToOne
    private PointTransactionType pointTransactionType;

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

    public Customer_To_Loyalty getCustomer_to_loyalty() {
        return customer_to_loyalty;
    }

    public void setCustomer_to_loyalty(Customer_To_Loyalty customer_to_loyalty) {
        this.customer_to_loyalty = customer_to_loyalty;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public PointTransactionType getPointTransactionType() {
        return pointTransactionType;
    }

    public void setPointTransactionType(PointTransactionType pointTransactionType) {
        this.pointTransactionType = pointTransactionType;
    }

}
