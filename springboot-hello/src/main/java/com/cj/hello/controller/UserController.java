package com.cj.hello.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cj.hello.entity.User;
import com.cj.hello.service.UserService;
import com.cj.hello.util.ResponseUtils;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public Object listAll() {
		List<User> users=userService.listAll();
		return ResponseUtils.result(users!=null,users);
	}
	
	@GetMapping("/users/{id}")
	public Object getDetails(@PathVariable String id) {
		User user=userService.getDetails(id);
		return ResponseUtils.result(user!=null,user);
	}
	
}
