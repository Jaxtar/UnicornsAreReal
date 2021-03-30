package school.newton.sysjs2.grupp3.UAR.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import school.newton.sysjs2.grupp3.UAR.model.Movie;
import school.newton.sysjs2.grupp3.UAR.repository.MovieRepository;

@Controller
@RequestMapping (path="/movies")
public class MovieController {

    @Autowired
    private MovieRepository repository;

    public MovieController(MovieRepository repository){
        this.repository = repository;
    }

    @GetMapping (path = "/all")
    public @ResponseBody Iterable<Movie> getAllMovies(){
        return repository.findAll();
    }
}
