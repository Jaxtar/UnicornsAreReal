package school.newton.sysjs2.grupp3.UAR.backend.model;

import com.vaadin.flow.component.template.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.sql.Time;
import java.util.Date;

@Entity
public class StaffSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer staffscheduleid;
    private Date date;
    private Time start_time;
    private Time end_time;
    private String workarea;
    private Integer _staffid;
    private Integer _theatreid;

    public StaffSchedule(){}

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
