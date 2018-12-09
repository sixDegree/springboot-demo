package com.cj.autoconfig.enable;

import org.springframework.context.annotation.Bean;

import com.cj.outside.HelloController;

//@Configuration
public class HelloWorldConfiguration {

	@Bean
	public String helloWorld() {
		System.out.println("new bean:helloWrold");
		return new String("Hello world!");
	}
	
	@Bean
	public HelloController helloController() {
		System.out.println("new bean:helloController");
		return new HelloController();
	}
}
