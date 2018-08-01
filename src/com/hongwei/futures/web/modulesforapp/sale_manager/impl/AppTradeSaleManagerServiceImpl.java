package com.hongwei.futures.web.modulesforapp.sale_manager.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuMoneyDetail;
import com.hongwei.futures.model.FuStockAccount;
import com.hongwei.futures.model.FuStockMoneyDetail;
import com.hongwei.futures.model.FuStockMoneyInfo;
import com.hongwei.futures.model.FuStockUserAccount;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.FuUserBak;
import com.hongwei.futures.service.FuMoneyDetailService;
import com.hongwei.futures.service.FuStockAccountService;
import com.hongwei.futures.service.FuStockMoneyDetailService;
import com.hongwei.futures.service.FuStockMoneyInfoService;
import com.hongwei.futures.service.FuStockUserAccountService;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.util.DateUtil;
import com.hongwei.futures.web.modulesforapp.sale_manager.AppTradeSaleManagerService;

public class AppTradeSaleManagerServiceImpl implements AppTradeSaleManagerService{
	private static final Log logger = LogFactory.getLog(AppTradeSaleManagerServiceImpl.class);
	@Autowired
	private FuUserService fuUserService;
	@Autowired
	private FuMoneyDetailService fuMoneyDetailService;
	@Autowired
	private FuStockAccountService fuStockAccountService;
	@Autowired
	private FuStockMoneyDetailService fuStockMoneyDetailService;
	@Autowired
	private FuStockMoneyInfoService fuStockMoneyInfoService;
	@Autowired
	private FuStockUserAccountService fuStockUserAccountService;
	
	
	// 根据用户的userId销售管理的首页数据
	public String index(String userId, String version) {
		JSONObject obj = new JSONObject();
		if (null == userId || "".equals(userId)) {
			obj.put("is_success", 0);
			obj.put("message", "缺少参数===>userId");
			logger.info(obj.toString());
			return obj.toString();
		}
		if (null == version || "".equals(version)) {
			obj.put("is_success", 0);
			obj.put("message", "缺少参数===>version");
			logger.info(obj.toString());
			return obj.toString();
		}
		
		FuUser fuUser = fuUserService.get(Long.parseLong(userId));
		if(null == fuUser) {
			obj.put("is_success", 0);
			obj.put("message", "用户不存在");
			logger.info(obj.toString());
			return obj.toString();
		}else if(fuUser.getHhrType() != 3) {
			obj.put("is_success", 0);
			obj.put("message", "该用户不是销售人员");
			logger.info(obj.toString());
			return obj.toString();
		}
		if(!"3.0.0".equals(version)) {
			obj.put("is_success", 0);
			obj.put("message", "版本号不正确");
			logger.info(obj.toString());
			return obj.toString();
		}else {
			// 销售累计收益
			BigDecimal money = fuMoneyDetailService.getCountMoneyByDictionaryIdAndUserId(fuUser.getId(), 12L, 1);
			obj.put("money", null == money ? "0.00" : money);
			// 查询客户姓名 股票账户的个数
			List<FuUserBak> userList = fuUserService.findUserByHhrParentId(fuUser.getId());
			if (null != userList && userList.size() > 0) {
				List<Object> list = new ArrayList<Object>();
				for (FuUserBak fuUserBak : userList) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					/*FuUser user = fuUserService.get(fuUserBak.getUserId().longValue());
					if(null == user.getHhrType()) {
						map.put("is_set", 1);		//  1: 存在(带红)
					}else {
						map.put("is_set", 0);		//  1: 不存在 (不带红)
					}*/
					// 根据用户查询股票账户信息
					HashMap<String, Object> map1 = new HashMap<String, Object>();
					map1.put("userId", fuUserBak.getUserId().longValue());
					map1.put("isDel", 0);
					List<FuStockAccount> fsaList = fuStockAccountService.findAccountByMap(0, 100, map1);
					
					if (null != fsaList && fsaList.size() > 0) {
						for (FuStockAccount fuStockAccount : fsaList) {
							if(null == fuStockAccount.getProfitModel()) {
								map.put("is_set", 1);		//  1: 存在(带红)
								break;
							}else {
								map.put("is_set", 0);		//  1: 不存在 (不带红)
							}
						}
					}else {
						map.put("is_set", 0);		//  1: 不存在 (不带红)
					}
					map.put("user_name", null != fuUserBak.getUserName() ? fuUserBak.getUserName()
							: null != fuUserBak.getNickName() ? fuUserBak.getNickName() : "佚名");
					map.put("avatar", fuUserBak.getAvatar());
					map.put("num", fuUserBak.getNum());
					map.put("user_id", fuUserBak.getUserId());
					list.add(map);
				}
				obj.put("userList", list);
			}else {
				obj.put("userList", "[]");
			}
			obj.put("is_success", 1);
			obj.put("message", "查询累计盈利首页数据成功");
			logger.info(obj.toString());
		}
		return obj.toString();
	}

	// 销售累计盈利详情
	public String saleCountInfo(String userId, String detailId, String version) {
		JSONObject obj = new JSONObject();
		if (null == userId || "".equals(userId)) {
			obj.put("is_success", 0);
			obj.put("message", "缺少参数===>userId");
			logger.info(obj.toString());
			return obj.toString();
		}
		if (null == version || "".equals(version)) {
			obj.put("is_success", 0);
			obj.put("message", "缺少参数===>version");
			logger.info(obj.toString());
			return obj.toString();
		}
		
		FuUser fuUser = fuUserService.get(Long.parseLong(userId));
		if(null == fuUser) {
			obj.put("is_success", 0);
			obj.put("message", "用户不存在");
			logger.info(obj.toString());
			return obj.toString();
		}else if(null == fuUser.getHhrType() || fuUser.getHhrType() != 3) {
			obj.put("is_success", 0);
			obj.put("message", "该用户不是销售人员");
			logger.info(obj.toString());
			return obj.toString();
		}
		
		if(!"3.0.0".equals(version)) {
			obj.put("is_success", 0);
			obj.put("message", "版本号不正确");
			logger.info(obj.toString());
			return obj.toString();
		}else {
			Long moneyDetailId = null ;
			if (null == detailId || "".equals(detailId) || "0".equals(detailId)) {
				moneyDetailId = null;
			}else {
				moneyDetailId = Long.parseLong(detailId);
			}
			List<FuMoneyDetail> detailList = fuMoneyDetailService.findListByDetailId(fuUser.getId(), 12L, moneyDetailId);
			List<Object> list = new ArrayList<Object>();
			if (null != detailList && detailList.size() > 0) {
				for (FuMoneyDetail detail : detailList) {
					HashMap<String, Object> map1 = new HashMap<String, Object>();
					map1.put("date", DateUtil.getStrFromDate(detail.getTime(), "yyyy.MM.dd"));
					map1.put("money", null == detail.getMoney() ? "0.00" : BigDecimal.ZERO.compareTo(detail.getMoney()) == 1 ? "+"+detail.getMoney(): detail.getMoney());
					map1.put("detail_id", detail.getId());
					list.add(map1);
				}
				obj.put("sale_list", list);
			}else {
				obj.put("sale_list", "[]");
			}
			obj.put("is_success", 1);
			obj.put("message", "查询累计盈利详情成功");
			logger.info(obj.toString());
		}
		return obj.toString();
	}

	// 查询用户下所有股票账详情(是销售人员下的用户)
	public String getStockListInfo(String userId, String version) {
		JSONObject obj = new JSONObject();
		if (null == userId || "".equals(userId)) {
			obj.put("is_success", 0);
			obj.put("message", "缺少参数===>userId");
			logger.info(obj.toString());
			return obj.toString();
		}
		if (null == version || "".equals(version)) {
			obj.put("is_success", 0);
			obj.put("message", "缺少参数===>version");
			logger.info(obj.toString());
			return obj.toString();
		}
		
		FuUser fuUser = fuUserService.get(Long.parseLong(userId));
		if(null == fuUser) {
			obj.put("is_success", 0);
			obj.put("message", "用户不存在");
			logger.info(obj.toString());
			return obj.toString();
		}
		if(!"3.0.0".equals(version)) {
			obj.put("is_success", 0);
			obj.put("message", "版本号不正确");
			logger.info(obj.toString());
			return obj.toString();
		}else {
			// 查询这个用户下的所有的昨日收益
			// 根据用户查询股票账户信息
			HashMap<String, Object> map1 = new HashMap<String, Object>();
			map1.put("userId", fuUser.getId());
			map1.put("isDel", 0);
			List<FuStockAccount> fsaList = fuStockAccountService.findAccountByMap(0, 100, map1);
			if (null != fsaList && fsaList.size() > 0) {
				BigDecimal totalProfit = new BigDecimal(0); // 昨日所有账号盈亏和

				List<Object> list = new ArrayList<Object>();
				for (FuStockAccount fuStockAccount : fsaList) {
					HashMap<String, Object> map2 = new HashMap<String, Object>();
					map2.put("open_equity", null == fuStockAccount.getOpenEquity() ? "" : fuStockAccount.getOpenEquity());
					map2.put("capital_account", null == fuStockAccount.getCapitalAccount() ? "" : fuStockAccount.getCapitalAccount());
					
					// 根据开户账户id 查询 开户详细信息
					FuStockMoneyDetail stockDetail = fuStockMoneyDetailService.findDetailByStockId(fuStockAccount.getId());
					if(stockDetail != null){
						totalProfit = totalProfit.add(stockDetail.getNowProfit());
					}
					// 这个账户的昨日收益
					map2.put("now_profit", null == stockDetail ? "0.00" : null == stockDetail.getNowProfit() ? "0.00" : stockDetail.getNowProfit());
					// 这个账号的累计盈利
					FuStockUserAccount fuStockUserAccount = fuStockUserAccountService.findByUserAndStock(fuUser.getId(), fuStockAccount.getId());
					map2.put("count_profit", null == fuStockUserAccount ? "0.00" : null == fuStockUserAccount.getProfitInfo() ? "0.00" : fuStockUserAccount.getProfitInfo());
					// 是否是稳定收益
					map2.put("profit_model", null == fuStockAccount.getProfitModel() ? 2 : fuStockAccount.getProfitModel());
					map2.put("stock_id", fuStockAccount.getId());
					// 股票收益
					map2.put("available_split", fuStockAccount.getAvailableSplit());
					// 资金收益
					map2.put("able_money_split", fuStockAccount.getAbleMoneySplit());
					list.add(map2);
				}
				obj.put("stock_list", list);
				// 昨日所有账户加起来的盈亏
				obj.put("day_total_profit",  BigDecimal.ZERO.compareTo(totalProfit) == 0 ? "0.00" : totalProfit);
				Map<String, Object> infoParames = new HashMap<String, Object>();
				infoParames.put("userId", fuUser.getId());
				FuStockMoneyInfo moneyInfo = fuStockMoneyInfoService.findMoneyInfoByMap(infoParames);
				// 所有账户 所有的累积盈亏
				obj.put("total_profit", null == moneyInfo ? "0.00" : moneyInfo.getProfitInfo() == null ? 0.00 : moneyInfo.getProfitInfo());
				//该用户所有的未缴费用
				obj.put("none_total_fee", null == moneyInfo ? "0.00" : moneyInfo.getNoneFeeInfo() ==  null ? 0.00 : moneyInfo.getNoneFeeInfo());
				obj.put("is_success", 1);
				obj.put("message", "查询用户下的所有的账号信息成功");
			}else {
				obj.put("stock_list", "[]");
				obj.put("is_success", 2);
				obj.put("message", "暂无信息");
			}
		}
		logger.info(obj.toString());
		return obj.toString();
	}
	
	// 	根据stocId 查询该账户的详情
	public String getInfo(String stockId, String detailId, String version) {
		JSONObject obj = new JSONObject();
		if (null == stockId || "".equals(stockId)) {
			obj.put("is_success", 0);
			obj.put("message", "缺少参数===>stockId");
			logger.info(obj.toString());
			return obj.toString();
		}
		if (null == version || "".equals(version)) {
			obj.put("is_success", 0);
			obj.put("message", "缺少参数===>version");
			logger.info(obj.toString());
			return obj.toString();
		}
		FuStockAccount fuStockAccount = fuStockAccountService.get(Long.parseLong(stockId));
		if(null == fuStockAccount) {
			obj.put("is_success", 0);
			obj.put("message", "该账户不存在");
			logger.info(obj.toString());
			return obj.toString();
		}

		if(!"3.0.0".equals(version)) {
			obj.put("is_success", 0);
			obj.put("message", "版本号不正确");
			logger.info(obj.toString());
			return obj.toString();
		}else {
			/*obj.put("open_equity", null == fuStockAccount.getOpenEquity() ? "" : fuStockAccount.getOpenEquity());
			obj.put("capital_account", null == fuStockAccount.getCapitalAccount() ? "" : fuStockAccount.getCapitalAccount());
			obj.put("stock_id", stockId);*/
			// 查询每日盈利的流水
			Long moneyDetailId = null ;
			if (null == detailId || "".equals(detailId) || "0".equals(detailId)) {
				moneyDetailId = null;
			}else {
				moneyDetailId = Long.parseLong(detailId);
			}
			List<FuStockMoneyDetail> detailList = fuStockMoneyDetailService.findByStockId(fuStockAccount.getId(), moneyDetailId);
			if (null != detailList && detailList.size() > 0) {
				List<Object> list = new ArrayList<Object>();
				for (FuStockMoneyDetail detail : detailList) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("date", DateUtil.getStrFromDate(detail.getTradeTime(), "yyyy.MM.dd"));
					map.put("manager_free", null == detail.getNowProfit() ? "0.00" : detail.getNowProfit());
					map.put("detail_id", detail.getId());
					list.add(map);
				}
				obj.put("detail_list", list);
				obj.put("is_success", 1);
				obj.put("message", "单账户每日流水查询成功");
			} else {
				obj.put("detail_list", "[]");
				obj.put("is_success", 1);
				obj.put("message", "单账户暂无流水信息");
			}
		}
		logger.info(obj.toString());
		return obj.toString();
	}

	 //  查询每日管理费流水详情
	public String feeInfo(String stockId, String detailId, String version) {
		JSONObject obj = new JSONObject();
		if (null == stockId || "".equals(stockId)) {
			obj.put("is_success", 0);
			obj.put("message", "缺少参数===>stockId");
			logger.info(obj.toString());
			return obj.toString();
		}
		if (null == version || "".equals(version)) {
			obj.put("is_success", 0);
			obj.put("message", "缺少参数===>version");
			logger.info(obj.toString());
			return obj.toString();
		}
		FuStockAccount fuStockAccount = fuStockAccountService.get(Long.parseLong(stockId));
		if(null == fuStockAccount) {
			obj.put("is_success", 0);
			obj.put("message", "该账户不存在");
			logger.info(obj.toString());
			return obj.toString();
		}

		if(!"3.0.0".equals(version)) {
			obj.put("is_success", 0);
			obj.put("message", "版本号不正确");
			logger.info(obj.toString());
			return obj.toString();
		}else {
			/*obj.put("open_equity", null == fuStockAccount.getOpenEquity() ? "" : fuStockAccount.getOpenEquity());
			obj.put("capital_account", null == fuStockAccount.getCapitalAccount() ? "" : fuStockAccount.getCapitalAccount());
			obj.put("stock_id", stockId);*/
			// 查询每日盈利的流水
			Long moneyDetailId = null ;
			if (null == detailId || "".equals(detailId) || "0".equals(detailId)) {
				moneyDetailId = null;
			}else {
				moneyDetailId = Long.parseLong(detailId);
			}
			List<FuStockMoneyDetail> detailList = fuStockMoneyDetailService.findByStockId(fuStockAccount.getId(), moneyDetailId);
			if (null != detailList && detailList.size() > 0) {
				List<Object> list = new ArrayList<Object>();
				for (FuStockMoneyDetail detail : detailList) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("date", DateUtil.getStrFromDate(detail.getTradeTime(), "yyyy.MM.dd"));
					map.put("manage_free", null == detail.getManageFee() ? "0.00" : detail.getManageFee());
					map.put("detail_id", detail.getId());
					list.add(map);
				}
				obj.put("manage_list", list);
				obj.put("message", "单账户每日管理费查询成功");
				obj.put("is_success", 1);
			} else {
				obj.put("manage_list", "[]");
				obj.put("is_success", 1);
				obj.put("message", "单账户暂无管理费流水信息");
			}
		}
		logger.info(obj.toString());
		return obj.toString();
	}

	//  查询每个账户的备注流水详情
	public String getRemark(String stockId, String detailId, String version) {
		JSONObject obj = new JSONObject();
		if (null == stockId || "".equals(stockId)) {
			obj.put("is_success", 0);
			obj.put("message", "缺少参数===>stockId");
			logger.info(obj.toString());
			return obj.toString();
		}
		if (null == version || "".equals(version)) {
			obj.put("is_success", 0);
			obj.put("message", "缺少参数===>version");
			logger.info(obj.toString());
			return obj.toString();
		}
		FuStockAccount fuStockAccount = fuStockAccountService.get(Long.parseLong(stockId));
		if(null == fuStockAccount) {
			obj.put("is_success", 0);
			obj.put("message", "该账户不存在");
			logger.info(obj.toString());
			return obj.toString();
		}

		if(!"3.0.0".equals(version)) {
			obj.put("is_success", 0);
			obj.put("message", "版本号不正确");
			logger.info(obj.toString());
			return obj.toString();
		}else {
			/*obj.put("open_equity", null == fuStockAccount.getOpenEquity() ? "" : fuStockAccount.getOpenEquity());
			obj.put("capital_account", null == fuStockAccount.getCapitalAccount() ? "" : fuStockAccount.getCapitalAccount());
			obj.put("stock_id", stockId);*/
			// 查询每日盈利的流水
			Long moneyDetailId = null ;
			if (null == detailId || "".equals(detailId) || "0".equals(detailId)) {
				moneyDetailId = null;
			}else {
				moneyDetailId = Long.parseLong(detailId);
			}
			List<FuStockMoneyDetail> detailList = fuStockMoneyDetailService.findByStockId(fuStockAccount.getId(), moneyDetailId);
			if (null != detailList && detailList.size() > 0) {
				List<Object> list = new ArrayList<Object>();
				for (FuStockMoneyDetail detail : detailList) {
					if(null != detail.getRemark() && !"".equals(detail.getRemark())) {
						HashMap<String, Object> map = new HashMap<String, Object>();
						map.put("date", DateUtil.getStrFromDate(detail.getCreateTime(), "yyyy.MM.dd"));
						map.put("remark", null == detail.getRemark() ? "" : detail.getRemark());
						map.put("detail_id", detail.getId());
						list.add(map);
					}
				}
				obj.put("remark_list", list);
				obj.put("message", "单账户每日备注流水查询成功");
				obj.put("is_success", 1);
			} else {
				obj.put("remark_list", "[]");
				obj.put("is_success", 1);
				obj.put("message", "单账户暂无备注流水信息");
			}
		}
		logger.info(obj.toString());
		return obj.toString();
	}

	// 设置账户的收益模式
	public String saveProfitModel(String stockId, String profitModel, String availableSplit, String ableMoneySplit, String version) {
		JSONObject obj = new JSONObject();
		if (null == stockId || "".equals(stockId)) {
			obj.put("is_success", 0);
			obj.put("message", "缺少参数===>stockId");
			logger.info(obj.toString());
			return obj.toString();
		}
		if (null == version || "".equals(version)) {
			obj.put("is_success", 0);
			obj.put("message", "缺少参数===>version");
			logger.info(obj.toString());
			return obj.toString();
		}
		FuStockAccount fuStockAccount = fuStockAccountService.get(Long.parseLong(stockId));
		if(null == fuStockAccount) {
			obj.put("is_success", 0);
			obj.put("message", "该账户不存在");
			logger.info(obj.toString());
			return obj.toString();
		}
		if(!"3.0.0".equals(version)) {
			obj.put("is_success", 0);
			obj.put("message", "版本号不正确");
			logger.info(obj.toString());
			return obj.toString();
		}else {
			if(null == fuStockAccount || null == fuStockAccount.getProfitModel()) {
				fuStockAccount.setAbleMoneySplit(ableMoneySplit);
				fuStockAccount.setAvailableSplit(availableSplit);
				fuStockAccount.setProfitModel(Integer.parseInt(profitModel));
				fuStockAccountService.save(fuStockAccount);
				
				obj.put("is_success", "1");
				obj.put("message", "设置成功");
			}else if(fuStockAccount.getProfitModel() == 0 || fuStockAccount.getProfitModel() == 1) {
				obj.put("is_success", 0);
				obj.put("message", "没有设置的权限");
				logger.info(obj.toString());
				return obj.toString();
			}
		}
		logger.info(obj.toString());
		return obj.toString();
	}

}
