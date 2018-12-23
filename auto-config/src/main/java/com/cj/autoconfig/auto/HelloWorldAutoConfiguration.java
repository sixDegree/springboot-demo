package com.cj.autoconfig.auto;

import org.springframework.context.annotation.Configuration;

import com.cj.autoconfig.condition.ConditionalOnSystemProperty;
import com.cj.autoconfig.enable.EnableHelloWorld;

@Configuration							// Spring 模式装配
@EnableHelloWorld						// Spring @Enable模块装配
@ConditionalOnSystemProperty(name="user.name",value="cj")	// Spring 条件装配
public class HelloWorldAutoConfiguration {

}
