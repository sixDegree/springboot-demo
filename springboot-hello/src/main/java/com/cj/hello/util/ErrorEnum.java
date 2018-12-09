package com.cj.hello.util;

public enum ErrorEnum {
	
	SUCCESS(1,"success"),
	FAIL(-1,"fail"),
	UNKNOW(0,"unknow");
	
	private Integer code;
	private String msg;
	
	ErrorEnum(Integer code,String msg){
		this.code=code;
		this.msg=msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
