package com.airline.flight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlightApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightApplication.class, args);
		System.out.println("Hello World, It is a Flight Service");
		System.out.println("Testing in flights testing again testing and checking one more time test");
	}
}
