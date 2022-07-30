package pl.upside.bearbnbbackend.config;

import lombok.Getter;

@Getter
public enum Routes {
    ROOT("/"),
    SIGNUP("/signup"),
    LOGIN("/login"),
    LOGOUT("/logout");

    final String route;

    Routes(String route) {
        this.route = route;
    }
}
