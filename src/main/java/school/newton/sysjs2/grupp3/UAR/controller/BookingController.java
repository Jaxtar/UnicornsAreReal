package school.newton.sysjs2.grupp3.UAR.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import school.newton.sysjs2.grupp3.UAR.model.Booking;
import school.newton.sysjs2.grupp3.UAR.repository.BookingRepository;


@Controller
public class BookingController {

    @Autowired
    private BookingRepository repository;

    public BookingController(BookingRepository repository){
        this.repository = repository;
    }

    public @ResponseBody
    Iterable<Booking> getAllBookings(){
        return repository.findAll();
    }
}
