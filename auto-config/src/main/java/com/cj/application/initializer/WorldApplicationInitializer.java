package com.cj.application.initializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;

public class WorldApplicationInitializer<C extends ConfigurableApplicationContext> 
	implements ApplicationContextInitializer<C>,Ordered {

	@Override
	public void initialize(C applicationContext) {
		System.out.println("World: ApplicationContext id="+applicationContext.getId());
	}

	@Override
	public int getOrder() {
		return Ordered.HIGHEST_PRECEDENCE+10-1;
	}
}