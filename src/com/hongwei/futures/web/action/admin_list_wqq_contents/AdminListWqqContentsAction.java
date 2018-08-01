package com.hongwei.futures.web.action.admin_list_wqq_contents;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.WqqContents;
import com.hongwei.futures.service.WqqContentsService;
import com.hongwei.futures.util.StringUtil;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminListWqqContentsAction extends BaseAction {
	@Autowired
	private WqqContentsService wqqContentsService;

	private Long id;
	private FuAdmin admin;
	private Integer totalCount;
	private Integer state;
	private String name;
	private Date createTime1;
	private Date createTime2;

	@Action("wqqContentsList")
	public String wqqContentsList() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtil.isBlank(name)) {
				map.put("name", name);
			}
			if (createTime1 != null) {
				map.put("createTime1", createTime1);
			}
			if (createTime2 != null) {
				map.put("createTime2", createTime2);
			}
			if (state != null) {
				map.put("state", state);
			}
			if (totalCount == null)
				totalCount = wqqContentsService.countContents(map);
			List<WqqContents> wqqContentslist = wqqContentsService
					.findContentsByMap(
							(this.getPageNo() - 1) * this.getPageSize(),
							this.getPageSize(), map);
			this.getActionContext().put("wqqContentslist", wqqContentslist);
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime1() {
		return createTime1;
	}

	public void setCreateTime1(Date createTime1) {
		this.createTime1 = createTime1;
	}

	public Date getCreateTime2() {
		return createTime2;
	}

	public void setCreateTime2(Date createTime2) {
		this.createTime2 = createTime2;
	}

}
