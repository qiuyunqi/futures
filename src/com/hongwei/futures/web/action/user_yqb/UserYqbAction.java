package com.hongwei.futures.web.action.user_yqb;

import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuIndexData;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuIndexDataService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("fu_common")
public class UserYqbAction extends BaseAction {
	private static final long serialVersionUID = -4066456208158538375L;

	@Autowired
	private FuIndexDataService fuIndexDataService;

	private FuUser fuUser;

	/**
	 * 收益排行
	 * 
	 * @return
	 */
	@Action("incomeChart")
	public String incomeChart() {
		try {
			List<FuIndexData> list = fuIndexDataService.findTop10();
			this.getActionContext().put("list", list);
			this.getActionContext().put("now", new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 更多详情
	 * 
	 * @return
	 */
	@Action("moreInfo")
	public String moreInfo() {
		return SUCCESS;
	}

	@Action("yqbInfo")
	public String yqbInfo() {
		this.getActionContext().put("jsptype", "yqbInfo");
		return SUCCESS;
	}

	public FuUser getFuUser() {
		return fuUser;
	}

	public void setFuUser(FuUser fuUser) {
		this.fuUser = fuUser;
	}

}
