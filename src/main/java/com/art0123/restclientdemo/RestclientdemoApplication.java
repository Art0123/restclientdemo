package com.art0123.restclientdemo;

import com.art0123.restclientdemo.model.User;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class RestclientdemoApplication {

	private static final Logger logger = LoggerFactory.getLogger(RestclientdemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RestclientdemoApplication.class, args);
		ArrayList<User> users = new ArrayList<>(getListOfUsers());

		for (User user : users) {
			logger.info(String.valueOf(user));
		}
	}

	@Bean
	public static RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public static List<User> getListOfUsers() {
		logger.info("Trying to access: https://jsonplaceholder.typicode.com/users");

		ResponseEntity<List<User>> responseEntity =
				restTemplate().exchange("https://jsonplaceholder.typicode.com/users",
						HttpMethod.GET, null,
						new ParameterizedTypeReference<>() {
						});

		List<User> users = responseEntity.getBody();

		return users;
	}
}
