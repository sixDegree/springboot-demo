package com.cj.demo2.listener;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

public class ExtendPropertySourcesApplicationListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

	@Override
	public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
		System.err.println("execute ExtendPropertySourcesApplicationListener#onApplicationEvent(ApplicationEnvironmentPreparedEvent): put user.id=55");
		MutablePropertySources propertySources=event.getEnvironment().getPropertySources();
		Map<String,Object> source=new HashMap<String,Object>();
		source.put("user.id",55);
		MapPropertySource ps=new MapPropertySource("from-applicationEnvironmentPreparedEvent", source);
		propertySources.addFirst(ps);
	}

}
