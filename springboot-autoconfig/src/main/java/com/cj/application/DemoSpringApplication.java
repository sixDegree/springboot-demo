package com.cj.application;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class DemoSpringApplication {
	public static void main(String[] args) {
		
		// 1
//		ConfigurableApplicationContext context=SpringApplication.run(ApplicationConfiguration.class, args);
		
		// 2
//		//SpringApplication app = new SpringApplication(ApplicationConfiguration.class);
//		SpringApplication app = new SpringApplication();
//		Set<String> sources = new HashSet<String>();
//		sources.add(ApplicationConfiguration.class.getName());
//		app.setSources(sources);
//		app.setWebApplicationType(WebApplicationType.NONE);
//		app.setAdditionalProfiles("Java8");
//		ConfigurableApplicationContext context=app.run(args);
		
		// 3
		ConfigurableApplicationContext context = new SpringApplicationBuilder(ApplicationConfiguration.class)
				.web(WebApplicationType.NONE)	// WebApplicationType.SERVLET,WebApplicationType.REACTIVE
				.profiles("Java8")
				.run(args);

		/*
		 verify: 
		 
		 WebApplicationType.NONE:
			ConfigurableApplicationContext -- org.springframework.context.annotation.AnnotationConfigApplicationContext 
			Environment -- org.springframework.core.env.StandardEnvironment
		
		 WebApplicationType.SERVLET:
			ConfigurableApplicationContext -- org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext
			Environment -- org.springframework.web.context.support.StandardServletEnvironment
		
		 WebApplicationType.REACTIVE:
		 	ConfigurableApplicationContext -- org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebServerApplicationContext
		 	Environment -- org.springframework.core.env.StandardEnvironment
		*/
//		System.out.println("ConfigurableApplicationContext 类型：" + context.getClass().getName());	
//        System.out.println("Environment 类型：" + context.getEnvironment().getClass().getName());
//        
//		CalculateService calculateService = context.getBean(CalculateService.class);
//		System.out.println("calculateService Bean : "+calculateService);	
//		calculateService.sum(1,2,3,4,5,6,7,8,9,10);
		
		context.close();
	}
	
	@SpringBootApplication//(scanBasePackages={/*"com.cj.application",*/"com.cj.autoconfig.service"})
    public static class ApplicationConfiguration {

    }
}
