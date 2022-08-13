package pl.upside.bearbnbbackend.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.upside.bearbnbbackend.exceptions.UserAlreadyExistsAuthException;
import pl.upside.bearbnbbackend.model.ERoles;
import pl.upside.bearbnbbackend.model.Role;
import pl.upside.bearbnbbackend.model.User;
import pl.upside.bearbnbbackend.repositories.RoleRepository;
import pl.upside.bearbnbbackend.repositories.UserRepository;

import java.util.Set;

@RequestMapping("/api/auth")
@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signin")
    public User signin(@RequestParam String email, @RequestParam String password){
        return new User(-1L, "not@implemented", "notimplemented", null);
    }

    @PostMapping("/signup")
    public User signup(@RequestParam String email, @RequestParam String password){
        Role userRole = roleRepository.findByName(ERoles.ROLE_USER.toString()).orElseThrow();
        User userToAdd = new User(email, passwordEncoder.encode(password), Set.of(userRole));
        User user = userRepository.saveIfNotExists(userToAdd).orElseThrow(UserAlreadyExistsAuthException::new);
        log.info("Created user: " + email);
        return user;
    }
}
