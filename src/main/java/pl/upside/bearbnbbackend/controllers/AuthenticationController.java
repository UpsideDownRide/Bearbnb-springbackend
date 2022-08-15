package pl.upside.bearbnbbackend.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.upside.bearbnbbackend.model.User;
import pl.upside.bearbnbbackend.model.UserPersonal;
import pl.upside.bearbnbbackend.model.requests.LoginRequest;
import pl.upside.bearbnbbackend.model.requests.SignupRequest;
import pl.upside.bearbnbbackend.model.responses.LoginResponse;
import pl.upside.bearbnbbackend.services.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RequestMapping("/api/auth")
@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody final LoginRequest loginRequest) {
        String token = userService.login(loginRequest.email(), loginRequest.password());
        User loggedInUser = userService.findByEmail(loginRequest.email()).orElseThrow();
        return new LoginResponse(loggedInUser, token);
    }

    @PostMapping("/signup")
    public User signup(@RequestBody final SignupRequest signupRequest) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSSX");
        LocalDate dateOfBirth = Optional.ofNullable(LocalDate.parse(signupRequest.dateOfBirth(), formatter))
                .orElseThrow(IllegalArgumentException::new);
        UserPersonal personalData = new UserPersonal();
        User userToSave = new User();

        personalData.setDateOfBirth(dateOfBirth);
        personalData.setFirstName(signupRequest.firstName());
        personalData.setLastName(signupRequest.lastName());
        personalData.setUser(userToSave);

        userToSave.setEmail(signupRequest.email());
        userToSave.setUserPersonal(personalData);
        userToSave.setEmail(signupRequest.email());
        userToSave.setPassword(signupRequest.password());

        return userService.save(userToSave);
    }
}
