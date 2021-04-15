package school.newton.sysjs2.grupp3.UAR.backend.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import school.newton.sysjs2.grupp3.UAR.backend.model.Screening;
import school.newton.sysjs2.grupp3.UAR.backend.repository.ScreeningRepository;

@Controller
public class ScreeningController {
    private static final Logger LOGGER = Logger.getLogger(MovieController.class.getName());

    @Autowired
    private ScreeningRepository repository;

    public ScreeningController(ScreeningRepository repository) {
        this.repository = repository;
    }

    public @ResponseBody
    Iterable<Screening> getAllScreenings() {
        return repository.findAll();
    }

    // Finds all screenings of a give movie
    public List<Screening> getScreeningsByMovieID(Integer movieID) {
        if (movieID == null || movieID.describeConstable().isEmpty()) {
            return repository.findAll();
        } else {
            return repository.findBy_movieid(movieID);
        }
    }

    public void delete(Screening screening) {
        if (screening == null) {
            LOGGER.log(Level.SEVERE, "Screening is null. Are you sure you have connected your form to the application?");
            return;
        }
        LOGGER.log(Level.SEVERE,
                "ID: "+ screening.getScreeningid() +
                        " MovieID: " + screening.get_movieid() +
                        " SalonID: " + screening.get_salonid() +
                        " Date: " + screening.getDate() +
                        " StartTime: " + screening.getStart_time() );
        repository.delete(screening);
    }

    public void save(Screening screening) {
        if (screening == null) {
            LOGGER.log(Level.SEVERE, "Screening is null. Are you sure you have connected your form to the application?");
            return;
        }
        LOGGER.log(Level.SEVERE,
                "ID: "+ screening.getScreeningid() +
                        " MovieID: " + screening.get_movieid() +
                        " SalonID: " + screening.get_salonid() +
                        " Date: " + screening.getDate() +
                        " StartTime: " + screening.getStart_time() );
        repository.save(screening);
    }
}
