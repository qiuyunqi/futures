package com.hongwei.futures.web.action.admin_list_program;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuProgram;
import com.hongwei.futures.model.FuServer;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuProgramService;
import com.hongwei.futures.util.ExportExcel;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminListProgramAction extends BaseAction {
	@Autowired
	private FuProgramService fuProgramService;

	private FuUser fuUser;
	private FuAdmin admin;
	private Long adminId;
	private Integer totalCount;
	private Long programId;// 方案id
	private Integer programType;// 方案类型
	private Integer status;// 方案状态
	private String userName;// 用户名
	private BigDecimal safeMoney1;// 最少保证金
	private BigDecimal safeMoney2;// 最多保证金
	private Integer doubleNum1;// 最少配资杠杆
	private Integer doubleNum2;// 最多配资杠杆
	private BigDecimal matchMoney1;// 最少借款
	private BigDecimal matchMoney2;// 最多借款
	private Integer cycleNum1;// 最少周期
	private Integer cycleNum2;// 最多周期
	private BigDecimal feePecent1;// 利率
	private BigDecimal feePecent2;
	private BigDecimal managerMoney1;// 管理费
	private BigDecimal managerMoney2;
	private Date time1;// 发起时间
	private Date time2;

	/**
	 * 方案管理
	 * 
	 * @return
	 */
	@Action("programList")
	public String programList() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (programId != null)
				map.put("programId", programId);
			if (programType != null)
				map.put("programType", programType);
			if (status != null)
				map.put("status", status);
			if (!StringUtil.isBlank(userName))
				map.put("userName", userName);
			if (safeMoney1 != null)
				map.put("safeMoney1", safeMoney1);
			if (safeMoney2 != null)
				map.put("safeMoney2", safeMoney2);
			if (doubleNum1 != null)
				map.put("doubleNum1", doubleNum1);
			if (doubleNum2 != null)
				map.put("doubleNum2", doubleNum2);
			if (matchMoney1 != null)
				map.put("matchMoney1", matchMoney1);
			if (matchMoney2 != null)
				map.put("matchMoney2", matchMoney2);
			if (cycleNum1 != null)
				map.put("cycleNum1", cycleNum1);
			if (cycleNum2 != null)
				map.put("cycleNum2", cycleNum2);
			if (feePecent1 != null)
				map.put("feePecent1", feePecent1);
			if (feePecent2 != null)
				map.put("feePecent2", feePecent2);
			if (managerMoney1 != null)
				map.put("managerMoney1", managerMoney1);
			if (managerMoney2 != null)
				map.put("managerMoney2", managerMoney2);
			if (time1 != null)
				map.put("time1", time1);
			if (time2 != null)
				map.put("time2", time2);

			if (totalCount == null)
				totalCount = fuProgramService.countProgram(map);
			List<FuProgram> programList = fuProgramService.findProgramList(
					(this.getPageNo() - 1) * this.getPageSize(),
					this.getPageSize(), map);
			this.getActionContext().put("programList", programList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 待审核方案
	 * 
	 * @return
	 */
	@Action("waitProgramList")
	public String waitProgramList() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (programId != null)
				map.put("programId", programId);
			if (programType != null)
				map.put("programType", programType);
			if (!StringUtil.isBlank(userName))
				map.put("userName", userName);
			if (safeMoney1 != null)
				map.put("safeMoney1", safeMoney1);
			if (safeMoney2 != null)
				map.put("safeMoney2", safeMoney2);
			if (doubleNum1 != null)
				map.put("doubleNum1", doubleNum1);
			if (doubleNum2 != null)
				map.put("doubleNum2", doubleNum2);
			if (matchMoney1 != null)
				map.put("matchMoney1", matchMoney1);
			if (matchMoney2 != null)
				map.put("matchMoney2", matchMoney2);
			if (cycleNum1 != null)
				map.put("cycleNum1", cycleNum1);
			if (cycleNum2 != null)
				map.put("cycleNum2", cycleNum2);
			if (feePecent1 != null)
				map.put("feePecent1", feePecent1);
			if (feePecent2 != null)
				map.put("feePecent2", feePecent2);
			if (managerMoney1 != null)
				map.put("managerMoney1", managerMoney1);
			if (managerMoney2 != null)
				map.put("managerMoney2", managerMoney2);
			if (time1 != null)
				map.put("time1", time1);
			if (time2 != null)
				map.put("time2", time2);
			if (totalCount == null)
				totalCount = fuProgramService.countWaitProgram(map);
			List<FuProgram> programList = fuProgramService.findWaitProgramList(
					(this.getPageNo() - 1) * this.getPageSize(),
					this.getPageSize(), map);
			this.getActionContext().put("programList", programList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 导出页面
	 * 
	 * @return
	 */
	@Action("exportExcelAjax")
	public String exportExcelAjax() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (programId != null)
				map.put("programId", programId);
			if (programType != null)
				map.put("programType", programType);
			if (status != null)
				map.put("status", status);
			if (!StringUtil.isBlank(userName))
				map.put("userName", userName);
			if (safeMoney1 != null)
				map.put("safeMoney1", safeMoney1);
			if (safeMoney2 != null)
				map.put("safeMoney2", safeMoney2);
			if (doubleNum1 != null)
				map.put("doubleNum1", doubleNum1);
			if (doubleNum2 != null)
				map.put("doubleNum2", doubleNum2);
			if (matchMoney1 != null)
				map.put("matchMoney1", matchMoney1);
			if (matchMoney2 != null)
				map.put("matchMoney2", matchMoney2);
			if (cycleNum1 != null)
				map.put("cycleNum1", cycleNum1);
			if (cycleNum2 != null)
				map.put("cycleNum2", cycleNum2);
			if (feePecent1 != null)
				map.put("feePecent1", feePecent1);
			if (feePecent2 != null)
				map.put("feePecent2", feePecent2);
			if (managerMoney1 != null)
				map.put("managerMoney1", managerMoney1);
			if (managerMoney2 != null)
				map.put("managerMoney2", managerMoney2);
			if (time1 != null)
				map.put("time1", time1);
			if (time2 != null)
				map.put("time2", time2);
			if (totalCount == null)
				totalCount = fuProgramService.countProgram(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * @throws UnsupportedEncodingException
	 *             导出数据
	 * */
	@Action("exportExcel")
	public String exportExcel() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (programId != null)
				map.put("programId", programId);
			if (programType != null)
				map.put("programType", programType);
			if (status != null)
				map.put("status", status);
			if (!StringUtil.isBlank(userName))
				map.put("userName", userName);
			if (safeMoney1 != null)
				map.put("safeMoney1", safeMoney1);
			if (safeMoney2 != null)
				map.put("safeMoney2", safeMoney2);
			if (doubleNum1 != null)
				map.put("doubleNum1", doubleNum1);
			if (doubleNum2 != null)
				map.put("doubleNum2", doubleNum2);
			if (matchMoney1 != null)
				map.put("matchMoney1", matchMoney1);
			if (matchMoney2 != null)
				map.put("matchMoney2", matchMoney2);
			if (cycleNum1 != null)
				map.put("cycleNum1", cycleNum1);
			if (cycleNum2 != null)
				map.put("cycleNum2", cycleNum2);
			if (feePecent1 != null)
				map.put("feePecent1", feePecent1);
			if (feePecent2 != null)
				map.put("feePecent2", feePecent2);
			if (managerMoney1 != null)
				map.put("managerMoney1", managerMoney1);
			if (managerMoney2 != null)
				map.put("managerMoney2", managerMoney2);
			if (time1 != null)
				map.put("time1", time1);
			if (time2 != null)
				map.put("time2", time2);
			if (totalCount == null)
				totalCount = fuProgramService.countProgram(map);
			List<FuProgram> list = fuProgramService.findProgramByParams(map);
			ExportExcel exportExcel = new ExportExcel();

			String[] headData = new String[21];
			headData[0] = "方案ID";
			headData[1] = "基金";
			headData[2] = "期货公司";
			headData[3] = "使用软件";
			headData[4] = "期货账号";
			headData[5] = "客户姓名";
			headData[6] = "方案状态";
			headData[7] = "账号总额";
			headData[8] = "公司资金";
			headData[9] = "客户资金";
			headData[10] = "销售比例";
			headData[11] = "隔夜商品";
			headData[12] = "隔夜股指";
			headData[13] = "预警线";
			headData[14] = "强平线";
			headData[15] = "利率";
			headData[16] = "日息/月息";
			headData[17] = "权属人";
			headData[18] = "开始日期";
			headData[19] = "结算日期";
			headData[20] = "备注";

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			DecimalFormat df = new DecimalFormat("#,###,##0.00");
			List<Map<Integer, Object>> dataSet = new ArrayList<Map<Integer, Object>>();
			FuProgram program = null;
			for (int i = 0; i < list.size(); i++) {
				program = list.get(i);
				FuServer server = program.getFuServer();
				Map<Integer, Object> m = new HashMap<Integer, Object>();
				m.put(0, program.getId());
				m.put(1, server == null ? "---" : server.getServerRealName());
				m.put(2, server == null ? "---" : server.getFutureCompany());
				m.put(3,
						server == null ? "---"
								: server.getClientType() == 1 ? "众期" : server
										.getClientType() == 1 ? "鑫管家" : "金牛");
				m.put(4,
						server == null ? "_" : server.getId() + "_"
								+ program.getTradeAccount());
				m.put(5, program.getFuUser().getUserName());
				m.put(6,
						program.getStatus() == -1 ? "已关闭"
								: program.getStatus() == 0 ? "待审核" : program
										.getStatus() == 1 ? "审核中" : program
										.getStatus() == 2 ? "交易中" : program
										.getStatus() == 3 ? "拒绝开户" : program
										.getStatus() == 4 ? "等待结算" : program
										.getStatus() == 5 ? "交易结束" : program
										.getStatus() == 6 ? "结算中" : "已穿仓");
				m.put(7,
						program.getTotalMatchMoney() == null ? 0.00 : df
								.format(program.getTotalMatchMoney()));
				m.put(8,
						program.getMatchMoney() == null ? 0.00 : df
								.format(program.getMatchMoney()));
				m.put(9,
						program.getSafeMoney() == null ? 0.00 : df
								.format(program.getSafeMoney()));
				m.put(10, "1:" + program.getDoubleNum());
				m.put(11, program.getOvernightGoodRate() == null ? "---"
						: program.getOvernightGoodRate());
				m.put(12, program.getOvernightStockIndexRate() == null ? "---"
						: program.getOvernightStockIndexRate());
				m.put(13,
						program.getWarnLine() == null ? 0.00 : df
								.format(program.getWarnLine()));
				m.put(14,
						program.getCloseLine() == null ? 0.00 : df
								.format(program.getCloseLine()));
				if (program.getProgramType() == 1) {
					m.put(15,
							program.getMoneyPercent() == null ? 0.00 : df
									.format(program.getMoneyPercent()));
				}
				if (program.getProgramType() == 2) {
					m.put(15,
							program.getMoneyPercent().multiply(
									new BigDecimal(100)) == null ? "0.0000%"
									: program.getMoneyPercent().multiply(
											new BigDecimal(100))
											+ "%");
				}
				m.put(16,
						program.getManageMoney() == null ? 0.00 : df
								.format(program.getManageMoney()));
				m.put(17, program.getFuUser().getRecommend() == null ? "---"
						: program.getFuUser().getRecommend().getUserName());
				m.put(18,
						program.getTradeTime() == null ? "---" : sdf
								.format(program.getTradeTime()));
				m.put(19,
						program.getClearTime() == null ? "---" : sdf
								.format(program.getClearTime()));
				m.put(20,
						program.getRemark() == null ? "---" : program
								.getRemark());
				dataSet.add(m);
			}
			exportExcel.exportToExcel(headData, dataSet, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Long getProgramId() {
		return programId;
	}

	public void setProgramId(Long programId) {
		this.programId = programId;
	}

	public Integer getProgramType() {
		return programType;
	}

	public void setProgramType(Integer programType) {
		this.programType = programType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BigDecimal getSafeMoney1() {
		return safeMoney1;
	}

	public void setSafeMoney1(BigDecimal safeMoney1) {
		this.safeMoney1 = safeMoney1;
	}

	public BigDecimal getSafeMoney2() {
		return safeMoney2;
	}

	public void setSafeMoney2(BigDecimal safeMoney2) {
		this.safeMoney2 = safeMoney2;
	}

	public Integer getDoubleNum1() {
		return doubleNum1;
	}

	public void setDoubleNum1(Integer doubleNum1) {
		this.doubleNum1 = doubleNum1;
	}

	public Integer getDoubleNum2() {
		return doubleNum2;
	}

	public void setDoubleNum2(Integer doubleNum2) {
		this.doubleNum2 = doubleNum2;
	}

	public BigDecimal getMatchMoney1() {
		return matchMoney1;
	}

	public void setMatchMoney1(BigDecimal matchMoney1) {
		this.matchMoney1 = matchMoney1;
	}

	public BigDecimal getMatchMoney2() {
		return matchMoney2;
	}

	public void setMatchMoney2(BigDecimal matchMoney2) {
		this.matchMoney2 = matchMoney2;
	}

	public Integer getCycleNum1() {
		return cycleNum1;
	}

	public void setCycleNum1(Integer cycleNum1) {
		this.cycleNum1 = cycleNum1;
	}

	public Integer getCycleNum2() {
		return cycleNum2;
	}

	public void setCycleNum2(Integer cycleNum2) {
		this.cycleNum2 = cycleNum2;
	}

	public BigDecimal getFeePecent1() {
		return feePecent1;
	}

	public void setFeePecent1(BigDecimal feePecent1) {
		this.feePecent1 = feePecent1;
	}

	public BigDecimal getFeePecent2() {
		return feePecent2;
	}

	public void setFeePecent2(BigDecimal feePecent2) {
		this.feePecent2 = feePecent2;
	}

	public BigDecimal getManagerMoney1() {
		return managerMoney1;
	}

	public void setManagerMoney1(BigDecimal managerMoney1) {
		this.managerMoney1 = managerMoney1;
	}

	public BigDecimal getManagerMoney2() {
		return managerMoney2;
	}

	public void setManagerMoney2(BigDecimal managerMoney2) {
		this.managerMoney2 = managerMoney2;
	}

	public Date getTime1() {
		return time1;
	}

	public void setTime1(Date time1) {
		this.time1 = time1;
	}

	public Date getTime2() {
		return time2;
	}

	public void setTime2(Date time2) {
		this.time2 = time2;
	}

	public FuUser getFuUser() {
		return fuUser;
	}

	public void setFuUser(FuUser fuUser) {
		this.fuUser = fuUser;
	}

}
