package hello;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Tom on 6/27/2016.
 */
@Entity
public class Company {

    //@SequenceGenerator(name = "companyGen", sequenceName = "COMPANY_SEQ")
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "companyGen")
    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String name;

    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
