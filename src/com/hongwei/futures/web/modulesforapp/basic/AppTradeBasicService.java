package com.hongwei.futures.web.modulesforapp.basic;

public interface AppTradeBasicService {

	/**
	 * 获取所有的产品列表
	 * @param sign			验证码
	 * @param userId		用户id
	 * @param version		版本号
	 */
	public String getProductList(String sign, String userId, String version);
	
	/**
	 * 根据当前页, 每页大小 查询出文章列表
	 * @param currentPage
	 * @param pageSize
	 * @param sign
	 * @param version
	 * @return
	 */
	public String getArticleList(String articleId, String currentPage, String pageSize, String sign, String version);

}
