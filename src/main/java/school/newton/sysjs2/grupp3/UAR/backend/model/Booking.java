package school.newton.sysjs2.grupp3.UAR.backend.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Booking {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer bookingid;
    private Integer _screeningid;
    private Integer _seatid;

    public Booking(){}

    public Booking(Integer screeningID, Integer seatID){
        this._screeningid = screeningID;
        this._seatid = seatID;
    }

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public Integer get_screeningid() {
        return _screeningid;
    }

    public void set_screeningid(Integer _screeningid) {
        this._screeningid = _screeningid;
    }

    public Integer get_seatid() {
        return _seatid;
    }

    public void set_seatid(Integer _seatid) {
        this._seatid = _seatid;
    }

}
