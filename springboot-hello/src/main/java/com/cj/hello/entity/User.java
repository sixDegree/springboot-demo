package com.cj.hello.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sys_user")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

	@Id
	private String id;
	private String name;
	private String pwd;
	private String email;
	
	public User() {
		super();
	}
	
	public User(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
