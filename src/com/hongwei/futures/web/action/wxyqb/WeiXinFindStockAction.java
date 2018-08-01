package com.hongwei.futures.web.action.wxyqb;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.hongwei.futures.service.FuStockAccountService;
import com.hongwei.futures.service.FuTransactionService;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.service.StockPublishService;
import com.hongwei.futures.util.Property;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("wx_yqb")
public class WeiXinFindStockAction extends BaseAction {
	/**
	 * 微信余劵宝找劵
	 */
	private static final long serialVersionUID = -5924365111519746838L;
	private static final Log logger = LogFactory.getLog(WeiXinFindStockAction.class);
	@Autowired
	private StockPublishService stockPublishService;
	@Autowired
	private FuUserService fuUserService;
	@Autowired
	private FuTransactionService fuTransactionService;
	@Autowired
	private FuStockAccountService fuStockAccountService;

	private FuUser fuUser;
	private Long id;
	private String title;
	private String description;
	private Long userId;
	private Long publishUserId;
	private String openEquity; // 开户劵商
	private String salesDept; // 营业
	private Integer accountType; // 账户类型
	private Integer profitModel; // 收益模式 0:稳定收益 1:保本分成
	private String availableSplit; // 收益分配
	private String message; // 留言
	private String capitalAccount;
	private String serverId;

	/**
	 * 给劵页面
	 * 
	 * @return
	 */
	@Action("giveTicket")
	public String giveTicket() {
		try {
			if (userId != null && userId > 0) {
				this.getHttpServletRequest().getSession().removeAttribute("fuUser");
				this.getHttpServletRequest().getSession().setAttribute("fuUser", fuUserService.get(userId));
			}
			if (publishUserId != null && userId.equals(publishUserId)) {// 找劵的用户id不能等于给劵的用户id
				write("-1"); // 给劵的目标用户不能是自己
				return null;
			}
			String appid = Property.getProperty("WEIXIN_APPID");
			String url = Property.getProperty("REDIRECT_URL");
			this.getHttpServletRequest().setAttribute("url", url);
			this.getHttpServletRequest().setAttribute("appid", appid);
			this.getActionContext().put("userId", userId);
			this.getActionContext().put("publishUserId", publishUserId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return SUCCESS;
	}

	/**
	 * 保存给劵
	 * 
	 * @return
	 */
	@Action("saveGiveTicket")
	public String saveGiveTicket() {
		try {
			if (null == openEquity || "".equals(openEquity)) {
				write("1"); // 开户劵商不能为空
				return null;
			}
			if (null == salesDept || "".equals(salesDept)) {
				write("2"); // 营业部不能为空
				return null;
			}
			if (null == accountType || (1 != accountType && 2 != accountType && 3 != accountType)) {
				write("3"); // 请选择类型
				return null;
			}

			if (null == profitModel) {
				write("4"); // 收益模式不能为空
				return null;
			}

			if (null == availableSplit) {
				write("5"); // 收益分配不能为空
				return null;
			}

			if (null != message && !"".equals(message) && message.length() > 25) {
				write("6"); // 最多25个字
				return null;
			}
			fuUser = (FuUser) this.getHttpServletRequest().getSession().getAttribute("fuUser");
			// 保存账号信息
			FuStockAccount fsa = new FuStockAccount();
			fsa.setFuUser(fuUser);
			fsa.setOpenEquity(openEquity);
			fsa.setSalesDept(salesDept);
			fsa.setAccountType(accountType);
			fsa.setProfitModel(profitModel);
			fsa.setAvailableSplit(availableSplit);
			fsa.setCapitalAccount(capitalAccount);
			fsa.setMessage(message);
			fsa.setSourceType(2); // 0: 网站 1:APP 2: 微信
			fsa.setCreateTime(new Date());
			// 创建时,更新时间与创建时间相同
			fsa.setUpdateTime(new Date());
			fsa.setIsDel(0); // 可用账号
			fsa.setExamineStatus(0);// 审核中状态
			fsa.setIsPublish(0); // 未发布状态
			fsa.setState(0);
			if (publishUserId != null) {// 此处的用户id是创建这个找劵信息的交易员，给劵用户是当前session里存的用户
				fsa.setFuUser(fuUserService.get(userId));
				fsa.setIsPublish(1); // 已发布状态
				fsa.setTransactionId(fuTransactionService.findByUserId(publishUserId) == null ? 1L : fuTransactionService.findByUserId(publishUserId).getId());
				fsa.setTransactionStatus(0);
			}
			fuStockAccountService.saveFsa(fsa, this.getHttpSession(), serverId);
			this.getHttpServletRequest().getSession().removeAttribute("srcId");
			write("8");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}

	/**
	 * 给劵成功
	 * 
	 * @return
	 */
	@Action("giveTicketSuccess")
	public String giveTicketSuccess() {
		return SUCCESS;
	}

	/**
	 * 申请成为合作交易员
	 */
	@Action("saveStockTrasction")
	public String saveStockTrasction() {
		try {
			FuUser fuUser = fuUserService.get(userId);
			if (fuUser != null && fuUser.getIsTransaction() != null && fuUser.getIsTransaction() == 1) {// 已经是交易员
				write("-1");
				return null;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", userId);
			List<FuTransaction> list = fuTransactionService.findTransactionByMap(0, Integer.MAX_VALUE, map);
			FuTransaction transaction = null;
			if (list != null && list.size() > 0) {
				transaction = list.get(0);
			}
			if (transaction == null) {
				transaction = new FuTransaction();
			}
			transaction.setFuUser(fuUser);
			transaction.setIsVerification(0);
			transaction.setIsDel(1);
			transaction.setRating(1);
			transaction.setCreateTime(new Date());
			fuTransactionService.save(transaction);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}

	/**
	 * 申请交易员成功
	 */
	@Action("businessSuccess")
	public String businessSuccess() {
		return SUCCESS;
	}

	/**
	 * 我要找劵页面
	 * 
	 * @return
	 */
	@Action("userFindTicket")
	public String userFindTicket() {
		try {
			FuTransaction transaction = fuTransactionService.findByUserId(userId);
			if (transaction == null) {// 用户不是交易员
				write("-1");
				return null;
			}
			if (transaction.getIsVerification() != 1 || null == transaction.getIsVerification()) {// 用户申请的交易员还未审核通过
				write("-2");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return SUCCESS;
	}

	/**
	 * 保存找劵信息
	 * 
	 * @return
	 */
	@Action("saveFindTicket")
	public String saveFindTicket() {
		try {
			StockPublish publish = new StockPublish();
			publish.setFuUser(fuUserService.get(userId));
			publish.setTitle(title);
			publish.setDescription(description);
			publish.setCreateTime(new Date());
			publish.setIsDel(1);
			stockPublishService.save(publish);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return SUCCESS;
	}

	/**
	 * 保存找劵成功页面
	 * 
	 * @return
	 */
	@Action("findIntroduceSuccess")
	public String findIntroduceSuccess() {
		return SUCCESS;
	}

	/**
	 * 找劵信息管理
	 * 
	 * @return
	 */
	@Action("findTicketManager")
	public String findTicketManager() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", userId);
			List<StockPublish> ticketList = stockPublishService.findPublishByMap(0, 5, map);
			this.getActionContext().put("ticketList", ticketList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return SUCCESS;
	}

	/**
	 * 找劵管理数据
	 * 
	 * @return
	 */
	@Action("ticketManagerData")
	public String ticketManagerData() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", userId);
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
	 * 找劵管理删除页面
	 */
	@Action("ticketManagerClose")
	public String ticketManagerClose() {
		try {
			StockPublish publish = stockPublishService.get(id);
			FuUser user = fuUserService.get(publish.getFuUser().getId());
			FuTransaction transaction = fuTransactionService.findByUserId(user.getId());
			this.getActionContext().put("userName", user.getUserName());
			this.getActionContext().put("rating", transaction == null ? 1 : transaction.getRating() == null ? 1 : transaction.getRating());
			this.getActionContext().put("publish", publish);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return SUCCESS;
	}

	/**
	 * 找劵管理详情
	 */
	@Action("ticketManagerDetail")
	public String ticketManagerDetail() {
		try {
			StockPublish publish = stockPublishService.get(id);
			FuUser user = fuUserService.get(publish.getFuUser().getId());
			FuTransaction transaction = fuTransactionService.findByUserId(user.getId());
			this.getActionContext().put("userName", user.getUserName());
			this.getActionContext().put("rating", transaction == null ? 1 : transaction.getRating() == null ? 1 : transaction.getRating());
			this.getActionContext().put("publish", publish);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return SUCCESS;
	}

	/**
	 * 保存找劵删除
	 * 
	 * @return
	 */
	@Action("saveTicketClose")
	public String saveTicketClose() {
		try {
			StockPublish publish = stockPublishService.get(id);
			publish.setIsDel(0);// 已删除
			stockPublishService.save(publish);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}

	public FuUser getFuUser() {
		return fuUser;
	}

	public void setFuUser(FuUser fuUser) {
		this.fuUser = fuUser;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getPublishUserId() {
		return publishUserId;
	}

	public void setPublishUserId(Long publishUserId) {
		this.publishUserId = publishUserId;
	}

	public String getOpenEquity() {
		return openEquity;
	}

	public void setOpenEquity(String openEquity) {
		this.openEquity = openEquity;
	}

	public String getSalesDept() {
		return salesDept;
	}

	public void setSalesDept(String salesDept) {
		this.salesDept = salesDept;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public Integer getProfitModel() {
		return profitModel;
	}

	public void setProfitModel(Integer profitModel) {
		this.profitModel = profitModel;
	}

	public String getAvailableSplit() {
		return availableSplit;
	}

	public void setAvailableSplit(String availableSplit) {
		this.availableSplit = availableSplit;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCapitalAccount() {
		return capitalAccount;
	}

	public void setCapitalAccount(String capitalAccount) {
		this.capitalAccount = capitalAccount;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
}
