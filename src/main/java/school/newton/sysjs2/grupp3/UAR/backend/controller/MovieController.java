package school.newton.sysjs2.grupp3.UAR.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import school.newton.sysjs2.grupp3.UAR.backend.model.Movie;
import school.newton.sysjs2.grupp3.UAR.backend.repository.MovieRepository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class MovieController {
    private static final Logger LOGGER = Logger.getLogger(MovieController.class.getName());
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

    public void delete(Movie movie) {
        repository.delete(movie);
    }

    public void save(Movie movie) {
        if (movie == null) {
            LOGGER.log(Level.SEVERE, "Movie is null. Are you sure you have connected your form to the application?");
            return;
        }
        LOGGER.log(Level.SEVERE, "ID: "+ movie.getMovieid() + " Title: " + movie.getTitle() + " Rating: " + movie.getAgerating() + " Description: " + movie.getDescription() );
        repository.save(movie);
    }
}
