package br.com.tecflix_app.model.enums;

public enum Category {
    CATEGORIA_A("CATEGORIA_A"),
    CATEGORIA_B("CATEGORIA_B"),
    CATEGORIA_C("CATEGORIA_C");
    
    private String category;

    Category(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}