package com.hongwei.futures.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.FuSmsLog;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface FuSmsLogDao extends BaseDao<FuSmsLog, Long> {

	public Integer countLog(HashMap<String, Object> map);

	public List<FuSmsLog> findLogList(int i,int j,HashMap<String, Object> map);
	
	public FuSmsLog findLogByMap(Map<String, Object> map);
	
	public List<FuSmsLog> findLogListByMap(Map<String, Object> map);

	/**
	 * 在规定的时间内查询该电话号码发送了多少条短信
	 * @param phone				手机号码
	 * @param regInterval		规定的时间内
	 * @return
	 */
	public Integer getNumByPhoneInMin(String phone, Integer regInterval);

	/**
	 * 查询该手机号最新的一条记录
	 * @param phone		记录
	 * @return
	 */
	public FuSmsLog findNewMessage(String phone);

	public void saveSendServiceEmail(String reason, String content);

	public List<FuSmsLog> findMailLogList();

	/**
	 * 获取最新的验证码
	 * @param phone		手机号码
	 * @return
	 */
	public FuSmsLog getNewCode(String phone);

}

