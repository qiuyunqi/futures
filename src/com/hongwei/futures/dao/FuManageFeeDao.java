package com.hongwei.futures.dao;

import java.util.HashMap;
import java.util.List;
import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.FuManageFee;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface FuManageFeeDao extends BaseDao<FuManageFee, Long> {

	public Integer countFee(HashMap<String, Object> map);

	public List<FuManageFee> findFeeList(int i, int pageSize,
			HashMap<String, Object> map);

	public List<FuManageFee> findFeeListByProgramId(Long id);
	
	public FuManageFee findfeeByProidLast(Long id);

}

