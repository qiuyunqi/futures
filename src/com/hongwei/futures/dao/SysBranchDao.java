package com.hongwei.futures.dao;

import java.util.List;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.SysBranch;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface SysBranchDao extends BaseDao<SysBranch, Long> {
	public List<SysBranch> findBranchByCity(Long bankId,Long cityId);
	public SysBranch findBy(Long bankId,Long cityId,String branchName,String branchAddress,String branchPhone);
	public SysBranch findByParams(Long bankId, Long cityId, String branchName);

}

