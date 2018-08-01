package com.hongwei.futures.web.trade;

import java.math.BigDecimal;

public interface TradeWebService {
	
	/**
	 * 订单回报
	 * @return
	 */
	public String orderBack(Long userId, String orderNum, String instrumentId, Integer direction, Integer offsetFlag, Integer volume, String price, String money, 
			String tradeDateTime, Integer backType, String failureCode, String failureMsg, String sign);
	
	/**
	 * 行情
	 * @return
	 */
	public String quotation(String trading_day, String instrument_id, String exchange_id, String exchange_inst_id, String last_price, 
			String pre_settlement_price, String pre_close_price, String pre_open_interest, String open_price, String highest_price, 
			String lowest_price, String volume, String turnover, String open_interest, String close_price, String settlement_price, 
			String upper_limit_price, String lower_limit_price, String pre_delta, String curr_delta, String update_time, String update_millisec, 
			String bid_price1, String bid_volume1, String ask_price1, String ask_volume1, String bid_price2, String bid_volume2, String ask_price2, 
			String ask_volume2, String bid_price3, String bid_volume3, String ask_price3, String ask_volume3, String bid_price4, String bid_volume4, 
			String ask_price4, String ask_volume4, String bid_price5, String bid_volume5, String ask_price5, String ask_volume5, String average_price, String cdate_time, String sign);
	
	/**
	 * 订单查询
	 * @return
	 */
	public String orderQuery(String state, String sign);
	
	/**
	 * 配置参数查询
	 * @return
	 */
	public String queryParameter(String sign);
	
	/**
	 * 修改配置参数
	 * @return
	 */
	public String changeParameter(Long id, String trade_variety, Integer day_trade_num, BigDecimal safe_money, BigDecimal manage_money, BigDecimal product_percent,  
			String trade_time, String close_time, BigDecimal risk_percent, BigDecimal stop_loss_percent, BigDecimal stop_profit_percent, Integer day_base_num,
			BigDecimal day_base_factor, String update_time, Integer main_position, BigDecimal main_safe_percent, Integer user_add_times, String sign);
	
	/**
	 * 推送服务错误信息
	 * @return
	 */
	public String pushServerLog(String error_code, String error_msg, String sign);
}
