package com.hongwei.futures.dao;

import java.math.BigDecimal;
import java.util.List;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.FuServer;

/**
 * 
 * @description 自动生成 dao
 * 
 * @author 弘威
 */
public interface FuServerDao extends BaseDao<FuServer, Long> {

	public List<FuServer> findListByServer();

	public FuServer findByName(String name);
	
	public List<FuServer> findListAvalible(BigDecimal total);
	
	public FuServer findServerByUserTypeId(Integer userTypeId);

}
