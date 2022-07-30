package pl.upside.bearbnbbackend.config;

import lombok.Getter;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum PublicRoutes {
    SIGNUP(Routes.SIGNUP.getRoute()),
    LOGIN(Routes.LOGIN.getRoute());

    final String route;

    PublicRoutes(String route) {
        this.route = route;
    }

    public static String[] getAll() {
        return EnumSet.allOf(PublicRoutes.class).stream().map(PublicRoutes::getRoute).toArray(String[]::new);
    }

}
