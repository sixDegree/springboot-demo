package com.cj.demo.servlet;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.cj.demo.config.DispatchServletConfig;

public class MyAnnotationConfigDispatcherServletInitializer 
	extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {	//web.xml
		return new Class[0];
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {	// DispatchServlet
		System.out.println("get servlet config classes.......");
		return new Class[]{DispatchServletConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		System.out.println("get servlet mappings......");
		return new String[]{"/"};
	}
}
