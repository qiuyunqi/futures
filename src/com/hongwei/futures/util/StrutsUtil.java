package com.hongwei.futures.util;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

/**
 * struts2 工具类
 * @author 充满智慧的威哥
 *
 */
public class StrutsUtil {

	/**
	 * 
	 * 获得Session
	 */
	public static Map<String, Object> getSession() {
		return ActionContext.getContext().getSession();
	}

	/**
	 * 
	 * 获得ActionContext
	 */
	public static ActionContext getActionContext() {
		return ActionContext.getContext();
	}
	
	/**
	 * 获得HttpServletRequest
	 */
	public static HttpServletRequest getHttpServletRequest() {
		return ServletActionContext.getRequest();
	}
	
	/**
	 * 获得HttpServletResponse
	 */
	public static HttpServletResponse getHttpServletResponse() {
		return ServletActionContext.getResponse();
	}
	
	/**
	 * 获得HttpSession
	 */
	public static HttpSession getHttpSession() {
		return getHttpServletRequest().getSession();
	}
	
	/**
	 * 获得ServletContext
	 */
	public static ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}
	
}
