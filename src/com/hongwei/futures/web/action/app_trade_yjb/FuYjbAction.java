package com.hongwei.futures.web.action.app_trade_yjb;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.web.action.BaseAction;
import com.hongwei.futures.web.modulesforapp.yjb.AppTradeYjbService;

@ParentPackage("api_package")
public class FuYjbAction extends BaseAction {

	private static final long serialVersionUID = -7639581710420378495L;

	@Autowired
	private AppTradeYjbService appTradeYjbService;

	/**
	 * 获取余劵宝主页数据
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("getIndex")
	public String getIndex() {
		try {
			String version = getHttpServletRequest().getParameter("version");
			String objStr = appTradeYjbService.getIndex(version);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 提交账户的初审资料
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("examine")
	public String examine() {
		try {
			String version = getHttpServletRequest().getParameter("version");
			String userId = getHttpServletRequest().getParameter("user_id");
			String openEquity = getHttpServletRequest().getParameter("open_equity");
//			String salesDept = getHttpServletRequest().getParameter("sales_dept");
			String accountType = getHttpServletRequest().getParameter("account_type");
			String stockImage = getHttpServletRequest().getParameter("stock_image");
			String stockImageStr = getHttpServletRequest().getParameter("stock_image_str");// 图片的字节转化的字符串
			String objStr = appTradeYjbService.examine(userId, openEquity, accountType, stockImage, stockImageStr, version);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取当前用户的可用账户的首页信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("finshIndex")
	public String finshIndex() {
		try {
			String version = getHttpServletRequest().getParameter("version");
			String userId = getHttpServletRequest().getParameter("user_id");
			String objStr = appTradeYjbService.finshIndex(userId, version);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 进一步完善账号信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("refined")
	public String refined() {
		try {
			String version = getHttpServletRequest().getParameter("version");
			String stockId = getHttpServletRequest().getParameter("stock_id");
			String salesDept = getHttpServletRequest().getParameter("sales_dept");
//			String accountType = getHttpServletRequest().getParameter("account_type");
			String capitalAccount = getHttpServletRequest().getParameter("capital_account");
			String capitalPassword = getHttpServletRequest().getParameter("capital_password");
			String objStr = appTradeYjbService.refined(stockId, salesDept, capitalAccount, capitalPassword, version);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据账号查询这个账号的具体信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("getInfo")
	public String getInfo() {
		try {
			String version = getHttpServletRequest().getParameter("version");
			String stockId = getHttpServletRequest().getParameter("stock_id");
			String objStr = appTradeYjbService.getInfo(stockId, version);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 累计盈利列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("getProfitList")
	public String getProfitList() {
		try {
			String version = getHttpServletRequest().getParameter("version");
			String userId = getHttpServletRequest().getParameter("user_id");
			String objStr = appTradeYjbService.getProfitList(userId, version);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 管理费列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("manageFeeList")
	public String manageFeeList() {
		try {
			String version = getHttpServletRequest().getParameter("version");
			String userId = getHttpServletRequest().getParameter("user_id");
			String objStr = appTradeYjbService.manageFeeList(userId, version);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 缴费记录
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("payRecord")
	public String payRecords() {
		try {
			String version = getHttpServletRequest().getParameter("version");
			String userId = getHttpServletRequest().getParameter("user_id");
			String objStr = appTradeYjbService.payRecords(userId, version);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 修改账号状态
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("updateState")
	public String updateState() {
		try {
			String version = getHttpServletRequest().getParameter("version");
			String stockId = getHttpServletRequest().getParameter("stock_id");
			String objStr = appTradeYjbService.updateState(stockId, version);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除审核失败的股票账号
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("delExaminFail")
	public String delExaminFail() {
		try {
			String version = getHttpServletRequest().getParameter("version");
			String stockId = getHttpServletRequest().getParameter("stock_id");
			String userId = getHttpServletRequest().getParameter("user_id");
			String objStr = appTradeYjbService.delExaminFail(userId, stockId, version);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
	/**---------------------------------------------------------------------------------------------------**/
	/**
	 * 融券宝业务
	 */
	
	/**
	 ***** 融券大厅
	 * 查询所有可以抢的股票账户
	 * @return
	 */
	@Action("findAllRqb")
	public String findAllRqb() {
		try{
			String version = getHttpServletRequest().getParameter("version");
			String objStr = appTradeYjbService.findAllRqb(version);
			write(objStr);
		}catch(Exception e) {
			
		}
		return null;
	}
	
	/**
	 * 获取账号信息
	 * @return
	 */
	@Action("getShareInfo")
	public String getShareInfo() {
		try{
			String version = getHttpServletRequest().getParameter("version");
			String stockId = getHttpServletRequest().getParameter("stock_id");
			String objStr = appTradeYjbService.getShareInfo(stockId, version);
			write(objStr);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取"我的融券"业务数据
	 * @return
	 */
	@Action("myRqb")
	public String myRqb() {
		try {
			String version = getHttpServletRequest().getParameter("version");
			String userId = getHttpServletRequest().getParameter("user_id");
			String objStr = appTradeYjbService.myRqb(userId, version);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 抢融券宝
	 * @return
	 */
	@Action("orderRqb")
	public String orderRqb() {
		try{
			String version = getHttpServletRequest().getParameter("version");
			String userId = getHttpServletRequest().getParameter("user_id");
			String stockId = getHttpServletRequest().getParameter("stock_id");
			String objStr = appTradeYjbService.orderRqb(userId, stockId, version);
			write(objStr);
			return null;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 退单
	 * @return
	 */
	@Action("chargeback")
	public String chargeback() {
		try {
			String version = getHttpServletRequest().getParameter("version");
			String userId = getHttpServletRequest().getParameter("user_id");
			String stockId = getHttpServletRequest().getParameter("stock_id");
			String objStr = appTradeYjbService.chargeback(userId, stockId, version);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
