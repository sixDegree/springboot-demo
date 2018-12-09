package com.cj.application.listener;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;

public class MySmartApplicationListener implements SmartApplicationListener{

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		System.out.println("MySmart ApplicationEvent: "+ event.getClass().getSimpleName());
		if (event instanceof ApplicationEnvironmentPreparedEvent) {
            ApplicationEnvironmentPreparedEvent preparedEvent = (ApplicationEnvironmentPreparedEvent) event;
            Environment environment = preparedEvent.getEnvironment();
            System.out.println("environment: " + environment);
        }
	}

	@Override
	public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
		return ApplicationEnvironmentPreparedEvent.class.isAssignableFrom(eventType)
                || ContextRefreshedEvent.class.isAssignableFrom(eventType);
	}

	@Override
	public boolean supportsSourceType(Class<?> sourceType) {
		return true;
	}
	
	@Override
	public int getOrder() {
		return Ordered.HIGHEST_PRECEDENCE+25;
	}

}
