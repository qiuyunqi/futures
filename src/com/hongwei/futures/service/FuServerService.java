package com.hongwei.futures.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.hongwei.futures.model.FuServer;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface FuServerService {
	
	//====================== 基本 C R U D 方法  ===========================
	public FuServer get(Long id) ;
	
	public void save(FuServer entity) ;
	
	public void delete(Long id) ;
	
	public List<FuServer> fundList();
	
	public void updateFuserver(FuServer fuServer);
	
	public FuServer findServerByName(String serverName);
	
	public FuServer findServerByUserTypeId(Integer userTypeId);
	
}

