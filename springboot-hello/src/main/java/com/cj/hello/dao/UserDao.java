package com.cj.hello.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cj.hello.entity.User;

public interface UserDao extends JpaRepository<User,String> {

}
