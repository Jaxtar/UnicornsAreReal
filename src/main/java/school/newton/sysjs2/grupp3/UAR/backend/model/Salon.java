package school.newton.sysjs2.grupp3.UAR.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Salon {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    public Integer salonid;
    public Integer numberofseats;
    public Integer _theatreid;

    public Integer getSalonid() {
        return salonid;
    }

    public void setSalonid(Integer salonid) {
        this.salonid = salonid;
    }

    public Integer getNumberofseats() {
        return numberofseats;
    }

    public void setNumberofseats(Integer numberofseats) {
        this.numberofseats = numberofseats;
    }

    public Integer get_theatreid() {
        return _theatreid;
    }

    public void set_theatreid(Integer _theatreid) {
        this._theatreid = _theatreid;
    }
}
