package com.hongwei.futures.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hongwei.futures.dao.FuDictionaryDao;
import com.hongwei.futures.dao.FuMoneyDetailDao;
import com.hongwei.futures.dao.FuParameterDao;
import com.hongwei.futures.dao.FuProgramDao;
import com.hongwei.futures.dao.FuUserDao;
import com.hongwei.futures.dao.HhrStatDao;
import com.hongwei.futures.dao.HhrStatDetailDao;
import com.hongwei.futures.model.FuMoneyDetail;
import com.hongwei.futures.model.FuParameter;
import com.hongwei.futures.model.FuProgram;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.HhrStat;
import com.hongwei.futures.model.HhrStatDetail;
import com.hongwei.futures.service.HhrStatService;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
@SuppressWarnings("all")
public class HhrStatServiceImpl implements HhrStatService {
	@Autowired
	private FuProgramDao fuProgramDao;
	@Autowired
	private FuUserDao fuUserDao;
	@Autowired
	private  FuDictionaryDao fuDictionaryDao;
	@Autowired
	private HhrStatDao hhrStatDao;
	@Autowired
	private HhrStatDetailDao hhrStatDetailDao;
	@Autowired
	private FuMoneyDetailDao fuMoneyDetailDao;
	@Autowired
	private FuParameterDao fuParameterDao;
	
	
	//====================== 基本 C R U D 方法  ===========================
	@Override
	public HhrStat get(Long id) {
		return hhrStatDao.get(id);
	}
	
	@Override
	public void save(HhrStat entity) {
		hhrStatDao.save(entity);
	}
	
	@Override
	public void delete(Long id) {
		hhrStatDao.delete(id);
	}
	
	@Override
	public Object[] findStatDataByMap(Map<String, Object> map){
		return hhrStatDao.findStatDataByMap(map);
	}

	@Override
	public HhrStat findHhrStatByUser(Long userId) {
		return hhrStatDao.findHhrStatByUser(userId);
	};
	
	@Override
	public HhrStat findStatDataByMap2(Map<String, Object> map){
		return hhrStatDao.findStatDataByMap2(map);
	}
	
	@Override
	public List<HhrStat> findUserByParentId(Long parentId){
		return hhrStatDao.findUserByParentId(parentId);
	}

	@Override
	public List<HhrStat> queryHhrStatList(int i, int j, Map<String, Object> map) {
		return hhrStatDao.queryHhrStatList(i, j, map);
	}

	@Override
	public Integer countHhrStat(Map<String, Object> map) {
		return hhrStatDao.countHhrStat(map);
	}

	/**
	 * 递归方法 
	 */
	public List<HashMap> hhrUserAward(FuUser fuUser, BigDecimal money, Double rate, int n, List<HashMap> hhrUserList, Integer incomeType, BigDecimal hhrPercent){		
		
		//如果当前用户有上层合伙人, 则进行递归计算	
		if(fuUser.getHhrParentID() != null && fuUser.getHhrParentID() != 0){
			//从下往上回溯, 直到最顶层为止			
			//每一层的奖励系数归纳
		    /*n=1, S1 = 0.7;
		      n=2, S2 = (1 - S1) * 0.7
		      n=3, S3 = (1 - S1 - S2) * 0.7;
		      n=4, S4 = (1 - S1 - S2 - S3) * 0.7; 
	       	     归纳后得出: 
	       	  Sn = Math.pow(1-0.7, n-1) * 0.7;
	       	*/
			HashMap userMap = new HashMap();
			userMap.put("USERID", fuUser.getHhrParentID());
			if(incomeType == 1 || incomeType == 5){
				userMap.put("AWARD",  new BigDecimal(Math.pow(1-rate, n-1)).multiply(new BigDecimal(rate)).multiply(money).setScale(1, BigDecimal.ROUND_HALF_UP));
			}else{
				userMap.put("AWARD",  new BigDecimal(Math.pow(1-rate, n-1)).multiply(new BigDecimal(rate)).multiply(money).multiply(hhrPercent).divide(new BigDecimal(12),2).setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			hhrUserList.add(userMap);
			//合伙人分成最多返四层
			if(n < 4){
				n = n + 1; 
				FuUser parentUser = fuUserDao.findFuUserById(fuUser.getHhrParentID());
				hhrUserAward(parentUser,money,rate,n,hhrUserList,incomeType,hhrPercent);
			}
		}
		return hhrUserList;
	}
	
	/**
	 * 从hhr_stat_temp里读取数据, 进行结算处理
	 */
	@Override
	public synchronized void updateHhrIncome(Long userId, BigDecimal money, Integer incomeType, Long programId) {
		String msg = "";
		if(incomeType == 1){
			msg = "注册红包";
		}else if(incomeType == 2){
			msg = "线下返利";
		}else if(incomeType == 3){
			msg = "配资返利";
		}else if(incomeType == 4){
			msg = "续约返利";
		}else if(incomeType == 5){
			msg = "合伙人联盟收益";
		}
		if(programId != null){
			msg = "方案["+programId+"]"+msg;
		}
		Double rate = 0.7;		
		BigDecimal hhrPercent = new BigDecimal(0.012);
		FuParameter params = fuParameterDao.findParameter();
		if(params != null){
			if(incomeType == 5){
				money = money.multiply(params.getStockPercent());
			}else{
				if(params.getHhrPercent()!=null){
					hhrPercent = params.getHhrPercent();
				}
			}
		}
		//当前用户对象
		FuUser fuUser = fuUserDao.get(userId);																	
		List<HashMap> hhrUserList = new ArrayList<HashMap>();
		HashMap userMap = new HashMap();
		userMap.put("USERID", fuUser.getId());
		if(incomeType == 1 || incomeType == 5){
			userMap.put("AWARD", money.multiply(new BigDecimal(rate)).setScale(1, BigDecimal.ROUND_HALF_UP));
		}else{
			userMap.put("AWARD", money.multiply(new BigDecimal(rate)).multiply(hhrPercent).divide(new BigDecimal(12),2).setScale(2, BigDecimal.ROUND_HALF_UP));
		}
		hhrUserList.add(userMap);
		hhrUserAward(fuUser, money, rate, 2, hhrUserList, incomeType, hhrPercent);
		if(hhrUserList != null && hhrUserList.size()>0){
			for(int j=0; j<hhrUserList.size(); j++){
				Long user_id = Long.valueOf(hhrUserList.get(j).get("USERID").toString()); 
				BigDecimal award = new BigDecimal(hhrUserList.get(j).get("AWARD").toString());	
				FuUser u = fuUserDao.get(user_id);
				u.setAccountBalance(u.getAccountBalance().add(award));
				fuUserDao.save(u);
				if(award.compareTo(BigDecimal.ZERO) != 0){
					FuMoneyDetail f = new FuMoneyDetail();
					if(programId != null){
						FuProgram pro = fuProgramDao.get(programId);
						f.setFuProgram(pro);
					}
					f.setFuUser(u);
					if(incomeType == 5){
						f.setFuDictionary(fuDictionaryDao.get(12L));
					}else{
						f.setFuDictionary(fuDictionaryDao.get(10L));
					}
					f.setMoney(award);
					f.setAccountBalanceAfter(u.getAccountBalance());
					f.setTime(new Date());
					f.setComment(msg+f.getMoney().setScale(2, BigDecimal.ROUND_HALF_UP)+"元");
					f.setIsIncome(true);
					f.setIsCheck(0);
					fuMoneyDetailDao.save(f);
					
					//每日合伙人统计
					HhrStatDetail hhrStatDetail = new HhrStatDetail();
					hhrStatDetail.setFuUser(u);
					hhrStatDetail.setStatDate(new Date());
					hhrStatDetail.setHhrParentID(u.getHhrParentID());
					hhrStatDetail.setHhrLevel(u.getHhrLevel());
					hhrStatDetail.setCreateDate(new Date());		
					hhrStatDetail.setDailyIncome(award);
					hhrStatDetail.setIncomeType(incomeType);
					hhrStatDetailDao.save(hhrStatDetail);
								
					//累计合伙人统计
					HhrStat hhrStat = hhrStatDao.findHhrStatByUser(user_id);
					if(hhrStat != null){
						hhrStat.setTotalIncome(hhrStat.getTotalIncome()==null?award:hhrStat.getTotalIncome().add(award));
						hhrStat.setStatDate(new Date());
						hhrStat.setUpdateTime(new Date());	
					}else{
						FuUser user = fuUserDao.get(user_id);
						hhrStat = new HhrStat(); 
						hhrStat.setFuUser(user);
						hhrStat.setStatDate(new Date());
						hhrStat.setHhrParentID(user.getHhrParentID());
						hhrStat.setHhrLevel(user.getHhrLevel());
						hhrStat.setTotalIncome(award);
						hhrStat.setUpdateTime(new Date());
					}
					hhrStatDao.save(hhrStat);
				}
			}	
		}
	}

	// 根据 hhrParentID 查询用户信息 
	public List<HhrStat> findUserByParentId(long userId, int curPage, int pageSize) {
		return hhrStatDao.findUserByParentId(userId, curPage, pageSize);
	}
	
}

