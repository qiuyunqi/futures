package com.hongwei.futures.web.action.admin_list_continue;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuContinueContract;
import com.hongwei.futures.service.FuContinueContractService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminListContinueAction extends BaseAction{
	@Autowired
	private FuContinueContractService fuContinueContractService;
	
	private FuAdmin admin;
	private Long adminId;
	private Integer totalCount;
	private String detailId;
	private String accountName;
	private String userName;
	private String programId;
	private Integer cycle1;
	private Integer cycle2;
	private Integer result;
	private Date time1;
	private Date time2;
	
	/**
	 * 续约记录列表
	 * @return
	 */
	@Action("continueList")
	public String continueList(){
		try{
			Map<String,Object> map=new HashMap<String, Object>();
			if(!StringUtil.isBlank(detailId))
				map.put("detailId", detailId);
			if(!StringUtil.isBlank(accountName))
				map.put("accountName", accountName);
			if(!StringUtil.isBlank(userName))
				map.put("userName", userName);
			if(!StringUtil.isBlank(programId))
				map.put("programId", programId);
			if(cycle1!=null)
				map.put("cycle1", cycle1);
			if(cycle2!=null)
				map.put("cycle2", cycle2);
			if(time1!=null)
				map.put("time1", time1);
			if(time1!=null)
				map.put("time2", time2);
			if(result!=null)
				map.put("result", result);
			
			if(totalCount==null)
				totalCount=fuContinueContractService.countContinue(map);
			List<FuContinueContract> continueList=fuContinueContractService.getContinueList((this.getPageNo()-1)*this.getPageSize(),this.getPageSize(),map);
			this.getActionContext().put("continueList", continueList);
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

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	public Integer getCycle1() {
		return cycle1;
	}

	public void setCycle1(Integer cycle1) {
		this.cycle1 = cycle1;
	}

	public Integer getCycle2() {
		return cycle2;
	}

	public void setCycle2(Integer cycle2) {
		this.cycle2 = cycle2;
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
