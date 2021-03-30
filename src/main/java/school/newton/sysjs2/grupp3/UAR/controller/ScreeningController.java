package school.newton.sysjs2.grupp3.UAR.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import school.newton.sysjs2.grupp3.UAR.model.Screening;
import school.newton.sysjs2.grupp3.UAR.repository.ScreeningRepository;

@Controller
@RequestMapping (path = "/screenings")
public class ScreeningController {

    @Autowired
    private ScreeningRepository repository;

    public ScreeningController(ScreeningRepository repository){
        this.repository = repository;
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Screening> getAllScreenings(){
        return repository.findAll();
    }
}
