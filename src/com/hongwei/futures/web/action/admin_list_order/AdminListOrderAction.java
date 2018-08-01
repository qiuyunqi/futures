package com.hongwei.futures.web.action.admin_list_order;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.TrdOrder;
import com.hongwei.futures.model.TrdTradeParameter;
import com.hongwei.futures.service.TrdOrderService;
import com.hongwei.futures.service.TrdTradeParameterService;
import com.hongwei.futures.util.StringUtil;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminListOrderAction extends BaseAction {

	private FuAdmin admin;
	private Long adminId;
	private Integer totalCount;
	private Long accountUserId;
	private String accountUserName;
	private String orderNum;

	private Long id;
	private Long userId;
	private String instrumentId;
	private Integer direction;
	private Integer offsetFlag;
	private BigDecimal stopProfit;
	private BigDecimal stopLoss;

	@Autowired
	private TrdOrderService trdOrderService;
	@Autowired
	private TrdTradeParameterService tradeParameterService;

	@Action("orderInfo")
	public String orderInfo() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (accountUserId != null) {
				map.put("accountUserId", accountUserId);
			}
			if (!StringUtil.isBlank(accountUserName)) {
				map.put("accountUserName", accountUserName);
			}
			if (!StringUtil.isBlank(orderNum)) {
				map.put("orderNum", orderNum);
			}
			List<TrdOrder> orderList = trdOrderService.queryOrderList(
					(this.getPageNo() - 1) * this.getPageSize(),
					this.getPageSize(), map);
			if (totalCount == null) {
				totalCount = trdOrderService.countOrders(map);
			}
			this.getActionContext().put("orderList", orderList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 下单页面
	 * 
	 * @return
	 */
	@Action("newOrderAjax")
	public String newOrderAjax() {
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
	 * 下单
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("commitOrderAjax")
	public String commitOrderAjax() {
		try {
			if (userId == null) {
				write("-2");
				return null;
			}
			if (StringUtil.isBlank(instrumentId)) {
				write("-3");
				return null;
			}
			if (stopProfit == null) {
				write("-4");
				return null;
			}
			if (stopLoss == null) {
				write("-5");
				return null;
			}
			Integer result = trdOrderService.saveCreateOrder(userId,
					instrumentId, direction, offsetFlag, stopProfit, stopLoss);
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

	public BigDecimal getStopProfit() {
		return stopProfit;
	}

	public void setStopProfit(BigDecimal stopProfit) {
		this.stopProfit = stopProfit;
	}

	public BigDecimal getStopLoss() {
		return stopLoss;
	}

	public void setStopLoss(BigDecimal stopLoss) {
		this.stopLoss = stopLoss;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

}
