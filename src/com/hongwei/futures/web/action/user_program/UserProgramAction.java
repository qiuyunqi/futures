package com.hongwei.futures.web.action.user_program;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAddMargin;
import com.hongwei.futures.model.FuDrawProfits;
import com.hongwei.futures.model.FuManageFee;
import com.hongwei.futures.model.FuProgram;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuAddMarginService;
import com.hongwei.futures.service.FuDrawProfitsService;
import com.hongwei.futures.service.FuManageFeeService;
import com.hongwei.futures.service.FuProgramService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("fu_common")
public class UserProgramAction extends BaseAction {
	@Autowired
	private FuProgramService fuProgramService;
	@Autowired
	private FuManageFeeService fuManagerFeeService;
	@Autowired
	private FuAddMarginService fuAddMarginService;
	@Autowired
	private FuDrawProfitsService fuDrawProfitsService;

	private FuUser fuUser;
	private Long userId;
	private Integer totalCount;
	private Integer flag;
	private Integer programType;// 1短线，2趋势
	private Float matchMoney;// 实盘资金
	private int cycleNum;// 预存周期
	private Integer tradeTimeType;// 1今天，2下个交易日
	private int num;// 资金赔率
	private int cycleNum1;
	private int cycleNum2;
	private Long id;
	private Long ppId;

	/**
	 * 方案(1短线，2趋势)
	 * 
	 * @return
	 */
	@Action(value = "programList", results = {
			@Result(name = "shortProgram", location = "/WEB-INF/content/user_program/shortProgram.jsp"),
			@Result(name = "longProgram", location = "/WEB-INF/content/user_program/longProgram.jsp") })
	public String programList() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (flag == null)
				flag = 1;
			map.put("flag", flag);
			map.put("userId", userId);
			map.put("programType", programType);
			if (totalCount == null)
				totalCount = fuProgramService.countProgramByUser(map);
			List<FuProgram> programList = fuProgramService.findProgramByUser(
					(this.getPageNo() - 1) * this.getPageSize(),
					this.getPageSize(), map);
			for (int i = 0; i < programList.size(); i++) {
				Calendar cal = Calendar.getInstance();
				// 下次付息时间
				if (programList.get(i).getProgramType() == 2) {// 天
					cal.setTime(programList.get(i).getEndManageMoneyTime());
					cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
				}
				Date nextTime = cal.getTime();
				programList.get(i).setEndManageMoneyTime(nextTime);
			}

			this.getActionContext().put("programList", programList);
			if (programType == 1) {
				// 短线进行天数
				List<Integer> dayList = new ArrayList<Integer>();
				Calendar calendar = Calendar.getInstance();

				for (int i = 0; programList != null && i < programList.size(); i++) {
					Calendar c = Calendar.getInstance();
					c.setTime(programList.get(i).getTradeTime());
					int d = 0;
					while (c.getTime().getTime() <= calendar.getTime()
							.getTime()) {
						int weekday = c.get(Calendar.DAY_OF_WEEK);
						c.set(Calendar.DATE, c.get(Calendar.DATE) + 1);
						if (weekday == 1 || weekday == 7) {
							continue;
						} else {
							d = d + 1;
						}
					}
					dayList.add(d);
				}
				this.getActionContext().put("dayList", dayList);
				return "shortProgram";
			} else {
				return "longProgram";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 方案详情
	 * 
	 * @return
	 */
	@Action(value = "programDetail", results = {
			@Result(name = "ok", location = "/WEB-INF/content/user_program/programDetail.jsp"),
			@Result(name = "no", location = "${ctx}/user_program/programList.htm?programType=${programType}", type = "redirect") })
	public String programDetail() {
		try {
			FuProgram program = fuProgramService.get(id);
			this.getActionContext().put("program", program);

			if (program == null
					|| !userId.toString().equals(
							program.getFuUser().getId().toString())) {
				if (program != null) {
					programType = program.getProgramType();
				} else {
					programType = 1;
				}
				return "no";
			}

			// 账户管理费记录
			List<FuManageFee> feeList = fuManagerFeeService
					.findFeeListByProgramId(id);
			this.getActionContext().put("feeList", feeList);

			// 追加保证金记录
			List<FuAddMargin> safeList = fuAddMarginService
					.findSafeMoneyByProgramId(id);
			this.getActionContext().put("safeList", safeList);

			// 增配所有记录
			List<FuProgram> upProgramList = fuProgramService
					.findProgramUpByProgram(id);
			this.getActionContext().put("upProgramList", upProgramList);

			// 有效增配子方案记录（状态0124567）
			List<FuProgram> sonProgram = fuProgramService
					.findSonProgramByProgram(id);
			this.getActionContext().put("sonProgram", sonProgram);

			// 提取利润记录
			List<FuDrawProfits> drawList = fuDrawProfitsService
					.findDrawProfitsByProgramId(id);
			this.getActionContext().put("drawList", drawList);

			this.getActionContext().put("jsptype", "programDetail");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ok";
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

	public Float getMatchMoney() {
		return matchMoney;
	}

	public void setMatchMoney(Float matchMoney) {
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

}
