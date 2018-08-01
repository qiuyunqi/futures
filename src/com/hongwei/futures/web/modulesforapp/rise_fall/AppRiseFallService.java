package com.hongwei.futures.web.modulesforapp.rise_fall;

public interface AppRiseFallService {

	/**
	 * 获取看跌张的产品信息
	 * @param userId 	用户id
	 * @param version 
	 * @return
	 */
	public String getRiseFallInfo(String userId, String version);

	/**
	 * 获取用户的订单详情
	 * @param userId
	 * @param version
	 * @return
	 */
	public String getOrder(String userId, String version);

	/**
	 * 立即支付
	 * @param userId
	 * @param drawPwd		交易密码
	 * @param money			用户购买的需要支付的钱
	 * @param version 
	 * @return
	 */
	public String quicklyPay(String userId, String drawPwd, String money, String guessType, String version);

}
