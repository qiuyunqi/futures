package com.hongwei.futures.web.action.admin_list_game;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuGame;
import com.hongwei.futures.service.FuGameService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminListGameAction extends BaseAction{
	@Autowired
	private FuGameService fuGameService;
	
	
	private FuAdmin admin;
	private Long adminId;
	private Integer totalCount;
	private Date time1;
	private Date time2;
	private String accountName;
	private String userName;
	
	/**
	 * 期货大赛排名
	 * @return
	 * @throws ParseException 
	 */
	@Action("gameList")
	public String gameList() {
		try{
			Map<String, Object> map=new HashMap<String, Object>();
			if(time1!=null)
				map.put("time1", time1);
			if(time2!=null)
				map.put("time2", time2);
			if(!StringUtil.isBlank(accountName)){
				map.put("accountName", accountName);
			}
			if(!StringUtil.isBlank(userName)){
				map.put("userName", userName);
			}
			if(totalCount==null)
				totalCount=fuGameService.countGame(map);
			List<FuGame> list=fuGameService.findGameProgramList((this.getPageNo()-1)*this.getPageSize(),this.getPageSize(),map);
			this.getActionContext().put("list", list);
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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

}
