package br.com.tecflix_app.model.enums;

public enum SocialName {
    X("X"),
    YOUTUBE("youtube"),
    FACEBOOK("facebook"),
    INSTAGRAM("instagram"),
    BLOG("blog"),
    OTHER("other");

    private String socialName;

    SocialName(String socialName) {
        this.socialName = socialName;
    }

    public String getSocialName() {
        return socialName;
    }
}
