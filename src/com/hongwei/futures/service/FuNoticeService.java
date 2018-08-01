package com.hongwei.futures.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.hongwei.futures.model.FuNotice;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface FuNoticeService {
	
	//====================== 基本 C R U D 方法  ===========================
	public FuNotice get(Long id) ;
	
	public void save(FuNotice entity) ;
	
	public void delete(Long id) ;
	/**
	 * 查找公告
	 * @param i
	 * @param j
	 * @return
	 */
	public List<FuNotice> findNoticeList(int i, int j);
	/**
	 * 统计公告数目
	 * @return
	 */
	public Integer countNotice();
	
}

