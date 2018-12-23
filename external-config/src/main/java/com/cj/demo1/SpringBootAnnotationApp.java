package com.cj.demo1;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.cj.demo1.domain.User;

@EnableAutoConfiguration
public class SpringBootAnnotationApp {

	// @Value
	@Bean
    public User user(@Value("${user.id}") Long id, @Value("${user.name}") String name,
                     @Value("${user.age:${my.user.age:32}}") Integer age) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        return user;
	}
	
	private class Hello{
		@Value("${hello.msg}")
		private String message;
		@Value("${hello.target}")
		private String target;
		@Override
		public String toString() {
			return "Hello [message=" + message + ", target=" + target + "]";
		}
	}
	
	@Bean
	public Hello hello(){
		return new Hello();
	}
	
	// @ConfigurationProperties,@ConditionalOnProperty
	@Bean
    @ConfigurationProperties(prefix = "user")
	@ConditionalOnProperty(prefix = "user",name = "city.postCode", matchIfMissing = false, havingValue = "215000")
    public User user2() {
        return new User();
    }
	
	@Configuration
	public static class MyEnvironment{
		private Environment environment;
		@Autowired
		public MyEnvironment(Environment environment){
			System.out.println("execute MyEnvironment#constructor...");
			this.environment = environment;
		}
	}
	
	@Configuration
	public static class MyEnvironmentBeanFactory implements BeanFactoryAware {
		private Environment environment;
		
		@Override
		public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
			System.out.println("execute MyEnvironmentBeanFactory#setBeanFactory...");
			this.environment=beanFactory.getBean(Environment.class);
		}
	}
	
	@Configuration
	public static class MyEnvironmentAware implements EnvironmentAware{
		private Environment environment;
		@Override
		public void setEnvironment(Environment environment) {
			System.out.println("execute MyEnvironmentAware#setEnvironment...");
			this.environment=environment;
		}
	}
	
//	@Autowired
//	private Environment environment;
	
	@Bean
	@Autowired	// inject Environment,this annotation is optional,will auto inject
	public User user3(Environment environment){
		Long id = environment.getRequiredProperty("user.id", Long.class);
	    String name = environment.getRequiredProperty("user.name");
	    Integer age = environment.getProperty("user.age", Integer.class,
	            environment.getProperty("my.user.age", Integer.class, 32));
	    User user = new User();
	    user.setId(id);
	    user.setName(name);
	    user.setAge(age);
	    return user;
	}
	
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx =
                new SpringApplicationBuilder(SpringBootAnnotationApp.class)
                        .web(WebApplicationType.NONE) // 非 Web 应用
                        .run(args);
        User user = ctx.getBean("user", User.class);
        Hello hello = ctx.getBean("hello",Hello.class);
        User user2 = ctx.getBean("user2",User.class);
        User user3 = ctx.getBean("user3",User.class);
        System.err.println("user:"+user);
        System.err.println("hello:"+hello);
        System.err.println("user2:"+user2);
        System.err.println("user3:"+user3);
        ctx.close();
	}
}


