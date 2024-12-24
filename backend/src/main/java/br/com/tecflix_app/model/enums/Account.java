package br.com.tecflix_app.model.enums;

public enum Account {
    POUPANCA("poupanca"),
    CORRENTE("corrente");
    
    private String account;

    Account(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }
}