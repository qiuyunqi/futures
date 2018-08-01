package com.hongwei.futures.web.action.admin_list_draw_profits;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuDrawProfits;
import com.hongwei.futures.service.FuDrawProfitsService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminListDrawProfitsAction extends BaseAction{
	
	@Autowired
	private FuDrawProfitsService drawProfitsService;
	
	private FuAdmin admin;
	private Long adminId;
	private Integer totalCount;
	private Long detailId;
	private String accountName;
	private String userName;
	private Long programId;
	private Integer state;
	private BigDecimal money1;
	private BigDecimal money2;
	private Date time1;
	private Date time2;
	
	/**
	 * 待利润提取列表
	 * @return
	 */
	@Action("drawProfitsList")
	public String drawProfitsList(){
		try{
			Map<String,Object> map = new HashMap<String,Object>();
			if(detailId!=null)
				map.put("detailId", detailId);
			if(!StringUtil.isBlank(accountName))
				map.put("accountName", accountName);
			if(!StringUtil.isBlank(userName))
				map.put("userName", userName);
			if(programId!=null)
				map.put("programId", programId);
			if(state!=null)
				map.put("state", state);
			if(money1!=null)
				map.put("money1", money1);
			if(money2!=null)
				map.put("money2", money2);
			if(time1!=null)
				map.put("time1", time1);
			if(time2!=null)
				map.put("time2", time2);
			if(totalCount==null)
				totalCount=drawProfitsService.countDrawProfits(map);
			List<FuDrawProfits> drawList = drawProfitsService.findDrawList((this.getPageNo()-1)*this.getPageSize(),this.getPageSize(),map);
			this.getActionContext().put("drawList", drawList);
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


	public Long getDetailId() {
		return detailId;
	}


	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}


	public String getAccountName() {
		return accountName;
	}


	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}


	public Long getProgramId() {
		return programId;
	}


	public void setProgramId(Long programId) {
		this.programId = programId;
	}


	public Integer getState() {
		return state;
	}


	public void setState(Integer state) {
		this.state = state;
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

	public Date getTime1() {
		return time1;
	}


	public void setTime1(Date time1) {
		this.time1 = time1;
	}


	public Date getTime2() {
		return time2;
	}


	public void setTime2(Date time2) {
		this.time2 = time2;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

}
