package com.hongwei.futures.dao.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.FuAdminDao;
import com.hongwei.futures.model.FuAdmin;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@Repository
public class FuAdminDaoImpl extends BaseDaoImpl<FuAdmin, Long> implements FuAdminDao {

	@Override
	public FuAdmin findAdminByAccount(String account) {
		String hql=" from FuAdmin where state=1 and account=? ";
		return this.findUniqueByHQL(hql, account);
	}

	@Override
	public FuAdmin findLoginByToken(String token) {
		String hql=" from FuAdmin where state=1 and loginToken=? ";
		return this.findUniqueByHQL(hql, token);
	}

	@Override
	public List<Object[]> findList(int i,int pageSize,Map<String, Object> map) {
		String sql="SELECT a.id,a.account,a.name,a.type,a.job_desc,a.create_time,a.update_login_time,r.rolename FROM fu_admin a " +
				"LEFT JOIN sys_user_role u ON a.id=u.adminid "+
				"LEFT JOIN sys_role r ON r.id=u.roleid WHERE a.state=1 ";
		if(map.get("account")!=null){
			sql=sql+" and a.account like '%"+map.get("account")+"%'";
		}
		if(map.get("name")!=null){
			sql=sql+" and a.name like '%"+map.get("name")+"%'";
		}
		sql=sql+" order by a.id desc";
		if(this.findListBySql(i, pageSize, sql, null).size()>0){
		    return this.findListBySql(i, pageSize, sql, null);
		}else{
			return null;
		}
	}

	@Override
	public Integer getCount(Map<String, Object> map) {
		StringBuilder hqlB=new StringBuilder();
		hqlB.append("from FuAdmin where state=1");
		if(map.get("account")!=null){
			hqlB.append(" and account like '%"+map.get("account")+"%'");
		}
		if(map.get("name")!=null){
			hqlB.append(" and name like '%"+map.get("name")+"%'");
		}
		hqlB.append(" order by id desc");
		return this.countQueryResult(hqlB.toString());
	}

}

