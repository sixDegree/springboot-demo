package com.cj.demo1;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AsyncTest {

	private long startTime;
	
    public void load(String source,int seconds) {
        try {
        	long startTime = System.currentTimeMillis();
            long milliseconds = TimeUnit.SECONDS.toMillis(seconds);
			Thread.sleep(milliseconds);
			long costTime = System.currentTimeMillis() - startTime;
		    System.out.printf("[Thread : %s] %s cost :  %d ms \n",Thread.currentThread().getName(), source, costTime);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
    }
    
    @Before
    public void before() {
    	startTime = System.currentTimeMillis();
    }
    @After
    public void after() {
    	long costTime = System.currentTimeMillis() - startTime;
	    System.out.println("total cost:" + costTime + " ms");
    }
    
    @Test
    public void serialLoadTest(){ // 串行计算
		load("loadConfigurations",1);
		load("loadUsers",2);
		load("loadOrders",3);
    } // total cost: 1s + 2s  + 3s  = 6s

    
    @Test
    public void parallelLoadTest() {	// 并行计算
        ExecutorService executorService = Executors.newFixedThreadPool(3); // 创建线程池
        CompletionService<String> completionService = new ExecutorCompletionService<String>(executorService);
        completionService.submit(()->{load("loadConfigurations",1);}, null);      //  耗时 >= 1s
        completionService.submit(()->{load("loadUsers",2);}, null);               //  耗时 >= 2s
        completionService.submit(()->{load("loadOrders",3);}, null);              //  耗时 >= 3s
        int count = 0;
        while (count < 3) { // 等待三个任务完成
            if (completionService.poll() != null) {
                count++;
            }
        }
        executorService.shutdown();
    }  // 总耗时 max(1s, 2s, 3s)  >= 3s
    
    @Test
    public void callbackTest() throws IOException {
    	 JFrame jFrame = new JFrame("GUI 示例");
         jFrame.setBounds(500, 300, 400, 300);
         LayoutManager layoutManager = new BorderLayout(400, 300);
         jFrame.setLayout(layoutManager);
         jFrame.addMouseListener(new MouseAdapter() { // callback 1
             @Override
             public void mouseClicked(MouseEvent e) {
                 System.out.printf("[线程 : %s] 鼠标点击，坐标(X : %d, Y : %d)\n",
                		 Thread.currentThread().getName(), e.getX(), e.getY());
             }
         });
         jFrame.addWindowListener(new WindowAdapter() {  // callback 2
             @Override
             public void windowClosing(WindowEvent e) {
                 System.out.printf("[线程 : %s] 清除 jFrame... \n", Thread.currentThread().getName());
                 jFrame.dispose(); // 清除 jFrame
             }

             @Override
             public void windowClosed(WindowEvent e) {
                 System.out.printf("[线程 : %s] 退出程序... \n", Thread.currentThread().getName());
                 System.exit(0); // 退出程序
             }
         });
         System.out.println("当前线程：" + Thread.currentThread().getName());
         jFrame.setVisible(true);
         
         System.in.read();
    }
    
    @Test
    public void chainLoadTest() {
    	// main -> submit -> ...
        // sub-thread : F1 -> F2 -> F3
        CompletableFuture
            .runAsync(()->{load("loadConfigurations",1);})
            .thenRun(()->{load("loadUsers",2);})
            .thenRun(()->{load("loadOrders",3);})
            .whenComplete((result, throwable) -> { // 完成时回调
                System.out.println("[Thread ：" + Thread.currentThread().getName() + "] Complete");
            })
            .exceptionally(throwable->{
                System.out.println("[Thread ：" + Thread.currentThread().getName() + "] Exception");
                return null;
            })
            .join(); // 等待完成
    }
    
    @Test
    public void futureBlockingLoadTest() {
	   ExecutorService executorService = Executors.newFixedThreadPool(3); // 创建线程池
       runCompletely(executorService.submit(()->{load("loadConfigurations",1);}));
       runCompletely(executorService.submit(()->{load("loadUsers",2);}));
       runCompletely(executorService.submit(()->{load("loadConfigurations",3);}));
       executorService.shutdown();
    }
    private void runCompletely(Future<?> future) {
        try {
            future.get();
        } catch (Exception e) {
        }
    }
    
   
}
