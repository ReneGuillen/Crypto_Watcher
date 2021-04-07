package com.project.crytowatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class CryptoWatcherApplication {
	
	//Starting point of the application.
	public static void main(String[] args) {
		SpringApplication.run(CryptoWatcherApplication.class, args);
	}
}
