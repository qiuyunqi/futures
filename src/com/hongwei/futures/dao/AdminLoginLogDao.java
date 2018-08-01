package com.hongwei.futures.dao;

import java.util.List;
import java.util.Map;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.AdminLoginLog;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 小合
 */
public interface AdminLoginLogDao extends BaseDao<AdminLoginLog, Long> {

	public List<AdminLoginLog> queryAdminLoginLog(int i, int pageSize, Map<String, Object> map);

	public Integer countAdminLoginLog(Map<String, Object> map);

}

