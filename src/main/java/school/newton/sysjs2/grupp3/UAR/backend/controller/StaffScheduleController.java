package school.newton.sysjs2.grupp3.UAR.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import school.newton.sysjs2.grupp3.UAR.backend.model.StaffSchedule;
import school.newton.sysjs2.grupp3.UAR.backend.repository.StaffScheduleRepository;

@Controller
public class StaffScheduleController {

    @Autowired
    private StaffScheduleRepository repository;

    public StaffScheduleController(StaffScheduleRepository repository){
        this.repository = repository;
    }

    public @ResponseBody
    Iterable<StaffSchedule> getAllStaffSchedules(){
        return repository.findAll();
    }
}
