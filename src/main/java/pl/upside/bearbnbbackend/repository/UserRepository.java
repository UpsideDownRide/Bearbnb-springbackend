package pl.upside.bearbnbbackend.repository;

import org.springframework.data.repository.CrudRepository;
import pl.upside.bearbnbbackend.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
