package school.newton.sysjs2.grupp3.UAR.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import school.newton.sysjs2.grupp3.UAR.model.Theatre;
import school.newton.sysjs2.grupp3.UAR.repository.TheatreRepository;


@Controller
@RequestMapping (path="/theatres")
public class TheatreController {

    @Autowired
    private TheatreRepository repository;

    public TheatreController(TheatreRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Theatre> getAllTheatres() {
        return repository.findAll();
    }
}
