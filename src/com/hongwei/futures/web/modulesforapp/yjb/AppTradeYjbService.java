package com.hongwei.futures.web.modulesforapp.yjb;

public abstract  interface AppTradeYjbService {

	/**
	 * 获取余劵宝主页数据
	 * @param version		版本号
	 * @return
	 */
	public String getIndex(String version);
	
	/**
	 * 提交账户的初审资料
	 * @param userId		用户id
	 * @param openEquity	开户劵商
	 * @param accountType	账号类型 		1）普通账户2）融资融券3）信用账户
	 * @param stockImage	上传的股票截图
	 * @param stockImageStr 股票截图字节
	 * @param version		版本号
	 * @return
	 */
	public String examine(String userId, String openEquity, String accountType,
			String stockImage, String stockImageStr, String version);

	/**
	 * 获取当前用户的可用账户的首页信息
	 * @param userId	用户id
	 * @param version	版本号
	 * @return
	 */
	public String finshIndex(String userId, String version);

	/**
	 * 进一步完善账号信息
	 * @param stockId				股票账号id
	 * @param SalesDept				营业部
	 * @param capitalAccount		资金账号	
	 * @param capitalPassword		资金密码
	 * @param version
	 * @return
	 */
	public String refined(String stockId, String salesDept,
			String capitalAccount, String capitalPassword, String version);

	/**
	 * 根据账号查询这个账号的具体信息
	 * @param stockId			股票账号id
	 * @param version	
	 * @return
	 */
	public String getInfo(String stockId, String version);

	/**
	 * 累计盈利列表
	 * @param userId			用户id
	 * @param version
	 * @return
	 */
	public String getProfitList(String userId, String version);

	/**
	 * 管理费列表
	 * @param userId			用id
	 * @param version
	 * @return
	 */
	public String manageFeeList(String userId, String version);

	/**
	 * 修改账号状态
	 * @param stockId			股票账户id
	 * @param version			版本号
	 * @return
	 */
	public String updateState(String stockId, String version);

	/**
	 * 缴费记录列表
	 * @param userId		用id
	 * @param version
	 * @return
	 */
	public String payRecords(String userId, String version);

	/**
	 * 删除审核失败的股票账号
	 * @param userId		用户Id
	 * @param stockId	股票账号id
	 * @param version
	 * @return
	 */
	public String delExaminFail(String userId, String stockId, String version);

	/**
	 * 根据stockAccount的主键查询出股票账号信息以及具体股票信息
	 * @param stockId		fuStockAccount的主键Id
	 * @param version
	 * @return
	 */
	public String getShareInfo(String stockId, String version);

	/**
	 * 查询所有可以抢的股票账户
	 * @param version     	版本号
	 * @return
	 */
	public String findAllRqb(String version);

	/**
	 * 抢接单中的股票账户
	 * @param userId			当前登录用户id
	 * @param stockId		接单中的股票账户的id
	 * @param version
	 * @return
	 */
	public String orderRqb(String userId, String stockId, String version);

	/**
	 * 获取"我的融券"业务数据
	 * @param userId
	 * @param version
	 * @return
	 */
	public String myRqb(String userId, String version);

	/**
	 * 交易团队退单
	 * @param userId		用户id
	 * @param stockId	股票的id
	 * @param version
	 * @return
	 */
	public String chargeback(String userId, String stockId, String version);
}
