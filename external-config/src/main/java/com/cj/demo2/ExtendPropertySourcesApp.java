package com.cj.demo2;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@EnableAutoConfiguration
public class ExtendPropertySourcesApp {
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = new SpringApplicationBuilder(ExtendPropertySourcesApp.class)
			.web(WebApplicationType.NONE)
			.properties("user.id=100")	// 17. Default properties (specified by setting SpringApplication.setDefaultProperties)
			.run(of("--user.id=60"));	// 4. Command line arguments.
		
		ConfigurableEnvironment environment = ctx.getEnvironment();
		Long userId=environment.getProperty("user.id",Long.class);
		System.out.println("userId:"+userId);
		
		environment.getPropertySources().forEach(item->{
			System.out.printf("[%s] : %s\n", item.getName(), item);
		});
		
		ctx.close();
	}
	private static <T> T[] of(T... args) {
		return args;
	}
	
	/*
	execute order:
		execute ExtendPropertySourcesEnvironmentPostProcessor#postProcessEnvironment: put user.id=60
		execute ExtendPropertySourcesApplicationListener#onApplicationEvent(ApplicationEnvironmentPreparedEvent): put user.id=55
		execute ExtendPropertySourcesRunListener#environmentPrepared: put user.id=50
	 	execute ExtendPropertySourcesApplicationContextInitializer#initialize: put user.id=65
		execute ExtendPropertySourcesRunListener#contextPrepared
		execute ExtendPropertySourcesRunListener#contextLoaded
		
	result:
		userId:65
		[from-applicationContextInitialize] : MapPropertySource {name='from-applicationContextInitialize'}
		[configurationProperties] : ConfigurationPropertySourcesPropertySource {name='configurationProperties'}
		[from-environmentPrepared] : MapPropertySource {name='from-environmentPrepared'}
		[from-applicationEnvironmentPreparedEvent] : MapPropertySource {name='from-applicationEnvironmentPreparedEvent'}
		[from-postProcessEnvironment] : MapPropertySource {name='from-postProcessEnvironment'}
		[commandLineArgs] : SimpleCommandLinePropertySource {name='commandLineArgs'}
		[systemProperties] : MapPropertySource {name='systemProperties'}
		[systemEnvironment] : OriginAwareSystemEnvironmentPropertySource {name='systemEnvironment'}
		[random] : RandomValuePropertySource {name='random'}
		[applicationConfig: [classpath:/application.properties]] : OriginTrackedMapPropertySource {name='applicationConfig: [classpath:/application.properties]'}
		[defaultProperties] : MapPropertySource {name='defaultProperties'}
	*/
}
