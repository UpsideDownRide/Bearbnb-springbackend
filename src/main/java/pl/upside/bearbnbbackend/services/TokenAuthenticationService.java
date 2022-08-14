package pl.upside.bearbnbbackend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.upside.bearbnbbackend.model.User;
import pl.upside.bearbnbbackend.model.UserDetailsImpl;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenAuthenticationService implements UserAuthenticationService {
    private final TokenService tokenService;
    private final UserService users;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<String> login(final String email, final String password) {
        return users
                .findByEmail(email)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .map(user -> tokenService.newToken(Map.of("email", email))); 
    }

    @Override
    public Optional<UserDetailsImpl> findByToken(final String token) {
        return Optional
                .of(tokenService.verify(token))
                .map(map -> map.get("email"))
                .flatMap(users::findByEmail)
                .map(UserDetailsImpl::new);
    }

    @Override
    public void logout(final User user) {
    }
}

