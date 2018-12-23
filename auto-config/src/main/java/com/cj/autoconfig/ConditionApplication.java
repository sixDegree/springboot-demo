package com.cj.autoconfig;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.cj.autoconfig.condition.ConditionalOnSystemProperty;

public class ConditionApplication {

	@Bean
	@ConditionalOnSystemProperty(name="user.name",value="cj")
	public String helloWorld() {
		return "Hello world!";
	}
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(ConditionApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		String helloWorld = context.getBean("helloWorld",String.class);	
		System.out.println("helloWorld Bean:"+helloWorld);
		
		context.close();
	}

}
