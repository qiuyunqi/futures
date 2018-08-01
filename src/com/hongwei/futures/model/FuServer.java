package com.hongwei.futures.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * FuServer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "fu_server")
public class FuServer implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3426420153835348730L;
	private Long id;
	private Integer usertypeId;
	private String serverName;
	private String serverRealName;
	private String serverIp;
	private String serverPriority;
	private BigDecimal serverMoney;
	private Integer clearingId;
	private Integer openuserId;
	private String portNumber;
	private Integer dayId1;
	private Integer dayId2;
	private Integer dayId3;
	private Integer dayId4;
	private Integer dayId5;
	private Integer dayId6;
	private Integer dayId7;
	private Integer dayId8;
	private Integer dayId9;
	private Integer dayId10;
	private Integer monthId1;
	private Integer monthId2;
	private Integer monthId3;
	private Integer monthId4;
	private Integer monthId5;
	private Integer monthId6;
	private Integer monthId7;
	private Integer monthId8;
	private Integer monthId9;
	private Integer monthId10;
	private Integer nowNumber;
	private Integer startNumber;
	private Integer isDel;
	private String dbIp;
	private String dbPort;
	private String dbName;
	private String dbUsername;
	private String dbPassword;
	private Integer isSimTrader;
	private Integer clientType;
	private String futureCompany;
	private String dbReadUserName;
	private String dbReadPassWord;
	// Constructors

	/** default constructor */
	public FuServer() {
	}

	public FuServer(Long id, Integer usertypeId, String serverName,
			String serverRealName, String serverIp, String serverPriority,
			BigDecimal serverMoney, Integer clearingId, Integer openuserId,
			String portNumber, Integer dayId1, Integer dayId2, Integer dayId3,
			Integer dayId4, Integer dayId5, Integer dayId6, Integer dayId7,
			Integer dayId8, Integer dayId9, Integer dayId10, Integer monthId1,
			Integer monthId2, Integer monthId3, Integer monthId4,
			Integer monthId5, Integer monthId6, Integer monthId7,
			Integer monthId8, Integer monthId9, Integer monthId10,
			Integer nowNumber, Integer startNumber, Integer isDel, String dbIp,
			String dbPort, String dbName, String dbUsername, String dbPassword,
			Integer isSimTrader, Integer clientType, String futureCompany,
			String dbReadUserName, String dbReadPassWord) {
		super();
		this.id = id;
		this.usertypeId = usertypeId;
		this.serverName = serverName;
		this.serverRealName = serverRealName;
		this.serverIp = serverIp;
		this.serverPriority = serverPriority;
		this.serverMoney = serverMoney;
		this.clearingId = clearingId;
		this.openuserId = openuserId;
		this.portNumber = portNumber;
		this.dayId1 = dayId1;
		this.dayId2 = dayId2;
		this.dayId3 = dayId3;
		this.dayId4 = dayId4;
		this.dayId5 = dayId5;
		this.dayId6 = dayId6;
		this.dayId7 = dayId7;
		this.dayId8 = dayId8;
		this.dayId9 = dayId9;
		this.dayId10 = dayId10;
		this.monthId1 = monthId1;
		this.monthId2 = monthId2;
		this.monthId3 = monthId3;
		this.monthId4 = monthId4;
		this.monthId5 = monthId5;
		this.monthId6 = monthId6;
		this.monthId7 = monthId7;
		this.monthId8 = monthId8;
		this.monthId9 = monthId9;
		this.monthId10 = monthId10;
		this.nowNumber = nowNumber;
		this.startNumber = startNumber;
		this.isDel = isDel;
		this.dbIp = dbIp;
		this.dbPort = dbPort;
		this.dbName = dbName;
		this.dbUsername = dbUsername;
		this.dbPassword = dbPassword;
		this.isSimTrader = isSimTrader;
		this.clientType = clientType;
		this.futureCompany = futureCompany;
		this.dbReadUserName = dbReadUserName;
		this.dbReadPassWord = dbReadPassWord;
	}

	/** full constructor */
	

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

	@Column(name = "usertype_id")
	public Integer getUsertypeId() {
		return this.usertypeId;
	}

	public void setUsertypeId(Integer usertypeId) {
		this.usertypeId = usertypeId;
	}

	@Column(name = "server_name")
	public String getServerName() {
		return this.serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	
	@Column(name = "server_realname")
	public String getServerRealName() {
		return serverRealName;
	}

	public void setServerRealName(String serverRealName) {
		this.serverRealName = serverRealName;
	}

	@Column(name = "server_ip")
	public String getServerIp() {
		return this.serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	@Column(name = "server_priority")
	public String getServerPriority() {
		return this.serverPriority;
	}

	public void setServerPriority(String serverPriority) {
		this.serverPriority = serverPriority;
	}

	@Column(name = "server_money")
	public BigDecimal getServerMoney() {
		return this.serverMoney;
	}

	public void setServerMoney(BigDecimal serverMoney) {
		this.serverMoney = serverMoney;
	}

	@Column(name = "clearing_id")
	public Integer getClearingId() {
		return this.clearingId;
	}

	public void setClearingId(Integer clearingId) {
		this.clearingId = clearingId;
	}

	@Column(name = "openuser_id")
	public Integer getOpenuserId() {
		return this.openuserId;
	}

	public void setOpenuserId(Integer openuserId) {
		this.openuserId = openuserId;
	}

	@Column(name = "day_id_1")
	public Integer getDayId1() {
		return this.dayId1;
	}

	public void setDayId1(Integer dayId1) {
		this.dayId1 = dayId1;
	}

	@Column(name = "day_id_2")
	public Integer getDayId2() {
		return this.dayId2;
	}

	public void setDayId2(Integer dayId2) {
		this.dayId2 = dayId2;
	}

	@Column(name = "day_id_3")
	public Integer getDayId3() {
		return this.dayId3;
	}

	public void setDayId3(Integer dayId3) {
		this.dayId3 = dayId3;
	}

	@Column(name = "day_id_4")
	public Integer getDayId4() {
		return this.dayId4;
	}

	public void setDayId4(Integer dayId4) {
		this.dayId4 = dayId4;
	}

	@Column(name = "day_id_5")
	public Integer getDayId5() {
		return this.dayId5;
	}

	public void setDayId5(Integer dayId5) {
		this.dayId5 = dayId5;
	}

	@Column(name = "day_id_6")
	public Integer getDayId6() {
		return this.dayId6;
	}

	public void setDayId6(Integer dayId6) {
		this.dayId6 = dayId6;
	}

	@Column(name = "day_id_7")
	public Integer getDayId7() {
		return this.dayId7;
	}

	public void setDayId7(Integer dayId7) {
		this.dayId7 = dayId7;
	}

	@Column(name = "day_id_8")
	public Integer getDayId8() {
		return this.dayId8;
	}

	public void setDayId8(Integer dayId8) {
		this.dayId8 = dayId8;
	}

	@Column(name = "day_id_9")
	public Integer getDayId9() {
		return this.dayId9;
	}

	public void setDayId9(Integer dayId9) {
		this.dayId9 = dayId9;
	}

	@Column(name = "day_id_10")
	public Integer getDayId10() {
		return this.dayId10;
	}

	public void setDayId10(Integer dayId10) {
		this.dayId10 = dayId10;
	}

	@Column(name = "month_id_1")
	public Integer getMonthId1() {
		return this.monthId1;
	}

	public void setMonthId1(Integer monthId1) {
		this.monthId1 = monthId1;
	}

	@Column(name = "month_id_2")
	public Integer getMonthId2() {
		return this.monthId2;
	}

	public void setMonthId2(Integer monthId2) {
		this.monthId2 = monthId2;
	}

	@Column(name = "month_id_3")
	public Integer getMonthId3() {
		return this.monthId3;
	}

	public void setMonthId3(Integer monthId3) {
		this.monthId3 = monthId3;
	}

	@Column(name = "month_id_4")
	public Integer getMonthId4() {
		return this.monthId4;
	}

	public void setMonthId4(Integer monthId4) {
		this.monthId4 = monthId4;
	}

	@Column(name = "month_id_5")
	public Integer getMonthId5() {
		return this.monthId5;
	}

	public void setMonthId5(Integer monthId5) {
		this.monthId5 = monthId5;
	}

	@Column(name = "month_id_6")
	public Integer getMonthId6() {
		return this.monthId6;
	}

	public void setMonthId6(Integer monthId6) {
		this.monthId6 = monthId6;
	}

	@Column(name = "month_id_7")
	public Integer getMonthId7() {
		return this.monthId7;
	}

	public void setMonthId7(Integer monthId7) {
		this.monthId7 = monthId7;
	}

	@Column(name = "month_id_8")
	public Integer getMonthId8() {
		return this.monthId8;
	}

	public void setMonthId8(Integer monthId8) {
		this.monthId8 = monthId8;
	}

	@Column(name = "month_id_9")
	public Integer getMonthId9() {
		return this.monthId9;
	}

	public void setMonthId9(Integer monthId9) {
		this.monthId9 = monthId9;
	}

	@Column(name = "month_id_10")
	public Integer getMonthId10() {
		return this.monthId10;
	}

	public void setMonthId10(Integer monthId10) {
		this.monthId10 = monthId10;
	}

	@Column(name = "now_number")
	public Integer getNowNumber() {
		return this.nowNumber;
	}

	public void setNowNumber(Integer nowNumber) {
		this.nowNumber = nowNumber;
	}

	@Column(name = "start_number")
	public Integer getStartNumber() {
		return this.startNumber;
	}

	public void setStartNumber(Integer startNumber) {
		this.startNumber = startNumber;
	}

	@Column(name = "is_del")
	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public void setDbIp(String dbIp) {
		this.dbIp = dbIp;
	}

	@Column(name="db_ip")
	public String getDbIp() {
		return dbIp;
	}

	public void setDbPort(String dbPort) {
		this.dbPort = dbPort;
	}

	@Column(name="db_port")
	public String getDbPort() {
		return dbPort;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	@Column(name="db_name")
	public String getDbName() {
		return dbName;
	}

	public void setDbUsername(String dbUsername) {
		this.dbUsername = dbUsername;
	}

	@Column(name="db_username")
	public String getDbUsername() {
		return dbUsername;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	@Column(name="db_password")
	public String getDbPassword() {
		return dbPassword;
	}
	@Column(name = "port_number")
	public String getPortNumber() {
		return this.portNumber;
	}

	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}

	@Column(name = "isSimTrader")
	public Integer getIsSimTrader() {
		return isSimTrader;
	}

	public void setIsSimTrader(Integer isSimTrader) {
		this.isSimTrader = isSimTrader;
	}

	@Column(name = "clientType")
	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	@Column(name = "futureCompany")
	public String getFutureCompany() {
		return futureCompany;
	}

	public void setFutureCompany(String futureCompany) {
		this.futureCompany = futureCompany;
	}

	@Column(name = "db_read_username")
	public String getDbReadUserName() {
		return dbReadUserName;
	}

	public void setDbReadUserName(String dbReadUserName) {
		this.dbReadUserName = dbReadUserName;
	}

	@Column(name = "db_read_password")
	public String getDbReadPassWord() {
		return dbReadPassWord;
	}

	public void setDbReadPassWord(String dbReadPassWord) {
		this.dbReadPassWord = dbReadPassWord;
	}
}