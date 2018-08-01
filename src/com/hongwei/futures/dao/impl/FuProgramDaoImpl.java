package com.hongwei.futures.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.hongwei.futures.dao.FuProgramDao;
import com.hongwei.futures.model.FuProgram;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@Repository
public class FuProgramDaoImpl extends BaseDaoImpl<FuProgram, Long> implements FuProgramDao {

	@Override
	public List<FuProgram> findProgramUpByProgram(Long id) {
		String hql=" from FuProgram where addMatchId=?";
		return this.findAllByHQL(hql, id);
	}
	
	@Override
	public List<FuProgram> findSonProgramByProgram(Long id) {
		String hql=" from FuProgram where addMatchId=? and status in (0,1,2,4,5,6,7)";
		return this.findAllByHQL(hql, id);
	}
	
	@Override
	public List<FuProgram> findProgramIsAcrossCabin() {
		String hql=" from FuProgram status=2";
		return this.findAllByHQL(hql);
	}
	
	@Override
	public List<FuProgram> findToTradeByUser(Long userId, int programType) {
		String hql;
		if(programType==1){
			hql=" from FuProgram where status in (-1,0,1,2,3,4,6,7) and fuUser.id=? and programType=? order by id desc ";
		}else{
			hql=" from FuProgram where status in (-1,0,1,2,3,4,6,7) and fuUser.id=? and programType>=? order by id desc ";
		}
		return this.findAllByHQL(hql, userId,programType);
	}
	
	@Override
	public Integer countProgramByUser2(Map<String, Object> map){
		List<Object> params= new ArrayList<Object>();
		String hql=" from FuProgram where 1=1 ";
		if(map.containsKey("userId")){
			params.add(map.get("userId"));
			hql=hql+" and fuUser.id=? ";
		}
		if(map.containsKey("programType")){
			params.add(map.get("programType"));
			hql=hql+" and programType=? ";
		}
		hql=hql+" and status in (0,1,2,4,6) ";
		return this.countQueryResult(hql, params);
	}

	@Override
	public Integer countProgramByUser(Map<String, Object> map) {
		List<Object> params= new ArrayList<Object>();
		String hql=" from FuProgram where 1=1 ";
		if(map.containsKey("userId")){
			params.add(map.get("userId"));
			hql=hql+" and fuUser.id=? ";
		}
		if(map.containsKey("programType")){
			params.add(map.get("programType"));
			if(map.get("programType").toString().equals("1")){
				hql=hql+" and programType=? ";
			}else{
				if(map.containsKey("type")){
					hql=hql+" and programType=? ";
				}else{
					hql=hql+" and programType>=? ";
				}
				
			}
		}
		if(map.containsKey("flag")){
			int flag=Integer.parseInt(map.get("flag").toString());
			if(flag==1){
				hql=hql+" and status<3 ";
			}else if(flag==2){
				hql=hql+" and status=4 ";
			}else if(flag==3){
				hql=hql+" and status=3 ";
			}else if(flag==4){
				hql=hql+" and status=5 ";
			}
		}
		return this.countQueryResult(hql, params);
	}

	@Override
	public List<FuProgram> findOpenProgramByParams(){
		String hql=" from FuProgram where status=1 and openPaymentId>0 ";
		return this.findAllByHQL(hql);
	}
	
	@Override
	public List<FuProgram> findProgramByUser(int i, int pageSize,
			Map<String, Object> map) {
		List<Object> params= new ArrayList<Object>();
		String hql=" from FuProgram where 1=1 ";
		if(map.containsKey("userId")){
			params.add(map.get("userId"));
			hql=hql+" and fuUser.id=? ";
		}
		if(map.containsKey("programType")){
			params.add(map.get("programType"));
			if(map.get("programType").toString().equals("1")){
				hql=hql+" and programType=? ";
			}else{
				hql=hql+" and programType>=? ";
			}
		}
		if(map.containsKey("flag")){
			int flag=Integer.parseInt(map.get("flag").toString());
			if(flag==1){
				hql=hql+" and status in (0,1,2,6) ";
			}else if(flag==2){
				hql=hql+" and status=4 ";
			}else if(flag==3){
				hql=hql+" and status=3 ";
			}else if(flag==4){
				hql=hql+" and status=5 ";
			}
		}
		hql=hql+" order by id desc ";
		return this.findListByHQL(i, pageSize, hql, params);
	}

	@Override
	public List<FuProgram> findAllTradeProgram() {
		String hql=" from FuProgram where status=2 order by id desc ";
		return this.findAllByHQL(hql);
	}
	
	@Override
	public List<FuProgram> findAllTradeSonProgram(){
		String hql=" from FuProgram where status=2 and addMatchId is not null order by id desc ";
		return this.findAllByHQL(hql);
	}
	
	@Override
	public List<FuProgram> findAllTradeProgram(Map<String, Object> map) {
		String hql=" from FuProgram where status=2 and addMatchId is null";
		if(map.containsKey("programType")){
			hql=hql+" and programType= " + map.get("programType");
		}
		hql=hql+" order by id desc";
		return this.findAllByHQL(hql);
	}
	
	@Override
	public List<FuProgram> findAllDueProgram() {
		String hql=" from FuProgram where status=0 order by id desc";
		return this.findAllByHQL(hql);
	}

	/**
	 *方案管理数目
	 */
	@Override
	public Integer countProgram(Map<String, Object> map) {
		List<Object> params= new ArrayList<Object>();
		String hql=" from FuProgram where 1=1 ";
		if(map.containsKey("programId")){
			params.add(map.get("programId"));
			hql=hql+" and id=? ";
		}
		if(map.containsKey("programType")){
			params.add(map.get("programType"));
			hql=hql+" and programType=? ";
		}
		if(map.containsKey("status")){
			params.add(map.get("status"));
			hql=hql+" and status=? ";
		}
		if(map.containsKey("userName")){
			params.add(map.get("userName"));
			hql=hql+" and fuUser.userName=? ";
		}
		
		if(map.containsKey("safeMoney1")){
			params.add(map.get("safeMoney1"));
			hql=hql+" and safeMoney >= ? ";
		}
		
		if(map.containsKey("safeMoney2")){
			params.add(map.get("safeMoney2"));
			hql=hql+" and safeMoney <= ? ";
		}
		if(map.containsKey("doubleNum1")){
			params.add(map.get("doubleNum1"));
			hql=hql+" and doubleNum >= ? ";
		}
		if(map.containsKey("doubleNum2")){
			params.add(map.get("doubleNum2"));
			hql=hql+" and doubleNum <= ? ";
		}
		if(map.containsKey("matchMoney1")){
			params.add(map.get("matchMoney1"));
			hql=hql+" and matchMoney >= ? ";
		}
		if(map.containsKey("matchMoney2")){
			params.add(map.get("matchMoney2"));
			hql=hql+" and matchMoney <= ? ";
		}
		if(map.containsKey("cycleNum1")){
			params.add(map.get("cycleNum1"));
			hql=hql+" and cycleNum >= ? ";
		}
		if(map.containsKey("cycleNum2")){
			params.add(map.get("cycleNum2"));
			hql=hql+" and cycleNum <= ? ";
		}
		
		if(map.containsKey("feePecent1")){
			params.add(map.get("feePecent1"));
			hql=hql+" and moneyPercent >= ? ";
		}
		if(map.containsKey("feePecent2")){
			params.add(map.get("feePecent2"));
			hql=hql+" and moneyPercent <= ? ";
		}
		if(map.containsKey("managerMoney1")){
			params.add(map.get("managerMoney1"));
			hql=hql+" and manageMoney >= ? ";
		}
		if(map.containsKey("managerMoney2")){
			params.add(map.get("managerMoney2"));
			hql=hql+" and manageMoney <= ? ";
		}
		if(map.containsKey("time1")){
			params.add(map.get("time1"));
			hql=hql+" and createTime >= ? ";
		}
		if(map.containsKey("time2")){
			params.add(map.get("time2"));
			hql=hql+" and createTime <= ? ";
		}
		
		return this.countQueryResult(hql, params);
	}
    /**
     * 后台方案列表
     */
	@Override
	public List<FuProgram> findProgramList(int i, int pageSize,
			Map<String, Object> map) {
		List<Object> params= new ArrayList<Object>();
		String hql=" from FuProgram where 1=1 ";
		if(map.containsKey("programId")){
			params.add(map.get("programId"));
			hql=hql+" and id=? ";
		}
		if(map.containsKey("programType")){
			params.add(map.get("programType"));
			hql=hql+" and programType=? ";
		}
		if(map.containsKey("status")){
			params.add(map.get("status"));
			hql=hql+" and status=? ";
		}
		if(map.containsKey("userName")){
			params.add(map.get("userName"));
			hql=hql+" and fuUser.userName=? ";
		}
		if(map.containsKey("safeMoney1")){
			params.add(map.get("safeMoney1"));
			hql=hql+" and safeMoney >= ? ";
		}
		if(map.containsKey("safeMoney2")){
			params.add(map.get("safeMoney2"));
			hql=hql+" and safeMoney <= ? ";
		}
		if(map.containsKey("doubleNum1")){
			params.add(map.get("doubleNum1"));
			hql=hql+" and doubleNum >= ? ";
		}
		if(map.containsKey("doubleNum2")){
			params.add(map.get("doubleNum2"));
			hql=hql+" and doubleNum <= ? ";
		}
		if(map.containsKey("matchMoney1")){
			params.add(map.get("matchMoney1"));
			hql=hql+" and matchMoney >= ? ";
		}
		if(map.containsKey("matchMoney2")){
			params.add(map.get("matchMoney2"));
			hql=hql+" and matchMoney <= ? ";
		}
		if(map.containsKey("cycleNum1")){
			params.add(map.get("cycleNum1"));
			hql=hql+" and cycleNum >= ? ";
		}
		if(map.containsKey("cycleNum2")){
			params.add(map.get("cycleNum2"));
			hql=hql+" and cycleNum <= ? ";
		}
		if(map.containsKey("feePecent1")){
			params.add(map.get("feePecent1"));
			hql=hql+" and moneyPercent >= ? ";
		}
		if(map.containsKey("feePecent2")){
			params.add(map.get("feePecent2"));
			hql=hql+" and moneyPercent <= ? ";
		}
		if(map.containsKey("managerMoney1")){
			params.add(map.get("managerMoney1"));
			hql=hql+" and manageMoney >= ? ";
		}
		if(map.containsKey("managerMoney2")){
			params.add(map.get("managerMoney2"));
			hql=hql+" and manageMoney <= ? ";
		}
		if(map.containsKey("time1")){
			params.add(map.get("time1"));
			hql=hql+" and createTime >= ? ";
		}
		if(map.containsKey("time2")){
			params.add(map.get("time2"));
			hql=hql+" and createTime <= ? ";
		}
		hql=hql+" order by id desc ";
		return this.findListByHQL(i, pageSize, hql, params);
	}
	
	/**
	 *待审核方案数目
	 */
	@Override
	public Integer countWaitProgram(Map<String, Object> map) {
		List<Object> params= new ArrayList<Object>();
		String hql=" from FuProgram where status=0 ";
		if(map.containsKey("programId")){
			params.add(map.get("programId"));
			hql=hql+" and id=? ";
		}
		if(map.containsKey("programType")){
			params.add(map.get("programType"));
			hql=hql+" and programType=? ";
		}
		if(map.containsKey("userName")){
			params.add(map.get("userName"));
			hql=hql+" and fuUser.userName=? ";
		}
		
		if(map.containsKey("safeMoney1")){
			params.add(map.get("safeMoney1"));
			hql=hql+" and safeMoney >= ? ";
		}
		
		if(map.containsKey("safeMoney2")){
			params.add(map.get("safeMoney2"));
			hql=hql+" and safeMoney <= ? ";
		}
		if(map.containsKey("doubleNum1")){
			params.add(map.get("doubleNum1"));
			hql=hql+" and doubleNum >= ? ";
		}
		if(map.containsKey("doubleNum2")){
			params.add(map.get("doubleNum2"));
			hql=hql+" and doubleNum <= ? ";
		}
		if(map.containsKey("matchMoney1")){
			params.add(map.get("matchMoney1"));
			hql=hql+" and matchMoney >= ? ";
		}
		if(map.containsKey("matchMoney2")){
			params.add(map.get("matchMoney2"));
			hql=hql+" and matchMoney <= ? ";
		}
		if(map.containsKey("cycleNum1")){
			params.add(map.get("cycleNum1"));
			hql=hql+" and cycleNum >= ? ";
		}
		if(map.containsKey("cycleNum2")){
			params.add(map.get("cycleNum2"));
			hql=hql+" and cycleNum <= ? ";
		}
		
		if(map.containsKey("feePecent1")){
			params.add(map.get("feePecent1"));
			hql=hql+" and moneyPercent >= ? ";
		}
		if(map.containsKey("feePecent2")){
			params.add(map.get("feePecent2"));
			hql=hql+" and moneyPercent <= ? ";
		}
		if(map.containsKey("managerMoney1")){
			params.add(map.get("managerMoney1"));
			hql=hql+" and manageMoney >= ? ";
		}
		if(map.containsKey("managerMoney2")){
			params.add(map.get("managerMoney2"));
			hql=hql+" and manageMoney <= ? ";
		}
		if(map.containsKey("time1")){
			params.add(map.get("time1"));
			hql=hql+" and createTime >= ? ";
		}
		if(map.containsKey("time2")){
			params.add(map.get("time2"));
			hql=hql+" and createTime <= ? ";
		}
		return this.countQueryResult(hql, params);
	}
    /**
     * 待审核方案列表
     */
	@Override
	public List<FuProgram> findWaitProgramList(int i, int pageSize,
			Map<String, Object> map) {
		List<Object> params= new ArrayList<Object>();
		String hql=" from FuProgram where status=0 ";
		if(map.containsKey("programId")){
			params.add(map.get("programId"));
			hql=hql+" and id=? ";
		}
		if(map.containsKey("programType")){
			params.add(map.get("programType"));
			hql=hql+" and programType=? ";
		}
		if(map.containsKey("userName")){
			params.add(map.get("userName"));
			hql=hql+" and fuUser.userName=? ";
		}
		
		if(map.containsKey("safeMoney1")){
			params.add(map.get("safeMoney1"));
			hql=hql+" and safeMoney >= ? ";
		}
		
		if(map.containsKey("safeMoney2")){
			params.add(map.get("safeMoney2"));
			hql=hql+" and safeMoney <= ? ";
		}
		if(map.containsKey("doubleNum1")){
			params.add(map.get("doubleNum1"));
			hql=hql+" and doubleNum >= ? ";
		}
		if(map.containsKey("doubleNum2")){
			params.add(map.get("doubleNum2"));
			hql=hql+" and doubleNum <= ? ";
		}
		if(map.containsKey("matchMoney1")){
			params.add(map.get("matchMoney1"));
			hql=hql+" and matchMoney >= ? ";
		}
		if(map.containsKey("matchMoney2")){
			params.add(map.get("matchMoney2"));
			hql=hql+" and matchMoney <= ? ";
		}
		if(map.containsKey("cycleNum1")){
			params.add(map.get("cycleNum1"));
			hql=hql+" and cycleNum >= ? ";
		}
		if(map.containsKey("cycleNum2")){
			params.add(map.get("cycleNum2"));
			hql=hql+" and cycleNum <= ? ";
		}
		if(map.containsKey("feePecent1")){
			params.add(map.get("feePecent1"));
			hql=hql+" and moneyPercent >= ? ";
		}
		if(map.containsKey("feePecent2")){
			params.add(map.get("feePecent2"));
			hql=hql+" and moneyPercent <= ? ";
		}
		if(map.containsKey("managerMoney1")){
			params.add(map.get("managerMoney1"));
			hql=hql+" and manageMoney >= ? ";
		}
		if(map.containsKey("managerMoney2")){
			params.add(map.get("managerMoney2"));
			hql=hql+" and manageMoney <= ? ";
		}
		if(map.containsKey("time1")){
			params.add(map.get("time1"));
			hql=hql+" and createTime >= ? ";
		}
		if(map.containsKey("time2")){
			params.add(map.get("time2"));
			hql=hql+" and createTime <= ? ";
		}
		hql=hql+" order by id asc ";
		return this.findListByHQL(i, pageSize, hql, params);
	}
	
	@Override
	public List<FuProgram> findProgramByParams(Map<String, Object> map) {
		List<Object> params= new ArrayList<Object>();
		String hql=" from FuProgram where 1=1 ";
		if(map.containsKey("programId")){
			params.add(map.get("programId"));
			hql=hql+" and id=? ";
		}
		if(map.containsKey("programType")){
			params.add(map.get("programType"));
			hql=hql+" and programType=? ";
		}
		if(map.containsKey("status")){
			params.add(map.get("status"));
			hql=hql+" and status=? ";
		}
		if(map.containsKey("userName")){
			params.add(map.get("userName"));
			hql=hql+" and fuUser.userName=? ";
		}
		if(map.containsKey("safeMoney1")){
			params.add(map.get("safeMoney1"));
			hql=hql+" and safeMoney >= ? ";
		}
		if(map.containsKey("safeMoney2")){
			params.add(map.get("safeMoney2"));
			hql=hql+" and safeMoney <= ? ";
		}
		if(map.containsKey("doubleNum1")){
			params.add(map.get("doubleNum1"));
			hql=hql+" and doubleNum >= ? ";
		}
		if(map.containsKey("doubleNum2")){
			params.add(map.get("doubleNum2"));
			hql=hql+" and doubleNum <= ? ";
		}
		if(map.containsKey("matchMoney1")){
			params.add(map.get("matchMoney1"));
			hql=hql+" and matchMoney >= ? ";
		}
		if(map.containsKey("matchMoney2")){
			params.add(map.get("matchMoney2"));
			hql=hql+" and matchMoney <= ? ";
		}
		if(map.containsKey("cycleNum1")){
			params.add(map.get("cycleNum1"));
			hql=hql+" and cycleNum >= ? ";
		}
		if(map.containsKey("cycleNum2")){
			params.add(map.get("cycleNum2"));
			hql=hql+" and cycleNum <= ? ";
		}
		if(map.containsKey("feePecent1")){
			params.add(map.get("feePecent1"));
			hql=hql+" and moneyPercent >= ? ";
		}
		if(map.containsKey("feePecent2")){
			params.add(map.get("feePecent2"));
			hql=hql+" and moneyPercent <= ? ";
		}
		if(map.containsKey("managerMoney1")){
			params.add(map.get("managerMoney1"));
			hql=hql+" and manageMoney >= ? ";
		}
		if(map.containsKey("managerMoney2")){
			params.add(map.get("managerMoney2"));
			hql=hql+" and manageMoney <= ? ";
		}
		if(map.containsKey("time1")){
			params.add(map.get("time1"));
			hql=hql+" and createTime >= ? ";
		}
		if(map.containsKey("time2")){
			params.add(map.get("time2"));
			hql=hql+" and createTime <= ? ";
		}
		hql=hql+" order by id asc ";
		return this.findAllByHQL(hql, params);
	}

	@Override
	public FuProgram findGameProgram(Long userId, Long gameId) {
		String hql=" from FuProgram where fuUser.id=? and game.id=? ";
		return this.findUniqueByHQL(hql, userId,gameId);
	}

	@Override
	public List<FuProgram> findGameOrder(Long gameId, int flag) {
		String hql=" from FuProgram where status!=3 and game.id=? ";
		if(flag==1){
			hql= hql+" order by gameIncomeWeek desc "; 
		}else{
			hql= hql+" order by gameIncomeMonth desc "; 
		}
		return this.findListByHQL(0, 5, hql, gameId);
	}

	@Override
	public Integer countGameProgram(Map<String, Object> map) {
		List<Object> params =new ArrayList<Object>();
		params.add(map.get("gameId"));
		String hql=" from FuProgram where status!=3 and game.id=? ";
		
		if(map.containsKey("userId")){
			params.add(map.get("userId"));
			hql=hql+" and fuUser.id=? ";
		}
		if(map.containsKey("userName")){
			params.add(map.get("userName"));
			hql=hql+" and fuUser.userName=? ";
		}
		return this.countQueryResult(hql, params);
	}

	@Override
	public List<FuProgram> findGameProgramList(int i, int pageSize,
			Map<String, Object> map) {
		List<Object> params =new ArrayList<Object>();
		params.add(map.get("gameId"));
		String hql=" from FuProgram where status!=3 and game.id=? ";
		
		if(map.containsKey("userId")){
			params.add(map.get("userId"));
			hql=hql+" and fuUser.id=? ";
		}
		if(map.containsKey("userName")){
			params.add(map.get("userName"));
			hql=hql+" and fuUser.userName=? ";
		}
		hql=hql+" order by id desc ";
		return this.findListByHQL(i, pageSize, hql, params);
	}

	@Override
	public List<FuProgram> findOverProgramList(Map<String, Object> map) {
		List<Object> params =new ArrayList<Object>();
		String hql=" from FuProgram where status=5 ";
		if(map.containsKey("userId")){
			params.add(map.get("userId"));
			hql=hql+" and fuUser.id=? ";
		}
		hql=hql+" order by id desc ";
		return this.findAllByHQL(hql, params);
	}

	@Override
	public Integer countWaitOverProgram(Map<String, Object> map) {
		List<Object> params= new ArrayList<Object>();
		String hql=" from FuProgram where  1=1  ";
		
		if(map.containsKey("programId")){
			params.add(map.get("programId"));
			hql=hql+" and id=? ";
		}
		if(map.containsKey("programType")){
			params.add(map.get("programType"));
			hql=hql+" and programType=? ";
		}
		if(map.containsKey("status")){
			params.add(map.get("status"));
			hql=hql+" and status=? ";
		}else{
			hql=hql+" and status in (2,4,5) ";
		}
		if(map.containsKey("userName")){
			params.add(map.get("userName"));
			hql=hql+" and fuUser.userName=? ";
		}
		if(map.containsKey("safeMoney1")){
			params.add(map.get("safeMoney1"));
			hql=hql+" and safeMoney >= ? ";
		}
		if(map.containsKey("safeMoney2")){
			params.add(map.get("safeMoney2"));
			hql=hql+" and safeMoney <= ? ";
		}
		if(map.containsKey("doubleNum1")){
			params.add(map.get("doubleNum1"));
			hql=hql+" and doubleNum >= ? ";
		}
		if(map.containsKey("doubleNum2")){
			params.add(map.get("doubleNum2"));
			hql=hql+" and doubleNum <= ? ";
		}
		if(map.containsKey("matchMoney1")){
			params.add(map.get("matchMoney1"));
			hql=hql+" and matchMoney >= ? ";
		}
		if(map.containsKey("matchMoney2")){
			params.add(map.get("matchMoney2"));
			hql=hql+" and matchMoney <= ? ";
		}
		if(map.containsKey("cycleNum1")){
			params.add(map.get("cycleNum1"));
			hql=hql+" and cycleNum >= ? ";
		}
		if(map.containsKey("cycleNum2")){
			params.add(map.get("cycleNum2"));
			hql=hql+" and cycleNum <= ? ";
		}
		
		if(map.containsKey("feePecent1")){
			params.add(map.get("feePecent1"));
			hql=hql+" and moneyPercent >= ? ";
		}
		if(map.containsKey("feePecent2")){
			params.add(map.get("feePecent2"));
			hql=hql+" and moneyPercent <= ? ";
		}
		if(map.containsKey("managerMoney1")){
			params.add(map.get("managerMoney1"));
			hql=hql+" and manageMoney >= ? ";
		}
		if(map.containsKey("managerMoney2")){
			params.add(map.get("managerMoney2"));
			hql=hql+" and manageMoney <= ? ";
		}
		if(map.containsKey("time1")){
			params.add(map.get("time1"));
			hql=hql+" and clearTime >= ? ";
		}
		if(map.containsKey("time2")){
			params.add(map.get("time2"));
			hql=hql+" and clearTime <= ? ";
		}
		return this.countQueryResult(hql, params);
	}

	/**
	 * 待结算方案的列表
	 */
	@Override
	public List<FuProgram> findWaitOverProgramList(int i, int pageSize,
			Map<String, Object> map) {
		List<Object> params= new ArrayList<Object>();
		String hql=" from FuProgram where 1=1 ";
		
		if(map.containsKey("programId")){
			params.add(map.get("programId"));
			hql=hql+" and id=? ";
		}
		if(map.containsKey("programType")){
			params.add(map.get("programType"));
			hql=hql+" and programType=? ";
		}
		if(map.containsKey("status")){
			params.add(map.get("status"));
			hql=hql+" and status=? ";
		}else{
			hql=hql+" and status in (2,4,5) ";
		}
		if(map.containsKey("userName")){
			params.add(map.get("userName"));
			hql=hql+" and fuUser.userName=? ";
		}
		if(map.containsKey("safeMoney1")){
			params.add(map.get("safeMoney1"));
			hql=hql+" and safeMoney >= ? ";
		}
		if(map.containsKey("safeMoney2")){
			params.add(map.get("safeMoney2"));
			hql=hql+" and safeMoney <= ? ";
		}
		if(map.containsKey("doubleNum1")){
			params.add(map.get("doubleNum1"));
			hql=hql+" and doubleNum >= ? ";
		}
		if(map.containsKey("doubleNum2")){
			params.add(map.get("doubleNum2"));
			hql=hql+" and doubleNum <= ? ";
		}
		if(map.containsKey("matchMoney1")){
			params.add(map.get("matchMoney1"));
			hql=hql+" and matchMoney >= ? ";
		}
		if(map.containsKey("matchMoney2")){
			params.add(map.get("matchMoney2"));
			hql=hql+" and matchMoney <= ? ";
		}
		if(map.containsKey("cycleNum1")){
			params.add(map.get("cycleNum1"));
			hql=hql+" and cycleNum >= ? ";
		}
		if(map.containsKey("cycleNum2")){
			params.add(map.get("cycleNum2"));
			hql=hql+" and cycleNum <= ? ";
		}
		if(map.containsKey("feePecent1")){
			params.add(map.get("feePecent1"));
			hql=hql+" and moneyPercent >= ? ";
		}
		if(map.containsKey("feePecent2")){
			params.add(map.get("feePecent2"));
			hql=hql+" and moneyPercent <= ? ";
		}
		if(map.containsKey("managerMoney1")){
			params.add(map.get("managerMoney1"));
			hql=hql+" and manageMoney >= ? ";
		}
		if(map.containsKey("managerMoney2")){
			params.add(map.get("managerMoney2"));
			hql=hql+" and manageMoney <= ? ";
		}
		if(map.containsKey("time1")){
			params.add(map.get("time1"));
			hql=hql+" and clearTime >= ? ";
		}
		if(map.containsKey("time2")){
			params.add(map.get("time2"));
			hql=hql+" and clearTime <= ? ";
		}
		hql=hql+" order by status,clearTime desc";
		return this.findListByHQL(i, pageSize, hql, params);
	}
	
	
	@Override
	public Integer countOfflineWaitOverProgram(Map<String, Object> map) {
		List<Object> params= new ArrayList<Object>();
		String hql=" from FuProgram where status=4 ";
		if(map.containsKey("programId")){
			params.add(map.get("programId"));
			hql=hql+" and id=? ";
		}
		if(map.containsKey("programType")){
			params.add(map.get("programType"));
			hql=hql+" and programType=? ";
		}
		if(map.containsKey("userName")){
			params.add(map.get("userName"));
			hql=hql+" and fuUser.userName=? ";
		}
		if(map.containsKey("safeMoney1")){
			params.add(map.get("safeMoney1"));
			hql=hql+" and safeMoney >= ? ";
		}
		if(map.containsKey("safeMoney2")){
			params.add(map.get("safeMoney2"));
			hql=hql+" and safeMoney <= ? ";
		}
		if(map.containsKey("doubleNum1")){
			params.add(map.get("doubleNum1"));
			hql=hql+" and doubleNum >= ? ";
		}
		if(map.containsKey("doubleNum2")){
			params.add(map.get("doubleNum2"));
			hql=hql+" and doubleNum <= ? ";
		}
		if(map.containsKey("matchMoney1")){
			params.add(map.get("matchMoney1"));
			hql=hql+" and matchMoney >= ? ";
		}
		if(map.containsKey("matchMoney2")){
			params.add(map.get("matchMoney2"));
			hql=hql+" and matchMoney <= ? ";
		}
		if(map.containsKey("cycleNum1")){
			params.add(map.get("cycleNum1"));
			hql=hql+" and cycleNum >= ? ";
		}
		if(map.containsKey("cycleNum2")){
			params.add(map.get("cycleNum2"));
			hql=hql+" and cycleNum <= ? ";
		}
		if(map.containsKey("feePecent1")){
			params.add(map.get("feePecent1"));
			hql=hql+" and moneyPercent >= ? ";
		}
		if(map.containsKey("feePecent2")){
			params.add(map.get("feePecent2"));
			hql=hql+" and moneyPercent <= ? ";
		}
		if(map.containsKey("managerMoney1")){
			params.add(map.get("managerMoney1"));
			hql=hql+" and manageMoney >= ? ";
		}
		if(map.containsKey("managerMoney2")){
			params.add(map.get("managerMoney2"));
			hql=hql+" and manageMoney <= ? ";
		}
		if(map.containsKey("time1")){
			params.add(map.get("time1"));
			hql=hql+" and createTime >= ? ";
		}
		if(map.containsKey("time2")){
			params.add(map.get("time2"));
			hql=hql+" and createTime <= ? ";
		}
		return this.countQueryResult(hql, params);
	}

	@Override
	public List<FuProgram> findOfflineWaitOverProgramList(int i, int pageSize,
			Map<String, Object> map) {
		List<Object> params= new ArrayList<Object>();
		String hql=" from FuProgram where status=4 ";
		if(map.containsKey("programId")){
			params.add(map.get("programId"));
			hql=hql+" and id=? ";
		}
		if(map.containsKey("programType")){
			params.add(map.get("programType"));
			hql=hql+" and programType=? ";
		}
		if(map.containsKey("userName")){
			params.add(map.get("userName"));
			hql=hql+" and fuUser.userName=? ";
		}
		if(map.containsKey("safeMoney1")){
			params.add(map.get("safeMoney1"));
			hql=hql+" and safeMoney >= ? ";
		}
		if(map.containsKey("safeMoney2")){
			params.add(map.get("safeMoney2"));
			hql=hql+" and safeMoney <= ? ";
		}
		if(map.containsKey("doubleNum1")){
			params.add(map.get("doubleNum1"));
			hql=hql+" and doubleNum >= ? ";
		}
		if(map.containsKey("doubleNum2")){
			params.add(map.get("doubleNum2"));
			hql=hql+" and doubleNum <= ? ";
		}
		if(map.containsKey("matchMoney1")){
			params.add(map.get("matchMoney1"));
			hql=hql+" and matchMoney >= ? ";
		}
		if(map.containsKey("matchMoney2")){
			params.add(map.get("matchMoney2"));
			hql=hql+" and matchMoney <= ? ";
		}
		if(map.containsKey("cycleNum1")){
			params.add(map.get("cycleNum1"));
			hql=hql+" and cycleNum >= ? ";
		}
		if(map.containsKey("cycleNum2")){
			params.add(map.get("cycleNum2"));
			hql=hql+" and cycleNum <= ? ";
		}
		if(map.containsKey("feePecent1")){
			params.add(map.get("feePecent1"));
			hql=hql+" and moneyPercent >= ? ";
		}
		if(map.containsKey("feePecent2")){
			params.add(map.get("feePecent2"));
			hql=hql+" and moneyPercent <= ? ";
		}
		if(map.containsKey("managerMoney1")){
			params.add(map.get("managerMoney1"));
			hql=hql+" and manageMoney >= ? ";
		}
		if(map.containsKey("managerMoney2")){
			params.add(map.get("managerMoney2"));
			hql=hql+" and manageMoney <= ? ";
		}
		if(map.containsKey("time1")){
			params.add(map.get("time1"));
			hql=hql+" and createTime >= ? ";
		}
		if(map.containsKey("time2")){
			params.add(map.get("time2"));
			hql=hql+" and createTime <= ? ";
		}
		hql=hql+" order by id desc";
		return this.findListByHQL(i, pageSize, hql, params);
	}
	
	@Override
	public Integer countDeleteProgram(Map<String, Object> map) {
		List<Object> params= new ArrayList<Object>();
		String hql=" from FuProgram where status=-2 ";
		
		if(map.containsKey("programId")){
			params.add(map.get("programId"));
			hql=hql+" and id=? ";
		}
		if(map.containsKey("programType")){
			params.add(map.get("programType"));
			hql=hql+" and programType=? ";
		}
		if(map.containsKey("userName")){
			params.add(map.get("userName"));
			hql=hql+" and fuUser.userName=? ";
		}
		
		if(map.containsKey("safeMoney1")){
			params.add(map.get("safeMoney1"));
			hql=hql+" and safeMoney >= ? ";
		}
		
		if(map.containsKey("safeMoney2")){
			params.add(map.get("safeMoney2"));
			hql=hql+" and safeMoney <= ? ";
		}
		if(map.containsKey("doubleNum1")){
			params.add(map.get("doubleNum1"));
			hql=hql+" and doubleNum >= ? ";
		}
		if(map.containsKey("doubleNum2")){
			params.add(map.get("doubleNum2"));
			hql=hql+" and doubleNum <= ? ";
		}
		if(map.containsKey("matchMoney1")){
			params.add(map.get("matchMoney1"));
			hql=hql+" and matchMoney >= ? ";
		}
		if(map.containsKey("matchMoney2")){
			params.add(map.get("matchMoney2"));
			hql=hql+" and matchMoney <= ? ";
		}
		if(map.containsKey("cycleNum1")){
			params.add(map.get("cycleNum1"));
			hql=hql+" and cycleNum >= ? ";
		}
		if(map.containsKey("cycleNum2")){
			params.add(map.get("cycleNum2"));
			hql=hql+" and cycleNum <= ? ";
		}
		
		if(map.containsKey("feePecent1")){
			params.add(map.get("feePecent1"));
			hql=hql+" and moneyPercent >= ? ";
		}
		if(map.containsKey("feePecent2")){
			params.add(map.get("feePecent2"));
			hql=hql+" and moneyPercent <= ? ";
		}
		if(map.containsKey("managerMoney1")){
			params.add(map.get("managerMoney1"));
			hql=hql+" and manageMoney >= ? ";
		}
		if(map.containsKey("managerMoney2")){
			params.add(map.get("managerMoney2"));
			hql=hql+" and manageMoney <= ? ";
		}
		if(map.containsKey("time1")){
			params.add(map.get("time1"));
			hql=hql+" and createTime >= ? ";
		}
		if(map.containsKey("time2")){
			params.add(map.get("time2"));
			hql=hql+" and createTime <= ? ";
		}
		return this.countQueryResult(hql, params);
	}

	@Override
	public List<FuProgram> findDeleteProgramList(int i, int pageSize,
			Map<String, Object> map) {
		List<Object> params= new ArrayList<Object>();
		String hql=" from FuProgram where status=-2 ";
		
		if(map.containsKey("programId")){
			params.add(map.get("programId"));
			hql=hql+" and id=? ";
		}
		if(map.containsKey("programType")){
			params.add(map.get("programType"));
			hql=hql+" and programType=? ";
		}
		if(map.containsKey("userName")){
			params.add(map.get("userName"));
			hql=hql+" and fuUser.userName=? ";
		}
		if(map.containsKey("safeMoney1")){
			params.add(map.get("safeMoney1"));
			hql=hql+" and safeMoney >= ? ";
		}
		if(map.containsKey("safeMoney2")){
			params.add(map.get("safeMoney2"));
			hql=hql+" and safeMoney <= ? ";
		}
		if(map.containsKey("doubleNum1")){
			params.add(map.get("doubleNum1"));
			hql=hql+" and doubleNum >= ? ";
		}
		if(map.containsKey("doubleNum2")){
			params.add(map.get("doubleNum2"));
			hql=hql+" and doubleNum <= ? ";
		}
		if(map.containsKey("matchMoney1")){
			params.add(map.get("matchMoney1"));
			hql=hql+" and matchMoney >= ? ";
		}
		if(map.containsKey("matchMoney2")){
			params.add(map.get("matchMoney2"));
			hql=hql+" and matchMoney <= ? ";
		}
		if(map.containsKey("cycleNum1")){
			params.add(map.get("cycleNum1"));
			hql=hql+" and cycleNum >= ? ";
		}
		if(map.containsKey("cycleNum2")){
			params.add(map.get("cycleNum2"));
			hql=hql+" and cycleNum <= ? ";
		}
		
		if(map.containsKey("feePecent1")){
			params.add(map.get("feePecent1"));
			hql=hql+" and moneyPercent >= ? ";
		}
		if(map.containsKey("feePecent2")){
			params.add(map.get("feePecent2"));
			hql=hql+" and moneyPercent <= ? ";
		}
		if(map.containsKey("managerMoney1")){
			params.add(map.get("managerMoney1"));
			hql=hql+" and manageMoney >= ? ";
		}
		if(map.containsKey("managerMoney2")){
			params.add(map.get("managerMoney2"));
			hql=hql+" and manageMoney <= ? ";
		}
		if(map.containsKey("time1")){
			params.add(map.get("time1"));
			hql=hql+" and createTime >= ? ";
		}
		if(map.containsKey("time2")){
			params.add(map.get("time2"));
			hql=hql+" and createTime <= ? ";
		}
		hql=hql+" order by status desc";
		return this.findListByHQL(i, pageSize, hql, params);
	}

	@Override
	public Integer countTradeInfo(Map<String, Object> map) {
		List<Object> params= new ArrayList<Object>();
		String hql=" from FuProgram where status<3 ";
		
		if(map.containsKey("beginTime1")){
			params.add(map.get("beginTime1"));
			hql=hql+" and tradeTime >= ? ";
		}
		if(map.containsKey("beginTime2")){
			params.add(map.get("beginTime2"));
			hql=hql+" and tradeTime <= ? ";
		}
		if(map.containsKey("endTime1")){
			params.add(map.get("endTime1"));
			hql=hql+" and closeTime >= ? ";
		}
		if(map.containsKey("endTime2")){
			params.add(map.get("endTime2"));
			hql=hql+" and closeTime <= ? ";
		}
		if(map.containsKey("userName")){
			params.add(map.get("userName"));
			hql=hql+" and fuUser.userName=? ";
		}
		
		return this.countQueryResult(hql,params);
	}

	@Override
	public List<FuProgram> findTradeInfoList(int i, int pageSize, Map<String, Object> map) {
		List<Object> params= new ArrayList<Object>();
		String hql=" from FuProgram where status<3  ";
		if(map.containsKey("beginTime1")){
			params.add(map.get("beginTime1"));
			hql=hql+" and tradeTime >= ? ";
		}
		if(map.containsKey("beginTime2")){
			params.add(map.get("beginTime2"));
			hql=hql+" and tradeTime <= ? ";
		}
		if(map.containsKey("endTime1")){
			params.add(map.get("endTime1"));
			hql=hql+" and closeTime >= ? ";
		}
		if(map.containsKey("endTime2")){
			params.add(map.get("endTime2"));
			hql=hql+" and closeTime <= ? ";
		}
		if(map.containsKey("userName")){
			params.add(map.get("userName"));
			hql=hql+" and fuUser.userName=? ";
		}
		
		hql=hql+" order by id desc ";
		return this.findListByHQL(i, pageSize, hql,params);
	}
	
	@Override
	public List<FuProgram> findTradeInfoList2(Map<String, Object> map) {
		List<Object> params= new ArrayList<Object>();
		String hql=" from FuProgram where status<3  ";
		if(map.containsKey("beginTime1")){
			params.add(map.get("beginTime1"));
			hql=hql+" and tradeTime >= ? ";
		}
		if(map.containsKey("beginTime2")){
			params.add(map.get("beginTime2"));
			hql=hql+" and tradeTime <= ? ";
		}
		if(map.containsKey("endTime1")){
			params.add(map.get("endTime1"));
			hql=hql+" and closeTime >= ? ";
		}
		if(map.containsKey("endTime2")){
			params.add(map.get("endTime2"));
			hql=hql+" and closeTime <= ? ";
		}
		if(map.containsKey("userName")){
			params.add(map.get("userName"));
			hql=hql+" and fuUser.userName=? ";
		}
		
		hql=hql+" order by id desc ";
		return this.findAllByHQL(hql, params);
	}

	@Override
	public List<FuProgram> findAllTradeInfoList() {
		String hql=" from FuProgram where status<3  order by id desc ";
		return this.findAllByHQL(hql);
	}

	@Override
	public Long findCountMatchMoneyByTrade(Integer tradeAccount) {
		String hql=" select SUM(matchMoney) from FuProgram where status<5 and tradeAccount=? and programWay=2 ";
		Query query=this.createQuery(hql, tradeAccount);
		Long result=(Long)query.uniqueResult();
		return result==null?0l:result;
	}

	@Override
	public List<FuProgram> findProgramsByDate(String queryDate) {   
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		try {
			Date date = sdf.parse(queryDate);
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DATE, -1);
			String begin = sdf.format(c.getTime()) + " 15:15:00";
			String end = queryDate + " 15:15:00";
			String hql= " from FuProgram where checkTime > ? and checkTime <= ? and status=2 ";
			return this.findAllByHQL(hql, begin, end);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;  
	}

	@Override
	public List<FuProgram> findToTradeByUser(Long userId) {
		String hql = " from FuProgram where status in (2,4,6) and fuUser.id=? and programType>=2 order by id desc ";
		return this.findAllByHQL(hql, userId);
	}
		
}

