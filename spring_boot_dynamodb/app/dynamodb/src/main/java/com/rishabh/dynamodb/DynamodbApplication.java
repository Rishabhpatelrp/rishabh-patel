package com.rishabh.dynamodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;

@SpringBootApplication
public class DynamodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(DynamodbApplication.class, args);
	}

}
