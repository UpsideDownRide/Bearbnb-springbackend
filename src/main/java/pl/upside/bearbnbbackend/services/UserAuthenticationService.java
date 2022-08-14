package pl.upside.bearbnbbackend.services;

import pl.upside.bearbnbbackend.model.User;
import pl.upside.bearbnbbackend.model.UserDetailsImpl;

import java.util.Optional;

public interface UserAuthenticationService {
    Optional<String> login(String username, String password);

    Optional<UserDetailsImpl> findByToken(String token);

    void logout(User user);
}
