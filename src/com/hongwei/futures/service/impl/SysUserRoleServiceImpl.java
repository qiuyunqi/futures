package com.hongwei.futures.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.SysUserRoleDao;
import com.hongwei.futures.model.SysUserRole;
import com.hongwei.futures.service.SysUserRoleService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {
	
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	
	//====================== 基本 C R U D 方法  ===========================
	public SysUserRole get(Long id) {
		return sysUserRoleDao.get(id);
	}
	
	public void save(SysUserRole entity) {
		sysUserRoleDao.save(entity);
	}
	
	public void delete(Long id) {
		sysUserRoleDao.delete(id);
	}
	
	public List<SysUserRole> findUserRoleByRoleId(Long roleId){
		return sysUserRoleDao.findUserRoleByRoleId(roleId);
	}
	
	public List<SysUserRole> findUserRole(Long adminId){
		return sysUserRoleDao.findUserRole(adminId);
	}
	
}

