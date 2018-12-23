package com.cj.autoconfig;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.cj.autoconfig.enable.EnableHelloWorld;
import com.cj.outside.HelloController;

@EnableHelloWorld
public class HelloWorldEnableApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(HelloWorldEnableApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);
			
		HelloController helloController = context.getBean("helloController",HelloController.class);
		System.out.println("helloController Bean : "+helloController);	
		
		String helloWorld = context.getBean("helloWorld",String.class);
		System.out.println("helloWorld Bean : "+helloWorld);	
		
		context.close();
	}
}
