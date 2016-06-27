package hello;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Tom on 6/27/2016.
 */
@Entity
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;

    @NotNull
    @ManyToOne
    private Company company;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
