package school.newton.sysjs2.grupp3.UAR.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import school.newton.sysjs2.grupp3.UAR.backend.model.Movie;
import school.newton.sysjs2.grupp3.UAR.backend.repository.MovieRepository;

@Controller
public class MovieController {

    @Autowired
    private MovieRepository repository;

    public MovieController(MovieRepository repository){
        this.repository = repository;
    }

    public @ResponseBody Iterable<Movie> getAllMovies(){
        return repository.findAll();
    }
}
