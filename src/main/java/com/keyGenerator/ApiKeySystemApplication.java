package com.keyGenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class ApiKeySystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiKeySystemApplication.class, args);
	}

}
