package com.cj.demo3;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.Servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.cj.demo.servlet.MyAsyncServlet;

@EnableAutoConfiguration	
// will load: ServletWebServerFactoryAutoConfiguration,DispatcherServletAutoConfiguration,...
@ServletComponentScan(basePackages="com.cj.demo.servlet")
// only for EmbeddedWebServer, will scan @WebServlet,...
public class SpringBootServletApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootServletApp.class, args);
	}
	
	// doesn't work,because DispatchServlet earlier and also mapped to [/]
	// Ordered.HIGHEST_PRECEDENCE also doesn't work.
	/*
	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)	
	public MyAsyncServlet myAsyncServlet(){
		return new MyAsyncServlet();
	}*/
	
	// below register earlier than @ServletComponentScan
	// servlet name:myAsyncServlet
	// so the same name myAsyncServlet register by @ServletComponentScan won't be registered.
	@Bean
	public ServletRegistrationBean<Servlet> asyncServletRegistrationBean(){
		return new ServletRegistrationBean<Servlet>(new MyAsyncServlet(),"/asyncServlet2");
	}
	
	// no log output on the console, could check when debug.
	// log output is in sub class,eg: ServletRegistrationBean
	@Bean
	public ServletContextInitializer myServletContextInitializer(){
		return servletContext->{
			CharacterEncodingFilter filter=new CharacterEncodingFilter();
			servletContext.addFilter("myCharacterFilter", filter)
				.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/123");
		};
	}

}
