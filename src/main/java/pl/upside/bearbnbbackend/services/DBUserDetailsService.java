package pl.upside.bearbnbbackend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.upside.bearbnbbackend.model.User;
import pl.upside.bearbnbbackend.model.UserDetailsImpl;
import pl.upside.bearbnbbackend.repository.UserRepository;

import java.util.Optional;

@RequiredArgsConstructor
public class DBUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> oUser = userRepository.findByEmail(email);
        User user = oUser.orElseThrow(() -> new UsernameNotFoundException("Error: username " + email + " not found."));
        return new UserDetailsImpl(user);
    }
}
