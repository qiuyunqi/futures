package com.hongwei.futures.web.action.admin_list_close_trade;

import java.math.BigDecimal;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.TrdTradeParameter;
import com.hongwei.futures.service.TrdTradeParameterService;
import com.hongwei.futures.service.TrdTradeService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminListCloseTradeAction extends BaseAction{
	@Autowired
	private TrdTradeParameterService tradeParameterService;
	@Autowired
	private TrdTradeService trdTradeService;
	private FuAdmin admin;
	private Long adminId;
	
	private String instrumentId;
	private BigDecimal closeMoney;
	private String closeTime;
	
	/**
	 * 强平页面
	 * @return
	 */
	@Action("closeTrade")
	public String closeTrade(){
		try{
			List<TrdTradeParameter> paramList = tradeParameterService.findAllParams();
			this.getActionContext().put("paramList", paramList);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 强平处理
	 * @return
	 * @throws Exception 
	 */
	@Action("closeProcess")
	public String closeProcess() {	
		try{
			trdTradeService.saveCloseProcess(instrumentId, closeMoney, closeTime);
			write("1");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
			
	public FuAdmin getAdmin() {
		return admin;
	}

	public void setAdmin(FuAdmin admin) {
		this.admin = admin;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	
	public String getInstrumentId() {
		return instrumentId;
	}

	public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
	}

	public BigDecimal getCloseMoney() {
		return closeMoney;
	}

	public void setCloseMoney(BigDecimal closeMoney) {
		this.closeMoney = closeMoney;
	}

	public String getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}
	
}
