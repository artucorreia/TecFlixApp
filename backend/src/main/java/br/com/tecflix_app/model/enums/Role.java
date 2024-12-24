package br.com.tecflix_app.model.enums;

public enum Role {
    ADMIN("admin"),
    PROFESSOR("professor"),
    USER("user");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
