package pl.upside.bearbnbbackend.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.upside.bearbnbbackend.model.ERoles;
import pl.upside.bearbnbbackend.model.Role;
import pl.upside.bearbnbbackend.model.User;
import pl.upside.bearbnbbackend.repository.RoleRepository;
import pl.upside.bearbnbbackend.repository.UserRepository;

import java.util.List;

@RequestMapping("/api/auth")
@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthenticationControler {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signin")
    public User signin(@RequestParam String email, @RequestParam String password){
        return new User(-1L, "not@implemented", "notimplemented", null);
    }

    @CrossOrigin("*")
    @PostMapping("/signup")
    public User signup(@RequestParam String email, @RequestParam String password){
        if (userRepository.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        }
        Role userRole = roleRepository.findByName(ERoles.ROLE_USER.toString()).orElseThrow();
        User userToAdd = new User(email, passwordEncoder.encode(password), List.of(userRole));
        User user = userRepository.save(userToAdd);
        log.info("Created user: " + email);
        return user;
    }

    @GetMapping("/signup")
    public User signupTest(){
        return new User(-1L, "not@implemented", "notimplemented", null);
    }
}
