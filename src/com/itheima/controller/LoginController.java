package com.itheima.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class LoginController {
	@RequestMapping("/login")
    public String login(Integer num, String password, Model model, HttpSession session, HttpServletRequest request ){

//       Student student = studentService.findUser(num, password);
//        if (student != null){
//            session.setAttribute("USER_SESSION", student);
//            request.setAttribute("success", "欢迎您！ "+ student.getUsername());
//            return "index";
//        }
		System.out.println("hello world");
        request.setAttribute("error", "账号或密码错误");
        return "login";
    }
}
