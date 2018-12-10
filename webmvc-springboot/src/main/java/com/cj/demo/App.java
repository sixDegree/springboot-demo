package com.cj.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication/*(scanBasePackages="com.cj.demo")*/
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
