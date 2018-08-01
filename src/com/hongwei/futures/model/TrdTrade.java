package com.hongwei.futures.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "trd_trade")
public class TrdTrade implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1863758840173433977L;
	private Long id;
	private FuUser fuUser; 
	private String instrumentId; //合约
	private Integer direction;//买卖标志
	private Integer offsetFlag;//开平
	private String openOrderNum; //开仓订单编号
	private Date openDateTime;//开仓时间
	private Integer openVolume;//开仓成交手数
	private BigDecimal openPrice;//开仓成交价格
	private BigDecimal openMoney;//开仓成交金额
	private String closeOrderNum; //平仓订单编号
	private Date closeDateTime;//平仓时间
	private Integer closeVolume;//平仓成交手数
	private BigDecimal closePrice;//平仓成交价格
	private BigDecimal closeMoney;//平仓成交金额
	private Integer state;//状态
	private Date createTime;//创建时间
	private Date updateTime;//更新时间
	private BigDecimal closeProfit;//平仓盈亏
	private BigDecimal safeMoney;//保证金
	private BigDecimal manageMoney;//管理费
	private Integer closeType;	// 平仓类型  1: 用户平仓 2: 系统止盈 3: 系统止损 4: 系统强平 5:到时终止
	private Integer backDirection;//回报买卖标志
	private Integer backOffsetFlag;//回报开平
		
	public TrdTrade() {
		super();
	}

	public TrdTrade(Long id, FuUser fuUser, String instrumentId, Integer direction, Integer offsetFlag, String openOrderNum, Date openDateTime, 
			Integer openVolume, BigDecimal openPrice, BigDecimal openMoney, String closeOrderNum, Date closeDateTime, Integer closeVolume,
			BigDecimal closePrice, BigDecimal closeMoney, Integer state, Date createTime, Date updateTime, BigDecimal closeProfit,BigDecimal 
			safeMoney, BigDecimal manageMoney, Integer closeType, Integer backDirection, Integer backOffsetFlag) {
		super();
		this.id = id;
		this.fuUser = fuUser;
		this.instrumentId = instrumentId;
		this.direction = direction;
		this.offsetFlag = offsetFlag;
		this.openOrderNum = openOrderNum;
		this.openDateTime = openDateTime;
		this.openVolume = openVolume;
		this.openPrice = openPrice;
		this.openMoney = openMoney;
		this.closeOrderNum = closeOrderNum;
		this.closeDateTime = closeDateTime;
		this.closeVolume = closeVolume;
		this.closePrice = closePrice;
		this.closeMoney = closeMoney;
		this.state = state;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.closeProfit = closeProfit;
		this.safeMoney = safeMoney;
		this.manageMoney = manageMoney;
		this.closeType = closeType;
		this.backDirection = backDirection;
		this.backOffsetFlag = backOffsetFlag;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public FuUser getFuUser() {
		return fuUser;
	}

	public void setFuUser(FuUser fuUser) {
		this.fuUser = fuUser;
	}

	@Column(name = "instrument_id")
	public String getInstrumentId() {
		return instrumentId;
	}

	public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
	}

	@Column(name = "direction")
	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	@Column(name = "offset_flag")
	public Integer getOffsetFlag() {
		return offsetFlag;
	}

	public void setOffsetFlag(Integer offsetFlag) {
		this.offsetFlag = offsetFlag;
	}

	@Column(name = "open_order_num")
	public String getOpenOrderNum() {
		return openOrderNum;
	}

	public void setOpenOrderNum(String openOrderNum) {
		this.openOrderNum = openOrderNum;
	}

	@Column(name = "open_datetime")
	public Date getOpenDateTime() {
		return openDateTime;
	}

	public void setOpenDateTime(Date openDateTime) {
		this.openDateTime = openDateTime;
	}

	@Column(name = "open_volume")
	public Integer getOpenVolume() {
		return openVolume;
	}

	public void setOpenVolume(Integer openVolume) {
		this.openVolume = openVolume;
	}

	@Column(name = "open_price")
	public BigDecimal getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(BigDecimal openPrice) {
		this.openPrice = openPrice;
	}

	@Column(name = "open_money")
	public BigDecimal getOpenMoney() {
		return openMoney;
	}

	public void setOpenMoney(BigDecimal openMoney) {
		this.openMoney = openMoney;
	}

	@Column(name = "close_order_num")
	public String getCloseOrderNum() {
		return closeOrderNum;
	}

	public void setCloseOrderNum(String closeOrderNum) {
		this.closeOrderNum = closeOrderNum;
	}

	@Column(name = "close_datetime")
	public Date getCloseDateTime() {
		return closeDateTime;
	}

	public void setCloseDateTime(Date closeDateTime) {
		this.closeDateTime = closeDateTime;
	}

	@Column(name = "close_volume")
	public Integer getCloseVolume() {
		return closeVolume;
	}

	public void setCloseVolume(Integer closeVolume) {
		this.closeVolume = closeVolume;
	}

	@Column(name = "close_price")
	public BigDecimal getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(BigDecimal closePrice) {
		this.closePrice = closePrice;
	}

	@Column(name = "close_money")
	public BigDecimal getCloseMoney() {
		return closeMoney;
	}

	public void setCloseMoney(BigDecimal closeMoney) {
		this.closeMoney = closeMoney;
	}

	@Column(name = "state")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "update_time")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "close_profit")
	public BigDecimal getCloseProfit() {
		return closeProfit;
	}

	public void setCloseProfit(BigDecimal closeProfit) {
		this.closeProfit = closeProfit;
	}
	
	@Column(name = "safe_money")
	public BigDecimal getSafeMoney() {
		return safeMoney;
	}

	public void setSafeMoney(BigDecimal safeMoney) {
		this.safeMoney = safeMoney;
	}

	@Column(name = "manage_money")
	public BigDecimal getManageMoney() {
		return manageMoney;
	}

	public void setManageMoney(BigDecimal manageMoney) {
		this.manageMoney = manageMoney;
	}

	@Column(name="close_type")
	public Integer getCloseType() {
		return closeType;
	}

	public void setCloseType(Integer closeType) {
		this.closeType = closeType;
	}
	
	@Column(name="back_direction")
	public Integer getBackDirection() {
		return backDirection;
	}

	public void setBackDirection(Integer backDirection) {
		this.backDirection = backDirection;
	}

	@Column(name="back_offset_flag")
	public Integer getBackOffsetFlag() {
		return backOffsetFlag;
	}

	public void setBackOffsetFlag(Integer backOffsetFlag) {
		this.backOffsetFlag = backOffsetFlag;
	}
}
