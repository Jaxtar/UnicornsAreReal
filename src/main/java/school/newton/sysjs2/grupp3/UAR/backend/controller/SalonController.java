package school.newton.sysjs2.grupp3.UAR.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import school.newton.sysjs2.grupp3.UAR.backend.model.Salon;
import school.newton.sysjs2.grupp3.UAR.backend.repository.SalonRepository;

@Controller
public class SalonController {

    @Autowired
    private SalonRepository repository;

    public SalonController(SalonRepository repository){
        this.repository = repository;
    }

    public @ResponseBody
    Iterable<Salon> getAllSalons(){
        return repository.findAll();
    }
}
