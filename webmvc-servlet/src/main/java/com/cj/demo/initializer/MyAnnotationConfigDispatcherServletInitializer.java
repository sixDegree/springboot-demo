//package com.cj.demo.initializer;
//
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//
//@ComponentScan(basePackages = "com.cj.demo.controller")
//public class MyAnnotationConfigDispatcherServletInitializer 
//	extends AbstractAnnotationConfigDispatcherServletInitializer{
//
//	@Override
//	protected Class<?>[] getRootConfigClasses() {	//web.xml
//		return new Class[0];
//	}
//
//	@Override
//	protected Class<?>[] getServletConfigClasses() {	// DispatchServlet
//		System.out.println("get servlet config classes.......");
//		//return new Class[]{DispatchServletConfig.class};
//		return new Class[] {this.getClass()};
//	}
//
//	@Override
//	protected String[] getServletMappings() {
//		System.out.println("get servlet mappings......");
//		return new String[]{"/"};
//	}
//}
