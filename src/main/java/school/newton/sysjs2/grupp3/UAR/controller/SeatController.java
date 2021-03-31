package school.newton.sysjs2.grupp3.UAR.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import school.newton.sysjs2.grupp3.UAR.model.Seat;
import school.newton.sysjs2.grupp3.UAR.repository.SeatRepository;


@Controller
public class SeatController {

    @Autowired
    private SeatRepository repository;

    public SeatController(SeatRepository repository){
        this.repository = repository;
    }

    public @ResponseBody
    Iterable<Seat> getAllSeats(){
        return repository.findAll();
    }
}
