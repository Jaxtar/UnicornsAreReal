package school.newton.sysjs2.grupp3.UAR.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Time;
import java.util.Date;

@Entity
public class Staffschedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer staffscheduleid;
    public Date date;
    public Time start_time;
    public Time end_time;
    public String workarea;
    public Integer _staffid;
    public Integer _theatreid;

    public Integer getStaffscheduleid() {
        return staffscheduleid;
    }

    public void setStaffscheduleid(Integer staffscheduleid) {
        this.staffscheduleid = staffscheduleid;
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

    public String getWorkarea() {
        return workarea;
    }

    public void setWorkarea(String workarea) {
        this.workarea = workarea;
    }

    public Integer get_staffid() {
        return _staffid;
    }

    public void set_staffid(Integer _staffid) {
        this._staffid = _staffid;
    }

    public Integer get_theatreid() {
        return _theatreid;
    }

    public void set_theatreid(Integer _theatreid) {
        this._theatreid = _theatreid;
    }
}
