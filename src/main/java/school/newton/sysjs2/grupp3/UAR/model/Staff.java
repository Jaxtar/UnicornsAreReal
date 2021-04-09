package school.newton.sysjs2.grupp3.UAR.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer staffid;

    @NotNull
    @NotEmpty
    private String firstname = "";

    @NotNull
    @NotEmpty
    private String lastname = "";

    @Email
    @NotNull
    @NotEmpty
    private String email = "";


    @NotNull
    @NotEmpty
    private String username = "";

    @NotNull
    @NotEmpty
    private String password = "";

    public Staff(String firstname, String lastname, String email, String username, String password){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Staff(){

    }

    public Integer getStaffid() {
        return staffid;
    }

    public boolean isPersisted() {
        return staffid != null;
    }

    @Override
    public int hashCode() {
        if (getStaffid() != null) {
            return getStaffid().hashCode();
        }
        return super.hashCode();
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() { return password; }

    public void setPassword (String password) {this.password = password;}

}

