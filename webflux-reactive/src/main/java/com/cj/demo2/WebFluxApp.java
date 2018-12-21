package com.cj.demo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
public class WebFluxApp {
	public static void main(String[] args) {
		SpringApplication.run(WebFluxApp.class,args);
	}
	
	private static void println(String message) {
        System.out.println("[" + Thread.currentThread().getName() + "] : " + message);
    }
	 
	@GetMapping("/mvc")
    public String mvc() {
        println("mvc");
        return "MVC";
    }

    @GetMapping("/mono")
    public Mono<String> mono() {
        println("mono");
        return Mono.just("Mono");
    }
    
    @Bean
    public RouterFunction<ServerResponse> hello(){
    	// curl -i localhost:8080/hello
    	// curl -i -H "Content-Type:application/json" localhost:8080/hello2
    	// curl -i localhost:8080/hello3
    	return RouterFunctions
    			.route(RequestPredicates.GET("/hello")
		    			,serverRequest->{
		    				return ServerResponse.ok().body(Mono.just("Hello World!"),String.class);
		    			})
    			.andRoute(RequestPredicates.GET("/hello2").and(RequestPredicates.contentType(MediaType.APPLICATION_JSON))
    					,this::hello2Handler)
    			.andRoute(RequestPredicates.GET("/hello3"), this::hello3Handler);
    }
    
    public Mono<ServerResponse> hello2Handler(ServerRequest request){
    	Mono<String> helloMono=Mono.just("Hello World 2");
		return ServerResponse.ok().body(helloMono,String.class);
    }
    
    public Mono<ServerResponse> hello3Handler(ServerRequest request){
    	Flux<String> helloFlux=Flux.just("A","B","C");
    	return ServerResponse.ok().body(helloFlux,String.class);
    }
    
    
}
