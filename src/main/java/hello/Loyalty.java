package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.geo.Point;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy="loyalty")
    private List<PointTransaction> pointTransactions;
    public List<PointTransaction> getPointTransactions() {
        return pointTransactions;
    }

    @OneToMany(mappedBy="loyalty")
    private List<Message> messages;
    public List<Message> getMessages() {
        return messages;
    }

    @ManyToMany
    @JoinTable(name="loyalty_person_map",
            joinColumns = @JoinColumn(name="loyalty_id"),
            inverseJoinColumns = @JoinColumn(name="person_id"))
    private Collection<Person> persons;

    private Date dateCreated;

    @Transient
    private String companyName;
    @Transient
    public String getCompanyName() { return getDivision().getCompany().getName(); }

    @Transient
    private String divisionName;
    @Transient
    public String getDivisionName() { return getDivision().getName(); }

    @Transient
    private int availablePoints;
    @Transient
    public int getAvailablePoints() {
        int pointCount = 0;
        if (getPointTransactions() != null)
        {
            for (PointTransaction pointTransaction : getPointTransactions())
            {
                pointCount += pointTransaction.getPoints();
            }
        }
        return pointCount;
    }

    @Transient
    private int heldPoints;
    @Transient
    public int getHeldPoints() {
        int pointCount = 0;
        if (getPointTransactions() != null)
        {
            for (PointTransaction pointTransaction : getPointTransactions())
            {
                String transactionTypeName = pointTransaction.getPointTransactionType().getName();
                if (transactionTypeName.equals("held"))
                {
                    pointCount += pointTransaction.getPoints();
                }
                else if (transactionTypeName.equals("redeemed"))
                {
                    pointCount -= pointTransaction.getPoints();
                }
            }
        }
        return pointCount;
    }

    public long getId() {
        return id;
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
