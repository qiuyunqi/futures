package com.hongwei.futures.web.action.admin_op_server;

import java.math.BigDecimal;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuServer;
import com.hongwei.futures.service.FuServerService;
import com.hongwei.futures.util.StringUtil;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminOpServerAction extends BaseAction {
	@Autowired
	private FuServerService fuServerService;

	private Long id;
	private String serverName;
	private String serverRealName;
	private String serverIp;
	private String serverPriority;
	private BigDecimal serverMoney;
	private Integer clearingId;
	private Integer usertypeId;
	private Integer openuserId;
	private Integer nowNumber;
	private Integer startNumber;
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
	private String dbIp;
	private String dbPort;
	private String dbName;
	private String dbUsername;
	private String dbPassword;
	private String portNumber;
	private Integer isSimTrader;
	private Integer clientType;
	private String futureCompany;
	private String dbReadUserName;
	private String dbReadPassWord;

	/**
	 * 添加服务器
	 * 
	 * @return
	 */
	@Action("newServerAjax")
	public String addServer() {
		return SUCCESS;
	}

	/**
	 * 保存服务器
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveServerAjax")
	public String saveServer() {
		try {
			FuServer fuServer = new FuServer();
			if (StringUtil.isBlank(serverName)) {
				write("-2");
				return null;
			}
			if (StringUtil.isBlank(serverPriority)) {
				write("-3");// 请输入优先级
				return null;
			}
			if (StringUtil.isBlank(serverIp)) {
				write("-4");// 请输入Ip地址
				return null;
			}
			if (StringUtil.isBlank(portNumber)) {
				write("-18");// 请输入端口号
				return null;
			}
			if (StringUtil.isBlank(dbIp)) {
				write("-12");
				return null;
			}
			if (StringUtil.isBlank(dbPort)) {
				write("-13");
				return null;
			}
			if (StringUtil.isBlank(dbName)) {
				write("-14");
				return null;
			}
			if (StringUtil.isBlank(dbUsername)) {
				write("-15");
				return null;
			}
			if (StringUtil.isBlank(dbPassword)) {
				write("-16");
				return null;
			}
			if (StringUtil.isBlank(dbReadUserName)) {
				write("-19");
				return null;
			}
			if (StringUtil.isBlank(dbReadPassWord)) {
				write("-20");
				return null;
			}
			if (StringUtil.isBlank(futureCompany)) {
				write("-7");
				return null;
			}
			if (serverMoney == null) {
				write("-5");// 请输入服务器金额
				return null;
			}
			if (startNumber == null) {
				write("-6");// 请输入开始序列号
				return null;
			}

			if (clearingId == null) {
				write("-8");// 请输入结算组ID
				return null;
			}

			if (openuserId == null) {
				write("-9");// 请输入开户组ID
				return null;
			}
			if (dayId1 == null || dayId2 == null || dayId3 == null
					|| dayId4 == null || dayId5 == null || dayId6 == null
					|| dayId7 == null || dayId8 == null || dayId9 == null
					|| dayId10 == null) {
				write("-10");// 请输完整日配Id
				return null;

			}
			if (monthId1 == null || monthId2 == null || monthId3 == null
					|| monthId4 == null || monthId5 == null || monthId6 == null
					|| monthId7 == null || monthId8 == null || monthId9 == null
					|| monthId10 == null) {
				write("-11");// 请输完整月配Id
				return null;

			}
			fuServer.setUsertypeId(usertypeId);
			fuServer.setClearingId(clearingId);
			fuServer.setOpenuserId(openuserId);
			fuServer.setServerName(serverName);
			fuServer.setServerRealName(serverRealName);
			fuServer.setStartNumber(startNumber);
			fuServer.setServerPriority(serverPriority);
			fuServer.setServerIp(serverIp);
			fuServer.setServerMoney(serverMoney);
			fuServer.setNowNumber(0);
			fuServer.setIsDel(1);
			fuServer.setPortNumber(portNumber);
			fuServer.setIsSimTrader(isSimTrader);
			fuServer.setDayId1(dayId1);
			fuServer.setDayId2(dayId2);
			fuServer.setDayId3(dayId3);
			fuServer.setDayId4(dayId4);
			fuServer.setDayId5(dayId5);
			fuServer.setDayId6(dayId6);
			fuServer.setDayId7(dayId7);
			fuServer.setDayId8(dayId8);
			fuServer.setDayId9(dayId9);
			fuServer.setDayId10(dayId10);
			fuServer.setMonthId1(monthId1);
			fuServer.setMonthId2(monthId2);
			fuServer.setMonthId3(monthId3);
			fuServer.setMonthId4(monthId4);
			fuServer.setMonthId5(monthId5);
			fuServer.setMonthId6(monthId6);
			fuServer.setMonthId7(monthId7);
			fuServer.setMonthId8(monthId8);
			fuServer.setMonthId9(monthId9);
			fuServer.setMonthId10(monthId10);
			fuServer.setDbIp(dbIp);
			fuServer.setDbPort(dbPort);
			fuServer.setDbName(dbName);
			fuServer.setDbUsername(dbUsername);
			fuServer.setDbPassword(dbPassword);
			fuServer.setDbReadUserName(dbReadUserName);
			fuServer.setDbReadPassWord(dbReadPassWord);
			fuServer.setClientType(clientType);
			fuServer.setFutureCompany(futureCompany);
			// Connection con=JDBCUtil.getConnection(fuServer);
			// if(con==null){
			// write("-17");
			// return null;
			// } else {
			// JDBCUtil.release(null, null, con);
			// }
			fuServerService.save(fuServer);
			write("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除服务器
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("delServerAjax")
	public String delServer() {
		try {
			FuServer fu = fuServerService.get(id);
			fu.setIsDel(0);
			fuServerService.updateFuserver(fu);
			write("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据Id显示服务器内容
	 * 
	 * @return
	 */
	@Action("showUpadateServer")
	public String showUpadateServer() {
		try {
			FuServer fu = fuServerService.get(id);
			this.getActionContext().put("fuServer", fu);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 修改服务器
	 * 
	 * @return
	 * @throws Exception
	 */

	@Action("updateServer")
	public String updateServer() {
		try {
			if (id != null) {
				if (StringUtil.isBlank(serverName)) {
					write("-2");
					return null;
				}
				if (StringUtil.isBlank(serverPriority)) {
					write("-3");// 请输入优先级
					return null;
				}
				if (StringUtil.isBlank(serverIp)) {
					write("-4");// 请输入Ip地址
					return null;
				}
				if (StringUtil.isBlank(portNumber)) {
					write("-19");// 请输入端口号
					return null;
				}
				if (StringUtil.isBlank(dbPassword)) {
					write("-18");
					return null;
				}
				if (StringUtil.isBlank(dbIp)) {
					write("-12");
					return null;
				}
				if (StringUtil.isBlank(dbPort)) {
					write("-13");
					return null;
				}
				if (StringUtil.isBlank(dbName)) {
					write("-14");
					return null;
				}
				if (StringUtil.isBlank(dbUsername)) {
					write("-15");
					return null;
				}
				if (StringUtil.isBlank(dbPassword)) {
					write("-16");
					return null;
				}
				if (StringUtil.isBlank(dbReadUserName)) {
					write("-20");
					return null;
				}
				if (StringUtil.isBlank(dbReadPassWord)) {
					write("-21");
					return null;
				}
				if (StringUtil.isBlank(futureCompany)) {
					write("-7");
					return null;
				}
				if (serverMoney == null) {
					write("-5");// 请输入服务器金额
					return null;
				}
				if (startNumber == null) {
					write("-6");// 请输入开始序列号
					return null;
				}
				if (clearingId == null) {
					write("-8");// 请输入结算组ID
					return null;
				}

				if (openuserId == null) {
					write("-9");// 请输入开户组ID
					return null;
				}
				if (dayId1 == null || dayId2 == null || dayId3 == null
						|| dayId4 == null || dayId5 == null || dayId6 == null
						|| dayId7 == null || dayId8 == null || dayId9 == null
						|| dayId10 == null) {
					write("-10");// 请输完整日配Id
					return null;

				}
				if (monthId1 == null || monthId2 == null || monthId3 == null
						|| monthId4 == null || monthId5 == null
						|| monthId6 == null || monthId7 == null
						|| monthId8 == null || monthId9 == null
						|| monthId10 == null) {
					write("-11");// 请输完整月配Id
					return null;

				}
				FuServer fuServer = fuServerService.get(id);
				fuServer.setClearingId(clearingId);
				fuServer.setOpenuserId(openuserId);
				fuServer.setServerName(serverName);
				fuServer.setServerRealName(serverRealName);
				fuServer.setStartNumber(startNumber);
				fuServer.setServerPriority(serverPriority);
				fuServer.setServerIp(serverIp);
				fuServer.setPortNumber(portNumber);
				fuServer.setUsertypeId(usertypeId);
				fuServer.setServerMoney(serverMoney);
				fuServer.setIsSimTrader(isSimTrader);
				fuServer.setDayId1(dayId1);
				fuServer.setDayId2(dayId2);
				fuServer.setDayId3(dayId3);
				fuServer.setDayId4(dayId4);
				fuServer.setDayId5(dayId5);
				fuServer.setDayId6(dayId6);
				fuServer.setDayId7(dayId7);
				fuServer.setDayId8(dayId8);
				fuServer.setDayId9(dayId9);
				fuServer.setDayId10(dayId10);
				fuServer.setMonthId1(monthId1);
				fuServer.setMonthId2(monthId2);
				fuServer.setMonthId3(monthId3);
				fuServer.setMonthId4(monthId4);
				fuServer.setMonthId5(monthId5);
				fuServer.setMonthId6(monthId6);
				fuServer.setMonthId7(monthId7);
				fuServer.setMonthId8(monthId8);
				fuServer.setMonthId9(monthId9);
				fuServer.setMonthId10(monthId10);
				fuServer.setDbIp(dbIp);
				fuServer.setDbPort(dbPort);
				fuServer.setDbName(dbName);
				fuServer.setDbUsername(dbUsername);
				fuServer.setDbPassword(dbPassword);
				fuServer.setDbReadUserName(dbReadUserName);
				fuServer.setDbReadPassWord(dbReadPassWord);
				fuServer.setClientType(clientType);
				fuServer.setFutureCompany(futureCompany);
				// Connection con=JDBCUtil.getConnection(fuServer);
				// if(con==null){
				// write("-17");
				// return null;
				// } else {
				// JDBCUtil.release(null, null, con);
				// }
				fuServerService.updateFuserver(fuServer);
				write("1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getServerRealName() {
		return serverRealName;
	}

	public void setServerRealName(String serverRealName) {
		this.serverRealName = serverRealName;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public String getServerPriority() {
		return serverPriority;
	}

	public void setServerPriority(String serverPriority) {
		this.serverPriority = serverPriority;
	}

	public FuServerService getFuServerService() {
		return fuServerService;
	}

	public void setFuServerService(FuServerService fuServerService) {
		this.fuServerService = fuServerService;
	}

	public BigDecimal getServerMoney() {
		return serverMoney;
	}

	public void setServerMoney(BigDecimal serverMoney) {
		this.serverMoney = serverMoney;
	}

	public Integer getClearingId() {
		return clearingId;
	}

	public void setClearingId(Integer clearingId) {
		this.clearingId = clearingId;
	}

	public Integer getOpenuserId() {
		return openuserId;
	}

	public void setOpenuserId(Integer openuserId) {
		this.openuserId = openuserId;
	}

	public Integer getNowNumber() {
		return nowNumber;
	}

	public void setNowNumber(Integer nowNumber) {
		this.nowNumber = nowNumber;
	}

	public Integer getStartNumber() {
		return startNumber;
	}

	public void setStartNumber(Integer startNumber) {
		this.startNumber = startNumber;
	}

	public Integer getDayId1() {
		return dayId1;
	}

	public void setDayId1(Integer dayId1) {
		this.dayId1 = dayId1;
	}

	public Integer getDayId2() {
		return dayId2;
	}

	public void setDayId2(Integer dayId2) {
		this.dayId2 = dayId2;
	}

	public Integer getDayId3() {
		return dayId3;
	}

	public void setDayId3(Integer dayId3) {
		this.dayId3 = dayId3;
	}

	public Integer getDayId4() {
		return dayId4;
	}

	public void setDayId4(Integer dayId4) {
		this.dayId4 = dayId4;
	}

	public Integer getDayId5() {
		return dayId5;
	}

	public void setDayId5(Integer dayId5) {
		this.dayId5 = dayId5;
	}

	public Integer getDayId6() {
		return dayId6;
	}

	public void setDayId6(Integer dayId6) {
		this.dayId6 = dayId6;
	}

	public Integer getDayId7() {
		return dayId7;
	}

	public void setDayId7(Integer dayId7) {
		this.dayId7 = dayId7;
	}

	public Integer getDayId8() {
		return dayId8;
	}

	public void setDayId8(Integer dayId8) {
		this.dayId8 = dayId8;
	}

	public Integer getDayId9() {
		return dayId9;
	}

	public void setDayId9(Integer dayId9) {
		this.dayId9 = dayId9;
	}

	public Integer getDayId10() {
		return dayId10;
	}

	public void setDayId10(Integer dayId10) {
		this.dayId10 = dayId10;
	}

	public Integer getMonthId1() {
		return monthId1;
	}

	public void setMonthId1(Integer monthId1) {
		this.monthId1 = monthId1;
	}

	public Integer getMonthId2() {
		return monthId2;
	}

	public void setMonthId2(Integer monthId2) {
		this.monthId2 = monthId2;
	}

	public Integer getMonthId3() {
		return monthId3;
	}

	public void setMonthId3(Integer monthId3) {
		this.monthId3 = monthId3;
	}

	public Integer getMonthId4() {
		return monthId4;
	}

	public void setMonthId4(Integer monthId4) {
		this.monthId4 = monthId4;
	}

	public Integer getMonthId5() {
		return monthId5;
	}

	public void setMonthId5(Integer monthId5) {
		this.monthId5 = monthId5;
	}

	public Integer getMonthId6() {
		return monthId6;
	}

	public void setMonthId6(Integer monthId6) {
		this.monthId6 = monthId6;
	}

	public Integer getMonthId7() {
		return monthId7;
	}

	public void setMonthId7(Integer monthId7) {
		this.monthId7 = monthId7;
	}

	public Integer getMonthId8() {
		return monthId8;
	}

	public void setMonthId8(Integer monthId8) {
		this.monthId8 = monthId8;
	}

	public Integer getMonthId9() {
		return monthId9;
	}

	public void setMonthId9(Integer monthId9) {
		this.monthId9 = monthId9;
	}

	public Integer getMonthId10() {
		return monthId10;
	}

	public void setMonthId10(Integer monthId10) {
		this.monthId10 = monthId10;
	}

	public Integer getUsertypeId() {
		return usertypeId;
	}

	public void setUsertypeId(Integer usertypeId) {
		this.usertypeId = usertypeId;
	}

	public void setDbIp(String dbIp) {
		this.dbIp = dbIp;
	}

	public String getDbIp() {
		return dbIp;
	}

	public void setDbPort(String dbPort) {
		this.dbPort = dbPort;
	}

	public String getDbPort() {
		return dbPort;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbUsername(String dbUsername) {
		this.dbUsername = dbUsername;
	}

	public String getDbUsername() {
		return dbUsername;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public String getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}

	public Integer getIsSimTrader() {
		return isSimTrader;
	}

	public void setIsSimTrader(Integer isSimTrader) {
		this.isSimTrader = isSimTrader;
	}

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public String getFutureCompany() {
		return futureCompany;
	}

	public void setFutureCompany(String futureCompany) {
		this.futureCompany = futureCompany;
	}

	public String getDbReadUserName() {
		return dbReadUserName;
	}

	public void setDbReadUserName(String dbReadUserName) {
		this.dbReadUserName = dbReadUserName;
	}

	public String getDbReadPassWord() {
		return dbReadPassWord;
	}

	public void setDbReadPassWord(String dbReadPassWord) {
		this.dbReadPassWord = dbReadPassWord;
	}

}
