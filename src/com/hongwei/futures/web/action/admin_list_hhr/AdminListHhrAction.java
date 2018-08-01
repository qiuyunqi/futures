package com.hongwei.futures.web.action.admin_list_hhr;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminListHhrAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9047205415267967662L;

	@Autowired
	private FuUserService fuUserService;

	private FuAdmin admin;
	private Long adminId;
	private Long id;
	private Integer totalCount;

	private Long userId;
	private String accountName;
	private String userName;
	private String phone;
	private String cardNumber;

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	@Action("hhrList")
	public String hhrList() {
		return SUCCESS;
	}

	/**
	 * 组织用户树形结构
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("hhrTree")
	public String hhrTree() {
		try {
			String id = getHttpServletRequest().getParameter("id");
			Long longId = 0L;
			if (null == id) {
				longId = 0L;
			} else {
				longId = Long.valueOf(id);
			}
			String res = fuUserService.findUserTree(longId);
			if (null == res) {
				res = "";
			}
			write(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
