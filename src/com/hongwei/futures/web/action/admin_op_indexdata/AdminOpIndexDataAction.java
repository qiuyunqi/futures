package com.hongwei.futures.web.action.admin_op_indexdata;

import java.math.BigDecimal;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuIndexData;
import com.hongwei.futures.service.FuIndexDataService;
import com.hongwei.futures.util.StringUtil;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminOpIndexDataAction extends BaseAction {
	@Autowired
	private FuIndexDataService fuIndexDataService;

	private Long id;
	private String userName;
	private Integer transactionType;
	private String transactionObject;
	private Integer transactionNum;
	private BigDecimal applyMoney;
	private BigDecimal getMoney;
	private Integer getGain;
	private Integer isDel;

	/**
	 * 删除数据
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("delIndexDataListAjax")
	public String delIndexDataListAjax() {
		try {
			FuIndexData fuIndexData = fuIndexDataService.get(id);
			fuIndexData.setIsDel(0);
			fuIndexDataService.save(fuIndexData);
			write("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 显示添加窗口
	 * 
	 * @return
	 */
	@Action("newAddIndexDataAjax")
	public String newAddIndexDataAjax() {
		return SUCCESS;
	}

	/**
	 * 添加数据
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("addIndexData")
	public String addIndexData() {
		try {
			if (StringUtil.isBlank(userName)) {
				write("-2");
				return null;
			}
			if (transactionType == null) {
				write("-3");
				return null;
			}
			if (StringUtil.isBlank(transactionObject)) {
				write("-4");
				return null;
			}
			if (transactionNum == null || applyMoney == null
					|| getMoney == null) {
				if (transactionType == 1 && transactionNum == null) {
					write("-5");
					return null;
				}
				if (transactionType == 2 && applyMoney == null) {
					write("-6");
					return null;
				}
				if (transactionType == 3) {
					if (getMoney == null) {
						write("-7");
						return null;
					}
					if (getGain == null) {
						write("-8");
						return null;
					}
				}
			}
			FuIndexData fuIndexData = new FuIndexData();
			fuIndexData.setGetMoney(getMoney);
			fuIndexData.setApplyMoney(applyMoney);
			fuIndexData.setIsDel(1);
			fuIndexData.setTransactionNum(transactionNum);
			fuIndexData.setUserName(userName);
			fuIndexData.setTransactionType(transactionType);
			fuIndexData.setTransactionObject(transactionObject);
			fuIndexData.setGetGain(getGain);
			fuIndexDataService.save(fuIndexData);
			write("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(Integer transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransactionObject() {
		return transactionObject;
	}

	public void setTransactionObject(String transactionObject) {
		this.transactionObject = transactionObject;
	}

	public Integer getTransactionNum() {
		return transactionNum;
	}

	public void setTransactionNum(Integer transactionNum) {
		this.transactionNum = transactionNum;
	}

	public BigDecimal getApplyMoney() {
		return applyMoney;
	}

	public void setApplyMoney(BigDecimal applyMoney) {
		this.applyMoney = applyMoney;
	}

	public BigDecimal getGetMoney() {
		return getMoney;
	}

	public void setGetMoney(BigDecimal getMoney) {
		this.getMoney = getMoney;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Integer getGetGain() {
		return getGain;
	}

	public void setGetGain(Integer getGain) {
		this.getGain = getGain;
	}

}
