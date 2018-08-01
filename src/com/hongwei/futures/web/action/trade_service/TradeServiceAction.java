package com.hongwei.futures.web.action.trade_service;

import java.math.BigDecimal;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.web.action.BaseAction;
import com.hongwei.futures.web.trade.TradeWebService;

@ParentPackage("fu_common")
public class TradeServiceAction extends BaseAction {
	private static final long serialVersionUID = 5916180806109032400L;

	@Autowired
	private TradeWebService tradeWebService;

	/**
	 * 订单回报
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("orderBack")
	public String orderBack() {
		try {
			Long user_id = getHttpServletRequest().getParameter("user_id") == null ? null
					: Long.valueOf(getHttpServletRequest().getParameter(
							"user_id"));
			String order_num = getHttpServletRequest()
					.getParameter("order_num");
			String instrument_id = getHttpServletRequest().getParameter(
					"instrument_id");
			Integer direction = getHttpServletRequest().getParameter(
					"direction") == null ? null : Integer
					.valueOf(getHttpServletRequest().getParameter("direction"));
			Integer offset_flag = getHttpServletRequest().getParameter(
					"offset_flag") == null ? null : Integer
					.valueOf(getHttpServletRequest()
							.getParameter("offset_flag"));
			Integer volume = getHttpServletRequest().getParameter("volume") == null ? null
					: Integer.valueOf(getHttpServletRequest().getParameter(
							"volume"));
			String price = getHttpServletRequest().getParameter("price");
			String money = getHttpServletRequest().getParameter("money");
			String trade_datetime = getHttpServletRequest().getParameter(
					"trade_datetime");
			Integer back_type = getHttpServletRequest().getParameter(
					"back_type") == null ? null : Integer
					.valueOf(getHttpServletRequest().getParameter("back_type"));
			String failure_code = getHttpServletRequest().getParameter(
					"failure_code");
			String failure_msg = getHttpServletRequest().getParameter(
					"failure_msg");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = tradeWebService.orderBack(user_id, order_num,
					instrument_id, direction, offset_flag, volume, price,
					money, trade_datetime, back_type, failure_code,
					failure_msg, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 行情
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("quotation")
	public String quotation() {
		try {
			String trading_day = getHttpServletRequest().getParameter(
					"trading_day");
			String instrument_id = getHttpServletRequest().getParameter(
					"instrument_id");
			String exchange_id = getHttpServletRequest().getParameter(
					"exchange_id");
			String exchange_inst_id = getHttpServletRequest().getParameter(
					"exchange_inst_id");
			String last_price = getHttpServletRequest().getParameter(
					"last_price");
			String pre_settlement_price = getHttpServletRequest().getParameter(
					"pre_settlement_price");
			String pre_close_price = getHttpServletRequest().getParameter(
					"pre_close_price");
			String pre_open_interest = getHttpServletRequest().getParameter(
					"pre_open_interest");
			String open_price = getHttpServletRequest().getParameter(
					"open_price");
			String highest_price = getHttpServletRequest().getParameter(
					"highest_price");
			String lowest_price = getHttpServletRequest().getParameter(
					"lowest_price");
			String volume = getHttpServletRequest().getParameter("volume");
			String turnover = getHttpServletRequest().getParameter("turnover");
			String open_interest = getHttpServletRequest().getParameter(
					"open_interest");
			String close_price = getHttpServletRequest().getParameter(
					"close_price");
			String settlement_price = getHttpServletRequest().getParameter(
					"settlement_price");
			String upper_limit_price = getHttpServletRequest().getParameter(
					"upper_limit_price");
			String lower_limit_price = getHttpServletRequest().getParameter(
					"lower_limit_price");
			String pre_delta = getHttpServletRequest()
					.getParameter("pre_delta");
			String curr_delta = getHttpServletRequest().getParameter(
					"curr_delta");
			String update_time = getHttpServletRequest().getParameter(
					"update_time");
			String update_millisec = getHttpServletRequest().getParameter(
					"update_millisec");
			String bid_price1 = getHttpServletRequest().getParameter(
					"bid_price1");
			String bid_volume1 = getHttpServletRequest().getParameter(
					"bid_volume1");
			String ask_price1 = getHttpServletRequest().getParameter(
					"ask_price1");
			String ask_volume1 = getHttpServletRequest().getParameter(
					"ask_volume1");
			String bid_price2 = getHttpServletRequest().getParameter(
					"bid_price2");
			String bid_volume2 = getHttpServletRequest().getParameter(
					"bid_volume2");
			String ask_price2 = getHttpServletRequest().getParameter(
					"ask_price2");
			String ask_volume2 = getHttpServletRequest().getParameter(
					"ask_volume2");
			String bid_price3 = getHttpServletRequest().getParameter(
					"bid_price3");
			String bid_volume3 = getHttpServletRequest().getParameter(
					"bid_volume3");
			String ask_price3 = getHttpServletRequest().getParameter(
					"ask_price3");
			String ask_volume3 = getHttpServletRequest().getParameter(
					"ask_volume3");
			String bid_price4 = getHttpServletRequest().getParameter(
					"bid_price4");
			String bid_volume4 = getHttpServletRequest().getParameter(
					"bid_volume4");
			String ask_price4 = getHttpServletRequest().getParameter(
					"ask_price4");
			String ask_volume4 = getHttpServletRequest().getParameter(
					"ask_volume4");
			String bid_price5 = getHttpServletRequest().getParameter(
					"bid_price5");
			String bid_volume5 = getHttpServletRequest().getParameter(
					"bid_volume5");
			String ask_price5 = getHttpServletRequest().getParameter(
					"ask_price5");
			String ask_volume5 = getHttpServletRequest().getParameter(
					"ask_volume5");
			String average_price = getHttpServletRequest().getParameter(
					"average_price");
			String cdate_time = getHttpServletRequest().getParameter(
					"cdate_time");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = tradeWebService.quotation(trading_day,
					instrument_id, exchange_id, exchange_inst_id, last_price,
					pre_settlement_price, pre_close_price, pre_open_interest,
					open_price, highest_price, lowest_price, volume, turnover,
					open_interest, close_price, settlement_price,
					upper_limit_price, lower_limit_price, pre_delta,
					curr_delta, update_time, update_millisec, bid_price1,
					bid_volume1, ask_price1, ask_volume1, bid_price2,
					bid_volume2, ask_price2, ask_volume2, bid_price3,
					bid_volume3, ask_price3, ask_volume3, bid_price4,
					bid_volume4, ask_price4, ask_volume4, bid_price5,
					bid_volume5, ask_price5, ask_volume5, average_price,
					cdate_time, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 订单查询
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("orderQuery")
	public String orderQuery() {
		try {
			String state = getHttpServletRequest().getParameter("state");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = tradeWebService.orderQuery(state, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 配置参数查询
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("queryParameter")
	public String queryParameter() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = tradeWebService.queryParameter(sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 配置参数修改
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("changeParameter")
	public String changeParameter() {
		try {
			Long id = getHttpServletRequest().getParameter("id") == null ? null
					: Long.valueOf(getHttpServletRequest().getParameter("id"));
			String trade_variety = getHttpServletRequest().getParameter(
					"trade_variety");
			Integer day_trade_num = getHttpServletRequest().getParameter(
					"day_trade_num") == null ? null : Integer
					.valueOf(getHttpServletRequest().getParameter(
							"day_trade_num"));
			BigDecimal safe_money = getHttpServletRequest().getParameter(
					"safe_money") == null ? null : (new BigDecimal(
					getHttpServletRequest().getParameter("safe_money")));
			BigDecimal manage_money = getHttpServletRequest().getParameter(
					"manage_money") == null ? null : (new BigDecimal(
					getHttpServletRequest().getParameter("manage_money")));
			BigDecimal product_percent = getHttpServletRequest().getParameter(
					"product_percent") == null ? null : (new BigDecimal(
					getHttpServletRequest().getParameter("product_percent")));
			String trade_time = getHttpServletRequest().getParameter(
					"trade_time");
			String close_time = getHttpServletRequest().getParameter(
					"close_time");
			BigDecimal risk_percent = getHttpServletRequest().getParameter(
					"risk_percent") == null ? null : (new BigDecimal(
					getHttpServletRequest().getParameter("risk_percent")));
			BigDecimal stop_loss_percent = getHttpServletRequest()
					.getParameter("stop_loss_percent") == null ? null
					: (new BigDecimal(getHttpServletRequest().getParameter(
							"stop_loss_percent")));
			BigDecimal stop_profit_percent = getHttpServletRequest()
					.getParameter("stop_profit_percent") == null ? null
					: (new BigDecimal(getHttpServletRequest().getParameter(
							"stop_profit_percent")));
			Integer day_base_num = getHttpServletRequest().getParameter(
					"day_base_num") == null ? null : Integer
					.valueOf(getHttpServletRequest().getParameter(
							"day_base_num"));
			BigDecimal day_base_factor = getHttpServletRequest().getParameter(
					"day_base_factor") == null ? null : (new BigDecimal(
					getHttpServletRequest().getParameter("day_base_factor")));
			String update_time = getHttpServletRequest().getParameter(
					"update_time");
			Integer main_position = getHttpServletRequest().getParameter(
					"main_position") == null ? null : Integer
					.valueOf(getHttpServletRequest().getParameter(
							"main_position"));
			BigDecimal main_safe_percent = getHttpServletRequest()
					.getParameter("main_safe_percent") == null ? null
					: (new BigDecimal(getHttpServletRequest().getParameter(
							"main_safe_percent")));
			Integer user_add_times = getHttpServletRequest().getParameter(
					"user_add_times") == null ? null : Integer
					.valueOf(getHttpServletRequest().getParameter(
							"user_add_times"));
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = tradeWebService.changeParameter(id, trade_variety,
					day_trade_num, safe_money, manage_money, product_percent,
					trade_time, close_time, risk_percent, stop_loss_percent,
					stop_profit_percent, day_base_num, day_base_factor,
					update_time, main_position, main_safe_percent,
					user_add_times, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 推送服务错误信息
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("pushServerLog")
	public String pushServerLog() {
		try {
			String error_code = getHttpServletRequest().getParameter(
					"error_code");
			String error_msg = getHttpServletRequest()
					.getParameter("error_msg");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = tradeWebService.pushServerLog(error_code,
					error_msg, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
