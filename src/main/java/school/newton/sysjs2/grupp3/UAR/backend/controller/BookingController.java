package school.newton.sysjs2.grupp3.UAR.backend.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import school.newton.sysjs2.grupp3.UAR.backend.model.Booking;
import school.newton.sysjs2.grupp3.UAR.backend.repository.BookingRepository;


@Controller
public class BookingController {
    private static final Logger LOGGER = Logger.getLogger(BookingController.class.getName());
  
    @Autowired
    private BookingRepository repository;

    public BookingController(BookingRepository repository){
        this.repository = repository;
    }

    public @ResponseBody
    Iterable<Booking> getAllBookings(){
        return repository.findAll();
    }

    public void save(Booking booking) {
        if (booking == null) {
            LOGGER.log(Level.SEVERE, "Booking is null. Are you sure you have connected your form to the application?");
            return;
        }
        LOGGER.log(Level.SEVERE,
                "ID: "+ booking.getBookingid() +
                " Screening ID: " + booking.get_screeningid() +
                " Seat ID: " + booking.get_seatid());
        repository.save(booking);
    }

    public List<Booking> getBookingsByScreeningID(Integer screeningID){
        return repository.findBy_screeningid(screeningID);
    }
}
