package com.hongwei.futures.web.action.admin_list_trade_parameter;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.codehaus.xfire.client.Client;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.TrdTradeParameter;
import com.hongwei.futures.service.TrdTradeParameterService;
import com.hongwei.futures.util.Constants;
import com.hongwei.futures.util.FileOperate;
import com.hongwei.futures.util.HttpRemoteUtil;
import com.hongwei.futures.util.StringUtil;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminListTradeParameterAction extends BaseAction {
	private static final long serialVersionUID = -453748054576126406L;

	private FuAdmin admin;
	private Long adminId;

	private Long id;
	private String tradeVariety;
	private Integer dayTradeNum;
	private BigDecimal safeMoney;
	private BigDecimal manageMoney;
	private BigDecimal productPercent;
	private String tradeTime;
	private String closeTime;
	private BigDecimal riskPercent;
	private BigDecimal stopLossPercent;
	private BigDecimal stopProfitPercent;
	private Integer dayBaseNum;
	private BigDecimal dayBaseFactor;
	private Integer mainPosition;
	private BigDecimal mainSafePercent;
	private Integer userAddTimes;
	private String productIco;
	private Integer todayBuyVolume;
	private String nightOpenTime;
	private String nightCloseTime;
	private String instrumentName;
	private String market;
	private BigDecimal smallestPriceChange;
	private BigDecimal changePoints;
	private String breakCloseTime;
	private Integer isOpenNight;
	private String timeSlot;

	@Autowired
	private TrdTradeParameterService tradeParameterService;

	@Action("parameterInfo")
	public String parameterInfo() {
		try {
			List<TrdTradeParameter> paramList = tradeParameterService
					.findAllParams();
			this.getActionContext().put("paramList", paramList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 添加产品交易参数
	 * 
	 * @return
	 */
	@Action("newTradeParameterAjax")
	public String addTradeParameter() {
		try {
			if (id != null) {
				TrdTradeParameter param = tradeParameterService.get(id);
				this.getActionContext().put("tradeParam", param);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 保存产品交易参数
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveTradeParameterAjax")
	public String saveTradeParameterAjax() {
		try {
			TrdTradeParameter param = new TrdTradeParameter();
			if (id != null) {
				param = tradeParameterService.get(id);
			}
			if (StringUtil.isBlank(tradeVariety)) {
				write("-2");
				return null;
			}
			if (dayTradeNum == null) {
				write("-3");
				return null;
			}
			if (safeMoney == null) {
				write("-4");
				return null;
			}
			if (manageMoney == null) {
				write("-5");
				return null;
			}
			if (productPercent == null) {
				write("-6");
				return null;
			}
			if (StringUtil.isBlank(tradeTime)) {
				write("-7");
				return null;
			}
			if (StringUtil.isBlank(closeTime)) {
				write("-8");
				return null;
			}
			if (riskPercent == null) {
				write("-9");
				return null;
			}
			if (stopLossPercent == null) {
				write("-10");
				return null;
			}
			if (stopProfitPercent == null) {
				write("-11");
				return null;
			}
			if (dayBaseNum == null) {
				write("-12");
				return null;
			}
			if (dayBaseFactor == null) {
				write("-13");
				return null;
			}
			if (mainPosition == null) {
				write("-15");
				return null;
			}
			if (mainSafePercent == null) {
				write("-16");
				return null;
			}
			if (userAddTimes == null) {
				write("-17");
				return null;
			}
			if (todayBuyVolume == null) {
				write("-18");
				return null;
			}
			/*
			 * if (StringUtil.isBlank(nightOpenTime)) { write("-19"); return
			 * null; } if (StringUtil.isBlank(nightCloseTime)) { write("-20");
			 * return null; }
			 */
			if (StringUtil.isBlank(instrumentName)) {
				write("-21");
				return null;
			}
			if (StringUtil.isBlank(market)) {
				write("-22");
				return null;
			}
			if (smallestPriceChange == null) {
				write("-23");
				return null;
			}
			if (changePoints == null) {
				write("-30");
				return null;
			}
			if (StringUtil.isBlank(breakCloseTime)) {
				write("-31");
				return null;
			}
			if (id == null) {
				TrdTradeParameter htp = tradeParameterService
						.findByTradeVariety(tradeVariety);
				if (htp != null) {
					write("-14");
					return null;
				}
			}
			if (timeSlot == null || "".equals(timeSlot)) {
				write("-32");
				return null;
			}
			param.setTradeVariety(tradeVariety);
			param.setDayTradeNum(dayTradeNum);
			param.setSafeMoney(safeMoney);
			param.setManageMoney(manageMoney);
			param.setProductPercent(productPercent.divide(new BigDecimal(100)));
			param.setTradeTime(tradeTime);
			param.setCloseTime(closeTime);
			param.setRiskPercent(riskPercent.divide(new BigDecimal(100)));
			param.setStopLossPercent(stopLossPercent
					.divide(new BigDecimal(100)));
			param.setStopProfitPercent(stopProfitPercent.divide(new BigDecimal(
					100)));
			param.setDayBaseNum(dayBaseNum);
			param.setDayBaseFactor(dayBaseFactor);
			param.setMainPosition(mainPosition);
			param.setMainSafePercent(mainSafePercent
					.divide(new BigDecimal(100)));
			param.setUserAddTimes(userAddTimes);
			param.setTodayBuyVolume(todayBuyVolume);
			param.setNightOpenTime(nightOpenTime);
			param.setNightCloseTime(nightCloseTime);
			param.setInstrumentName(instrumentName);
			param.setMarket(market);
			param.setSmallestPriceChange(smallestPriceChange);
			param.setChangePoints(changePoints);
			param.setBreakCloseTime(breakCloseTime);
			param.setIsOpenNight(isOpenNight);
			String newPath = "";
			if (!StringUtil.isBlank(productIco)) {
				if (this.productIco.contains(Constants.DIR_TEMP)
						&& !Constants.OSSEnable) {
					FileOperate op = new FileOperate();
					newPath = this.productIco.replace(Constants.DIR_TEMP,
							Constants.DIR_WX);
					if (!this.productIco.equals(newPath)) {
						op.moveFile(
								this.getServletContext().getRealPath(
										this.productIco),
								this.getServletContext().getRealPath(newPath));
					}
				} else {
					newPath = productIco;
				}
				param.setProductIco(newPath);
			}
			// 存储时间段
			param.setTimeSlot(timeSlot);
			tradeParameterService.save(param);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");

			// argsMap中各属性均不能为null
			if (id != null) {
				Map<String, Object> argsMap = new HashMap<String, Object>();
				argsMap.put("id", id);
				argsMap.put("trade_variety", tradeVariety);
				argsMap.put("day_trade_num", dayTradeNum);
				argsMap.put("safe_money", safeMoney);
				argsMap.put("manage_money", manageMoney);
				argsMap.put("product_percent",
						productPercent.divide(new BigDecimal(100)));
				argsMap.put("trade_time", tradeTime);
				// argsMap.put("close_time", closeTime);
				argsMap.put("close_time", breakCloseTime);
				argsMap.put("risk_percent",
						riskPercent.divide(new BigDecimal(100)));
				argsMap.put("stop_loss_percent",
						stopLossPercent.divide(new BigDecimal(100)));
				argsMap.put("stop_profit_percent",
						stopProfitPercent.divide(new BigDecimal(100)));
				argsMap.put("day_base_num", dayBaseNum);
				argsMap.put("day_base_factor", dayBaseFactor);
				argsMap.put("update_time", sdf.format(new Date()));
				argsMap.put("main_position", mainPosition);
				argsMap.put("main_safe_percent",
						mainSafePercent.divide(new BigDecimal(100)));
				argsMap.put("user_add_times", userAddTimes);
				String html = null;
				InputStream in = Client.class.getClassLoader()
						.getResourceAsStream("trade.properties");
				Properties properties = new Properties();
				properties.load(in);
				html = HttpRemoteUtil.orderPostMethod(
						properties.getProperty("trade.url")
								+ "changeParameter.htm", argsMap);
				if (html != null && !"".equals(html)) {
					JSONObject obj = JSONObject.fromObject(html);
					System.out.println("修改参数返回结果: " + obj);
					String res = obj.get("is_success").toString();
					if (!"1".equals(res)) {
						write("-24");
						return null;
					}
				}
			}
			write("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除参数
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("delParam")
	public String delParam() {
		try {
			tradeParameterService.delete(id);
			write("1");
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

	public String getTradeVariety() {
		return tradeVariety;
	}

	public void setTradeVariety(String tradeVariety) {
		this.tradeVariety = tradeVariety;
	}

	public Integer getDayTradeNum() {
		return dayTradeNum;
	}

	public void setDayTradeNum(Integer dayTradeNum) {
		this.dayTradeNum = dayTradeNum;
	}

	public BigDecimal getSafeMoney() {
		return safeMoney;
	}

	public void setSafeMoney(BigDecimal safeMoney) {
		this.safeMoney = safeMoney;
	}

	public BigDecimal getManageMoney() {
		return manageMoney;
	}

	public void setManageMoney(BigDecimal manageMoney) {
		this.manageMoney = manageMoney;
	}

	public BigDecimal getProductPercent() {
		return productPercent;
	}

	public void setProductPercent(BigDecimal productPercent) {
		this.productPercent = productPercent;
	}

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}

	public BigDecimal getRiskPercent() {
		return riskPercent;
	}

	public void setRiskPercent(BigDecimal riskPercent) {
		this.riskPercent = riskPercent;
	}

	public BigDecimal getStopLossPercent() {
		return stopLossPercent;
	}

	public void setStopLossPercent(BigDecimal stopLossPercent) {
		this.stopLossPercent = stopLossPercent;
	}

	public BigDecimal getStopProfitPercent() {
		return stopProfitPercent;
	}

	public void setStopProfitPercent(BigDecimal stopProfitPercent) {
		this.stopProfitPercent = stopProfitPercent;
	}

	public Integer getDayBaseNum() {
		return dayBaseNum;
	}

	public void setDayBaseNum(Integer dayBaseNum) {
		this.dayBaseNum = dayBaseNum;
	}

	public BigDecimal getDayBaseFactor() {
		return dayBaseFactor;
	}

	public void setDayBaseFactor(BigDecimal dayBaseFactor) {
		this.dayBaseFactor = dayBaseFactor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getMainPosition() {
		return mainPosition;
	}

	public void setMainPosition(Integer mainPosition) {
		this.mainPosition = mainPosition;
	}

	public BigDecimal getMainSafePercent() {
		return mainSafePercent;
	}

	public void setMainSafePercent(BigDecimal mainSafePercent) {
		this.mainSafePercent = mainSafePercent;
	}

	public Integer getUserAddTimes() {
		return userAddTimes;
	}

	public void setUserAddTimes(Integer userAddTimes) {
		this.userAddTimes = userAddTimes;
	}

	public String getProductIco() {
		return productIco;
	}

	public void setProductIco(String productIco) {
		this.productIco = productIco;
	}

	public Integer getTodayBuyVolume() {
		return todayBuyVolume;
	}

	public void setTodayBuyVolume(Integer todayBuyVolume) {
		this.todayBuyVolume = todayBuyVolume;
	}

	public String getNightOpenTime() {
		return nightOpenTime;
	}

	public void setNightOpenTime(String nightOpenTime) {
		this.nightOpenTime = nightOpenTime;
	}

	public String getNightCloseTime() {
		return nightCloseTime;
	}

	public void setNightCloseTime(String nightCloseTime) {
		this.nightCloseTime = nightCloseTime;
	}

	public String getInstrumentName() {
		return instrumentName;
	}

	public void setInstrumentName(String instrumentName) {
		this.instrumentName = instrumentName;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public BigDecimal getSmallestPriceChange() {
		return smallestPriceChange;
	}

	public void setSmallestPriceChange(BigDecimal smallestPriceChange) {
		this.smallestPriceChange = smallestPriceChange;
	}

	public BigDecimal getChangePoints() {
		return changePoints;
	}

	public void setChangePoints(BigDecimal changePoints) {
		this.changePoints = changePoints;
	}

	public String getBreakCloseTime() {
		return breakCloseTime;
	}

	public void setBreakCloseTime(String breakCloseTime) {
		this.breakCloseTime = breakCloseTime;
	}

	public Integer getIsOpenNight() {
		return isOpenNight;
	}

	public void setIsOpenNight(Integer isOpenNight) {
		this.isOpenNight = isOpenNight;
	}

	public String getTimeSlot() {
		return this.timeSlot;
	}

	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}
}
