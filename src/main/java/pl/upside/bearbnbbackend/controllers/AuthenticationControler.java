package pl.upside.bearbnbbackend.controllers;

import org.springframework.web.bind.annotation.*;
import pl.upside.bearbnbbackend.model.User;

@RequestMapping("/api/auth")
@RestController
public class AuthenticationControler {
    @PostMapping("/signin")
    public User signin(@RequestParam String email, @RequestParam String password){
        return new User(-1L, "not@implemented", "notimplemented", null);
    }

    @CrossOrigin("*")
    @PostMapping("/signup")
    public User signup(@RequestParam String email, @RequestParam String password){
        return new User(-1L, email, password, null);
    }

    @GetMapping("/signup")
    public User signupTest(){
        return new User(-1L, "not@implemented", "notimplemented", null);
    }
}
