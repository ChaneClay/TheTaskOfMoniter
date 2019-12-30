package com.itheima.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.json.JSONArray;


@Controller
public class ShowCPUDataController{
	 @RequestMapping("/cpu")
	 public String cpu(HttpServletRequest request, HttpServletResponse response, Model model) {
	  System.out.println("ShowCPUDataController-cpu");
	  return "cpu";
 }
 @RequestMapping("/ShowCPUData")
 public void showCPUData(HttpServletRequest request, HttpServletResponse response, Model model) 
   throws ServletException, IOException {
	  System.out.println("ShowCPUDataController-showCPUData");
	  System.out.println(request==null);
	  response.setContentType("application/json;charset=UTF-8");
	
	  
	  @SuppressWarnings("unchecked")
	  List<Map<String, Object>> cpuData = (List<Map<String, Object>>) request.getServletContext().getAttribute("cpuData");
	  
	  String jsonData = JSONArray.fromObject(cpuData).toString();
	  //jsonData = "[{\"data\":[[0,\"84.3\"],[1,\"21.9\"],[2,\"3.1\"],[3,\"28.1\"],[4,\"15.5\"],[5,\"20.3\"],[6,\"3.1\"],[7,\"14.1\"],[8,\"43.7\"],[9,\"14.1\"]],\"label\":\"第1块CPU\"},{\"data\":[[0,\"81.3\"],[1,\"23.4\"],[2,\"4.7\"],[3,\"15.6\"],[4,\"17.2\"],[5,\"15.6\"],[6,\"4.7\"],[7,\"9.3\"],[8,\"42.2\"],[9,\"15.6\"]],\"label\":\"第2块CPU\"},{\"data\":[[0,\"93.7\"],[1,\"34.4\"],[2,\"6.2\"],[3,\"21.8\"],[4,\"9.4\"],[5,\"15.6\"],[6,\"6.3\"],[7,\"15.6\"],[8,\"31.2\"],[9,\"20.3\"]],\"label\":\"第3块CPU\"},{\"data\":[[0,\"81.2\"],[1,\"25.0\"],[2,\"1.6\"],[3,\"26.5\"],[4,\"9.3\"],[5,\"12.5\"],[6,\"17.2\"],[7,\"12.5\"],[8,\"53.2\"],[9,\"37.4\"]],\"label\":\"第4块CPU\"}]\r\n" + 
	  //		"";
	  response.getWriter().print(jsonData);
  
	  System.out.println(jsonData);
 }

}