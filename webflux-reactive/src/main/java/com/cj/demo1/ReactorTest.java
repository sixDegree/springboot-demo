package com.cj.demo1;

import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class ReactorTest {
	
    private static void println(Object object) {
        String threadName = Thread.currentThread().getName();
        System.out.println("[Thread:" + threadName + "] " + object);
    }
    
	@Test
	public void fluxSyncFunctinalProgramingTest() {
		println("Run fluxSyncFunctinalProgramingTest ...");
        Flux.just("A", "B", "C") 			// Sequence: A -> B -> C
            .map(value -> "+" + value) 		// "A" -> "+A" 
            .subscribe(
            		ReactorTest::println, 	// 数据消费 = onNext(T)
            		ReactorTest::println, 	// 异常处理 = onError(Throwable)
                    () -> { 				// 完成回调 = onComplete()
                        println("Complete!");
                    },
                    subscription -> { 		// 背压操作 onSubscribe(Subscription)
                        subscription.request(Integer.MAX_VALUE); // 请求的元素数量
                        subscription.cancel(); 	// 取消上游传输数据到下游
                    }
            );
        /*
         Summary:
         subscription.request(0);	=> Exception (n must >0)
         subscription.request(2);	=> main: +A -> +B
         subscription.request(5);	=> main: +A -> +B -> +C -> Complete!
         subscription.request(2); subscription.cancel(); => main: +A -> +B
         subscription.request(5); subscription.cancel(); => main: +A -> +B -> +C -> Complete!
         subscription.cancel(); ... => none
         */
	}
	
	@Test
	public void fluxAsyncFunctionalProgramingTest() throws InterruptedException {
		println("Run fluxAsyncFunctionalProgramingTest ...");
        Flux.just("A", "B", "C")
        	.publishOn(Schedulers.elastic()) 	// 线程池切换
            .map(value -> "+" + value)
            .subscribe(
            		ReactorTest::println,
            		ReactorTest::println,
                    () -> {
                        println("Complete!");
                    },
                    subscription -> {
                        subscription.request(5);
//                        subscription.cancel();
                    }
            );
        /*
         Summary:
         subscription.request(2); => elastic-2: +A -> +B
         subscription.request(5); => elastic-2: +A -> +B -> +C -> Complete!
         subscription.request(2);subscription.cancel(); => none
        */
        
	}
	
	@Test
	public void fluxResponseProgramingTest() {
		println("Run fluxResponseProgramingTest ...");
		Flux.just("A", "B", "C")
			//.publishOn(Schedulers.elastic()) 
			.map(value -> "+" + value)
			.subscribe(new Subscriber<String>() {
				private Subscription subscription;
				private int count = 0;
				@Override
				public void onSubscribe(Subscription s) {
					subscription = s;
					subscription.request(1);
				}
				@Override
				public void onNext(String s) {
					if (count == 2) {
						throw new RuntimeException("故意抛异常！");
					}
					println(s);
					count++;
					subscription.request(1);
				}
				@Override
				public void onError(Throwable t) {
					println(t.getMessage());
				}
				@Override
				public void onComplete() {
					println("Complete");
				}
			});
		
		/*
		 Summary:
		 1. sync => main: +A -> +B -> Exception
		 2. async: .publishOn(Schedulers.elastic()) => main: +A -> +B 
		 */
	}

}
