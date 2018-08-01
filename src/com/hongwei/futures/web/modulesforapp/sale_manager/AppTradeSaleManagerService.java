package com.hongwei.futures.web.modulesforapp.sale_manager;

public interface AppTradeSaleManagerService {

	/**
	 * 根据用户的userId销售管理的首页数据
	 * @param userId    用户id    
	 * @param version   当前接口的版本号     
	 * @return              
	 */                 
	public String index(String userId, String version);

	/**
	 * 销售累计盈利详情
	 * @param userId		用户id
	 * @param detailId		FuMoneyDetail的主键
	 * @param version		当前接口的版本号
	 * @return
	 */
	public String saleCountInfo(String userId, String detailId, String version);

	/**
	 * 查询用户下所有股票账详情(是销售人员下的用户)
	 * @param userId        用户id     
	 * @param version       当前接口的版本号 
	 * @return
	 */
	public String getStockListInfo(String userId, String version);

	/**
	 * 根据stocId 查询该账户的详情
	 * @param stockId		fuStockAccount的主键
	 * @param detailId		FuMoneyDetail的主键
	 * @param version 		当前接口的版本号 
	 * @return
	 */
	public String getInfo(String stockId, String detailId, String version);

	/**
	 * 每日管理费用流水详情
	 * @param stockId        fuStockAccount的主键
	 * @param detailId       FuMoneyDetail的主键
	 * @param version        当前接口的版本号         
	 * @return
	 */
	public String feeInfo(String stockId, String detailId, String version);

	/**
	 * 查询每个账户的备注流水详情
	 * @param stockId		fuStockAccount的主键
	 * @param detailId      FuMoneyDetail的主键         
	 * @param version       当前接口的版本号       
	 * @return
	 */
	public String getRemark(String stockId, String detailId, String version);

	/**
	 * 设置收益模式
	 * @param stockId			fuStockAccount的主键
	 * @param profitModel		收益模式	0:稳定收益 1:保本分成 null == 2:没设置收益模式
	 * @param availableSplit	股票收益
	 * @param ableMoneySplit 	资金收益
	 * @param version			当前接口的版本号
	 * @return
	 */
	public String saveProfitModel(String stockId, String profitModel, String availableSplit, String ableMoneySplit, String version);

}
