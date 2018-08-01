package com.hongwei.futures.web.action.admin_op_bank_card;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuBankCard;
import com.hongwei.futures.service.FuBankCardService;
import com.hongwei.futures.service.SysCityService;
import com.hongwei.futures.service.SysProvinceService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class adminOpBankCardAction extends BaseAction {
	@Autowired
	private FuBankCardService fuBankCardService;
	@Autowired
	private SysProvinceService sysProvinceService;
	@Autowired
	private SysCityService sysCityService;

	private FuAdmin admin;
	private Long id;
	private String bankBranchName;

	/**
	 * 修改银行卡
	 */
	@Action("updateCardAjax")
	public String updateCardAjax() {
		this.getActionContext().put("id", id);
		FuBankCard bankCard = fuBankCardService.get(id);
		this.getActionContext().put("bankCard", bankCard);
		return SUCCESS;
	}

	/**
	 * 修改银行卡信息
	 * 
	 * @return
	 */
	@Action("saveBankCard")
	public String saveBankCard() {
		try {
			FuBankCard card = fuBankCardService.get(id);
			card.setBankBranchName(bankBranchName);
			String str = sysProvinceService.get(card.getBankProvince()).getProvinceName();
			str = str + sysCityService.get(card.getBankCity()).getCityName();
			if (bankBranchName != null || bankBranchName != "") {
				str = str + bankBranchName;
			}
			card.setBankAddress(str);
			fuBankCardService.save(card);
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBankBranchName() {
		return bankBranchName;
	}

	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}

}
