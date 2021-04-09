package school.newton.sysjs2.grupp3.UAR.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import school.newton.sysjs2.grupp3.UAR.backend.model.Movie;
import school.newton.sysjs2.grupp3.UAR.backend.repository.MovieRepository;

import java.util.List;

@Controller
public class MovieController {

    private MovieRepository repository;

    @Autowired
    public MovieController(MovieRepository repository){
        this.repository = repository;
    }

    public List<Movie> findAll(){
        return repository.findAll();
    }

    public List<Movie> findAll(String stringFilter){
        if (stringFilter == null || stringFilter.isEmpty()){
            return repository.findAll();
        } else {
            return repository.search(stringFilter);
        }
    }
}
