package com.airline.flight;

import com.airline.flight.entity.Airplane;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class FlightApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(FlightApplication.class, args);
		System.out.println("Hello World, It is a Flight Service");
		System.out.println("Testing in flights testing again testing and checking one more time test");

		Airplane airplane = context.getBean(Airplane.class);


	}
}
