package br.com.tecflix_app.model.enums;

public enum Gender {
    FAMALE("female"),
    MALE("male"),
    OTHER("other");
    
    private String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
