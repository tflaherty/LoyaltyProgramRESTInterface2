package hello;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Tom on 9/21/2016.
 */
@Entity
public class PointTransactionMaster
{
    @SequenceGenerator(name = "pointTransactionMasterGen", sequenceName = "point_transaction_master_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pointTransactionMasterGen")
    @Id
    private long id;

    @NotNull
    @Column(name = "created_date")
    private Date createdDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "point_transaction_type_id")
    private PointTransactionType pointTransactionType;

    @Column(name = "dollar_value")
    private BigDecimal dollarValue;

    @OneToMany(mappedBy = "pointTransactionMaster")
    private List<PointTransactionDetail> pointTransactionDetails;

    @Transient
    private String pointTransactionTypeName;
    @Transient
    public String getPointTransactionTypeName()
    {
        return getPointTransactionType().getName();
    }

    public long getId()
    {
        return id;
    }
    public void setId(long id)
    {
        this.id = id;
    }

    public Date getCreatedDate()
    {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate)
    {
        this.createdDate = createdDate;
    }

    public PointTransactionType getPointTransactionType()
    {
        return pointTransactionType;
    }
    public void setPointTransactionType(PointTransactionType pointTransactionType)
    {
        this.pointTransactionType = pointTransactionType;
    }

    public BigDecimal getDollarValue()
    {
        return dollarValue;
    }
    public void setDollarValue(BigDecimal dollarValue)
    {
        this.dollarValue = dollarValue;
    }
}
