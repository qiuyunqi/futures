package com.hongwei.futures.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hongwei.futures.dao.TrdOrderDao;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.TrdOrder;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@SuppressWarnings("all")
@Repository
public class TrdOrderDaoImpl extends BaseDaoImpl<TrdOrder, Long> implements TrdOrderDao {

	@Override
	public TrdOrder findByOrderNum(String orderNum) {
		String hql=" from TrdOrder where orderNum=? ";
		return this.findUniqueByHQL(hql, orderNum);
	}

	@Override
	public List<TrdOrder> findTrdOrders() {
		String hql=" from TrdOrder where failureCode != '0' order by tradeDateTime desc ";
		return this.findAllByHQL(hql);
	}

	@Override
	public List<TrdOrder> findAllOrders() {
		String hql=" from TrdOrder ";
		return this.findAllByHQL(hql);
	}

	@Override
	public String findMaxOrder() {
		String sql = " select id, max(order_num+0) as max_order_num from trd_order ";
		JdbcTemplate jdbcTemplate = this.getJdbcTemplate();
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		if(list.get(0).get("max_order_num") == null || (Double) list.get(0).get("max_order_num") < 0){
			return "10000005";
		}else{
			BigDecimal bd = new BigDecimal(list.get(0).get("max_order_num").toString()); 
//			然后转换成字符串：
			String str = bd.toPlainString();
			String res = String.valueOf(Long.parseLong(str)+1);
			return res;
		}
	}

	public List<TrdOrder> getOrderByState(Long user_id, String state) {

		String hql = "from TrdOrder where state = :state and user_id = :user_id";
		return this.getSession().createQuery(hql)//
		.setInteger("state", Integer.parseInt(state))//
		.setLong("user_id", user_id)//
		.list();
	}

	@Override
	public List<TrdOrder> queryOrderList(int i, int pageSize, Map<String, Object> map) {
		String hql=" from TrdOrder where 1=1";
		List<Object> params=new ArrayList<Object>();
		if(map.containsKey("accountUserId")){
			params.add(map.get("accountUserId"));
			hql=hql+" and fuUser.id=? ";
		}
		if(map.containsKey("accountUserName")){
			params.add(map.get("accountUserName"));
			hql=hql+" and fuUser.userName=? ";
		}
		if(map.containsKey("orderNum")){
			params.add(map.get("orderNum"));
			hql=hql+" and orderNum=? ";
		}
		hql=hql+" order by createTime desc";
		return this.findListByHQL(i, pageSize, hql, params);
	}

	@Override
	public Integer countOrders(Map<String, Object> map) {
		String hql = "from TrdOrder where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		if(map.containsKey("accountUserId")){
			params.add(map.get("accountUserId"));
			hql=hql+" and fuUser.id=? ";
		}
		if(map.containsKey("accountUserName")){
			params.add(map.get("accountUserName"));
			hql=hql+" and fuUser.userName=? ";
		}
		if(map.containsKey("orderNum")){
			params.add(map.get("orderNum"));
			hql=hql+" and orderNum=? ";
		}
		hql=hql+" order by createTime desc";
		return this.countQueryResult(hql,params);
	}

}

