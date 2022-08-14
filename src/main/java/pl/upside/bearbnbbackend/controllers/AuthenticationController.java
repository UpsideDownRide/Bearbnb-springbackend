package pl.upside.bearbnbbackend.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.upside.bearbnbbackend.model.User;
import pl.upside.bearbnbbackend.model.requests.LoginRequest;
import pl.upside.bearbnbbackend.model.requests.SignupRequest;
import pl.upside.bearbnbbackend.model.responses.LoginResponse;
import pl.upside.bearbnbbackend.services.JwtTokenService;
import pl.upside.bearbnbbackend.services.UserService;

@RequestMapping("/api/auth")
@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final JwtTokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody final LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password()));
        UserDetails user = (UserDetails) authentication.getPrincipal();
        String token = tokenService.generateToken(user);
        return new LoginResponse(loginRequest.email(), token);
    }

    @PostMapping("/signup")
    public User signup(@RequestBody final SignupRequest signupRequest){
        User userToSave = new User(signupRequest.email(), signupRequest.password());
        return userService.save(userToSave);
    }
}
