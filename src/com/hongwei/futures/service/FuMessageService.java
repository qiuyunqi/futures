package com.hongwei.futures.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.hongwei.futures.model.FuMessage;
import com.hongwei.futures.model.HhrStat;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface FuMessageService {
	
	//====================== 基本 C R U D 方法  ===========================
	public FuMessage get(Long id) ;
	
	public void save(FuMessage entity) ;
	
	public void delete(Long id) ;
	/**
	 * 查找某个用户留言数目
	 * @param userId
	 * @return
	 */
	public Integer countMessage(Long userId);
	/**
	 * 某个用户留言分页列表
	 * @param i
	 * @param pageSize
	 * @param userId
	 * @return
	 */
	public List<FuMessage> findMessageList(int i, int pageSize, Long userId);
	/**
	 * 查找所以的留言信息
	 * @param map 
	 */
	public Integer countAllMessage(Map<String, Object> map);
	/**
	 * 费用查找所以的留言信息
	 * @param i
	 * @param pageSize
	 * @param map 
	 * @return
	 */
	public List<FuMessage> findAllMessage(int i, int pageSize, Map<String, Object> map);

	public List<FuMessage> findMessageByUser(Long userId);
}

