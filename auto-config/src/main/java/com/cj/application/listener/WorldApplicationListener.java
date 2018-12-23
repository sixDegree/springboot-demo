package com.cj.application.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;

public class WorldApplicationListener implements ApplicationListener<ContextRefreshedEvent>,Ordered{

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println("World ApplicationEvent: "+event.getClass().getSimpleName());
	}

	@Override
	public int getOrder() {
		return Ordered.HIGHEST_PRECEDENCE+30;
	}

}
