package com.itheima.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.Sigar;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class ShowDiskDirController extends HttpServlet {
	
	private Sigar sigar = new Sigar();
	
	@RequestMapping("/ShowDiskDir")
	public void ShowDiskDir(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			System.out.println("ShowDiskDirController-doGet");
			List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
			
			//获得指定盘符
			String id = request.getParameter("id");
			if(id == null){
				//显示所有盘符
				FileSystem[] fileSystemArray = sigar.getFileSystemList();
				for (int i = 0; i < fileSystemArray.length; i++) {
					FileSystem fs = fileSystemArray[i];
					
					Map<String, Object> currentDir = new HashMap<String, Object>();
					dataList.add(currentDir);
					
					currentDir.put("id", fs.getDirName());
					currentDir.put("text", fs.getDevName());
					currentDir.put("state","closed");
					
				}
			} else {
				//指定盘符
				File dirFile = new File(id);
				if(dirFile.isDirectory()){
					File[] allSubFile = dirFile.listFiles();
					for (File subFile : allSubFile) {
						if(subFile.isDirectory() && !subFile.isHidden()){
							Map<String, Object> currentDir = new HashMap<String, Object>();
							dataList.add(currentDir);
							
							currentDir.put("id", subFile.getAbsolutePath());
							currentDir.put("text", subFile.getName());
							currentDir.put("state","closed");
						}
					}
				}
			}
			
			
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().print(JSONArray.fromObject(dataList).toString());
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	@RequestMapping("/diskDir")
	public void diskDir(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ShowDiskDirController-doPost");
		ShowDiskDir(request,response);
		
	}

}
