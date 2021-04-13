package school.newton.sysjs2.grupp3.UAR.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.sql.Date;

@Entity
public class StaffSchedule {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer staffscheduleid;

    @NotNull
    @NotEmpty
    private Date date;

    @NotNull
    @NotEmpty
    private Time start_time;

    @NotNull
    @NotEmpty
    private Time end_time;

    @NotNull
    @NotEmpty
    private Integer _staffid;

    @NotNull
    @NotEmpty
    private String firstname;

    @NotNull
    @NotEmpty
    private String lastname;

    @NotNull
    @NotEmpty
    private String workarea;

    @NotNull
    @NotEmpty
    private Integer _theatreid;

    public StaffSchedule(Date date, Time start_time, Time end_time, Integer _staffid, String firstname, String lastname, String workarea, Integer _theatreid ){
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
        this._staffid = _staffid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.workarea = workarea;
        this._theatreid = _theatreid;
    }

    public StaffSchedule(){

    }


    public Integer getStaffscheduleid() {
        return staffscheduleid;
    }

    public boolean isPersisted() {
        return staffscheduleid != null;
    }

    @Override
    public int hashCode() {
        if (getStaffscheduleid() != null) {
            return getStaffscheduleid().hashCode();
        }
        return super.hashCode();
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

    public void setEnd_time(Time end_time) { this.end_time = end_time;
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

    public String getFirstname() {
        return firstname;
    }

    public void setfirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer get_theatreid() {
        return _theatreid;
    }

    public void set_theatreid(Integer _theatreid) {
        this._theatreid = _theatreid;
    }

}
