package hello;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Tom on 6/27/2016.
 */
@Entity
public class Division
{
    @SequenceGenerator(name = "divisionGen", sequenceName = "division_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "divisionGen")
    @Id
    private long id;

    @NotNull
    private String name;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "site_code", unique = true)
    private String siteCode;

    public long getId()
    {
        return id;
    }
    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public Company getCompany()
    {
        return company;
    }
    public void setCompany(Company company)
    {
        this.company = company;
    }

    public String getSiteCode()
    {
        return siteCode;
    }
    public void setSiteCode(String siteCode)
    {
        this.siteCode = siteCode;
    }
}
