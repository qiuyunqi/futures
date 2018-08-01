package com.hongwei.futures.web.action.admin_op_continue;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuContinueContract;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuContinueContractService;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminOpContinueAction extends BaseAction {
	private static final long serialVersionUID = -6454986280708425430L;
	
	@Autowired
	private FuContinueContractService fuContinueContractService;
	@Autowired
	private FuUserService fuUserService;

	private FuAdmin admin;
	private Long adminId;
	private Long id;
	private Integer result;
	private String comment;
	private String msg;

	/**
	 * 续约审核页面（后台）
	 * 
	 * @return
	 */
	@Action("checkContinueAjax")
	public String checkContinueAjax() {
		try {
			FuContinueContract con = fuContinueContractService.get(id);
			con.setResult(1);// 审核中
			con.setFuAdmin(admin);
			fuContinueContractService.save(con);
			this.getActionContext().put("con", con);
			FuUser user = fuUserService.get(con.getFuUser().getId());
			this.getActionContext().put("user", user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 取消续约审核(后台)
	 * 
	 * @return
	 */
	@Action("noCheckContinueAjax")
	public String noCheckContinueAjax() {
		try {
			FuContinueContract con = fuContinueContractService.get(id);
			con.setResult(0);// 待审核
			con.setFuAdmin(null);
			fuContinueContractService.save(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 保存续约记录
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveContinueAjax")
	public String saveContinueAjax() throws Exception {
		try {
			if (result == null) {
				write("-2");
				return null;
			}
			FuContinueContract con = fuContinueContractService.get(id);
			con.setCheckTime(new Date());
			con.setFuAdmin(admin);
			con.setResult(result);
			con.setComment(comment);
			fuContinueContractService.saveContAndPro(con, msg);
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

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
