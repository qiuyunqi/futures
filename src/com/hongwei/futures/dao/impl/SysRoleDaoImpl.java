package com.hongwei.futures.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hongwei.futures.dao.SysRoleDao;
import com.hongwei.futures.model.SysRole;

/**
 * 
 * @description 自动生成 daoImpl
 * 
 * @author 弘威
 */
@Repository
public class SysRoleDaoImpl extends BaseDaoImpl<SysRole, Long> implements SysRoleDao {
	//根据当前登录的管理员id查询这个角色下的下属角色
	public List<SysRole> findRoleListByMap(Map<String, Object> map) {
		String hql = " from SysRole where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if (map.get("parentId") != null) {
			params.add(map.get("parentId"));
			hql = hql + " and parentId=?";
		}
		if (map.containsKey("adminId")) {
			params.add(map.get("adminId"));
			hql = hql + " and parentId=(select sysRole.id from SysUserRole where fuAdmin.id=? )";
		}
		return this.findAllByHQL(hql, params);
	}

	// 根据用户id查询用户对应的角色
	public List<SysRole> findRoleListByRoleId(Long userId) {
		String hql = "from SysRole where id in (select sysRole.id from SysUserRole where fuAdmin.id = "+userId+")";
		return this.findAllByHQL(hql);
	}
}
