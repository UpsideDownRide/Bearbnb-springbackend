package pl.upside.bearbnbbackend.controllers;

import org.springframework.web.bind.annotation.*;
import pl.upside.bearbnbbackend.model.User;

@RestController
@RequestMapping("/auth")
public class AuthenticationControler {

    @PostMapping("/signup")
    public User signin(@RequestParam String email, @RequestParam String password){
        return new User(-1L, "not@implemented", "notimplemented", null);
    }
}
