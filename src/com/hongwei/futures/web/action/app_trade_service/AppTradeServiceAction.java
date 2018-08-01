package com.hongwei.futures.web.action.app_trade_service;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.web.action.BaseAction;
import com.hongwei.futures.web.apptrade.AppTradeWebService;

@ParentPackage("api_package")
public class AppTradeServiceAction extends BaseAction {
	private static final long serialVersionUID = 6353133453861341796L;
	@Autowired
	private AppTradeWebService appTradeWebService;

	/**
	 * 返回产品列表
	 * 
	 * @return objStr
	 * @throws Exception
	 */
	@Action("queryProductList")
	public String queryProductList() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String user_id = getHttpServletRequest().getParameter("user_id");
			String objStr = appTradeWebService.queryProductList(user_id, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 返回当天历史行情
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("queryTodayTick")
	public String queryTodayTick() {
		try {
			String instrumentId = getHttpServletRequest().getParameter(
					"instrument_id");
			String sign = getHttpServletRequest().getParameter("sign");
			String objStr = appTradeWebService.queryTodayTick(instrumentId,
					sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查看日K线
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("queryKLine")
	public String queryKLine() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String instrument_id = getHttpServletRequest().getParameter(
					"instrument_id");
			String objStr = appTradeWebService.queryKLine(instrument_id, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 提交订单到服务端。报单分开仓报单和平仓报单，分别产生唯一订单号
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("order")
	public String order() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String trdTradeId = getHttpServletRequest()
					.getParameter("trade_id");
			String user_id = getHttpServletRequest().getParameter("user_id");
			String instrument_id = getHttpServletRequest().getParameter(
					"instrument_id");
			String direction = getHttpServletRequest()
					.getParameter("direction");
			String offset_flag = getHttpServletRequest().getParameter(
					"offset_flag");
			String volume = getHttpServletRequest().getParameter("volume");
			String stop_profit = getHttpServletRequest().getParameter(
					"stop_profit");
			String stop_loss = getHttpServletRequest()
					.getParameter("stop_loss");
			String objStr = appTradeWebService.order(trdTradeId, user_id,
					instrument_id, direction, offset_flag, volume, stop_profit,
					stop_loss, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据订单的状态 查询订单
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("orderQuery")
	public String orderQuery() {
		try {
			String state = getHttpServletRequest().getParameter("state");
			String sign = getHttpServletRequest().getParameter("sign");
			String user_id = getHttpServletRequest().getParameter("user_id");
			String objStr = appTradeWebService.orderQuery(user_id, sign, state);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询用户持有状态的信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("trdTradeQuery")
	public String trdTradeQuery() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String user_id = getHttpServletRequest().getParameter("user_id");
			String instrument_id = getHttpServletRequest().getParameter(
					"instrument_id");
			String objStr = appTradeWebService.trdTradeQuery(user_id,
					instrument_id, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 查询用户的结算信息
	@Action("tlementQuery")
	public String tlementQuery() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String user_id = getHttpServletRequest().getParameter("user_id");
			String instrument_id = getHttpServletRequest().getParameter(
					"instrument_id");
			String objStr = appTradeWebService.tlementQuery(user_id,
					instrument_id, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 结算详情
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("setTlement")
	public String setTlement() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String user_id = getHttpServletRequest().getParameter("user_id");
			String trade_id = getHttpServletRequest().getParameter("trade_id");
			String instrument_id = getHttpServletRequest().getParameter(
					"instrument_id");
			String objStr = appTradeWebService.setTlement(user_id, trade_id,
					instrument_id, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 查询用户的结算的盈利分配的和
	@Action("sumProfit")
	public String sumProfit() {
		try {
			String sign = getHttpServletRequest().getParameter("sign");
			String user_id = getHttpServletRequest().getParameter("user_id");
			String objStr = appTradeWebService.sumProfit(user_id, sign);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
