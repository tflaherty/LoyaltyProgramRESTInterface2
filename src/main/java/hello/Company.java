package hello;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Tom on 6/27/2016.
 */
@Entity
public class Company {

    @Id
    @SequenceGenerator(name = "companyGen", sequenceName = "COMPANY_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "companyGen")
    private long id;

    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
