package school.newton.sysjs2.grupp3.UAR.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Time;
import java.sql.Date;

@Entity
public class Screening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer screeningid;
    private Integer _movieid;
    private Integer _salonid;
    private Date date;
    private Time start_time;
    private Time end_time;

    public Screening(){}

    public Integer getScreeningid() {
        return screeningid;
    }

    public void setScreeningid(Integer screeningid) {
        this.screeningid = screeningid;
    }

    public Integer get_movieid() {
        return _movieid;
    }

    public void set_movieid(Integer _movieid) {
        this._movieid = _movieid;
    }

    public Integer get_salonid() {
        return _salonid;
    }

    public void set_salonid(Integer _salonid) {
        this._salonid = _salonid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStart_time() {
        return start_time;
    }

    public void setStart_time(Time start_time) {
        this.start_time = start_time;
    }

    public Time getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Time end_time) {
        this.end_time = end_time;
    }
}
