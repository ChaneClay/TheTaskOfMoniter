package com.itheima.dao;

import org.apache.ibatis.annotations.Param;

import com.itheima.po.User;

public interface UserDao {
	public User findUser(@Param("username")String username, @Param("password")String password);
	public User createUser(@Param("username")String username, @Param("password")String password);
	 
}
