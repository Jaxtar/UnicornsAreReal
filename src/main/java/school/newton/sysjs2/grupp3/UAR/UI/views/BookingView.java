package school.newton.sysjs2.grupp3.UAR.UI.views;

import java.sql.Time;
import java.time.LocalDate;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ArrayUpdater.Update;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import school.newton.sysjs2.grupp3.UAR.UI.Navbar;
import school.newton.sysjs2.grupp3.UAR.backend.controller.BookingController;
import school.newton.sysjs2.grupp3.UAR.backend.controller.MovieController;
import school.newton.sysjs2.grupp3.UAR.backend.controller.SalonController;
import school.newton.sysjs2.grupp3.UAR.backend.controller.ScreeningController;
import school.newton.sysjs2.grupp3.UAR.backend.model.Booking;
import school.newton.sysjs2.grupp3.UAR.backend.model.Movie;
import school.newton.sysjs2.grupp3.UAR.backend.model.Salon;
import school.newton.sysjs2.grupp3.UAR.backend.model.Screening;

@Route(value="/booking", layout = Navbar.class)
@CssImport("/common.css")
@PageTitle("Book Your Ticket")
public class BookingView extends VerticalLayout{
    ComboBox<Movie> movie = new ComboBox<>("Movies");
    ComboBox<Screening> date = new ComboBox<>("Screening Date");
    Button book = new Button("Wanna book this?");
    H1 bookingText = new H1("Your booking has been saved");

    private int seatsLeft = 0;
    
    MovieController movieController;
    ScreeningController screenController;
    BookingController bookingController;
    SalonController salonController;

    public BookingView(MovieController movieController, 
                       ScreeningController screenController, 
                       BookingController bookingController, 
                       SalonController salonController){
        this.movieController = movieController;
        this.screenController = screenController;
        this.bookingController = bookingController;
        this.salonController = salonController;

        movie.setItems(movieController.findAll());
        movie.setItemLabelGenerator(Movie::getTitle);
        movie.addValueChangeListener(e -> updateDateTime(e.getValue().getMovieid()));
        movie.setWidth("25%");

        
        date.setWidth("25%");
        
        book.setEnabled(false);
        book.addClickListener(e -> bookScreening());

        bookingText.setVisible(false);

        add(movie, date, book, bookingText);
    }

    private void updateDateTime(Integer id){
        date.setItems(screenController.getScreeningsByMovieID(id));
        date.setItemLabelGenerator(e -> {
                                        return "Salon " + e.get_salonid() + ": " +
                                        e.getDate().toLocalDate().getMonth().toString() 
                                        + " " 
                                        + e.getDate().toLocalDate().getDayOfMonth()
                                        + " "
                                        + e.getStart_time().toLocalTime().getHour()
                                        + ":00 ("
                                        + getSeatsLeft(e)
                                        + " seats left)";
                                    });
        date.addValueChangeListener(e -> {
                                            if(getSeatsLeft(e.getValue()) != 0) {book.setEnabled(true);}
                                            else {book.setEnabled(false);}
                                    });
    }

    private int getSeatsLeft(Screening screening){
        seatsLeft = salonController.getSalonBySalonID(screening.get_salonid()).get(0).getNumberofseats();
        seatsLeft -= bookingController.getBookingsByScreeningID(screening.getScreeningid()).size();

        return seatsLeft;
    }

    private void bookScreening(){
        bookingController.save(new Booking(date.getValue().getScreeningid(), getSeatsLeft(date.getValue())));
        UI.getCurrent().navigate(SuccessfulView.class);
    }
}
