package com.hongwei.futures.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.SysUserRoleDao;
import com.hongwei.futures.model.SysUserRole;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@Repository
public class SysUserRoleDaoImpl extends BaseDaoImpl<SysUserRole, Long> implements SysUserRoleDao {
	@Override  
    public List<SysUserRole> findUserRole(Long adminId) {  
		String hql=" from SysUserRole where fuAdmin.id=? ";
		if(this.findAllByHQL(hql, adminId).size()>0){
			return this.findAllByHQL(hql, adminId);
		}else{
			return null;
		}
    }

	
	public List<SysUserRole> findUserRoleByRoleId(Long roleId){
		String hql=" from SysUserRole where sysRole.id=? ";
		if(this.findAllByHQL(hql, roleId).size()>0){
			return this.findAllByHQL(hql, roleId);
		}else{
			return null;
		}
	}
}

