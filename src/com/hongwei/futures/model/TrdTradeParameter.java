package com.hongwei.futures.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TrdTradeParameter entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "trd_trade_parameter")
public class TrdTradeParameter implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6790014477958645689L;
	// Fields
	private Long id;
	private String tradeVariety;
	private Integer dayTradeNum;
	private BigDecimal safeMoney;
	private BigDecimal manageMoney;
	private BigDecimal productPercent;
	private String tradeTime; // 开盘时间
	private String closeTime; // 收盘时间
	private BigDecimal riskPercent;
	private BigDecimal stopLossPercent;
	private BigDecimal stopProfitPercent;
	private Integer dayBaseNum;
	private BigDecimal dayBaseFactor;
	private Integer mainPosition;
	private BigDecimal mainSafePercent;
	private Integer userAddTimes;	
	private String productIco; 			// 产品图标链接地址
	private Integer todayBuyVolume; 	// 产品当天交易笔数
	private String nightOpenTime; 		// 晚上开盘时间
	private String nightCloseTime; 	    // 晚上关盘时间
	private String instrumentName; 		// 产品名称
	private String market;         		// 交易所名称
	private BigDecimal smallestPriceChange;   //最小变动价位
	private BigDecimal changePoints;        // 每次跳动点位
	private Integer openType;				// 买入类型 : 1: 市价买入 2:限价买入
	private String investor;				// 投资人姓名
	private String breakCloseTime;      // 强行平仓时间收盘时间
	private Integer isOpenNight;		// 是否开启晚上的时间  0: 开启  1: 不开启
	private BigDecimal actualRiskPercent; //每日涨跌幅
	private String timeSlot;			// 产品开盘的时间段


	// Constructors
	/** default constructor */
	public TrdTradeParameter() {
	}

	public TrdTradeParameter(Long id, String tradeVariety, Integer dayTradeNum,
			BigDecimal safeMoney, BigDecimal manageMoney,
			BigDecimal productPercent, String tradeTime, String closeTime,
			BigDecimal riskPercent, BigDecimal stopLossPercent,
			BigDecimal stopProfitPercent, Integer dayBaseNum,
			BigDecimal dayBaseFactor, Integer mainPosition,
			BigDecimal mainSafePercent, Integer userAddTimes,
			String productIco, Integer todayBuyVolume, String nightOpenTime,
			String nightCloseTime, String instrumentName, String market, 
			BigDecimal smallestPriceChange, BigDecimal changePoints, Integer openType, String investor,
			String breakCloseTime, Integer isOpenNight, BigDecimal actualRiskPercent, String timeSlot) {
		super();
		this.id = id;
		this.tradeVariety = tradeVariety;
		this.dayTradeNum = dayTradeNum;
		this.safeMoney = safeMoney;
		this.manageMoney = manageMoney;
		this.productPercent = productPercent;
		this.tradeTime = tradeTime;
		this.closeTime = closeTime;
		this.riskPercent = riskPercent;
		this.stopLossPercent = stopLossPercent;
		this.stopProfitPercent = stopProfitPercent;
		this.dayBaseNum = dayBaseNum;
		this.dayBaseFactor = dayBaseFactor;
		this.mainPosition = mainPosition;
		this.mainSafePercent = mainSafePercent;
		this.userAddTimes = userAddTimes;
		this.productIco = productIco;
		this.todayBuyVolume = todayBuyVolume;
		this.nightOpenTime = nightOpenTime;
		this.nightCloseTime = nightCloseTime;
		this.instrumentName = instrumentName;
		this.market = market;
		this.smallestPriceChange = smallestPriceChange;
		this.changePoints = changePoints;
		this.openType = openType;
		this.investor = investor;
		this.breakCloseTime = breakCloseTime;
		this.isOpenNight = isOpenNight;
		this.actualRiskPercent = actualRiskPercent;
		this.timeSlot = timeSlot;
	}

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="trade_variety")
	public String getTradeVariety() {
		return tradeVariety;
	}

	public void setTradeVariety(String tradeVariety) {
		this.tradeVariety = tradeVariety;
	}

	@Column(name="day_trade_num")
	public Integer getDayTradeNum() {
		return dayTradeNum;
	}

	public void setDayTradeNum(Integer dayTradeNum) {
		this.dayTradeNum = dayTradeNum;
	}

	@Column(name="safe_money")
	public BigDecimal getSafeMoney() {
		return safeMoney;
	}

	public void setSafeMoney(BigDecimal safeMoney) {
		this.safeMoney = safeMoney;
	}

	@Column(name="manage_money")
	public BigDecimal getManageMoney() {
		return manageMoney;
	}

	public void setManageMoney(BigDecimal manageMoney) {
		this.manageMoney = manageMoney;
	}

	@Column(name="product_percent")
	public BigDecimal getProductPercent() {
		return productPercent;
	}

	public void setProductPercent(BigDecimal productPercent) {
		this.productPercent = productPercent;
	}

	@Column(name="trade_time")
	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	@Column(name="close_time")
	public String getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}

	@Column(name="risk_percent")
	public BigDecimal getRiskPercent() {
		return riskPercent;
	}

	public void setRiskPercent(BigDecimal riskPercent) {
		this.riskPercent = riskPercent;
	}

	@Column(name="stop_loss_percent")
	public BigDecimal getStopLossPercent() {
		return stopLossPercent;
	}

	public void setStopLossPercent(BigDecimal stopLossPercent) {
		this.stopLossPercent = stopLossPercent;
	}

	@Column(name="stop_profit_percent")
	public BigDecimal getStopProfitPercent() {
		return stopProfitPercent;
	}

	public void setStopProfitPercent(BigDecimal stopProfitPercent) {
		this.stopProfitPercent = stopProfitPercent;
	}

	@Column(name="day_base_num")
	public Integer getDayBaseNum() {
		return dayBaseNum;
	}

	public void setDayBaseNum(Integer dayBaseNum) {
		this.dayBaseNum = dayBaseNum;
	}

	@Column(name="day_base_factor")
	public BigDecimal getDayBaseFactor() {
		return dayBaseFactor;
	}

	public void setDayBaseFactor(BigDecimal dayBaseFactor) {
		this.dayBaseFactor = dayBaseFactor;
	}

	@Column(name="main_position")
	public Integer getMainPosition() {
		return mainPosition;
	}

	public void setMainPosition(Integer mainPosition) {
		this.mainPosition = mainPosition;
	}

	@Column(name="main_safe_percent")
	public BigDecimal getMainSafePercent() {
		return mainSafePercent;
	}

	public void setMainSafePercent(BigDecimal mainSafePercent) {
		this.mainSafePercent = mainSafePercent;
	}

	@Column(name="user_add_times")
	public Integer getUserAddTimes() {
		return userAddTimes;
	}

	public void setUserAddTimes(Integer userAddTimes) {
		this.userAddTimes = userAddTimes;
	}

	@Column(name="product_ico")
	public String getProductIco() {
		return productIco;
	}

	public void setProductIco(String productIco) {
		this.productIco = productIco;
	}

	@Column(name="today_buy_volume")
	public Integer getTodayBuyVolume() {
		return todayBuyVolume;
	}

	public void setTodayBuyVolume(Integer todayBuyVolume) {
		this.todayBuyVolume = todayBuyVolume;
	}

	@Column(name="night_open_time")
	public String getNightOpenTime() {
		return nightOpenTime;
	}

	public void setNightOpenTime(String nightOpenTime) {
		this.nightOpenTime = nightOpenTime;
	}

	@Column(name="night_close_time")
	public String getNightCloseTime() {
		return nightCloseTime;
	}

	public void setNightCloseTime(String nightCloseTime) {
		this.nightCloseTime = nightCloseTime;
	}

	@Column(name="instrument_name")
	public String getInstrumentName() {
		return instrumentName;
	}

	public void setInstrumentName(String instrumentName) {
		this.instrumentName = instrumentName;
	}

	@Column(name="market")
	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}
	
	@Column(name="smallest_price_change")
	public BigDecimal getSmallestPriceChange() {
		return smallestPriceChange;
	}

	public void setSmallestPriceChange(BigDecimal smallestPriceChange) {
		this.smallestPriceChange = smallestPriceChange;
	}
	
	@Column(name="change_points")
	public BigDecimal getChangePoints() {
		return changePoints;
	}

	public void setChangePoints(BigDecimal changePoints) {
		this.changePoints = changePoints;
	}

	@Column(name="open_type")
	public Integer getOpenType() {
		return openType;
	}

	public void setOpenType(Integer openType) {
		this.openType = openType;
	}
	
	@Column(name="investor")
	public String getInvestor() {
		return investor;
	}

	public void setInvestor(String investor) {
		this.investor = investor;
	}
	
	@Column(name="break_close_time")
	public String getBreakCloseTime() {
		return breakCloseTime;
	}

	public void setBreakCloseTime(String breakCloseTime) {
		this.breakCloseTime = breakCloseTime;
	}

	@Column(name="is_open_night")
	public Integer getIsOpenNight() {
		return isOpenNight;
	}

	public void setIsOpenNight(Integer isOpenNight) {
		this.isOpenNight = isOpenNight;
	}
	
	@Column(name="actual_risk_percent")
	public BigDecimal getActualRiskPercent() {
		return actualRiskPercent;
	}

	public void setActualRiskPercent(BigDecimal actualRiskPercent) {
		this.actualRiskPercent = actualRiskPercent;
	}
	
	@Column(name="time_slot")
	public String getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}
	
	
}