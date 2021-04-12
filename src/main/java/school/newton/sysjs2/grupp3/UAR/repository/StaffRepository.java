package school.newton.sysjs2.grupp3.UAR.repository;

import org.springframework.data.repository.CrudRepository;
import school.newton.sysjs2.grupp3.UAR.model.Staff;

import java.util.List;

public interface StaffRepository extends CrudRepository<Staff, Integer> {

    List<Staff> findAll();
    List<Staff> findByLastnameStartsWithIgnoreCase(String lastname);
}
