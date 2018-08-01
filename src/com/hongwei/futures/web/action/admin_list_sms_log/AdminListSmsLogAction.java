package com.hongwei.futures.web.action.admin_list_sms_log;

import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuSmsLog;
import com.hongwei.futures.service.FuSmsLogService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminListSmsLogAction extends BaseAction {
	@Autowired
	private FuSmsLogService fuSmsLogService;

	private FuAdmin admin;
	private Long adminId;
	private String userName;
	private String destination;
	private Integer type;// 类型
	private Integer state;// 发送状态
	private String reason;
	private Integer totalCount;
	private Integer prio;
	private String content;

	/**
	 * 发送的消息历史记录
	 * 
	 * @return
	 */
	@Action("smsLogList")
	public String smsLogList() {
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			if (!StringUtil.isBlank(userName)) {
				map.put("userName", userName);
			}
			if (!StringUtil.isBlank(destination)) {
				map.put("destination", destination);
			}
			if (type != null) {
				map.put("type", type);
			}
			if (state != null) {
				map.put("state", state);
			}
			if (!StringUtil.isBlank(reason)) {
				map.put("reason", reason);
			}
			if (prio != null) {
				map.put("prio", prio);
			}
			if (!StringUtil.isBlank(content)) {
				map.put("content", content);
			}
			if (totalCount == null)
				totalCount = fuSmsLogService.countLog(map);
			List<FuSmsLog> logList = fuSmsLogService.findLogList(
					(this.getPageNo() - 1) * this.getPageSize(),
					this.getPageSize(), map);
			this.getActionContext().put("logList", logList);
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getPrio() {
		return prio;
	}

	public void setPrio(Integer prio) {
		this.prio = prio;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
