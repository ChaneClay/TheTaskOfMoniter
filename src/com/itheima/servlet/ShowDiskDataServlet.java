package com.itheima.servlet;

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
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Sigar;

public class ShowDiskDataServlet extends HttpServlet {
	//1 
	private Sigar sigar = new Sigar();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			//[{name:'c盘',data=[{"label":"未使用","data":20},{}]},{}]
			List<Map<String, Object>> diskList = new ArrayList<Map<String,Object>>();
			
			//2 获得所有的硬盘
			FileSystem[] fileSystemArray = sigar.getFileSystemList();
			// 遍历
			for (FileSystem fileSystem : fileSystemArray) {
				//3.1 盘符的名称
				String devName = fileSystem.getDevName();
				
				//#当前硬盘 名称和数据
				Map<String, Object> currentDisk = new HashMap<String, Object>();
				currentDisk.put("name", devName);
				
				List<Map<String,Object>> currentData = new ArrayList<Map<String,Object>>();
				currentDisk.put("data", currentData);
				
				diskList.add(currentDisk);
				
				
				if(fileSystem.getType() == FileSystem.TYPE_LOCAL_DISK){  //2 表示 本地硬盘
					// 获得硬盘是否对象
					FileSystemUsage fileSystemUsage = sigar.getFileSystemUsage(devName);
					//3.2 总大小
					long total = fileSystemUsage.getTotal();
					//3.3 已经使用的
					long user = fileSystemUsage.getUsed();
					Map<String,Object> userMap = new HashMap<String, Object>();
					userMap.put("label", "已使用");
					userMap.put("data", user);
					currentData.add(userMap);
					//3.4 没有使用的
					long avail = fileSystemUsage.getAvail();
					Map<String,Object> availMap = new HashMap<String, Object>();
					availMap.put("label", "未使用");
					availMap.put("data", avail);
					currentData.add(availMap);
					
					
				}
			}
			
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().print(JSONArray.fromObject(diskList).toString());
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	public static void main(String[] args) {
		file();
	}
	
	private static void file()  {
		try {
			Sigar sigar = new Sigar();
			FileSystem fslist[] = sigar.getFileSystemList();
			for (int i = 0; i < fslist.length; i++) {
				System.out.println("分区的盘符名称" + i);
				FileSystem fs = fslist[i];
				// 分区的盘符名称
				System.out.println("盘符名称:    " + fs.getDevName());
				// 分区的盘符名称
				System.out.println("盘符路径:    " + fs.getDirName());
				System.out.println("盘符标志:    " + fs.getFlags());//
				// 文件系统类型，比如 FAT32、NTFS
				System.out.println("盘符类型:    " + fs.getSysTypeName());
				// 文件系统类型名，比如本地硬盘、光驱、网络文件系统等
				System.out.println("盘符类型名:    " + fs.getTypeName());
				// 文件系统类型
				System.out.println("盘符文件系统类型:    " + fs.getType());
				FileSystemUsage usage = null;
				usage = sigar.getFileSystemUsage(fs.getDirName());
				switch (fs.getType()) {
				case 0: // TYPE_UNKNOWN ：未知
					break;
				case 1: // TYPE_NONE
					break;
				case 2: // TYPE_LOCAL_DISK : 本地硬盘
					// 文件系统总大小
					System.out.println(fs.getDevName() + "总大小:    " + usage.getTotal() + "KB");
					// 文件系统剩余大小
					System.out.println(fs.getDevName() + "剩余大小:    " + usage.getFree() + "KB");
					// 文件系统可用大小
					System.out.println(fs.getDevName() + "可用大小:    " + usage.getAvail() + "KB");
					// 文件系统已经使用量
					System.out.println(fs.getDevName() + "已经使用量:    " + usage.getUsed() + "KB");
					double usePercent = usage.getUsePercent() * 100D;
					// 文件系统资源的利用率
					System.out.println(fs.getDevName() + "资源的利用率:    " + usePercent + "%");
					break;
				case 3:// TYPE_NETWORK ：网络
					break;
				case 4:// TYPE_RAM_DISK ：闪存
					break;
				case 5:// TYPE_CDROM ：光驱
					break;
				case 6:// TYPE_SWAP ：页面交换
					break;
				}
				System.out.println(fs.getDevName() + "读出：    " + usage.getDiskReads());
				System.out.println(fs.getDevName() + "写入：    " + usage.getDiskWrites());
			}
			return;
		} catch (Exception e) {
			System.out.println("@@@@@ " + e.getMessage() );
		}
	}

}
