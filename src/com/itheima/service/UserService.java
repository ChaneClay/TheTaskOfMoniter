package com.itheima.service;
import com.itheima.po.User;

public interface UserService {
	 
	User findUser(String username, String password);
	User createUser(String username, String password);

}




