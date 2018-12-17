package com.cj.demo3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.cj.demo")
public class SpringBootStartApp {

	/*
	 * auto register servlet: Servlet dispatcherServlet mapped to [/]
	 * won't use self class: MyAnnotationConfigDispatcherServletInitializer
	 * visit http://localhost:8080/hello,http://localhost:8080/callable,... success!
	 * 
	 * can't scan out the @WebServlet annotation class: MyAsyncServlet
	 * visit localhost:8080/asyncServlet 404 fail!
	 * */
	public static void main(String[] args) {
		SpringApplication.run(SpringBootStartApp.class, args);
	}

}
