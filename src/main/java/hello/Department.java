package hello;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Tom on 7/24/2016.
 */
@Entity
@Table(name="Department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Department_key")
    private long id;

    @NotNull
    @Column(name="Department_code")
    private int departmentCode;

    @Column(name="Segment_fkey")
    private long Segment_fkey;

    @Column(name="Name")
    private String name;

    @Column(name="Description")
    private String description;

    @Column(name="EcometryDepartment_code")
    private String ecometryDepartmentCode;

    public long getId() {
        return id;
    }
    public void setId(long id)
    {
        this.id = id;
    }

    public int getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(int departmentCode) {
        this.departmentCode = departmentCode;
    }

    public long getSegment_fkey() {
        return Segment_fkey;
    }

    public void setSegment_fkey(long segment_fkey) {
        Segment_fkey = segment_fkey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEcometryDepartmentCode() {
        return ecometryDepartmentCode;
    }

    public void setEcometryDepartmentCode(String ecometryDepartmentCode) {
        this.ecometryDepartmentCode = ecometryDepartmentCode;
    }
}
