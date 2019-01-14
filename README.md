# springboot-demo

## Starter

see springboot-hello

- HelloWorld
- 热部署
- 单元测试
- 属性配置
- AOP
- 异常处理
- Security


## 自动装配

see auto-config

1. Spring装配技术：
	+ 模式注解装配：`@Component`,`@Repository`,`@Service`,`@Controller`,`@Configuration`
	+ Enable模块装配: `@Enable模块`
	+ 条件装配:`@Profile`,`@Conditional`
	+ 工厂加载机制: `SpringFactoriesLoader` + `META-INF/spring.factories`
	
2. SpringBoot自动装配：
	+ 依赖Spring装配技术，基于约定大于配置的原则，实现自动装配
	+ 实现步骤：
		* 激活自动装配 `@EnableAutoConfiguration`
		* 实现装配 `XxxAutoConfiguration`
		* 配置 `META-INF/spring.factories`

## 外部化配置

see external-config

Refer [SpringBoot Doc](https://docs.spring.io/spring-boot/docs/2.0.2.RELEASE/reference/htmlsingle/#boot-features-external-config)


加载外部配置资源的内容到程序变量中用于动态控制：

1. 外部化配置资源（保存到`PropertySource`对象）
	- 常见的加载项顺序：
		+ `@TestPropertySource#properties`
		+ `@SpringBootTest#properties`
		+ `@TestPropertySource#location`
		+ cmd line args (eg:`-Duser.id=13`)
		+ system properties (eg:`-Duser.city.post_code=0731`),可通过`System.getProperties()`获取
		+ os env variables (eg:`USER_CITY_POST_CODE=001`)
		+ application property files/yaml variants (eg:`user.city.post-code=0571`)
			* `application-{profile}.properties`outside pkg jar
			* `application-{profile}.properties` packaged inside jar
			* `application.properties` outside pkg jar
			* `application.properties` packaged inside jar
		+ `@PropertySouce` on `@Configuration` class
		+ default properties
	- `PropertySource`: 带有名称的外部化配置属性源
		```java
		MutablePropertySources propertySources=environment.getPropertySources();
		propertySources.forEach(item->{
			System.out.printf("[%s] : %s\n", item.getName(), item);
		});
		```
2. 绑定到变量
	+ XML方式：`<bean>` -> `<property name="xxx" value="${...}" />`
	+ Annotation方式: `@Value` ,`@ConfigurationProperties`



## Servlet

see webmvc-servlet,webmvc-spring,webmvc-springboot

1. Servlet 组件注册

| 组件 | 传统方式 | 注解方式 | 编程方式（利用Servlet SPI：`ServletContainerInitializer#onStartup(classes,ctx)`） |
|:-|:-|:-|:-|
| Servlet | web.xml: `<servlet>`,`<servlet-mapping>` | `@WebServlet` | ServletContext#addServlet | 
| Filter | web.xml: `<filter>`,`<filter-mapping>` | `@WebFilter` | ServletContext#addFilter |
| Listener | web.xml: `<listener>`,`<context-param>` | `@WebListener` | ServletContext#addListener |
	
2. Servlet容器

| Servlet容器 | 启动 | 初始化器
|:-|:-|:-|
| 独立Servlet容器 | 独立Servlet容器 -> 启动 App | Servlet SPI(3.0): `ServletContainerInitializer`; Spring 适配：`SpringServletContainerInitializer`+ `@HandlesTypes(WebApplicationInitializer.class)` |
| 嵌入式Servlet容器 | App －> 启动嵌入式Servlet容器 | SpringBoot: `ServletContextInitializer`|

3. Servlet -> Spring -> SpringBoot:

| Jar | XML方式 | 注解方式 | 编程方式 |
|:-|:-|:-|:-|
| Servlet | web.xml | @WebServlet等 | interface `ServletContainerInitializer` |
| Spring(独立Servlet容器) | &radic; | &radic; | `SpringServletContainerInitializer`(implements ServletContainerInitializer) + `@HandlesTypes(WebApplicationInitializer.class)` |
| SpringBoot(独立Servlet容器) | &times; | &radic; | `SpringBootServletInitializer`(implements WebApplicationInitializer) <br/> note: 支持使用`ServletContextInitializer`注入的Servlet组件 <br/> eg: <br/> @Bean ServletContextInitializer <br/> @Bean ServletRegisterBean,FilterRegisterBean,ServletListenerRegisterBean (implements ServletContextInitializer)|
| SpringBoot(嵌入式Servlet容器) | &times; | limited: need to add `@ServletComponentScan` | only can use `ServletContextInitializer` <br/> note: 不支持使用`SpringBootServletInitializer`(implements WebApplicationInitializer) |

4. Servlet异步支持（3.0+）: 
- HttpServlet
	+ request.isAsyncSupported()
	+ AsyncContext ctx=req.startAsync();
	+ ctx.start(Runnable)
- Java
	+ Callable
	+ CompletionStage
- Spring
	+ DeferredResult （spring-web)
	

### 示例

Spring＋独立Servlet容器：
- 使用xml/annotation配置WebMvc相关组件
- 使用`web.xml` 或者自定义的`WebApplicationInitializer`实现类注册`DispatcherServlet`
- 自定义一个Servlet，并通过`@WebServlet`注册
- 使用`独立Servlet`启动运行

SpringBoot＋独立Servlet容器：
- 使用XML/Annotation(`@Configuration`) 配置WebMvc相关组件
- 使用`@EnableAutoConfiguration`自动装配DispatcherServlet
- 自定义一个Servlet，并通过`@WebServlet`注册
- 使用`SpringBootServletInitializer`配置主引导类 (SpringBootServletInitializer implements WebApplicationInitializer)
- 使用`独立Servlet`启动运

SpringBoot＋嵌入式Servlet容器：
- 使用xml/annotation方式配置WebMvc组件
- 使用`@EnableAutoConfiguration`自动部署DispatcherServlet
- 自定义Servlet，通过`@WebServlet`+`@ServletComponentScan`，或`RegistrationBean`,或`ServletContextInitializer#onStartup->servletContext.addServlet`方式注册
- 使用嵌入式容器（直接运行main方法）/独立容器启动进行验证

Servlet异步支持: 
- 自定义Servlet支持异步或使用DeferredResult/Callable/CompletionStage


## WebMvc

see webmvc-view,webmvc-rest

- 核心组件(sprinb-webmvc)
	+ DispatcherServlet
	+ HandlerMapping
	+ HandlerAdapter
		* HandlerMethodArgumentResolver
		* HandlerMethodReturnValueHandler
	+ HandlerExceptionResolver
	+ ViewResolver
		* ContentNegotiatingViewResolver
		* InternalResourceViewResolver
		* ...
	+ View
		* RedirectView
		* JstlView
		* ...
- 注解
	+ @Controller,@RestController,@ControllerAdvice
	+ @RequestMapping（@GetMapping,@PostMapping,…)
	+ @RequestBody,@RequestParm,@RequestHeader,@PathVariable,@CookieValue
	+ @RestController,@RestControllerAdvice,@ResponseBody(for Rest)
	+ @ModelAttribute,@Valid,@Validated
- 配置
	+ WebMvcConfigurer
	+ ContentNegotiationConfigurer

### 示例：

- 模版视图：JSP,Thymeleaf,...
	- ViewResolver#resolveViewName
	- View#render
	
- REST
	+ @RestController(@Controller+@RequestBody),@RequestMapping(@XxxMapping),@ResponseBody
	+ RequestResponseBodyMethodProcessor,ContentNegotiationManager,HttpMessageConverter
	+ MediaType
		* ContentNegotiationManager#resolveMediaTypes
		* @RequestMapping#consumes
		* @RequestMapping#produces
		
- 内容协商（多视图处理）
	+ 解析器 ContentNegotiatingViewResolver (resolveViewName)
	+ 工厂 ContentNegotiationManagerFactoryBean（构建ContentNegotiationManager）
	+ 管理器 ContentNegotiationManager（管理ContentNegotiationStrategy）
	+ 策略 ContentNegotiationStrategy (resolveMediaTypes)
	+ 配置 ContentNegotiationConfigurer


## WebFlux

see webflux-reactive

- Java 并发模型 (阻塞／非阻塞，同步／异步）
- Reactive Programming （编程模型）
	+ 观察者模式(observer pattern)的延伸
	+ 处理流式数据（streams: sequenced data/events）
	+ 非阻塞下的同步／异步执行 （no-blocking)
	+ 推拉相结合（push-based and pull-based）
	+ 响应数据传播时的变化 (responsive)
	+ 结合背压（Backpressure）技术处理数据生产与消费的平衡问题
- Reactor框架
	+ reactive-streams: 
		* Publisher,Subscriber,Subscription,Processor
		* backpressure handle
	+ Mono/Flux(implements Publisher),Scheduler(Schedulers.single/elastic/parallel())
- Spring Web
	+ HttpHandler#handle(ServerHttpRequest,ServerHttpResponse) -> Mono
	+ WebHandler#handle(ServerWebExchange) -> Mono
- Spring Web Flux
	+ 编程
		* 注解式驱动 Annotated Controllers: 同Spring WebMvc使用的注解
		* 函数式端点 Functional Endpoints : 使用RouterFunction (spring-webflux)
	+ 核心组件
		* DispatcherHandler
		* HandlerMapping
		* HandlerAdapter
			+ HandlerMethodArgumentResolver
			+ HandlerResultHandler
		* HandlerResult#exceptionHandler
		* ViewResolver
		* View
	+ 配置
		* WebFluxConfigurer
		* WebFluxConfigurationSupport
		

### 示例

Java
- Callback
- Future

Reactor
- Mono
- Flux

WebFlux
- Annotation
- Functional


## Spring Data JPA

see springboot-jpa

### Entity

1. Table (`@Table`): 
	- pe_employee : id,name,remark,department_id
	- pe_department : id,name,remark
2. Entity (`@Entity`):
	- Employee : id,name,remark,Department department
	- Department : id,name,remark,List<Employee> employees
3. Relationship (`@ManyToOne`,`@OneToMany`): 
	- Employee -> Department : ManyToOne
	- Employee <- Department : OneToMany


### Code

1. pom.xml
	```xml
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter</artifactId>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-data-jpa</artifactId>
	</dependency>
	<dependency>
		<groupId>mysql</groupId>
		<artifactId>mysql-connector-java</artifactId>
		<scope>runtime</scope>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-test</artifactId>
		<scope>test</scope>
	</dependency>
	<dependency>
		<groupId>commons-beanutils</groupId>
		<artifactId>commons-beanutils</artifactId>
		<version>1.8.3</version>
	</dependency>
	```

2. resources/application.yml:
	```yml
	spring:
	  datasource:
	    url: jdbc:mysql://localhost:3306/demo1?characterEncoding=utf8&useSSL=false
	    username: cj
	    password: 123
	    driver-class-name: com.mysql.jdbc.Driver
	  jpa:
	    database: mysql
	    show-sql: true
	    open-in-view: false
	    properties:
	      hibernate.format_sql: true
	    hibernate:
	      naming:
	        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
	  jackson:
	    serialization:
	      FAIL_ON_EMPTY_BEANS: false  
	    default-property-inclusion: NON_EMPTY
	```

3. entity:
	+ Employee
	+ Department

4. repository:
	+ EmployeeRepository
	+ EmployeeRepositoryCustom
	+ EmployeeRepositoryImpl
	+ DepartmentRepository

5. service:
	+ EmployeeService
	+ DepartmentService

6. util:
	+ FetchEntity
	+ SearchCondition
	+ SpecificationBuildUtils

7. test(service):
	+ EmployeeServiceTest
	+ DeploymentServiceTest

