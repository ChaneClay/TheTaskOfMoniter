package com.itheima.controller;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoteDesktopController extends HttpServlet {
	
	private Robot robot;
	
	@Override
	public void init() throws ServletException {
		try {
			System.out.println("RemoteDesktopServlet-init");
			robot = new Robot();
		} catch (AWTException e) {
			throw new RuntimeException("切屏程序初始化异常", e);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("refresh", "1");
		System.out.println("RemoteDesktopServlet-doGet");
		BufferedImage bi = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		ImageIO.write(bi, "jpeg", response.getOutputStream());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("RemoteDesktopServlet-doPost");
		this.doGet(request, response);
	}

}
