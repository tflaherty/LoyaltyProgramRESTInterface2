package hello;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Tom on 6/23/2016.
 */
@Entity
public class Loyalty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String loyaltyCode;

    @NotNull
    @ManyToOne
    private Division division;

    private Date dateCreated;

    @Transient
    private int points;
    @Transient
    public int getPoints() {
        return 7;
    }

    public String getLoyaltyCode() {
        return loyaltyCode;
    }

    public void setLoyaltyCode(String loyaltyCode) {
        this.loyaltyCode = loyaltyCode;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

}
