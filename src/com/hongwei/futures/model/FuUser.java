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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * FuUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "fu_user")
public class FuUser implements java.io.Serializable {

	private static final long serialVersionUID = 8505358166051090048L;
	// Fields

	private Long id;
	private String accountName;
	private String password;
	private String userName;
	private String remarkName;
	private String cardNumber;
	private Integer isCheckCard;
	private String phone;
	private String email;
	private Integer isBindEmail;
	private Integer isMarriage;
	private String liveAddress;
	private Integer maxDegree;
	private String businessType;
	private String positionName;
	private BigDecimal salary;
	private String drawPassword;
	private String cardBeforePic;
	private String cardBehindPic;
	private String cardHandPic;
	private BigDecimal accountBalance;
	private BigDecimal accountTotalMoney;
	private BigDecimal matchMoney;
	private BigDecimal safeMoney;
	private BigDecimal freezeMoney;
	private Integer safeLevel;
	private Date registerTime;
	private String registerIp;
	private Integer extendPersonNum;
	private Integer borrowPersonNum;
	private Integer visitIp;
	private Integer visitNum;
	private BigDecimal commissionTotal;
	private BigDecimal exchangeMoney;
	private String phoneCode;
	private String emailCode;
	private String loginToken;
	private Integer state;
	private Long provinceId;
	private Long cityId;
	private String provinceName;
	private String cityName;
	private Integer gender;
	private FuUser recommend;
	private Date loginTime;
	private BigDecimal drawMoney;
	private BigDecimal rechargeMoney;
	private BigDecimal feeTotal;
	private Date phoneCodeTime;
	private Integer hhrLevel;
	private Long hhrParentID;
	private String invitationCode;
	private String userAvatar;
	private String lastLoginIp;
	private String introduction;
	private String nickName;
	private Integer isPhoneReg;
	private BigDecimal integral;
	private Integer isAcrossCabin;
	private String weixinCode;
	private Integer hhrType;
	private String ryToken;
	private Integer isTransaction; // 是否是交易团队 0: 不是 1: 是
	private Integer isSale; // 是否是销售 0: 不是 1: 是
	private Integer qidaRank;
	private BigDecimal qidaIntegral;
	private Date nickNameTime; //昵称的修改时间

	private Set<FuAddMargin> fuAddMargins = new HashSet<FuAddMargin>(0);
	private Set<FuCommission> fuCommissionsForExtendUserId = new HashSet<FuCommission>(0);
	private Set<FuPromote> fuPromotesForPromotedId = new HashSet<FuPromote>(0);
	private Set<FuDrawMoney> fuDrawMoneies = new HashSet<FuDrawMoney>(0);
	private Set<FuRecharge> fuRecharges = new HashSet<FuRecharge>(0);
	private Set<FuCommission> fuCommissionsForUserId = new HashSet<FuCommission>(0);
	private Set<FuMessage> fuMessages = new HashSet<FuMessage>(0);
	private Set<FuSmsLog> fuSmsLogs = new HashSet<FuSmsLog>(0);
	private Set<FuContinueContract> fuContinueContracts = new HashSet<FuContinueContract>(0);
	private Set<FuManageFee> fuManageFees = new HashSet<FuManageFee>(0);
	private Set<FuMoneyDetail> fuMoneyDetails = new HashSet<FuMoneyDetail>(0);
	private Set<FuPromote> fuPromotesForPromoteId = new HashSet<FuPromote>(0);
	private Set<FuProgram> fuPrograms = new HashSet<FuProgram>(0);
	private Set<FuDrawProfits> fuDrawProfitses = new HashSet<FuDrawProfits>(0);
	private Set<FuBadCredit> fuBadCredits = new HashSet<FuBadCredit>(0);

	// Constructors

	/** default constructor */
	public FuUser() {
	}

	public FuUser(Long id, String accountName, String password, String userName, String remarkName, String cardNumber, Integer isCheckCard, String phone, String email, Integer isBindEmail,
			Integer isMarriage, String liveAddress, Integer maxDegree, String businessType, String positionName, BigDecimal salary, String drawPassword, String cardBeforePic, String cardBehindPic,
			String cardHandPic, BigDecimal accountBalance, BigDecimal accountTotalMoney, BigDecimal matchMoney, BigDecimal safeMoney, BigDecimal freezeMoney, Integer safeLevel, Date registerTime,
			String registerIp, Integer extendPersonNum, Integer borrowPersonNum, Integer visitIp, Integer visitNum, BigDecimal commissionTotal, BigDecimal exchangeMoney, String phoneCode,
			String emailCode, String loginToken, Integer state, Long provinceId, Long cityId, String provinceName, String cityName, Integer gender, FuUser recommend, Date loginTime,
			BigDecimal drawMoney, BigDecimal rechargeMoney, BigDecimal feeTotal, Date phoneCodeTime, Integer hhrLevel, Long hhrParentID, String invitationCode, String userAvatar, String lastLoginIp,
			String introduction, String nickName, Integer isPhoneReg, BigDecimal integral, Integer isAcrossCabin, String weixinCode, Integer hhrType, String ryToken, Integer isTransaction,
			Integer isSale, Integer qidaRank, BigDecimal qidaIntegral, Date nickNameTime, Set<FuAddMargin> fuAddMargins, Set<FuCommission> fuCommissionsForExtendUserId, Set<FuPromote> fuPromotesForPromotedId,
			Set<FuDrawMoney> fuDrawMoneies, Set<FuRecharge> fuRecharges, Set<FuCommission> fuCommissionsForUserId, Set<FuMessage> fuMessages, Set<FuSmsLog> fuSmsLogs,
			Set<FuContinueContract> fuContinueContracts, Set<FuManageFee> fuManageFees, Set<FuMoneyDetail> fuMoneyDetails, Set<FuPromote> fuPromotesForPromoteId, Set<FuProgram> fuPrograms,
			Set<FuDrawProfits> fuDrawProfitses, Set<FuBadCredit> fuBadCredits) {
		super();
		this.id = id;
		this.accountName = accountName;
		this.password = password;
		this.userName = userName;
		this.remarkName = remarkName;
		this.cardNumber = cardNumber;
		this.isCheckCard = isCheckCard;
		this.phone = phone;
		this.email = email;
		this.isBindEmail = isBindEmail;
		this.isMarriage = isMarriage;
		this.liveAddress = liveAddress;
		this.maxDegree = maxDegree;
		this.businessType = businessType;
		this.positionName = positionName;
		this.salary = salary;
		this.drawPassword = drawPassword;
		this.cardBeforePic = cardBeforePic;
		this.cardBehindPic = cardBehindPic;
		this.cardHandPic = cardHandPic;
		this.accountBalance = accountBalance;
		this.accountTotalMoney = accountTotalMoney;
		this.matchMoney = matchMoney;
		this.safeMoney = safeMoney;
		this.freezeMoney = freezeMoney;
		this.safeLevel = safeLevel;
		this.registerTime = registerTime;
		this.registerIp = registerIp;
		this.extendPersonNum = extendPersonNum;
		this.borrowPersonNum = borrowPersonNum;
		this.visitIp = visitIp;
		this.visitNum = visitNum;
		this.commissionTotal = commissionTotal;
		this.exchangeMoney = exchangeMoney;
		this.phoneCode = phoneCode;
		this.emailCode = emailCode;
		this.loginToken = loginToken;
		this.state = state;
		this.provinceId = provinceId;
		this.cityId = cityId;
		this.provinceName = provinceName;
		this.cityName = cityName;
		this.gender = gender;
		this.recommend = recommend;
		this.loginTime = loginTime;
		this.drawMoney = drawMoney;
		this.rechargeMoney = rechargeMoney;
		this.feeTotal = feeTotal;
		this.phoneCodeTime = phoneCodeTime;
		this.hhrLevel = hhrLevel;
		this.hhrParentID = hhrParentID;
		this.invitationCode = invitationCode;
		this.userAvatar = userAvatar;
		this.lastLoginIp = lastLoginIp;
		this.introduction = introduction;
		this.nickName = nickName;
		this.isPhoneReg = isPhoneReg;
		this.integral = integral;
		this.isAcrossCabin = isAcrossCabin;
		this.weixinCode = weixinCode;
		this.hhrType = hhrType;
		this.ryToken = ryToken;
		this.isTransaction = isTransaction;
		this.isSale = isSale;
		this.qidaRank = qidaRank;
		this.qidaIntegral = qidaIntegral;
		this.nickNameTime = nickNameTime;
		this.fuAddMargins = fuAddMargins;
		this.fuCommissionsForExtendUserId = fuCommissionsForExtendUserId;
		this.fuPromotesForPromotedId = fuPromotesForPromotedId;
		this.fuDrawMoneies = fuDrawMoneies;
		this.fuRecharges = fuRecharges;
		this.fuCommissionsForUserId = fuCommissionsForUserId;
		this.fuMessages = fuMessages;
		this.fuSmsLogs = fuSmsLogs;
		this.fuContinueContracts = fuContinueContracts;
		this.fuManageFees = fuManageFees;
		this.fuMoneyDetails = fuMoneyDetails;
		this.fuPromotesForPromoteId = fuPromotesForPromoteId;
		this.fuPrograms = fuPrograms;
		this.fuDrawProfitses = fuDrawProfitses;
		this.fuBadCredits = fuBadCredits;
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

	@Column(name = "account_name")
	public String getAccountName() {
		return this.accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	@Column(name = "password")
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "user_name")
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "card_number")
	public String getCardNumber() {
		return this.cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	@Column(name = "is_check_card")
	public Integer getIsCheckCard() {
		return this.isCheckCard;
	}

	public void setIsCheckCard(Integer isCheckCard) {
		this.isCheckCard = isCheckCard;
	}

	@Column(name = "phone")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "is_bind_email")
	public Integer getIsBindEmail() {
		return this.isBindEmail;
	}

	public void setIsBindEmail(Integer isBindEmail) {
		this.isBindEmail = isBindEmail;
	}

	@Column(name = "is_marriage")
	public Integer getIsMarriage() {
		return this.isMarriage;
	}

	public void setIsMarriage(Integer isMarriage) {
		this.isMarriage = isMarriage;
	}

	@Column(name = "live_address")
	public String getLiveAddress() {
		return this.liveAddress;
	}

	public void setLiveAddress(String liveAddress) {
		this.liveAddress = liveAddress;
	}

	@Column(name = "max_degree")
	public Integer getMaxDegree() {
		return this.maxDegree;
	}

	public void setMaxDegree(Integer maxDegree) {
		this.maxDegree = maxDegree;
	}

	@Column(name = "business_type")
	public String getBusinessType() {
		return this.businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	@Column(name = "position_name")
	public String getPositionName() {
		return this.positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	@Column(name = "salary")
	public BigDecimal getSalary() {
		return this.salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	@Column(name = "draw_password")
	public String getDrawPassword() {
		return this.drawPassword;
	}

	public void setDrawPassword(String drawPassword) {
		this.drawPassword = drawPassword;
	}

	@Column(name = "card_before_pic")
	public String getCardBeforePic() {
		return this.cardBeforePic;
	}

	public void setCardBeforePic(String cardBeforePic) {
		this.cardBeforePic = cardBeforePic;
	}

	@Column(name = "card_behind_pic")
	public String getCardBehindPic() {
		return this.cardBehindPic;
	}

	public void setCardBehindPic(String cardBehindPic) {
		this.cardBehindPic = cardBehindPic;
	}

	@Column(name = "card_hand_pic")
	public String getCardHandPic() {
		return this.cardHandPic;
	}

	public void setCardHandPic(String cardHandPic) {
		this.cardHandPic = cardHandPic;
	}

	@Column(name = "account_balance")
	public BigDecimal getAccountBalance() {
		return this.accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	@Column(name = "account_total_money")
	public BigDecimal getAccountTotalMoney() {
		return this.accountTotalMoney;
	}

	public void setAccountTotalMoney(BigDecimal accountTotalMoney) {
		this.accountTotalMoney = accountTotalMoney;
	}

	@Column(name = "match_money")
	public BigDecimal getMatchMoney() {
		return this.matchMoney;
	}

	public void setMatchMoney(BigDecimal matchMoney) {
		this.matchMoney = matchMoney;
	}

	@Column(name = "safe_money")
	public BigDecimal getSafeMoney() {
		return this.safeMoney;
	}

	public void setSafeMoney(BigDecimal safeMoney) {
		this.safeMoney = safeMoney;
	}

	@Column(name = "freeze_money")
	public BigDecimal getFreezeMoney() {
		return this.freezeMoney;
	}

	public void setFreezeMoney(BigDecimal freezeMoney) {
		this.freezeMoney = freezeMoney;
	}

	@Column(name = "safe_level")
	public Integer getSafeLevel() {
		return this.safeLevel;
	}

	public void setSafeLevel(Integer safeLevel) {
		this.safeLevel = safeLevel;
	}

	@Column(name = "register_time", length = 19)
	public Date getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	@Column(name = "register_ip")
	public String getRegisterIp() {
		return registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}

	@Column(name = "extend_person_num")
	public Integer getExtendPersonNum() {
		return this.extendPersonNum;
	}

	public void setExtendPersonNum(Integer extendPersonNum) {
		this.extendPersonNum = extendPersonNum;
	}

	@Column(name = "commission_total")
	public BigDecimal getCommissionTotal() {
		return this.commissionTotal;
	}

	public void setCommissionTotal(BigDecimal commissionTotal) {
		this.commissionTotal = commissionTotal;
	}

	@Column(name = "exchange_money")
	public BigDecimal getExchangeMoney() {
		return this.exchangeMoney;
	}

	public void setExchangeMoney(BigDecimal exchangeMoney) {
		this.exchangeMoney = exchangeMoney;
	}

	@Column(name = "phone_code")
	public String getPhoneCode() {
		return this.phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	@Column(name = "email_code")
	public String getEmailCode() {
		return this.emailCode;
	}

	public void setEmailCode(String emailCode) {
		this.emailCode = emailCode;
	}

	@Column(name = "login_token")
	public String getLoginToken() {
		return this.loginToken;
	}

	public void setLoginToken(String loginToken) {
		this.loginToken = loginToken;
	}

	@Column(name = "state")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "province_id")
	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	@Column(name = "city_id")
	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	@Column(name = "province_name")
	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	@Column(name = "city_name")
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Column(name = "borrow_person_num")
	public Integer getBorrowPersonNum() {
		return borrowPersonNum;
	}

	public void setBorrowPersonNum(Integer borrowPersonNum) {
		this.borrowPersonNum = borrowPersonNum;
	}

	@Column(name = "visit_ip")
	public Integer getVisitIp() {
		return visitIp;
	}

	public void setVisitIp(Integer visitIp) {
		this.visitIp = visitIp;
	}

	@Column(name = "visit_num")
	public Integer getVisitNum() {
		return visitNum;
	}

	public void setVisitNum(Integer visitNum) {
		this.visitNum = visitNum;
	}

	@Column(name = "gender")
	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "recommend_id")
	public FuUser getRecommend() {
		return recommend;
	}

	public void setRecommend(FuUser recommend) {
		this.recommend = recommend;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fuUser")
	public Set<FuAddMargin> getFuAddMargins() {
		return this.fuAddMargins;
	}

	public void setFuAddMargins(Set<FuAddMargin> fuAddMargins) {
		this.fuAddMargins = fuAddMargins;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fuUserByExtendUserId")
	public Set<FuCommission> getFuCommissionsForExtendUserId() {
		return this.fuCommissionsForExtendUserId;
	}

	public void setFuCommissionsForExtendUserId(Set<FuCommission> fuCommissionsForExtendUserId) {
		this.fuCommissionsForExtendUserId = fuCommissionsForExtendUserId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fuUserByPromotedId")
	public Set<FuPromote> getFuPromotesForPromotedId() {
		return this.fuPromotesForPromotedId;
	}

	public void setFuPromotesForPromotedId(Set<FuPromote> fuPromotesForPromotedId) {
		this.fuPromotesForPromotedId = fuPromotesForPromotedId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fuUser")
	public Set<FuDrawMoney> getFuDrawMoneies() {
		return this.fuDrawMoneies;
	}

	public void setFuDrawMoneies(Set<FuDrawMoney> fuDrawMoneies) {
		this.fuDrawMoneies = fuDrawMoneies;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fuUser")
	public Set<FuRecharge> getFuRecharges() {
		return this.fuRecharges;
	}

	public void setFuRecharges(Set<FuRecharge> fuRecharges) {
		this.fuRecharges = fuRecharges;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fuUserByUserId")
	public Set<FuCommission> getFuCommissionsForUserId() {
		return this.fuCommissionsForUserId;
	}

	public void setFuCommissionsForUserId(Set<FuCommission> fuCommissionsForUserId) {
		this.fuCommissionsForUserId = fuCommissionsForUserId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fuUser")
	public Set<FuMessage> getFuMessages() {
		return this.fuMessages;
	}

	public void setFuMessages(Set<FuMessage> fuMessages) {
		this.fuMessages = fuMessages;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fuUser")
	public Set<FuSmsLog> getFuSmsLogs() {
		return this.fuSmsLogs;
	}

	public void setFuSmsLogs(Set<FuSmsLog> fuSmsLogs) {
		this.fuSmsLogs = fuSmsLogs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fuUser")
	public Set<FuContinueContract> getFuContinueContracts() {
		return this.fuContinueContracts;
	}

	public void setFuContinueContracts(Set<FuContinueContract> fuContinueContracts) {
		this.fuContinueContracts = fuContinueContracts;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fuUser")
	public Set<FuManageFee> getFuManageFees() {
		return this.fuManageFees;
	}

	public void setFuManageFees(Set<FuManageFee> fuManageFees) {
		this.fuManageFees = fuManageFees;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fuUser")
	public Set<FuMoneyDetail> getFuMoneyDetails() {
		return this.fuMoneyDetails;
	}

	public void setFuMoneyDetails(Set<FuMoneyDetail> fuMoneyDetails) {
		this.fuMoneyDetails = fuMoneyDetails;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fuUserByPromoteId")
	public Set<FuPromote> getFuPromotesForPromoteId() {
		return this.fuPromotesForPromoteId;
	}

	public void setFuPromotesForPromoteId(Set<FuPromote> fuPromotesForPromoteId) {
		this.fuPromotesForPromoteId = fuPromotesForPromoteId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fuUser")
	public Set<FuProgram> getFuPrograms() {
		return this.fuPrograms;
	}

	public void setFuPrograms(Set<FuProgram> fuPrograms) {
		this.fuPrograms = fuPrograms;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fuUser")
	public Set<FuDrawProfits> getFuDrawProfitses() {
		return this.fuDrawProfitses;
	}

	public void setFuDrawProfitses(Set<FuDrawProfits> fuDrawProfitses) {
		this.fuDrawProfitses = fuDrawProfitses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fuUser")
	public Set<FuBadCredit> getFuBadCredits() {
		return this.fuBadCredits;
	}

	public void setFuBadCredits(Set<FuBadCredit> fuBadCredits) {
		this.fuBadCredits = fuBadCredits;
	}

	@Column(name = "login_time")
	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	@Column(name = "draw_money")
	public BigDecimal getDrawMoney() {
		return drawMoney;
	}

	public void setDrawMoney(BigDecimal drawMoney) {
		this.drawMoney = drawMoney;
	}

	@Column(name = "recharge_money")
	public BigDecimal getRechargeMoney() {
		return rechargeMoney;
	}

	public void setRechargeMoney(BigDecimal rechargeMoney) {
		this.rechargeMoney = rechargeMoney;
	}

	@Column(name = "fee_total")
	public BigDecimal getFeeTotal() {
		return feeTotal;
	}

	public void setFeeTotal(BigDecimal feeTotal) {
		this.feeTotal = feeTotal;
	}

	@Column(name = "phone_code_time")
	public Date getPhoneCodeTime() {
		return phoneCodeTime;
	}

	public void setPhoneCodeTime(Date phoneCodeTime) {
		this.phoneCodeTime = phoneCodeTime;
	}

	@Column(name = "hhr_level")
	public Integer getHhrLevel() {
		return hhrLevel;
	}

	public void setHhrLevel(Integer hhrLevel) {
		this.hhrLevel = hhrLevel;
	}

	@Column(name = "hhr_parentID")
	public Long getHhrParentID() {
		return hhrParentID;
	}

	public void setHhrParentID(Long hhrParentID) {
		this.hhrParentID = hhrParentID;
	}

	@Column(name = "invitation_code")
	public String getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}

	@Column(name = "user_avatar")
	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

	@Column(name = "last_login_ip")
	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	@Column(name = "introduction")
	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	@Column(name = "nick_name")
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Column(name = "isPhone_reg")
	public Integer getIsPhoneReg() {
		return isPhoneReg;
	}

	public void setIsPhoneReg(Integer isPhoneReg) {
		this.isPhoneReg = isPhoneReg;
	}

	@Column(name = "integral")
	public BigDecimal getIntegral() {
		return integral;
	}

	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}

	@Column(name = "isAcrossCabin")
	public Integer getIsAcrossCabin() {
		return isAcrossCabin;
	}

	public void setIsAcrossCabin(Integer isAcrossCabin) {
		this.isAcrossCabin = isAcrossCabin;
	}

	@Column(name = "weixin_code")
	public String getWeixinCode() {
		return weixinCode;
	}

	public void setWeixinCode(String weixinCode) {
		this.weixinCode = weixinCode;
	}

	@Column(name = "hhr_type")
	public Integer getHhrType() {
		return hhrType;
	}

	public void setHhrType(Integer hhrType) {
		this.hhrType = hhrType;
	}

	@Column(name = "remark_name")
	public String getRemarkName() {
		return remarkName;
	}

	public void setRemarkName(String remarkName) {
		this.remarkName = remarkName;
	}

	@Column(name = "ry_token")
	public String getRyToken() {
		return ryToken;
	}

	public void setRyToken(String ryToken) {
		this.ryToken = ryToken;
	}

	@Column(name = "is_transaction")
	public Integer getIsTransaction() {
		return isTransaction;
	}

	public void setIsTransaction(Integer isTransaction) {
		this.isTransaction = isTransaction;
	}

	@Column(name = "is_sale")
	public Integer getIsSale() {
		return isSale;
	}

	public void setIsSale(Integer isSale) {
		this.isSale = isSale;
	}

	@Column(name = "qida_rank")
	public Integer getQidaRank() {
		return qidaRank;
	}

	public void setQidaRank(Integer qidaRank) {
		this.qidaRank = qidaRank;
	}

	@Column(name = "qida_integral")
	public BigDecimal getQidaIntegral() {
		return qidaIntegral;
	}

	public void setQidaIntegral(BigDecimal qidaIntegral) {
		this.qidaIntegral = qidaIntegral;
	}

	@Column(name = "nick_name_time")
	public Date getNickNameTime() {
		return nickNameTime;
	}

	public void setNickNameTime(Date nickNameTime) {
		this.nickNameTime = nickNameTime;
	}
	
	

}