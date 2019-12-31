package com.itheima.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UploadController extends HttpServlet {

	@RequestMapping("Upload")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			System.out.println("UploadServlet-doGet");
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			List<FileItem> allFileItem = fileUpload.parseRequest(request);
			// 隐藏字段，父目录
			String id = allFileItem.get(0).getString("UTF-8");
			// 上传文件
			FileItem fi = allFileItem.get(1);
			String fileName = fi.getName();
			fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
			
			FileUtils.copyInputStreamToFile(fi.getInputStream(), new File(id,fileName));
			
			//重定向到
			String url = request.getContextPath() + "/ShowAllFile?id=" + id;
			response.sendRedirect(url);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void doPost22(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("UploadServlet-doPost");
		
	}

}
