package com.hongwei.futures.web.action.app_trade_basic;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.web.action.BaseAction;
import com.hongwei.futures.web.modulesforapp.basic.AppTradeBasicService;

@ParentPackage("api_package")
public class AppTradeBasicAction extends BaseAction {
	private static final long serialVersionUID = 3610138740651820566L;

	@Autowired
	private AppTradeBasicService appTradeBasicService;

	/**
	 * 获取所有的产品列表
	 */
	@Action("getProductList")
	public String getProductList() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String version = getHttpServletRequest().getParameter("version");
			String userId = getHttpServletRequest().getParameter("user_id");
			String objStr = appTradeBasicService.getProductList(sign, userId, version);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取书籍列表
	 */
	@Action("getArticleList")
	public String getArticleList() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String version = getHttpServletRequest().getParameter("version");
			String currentPage = getHttpServletRequest().getParameter("current_page");
			String pageSize = getHttpServletRequest().getParameter("page_size");
			String articleId = getHttpServletRequest().getParameter("article_id");
			String objStr = appTradeBasicService.getArticleList(articleId, currentPage, pageSize, sign, version);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
