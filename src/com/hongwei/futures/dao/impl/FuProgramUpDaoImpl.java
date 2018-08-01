package com.hongwei.futures.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.FuProgramUpDao;
import com.hongwei.futures.model.FuProgram;
import com.hongwei.futures.model.FuProgramUp;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@Repository
public class FuProgramUpDaoImpl extends BaseDaoImpl<FuProgramUp, Long> implements FuProgramUpDao {

	@Override
	public List<FuProgramUp> findProgramUpList(int type, int status, Date closeTime) {
		String hql=" from FuProgramUp where type=? and status=? and closeTime<=? ";
		return this.findListByHQL(hql, type,status,closeTime);
	}

	@Override
	public List<FuProgramUp> findProgramUpByPidAndNoDeal(Long programId) {
		String hql=" from FuProgramUp where  fuProgram.id=? and type=1 and status=1 ";
		return this.findListByHQL(hql, programId);
	}

}

