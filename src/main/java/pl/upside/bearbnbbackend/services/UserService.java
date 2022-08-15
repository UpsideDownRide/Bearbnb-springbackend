package pl.upside.bearbnbbackend.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.upside.bearbnbbackend.exceptions.UserAlreadyExistsAuthException;
import pl.upside.bearbnbbackend.model.ERoles;
import pl.upside.bearbnbbackend.model.Role;
import pl.upside.bearbnbbackend.model.User;
import pl.upside.bearbnbbackend.repositories.RoleRepository;
import pl.upside.bearbnbbackend.repositories.UserRepository;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService tokenService;

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User save(User user) {
        Set<Role> userRoles = user.getRoles() == null ? Set.of(roleRepository.findByName(ERoles.ROLE_USER.toString()).orElseThrow()) : user.getRoles();
        user.setRoles(userRoles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("Creating user: " + user.getEmail());
        return userRepository.saveIfNotExists(user).orElseThrow(UserAlreadyExistsAuthException::new);
    }

    public String login(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        UserDetails user = (UserDetails) authentication.getPrincipal();
        return tokenService.generateToken(user);
    }
}
