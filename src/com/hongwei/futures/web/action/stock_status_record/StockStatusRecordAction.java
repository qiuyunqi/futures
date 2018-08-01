package com.hongwei.futures.web.action.stock_status_record;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.StockStatusRecord;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.service.StockStatusRecordService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class StockStatusRecordAction extends BaseAction {

	private static final long serialVersionUID = 7443489957294115845L;
	
	@Autowired
	private FuUserService fuUserService;
	@Autowired
	private StockStatusRecordService stockStatusRecordService;

	private FuAdmin admin;
	private Long adminId;
	private Integer totalCount;

	private Long userId;
	private String phone;
	private String capitalAccount;
	private Date time1;
	private Date time2;
	private Integer type;

	/**
	 * 股票状态记录
	 * 
	 * @return
	 */
	@Action("statusRecordList")
	public String statusRecordList() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Map<String, Object> map = new HashMap<String, Object>();
			if (userId != null) {
				map.put("userId", userId);
			}
			if (!StringUtil.isBlank(phone)) {
				map.put("phone", phone);
			}
			if (!StringUtil.isBlank(capitalAccount)) {
				map.put("capitalAccount", capitalAccount);
			}
			if (time1 != null) {
				map.put("time1", time1);
			}
			if (time2 != null) {
				map.put("time2", time2);
			}
			// 今日开启申请
			if (type != null) {
				// 今日开启申请
				if (type == 0) {
					map.put("time", sdf.format(new Date()));
					map.put("type", type);
				}
				// 今日暂停申请
				if (type == 1) {
					map.put("time", sdf.format(new Date()));
					map.put("type", type);
				}
				// 所有暂停
				if (type == 2) {
					map.put("type", 1);
				}
				// 提交账户
				if (type == 3) {
					map.put("type", 4);
				}
			}
			List<StockStatusRecord> recordList = stockStatusRecordService
					.queryStockStatusRecord(
							(this.getPageNo() - 1) * this.getPageSize(),
							this.getPageSize(), map);
			if (totalCount == null) {
				totalCount = stockStatusRecordService
						.countStockStatusRecord(map);
			}
			this.getActionContext().put("recordList", recordList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
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

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCapitalAccount() {
		return capitalAccount;
	}

	public void setCapitalAccount(String capitalAccount) {
		this.capitalAccount = capitalAccount;
	}

	public Date getTime1() {
		return time1;
	}

	public void setTime1(Date time1) {
		this.time1 = time1;
	}

	public Date getTime2() {
		return time2;
	}

	public void setTime2(Date time2) {
		this.time2 = time2;
	}
}
