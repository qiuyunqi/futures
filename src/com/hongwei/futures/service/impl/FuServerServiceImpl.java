package com.hongwei.futures.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.FuServerDao;
import com.hongwei.futures.model.FuServer;
import com.hongwei.futures.service.FuServerService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class FuServerServiceImpl implements FuServerService {
	
	@Autowired
	private FuServerDao fuServerDao;
	
	//====================== 基本 C R U D 方法  ===========================
	@Override
	public FuServer get(Long id) {
		return fuServerDao.get(id);
	}
	
	@Override
	public void save(FuServer entity) {
		fuServerDao.save(entity);
	}
	
	@Override
	public void delete(Long id) {
		fuServerDao.delete(id);
	}

	@Override
	public List<FuServer> fundList() {
		return fuServerDao.findListByServer();
	}
	
	@Override
	public void updateFuserver(FuServer fuServer){
		fuServerDao.update(fuServer);
		
	}
	
	@Override
	public FuServer findServerByName(String serverName){
		return fuServerDao.findByName(serverName);

	}
	
	public FuServer findServerByUserTypeId(Integer userTypeId){
		return fuServerDao.findServerByUserTypeId(userTypeId);
	}
	
}

