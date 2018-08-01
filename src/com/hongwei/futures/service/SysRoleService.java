package com.hongwei.futures.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.SysRole;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public interface SysRoleService {

	// ====================== 基本 C R U D 方法 ===========================
	public SysRole get(Long id);

	public void save(SysRole entity);

	public void delete(Long id);

	public List<SysRole> findRoleListByMap(Map<String, Object> map);

	// 根据用户id查询用户对应的角色
	public List<SysRole> findRoleListByRoleId(Long userId);

	public void deleteRole(Long roleId);

}
