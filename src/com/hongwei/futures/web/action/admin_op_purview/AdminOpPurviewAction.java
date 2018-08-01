package com.hongwei.futures.web.action.admin_op_purview;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.SysPurview;
import com.hongwei.futures.model.SysRole;
import com.hongwei.futures.service.SysPurviewService;
import com.hongwei.futures.service.SysRolePurviewService;
import com.hongwei.futures.service.SysRoleService;
import com.hongwei.futures.util.StringUtil;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminOpPurviewAction extends BaseAction {
	private static final long serialVersionUID = 6683991956413606350L;

	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysPurviewService sysPurviewService;
	@Autowired
	private SysRolePurviewService sysRolePurviewService;

	private FuAdmin admin;
	private Long adminId;
	private Long parentId;
	private String roleCode;
	private String roleName;
	private String roleDesc;
	private Long roleId;
	private Long id;
	private Integer totalCount;
	private String name;
	private String url;
	private String remark;
	private String purviewArray;

	/**
	 * 根据当前后台用户查询他下面的所有角色
	 * 
	 * @return
	 */
	@Action("roleList")
	public String roleList() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			FuAdmin admin = (FuAdmin) getSession().get("admin");
			map.put("adminId", admin.getId());
			List<SysRole> roleList = sysRoleService.findRoleListByMap(map);
			this.getActionContext().put("roleList", roleList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 新增角色页面
	 */
	@Action("addRole")
	public String addRole() {
		try {
			List<SysRole> roleList = sysRoleService.findRoleListByMap(new HashMap<String, Object>());
			this.getActionContext().put("roleList", roleList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 保存角色
	 * 
	 * @return
	 */
	@Action("saveRoleAjax")
	public String saveRoleAjax() {
		try {
			if (StringUtil.isBlank(roleCode)) {
				write("-1");
				return null;
			}
			if (StringUtil.isBlank(roleName)) {
				write("-2");
				return null;
			}
			if (parentId == null) {
				write("-3");
				return null;
			}
			SysRole role = new SysRole();
			role.setRoleCode(roleCode);
			role.setRoleName(roleName);
			role.setRoleDesc(roleDesc);
			role.setParentId(parentId);
			role.setCreateAdmin((FuAdmin) getSession().get("admin"));
			role.setCreateTime(new Date());
			sysRoleService.save(role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 编辑角色页面
	 */
	@Action("editRole")
	public String editRole() {
		try {
			List<SysRole> roleList = sysRoleService.findRoleListByMap(new HashMap<String, Object>());
			SysRole role = sysRoleService.get(roleId);
			this.getActionContext().put("roleList", roleList);
			this.getActionContext().put("role", role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 保存编辑后的角色
	 * 
	 * @return
	 */
	@Action("editRoleAjax")
	public String editRoleAjax() {
		try {
			if (StringUtil.isBlank(roleCode)) {
				write("-1");
				return null;
			}
			if (StringUtil.isBlank(roleName)) {
				write("-2");
				return null;
			}
			if (parentId == null) {
				write("-3");
				return null;
			}
			SysRole role = sysRoleService.get(roleId);
			role.setRoleCode(roleCode);
			role.setRoleName(roleName);
			role.setRoleDesc(roleDesc);
			role.setParentId(parentId);
			role.setUpdateAdmin((FuAdmin) getSession().get("admin"));
			role.setUpdateTime(new Date());
			sysRoleService.save(role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 保存删除角色
	 * 
	 * @return
	 */
	@Action("delRoleAjax")
	public String delRoleAjax() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("parentId", roleId);
			List<SysRole> roles = sysRoleService.findRoleListByMap(map);// 查询当前角色是否有下级角色
			if (roles.size() > 0) {
				write("-1");
				return null;
			}
			sysRoleService.deleteRole(roleId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 设置权限页面
	 */
	@Action("setPurview")
	public String setPurview() {
		try {
			this.getActionContext().put("roleId", roleId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 权限树
	 * 
	 * @return
	 */
	@Action("treedata")
	public String treedata() {
		try {
			String jsonStr = sysRolePurviewService.findPurviewList(roleId);
			if (jsonStr == null) {
				jsonStr = "";
			}
			write(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 菜单管理
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("purviewList")
	public String purviewList() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("url", url);
			map.put("parentId", parentId);
			if (totalCount == null)
				totalCount = sysPurviewService.getCount(map);
			List<SysPurview> purviewList = sysPurviewService.findPurviewList((this.getPageNo() - 1) * this.getPageSize(), this.getPageSize(), map);
			List<SysPurview> list = sysPurviewService.findTopPrivilege();
			this.getActionContext().put("list", list);
			this.getActionContext().put("purviewList", purviewList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 新增或编辑菜单
	 * 
	 * @return
	 */
	@Action("editPurviewAjax")
	public String editPurviewAjax() {
		try {
			if (id != null) {// 修改
				SysPurview sysPurview = sysPurviewService.get(id);
				this.getActionContext().put("sysPurview", sysPurview);
				this.getActionContext().put("id", id);
			}
			if (parentId != null) {// 添加
				this.getActionContext().put("parentId", parentId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 保存菜单
	 * 
	 * @return
	 */
	@Action("savePurview")
	public String savePurview() {
		try {
			if (StringUtil.isBlank(name)) {
				write("-1");
				return null;
			}
			if (StringUtil.isBlank(url)) {
				write("-2");
				return null;
			}
			if (id != null) {// 修改子菜单
				SysPurview sysPurview = sysPurviewService.get(id);
				sysPurview.setName(name);
				sysPurview.setUrl(url);
				sysPurview.setRemark(remark);
				sysPurview.setUpdateAdmin((FuAdmin) getSession().get("admin"));
				sysPurview.setUpdateTime(new Date());
				sysPurviewService.save(sysPurview);
				return null;
			}
			if (parentId != null) {// 添加子菜单
				SysPurview purview = new SysPurview();
				purview.setParentId(parentId);
				purview.setName(name);
				purview.setUrl(url);
				purview.setRemark(remark);
				purview.setCreateAdmin((FuAdmin) getSession().get("admin"));
				purview.setCreateTime(new Date());
				sysPurviewService.save(purview);
				return null;
			}
			if (id == null && parentId == null) {// 添加主菜单
				SysPurview purview = new SysPurview();
				purview.setParentId(0L);
				purview.setName(name);
				purview.setUrl(url);
				purview.setRemark(remark);
				purview.setCreateAdmin((FuAdmin) getSession().get("admin"));
				purview.setCreateTime(new Date());
				sysPurviewService.save(purview);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除菜单
	 * 
	 * @return
	 */
	@Action("delPurview")
	public String delPurview() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("parentId", id);
			List<SysPurview> list = sysPurviewService.findPurviewList(0, Integer.MAX_VALUE, map);// 查询当前菜单是否有下级菜单
			if (list.size() > 0) {
				write("-1");
				return null;
			}
			sysPurviewService.deletePurview(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 保存角色的菜单权限
	 */
	@Action("saveRolePurview")
	public String saveRolePurview() {
		try {
			sysRolePurviewService.saveRolePurview(roleId, purviewArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPurviewArray() {
		return purviewArray;
	}

	public void setPurviewArray(String purviewArray) {
		this.purviewArray = purviewArray;
	}

	public FuAdmin getAdmin() {
		return admin;
	}

	public void setAdmin(FuAdmin admin) {
		this.admin = admin;
	}
}
