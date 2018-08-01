package com.hongwei.futures.web.action.user_money_detail;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuDictionary;
import com.hongwei.futures.model.FuMoneyDetail;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuDictionaryService;
import com.hongwei.futures.service.FuMoneyDetailService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("fu_common")
public class UserMoneyDetailAction extends BaseAction {
	@Autowired
	private FuMoneyDetailService fuMoneyDetailService;
	@Autowired
	private FuDictionaryService fuDictionaryService;

	private FuUser fuUser;
	private Long userId;
	private Long pid;
	private Long dictionaryId;
	private Integer flag;
	private Integer totalCount;

	/**
	 * 资金明细
	 * 
	 * @return
	 * @throws ParseException
	 */
	@Action("moneyDetailList")
	public String moneyDetailList() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", userId);
			if (pid != null) {
				map.put("pid", pid);
			}
			if (dictionaryId != null) {
				map.put("dictionaryId", dictionaryId);
			}
			map.put("isEnabled", 1);
			if (flag != null) {// 时间筛选
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar c = Calendar.getInstance();
				if (flag == 1) {// 近一个星期
					c.add(Calendar.WEEK_OF_MONTH, -1);
					map.put("date1", sdf.parse(sdf.format(c.getTime())));
				}
				if (flag == 2) {// 近一个月
					c.add(Calendar.MONTH, -1);
					map.put("date1", sdf.parse(sdf.format(c.getTime())));
				}
				if (flag == 3) {// 近一年
					c.add(Calendar.YEAR, -1);
					map.put("date1", sdf.parse(sdf.format(c.getTime())));
				}
				map.put("date2", new Date());
			}
			if (totalCount == null) {
				totalCount = fuMoneyDetailService.getCount(map);
			}
			List<FuMoneyDetail> detailList = fuMoneyDetailService.findListBy(
					(this.getPageNo() - 1) * this.getPageSize(),
					this.getPageSize(), map);
			int incomeCount = 0;
			int spendCount = 0;
			BigDecimal incomeMoney = new BigDecimal(0.00);
			BigDecimal spendMoney = new BigDecimal(0.00);
			for (FuMoneyDetail detail : detailList) {
				if (detail.getIsIncome()) {
					incomeCount += 1;
					incomeMoney = incomeMoney.add(detail.getMoney());
				} else {
					spendCount += 1;
					spendMoney = spendMoney.add(detail.getMoney());
				}
			}
			List<FuDictionary> dictionaries = fuDictionaryService
					.findListByMap(0, Integer.MAX_VALUE, map);
			this.getActionContext().put("dictionaries", dictionaries);
			this.getActionContext().put("detailList", detailList);
			this.getActionContext().put("incomeCount", incomeCount);
			this.getActionContext().put("spendCount", spendCount);
			this.getActionContext().put("incomeMoney", incomeMoney);
			this.getActionContext().put("spendMoney", spendMoney);
			this.getActionContext().put("jsptype", "moneyDetailList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public FuUser getFuUser() {
		return fuUser;
	}

	public void setFuUser(FuUser fuUser) {
		this.fuUser = fuUser;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Long getDictionaryId() {
		return dictionaryId;
	}

	public void setDictionaryId(Long dictionaryId) {
		this.dictionaryId = dictionaryId;
	}

}
