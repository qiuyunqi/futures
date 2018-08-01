package com.hongwei.futures.web.action.admin_list_message;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuMessage;
import com.hongwei.futures.service.FuMessageService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminMessageAction extends BaseAction {
	@Autowired
	private FuMessageService fuMessageService;

	private FuAdmin admin;
	private Long adminId;

	private Long id;
	private Integer totalCount;
	private String leaveUser;
	private String replyAdmin;
	private String messageRemark;
	private Date leaveBeginTime;
	private Date leaveEndTime;
	private Date replyBeginTime;
	private Date replyEndTime;
	private Integer type;
	private String content;

	/**
	 * 留言列表
	 * 
	 * @return
	 */
	@Action("messageList")
	public String messageList() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtil.isBlank(leaveUser)) {
				map.put("leaveUser", leaveUser);
			}
			if (!StringUtil.isBlank(replyAdmin)) {
				map.put("replyAdmin", replyAdmin);
			}
			if (!StringUtil.isBlank(messageRemark)) {
				map.put("messageRemark", messageRemark);
			}
			if (leaveBeginTime != null) {
				map.put("leaveBeginTime", leaveBeginTime);
			}
			if (leaveEndTime != null) {
				map.put("leaveEndTime", leaveEndTime);
			}
			if (replyBeginTime != null) {
				map.put("replyBeginTime", replyBeginTime);
			}
			if (replyEndTime != null) {
				map.put("replyEndTime", replyEndTime);
			}
			if (type != null) {
				map.put("type", type);
			}
			if (!StringUtil.isBlank(content)) {
				map.put("content", content);
			}
			if (totalCount == null)
				totalCount = fuMessageService.countAllMessage(map);
			List<FuMessage> messageList = fuMessageService.findAllMessage(
					(this.getPageNo() - 1) * this.getPageSize(),
					this.getPageSize(), map);
			this.getActionContext().put("messageList", messageList);
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public String getMessageRemark() {
		return messageRemark;
	}

	public void setMessageRemark(String messageRemark) {
		this.messageRemark = messageRemark;
	}

	public String getLeaveUser() {
		return leaveUser;
	}

	public void setLeaveUser(String leaveUser) {
		this.leaveUser = leaveUser;
	}

	public String getReplyAdmin() {
		return replyAdmin;
	}

	public void setReplyAdmin(String replyAdmin) {
		this.replyAdmin = replyAdmin;
	}

	public Date getLeaveBeginTime() {
		return leaveBeginTime;
	}

	public void setLeaveBeginTime(Date leaveBeginTime) {
		this.leaveBeginTime = leaveBeginTime;
	}

	public Date getLeaveEndTime() {
		return leaveEndTime;
	}

	public void setLeaveEndTime(Date leaveEndTime) {
		this.leaveEndTime = leaveEndTime;
	}

	public Date getReplyBeginTime() {
		return replyBeginTime;
	}

	public void setReplyBeginTime(Date replyBeginTime) {
		this.replyBeginTime = replyBeginTime;
	}

	public Date getReplyEndTime() {
		return replyEndTime;
	}

	public void setReplyEndTime(Date replyEndTime) {
		this.replyEndTime = replyEndTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
