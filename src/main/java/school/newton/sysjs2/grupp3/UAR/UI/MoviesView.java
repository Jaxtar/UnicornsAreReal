package school.newton.sysjs2.grupp3.UAR.UI;


import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import school.newton.sysjs2.grupp3.UAR.backend.controller.MovieController;
import school.newton.sysjs2.grupp3.UAR.backend.model.Movie;
import school.newton.sysjs2.grupp3.UAR.backend.repository.MovieRepository;


@Route("/movies")
public class MoviesView extends VerticalLayout {

    Grid<Movie> grid = new Grid<>(Movie.class);
    private MovieController movieController;
    private TextField filter;
    private MovieRepository repository;

    public MoviesView(MovieController movieController, MovieRepository repository){
        this.movieController = movieController;
        this.filter = new TextField();
        this.repository = repository;
        setSizeFull();
        configureFilter();
        configureGrid();

        add(filter, grid);
        updateList();
    }

    private void configureFilter() {
        filter.setPlaceholder("Filter by name...");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(e -> updateList());
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("title", "agerating");
    }

    private void updateList() {
        grid.setItems(movieController.findAll(filter.getValue()));
    }

}
