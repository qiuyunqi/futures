package com.hongwei.futures.web.action.admin_list_fee;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuManageFee;
import com.hongwei.futures.service.FuManageFeeService;
import com.hongwei.futures.web.action.BaseAction;
@ParentPackage("admin")
public class AdminListFeeAction extends BaseAction{
	@Autowired
	private FuManageFeeService fuManageFeeService;
	
	private FuAdmin admin;
	private Long adminId;
	private Integer totalCount;
	
	private Long programId;//方案id
	private Long userId;//用户id;
	private String accountName;
	private String userName;
	private Integer payCycle1;
	private Integer payCycle2;
	private BigDecimal money1;
	private BigDecimal money2;
	private Date payTime1;
	private Date payTime2;
	
	
	/**
	 * 管理费核查
	 * @return
	 */
	@Action("feeList")
	public String feeList(){
		try{
			HashMap<String, Object> map = new HashMap<String, Object>();
			if(programId!=null)
				map.put("programId", programId);
			if(userId!=null)
				map.put("userId", userId);
			if(!StringUtil.isBlank(accountName))
				map.put("accountName", accountName);
			if(!StringUtil.isBlank(userName))
				map.put("userName", userName);
			if(payCycle1!=null)
				map.put("payCycle1", payCycle1);
			if(payCycle2!=null)
				map.put("payCycle2", payCycle2);
			if(money1!=null)
				map.put("money1", money1);
			if(money2!=null)
				map.put("money2", money2);
			if(payTime1!=null)
				map.put("payTime1", payTime1);
			if(payTime2!=null)
				map.put("payTime2", payTime2);
			
			if(totalCount==null)
				totalCount=fuManageFeeService.countFee(map);
			List<FuManageFee> feeList=fuManageFeeService.findFeeList((this.getPageNo()-1)*this.getPageSize(),this.getPageSize(),map);
			this.getActionContext().put("feeList", feeList);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
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
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Long getProgramId() {
		return programId;
	}

	public void setProgramId(Long programId) {
		this.programId = programId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getPayCycle1() {
		return payCycle1;
	}

	public void setPayCycle1(Integer payCycle1) {
		this.payCycle1 = payCycle1;
	}

	public Integer getPayCycle2() {
		return payCycle2;
	}

	public void setPayCycle2(Integer payCycle2) {
		this.payCycle2 = payCycle2;
	}

	public BigDecimal getMoney1() {
		return money1;
	}

	public void setMoney1(BigDecimal money1) {
		this.money1 = money1;
	}

	public BigDecimal getMoney2() {
		return money2;
	}

	public void setMoney2(BigDecimal money2) {
		this.money2 = money2;
	}

	public Date getPayTime1() {
		return payTime1;
	}

	public void setPayTime1(Date payTime1) {
		this.payTime1 = payTime1;
	}

	public Date getPayTime2() {
		return payTime2;
	}

	public void setPayTime2(Date payTime2) {
		this.payTime2 = payTime2;
	}
	
}
