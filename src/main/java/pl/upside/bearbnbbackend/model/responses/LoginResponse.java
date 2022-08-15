package pl.upside.bearbnbbackend.model.responses;

import pl.upside.bearbnbbackend.model.User;

public record LoginResponse(User user, String token) {
}
