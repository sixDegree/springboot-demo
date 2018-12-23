package com.cj.demo1;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cj.demo1.domain.User;

public class SpringPlaceholderApp {
	 public static void main(String[] args) {
	        String[] locations = {"spring-context.xml","bean.xml"};
	        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(locations);
	        User user = ctx.getBean("user", User.class);
	        System.err.println(user);	// User{id=20, name='Tom', age=null, city=City{postCode='null', name='null'}}
	        ctx.close();
	    }
}
