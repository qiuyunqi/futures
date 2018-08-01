package com.hongwei.futures.web.action.wxyqb;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuStockAccount;
import com.hongwei.futures.model.FuTransaction;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.StockPublish;
import com.hongwei.futures.model.StockTransactionRank;
import com.hongwei.futures.model.StockYjbRank;
import com.hongwei.futures.service.FuStockAccountService;
import com.hongwei.futures.service.FuTransactionService;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.service.StockPublishService;
import com.hongwei.futures.service.StockShareService;
import com.hongwei.futures.service.StockTransactionRankService;
import com.hongwei.futures.service.StockYjbRankService;
import com.hongwei.futures.util.DateUtil;
import com.hongwei.futures.util.Property;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("fu_common")
public class NoLoginAction extends BaseAction {

	private static final long serialVersionUID = 8773115634254718766L;
	private static final Log logger = LogFactory.getLog(NoLoginAction.class);

	@Autowired
	private StockYjbRankService stockYjbRankService;
	@Autowired
	private StockTransactionRankService stockTransactionRankService;
	@Autowired
	private FuStockAccountService fuStockAccountService;
	@Autowired
	private FuTransactionService fuTransactionService;
	@Autowired
	private FuUserService fuUserService;
	@Autowired
	private StockPublishService stockPublishService;
	@Autowired
	private StockShareService stockShareService;

	private Long id;
	private String query; // 搜索的条件

	/**
	 * 查询余劵宝收益龙虎榜
	 * 
	 * @return
	 */
	@Action("newIndex")
	public String index() {
		try {

			// 十条收益龙虎榜的数据
			List<StockYjbRank> yjbRankList = stockYjbRankService.getRank(0, 10);
			logger.info("yjbRankList=======>" + yjbRankList);
			this.getHttpServletRequest().setAttribute("yjbRankList", yjbRankList);

			// 十条交易员收益排行榜
			List<StockTransactionRank> transRankList = stockTransactionRankService.getRank(0, 10);
			logger.info("transRankList=======>" + transRankList);
			this.getHttpServletRequest().setAttribute("transRankList", transRankList);

			FuUser fuUser = (FuUser) this.getHttpServletRequest().getSession().getAttribute("fuUser");
			String appid = Property.getProperty("WEIXIN_APPID");
			String url = Property.getProperty("REDIRECT_URL");
			this.getHttpServletRequest().setAttribute("url", url);
			this.getHttpServletRequest().setAttribute("appid", appid);
			this.getHttpServletRequest().setAttribute("fuUser", fuUser);
			String sources = stockYjbRankService.getJsonList();
			this.getHttpServletRequest().setAttribute("sources", sources);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return SUCCESS;
	}

	/**
	 * 查询余劵宝收益龙虎榜
	 * 
	 * @return
	 */
	@Action("tirgerTop")
	public String tirgerTop() {
		try {
			FuUser fuUser = (FuUser) this.getHttpServletRequest().getSession().getAttribute("fuUser");
			String appid = Property.getProperty("WEIXIN_APPID");
			String url = Property.getProperty("REDIRECT_URL");
			this.getHttpServletRequest().setAttribute("url", url);
			this.getHttpServletRequest().setAttribute("appid", appid);
			this.getHttpServletRequest().setAttribute("fuUser", fuUser);
			String sources = stockYjbRankService.getJsonList();
			this.getHttpServletRequest().setAttribute("sources", sources);
			List<StockYjbRank> yjbRankList = stockYjbRankService.getRank(0, 20);
			this.getActionContext().put("yjbRankList", yjbRankList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return SUCCESS;
	}

	/**
	 * 加载更多的龙虎榜内容 输出格式 json
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("tirgerData")
	public String tirgerData() {
		try {
			List<StockYjbRank> yjbRankList = stockYjbRankService.getRank(0, 1000);
			if (null != yjbRankList && yjbRankList.size() > 0) {
				JSONObject obj = new JSONObject();
				List<Object> list = new ArrayList<Object>();
				for (StockYjbRank rank : yjbRankList) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", rank.getId());
					map.put("serialNo", rank.getSerialNo());
					map.put("stockName", null == rank.getStockName() ? "" : rank.getStockName());
					map.put("monthProfit", null == rank.getMonthProfit() ? 0 : rank.getMonthProfit());
					map.put("heat", null == rank.getHeat() ? 0 : rank.getHeat());
					map.put("transactionName", null == rank.getTransactionName() ? "" : rank.getTransactionName());
					list.add(map);
				}
				obj.put("yjbRankList", list);
				writeJson(obj);
			} else {
				write("1");
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}

	/**
	 * 搜索股票
	 * 
	 * @return
	 */
	@Action("searchTirger")
	public String searchTirger() {
		try {
			List<StockYjbRank> yjbRankList = stockYjbRankService.getSearcher(query);
			List<Object> list = new ArrayList<Object>();
			JSONObject obj = new JSONObject();
			if (yjbRankList != null && yjbRankList.size() > 0) {
				for (StockYjbRank rank : yjbRankList) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("serialNo", rank.getSerialNo());
					map.put("stockName", rank.getStockName());
					map.put("monthProfit", rank.getMonthProfit());
					map.put("heat", null == rank.getHeat() ? 0 : rank.getHeat());
					map.put("transactionName", rank.getTransactionName());
					list.add(map);
				}
			}
			obj.put("yjbRankList", list);
			writeJson(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}

	/**
	 * 交易排行榜
	 * 
	 * @return
	 */
	@Action("profitTop")
	public String profitTop() {
		List<StockTransactionRank> transRankList = stockTransactionRankService.getRank(0, 20);
		this.getActionContext().put("transRankList", transRankList);
		return SUCCESS;
	}

	/**
	 * 交易的排行榜数据
	 * 
	 * @return
	 */
	@Action("profitData")
	public String profitData() {
		try {
			List<StockTransactionRank> transRankList = stockTransactionRankService.getRank(0, 1000);
			JSONObject obj = new JSONObject();
			List<Object> list = new ArrayList<Object>();
			if (transRankList != null && transRankList.size() > 0) {
				for (StockTransactionRank stockTransactionRank : transRankList) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", stockTransactionRank.getId());
					map.put("serialNo", stockTransactionRank.getSerialNo());
					map.put("transactionName", null == stockTransactionRank.getTransactionName() ? "" : stockTransactionRank.getTransactionName());
					map.put("monthProfit", null == stockTransactionRank.getMonthProfit() ? 0 : stockTransactionRank.getMonthProfit());
					map.put("managerScale", null == stockTransactionRank.getManagerScale() ? 0 : stockTransactionRank.getManagerScale());
					map.put("rating", null == stockTransactionRank.getFuTransaction().getRating() ? "" : stockTransactionRank.getFuTransaction().getRating());
					list.add(map);
				}
			}
			obj.put("transRankList", list);
			writeJson(obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}

	/**
	 * 根据id查询余劵宝排行中的数据
	 * 
	 * @return 一个StockYjbRank对象
	 */
	@Action("getById")
	public String getById() {
		try {
			if (null == id || 0 == id) {
				write("1"); // id 不能为空, 没有这个股票
				return null;
			}
			StockYjbRank yjbRank = stockYjbRankService.getById(id);
			if (null == yjbRank) {
				write("1"); // id 不能为空, 没有这个股票
			} else {
				JSONObject obj = new JSONObject();
				obj.put("yjbRank", yjbRank);
				writeJson(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}

	/**
	 * 进入供劵的主页
	 * 
	 * @return
	 */
	@Action("forTicket")
	public String forTicket() throws Exception {
		FuUser fuUser = (FuUser) this.getHttpServletRequest().getSession().getAttribute("fuUser");
		if (null == fuUser) {
			this.getHttpServletRequest().setAttribute("isLogin", 0);
		} else {
			this.getHttpServletRequest().setAttribute("isLogin", 1);
		}
		String appid = Property.getProperty("WEIXIN_APPID");
		String url = Property.getProperty("REDIRECT_URL");
		this.getHttpServletRequest().setAttribute("url", url);
		this.getHttpServletRequest().setAttribute("appid", appid);

		String sources = fuStockAccountService.getJsonList();
		this.getHttpServletRequest().setAttribute("sources", sources);
		this.getHttpServletRequest().setAttribute("fuUser", fuUser);
		// 查询全部的message
		return SUCCESS;
	}

	/**
	 * 查询供劵账户详情根据id
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("forTicketInfo")
	public String forTicketInfo() throws Exception {
		if (null == id || "".equals(id)) {
			this.getHttpServletRequest().setAttribute("message", "数据不存在");
			return SUCCESS;
		}
		FuStockAccount fuStockAccount = fuStockAccountService.findAccountById(id);
		FuStockAccount sa = new FuStockAccount();
		sa = fuStockAccount;
		// if (sa != null && sa.getCapitalAccount() != null) {
		// String ca = sa.getCapitalAccount();
		// if (ca.length() > 5) {
		// String substring = ca.substring(ca.length() - 5, ca.length());
		// ca = ca.replace(substring, "*****");
		// }
		// }
		sa.setCapitalAccount("******");
		this.getHttpServletRequest().setAttribute("account", sa);

		String appid = Property.getProperty("WEIXIN_APPID");
		String url = Property.getProperty("REDIRECT_URL");
		this.getHttpServletRequest().setAttribute("url", url);
		this.getHttpServletRequest().setAttribute("appid", appid);

		return SUCCESS;
	}

	/**
	 * Aajax 访问数据
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("forTicketAjax")
	public String forTicketAjax() throws Exception {
		// 查询所有可以抢和已经抢过的劵
		// List<FuStockAccount> accountList = fuStockAccountService.findAll(0,
		// 1, null == start ? 0 : start * 5 - 5, 5);
		List<FuStockAccount> accountList = fuStockAccountService.findAll(0, 3, 0, 10000);
		if (null != accountList && accountList.size() > 0) {
			JSONObject obj = new JSONObject();
			List<Object> list = new ArrayList<Object>();
			for (FuStockAccount fuStockAccount : accountList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", fuStockAccount.getId());
				map.put("message", null == fuStockAccount.getMessage() ? "" : fuStockAccount.getMessage());
				map.put("available", null == fuStockAccount.getAvailable() ? 0 : fuStockAccount.getAvailable());
				map.put("availableSplit", null == fuStockAccount.getAvailableSplit() ? 0 : fuStockAccount.getAvailableSplit());
				map.put("createTime", null != fuStockAccount.getCreateTime() ? DateUtil.getStrFromDate(fuStockAccount.getCreateTime(), "yyyy-MM-dd HH:mm:ss") : "");
				map.put("transactionId", fuStockAccount.getTransactionId());
				list.add(map);
			}
			obj.put("accountList", list);
			writeJson(obj);
		} else {
			write("1");
		}
		return null;
	}

	@Action("getAccountById")
	public String getAccountById() {
		try {
			if (null == id || 0 == id) {
				write("1"); // id 不能为空, 没有这个股票
				return null;
			}
			FuStockAccount fuStockAccount = fuStockAccountService.get(id);
			logger.info("fuStockAccount=========>" + fuStockAccount);
			if (null == fuStockAccount) {
				write("1"); // id 不能为空, 没有这个股票
			} else {
				JSONObject obj = new JSONObject();
				obj.put("id", fuStockAccount.getId());
				obj.put("transactionId", fuStockAccount.getTransactionId());
				obj.put("message", null == fuStockAccount.getMessage() ? "" : fuStockAccount.getMessage());
				obj.put("available", null == fuStockAccount.getAvailable() || fuStockAccount.getAvailable().compareTo(BigDecimal.ZERO) == 0 ? new BigDecimal(0.00) : fuStockAccount.getAvailable());
				obj.put("availableSplit", null == fuStockAccount.getAvailableSplit() ? "" : fuStockAccount.getAvailableSplit());
				obj.put("createTime", null == fuStockAccount.getCreateTime() ? "" : DateUtil.getStrFromDate(fuStockAccount.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
				writeJson(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}

	/**
	 * 根据条件查询fuStockAccount like
	 * 
	 * @return
	 */
	@Action("findByMsg")
	public String findByMsg() throws Exception {
		JSONObject obj = new JSONObject();
		if (null == query || "".equals(query)) {
			obj.put("isSuccess", 0);
			obj.put("message", "请输入查询的股票名称");
			return obj.toString();
		}
		List<FuStockAccount> accountList = fuStockAccountService.findByMsg(query);
		List<Object> list = new ArrayList<Object>();
		for (FuStockAccount account : accountList) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", account.getId());
			map.put("message", account.getMessage());
			map.put("available", account.getAvailable());
			map.put("availableSplit", account.getAvailableSplit());
			map.put("createTime", null != account.getCreateTime() ? DateUtil.getStrFromDate(account.getCreateTime(), "yyyy-MM-dd HH:mm:ss") : "");
			list.add(map);
		}
		obj.put("saList", list);
		writeJson(obj);
		return null;
	}

	/**
	 * 找劵主页
	 * 
	 * @return
	 */
	@Action("findTicket")
	public String findTicket() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			List<StockPublish> ticketList = stockPublishService.findPublishByMap(0, 5, map);
			this.getActionContext().put("ticketList", ticketList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return SUCCESS;
	}

	/**
	 * 找劵主页数据
	 * 
	 * @return
	 */
	@Action("ticketData")
	public String ticketData() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			List<StockPublish> ticketList = stockPublishService.findPublishByMap(0, Integer.MAX_VALUE, map);
			JSONObject json = new JSONObject();
			JSONArray array = new JSONArray();
			for (StockPublish stockPublish : ticketList) {
				JSONObject obj = new JSONObject();
				obj.put("id", stockPublish.getId());
				obj.put("title", stockPublish.getTitle());
				obj.put("createTime", new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(stockPublish.getCreateTime()));
				obj.put("isDel", stockPublish.getIsDel());
				array.add(obj);
			}
			json.put("array", array);
			write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}

	/**
	 * 给劵
	 */
	@Action("findTicketInfo")
	public String findTicketInfo() {
		try {
			FuUser fuUser = (FuUser) this.getHttpServletRequest().getSession().getAttribute("fuUser");
			StockPublish publish = stockPublishService.get(id);
			FuUser user = fuUserService.get(publish.getFuUser().getId());
			FuTransaction transaction = fuTransactionService.findByUserId(user.getId());
			this.getActionContext().put("publishUserId", user.getId());// 找劵的用户id
			this.getActionContext().put("userId", fuUser == null ? 0 : fuUser.getId());
			this.getActionContext().put("nickName", user.getNickName());
			this.getActionContext().put("rating", transaction == null ? 1 : transaction.getRating() == null ? 1 : transaction.getRating());
			this.getActionContext().put("publish", publish);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return SUCCESS;
	}

	/**
	 * 找劵详情
	 */
	@Action("findTicketDetail")
	public String findTicketDetail() {
		try {
			StockPublish publish = stockPublishService.get(id);
			FuUser user = fuUserService.get(publish.getFuUser().getId());
			FuTransaction transaction = fuTransactionService.findByUserId(user.getId());
			this.getActionContext().put("publishUserId", user.getId());
			this.getActionContext().put("nickName", user.getNickName());
			this.getActionContext().put("rating", transaction == null ? 1 : transaction.getRating() == null ? 1 : transaction.getRating());
			this.getActionContext().put("publish", publish);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return SUCCESS;
	}

	/**
	 * 申请成为交易员
	 */
	@Action("business")
	public String business() {
		return SUCCESS;
	}

	/**
	 * 通过查询StockShare对象的Code 或者 Name 查询FuStockAccount对象
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("findByCodeOrName")
	public String findByCodeOrName() throws Exception {
		JSONObject obj = new JSONObject();
		if (null == query || "".equals(query)) {
			obj.put("isSuccess", 0);
			obj.put("message", "请输入查询的股票名称");
			return obj.toString();
		}
		List<FuStockAccount> accountList = stockShareService.findByCodeOrName(query);
		List<Object> list = new ArrayList<Object>();
		for (FuStockAccount account : accountList) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", account.getId());
			map.put("message", null == account.getMessage() ? "" : account.getMessage());
			map.put("available", account.getAvailable() == null ? 0.00 : account.getAvailable());
			map.put("availableSplit", account.getAvailableSplit() == null ? 0.00 : account.getAvailableSplit());
			map.put("createTime", null != account.getCreateTime() ? DateUtil.getStrFromDate(account.getCreateTime(), "yyyy-MM-dd HH:mm:ss") : "");
			list.add(map);
		}
		obj.put("saList", list);
		writeJson(obj);
		return null;
	}
	
	/**
	 * ------------------------------------------------------------------------
	 * ------------------------------------------- /** get set function
	 **/
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}