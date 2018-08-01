package com.hongwei.futures.dao;

import java.util.List;
import java.util.Map;
import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.SysPurview;
import com.hongwei.futures.model.SysRolePurview;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface SysRolePurviewDao extends BaseDao<SysRolePurview, Long> {
	public List<SysRolePurview> findList(Map<String, Object> map);
	
	public String findPurviewList(Long roleId);

	public List<SysPurview> findPurviewListByRoleId(Long roleId);
}

