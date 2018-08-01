package com.hongwei.futures.web.action.admin_list_quali_person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.HhrQualiRegister;
import com.hongwei.futures.service.HhrQualiRegisterService;
import com.hongwei.futures.util.StringUtil;
import com.hongwei.futures.web.action.BaseAction;

/* @author 熊枫
 * 2015-05-24
 * */
@ParentPackage("admin")
public class AdminListQualiPersonAction extends BaseAction {
	@Autowired
	private HhrQualiRegisterService qualiService;

	private FuAdmin admin;

	private Long id;

	private FuUser fuUser;

	// 真实姓名
	private String userName;

	// 资格证号
	private String qualiNum;

	private String qualiPic;

	private Integer flag;

	// 总记录数
	private Integer totalCount;

	/**
	 * 资格认证
	 * 
	 * @return
	 */
	@Action("qualiPersonList")
	public String qualiPersonList() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtil.isBlank(qualiNum)) {
				map.put("qualiNum", qualiNum);
			}
			if (!StringUtil.isBlank(userName)) {
				map.put("userName", userName);
			}
			List<HhrQualiRegister> qualiList = qualiService.queryQualiList(
					(this.getPageNo() - 1) * this.getPageSize(),
					this.getPageSize(), map);
			if (totalCount == null) {
				totalCount = qualiService.countQuali(map);
			}
			this.getActionContext().put("qualiList", qualiList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 查看资格认证信息
	 * 
	 * @return
	 */
	@Action("qualiPersonAjax")
	public String qualiPersonAjax() {
		try {
			HhrQualiRegister quali = qualiService.get(id);
			this.getActionContext().put("quali", quali);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 保存资格认证信息
	 * 
	 * @return
	 */
	@Action("saveQualiInfoAjax")
	public String saveQualiInfoAjax() {
		try {
			HhrQualiRegister quali = qualiService.get(id);
			quali.setIsChecked(flag);
			qualiService.save(quali);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public FuAdmin getAdmin() {
		return admin;
	}

	public void setAdmin(FuAdmin admin) {
		this.admin = admin;
	}

	public String getQualiNum() {
		return qualiNum;
	}

	public void setQualiNum(String qualiNum) {
		this.qualiNum = qualiNum;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FuUser getFuUser() {
		return fuUser;
	}

	public void setFuUser(FuUser fuUser) {
		this.fuUser = fuUser;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getQualiPic() {
		return qualiPic;
	}

	public void setQualiPic(String qualiPic) {
		this.qualiPic = qualiPic;
	}

}
