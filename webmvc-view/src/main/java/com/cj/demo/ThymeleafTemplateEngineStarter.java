package com.cj.demo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

public class ThymeleafTemplateEngineStarter {
	public static void main(String[] args) throws IOException {
		// 1. 获取模板内容
		//String content = "<p th:text=\"${message}\">!!!</p>";
		
		// 从 classpath:/templates/thymeleaf/hello.html 读取内容
	    ResourceLoader resourceLoader = new DefaultResourceLoader();
	    Resource resource = resourceLoader.getResource("classpath:/templates/thymeleaf/hello.html");
	    File templateFile = resource.getFile();
	    FileInputStream inputStream = new FileInputStream(templateFile);
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    IOUtils.copy(inputStream, outputStream);
	    inputStream.close();
	    String content = outputStream.toString("UTF-8");
		
		// 2. 创建渲染上下文
		Context context = new Context();
		context.setVariable("message", "Hello,World");
	 		
		// 3. 模板引擎进行渲染
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		String result = templateEngine.process(content, context);
		System.out.println(result);
	}
}
