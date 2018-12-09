package com.cj.hello.util;

public class Response<T> {
	
	private boolean success;
	private String msg;
	private T data;
	
	public Response() {
		super();
	}

	public Response(boolean success, String msg, T data) {
		super();
		this.success = success;
		this.msg = msg;
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
}
