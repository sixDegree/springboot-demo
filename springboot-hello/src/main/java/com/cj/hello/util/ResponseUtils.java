package com.cj.hello.util;

public class ResponseUtils {
	
	public static <T> Response<T> result(boolean success,String msg,T data) {
		return new Response<T>(success,msg,data);
	}
	
	public static <T> Response<T> result(boolean success,T data){
		return new Response<T>(success,null,data);
	}
	
	public static <T> Response<T> success(T data) {
		return new Response<T>(true,null,data);
	}
	
	public static <T> Response<T> fail(String msg) {
		return new Response<T>(false,msg,null);
	}
}
