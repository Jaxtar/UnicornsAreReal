package school.newton.sysjs2.grupp3.UAR.backend.repository;

import org.springframework.data.repository.CrudRepository;
import school.newton.sysjs2.grupp3.UAR.backend.model.Booking;

public interface BookingRepository extends CrudRepository<Booking, Integer> {
}