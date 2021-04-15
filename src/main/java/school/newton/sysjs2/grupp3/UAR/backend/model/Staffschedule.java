package school.newton.sysjs2.grupp3.UAR.backend.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Staffschedule {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer staffscheduleid;

    @NotNull
    public Date date = Date.valueOf(LocalDate.now());

    @NotNull
    public Time start_time = Time.valueOf(LocalTime.now());

    @NotNull
    private Time end_time = Time.valueOf(LocalTime.now());

    @NotNull
    private Integer _staffid = 0 ;

    @NotNull
    @NotEmpty
    private String firstname = "";

    @NotNull
    @NotEmpty
    private String lastname = "";

    @Enumerated(EnumType.STRING)
    private Workarea workarea;

    @NotNull
    private Integer _theatreid = 0;

    public Staffschedule(Date date, Time start_time, Time end_time, Integer _staffid, String firstname, String lastname, Workarea workarea, Integer _theatreid ){
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
        this._staffid = _staffid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.workarea = workarea;
        this._theatreid = _theatreid;
    }

    public Staffschedule(){

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


    public enum Workarea {
        Supervisor, Projectionist, Cashier, Usher
    }

    public Workarea getWorkarea() {
        return workarea;
    }

    public void setWorkarea(Workarea workarea) {
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