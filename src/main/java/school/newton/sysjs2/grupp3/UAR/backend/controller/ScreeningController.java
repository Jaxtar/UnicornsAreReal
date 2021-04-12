package school.newton.sysjs2.grupp3.UAR.backend.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import school.newton.sysjs2.grupp3.UAR.backend.model.Screening;
import school.newton.sysjs2.grupp3.UAR.backend.repository.ScreeningRepository;

@Controller
public class ScreeningController {

    @Autowired
    private ScreeningRepository repository;

    public ScreeningController(ScreeningRepository repository){
        this.repository = repository;
    }

    public @ResponseBody
    Iterable<Screening> getAllScreenings(){
        return repository.findAll();
    }

    public List<Screening> getScreeningsByMovieID(Integer movieID){
        return repository.findBy_movieid(movieID);
    }
}
