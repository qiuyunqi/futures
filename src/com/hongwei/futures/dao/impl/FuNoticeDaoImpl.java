package com.hongwei.futures.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.FuNoticeDao;
import com.hongwei.futures.model.FuNotice;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@Repository
public class FuNoticeDaoImpl extends BaseDaoImpl<FuNotice, Long> implements FuNoticeDao {

	@Override
	public List<FuNotice> findNoticeList(int i, int j) {
		String hql=" from FuNotice where state=1 order by id desc";
		return this.findListByHQL(hql, i,j);
	}

	@Override
	public Integer countNotice() {
		String hql=" from FuNotice where state=1 ";
		return this.countQueryResult(hql);
	}

}

