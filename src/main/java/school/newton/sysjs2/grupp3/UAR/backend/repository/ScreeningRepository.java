package school.newton.sysjs2.grupp3.UAR.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import school.newton.sysjs2.grupp3.UAR.backend.model.Screening;

public interface ScreeningRepository extends CrudRepository<Screening, Integer> {
    List<Screening> findBy_movieid(Integer _movieid);
}
