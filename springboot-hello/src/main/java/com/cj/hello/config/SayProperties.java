package com.cj.hello.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "say")
public class SayProperties {
	private String prefix;
	private String target;
	private Integer times;
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer times) {
		this.times = times;
	}
	
	@Override
	public String toString() {
		return "SayProperties [prefix=" + prefix + ", target=" + target + ", times=" + times + "]";
	}
}
