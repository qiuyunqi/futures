package com.hongwei.futures.web.action.admin_list_commission;

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
import com.hongwei.futures.model.FuCommission;
import com.hongwei.futures.service.FuCommissionService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class CommissionAction extends BaseAction{
	@Autowired
	private FuCommissionService commissionService;
	
	private FuAdmin admin;
	private Long adminId;
	
	private Integer totalCount;
	
	private String accountName;
	private String extendName;
    private String remark;
    private BigDecimal money1;
	private BigDecimal money2;
	private Date time1;
	private Date time2;
	private Integer status;
	private Long programId;
	
	
	/**
	 * 佣金兑换—审核用户佣金，任何产生的佣金都需要经过审核才能生效
	 * @return
	 */
	@Action("commissionList")
	public String commissionList(){
		try{
			Map<String,Object> map = new HashMap<String,Object>();
			if(!StringUtil.isBlank(accountName)){
				map.put("accountName", accountName);
			}
			if(!StringUtil.isBlank(extendName)){
				map.put("extendName", extendName);
			}
			if(programId!=null){
				map.put("programId",programId);
			}
			if(status!=null){
				map.put("status",status);
			}
			if(!StringUtil.isBlank(remark)){
				map.put("remark",remark);
			}
			if(money1!=null){
				map.put("money1",money1);
			}
			if(money2!=null){
				map.put("money2",money2);
			}
			if(time1!=null){
				map.put("time1", time1);
			}
			if(time2!=null){
				map.put("time2", time2);
			}
			if(totalCount==null){
				totalCount = commissionService.getCount(map);
			}
			List<FuCommission> commissionList = commissionService.findList((this.getPageNo()-1)*this.getPageSize(), this.getPageSize(), map);
			this.getActionContext().put("commissionList",commissionList);
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

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getExtendName() {
		return extendName;
	}

	public void setExtendName(String extendName) {
		this.extendName = extendName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Long getProgramId() {
		return programId;
	}

	public void setProgramId(Long programId) {
		this.programId = programId;
	}
}
