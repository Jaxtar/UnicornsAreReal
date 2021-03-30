package school.newton.sysjs2.grupp3.UAR.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import school.newton.sysjs2.grupp3.UAR.model.StaffSchedule;
import school.newton.sysjs2.grupp3.UAR.repository.StaffScheduleRepository;

@Controller
@RequestMapping(path="/staffschedules")
public class StaffScheduleController {

    @Autowired
    private StaffScheduleRepository repository;

    public StaffScheduleController(StaffScheduleRepository repository){
        this.repository = repository;
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<StaffSchedule> getAllStaffSchedules(){
        return repository.findAll();
    }
}
