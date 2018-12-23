package com.cj.demo2.processor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

public class ExtendPropertySourcesEnvironmentPostProcessor implements EnvironmentPostProcessor{

	@Override
	public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
		System.err.println("execute ExtendPropertySourcesEnvironmentPostProcessor#postProcessEnvironment: put user.id=60");
		MutablePropertySources propertySources=environment.getPropertySources();
		Map<String,Object> source=new HashMap<String,Object>();
		source.put("user.id",60);
		MapPropertySource ps=new MapPropertySource("from-postProcessEnvironment", source);
		propertySources.addFirst(ps);
	}

}
