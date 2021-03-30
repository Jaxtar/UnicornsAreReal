package school.newton.sysjs2.grupp3.UAR.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Seat {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    public Integer seatid;
    public Integer _salonid;
    public Integer seatnumber;

    public Integer getSeatid() {
        return seatid;
    }

    public void setSeatid(Integer seatid) {
        this.seatid = seatid;
    }

    public Integer get_salonid() {
        return _salonid;
    }

    public void set_salonid(Integer _salonid) {
        this._salonid = _salonid;
    }

    public Integer getSeatnumber() {
        return seatnumber;
    }

    public void setSeatnumber(Integer seatnumber) {
        this.seatnumber = seatnumber;
    }
}
