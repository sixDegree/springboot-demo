package com.cj.demo.controller;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class HelloAsyncController {
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello!";
	}
	
	@GetMapping("/deffered")
	public DeferredResult<String> helloDeffered(){
		DeferredResult<String> result=new DeferredResult<>(50L);
		result.setResult("[DefferedResult] Hello world!");
		println("[DefferedResult] Hello world!");
		
		result.onCompletion(()->{
			println("[DefferedResult] execute complete");
		});
		result.onTimeout(()->{
			println("[DefferedResult] execute timeout");
		});
		return result;
	}
	
	@GetMapping("/callable")
    public Callable<String> helloCallable() {
        final long startTime = System.currentTimeMillis();
        println("[Callable] Hello world!");

        return () -> {
            long costTime = System.currentTimeMillis() - startTime;
            println("[Callable] cost " + costTime + " ms.");
            return "[Callable] Hello world!";
        };
    } 
	
	@GetMapping("/completionStage")
	public CompletionStage<String> helloCompletionStage() {
		final long startTime = System.currentTimeMillis();
		println("[CompletionStage] Hello world!");

		return CompletableFuture.supplyAsync(() -> {
			long costTime = System.currentTimeMillis() - startTime;
			println("[CompletionStage] cost " + costTime + " ms.");
			return "[CompletionStage] Hello world!";
		});
	}
	  
    private static void println(Object object) {
        String threadName = Thread.currentThread().getName();
        System.out.println("HelloAsyncController[" + threadName + "]: " + object);
    }
}
