package com.hongwei.futures.web.action.user_program;

import java.math.BigDecimal;
import java.text.ParseException;
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

import com.hongwei.futures.model.FuDrawProfits;
import com.hongwei.futures.model.FuProgram;
import com.hongwei.futures.model.FuProgramUp;
import com.hongwei.futures.model.FuRate;
import com.hongwei.futures.model.FuServer;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuDrawProfitsService;
import com.hongwei.futures.service.FuProgramService;
import com.hongwei.futures.service.FuProgramUpService;
import com.hongwei.futures.service.FuRateService;
import com.hongwei.futures.web.action.BaseAction;
import com.hongwei.futures.zhongqi.ZhongqiService;

@ParentPackage("fu_common")
public class ProgramOpAction extends BaseAction {
	@Autowired
	private FuDrawProfitsService fuDrawProfitsService;
	@Autowired
	private FuProgramService fuProgramService;
	@Autowired
	private FuProgramUpService fuProgramUpService;
	@Autowired
	private FuRateService fuRateService;
	@Autowired
	private ZhongqiService zhongqiService;

	private FuUser fuUser;
	private Long userId;
	private Long id;
	private BigDecimal money;
	private Integer cycleNum;
	private String introduce;
	private Integer upType;
	private Integer n;
	private Integer h;
	private Long profitsId;
	private String result;
	private int drawType;

	/**
	 * 追加保证金页面
	 * 
	 */
	@Action("addConfirmMoneyAjax")
	public String addConfirmMoneyAjax() {
		try {
			FuProgram pro = fuProgramService.get(id);
			FuServer fuServer = pro.getFuServer();
			BigDecimal balance = zhongqiService.findBalance(fuServer,
					pro.getTradeAccount());
			BigDecimal matchMoney = pro.getMatchMoney();
			// 如果是增配子方案
			if (pro.getAddMatchId() != null && pro.getAddMatchId() > 0) {
				FuProgram primayPro = fuProgramService.get(pro.getAddMatchId());
				matchMoney = matchMoney.add(primayPro.getMatchMoney());
			}
			// 如果是主方案，并且下面有有交易中的子方案
			List<FuProgram> list = fuProgramService.findSonProgramByProgram(pro
					.getId());
			if (list.size() > 0
					&& (list.get(0).getStatus() == 2 || list.get(0).getStatus() == 7)) {
				matchMoney = matchMoney.add(list.get(0).getMatchMoney());
			}
			BigDecimal money = matchMoney.subtract(balance);
			this.getActionContext().put("pro", pro);
			this.getActionContext().put("money", money);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 前台保存追加保证金记录
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveConfirmMoneyAjax")
	public String saveConfirmMoneyAjax() {
		try {
			if (money == null) {
				write("-2");// 请输入追加金额
				return null;
			}
			if (fuUser.getAccountBalance().compareTo(money) == -1) {
				write("-3");// 余额不足
				return null;
			}
			FuProgram pro = fuProgramService.get(id);

			// 前台追加保证金，余额充足直接通过审核并写入众期数据库
			fuProgramService.saveAddConfirmMoney(pro, money);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 续约
	 * 
	 * @return
	 */
	@Action("programContinueAjax")
	public String programContinueAjax() {
		try {
			FuProgram program = fuProgramService.get(id);
			this.getActionContext().put("program", program);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cale = Calendar.getInstance();
			cale.setTime(program.getCloseTime());
			if (program.getProgramType() == 2) {
				cale.add(Calendar.MONTH, 1);
			} else {
				cale.add(Calendar.DATE, 1);
			}
			this.getActionContext()
					.put("closeDate", sdf.format(cale.getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 根据续约选项获取方案结束时间
	 * 
	 * @return
	 */
	@Action("getProgramCloseDate")
	public String getProgramCloseDate() {
		try {
			FuProgram program = fuProgramService.get(id);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cale = Calendar.getInstance();
			cale.setTime(program.getCloseTime());
			if (cycleNum == null) {
				cycleNum = 1;
			}
			if (program.getProgramType() == 2) {
				cale.add(Calendar.MONTH, cycleNum);
			} else {
				cale.add(Calendar.DATE, cycleNum);
			}
			JSONObject obj = new JSONObject();
			obj.put("closeDate", sdf.format(cale.getTime()));
			write(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 保存续约信息记录（前台）
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveProgramContinueAjax")
	public String saveProgramContinueAjax() {
		try {
			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			FuProgram pro = fuProgramService.get(id);
			if (pro.getAddMatchId() != null) {
				FuProgram primaryPro = fuProgramService
						.get(pro.getAddMatchId());// 主方案
				Calendar cale = Calendar.getInstance();
				cale.setTime(pro.getCloseTime());
				if (pro.getProgramType() == 2) {
					cale.add(Calendar.MONTH, cycleNum);
				} else {
					cale.add(Calendar.DATE, cycleNum);
				}
				if (cale.getTime().getTime() > primaryPro.getCloseTime()
						.getTime()) {
					obj.put("code", -1);
					write(obj.toString());
					return null;
				}
			}
			if (cycleNum == null) {
				obj.put("code", -2);// 请选择续约周期
				write(obj.toString());
				return null;
			}
			fuProgramService.saveProgramContinue(pro, cycleNum, money,
					introduce);
			obj.put("code", 1);
			write(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 提取利润
	 * 
	 * @return
	 */
	@Action("drawMoneyAjax")
	public String drawMoneyAjax() {
		try {
			FuProgram pro = fuProgramService.get(id);
			BigDecimal oldMatchMoney;
			BigDecimal oldSafeMoney;
			if (pro.getAddMatchId() != null) {// 如果是增配子方案
				FuProgram primaryPro = fuProgramService
						.get(pro.getAddMatchId());
				oldMatchMoney = primaryPro.getMatchMoney();
				oldSafeMoney = primaryPro.getSafeMoney();
			} else {
				oldMatchMoney = BigDecimal.ZERO;
				oldSafeMoney = BigDecimal.ZERO;
			}
			BigDecimal benefit = zhongqiService.getBenefit(pro.getFuServer(),
					pro, oldMatchMoney, oldSafeMoney);
			this.getActionContext().put("benefit", benefit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 保存提取利润记录
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveDrawMoneyAjax")
	public String saveDrawMoneyAjax() {
		try {
			FuProgram pro;
			FuProgram program = fuProgramService.get(id);
			if (drawType == 1) {// 提取利润的是有增配的主方案
				pro = fuProgramService.get(program.getAddMatchId());
			} else {
				pro = program;
			}
			if (money == null || money.compareTo(BigDecimal.ZERO) == 0) {
				write("-2");// 请输入您要提取的金额
				return null;
			}
			// 有处理中的提盈就不能再提盈
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("state", 1);
			List<FuDrawProfits> list = fuDrawProfitsService.findDrawList(0,
					Integer.MAX_VALUE, map);// 审核中的提取利润记录
			if (list.size() > 0) {
				for (FuDrawProfits profits : list) {
					if (profits.getFuProgram().getId() == pro.getId()) {
						write("-3");
						return null;
					}
				}
			}
			// 重新查询可提盈金额，提取的金额不能超过这个金额
			BigDecimal oldMatchMoney;
			BigDecimal oldSafeMoney;
			if (program.getAddMatchId() != null) {// 如果是增配子方案
				FuProgram primaryPro = fuProgramService.get(program
						.getAddMatchId());
				oldMatchMoney = primaryPro.getMatchMoney();
				oldSafeMoney = primaryPro.getSafeMoney();
			} else {
				oldMatchMoney = BigDecimal.ZERO;
				oldSafeMoney = BigDecimal.ZERO;
			}
			BigDecimal benefit = zhongqiService
					.getBenefit(program.getFuServer(), program, oldMatchMoney,
							oldSafeMoney);
			if (money.compareTo(benefit) == 1) {
				write("-4");
				return null;
			}
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
			boolean dealing2 = time3.after(time4) && time3.before(time5);// 9:15-14:50时间段

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
			boolean dealing4 = time9.after(time10) && time3.before(time11);// 15:16-16:00时间段

			JSONObject obj = new JSONObject();
			String result;
			Calendar cal1 = Calendar.getInstance();
			int weekday = cal1.get(Calendar.DAY_OF_WEEK);
			if (weekday == 1 || weekday == 7) {// 周末
				dealing2 = false;
				dealing4 = false;
				result = "非提盈时间";
				obj.put("result", result);
				write(obj.toString());
				return null;
			}
			if (dealing2 == true || dealing4 == true) {
				fuProgramService.saveDrowMoney(pro, money);
				result = "提取利润成功";
			} else {
				result = "非提盈时间";
			}
			obj.put("id", pro.getId());
			obj.put("result", result);
			write(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 前台点击结算
	 * 
	 * @return
	 */
	@Action("overProgramAjax")
	public String overProgramAjax() {
		return SUCCESS;
	}

	/**
	 * 前台结算方案
	 * 
	 * @return
	 */
	@Action("saveOverProgramAjax")
	public String saveOverProgramAjax() {
		try {
			Long proId;
			FuProgram pro = fuProgramService.get(id);
			if (pro.getAddMatchId() != null) {
				FuProgram primaryPro = fuProgramService
						.get(pro.getAddMatchId());
				proId = primaryPro.getId();
			} else {
				proId = pro.getId();
			}

			Calendar cal1 = Calendar.getInstance();
			Date time0 = cal1.getTime();
			cal1.set(Calendar.HOUR_OF_DAY, 9);
			cal1.set(Calendar.MINUTE, 0);
			cal1.set(Calendar.SECOND, 1);
			Date time1 = cal1.getTime();
			cal1.set(Calendar.HOUR_OF_DAY, 15);
			cal1.set(Calendar.MINUTE, 0);
			cal1.set(Calendar.SECOND, 0);
			Date time2 = cal1.getTime();
			boolean dealing = time0.after(time1) && time0.before(time2);// 9:00-15:00时间段

			JSONObject obj = new JSONObject();
			String result;
			int weekday = cal1.get(Calendar.DAY_OF_WEEK);
			if (weekday == 1 || weekday == 7) {// 周末
				dealing = false;
				result = "非清算时间";
				obj.put("result", result);
				write(obj.toString());
				return null;
			}
			if (dealing == true) {// 非周末的9点到15点
				result = fuProgramService.saveOverProgramByBefore(pro);
			} else {
				result = "非清算时间";
			}
			obj.put("proId", proId);
			obj.put("result", result);
			write(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 降级
	@Action("downProgramAjax")
	public String downProgramAjax() {
		try {
			FuProgram pro = fuProgramService.get(id);
			// Double curMargin=zhongqiService.findCurrMargin(pro.getFuServer(),
			// pro.getTradeAccount());
			// if(curMargin>0){
			// write("-1");
			// return null;
			// }
			// 根据方案查找未结束的临时升级记录
			List<FuProgramUp> upList = fuProgramUpService
					.findProgramUpByPidAndNoDeal(id);
			if (upList.size() > 0) {
				write("-2");
				return null;
			}
			Long oldTotalCredit = fuProgramService
					.findCountMatchMoneyByTrade(pro.getTradeAccount());
			// Double balance=zhongqiService.findBalance(pro.getFuServer(),
			// pro.getTradeAccount(),oldTotalCredit/100d);
			// if(balance>=(pro.getMatchMoney()+pro.getSafeMoney())/100d){
			// write("-3");
			// return null;
			// }
			// if(balance-pro.getMatchMoney()/100d<1000d){
			// write("-4");
			// return null;
			// }
			// 将方案标记为申请降级
			pro.setProgramWay(1);// 降级
			pro.setStatus(4);// 申请中
			fuProgramService.save(pro);
			write("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 降级确认页面
	 * 
	 * @return
	 */
	@Action("downPageAjax")
	public String downPageAjax() {
		return SUCCESS;
	}

	/**
	 * 升级确认页面
	 * 
	 * @return
	 * @throws ParseException
	 */
	@Action("upPageAjax")
	public String upProgramAjax() {
		try {
			FuProgram program = fuProgramService.get(id);
			this.getActionContext().put("program", program);

			// 得到用户对应的费率
			FuRate param = fuRateService.getByUserID(fuUser.getId());
			this.getActionContext().put("pam", param);

			// 今天到方案结束（除去非工作日）
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");

			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(sdf.parse(sdf.format(new Date())));
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(sdf.parse(sdf.format(program.getCloseTime())));

			int n = 0;
			int h = 0;
			while (cal1.getTime().getTime() <= cal2.getTime().getTime()) {
				int weekday = cal1.get(Calendar.DAY_OF_WEEK);
				cal1.add(Calendar.DATE, 1);
				h = h + 1;
				if (weekday == 1 || weekday == 7) {
					continue;
				} else {
					n = n + 1;
				}
			}
			this.getActionContext().put("n", n);
			this.getActionContext().put("h", h);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 保存方案升级信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveUpProgramAjax")
	public String saveUpProgramAjax() {
		try {
			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			if (fuUser == null) {
				obj.put("code", -2);// 未登录
				write(obj.toString());
				return null;
			}
			// 得到用户对应的费率
			FuRate param = fuRateService.getByUserID(fuUser.getId());

			FuProgram program = fuProgramService.get(id);
			BigDecimal safeMoney = money.divide(new BigDecimal(program
					.getDoubleNum()));// 保证金
			BigDecimal managerMoney = new BigDecimal(0.00);// 管理费
			if (upType == 1) {// 临时升级
				managerMoney = money.divide(new BigDecimal(1000)).multiply(
						param.getFeeDay() == null ? new BigDecimal(0.00)
								: param.getFeeDay());// 每一万需要多少管理费
			} else {
				if (program.getProgramType() == 1) {// 日配
					managerMoney = money.divide(new BigDecimal(1000)).multiply(
							param.getFeeDay() == null ? new BigDecimal(0.00)
									: param.getFeeDay());// 每一万需要多少管理费
					managerMoney = managerMoney.multiply(new BigDecimal(n));// 不含周末
				} else {// 月配
					managerMoney = money
							.multiply(program.getMoneyPercent() == null ? new BigDecimal(
									0.00) : program.getMoneyPercent());
					managerMoney = managerMoney.divide(new BigDecimal(30))
							.multiply(new BigDecimal(h));// 含周末
				}
			}
			if (fuUser.getAccountBalance().compareTo(
					safeMoney.add(managerMoney)) == -1) {
				obj.put("code", -3);// 账户余额不足
				obj.put("message", (safeMoney.add(managerMoney)));
				write(obj.toString());
				return null;
			}

			BigDecimal closeLine = money.add(safeMoney.multiply(param
					.getFlatlinePercent() == null ? new BigDecimal(0.00)
					: param.getFlatlinePercent()));
			BigDecimal warnLine = money.add(safeMoney.multiply(param
					.getWarnlinePercent() == null ? new BigDecimal(0.00)
					: param.getWarnlinePercent()));
			BigDecimal matchMoney = money;
			BigDecimal totalMatchMoney = matchMoney.add(safeMoney);

			program.setMatchMoney(program.getMatchMoney().add(matchMoney));
			program.setSafeMoney(program.getSafeMoney().add(safeMoney));
			program.setManageMoney(program.getManageMoney().add(managerMoney));
			program.setTotalMatchMoney(program.getTotalMatchMoney().add(
					totalMatchMoney));
			program.setWarnLine(program.getWarnLine().add(warnLine));
			program.setCloseLine(program.getCloseLine().add(closeLine));

			FuProgramUp programUp = new FuProgramUp();
			programUp.setCloseLine(closeLine);
			programUp.setManageMoney(managerMoney);
			programUp.setMatchMoney(matchMoney);
			programUp.setSafeMoney(safeMoney);
			programUp.setTotalMatchMoney(totalMatchMoney);
			programUp.setType(upType);
			programUp.setWarnLine(warnLine);
			programUp.setStatus(1);
			programUp.setTime(new Date());
			programUp.setFuUser(fuUser);
			programUp.setFuProgram(program);

			programUp.setCommisionPercent(param.getCommissionPercent());
			if (upType == 1) {
				programUp.setDayNum(1);
				Calendar cal = Calendar.getInstance();
				int weekday = cal.get(Calendar.DAY_OF_WEEK);
				while (weekday == 1 || weekday == 7) {
					cal.add(Calendar.DATE, 1);
					weekday = cal.get(Calendar.DAY_OF_WEEK);
				}
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd 00:00:00");
				programUp.setCloseTime(sdf.parse(sdf.format(cal.getTime())));
			} else {
				if (program.getProgramType() == 1) {
					programUp.setDayNum(n == null ? 1 : n);// 不含周末
				} else {
					programUp.setDayNum(h == null ? 1 : h);// 含周末
				}
				programUp.setCloseTime(program.getCloseTime());
			}
			// 保存方案/升级信息
			fuProgramService.saveProgramUpInfo(program, programUp);
			// zhongqiService.saveAccount(program.getFuServer(),
			// program.getTradeAccount(), totalMatchMoney/100d);
			obj.put("code", 1);
			write(obj.toString());
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Integer getCycleNum() {
		return cycleNum;
	}

	public void setCycleNum(Integer cycleNum) {
		this.cycleNum = cycleNum;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public Integer getUpType() {
		return upType;
	}

	public void setUpType(Integer upType) {
		this.upType = upType;
	}

	public Integer getN() {
		return n;
	}

	public void setN(Integer n) {
		this.n = n;
	}

	public Integer getH() {
		return h;
	}

	public void setH(Integer h) {
		this.h = h;
	}

	public Long getProfitsId() {
		return profitsId;
	}

	public void setProfitsId(Long profitsId) {
		this.profitsId = profitsId;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getDrawType() {
		return drawType;
	}

	public void setDrawType(int drawType) {
		this.drawType = drawType;
	}
}
