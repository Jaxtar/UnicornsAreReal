package school.newton.sysjs2.grupp3.UAR.UI.views;

import java.sql.Time;
import java.time.LocalDate;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import school.newton.sysjs2.grupp3.UAR.UI.Navbar;
import school.newton.sysjs2.grupp3.UAR.backend.controller.MovieController;
import school.newton.sysjs2.grupp3.UAR.backend.controller.ScreeningController;
import school.newton.sysjs2.grupp3.UAR.backend.model.Movie;
import school.newton.sysjs2.grupp3.UAR.backend.model.Screening;

@Route(value="/booking", layout = Navbar.class)
@CssImport("/common.css")
@PageTitle("Book Your Ticket")
public class BookingView extends VerticalLayout{
    ComboBox<Movie> movie = new ComboBox<>("Movies");
    ComboBox<Screening> date = new ComboBox<>("Screening Date");
    ComboBox<Time> time = new ComboBox<>("Screening Time");
    
    MovieController movieController;
    ScreeningController screenController;

    public BookingView(MovieController movieController, ScreeningController screenController){
        this.movieController = movieController;
        this.screenController = screenController;

        movie.setItems(movieController.findAll());
        movie.setItemLabelGenerator(Movie::getTitle);
        movie.addValueChangeListener(e -> updateDate(e.getValue().getMovieid()));

        add(movie, date);
    }

    private void updateDate(Integer id){
        date.setItems(screenController.getScreeningsByMovieID(id));
        date.setItemLabelGenerator(e -> {
                                        return e.getDate().toLocalDate().getMonth().toString() 
                                        + " " 
                                        + e.getDate().toLocalDate().getDayOfMonth()
                                        + " "
                                        + e.getStart_time().toLocalTime().getHour()
                                        + ":00";
                                    });
    }
}
