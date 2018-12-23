package com.cj.autoconfig;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.cj.outside.HelloController;

@ComponentScan(basePackages="com.cj.outside")
public class HelloWorldScanApplication {
	 public static void main(String[] args) {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(HelloWorldScanApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);
			
		HelloController helloController = context.getBean("helloController",HelloController.class);
		System.out.println("helloController Bean : "+helloController);	
		
		context.close();
	}
}
