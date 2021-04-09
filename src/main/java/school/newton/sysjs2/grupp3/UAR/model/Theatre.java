package school.newton.sysjs2.grupp3.UAR.model;

import javax.persistence.*;

@Entity
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer theatreid;
    public String theatrename;

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