package com.g4.tp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.g4.tp.model.User;

@SpringBootApplication
public class TpApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpApplication.class, args);

		User user = new User("John Doe", "john.doe@example.com", "password123");

		
	}

}
