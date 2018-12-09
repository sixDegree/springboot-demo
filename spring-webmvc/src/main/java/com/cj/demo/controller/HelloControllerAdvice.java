package com.cj.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;

@ControllerAdvice(assignableTypes=HelloController.class)
public class HelloControllerAdvice {
	
	@ModelAttribute("message")
	public String message(){
		return "Where are you?";
	}
	@ModelAttribute("acceptLanguage")
	public String acceptLanguage(@RequestHeader("Accept-Language") String acceptLanguage){
		return acceptLanguage;
	}
	@ModelAttribute("jsessionId")
	public String jsessionId(@CookieValue("JSESSIONID") String jsessionId){
		return jsessionId;
	}
	
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<String> onException(Throwable ex){
		return ResponseEntity.ok("handle exception:"+ex.getMessage());
	}
}
