package com.hongwei.futures.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.SysPurview;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public interface SysPurviewService {

	// ====================== 基本 C R U D 方法 ===========================
	public SysPurview get(Long id);

	public void save(SysPurview entity);

	public void delete(Long id);

	public Integer getCount(Map<String, Object> map);

	/* 查询出所有的顶级权限的URL */
	public List<SysPurview> findTopPrivilege();

	/* 获取所有的权限URL */
	public Collection<String> getAllPrivilegeUrls();

	public List<SysPurview> findPurviewList(int i, int pageSize,
			Map<String, Object> map);

	public void deletePurview(Long id);

}
