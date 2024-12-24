package br.com.tecflix_app.service.util;

import java.util.UUID;

public class EmailCodeGenerator {
    public static String genarateCode() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
