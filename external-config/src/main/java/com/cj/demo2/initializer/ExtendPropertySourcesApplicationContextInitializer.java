package com.cj.demo2.initializer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

public class ExtendPropertySourcesApplicationContextInitializer<C extends ConfigurableApplicationContext> implements ApplicationContextInitializer<C> {

	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {
		System.err.println("execute ExtendPropertySourcesApplicationContextInitializer#initialize: put user.id=65");
		MutablePropertySources propertySources=applicationContext.getEnvironment().getPropertySources();
		Map<String,Object> source=new HashMap<String,Object>();
		source.put("user.id",65);
		MapPropertySource ps=new MapPropertySource("from-applicationContextInitialize", source);
		propertySources.addFirst(ps);
		
	}

}
