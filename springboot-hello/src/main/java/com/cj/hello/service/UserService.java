package com.cj.hello.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cj.hello.dao.UserDao;
import com.cj.hello.entity.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public List<User> listAll(){
		return userDao.findAll();
	}
	
	public User getDetails(String id) {
		return userDao.getOne(id);
	}
}
