package com.hongwei.futures.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.hongwei.futures.model.SysUserRole;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface SysUserRoleService {
	
	//====================== 基本 C R U D 方法  ===========================
	public SysUserRole get(Long id) ;
	
	public void save(SysUserRole entity) ;
	
	public void delete(Long id) ;

	public List<SysUserRole> findUserRoleByRoleId(Long roleId);
	
	public List<SysUserRole> findUserRole(Long adminId);
	
}

