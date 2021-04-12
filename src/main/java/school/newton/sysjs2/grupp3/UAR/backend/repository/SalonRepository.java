package school.newton.sysjs2.grupp3.UAR.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import school.newton.sysjs2.grupp3.UAR.backend.model.Salon;

public interface SalonRepository extends CrudRepository<Salon, Integer> {
    List<Salon> findBySalonid(Integer salonid);
}
