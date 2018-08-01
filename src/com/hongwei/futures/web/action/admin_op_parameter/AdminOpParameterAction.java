package com.hongwei.futures.web.action.admin_op_parameter;

import java.math.BigDecimal;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuParameter;
import com.hongwei.futures.service.FuParameterService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminOpParameterAction extends BaseAction {
	private static final long serialVersionUID = -2701693306517761494L;
	
	@Autowired
	private FuParameterService fuParameterService;
	
	private FuAdmin amdin;
	private Long adminId;
	private Long id;
	private BigDecimal commissionPercent;
	private BigDecimal feePercent;
	private BigDecimal flatlinePercent;
	private BigDecimal gameMoney;
	private BigDecimal interestPercent;
	private Integer longNum;
	private String[] zf;
	private Integer shortNum;
	private BigDecimal warnlinePercent;
	private BigDecimal feeDay;
	private Integer programDueTime;
	private BigDecimal payPoundage;
	private BigDecimal hhrPercent;
	private BigDecimal stockPercent;
	private Integer regNum;
	private Integer regInterval;
	private Integer messageType;
	private String serviceEmail;
	private Integer isMessage;
	private Integer isPayIncome;

	/**
	 * 保存参数设置
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveParamsAjax")
	public String saveParamsAjax() {
		try {
			if (zf.length == 0) {
				write("-2");
				return null;
			}
			FuParameter param = fuParameterService.findParameter();
			if (param == null) {
				param = new FuParameter();
			}
			param.setCommissionPercent(commissionPercent.divide(new BigDecimal(100)));
			param.setFeePercent(feePercent.divide(new BigDecimal(100)));
			param.setFlatlinePercent(flatlinePercent.divide(new BigDecimal(100)));
			param.setGameMoney(gameMoney);
			param.setInterestPercent(interestPercent.divide(new BigDecimal(100)));
			param.setLongNum(longNum);
			param.setShortNum(shortNum);
			param.setWarnlinePercent(warnlinePercent.divide(new BigDecimal(100)));
			param.setFeeDay(feeDay);
			param.setProgramDueTime(programDueTime);
			param.setPayPoundage(payPoundage.divide(new BigDecimal(100)));
			param.setHhrPercent(hhrPercent.divide(new BigDecimal(100)));
			param.setStockPercent(stockPercent.divide(new BigDecimal(100)));
			param.setRegNum(regNum);
			param.setRegInterval(regInterval);
			param.setIsMessage(isMessage);
			param.setMessageType(messageType);
			param.setServiceEmail(serviceEmail);
			param.setIsPayIncome(isPayIncome);
			JSONObject obj = new JSONObject();
			for (int i = 0; i < zf.length; i++) {
				obj.put("zf" + zf[i], zf[i]);
			}
			param.setRechargeConfig(obj.toString());
			fuParameterService.save(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public FuAdmin getAmdin() {
		return amdin;
	}

	public void setAmdin(FuAdmin amdin) {
		this.amdin = amdin;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getCommissionPercent() {
		return commissionPercent;
	}

	public void setCommissionPercent(BigDecimal commissionPercent) {
		this.commissionPercent = commissionPercent;
	}

	public BigDecimal getFeePercent() {
		return feePercent;
	}

	public void setFeePercent(BigDecimal feePercent) {
		this.feePercent = feePercent;
	}

	public BigDecimal getFlatlinePercent() {
		return flatlinePercent;
	}

	public void setFlatlinePercent(BigDecimal flatlinePercent) {
		this.flatlinePercent = flatlinePercent;
	}

	public BigDecimal getGameMoney() {
		return gameMoney;
	}

	public void setGameMoney(BigDecimal gameMoney) {
		this.gameMoney = gameMoney;
	}

	public BigDecimal getInterestPercent() {
		return interestPercent;
	}

	public void setInterestPercent(BigDecimal interestPercent) {
		this.interestPercent = interestPercent;
	}

	public Integer getLongNum() {
		return longNum;
	}

	public void setLongNum(Integer longNum) {
		this.longNum = longNum;
	}

	public String[] getZf() {
		return zf;
	}

	public void setZf(String[] zf) {
		this.zf = zf;
	}

	public Integer getShortNum() {
		return shortNum;
	}

	public void setShortNum(Integer shortNum) {
		this.shortNum = shortNum;
	}

	public BigDecimal getWarnlinePercent() {
		return warnlinePercent;
	}

	public void setWarnlinePercent(BigDecimal warnlinePercent) {
		this.warnlinePercent = warnlinePercent;
	}

	public BigDecimal getFeeDay() {
		return feeDay;
	}

	public void setFeeDay(BigDecimal feeDay) {
		this.feeDay = feeDay;
	}

	public Integer getProgramDueTime() {
		return programDueTime;
	}

	public void setProgramDueTime(Integer programDueTime) {
		this.programDueTime = programDueTime;
	}

	public BigDecimal getPayPoundage() {
		return payPoundage;
	}

	public void setPayPoundage(BigDecimal payPoundage) {
		this.payPoundage = payPoundage;
	}

	public BigDecimal getHhrPercent() {
		return hhrPercent;
	}

	public void setHhrPercent(BigDecimal hhrPercent) {
		this.hhrPercent = hhrPercent;
	}

	public BigDecimal getStockPercent() {
		return stockPercent;
	}

	public void setStockPercent(BigDecimal stockPercent) {
		this.stockPercent = stockPercent;
	}

	public Integer getRegNum() {
		return regNum;
	}

	public void setRegNum(Integer regNum) {
		this.regNum = regNum;
	}

	public Integer getRegInterval() {
		return regInterval;
	}

	public void setRegInterval(Integer regInterval) {
		this.regInterval = regInterval;
	}

	public Integer getMessageType() {
		return messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	public String getServiceEmail() {
		return serviceEmail;
	}

	public void setServiceEmail(String serviceEmail) {
		this.serviceEmail = serviceEmail;
	}

	public Integer getIsMessage() {
		return isMessage;
	}

	public void setIsMessage(Integer isMessage) {
		this.isMessage = isMessage;
	}

	public Integer getIsPayIncome() {
		return isPayIncome;
	}

	public void setIsPayIncome(Integer isPayIncome) {
		this.isPayIncome = isPayIncome;
	}

}
