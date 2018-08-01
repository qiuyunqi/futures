package com.hongwei.futures.web.action.admin_op_program;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuBadCredit;
import com.hongwei.futures.model.FuContinueContract;
import com.hongwei.futures.model.FuManageFee;
import com.hongwei.futures.model.FuMoneyDetail;
import com.hongwei.futures.model.FuProgram;
import com.hongwei.futures.model.FuServer;
import com.hongwei.futures.model.FuSmsLog;
import com.hongwei.futures.service.FuBadCreditService;
import com.hongwei.futures.service.FuContinueContractService;
import com.hongwei.futures.service.FuManageFeeService;
import com.hongwei.futures.service.FuMoneyDetailService;
import com.hongwei.futures.service.FuProgramService;
import com.hongwei.futures.service.FuServerService;
import com.hongwei.futures.service.FuSmsLogService;
import com.hongwei.futures.util.DateUtil;
import com.hongwei.futures.util.RMB;
import com.hongwei.futures.util.StringUtil;
import com.hongwei.futures.web.action.BaseAction;
import com.hongwei.futures.zhongqi.ZhongqiService;

@ParentPackage("admin")
public class AdminOpProgramAction extends BaseAction {
	@Autowired
	private FuProgramService fuProgramService;
	@Autowired
	private FuBadCreditService fuBadCreditService;
	@Autowired
	private ZhongqiService zhongqiService;
	@Autowired
	private FuServerService fuServerService;
	@Autowired
	private FuSmsLogService fuSmsLogService;
	@Autowired
	private FuManageFeeService fuManageFeeService;
	@Autowired
	private FuContinueContractService fuContinueContractService;
	@Autowired
	private FuMoneyDetailService fuMoneyDetailService;

	private FuAdmin admin;
	private Long adminId;
	private Long id;
	private int flag;// 2审核通过，3拒绝开户
	private String comment;
	private String badDetail;
	private BigDecimal money;
	private Integer type;
	private Date time;
	private Long serverId;
	private Integer tradeAccount;
	private String tradePassword;
	private Date nextManageMoneyTime;
	private Long addMatchId;// 主方案ID
	private String msg; // 告之开户内容
	private String goodsFee; // 商品期货手续费
	private String stockIndexFee; // 股指期货手续费
	private String returnCommission; // 返佣
	private String mediator; // 居间人（上线）
	private String safeMoneyRate; // 保证金率
	private BigDecimal overnightGoodRate; // 隔夜商品保证金占用
	private BigDecimal overnightStockIndexRate; // 隔夜沪指保证金占用
	private String longHolidayRate; // 小长假持仓标准
	private String remark; // 备注
	private Integer closeStatus; // 结算状态 1:同意2:拒绝

	/**
	 * 待审核方案详情
	 * 
	 * @return
	 */
	@Action("programDetail")
	public String programDetail() {
		try {
			FuProgram program = fuProgramService.get(id);
			this.getActionContext().put("program", program);

			BigDecimal safeMoney = program.getSafeMoney();
			BigDecimal manageMoney = program.getManageMoney();
			// 实收金额
			BigDecimal bigAmg = safeMoney.add(manageMoney);

			// 大写金额
			this.getActionContext().put("smallAmg", bigAmg);
			this.getActionContext().put("bigAmg", RMB.toBigAmt(bigAmg.doubleValue()));

			// 该方案的不良详情
			List<FuBadCredit> badList = fuBadCreditService.findListByProgram(program.getId());
			this.getActionContext().put("badList", badList);

			// 管理费列表
			List<FuManageFee> feeList = fuManageFeeService.findFeeListByProgramId(id);
			this.getActionContext().put("feeList", feeList);

			// 续约记录列表
			List<FuContinueContract> continueList = fuContinueContractService.findContinueListByProgramId(id);
			this.getActionContext().put("continueList", continueList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 方案管理详情
	 * 
	 * @return
	 */
	@Action("programListDetail")
	public String programListDetail() {
		try {
			FuProgram program = fuProgramService.get(id);
			this.getActionContext().put("program", program);

			BigDecimal safeMoney = program.getSafeMoney();
			BigDecimal manageMoney = program.getManageMoney();
			// 实收金额
			BigDecimal bigAmg = safeMoney.add(manageMoney);

			// 大写金额
			this.getActionContext().put("smallAmg", bigAmg);
			this.getActionContext().put("bigAmg", RMB.toBigAmt(bigAmg.doubleValue()));

			// 该方案的不良详情
			List<FuBadCredit> badList = fuBadCreditService.findListByProgram(program.getId());
			this.getActionContext().put("badList", badList);

			// 增配主方案
			BigDecimal warnLine = BigDecimal.ZERO;
			BigDecimal closeLine = BigDecimal.ZERO;
			if (program.getAddMatchId() != null && program.getAddMatchId() > 0) {
				FuProgram addProgram = fuProgramService.get(program.getAddMatchId());
				warnLine = addProgram.getWarnLine().add(program.getWarnLine());
				closeLine = addProgram.getCloseLine().add(program.getCloseLine());
				this.getActionContext().put("addProgram", addProgram);
			}

			// 减配主方案
			if (program.getSubMatchId() != null && program.getSubMatchId() > 0) {
				FuProgram subProgram = fuProgramService.get(program.getSubMatchId());
				warnLine = subProgram.getWarnLine();
				closeLine = subProgram.getCloseLine();
				this.getActionContext().put("subProgram", subProgram);
			}
			this.getActionContext().put("warnLine", warnLine);
			this.getActionContext().put("closeLine", closeLine);

			// 管理费列表
			List<FuManageFee> feeList = fuManageFeeService.findFeeListByProgramId(id);
			this.getActionContext().put("feeList", feeList);

			// 续约记录列表
			List<FuContinueContract> continueList = fuContinueContractService.findContinueListByProgramId(id);
			this.getActionContext().put("continueList", continueList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 结算方案详情
	 * 
	 * @return
	 */
	@Action("overProgramDetail")
	public String overProgramDetail() {
		try {
			FuProgram program = fuProgramService.get(id);
			this.getActionContext().put("program", program);

			BigDecimal safeMoney = program.getSafeMoney();
			BigDecimal manageMoney = program.getManageMoney();
			// 实收金额
			BigDecimal bigAmg = safeMoney.add(manageMoney);

			// 大写金额
			this.getActionContext().put("smallAmg", bigAmg);
			this.getActionContext().put("bigAmg", RMB.toBigAmt(bigAmg.doubleValue()));

			// 该方案的不良详情
			List<FuBadCredit> badList = fuBadCreditService.findListByProgram(program.getId());
			this.getActionContext().put("badList", badList);

			// 管理费列表
			List<FuManageFee> feeList = fuManageFeeService.findFeeListByProgramId(id);
			this.getActionContext().put("feeList", feeList);

			// 续约记录列表
			List<FuContinueContract> continueList = fuContinueContractService.findContinueListByProgramId(id);
			this.getActionContext().put("continueList", continueList);

			// 结算的出金明细
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("dictionaryId", 32);
			List<FuMoneyDetail> detailList = fuMoneyDetailService.findFuMoneyDetailByParams(map);
			this.getActionContext().put("detailList", detailList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 线下待结算方案详情
	 * 
	 * @return
	 */
	@Action("offlineOverProgramDetail")
	public String offlineOverProgramDetail() {
		try {
			FuProgram program = fuProgramService.get(id);
			this.getActionContext().put("program", program);

			BigDecimal safeMoney = program.getSafeMoney();
			BigDecimal manageMoney = program.getManageMoney();
			// 实收金额
			BigDecimal bigAmg = safeMoney.add(manageMoney);

			// 大写金额
			this.getActionContext().put("smallAmg", bigAmg);
			this.getActionContext().put("bigAmg", RMB.toBigAmt(bigAmg.doubleValue()));

			// 该方案的不良详情
			List<FuBadCredit> badList = fuBadCreditService.findListByProgram(program.getId());
			this.getActionContext().put("badList", badList);

			// 管理费列表
			List<FuManageFee> feeList = fuManageFeeService.findFeeListByProgramId(id);
			this.getActionContext().put("feeList", feeList);

			// 续约记录列表
			List<FuContinueContract> continueList = fuContinueContractService.findContinueListByProgramId(id);
			this.getActionContext().put("continueList", continueList);

			// 结算的出金明细
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("programId", id);
			map.put("dictionaryId", 32);
			List<FuMoneyDetail> detailList = fuMoneyDetailService.findFuMoneyDetailByParams(map);
			this.getActionContext().put("detailList", detailList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 已删除方案详情
	 * 
	 * @return
	 */
	@Action("deleteProgramDetail")
	public String deleteProgramDetail() {
		try {
			FuProgram program = fuProgramService.get(id);
			this.getActionContext().put("program", program);

			BigDecimal safeMoney = program.getSafeMoney();
			BigDecimal manageMoney = program.getManageMoney();
			// 实收金额
			BigDecimal bigAmg = safeMoney.add(manageMoney);

			// 大写金额
			this.getActionContext().put("smallAmg", bigAmg);
			this.getActionContext().put("bigAmg", RMB.toBigAmt(bigAmg.doubleValue()));

			// 该方案的不良详情
			List<FuBadCredit> badList = fuBadCreditService.findListByProgram(program.getId());
			this.getActionContext().put("badList", badList);

			// 管理费列表
			List<FuManageFee> feeList = fuManageFeeService.findFeeListByProgramId(id);
			this.getActionContext().put("feeList", feeList);

			// 续约记录列表
			List<FuContinueContract> continueList = fuContinueContractService.findContinueListByProgramId(id);
			this.getActionContext().put("continueList", continueList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 营销补充方案风控信息
	 * 
	 * @return
	 */
	@Action("updateProgramAjax")
	public String updateProgramAjax() {
		try {
			FuProgram program = fuProgramService.get(id);
			this.getActionContext().put("program", program);
			List<FuServer> serverList = fuServerService.fundList();
			this.getActionContext().put("serverList", serverList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 修改要素表
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveProgramAjax")
	public String saveProgramAjax() {
		try {
			if (flag == 2 && serverId == null) {
				write("-2");
				return null;
			}
			if (flag == 2 && tradeAccount == null) {
				write("-3");
				return null;
			}
			if (flag == 2 && StringUtil.isBlank(tradePassword)) {
				write("-4");
				return null;
			}
			FuProgram program = fuProgramService.get(id);
			if (serverId != null) {
				FuServer sv = fuServerService.get(serverId);
				program.setTradeServiceName(sv.getServerName());
				program.setTradeIp(sv.getServerIp());
				program.setTradePort(sv.getPortNumber());
				program.setFuServer(sv);
				program.setRoleId(sv.getUsertypeId());
			}
			program.setNextManageMoneyTime(nextManageMoneyTime);// 下次收取管理费的时间
			program.setTradeAccount(tradeAccount); // 交易账号
			program.setTradePassword(tradePassword); // 交易密码
			program.setComment(comment); // 营销员
			program.setGoodsFee(goodsFee); // 商品期货手续费
			program.setStockIndexFee(stockIndexFee);// 股指期货手续费
			program.setReturnCommission(returnCommission);// 返佣
			program.setMediator(mediator);// 居间人
			program.setSafeMoneyRate(safeMoneyRate);// 保证金率
			program.setOvernightGoodRate(overnightGoodRate);// 隔夜商品保证金占用
			program.setOvernightStockIndexRate(overnightStockIndexRate);// 隔夜沪指保证金占用
			program.setLongHolidayRate(longHolidayRate);// 小长假持仓标准
			program.setRemark(remark);// 备注
			program.setFuAdmin(admin);
			program.setCheckTime(new Date());
			fuProgramService.save(program);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 结算方案页面（后台）
	 * 
	 * @return
	 */
	@Action("programCloseAjax")
	public String programCloseAjax() {
		try {
			FuProgram program = fuProgramService.get(id);
			this.getActionContext().put("program", program);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 后台主动结算方案
	 * 
	 * @return
	 */
	@Action("closeProgramAjax")
	public String closeProgramAjax() {
		try {
			FuProgram pro = fuProgramService.get(id);
			// 占用保证金（大于0有持仓，否则无持仓）
			BigDecimal margin = zhongqiService.findCurrMargin(pro.getFuServer(), pro.getTradeAccount());
			if (margin.compareTo(BigDecimal.ZERO) == 1) {// 有持仓
				write("-1");
				return null;
			}
			Calendar cal1 = Calendar.getInstance();
			Date time0 = cal1.getTime();
			cal1.set(Calendar.HOUR_OF_DAY, 15);
			cal1.set(Calendar.MINUTE, 16);
			cal1.set(Calendar.SECOND, 0);
			Date time1 = cal1.getTime();
			cal1.set(Calendar.HOUR_OF_DAY, 16);
			cal1.set(Calendar.MINUTE, 20);
			cal1.set(Calendar.SECOND, 0);
			Date time2 = cal1.getTime();
			boolean dealing = time0.after(time1) && time0.before(time2);// 15:16～16:20时间段

			int weekday = cal1.get(Calendar.DAY_OF_WEEK);
			if (weekday == 1 || weekday == 7) {// 周末
				write("-2");
				return null;
			}
			if (dealing == false) {
				write("-3");
				return null;
			}
			if (pro.getAddMatchId() != null) {// 增配子方案不能主动结算
				write("-4");
				return null;
			}

			String result;
			List<FuProgram> list = fuProgramService.findSonProgramByProgram(pro.getId());// 根据主方案id得到子方案
			if (list.size() > 0 && list.get(0).getStatus() == 2) {// 如果当前方案有交易中的增配子方案
				result = fuProgramService.saveOverProgramByAfter(list.get(0), admin);
			} else {// 独立的方案
				result = fuProgramService.saveOverProgramByAfter(pro, admin);
			}
			JSONObject obj = new JSONObject();
			obj.put("result", result);
			write(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 线下结算方案页面（后台）
	 * 
	 * @return
	 */
	@Action("programCloseOfflineAjax")
	public String programCloseOfflineAjax() {
		try {
			FuProgram program = fuProgramService.get(id);
			this.getActionContext().put("program", program);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 后台线下结算方案
	 * 
	 * @return
	 */
	@Action("closeProgramOfflineAjax")
	public String closeProgramOfflineAjax() {
		try {
			FuProgram pro = fuProgramService.get(id);
			// 占用保证金（大于0有持仓，否则无持仓）
			BigDecimal margin = zhongqiService.findCurrMargin(pro.getFuServer(), pro.getTradeAccount());
			if (margin.compareTo(BigDecimal.ZERO) == 1) {// 有持仓
				write("-1");
				return null;
			}
			Calendar cal1 = Calendar.getInstance();
			Date time0 = cal1.getTime();
			cal1.set(Calendar.HOUR_OF_DAY, 15);
			cal1.set(Calendar.MINUTE, 16);
			cal1.set(Calendar.SECOND, 0);
			Date time1 = cal1.getTime();
			cal1.set(Calendar.HOUR_OF_DAY, 16);
			cal1.set(Calendar.MINUTE, 20);
			cal1.set(Calendar.SECOND, 0);
			Date time2 = cal1.getTime();
			boolean dealing = time0.after(time1) && time0.before(time2);// 15:16～16:20时间段

			int weekday = cal1.get(Calendar.DAY_OF_WEEK);
			if (weekday == 1 || weekday == 7) {// 周末
				write("-2");
				return null;
			}
			if (dealing == false) {
				write("-3");
				return null;
			}

			String result;
			if (pro.getAddMatchId() != null) {// 到期的待结算增配子方案
				result = fuProgramService.saveOfflineOverSonProgram(pro, admin);
			}
			List<FuProgram> list = fuProgramService.findSonProgramByProgram(pro.getId());// 根据主方案id得到子方案
			if (list.size() > 0 && list.get(0).getStatus() == 2) {// 如果当前方案有交易中的增配子方案
				result = fuProgramService.saveOverProgramOffline(list.get(0), admin);
			} else {// 独立的方案
				result = fuProgramService.saveOverProgramOffline(pro, admin);
			}
			JSONObject obj = new JSONObject();
			obj.put("result", result);
			write(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 打开审核方案页面（后台）
	 * 
	 * @return
	 */
	@Action("programAjax")
	public String programAjax() {
		try {
			FuProgram program = fuProgramService.get(id);
			this.getActionContext().put("program", program);
			List<FuServer> serverList = fuServerService.fundList();
			if (addMatchId == null) {
				addMatchId = new Long("-1");
			}
			this.getActionContext().put("addMatchId", addMatchId);
			this.getActionContext().put("serverList", serverList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 审核方案开户（后台）
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("checkProgramAjax")
	public String checkProgramAjax() {
		try {
			FuProgram program = fuProgramService.get(id);
			if (flag == 2 && addMatchId == null && serverId == null) {
				write("-2");
				return null;
			}
			if (flag == 2 && program.getFuUser().getAccountBalance().compareTo((program.getManageMoney().add(program.getSafeMoney()))) == -1) {
				write("-5");
				return null;
			}
			Calendar cal2 = Calendar.getInstance();
			Date time3 = cal2.getTime();
			cal2.set(Calendar.HOUR_OF_DAY, 16);
			cal2.set(Calendar.MINUTE, 20);
			cal2.set(Calendar.SECOND, 1);
			Date time4 = cal2.getTime();
			cal2.set(Calendar.HOUR_OF_DAY, 16);
			cal2.set(Calendar.MINUTE, 50);
			cal2.set(Calendar.SECOND, 0);
			Date time5 = cal2.getTime();
			boolean dealing = time3.after(time4) && time3.before(time5);// 16:20-16:50时间段
			if (dealing == true) {
				write("-3");
				return null;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (sdf.parse(sdf.format(program.getTradeTime())).getTime() > sdf.parse(sdf.format(new Date())).getTime() && flag == 2) {
				write("-4");
				return null;
			}
			if (program.getFuUser().getIsAcrossCabin() == 1) {
				write("-8");
				return null;
			}
			program.setFuAdmin(admin);
			program.setCheckTime(new Date());
			if (flag == 2) {
				if (serverId != null) {
					FuServer sv = fuServerService.get(serverId);
					if (sv.getServerMoney().compareTo(program.getMatchMoney()) == -1) {
						write("-6");
						return null;
					}
					program.setTradeServiceName(sv.getServerName());
					program.setTradeIp(sv.getServerIp());
					program.setTradePort(sv.getPortNumber());
					program.setFuServer(sv);
					program.setRoleId(sv.getUsertypeId());
					if (program.getDoubleNum() == 1) {
						program.setGroupId(program.getProgramType() == 1 ? sv.getDayId1() : sv.getMonthId1());
					}
					if (program.getDoubleNum() == 2) {
						program.setGroupId(program.getProgramType() == 1 ? sv.getDayId2() : sv.getMonthId2());
					}
					if (program.getDoubleNum() == 3) {
						program.setGroupId(program.getProgramType() == 1 ? sv.getDayId3() : sv.getMonthId3());
					}
					if (program.getDoubleNum() == 4) {
						program.setGroupId(program.getProgramType() == 1 ? sv.getDayId4() : sv.getMonthId4());
					}
					if (program.getDoubleNum() == 5) {
						program.setGroupId(program.getProgramType() == 1 ? sv.getDayId5() : sv.getMonthId5());
					}
					if (program.getDoubleNum() == 6) {
						program.setGroupId(program.getProgramType() == 1 ? sv.getDayId6() : sv.getMonthId6());
					}
					if (program.getDoubleNum() == 7) {
						program.setGroupId(program.getProgramType() == 1 ? sv.getDayId7() : sv.getMonthId7());
					}
					if (program.getDoubleNum() == 8) {
						program.setGroupId(program.getProgramType() == 1 ? sv.getDayId8() : sv.getMonthId8());
					}
					if (program.getDoubleNum() == 9) {
						program.setGroupId(program.getProgramType() == 1 ? sv.getDayId9() : sv.getMonthId9());
					}
					if (program.getDoubleNum() == 10) {
						program.setGroupId(program.getProgramType() == 1 ? sv.getDayId10() : sv.getMonthId10());
					}
					fuProgramService.save(program);
				} else {// 如果没有服务器id，说明是增配
					FuProgram pro = fuProgramService.get(addMatchId);// 增配主方案
					FuServer sv = pro.getFuServer();
					if (sv.getServerMoney().compareTo(program.getMatchMoney()) == -1) {
						write("-7");
						return null;
					}
					program.setTradeServiceName(pro.getTradeServiceName());
					program.setTradeIp(pro.getTradeIp());
					program.setTradePort(pro.getTradePort());
					program.setFuServer(pro.getFuServer());
					program.setRoleId(pro.getRoleId());
					program.setGroupId(pro.getGroupId());
					program.setTradeAccount(pro.getTradeAccount());
					program.setTradePassword(pro.getFuUser().getPhone().substring(5, 11));
				}
			}
			fuProgramService.saveCheck(program, flag, admin, serverId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 添加不良记录
	 * 
	 * @return
	 */
	@Action("addBadRecordAjax")
	public String addBadRecordAjax() {
		try {
			FuProgram program = fuProgramService.get(id);
			this.getActionContext().put("program", program);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 保存不良记录信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveBadRecordAjax")
	public String saveBadRecordAjax() {
		try {
			if (type == null) {
				write("-2");// 请选择类型
				return null;
			}
			if (time == null) {
				write("-3");// 请选择时间
				return null;
			}
			if (StringUtil.isBlank(badDetail)) {
				write("-4");// 请输入不良详情
				return null;
			}
			FuProgram pro = fuProgramService.get(id);
			FuBadCredit bad = new FuBadCredit();
			bad.setFuAdmin(admin);
			bad.setAddTime(new Date());
			bad.setDetail(badDetail);
			bad.setFuProgram(pro);
			bad.setType(type);
			bad.setFuUser(pro.getFuUser());
			if (money != null)
				bad.setMoney(money);
			bad.setTime(time);
			fuBadCreditService.save(bad);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 告知开户
	 * 
	 * @return
	 */
	@Action("tellOpenAjax")
	public String tellOpenAjax() {
		try {
			FuProgram program = fuProgramService.get(id);
			this.getActionContext().put("program", program);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 发送短信
	 * 
	 * @return
	 */
	@Action("sendMsgAjax")
	public String sendMsgAjax() {
		try {
			FuProgram program = fuProgramService.get(id);
			program.setFuAdmin(admin); // 核对人
			program.setCheckTime(new Date()); // 核对时间
			fuProgramService.save(program);

			// 发短信
			String message = URLDecoder.decode(msg, "UTF-8");
			FuSmsLog log = new FuSmsLog();
			log.setFuUser(program.getFuUser());
			log.setContent(message);
			log.setPrio(1);
			log.setReason("告知开户成功");
			log.setDestination(program.getFuUser().getPhone());
			log.setPlanTime(new Date());
			log.setType(1);// 短信
			log.setState(0);
			fuSmsLogService.save(log);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 导出方案excel
	 * 
	 * @return
	 */
	@Action("exportExcel")
	public String exportExcel() {
		try {
			// 获取数据库方案信息
			FuProgram program = fuProgramService.get(id);

			// 直接往response的输出流中写excel
			OutputStream os = this.getHttpServletResponse().getOutputStream();
			// 获取文件名称
			String fileName = System.currentTimeMillis() + ".xls";
			// 下载格式设置
			this.getHttpServletResponse().setContentType("APPLICATION/OCTET-STREAM");
			this.getHttpServletResponse().setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

			// 选择模板文件：
			// excel模板存放位置
			String excelPath = this.getServletContext().getRealPath("/");
			String separator = System.getProperty("file.separator");
			excelPath = excelPath + "uploads" + separator + "attach" + separator + "program_template.xls";
			System.out.println(excelPath);
			InputStream is = new FileInputStream(excelPath);
			Workbook wb = Workbook.getWorkbook(is);
			WorkbookSettings settings = new WorkbookSettings();
			settings.setWriteAccess(null);
			// 通过模板得到一个可写的Workbook：
			WritableCell wc = null;

			WritableWorkbook wwb = Workbook.createWorkbook(os, wb, settings);
			// 选择模板中名称为sheet1的Sheet：
			WritableSheet ws = wwb.getSheet("sheet1");

			WritableCellFormat wcf = getWritableCellFormatCellFormat();
			WritableCellFormat noWCF = new WritableCellFormat();
			noWCF.setBorder(Border.ALL, BorderLineStyle.NONE);
			// 选择单元格，写入动态值，根据单元格的不同类型转换成相应类型的单元格：
			// Label lable;
			// wc = ws.getWritableCell(7, 11);
			// wc = cloneCellWithValue(7, 11, "1100019/MAG11041", noWCF);
			// ws.addCell(wc);

			// 第2行填表时间
			WritableCellFormat wcf2 = new WritableCellFormat();
			wcf2.setAlignment(Alignment.RIGHT);
			wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);
			wc = ws.getWritableCell(0, 1);
			wc = cloneCellWithValue(0, 1, "填表时间：  " + DateUtil.getStrFromDate(program.getCreateTime(), "yyyy年MM月dd日"), wcf2); // 方案提交时间
			ws.addCell(wc);

			// 第5行
			WritableCellFormat wcf5 = new WritableCellFormat();
			wcf5.setBorder(Border.ALL, BorderLineStyle.THIN);
			wcf5.setAlignment(Alignment.CENTRE);
			wcf5.setVerticalAlignment(VerticalAlignment.CENTRE);
			wc = ws.getWritableCell(3, 4);
			wc = cloneCellWithValue(3, 4, program.getFuUser().getUserName(), wcf5); // 用户姓名
			ws.addCell(wc);
			wc = ws.getWritableCell(5, 4);
			wc = cloneCellWithValue(5, 4, program.getFuUser().getPhone(), wcf5); // 用户电话
			ws.addCell(wc);
			wc = ws.getWritableCell(7, 4);
			if (null != program.getTradeAccount() && null != program.getTradePassword()) {
				wc = cloneCellWithValue(7, 4, program.getTradeAccount() + "     （ 密码：" + program.getTradePassword() + "）", wcf5); // 分账户号
			} else {
				wc = cloneCellWithValue(7, 4, "", wcf5); // 分账户号
			}
			ws.addCell(wc);

			// 第6行
			WritableCellFormat wcf6 = new WritableCellFormat();
			wcf6.setBorder(Border.ALL, BorderLineStyle.THIN); // 加四周边框线条
			wcf6.setAlignment(Alignment.CENTRE); // 水平居中
			wcf6.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直居中
			wc = ws.getWritableCell(3, 5);
			String serverRealName = "";
			if (null != program.getFuServer() && null != program.getFuServer().getServerRealName()) {
				serverRealName = serverRealName + program.getFuServer().getServerRealName();
			}
			wc = cloneCellWithValue(3, 5, serverRealName, wcf6); // 期货公司
			ws.addCell(wc);
			wc = ws.getWritableCell(5, 5);
			wc = cloneCellWithValue(5, 5, program.getFuUser().getUserName(), wcf6); // 开户姓名
			ws.addCell(wc);
			wc = ws.getWritableCell(7, 5);
			wc = cloneCellWithValue(7, 5, "", wcf6); // 主账户号
			ws.addCell(wc);

			// 第7行
			WritableCellFormat wcf7 = new WritableCellFormat();
			wcf7.setBorder(Border.ALL, BorderLineStyle.THIN); // 加四周边框线条
			wcf7.setAlignment(Alignment.CENTRE); // 水平居中
			wcf7.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直居中
			wc = ws.getWritableCell(3, 6);
			wc = cloneCellWithValue(3, 6, program.getFuUser().getId(), wcf7); // 客户ID
			ws.addCell(wc);
			wc = ws.getWritableCell(5, 6);
			wc = cloneCellWithValue(5, 6, program.getId(), wcf7); // 方案ID
			ws.addCell(wc);
			wc = ws.getWritableCell(7, 6);
			if (null != program.getTradeIp() && null != program.getTradePort()) {
				wc = cloneCellWithValue(7, 6, program.getTradeIp() + ":" + program.getTradePort(), wcf7); // IP地址
			} else {
				wc = cloneCellWithValue(7, 6, "", wcf7); // IP地址
			}
			ws.addCell(wc);

			// 第8行
			WritableCellFormat wcf8 = new WritableCellFormat();
			wcf8.setBorder(Border.ALL, BorderLineStyle.THIN); // 加四周边框线条
			wcf8.setAlignment(Alignment.LEFT); // 水平居左
			wcf8.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直居中
			wc = ws.getWritableCell(2, 7);
			String fromDate = DateUtil.getStrFromDate(program.getCreateTime(), "yyyy年MM月dd日"); // 合同生效日期
			String toDate = DateUtil.getStrFromDate(program.getCloseTime(), "yyyy年MM月dd日"); // 合同失效日期
			wc = cloneCellWithValue(2, 7, "合同期限：   " + fromDate + "  至    " + toDate, wcf8); // 合同期限
			ws.addCell(wc);

			// 第9行
			WritableCellFormat wcf9 = new WritableCellFormat();
			wcf9.setBorder(Border.ALL, BorderLineStyle.THIN); // 加四周边框线条
			wcf9.setAlignment(Alignment.LEFT); // 水平居左
			wcf9.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直居中
			wc = ws.getWritableCell(2, 8);
			BigDecimal safeMoney = BigDecimal.ZERO; // 客户本金
			BigDecimal matchMoney = BigDecimal.ZERO; // 配资资金
			BigDecimal totalMatchMoney = BigDecimal.ZERO; // 账户总额
			if (null != program.getSafeMoney()) {
				safeMoney = program.getSafeMoney();
			}
			if (null != program.getSafeMoney()) {
				matchMoney = program.getMatchMoney();
			}
			if (null != program.getSafeMoney()) {
				totalMatchMoney = program.getTotalMatchMoney();
			}
			wc = cloneCellWithValue(2, 8, "客户本金（小写）：      " + StringUtil.getDecimalFormat(safeMoney) + "元  ， 配资资金（小写）：   " + StringUtil.getDecimalFormat(matchMoney) + "元  ， 账户总额：    " + StringUtil.getDecimalFormat(totalMatchMoney) + "元", wcf9); // 客户本金
			ws.addCell(wc);

			// 第10行
			WritableCellFormat wcf10 = new WritableCellFormat();
			wcf10.setBorder(Border.ALL, BorderLineStyle.THIN); // 加四周边框线条
			wcf10.setAlignment(Alignment.LEFT); // 水平居左
			wcf10.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直居中
			wc = ws.getWritableCell(2, 9);
			BigDecimal monthlyInterest = BigDecimal.ZERO; // 月息
			if (null != program.getMatchMoney()) {
				monthlyInterest = program.getMatchMoney().multiply(program.getMoneyPercent());
			}
			String pzbl = "";
			if (program.getProgramType() == 1) {
				pzbl = "配资比例：     1  ： " + program.getDoubleNum() + " 日息：  " + StringUtil.getDecimalFormat(program.getMoneyPercent()) + " 元  ";
			} else {
				BigDecimal bdMoneyPercent = program.getMoneyPercent();
				bdMoneyPercent = bdMoneyPercent.divide(new BigDecimal(100));
				String moneyPercent = StringUtil.getDecimalFormat(bdMoneyPercent);
				pzbl = "配资比例：     1  ： " + program.getDoubleNum() + " 月息：  " + StringUtil.getDecimalFormat(monthlyInterest) + " 元 ， 月息  " + program.getMoneyPercent().multiply(new BigDecimal(100)) + " %";
			}
			wc = cloneCellWithValue(2, 9, pzbl, wcf10); // 配资比例
			ws.addCell(wc);

			// 第11行
			WritableCellFormat wcf11 = new WritableCellFormat();
			wcf11.setBorder(Border.ALL, BorderLineStyle.THIN); // 加四周边框线条
			wcf11.setAlignment(Alignment.LEFT); // 水平居中
			wcf11.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直居中
			wc = ws.getWritableCell(3, 10);
			wc = cloneCellWithValue(3, 10, program.getComment(), wcf11); // 营销员
			ws.addCell(wc);
			wc = ws.getWritableCell(5, 10);
			wc = cloneCellWithValue(5, 10, program.getMediator(), wcf11); // 上线
			ws.addCell(wc);
			wc = ws.getWritableCell(6, 10);
			String returnCommission = "返佣（手续费、利息）：";
			if (null != program.getReturnCommission()) {
				returnCommission = returnCommission + program.getReturnCommission();
			}
			wc = cloneCellWithValue(6, 10, returnCommission, wcf11); // 返佣（手续费、利息）
			ws.addCell(wc);

			// 第12行
			WritableCellFormat wcf12 = new WritableCellFormat();
			wcf12.setBorder(Border.ALL, BorderLineStyle.THIN); // 加四周边框线条
			wcf12.setAlignment(Alignment.LEFT); // 水平居左
			wcf12.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直居中
			wc = ws.getWritableCell(3, 11);
			wc = cloneCellWithValue(3, 11, StringUtil.getDecimalFormat(safeMoney) + "元", wcf12); // 保证金
			ws.addCell(wc);
			wc = ws.getWritableCell(7, 11);
			wc = cloneCellWithValue(7, 11, "超过  " + StringUtil.getDecimalFormat(totalMatchMoney) + "  元,可出  " + StringUtil.getDecimalFormat(totalMatchMoney) + " 元以上", wcf12); // 出金
			ws.addCell(wc);

			// 第13行
			WritableCellFormat wcf13 = new WritableCellFormat();
			wcf13.setBorder(Border.ALL, BorderLineStyle.THIN); // 加四周边框线条
			wcf13.setAlignment(Alignment.LEFT); // 水平居左
			wcf13.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直居中
			wc = ws.getWritableCell(3, 12);
			String stockIndexFee = "股指: ";
			if (null != program.getStockIndexFee()) {
				stockIndexFee = stockIndexFee + program.getStockIndexFee();
			}
			String goodsFee = "商品: ";
			if (null != program.getGoodsFee()) {
				goodsFee = goodsFee + program.getGoodsFee();
			}
			wc = cloneCellWithValue(3, 12, stockIndexFee + " ,  " + goodsFee, wcf13); // 手续费
			ws.addCell(wc);
			wc = ws.getWritableCell(7, 12);
			String safeMoneyRate = "";
			if (null != program.getSafeMoneyRate()) {
				safeMoneyRate = safeMoneyRate + program.getSafeMoneyRate();
			}
			wc = cloneCellWithValue(7, 12, safeMoneyRate, wcf13); // 保证金率
			ws.addCell(wc);

			// 第14行
			WritableCellFormat wcf14 = new WritableCellFormat();
			wcf14.setBorder(Border.ALL, BorderLineStyle.THIN); // 加四周边框线条
			wcf14.setAlignment(Alignment.LEFT); // 水平居左
			wcf14.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直居中
			BigDecimal warnLine = BigDecimal.ZERO; // 账户总额
			if (null != program.getWarnLine()) {
				warnLine = program.getWarnLine();
			}
			wc = ws.getWritableCell(3, 13);
			wc = cloneCellWithValue(3, 13, "   " + StringUtil.getDecimalFormat(warnLine) + "    元", wcf14); // 预警线
			ws.addCell(wc);
			wc = ws.getWritableCell(6, 13);
			String overnightGoodRate = "隔夜商品保证金占用 : ";
			if (null != program.getOvernightGoodRate()) {
				overnightGoodRate = overnightGoodRate + program.getOvernightGoodRate();
			}
			wc = cloneCellWithValue(6, 13, overnightGoodRate, wcf14); // 隔夜商品保证金
			ws.addCell(wc);

			// 第15行
			WritableCellFormat wcf15 = new WritableCellFormat();
			wcf15.setBorder(Border.ALL, BorderLineStyle.THIN); // 加四周边框线条
			wcf15.setAlignment(Alignment.LEFT); // 水平居左
			wcf15.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直居中
			BigDecimal closeLine = BigDecimal.ZERO; // 强平线
			if (null != program.getCloseLine()) {
				closeLine = program.getCloseLine();
			}
			wc = ws.getWritableCell(3, 14);
			wc = cloneCellWithValue(3, 14, "   " + StringUtil.getDecimalFormat(closeLine) + "    元", wcf15); // 强平线
			ws.addCell(wc);
			wc = ws.getWritableCell(6, 14);
			String overnightStockIndexRate = "隔夜沪指保证金占用 : ";
			if (null != program.getOvernightStockIndexRate()) {
				overnightStockIndexRate = overnightStockIndexRate + program.getOvernightStockIndexRate();
			}
			wc = cloneCellWithValue(6, 14, overnightStockIndexRate, wcf15); // 隔夜沪指保证金占用
			ws.addCell(wc);

			// 第16行
			WritableCellFormat wcf16 = new WritableCellFormat();
			wcf16.setBorder(Border.ALL, BorderLineStyle.THIN); // 加四周边框线条
			wcf16.setAlignment(Alignment.LEFT); // 水平居左
			wcf16.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直居中
			wc = ws.getWritableCell(6, 15);
			String longHolidayRate = "小长假持仓标准: ";
			if (null != program.getLongHolidayRate()) {
				longHolidayRate = longHolidayRate + program.getLongHolidayRate();
			}
			wc = cloneCellWithValue(6, 15, longHolidayRate, wcf16); // 小长假持仓标准
			ws.addCell(wc);

			// 第21行
			WritableCellFormat wcf21 = new WritableCellFormat();
			wcf21.setBorder(Border.ALL, BorderLineStyle.THIN); // 加四周边框线条
			wcf21.setAlignment(Alignment.LEFT); // 水平居左
			wcf21.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直居中
			BigDecimal bigAmg = new BigDecimal(0); // 强平线
			if (null != program.getSafeMoney()) {
				BigDecimal _safeMoney = program.getSafeMoney();
				BigDecimal _manageMoney = program.getManageMoney();
				// 实收金额
				bigAmg = _safeMoney.add(_manageMoney);
			}
			wc = ws.getWritableCell(2, 20);
			wc = cloneCellWithValue(2, 20, "金额：   " + RMB.toBigAmt(bigAmg.doubleValue()) + " （￥" + StringUtil.getDecimalFormat(bigAmg) + "）", wcf21); // 金额
			ws.addCell(wc);

			// 第23行
			WritableCellFormat wcf23 = new WritableCellFormat();
			wcf23.setBorder(Border.ALL, BorderLineStyle.THIN); // 加四周边框线条
			wcf23.setAlignment(Alignment.LEFT); // 水平居左
			wcf23.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直居中
			wc = ws.getWritableCell(0, 22);
			String remark = "备注： ";
			if (null != program.getRemark()) {
				remark = remark + program.getRemark();
			}
			wc = cloneCellWithValue(0, 22, remark, wcf23); // 备注
			ws.addCell(wc);

			// 第24行
			WritableCellFormat wcf24 = new WritableCellFormat();
			wcf24.setBorder(Border.ALL, BorderLineStyle.THIN); // 加四周边框线条
			wcf24.setAlignment(Alignment.RIGHT); // 水平居右
			wcf24.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直居中
			wc = ws.getWritableCell(0, 23);
			String operator = "开户经办人：";
			if (null != program.getFuAdmin() && null != program.getFuAdmin().getName()) {
				operator = operator + program.getFuAdmin().getName();
			}
			String operatortime = "开户经办时间：";
			if (null != program.getCheckTime()) {
				operatortime = operatortime + DateUtil.getStrFromDate(program.getCheckTime(), "yyyy-MM-dd HH:mm:ss");
			}
			String checkor = "开户核对人：";
			if (null != program.getFuAdmin() && null != program.getFuAdmin().getName()) {
				checkor = checkor + program.getFuAdmin().getName();
			}
			String checktime = "开户核对时间：";
			if (null != program.getCheckTime()) {
				checktime = checktime + DateUtil.getStrFromDate(program.getCheckTime(), "yyyy-MM-dd HH:mm:ss");
			}
			wc = cloneCellWithValue(0, 23, operator + "     " + operatortime + "      " + checkor + "       " + checktime, wcf24); // 经办
			ws.addCell(wc);

			// 表格主体循环打入数据
			// for (int i = 0; i < invoiceList.size(); i++) {
			// wc = ws.getWritableCell(0, 18 + i);
			// wc = cloneCellWithValue(0, 18 + i, invoiceList.get(i).getBoxno(),
			// wcf);
			// ws.addCell(wc);
			// wc = ws.getWritableCell(1, 18 + i);
			// wc = cloneCellWithValue(1, 18 + i,
			// invoiceList.get(i).getOrderno(), wcf);
			// ws.addCell(wc);
			// wc = ws.getWritableCell(2, 18 + i);
			// wc = cloneCellWithValue(2, 18 + i, invoiceList.get(i).getCo(),
			// wcf);
			// ws.addCell(wc);
			// wc = ws.getWritableCell(3, 18 + i);
			// wc = cloneCellWithValue(3, 18 + i,
			// invoiceList.get(i).getPartno(), wcf);
			// ws.addCell(wc);
			// wc = ws.getWritableCell(4, 18 + i);
			// wc = cloneCellWithValue(4, 18 + i,
			// invoiceList.get(i).getSsrelationship(), wcf);
			// ws.addCell(wc);
			// wc = ws.getWritableCell(5, 18 + i);
			// wc = cloneCellWithValue(5, 18 + i,
			// invoiceList.get(i).getDsction(), wcf);
			// ws.addCell(wc);
			// wc = ws.getWritableCell(6, 18 + i);
			// wc = cloneCellWithValue(6, 18 + i, invoiceList.get(i).getQty(),
			// wcf);
			// ws.addCell(wc);
			// wc = ws.getWritableCell(7, 18 + i);
			// wc = cloneCellWithValue(7, 18 + i,
			// invoiceList.get(i).getUnitprice(), wcf);
			// ws.addCell(wc);
			// wc = ws.getWritableCell(8, 18 + i);
			// wc = cloneCellWithValue(8, 18 + i, invoiceList.get(i).getAmout(),
			// wcf);
			// ws.addCell(wc);
			// }
			wwb.write();
			// 关闭文件
			wwb.close();
			System.out.println("导出成功");
			return null;
		} catch (Exception e) {
			System.out.println("导出失败");
			e.printStackTrace();
			return null;
		}
		// } catch (IOException e){
		// System.out.println("导出失败");
		// e.printStackTrace();
		// return null;
		// } catch (RowsExceededException e){
		// System.out.println("导出失败");
		// e.printStackTrace();
		// return null;
		// } catch (WriteException e){
		// System.out.println("导出失败");
		// e.printStackTrace();
		// return null;
		// }
	}

	/**
	 * 验证输入的数据格式转换
	 * 
	 * @param col
	 * @param row
	 * @param value
	 * @param wcFormat
	 * @return
	 */
	public WritableCell cloneCellWithValue(int col, int row, Object value, WritableCellFormat wcFormat) {
		WritableCell wc = null;
		try {
			// 判断数据是否为STRING类型，是用LABLE形式插入，否则用NUMBER形式插入
			if (value == null) {
				wc = new jxl.write.Blank(col, row, wcFormat);
			} else if (value instanceof String) {
				jxl.write.Label label = new jxl.write.Label(col, row, value.toString(), wcFormat);
				wc = label;
			} else {
				wc = new jxl.write.Number(col, row, new Double(value.toString()).doubleValue(), wcFormat);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wc;
	}

	/**
	 * 获得单元格标准格式
	 * 
	 * @return
	 */
	public WritableCellFormat getWritableCellFormatCellFormat() {
		WritableCellFormat wcf = new WritableCellFormat();
		try {
			// 设置居中
			wcf.setAlignment(Alignment.CENTRE);
			wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return wcf;
	}

	public FuAdmin getAdmin() {
		return admin;
	}

	public void setAdmin(FuAdmin admin) {
		this.admin = admin;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getBadDetail() {
		return badDetail;
	}

	public void setBadDetail(String badDetail) {
		this.badDetail = badDetail;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Long getServerId() {
		return serverId;
	}

	public void setServerId(Long serverId) {
		this.serverId = serverId;
	}

	public String getTradePassword() {
		return tradePassword;
	}

	public void setTradePassword(String tradePassword) {
		this.tradePassword = tradePassword;
	}

	public Integer getTradeAccount() {
		return tradeAccount;
	}

	public void setTradeAccount(Integer tradeAccount) {
		this.tradeAccount = tradeAccount;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getGoodsFee() {
		return goodsFee;
	}

	public void setGoodsFee(String goodsFee) {
		this.goodsFee = goodsFee;
	}

	public String getStockIndexFee() {
		return stockIndexFee;
	}

	public void setStockIndexFee(String stockIndexFee) {
		this.stockIndexFee = stockIndexFee;
	}

	public String getReturnCommission() {
		return returnCommission;
	}

	public void setReturnCommission(String returnCommission) {
		this.returnCommission = returnCommission;
	}

	public String getMediator() {
		return mediator;
	}

	public void setMediator(String mediator) {
		this.mediator = mediator;
	}

	public String getSafeMoneyRate() {
		return safeMoneyRate;
	}

	public void setSafeMoneyRate(String safeMoneyRate) {
		this.safeMoneyRate = safeMoneyRate;
	}

	public BigDecimal getOvernightGoodRate() {
		return overnightGoodRate;
	}

	public void setOvernightGoodRate(BigDecimal overnightGoodRate) {
		this.overnightGoodRate = overnightGoodRate;
	}

	public BigDecimal getOvernightStockIndexRate() {
		return overnightStockIndexRate;
	}

	public void setOvernightStockIndexRate(BigDecimal overnightStockIndexRate) {
		this.overnightStockIndexRate = overnightStockIndexRate;
	}

	public String getLongHolidayRate() {
		return longHolidayRate;
	}

	public void setLongHolidayRate(String longHolidayRate) {
		this.longHolidayRate = longHolidayRate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getCloseStatus() {
		return closeStatus;
	}

	public void setCloseStatus(Integer closeStatus) {
		this.closeStatus = closeStatus;
	}

	public Date getNextManageMoneyTime() {
		return nextManageMoneyTime;
	}

	public void setNextManageMoneyTime(Date nextManageMoneyTime) {
		this.nextManageMoneyTime = nextManageMoneyTime;
	}

	public Long getAddMatchId() {
		return addMatchId;
	}

	public void setAddMatchId(Long addMatchId) {
		this.addMatchId = addMatchId;
	}

}
