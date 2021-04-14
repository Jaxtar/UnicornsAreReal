package school.newton.sysjs2.grupp3.UAR.UI;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import school.newton.sysjs2.grupp3.UAR.backend.model.Movie;

@SpringBootTest
public class MovieFormTest {
    private List<Movie> movies;
    private Movie movie;

    @Before
    public void setupData(){
        movies = new ArrayList<>();

        movie = new Movie();
        movie.setAgerating("PG");
        movie.setDescription("This do be wholesome");
        movie.setTitle("Swinging in the rain");

        movies.add(movie);
    }

    @Test
    public void formFieldsPopulated(){
        MovieForm form = new MovieForm(movies);
        form.setMovie(movie);

        Assert.assertEquals("PG", form.agerating.getValue());
        Assert.assertEquals("This do be wholesome", form.description.getValue());
        Assert.assertEquals("Swinging in the rain", form.title.getValue());
    }

    @Test
    public void savesCorrectValues(){
        MovieForm form = new MovieForm(movies);
        form.setMovie(movie);

        form.agerating.setValue("R");
        form.description.setValue("Test2");
        form.title.setValue("A witty title mate");

        AtomicReference<Movie> savedMovieRef = new AtomicReference<>(null);
        
        form.addListener(MovieForm.SaveEvent.class, e -> {
            savedMovieRef.set(e.getMovie()); 
        });
        form.save.click();

        Movie savedMovie = savedMovieRef.get();

        Assert.assertEquals("R", savedMovie.getAgerating());
        Assert.assertEquals("Test2", savedMovie.getDescription());
        Assert.assertEquals("A witty title mate", savedMovie.getTitle());
    }
}
