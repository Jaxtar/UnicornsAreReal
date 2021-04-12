package school.newton.sysjs2.grupp3.UAR.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import school.newton.sysjs2.grupp3.UAR.backend.model.Booking;

public interface BookingRepository extends CrudRepository<Booking, Integer> {
    List<Booking> findBy_screeningid(Integer _screeningid);
}
