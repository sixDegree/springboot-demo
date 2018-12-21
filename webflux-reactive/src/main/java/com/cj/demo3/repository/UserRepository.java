package com.cj.demo3.repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Repository;

import com.cj.demo3.domain.User;

@Repository
public class UserRepository {

    private final ConcurrentMap<Integer,User> users = new ConcurrentHashMap<Integer,User>();
    private final static AtomicInteger idGenerator = new AtomicInteger();

    public boolean save(User user){
        Integer id = idGenerator.incrementAndGet();
        user.setId(id);
        return users.put(id,user)==null;
    }

    public Collection<User> list(){
        return users.values();
    }
}
