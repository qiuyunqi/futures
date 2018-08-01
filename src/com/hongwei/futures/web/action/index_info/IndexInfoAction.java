package com.hongwei.futures.web.action.index_info;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;



import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuGame;
import com.hongwei.futures.model.FuParameter;
import com.hongwei.futures.model.FuProgram;
import com.hongwei.futures.model.FuRate;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuGameService;
import com.hongwei.futures.service.FuParameterService;
import com.hongwei.futures.service.FuProgramService;
import com.hongwei.futures.service.FuRateService;
import com.hongwei.futures.service.FuStockAccountService;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("fu_common")
public class IndexInfoAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private FuParameterService fuParameterService;
	@Autowired
	private FuGameService FuGameService;
	@Autowired
	private FuProgramService fuProgramService;
	@Autowired
	private FuRateService fuRateService;
	@Autowired 
	private FuUserService fuUserService;
	@Autowired
	private FuStockAccountService   fuStockAccountService;
	
	private FuUser fuUser;
	private Long userId;
	private Float matchMoney;
	private Integer num;
	private Integer tradeTimeType;
	private Integer cycleNum1;
	private Integer cycleNum2;
	private Integer programType;
	private Long ppId;

	/**
	 * 首页
	 * 
	 * @return
	 */
	@Action(value = "index")
	public String index() {
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
			this.getActionContext().put("jsptype", "index");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 期货大赛首页
	 * 
	 * @return
	 * @throws ParseException
	 */
	@Action("gameIndex")
	public String gameIndex() {
		try {
			FuParameter param = fuParameterService.findParameter();
			BigDecimal matchMoney = new BigDecimal(0.00);
			if (param != null && param.getGameMoney() != null)
				matchMoney = param.getGameMoney();
			BigDecimal safeMoney = matchMoney.divide(new BigDecimal(10));
			BigDecimal totalMoney = matchMoney.add(safeMoney);
			this.getActionContext().put("safeMoney", safeMoney);
			this.getActionContext().put("matchMoney", matchMoney);
			this.getActionContext().put("totalMoney", totalMoney);

			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DATE, 1);
			this.getActionContext().put("time1", cal.getTime());

			cal.add(Calendar.MONTH, 1);
			cal.add(Calendar.DATE, -1);
			this.getActionContext().put("time2", cal.getTime());

			cal.add(Calendar.DATE, 1);
			cal.add(Calendar.MONTH, 1);
			cal.add(Calendar.DATE, -1);
			this.getActionContext().put("time3", cal.getTime());

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.set(Calendar.DATE, 1);
			FuGame g2 = FuGameService.findGameByTime(sdf.parse(sdf.format(c
					.getTime())));
			this.getActionContext().put("g2", g2);

			c.add(Calendar.MONTH, -1);
			FuGame g1 = FuGameService.findGameByTime(sdf.parse(sdf.format(c
					.getTime())));
			this.getActionContext().put("g1", g1);

			// 本周top5
			if (g2 != null) {
				List<FuProgram> weekList = fuProgramService.findGameOrder(
						g2.getId(), 1);
				// 本月top5
				List<FuProgram> monthList = fuProgramService.findGameOrder(
						g2.getId(), 2);
				this.getActionContext().put("weekList", weekList);
				this.getActionContext().put("monthList", monthList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
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

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Float getMatchMoney() {
		return matchMoney;
	}

	public void setMatchMoney(Float matchMoney) {
		this.matchMoney = matchMoney;
	}

	public Integer getTradeTimeType() {
		return tradeTimeType;
	}

	public void setTradeTimeType(Integer tradeTimeType) {
		this.tradeTimeType = tradeTimeType;
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

	public Integer getProgramType() {
		return programType;
	}

	public void setProgramType(Integer programType) {
		this.programType = programType;
	}

	public Long getPpId() {
		return ppId;
	}

	public void setPpId(Long ppId) {
		this.ppId = ppId;
	}

}
