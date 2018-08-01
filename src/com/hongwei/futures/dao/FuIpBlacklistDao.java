package com.hongwei.futures.dao;

import java.util.List;
import java.util.Map;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.FuIpBlacklist;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 小合
 */
public interface FuIpBlacklistDao extends BaseDao<FuIpBlacklist, Long> {
	public FuIpBlacklist findBlackByRegIp(String registerIp);
	
	public Integer countIpBlack(Map<String, Object> map);
	
	public List<FuIpBlacklist> findIpBlackList(int i, int pageSize, Map<String, Object> map);

	/**
	 * 根据手机号码或者ip查询 fuIpBlackList对象
	 * @param phone	手机号码
	 * @param ip		IP地址
	 * @return	FuIpBlacklist 集合
	 */
	public List<FuIpBlacklist> getBlackListByPhoneOrIp(String phone, String ip);

}

