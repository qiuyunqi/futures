package com.hongwei.futures.web.modulesforapp.rise_fall.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.sf.json.JSONObject;

import com.hongwei.futures.model.FuAd;
import com.hongwei.futures.model.FuProduct;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.WqqOptions;
import com.hongwei.futures.service.FuAdService;
import com.hongwei.futures.service.FuProductService;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.service.WqqOptionsService;
import com.hongwei.futures.util.DateUtil;
import com.hongwei.futures.web.modulesforapp.rise_fall.AppRiseFallService;

public class AppRiseFallServiceImpl implements AppRiseFallService {

	@Autowired
	private FuProductService fuProductService;
	@Autowired
	private FuAdService fuAdService;
	@Autowired
	private FuUserService fuUserService;
	@Autowired
	private WqqOptionsService wqqOptionsService;

	// ** 获取看涨跌的产品信息
	public String getRiseFallInfo(String userId, String version) {
		JSONObject obj = new JSONObject();
		try {
			if (null == version || "".equals(version)) {
				obj.put("is_success", 0);
				obj.put("message", "缺少参数");
				System.out.println(obj.toString());
				return obj.toString();
			}
			if ("2.0.0".equals(version)) {
				FuProduct fuProduct = fuProductService.get(4L);
				if (null == fuProduct) {
					obj.put("is_success", 0);
					obj.put("message", "该产品可能已经下架, 请联系管理员");
					System.out.println(obj.toString());
					return obj.toString();
				}

				if (null == userId || "".equals(userId)) {
					obj.put("is_success", 0);
					obj.put("message", "用户不存在, 请重新登录");
					System.out.println(obj.toString());
					return obj.toString();
				}
				FuUser fuUser = fuUserService.get(Long.parseLong(userId));
				if (null == fuUser) {
					obj.put("is_success", 0);
					obj.put("message", "用户不存在, 请重新登录");
					System.out.println(obj.toString());
					return obj.toString();
				}

				// 获取广告位的图片
				List<FuAd> ad = fuAdService.getList(2);
				obj.put("name", fuProduct.getName() == null ? "7天涨跌赢"
						: fuProduct.getName());
				obj.put("ad", null == ad || ad.size() <= 0 ? "" : ad.get(0)
						.getImageUrl());
				obj.put("ad_title", fuProduct.getAdTitle() == null ? ""
						: fuProduct.getAdTitle());
				obj.put("title",
						null == fuProduct.getTitle() ? "" : fuProduct
								.getTitle());
				obj.put("rise_profit", fuProduct.getRiseProfit() == null ? ""
						: fuProduct.getRiseProfit());
				obj.put("fall_profit", fuProduct.getFallProfit() == null ? ""
						: fuProduct.getFallProfit());
				// 截止时间到 - 现在时间
				obj.put("deadline", fuProduct.getEndTime().getTime());
				obj.put("now_time", new Date().getTime());

				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("isPay", 1);
				map.put("contentsId", fuProduct.getWqqContents().getId());
				// 所有已经支付过的用户数量
				float allPayNum = (float) wqqOptionsService.countOptions(map);
				// 查询已经支付并且购买的是看涨的人的数量
				HashMap<String, Object> map1 = new HashMap<String, Object>();
				map1.put("isPay", 1);
				map1.put("guessType", 1);
				map1.put("contentsId", fuProduct.getWqqContents().getId());
				float riseNum = (float) wqqOptionsService.countOptions(map1);
				float ratio = 0f;
				if (0 == allPayNum || 0.0 == allPayNum) {
					ratio = 0.5f;
				} else {
					ratio = riseNum / allPayNum;
				}
				obj.put("ratio", ratio); // 看涨和全部的比例
				obj.put("href", fuProduct.getInfoHref() == null ? "" : fuProduct.getInfoHref());
				obj.put("account_balance", fuUser.getAccountBalance() == null ? 0 : fuUser.getAccountBalance()); // 用户余额
				obj.put("is_success", 1);
				obj.put("message", "查询看跌张产品信息成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "系统正在维护, 请稍后再试");
		}
		System.out.println(obj.toString());
		return obj.toString();
	}

	// ** 获取用户的订单详情
	public String getOrder(String userId, String version) {
		JSONObject obj = new JSONObject();
		try {
			if (null == version || "".equals(version)) {
				obj.put("is_success", 0);
				obj.put("message", "缺少参数");
				System.out.println(obj.toString());
				return obj.toString();
			}
			if ("2.0.0".equals(version)) {
				if (null == userId || "".equals(userId)) {
					obj.put("is_success", 0);
					obj.put("message", "缺少参数userId");
					System.out.println(obj.toString());
					return obj.toString();
				}
				FuUser fuUser = fuUserService.get(Long.parseLong(userId));
				if (null == fuUser) {
					obj.put("is_success", 0);
					obj.put("message", "用户不存在");
					System.out.println(obj.toString());
					return obj.toString();
				}

				List<WqqOptions> wqqList = wqqOptionsService.getInfoByUserId(fuUser.getId());
				List<Object> list = new ArrayList<Object>();
				if (null != wqqList && wqqList.size() > 0) {
					for (WqqOptions wqqOptions : wqqList) {
						HashMap<String, Object> map = new HashMap<String, Object>();
						map.put("tradeNo", wqqOptions.getTradeNo() == null ? "" : wqqOptions.getTradeNo());
						map.put("money", wqqOptions.getMoney() == null ? "" : wqqOptions.getMoney());
						map.put("tradeTime", wqqOptions.getPayTime() == null ? "" : DateUtil.getStrFromDate(wqqOptions.getPayTime(), "yyyy-MM-dd HH:mm"));
						map.put("guessType", wqqOptions.getGuessType() == null ? "" : wqqOptions.getGuessType() == 0 ? "看跌" : "看涨");
						map.put("title", wqqOptions.getWqqContents() == null ? "7天涨跌赢第2期" : wqqOptions.getWqqContents().getName() == null ? "7天涨跌赢第2期"
												: wqqOptions.getWqqContents().getName());
						map.put("orderIncome",
								null == wqqOptions ? "7天后开奖"
										: null == wqqOptions.getOrderIncome() ? null == wqqOptions.getWqqContents().getEndTime() ? "7天后开奖" :DateUtil
												.getStrFromDate(wqqOptions
														.getWqqContents()
														.getEndTime(),
														"yyyy-MM-dd")
												+ "兑付收益" : wqqOptions
												.getOrderIncome());
						map.put("region", null == wqqOptions ? ""
								: null == wqqOptions.getWqqContents() ? ""
										: null == wqqOptions.getWqqContents()
												.getUpdownRegion() ? ""
												: wqqOptions.getWqqContents()
														.getUpdownRegion());
						String href = "https://www.hhr360.com/user_options/wqqInfo.htm?id="+wqqOptions.getWqqContents().getId();
						map.put("href", href);
						list.add(map);
					}
					obj.put("is_success", 1);
					obj.put("message", "查询我的订单成功");
					obj.put("wqqList", list);
					System.out.println(obj.toString());
					return obj.toString();

				} else {
					obj.put("is_success", 2);
					obj.put("message", "暂无订单信息");
					obj.put("wqqList", "[]");
					System.out.println(obj.toString());
					return obj.toString();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "系统正在维护, 请稍后再试");

		}
		System.out.println(obj.toString());
		return obj.toString();
	}

	// ** 立即支付
	public String quicklyPay(String userId, String drawPwd, String money,
			String guessType, String version) {
		JSONObject obj = new JSONObject();
		try {
			if (null == version || "".equals(version)) {
				obj.put("is_success", 0);
				obj.put("message", "缺少参数");
				System.out.println(obj.toString());
				return null;
			}
			if ("2.0.0".equals(version)) {
				if (null == money || "".equals(money)) {
					obj.put("is_success", 0);
					obj.put("message", "请填写购买金额");
					System.out.println(obj.toString());
					return null;
				}
				FuUser fuUser = fuUserService.get(Long.parseLong(userId));
				if (null == fuUser) {
					obj.put("is_success", 0);
					obj.put("message", "用户不存在");
					System.out.println(obj.toString());
					return null;
				}
				if (null == drawPwd || "".equals(drawPwd)) {
					obj.put("is_success", 0);
					obj.put("message", "请输入交易密码");
					System.out.println(obj.toString());
					return null;
				}
				if (!drawPwd.equals(fuUser.getDrawPassword())) {
					obj.put("is_success", 0);
					obj.put("message", "交易输入错误, 请重新输入");
					System.out.println(obj.toString());
					return obj.toString();

				}
				BigDecimal accountBalance = fuUser.getAccountBalance();
				BigDecimal payMoney = new BigDecimal(money);
				if (null != accountBalance
						&& accountBalance.compareTo(payMoney) == -1) {
					obj.put("is_success", 0);
					obj.put("message", "账户余额不足, 请先充值");
					System.out.println(obj.toString());
					return obj.toString();
				}

				// 生成订单号
				SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
				String tradeno = sdf.format(new Date())
						+ String.valueOf((int) (Math.random() * 90 + 10));
				// 保存购买弃权期权的的记录以及扣除余额
				FuProduct fuProduct = fuProductService.get(4L);
				wqqOptionsService.saveOptionPayWeb(fuUser, tradeno, payMoney,
						1, Integer.parseInt(guessType), fuProduct
								.getWqqContents() == null ? 0L : fuProduct
								.getWqqContents().getId());
				obj.put("is_success", 1);
				obj.put("message", "购买期权成功");
				return obj.toString();

			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "系统正在维护中, 请稍后再试");
		}
		System.out.println(obj.toString());
		return obj.toString();
	}
}
