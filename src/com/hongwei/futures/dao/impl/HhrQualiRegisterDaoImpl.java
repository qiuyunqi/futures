package com.hongwei.futures.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.HhrQualiRegisterDao;
import com.hongwei.futures.model.HhrQualiRegister;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@Repository
public class HhrQualiRegisterDaoImpl extends BaseDaoImpl<HhrQualiRegister, Long> implements HhrQualiRegisterDao {

	@Override
	public List<HhrQualiRegister> findQualiByUser(Long userId, String type) {
		String hql = null;
		if("all".equals(type)){
			hql=" from HhrQualiRegister where fuUser.id=? order by id desc";
		}else{
			hql=" from HhrQualiRegister where isChecked=2 and fuUser.id=? order by id desc";
		}
		return this.findAllByHQL(hql, userId);
	}

	@Override
	public List<HhrQualiRegister> queryQualiList(int i, int pageSize, Map<String, Object> map) {
		String hql = "from HhrQualiRegister where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		if(map.containsKey("userName")){
			params.add(map.get("userName"));
			hql=hql+" and userName=? ";
		}
		if(map.containsKey("qualiNum")){
			params.add(map.get("qualiNum"));
			hql=hql+" and qualiNum=? ";
		}		
		return this.findListByHQL(i, pageSize, hql,params);
	}

	@Override
	public Integer countQuali(Map<String, Object> map) {
		String hql = "from HhrQualiRegister where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		if(map.containsKey("userName")){
			params.add(map.get("userName"));
			hql=hql+" and userName=? ";
		}
		if(map.containsKey("qualiNum")){
			params.add(map.get("qualiNum"));
			hql=hql+" and qualiNum=? ";
		}		
		return this.countQueryResult(hql,params);
	}

	@Override
	public int countQualiPerson(Map<String, Object> map) {
		String hql = "from HhrQualiRegister where 1=1 and isChecked=1";
		List<Object> params=new ArrayList<Object>();		
		return this.countQueryResult(hql,params);
	}

	@Override
	public List<HhrQualiRegister> countByUserAndType(Long userId, Integer type) {
		String hql = " from HhrQualiRegister where fuUser.id=? and type=?";
		return this.findAllByHQL(hql, userId, type);
	}

	@Override
	public List<HhrQualiRegister> countByQualiNum(String qualiNum) {
		String hql = " from HhrQualiRegister where quali_num=?";
		return this.findAllByHQL(hql, qualiNum);
	}
}

