package com.hongwei.futures.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.DrawInfoDao;
import com.hongwei.futures.model.DrawInfo;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author  小合
 */
@SuppressWarnings("all")
@Repository
public class DrawInfoDaoImpl extends BaseDaoImpl<DrawInfo, Long> implements DrawInfoDao {
	
	/**
	 * @author samuel
	 * @since 2015-12-07
	 */
	public DrawInfo getByUserId(Long userId) {
		String hql = "FROM DrawInfo AS d WHERE d.userId = :userId";
		List<DrawInfo> list = this.getSession().createQuery(hql)//
				.setParameter("userId", userId)//
				.list();
		if(null != list && list.size() > 0){
			return list.get(0);
		}else {
			return null;
		}
	}

	// 查询出所有中奖信息
	public List<DrawInfo> getDrawInfo() {
		String hql = "FROM DrawInfo AS d WHERE d.drawName <> NULL";
		return this.getSession().createQuery(hql)//
				.list();
	}

}

