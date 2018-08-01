package com.hongwei.futures.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.SysBranchDao;
import com.hongwei.futures.model.SysBranch;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@Repository
public class SysBranchDaoImpl extends BaseDaoImpl<SysBranch, Long> implements SysBranchDao {
	@Override
	public List<SysBranch> findBranchByCity(Long bankId, Long cityId) {
		String hql = "from SysBranch where bankId=? and cityId=?";
		return this.findAllByHQL(hql, bankId,cityId);
	}

	@Override
	public SysBranch findBy(Long bankId, Long cityId, String branchName,String branchAddress,String branchPhone) {
		String hql = "from SysBranch where bankId=? and cityId=? and branchName=? and branchAddress=? and branchPhone=?";
		return this.findUniqueByHQL(hql, bankId,cityId,branchName,branchAddress,branchPhone);
	}

	@Override
	public SysBranch findByParams(Long bankId, Long cityId, String branchName) {
		String hql = "from SysBranch where bankId=? and cityId=? and branchName=?";
		return this.findUniqueByHQL(hql, bankId,cityId,branchName);
	}
}

