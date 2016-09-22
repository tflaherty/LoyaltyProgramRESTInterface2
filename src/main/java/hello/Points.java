package hello;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Tom on 9/21/2016.
 */
@Entity
public class Points
{
    @SequenceGenerator(name = "pointsGen", sequenceName = "points_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pointsGen")
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


}
