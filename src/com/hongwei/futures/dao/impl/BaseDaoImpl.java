package com.hongwei.futures.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.util.Assert;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.util.Constants;
import com.hongwei.futures.util.Pager;
import com.hongwei.futures.util.ReflectionUtil;

@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T, ID extends Serializable> implements
		BaseDao<T, ID> {

	private SessionFactory sessionFactory;

	private HibernateTemplate hibernateTemplate;

	private JdbcTemplate jdbcTemplate;

	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	// public FullTextSession getFullTextSession() {
	// return Search.getFullTextSession(getSession());
	// }

	@Override
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Autowired
	public void setHibernateTemplate(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Autowired
	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private static Log log = LogFactory.getLog(BaseDaoImpl.class);

	private Class<T> entityClass;

	public BaseDaoImpl() {
		Type[] actualTypeArguments = ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments();
		this.entityClass = (Class<T>) actualTypeArguments[0];
	}

	public List<T> findAllByHQL(String hql,
			List<Object> valueList) {
		Object[] objArray = new Object[valueList.size()];
		for (int i = 0; i < valueList.size(); i++) {
			objArray[i] = valueList.get(i);
		}
		return findAllByHQL(hql, objArray);
	}
	
	public int countQueryResult(String hql,List<Object> valueList) {
		Object[] objArray = new Object[valueList.size()];
		for (int i = 0; i < valueList.size(); i++) {
			objArray[i] = valueList.get(i);
		}
		return countQueryResult(hql,objArray);
	}
	
	public Class<T> getEntityClass() {
		return entityClass;
	}

	@Override
	public void reload(T entity) {
		getSession().lock(entity, LockMode.NONE);
	}

	@Override
	public T merge(T entity) {
		return (T) getSession().merge(entity);
	}

	@Override
	public T get(ID id) {
		return (T) getSession().get(getEntityClass(), id);
	}

	@Override
	public void save(T entity) {
		Assert.notNull(entity);
		getSession().saveOrUpdate(entity);
		log.debug("save or update entity: " + entity);
	}

	@Override
	public void update(T entity) {
		Assert.notNull(entity);
		getSession().update(entity);
		log.debug("update entity: " + entity);
	}

	@Override
	public void delete(T entity) {
		Assert.notNull(entity);
		getSession().delete(entity);
		log.debug("delete entity: " + entity);
	}

	@Override
	public void delete(ID id) {
		Assert.notNull(id);
		delete(get(id));
	}

	@Override
	public Pager<T> findByExample(T exampleInstance, String... excludeProperty) {
		return findByExample(1, exampleInstance, excludeProperty);
	}

	@Override
	public Pager<T> findByExample(int pageNo, T exampleInstance,
			String... excludeProperty) {
		return findByExample(pageNo, Constants.DEFAULT_PAGE_SIZE,
				exampleInstance, excludeProperty);
	}

	@Override
	public Pager<T> findByExample(int pageNo, int pageSize, T exampleInstance,
			String... excludeProperty) {
		Pager<T> pager = new Pager<T>(pageNo, pageSize);
		return findByExample(pager, exampleInstance, excludeProperty);
	}

	@Override
	public Pager<T> findByExample(Pager<T> pager, T exampleInstance,
			String... excludeProperty) {
		Criteria crit = getSession().createCriteria(getEntityClass());
		Example example = Example.create(exampleInstance);
		if (excludeProperty != null) {
			for (String exclude : excludeProperty) {
				example.excludeProperty(exclude);
			}
		}
		crit.add(example);

		pager.setTotalCount(countQueryResult(crit));

		crit.setFirstResult(pager.getFirstIndex());
		crit.setMaxResults(pager.getPageSize());
		List list = crit.list();
		pager.setList(list);
		if (list.isEmpty() && pager.getPageNo() > 1) {
			pager.setPageNo(pager.getPageNo() - 1);
			pager = findByExample(pager, exampleInstance, excludeProperty);
		}
		return pager;
	}

	@Override
	public Pager<T> findByCriteria(Criterion... criterions) {
		return findByCriteria(new Pager<T>(), criterions);
	}

	@Override
	public Pager<T> findByCriteria() {
		return findByCriteria(1);
	}

	@Override
	public Pager<T> findByCriteria(int pageNo) {
		return findByCriteria(pageNo, Constants.DEFAULT_PAGE_SIZE);
	}

	@Override
	public Pager<T> findByCriteria(int pageNo, int pageSize) {
		Pager<T> pager = new Pager<T>(pageNo, pageSize);
		return findByCriteria(pager);
	}

	@Override
	public Pager<T> findByParamMap(int pageNo, Map<String, Object> params) {
		return findByParamMap(pageNo, Constants.DEFAULT_PAGE_SIZE, params);
	}

	@Override
	public Pager<T> findByParamMap(Map<String, Object> params) {
		return findByParamMap(1, params);
	}

	@Override
	public Pager<T> findByParamMap(int pageNo, int pageSize,
			Map<String, Object> params) {
		Pager<T> pager = new Pager<T>(pageNo, pageSize);
		return findByParamMap(pager, params);
	}

	@Override
	public Pager<T> findByParamMap(Pager<T> pager, Map<String, Object> params) {
		if (params == null) {
			return findByCriteria(pager);
		}
		Set<Map.Entry<String, Object>> set = params.entrySet();
		Criterion[] criterions = new Criterion[set.size()];
		int i = 0;
		for (Map.Entry<String, Object> entry : set) {
			criterions[i++] = Restrictions.eq(entry.getKey(), entry.getValue());
		}
		return findByCriteria(pager, criterions);
	}

	@Override
	public Pager<T> findByCriteria(final Pager<T> pager,
			final Criterion... criterions) {
		return findByCriteria(pager, new Order[] {}, criterions);
	}

	@Override
	public Pager<T> findByCriteria(Pager<T> pager, Order[] orders,
			Criterion... criterion) {

		Assert.notNull(pager);

		Criteria crit = createCriteria(criterion);
		pager.setTotalCount(countQueryResult(crit));

		crit.setFirstResult(pager.getFirstIndex());
		crit.setMaxResults(pager.getPageSize());
		for (Order order : orders) {
			crit.addOrder(order);
		}
		List list = crit.list();
		pager.setList(list);
		if (list.isEmpty() && pager.getPageNo() > 1) {
			pager.setPageNo(pager.getPageNo() - 1);
			pager = findByCriteria(pager, orders, criterion);
		}
		return pager;
	}

	@Override
	public Pager<T> findByProperty(String propertyName, Object value) {
		Assert.hasText(propertyName);
		Criterion criterion = Restrictions.eq(propertyName, value);
		return findByCriteria(criterion);
	}

	@Override
	public Pager<T> findByHQL(String hql, Object... values) {
		return findByHQL(1, hql, values);
	}

	@Override
	public Pager<T> findByHQL(int pageNo, String hql, Object... values) {
		return findByHQL(pageNo, Constants.DEFAULT_PAGE_SIZE, hql, values);
	}

	@Override
	public Pager<T> findByHQL(int pageNo, int pageSize, String hql,
			Object... values) {
		Pager<T> pager = new Pager<T>(pageNo, pageSize);
		return findByHQL(pager, hql, values);
	}

	@Override
	public Pager<T> findByHQL(int pageNo, int pageSize, String hql,
			List<Object> valueList) {
		Object[] objArray = new Object[valueList.size()];
		for (int i = 0; i < valueList.size(); i++) {
			objArray[i] = valueList.get(i);
		}
		return findByHQL(pageNo, pageSize, hql, objArray);
	}

	@Override
	public Pager<T> findByHQL(Pager<T> pager, String hql, Object... values) {
		Assert.notNull(pager);

		pager.setTotalCount(countQueryResult(hql, values));
		Query query = createQuery(hql, values);
		query.setFirstResult(pager.getFirstIndex());
		query.setMaxResults(pager.getPageSize());
		List list = query.list();
		pager.setList(list);
		return pager;
	}

	@Override
	public List<T> findListByExample(T exampleInstance,
			String... excludeProperty) {
		return findListByExample(Constants.DEFAULT_SIZE, exampleInstance,
				excludeProperty);
	}

	@Override
	public List<T> findListByExample(int size, T exampleInstance,
			String... excludeProperty) {
		return findListByExample(0, size, exampleInstance, excludeProperty);
	}

	@Override
	public List<T> findListByExample(int fromIndex, int size,
			T exampleInstance, String... excludeProperty) {
		Criteria crit = getSession().createCriteria(getEntityClass());
		Example example = Example.create(exampleInstance);
		if (excludeProperty != null) {
			for (String exclude : excludeProperty) {
				example.excludeProperty(exclude);
			}
		}
		crit.add(example);
		crit.setFirstResult(fromIndex);
		crit.setMaxResults(size);
		return crit.list();

	}

	@Override
	public List<T> findListByCriteria(Criterion... criterions) {
		Criteria crit = getSession().createCriteria(getEntityClass());
		crit.setMaxResults(Constants.DEFAULT_PAGE_SIZE);
		return crit.list();
	}

	@Override
	public List<T> findListByCriteria() {
		return findListByCriteria(Constants.DEFAULT_SIZE);
	}

	@Override
	public List<T> findListByCriteria(int size) {
		return findListByCriteria(0, size);
	}

	@Override
	public List<T> findListByCriteria(int fromIndex, int size) {
		Criteria crit = getSession().createCriteria(getEntityClass());
		crit.setFirstResult(fromIndex);
		crit.setMaxResults(size);
		return crit.list();
	}

	@Override
	public List<T> findListByParamMap(int size, Map<String, Object> params) {
		return findListByParamMap(0, size, params);
	}

	@Override
	public List<T> findListByParamMap(Map<String, Object> params) {
		return findListByParamMap(Constants.DEFAULT_SIZE, params);
	}

	@Override
	public List<T> findListByParamMap(int fromIndex, int size,
			Map<String, Object> params) {
		if (params == null) {
			return findListByCriteria(fromIndex, size);
		}
		Set<Map.Entry<String, Object>> set = params.entrySet();
		Criterion[] criterions = new Criterion[set.size()];
		int i = 0;
		for (Map.Entry<String, Object> entry : set) {
			criterions[i++] = Restrictions.eq(entry.getKey(), entry.getValue());
		}
		return findListByCriteria(fromIndex, size, criterions);

	}

	public List<T> findListByCriteria(int fromIndex, int size,
			Criterion... criterion) {
		return findListByCriteria(fromIndex, size, new Order[] {}, criterion);
	}

	@Override
	public List<T> findListByCriteria(int fromIndex, int size, Order[] orders,
			Criterion... criterion) {
		Criteria crit = createCriteria(criterion);
		crit.setFirstResult(fromIndex);
		crit.setMaxResults(size);
		for (Order order : orders) {
			crit.addOrder(order);
		}
		return crit.list();
	}

	@Override
	public List<T> findListByProperty(String propertyName, Object value) {
		Assert.hasText(propertyName);
		Criterion criterion = Restrictions.eq(propertyName, value);
		return findListByCriteria(criterion);
	}

	@Override
	public List<T> findListByHQL(String hql, Object... values) {
		return findListByHQL(Constants.DEFAULT_SIZE, hql, values);
	}

	@Override
	public List<T> findListByHQL(int size, String hql, Object... values) {
		return findListByHQL(0, size, hql, values);
	}
	
	

	@Override
	public List<T> findListByHQL(int size, String hql, List<Object> valueList) {
		Object[] objArray = new Object[valueList.size()];
		for (int i = 0; i < valueList.size(); i++) {
			objArray[i] = valueList.get(i);
		}
		return findListByHQL(0, size, hql, objArray);
	}


	@Override
	public List<T> findListByHQL(int fromIndex, int size, String hql,
			Object... values) {
		Query query = createQuery(hql, values);
		query.setFirstResult(fromIndex);
		query.setMaxResults(size);
		return query.list();

	}

	@Override
	public T findUniqueByHQL(String hql, Object... values) {
		Query query = createQuery(hql, values);
		query.setFirstResult(0);
		query.setMaxResults(1);
		List<T> list=query.list();
		if(list.size()>0)
			return list.get(0);
		return null;
	}

	@Override
	public T findUniqueByProperty(String propertyName, Object value) {
		Assert.hasText(propertyName);
		if (value != null) {
			return (T) createCriteria(Restrictions.eq(propertyName, value))
					.uniqueResult();
		} else {
			return (T) createCriteria(Restrictions.isNull(propertyName))
					.uniqueResult();
		}
	}

	protected Criteria createCriteria(Criterion... criterions) {
		Criteria crit = getSession().createCriteria(entityClass).setCacheable(
				true);
		if (criterions != null && criterions.length > 0) {
			for (Criterion c : criterions) {
				crit.add(c);
			}
		}
		return crit;
	}

	@Override
	public int countQueryResult(Criteria crit) {
		CriteriaImpl impl = (CriteriaImpl) crit;

		Projection projection = impl.getProjection();
		ResultTransformer transformer = impl.getResultTransformer();

		List<CriteriaImpl.OrderEntry> orderEntries = null;
		try {
			orderEntries = (List) ReflectionUtil.getFieldValue(impl,
					"orderEntries");
			ReflectionUtil.setFieldValue(impl, "orderEntries", new ArrayList());
		} catch (Exception e) {
			log.error("Exception: " + e.getMessage());
		}

		int totalCount = (Integer) crit.setProjection(Projections.rowCount())
				.uniqueResult();

		crit.setProjection(projection);

		if (projection == null) {
			crit.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		if (transformer != null) {
			crit.setResultTransformer(transformer);
		}

		try {
			ReflectionUtil.setFieldValue(impl, "orderEntries", orderEntries);
		} catch (Exception e) {
			log.error("Exception: " + e.getMessage());
		}

		return totalCount;
	}

	public List<T> findListByHQL(int fromIndex, int size, String hql,
			List<Object> valueList) {
		Object[] objArray = new Object[valueList.size()];
		for (int i = 0; i < valueList.size(); i++) {
			objArray[i] = valueList.get(i);
		}
		return findListByHQL(fromIndex, size, hql, objArray);
	}
//	public int countQueryResult(String hql, List<Object> values) {
//		String fromClause = hql.substring(hql.indexOf("from"));
//		String countHql = "select count(*) " + fromClause;
//		Query query = createQuery(countHql, values);
//		return ((Long) query.uniqueResult()).intValue();
//	}
	
	//查询数据表的数量
	@Override
	public int countQueryResult(String hql, Object... values) {
		String fromClause = hql.substring(hql.indexOf("from"));
		String countHql = "select count(*) " + fromClause;
		Query query = createQuery(countHql, values);
		return ((Long) query.uniqueResult()).intValue();
	}

	@Override
	public int countParamListQueryResult(String hql, String paramName,
			Object[] paramList, Object... values) {
		String fromClause = hql.substring(hql.indexOf("from"));
		String countHql = "select count(*) " + fromClause;
		Query query = getSession().createQuery(countHql).setCacheable(true)
				.setParameterList(paramName, paramList);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return ((Long) query.uniqueResult()).intValue();
	}

	@Override
	public int countSqlQueryResult(String sql, Object... values) {
		String fromClause = sql.substring(sql.indexOf("from"));
		String countSql = "select count(0) " + fromClause;
		SQLQuery query = createSQLQuery(countSql, values);
		return ((BigInteger) query.uniqueResult()).intValue();

	}

	// public int countLuceneQueryResult(org.apache.lucene.search.Query
	// luceneQuery) {
	// try {
	// DirectoryProvider[] directoryProviders =
	// getFullTextSession().getSearchFactory().getDirectoryProviders(
	// entityClass);
	// int totalCount = 0;
	// for(DirectoryProvider<Directory> directoryProvider:directoryProviders) {
	// Directory directory = directoryProvider.getDirectory();
	// IndexSearcher searcher = new IndexSearcher(directory);
	// totalCount += searcher.search(luceneQuery, 10).totalHits;
	// searcher.close();
	// }
	// return totalCount;
	// } catch (Exception e) {
	// e.printStackTrace();
	// throw new RuntimeException(e);
	// }
	// }

	protected Query createQuery(String queryString, List<Object> values) {
		Assert.hasText(queryString);
		Query query = getSession().createQuery(queryString).setCacheable(true);
		if (values != null) {
			for (int i = 0; i < values.size(); i++) {
				query.setParameter(i, values.get(i));
			}
		}
		return query;
	}
	
	protected Query createQuery(String queryString, Object... values) {
		Assert.hasText(queryString);
		Query query = getSession().createQuery(queryString).setCacheable(true);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}

	protected SQLQuery createSQLQuery(String sqlString, Object... values) {
		Assert.hasText(sqlString);
		SQLQuery sqlQuery = getSession().createSQLQuery(sqlString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				sqlQuery.setParameter(i, values[i]);
			}
		}
		return sqlQuery;
	}

	protected Query createQuery(String queryString, Map<String, Object> paramMap) {
		Assert.hasText(queryString);
		Query query = getSession().createQuery(queryString).setCacheable(true);
		if (paramMap != null) {
			for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return query;
	}

	@Override
	public void executeUpdate(final String updateString,
			final Map<String, Object> paramMap) {
		Assert.hasText(updateString);
		createQuery(updateString, paramMap).executeUpdate();
	}

	@Override
	public void executeUpdate(final String updateString, final Object... values) {
		Assert.hasText(updateString);
		createQuery(updateString, values).executeUpdate();
	}

	@Override
	public void executeUpdateByParamList(String updateString, String paramName,
			Object[] values) {
		getSession().createQuery(updateString).setParameterList(paramName,
				values).executeUpdate();
	}

	@Override
	public void executeUpdate(final String updateString,
			final String paramName, final Object value) {
		Assert.hasText(updateString);
		Query query = createQuery(updateString);
		query.setParameter(paramName, value);
		query.executeUpdate();
	}

	@Override
	public void saveOrUpdateAll(final Collection<T> collection) {
		if (collection != null) {
			for (T t : collection) {
				getSession().saveOrUpdate(t);
			}
		}
	}

	@Override
	public void executeSqlUpdate(String sqlString, Object... values) {
		createSQLQuery(sqlString, values).executeUpdate();
	}

	@Override
	public List<T> findListBySQL(String sqlString, Object... values) {
		return findListBySQL(0, Constants.DEFAULT_SIZE, sqlString, values);
	}

	@Override
	public List<T> findListByDetachedCriteria(DetachedCriteria detachedCriteria) {
		return getHibernateTemplate().findByCriteria(detachedCriteria);
	}

	@Override
	public Pager<T> findBySQL(int pageNo, int pageSize, String sqlString,
			Object... values) {
		SQLQuery sqlQuery = createSQLQuery(sqlString, values).addEntity(
				getEntityClass());
		Pager<T> pager = new Pager<T>(pageNo, pageSize);
		pager.setTotalCount(countSqlQueryResult(sqlString, values));
		sqlQuery.setFirstResult(pager.getFirstIndex());
		sqlQuery.setMaxResults(pager.getPageSize());
		List<T> list = sqlQuery.list();
		pager.setList(list);
		return pager;
	}

	@Override
	public List<T> findListBySQL(int fromIndex, int size, String sqlString,
			Object... values) {
		SQLQuery sqlQuery = createSQLQuery(sqlString, values).addEntity(
				getEntityClass());
		sqlQuery.setFirstResult(fromIndex);
		sqlQuery.setMaxResults(size);
		return sqlQuery.list();
	}
	
	@Override
	public List<Object[]> findListBySql(final int fromIndex, final int size, final String sql, final List paramList) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			@Override
			public List<Object[]> doInHibernate(Session session)
					throws HibernateException, SQLException {
				SQLQuery sqlquery=session.createSQLQuery(sql);
				if(paramList!=null){
					for(int i=0;i<paramList.size();i++){
						sqlquery.setParameter(i, paramList.get(i));
					}
				}
				sqlquery.setFirstResult(fromIndex);
				sqlquery.setMaxResults(size);
				return sqlquery.list();
			}
		});
	}

	@Override
	public Pager<T> findByDetachedCriteria(int pageNo,
			DetachedCriteria detachedCriteria) {
		return findByDetachedCriteria(pageNo, Constants.DEFAULT_PAGE_SIZE,
				detachedCriteria);
	}

	@Override
	public Pager<T> findByDetachedCriteria(DetachedCriteria detachedCriteria) {
		return findByDetachedCriteria(1, detachedCriteria);
	}

	@Override
	public Pager<T> findByDetachedCriteria(int pageNo, int pageSize,
			DetachedCriteria detachedCriteria) {
		Pager<T> pager = new Pager<T>(pageNo);
		List<T> list = findListByDetachedCriteria(detachedCriteria);
		pager.setTotalCount(list.size());
		int end = pageNo * pageSize < list.size() ? pageNo * pageSize - 1
				: list.size();
		pager.setList(list.subList((pageNo - 1) * pageSize, end));
		return pager;
	}

	@Override
	public List<T> findAll() {
		return findListByCriteria(Integer.MAX_VALUE);
	}
	
	@Override
	public List<T> findAllByHQL(String hql, Object... values) {
		return findListByHQL(Integer.MAX_VALUE, hql, values);
	}

	@Override
	public Pager<T> findByParamList(int pageNo, int pageSize, String hql,
			String paramName, Object[] paramList, Object... values) {
		Query query = createQuery(hql, values);
		query.setParameterList(paramName, paramList);
		Pager<T> pager = new Pager<T>(pageNo, pageSize);
		pager.setTotalCount(countParamListQueryResult(hql, paramName,
				paramList, values));
		query.setFirstResult(pager.getFirstIndex());
		query.setMaxResults(pageSize);
		pager.setList(query.list());
		return pager;
	}

	@Override
	public List<T> findListByParamList(int size, String hql, String paramName,
			Object[] paramList, Object... values) {
		Query query = createQuery(hql, values);
		query.setParameterList(paramName, paramList);
		query.setMaxResults(size);
		return query.list();
	}

	@Override
	public List<Object> findParamListByHQL(String hql, Object... values) {
		Query query = createQuery(hql, values);
		List<Object> objArrayList = query.list();
		return objArrayList;
	}

	@Override
	public List<Object[]> findParamsListByHQL(String hql, Object... values) {
		Query query = createQuery(hql, values);
		List<Object[]> objArrayList = query.list();
		return objArrayList;
	}

	@Override
	public void update(Long[] ids, Map<String, Object> param) {
		if (param != null) {
			int index = 0;
			StringBuffer hql = new StringBuffer("update "
					+ getEntityClass().getName() + " set ");
			Iterator<String> params = param.keySet().iterator();
			while (params.hasNext()) {
				String temp = params.next();
				hql.append(temp + "=? ,");
			}
			hql.deleteCharAt(hql.length() - 1);
			if (ids != null) {
				hql.append("where ");
				for (long id : ids) {
					hql.append(getIdName(getEntityClass()) + "=" + id + " or ");
				}
			}

			int end = hql.lastIndexOf("or");
			Query query = getSession().createQuery(
					end != -1 ? hql.substring(0, end) : hql.toString());

			params = param.keySet().iterator();
			while (params.hasNext()) {
				String temp = params.next();
				query.setParameter(index, param.get(temp));
				index++;
			}

			query.executeUpdate();
		}
	}

	/**
	 * 取得对象的主键名,辅助函数.
	 */
	public String getIdName(Class clazz) {
		Assert.notNull(clazz);
		ClassMetadata meta = getSessionFactory().getClassMetadata(clazz);
		Assert.notNull(meta, "Class " + clazz
				+ " not define in hibernate session factory.");
		String idName = meta.getIdentifierPropertyName();
		Assert.hasText(idName, clazz.getSimpleName()
				+ " has no identifier property define.");
		return idName;
	}
	/**
	 * 通过sql，根据list类型的条件参数 查询对象数组集合
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public List<Object[]> findBySqlGetArray(final String sql,final List paramList) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			@Override
			public List<Object[]> doInHibernate(Session session)
					throws HibernateException, SQLException {
				SQLQuery sqlquery=session.createSQLQuery(sql);
				if(paramList!=null){
					for(int i=0;i<paramList.size();i++){
						sqlquery.setParameter(i, paramList.get(i));
					}
				}
				return sqlquery.list();
			}
		});
	}
}