package com.hongwei.futures.dao;

import java.util.List;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.SysBank;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface SysBankDao extends BaseDao<SysBank, Long> {
	public List<SysBank> findAllBank();
	public SysBank findBy(String name);
}

