package com.cj.demo1;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.ConfigurableEnvironment;

import com.cj.demo1.domain.User;

@ImportResource("bean.xml") // 加载 XML 文件
@EnableAutoConfiguration
public class SpringBootPlaceholderApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                new SpringApplicationBuilder(SpringBootPlaceholderApp.class)
                        .web(WebApplicationType.NONE) // 非 Web 应用
                        .run(args);
        User user = ctx.getBean("user", User.class);
        System.err.println(user);
        System.err.printf("System.getProperty(\"%s\") : %s \n", "user.name", System.getProperty("user.name"));
        
        ConfigurableEnvironment environment = ctx.getEnvironment();
		environment.getPropertySources().forEach(item->{
			System.out.printf("[%s] : %s\n", item.getName(), item);
		});
        
        ctx.close();
    }
}
