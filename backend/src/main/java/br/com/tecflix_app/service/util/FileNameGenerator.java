package br.com.tecflix_app.service.util;

public class FileNameGenerator {
    public static String generateFileName(String[] subStrings) {
        StringBuilder sb = new StringBuilder();
        for (String suString : subStrings) { sb.append(suString).append("_"); }
        return sb.toString();
    }    
}
