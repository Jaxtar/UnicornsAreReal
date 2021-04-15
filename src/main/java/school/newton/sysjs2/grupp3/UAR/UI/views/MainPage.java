package school.newton.sysjs2.grupp3.UAR.UI.views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import school.newton.sysjs2.grupp3.UAR.UI.Navbar;
import school.newton.sysjs2.grupp3.UAR.backend.controller.MovieController;
import school.newton.sysjs2.grupp3.UAR.backend.model.Movie;
import school.newton.sysjs2.grupp3.UAR.backend.repository.MovieRepository;

@Route(value="", layout=Navbar.class)
@PageTitle("Unicorns Are Real!")
public class MainPage extends VerticalLayout{

    Grid<Movie> grid = new Grid<>(Movie.class);
    private MovieController movieController;
    private MovieRepository movieRepository;
    private TextField filter;


    public MainPage(MovieController movieController,
                    MovieRepository movieRepository){
        this.movieController = movieController;
        this.movieRepository = movieRepository;
        this.filter = new TextField();

        setSizeFull();
        getToolbar();
        configureGrid();

        Div content = new Div(grid);
        content.addClassName("content");
        content.setSizeFull();

        add(getToolbar(), content);
        updateList();
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("title", "agerating", "description");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        filter.setPlaceholder("Filter by title...");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(e -> updateList());

        HorizontalLayout toolbar = new HorizontalLayout(filter);
        toolbar.addClassName("toolbar");

        return toolbar;
    }


    private void updateList() {
        grid.setItems(movieController.findAll(filter.getValue()));
    }

}