package com.cj.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class WebMvcConfig {

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		// ThymeleafAutoConfiguration: ThymeleafViewResolver Ordered.LOWEST_PRECEDENCE - 5
		viewResolver.setOrder(Ordered.LOWEST_PRECEDENCE-10);
		return viewResolver;
	} 
}
