package com.hongwei.futures.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hongwei.futures.dao.FuArticleDao;
import com.hongwei.futures.model.FuArticle;

/**
 * 
 * @description 自动生成 daoImpl
 * 
 * @author 弘威
 */
@Repository
public class FuArticleDaoImpl extends BaseDaoImpl<FuArticle, Long> implements FuArticleDao {

	@Override
	public List<FuArticle> findArticleList(int i, int pageSize) {
		String hql = " from FuArticle where state=1 and fuDictionary.id = 40 order by id desc";
		return this.findListByHQL(i, pageSize, hql);
	}

	@Override
	public Integer countArticle() {
		String hql = " from FuArticle where state=1  and fuDictionary.id = 40";
		return this.countQueryResult(hql);
	}

	// 根据文章的id 查询这个文章的前十条数据
	@SuppressWarnings("unchecked")
	public List<FuArticle> findList(String articleId, int curPage, int pageNum) {
		String hql = "from FuArticle as f where state = 1 and fuDictionary.id = 40";
		if (null != articleId && !"".equals(articleId)) {
			hql += " and f.id < " + Long.parseLong(articleId);
			hql += " order by f.time desc";

			return this.getSession().createQuery(hql)//
					.setFirstResult(0)//
					.setMaxResults(pageNum)//
					.list();
		} else {
			hql += " order by f.time desc";
			return this.getSession().createQuery(hql)//
					.setFirstResult(curPage)//
					.setMaxResults(pageNum)//
					.list();
		}
	}

	@Override
	public List<FuArticle> findHelpList(int i, int pageSize, Map<String, Object> map) {
		StringBuilder hqlB = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		setHQL(hqlB, map, params);
		return this.findListByHQL(i, pageSize, hqlB.toString(), params);
	}

	@Override
	public Integer getCount(Map<String, Object> map) {
		StringBuilder hqlB = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		setHQL(hqlB, map, params);
		return this.countQueryResult(hqlB.toString(), params);
	}

	public void setHQL(StringBuilder hqlB, Map<String, Object> map, List<Object> params) {
		hqlB.append("from FuArticle where state=1 and fuDictionary.id <> 40");
		if (map.get("dictionaryId") != null) {
			params.add(map.get("dictionaryId"));
			hqlB.append("and fuDictionary.id=?");
		}
		hqlB.append(" order by id desc");
	}

	/**
	 * 根据条件查询帮助中心的数据
	 * 
	 * @param map
	 * @return
	 */
	public List<FuArticle> findHelpListByMap(Map<String, Object> map) {
		String hql = "from FuArticle where state = 1 and fuDictionary.id <> 40";
		List<Object> params = new ArrayList<Object>();
		if (map.get("dictionaryId") != null) {
			params.add(map.get("dictionaryId"));
			hql = hql + " and fuDictionary.id=?";
		}
		return this.findAllByHQL(hql, params);
	}

}
