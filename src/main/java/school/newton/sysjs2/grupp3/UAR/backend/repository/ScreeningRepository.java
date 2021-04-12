package school.newton.sysjs2.grupp3.UAR.backend.repository;

import org.springframework.data.repository.CrudRepository;
import school.newton.sysjs2.grupp3.UAR.backend.model.Screening;

import java.util.List;

public interface ScreeningRepository extends CrudRepository<Screening, Integer> {
    List<Screening> findAll();
    List<Screening> findBy_movieid(Integer searchTerm);
}
