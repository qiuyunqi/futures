package com.hongwei.futures.web.action.user_message;

import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuMessage;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuMessageService;
import com.hongwei.futures.service.FuSmsLogService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("fu_common")
public class UserMessageAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6839010643684902723L;
	@Autowired
	private FuMessageService fuMessageService;
	@Autowired
	private FuSmsLogService fuSmsLogService;

	private FuUser fuUser;
	private Long userId;

	private Long id;
	private Integer totalCount;

	private Integer type;// 1短信投资，2股票配资，3期货大赛，4功能使用，5其他
	private String content;

	/**
	 * 在线留言
	 * 
	 * @return
	 */
	@Action("messageInfo")
	public String messageInfo() {
		if (totalCount == null) {
			totalCount = fuMessageService.countMessage(userId);
		}
		List<FuMessage> messageList = fuMessageService.findMessageList((this.getPageNo() - 1) * this.getPageSize(), this.getPageSize(), userId);
		this.getActionContext().put("messageList", messageList);

		this.getActionContext().put("jsptype", "messageInfo");
		return SUCCESS;
	}

	/**
	 * 提交留言信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveMessageAjax")
	public String saveMessageAjax() {
		try {
			if (type == null) {
				write("-2");// 请选择留言类型
				return null;
			}
			if (StringUtil.isBlank(content)) {
				write("-3"); // 请输入留言内容
				return null;
			}
			FuMessage message = new FuMessage();
			message.setContent(content);
			message.setFuUser(fuUser);
			message.setState(0);
			message.setTime(new Date());
			message.setType(type);
			fuMessageService.save(message);
			// fuSmsLogService.saveSendServiceEmail("客户留言", "客户"+fuUser.getUserName()+"留言");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 留言分页
	 * 
	 * @return
	 */
	@Action("messageAjax")
	public String messageAjax() {
		try {
			if (totalCount == null)
				totalCount = fuMessageService.countMessage(userId);
			List<FuMessage> messageList = fuMessageService.findMessageList((this.getPageNo() - 1) * this.getPageSize(), this.getPageSize(), userId);
			this.getActionContext().put("messageList", messageList);
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

	public FuUser getFuUser() {
		return fuUser;
	}

	public void setFuUser(FuUser fuUser) {
		this.fuUser = fuUser;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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
