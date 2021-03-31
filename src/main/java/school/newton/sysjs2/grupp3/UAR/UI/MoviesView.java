package school.newton.sysjs2.grupp3.UAR.UI;


import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import school.newton.sysjs2.grupp3.UAR.backend.controller.MovieController;
import school.newton.sysjs2.grupp3.UAR.backend.model.Movie;

import java.util.Collection;

@Route("/movies")
public class MoviesView extends VerticalLayout {

    Grid<Movie> grid = new Grid<>(Movie.class);
    private MovieController movieController;

    public MoviesView(MovieController movieController){
        this.movieController = movieController;
        setSizeFull();
        configureGrid();

        add(grid);
        updateList();
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("title", "agerating");
    }

    private void updateList() {
        grid.setItems((Collection<Movie>) movieController.getAllMovies());
    }
}
