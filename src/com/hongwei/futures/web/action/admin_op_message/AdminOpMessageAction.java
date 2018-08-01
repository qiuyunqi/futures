package com.hongwei.futures.web.action.admin_op_message;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuMessage;
import com.hongwei.futures.service.FuMessageService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminOpMessageAction extends BaseAction {
	@Autowired
	private FuMessageService fuMessageService;

	private FuAdmin admin;
	private Long adminId;

	private Long id;
	private String replyContent;
	private String messageRemark;

	/**
	 * 回复留言
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveReplyMessageAjax")
	public String saveReplyMessageAjax() throws Exception {
		try {
			if (StringUtil.isBlank(replyContent)) {
				write("-2");// 请输入回复内容
				return null;
			}
			FuMessage message = fuMessageService.get(id);
			message.setFuAdmin(admin);
			message.setReplyContent(replyContent);
			message.setReplyMark(messageRemark);
			message.setReplyTime(new Date());
			fuMessageService.save(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 回复页面
	 * 
	 * @return
	 */
	@Action("messageReplyAjax")
	public String messageReplyAjax() {
		try {
			FuMessage message = fuMessageService.get(id);
			this.getActionContext().put("message", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 删除留言信息
	 * 
	 * @return
	 */
	@Action("delMessageAjax")
	public String delMessageAjax() {
		try {
			FuMessage message = fuMessageService.get(id);
			message.setState(0);
			fuMessageService.save(message);
		} catch (Exception e) {
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getMessageRemark() {
		return messageRemark;
	}

	public void setMessageRemark(String messageRemark) {
		this.messageRemark = messageRemark;
	}

}
