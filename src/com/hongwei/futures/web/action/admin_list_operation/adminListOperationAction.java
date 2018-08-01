package com.hongwei.futures.web.action.admin_list_operation;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuOperation;
import com.hongwei.futures.service.FuOperationService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class adminListOperationAction extends BaseAction {
	@Autowired
	private FuOperationService fuOperationService;

	private FuAdmin admin;
	private String modelMain;
	private String modelSub;
	private Integer operation;
	private Long opId;
	private Date time1;
	private Date time2;
	private Integer totalCount;

	/**
	 * 管理员操作记录
	 * 
	 * @return
	 */
	@Action("operationList")
	public String operationList() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtil.isBlank(modelMain)) {
				map.put("modelMain", modelMain);
			}
			if (!StringUtil.isBlank(modelSub)) {
				map.put("modelSub", modelSub);
			}
			if (operation != null) {
				map.put("operation", operation);
			}
			if (opId != null) {
				map.put("opId", opId);
			}
			if (time1 != null) {
				map.put("time1", time1);
			}
			if (time2 != null) {
				map.put("time2", time2);
			}
			if (totalCount == null) {
				totalCount = fuOperationService.countOperation(map);
			}
			List<FuOperation> operationList = fuOperationService
					.findOperationlist(
							(this.getPageNo() - 1) * this.getPageSize(),
							this.getPageSize(), map);
			this.getActionContext().put("operationList", operationList);
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

	public String getModelMain() {
		return modelMain;
	}

	public void setModelMain(String modelMain) {
		this.modelMain = modelMain;
	}

	public String getModelSub() {
		return modelSub;
	}

	public void setModelSub(String modelSub) {
		this.modelSub = modelSub;
	}

	public Integer getOperation() {
		return operation;
	}

	public void setOperation(Integer operation) {
		this.operation = operation;
	}

	public Long getOpId() {
		return opId;
	}

	public void setOpId(Long opId) {
		this.opId = opId;
	}

	public Date getTime1() {
		return time1;
	}

	public void setTime1(Date time1) {
		this.time1 = time1;
	}

	public Date getTime2() {
		return time2;
	}

	public void setTime2(Date time2) {
		this.time2 = time2;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

}
