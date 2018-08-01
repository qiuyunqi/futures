package com.hongwei.futures.web.action.admin_login_log;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.AdminLoginLog;
import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.service.AdminLoginLogService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminLoginLogAction extends BaseAction {

	private FuAdmin admin;
	private Long adminId;
	private Integer totalCount;

	private Date time1;
	private Date time2;
	private Long userId;
	private Integer logType;

	@Autowired
	private AdminLoginLogService adminLoginLogService;

	@Action("logList")
	public String logList() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if(logType == null){
				logType = 0;
			}
			map.put("logType", logType);
			if (userId != null) {
				map.put("adminId", userId);
			}
			if (time1 != null) {
				map.put("time1", time1);
			}
			if (time2 != null) {
				map.put("time2", time2);
			}
			List<AdminLoginLog> adminLoginLogList = adminLoginLogService.queryAdminLoginLog((this.getPageNo() - 1) * this.getPageSize(), this.getPageSize(), map);
			if (totalCount == null) {
				totalCount = adminLoginLogService.countAdminLoginLog(map);
			}
			this.getActionContext().put("adminLoginLogList", adminLoginLogList);
		} catch (Exception e) {
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Integer getLogType() {
		return logType;
	}

	public void setLogType(Integer logType) {
		this.logType = logType;
	}
	
}
