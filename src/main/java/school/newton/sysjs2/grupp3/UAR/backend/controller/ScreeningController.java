package school.newton.sysjs2.grupp3.UAR.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import school.newton.sysjs2.grupp3.UAR.backend.model.Movie;
import school.newton.sysjs2.grupp3.UAR.backend.model.Screening;
import school.newton.sysjs2.grupp3.UAR.backend.repository.MovieRepository;
import school.newton.sysjs2.grupp3.UAR.backend.repository.ScreeningRepository;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class ScreeningController {
    private static final Logger LOGGER = Logger.getLogger(ScreeningController.class.getName());
    private ScreeningRepository repository;

    @Autowired
    public ScreeningController(ScreeningRepository repository){
        this.repository = repository;
    }

    public List<Screening> findAll(){
        return repository.findAll();
    }

    public List<Screening> findAll(Integer integerFilter){
        if (integerFilter == null || integerFilter.describeConstable().isEmpty()){
            return repository.findAll();
        } else {
            return repository.findBy_movieid(integerFilter);
        }
    }
}
