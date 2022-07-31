package pl.upside.bearbnbbackend.repositories;

import pl.upside.bearbnbbackend.model.User;

import java.util.Optional;

public interface UserRepositoryCustom {

    boolean existsByEmail(String email);
    Optional<User> saveIfNotExists(User user);
}
