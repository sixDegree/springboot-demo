package com.cj.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
	
//	@RequestMapping(value={"/"},method=RequestMethod.GET)
//	public String index(@RequestHeader("Accept-Language") String acceptLanguage
//			,@CookieValue("JSESSIONID") String jsessionId
//			,Model model) {
//		List<String> users=new ArrayList<String>();
//		users.add("Tom");
//		users.add("Susan");
//		users.add("Jack");
//		model.addAttribute("users", users);
//		model.addAttribute("message", "Where are you?");
//		model.addAttribute("acceptLanguage",acceptLanguage);
//		model.addAttribute("jsessionId",jsessionId);
//		System.out.println(model);
//		return "index";
//	}
	
	@RequestMapping(value={"/"},method=RequestMethod.GET)
	public String index(Model model){
		List<String> users=new ArrayList<String>();
		users.add("Tom");
		users.add("Susan");
		users.add("Jack");
		model.addAttribute("users", users);
		return "index";
	}
	
	// Move to HelloControllerAdvice
//	@ModelAttribute("message")
//	public String message(){
//		return "Where are you?";
//	}
//	@ModelAttribute("acceptLanguage")
//	public String acceptLanguage(@RequestHeader("Accept-Language") String acceptLanguage){
//		return acceptLanguage;
//	}
//	@ModelAttribute("jsessionId")
//	public String jsessionId(@CookieValue("JSESSIONID") String jsessionId){
//		return jsessionId;
//	}
	
	@RequestMapping(value="/{num}",method=RequestMethod.GET)
	public String doExTest(@PathVariable int num,Model model){
		model.addAttribute("message","number:"+num);
		return "index";
	}
}
