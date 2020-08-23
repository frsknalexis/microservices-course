package com.dev.microservices.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MicroservicesCoursesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesCoursesApplication.class, args);
	}

}
