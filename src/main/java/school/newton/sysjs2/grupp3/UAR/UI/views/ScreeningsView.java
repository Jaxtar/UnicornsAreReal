package school.newton.sysjs2.grupp3.UAR.UI.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import school.newton.sysjs2.grupp3.UAR.UI.ScreeningForm;
import school.newton.sysjs2.grupp3.UAR.backend.controller.MovieController;
import school.newton.sysjs2.grupp3.UAR.backend.controller.ScreeningController;
import school.newton.sysjs2.grupp3.UAR.backend.model.Movie;
import school.newton.sysjs2.grupp3.UAR.backend.model.Screening;
import school.newton.sysjs2.grupp3.UAR.backend.repository.ScreeningRepository;

import java.util.Collection;

@Route(value="/screenings", layout= StaffLayout.class)
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
        configureGrid();

        movie.setItems(movieController.findAll());
        movie.setItemLabelGenerator(Movie::getTitle);
        movie.addValueChangeListener(e -> updateList());

        Button addNewScreeningButton = new Button("New Screening", click -> addScreening());

        HorizontalLayout toolbar = new HorizontalLayout(movie, addNewScreeningButton);
        toolbar.setDefaultVerticalComponentAlignment(Alignment.END);

        screeningForm = new ScreeningForm(screeningController.getAllScreenings());
        screeningForm.addListener(ScreeningForm.SaveEvent.class,this::saveScreening);
        screeningForm.addListener(ScreeningForm.DeleteEvent.class, this::deleteScreening);
        screeningForm.addListener(ScreeningForm.CloseEvent.class, e -> closeEditor());
        closeEditor();

        Div content = new Div(grid, screeningForm);
        content.addClassName("content");
        content.setSizeFull();

        add(toolbar, content);
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("_movieid", "_salonid", "date", "start_time", "end_time");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(evt -> editScreening(evt.getValue()));
    }

    private void closeEditor() {
        screeningForm.setScreening(null);
        screeningForm.setVisible(false);
        removeClassName("editing");
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

    private void addScreening() {
        grid.asSingleSelect().clear();
        editScreening(new Screening());
    }

    private void saveScreening(ScreeningForm.SaveEvent evt) {
        screeningController.save(evt.getScreening());
        updateList();
        closeEditor();
    }

    private void deleteScreening(ScreeningForm.DeleteEvent evt) {
        screeningController.delete(evt.getScreening());
        updateList();
        closeEditor();
    }

    private void updateList() {
        grid.setItems((Collection<Screening>) screeningController.getScreeningsByMovieID(movie.getValue().getMovieid()));    }
}
