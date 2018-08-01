package com.hongwei.futures.web.action.user_draw_money;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.json.JSONObject;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuBankCard;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.SysBank;
import com.hongwei.futures.model.SysProvince;
import com.hongwei.futures.service.FuBankCardService;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.service.SysBankService;
import com.hongwei.futures.service.SysCityService;
import com.hongwei.futures.service.SysProvinceService;
import com.hongwei.futures.util.CommonUtil;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("fu_common")
public class UserBankAction extends BaseAction {
	private static final long serialVersionUID = 230192952240203543L;
	
	@Autowired
	private FuBankCardService fuBankCardService;
	@Autowired
	private SysProvinceService sysProvinceService;
	@Autowired
	private SysCityService sysCityService;
	@Autowired
	private SysBankService sysBankService;
	@Autowired
	private FuUserService fuUserService;

	private FuUser fuUser;
	private Long userId;
	private Long id;
	private Long bankId;
	private Long provinceId;
	private Long cityId;
	private Long branchId;
	private String cardNumber;
	private String reCardNumber;
	private String drawPassword;
	private String bankBranchName;

	/**
	 * 银行卡管理
	 * 
	 * @return
	 */
	@Action("bankManager")
	public String bankManager() {
		try {
			List<FuBankCard> bankCardList = fuBankCardService.findListByUser(userId);
			this.getActionContext().put("bankCardList", bankCardList);
			this.getActionContext().put("jsptype", "bankManager");

			List<SysProvince> provinceList = sysProvinceService.findAllProvince();
			this.getActionContext().put("provinceList", provinceList);
			List<SysBank> bankList = sysBankService.findAllBank();
			this.getActionContext().put("bankList", bankList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 添加修改银行卡
	 * 
	 * @return
	 */
	@Action("bankCardInfo")
	public String bankCardInfo() {
		try {
			if (fuUser.getIsCheckCard() == null || fuUser.getIsCheckCard() != 2) {
				write("-1");// 请先进行实名认证后，再进行操作
				return null;
			}
			JSONObject obj = new JSONObject();
			List<SysProvince> provinceList = sysProvinceService.findAllProvince();
			obj.put("provinceList", provinceList);
			List<SysBank> bankList = sysBankService.findAllBank();
			obj.put("bankList", bankList);
			if (id != null) {
				FuBankCard card = fuBankCardService.get(id);
				obj.put("cardNumber", card.getCardNumber());
				obj.put("bankNameId", card.getBankNameId());
				obj.put("bankProvince", card.getBankProvince());
				obj.put("bankCity", card.getBankCity());
				obj.put("bankBranchName", card.getBankBranchName());
				obj.put("bankId", card.getId());
			}
			write(obj.toString());
			// this.getActionContext().put("jsptype", "bankCardInfo");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 保存银行卡信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveBankCardAjax")
	public String saveBankCardAjax() {
		try {
			if (bankId == null) {
				write("-2");// 请选择银行
				return null;
			}
			if (provinceId == null) {
				write("-3");// 请选择省份
				return null;
			}
			if (cityId == null) {// 请选择市
				write("-4");
				return null;
			}
			if (StringUtil.isBlank(bankBranchName)) {
				write("-5");// 请选择营业网点
				return null;
			}
			if (StringUtil.isBlank(cardNumber)) {
				write("-6");
				return null;// 请输入银行卡卡号
			}
			if (!cardNumber.equals(reCardNumber)) {
				write("-7");// 两次输入的卡号不一致
				return null;
			}
			FuBankCard card = fuBankCardService.findCardByNumber(userId, cardNumber);
			if (id == null && card != null) {
				write("-8");// 该银行卡已经绑定了
				return null;
			}
			if (StringUtil.isBlank(fuUser.getDrawPassword())) {
				if (StringUtil.isBlank(drawPassword)) {
					write("-9");
					return null;
				}
				fuUser.setDrawPassword(CommonUtil.getMd5(drawPassword));
				fuUserService.save(fuUser);
			}
			if (id != null) {
				card = fuBankCardService.get(id);
			}
			if (card == null) {
				card = new FuBankCard();
				card.setState(1);
				card.setUserId(userId);
			}
			card.setAccountName(fuUser.getUserName());
			// card.setBankBranch(branchId);
			card.setBankBranchName(bankBranchName);
			card.setBankCity(cityId);
			card.setBankName(sysBankService.get(bankId).getBankName());
			card.setBankNameId(bankId);
			card.setBankProvince(provinceId);
			card.setCardNumber(cardNumber);
			String str = sysProvinceService.get(provinceId).getProvinceName();
			str = str + sysCityService.get(cityId).getCityName();
			// if(branchId!=null){
			// str=str+sysBranchService.get(branchId).getBranchName();
			// }
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

	/**
	 * 删除银行卡
	 * 
	 * @return
	 */
	@Action("delCardAjax")
	public String delCardAjax() {
		try {
			FuBankCard card = fuBankCardService.get(id);
			card.setState(0);
			fuBankCardService.save(card);
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

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getReCardNumber() {
		return reCardNumber;
	}

	public void setReCardNumber(String reCardNumber) {
		this.reCardNumber = reCardNumber;
	}

	public Long getBankId() {
		return bankId;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	public String getDrawPassword() {
		return drawPassword;
	}

	public void setDrawPassword(String drawPassword) {
		this.drawPassword = drawPassword;
	}

	public String getBankBranchName() {
		return bankBranchName;
	}

	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}

}
