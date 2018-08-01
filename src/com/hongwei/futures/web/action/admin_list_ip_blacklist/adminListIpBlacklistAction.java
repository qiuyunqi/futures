package com.hongwei.futures.web.action.admin_list_ip_blacklist;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuIpBlacklist;
import com.hongwei.futures.service.FuIpBlacklistService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class adminListIpBlacklistAction extends BaseAction {
	@Autowired
	private FuIpBlacklistService fuIpBlacklistService;

	private FuAdmin admin;
	private Long adminId;
	private Long id;
	private String ip;
	private Integer isBlack;
	private Integer totalCount;

	@Action("ipBlackList")
	public String ipBlackList() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtil.isBlank(ip)) {
				map.put("ip", ip);
			}
			if (isBlack != null) {
				map.put("isBlack", isBlack);
			}
			if (totalCount == null)
				totalCount = fuIpBlacklistService.countIpBlack(map);
			List<FuIpBlacklist> IpBlackList = fuIpBlacklistService
					.findIpBlackList(
							(this.getPageNo() - 1) * this.getPageSize(),
							this.getPageSize(), map);
			this.getActionContext().put("IpBlackList", IpBlackList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 添加修改黑名单
	 * 
	 * @return
	 */
	@Action("newIpBlackAjax")
	public String newIpBlackAjax() {
		if (id != null) {
			FuIpBlacklist ipBlacklist = fuIpBlacklistService.get(id);
			this.getActionContext().put("ipBlacklist", ipBlacklist);
		}
		return SUCCESS;
	}

	/**
	 * 保存黑名单
	 * 
	 * @return
	 */
	@Action("saveIpBlackAjax")
	public String saveIpBlackAjax() {
		if (id != null) {
			FuIpBlacklist ipBlacklist = fuIpBlacklistService.get(id);
			ipBlacklist.setIp(ip);
			ipBlacklist.setIsBlack(isBlack);
			ipBlacklist.setUpdateAdmin(admin);
			ipBlacklist.setUpdateTime(new Date());
			//设为白名单时,重置登录错误次数和禁止登录时间
			if(isBlack == 0){
				admin.setLoginErrorTimes(0);
				admin.setForbidLoginTime(null);
			}
			fuIpBlacklistService.save(ipBlacklist);
		} else {
			FuIpBlacklist ipBlacklist = new FuIpBlacklist();
			ipBlacklist.setIp(ip);
			ipBlacklist.setIsBlack(isBlack);
			ipBlacklist.setCreatAdmin(admin);
			ipBlacklist.setCreateTime(new Date());
			fuIpBlacklistService.save(ipBlacklist);
		}
		return null;
	}

	public FuAdmin getAdmin() {
		return admin;
	}

	public void setAdmin(FuAdmin admin) {
		this.admin = admin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getIsBlack() {
		return isBlack;
	}

	public void setIsBlack(Integer isBlack) {
		this.isBlack = isBlack;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

}
