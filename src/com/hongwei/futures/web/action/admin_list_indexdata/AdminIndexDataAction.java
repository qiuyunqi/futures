package com.hongwei.futures.web.action.admin_list_indexdata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuIndexData;
import com.hongwei.futures.service.FuIndexDataService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminIndexDataAction extends BaseAction {
	@Autowired
	private FuIndexDataService fuIndexDataService;

	private FuAdmin admin;
	private Integer totalCount;
	private Integer id;

	/**
	 * 收益排行数据
	 * 
	 * @return
	 */
	@Action("IndexDataList")
	public String IndexDataList() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (id != null) {
				map.put("id", id);
			}
			if (totalCount == null) {
				totalCount = fuIndexDataService.getCount(map);
			}
			List<FuIndexData> list = fuIndexDataService.findListBy(
					(this.getPageNo() - 1) * this.getPageSize(),
					this.getPageSize(), map);
			this.getActionContext().put("FuIndexData", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

}
