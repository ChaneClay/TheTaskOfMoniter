package com.itheima.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

public class ShowCPUDataServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("application/json;charset=UTF-8");
		
		List<Map<String, Object>> cpuData = (List<Map<String, Object>>) this.getServletContext().getAttribute("cpuData");
		
		String jsonData = JSONArray.fromObject(cpuData).toString();
		
		response.getWriter().print(jsonData);
		
//		System.out.println(jsonData);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
	

}
