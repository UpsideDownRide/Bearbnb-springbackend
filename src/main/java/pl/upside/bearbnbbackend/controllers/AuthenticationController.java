package pl.upside.bearbnbbackend.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.upside.bearbnbbackend.model.User;
import pl.upside.bearbnbbackend.model.requests.LoginRequest;
import pl.upside.bearbnbbackend.model.requests.SignupRequest;
import pl.upside.bearbnbbackend.model.responses.LoginResponse;
import pl.upside.bearbnbbackend.services.UserAuthenticationService;
import pl.upside.bearbnbbackend.services.UserService;

@RequestMapping("/api/auth")
@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final UserAuthenticationService auth;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        String token = auth.login(loginRequest.email(), loginRequest.password())
                .orElseThrow(() -> new BadCredentialsException("Invalid login/password"));
        return new LoginResponse(loginRequest.email(), token);
    }

    @PostMapping("/signup")
    public User signup(@RequestBody SignupRequest signupRequest){
        User userToSave = new User(signupRequest.email(), signupRequest.password());
        return userService.save(userToSave);
    }
}
