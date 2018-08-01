package com.hongwei.futures.dao;

import java.util.List;
import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.SysUserRole;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface SysUserRoleDao extends BaseDao<SysUserRole, Long> {
	public List<SysUserRole> findUserRole(Long adminId);
	
	public List<SysUserRole> findUserRoleByRoleId(Long roleId);
}

