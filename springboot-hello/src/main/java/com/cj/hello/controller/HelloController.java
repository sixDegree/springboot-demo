package com.cj.hello.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping
	public Object Index() {
		return "Index Page";
	}
	
	@GetMapping("/say")
	public Object sayHello() {
		return "Hello world!";
	}
}
