package br.com.tecflix_app.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:4200"})
@RestController
public class HelloWorldController {
    @GetMapping("/hello-world")
    public String helloWorldEndpoint() {
        return "Hello, World!";
    }
}
