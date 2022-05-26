package com.sparta.assignment031;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Assignment031Application {

	public static void main(String[] args) {
		SpringApplication.run(Assignment031Application.class, args);
	}

}
