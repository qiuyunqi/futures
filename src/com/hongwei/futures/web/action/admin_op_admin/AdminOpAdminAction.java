package com.hongwei.futures.web.action.admin_op_admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.SysRole;
import com.hongwei.futures.model.SysUserRole;
import com.hongwei.futures.service.FuAdminService;
import com.hongwei.futures.service.SysRoleService;
import com.hongwei.futures.service.SysUserRoleService;
import com.hongwei.futures.util.CommonUtil;
import com.hongwei.futures.util.StringUtil;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminOpAdminAction extends BaseAction {
	@Autowired
	private FuAdminService adminService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysUserRoleService sysUserRoleService;

	private FuAdmin admin;
	private Long adminId;
	private Long id;
	private String account;
	private String password;
	private String confirmPassword;
	private Integer type;
	private String name;
	private String jobDesc;
	private String[] kf;// 客服
	private String[] yh;// 用户
	private String[] jd;// 借贷
	private Long roleId;
	private String rolename;
	private String email;
	private String phone;
	private Integer loginErrorTimes;
	private Date forbidLoginTime;

	/**
	 * 新增用户
	 * 
	 * @return
	 */
	@Action("newAdminAjax")
	public String newAdmin() {
		try {
			if (id != null) {
				FuAdmin fuAdmin = adminService.get(id);
				this.getActionContext().put("fuAdmin", fuAdmin);
				JSONObject obj = new JSONObject();
				if (fuAdmin.getRageConfig() != null) {
					obj = JSONObject.fromObject(fuAdmin.getRageConfig());
					this.getActionContext().put("rageConfig", obj);
				}
			}
			Map<String, Object> map = new HashMap<String, Object>();
			List<SysRole> roleList = sysRoleService.findRoleListByMap(map);
			this.getActionContext().put("roleList", roleList);
			this.getActionContext().put("rolename", rolename);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 保存用户
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveAdminAjax")
	public String saveAdmin() {
		try {
			FuAdmin fuAdmin = new FuAdmin();
			if (id != null) {// 修改
				fuAdmin = adminService.get(id);
			}
			if (id == null) {// 新增
				fuAdmin.setState(1);
				fuAdmin.setCreateTime(new Date());
				fuAdmin.setType(0);// 新增时默认是普通管理员
				fuAdmin.setLoginErrorTimes(0);
			}
			if (StringUtil.isBlank(account)) {
				write("-2");
				return null;
			} else if (id == null
					&& this.adminService.findAdminByAccount(account) != null) {
				write("-3");
				return null;
			}
			if (StringUtil.isBlank(password)) {
				write("-4");
				return null;
			} else {
				if (id == null
						|| (id != null && !password.equals(fuAdmin
								.getPassword()))) {// 修改密码或者新增密码
					if (StringUtil.isBlank(confirmPassword)) {
						write("-5");
						return null;
					}
					if (!password.equals(confirmPassword)) {
						write("-6");
						return null;
					}
					fuAdmin.setPassword(CommonUtil.getMd5(password));
				} else {
					fuAdmin.setPassword(password);
				}
			}
			if (StringUtil.isBlank(name)) {
				write("-7");
				return null;
			}
			if (StringUtil.isBlank(jobDesc)) {
				write("-8");
				return null;
			}
			if (StringUtil.isBlank(email)) {
				write("-9");
				return null;
			}
			/*
			 * JSONObject rageConfig = new JSONObject(); JSONObject kfObject =
			 * new JSONObject(); JSONObject yhObject = new JSONObject();
			 * JSONObject jdObject = new JSONObject(); if(kf!=null){ for(int
			 * i=0;i<kf.length;i++){ kfObject.put("kf"+kf[i],kf[i]); }
			 * rageConfig.put("kf",kfObject); } if(yh!=null){ for(int
			 * i=0;i<yh.length;i++){ yhObject.put("yh"+yh[i],yh[i]); }
			 * rageConfig.put("yh",yhObject); } if(jd!=null){ for(int
			 * i=0;i<jd.length;i++){ jdObject.put("jd"+jd[i],jd[i]); }
			 * rageConfig.put("jd",jdObject); }
			 */
			fuAdmin.setAccount(account);
			fuAdmin.setName(name);
			fuAdmin.setJobDesc(jobDesc);
			fuAdmin.setEmail(email);
			if (!StringUtil.isBlank(phone)) {
				fuAdmin.setPhone(phone);
			}
			fuAdmin.setLoginErrorTimes(loginErrorTimes);
			fuAdmin.setForbidLoginTime(forbidLoginTime);
			// fuAdmin.setRageConfig(rageConfig.toString());
			adminService.save(fuAdmin);
			if (id == null) {
				SysUserRole userRole = new SysUserRole();
				userRole.setSysRole(sysRoleService.get(roleId));
				userRole.setFuAdmin(fuAdmin);
				sysUserRoleService.save(userRole);
			} else {
				List<SysUserRole> userRoles = sysUserRoleService
						.findUserRole(id);// 根据管理员id查询用户角色中间表
				if (userRoles.size() > 0) {
					SysUserRole userRole = userRoles.get(0);
					userRole.setSysRole(sysRoleService.get(roleId));
					sysUserRoleService.save(userRole);
				}
			}
			write("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除用户
	 * 
	 * @return
	 */
	@Action("delAdminAjax")
	public String delAdmin() {
		if (id != null) {
			FuAdmin fuAdmin = adminService.get(id);
			fuAdmin.setState(0);
			adminService.save(fuAdmin);
		}
		return null;
	}

	/**
	 * 设为管理员
	 * 
	 * @return
	 */
	@Action("setAdminAjax")
	public String setAdmin() {
		if (id != null) {
			FuAdmin fuAdmin = adminService.get(id);
			fuAdmin.setType(type);
			adminService.save(fuAdmin);
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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJobDesc() {
		return jobDesc;
	}

	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	public String[] getKf() {
		return kf;
	}

	public void setKf(String[] kf) {
		this.kf = kf;
	}

	public String[] getYh() {
		return yh;
	}

	public void setYh(String[] yh) {
		this.yh = yh;
	}

	public String[] getJd() {
		return jd;
	}

	public void setJd(String[] jd) {
		this.jd = jd;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getLoginErrorTimes() {
		return loginErrorTimes;
	}

	public void setLoginErrorTimes(Integer loginErrorTimes) {
		this.loginErrorTimes = loginErrorTimes;
	}

	public Date getForbidLoginTime() {
		return forbidLoginTime;
	}

	public void setForbidLoginTime(Date forbidLoginTime) {
		this.forbidLoginTime = forbidLoginTime;
	}

}
