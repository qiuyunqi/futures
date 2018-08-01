package com.hongwei.futures.web.action.admin_op_wxyqb;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuTransaction;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.StockPublish;
import com.hongwei.futures.service.FuTransactionService;
import com.hongwei.futures.service.StockPublishService;
import com.hongwei.futures.web.action.BaseAction;
import com.hongwei.futures.web.action.admin_list_wxyqb.AdminListWxyqbAction;

public class AdminOpWxyqbAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8403093556300189457L;

	private static final Log logger = LogFactory.getLog(AdminListWxyqbAction.class);
	@Autowired
	private FuTransactionService fuTransactionService;
	@Autowired
	private StockPublishService stockPublishService;

	private Long id;
	private String name;
	private Integer isDel;
	private Integer isVerification;
	private Integer rating;
	private String title;
	private String description;

	/**
	 * 修改交易员
	 * 
	 * @return
	 */
	@Action("updateTransactionAjax")
	public String updateTransactionAjax() {
		if (id != null) {
			FuTransaction transaction = fuTransactionService.get(id);
			this.getActionContext().put("transac", transaction);
		}
		return SUCCESS;
	}

	/**
	 * 保存修改交易员
	 * 
	 * @return
	 */
	@Action("saveUpdateTransac")
	public String saveUpdateTransac() {
		try {
			FuTransaction transaction = fuTransactionService.get(id);
			transaction.setName(name);
			transaction.setIsDel(isDel);
			transaction.setIsVerification(isVerification);
			transaction.setRating(rating);
			FuUser fuUser = transaction.getFuUser();
			fuUser.setIsTransaction(isVerification == 1 ? 1 : 0);//交易员的审核通过才能设为1，其余设为0
			fuTransactionService.saveTransc(transaction, transaction.getFuUser());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}

	/**
	 * 添加修改找劵
	 * 
	 * @return
	 */
	@Action("addPublishAjax")
	public String addPublishAjax() {
		if (id != null) {
			StockPublish stockPublish = stockPublishService.get(id);
			this.getActionContext().put("stockPublish", stockPublish);
		}
		return SUCCESS;
	}

	/**
	 * 保存添加修改找劵
	 * 
	 * @return
	 */
	@Action("saveAddPublish")
	public String saveAddPublish() {
		try {
			StockPublish stockPublish = stockPublishService.get(id);
			stockPublish.setTitle(title);
			stockPublish.setDescription(description);
			stockPublish.setIsDel(isDel);
			stockPublishService.save(stockPublish);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Integer getIsVerification() {
		return isVerification;
	}

	public void setIsVerification(Integer isVerification) {
		this.isVerification = isVerification;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
