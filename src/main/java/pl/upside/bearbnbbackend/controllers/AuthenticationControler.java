package pl.upside.bearbnbbackend.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/signin")
    public User signin(@RequestParam String email, @RequestParam String password){
        return new User(-1L, "not@implemented", "notimplemented", null);
    }

    @CrossOrigin("*")
    @PostMapping("/signup")
    public User signup(@RequestParam String email, @RequestParam String password){
        Role userRole = roleRepository.findByName(ERoles.ROLE_USER.toString()).orElseThrow();
        User user = userRepository.save(new User(email, password, List.of(userRole)));
        log.info("Created user: " + email);
        return user;
    }

    @GetMapping("/signup")
    public User signupTest(){
        return new User(-1L, "not@implemented", "notimplemented", null);
    }
}
