package com.aaro.cleanDogAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.aaro.cleanDogAPI")
public class CleanDogApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CleanDogApiApplication.class, args);
	}

}
