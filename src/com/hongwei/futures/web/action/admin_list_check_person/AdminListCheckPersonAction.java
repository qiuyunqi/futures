package com.hongwei.futures.web.action.admin_list_check_person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminListCheckPersonAction extends BaseAction {
	private static final long serialVersionUID = 1207014146467209780L;

	@Autowired
	private FuUserService fuUserService;
	
	private FuAdmin admin;
	private Long adminId;
	private Long id;
	private Integer totalCount;
	
	private String accountName;
	private String userName;
	private String cardNumber;
	
	/**
	 * 身份证验证列表
	 * @return
	 */
	@Action("checkPersonList")
	public String checkPersonList(){
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			if(!StringUtil.isBlank(accountName)){
				map.put("accountName", accountName);
			}
			if(!StringUtil.isBlank(userName)){
				map.put("userName", userName);
			}
			if(!StringUtil.isBlank(cardNumber)){
				map.put("cardNumber", cardNumber);
			}
			if(totalCount==null)
				totalCount=fuUserService.countCheckUser(map);
			List<FuUser> checkUserList = fuUserService.findCheckUserList((this.getPageNo()-1)*this.getPageSize(),this.getPageSize(),map);
			this.getActionContext().put("checkUserList", checkUserList);
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
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	
	
	
	
	
}
