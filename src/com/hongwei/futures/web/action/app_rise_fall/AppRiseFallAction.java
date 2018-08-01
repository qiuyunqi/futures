package com.hongwei.futures.web.action.app_rise_fall;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.web.action.BaseAction;
import com.hongwei.futures.web.modulesforapp.rise_fall.AppRiseFallService;

@ParentPackage("api_package")
public class AppRiseFallAction extends BaseAction {
	private static final long serialVersionUID = 847779256357646487L;

	@Autowired
	private AppRiseFallService appRiseFallService;

	/**
	 * 获取看涨跌的的产品信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("getRiseFallInfo")
	public String getRiseFallInfo() {
		try {
			String userId = getHttpServletRequest().getParameter("user_id");
			String version = getHttpServletRequest().getParameter("version");
			String objStr = appRiseFallService.getRiseFallInfo(userId, version);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取订单信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("getOrder")
	public String getOrder() {
		try {
			String version = getHttpServletRequest().getParameter("version");
			String userId = getHttpServletRequest().getParameter("user_id");
			String objStr = appRiseFallService.getOrder(userId, version);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 立即支付
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("quicklyPay")
	public String quicklyPay() {
		try {
			String version = getHttpServletRequest().getParameter("version");
			String userId = getHttpServletRequest().getParameter("user_id");
			String money = getHttpServletRequest().getParameter("money");
			String drawPwd = getHttpServletRequest().getParameter("draw_pwd");
			String guessType = getHttpServletRequest().getParameter(
					"guess_type"); // 0:
									// 跌
									// 1:涨
			String objStr = appRiseFallService.quicklyPay(userId, drawPwd,
					money, guessType, version);
			write(objStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
