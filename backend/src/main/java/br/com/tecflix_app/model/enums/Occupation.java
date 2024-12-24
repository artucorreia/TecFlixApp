package br.com.tecflix_app.model.enums;

public enum Occupation {
    PROFESSOR("professor"),
    DEVELOPER("developer");

    private String occupation;

    Occupation(String occupation) {
        this.occupation = occupation;
    }

    public String getOccupation() {
        return occupation;
    }
}
