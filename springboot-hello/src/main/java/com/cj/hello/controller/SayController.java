package com.cj.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cj.hello.config.SayProperties;

@RestController
public class SayController {

	@Value("${content}")
	private String content;
	@Autowired
    private SayProperties sayProperties;
	
	@GetMapping("/testSay")
    public String say(){
        return content+" -- "+sayProperties;
    }
}
