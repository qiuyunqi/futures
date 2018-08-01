package com.hongwei.futures.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.hongwei.futures.dao.FuStockAccountDao;
import com.hongwei.futures.model.FuStockAccount;
import com.hongwei.futures.model.StockShare;

/**
 * 
 * @description 自动生成 daoImpl
 * 
 * @author 弘威
 */
@SuppressWarnings("all")
@Repository
public class FuStockAccountDaoImpl extends BaseDaoImpl<FuStockAccount, Long> implements FuStockAccountDao {

	@Override
	public List<FuStockAccount> queryAccountList(int current, int pageNum, Map<String, Object> map) {
		String hql = " from FuStockAccount where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if (map.containsKey("phone")) {
			params.add(map.get("phone"));
			hql = hql + " and fuUser.phone=? ";
		}
		if (map.containsKey("userName")) {
			params.add(map.get("userName"));
			hql = hql + " and fuUser.userName=? ";
		}
		if (map.containsKey("accountUserId")) {
			params.add(map.get("accountUserId"));
			hql = hql + " and fuUser.id=? ";
		}
		if (map.containsKey("capitalAccount")) {
			params.add(map.get("capitalAccount"));
			hql = hql + " and capitalAccount=? ";
		}
		if (map.containsKey("state")) {
			params.add(map.get("state"));
			hql = hql + " and state=? ";
		}
		if (map.containsKey("isDel")) {
			params.add(map.get("isDel"));
			hql = hql + " and isDel=? ";
		}
		if (map.containsKey("time1")) {
			params.add(map.get("time1"));
			hql = hql + " and createTime>=? ";
		}
		if (map.containsKey("time2")) {
			params.add(map.get("time2"));
			hql = hql + " and createTime<=? ";
		}
		if (map.containsKey("isPublish")) {
			params.add(map.get("isPublish"));
			hql = hql + " and isPublish=? ";
		}
		if (map.containsKey("createTimeUp")) {
			hql = hql + " order by createTime asc ";
		}
		if (map.containsKey("createTimeDown")) {
			hql = hql + " order by createTime desc ";
		}
		if (map.containsKey("updateTimeUp")) {
			hql = hql + " order by updateTime asc ";
		}
		if (map.containsKey("updateTimeDown")) {
			hql = hql + " order by updateTime desc ";
		}
		if (!map.containsKey("createTimeUp") && !map.containsKey("createTimeDown") && !map.containsKey("updateTimeUp") && !map.containsKey("updateTimeDown")) {
			hql = hql + " order by updateTime desc ";
		}
		return this.findListByHQL(current, pageNum, hql, params);
	}

	@Override
	public Integer countAccounts(Map<String, Object> map) {
		String hql = "from FuStockAccount where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if (map.containsKey("phone")) {
			params.add(map.get("phone"));
			hql = hql + " and fuUser.phone=? ";
		}
		if (map.containsKey("userName")) {
			params.add(map.get("userName"));
			hql = hql + " and fuUser.userName=? ";
		}
		if (map.containsKey("accountUserId")) {
			params.add(map.get("accountUserId"));
			hql = hql + " and fuUser.id=? ";
		}
		if (map.containsKey("capitalAccount")) {
			params.add(map.get("capitalAccount"));
			hql = hql + " and capitalAccount=? ";
		}
		if (map.containsKey("state")) {
			params.add(map.get("state"));
			hql = hql + " and state=? ";
		}
		if (map.containsKey("isDel")) {
			params.add(map.get("isDel"));
			hql = hql + " and isDel=? ";
		}
		if (map.containsKey("time1")) {
			params.add(map.get("time1"));
			hql = hql + " and createTime>=? ";
		}
		if (map.containsKey("time2")) {
			params.add(map.get("time2"));
			hql = hql + " and createTime<=? ";
		}
		if (map.containsKey("isPublish")) {
			params.add(map.get("isPublish"));
			hql = hql + " and isPublish=? ";
		}
		if (map.containsKey("createTimeUp")) {
			hql = hql + " order by createTime asc ";
		}
		if (map.containsKey("createTimeDown")) {
			hql = hql + " order by createTime desc ";
		}
		if (map.containsKey("updateTimeUp")) {
			hql = hql + " order by updateTime asc ";
		}
		if (map.containsKey("updateTimeDown")) {
			hql = hql + " order by updateTime desc ";
		}
		return this.countQueryResult(hql, params);
	}

	public List<FuStockAccount> findAccountByMap(int i, int pageSize, Map<String, Object> map) {
		List<Object> params = new ArrayList<Object>();
		String hql = "from FuStockAccount where 1=1 ";
		if (map.containsKey("userId")) {
			params.add(map.get("userId"));
			hql = hql + " and fuUser.id=? ";
		}
		if (map.containsKey("transactionId")) {
			params.add(map.get("transactionId"));
			hql = hql + " and transactionId=? ";
		}

		if (map.containsKey("examineStatus")) {
			params.add(map.get("examineStatus"));
			hql = hql + " and examineStatus = ? ";
		}
		if (map.containsKey("isDel")) {
			params.add(map.get("isDel"));
			hql = hql + " and isDel=? ";
		}
		hql = hql + " order by id desc ";
		return this.findListByHQL(i, pageSize, hql, params);
	}

	@Override
	public FuStockAccount findStockByCapitalAccount(String capitalAccount) {
		String hql = " from FuStockAccount where capitalAccount=? ";
		return this.findUniqueByHQL(hql, capitalAccount);
	}

	// 根据user_id 和 capitalAccount 查询这个账户
	public FuStockAccount findAccountByUserIdAndCap(Long userId, String capitalAccount) {
		String hql = "FROM  FuStockAccount as fs WHERE fs.fuUser.id = :userId and fs.capitalAccount = :capit";
		return (FuStockAccount) this.getSession().createQuery(hql)//
				.setParameter("userId", userId)//
				.setParameter("capit", capitalAccount)//
				.uniqueResult();
	}

	// 查询状态在 '中的状态 2 3 4'的主账户
	public List<FuStockAccount> getAccountByState() {
		String hql = "FROM FuStockAccount as fs WHERE fs.state != 0 and fs.state != 1 and fs.isDel = 0";
		return this.getSession().createQuery(hql)//
				.list();
	}

	// 查询这个用户是否有已经审核成功的 并可用的账号
	public List<FuStockAccount> findByUserIdAndIsDel(long userId, int isDel) {
		String hql = "FROM FuStockAccount as fs WHERE fs.fuUser.id = :userId and fs.isDel = :isDel";
		return this.getSession().createQuery(hql)//
				.setParameter("userId", userId)//
				.setParameter("isDel", isDel)//
				// .setParameter("examineStatus", examineStatus)//
				.list();
	}

	// 根据状态查询账号列表
	public List<FuStockAccount> findByStatus(int examineStatus, int isDel) {
		String hql = "FROM FuStockAccount AS f WHERE f.examineStatus = :examineStatus " + "AND f.isPublish = 1 AND f.isDel = :isDel ORDER BY f.updateTime desc, f.createTime desc";
		return this.getSession().createQuery(hql)//
				.setParameter("examineStatus", examineStatus)//
				.setParameter("isDel", isDel)//
				.list();
	}

	// 查询该交易团队下所有的融券
	public List<FuStockAccount> findByTransactionId(Long fuTransactionId) {
		String hql = "FROM FuStockAccount AS f WHERE " + "f.transactionId = :fuTransactionId AND f.transactionStatus = 0 AND f.isDel = 0 " + "ORDER BY f.orderTime desc, f.transactionStatus";
		return this.getSession().createQuery(hql)//
				.setParameter("fuTransactionId", fuTransactionId)//
				.list();
	}

	/**
	 * 查询全部的股票账户信息
	 * 
	 * @param isDel
	 *            是否删除 0: 没有删除 1:删除
	 * @param examin
	 *            是否审核 0: 审核中 1:审核成功 2:审核失败 3: 接单中 4: 接单成功
	 * @param start
	 * @param pageSize
	 * @return
	 */
	public List<FuStockAccount> findAll(int isDel, int examin, int start, int pageSize) {
		String hql = "FROM FuStockAccount AS f WHERE f.isDel = :isDel AND (f.examineStatus = :examin  OR f.isPublish = 1) ORDER BY f.transactionId, f.createTime desc";
		return this.getSession().createQuery(hql)//
				.setParameter("isDel", isDel)//
				.setParameter("examin", examin)//
				.setFirstResult(start)//
				.setMaxResults(pageSize)//
				.list();
	}

	// 	查询所有的股票message并组成一个JSON字符串
	public String getJsonList() {
//		String hql = "FROM FuStockAccount WHERE message != null";
//		List<FuStockAccount> list = this.getSession().createQuery(hql).list();
//		JSONArray jsonArray = new JSONArray();
//		for (FuStockAccount fuStockAccount : list) {
//			JSONObject obj = new JSONObject();
//			obj.put("id", fuStockAccount.getId());
//			obj.put("label", fuStockAccount.getMessage());
//			obj.put("category", fuStockAccount.getMessage());
//			jsonArray.add(obj);
//		}
		String hql = "FROM StockShare AS f WHERE f.fuStockAccount.isDel = 0";
		List<StockShare> list = this.getSession().createQuery(hql).list();
		JSONArray jsonArray = new JSONArray();
		for (StockShare stockShare : list) {
			JSONObject obj = new JSONObject();
			obj.put("id", stockShare.getFuStockAccount().getId());
			obj.put("label", stockShare.getName()+stockShare.getCode());
			obj.put("category", stockShare.getName());
			jsonArray.add(obj);
		}
		return jsonArray.toString();
	}
	
	

	/**
	 * 模糊查询message
	 * @param query  条件
	 * @return
	 */
	public List<FuStockAccount> findByMsg(String query) {
		String hql = "FROM FuStockAccount AS f WHERE f.message like '%" + query + "%' AND f.isDel = 0 AND (f.examineStatus != 0 AND f.examineStatus != 2)";
		return this.getSession().createQuery(hql).list();
	}

}
