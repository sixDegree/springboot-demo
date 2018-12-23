package com.cj.demo2.listener;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

public class ExtendPropertySourcesRunListener implements SpringApplicationRunListener,Ordered{

	private final SpringApplication application;
	private final String[] args;
	
	public ExtendPropertySourcesRunListener(SpringApplication application, String[] args) {
		this.application = application;
		this.args = args;
	}


	@Override
	public int getOrder() {
		return 1;	// EventPublishingRunListener order is 0
	}
	
	@Override
	public void starting() {
		
	}

	@Override
	public void environmentPrepared(ConfigurableEnvironment environment) {
		System.err.println("execute ExtendPropertySourcesRunListener#environmentPrepared: put user.id=50");
		MutablePropertySources propertySources=environment.getPropertySources();
		Map<String,Object> source=new HashMap<String,Object>();
		source.put("user.id",50);
		MapPropertySource ps=new MapPropertySource("from-environmentPrepared", source);
		propertySources.addFirst(ps);
	}

	@Override
	public void contextPrepared(ConfigurableApplicationContext context) {
		System.err.println("execute ExtendPropertySourcesRunListener#contextPrepared");
	}

	@Override
	public void contextLoaded(ConfigurableApplicationContext context) {
		System.err.println("execute ExtendPropertySourcesRunListener#contextLoaded");
	}

	@Override
	public void started(ConfigurableApplicationContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void running(ConfigurableApplicationContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void failed(ConfigurableApplicationContext context, Throwable exception) {
		// TODO Auto-generated method stub
		
	}

}
