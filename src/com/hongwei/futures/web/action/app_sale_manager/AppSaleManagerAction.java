package com.hongwei.futures.web.action.app_sale_manager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.web.action.BaseAction;
import com.hongwei.futures.web.modulesforapp.sale_manager.AppTradeSaleManagerService;

@ParentPackage("api_package")
public class AppSaleManagerAction extends BaseAction{
	private static final long serialVersionUID = 8654842957710240458L;
	private static final Log logger = LogFactory.getLog(AppSaleManagerAction.class);
	
	@Autowired
	private AppTradeSaleManagerService appTradeSaleManagerService;
	/**
	 * 销售管理
	 * @return
	 */
	@Action("index")
	public String index() {
		try{
			String userId = getHttpServletRequest().getParameter("user_id");
			String version = getHttpServletRequest().getParameter("version");
			String objStr = appTradeSaleManagerService.index(userId, version);
			write(objStr);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}

	/**
	 * 销售累计盈利详情
	 * @return
	 */
	@Action("saleCountInfo")
	public String saleCountInfo() {
		try{
			String userId = getHttpServletRequest().getParameter("user_id");
			String detailId = getHttpServletRequest().getParameter("detail_id");
			String version = getHttpServletRequest().getParameter("version");
			String objStr = appTradeSaleManagerService.saleCountInfo(userId, detailId, version);
			write(objStr);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}
	
	/**
	 * 查询用户下所有股票账详情(是销售人员下的用户)
	 * @return
	 */
	@Action("getStockListInfo")
	public String getStockListInfo() {
		try {
			String userId = getHttpServletRequest().getParameter("user_id");
			String version = getHttpServletRequest().getParameter("version");
			String objStr = appTradeSaleManagerService.getStockListInfo(userId, version);
			write(objStr);
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}
	
	/**
	 * 获取每个账户的每日盈利详情信息
	 * @return
	 */
	@Action("getInfo")
	public String getInfo() {
		try{
			String stockId = getHttpServletRequest().getParameter("stock_id");
			String detailId = getHttpServletRequest().getParameter("detail_id");
			String version = getHttpServletRequest().getParameter("version");
			String objStr = appTradeSaleManagerService.getInfo(stockId, detailId, version);
			write(objStr);
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}
	
	/**
	 * 获取每个账户的每日管理费详情信息
	 * @return
	 */
	@Action("freeInfo")
	public String freeInfo() {
		try {
			String stockId = getHttpServletRequest().getParameter("stock_id");
			String detailId = getHttpServletRequest().getParameter("detail_id");
			String version = getHttpServletRequest().getParameter("version");
			String objStr = appTradeSaleManagerService.feeInfo(stockId, detailId, version);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		return null;
	}
	
	/**
	 * 获取交易备注流水
	 * @return
	 */
	@Action("getRemark")
	public String getRemark() {
		try {
			String stockId = getHttpServletRequest().getParameter("stock_id");
			String detailId = getHttpServletRequest().getParameter("detail_id");
			String version = getHttpServletRequest().getParameter("version");
			String objStr = appTradeSaleManagerService.getRemark(stockId, detailId, version);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}

	/**
	 * 设置收益模式
	 * @return
	 */
	@Action("saveProfitModel")
	public String saveProfitModel() {
		try {
			String stockId = getHttpServletRequest().getParameter("stock_id");
			String profitModel = getHttpServletRequest().getParameter("profit_model");
			String availableSplit = getHttpServletRequest().getParameter("available_split"); 	// 股票收益
			String ableMoneySplit = getHttpServletRequest().getParameter("able_money_split");	// 资金收益
			String version = getHttpServletRequest().getParameter("version");
			String objStr = appTradeSaleManagerService.saveProfitModel(stockId, profitModel, availableSplit, ableMoneySplit, version);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}
}
