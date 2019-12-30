package com.itheima.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.json.JSONArray;
/*
 * 1.事实上model数据，最终spring也是写到HttpServletRequest属性中，只是用model更符合mvc设计,减少各层间耦合。
 */

/*
 * 1.由ServletContextListener监控
 * 2.将cpuData数据转换成json格式返回给浏览器
 */
@Controller
public class ShowCPUDataController {
 @RequestMapping("/cpu")
 public String cpu(HttpServletRequest request, HttpServletResponse response, Model model) {
  System.out.println("ShowCPUDataController-cpu");
  
  return "cpu";
 }
 @RequestMapping("/ShowCPUDataServlet")
 public void showCPUData(HttpServletRequest request, HttpServletResponse response, Model model) 
   throws ServletException, IOException {
	 System.out.println("ShowCPUDataController-showCPUData");
  response.setContentType("application/json;charset=UTF-8");
  //request同样也可以获取服务器容器对象
  @SuppressWarnings("unchecked")
  List<Map<String, Object>> cpuData = (List<Map<String, Object>>) request.getServletContext().getAttribute("cpuData");
  
  String jsonData = JSONArray.fromObject(cpuData).toString();
  response.getWriter().print(jsonData);
  

//  System.out.println(jsonData);
 }

}