package com.cj.demo2.initializer;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.cj.demo2.SpringBootDemoApp;

public class MySpringBootServletInitializer extends SpringBootServletInitializer{
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		System.out.println("MySpringBootServletInitializer configure");
		builder.sources(SpringBootDemoApp.class);
		return builder;
	}
}
