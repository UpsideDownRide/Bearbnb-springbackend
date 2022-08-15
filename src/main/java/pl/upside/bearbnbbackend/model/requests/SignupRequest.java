package pl.upside.bearbnbbackend.model.requests;

public record SignupRequest(String email, String password, String firstName, String lastName, String dateOfBirth) {
}
