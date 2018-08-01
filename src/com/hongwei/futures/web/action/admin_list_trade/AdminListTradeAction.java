package com.hongwei.futures.web.action.admin_list_trade;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.TrdTrade;
import com.hongwei.futures.service.TrdOrderService;
import com.hongwei.futures.service.TrdTradeService;
import com.hongwei.futures.util.StringUtil;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminListTradeAction extends BaseAction {

	private FuAdmin admin;
	private Long adminId;
	private Integer totalCount;
	private Long accountUserId;
	private String accountUserName;

	private Long id;
	private String instrumentId;
	private Integer direction;
	private Integer offsetFlag;
	private String openOrderNum; // 开仓订单编号
	private Date openDateTime;// 开仓时间
	private Integer openVolume;// 开仓成交手数
	private BigDecimal openPrice;// 开仓成交价格
	private BigDecimal openMoney;// 开仓成交金额
	private String closeOrderNum; // 平仓订单编号
	private Date closeDateTime;// 平仓时间
	private Integer closeVolume;// 平仓成交手数
	private BigDecimal closePrice;// 平仓成交价格
	private BigDecimal closeMoney;// 平仓成交金额
	private Integer state;// 状态
	private Date createTime;// 创建时间
	private Date updateTime;// 更新时间
	private BigDecimal closeProfit;// 平仓盈亏

	@Autowired
	private TrdTradeService trdTradeService;
	@Autowired
	private TrdOrderService trdOrderService;

	@Action("tradeInfo")
	public String tradeInfo() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (accountUserId != null) {
				map.put("accountUserId", accountUserId);
			}
			if (!StringUtil.isBlank(accountUserName)) {
				map.put("accountUserName", accountUserName);
			}
			List<TrdTrade> tradeList = trdTradeService.queryTradeList(
					(this.getPageNo() - 1) * this.getPageSize(),
					this.getPageSize(), map);
			if (totalCount == null) {
				totalCount = trdTradeService.countTrades(map);
			}
			this.getActionContext().put("tradeList", tradeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 平仓
	 * 
	 * @throws Exception
	 */
	@Action("closeTradeAjax")
	public String closeTradeAjax() {
		try {
			TrdTrade trade = trdTradeService.get(id);
			Integer result = trdOrderService.saveCloseTrade(trade.getFuUser()
					.getId(), trade.getInstrumentId(), trade.getDirection(), 1,
					trade);
			write(result.toString());
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInstrumentId() {
		return instrumentId;
	}

	public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
	}

	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	public Integer getOffsetFlag() {
		return offsetFlag;
	}

	public void setOffsetFlag(Integer offsetFlag) {
		this.offsetFlag = offsetFlag;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getOpenOrderNum() {
		return openOrderNum;
	}

	public void setOpenOrderNum(String openOrderNum) {
		this.openOrderNum = openOrderNum;
	}

	public Date getOpenDateTime() {
		return openDateTime;
	}

	public void setOpenDateTime(Date openDateTime) {
		this.openDateTime = openDateTime;
	}

	public Integer getOpenVolume() {
		return openVolume;
	}

	public void setOpenVolume(Integer openVolume) {
		this.openVolume = openVolume;
	}

	public BigDecimal getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(BigDecimal openPrice) {
		this.openPrice = openPrice;
	}

	public BigDecimal getOpenMoney() {
		return openMoney;
	}

	public void setOpenMoney(BigDecimal openMoney) {
		this.openMoney = openMoney;
	}

	public String getCloseOrderNum() {
		return closeOrderNum;
	}

	public void setCloseOrderNum(String closeOrderNum) {
		this.closeOrderNum = closeOrderNum;
	}

	public Date getCloseDateTime() {
		return closeDateTime;
	}

	public void setCloseDateTime(Date closeDateTime) {
		this.closeDateTime = closeDateTime;
	}

	public Integer getCloseVolume() {
		return closeVolume;
	}

	public void setCloseVolume(Integer closeVolume) {
		this.closeVolume = closeVolume;
	}

	public BigDecimal getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(BigDecimal closePrice) {
		this.closePrice = closePrice;
	}

	public BigDecimal getCloseMoney() {
		return closeMoney;
	}

	public void setCloseMoney(BigDecimal closeMoney) {
		this.closeMoney = closeMoney;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public BigDecimal getCloseProfit() {
		return closeProfit;
	}

	public void setCloseProfit(BigDecimal closeProfit) {
		this.closeProfit = closeProfit;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Long getAccountUserId() {
		return accountUserId;
	}

	public void setAccountUserId(Long accountUserId) {
		this.accountUserId = accountUserId;
	}

	public String getAccountUserName() {
		return accountUserName;
	}

	public void setAccountUserName(String accountUserName) {
		this.accountUserName = accountUserName;
	}

}
