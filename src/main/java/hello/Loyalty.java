package hello;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Tom on 6/23/2016.
 */
@Entity
public class Loyalty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String loyaltyCode;
    private String customerCode;
    private String companyCode;
    private String divisionCode;
    private Date dateCreated;

    @Transient
    private int points;
    @Transient
    public int getPoints() {
        return 7;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getDivisionCode() {
        return divisionCode;
    }

    public void setDivisionCode(String divisionCode) {
        this.divisionCode = divisionCode;
    }

    public String getLoyaltyCode() {
        return loyaltyCode;
    }

    public void setLoyaltyCode(String loyaltyCode) {
        this.loyaltyCode = loyaltyCode;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
