package com.cj.demo.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cj.demo.converter.PropertiesHttpMessageConverter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

//	@Override
//	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//		converters.add(new PropertiesHttpMessageConverter());
//	}
	
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new PropertiesHttpMessageConverter());
	}
}
