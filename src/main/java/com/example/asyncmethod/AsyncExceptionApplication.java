package com.example.asyncmethod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AsyncExceptionApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsyncExceptionApplication.class, args);
	}
}
