package school.newton.sysjs2.grupp3.UAR.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import school.newton.sysjs2.grupp3.UAR.backend.model.Staffschedule;
import school.newton.sysjs2.grupp3.UAR.backend.repository.StaffScheduleRepository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class StaffScheduleController {

    private static final Logger LOGGER = Logger.getLogger(StaffScheduleController.class.getName());
    private StaffScheduleRepository repository;

    @Autowired
    public StaffScheduleController(StaffScheduleRepository repository){
        this.repository = repository;
    }


    public List<StaffSchedule> findAll() {return repository.findAll();}

    public List<StaffSchedule> findAll(String stringFilter){

        if(stringFilter == null || stringFilter.isEmpty()) {
            return repository.findAll();
        }else {
            return repository.findByLastnameStartsWithIgnoreCase(stringFilter);
        }
    }


    public void delete(StaffSchedule staffschedule) {
        repository.delete(staffschedule);
    }

    public void save(StaffSchedule staffschedule) {

        if (staffschedule == null) {
            LOGGER.log(Level.SEVERE,
                    "Schedule is null. Are you sure you have connected your form to the application?");
            return;
        } else {
            LOGGER.log(Level.SEVERE,
                    "ID: " + staffschedule.getStaffscheduleid() +
                            " Date: " + staffschedule.getDate() +
                            " Start Time: " + staffschedule.getStart_time() +
                            " End Time: " + staffschedule.getEnd_time() +
                            " Staff ID: " + staffschedule.get_staffid() +
                            " First Name: " + staffschedule.getFirstname() +
                            " Last Name: " + staffschedule.getLastname() +
                            " Work Area: " + staffschedule.getWorkarea() +
                            " Theater ID: " + staffschedule.get_theatreid());
            repository.save(staffschedule);
        }
    }
}