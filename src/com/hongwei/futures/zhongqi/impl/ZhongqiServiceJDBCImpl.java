package com.hongwei.futures.zhongqi.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hongwei.futures.dao.FuProgramDao;
import com.hongwei.futures.dao.FuServerDao;
import com.hongwei.futures.model.FuProgram;
import com.hongwei.futures.model.FuServer;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.util.Property;
import com.hongwei.futures.util.SQLServerUtil;
import com.hongwei.futures.zhongqi.ZhongqiService;
import java.sql.Connection;

@Service
public class ZhongqiServiceJDBCImpl implements ZhongqiService {
	@Autowired
	private FuServerDao fuServerDao;
	@Autowired
	private FuProgramDao fuProgramDao;

	/**
	 * 查询可用资金
	 * @param investorId
	 * @return
	 */
	public BigDecimal findWithdrawQuota(FuServer fuServer,Integer investorId){
		//持仓占用保证金
		BigDecimal curMargin = findCurrMargin(fuServer, investorId);
				
		//动态权益
		BigDecimal balance = findBalance(fuServer, investorId);
				
		//可用资金
		BigDecimal withdrawQuota = balance.subtract(curMargin);
		return withdrawQuota;
	}

	/**
	 * 查询动态权益
	 * 
	 * @param investorId
	 * @return 
	 */
	public BigDecimal findBalance(FuServer fuServer,Integer investorId) {
		String sql="SELECT [Balance] FROM [InvestorAccountIntime] WHERE [InvestorID]=?";
		String name="Balance";
		Object[] params=new Object[]{investorId};
		BigDecimal Balance=null;
		try {
			Object result=SQLServerUtil.ExecuteScalar(sql, name, params, fuServer);
			if(result==null){
				Balance=BigDecimal.ZERO;
			}else{
				Balance=new BigDecimal(result.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				SQLServerUtil.colseConnection(SQLServerUtil.getConnect(fuServer));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return Balance;
	}

	/**
	 * 查询持仓占用保证金
	 * 
	 * @param investorId
	 * @return 
	 */
	public BigDecimal findCurrMargin(FuServer fuServer,Integer investorId) {
		String sql="SELECT [CurrMargin] FROM [InvestorAccountIntime] WHERE [InvestorID]=?";
		String name="CurrMargin";
		Object[] params=new Object[]{investorId};
		BigDecimal CurrMargin=null;
		try {
			Object result=SQLServerUtil.ExecuteScalar(sql, name, params, fuServer);
			if(result==null){
				CurrMargin=BigDecimal.ZERO;
			}else{
				CurrMargin=new BigDecimal(result.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				SQLServerUtil.colseConnection(SQLServerUtil.getConnect(fuServer));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return CurrMargin;
	}

	/**
	 * 查询风险度
	 * @return
	 */
	public BigDecimal findRisk(FuServer fuServer,Integer investorId){
		String sql="SELECT [Risk] FROM [InvestorAccountIntime] where [InvestorID] = ?";
		String name="Risk";
		Object[] params=new Object[]{investorId};
		BigDecimal Risk=null;
		try {
			Object result=SQLServerUtil.ExecuteScalar(sql, name, params, fuServer);
			if(result==null){
				Risk=BigDecimal.ZERO;
			}else{
				Risk=new BigDecimal(result.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				SQLServerUtil.colseConnection(SQLServerUtil.getConnect(fuServer));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return Risk;
	}
	
	/**
	 * 根据id查询出入金的处理结果和信息
	 */
	public Map<String, Object> findResultByInvestorPaymentId(FuServer fuServer,Integer id){
		Map<String, Object> map=new HashMap<String, Object>();
		String sql="SELECT [Result],[ErrorMsg] FROM [InvestorPayment] where [ID] = ?";
		Object[] params=new Object[]{id};
		try {
			ArrayList list=SQLServerUtil.ExecuteReader(sql, params, fuServer);
			if(list.size()>0){
				map.put("result", list.get(0));
				map.put("errormsg", list.get(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				SQLServerUtil.colseConnection(SQLServerUtil.getConnect(fuServer));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	
	/**
	 * 根据id查询增加用户的处理结果和信息
	 */
	public Map<String, Object> findResultByInvestorAddId(FuServer fuServer,Integer id){
		Map<String, Object> map=new HashMap<String, Object>();
		String sql="SELECT [Result],[ErrorMsg] FROM [InvestorAdd] where [ID] = ?";
		Object[] params=new Object[]{id};
		try {
			ArrayList list=SQLServerUtil.ExecuteReader(sql, params, fuServer);
			if(list.size()>0){
				map.put("result", list.get(0));
				map.put("errormsg", list.get(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				SQLServerUtil.colseConnection(SQLServerUtil.getConnect(fuServer));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	
	/**
	 * 根据id查询调整组规则的处理结果和信息
	 */
	public Map<String, Object> findResultByInvestorTriggerId(FuServer fuServer,Integer id){
		Map<String, Object> map=new HashMap<String, Object>();
		String sql="SELECT [Result],[ErrorMsg] FROM [InvestorTrigger] where [ID] = ?";
		Object[] params=new Object[]{id};
		try {
			ArrayList list=SQLServerUtil.ExecuteReader(sql, params, fuServer);
			if(list.size()>0){
				map.put("result", list.get(0));
				map.put("errormsg", list.get(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				SQLServerUtil.colseConnection(SQLServerUtil.getConnect(fuServer));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	/**
	 * 提取利润出金
	 * 
	 * @param investorId
	 * @param account
	 * @return
	 */
	public Integer drawProfit(FuServer fuServer, FuProgram fuProgram, BigDecimal money) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql="INSERT INTO [InvestorPayment]([InvestorID],[Payment],[PaymentCredit],[PaymentDate],[Description],[Result],[ErrorMsg])VALUES(?,?,?,?,?,?,?)";
		Object[] params=new Object[7];
		params[0]=fuProgram.getTradeAccount();
		params[1]=money.multiply(new BigDecimal(-1));
		params[2]=BigDecimal.ZERO;
		params[3]=new Timestamp(new Date().getTime());
		params[4]="提取利润出金"+sdf.format(new Date());
		params[5]=0;	
		params[6]="待出金";
		int paymentId=0;
		Connection conn=SQLServerUtil.getConnect(fuServer);
		try {
			conn.setAutoCommit(false);
			paymentId=SQLServerUtil.ExecuteNonQueryId(sql, params, conn); 
			if(paymentId>0){
				conn.commit();
			}else{
				conn.rollback();
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				SQLServerUtil.colseConnection(SQLServerUtil.getConnect(fuServer));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		return paymentId;
	}
	
	/**
	 * 查询可提取的利润
	 * 
	 */
	public BigDecimal getBenefit(FuServer fuServer,FuProgram fuProgram,BigDecimal oldMatchMoney, BigDecimal oldSafeMoney) {
		Calendar cal1 = Calendar.getInstance();
		Date time0 = cal1.getTime();
		cal1.set(Calendar.HOUR_OF_DAY, 0);
		cal1.set(Calendar.MINUTE, 0);
		cal1.set(Calendar.SECOND, 0);
		Date time1 = cal1.getTime();
		cal1.set(Calendar.HOUR_OF_DAY, 9);
		cal1.set(Calendar.MINUTE, 15);
		cal1.set(Calendar.SECOND, 0);
		Date time2 = cal1.getTime();
		boolean dealing1 = time0.after(time1) && time0.before(time2);//0:00-9:15时间段
		
		Calendar cal2 = Calendar.getInstance();
		Date time3 = cal2.getTime();
		cal2.set(Calendar.HOUR_OF_DAY, 9);
		cal2.set(Calendar.MINUTE, 15);
		cal2.set(Calendar.SECOND, 1);
		Date time4 = cal2.getTime();
		cal2.set(Calendar.HOUR_OF_DAY, 14);
		cal2.set(Calendar.MINUTE, 50);
		cal2.set(Calendar.SECOND, 0);
		Date time5 = cal2.getTime();
		boolean dealing2 = time3.after(time4) && time3.before(time5);//9:15-14:50时间段
		
		Calendar cal3 = Calendar.getInstance();
		Date time6 = cal3.getTime();
		cal3.set(Calendar.HOUR_OF_DAY, 14);
		cal3.set(Calendar.MINUTE, 50);
		cal3.set(Calendar.SECOND, 1);
		Date time7 = cal3.getTime();
		cal3.set(Calendar.HOUR_OF_DAY, 15);
		cal3.set(Calendar.MINUTE, 16);
		cal3.set(Calendar.SECOND, 0);
		Date time8 = cal3.getTime();
		boolean dealing3 = time6.after(time7) && time3.before(time8);//14:50-15:16时间段
		
		Calendar cal4 = Calendar.getInstance();
		Date time9 = cal4.getTime();
		cal4.set(Calendar.HOUR_OF_DAY, 15);
		cal4.set(Calendar.MINUTE, 16);
		cal4.set(Calendar.SECOND, 1);
		Date time10 = cal4.getTime();
		cal4.set(Calendar.HOUR_OF_DAY, 16);
		cal4.set(Calendar.MINUTE, 0);
		cal4.set(Calendar.SECOND, 0);
		Date time11 = cal4.getTime();
		boolean dealing4 = time9.after(time10) && time3.before(time11);//15:16-16:00时间段
		
		Calendar cal5 = Calendar.getInstance();
		Date time12 = cal5.getTime();
		cal5.set(Calendar.HOUR_OF_DAY, 16);
		cal5.set(Calendar.MINUTE, 0);
		cal5.set(Calendar.SECOND, 1);
		Date time13 = cal5.getTime();
		cal5.set(Calendar.HOUR_OF_DAY, 23);
		cal5.set(Calendar.MINUTE, 59);
		cal5.set(Calendar.SECOND, 59);
		Date time14 = cal5.getTime();
		boolean dealing5 = time12.after(time13) && time3.before(time14);//16:00-23:59时间段
		
		//可提盈金额
		BigDecimal benefit=new BigDecimal(0);
		int weekday=cal1.get(Calendar.DAY_OF_WEEK);
		if(weekday==1||weekday==7){//周末
			dealing1=false;
			dealing2=false;
			dealing3=false;
			dealing4=false;
			dealing5=false;
			return new BigDecimal(0);
		}
		//持仓占用保证金
		BigDecimal curMargin = findCurrMargin(fuServer, fuProgram.getTradeAccount());
		
		//动态权益
		BigDecimal balance = findBalance(fuServer, fuProgram.getTradeAccount());
		
		//可用资金
		BigDecimal withdrawQuota = findWithdrawQuota(fuServer, fuProgram.getTradeAccount());
		
		//（客户保证金＋配资金额）*80％
		BigDecimal positionMargin = fuProgram.getTotalMatchMoney().add(oldMatchMoney).add(oldSafeMoney).multiply(new BigDecimal(0.8));
		//持仓占用保证金>0表示有持仓
		if(curMargin.compareTo(BigDecimal.ZERO)==1){
			// 盘中计算盈利方法
			if (dealing1==true||dealing2==true||dealing5==true) {
				//持仓占用保证金<=（客户保证金＋配资金额）*80％
				if(curMargin.compareTo(positionMargin)==-1||curMargin.compareTo(positionMargin)==0){
					//可用资金小就用可用资金，否则就正常计算
					if(withdrawQuota.compareTo(balance.subtract(fuProgram.getTotalMatchMoney().add(oldMatchMoney).add(oldSafeMoney)))==-1){
						benefit=withdrawQuota;
					}else{
						benefit=balance.subtract(fuProgram.getTotalMatchMoney().add(oldMatchMoney).add(oldSafeMoney));
					}
				}else{
					return new BigDecimal(0);
				}
			}
			
			//隔夜倍数
			BigDecimal geYe;
			if(fuProgram.getDoubleNum()>5){//不隔夜
				geYe=new BigDecimal(Property.getProperty("OverNight_Not"));
			}else{
				if(new BigDecimal(Property.getProperty("OverNight_Good_Rate")).compareTo(new BigDecimal(Property.getProperty("OverNight_Stock_Index_Rate")))==-1){
					geYe=new BigDecimal(Property.getProperty("OverNight_Good_Rate"));
				}else{
					geYe=new BigDecimal(Property.getProperty("OverNight_Stock_Index_Rate"));
				}
			}
			
			//盘后计算盈利方法
			if (dealing3==true||dealing4==true) {
				//客户保证金－持仓占用保证金／隔夜倍数>0
				if(fuProgram.getSafeMoney().add(oldSafeMoney).compareTo(curMargin.divide(geYe,2,BigDecimal.ROUND_HALF_UP))==1){
					benefit=balance.subtract(fuProgram.getTotalMatchMoney().add(oldMatchMoney).add(oldSafeMoney));
				}else{
					benefit=balance.subtract(fuProgram.getTotalMatchMoney().add(oldMatchMoney).add(oldSafeMoney)).subtract(curMargin.divide(geYe,2,BigDecimal.ROUND_HALF_UP).subtract(fuProgram.getSafeMoney().add(oldSafeMoney)));
				}
			}
		}else{//持仓占用保证金<=0无持仓
			benefit = balance.subtract(fuProgram.getTotalMatchMoney().add(oldMatchMoney).add(oldSafeMoney));
		}
		if(benefit.compareTo(BigDecimal.ZERO)==-1){
			return BigDecimal.ZERO;
		}else{
			return benefit;
		}
	}
	
	/**
	 * 常规方案结算的时候出金(先出实盘资金，再出交易账号用户自己的资金)
	 * @return
	 */
	public List<Object> programStop(FuServer fuServer,FuProgram fuProgram,BigDecimal matchMoney,BigDecimal drawMoney){
		List<Object> list=new ArrayList<Object>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql="INSERT INTO [InvestorPayment]([InvestorID],[Payment],[PaymentCredit],[PaymentDate],[Description],[Result],[ErrorMsg])VALUES(?,?,?,?,?,?,?)";
		Object[] params=new Object[7];
		params[0]=fuProgram.getTradeAccount();
		params[1]=matchMoney.multiply(new BigDecimal(-1));
		params[2]=BigDecimal.ZERO;
		params[3]=new Timestamp(new Date().getTime());
		params[4]="结算实盘资金出金"+sdf.format(new Date());
		params[5]=0;	
		params[6]="待出金";
		
		String sql2="INSERT INTO [InvestorPayment]([InvestorID],[Payment],[PaymentCredit],[PaymentDate],[Description],[Result],[ErrorMsg])VALUES(?,?,?,?,?,?,?)";
		Object[] params2=new Object[7];
		params2[0]=fuProgram.getTradeAccount();
		params2[1]=drawMoney.multiply(new BigDecimal(-1));
		params2[2]=BigDecimal.ZERO;
		params2[3]=new Timestamp(new Date().getTime());
		params2[4]="结算账户余额出金"+sdf.format(new Date());
		params2[5]=0;	
		params2[6]="待出金";
		
		int paymentId1=0;
		int paymentId2=0;
		Connection conn=SQLServerUtil.getConnect(fuServer);
		try {
			conn.setAutoCommit(false);
			paymentId1=SQLServerUtil.ExecuteNonQueryId(sql, params, conn); 
			paymentId2=SQLServerUtil.ExecuteNonQueryId(sql2, params2, conn); 
			if(paymentId1>0&&paymentId2>0){
				conn.commit();
			}else{
				conn.rollback();
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				SQLServerUtil.colseConnection(SQLServerUtil.getConnect(fuServer));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		list.add(paymentId1);
		list.add(paymentId2);
		return list;
	}
	
	/**
	 * 增配子方案到期结算的操作(先降低风控线，再出实盘资金，再出交易账号用户自己的资金)
	 * @return
	 */
	public List<Object> sonProgramStop(FuServer fuServer,FuProgram fuProgram,FuProgram sonPro,BigDecimal matchMoney,BigDecimal drawMoney){
		List<Object> list=new ArrayList<Object>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		/**
		 * 调整组规则
		 */
		String sql1="INSERT INTO [InvestorTrigger]([InvestorID],[ForceLineWarn],[ForceLine],[TradeProduct],[Description],[Result],[ErrorMsg])VALUES(?,?,?,?,?,?,?)";
		Object[] params1=new Object[7];
		params1[0]=fuProgram.getTradeAccount();
		params1[1]=fuProgram.getWarnLine();
		params1[2]=fuProgram.getCloseLine();
		if(fuProgram.getDoubleNum()>5){
			params1[3]=2;//只能做股指
		}else{
			params1[3]=3;//全都可以做
		}
		params1[4]="增配子方案到期结算调增风控规则"+sdf.format(new Date());
		params1[5]=0;
		params1[6]="请设置规则";
		/**
		 * 出实盘资金
		 */
		String sql2="INSERT INTO [InvestorPayment]([InvestorID],[Payment],[PaymentCredit],[PaymentDate],[Description],[Result],[ErrorMsg])VALUES(?,?,?,?,?,?,?)";
		Object[] params2=new Object[7];
		params2[0]=fuProgram.getTradeAccount();
		params2[1]=matchMoney.multiply(new BigDecimal(-1));
		params2[2]=BigDecimal.ZERO;
		params2[3]=new Timestamp(new Date().getTime());
		params2[4]="结算实盘资金出金"+sdf.format(new Date());
		params2[5]=0;	
		params2[6]="待出金";
		/**
		 * 出自有资金
		 */
		String sql3="INSERT INTO [InvestorPayment]([InvestorID],[Payment],[PaymentCredit],[PaymentDate],[Description],[Result],[ErrorMsg])VALUES(?,?,?,?,?,?,?)";
		Object[] params3=new Object[7];
		params3[0]=fuProgram.getTradeAccount();
		params3[1]=drawMoney.multiply(new BigDecimal(-1));
		params3[2]=BigDecimal.ZERO;
		params3[3]=new Timestamp(new Date().getTime());
		params3[4]="结算账户余额出金"+sdf.format(new Date());
		params3[5]=0;	
		params3[6]="待出金";
		
		int id1=0;
		int id2=0;
		int id3=0;
		Connection conn=SQLServerUtil.getConnect(fuServer);
		try {
			conn.setAutoCommit(false);
			id1=SQLServerUtil.ExecuteNonQueryId(sql1, params1, conn); 
			id2=SQLServerUtil.ExecuteNonQueryId(sql2, params2, conn); 
			id3=SQLServerUtil.ExecuteNonQueryId(sql3, params3, conn); 
			if(id1>0&&id2>0&&id3>0){
				conn.commit();
			}else{
				conn.rollback();
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				SQLServerUtil.colseConnection(SQLServerUtil.getConnect(fuServer));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		list.add(id1);
		list.add(id2);
		list.add(id3);
		return list;
	}

	/**
	 * 后台管理员审核通过方案
	 * @param fuUser
	 * @param fuProgram
	 * @param fuServer
	 * @return
	 */
	public List<Object> saveProgram(FuUser fuUser,FuProgram fuProgram,FuServer fuServer){
		List<Object> list=new ArrayList<Object>();
		//新增用户
		String sql1="INSERT INTO [InvestorAdd]([GroupID],[InvestorID],[InvestorName],[Password],[Description],[IsSimTrader],[Result],[ErrorMsg])VALUES(?,?,?,?,?,?,?,?)";
		Object[] params=new Object[8];
		//日配
		if(fuProgram.getProgramType()==1){
			if(fuProgram.getDoubleNum()==1){
				params[0]=fuServer.getDayId1();
			}else if(fuProgram.getDoubleNum()==2){
				params[0]=fuServer.getDayId2();
			}else if(fuProgram.getDoubleNum()==3){
				params[0]=fuServer.getDayId3();
			}else if(fuProgram.getDoubleNum()==4){
				params[0]=fuServer.getDayId4();
			}else if(fuProgram.getDoubleNum()==5){
				params[0]=fuServer.getDayId5();
			}else if(fuProgram.getDoubleNum()==6){
				params[0]=fuServer.getDayId6();
			}else if(fuProgram.getDoubleNum()==7){
				params[0]=fuServer.getDayId7();
			}else if(fuProgram.getDoubleNum()==8){
				params[0]=fuServer.getDayId8();
			}else if(fuProgram.getDoubleNum()==9){
				params[0]=fuServer.getDayId9();
			}else if(fuProgram.getDoubleNum()==10){
				params[0]=fuServer.getDayId10();
			}
		}
		//月配
		if(fuProgram.getProgramType()==2){
			if(fuProgram.getDoubleNum()==1){
				params[0]=fuServer.getMonthId1();
			}else if(fuProgram.getDoubleNum()==2){
				params[0]=fuServer.getMonthId2();
			}else if(fuProgram.getDoubleNum()==3){
				params[0]=fuServer.getMonthId3();
			}else if(fuProgram.getDoubleNum()==4){
				params[0]=fuServer.getMonthId4();
			}else if(fuProgram.getDoubleNum()==5){
				params[0]=fuServer.getMonthId5();
			}else if(fuProgram.getDoubleNum()==6){
				params[0]=fuServer.getMonthId6();
			}else if(fuProgram.getDoubleNum()==7){
				params[0]=fuServer.getMonthId7();
			}else if(fuProgram.getDoubleNum()==8){
				params[0]=fuServer.getMonthId8();
			}else if(fuProgram.getDoubleNum()==9){
				params[0]=fuServer.getMonthId9();
			}else if(fuProgram.getDoubleNum()==10){
				params[0]=fuServer.getMonthId10();
			}
		}
		//如果该用户号在用户刷新表或新增用户表存在就一直+1,直到不存在
		int i=0;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int userNum;
		synchronized(this){
			while(true){
				i++;
				int	uNum=fuServer.getNowNumber()+fuServer.getStartNumber()+i;
				String sql4="SELECT [InvestorID] FROM [InvestorAccountIntime] where [InvestorID] = ?";
				String name4="InvestorID";
				Object[] params4=new Object[]{uNum};
				Object result=new Object();
				try {
					result=SQLServerUtil.ExecuteScalar(sql4, name4, params4, fuServer);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(result==null){//先从用户信息刷新表，没相同的用户号再查新增用户表
					String sql5="SELECT [InvestorID] FROM [InvestorAdd] where [InvestorID] = ?";
					String name5="InvestorID";
					Object[] params5=new Object[]{uNum};
					Object result2=new Object();
					try {
						result2=SQLServerUtil.ExecuteScalar(sql5, name5, params5, fuServer);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if(result2==null){
						userNum=fuServer.getNowNumber()+fuServer.getStartNumber()+i;
					    break;
					}
				}
			}
		}
		params[1]=userNum;
		params[2]=fuUser.getUserName();
		params[3]=fuProgram.getFuUser().getPhone().substring(5, 11);
		params[4]="新增用户"+sdf.format(new Date());
		params[5]=fuServer.getIsSimTrader();
		params[6]=0;
		params[7]="请开通";
			
			
		/**
		 * 调整资金
		 */
		String sql2="INSERT INTO [InvestorPayment]([InvestorID],[Payment],[PaymentCredit],[PaymentDate],[Description],[Result],[ErrorMsg])VALUES(?,?,?,?,?,?,?)";
		Object[] params2=new Object[7];
		params2[0]=userNum;
		params2[1]=fuProgram.getMatchMoney().add(fuProgram.getSafeMoney());
		params2[2]=BigDecimal.ZERO;
		params2[3]=new Timestamp(new Date().getTime());
		params2[4]="开户入金"+sdf.format(new Date());
		params2[5]=0;
		params2[6]="请入金";
		
		/**
		 * 调整组规则
		 */
		String sql3="INSERT INTO [InvestorTrigger]([InvestorID],[ForceLineWarn],[ForceLine],[TradeProduct],[Description],[Result],[ErrorMsg])VALUES(?,?,?,?,?,?,?)";
		Object[] params3=new Object[7];
		params3[0]=userNum;
		params3[1]=fuProgram.getWarnLine();
		params3[2]=fuProgram.getCloseLine();
		if(fuProgram.getDoubleNum()>5){
			params3[3]=2;//只能做股指
		}else{
			params3[3]=3;//全都可以做
		}
		params3[4]="开户新增风控规则"+sdf.format(new Date());
		params3[5]=0;
		params3[6]="请设置规则";
			
		int id1=0;	
		int id2=0;	
		int id3=0;	
		Connection conn=SQLServerUtil.getConnect(fuServer);
		try {
			conn.setAutoCommit(false);
			id1=SQLServerUtil.ExecuteNonQueryId(sql1, params, conn); 
			id2=SQLServerUtil.ExecuteNonQueryId(sql2, params2, conn); 
			id3=SQLServerUtil.ExecuteNonQueryId(sql3, params3, conn);
			if(id1>0&&id2>0&&id3>0){
				conn.commit();
				fuServer.setNowNumber(fuServer.getNowNumber()+i);
				fuServer.setServerMoney(fuServer.getServerMoney().subtract(fuProgram.getMatchMoney()));
				fuServerDao.save(fuServer);
				fuProgram.setTradeAccount(userNum);
				fuProgram.setTradePassword(fuProgram.getFuUser().getPhone().substring(5, 11));
				fuProgramDao.save(fuProgram);
			}else{
				conn.rollback();
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				SQLServerUtil.colseConnection(SQLServerUtil.getConnect(fuServer));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		list.add(id1);
		list.add(id2);
		list.add(id3);
		return list;
	}
	
	/**
	 * 期货大赛创建账号
	 * @param fuUser
	 * @param fuProgram
	 * @param fuServer
	 * @return
	 */
	public List<Object> addUserProgramGame(FuUser fuUser,FuServer fuServer){
		List<Object> list=new ArrayList<Object>();
		//新增用户
		String sql1="INSERT INTO [InvestorAdd]([GroupID],[InvestorID],[InvestorName],[Password],[Description],[IsSimTrader],[Result],[ErrorMsg])VALUES(?,?,?,?,?,?,?,?)";
		Object[] params=new Object[8];
		//月配
		params[0]=fuServer.getMonthId9();
			
		//如果该用户号在用户刷新表或新增用户表存在就一直+1,直到不存在
		int i=0;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int userNum=0;
		synchronized(this){
			while(true){
				i++;
				int	uNum=fuServer.getNowNumber()+fuServer.getStartNumber()+i;
				String sql4="SELECT [InvestorID] FROM [InvestorAccountIntime] where [InvestorID] = ?";
				String name4="InvestorID";
				Object[] params4=new Object[]{uNum};
				Object result=new Object();
				try {
					result=SQLServerUtil.ExecuteScalar(sql4, name4, params4, fuServer);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(result==null){//先从用户信息刷新表，没相同的用户号再查新增用户表
					String sql5="SELECT [InvestorID] FROM [InvestorAdd] where [InvestorID] = ?";
					String name5="InvestorID";
					Object[] params5=new Object[]{uNum};
					Object result2=new Object();
					try {
						result2=SQLServerUtil.ExecuteScalar(sql5, name5, params5, fuServer);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if(result2==null){
						userNum=fuServer.getNowNumber()+fuServer.getStartNumber()+i;
					    break;
					}
				}
			}
		}
		params[1]=userNum;
		params[2]=fuUser.getPhone();
		params[3]=fuUser.getPhone().substring(5, 11);
		params[4]="期指大赛新增用户"+sdf.format(new Date());
		params[5]=fuServer.getIsSimTrader();
		params[6]=0;
		params[7]="请开通";
		
		/**
		 * 调整资金
		 */
		String sql2="INSERT INTO [InvestorPayment]([InvestorID],[Payment],[PaymentCredit],[PaymentDate],[Description],[Result],[ErrorMsg])VALUES(?,?,?,?,?,?,?)";
		Object[] params2=new Object[7];
		params2[0]=userNum;
		params2[1]=new BigDecimal(1000000);
		params2[2]=BigDecimal.ZERO;
		params2[3]=new Timestamp(new Date().getTime());
		params2[4]="期指大赛开户入金"+sdf.format(new Date());
		params2[5]=0;
		params2[6]="请入金";
		
		/**
		 * 调整组规则
		 */
		String sql3="INSERT INTO [InvestorTrigger]([InvestorID],[ForceLineWarn],[ForceLine],[TradeProduct],[Description],[Result],[ErrorMsg])VALUES(?,?,?,?,?,?,?)";
		Object[] params3=new Object[7];
		params3[0]=userNum;
		params3[1]=new BigDecimal(900000);
		params3[2]=new BigDecimal(900000);
		params3[3]=2;//只能做股指
		params3[4]="期指大赛风控规则"+sdf.format(new Date());
		params3[5]=0;
		params3[6]="请设置规则";
			
		int id1=0;	
		int id2=0;	
		int id3=0;	
		Connection conn=SQLServerUtil.getConnect(fuServer);
		try {
			conn.setAutoCommit(false);
			id1=SQLServerUtil.ExecuteNonQueryId(sql1, params, conn); 
			id2=SQLServerUtil.ExecuteNonQueryId(sql2, params2, conn); 
			id3=SQLServerUtil.ExecuteNonQueryId(sql3, params3, conn);
			if(id1>0 && id2>0 && id3>0){
				conn.commit();
				fuServer.setNowNumber(fuServer.getNowNumber()+i);
				fuServerDao.save(fuServer);
			}else{
				conn.rollback();
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				SQLServerUtil.colseConnection(SQLServerUtil.getConnect(fuServer));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		list.add(id1);
		list.add(id2);
		list.add(id3);
		list.add(userNum);
		return list;
	}
	
	/**
	 * 期指大赛正式开始，初始化资金为100万
	 * @return
	 */
	public Integer gameResetMoney(FuServer fuServer,Integer tradeAccount,BigDecimal addMoney){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql="INSERT INTO [InvestorPayment]([InvestorID],[Payment],[PaymentCredit],[PaymentDate],[Description],[Result],[ErrorMsg])VALUES(?,?,?,?,?,?,?)";
		Object[] params=new Object[7];
		params[0]=tradeAccount;
		params[1]=addMoney;
		params[2]=BigDecimal.ZERO;
		params[3]=new Timestamp(new Date().getTime());
		params[4]="期指大赛初始化资金"+sdf.format(new Date());
		params[5]=0;	
		params[6]="待入金";
		
		Integer paymentId=0;
		Connection conn=SQLServerUtil.getConnect(fuServer);
		try {
			conn.setAutoCommit(false);
			paymentId=SQLServerUtil.ExecuteNonQueryId(sql, params, conn); 
			if(paymentId>0){
				conn.commit();
			}else{
				conn.rollback();
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				SQLServerUtil.colseConnection(SQLServerUtil.getConnect(fuServer));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		return paymentId;
	}
	
	/**
	 * 增配子方案审核通过入金
	 * @param fuUser
	 * @param fuProgram
	 * @param fuServer
	 * @return
	 */
	public Integer addPaymentBySonProgram(FuUser fuUser,FuProgram fuProgram,FuProgram sonPro,FuServer fuServer){
		/**
		 * 调整资金
		 */
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql="INSERT INTO [InvestorPayment]([InvestorID],[Payment],[PaymentCredit],[PaymentDate],[Description],[Result],[ErrorMsg])VALUES(?,?,?,?,?,?,?)";
		Object[] params=new Object[7];
		params[0]=fuProgram.getTradeAccount();
		params[1]=sonPro.getMatchMoney().add(sonPro.getSafeMoney());
		params[2]=BigDecimal.ZERO;
		params[3]=new Timestamp(new Date().getTime());
		params[4]="增配子方案开户入金"+sdf.format(new Date());
		params[5]=0;
		params[6]="请入金";
		int id=0;
		Connection conn=SQLServerUtil.getConnect(fuServer);
		try {
			conn.setAutoCommit(false);
			id=SQLServerUtil.ExecuteNonQueryId(sql, params, conn); 
			if(id>0){
				conn.commit();
				fuServer.setServerMoney(fuServer.getServerMoney().subtract(sonPro.getMatchMoney()));
				fuServerDao.save(fuServer);
			}else{
				conn.rollback();
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				SQLServerUtil.colseConnection(SQLServerUtil.getConnect(fuServer));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		return id;
	}
	
	/**
	 * 增配子方案审核通过风控线更新
	 * @param fuUser
	 * @param fuProgram
	 * @param fuServer
	 * @return
	 */
	public Integer addTriggerBySonProgram(FuUser fuUser,FuProgram fuProgram,FuProgram sonPro,FuServer fuServer){
		/**
		 * 调整组规则
		 */
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql="INSERT INTO [InvestorTrigger]([InvestorID],[ForceLineWarn],[ForceLine],[TradeProduct],[Description],[Result],[ErrorMsg])VALUES(?,?,?,?,?,?,?)";
		Object[] params=new Object[7];
		params[0]=fuProgram.getTradeAccount();
		params[1]=fuProgram.getWarnLine().add(sonPro.getWarnLine());
		params[2]=fuProgram.getCloseLine().add(sonPro.getCloseLine());
		if(fuProgram.getDoubleNum()>5){
			params[3]=2;//只能做股指
		}else{
			params[3]=3;//全都可以做
		}
		params[4]="增配子方案开户调整风控规则"+sdf.format(new Date());
		params[5]=0;
		params[6]="请设置规则";
		int id=0;
		Connection conn=SQLServerUtil.getConnect(fuServer);
		try {
			conn.setAutoCommit(false);
			id=SQLServerUtil.ExecuteNonQueryId(sql, params, conn); 
			if(id>0){
				conn.commit();
			}else{
				conn.rollback();
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				SQLServerUtil.colseConnection(SQLServerUtil.getConnect(fuServer));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		return id;
	}
	
	/**
	 * 减配方案
	 * @param fuUser
	 * @param fuProgram
	 * @param fuServer
	 * @return
	 */
	public List<Object> saveSubProgram(FuUser fuUser,FuProgram fuProgram,FuProgram subPro,FuServer fuServer){
		List<Object> list=new ArrayList<Object>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		/**
		 * 调整组规则
		 */
		String sql2="INSERT INTO [InvestorTrigger]([InvestorID],[ForceLineWarn],[ForceLine],[TradeProduct],[Description],[Result],[ErrorMsg])VALUES(?,?,?,?,?,?,?)";
		Object[] params2=new Object[7];
		params2[0]=fuProgram.getTradeAccount();
		params2[1]=subPro.getWarnLine();
		params2[2]=subPro.getCloseLine();
		if(fuProgram.getDoubleNum()>5){
			params2[3]=2;//只能做股指
		}else{
			params2[3]=3;//全都可以做
		}
		params2[4]="减配调整风控规则"+sdf.format(new Date());
		params2[5]=0;
		params2[6]="请设置规则";
		
		/**
		 * 调整资金
		 */
		String sql1="INSERT INTO [InvestorPayment]([InvestorID],[Payment],[PaymentCredit],[PaymentDate],[Description],[Result],[ErrorMsg])VALUES(?,?,?,?,?,?,?)";
		Object[] params1=new Object[7];
		params1[0]=fuProgram.getTradeAccount();
		params1[1]=fuProgram.getMatchMoney().subtract(subPro.getMatchMoney()).multiply(new BigDecimal(-1));
		params1[2]=BigDecimal.ZERO;
		params1[3]=new Timestamp(new Date().getTime());
		params1[4]="减配出金"+sdf.format(new Date());
		params1[5]=0;
		params1[6]="请出金";
		
		int id1=0;
		int id2=0;
		Connection conn=SQLServerUtil.getConnect(fuServer);
		try {
			conn.setAutoCommit(false);
			id2=SQLServerUtil.ExecuteNonQueryId(sql2, params2, conn); 
			id1=SQLServerUtil.ExecuteNonQueryId(sql1, params1, conn); 
			if(id1>0&&id2>0){
				conn.commit();
				fuServer.setServerMoney(fuServer.getServerMoney().add(fuProgram.getMatchMoney().subtract(subPro.getMatchMoney())));//服务器的金额增加了，金额=原实盘资金减新实盘资金
				fuServerDao.save(fuServer);
			}else{
				conn.rollback();
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				SQLServerUtil.colseConnection(SQLServerUtil.getConnect(fuServer));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		list.add(id1);
		list.add(id2);
		return list;
	}
	
	/**
	 * 前台追加保证金，余额充足直接通过审核并写入众期数据库（入金）
	 * @param fuProgram
	 * @param fuServer
	 * @return
	 */
	public Integer savePayment(BigDecimal money,FuProgram fuProgram,FuServer fuServer){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql="INSERT INTO [InvestorPayment]([InvestorID],[Payment],[PaymentCredit],[PaymentDate],[Description],[Result],[ErrorMsg])VALUES(?,?,?,?,?,?,?)";
		Object[] params=new Object[7];
		params[0]=fuProgram.getTradeAccount();
		params[1]=money;
		params[2]=BigDecimal.ZERO;
		params[3]=new Timestamp(new Date().getTime());
		params[4]="追加保证金"+sdf.format(new Date());
		params[5]=0;
		params[6]="请入金";
		int paymentId=0;
		Connection conn=SQLServerUtil.getConnect(fuServer);
		try {
			conn.setAutoCommit(false);
			paymentId=SQLServerUtil.ExecuteNonQueryId(sql, params, conn);
			if(paymentId>0){
				conn.commit();
			}else{
				conn.rollback();
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				SQLServerUtil.colseConnection(SQLServerUtil.getConnect(fuServer));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		return paymentId;
	}

}
