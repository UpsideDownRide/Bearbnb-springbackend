package pl.upside.bearbnbbackend.config;

import lombok.Getter;

@Getter
public enum Routes {
    ROOT("/api/auth"),
    SIGNUP(ROOT.getRoute() + "/signup"),
    LOGIN(ROOT.getRoute() + "/login"),
    LOGOUT(ROOT.getRoute() + "/logout");

    final String route;

    Routes(String route) {
        this.route = route;
    }
}
