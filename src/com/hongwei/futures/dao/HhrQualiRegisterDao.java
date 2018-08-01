package com.hongwei.futures.dao;

import java.util.List;
import java.util.Map;

import com.hongwei.futures.dao.BaseDao;
import com.hongwei.futures.model.HhrQualiRegister;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface HhrQualiRegisterDao extends BaseDao<HhrQualiRegister, Long> {

	List<HhrQualiRegister> findQualiByUser(Long userId, String type);

	List<HhrQualiRegister> queryQualiList(int i, int pageSize, Map<String, Object> map);

	Integer countQuali(Map<String, Object> map);

	int countQualiPerson(Map<String, Object> map);

	List<HhrQualiRegister> countByUserAndType(Long userId, Integer type);

	List<HhrQualiRegister> countByQualiNum(String qualiNum);

}

