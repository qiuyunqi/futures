package com.hongwei.futures.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.dao.FuSmsLogDao;
import com.hongwei.futures.model.FuIpBlacklist;
import com.hongwei.futures.model.FuParameter;
import com.hongwei.futures.model.FuSmsLog;
import com.hongwei.futures.service.FuIpBlacklistService;
import com.hongwei.futures.service.FuParameterService;
import com.hongwei.futures.service.FuSmsLogService;
/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public class FuSmsLogServiceImpl implements FuSmsLogService {
	
	@Autowired
	private FuSmsLogDao fuSmsLogDao;
	@Autowired
	private FuIpBlacklistService fuIpBlacklistService;
	@Autowired
	private FuParameterService fuParameterService;
	
	//====================== 基本 C R U D 方法  ===========================
	@Override
	public FuSmsLog get(Long id) {
		return fuSmsLogDao.get(id);
	}
	
	@Override
	public void save(FuSmsLog entity) {
		fuSmsLogDao.save(entity);
	}
	
	@Override
	public void delete(Long id) {
		fuSmsLogDao.delete(id);
	}

	@Override
	public Integer countLog(HashMap<String, Object> map) {
		return fuSmsLogDao.countLog(map);
	}

	@Override
	public List<FuSmsLog> findLogList(int i,int j,HashMap<String, Object> map) {
		return fuSmsLogDao.findLogList(i,j,map);
	}
	
	@Override
	public FuSmsLog findLogByMap(Map<String, Object> map){
		return fuSmsLogDao.findLogByMap(map);
	}
	
	@Override
	public List<FuSmsLog> findLogListByMap(Map<String, Object> map){
		return fuSmsLogDao.findLogListByMap(map);
	}

	public int examin(String phone, String ip) {
		// 查询手机号和ip是否在黑名单中
		List<FuIpBlacklist> backList = fuIpBlacklistService.getBlackListByPhoneOrIp(phone, ip);
		if(null != backList && backList.size() > 0) { // 在黑名单中
			return 0;
		}
		// 查询手机号在一个时段发送了多少条
		FuParameter fuParameter = fuParameterService.findParameter();
		Integer num = fuSmsLogDao.getNumByPhoneInMin(phone, fuParameter.getRegInterval());
		if(num > (null == fuParameter.getSendNum() ? 10 : fuParameter.getSendNum())) { // 加入黑名单 
			FuIpBlacklist fb = new FuIpBlacklist();
			fb.setIp(phone);
			fb.setType(1);
			fb.setCreateTime(new Date());
			fb.setIsBlack(1);
			fuIpBlacklistService.save(fb);
			return 0;
		}
		// 间隔上一个短信时间是 30/S
		//FuIpBlacklist findBlackByRegIp = fuIpBlacklistService.findBlackByRegIp(ip);
		FuSmsLog fsl = fuSmsLogDao.findNewMessage(phone);
		if(null != fsl) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.SECOND, -60);
			Long d = cal.getTime().getTime();
			Long s = d - (fsl.getPlanTime().getTime());
			
			//Date date = new Date(s);
			//String strFromDate = DateUtil.getStrFromDate(date, "ss");
			if(s >= 0) {
				return 1;
			}else {
				return 2;
			}
		}
		return 1;
	}

	@Override
	public void saveSendServiceEmail(String reason, String content) {
		fuSmsLogDao.saveSendServiceEmail(reason, content);
		
	}

	@Override
	public List<FuSmsLog> findMailLogList() {
		return fuSmsLogDao.findMailLogList();
	}

	// 获取最新的验证码
	public FuSmsLog getNewCode(String phone) {
		return fuSmsLogDao.getNewCode(phone);
	}
	
}

