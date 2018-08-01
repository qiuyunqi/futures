package com.hongwei.futures.dao;

import java.util.Date;
import java.util.List;
import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.FuProgramUp;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface FuProgramUpDao extends BaseDao<FuProgramUp, Long> {

	public List<FuProgramUp> findProgramUpList(int type, int status, Date closeTime);

	public List<FuProgramUp> findProgramUpByPidAndNoDeal(Long programId);

}

