package com.hongwei.futures.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "stock_account")
public class FuStockAccount implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8486832228424272483L;
	private Long id;
	private FuUser fuUser;
	private String openUser;
	private String openEquity;
	private String salesDept;
	private String capitalAccount;
	private String capitalPassword;
	private String partnerAccount;
	private Integer accountType;
	private Date createTime;
	private Integer state;// 状态0开启委托，1暂停委托，2申请开启委托中，3申请暂停委托中，4申请结算中
	private Integer isDel;

	private Integer examineStatus; // 0:审核中 1:审核成功 2:审核失败 3:接单中 4:接单成功
	private String stockImage;
	private Integer profitModel; // 0:稳定收益 1:保本分成
	private BigDecimal available;
	private String availableSplit;
	private BigDecimal ableMoney;
	private String ableMoneySplit;
	private Long transactionId;
	private Integer transactionStatus; // 交易团队的是否退券状态 0: 未退券 1:退券
	private String commission; // 佣金
	private Date orderTime; // 抢单时间
	private Date updateTime;
	private Integer isPublish; // 是否发布 0: 未发布 1:发布
	private Integer sourceType; // 0: 网站 1:APP 2: 微信 3:手机微信(2016-06)
	private BigDecimal profitConfig; // 收益分配
	private String message; // 留给交易员的消息
	private Integer isPayMargin;

	private Set<FuStockMoneyDetail> fuStockMoneyDetails = new HashSet<FuStockMoneyDetail>(0);
	private Set<StockShare> ssList = new HashSet<StockShare>();

	// Constructors

	/** default constructor */
	public FuStockAccount() {
	}

	public FuStockAccount(Long id, FuUser fuUser, String openUser, String openEquity, String salesDept, String capitalAccount, String capitalPassword, String partnerAccount, Integer accountType, Date createTime, Integer state, Integer isDel, Integer examineStatus, String stockImage, Integer profitModel, BigDecimal available, String availableSplit, BigDecimal ableMoney, String ableMoneySplit,
			Long transactionId, Integer transactionStatus, String commission, Date orderTime, Date updateTime, Integer isPublish, Integer sourceType, BigDecimal profitConfig, String message, Integer isPayMargin, Set<FuStockMoneyDetail> fuStockMoneyDetails, Set<StockShare> ssList) {
		super();
		this.id = id;
		this.fuUser = fuUser;
		this.openUser = openUser;
		this.openEquity = openEquity;
		this.salesDept = salesDept;
		this.capitalAccount = capitalAccount;
		this.capitalPassword = capitalPassword;
		this.partnerAccount = partnerAccount;
		this.accountType = accountType;
		this.createTime = createTime;
		this.state = state;
		this.isDel = isDel;
		this.examineStatus = examineStatus;
		this.stockImage = stockImage;
		this.profitModel = profitModel;
		this.available = available;
		this.availableSplit = availableSplit;
		this.ableMoney = ableMoney;
		this.ableMoneySplit = ableMoneySplit;
		this.transactionId = transactionId;
		this.transactionStatus = transactionStatus;
		this.commission = commission;
		this.orderTime = orderTime;
		this.updateTime = updateTime;
		this.isPublish = isPublish;
		this.sourceType = sourceType;
		this.profitConfig = profitConfig;
		this.message = message;
		this.fuStockMoneyDetails = fuStockMoneyDetails;
		this.ssList = ssList;
		this.isPayMargin = isPayMargin;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
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

	@Column(name = "open_user")
	public String getOpenUser() {
		return openUser;
	}

	public void setOpenUser(String openUser) {
		this.openUser = openUser;
	}

	@Column(name = "open_equity")
	public String getOpenEquity() {
		return openEquity;
	}

	public void setOpenEquity(String openEquity) {
		this.openEquity = openEquity;
	}

	@Column(name = "sales_dept")
	public String getSalesDept() {
		return salesDept;
	}

	public void setSalesDept(String salesDept) {
		this.salesDept = salesDept;
	}

	@Column(name = "capital_account")
	public String getCapitalAccount() {
		return capitalAccount;
	}

	public void setCapitalAccount(String capitalAccount) {
		this.capitalAccount = capitalAccount;
	}

	@Column(name = "partner_account")
	public String getPartnerAccount() {
		return partnerAccount;
	}

	public void setPartnerAccount(String partnerAccount) {
		this.partnerAccount = partnerAccount;
	}

	@Column(name = "account_type")
	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	@Column(name = "capital_password")
	public String getCapitalPassword() {
		return capitalPassword;
	}

	public void setCapitalPassword(String capitalPassword) {
		this.capitalPassword = capitalPassword;
	}

	@Column(name = "createtime")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "state")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "is_del")
	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fuStockAccount")
	public Set<FuStockMoneyDetail> getFuStockMoneyDetails() {
		return fuStockMoneyDetails;
	}

	public void setFuStockMoneyDetails(Set<FuStockMoneyDetail> fuStockMoneyDetails) {
		this.fuStockMoneyDetails = fuStockMoneyDetails;
	}

	@Column(name = "examine_status")
	public Integer getExamineStatus() {
		return examineStatus;
	}

	public void setExamineStatus(Integer examineStatus) {
		this.examineStatus = examineStatus;
	}

	@Column(name = "stock_image")
	public String getStockImage() {
		return stockImage;
	}

	public void setStockImage(String stockImage) {
		this.stockImage = stockImage;
	}

	@Column(name = "profit_model")
	public Integer getProfitModel() {
		return profitModel;
	}

	public void setProfitModel(Integer profitModel) {
		this.profitModel = profitModel;
	}

	@Column(name = "available")
	public BigDecimal getAvailable() {
		return available;
	}

	public void setAvailable(BigDecimal available) {
		this.available = available;
	}

	@Column(name = "available_split")
	public String getAvailableSplit() {
		return availableSplit;
	}

	public void setAvailableSplit(String availableSplit) {
		this.availableSplit = availableSplit;
	}

	@Column(name = "able_money")
	public BigDecimal getAbleMoney() {
		return ableMoney;
	}

	public void setAbleMoney(BigDecimal ableMoney) {
		this.ableMoney = ableMoney;
	}

	@Column(name = "able_money_split")
	public String getAbleMoneySplit() {
		return ableMoneySplit;
	}

	public void setAbleMoneySplit(String ableMoneySplit) {
		this.ableMoneySplit = ableMoneySplit;
	}

	@Column(name = "transaction_id")
	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	@Column(name = "transaction_status")
	public Integer getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(Integer transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.REMOVE })
	@JoinColumns(value = { @JoinColumn(name = "account_id", referencedColumnName = "id") })
	public Set<StockShare> getSsList() {
		return ssList;
	}

	public void setSsList(Set<StockShare> ssList) {
		this.ssList = ssList;
	}

	@Column(name = "commission")
	public String getCommission() {
		return commission;
	}

	public void setCommission(String commission) {
		this.commission = commission;
	}

	@Column(name = "order_time")
	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	@Column(name = "updatetime")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "is_publish")
	public Integer getIsPublish() {
		return isPublish;
	}

	public void setIsPublish(Integer isPublish) {
		this.isPublish = isPublish;
	}

	@Column(name = "source_type")
	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

	@Column(name = "profit_config")
	public BigDecimal getProfitConfig() {
		return profitConfig;
	}

	public void setProfitConfig(BigDecimal profitConfig) {
		this.profitConfig = profitConfig;
	}

	@Column(name = "message")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Column(name = "is_pay_margin")
	public Integer getIsPayMargin() {
		return isPayMargin;
	}

	public void setIsPayMargin(Integer isPayMargin) {
		this.isPayMargin = isPayMargin;
	}

}