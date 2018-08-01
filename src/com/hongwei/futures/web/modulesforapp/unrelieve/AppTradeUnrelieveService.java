package com.hongwei.futures.web.modulesforapp.unrelieve;

public interface AppTradeUnrelieveService {

	/**
	 * 昨日盈亏 自己的账户基本信息 累积盈利 未缴管理费
	 * @param userId
	 * @param version
	 * @param sign
	 * @return
	 */
	public String accountList(String userId, String version, String sign);
	
	/**
	 * 保存账号
	 * @param userId					用户 id
	 * @param openUser					开户姓名
	 * @param openEquity				开户券商	
	 * @param salesDept					营业部
	 * @param capitalAccount			资金账号
	 * @param capitalPassword			交易密码
	 * @param partnerAccount			股东账号
	 * @param accountType				账户类型  1: 普通账户 2:融资融券 3:信用账户
	 * @param version
	 * @param sign
	 * @return
	 */
	public String addAccountInfo(String userId, String openUser,
			String openEquity, String salesDept, String capitalAccount,
			String capitalPassword, String partnerAccount, String accountType,
			String version, String sign);

	/**
	 * 每日盈亏流水
	 * @param userId		
	 * @param version
	 * @param sign
	 * @return
	 */
	public String profitOrLossDetail(String userId, String version, String sign);

	/**
	 * 查询费用
	 * @param userId			用户 id
	 * @param flag				0: 全部  1: 应缴费用 2: 已缴费用
	 * @param sign
	 * @param version
	 * @return
	 */
	public String cost(String userId, String sign, String version);

	/**
	 * 根据 id 查询账号具体信息
	 * @param stockId		
	 * @param version
	 * @param sign
	 * @return
	 */
	public String accountDetail(String stockId, String version, String sign);

	/**
	 * 一键缴费
	 * @param userId			用户 id
	 * @param version
	 * @param sign
	 * @return
	 */
	public String keyPayment(String userId, String version, String sign);

	/**
	 * 已缴费用  // 交付费用
	 * @param userId
	 * @param sign
	 * @param version
	 * @return
	 */
	public String feesPaid(String userId, String sign, String version);

	/**
	 * 应缴费用  // 平台管理 和 平台赔付
	 * @param userId
	 * @param sign
	 * @param version
	 * @return
	 */
	public String noFeesPaid(String userId, String sign, String version);

}
