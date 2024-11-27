package br.com.tecflix_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TecflixAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TecflixAppApplication.class, args);
	}

}
