package school.newton.sysjs2.grupp3.UAR.UI.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import school.newton.sysjs2.grupp3.UAR.UI.MovieForm;
import school.newton.sysjs2.grupp3.UAR.UI.Navbar;
import school.newton.sysjs2.grupp3.UAR.UI.ScreeningForm;
import school.newton.sysjs2.grupp3.UAR.backend.controller.MovieController;
import school.newton.sysjs2.grupp3.UAR.backend.controller.ScreeningController;
import school.newton.sysjs2.grupp3.UAR.backend.model.Movie;
import school.newton.sysjs2.grupp3.UAR.backend.model.Screening;
import school.newton.sysjs2.grupp3.UAR.backend.repository.ScreeningRepository;

import java.util.Collection;

@Route(value="/screenings", layout= Navbar.class)
@CssImport("./common.css")
public class ScreeningsView extends VerticalLayout {

    Grid<Screening> grid = new Grid<>(Screening.class);
    ComboBox<Movie> movie = new ComboBox<>("Movies");

    private ScreeningController screeningController;
    private ScreeningRepository screeningRepository;
    private MovieController movieController;
    private TextField filter;

    private ScreeningForm screeningForm;

    public ScreeningsView (ScreeningController screeningController,
                          ScreeningRepository screeningRepository,
                           MovieController movieController){
        addClassName("list-view");
        this.screeningController = screeningController;
        this.screeningRepository = screeningRepository;
        this.filter = new TextField();
        setSizeFull();
        getToolbar();
        configureGrid();

        movie.setItems(movieController.findAll());
        movie.setItemLabelGenerator(Movie::getTitle);
        movie.addValueChangeListener(e -> updateScreeningData(e.getValue().getMovieid()));
        movie.setWidth("25%");

        screeningForm = new ScreeningForm(screeningController.getAllScreenings());
        screeningForm.addListener(ScreeningForm.SaveEvent.class,this::saveScreening);
        screeningForm.addListener(ScreeningForm.DeleteEvent.class, this::deleteScreening);
        screeningForm.addListener(MovieForm.CloseEvent.class, e -> closeEditor());

        Div content = new Div(grid, screeningForm);
        content.addClassName("content");
        content.setSizeFull();

        add(getToolbar(), movie, content);
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("_movieid", "_salonid", "date", "start_time");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(evt -> editScreening(evt.getValue()));
    }

    private HorizontalLayout getToolbar() {
        filter.setValue("Filter by title...");
        filter.setClearButtonVisible(false);
        filter.setEnabled(false);

        Button addNewMovieButton = new Button("MovieList");
        addNewMovieButton.addClickListener(e -> UI.getCurrent().navigate(MoviesView.class));

        Button addNewScreeningButton = new Button("Add New Screening", click -> addScreening());

        HorizontalLayout toolbar = new HorizontalLayout(filter, addNewMovieButton, addNewScreeningButton);
        toolbar.addClassName("toolbar");

        return toolbar;
    }

    private void deleteScreening(ScreeningForm.DeleteEvent evt) {
        screeningController.delete(evt.getScreening());
        //updateScreeningData();
        updateList();
        closeEditor();
    }

    private void saveScreening(ScreeningForm.SaveEvent evt) {
        screeningController.save(evt.getScreening());
        //updateScreeningData();
        updateList();
        closeEditor();
    }

    private void addScreening() {
        grid.asSingleSelect().clear();
        editScreening(new Screening());
    }

    private void editScreening(Screening screening) {
        if (screening == null){
            closeEditor();
        } else {
            screeningForm.setScreening(screening);
            screeningForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        screeningForm.setScreening(null);
        screeningForm.setVisible(false);
        removeClassName("editing");
    }

    private void updateScreeningData(Integer movieid) {
        grid.setItems((Collection<Screening>) screeningController.getScreeningsByMovieID(movieid));
    }

    private void updateList() {
        grid.setItems((Collection<Screening>) screeningController.getAllScreenings());
    }
}
