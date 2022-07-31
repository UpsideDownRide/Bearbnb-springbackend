package pl.upside.bearbnbbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistsAuthException extends AuthenticationException {
    public UserAlreadyExistsAuthException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UserAlreadyExistsAuthException(String msg) {
        super(msg);
    }

    public UserAlreadyExistsAuthException(Throwable cause) {
        super("Username already exists", cause);
    }

    public UserAlreadyExistsAuthException() {
        super("Username already exists");
    }
}
