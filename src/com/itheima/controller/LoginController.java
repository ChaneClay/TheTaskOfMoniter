package com.itheima.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itheima.po.User;
import com.itheima.service.UserService;
@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
    public String login(String username, String password, Model model, HttpSession session, HttpServletRequest request ){

		System.out.println("username: "+username);
		System.out.println("password: "+password);
		User user = userService.findUser(username, password);
        if (user != null){
            session.setAttribute("USER_SESSION", user);
            //request.setAttribute("success", "欢迎您！ "+ user.getUsername());
            return "home";
        }
        request.setAttribute("msg", "账号或密码错误");
        return "login";
    }
	
	@RequestMapping("/toRegister")
	public String toRegister() {
		System.out.println("toRegister");
		return "register";
	}
	
	@RequestMapping("/register")
	public String register(String username, String password, Model model, HttpSession session, HttpServletRequest request) {
		User user = userService.findUser(username, password);
		if(user == null) {
			
			System.out.println(userService.createUser(username, password));;
			
			request.setAttribute("msg", "注册成功！");
			return "login";
		}
		request.setAttribute("msg", "用户已存在！");
		return "register";
	}
	
}
