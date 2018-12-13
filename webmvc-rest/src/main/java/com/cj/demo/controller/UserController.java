package com.cj.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cj.demo.entity.User;

@RestController
public class UserController {

  	//@CrossOrigin("*")
	@GetMapping(value="/say")
    public String say(@RequestParam(required = false) String message) {
        return "Say:" + message;
    }
	
	@GetMapping(value="/saylimit",consumes="application/json",produces="text/html;charset=UTF-8")
    public String saylimit(@RequestParam(required = false) String message) {
        return "Saylimit:" + message;
    }
	
	@GetMapping("/users")
	public Object listUsers() {
		List<User> users=new ArrayList<User>();
		users.add(new User(1,"Tom"));
		users.add(new User(2,"Lucy"));
		users.add(new User(3,"Jack"));
		return users;
	}
	
	@PostMapping("/users")
	public Object addUser(@RequestBody User user) {
		System.out.println("addUser:"+user);
		return user;
	}
	
	@PostMapping(value="/props",consumes = "text/properties;charset=UTF-8")
	public Object addProp(@RequestBody Properties prop) {
		System.out.println("addProp:"+prop);
		return prop;
	}
}
