package com.hongwei.futures.dao;

import java.util.List;
import java.util.Map;
import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.SysRole;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface SysRoleDao extends BaseDao<SysRole, Long> {
	public List<SysRole> findRoleListByMap(Map<String, Object> map);
//	根据用户id查询用户对应的角色
	public List<SysRole> findRoleListByRoleId(Long userId);
}

