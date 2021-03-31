package school.newton.sysjs2.grupp3.UAR.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import school.newton.sysjs2.grupp3.UAR.model.Staff;
import school.newton.sysjs2.grupp3.UAR.repository.StaffRepository;

@Controller
public class StaffController {

    @Autowired
    private StaffRepository repository;

    public StaffController (StaffRepository repository){
        this.repository = repository;
    }

    public @ResponseBody
    Iterable<Staff> getAllStaff(){
        return repository.findAll();
    }
}
