package br.com.tecflix_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableScheduling
@OpenAPIDefinition(
	info = 
		@Info(
			title = "TecFlix API", 
			version = "v1", 
			description = "API developed for TecFlix project",
			termsOfService = "https://com.example.com/terms",
			license = @License(
				name = "Apache 2.0",
				url = "https://www.apache.org/licenses/LICENSE-2.0"
			)
		)
) 
public class TecflixAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TecflixAppApplication.class, args);
	}

}
