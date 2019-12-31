package com.itheima.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itheima.po.*;
import com.itheima.utils.ImageUtils;

@Controller
public class ShowAllFileController extends HttpServlet {

	@RequestMapping("/login/ShowAllFile")
	public void ShowAllFile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ShowAllFileServlet-doGet");
		String id = request.getParameter("id");//此处容易出现中文乱码
		id = java.net.URLDecoder.decode(id,"UTF-8");
		System.out.println("id: "+id);
		File dir = new File(id);
		File allFile[] = dir.listFiles();
		List<FileBean> allFileBean = new ArrayList<FileBean>();
		for (File file : allFile) {
			FileBean fileBean = new FileBean(file.getAbsolutePath(), file.getName(), file.isFile());
			
			//文件后面追加，文件夹前面插入（前面是文件夹，后面是文件）
			if(file.isFile()){
				fileBean.setIcoName(ImageUtils.getExtension(file.getName(), true));
				allFileBean.add(fileBean);
			} else {
				fileBean.setIcoName(ImageUtils.getExtension(file.getName(), false));
				allFileBean.add(0, fileBean);
			}
		}
		
		response.setContentType("text/html;charset=UTF-8");
		request.setAttribute("allFileBean", allFileBean); //所有数据
		request.setAttribute("id", id);	//当前目录
		request.getRequestDispatcher("/WEB-INF/jsp/all_file.jsp").forward(request, response);

	}

	public void doPost22(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ShowAllFileServlet-doPost");
		this.doGet(request, response);
	}

}
