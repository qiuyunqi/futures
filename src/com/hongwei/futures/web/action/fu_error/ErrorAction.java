package com.hongwei.futures.web.action.fu_error;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import com.hongwei.futures.web.action.BaseAction;


@ParentPackage("fu_common")
public class ErrorAction extends BaseAction{
	
	/**
	 * 手机端错误页面
	 * @return
	 * @throws Exception 
	 */
	@Action("errorPhone")
	public String errorPhone() throws Exception{
		return SUCCESS;
	}

	/**
	 * PC端错误页面
	 * @return
	 * @throws Exception 
	 */
	@Action("errorPC")
	public String errorPC() throws Exception{
		return SUCCESS;
	}
	

	/**
	 * 500错误页面
	 * @return
	 * @throws Exception 
	 */
	@Action("serverError")
	public String serverError() throws Exception{
		return SUCCESS;
	}
	
	/**
	 * 403错误页面
	 * @return
	 * @throws Exception 
	 */
	@Action("httpError")
	public String httpError() throws Exception{
		return SUCCESS;
	}
}
