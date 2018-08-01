package com.hongwei.futures.dao;

import java.util.List;
import java.util.Map;

import com.hongwei.futures.model.TrdOrder;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface TrdOrderDao extends BaseDao<TrdOrder, Long> {

	public TrdOrder findByOrderNum(String orderNum);

	public List<TrdOrder> findTrdOrders();

	public List<TrdOrder> findAllOrders();

	public String findMaxOrder();

	public List<TrdOrder> getOrderByState(Long user_id, String state);

	public List<TrdOrder> queryOrderList(int i, int pageSize, Map<String, Object> map);

	public Integer countOrders(Map<String, Object> map);

}

