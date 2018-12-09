package com.cj.application;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DemoSpringApplicationEvent {
	 public static void main(String[] args) {
        // 创建上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 注册应用事件监听器
        context.addApplicationListener(event -> {
        	if(event instanceof PayloadApplicationEvent){
        		PayloadApplicationEvent payloadEvent=(PayloadApplicationEvent)event;
        		System.out.println("监听到Payload事件: " + payloadEvent.getPayload());
        	}else
        		System.out.println("监听到事件: " + event.getClass().getSimpleName()+"[source="+event.getSource()+"]");
        });

        // 启动上下文
        context.refresh();
        // 发送事件
        context.publishEvent("HelloWorld");
        context.publishEvent(new ApplicationEvent("Outside") {});

        // 关闭上下文
        context.close();
    }
}
