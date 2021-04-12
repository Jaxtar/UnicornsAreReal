package school.newton.sysjs2.grupp3.UAR.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import school.newton.sysjs2.grupp3.UAR.model.Staff;
import school.newton.sysjs2.grupp3.UAR.repository.StaffRepository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class StaffController {

    private static final Logger LOGGER = Logger.getLogger(StaffController.class.getName());     //new
    private StaffRepository repository;

    @Autowired
    public StaffController (StaffRepository repository){
        this.repository = repository;
    }

    public List<Staff> findAll() {return repository.findAll();}

    public List<Staff> findAll(String stringFilter){
        if(stringFilter == null || stringFilter.isEmpty()) {
            return repository.findAll();
        }else {
            return repository.findByLastnameStartsWithIgnoreCase(stringFilter);
        }
    }

    public void delete(Staff staff) {
        repository.delete(staff);
    }

    public void save(Staff staff) {
        if (staff == null) {
            LOGGER.log(Level.SEVERE,
                    "Staff is null. Are you sure you have connected your form to the application?");
            return;
        } else {
            LOGGER.log(Level.SEVERE,
                    "ID: " + staff.getStaffid() +
                            "First Name: "+ staff.getFirstname() +
                            " Last Name: " + staff.getLastname() +
                            " Email: " + staff.getEmail() +
                            " Username: " + staff.getUsername() +
                            " Password: " + staff.getPassword());
            repository.save(staff);
        }
    }
}
