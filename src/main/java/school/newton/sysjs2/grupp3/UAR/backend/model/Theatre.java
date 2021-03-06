package school.newton.sysjs2.grupp3.UAR.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer theatreid;
    private String theatrename;

    public Theatre(){}

    public Integer getTheatreID() {
        return theatreid;
    }

    public void setTheatreID(Integer theatreid) {
        this.theatreid = theatreid;
    }

    public String getTheatreName() {
        return theatrename;
    }

    public void setTheatreName(String theatrename) {
        this.theatrename = theatrename;
    }

}