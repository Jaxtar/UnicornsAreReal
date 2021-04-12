package school.newton.sysjs2.grupp3.UAR.repository;

import org.springframework.data.repository.CrudRepository;
import school.newton.sysjs2.grupp3.UAR.model.Staffschedule;

import java.util.List;

public interface StaffScheduleRepository extends CrudRepository<Staffschedule, Integer> {
    List<Staffschedule> findAll();
    List<Staffschedule> findByLastnameStartsWithIgnoreCase(String lastname);
}