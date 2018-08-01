package com.hongwei.futures.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongwei.futures.dao.FuOperationDao;
import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuOperation;
import com.hongwei.futures.service.FuOperationService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class FuOperationServiceImpl implements FuOperationService {
	
	@Autowired
	private FuOperationDao fuOperationDao;
	
	//====================== 基本 C R U D 方法  ===========================
	public FuOperation get(Long id) {
		return fuOperationDao.get(id);
	}
	
	public void save(FuOperation entity) {
		fuOperationDao.save(entity);
	}
	
	public void delete(Long id) {
		fuOperationDao.delete(id);
	}
	
	public void saveOperationRecord(String modelMain,String modelSub,Integer operation,Long opId,String beforeContent,String afterContent,FuAdmin fuAdmin){
		FuOperation fuOperation=new FuOperation();
		fuOperation.setModelMain(modelMain);
		fuOperation.setModelSub(modelSub);
		fuOperation.setOperation(operation);
		fuOperation.setOpId(opId);
		fuOperation.setBeforeContent(beforeContent);
		fuOperation.setAfterContent(afterContent);
		fuOperation.setFuAdmin(fuAdmin);
		fuOperation.setUpdateTime(new Date());
		fuOperationDao.save(fuOperation);
	}
	
	public Integer countOperation(Map<String, Object> map){
		return fuOperationDao.countOperation(map);
	}
	
	public List<FuOperation> findOperationlist(int i, int pageSize, Map<String, Object> map){
		return fuOperationDao.findOperationlist(i, pageSize, map);
	}
	
}

