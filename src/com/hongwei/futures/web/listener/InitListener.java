package com.hongwei.futures.web.listener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hongwei.futures.model.SysPurview;
import com.hongwei.futures.service.SysPurviewService;
import com.hongwei.futures.util.Property;

public class InitListener implements ServletContextListener {

	// 装载权限列表的URL数据
	public void contextInitialized(ServletContextEvent sc) {
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sc.getServletContext());
		SysPurviewService sysPurviewService = (SysPurviewService) ac.getBean("sysPurviewServiceImpl");

		List<SysPurview> purviewList = sysPurviewService.findTopPrivilege();
		sc.getServletContext().setAttribute("topPrivilegeList", purviewList);
		System.out.println("------------> 已准备数据topPrivilegeList <------------");
		//
		// 准备数据：allPrivilegeUrls
		Collection<String> allPrivilegeUrls = sysPurviewService.getAllPrivilegeUrls();
		sc.getServletContext().setAttribute("allPrivilegeUrls", allPrivilegeUrls);
		System.out.println("------------> 已准备数据allPrivilegeUrls <------------");

		String src = "/keyword.txt";

		InputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null; // 用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
		try {
			String str = "";
			String str1 = "";
			fis = InitListener.class.getResourceAsStream(src);// FileInputStream
			// 从文件系统中的某个文件中获取字节
			isr = new InputStreamReader(fis, "UTF-8");// InputStreamReader 是字节流通向字符流的桥梁,
			br = new BufferedReader(isr);// 从字符输入流中读取文件中的内容,封装了一个new
			// 当读取的一行不为空时,把读到的str的值赋给str1
			while ((str = br.readLine()) != null) {
				str1 += str + ",";
			}
			sc.getServletContext().setAttribute("keys", str1.split(","));
			sc.getServletContext().setAttribute("HHR_PREFIX", Property.getProperty("HHR_PREFIX"));
			System.out.println("------------> 已准备数据关键词列表 <------------");
		} catch (FileNotFoundException e) {
			System.out.println("找不到指定文件");
		} catch (IOException e) {
			System.out.println("读取文件失败");
		} finally {
			try {
				br.close();
				isr.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void contextDestroyed(ServletContextEvent arg0) {

	}
}
