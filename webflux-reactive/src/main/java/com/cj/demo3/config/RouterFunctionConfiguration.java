package com.cj.demo3.config;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.cj.demo3.domain.User;
import com.cj.demo3.repository.UserRepository;

import reactor.core.publisher.Flux;

@Configuration
public class RouterFunctionConfiguration {

    /**
     * Servlet
     * 请求接口： ServletRequest/HttpServletRequest
     * 响应接口： ServletResponse/HttpServletResponse
     *
     * Spring 5.0 重新定义了服务请求接口和响应接口
     * 请求接口：ServerRequest
     * 响应接口：ServerResponse
     * 既可支持Servlet规范，又可支持自定义，例如：Netty(Web Server)
     *
     * 本例：
     * 定义一个Get请求，返回所有Users (URI: /users)
     * Flux 是0～N个对象集合
     * Mono 是0～1个对象集合
     * Reactive中的 Flux/Mono 是异步处理（非阻塞），集合对象基本上是同步处理（阻塞）
     * Flux/Mono 都是 Publisher
     */
    @Bean
    //@Autowired
    public RouterFunction<ServerResponse> listUsers(UserRepository userRepository){
        return RouterFunctions.route(RequestPredicates.GET("/users")
                ,request -> {
                    Collection<User> users = userRepository.list();
                    Flux<User> userFlux = Flux.fromIterable(users);
                    return ServerResponse.ok().body(userFlux,User.class);
                });
    }
}
