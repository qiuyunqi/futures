package com.hongwei.futures.dao;

import java.util.List;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.FuNotice;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface FuNoticeDao extends BaseDao<FuNotice, Long> {

	public List<FuNotice> findNoticeList(int i, int j);

	public Integer countNotice();

}

