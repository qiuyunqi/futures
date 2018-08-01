package com.hongwei.futures.dao.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hongwei.futures.dao.FuUserDao;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.FuUserBak;

/**
 * 
 * @description 自动生成 daoImpl
 *
 * @author 弘威
 */
@Repository
@SuppressWarnings("unchecked")
public class FuUserDaoImpl extends BaseDaoImpl<FuUser, Long> implements FuUserDao {

	@Override
	public FuUser findUserByUserName(String userName) {
		String hql=" from FuUser where state=1 and userName=? ";
		return this.findUniqueByHQL(hql, userName);
	}
	
	@Override
	public FuUser findUserByNickName(String nickName) {
		String hql=" from FuUser where nickName=? ";
		return this.findUniqueByHQL(hql, nickName);
	}

	@Override
	public FuUser findLoginByToken(String token) {
		String hql=" from FuUser where state=1 and loginToken=? ";
		return this.findUniqueByHQL(hql, token);
	}

	@Override
	public FuUser findUserByPhone(String phone) {
		String hql=" from FuUser where state=1 and phone=? ";
		return this.findUniqueByHQL(hql, phone);
	}

	@Override
	public FuUser findUserByEmail(String email) {
		String hql=" from FuUser where state=1 and email=? ";
		return this.findUniqueByHQL(hql, email);
	}

	@Override
	public FuUser findUserByAccount(String accountName) {
		String hql=" from FuUser where state=1 and accountName=? or phone=?";
		return this.findUniqueByHQL(hql, accountName, accountName);
	}

	@Override
	public Integer countUser(Map<String, Object> map) {
		List<Object> params=new ArrayList<Object>();
		String hql=" from FuUser where 1=1 ";
		String searchType=map.get("searchType").toString();
		if(map.containsKey("userId")){
			params.add(map.get("userId"));
			hql=hql+" and id=? ";
		}
		if(map.containsKey("accountName")){
			if(searchType.equals("1")){
				params.add(map.get("accountName"));
				hql=hql+" and accountName=? ";
			}else{
				hql=hql+" and accountName like '%"+map.get("accountName")+"%' ";
			}
		}
		if(map.containsKey("userName")){
			if(searchType.equals("1")){
				params.add(map.get("userName"));
				hql=hql+" and userName=? ";
			}else{
				hql=hql+" and userName like '%"+map.get("userName")+"%' ";
			}
		}
		if(map.containsKey("phone")){
			if(searchType.equals("1")){
				params.add(map.get("phone"));
				hql=hql+" and phone=? ";
			}else{
				hql=hql+" and phone like '%"+map.get("phone")+"%' ";
			}
		}
		if(map.containsKey("cardNumber")){
			if(searchType.equals("1")){
				params.add(map.get("cardNumber"));
				hql=hql+" and cardNumber=? ";
			}else{
				hql=hql+" and cardNumber like '%"+map.get("cardNumber")+"%' ";
			}
		}
		if(map.containsKey("email")){
			if(searchType.equals("1")){
				params.add(map.get("email"));
				hql=hql+" and email=? ";
			}else{
				hql=hql+" and email like '%"+map.get("email")+"%' ";
			}
		}
		if(map.containsKey("registerIp")){
			if(searchType.equals("1")){
				params.add(map.get("registerIp"));
				hql=hql+" and registerIp=? ";
			}else{
				hql=hql+" and registerIp like '%"+map.get("registerIp")+"%' ";
			}
		}
		if(map.containsKey("state")){
			params.add(map.get("state"));
			hql=hql+" and state=?";
		}
		if(map.containsKey("matchMoney1")){
			params.add(map.get("matchMoney1"));
			hql=hql+" and matchMoney>=?  ";
		}
		if(map.containsKey("matchMoney2")){
			params.add(map.get("matchMoney2"));
			hql=hql+" and matchMoney<=?  ";
		}
		if(map.containsKey("safeMoney1")){
			params.add(map.get("safeMoney1"));
			hql=hql+" and safeMoney>=?  ";
		}
		if(map.containsKey("safeMoney2")){
			params.add(map.get("safeMoney2"));
			hql=hql+" and safeMoney<=?  ";
		}
		if(map.containsKey("invitationCode")){
			params.add(map.get("invitationCode"));
			hql=hql+" and invitationCode=?";
		}
		if(map.containsKey("time1")){
			params.add(map.get("time1"));
			hql=hql+" and registerTime>=?";
		}
		if(map.containsKey("time2")){
			params.add(map.get("time2"));
			hql=hql+" and registerTime<=?";
		}
		if(map.containsKey("time3")){
			params.add(map.get("time3"));
			hql=hql+" and loginTime>=?";
		}
		if(map.containsKey("time4")){
			params.add(map.get("time4"));
			hql=hql+" and loginTime<=?";
		}
		return this.countQueryResult(hql,params);
	}

	@Override
	public List<FuUser> findUserList(int i, int pageSize,Map<String, Object> map) {
		List<Object> params=new ArrayList<Object>();
		String hql=" from FuUser where 1=1 ";
		String searchType=map.get("searchType").toString();
		if(map.containsKey("userId")){
			params.add(map.get("userId"));
			hql=hql+" and id=? ";
		}
		if(map.containsKey("accountName")){
			if(searchType.equals("1")){
				params.add(map.get("accountName"));
				hql=hql+" and accountName=? ";
			}else{
				hql=hql+" and accountName like '%"+map.get("accountName")+"%' ";
			}
		}
		if(map.containsKey("userName")){
			if(searchType.equals("1")){
				params.add(map.get("userName"));
				hql=hql+" and userName=? ";
			}else{
				hql=hql+" and userName like '%"+map.get("userName")+"%' ";
			}
		}
		if(map.containsKey("phone")){
			if(searchType.equals("1")){
				params.add(map.get("phone"));
				hql=hql+" and phone=? ";
			}else{
				hql=hql+" and phone like '%"+map.get("phone")+"%' ";
			}
		}
		if(map.containsKey("cardNumber")){
			if(searchType.equals("1")){
				params.add(map.get("cardNumber"));
				hql=hql+" and cardNumber=? ";
			}else{
				hql=hql+" and cardNumber like '%"+map.get("cardNumber")+"%' ";
			}
		}
		if(map.containsKey("email")){
			if(searchType.equals("1")){
				params.add(map.get("email"));
				hql=hql+" and email=? ";
			}else{
				hql=hql+" and email like '%"+map.get("email")+"%' ";
			}
		}
		if(map.containsKey("registerIp")){
			if(searchType.equals("1")){
				params.add(map.get("registerIp"));
				hql=hql+" and registerIp=? ";
			}else{
				hql=hql+" and registerIp like '%"+map.get("registerIp")+"%' ";
			}
		}
		if(map.containsKey("matchMoney1")){
			params.add(map.get("matchMoney1"));
			hql=hql+" and matchMoney>=?  ";
		}
		if(map.containsKey("matchMoney2")){
			params.add(map.get("matchMoney2"));
			hql=hql+" and matchMoney<=?  ";
		}
		if(map.containsKey("safeMoney1")){
			params.add(map.get("safeMoney1"));
			hql=hql+" and safeMoney>=?  ";
		}
		if(map.containsKey("safeMoney2")){
			params.add(map.get("safeMoney2"));
			hql=hql+" and safeMoney<=?  ";
		}
		if(map.containsKey("invitationCode")){
			params.add(map.get("invitationCode"));
			hql=hql+" and invitationCode=?  ";
		}
		if(map.containsKey("state")){
			params.add(map.get("state"));
			hql=hql+" and state=?";
		}
		if(map.containsKey("time1")){
			params.add(map.get("time1"));
			hql=hql+" and registerTime>=?";
		}
		if(map.containsKey("time2")){
			params.add(map.get("time2"));
			hql=hql+" and registerTime<=?";
		}
		if(map.containsKey("time3")){
			params.add(map.get("time3"));
			hql=hql+" and loginTime>=?";
		}
		if(map.containsKey("time4")){
			params.add(map.get("time4"));
			hql=hql+" and loginTime<=?";
		}
		if(map.containsKey("commission")){
			hql=hql+" and commissionTotal>0 order by commissionTotal desc ";
		}else{
			hql=hql+" order by id desc ";
		}
		return this.findListByHQL(i, pageSize, hql,params);
	}

	@Override
	public Integer countCheckUser(Map<String, Object> map) {
		String hql="  from FuUser where state=1 and isCheckCard>0";
		List<Object> params=new ArrayList<Object>();
		if(map.containsKey("accountName")){
			params.add(map.get("accountName"));
			hql=hql+" and accountName=? ";
		}
		if(map.containsKey("userName")){
			params.add(map.get("userName"));
			hql=hql+" and userName=? ";
		}
		if(map.containsKey("cardNumber")){
			params.add(map.get("cardNumber"));
			hql=hql+" and cardNumber=? ";
		}
		return this.countQueryResult(hql,params);
	}
	
	@Override
	public Integer countCheckUser2(Map<String, Object> map) {
		String hql="  from FuUser where state=1 and isCheckCard=1";
		return this.countQueryResult(hql);
	}

	@Override
	public List<FuUser> findCheckUserList(int i, int j, Map<String, Object> map) {
		String hql="  from FuUser where state=1 and isCheckCard>0";
		List<Object> params=new ArrayList<Object>();
		if(map.containsKey("accountName")){
			params.add(map.get("accountName"));
			hql=hql+" and accountName=? ";
		}
		if(map.containsKey("userName")){
			params.add(map.get("userName"));
			hql=hql+" and userName=? ";
		}
		if(map.containsKey("cardNumber")){
			params.add(map.get("cardNumber"));
			hql=hql+" and cardNumber=? ";
		}
		hql=hql+" order by isCheckCard,id asc";
		return this.findListByHQL(i, j, hql,params);
	}

	@Override
	public int countRegister() {
		String hql=" from FuUser where state=1";
		return this.countQueryResult(hql);
	}

	@Override
	public BigDecimal countTotalMoney() {
		String hql="select sum(accountTotalMoney) from FuUser where state=1";
		//Object[] objArray = new Object[0];
		Query query = createQuery(hql);
		return new BigDecimal(query.uniqueResult().toString());
	}

	@Override
	public FuUser findUserByRegPhone(String phone) {
		String hql=" from FuUser where  phone=? ";
		return this.findUniqueByHQL(hql, phone);
	}

	@Override
	public FuUser findUserByRegAccountName(String accountName) {
		String hql=" from FuUser where  accountName=? ";
		return this.findUniqueByHQL(hql, accountName);
	}
	
	@Override
	public FuUser findUserByRegInvitationcode(String invitation_code){
		String hql=" from FuUser where  invitationCode=? ";
		return this.findUniqueByHQL(hql, invitation_code);
	}

	@Override
	public Object[] findUserDataByMap(Map<String, Object> map) {
		String sql="select id,account_name,user_avatar,phone,is_check_card,user_name,card_number," +
				   "gender,is_marriage,live_address,max_degree,business_type,position_name,salary," +
				   "introduction,nick_name,email,invitation_code,province_name,city_id,draw_password,province_id from fu_user where state=1 ";
		List<Object> params=new ArrayList<Object>();
		if(map.containsKey("user_id")){
			params.add(map.get("user_id"));
			sql=sql+" and id=? ";
		}
		if(map.containsKey("invitation_code")){
			params.add(map.get("invitation_code"));
			sql=sql+" and invitation_code=? ";
		}
		if(map.containsKey("phone")){
			params.add(map.get("phone"));
			sql=sql+" and phone=? ";
		}
		if(this.findBySqlGetArray(sql, params).size()>0){
		    return this.findBySqlGetArray(sql, params).get(0);
		}else{
			return null;
		}
	}

	@Override
	public FuUser findFuUserById(Long userId) {
		String hql = " from FuUser where id = ? ";
		return this.findUniqueByHQL(hql, userId);
	}
	
	@Override
	public Integer countInvitationCode(String code){
		List<Object> params=new ArrayList<Object>();
		String hql=" from FuUser where state=1 ";
		if(code!=null){
			params.add(code);
			hql=hql+" and invitationCode=? ";
		}
		return this.countQueryResult(hql,params);
	}

	@Override
	public List<FuUser> findListByUser(Long userId) {
		String hql=" from FuUser where isCheckCard=2 and id=? order by id desc";
		return this.findAllByHQL(hql, userId);
	}

	@Override
	public List<FuUser> findSameNickNameUser(String nickName) {
		String hql=" from FuUser where nickName=? and state=1 order by id desc";
		return this.findAllByHQL(hql, nickName);
	}

	@Override
	public List<FuUser> findListByParentId(Long userId) {
		String hql=" from FuUser where hhrParentID=? ";
		return this.findAllByHQL(hql, userId);
	}

	@Override
	public String findUserTree(Long id) {
		String sql = " select id, account_name, user_name, hhr_parentID as pId, phone, hhr_level from fu_user " +
					 " where hhr_parentID is not null and hhr_level = 1 ";
		if(id != 0){
			sql = " select id, account_name, user_name, hhr_parentID as pId, phone, hhr_level from fu_user " +
				  " where hhr_parentID = "+Long.valueOf(id);
		}
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
		JSONArray jsonArr = new JSONArray();
		for(Map<String, Object> user : list){
			List<FuUser> extend = this.findListByParentId(Long.valueOf(user.get("id").toString()));
			JSONObject obj = new JSONObject();
			obj.put("id", user.get("id").toString());
			obj.put("name", user.get("id").toString()+"_"+(user.get("phone")!=null?user.get("phone").toString():"")+"("+(user.get("user_name")==null?"":user.get("user_name").toString())+")"+"_"+(user.get("hhr_level")!=null?user.get("hhr_level").toString():""));
			obj.put("pId", user.get("pId")!=null?user.get("pId").toString():"");
			obj.put("isParent", extend.size()>0?"true":"false");
			jsonArr.add(obj);
		}
		return jsonArr.toString();
	}
	
	
	/**
	 * 查询所有用户
	 * @return
	 */
	@Override
	public List<FuUser> findAllUsers(){
		String hql=" from FuUser where state=1";
		return this.findAllByHQL(hql);
	}

	@Override
	public FuUser findUserByWeixinCode(String weixinCode) {
		String hql=" from FuUser where state=1 and weixinCode=?";
		return (FuUser) this.getSession().createQuery(hql)//
				.setParameter(0, weixinCode)//
				.uniqueResult();
	}

	@Override
	public FuUser findUserByCardNumber(String cardNumber) {
		/*String hql=" from FuUser where state=1 and isCheckCard=2 and cardNumber=? ";
		return this.findUniqueByHQL(hql, cardNumber);*/
		String hql="from FuUser where state=1 and isCheckCard=2 and cardNumber=:cardNumber";
		List<FuUser> userList  = this.getSession().createQuery(hql)//
				.setParameter("cardNumber", cardNumber)//
				.list();
		return userList == null || userList.size() <= 0 ? null : userList.get(0);
	}

	@Override
	public List<Object[]> queryUserAccountBalanceList(int i, int pageSize, Map<String, Object> map) {
		String sql = " SELECT a.id,a.account_name,a.user_name,a.account_balance,b.account_balance_after,a.state FROM fu_user a " +  
				     " left join " +
				     " (select c.id, c.user_id, c.account_balance_after from " +
				     "     (select id, user_id, account_balance_after from fu_money_detail order by id desc) c " +
				     "  group by c.user_id ) b " +
				     " on a.id = b.user_id " +
				     " where a.state = "+map.get("state");
		if(map.get("beginTime")!=null && map.get("endTime")!=null){
			   sql = " SELECT a.id,a.account_name,a.user_name,a.account_balance,b.account_balance_after,a.state FROM fu_user a " +  
				     " left join " +
				     " (select c.id, c.user_id, c.account_balance_after from " +
				     "     (select id, user_id, account_balance_after from fu_money_detail " +
				     "			where date_format(time,'%Y-%m-%d') >  '"+ map.get("beginTime") + "'" + 
				     " 			  and date_format(time,'%Y-%m-%d') <= '"+ map.get("endTime")+ "' order by id desc) c " +
				     "  group by c.user_id ) b " +
				     " on a.id = b.user_id " +
				     " where a.state = "+map.get("state");
		}
		if(map.get("beginTime")!=null && map.get("endTime")==null){
			sql = " SELECT a.id,a.account_name,a.user_name,a.account_balance,b.account_balance_after,a.state FROM fu_user a " +  
				     " left join " +
				     " (select c.id, c.user_id, c.account_balance_after from " +
				     "     (select id, user_id, account_balance_after from fu_money_detail " +
				     "			where date_format(time,'%Y-%m-%d') > '"+ map.get("beginTime")+ "' order by id desc) c " +
				     "  group by c.user_id ) b " +
				     " on a.id = b.user_id " +
				     " where a.state = "+map.get("state");
		}
		if(map.get("beginTime")==null && map.get("endTime")!=null){
			sql = " SELECT a.id,a.account_name,a.user_name,a.account_balance,b.account_balance_after,a.state FROM fu_user a " +  
				     " left join " +
				     " (select c.id, c.user_id, c.account_balance_after from " +
				     "     (select id, user_id, account_balance_after from fu_money_detail " +
				     "			where date_format(time,'%Y-%m-%d') <= '"+ map.get("endTime")+ "' order by id desc) c " +
				     "  group by c.user_id ) b " +
				     " on a.id = b.user_id " +
				     " where a.state = "+map.get("state");
		}
		if(this.findListBySql(i, pageSize, sql, null).size()>0){
		    return this.findListBySql(i, pageSize, sql, null);
		}else{
			return null;
		}
	}

	@Override
	public Integer countUserAccountBalance(Map<String, Object> map) {
		String sql = " SELECT a.id,a.account_name,a.user_name,a.account_balance,b.account_balance_after,a.state FROM fu_user a " +  
			     " left join " +
			     " (select c.id, c.user_id, c.account_balance_after from " +
			     "     (select id, user_id, account_balance_after from fu_money_detail order by id desc) c " +
			     "  group by c.user_id ) b " +
			     " on a.id = b.user_id " +
			     " where a.state = "+map.get("state");
		if(map.get("beginTime")!=null && map.get("endTime")!=null){
			   sql = " SELECT a.id,a.account_name,a.user_name,a.account_balance,b.account_balance_after,a.state FROM fu_user a " +  
				     " left join " +
				     " (select c.id, c.user_id, c.account_balance_after from " +
				     "     (select id, user_id, account_balance_after from fu_money_detail " +
				     "			where date_format(time,'%Y-%m-%d') >  '"+ map.get("beginTime") + "'" +
				     " 			  and date_format(time,'%Y-%m-%d') <= '"+ map.get("endTime")+ "' order by id desc) c " +
				     "  group by c.user_id ) b " +
				     " on a.id = b.user_id " +
				     " where a.state = "+map.get("state");
		}
		if(map.get("beginTime")!=null && map.get("endTime")==null){
			sql = " SELECT a.id,a.account_name,a.user_name,a.account_balance,b.account_balance_after,a.state FROM fu_user a " +  
				     " left join " +
				     " (select c.id, c.user_id, c.account_balance_after from " +
				     "     (select id, user_id, account_balance_after from fu_money_detail " +
				     "			where date_format(time,'%Y-%m-%d') > '"+ map.get("beginTime")+ "' order by id desc) c " +
				     "  group by c.user_id ) b " +
				     " on a.id = b.user_id " +
				     " where a.state = "+map.get("state");
		}
		if(map.get("beginTime")==null && map.get("endTime")!=null){
			sql = " SELECT a.id,a.account_name,a.user_name,a.account_balance,b.account_balance_after,a.state FROM fu_user a " +  
				     " left join " +
				     " (select c.id, c.user_id, c.account_balance_after from " +
				     "     (select id, user_id, account_balance_after from fu_money_detail " +
				     "			where date_format(time,'%Y-%m-%d') <= '"+ map.get("endTime")+ "' order by id desc) c " +
				     "  group by c.user_id ) b " +
				     " on a.id = b.user_id " +
				     " where a.state = "+map.get("state");
		}
		JdbcTemplate jdbcTemplate = this.getJdbcTemplate();
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list!=null?list.size():0;
	}
	
	public FuUser findUserByRegIp(String registerIp){
		String hql=" from FuUser where registerIp=? order by registerTime desc";
		return this.findUniqueByHQL(hql, registerIp);
	}
	
	public Integer findCountByRegIp(String registerIp){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String nowDay=sdf.format(new Date());
		String hql=" from FuUser where registerIp=? and date_format(registerTime,'%Y-%m-%d')='"+nowDay+"'";
		return this.countQueryResult(hql, registerIp);
	}

	@Override
	public List<Object[]> queryAllUserAccountBalanceList(Map<String, Object> map) {
		String sql = " SELECT a.id,a.account_name,a.user_name,a.account_balance,b.account_balance_after,a.state FROM fu_user a " +  
			     " left join " +
			     " (select c.id, c.user_id, c.account_balance_after from " +
			     "     (select id, user_id, account_balance_after from fu_money_detail order by id desc) c " +
			     "  group by c.user_id ) b " +
			     " on a.id = b.user_id " +
			     " where a.state = "+map.get("state");
		if(map.get("beginTime")!=null && map.get("endTime")!=null){
			   sql = " SELECT a.id,a.account_name,a.user_name,a.account_balance,b.account_balance_after,a.state FROM fu_user a " +  
				     " left join " +
				     " (select c.id, c.user_id, c.account_balance_after from " +
				     "     (select id, user_id, account_balance_after from fu_money_detail " +
				     "			where date_format(time,'%Y-%m-%d') >  '"+ map.get("beginTime") + "'" + 
				     " 			  and date_format(time,'%Y-%m-%d') <= '"+ map.get("endTime")+ "' order by id desc) c " +
				     "  group by c.user_id ) b " +
				     " on a.id = b.user_id " +
				     " where a.state = "+map.get("state");
		}
		if(map.get("beginTime")!=null && map.get("endTime")==null){
			sql = " SELECT a.id,a.account_name,a.user_name,a.account_balance,b.account_balance_after,a.state FROM fu_user a " +  
				     " left join " +
				     " (select c.id, c.user_id, c.account_balance_after from " +
				     "     (select id, user_id, account_balance_after from fu_money_detail " +
				     "			where date_format(time,'%Y-%m-%d') > '"+ map.get("beginTime")+ "' order by id desc) c " +
				     "  group by c.user_id ) b " +
				     " on a.id = b.user_id " +
				     " where a.state = "+map.get("state");
		}
		if(map.get("beginTime")==null && map.get("endTime")!=null){
			sql = " SELECT a.id,a.account_name,a.user_name,a.account_balance,b.account_balance_after,a.state FROM fu_user a " +  
				     " left join " +
				     " (select c.id, c.user_id, c.account_balance_after from " +
				     "     (select id, user_id, account_balance_after from fu_money_detail " +
				     "			where date_format(time,'%Y-%m-%d') <= '"+ map.get("endTime")+ "' order by id desc) c " +
				     "  group by c.user_id ) b " +
				     " on a.id = b.user_id " +
				     " where a.state = "+map.get("state");
		}
		if(this.findBySqlGetArray(sql, null).size()>0){
		    return this.findBySqlGetArray(sql, null);
		}else{
			return null;
		}
	}

	public List<String> getUserList(int i, int j) {
		String sql = "select phone from fu_user where state != 0 order by id desc";
		return (List<String>) this.getSession().createSQLQuery(sql)//
				.setFirstResult(i)
				.setMaxResults(j)//
				.list();
	}

	// 通过微信号和手机号查询是否存在用户
	public FuUser findUserByWeiXinAndPhone(String weixinCode, String phone) {
		String hql = "FROM FuUser AS f WHERE f.state !=0 AND f.phone  = :phone AND f.weixinCode = :weixinCode";
		return (FuUser)this.getSession().createQuery(hql)//
				.setParameter("phone", phone)//
				.setParameter("weixinCode", weixinCode)//
				.uniqueResult();
	}

	// 根据用户id 查询这个用户下的所有的有有效账户的个数以及信息
	public List<FuUserBak> findUserByHhrParentId(Long hhrParentId) {
		String sql = "SELECT fu_user.id, count(stock_account.user_id) as num, fu_user.user_name , fu_user.nick_name , fu_user.user_avatar"
				+ " FROM stock_account, fu_user WHERE "
				+ " stock_account.user_id IN ( SELECT fu_user.id FROM fu_user WHERE fu_user.hhr_parentID = :hhrParentId) "
				+ " AND fu_user.id = stock_account.user_id "
				+ " AND stock_account.is_del = 0"
				+ " GROUP BY stock_account.user_id ORDER BY num DESC";
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.setParameter("hhrParentId", hhrParentId);
		List<Object[]> list = query.list();
		// 将一个list转换成一个OPP对象
		List<FuUserBak> userList = new  ArrayList<FuUserBak>();
		for (int i = 0; i < list.size(); i++) {
			FuUserBak user = new FuUserBak();
			user.setUserId((BigInteger) list.get(i)[0]);
			user.setNum((BigInteger) list.get(i)[1]);
			user.setUserName((String) list.get(i)[2]);
			user.setNickName((String) list.get(i)[3]);
			user.setAvatar((String) list.get(i)[4]);
			userList.add(user);
		}
		return userList;
	}

	//根据指定记录数查询用户的下级合伙人
	@Override
	public List<FuUser> findListByParentIdCount(Long userId, Integer count) {
		List<Object> params=new ArrayList<Object>();
		String hql = " from FuUser where 1=1 ";
		params.add(userId);
		hql = hql + " and hhrParentID=? ";
		return this.findListByHQL(0, count, hql, params);
	}
	
}

