package com.hongwei.futures.web.action.admin_list_kline;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.TrdKLines;
import com.hongwei.futures.service.TrdKLinesService;
import com.hongwei.futures.util.StringUtil;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminListKlineAction extends BaseAction {

	private FuAdmin admin;
	private Long adminId;
	private Integer totalCount;

	private Long id;
	private String tradingDay;
	private String instrumentId;
	private BigDecimal highestPrice;
	private BigDecimal lowestPrice;
	private BigDecimal openPrice;
	private BigDecimal closePrice;

	@Autowired
	private TrdKLinesService trdKLinesService;

	@Action("kLinesInfo")
	public String kLinesInfo() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtil.isBlank(tradingDay)) {
				map.put("tradingDay", tradingDay);
			}
			if (!StringUtil.isBlank(instrumentId)) {
				map.put("instrumentId", instrumentId);
			}
			List<TrdKLines> klineList = trdKLinesService.queryKlinesList(
					(this.getPageNo() - 1) * this.getPageSize(),
					this.getPageSize(), map);
			if (totalCount == null) {
				totalCount = trdKLinesService.countKlines(map);
			}
			this.getActionContext().put("klineList", klineList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 添加K线记录
	 * 
	 * @return
	 */
	@Action("newKlineAjax")
	public String newKlineAjax() {
		try {
			if (id != null) {
				TrdKLines kline = trdKLinesService.get(id);
				this.getActionContext().put("kline", kline);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 保存K线记录
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveKlineAjax")
	public String saveKlineAjax() {
		try {
			TrdKLines kline = new TrdKLines();
			if (id != null) {
				kline = trdKLinesService.get(id);
			}
			if (StringUtil.isBlank(tradingDay)) {
				write("-2");
				return null;
			}
			if (StringUtil.isBlank(instrumentId)) {
				write("-3");
				return null;
			}
			if (highestPrice == null) {
				write("-4");
				return null;
			}
			if (lowestPrice == null) {
				write("-5");
				return null;
			}
			if (openPrice == null) {
				write("-6");
				return null;
			}
			if (closePrice == null) {
				write("-7");
				return null;
			}
			if (id == null) {
				TrdKLines tkl = trdKLinesService.findKlineByInstrumentAndDate(
						instrumentId, tradingDay);
				if (tkl != null) {
					write("-8");
					return null;
				}
			}
			kline.setTradingDay(tradingDay);
			kline.setInstrumentId(instrumentId);
			kline.setHighestPrice(highestPrice);
			kline.setLowestPrice(lowestPrice);
			kline.setClosePrice(closePrice);
			kline.setOpenPrice(openPrice);
			trdKLinesService.save(kline);
			write("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除K线记录
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("delKline")
	public String delKline() {
		try {
			trdKLinesService.delete(id);
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTradingDay() {
		return tradingDay;
	}

	public void setTradingDay(String tradingDay) {
		this.tradingDay = tradingDay;
	}

	public String getInstrumentId() {
		return instrumentId;
	}

	public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
	}

	public BigDecimal getHighestPrice() {
		return highestPrice;
	}

	public void setHighestPrice(BigDecimal highestPrice) {
		this.highestPrice = highestPrice;
	}

	public BigDecimal getLowestPrice() {
		return lowestPrice;
	}

	public void setLowestPrice(BigDecimal lowestPrice) {
		this.lowestPrice = lowestPrice;
	}

	public BigDecimal getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(BigDecimal closePrice) {
		this.closePrice = closePrice;
	}

	public BigDecimal getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(BigDecimal openPrice) {
		this.openPrice = openPrice;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
}
