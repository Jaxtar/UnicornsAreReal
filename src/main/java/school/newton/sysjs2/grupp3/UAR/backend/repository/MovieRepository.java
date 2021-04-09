package school.newton.sysjs2.grupp3.UAR.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import school.newton.sysjs2.grupp3.UAR.backend.model.Movie;

import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {
   List<Movie> findAll();
   
      List<Movie> search(@Param("searchTerm") String searchTerm);
}
