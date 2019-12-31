package com.itheima.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;



@Controller
public class FileDownloadController {

	 @RequestMapping("/FileDownload")
	 public String fileDownLoad(HttpServletRequest request, HttpServletResponse response)
	   throws ServletException, IOException {
	  
		  request.setCharacterEncoding("UTF-8");
		  
		  String id = request.getParameter("id");
		  id = java.net.URLDecoder.decode(id,"UTF-8");
		  
		  //第二个参数为下载的绝对路径
		  String filename = this.fileDownLoad(id, request.getSession().getServletContext().getRealPath("/WEB-INF/download"), response.getOutputStream());
		  response.setHeader("content-disposition", "attachment;filename=" + new String( filename.getBytes("gb2312"), "ISO8859-1" ));
		  //System.out.println("FileDownloadServlet filename = "+filename+file.getAbsolutePath());
		  return null;
	 }
	 
	 
	 public String fileDownLoad(String id, String parentDir, OutputStream servletOS) throws IOException {

			  id = new String(id.getBytes(), System.getProperty("file.encoding"));
			  File file = new File(id);
			  String filename = "";
			  if(file.isDirectory()){
			   //打包
			   filename = file.getName() + ".zip";
			   //第二个参数传了站点的绝对路径
			   file = compressDirToFile(file, parentDir);
			  } else {
			   filename = file.getName();
			  }	
			  //将文件名字串改为系统当前编码格式
			  filename = new String(filename.getBytes(),System.getProperty("file.encoding"));
			  FileUtils.copyFile(file, servletOS);
			  
			  System.out.println("FileDownloadServlet id = "+id);
			  System.out.println("FileDownloadServlet filename = "+filename+file.getAbsolutePath());
			  return filename;
		 }

		 /**
		  * 压缩目录
		  * @param dirPath 目录位置
		  * @param fileName 文件名（不含扩展名）
		  * @return
		  * @throws ArchiveException 
		  * @throws FileNotFoundException 
		  */
		 private File compressDirToFile(File dirFile, String parentDir) {
		  try {
			   String dirPath = dirFile.getCanonicalPath();
			   String fileName = com.itheima.utils.MyStringUtils.getMD5Value(dirPath);
			   File zipFile = new File(parentDir, fileName + ".zip");
			   if(zipFile.exists()){
			    return zipFile;
			   }
			  //父目录不存在
			   if(!zipFile.getParentFile().exists()){
			    zipFile.getParentFile().mkdirs();
			   }
			   ArchiveOutputStream zipOut = new ArchiveStreamFactory().createArchiveOutputStream(ArchiveStreamFactory.ZIP,new FileOutputStream(zipFile));
			   write(dirPath,dirPath, zipOut);
			   zipOut.close();
			   return zipFile;
			  } catch (Exception e) {
			   throw new RuntimeException(e);
			  }
		 }

		 private void write(String rootPath , String dirPath, ArchiveOutputStream zipOut) throws IOException {
		  //1 压缩文件封装对象
		  ArchiveEntry entry = null;
		  //2 需要压缩目录
		  File dir = new File(dirPath);
		  
		  //3 遍历所有的内容
		  for(File file : dir.listFiles()){
		   // 处理文件名。例如 /a/b/c/  或 /a/b/c/1.txt
		   String relativeName = file.getCanonicalPath().substring(rootPath.length());
		   if(file.isDirectory()){
		    relativeName = relativeName + File.separator;
		   }
		   entry = new ZipArchiveEntry(relativeName);
		   
		   //添加到zip文件中
		   zipOut.putArchiveEntry(entry);
		   
		 
		   if(file.isDirectory()){
		    zipOut.closeArchiveEntry();
		  
		    write(rootPath, file.getCanonicalPath() ,zipOut);
		    continue;
		   }
		   
		   IOUtils.copy(new FileInputStream(file), zipOut);
		   zipOut.closeArchiveEntry();
		   
		  }
		 }
	 
}






