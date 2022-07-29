package pl.upside.bearbnbbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private final long id;
    private String email;
    private String password;
    private List<RoleNotImplemented> roles;
}
