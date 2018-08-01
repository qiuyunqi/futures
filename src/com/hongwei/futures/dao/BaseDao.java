package com.hongwei.futures.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.hongwei.futures.util.Pager;

/**
 * 
 * @author Administrator
 *
 * @param <T>
 * @param <ID>
 */
public interface BaseDao<T, ID extends Serializable> {
	
	/**
	 * 获得sessionFactory
	 * @return
	 */
	SessionFactory getSessionFactory();

	/**
	 * 获得session
	 * @return
	 */
	Session getSession();

	/**
	 * 获得全文检索用的session
	 * @return
	 */
//	FullTextSession getFullTextSession();
	
	/**
	 * 获得HibernateTemplate实例
	 * @return
	 */
	HibernateTemplate getHibernateTemplate();
	
	/**
	 * 获得JdbcTemplate实例
	 * @return
	 */
	JdbcTemplate getJdbcTemplate();
	
	/**
	 * 游离状态转换成持久状态，就是一个对象从新得到了hibernate的session
	 * @param id
	 * @return
	 */
	void reload(T entity);
	
	T merge(T entity);
	
	/**
	 * 根据id获得实体对象
	 * @param id
	 * @return
	 */
	T get(ID id);
	
	/**
	 * 保存实体
	 * @param entity
	 */
    void save(T entity);
    
	/**
	 * 更新实体
	 * @param entity
	 */
    void update(T entity);
  
    /**
     * 删除实体
     * @param entity
     */
    void delete(T entity);   
    
    /**
     * 根据id删除实体
     * @param id
     */
    void delete(ID id);
	
    /**
     * QBC查询方式，封装成Pager返回
     * @param criterions
     * @return
     */
	Pager<T> findByCriteria(final Criterion... criterions);
	
	/**
	 * QBC查询方式，封装成Pager返回
	 * @return
	 */
	Pager<T> findByCriteria();
	
	/**
	 * QBC查询方式，封装成Pager返回
	 * @param pageNo 页码
	 * @return
	 */
	Pager<T> findByCriteria(final int pageNo);
	
	/**
	 * QBC查询方式，封装成Pager返回
	 * @param pageNo 页码
	 * @param pageSize 每页显示数
	 * @return
	 */
	Pager<T> findByCriteria(final int pageNo, final int pageSize);
	
	/**
	 * QBC查询方式，封装成Pager返回
	 * @param mapParams 以map封装的查询参数
	 * @return
	 */
	Pager<T> findByParamMap(final Map<String, Object> mapParams); 
	
	/**
	 * 以map查询条件的查询，封装成Pager返回
	 * @param pageNo 页码
	 * @param mapParams 以map封装的查询参数
	 * @return
	 */
	Pager<T> findByParamMap(final int pageNo, final Map<String, Object> mapParams);
	
	/**
	 * QBC查询方式，封装成Pager返回
	 * @param pageNo 页码
	 * @param pageSize 每页显示数
	 * @param mapParams 以map封装的查询参数
	 * @return
	 */
	Pager<T> findByParamMap(final int pageNo, final int pageSize, final Map<String, Object> mapParams);
	
	/**
	 * QBC查询方式，封装成Pager返回
	 * @param pager 以pager封装的参数，主要取其中的页码和每页显示数来进行查询
	 * @param mapParams
	 * @return
	 */
	Pager<T> findByParamMap(final Pager<T> pager, final Map<String, Object> mapParams);
	
	/**
	 * QBC查询方式，封装成Pager返回
	 * @param pager 以pager封装的参数，主要取其中的页码和每页显示数来进行查询
	 * @param criterions Criterion参数列表
	 * @return
	 */
	Pager<T> findByCriteria(final Pager<T> pager, final Criterion... criterions);
	
	/**
	 * QBC查询方式，封装成Pager返回
	 * @param pager 以pager封装的参数，主要取其中的页码和每页显示数来进行查询
	 * @param orders 排序字段数组
	 * @param criterions Criterion参数列表
	 * @return
	 */
	Pager<T> findByCriteria(final Pager<T> pager, final Order[] orders, final Criterion... criterions);
	
	/**
	 * 根据一个属性进行查询
	 * @param propertyName 属性名
	 * @param value 属性值
	 * @return
	 */
	Pager<T> findByProperty(final String propertyName, final Object value);
	
	/**
	 * 根据一个实体对象的示例进行查询
	 * @param exampleInstance 实体对象的示例
	 * @param excludeProperty 排除在外的属性--即不根据这些属性进行查询
	 * @return
	 */
	Pager<T> findByExample(T exampleInstance, String... excludeProperty);
	
	/**
	 * 根据一个实体对象的示例进行查询
	 * @param pageNo 页码
	 * @param exampleInstance 实体对象的示例
	 * @param excludeProperty 排除在外的属性--即不根据这些属性进行查询
	 * @return
	 */
	Pager<T> findByExample(final int pageNo, T exampleInstance, String... excludeProperty);
	
	/**
	 * 根据一个实体对象的示例进行查询
	 * @param pageNo 页码
	 * @param pageSize 每页显示数
	 * @param exampleInstance 实体对象的示例
	 * @param excludeProperty 排除在外的属性--即不根据这些属性进行查询
	 * @return
	 */
	Pager<T> findByExample(final int pageNo, final int pageSize, T exampleInstance, String... excludeProperty);

	/**
	 * 根据一个实体对象的示例进行查询
	 * @param pager 以pager封装的参数，主要取其中的页码和每页显示数来进行查询
	 * @param exampleInstance 实体对象的示例
	 * @param excludeProperty 排除在外的属性--即不根据这些属性进行查询
	 * @return
	 */
	Pager<T> findByExample(final Pager<T> pager, T exampleInstance, String... excludeProperty);
	
	/**
	 * 根据SQL语句进行查询，并返回Pager对象
	 * @param pageNo 页码
	 * @param pageSize 每页显示数
	 * @param sqlString sql语句
	 * @param values 参数值
	 * @return
	 */
	Pager<T> findBySQL(final int pageNo, final int pageSize, final String sqlString, final Object... values);
	
	/**
	 * 根据HQL语句进行查询，并返回Pager对象
	 * @param hql hql语句
	 * @param values 参数值
	 * @return
	 */
	Pager<T> findByHQL(final String hql, final Object... values);
	
	/**
	 * 根据HQL语句进行查询，并返回Pager对象
	 * @param pageNo 页码
	 * @param hql hql语句
	 * @param values 参数值
	 * @return
	 */
	Pager<T> findByHQL(final int pageNo, final String hql, final Object... values);
	
	/**
	 * 根据HQL语句进行查询，并返回Pager对象
	 * @param pageNo 页码
	 * @param pageSize 每页显示数
	 * @param hql hql语句
	 * @param values 参数值
	 * @return
	 */
	Pager<T> findByHQL(final int pageNo, final int pageSize, final String hql, final Object... values);
	
	Pager<T> findByHQL(final int pageNo, final int pageSize, final String hql, final List<Object> valueList);

	/**
	 * 根据HQL语句进行查询，并返回Pager对象
	 * @param pager 以pager封装的参数，主要取其中的页码和每页显示数来进行查询
	 * @param hql hql语句
	 * @param values 参数值
	 * @return
	 */
	Pager<T> findByHQL(final Pager<T> pager, final String hql, final Object... values);
	
	/**
	 * QBC查询方式，返回List
	 * @param criterions
	 * @return
	 */
	List<T> findListByCriteria(final Criterion... criterions);
	
	/**
	 * QBC查询方式，返回List
	 * @param criterions
	 * @return
	 */
	List<T> findListByCriteria();
	
	/**
	 * QBC查询方式，返回List
	 * @param size 需要获取的结果数
	 * @return
	 */
	List<T> findListByCriteria(final int size);
	
	/**
	 * QBC查询方式，返回List
	 * @param fromIndex 起始索引
	 * @param size 需要获取的结果数
	 * @return
	 */
	List<T> findListByCriteria(final int fromIndex, final int size);
	
	/**
	 * 以map作为查询条件的查询，返回List
	 * @param mapParams 以map封装的查询参数
	 * @return
	 */
	List<T> findListByParamMap(final Map<String, Object> mapParams);
	
	/**
	 * 以map作为查询条件的查询，返回List
	 * @param size 需要获取的结果数
	 * @param mapParams 以map封装的查询参数
	 * @return
	 */
	List<T> findListByParamMap(final int size, final Map<String, Object> mapParams);
	
	/**
	 * 以map作为查询条件的查询，返回List
	 * @param fromIndex 起始索引
	 * @param size 需要获取的结果数
	 * @param mapParams 以map封装的查询参数
	 * @return
	 */
	List<T> findListByParamMap(final int fromIndex, final int size, final Map<String, Object> mapParams);
	
	/**
	 * QBC查询方式，返回List
	 * @param fromIndex 起始索引
	 * @param size 需要获取的结果数
	 * @param orders 排序字段列表
	 * @param criterions
	 * @return
	 */
	List<T> findListByCriteria(final int fromIndex, final int size, final Order[] orders, final Criterion... criterions);

	/**
	 * 通过属性进行查询
	 * @param propertyName 属性名称
	 * @param value 属性值
	 * @return
	 */
	List<T> findListByProperty(final String propertyName, final Object value);
	
	/**
	 * 根据一个实体对象的示例进行查询
	 * @param exampleInstance 实体示例
	 * @param excludeProperty 排除在外的属性列表
	 * @return
	 */
	List<T> findListByExample(T exampleInstance, String... excludeProperty);
	
	/**
	 * 根据一个实体对象的示例进行查询
	 * @param size 需要获取的结果数
	 * @param exampleInstance 实体示例
	 * @param excludeProperty 排除在外的属性列表
	 * @return
	 */
	List<T> findListByExample(final int size, T exampleInstance, String... excludeProperty);
	
	/**
	 * 根据一个实体对象的示例进行查询
	 * @param fromIndex 起始索引
	 * @param size 需要获取的结果数 
	 * @param exampleInstance 实体示例
	 * @param excludeProperty 排除在外的属性列表
	 * @return
	 */
	List<T> findListByExample(final int fromIndex, final int size, T exampleInstance, String... excludeProperty);

	/**
	 * 通过sql语句查询结果集
	 * @param fromIndex 起始索引
	 * @param size 需要获取的结果数 
	 * @param sqlString 实体示例
	 * @param values 排除在外的属性列表
	 * @return
	 */
	List<T> findListBySQL(final int fromIndex, final int size, final String sqlString, final Object... values);
	
	/**
	 * 通过hql语句查询结果集
	 * @param hql hql语句
	 * @param values 参数值列表
	 * @return
	 */
	List<T> findListByHQL(final String hql, final Object... values);
	
	/**
	 * 通过hql语句查询结果集
	 * @param size 需要获取的结果数
	 * @param hql hql语句
	 * @param values 参数值列表
	 * @return
	 */
	List<T> findListByHQL(final int size, final String hql, final Object... values);
	
	/**
	 * 通过hql语句查询结果集
	 * @param fromIndex 起始索引
	 * @param size 需要获取的结果数
	 * @param hql hql语句
	 * @param values 参数值列表
	 * @return
	 */
	List<T> findListByHQL(final int fromIndex, final int size, final String hql, final Object... values);
	
	List<T> findListByHQL(int size, String hql, List<Object> valueList);

	/**
	 * 通过hql语句查询唯一结果
	 * @param hql hql语句
	 * @param values 参数值列表
	 * @return
	 */
	T findUniqueByHQL(final String hql, final Object... values);
	
	/**
	 * 通过属性查询唯一结果
	 * @param propertyName 属性名
	 * @param value 属性值
	 * @return
	 */
	T findUniqueByProperty(final String propertyName, final Object value);
	
	/**
	 * 保存或修改集合参数中的所有实体对象
	 * @param collection
	 */
	void saveOrUpdateAll(final Collection<T> collection);
	
	/**
	 * 执行"?"占位符的HQL语句
	 * @param updateString
	 * @param values
	 */
	void executeUpdate(final String updateString, final Object...values);
	
	/**
	 * 执行带有"in"的HQL语句
	 * @param updateString
	 * @param paramName
	 * @param values
	 */
	void executeUpdateByParamList(final String updateString, final String paramName, final Object[] values);
	
	/**
	 * 执行带有参数名称的HQL语句
	 * @param updateString
	 * @param paramName
	 * @param values
	 */
	void executeUpdate(final String updateString, final String paramName, final Object value);
	
	/**
	 * 执行用Map封装参数的的HQL语句
	 * @param updateString
	 * @param paramMap
	 */
	void executeUpdate(final String updateString, final Map<String,Object> paramMap);
	
	/**
	 * 执行SQL语句
	 * @param sqlString
	 * @param values
	 */
	void executeSqlUpdate(final String sqlString, final Object... values);
	
	/**
	 * 通过SQL查询结果集
	 * @param sqlString
	 * @param values
	 * @return
	 */
	List<T> findListBySQL(final String sqlString, final Object... values);
	
	/**
	 * 通过DetachedCriteria查询结果集
	 * @param detachedCriteria
	 * @return
	 */
	List<T> findListByDetachedCriteria(DetachedCriteria detachedCriteria);
	
	/**
	 * 通过DetachedCriteria查询结果集,返回Pager对象
	 * @param pageNo 页码
	 * @param detachedCriteria
	 * @return
	 */
	Pager<T> findByDetachedCriteria(DetachedCriteria detachedCriteria);
	
	/**
	 * 通过DetachedCriteria查询结果集,返回Pager对象
	 * @param pageNo 页码
	 * @param detachedCriteria
	 * @return
	 */
	Pager<T> findByDetachedCriteria(int pageNo, DetachedCriteria detachedCriteria);
	
	/**
	 * 通过DetachedCriteria查询结果集,返回Pager对象
	 * @param pageNo 页码
	 * @param pageSize 每页显示的结果数
	 * @param detachedCriteria
	 * @return
	 */
	Pager<T> findByDetachedCriteria(int pageNo, int pageSize, DetachedCriteria detachedCriteria);
	
	/**
	 * 查询所有结果
	 */
	List<T> findAll();
	
	/**
	 * 通过hql查询所有结果
	 */
	List<T> findAllByHQL(String hql, final Object... values);
	
	/**
	 * 通过in查询所有结果,返回分页
	 */
	Pager<T> findByParamList(int pageNo, int pageSize, String hql,String paramName, Object[] paramList, Object... values);
	
	/**
	 * 通过in查询结果
	 */
	List<T> findListByParamList(int size, String hql,String paramName, Object[] paramList, Object... values);
	
	/**
	 * count查询
	 * @param crit
	 * @return
	 */
	public int countQueryResult(Criteria crit) ;
	
	public List<Object[]> findListBySql(final int fromIndex, final int size, final String sql, final List paramList);
	
	public int countQueryResult(String hql, Object... values) ;
	
	public int countParamListQueryResult(String hql, String paramName,Object[] paramList, Object... values) ;
	
	public int countSqlQueryResult(String sql, Object... values) ;
	
	public List<Object> findParamListByHQL(String hql, Object... values);
	
	public List<Object[]> findParamsListByHQL(String hql, Object... values);
	
	/**
	 * map (key 为数据库字段 value 为修改的值) ids为空表示修改所有 
	 * 根据主建批量更新 支持多字段
	 * 支持修改字段类型int long String byte
	 */
	public void update(Long[] ids, Map<String, Object> param);
	
	/**
	 * 通过sql，根据list类型的条件参数 查询对象数组集合
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<Object[]> findBySqlGetArray(String sql,List paramList);
}

