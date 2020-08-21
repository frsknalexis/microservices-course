package com.dev.microservices.core.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MicroservicesUsersApplication {

	public static void main(String... args) {
		SpringApplication.run(MicroservicesUsersApplication.class, args);
	}
}