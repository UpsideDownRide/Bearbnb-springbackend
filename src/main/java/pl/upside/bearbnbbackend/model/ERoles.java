package pl.upside.bearbnbbackend.model;

public enum ERoles {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    final String roleName;

    ERoles(String roleName) {
        this.roleName = roleName;
    }
}
