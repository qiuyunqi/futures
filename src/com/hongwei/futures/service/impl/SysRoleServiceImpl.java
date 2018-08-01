package com.hongwei.futures.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongwei.futures.dao.SysRoleDao;
import com.hongwei.futures.dao.SysRolePurviewDao;
import com.hongwei.futures.dao.SysUserRoleDao;
import com.hongwei.futures.model.SysRole;
import com.hongwei.futures.model.SysRolePurview;
import com.hongwei.futures.model.SysUserRole;
import com.hongwei.futures.service.SysRoleService;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	@Autowired
	private SysRolePurviewDao sysRolePurviewDao;
	@Autowired
	private SysRoleDao sysRoleDao;

	// ====================== 基本 C R U D 方法 ===========================
	public SysRole get(Long id) {
		return sysRoleDao.get(id);
	}

	public void save(SysRole entity) {
		sysRoleDao.save(entity);
	}

	public void delete(Long id) {
		sysRoleDao.delete(id);
	}

	public List<SysRole> findRoleListByMap(Map<String, Object> map) {
		return sysRoleDao.findRoleListByMap(map);
	}

	// 根据用户id查询用户对应的角色
	public List<SysRole> findRoleListByRoleId(Long userId) {
		return sysRoleDao.findRoleListByRoleId(userId);
	}

	public void deleteRole(Long roleId) {
		// 先删除对应用户角色中间表内容
		List<SysUserRole> list = sysUserRoleDao.findUserRoleByRoleId(roleId);
		if (list != null && list.size() > 0) {
			for (SysUserRole sysUserRole : list) {
				sysUserRoleDao.delete(sysUserRole);
			}
		}
		// 删除对应角色菜单中间表的内容
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleId);
		List<SysRolePurview> rolePurviews = sysRolePurviewDao.findList(map);
		if (rolePurviews != null && rolePurviews.size() > 0) {
			for (SysRolePurview sysRolePurview : rolePurviews) {
				sysRolePurviewDao.delete(sysRolePurview);
			}
		}
		// 再删除角色表的
		sysRoleDao.delete(roleId);
	}

}
