package school.newton.sysjs2.grupp3.UAR.UI.views;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import school.newton.sysjs2.grupp3.UAR.UI.MovieForm;
import school.newton.sysjs2.grupp3.UAR.UI.Navbar;
import school.newton.sysjs2.grupp3.UAR.UI.ScreeningForm;
import school.newton.sysjs2.grupp3.UAR.backend.controller.MovieController;
import school.newton.sysjs2.grupp3.UAR.backend.controller.ScreeningController;
import school.newton.sysjs2.grupp3.UAR.backend.model.Movie;
import school.newton.sysjs2.grupp3.UAR.backend.model.Screening;
import school.newton.sysjs2.grupp3.UAR.backend.repository.MovieRepository;
import school.newton.sysjs2.grupp3.UAR.backend.repository.ScreeningRepository;


@Route(value="/movies", layout= Navbar.class)
@CssImport("./common.css")
public class MoviesView extends VerticalLayout {

    Grid<Movie> grid = new Grid<>(Movie.class);
    private MovieController movieController;
    private MovieRepository movieRepository;
    private TextField filter;

    private MovieForm movieForm;

    public MoviesView(MovieController movieController,
                      MovieRepository movieRepository){
        addClassName("list-view");
        this.movieController = movieController;
        this.movieRepository = movieRepository;
        this.filter = new TextField();
        setSizeFull();
        getToolbar();
        configureGrid();

        movieForm = new MovieForm(movieController.findAll());
        movieForm.addListener(MovieForm.SaveEvent.class,this::saveMovie);
        movieForm.addListener(MovieForm.DeleteEvent.class, this::deleteMovie);
        movieForm.addListener(MovieForm.CloseEvent.class, e -> closeEditor());

        Div content = new Div(grid, movieForm);
        content.addClassName("content");
        content.setSizeFull();

        add(getToolbar(), content);
        updateList();
        closeEditor();
    }

    private void deleteMovie(MovieForm.DeleteEvent evt) {
        movieController.delete(evt.getMovie());
        updateList();
        closeEditor();
    }

    private void saveMovie(MovieForm.SaveEvent evt) {
        movieController.save(evt.getMovie());
        updateList();
        closeEditor();
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("title", "agerating", "description");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(evt -> editMovie(evt.getValue()));
    }

    private void editMovie(Movie movie) {
        if (movie == null){
            closeEditor();
        } else {
            movieForm.setMovie(movie);
            movieForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        movieForm.setMovie(null);
        movieForm.setVisible(false);
        removeClassName("editing");
    }

    private HorizontalLayout getToolbar() {
        filter.setPlaceholder("Filter by title...");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(e -> updateList());

       Button addNewMovieButton = new Button("Add Movie", click -> addMovie());

       Button addNewScreeningButton = new Button("Screenings");
        addNewScreeningButton.addClickListener(e -> UI.getCurrent().navigate(ScreeningsView.class));

       HorizontalLayout toolbar = new HorizontalLayout(filter, addNewMovieButton, addNewScreeningButton);
       toolbar.addClassName("toolbar");

       return toolbar;
    }

    private void addMovie() {
        grid.asSingleSelect().clear();
        editMovie(new Movie());
    }

    private void updateList() {
        grid.setItems(movieController.findAll(filter.getValue()));
    }

}
