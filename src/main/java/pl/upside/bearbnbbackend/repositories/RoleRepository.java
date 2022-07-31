package pl.upside.bearbnbbackend.repositories;
import org.springframework.data.repository.CrudRepository;
import pl.upside.bearbnbbackend.model.Role;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(String role);
}
