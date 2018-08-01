package com.hongwei.futures.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.SysPurview;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface SysPurviewDao extends BaseDao<SysPurview, Long> {
	public Integer getCount(Map<String, Object> map);
	public List<SysPurview> findPurviewList(int i, int pageSize, Map<String, Object> map);

	/*查询出所有的顶级权限的URL*/
	public List<SysPurview> findTopPrivilege();
	/*查询所有的权限URL*/
	public Collection<String> getAllPrivilegeUrls();

}

