package br.com.tecflix_app.model.enums;

public enum Category {
    CATEGORY_A("category a"),
    CATEGORY_B("category b"),
    CATEGORY_C("category c");
    
    private String category;

    Category(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}