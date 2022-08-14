package pl.upside.bearbnbbackend.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }


    public User save(User user) {
        Set<Role> userRoles = user.getRoles() == null ? Set.of(roleRepository.findByName(ERoles.ROLE_USER.toString()).orElseThrow()) : user.getRoles();
        User userToAdd = new User(user.getEmail(),
                passwordEncoder.encode(user.getPassword()),
                userRoles);
        log.info("Created user: " + user.getEmail());
        return userRepository.saveIfNotExists(userToAdd).orElseThrow(UserAlreadyExistsAuthException::new);
    }
}
