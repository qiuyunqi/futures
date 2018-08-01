package com.hongwei.futures.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.FuProgram;
import com.hongwei.futures.model.FuProgramUp;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface FuProgramUpService {
	
	//====================== 基本 C R U D 方法  ===========================
	public FuProgramUp get(Long id) ;
	
	public void save(FuProgramUp entity) ;
	
	public void delete(Long id) ;
	/**
	 * 根据类型,状态,结束时间查找升级记录
	 * @param status 
	 * @param type 
	 * @param closeTime 
	 * @return
	 */
	public List<FuProgramUp> findProgramUpList(int type, int status, Date closeTime);
	
	/**
	 * 根据方案查找未结束的临时升级记录
	 * @param id
	 * @return
	 */
	public List<FuProgramUp> findProgramUpByPidAndNoDeal(Long programId);
	
}

