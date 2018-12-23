package com.cj.demo2;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/* 1. @TestPropertySource */

//@RunWith(SpringRunner.class)
//@TestPropertySource(properties="user.id=10")
//public class PropertySourceOrderTest {
//
//	@Value("${user.id}")
//	private Long userId;
//	
//	@Test
//	public void userIdTest(){
//		System.out.println("userId:"+userId);
//		Assert.assertEquals(new Long(10),userId);
//	}
//}


/* 2. @SpringBootTest */

//@RunWith(SpringRunner.class)
//@TestPropertySource(properties="user.id=10")
//@SpringBootTest(properties="user.id=11",classes=PropertySourceOrderTest.class)
//public class PropertySourceOrderTest {
//
//	@Value("${user.id}")
//	private Long userId;
//	
//	@Test
//	public void userIdTest(){
//		System.out.println("userId:"+userId);
//		Assert.assertEquals(new Long(10),userId);
//	}
//}

/* 3. Priority
 * @TestPropertySource#properties  user.id=10
 * @SpringBootTest#properties	   user.id=11
 * @TestPropertySource#location	   user.id=12
 * cmd args: -Duser.id=13		   user.id=13
 * */
//@RunWith(SpringRunner.class)
//@TestPropertySource(/*properties="user.id=10"*/locations="classpath:test.properties")
//@SpringBootTest(/*properties="user.id=11",*/classes=PropertySourceOrderTest.class)
//public class PropertySourceOrderTest {
//
//	@Value("${user.id:1}")
//	private Long userId;
//	
//	@Test
//	public void userIdTest(){
//		System.out.println("userId:"+userId);
//		Assert.assertEquals(new Long(12),userId);
//	}
//}


// PropertySource order:
// https://docs.spring.io/spring-boot/docs/2.0.2.RELEASE/reference/htmlsingle/#boot-features-external-config
// PropertySource : 带有名称的属性源,eg: Map,Properties 文件,YAML 文件
// Environment(ConfigurableEnvironment): PropertySources(MutablePropertySources) => 1:1
// PropertySources : PropertySource => 1:N 
@RunWith(SpringRunner.class)
@TestPropertySource(properties="user.id=10",locations="classpath:test.properties")
@SpringBootTest(properties="user.id=11",classes=PropertySourceOrderTest.class)
@PropertySource(value="classpath:test2.properties") // need to add on @Configuration classes
@Configuration
public class PropertySourceOrderTest {

	@Value("${user.id:1}")
	private Long userId;
	
	@Test
	public void userIdTest(){
		System.out.println("userId:"+userId);
		Assert.assertEquals(new Long(13),userId);
	}
	
	@Autowired
	private ConfigurableEnvironment environment;
	
	@Test
	public void propertySourcesTest(){
		MutablePropertySources propertySources=environment.getPropertySources();
		propertySources.forEach(item->{
			System.out.printf("[%s] : %s\n", item.getName(), item);
		});
	}
	/*
	[configurationProperties] : ConfigurationPropertySourcesPropertySource {name='configurationProperties'}
	[Inlined Test Properties] : MapPropertySource {name='Inlined Test Properties'}
	[class path resource [test.properties]] : ResourcePropertySource {name='class path resource [test.properties]'}
	[systemProperties] : MapPropertySource {name='systemProperties'}
	[systemEnvironment] : OriginAwareSystemEnvironmentPropertySource {name='systemEnvironment'}
	[random] : RandomValuePropertySource {name='random'}
	[applicationConfig: [classpath:/application.properties]] : OriginTrackedMapPropertySource {name='applicationConfig: [classpath:/application.properties]'}
	[class path resource [test2.properties]] : ResourcePropertySource {name='class path resource [test2.properties]'}
	*/
}
