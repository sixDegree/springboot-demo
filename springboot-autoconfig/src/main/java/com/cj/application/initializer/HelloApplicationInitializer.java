package com.cj.application.initializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Order(Ordered.HIGHEST_PRECEDENCE+10)
public class HelloApplicationInitializer<C extends ConfigurableApplicationContext> 
	implements ApplicationContextInitializer<C> {

	@Override
	public void initialize(C applicationContext) {
		System.out.println("Hello: ApplicationContext id="+applicationContext.getId());
	}

}