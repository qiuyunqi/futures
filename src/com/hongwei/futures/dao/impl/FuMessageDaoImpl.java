package com.hongwei.futures.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hongwei.futures.dao.impl.BaseDaoImpl;
import com.hongwei.futures.dao.FuMessageDao;
import com.hongwei.futures.model.FuMessage;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@Repository
public class FuMessageDaoImpl extends BaseDaoImpl<FuMessage, Long> implements FuMessageDao {

	@Override
	public Integer countMessage(Long userId) {
		String hql = " from FuMessage where fuUser.id=? ";
		return this.countQueryResult(hql, userId);
	}

	@Override
	public List<FuMessage> findMessageList(int i, int pageSize, Long userId) {
		String hql = " from FuMessage where fuUser.id=? order by id desc ";
		return this.findListByHQL(i, pageSize, hql, userId);
	}

	@Override
	public Integer countAllMessage(Map<String, Object> map) {
		String hql = " from FuMessage where state=0 ";
		List<Object> params=new ArrayList<Object>();
		if(map.containsKey("leaveUser")){
			params.add(map.get("leaveUser"));
			hql=hql+" and fuUser.userName=? ";
		}
		if(map.containsKey("replyAdmin")){
			params.add(map.get("replyAdmin"));
			hql=hql+" and fuAdmin.account=? ";
		}
		if(map.containsKey("messageRemark")){
			hql=hql+" and replyMark like '%"+map.get("messageRemark")+"%'   ";
		}
		if(map.containsKey("leaveBeginTime")){
			params.add(map.get("leaveBeginTime"));
			hql=hql+" and time>=? ";
		}
		if(map.containsKey("leaveEndTime")){
			params.add(map.get("leaveEndTime"));
			hql=hql+" and time<=? ";
		}
		if(map.containsKey("replyBeginTime")){
			params.add(map.get("replyBeginTime"));
			hql=hql+" and replyTime>=? ";
		}
		if(map.containsKey("replyEndTime")){
			params.add(map.get("replyEndTime"));
			hql=hql+" and replyTime<=? ";
		}
		if(map.containsKey("type")){
			params.add(map.get("type"));
			hql=hql+" and type=? ";
		}
		if(map.containsKey("noReply")){
			hql=hql+" and fuAdmin=null ";
		}
		if(map.containsKey("content")){
			hql=hql+" and content like '%"+map.get("content")+"%'  ";
		}
		return this.countQueryResult(hql,params);
	}

	@Override
	public List<FuMessage> findAllMessage(int i, int pageSize, Map<String, Object> map) {
		String hql = " from FuMessage where 1=1";
		List<Object> params=new ArrayList<Object>();
		if(map.containsKey("userId")){
			params.add(map.get("userId"));
			hql=hql+" and fuUser.id=? ";
		}
		if(map.containsKey("leaveUser")){
			params.add(map.get("leaveUser"));
			hql=hql+" and fuUser.userName=? ";
		}
		if(map.containsKey("replyAdmin")){
			params.add(map.get("replyAdmin"));
			hql=hql+" and fuAdmin.account=? ";
		}
		if(map.containsKey("messageRemark")){
			hql=hql+" and replyMark like '%"+map.get("messageRemark")+"%'   ";;
		}
		if(map.containsKey("leaveBeginTime")){
			params.add(map.get("leaveBeginTime"));
			hql=hql+" and time>=? ";
		}
		if(map.containsKey("leaveEndTime")){
			params.add(map.get("leaveEndTime"));
			hql=hql+" and time<=? ";
		}
		if(map.containsKey("replyBeginTime")){
			params.add(map.get("replyBeginTime"));
			hql=hql+" and replyTime>=? ";
		}
		if(map.containsKey("replyEndTime")){
			params.add(map.get("replyEndTime"));
			hql=hql+" and replyTime<=? ";
		}
		if(map.containsKey("type")){
			params.add(map.get("type"));
			hql=hql+" and type=? ";
		}
		if(map.containsKey("content")){
			hql=hql+" and content like '%"+map.get("content")+"%'  ";
		}
		hql=hql+" order by id desc";
		return this.findListByHQL(i, pageSize, hql,params);
	}

	@Override
	public List<FuMessage> findMessageByUser(Long userId) {
		String hql = "from HhrStat where fuUser.id=?";
		return this.findAllByHQL(hql, userId);
	}

}

