package com.cj.application.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class MySpringApplicationRunListener implements SpringApplicationRunListener {

	public MySpringApplicationRunListener(SpringApplication application, String[] args) {

    }

	
	@Override
	public void starting() {
		System.out.println("MyRunListener: starting()");
	}

	@Override
	public void environmentPrepared(ConfigurableEnvironment environment) {
		System.out.println("MyRunListener: environmentPrepared(environment)");
	}

	@Override
	public void contextPrepared(ConfigurableApplicationContext context) {
		System.out.println("MyRunListener: contextPrepared(context)");
	}

	@Override
	public void contextLoaded(ConfigurableApplicationContext context) {
		System.out.println("MyRunListener: contextLoaded(context)");
	}

	@Override
	public void started(ConfigurableApplicationContext context) {
		System.out.println("MyRunListener: started(context)");
	}

	@Override
	public void running(ConfigurableApplicationContext context) {
		System.out.println("MyRunListener: running(context)");
	}

	@Override
	public void failed(ConfigurableApplicationContext context, Throwable exception) {
		System.out.println("MySpringRunListener: failed(context,exception)");
	}

}
