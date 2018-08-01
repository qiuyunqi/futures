package com.hongwei.futures.web.action.admin_list_bank_card;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuBankCard;
import com.hongwei.futures.service.FuBankCardService;
import com.hongwei.futures.util.StringUtil;
import com.hongwei.futures.web.action.BaseAction;

/* @author 熊枫
 * 2015-05-21
 * */
@ParentPackage("admin")
public class AdminListBankCardAction extends BaseAction {
	@Autowired
	private FuBankCardService fuBankCardService;

	private FuAdmin admin;
	private String accountName;
	private String cardNumber;
	private Integer totalCount;

	/**
	 * 银行卡查询
	 * 
	 * @return
	 */
	@Action("bankcardQuery")
	public String bankcardQuery() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtil.isBlank(accountName)) {
				map.put("accountName", accountName);
			}
			if (!StringUtil.isBlank(cardNumber)) {
				map.put("cardNumber", cardNumber);
			}
			List<FuBankCard> bankCardList = fuBankCardService.queryFuBankCardList((this.getPageNo() - 1) * this.getPageSize(), this.getPageSize(), map);
			if (totalCount == null) {
				totalCount = fuBankCardService.countFuBankCard(map);
			}
			this.getActionContext().put("bankCardList", bankCardList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public FuAdmin getAdmin() {
		return admin;
	}

	public void setAdmin(FuAdmin admin) {
		this.admin = admin;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
}
