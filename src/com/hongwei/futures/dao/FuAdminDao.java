package com.hongwei.futures.dao;

import java.util.List;
import java.util.Map;
import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.FuAdmin;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface FuAdminDao extends BaseDao<FuAdmin, Long> {

	public FuAdmin findAdminByAccount(String account);

	public FuAdmin findLoginByToken(String token);
	
	public List<Object[]> findList(int i,int pageSize,Map<String, Object> map);
	
	public Integer getCount(Map<String,Object> map);

}

