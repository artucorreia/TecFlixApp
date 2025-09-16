package br.com.tecflix_app.service.util;

import java.util.UUID;

public class EmailCodeGenerator {
    public static String generateCode() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
