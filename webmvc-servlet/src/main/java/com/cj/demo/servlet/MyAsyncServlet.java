package com.cj.demo.servlet;

import static javax.servlet.http.HttpServletResponse.SC_SERVICE_UNAVAILABLE;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(asyncSupported=true,name="myAsyncServlet", urlPatterns="/asyncServlet")
public class MyAsyncServlet extends HttpServlet{
	
	 @Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		 if(req.isAsyncSupported()) {
			 AsyncContext ctx=req.startAsync();
			 ctx.setTimeout(500L);
			 ctx.addListener(new AsyncListener() {
				@Override
				public void onTimeout(AsyncEvent event) throws IOException {
//					HttpServletResponse servletResponse = (HttpServletResponse)event.getSuppliedResponse();
//	                servletResponse.setStatus(SC_SERVICE_UNAVAILABLE);
					println("[MyAsyncServlet] Timeout");
				}
				
				@Override
				public void onStartAsync(AsyncEvent event) throws IOException {
					println("[MyAsyncServlet] StartAsync");
				}
				
				@Override
				public void onError(AsyncEvent event) throws IOException {
					println("[MyAsyncServlet] Error");
				}
				
				@Override
				public void onComplete(AsyncEvent event) throws IOException {
					println("[MyAsyncServlet] Complete");
				}
			});
			
			println("[MyAsyncServlet] Hello world!");
			ServletResponse servletResponse=ctx.getResponse();
			servletResponse.setContentType("text/json;charset=UTF-8");
			PrintWriter writer=servletResponse.getWriter();
			writer.println("[MyAsyncServlet] Hello world!");
			writer.flush();
		 }
	}

	private static void println(Object object) {
        String threadName = Thread.currentThread().getName();
        System.out.println("MyAsyncServlet[" + threadName + "]: " + object);
    }
}
