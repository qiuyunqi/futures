package com.hongwei.futures.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.FuSmsLog;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface FuSmsLogService {
	
	//====================== 基本 C R U D 方法  ===========================
	public FuSmsLog get(Long id) ;
	
	public void save(FuSmsLog entity) ;
	
	public void delete(Long id) ;
	/**
	 * 发送消息记录数目
	 * @param map
	 * @return
	 */
	public Integer countLog(HashMap<String, Object> map);
	/**
	 * 发送消息记录分页列表
	 * @param j 
	 * @param i 
	 * @param map
	 * @return
	 */
	public List<FuSmsLog> findLogList(int i, int j, HashMap<String, Object> map);
	/**
	 * 根据参数查询某条短信消息
	 * @param map
	 * @return
	 */
	public FuSmsLog findLogByMap(Map<String, Object> map);
	/**
	 * 根据参数查询短信消息集合
	 * @param map
	 * @return
	 */
	public List<FuSmsLog> findLogListByMap(Map<String, Object> map);

	/**
	 * 根据手机号 和 ip 判断是否在黑名单中
	 * @param phone
	 * @param ip
	 * @return
	 */
	public int examin(String phone, String ip);
	
	public void saveSendServiceEmail(String reason, String content);
	
	public List<FuSmsLog> findMailLogList();

	/**
	 * 获取最新的验证码
	 * @param phone 	手机号码
	 * @return
	 */
	public FuSmsLog getNewCode(String phone);
	
}

