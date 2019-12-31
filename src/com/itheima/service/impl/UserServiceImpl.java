package com.itheima.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itheima.po.User;
import com.itheima.service.UserService;
import com.itheima.dao.*;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
    private UserDao UserDao;
	
	
	@Override
	public User findUser(String username, String password) {
		User user = UserDao.findUser(username, password);
	    return user;
		
	}


	@Override
	public User createUser(String username, String password) {
		User user = UserDao.createUser(username, password);
        System.out.println("user is: "+ user);
        return user;
    }




	

}
