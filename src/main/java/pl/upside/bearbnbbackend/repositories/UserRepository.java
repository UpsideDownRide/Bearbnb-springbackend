package pl.upside.bearbnbbackend.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.upside.bearbnbbackend.model.User;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long>, UserRepositoryCustom {
    Optional<User> findByEmail(String email);
}
