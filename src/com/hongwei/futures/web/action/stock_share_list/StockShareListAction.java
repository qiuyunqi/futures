package com.hongwei.futures.web.action.stock_share_list;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.StockShare;
import com.hongwei.futures.service.StockShareService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class StockShareListAction extends BaseAction {
	private static final long serialVersionUID = 1058143837396685919L;

	@Autowired
	private StockShareService stockShareService;

	private FuAdmin admin;
	private Long adminId;
	private Integer totalCount;

	private String shareName;
	private String shareCode;
	private String capitalAccount;

	/**
	 * 股票查询
	 * @return
	 */
	@Action("shareList")
	public String shareList() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtil.isBlank(shareName)) {
				map.put("shareName", shareName);
			}
			if (!StringUtil.isBlank(shareCode)) {
				map.put("shareCode", shareCode);
			}
			if (!StringUtil.isBlank(capitalAccount)) {
				map.put("capitalAccount", capitalAccount);
			}
			List<StockShare> shareList = stockShareService.queryStockShareList((this.getPageNo() - 1) * this.getPageSize(), this.getPageSize(), map);
			if (totalCount == null) {
				totalCount = stockShareService.countStockShare(map);
			}
			this.getActionContext().put("shareList", shareList);
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

	public String getShareName() {
		return shareName;
	}

	public void setShareName(String shareName) {
		this.shareName = shareName;
	}

	public String getShareCode() {
		return shareCode;
	}

	public void setShareCode(String shareCode) {
		this.shareCode = shareCode;
	}

	public String getCapitalAccount() {
		return capitalAccount;
	}

	public void setCapitalAccount(String capitalAccount) {
		this.capitalAccount = capitalAccount;
	}
	
}
