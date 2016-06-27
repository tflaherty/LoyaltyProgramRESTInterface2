package hello;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Tom on 6/27/2016.
 */
@Entity
public class Customer_To_Loyalty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String customerCode;
    @ManyToOne
    private Loyalty loyalty;
    @ManyToOne
    private Division division;
    private Date birthdate;
    private Date dateCreated;

    public Loyalty getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(Loyalty loyalty) {
        this.loyalty = loyalty;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
}
