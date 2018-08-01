package com.hongwei.futures.web.action.admin_list_over_program;

import java.math.BigDecimal;
import java.text.ParseException;
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
import com.hongwei.futures.service.FuProgramService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminListOverProgramAction extends BaseAction {
	@Autowired
	private FuProgramService fuProgramService;

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
	private Float feePecent1;// 利率
	private Float feePecent2;
	private BigDecimal managerMoney1;// 管理费
	private BigDecimal managerMoney2;
	private Date time1;// 发起时间
	private Date time2;

	/**
	 * 结算方案列表
	 * 
	 * @return
	 * @throws ParseException
	 */
	@Action("overProgramList")
	public String overProgramList() {
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
				map.put("feePecent1", (int) (feePecent1 * 10000));
			if (feePecent2 != null)
				map.put("feePecent2", (int) (feePecent2 * 10000));
			if (managerMoney1 != null)
				map.put("managerMoney1", managerMoney1);
			if (managerMoney2 != null)
				map.put("managerMoney2", managerMoney2);
			if (time1 != null)
				map.put("time1", time1);
			if (time2 != null)
				map.put("time2", time2);

			if (totalCount == null) {
				totalCount = fuProgramService.countWaitOverProgram(map);
			}
			List<FuProgram> programList = fuProgramService
					.findWaitOverProgramList(
							(this.getPageNo() - 1) * this.getPageSize(),
							this.getPageSize(), map);
			this.getActionContext().put("programList", programList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 线下待结算方案列表
	 * 
	 * @return
	 * @throws ParseException
	 */
	@Action("offlineOverProgramList")
	public String offlineOverProgramList() {
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
				map.put("feePecent1", (int) (feePecent1 * 10000));
			if (feePecent2 != null)
				map.put("feePecent2", (int) (feePecent2 * 10000));
			if (managerMoney1 != null)
				map.put("managerMoney1", managerMoney1);
			if (managerMoney2 != null)
				map.put("managerMoney2", managerMoney2);
			if (time1 != null)
				map.put("time1", time1);
			if (time2 != null)
				map.put("time2", time2);

			if (totalCount == null) {
				totalCount = fuProgramService.countOfflineWaitOverProgram(map);
			}
			List<FuProgram> programList = fuProgramService
					.findOfflineWaitOverProgramList((this.getPageNo() - 1)
							* this.getPageSize(), this.getPageSize(), map);
			this.getActionContext().put("programList", programList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
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

	public Float getFeePecent1() {
		return feePecent1;
	}

	public void setFeePecent1(Float feePecent1) {
		this.feePecent1 = feePecent1;
	}

	public Float getFeePecent2() {
		return feePecent2;
	}

	public void setFeePecent2(Float feePecent2) {
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

}
