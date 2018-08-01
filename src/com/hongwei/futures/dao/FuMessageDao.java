package com.hongwei.futures.dao;

import java.util.List;
import java.util.Map;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.FuMessage;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface FuMessageDao extends BaseDao<FuMessage, Long> {

	public Integer countMessage(Long userId);

	public List<FuMessage> findMessageList(int i, int pageSize, Long userId);

	public Integer countAllMessage(Map<String, Object> map);

	public List<FuMessage> findAllMessage(int i, int pageSize, Map<String, Object> map);

	public List<FuMessage> findMessageByUser(Long userId);

}

