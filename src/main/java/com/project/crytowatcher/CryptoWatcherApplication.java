package com.project.crytowatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CryptoWatcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoWatcherApplication.class, args);
	}
}
