package com.hongwei.futures.web.apptrade;

public interface AppTradeWebService {

	/**
	 * 查询产品列表
	 * @param sign 验证码
	 * @return
	 */
	public String queryProductList(String user_id, String sign);

	/**
	 * 通过产品编号查询该产品的历史行情
	 * @param instrumentId  产品编号
	 * @param sign
	 * @return
	 */
	public String queryTodayTick(String instrumentId, String sign);

	/**
	 * 返回日K线历史数据
	 * @param instrument_id  产品编号
	 * @param sign			 验证码
	 * @return
	 */
	public String queryKLine(String instrument_id, String sign);

	/**
	 * 提交订单到服务端。报单分开仓报单和平仓报单，分别产生唯一订单号
	 * @param user_id                 用户编号(必填)
	 * @param instrument_id           合约产品编号(必填)
	 * @param direction               买0卖1 (必填)
	 * @param offset_flag             开0 平1 (必填)
	 * @param volume                  手数(必填) 默认是1
	 * @param stop_profit			     止盈           默认是0
	 * @param stop_loss               止损           默认是0
	 * @param sign                    验证码
	 * @return
	 */
	public String order(String trdTradeId, String user_id, String instrument_id, String direction,
			String offset_flag, String volume, String stop_profit,
			String stop_loss, String sign);

	/**
	 * 根据状态 查询相应的订单
	 * @param user_id 	      用户的ID
	 * @param sign        验证码
	 * @param state       订单状态  state(只能查询状态是0的)
	 * @return
	 */
	public String orderQuery(String user_id, String sign, String state);

	/**
	 * 根据状态和用户ID 查询持仓信息
	 * @param user_id						用户的ID
	 * @param sign							验证码
	 * @param instrument_id 				产品编号
	 * @return
	 */
	public String trdTradeQuery(String user_id, String instrument_id, String sign);

	/**
	 * 结算详情
	 * @param user_id			用户id
	 * @param trade_id			持仓表id
	 * @param instrument_id		产品编号
	 * @param sign 
	 * @param trade_id 
	 * @return
	 */
	public String setTlement(String user_id, String trade_id, String instrument_id, String sign);

	/**
	 * 查询用户的结算信息
	 * @param user_id
	 * @param instrument_id 				产品编号
	 * @param sign
	 * @return
	 */
	public String tlementQuery(String user_id, String instrument_id, String sign);
	
	/**
	 * 查询所有结算单的盈利 以及盈利的笔数
	 * @param user_id
	 * @param sign
	 * @return
	 */
	public String sumProfit(String user_id, String sign);
		
}
