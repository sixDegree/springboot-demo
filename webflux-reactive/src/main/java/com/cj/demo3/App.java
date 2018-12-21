package com.cj.demo3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class,args);
	}
	/* Verify:
	 *  curl -i -X POST localhost:8080/users?name=Tom
	 *  curl -i -X POST localhost:8080/users?name=Lucy
	 *  curl -i localhost:8080/users
	 * */
}
