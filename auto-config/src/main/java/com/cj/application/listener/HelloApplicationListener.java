package com.cj.application.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Order(Ordered.HIGHEST_PRECEDENCE+20)
public class HelloApplicationListener<C extends ApplicationEvent> implements ApplicationListener<C>{

	@Override
	public void onApplicationEvent(C event) {
		System.out.println("Hello ApplicationEvent: "+event.getClass().getSimpleName());
	}

}
