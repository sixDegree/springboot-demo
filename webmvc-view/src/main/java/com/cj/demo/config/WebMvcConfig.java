package com.cj.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	// 1.修改bean name为myViewResolver
	// 不与ContentNegotiatingViewResolver装载条件冲突,则系统会自动加载ContentNegotiatingViewResolver
	// ContentNegotiatingViewResolver装载条件：
	// @ConditionalOnBean(ViewResolver.class)
	// @ConditionalOnMissingBean(name = "viewResolver", value = ContentNegotiatingViewResolver.class)，
	// 2. set viewResolver content-type (to distinguish with other viewResolvers')
	@Bean
	public InternalResourceViewResolver myViewResolver() {
		InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		// ThymeleafAutoConfiguration: ThymeleafViewResolver Ordered.LOWEST_PRECEDENCE - 5
		viewResolver.setOrder(Ordered.LOWEST_PRECEDENCE-10);
		// Set ViewResolver Content-Type
		viewResolver.setContentType("text/xml;charset=UTF-8");
		return viewResolver;
	} 
	
//	@Override
//	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//		configurer.favorParameter(true)
//				.favorPathExtension(true);
//	}
}
