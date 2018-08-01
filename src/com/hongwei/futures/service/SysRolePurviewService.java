package com.hongwei.futures.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.SysPurview;
import com.hongwei.futures.model.SysRolePurview;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public interface SysRolePurviewService {

	// ====================== 基本 C R U D 方法 ===========================
	public SysRolePurview get(Long id);

	public void save(SysRolePurview entity);

	public void delete(Long id);

	public List<SysRolePurview> findList(Map<String, Object> map);

	public String findPurviewList(Long roleId);

	public List<SysPurview> findPurviewListByRoleId(Long id);

	public void saveRolePurview(Long roleId, String purviewArray);

}
