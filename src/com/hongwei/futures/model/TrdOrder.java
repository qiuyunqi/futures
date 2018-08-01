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
@Table(name = "trd_order")
public class TrdOrder implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5819259198712262014L;
	private Long id;
	private FuUser fuUser; 
	private String orderNum; //订单编号
	private String instrumentId; //合约
	private Integer direction;//买卖标志
	private Integer offsetFlag;//开平
	private Integer volume;//成交手数
	private BigDecimal price;//成交价格
	private BigDecimal money;//成交金额
	private Date tradeDateTime;//成交时间
	private String failureCode; //错误编码
	private String failureMsg; //错误信息
	private BigDecimal stopProfit;//止盈
	private BigDecimal stopLoss;//止损
	private Integer state;//订单状态
	private BigDecimal safeMoney;//保证金
	private BigDecimal manageMoney;//管理费
	private Date createTime;//创建时间
	private Date updateTime;//更新时间
		

	public TrdOrder() {
		super();
	}

	public TrdOrder(Long id, FuUser fuUser, String orderNum, String instrumentId, Integer direction, Integer offsetFlag, Integer volume, BigDecimal price, BigDecimal money, 
			Date tradeDateTime,  String failureCode, String failureMsg, BigDecimal stopProfit, BigDecimal stopLoss, Integer state, BigDecimal safeMoney, BigDecimal manageMoney,
			Date createTime, Date updateTime) {
		super();
		this.id = id;
		this.fuUser = fuUser;
		this.orderNum = orderNum;
		this.instrumentId = instrumentId;
		this.direction = direction;
		this.offsetFlag = offsetFlag;
		this.volume = volume;
		this.price = price;
		this.money = money;
		this.tradeDateTime = tradeDateTime;
		this.failureCode = failureCode;
		this.failureMsg = failureMsg;
		this.stopProfit = stopProfit;
		this.stopLoss = stopLoss;
		this.state = state;
		this.safeMoney = safeMoney;
		this.manageMoney = manageMoney;
		this.createTime = createTime;
		this.updateTime = updateTime;		
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

	@Column(name = "order_num")
	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
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

	@Column(name = "volume")
	public Integer getVolume() {
		return volume;
	}

	public void setVolume(Integer volume) {
		this.volume = volume;
	}

	@Column(name = "price")
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Column(name = "money")
	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	@Column(name = "trade_datetime")
	public Date getTradeDateTime() {
		return tradeDateTime;
	}

	public void setTradeDateTime(Date tradeDateTime) {
		this.tradeDateTime = tradeDateTime;
	}

	@Column(name = "failure_code")
	public String getFailureCode() {
		return failureCode;
	}

	public void setFailureCode(String failureCode) {
		this.failureCode = failureCode;
	}

	@Column(name = "failure_msg")
	public String getFailureMsg() {
		return failureMsg;
	}

	public void setFailureMsg(String failureMsg) {
		this.failureMsg = failureMsg;
	}
	
	@Column(name = "stop_profit")
	public BigDecimal getStopProfit() {
		return stopProfit;
	}

	public void setStopProfit(BigDecimal stopProfit) {
		this.stopProfit = stopProfit;
	}

	@Column(name = "stop_loss")
	public BigDecimal getStopLoss() {
		return stopLoss;
	}

	public void setStopLoss(BigDecimal stopLoss) {
		this.stopLoss = stopLoss;
	}
	
	@Column(name = "state")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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
}
