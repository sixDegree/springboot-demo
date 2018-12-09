package com.cj.autoconfig;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.cj.outside.HelloController;

@EnableAutoConfiguration
public class HelloWorldAutoApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(HelloWorldAutoApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);
			
		HelloController helloController = context.getBean("helloController",HelloController.class);
		System.out.println("helloController Bean : "+helloController);	
		
		String helloWorld = context.getBean("helloWorld",String.class);
		System.out.println("helloWorld Bean : "+helloWorld);	
		
		context.close();
	}
}
