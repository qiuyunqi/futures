package com.hongwei.futures.web.action.index_options;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("fu_common")
public class IndexOptionsAction extends BaseAction {
	private FuUser fuUser;

	/**
	 * 微期权宣传
	 * 
	 * @return
	 */
	@Action("optionSolicitation")
	public String optionSolicitation() {
		this.getActionContext().put("jsptype", "optionSolicitation");
		return SUCCESS;
	}

	public FuUser getFuUser() {
		return fuUser;
	}

	public void setFuUser(FuUser fuUser) {
		this.fuUser = fuUser;
	}

}
