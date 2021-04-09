package school.newton.sysjs2.grupp3.UAR.UI.views;


import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import school.newton.sysjs2.grupp3.UAR.UI.MoviesForm;
import school.newton.sysjs2.grupp3.UAR.backend.controller.MovieController;
import school.newton.sysjs2.grupp3.UAR.backend.model.Movie;
import school.newton.sysjs2.grupp3.UAR.backend.repository.MovieRepository;


@Route("/movies")
@CssImport("/common.css")
public class MoviesView extends VerticalLayout {

    Grid<Movie> grid = new Grid<>(Movie.class);
    private MovieController movieController;
    private TextField filter;
    private MovieRepository repository;

    private MoviesForm moviesForm;

    public MoviesView(MovieController movieController, MovieRepository repository){
        this.movieController = movieController;
        addClassName("list-view");
        this.filter = new TextField();
        this.repository = repository;
        setSizeFull();
        configureFilter();
        configureGrid();

        moviesForm = new MoviesForm();

        Div content = new Div(grid, moviesForm);
        content.addClassName("content");
        content.setSizeFull();

        add(filter, content);
        updateList();
    }

    private void configureFilter() {
        filter.setPlaceholder("Filter by title or rating...");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(e -> updateList());
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.setColumns("title", "agerating");
    }

    private void updateList() {
        grid.setItems(movieController.findAll(filter.getValue()));
    }

}
