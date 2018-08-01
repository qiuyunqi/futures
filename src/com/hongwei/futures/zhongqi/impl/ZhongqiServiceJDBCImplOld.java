package com.hongwei.futures.zhongqi.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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
import com.hongwei.futures.util.JDBCUtil;
import com.hongwei.futures.util.Property;
import com.hongwei.futures.zhongqi.ZhongqiServiceOld;

@Service
public class ZhongqiServiceJDBCImplOld implements ZhongqiServiceOld {
	@Autowired
	private FuServerDao fuServerDao;
	@Autowired
	private FuProgramDao fuProgramDao;

	/**
	 * 查询可用资金
	 * @param investorId
	 * @return
	 */
	@Override
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
	@Override
	public BigDecimal findBalance(FuServer fuServer,Integer investorId) {
		Connection con=JDBCUtil.getConnection(fuServer);
		PreparedStatement ps=null;
		ResultSet rs = null;
		try {
			ps=con.prepareStatement("SELECT [Balance] FROM [InvestorAccountIntime] WHERE [InvestorID]=?");
			ps.setInt(1, investorId);
			rs = ps.executeQuery();
			if(rs.next()){
				return rs.getBigDecimal("Balance");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.release(rs, ps, con);
		}
		return new BigDecimal(0);
	}

	/**
	 * 查询持仓占用保证金
	 * 
	 * @param investorId
	 * @return 
	 */
	@Override
	public BigDecimal findCurrMargin(FuServer fuServer,Integer investorId) {
		Connection con=JDBCUtil.getConnection(fuServer);
		PreparedStatement ps=null;
		ResultSet rs = null;
		try {
			ps=con.prepareStatement("SELECT [CurrMargin] FROM [InvestorAccountIntime] WHERE [InvestorID]=?");
			ps.setInt(1, investorId);
			rs = ps.executeQuery();
			if(rs.next()){
				return rs.getBigDecimal("CurrMargin");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.release(rs, ps, con);
		}
		return new BigDecimal(0);
	}

	/**
	 * 查询风险度
	 * @return
	 */
	@Override
	public BigDecimal findRisk(FuServer fuServer,Integer investorId){
		Connection con=JDBCUtil.getConnection(fuServer);
		PreparedStatement ps=null;
		ResultSet rs = null;
		try {
			ps=con.prepareStatement("SELECT [Risk] FROM [InvestorAccountIntime] where [InvestorID] = ?");
			ps.setInt(1, investorId);
			rs = ps.executeQuery();
			if(rs.next()){
				return rs.getBigDecimal("Risk");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.release(rs, ps, con);
		}
		return new BigDecimal(0);
	}
	
	/**
	 * 根据id查询出入金的处理结果和信息
	 */
	@Override
	public Map<String, Object> findResultByInvestorPaymentId(FuServer fuServer,Integer id){
		Connection con=JDBCUtil.getConnection(fuServer);
		PreparedStatement ps=null;
		ResultSet rs = null;
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			ps=con.prepareStatement("SELECT [Result],[ErrorMsg] FROM [InvestorPayment] where [ID] = ?");
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next()){
				int result=rs.getInt(1);
				String errormsg=rs.getString(2);
				map.put("result", result);
				map.put("errormsg", errormsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.release(rs, ps, con);
		}
		return map;
	}
	
	/**
	 * 根据id查询增加用户的处理结果和信息
	 */
	@Override
	public Map<String, Object> findResultByInvestorAddId(FuServer fuServer,Integer id){
		Connection con=JDBCUtil.getConnection(fuServer);
		PreparedStatement ps=null;
		ResultSet rs = null;
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			ps=con.prepareStatement("SELECT [Result],[ErrorMsg] FROM [InvestorAdd] where [ID] = ?");
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next()){
				int result=rs.getInt(1);
				String errormsg=rs.getString(2);
				map.put("result", result);
				map.put("errormsg", errormsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.release(rs, ps, con);
		}
		return map;
	}
	
	/**
	 * 根据id查询调整组规则的处理结果和信息
	 */
	@Override
	public Map<String, Object> findResultByInvestorTriggerId(FuServer fuServer,Integer id){
		Connection con=JDBCUtil.getConnection(fuServer);
		PreparedStatement ps=null;
		ResultSet rs = null;
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			ps=con.prepareStatement("SELECT [Result],[ErrorMsg] FROM [InvestorTrigger] where [ID] = ?");
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if(rs.next()){
				int result=rs.getInt(1);
				String errormsg=rs.getString(2);
				map.put("result", result);
				map.put("errormsg", errormsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.release(rs, ps, con);
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
	@Override
	public int drawProfit(FuServer fuServer, FuProgram fuProgram, BigDecimal money) {
		Connection con=JDBCUtil.getConnection(fuServer);
		PreparedStatement ps=null;
		ResultSet rs = null;
		int paymentId=0;
		try {
			con.setAutoCommit(false);
			/**
			 * 调整资金
			 */
			ps=con.prepareStatement("INSERT INTO [InvestorPayment]([InvestorID],[Payment],[PaymentCredit],[PaymentDate],[Description],[Result],[ErrorMsg])VALUES(?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, fuProgram.getTradeAccount());
			ps.setBigDecimal(2, money.multiply(new BigDecimal(-1)));
			ps.setBigDecimal(3, new BigDecimal(0.00));
			Timestamp sqlDate=new Timestamp(new Date().getTime());
			ps.setTimestamp(4, sqlDate);
			ps.setString(5, "提取利润出金");
			ps.setInt(6, 0);
			ps.setString(7, "待出金");
			int a = ps.executeUpdate();
			rs = ps.getGeneratedKeys();//ResultSet 指示键值 
			if(rs.next()){
				paymentId = rs.getInt(1);//得到第一个键值 
			}
			if(a>0){
				con.commit();
			}else{
				con.rollback();
			}
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}  finally {
			JDBCUtil.release(rs, ps, con);
		}
		return paymentId;
	}
	
	/**
	 * 查询可提取的利润
	 * 
	 */
	@Override
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
	 * 方案结算的时候出金
	 * @return
	 */
	@Override
	public List<Object> programStop(FuServer fuServer,FuProgram fuProgram,BigDecimal matchMoney,BigDecimal drawMoney){
		Connection con=JDBCUtil.getConnection(fuServer);
		PreparedStatement ps=null;
		PreparedStatement ps2=null;
		ResultSet rs = null;
		List<Object> list=new ArrayList<Object>();
		try {
			con.setAutoCommit(false);
			/**
			 * 调整资金(先出实盘资金，再出交易账号用户自己的资金)
			 */
			ps=con.prepareStatement("INSERT INTO [InvestorPayment]([InvestorID],[Payment],[PaymentCredit],[PaymentDate],[Description],[Result],[ErrorMsg])VALUES(?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, fuProgram.getTradeAccount());
			ps.setBigDecimal(2, matchMoney.multiply(new BigDecimal(-1)));
			ps.setBigDecimal(3, new BigDecimal(0.00));
			Timestamp sqlDate=new Timestamp(new Date().getTime());
			ps.setTimestamp(4, sqlDate);
			ps.setString(5, "结算实盘资金出金");
			ps.setInt(6, 0);
			ps.setString(7, "待出金");
			int a = ps.executeUpdate();
			rs = ps.getGeneratedKeys();//ResultSet 指示键值 
			int id1=0;
			if(rs.next()){
				id1 = rs.getInt(1);//得到第一个键值 
			}
			
			ps2=con.prepareStatement("INSERT INTO [InvestorPayment]([InvestorID],[Payment],[PaymentCredit],[PaymentDate],[Description],[Result],[ErrorMsg])VALUES(?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			ps2.setInt(1, fuProgram.getTradeAccount());
			ps2.setBigDecimal(2, drawMoney.multiply(new BigDecimal(-1)));
			ps2.setBigDecimal(3, new BigDecimal(0.00));
			Timestamp sqlDate2=new Timestamp(new Date().getTime());
			ps2.setTimestamp(4, sqlDate2);
			ps2.setString(5, "结算交易账户余额出金");
			ps2.setInt(6, 0);
			ps2.setString(7, "待出金");
			int b = ps2.executeUpdate();
			rs = ps2.getGeneratedKeys();//ResultSet 指示键值 
			int id2=0;
			if(rs.next()){
				id2 = rs.getInt(1);//得到第一个键值 
			}
			
			if(a>0&&b>0){
				con.commit();
				list.add(id1);
				list.add(id2);
			}else{
				con.rollback();
			}
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}  finally {
			JDBCUtil.release(rs, ps, con);
			JDBCUtil.release(rs, ps2, con);
		}
		return list;
	}

	/**
	 * 后台管理员审核通过方案
	 * @param fuUser
	 * @param fuProgram
	 * @param fuServer
	 * @return
	 */
	@Override
	public List<Object> saveProgram(FuUser fuUser,FuProgram fuProgram,FuServer fuServer){
		Connection con=JDBCUtil.getConnection(fuServer);
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		PreparedStatement ps2=null;
		PreparedStatement ps3=null;
		PreparedStatement ps4=null;
		ResultSet rs = null;
		List<Object> list=new ArrayList<Object>();
		try {
			//con.setAutoCommit(false);
			/**
			 * 新增用户
			 */
			ps1=con.prepareStatement("INSERT INTO [InvestorAdd]([GroupID],[InvestorID],[InvestorName],[Password],[Description],[IsSimTrader],[Result],[ErrorMsg])VALUES(?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			//日配
			if(fuProgram.getProgramType()==1){
				if(fuProgram.getDoubleNum()==1){
					ps1.setInt(1, fuServer.getDayId1());
				}else if(fuProgram.getDoubleNum()==2){
					ps1.setInt(1, fuServer.getDayId2());
				}else if(fuProgram.getDoubleNum()==3){
					ps1.setInt(1, fuServer.getDayId3());
				}else if(fuProgram.getDoubleNum()==4){
					ps1.setInt(1, fuServer.getDayId4());
				}else if(fuProgram.getDoubleNum()==5){
					ps1.setInt(1, fuServer.getDayId5());
				}else if(fuProgram.getDoubleNum()==6){
					ps1.setInt(1, fuServer.getDayId6());
				}else if(fuProgram.getDoubleNum()==7){
					ps1.setInt(1, fuServer.getDayId7());
				}else if(fuProgram.getDoubleNum()==8){
					ps1.setInt(1, fuServer.getDayId8());
				}else if(fuProgram.getDoubleNum()==9){
					ps1.setInt(1, fuServer.getDayId9());
				}else if(fuProgram.getDoubleNum()==10){
					ps1.setInt(1, fuServer.getDayId10());
				}
			}
			//月配
			if(fuProgram.getProgramType()==2){
				if(fuProgram.getDoubleNum()==1){
					ps1.setInt(1, fuServer.getMonthId1());
				}else if(fuProgram.getDoubleNum()==2){
					ps1.setInt(1, fuServer.getMonthId2());
				}else if(fuProgram.getDoubleNum()==3){
					ps1.setInt(1, fuServer.getMonthId3());
				}else if(fuProgram.getDoubleNum()==4){
					ps1.setInt(1, fuServer.getMonthId4());
				}else if(fuProgram.getDoubleNum()==5){
					ps1.setInt(1, fuServer.getMonthId5());
				}else if(fuProgram.getDoubleNum()==6){
					ps1.setInt(1, fuServer.getMonthId6());
				}else if(fuProgram.getDoubleNum()==7){
					ps1.setInt(1, fuServer.getMonthId7());
				}else if(fuProgram.getDoubleNum()==8){
					ps1.setInt(1, fuServer.getMonthId8());
				}else if(fuProgram.getDoubleNum()==9){
					ps1.setInt(1, fuServer.getMonthId9());
				}else if(fuProgram.getDoubleNum()==10){
					ps1.setInt(1, fuServer.getMonthId10());
				}
			}
			//如果该用户号存在就一直+1,直到不存在
			int i=0;
			int userNum;
			while(true){
				i++;
				int uNum=fuServer.getNowNumber()+fuServer.getStartNumber()+i;
				ps4=con.prepareStatement("SELECT [InvestorID] FROM [InvestorAccountIntime] where [InvestorID] = ?");
				ps4.setInt(1, uNum);
				rs = ps4.executeQuery();
				if(rs.next()==false){//先从用户信息刷新表，没相同的用户号再查新增用户表
					ps=con.prepareStatement("SELECT [InvestorID] FROM [InvestorAdd] where [InvestorID] = ?");
					ps.setInt(1, uNum);
					rs = ps.executeQuery();
					if(rs.next()==false){
						userNum=fuServer.getNowNumber()+fuServer.getStartNumber()+i;
					    break;
					}
				}
			}
			ps1.setInt(2, userNum);
			ps1.setString(3, fuUser.getUserName());
			ps1.setString(4, fuProgram.getFuUser().getPhone().substring(5, 11));
			ps1.setString(5, "新增用户");
			ps1.setInt(6, fuServer.getIsSimTrader());
			ps1.setInt(7, 0);
			ps1.setString(8, "请开通");
			int a = ps1.executeUpdate();
			rs = ps1.getGeneratedKeys();//ResultSet 指示键值 
			int id1=0;
			if(rs.next()){
				id1 = rs.getInt(1);//得到第一个键值 
			}
			/**
			 * 调整资金
			 */
			ps2=con.prepareStatement("INSERT INTO [InvestorPayment]([InvestorID],[Payment],[PaymentCredit],[PaymentDate],[Description],[Result],[ErrorMsg])VALUES(?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			ps2.setInt(1, userNum);
			ps2.setBigDecimal(2, fuProgram.getMatchMoney().add(fuProgram.getSafeMoney()));
			ps2.setBigDecimal(3, new BigDecimal(0.00));
			java.util.Date newDate = new java.util.Date();
			Timestamp sqlDate=new Timestamp(newDate.getTime());
			ps2.setTimestamp(4, sqlDate);
			ps2.setString(5, "开户入金");
			ps2.setInt(6, 0);
			ps2.setString(7, "请入金");
			int b = ps2.executeUpdate();
			rs = ps2.getGeneratedKeys();//ResultSet 指示键值 
			int id2=0;
			if(rs.next()){
				id2 = rs.getInt(1);//得到第一个键值 
			}
			/**
			 * 调整组规则
			 */
			ps3=con.prepareStatement("INSERT INTO [InvestorTrigger]([InvestorID],[ForceLineWarn],[ForceLine],[TradeProduct],[Description],[Result],[ErrorMsg])VALUES(?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			ps3.setInt(1, userNum);
			ps3.setBigDecimal(2, fuProgram.getWarnLine());
			ps3.setBigDecimal(3, fuProgram.getCloseLine());
			if(fuProgram.getDoubleNum()>5){
				ps3.setInt(4, 2);//只能做股指
			}else{
				ps3.setInt(4, 3);//全都可以做
			}
			ps3.setString(5, "");
			ps3.setInt(6, 0);
			ps3.setString(7, "请设置规则");
			int c = ps3.executeUpdate();
			rs = ps3.getGeneratedKeys();//ResultSet 指示键值 
			int id3=0;
			if(rs.next()){
				id3 = rs.getInt(1);//得到第一个键值 
			}
			
			if(a>0&&b>0&&c>0){
				//con.commit();
				list.add(id1);
				list.add(id2);
				list.add(id3);
				//数据能正确写入后才执行mysql的数据库操作
				fuServer.setNowNumber(fuServer.getNowNumber()+i);
				fuServer.setServerMoney(fuServer.getServerMoney().subtract(fuProgram.getMatchMoney()));
				fuServerDao.save(fuServer);
				fuProgram.setTradeAccount(userNum);
				fuProgram.setTradePassword(fuProgram.getFuUser().getPhone().substring(5, 11));
				fuProgramDao.save(fuProgram);
			}else{
				con.rollback();
			}
		} catch (Exception e) {
			e.getMessage();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}  finally {
			JDBCUtil.release(rs, ps, con);
			JDBCUtil.release(rs, ps1, con);
			JDBCUtil.release(rs, ps2, con);
			JDBCUtil.release(rs, ps3, con);
		}
		return list;
	}
	
	/**
	 * 增配子方案审核通过
	 * @param fuUser
	 * @param fuProgram
	 * @param fuServer
	 * @return
	 */
	@Override
	public List<Object> saveSonProgram(FuUser fuUser,FuProgram fuProgram,FuProgram sonPro,FuServer fuServer){
		Connection con=JDBCUtil.getConnection(fuServer);
		PreparedStatement ps1=null;
		PreparedStatement ps2=null;
		ResultSet rs = null;
		List<Object> list=new ArrayList<Object>();
		try {
			con.setAutoCommit(false);
			/**
			 * 调整资金
			 */
			ps1=con.prepareStatement("INSERT INTO [InvestorPayment]([InvestorID],[Payment],[PaymentCredit],[PaymentDate],[Description],[Result],[ErrorMsg])VALUES(?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			ps1.setInt(1, fuProgram.getTradeAccount());
			ps1.setBigDecimal(2, sonPro.getMatchMoney().add(sonPro.getSafeMoney()));
			ps1.setBigDecimal(3, new BigDecimal(0.00));
			java.util.Date newDate = new java.util.Date();
			Timestamp sqlDate=new Timestamp(newDate.getTime());
			ps1.setTimestamp(4, sqlDate);
			ps1.setString(5, "增配入金");
			ps1.setInt(6, 0);
			ps1.setString(7, "请入金");
			int a = ps1.executeUpdate();
			rs = ps1.getGeneratedKeys();//ResultSet 指示键值 
			int id1=0;
			if(rs.next()){
				id1 = rs.getInt(1);//得到第一个键值 
			}
			
			/**
			 * 调整组规则
			 */
			ps2=con.prepareStatement("INSERT INTO [InvestorTrigger]([InvestorID],[ForceLineWarn],[ForceLine],[TradeProduct],[Description],[Result],[ErrorMsg])VALUES(?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			ps2.setInt(1, fuProgram.getTradeAccount());
			ps2.setBigDecimal(2, fuProgram.getWarnLine().add(sonPro.getWarnLine()));
			ps2.setBigDecimal(3, fuProgram.getCloseLine().add(sonPro.getCloseLine()));
			if(fuProgram.getDoubleNum()>5){
				ps2.setInt(4, 2);//只能做股指
			}else{
				ps2.setInt(4, 3);//全都可以做
			}
			ps2.setString(5, "");
			ps2.setInt(6, 0);
			ps2.setString(7, "请设置规则");
			int b = ps2.executeUpdate();
			rs = ps2.getGeneratedKeys();//ResultSet 指示键值 
			int id2=0;
			if(rs.next()){
				id2 = rs.getInt(1);//得到第一个键值 
			}
			
			if(a>0&&b>0){
				con.commit();
				list.add(id1);
				list.add(id2);
				//数据能正确写入后才执行mysql的数据库操作
				fuServer.setServerMoney(fuServer.getServerMoney().subtract(sonPro.getMatchMoney()));
				fuServerDao.save(fuServer);
				sonPro.setTradeAccount(fuProgram.getTradeAccount());
				sonPro.setTradePassword(fuProgram.getFuUser().getPhone().substring(5, 11));
				fuProgramDao.save(sonPro);
			}else{
				con.rollback();
			}
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}  finally {
			JDBCUtil.release(rs, ps1, con);
			JDBCUtil.release(rs, ps2, con);
		}
		return list;
	}
	
	/**
	 * 减配方案
	 * @param fuUser
	 * @param fuProgram
	 * @param fuServer
	 * @return
	 */
	public List<Object> saveSubProgram(FuUser fuUser,FuProgram fuProgram,FuProgram subPro,FuServer fuServer){
		Connection con=JDBCUtil.getConnection(fuServer);
		PreparedStatement ps1=null;
		PreparedStatement ps2=null;
		ResultSet rs = null;
		List<Object> list=new ArrayList<Object>();
		try {
			con.setAutoCommit(false);
			/**
			 * 调整资金
			 */
			ps1=con.prepareStatement("INSERT INTO [InvestorPayment]([InvestorID],[Payment],[PaymentCredit],[PaymentDate],[Description],[Result],[ErrorMsg])VALUES(?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			ps1.setInt(1, fuProgram.getTradeAccount());
			ps1.setBigDecimal(2, fuProgram.getMatchMoney().subtract(subPro.getMatchMoney()).multiply(new BigDecimal(-1)));
			ps1.setBigDecimal(3, new BigDecimal(0.00));
			java.util.Date newDate = new java.util.Date();
			Timestamp sqlDate=new Timestamp(newDate.getTime());
			ps1.setTimestamp(4, sqlDate);
			ps1.setString(5, "减配出金");
			ps1.setInt(6, 0);
			ps1.setString(7, "请出金");
			int a = ps1.executeUpdate();
			rs = ps1.getGeneratedKeys();//ResultSet 指示键值 
			int id1=0;
			if(rs.next()){
				id1 = rs.getInt(1);//得到第一个键值 
			}
			
			/**
			 * 调整组规则
			 */
			ps2=con.prepareStatement("INSERT INTO [InvestorTrigger]([InvestorID],[ForceLineWarn],[ForceLine],[TradeProduct],[Description],[Result],[ErrorMsg])VALUES(?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			ps2.setInt(1, fuProgram.getTradeAccount());
			ps2.setBigDecimal(2, subPro.getWarnLine());
			ps2.setBigDecimal(3, subPro.getCloseLine());
			if(fuProgram.getDoubleNum()>5){
				ps2.setInt(4, 2);//只能做股指
			}else{
				ps2.setInt(4, 3);//全都可以做
			}
			ps2.setString(5, "");
			ps2.setInt(6, 0);
			ps2.setString(7, "请设置规则");
			int b = ps2.executeUpdate();
			rs = ps2.getGeneratedKeys();//ResultSet 指示键值 
			int id2=0;
			if(rs.next()){
				id2 = rs.getInt(1);//得到第一个键值 
			}
			
			if(a>0&&b>0){
				con.commit();
				list.add(id1);
				list.add(id2);
			}else{
				con.rollback();
			}
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}  finally {
			JDBCUtil.release(rs, ps1, con);
			JDBCUtil.release(rs, ps2, con);
		}
		return list;
	}
	
	/**
	 * 前台追加保证金，余额充足直接通过审核并写入众期数据库（入金）
	 * @param fuProgram
	 * @param fuServer
	 * @return
	 */
	@Override
	public int savePayment(BigDecimal money,FuProgram fuProgram,FuServer fuServer){
		Connection con=JDBCUtil.getConnection(fuServer);
		PreparedStatement ps=null;
		ResultSet rs = null;
		int paymentId=0;
		try {
			con.setAutoCommit(false);
			/**
			 * 调整资金
			 */
			ps=con.prepareStatement("INSERT INTO [InvestorPayment]([InvestorID],[Payment],[PaymentCredit],[PaymentDate],[Description],[Result],[ErrorMsg])VALUES(?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, fuProgram.getTradeAccount());
			ps.setBigDecimal(2, money);
			ps.setBigDecimal(3,new BigDecimal(0.00));
			java.util.Date newDate = new java.util.Date();
			Timestamp sqlDate=new Timestamp(newDate.getTime());
			ps.setTimestamp(4, sqlDate);
			ps.setString(5, "追加保证金");
			ps.setInt(6, 0);
			ps.setString(7, "请入金");
			int a = ps.executeUpdate();
			rs = ps.getGeneratedKeys();//ResultSet 指示键值 
			if(rs.next()){
				paymentId = rs.getInt(1);//得到第一个键值 
			}
			if(a>0){
				con.commit();
			}else{
				con.rollback();
			}
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}  finally {
			JDBCUtil.release(rs, ps, con);
		}
		return paymentId;
	}

}
