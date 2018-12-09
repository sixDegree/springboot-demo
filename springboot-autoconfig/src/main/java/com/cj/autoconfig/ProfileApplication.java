package com.cj.autoconfig;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.cj.autoconfig.service.CalculateService;

@ComponentScan(basePackages="com.cj.autoconfig.service")
public class ProfileApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(ProfileApplication.class)
			.web(WebApplicationType.NONE)
			.profiles("Java8")
			.run(args);
			
		CalculateService calculateService = context.getBean(CalculateService.class);
		System.out.println("calculateService Bean : "+calculateService);	
		calculateService.sum(1,2,3,4,5,6,7,8,9,10);
		
		context.close();
	}
}
