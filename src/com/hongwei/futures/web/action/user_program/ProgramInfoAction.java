package com.hongwei.futures.web.action.user_program;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuGame;
import com.hongwei.futures.model.FuParameter;
import com.hongwei.futures.model.FuProgram;
import com.hongwei.futures.model.FuRate;
import com.hongwei.futures.model.FuRecharge;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuGameService;
import com.hongwei.futures.service.FuParameterService;
import com.hongwei.futures.service.FuProgramService;
import com.hongwei.futures.service.FuRateService;
import com.hongwei.futures.service.FuRechargeService;
import com.hongwei.futures.util.Property;
import com.hongwei.futures.util.StringUtil;
import com.hongwei.futures.web.action.BaseAction;
import com.hongwei.futures.zhongqi.ZhongqiService;

@ParentPackage("fu_common")
public class ProgramInfoAction extends BaseAction {
	@Autowired
	private FuProgramService fuProgramService;
	@Autowired
	private FuParameterService fuParameterService;
	@Autowired
	private FuRateService fuRateService;
	@Autowired
	private FuRechargeService fuRechargeService;
	@Autowired
	private ZhongqiService zhongqiService;
	@Autowired
	private FuGameService fuGameService;

	private FuUser fuUser;
	private Long userId;
	private Integer totalCount;
	private Integer flag;
	private Integer programType;// 1短线，2趋势
	private BigDecimal matchMoney;// 实盘资金
	private int cycleNum;// 预存周期
	private Integer tradeTimeType;// 1今天，2下个交易日
	private String money;
	private int num;// 资金赔率
	private String bank;
	private Integer type;
	private String account;
	private int cycleNum1; // 日配周期
	private int cycleNum2; // 月配周期
	private String Pay;
	private Long id;
	private Long ppId;
	private BigDecimal integral;
	private Long addMatchId;// 增配主方案id
	private Long subMatchId;// 减配主方案Id

	/**
	 * 关闭待审核的方案
	 * 
	 * @return
	 */
	@Action("programClose")
	public String programClose() {
		try {
			FuProgram program = fuProgramService.get(id);
			program.setStatus(-1);// 已关闭
			fuProgramService.save(program);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除待审核的方案
	 * 
	 * @return
	 */
	@Action("programDelete")
	public String programDelete() {
		try {
			FuProgram program = fuProgramService.get(id);
			if (program.getStatus() == -1 || program.getStatus() == 0
					|| program.getStatus() == 3 || program.getStatus() == 5) {
				program.setStatus(-2);// 已删除
				fuProgramService.save(program);
			} else {
				write("-1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 方案确认页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("addProgramAjax")
	public String addProgramAjax() {
		try {
			if (fuUser == null) {
				write("-5");
				return null;
			}
			if (matchMoney == null) {
				write("-2");// 请选择实盘资金
				return null;
			}
			if (tradeTimeType == null) {
				write("-3");// 请选择资金使用时间
				return null;
			}
			// 得到用户对应的费率
			FuRate param = fuRateService.getByUserID(fuUser.getId());
			if (param == null) {
				write("-4");
				return null;// 系统参数还没有设置，请联系客服
			}
			BigDecimal safeMoney = matchMoney.multiply(new BigDecimal(10000))
					.divide(new BigDecimal(num), 0, BigDecimal.ROUND_HALF_UP);// 风险保证金
			BigDecimal managerMoney = new BigDecimal(0.00); // 管理费
			BigDecimal totalManagerMoney = new BigDecimal(0.00);
			if (programType == 1) {
				managerMoney = matchMoney.multiply(param.getFeeDay());
				totalManagerMoney = managerMoney.multiply(new BigDecimal(
						cycleNum1));
			} else {
				if (cycleNum2 > 3) {
					managerMoney = matchMoney.multiply(new BigDecimal(10000))
							.multiply(param.getInterestPercent());
				} else {
					managerMoney = matchMoney.multiply(new BigDecimal(10000))
							.multiply(param.getFeePercent());
				}
				totalManagerMoney = managerMoney.multiply(new BigDecimal(
						cycleNum2));
			}
			BigDecimal closeLine = matchMoney.multiply(new BigDecimal(10000))
					.add(safeMoney.multiply(param.getFlatlinePercent()));
			BigDecimal warnLine = matchMoney.multiply(new BigDecimal(10000))
					.add(safeMoney.multiply(param.getWarnlinePercent()));
			BigDecimal totalMoney = safeMoney.add(matchMoney
					.multiply(new BigDecimal(10000)));

			this.getActionContext().put("managerMoney", managerMoney);
			this.getActionContext().put("safeMoney", safeMoney);
			this.getActionContext().put("totalManagerMoney", totalManagerMoney);
			this.getActionContext().put("closeLine", closeLine);
			this.getActionContext().put("warnLine", warnLine);
			this.getActionContext().put("totalMoney", totalMoney);
			this.getActionContext().put("integral", integral);
			this.getActionContext().put("jsptype", "addProgramAjax");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 前台保存方案（正常方案和增配方案）
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveShortProgramAjax")
	public String saveShortProgramAjax() {
		try {
			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			if (fuUser == null) {
				obj.put("code", -5);
				write(obj.toString());
				return null;
			}
			// 得到用户对应的费率
			FuRate param = fuRateService.getByUserID(fuUser.getId());
			// 系统参数还没有设置，请联系客服
			if (param == null) {
				obj.put("code", -1);
				write(obj.toString());
				return null;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", userId);
			map.put("programType", programType);// 短线 1,趋势之王 2
			int count = fuProgramService.countProgramByUser2(map);

			if (programType == 1 && param.getShortNum() != null
					&& count > param.getShortNum()) {// 超出了交易个数
				obj.put("code", -2);
				write(obj.toString());
				return null;
			}
			if (programType == 2 && param.getLongNum() != null
					&& count > param.getLongNum()) {// 超出了交易个数
				obj.put("code", -2);
				write(obj.toString());
				return null;
			}
			if (fuUser.getIsCheckCard() == null || fuUser.getIsCheckCard() != 2) {
				obj.put("code", -3);// 请先进行实名认证后，再进行操作
				write(obj.toString());
				return null;
			}
			if (fuUser.getIsAcrossCabin() == 1) {
				obj.put("code", -6);// 已穿仓
				write(obj.toString());
				return null;
			}

			// 计算管理费和保证金
			BigDecimal safeMoney = matchMoney.multiply(new BigDecimal(10000))
					.divide(new BigDecimal(num), 0, BigDecimal.ROUND_HALF_UP);// 风险保证金
			BigDecimal managerMoney = new BigDecimal(0.00); // 管理费
			if (programType == 1) {
				managerMoney = matchMoney
						.multiply(param.getFeeDay() == null ? new BigDecimal(
								0.00) : param.getFeeDay());
			} else {
				if (cycleNum2 > 3) {
					managerMoney = matchMoney
							.multiply(new BigDecimal(10000))
							.multiply(
									param.getInterestPercent() == null ? new BigDecimal(
											0.00) : param.getInterestPercent());
				} else {
					managerMoney = matchMoney
							.multiply(new BigDecimal(10000))
							.multiply(
									param.getFeePercent() == null ? new BigDecimal(
											0.00) : param.getFeePercent());
				}
			}

			/**
			 * 日配余额不足
			 */
			if (programType == 1
					&& fuUser.getAccountBalance().compareTo(
							safeMoney.add(managerMoney).subtract(integral)) == -1) {
				obj.put("code", -4);// 账户余额不足
				write(obj.toString());
				return null;
			}
			/**
			 * 月配余额不足
			 */
			if (programType == 2
					&& fuUser.getAccountBalance().compareTo(
							safeMoney.add(managerMoney).subtract(integral)) == -1) {
				obj.put("code", -4);// 账户余额不足
				write(obj.toString());
				return null;
			}

			/**
			 * 创建新的方案
			 */
			FuProgram program = new FuProgram();
			if (addMatchId != null) {// 如果增配主方案id存在
				program.setAddMatchId(addMatchId);
			}
			if (num <= 5) {
				program.setOvernightGoodRate(new BigDecimal(Property
						.getProperty("OverNight_Good_Rate")));
				program.setOvernightStockIndexRate(new BigDecimal(Property
						.getProperty("OverNight_Stock_Index_Rate")));
			} else {
				program.setOvernightGoodRate(new BigDecimal(Property
						.getProperty("OverNight_Not")));
				program.setOvernightStockIndexRate(new BigDecimal(Property
						.getProperty("OverNight_Not")));
			}
			program.setGoodsFee("交易所的1.2倍");
			program.setStockIndexFee("交易所的1.2倍");
			program.setSafeMoneyRate("商品1.5倍，股指1.3倍");
			program.setStatus(0);// 待审核
			program.setCreateTime(new Date());
			program.setCloseLine(matchMoney
					.multiply(new BigDecimal(10000))
					.add(safeMoney.multiply(param.getFlatlinePercent() == null ? new BigDecimal(
							0.00) : param.getFlatlinePercent())));// 亏损平仓线
			program.setWarnLine(matchMoney
					.multiply(new BigDecimal(10000))
					.add(safeMoney.multiply(param.getWarnlinePercent() == null ? new BigDecimal(
							0.00) : param.getWarnlinePercent())));// 亏损警戒线
			program.setFuUser(fuUser);
			program.setMatchMoney(matchMoney.multiply(new BigDecimal(10000)));// //------实盘资金--------不加风险保证金
			program.setSafeMoney(safeMoney);
			program.setTotalMatchMoney(matchMoney.multiply(
					new BigDecimal(10000)).add(safeMoney));// 总操盘
			if (programType == 1) {// 日配管理费
				program.setCycleNum(cycleNum1);
				program.setManageMoney(managerMoney);
				program.setMoneyPercent(param.getFeeDay() == null ? new BigDecimal(
						0.00) : param.getFeeDay());
			} else {// 月配管理费
				program.setCycleNum(cycleNum2);
				program.setManageMoney(managerMoney);// 月配是每个月来扣除管理费
				program.setMoneyPercent(param.getFeePercent() == null ? new BigDecimal(
						0.00) : param.getFeePercent());
			}

			program.setProgramType(programType);
			program.setProgramWay(0);// 正常交易
			program.setDoubleNum(num);
			program.setCommisionPercent(param.getCommissionPercent() == null ? new BigDecimal(
					0.00) : param.getCommissionPercent());

			// 下个交易日，应该是审核过后的第一个交易日
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			Calendar cal = Calendar.getInstance();
			if (tradeTimeType == 1) {// 今天
				program.setTradeTime(sdf.parse(sdf.format(cal.getTime())));
			} else {// 下个交易日
				int weekday = cal.get(Calendar.DAY_OF_WEEK);
				if (weekday == 1) {// 今天星期天
					cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 1);
				} else if (weekday == 7) {// 今天星期六
					cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 2);
				} else if (weekday == 6) {// 今天星期五
					cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 3);
				} else {// 其他
					cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 1);
				}
				program.setTradeTime(sdf.parse(sdf.format(cal.getTime())));
			}
			if (programType == 2) {// 趋势之王的结束日期可以确定
				if (tradeTimeType == 1) {
					Calendar calen = Calendar.getInstance();
					calen.add(Calendar.MONTH, cycleNum2);
					calen.add(Calendar.DAY_OF_MONTH, -1);
					program.setCloseTime(sdf.parse(sdf.format(calen.getTime())));
				}
				if (tradeTimeType == 2) {
					Calendar calen = Calendar.getInstance();
					// 先+1天，如果还是周末就再加，直到不是周末
					calen.add(Calendar.DATE, 1);
					int weekday = calen.get(Calendar.DAY_OF_WEEK);
					while (weekday == 1 || weekday == 7) {
						calen.add(Calendar.DATE, 1);
						weekday = calen.get(Calendar.DAY_OF_WEEK);
					}

					calen.add(Calendar.MONTH, cycleNum2);
					program.setCloseTime(sdf.parse(sdf.format(calen.getTime())));
				}
			} else {// 短线高手
				if (tradeTimeType == 1) {
					for (int i = 1; i < cycleNum1; i++) {
						cal.add(Calendar.DATE, 1);
						int weekday = cal.get(Calendar.DAY_OF_WEEK);
						while (weekday == 1 || weekday == 7) {
							cal.add(Calendar.DATE, 1);
							weekday = cal.get(Calendar.DAY_OF_WEEK);
						}
					}
					program.setCloseTime(sdf.parse(sdf.format(cal.getTime())));
				}
				if (tradeTimeType == 2) {
					for (int i = 1; i < cycleNum1 + 1; i++) {
						cal.add(Calendar.DATE, 1);
						int weekday = cal.get(Calendar.DAY_OF_WEEK);
						while (weekday == 1 || weekday == 7) {
							cal.add(Calendar.DATE, 1);
							weekday = cal.get(Calendar.DAY_OF_WEEK);
						}
					}
					program.setCloseTime(sdf.parse(sdf.format(cal.getTime())));
				}
			}
			program.setAgreeTime(new Date());
			program.setAgreeIp(this.getHttpServletRequest().getRemoteAddr());
			program.setExpenseScore(integral);
			fuUser.setIntegral(fuUser.getIntegral().subtract(integral));// 先减去积分，审核被拒绝就返还积分
			fuProgramService.saveInfo(program, fuUser, ppId);

			obj.put("code", 1);
			obj.put("message", program.getId());
			write(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 前台保存减配方案（减配方案）
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveSubProgramAjax")
	public String saveSubProgramAjax() {
		try {
			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			if (fuUser == null) {
				obj.put("code", -5);
				write(obj.toString());
				return null;
			}
			// 得到用户对应的费率
			FuRate param = fuRateService.getByUserID(fuUser.getId());
			// 系统参数还没有设置，请联系客服
			if (param == null) {
				obj.put("code", -1);
				write(obj.toString());
				return null;
			}
			if (fuUser.getIsAcrossCabin() == 1) {
				obj.put("code", -2);// 已穿仓
				write(obj.toString());
				return null;
			}
			if (fuUser.getIsCheckCard() == null || fuUser.getIsCheckCard() != 2) {
				obj.put("code", -3);// 请先进行实名认证后，再进行操作
				write(obj.toString());
				return null;
			}

			// 计算管理费和保证金
			BigDecimal safeMoney = matchMoney.multiply(new BigDecimal(10000))
					.divide(new BigDecimal(num), 0, BigDecimal.ROUND_HALF_UP);// 风险保证金
			BigDecimal managerMoney = new BigDecimal(0.00); // 管理费
			if (programType == 1) {
				managerMoney = matchMoney
						.multiply(param.getFeeDay() == null ? new BigDecimal(
								0.00) : param.getFeeDay());
			} else {
				if (cycleNum2 > 3) {
					managerMoney = matchMoney
							.multiply(new BigDecimal(10000))
							.multiply(
									param.getInterestPercent() == null ? new BigDecimal(
											0.00) : param.getInterestPercent());
				} else {
					managerMoney = matchMoney
							.multiply(new BigDecimal(10000))
							.multiply(
									param.getFeePercent() == null ? new BigDecimal(
											0.00) : param.getFeePercent());
				}
			}

			FuProgram primaryPro = fuProgramService.get(subMatchId);
			// 保存减配方案
			FuProgram program = fuProgramService.saveSubProgramAjax(param,
					primaryPro, safeMoney, managerMoney, matchMoney, num);
			obj.put("code", 1);
			obj.put("programId", program.getId());
			write(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 前台参加期货大赛报名
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveProgramGame")
	public String saveProgramGame() {
		try {
			JSONObject obj = new JSONObject();
			if (fuUser == null) {
				obj.put("code", -1);
				write(obj.toString());
				return null;
			}
			// 得到用户对应的费率
			FuRate param = fuRateService.getByUserID(fuUser.getId());
			// 系统参数还没有设置，请联系客服
			if (param == null) {
				obj.put("code", -2);
				write(obj.toString());
				return null;
			}
			// 周末不能参加大赛
			/*
			 * Calendar calen = Calendar.getInstance(); int
			 * weekday=calen.get(Calendar.DAY_OF_WEEK);
			 * if(weekday==1||weekday==7){//周末 obj.put("code", -4);
			 * write(obj.toString()); return null; }
			 */
			/*
			 * List<FuGame> list=fuGameService.findGameByUser(fuUser);
			 * if(list.size()>0){ obj.put("code", -5); obj.put("tradeAccount",
			 * list.get(0).getTradeAccount().toString()); write(obj.toString());
			 * return null; }
			 */
			// 根据用户以及比赛序列号查询用户是否参加过该赛事
			List<FuGame> list = fuGameService.findGameByUser(fuUser, "771");
			if (list != null && list.size() > 0) {
				obj.put("code", -5);
				write(obj.toString());
				return null;
			}
			/*
			 * SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 * Date nowDate = new Date(); Date minDate =
			 * sdf.parse("2015-09-15 00:00:00"); Date maxDate =
			 * sdf.parse("2015-10-14 15:15:59");
			 * 
			 * if(nowDate.getTime()<minDate.getTime() ||
			 * nowDate.getTime()>maxDate.getTime()){ obj.put("code", -6);
			 * write(obj.toString()); return null; }
			 */
			Calendar cal = Calendar.getInstance();
			Date time1 = cal.getTime();
			cal.set(Calendar.HOUR_OF_DAY, 16);
			cal.set(Calendar.MINUTE, 20);
			cal.set(Calendar.SECOND, 1);
			Date time2 = cal.getTime();
			cal.set(Calendar.HOUR_OF_DAY, 16);
			cal.set(Calendar.MINUTE, 50);
			cal.set(Calendar.SECOND, 0);
			Date time3 = cal.getTime();
			boolean dealing = time1.after(time2) && time1.before(time3);// 16:20-16:50时间段
			if (dealing == true) {
				obj.put("code", -7);
				write(obj.toString());
				return null;
			}
			String competitionNum = "2";
			// 保存比赛
			fuProgramService.saveGame(param, fuUser,
					Integer.parseInt(competitionNum));
			obj.put("code", 1);
			write(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 余额不足跳转充值页面
	 */
	@Action("pay")
	public String pay() {
		try {
			FuProgram program = fuProgramService.get(id);
			this.getActionContext().put("program", program);
			this.getActionContext().put("money", money);
			this.getActionContext().put("jsptype", "pay");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 保存充值记录（前台）
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveRechargeAjax2")
	public String saveRechargeAjax2() {
		try {
			// 暂时不需要这个逻辑
			/*
			 * if(type!=3&&StringUtil.isBlank(bank)){ write("-2"); return null;
			 * }
			 */
			if (type == 4 && StringUtil.isBlank(bank)) {
				write("-6");
				return null;
			}
			if ((type == 3 || type == 4) && StringUtil.isBlank(account)) {// 支付宝
																			// or银行转账
				write("-3");
				return null;
			}
			if (StringUtil.isBlank(money)) {
				write("-4");
				return null;
			}
			String drawM = "";
			if (money.contains(",")) {
				money = money.replace(",", "");
			}
			if (money.contains("，")) {
				money = money.replace("，", "");
			}
			drawM = money;
			if (money.contains(".")) {
				money = money.replace(".", "");
			}
			if (!StringUtil.isNumeric(money)) {
				write("-5");
				return null;
			}
			FuRecharge recharge = new FuRecharge();
			recharge.setFuUser(fuUser);
			recharge.setOrderNum(String.valueOf((int) ((Math.random() * 1000000 + 100000))));// 随机产生订单号
			recharge.setType(type);
			if (type == 1 || type == 2) {
				recharge.setPayStatus(0);// 在线充值 未付款
				recharge.setRechargeBank("银联支付");
			} else {
				recharge.setRechargeBank(bank);
			}
			recharge.setRechargeAccount(account);
			recharge.setRechargeMoney(new BigDecimal(drawM));
			recharge.setRechargeTime(new Date());
			recharge.setRechargeStatus(0);// 待审核
			recharge.setState(1);
			fuRechargeService.save(recharge);
			write(recharge.getId().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 方案创建成功
	 * 
	 * @return
	 */
	@Action("programSuccessAjax")
	public String programSuccessAjax() {
		try {
			FuProgram program = fuProgramService.get(id);
			this.getActionContext().put("program", program);
			this.getActionContext().put("Pay", Pay);
			this.getActionContext().put("jsptype", "programSuccessAjax");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 期货配资页面
	 * 
	 * @return
	 */
	@Action("addPeizi")
	public String addPeizi() {
		try {
			if (null == fuUser) {// 没有登陆从数据库读出默认费率
				FuParameter param = fuParameterService.findParameter();
				this.getActionContext().put("pam", param);
			} else { // 登陆后读用户对应费率
				FuRate rate = fuRateService.getByUserID(fuUser.getId());
				this.getActionContext().put("pam", rate);
			}

			Calendar cal = Calendar.getInstance();
			int weekday = cal.get(Calendar.DAY_OF_WEEK);
			this.getActionContext().put("weekday", weekday);
			this.getActionContext().put("matchMoney", matchMoney);
			this.getActionContext().put("num", num);
			this.getActionContext().put("programType", programType);
			this.getActionContext().put("cycleNum2", cycleNum2);
			this.getActionContext().put("tradeTimeType", tradeTimeType);
			this.getActionContext().put("integral", integral);

			this.getActionContext().put("jsptype", "addPeizi");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 增配改变方案周期
	 * 
	 * @return
	 */
	@Action("changeCycleNumAjax")
	public String changeCycleNumAjax() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			FuProgram pro = fuProgramService.get(addMatchId);
			Calendar calen = Calendar.getInstance();
			calen.setTime(sdf.parse(sdf.format(new Date())));
			if (cycleNum1 > 0) {
				calen.add(Calendar.DATE, cycleNum1);
			}
			if (cycleNum2 > 0) {
				calen.add(Calendar.MONTH, cycleNum2);
			}
			if (tradeTimeType == 2) {// 如果是下个交易日还要加一天
				calen.add(Calendar.DATE, 1);
			}
			if (calen.getTime().getTime() > pro.getCloseTime().getTime()) {
				write("-1");
			} else {
				write("1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 增配页面
	 * 
	 * @return
	 */
	@Action("appendPeizi")
	public String appendPeizi() {
		try {
			if (null == fuUser) {// 没有登陆从数据库读出默认费率
				FuParameter param = fuParameterService.findParameter();
				this.getActionContext().put("pam", param);
			} else { // 登陆后读用户对应费率
				FuRate rate = fuRateService.getByUserID(fuUser.getId());
				this.getActionContext().put("pam", rate);
			}

			Calendar cal = Calendar.getInstance();
			int weekday = cal.get(Calendar.DAY_OF_WEEK);
			this.getActionContext().put("weekday", weekday);

			// 升级方案
			if (ppId != null) {
				FuProgram ppProgram = fuProgramService.get(ppId);
				if (ppProgram.getProgramWay() == 2
						|| ppProgram.getStatus() >= 3
						|| !ppProgram.getFuUser().getId().toString()
								.equals(userId.toString())) {
					this.ppId = null;
				} else {
					this.getActionContext().put("ppProgram", ppProgram);
				}
			}

			FuProgram pro = fuProgramService.get(addMatchId);
			this.getActionContext().put("doubleNum", pro.getDoubleNum());
			this.getActionContext().put("matchMoney", matchMoney);
			this.getActionContext().put("num", num);
			this.getActionContext().put("programType", programType);
			this.getActionContext().put("cycleNum1", cycleNum1);
			this.getActionContext().put("cycleNum2", cycleNum2);
			this.getActionContext().put("tradeTimeType", tradeTimeType);
			this.getActionContext().put("integral", integral);

			this.getActionContext().put("jsptype", "addPeizi");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 前台减配操作时间限制
	 * 
	 * @return
	 */
	@Action("subPeiziAjax")
	public String subPeiziAjax() {
		try {
			JSONObject obj = new JSONObject();
			String result;
			FuProgram pro = fuProgramService.get(id);
			// 占用保证金（大于0有持仓，否则无持仓）
			BigDecimal currMargin = zhongqiService.findCurrMargin(
					pro.getFuServer(), pro.getTradeAccount());
			if (currMargin.compareTo(BigDecimal.ZERO) == 1) {// 有持仓
				Calendar cal1 = Calendar.getInstance();
				Date time0 = cal1.getTime();
				cal1.set(Calendar.HOUR_OF_DAY, 8);
				cal1.set(Calendar.MINUTE, 30);
				cal1.set(Calendar.SECOND, 1);
				Date time1 = cal1.getTime();
				cal1.set(Calendar.HOUR_OF_DAY, 9);
				cal1.set(Calendar.MINUTE, 0);
				cal1.set(Calendar.SECOND, 0);
				Date time2 = cal1.getTime();
				boolean dealing1 = time0.after(time1) && time0.before(time2);// 8:30-9:00时间段

				Calendar cal2 = Calendar.getInstance();
				Date time3 = cal2.getTime();
				cal2.set(Calendar.HOUR_OF_DAY, 11);
				cal2.set(Calendar.MINUTE, 30);
				cal2.set(Calendar.SECOND, 1);
				Date time4 = cal2.getTime();
				cal2.set(Calendar.HOUR_OF_DAY, 13);
				cal2.set(Calendar.MINUTE, 00);
				cal2.set(Calendar.SECOND, 0);
				Date time5 = cal2.getTime();
				boolean dealing2 = time3.after(time4) && time3.before(time5);// 11:30-13:00时间段

				Calendar cal3 = Calendar.getInstance();
				Date time6 = cal3.getTime();
				cal3.set(Calendar.HOUR_OF_DAY, 15);
				cal3.set(Calendar.MINUTE, 15);
				cal3.set(Calendar.SECOND, 1);
				Date time7 = cal3.getTime();
				cal3.set(Calendar.HOUR_OF_DAY, 16);
				cal3.set(Calendar.MINUTE, 00);
				cal3.set(Calendar.SECOND, 0);
				Date time8 = cal3.getTime();
				boolean dealing3 = time6.after(time7) && time3.before(time8);// 15:15-16:00时间段

				Calendar cal4 = Calendar.getInstance();
				int weekday = cal4.get(Calendar.DAY_OF_WEEK);
				if (weekday == 1 || weekday == 7) {// 周末
					dealing1 = false;
					dealing2 = false;
					dealing3 = false;
					result = "对不起，非工作日无法减配！";
					obj.put("result", result);
					write(obj.toString());
					return null;
				}
				if (dealing1 == true || dealing2 == true || dealing3 == true) {
					result = "1";
				} else {
					result = "对不起，当前时间无法减配，减配时间为8:30-9:00,11:30-13:30,15:15-16:00！";
				}
			} else {// 无持仓
				result = "1";
			}
			obj.put("result", result);
			write(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 减配页面
	 * 
	 * @return
	 */
	@Action("minusPeizi")
	public String minusPeizi() {
		try {
			if (null == fuUser) {// 没有登陆从数据库读出默认费率
				FuParameter param = fuParameterService.findParameter();
				this.getActionContext().put("pam", param);
			} else { // 登陆后读用户对应费率
				FuRate rate = fuRateService.getByUserID(fuUser.getId());
				this.getActionContext().put("pam", rate);
			}

			Calendar cal = Calendar.getInstance();
			int weekday = cal.get(Calendar.DAY_OF_WEEK);
			this.getActionContext().put("weekday", weekday);

			FuProgram pro = fuProgramService.get(subMatchId);
			// 动态权益
			BigDecimal balance = zhongqiService.findBalance(pro.getFuServer(),
					pro.getTradeAccount());
			BigDecimal ownMoney = balance.subtract(pro.getMatchMoney());

			this.getActionContext().put("ownMoney", ownMoney);
			this.getActionContext().put("doubleNum", pro.getDoubleNum());
			this.getActionContext().put(
					"match",
					pro.getMatchMoney().divide(new BigDecimal(10000))
							.intValue());
			this.getActionContext().put("programType", pro.getProgramType());
			if (pro.getTradeTime().getTime() > pro.getCreateTime().getTime()) {// 下个交易日
				this.getActionContext().put("tradeTimeType", 2);
			} else {// 今天
				this.getActionContext().put("tradeTimeType", 1);
			}
			int cycle = pro.getCycleNum();
			if (pro.getProgramType() == 1) {
				this.getActionContext().put("cycleDay", cycle);
				this.getActionContext().put("cycleMonth", 1);
			}
			if (pro.getProgramType() == 2) {
				this.getActionContext().put("cycleDay", 1);
				this.getActionContext().put("cycleMonth", cycle);
			}
			this.getActionContext().put("integral", pro.getExpenseScore());
			this.getActionContext().put("matchMoney", matchMoney);
			this.getActionContext().put("num", num);
			this.getActionContext().put("jsptype", "addPeizi");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 减配改变方案倍率
	 * 
	 * @return
	 */
	@Action("changeDoubleNumAjax")
	public String changeDoubleNumAjax() {
		try {
			FuProgram pro = fuProgramService.get(subMatchId);
			// 动态权益
			BigDecimal balance = zhongqiService.findBalance(pro.getFuServer(),
					pro.getTradeAccount());
			BigDecimal ownMoney = balance.subtract(pro.getMatchMoney());
			BigDecimal safeMoney;
			if (num > 0) {
				safeMoney = matchMoney.multiply(new BigDecimal(10000)).divide(
						new BigDecimal(num), 0, BigDecimal.ROUND_HALF_UP);
				if (safeMoney.compareTo(ownMoney) == 1) {// 减配的金额大于交易账户的私有资金
					write("-1");
				} else {
					write("1");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据倍率查询减配的实盘资金最大值
	 * 
	 * @return
	 */
	@Action("findMaxMatch")
	public String findMaxMatch() {
		try {
			FuProgram pro = fuProgramService.get(subMatchId);
			// 动态权益
			BigDecimal balance = zhongqiService.findBalance(pro.getFuServer(),
					pro.getTradeAccount());
			BigDecimal ownMoney = balance.subtract(pro.getMatchMoney());
			int matchMoney = 0;
			if (num > 0) {
				matchMoney = ownMoney
						.multiply(new BigDecimal(num))
						.divide(new BigDecimal(10000), 0,
								BigDecimal.ROUND_HALF_DOWN).intValue();// 向下取整
			}
			if (matchMoney <= 0) {
				matchMoney = 1;
			}
			write(String.valueOf(matchMoney));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public FuUser getFuUser() {
		return fuUser;
	}

	public void setFuUser(FuUser fuUser) {
		this.fuUser = fuUser;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public FuProgramService getFuProgramService() {
		return fuProgramService;
	}

	public void setFuProgramService(FuProgramService fuProgramService) {
		this.fuProgramService = fuProgramService;
	}

	public Integer getProgramType() {
		return programType;
	}

	public void setProgramType(Integer programType) {
		this.programType = programType;
	}

	public BigDecimal getMatchMoney() {
		return matchMoney;
	}

	public void setMatchMoney(BigDecimal matchMoney) {
		this.matchMoney = matchMoney;
	}

	public int getCycleNum() {
		return cycleNum;
	}

	public void setCycleNum(int cycleNum) {
		this.cycleNum = cycleNum;
	}

	public Integer getTradeTimeType() {
		return tradeTimeType;
	}

	public void setTradeTimeType(Integer tradeTimeType) {
		this.tradeTimeType = tradeTimeType;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getCycleNum1() {
		return cycleNum1;
	}

	public void setCycleNum1(int cycleNum1) {
		this.cycleNum1 = cycleNum1;
	}

	public int getCycleNum2() {
		return cycleNum2;
	}

	public void setCycleNum2(int cycleNum2) {
		this.cycleNum2 = cycleNum2;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPpId() {
		return ppId;
	}

	public void setPpId(Long ppId) {
		this.ppId = ppId;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPay() {
		return Pay;
	}

	public void setPay(String pay) {
		Pay = pay;
	}

	public BigDecimal getIntegral() {
		return integral;
	}

	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}

	public Long getAddMatchId() {
		return addMatchId;
	}

	public void setAddMatchId(Long addMatchId) {
		this.addMatchId = addMatchId;
	}

	public Long getSubMatchId() {
		return subMatchId;
	}

	public void setSubMatchId(Long subMatchId) {
		this.subMatchId = subMatchId;
	}

}
