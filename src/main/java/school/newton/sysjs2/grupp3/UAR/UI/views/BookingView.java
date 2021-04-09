package school.newton.sysjs2.grupp3.UAR.UI.views;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import school.newton.sysjs2.grupp3.UAR.UI.Navbar;
import school.newton.sysjs2.grupp3.UAR.backend.controller.MovieController;
import school.newton.sysjs2.grupp3.UAR.backend.model.Movie;

@Route(value="/booking", layout = Navbar.class)
@CssImport("/common.css")
public class BookingView extends VerticalLayout{
    ComboBox<Movie> movie = new ComboBox<>("Movies");
    MovieController controller;

    public BookingView(MovieController controller){
        this.controller = controller;

        movie.setItems(controller.findAll());
        movie.setItemLabelGenerator(Movie::getTitle);
        movie.addValueChangeListener(e -> updateDate(e.getValue().getMovieid()));

        add(movie);
    }

    private void updateDate(Integer id){

    }
}
