package com.hongwei.futures.web.modulesforapp.yjb.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import sun.misc.BASE64Decoder;
import net.sf.json.JSONObject;

import com.hongwei.futures.model.FuMoneyDetail;
import com.hongwei.futures.model.FuStockAccount;
import com.hongwei.futures.model.FuStockMoneyDetail;
import com.hongwei.futures.model.FuStockMoneyInfo;
import com.hongwei.futures.model.FuTransaction;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.FuYjb;
import com.hongwei.futures.model.StockOptionRecord;
import com.hongwei.futures.model.StockShare;
import com.hongwei.futures.model.StockStatusRecord;
import com.hongwei.futures.service.FuMoneyDetailService;
import com.hongwei.futures.service.FuStockAccountService;
import com.hongwei.futures.service.FuStockMoneyDetailService;
import com.hongwei.futures.service.FuStockMoneyInfoService;
import com.hongwei.futures.service.FuTransactionService;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.service.FuYjbService;
import com.hongwei.futures.service.StockOptionRecordService;
import com.hongwei.futures.service.StockStatusRecordService;
import com.hongwei.futures.util.Base64;
import com.hongwei.futures.util.DateUtil;
import com.hongwei.futures.util.OSSUploadUtil;
import com.hongwei.futures.web.modulesforapp.yjb.AppTradeYjbService;

public class AppTradeYjbServiceImpl implements AppTradeYjbService{
	protected final Log log = LogFactory.getLog(AppTradeYjbServiceImpl.class); 
	@Autowired
	private FuYjbService fuYjbService;
	@Autowired
	private FuUserService fuUserService;
	@Autowired
	private FuStockAccountService fuStockAccountService;
	@Autowired
	private FuTransactionService fuTransactionService;
	@Autowired
	private FuStockMoneyDetailService fuStockMoneyDetailService;
	@Autowired
	private FuStockMoneyInfoService fuStockMoneyInfoService;
	@Autowired
	private StockStatusRecordService  stockStatusRecordService;
	@Autowired
	private FuMoneyDetailService fuMoneyDetailService;
	@Autowired
	private StockOptionRecordService optionRecordService;
	// 获取余劵宝主页数据
	public String getIndex(String version) {
		JSONObject obj = new JSONObject();
		try {
			if(null == version || "".equals(version)) {
				obj.put("is_success", 0);
				obj.put("message", "缺少参数");
				System.out.println(new Date() + "-->apptradeYjbServiceImpl/getIndex");
				return obj.toString();
			}
			if("3.0.0".equals(version)) {
				List<FuYjb> yjbList = fuYjbService.findAll();
				if(null != yjbList && yjbList.size() > 0) {
					FuYjb yjb = yjbList.get(0);
					obj.put("market_value", null == yjb ? "" : yjb.getMarketValue());
					obj.put("available", null == yjb ? "" : yjb.getAvailable());
					obj.put("profit_image", null == yjb ? "" : yjb.getProfitImage());
					obj.put("agreement", null == yjb ? "" : yjb.getAgreement());
					obj.put("rank", null == yjb ? "" : yjb.getRank());
					obj.put("more_detail", null == yjb ? "" : yjb.getMoreDetail());
					obj.put("cumulative_value", null == yjb ? "" : yjb.getCumulativeVale());
					obj.put("is_success", "1");
					obj.put("message", "查询余劵宝数据成功");
					System.out.println(new Date() + "-->apptradeYjbServiceImpl/getIndex");
					return obj.toString();
				}else {
					obj.put("is_success", 2);
					obj.put("message", "产品暂时不存在");
					System.out.println(new Date() + "-->apptradeYjbServiceImpl/getIndex");;
					return obj.toString();
				}
				
				
			}else {
				obj.put("is_success", 0);
				obj.put("message", "version 版本号错误");
			}
		}catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "服务器正在更新, 请稍后再试");
		}
		System.out.println(new Date() + "-->apptradeYjbServiceImpl/getIndex");
		return obj.toString();
	}


	// 提交账户的初审资料
	public String examine(String userId, String openEquity, String accountType,
			String stockImage, String stockImageStr, String version) {
		
		JSONObject obj = new JSONObject();
		try {
			if(null == version || "".equals(version)) {
				obj.put("is_success", 0);
				obj.put("message", "缺少参数");
				log.info("缺少参数===>version");
				return obj.toString();
			}
			if(null == userId || "".equals(userId)) {
				obj.put("is_success", 0);
				obj.put("message", "缺少参数");
				log.info("缺少参数====>userId");
				return obj.toString();
			}
			
			FuUser fuUser = fuUserService.get(Long.parseLong(userId));
			if(null == fuUser) {
				obj.put("is_success", 0);
				obj.put("message", "用户不存在");
				log.info("用户不存在===>userId不存在");
				return obj.toString();
			}
			
			if(null == openEquity || "".equals(openEquity)) {
				obj.put("is_success", 0);
				obj.put("message", "请填写开户劵商");
				log.info("请填写开户券商===>openEquity 不存在");
				return obj.toString();
			}
			/*if(null == salesDept || "".equals(salesDept)) {
				obj.put("is_success", 0);
				obj.put("message", "请填写营业部");
				System.out.println(obj.toString() + "--->salesDept");
				return obj.toString();
			}*/
			if(null == accountType || "".equals(accountType)) {
				obj.put("is_success", 0);
				obj.put("message", "请选择账户类型");
				System.out.println(obj.toString() + "--->accountType");
				log.info("请填写账户类型===> accountType 不存在");
				return obj.toString();
			}
			if(null == stockImage || "".equals(stockImage)) {
				obj.put("is_success", 0);
				obj.put("message", "请上传股票截图");
				System.out.println(obj.toString() + "--->stockImage");
				return obj.toString();
			}
			if(null == stockImageStr || "".equals(stockImageStr)) {
				obj.put("is_success", 0);
				obj.put("message", "字节数组缺失");
				System.out.println(obj.toString() + "--->stockImage");
				return obj.toString();
			}
			
			if("3.0.0".equals(version)) {
				Date nowDate = new Date();
				FuStockAccount fsa = new FuStockAccount();
				fsa.setFuUser(fuUser);
				fsa.setOpenEquity(openEquity);
//				fsa.setSalesDept(salesDept);
				fsa.setAccountType(Integer.parseInt(accountType));
				
				// update stockImage
				BASE64Decoder decoder = new BASE64Decoder();
				byte[] fileByte = decoder.decodeBuffer(stockImageStr);
	            for (int i = 0; i < fileByte.length; ++i) {
	                if (fileByte[i] < 0) {// 调整异常数据
	                	fileByte[i] += 256;
	                }
	            }
	            stockImage = OSSUploadUtil.imageFileUpload(fileByte, stockImage,"_stock");
	            fsa.setStockImage(stockImage);
	            
	            fsa.setCreateTime(nowDate);
	            //创建时,更新时间与创建时间相同
	            fsa.setUpdateTime(nowDate);
	            fsa.setIsDel(0); // 可用账号
	            fsa.setExamineStatus(0);// 审核中状态
	            fsa.setIsPublish(0); // 未发布状态  
	            fsa.setState(0);
	            fuStockAccountService.save(fsa);
	            
	            obj.put("is_success", "1");
				obj.put("message", "添加股票账户成功");
			}else {
				obj.put("is_success", 0);
				obj.put("message", "version 版本号错误");
			}
		}catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "服务器正在更新, 请稍后再试");
			log.error(e);
		}
		log.info("保存账户基本信息成功");
		return obj.toString();
	}


	// 获取当前用户的可用账户的首页信息
	@SuppressWarnings("static-access")
	public String finshIndex(String userId, String version) {
		JSONObject obj = new JSONObject();
		try {
			if(null == version || "".equals(version)) {
				obj.put("is_success", 0);
				obj.put("message", "缺少参数");
				System.out.println(obj.toString());
				return obj.toString();
			}
			if(null == userId || "".equals(userId)) {
				obj.put("is_success", 0);
				obj.put("message", "请先登录");
				return obj.toString();
			}
			FuUser fuUser = fuUserService.get(Long.parseLong(userId));
			if(null == fuUser) {
				obj.put("is_success", 0);
				obj.put("message", "该用户不存在");
				return obj.toString();
			}
			// 根据用户查询股票账户信息
			HashMap<String, Object> map2 = new HashMap<String, Object>();
			map2.put("userId", fuUser.getId());
			//map2.put("examineStatus", 1);
			map2.put("isDel", 0);
			List<FuStockAccount> fsaList = fuStockAccountService.findAccountByMap(0, 100, map2);
			if("3.0.0".equals(version)) {
				if(null != fsaList && fsaList.size() > 0) {
					BigDecimal totalProfit = new BigDecimal(0); // 昨日所有账号盈亏和
										
					List<Object> list = new ArrayList<Object>();
					for (FuStockAccount fuStockAccount : fsaList) {
						HashMap<String, Object> map = new HashMap<String, Object>();
						map.put("stock_account_id", fuStockAccount.getId());
						map.put("capital_account", null == fuStockAccount.getCapitalAccount() ? " " : fuStockAccount.getCapitalAccount());
						map.put("open_equity", fuStockAccount.getOpenEquity() == null ? "" : fuStockAccount.getOpenEquity());
						map.put("examine_status", fuStockAccount.getExamineStatus());
						map.put("state", fuStockAccount.getState());
						// 状态0开启委托，1暂停委托，2申请开启委托中，3申请暂停委托中，4申请结算中
						if(fuStockAccount.getState() == 4) {
							// 根据状态和股票账号查询这股票的结算日期
							StockStatusRecord statusRecord = stockStatusRecordService.findStatusByAccountId(fuStockAccount.getId());
							map.put("del_time", null == statusRecord ? "" : DateUtil.changeTime(statusRecord.getChangeTime(), "yyyy-MM-dd", 1));
						}
						if(fuStockAccount.getExamineStatus() == 3) {
							// transactionName
							if(null == fuStockAccount.getTransactionId()) {
								map.put("transaction_name", "交易团队接单中");
								map.put("examine_status", 3);
							}else {
								// 接单成功
								FuTransaction fuTransaction = fuTransactionService.get(fuStockAccount.getTransactionId());
								map.put("transaction_name", null == fuTransaction ? "" : null == fuTransaction.getName() ? "" : fuTransaction.getName());
								map.put("examine_status", 4);
							}						
						}
						// 根据开户账户id 查询 开户详细信息
						FuStockMoneyDetail stockDetail = fuStockMoneyDetailService.findDetailByStockId(fuStockAccount.getId());
						if(stockDetail != null){
							totalProfit = totalProfit.add(stockDetail.getNowProfit());
						}
						
						// 根据stockId和userId查询 fuStockMoneyDetail 最大的五个值
						List<FuStockMoneyDetail> sdList = fuStockMoneyDetailService.findByUserIdAndStockId(fuUser.getId(), fuStockAccount.getId());
						if(null == sdList || sdList.size() <= 0) {
							Date createTime = null;
							List<Object> nullList = new ArrayList<Object>();
							int num = 0;
							for(int j = 5-sdList.size(); j > 0; j--) {
								Map<String, Object> sdMap = new HashMap<String, Object>();
								if(null == createTime) {
									// 起息日
									createTime = fuStockAccount.getCreateTime();
								}
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(DateUtil.getDateFromString(DateUtil.getStrFromDate(null == createTime ? new Date() : createTime, "yyyy.MM.dd"), "yyyy.MM.dd"));
								calendar.add(calendar.DATE, 1);
								Date time = calendar.getTime();
								String now = DateUtil.getStrFromDate(time, "M.dd");
								if(num == 0) {
									sdMap.put("available", null == fuStockAccount.getAvailable() ? "" : fuStockAccount.getAvailable());
									sdMap.put("time", DateUtil.getStrFromDate(fuStockAccount.getCreateTime(), "M.d"));
								}else {
									createTime = time;
									sdMap.put("available", "");
									sdMap.put("time", now);
								}
								num++;
								sdMap.put("stock_value", "0");
								nullList.add(sdMap);
							}
							map.put("stock_detail_list", nullList);
						}else if(null != sdList && (sdList.size() > 0 && sdList.size() < 5)) {
							Date createTime = null;
							List<Object> oList = new ArrayList<Object>();
							for(int j = 5-sdList.size()-1; j > 0; j--) {
								Map<String, Object> sdMap = new HashMap<String, Object>();
								if(null == createTime) {
									createTime = sdList.get(0).getTradeTime();
								}
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(DateUtil.getDateFromString(DateUtil.getStrFromDate(createTime, "yyyy.MM.dd"), "yyyy.MM.dd"));
								calendar.add(calendar.DATE, 1);
								Date time = calendar.getTime();
								String now = DateUtil.getStrFromDate(time, "M.dd");
								createTime = time;
								sdMap.put("available", "");
								sdMap.put("stock_value", "");
								sdMap.put("time", now);
								oList.add(sdMap);								
							}
							// 排序
							List<Object> list3 = new ArrayList<Object>();
							for(int j = oList.size()-1; j >= 0; j--) {
								list3.add(oList.get(j));
							}
							
//							List<Object> lList = new ArrayList<Object>();
							for(FuStockMoneyDetail fmd : sdList) {
								Map<String, Object> sdMap = new HashMap<String, Object>();
								BigDecimal nowProfit = fmd.getNowProfit(); // 昨日盈亏
								// 黄线 股票市值
								BigDecimal available = fmd.getTotalValue(); // 股票市值
								//(盈利市值) 深黄 股票市值//+昨日盈亏
//								BigDecimal profitValue = available.add(nowProfit);
								sdMap.put("available", null == available ? "" : available);
								sdMap.put("stock_value", null == nowProfit ? "" : nowProfit);
								sdMap.put("time", DateUtil.getStrFromDate(fmd.getTradeTime(), "M.dd"));
								list3.add(sdMap);
							}
							Map<String, Object> m = new HashMap<String, Object>();
							m.put("available", null == fuStockAccount.getAvailable() ? "" : fuStockAccount.getAvailable());
							m.put("stock_value", "");
							//String changeTime = DateUtil.changeTime(fuStockAccount.getCreateTime(), "yyyy-MM-dd", 1);
							m.put("time", DateUtil.getStrFromDate(fuStockAccount.getCreateTime(), "M.dd"));
							list3.add(m);
							//oList.addAll(list3);
							/*List<Object> fList = new ArrayList<Object>();
							for (Object object : oList) {
								fList.add(object);
							}*/
							
							List<Object> fList1 = new ArrayList<Object>();
							for(int k = list3.size()-1; k >=0 ; k--) {
								fList1.add(list3.get(k));
							}
							map.put("stock_detail_list", fList1);
						}else {
							List<Object> list1 = new ArrayList<Object>();
							for (FuStockMoneyDetail fmd : sdList) {
								Map<String, Object> map1 = new HashMap<String, Object>();
								BigDecimal nowProfit = fmd.getNowProfit(); // 昨日盈亏
								// 黄线 股票市值
								BigDecimal available = fmd.getTotalValue(); // 股票市值
								//(盈利市值) 深黄 股票市值 //+昨日盈亏
//								BigDecimal profitValue = available.add(nowProfit);
								map1.put("available", null == available ? "" : available);
								map1.put("stock_value", null == nowProfit ? "" : nowProfit);
								map1.put("time", DateUtil.getStrFromDate(fmd.getTradeTime(), "M.dd"));
								list1.add(map1);
							}
							
							List<Object> list2 = new ArrayList<Object>();
							for(int i = list1.size()-1; i >= 0; i--) {
								list2.add(list1.get(i));
							}
							
							map.put("stock_detail_list", list2);
						}
						list.add(map);
						
					}
					obj.put("stocks", list);
					// 昨日所有账户加起来的盈亏
					obj.put("day_total_profit",  BigDecimal.ZERO.compareTo(totalProfit) == 0 ? "0.00" : totalProfit);
					
					Map<String, Object> infoParames = new HashMap<String, Object>();
					infoParames.put("userId", fuUser.getId());
					FuStockMoneyInfo moneyInfo = fuStockMoneyInfoService.findMoneyInfoByMap(infoParames);
					// 所有账户 所有的累积盈亏
					obj.put("total_profit", null == moneyInfo ? "0.00" : moneyInfo.getProfitInfo() == null ? 0.00 : moneyInfo.getProfitInfo());
					//该用户所有的未缴费用
					obj.put("none_total_fee", null == moneyInfo ? "0.00" : moneyInfo.getNoneFeeInfo() ==  null ? 0.00 : moneyInfo.getNoneFeeInfo());

					obj.put("message", "查询股票账户信息");
					obj.put("is_success", 1);
				}else {
					obj.put("message", "没有数据!");
					obj.put("is_success", 2);
					obj.put("stocks", "[]");
				}
				
			}else {
				obj.put("is_success", 0);
				obj.put("message", "version 版本号错误");
			}
		}catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "服务器正在更新, 请稍后再试");
		}
		System.out.println(new Date() + "-->apptradeYjbServiceImpl/finshIndex");
		return obj.toString();
	}


	// 进一步完善账号信息
	public String refined(String stockId, String salesDept,
			String capitalAccount, String capitalPassword, String version) {
		JSONObject obj = new JSONObject();
		try {
			if(null == version || "".equals(version)) {
				obj.put("is_success", 0);
				obj.put("message", "缺少参数");
				System.out.println(obj.toString());
				return obj.toString();
			}
			if(null == stockId || "".equals(stockId)){
				obj.put("is_success", 0);
				obj.put("message", "缺少参数");
				log.info("缺少参数===>stockId不存在");
				return obj.toString();
			}
			if(null == salesDept || "".equals(salesDept)){
				obj.put("is_success", 0);
				obj.put("message", "请填写营业部名称");
				log.info("请填写营业部名称===>salesDept");
				return obj.toString();
			}
			
			/*if(null == accountType || "".equals(accountType)) {
				obj.put("is_success", 0);
				obj.put("message", "请选择账户类型");
				System.out.println(obj.toString() + "--->accountType");
				log.info("请填写账户类型===> accountType 不存在");
				return obj.toString();
			}*/
			if(null == capitalAccount || "".equals(capitalAccount)){
				obj.put("is_success", 0);
				obj.put("message", "请填写资金账号");
				log.info("请填写资金账号===>capitalAccount");
				return obj.toString();
			}
			if(null == capitalPassword || "".equals(capitalPassword)){
				obj.put("is_success", 0);
				obj.put("message", "请填写资金密码");
				System.out.println(obj.toString() + "--->capitalPassword");
				log.info(obj.toString());
				return obj.toString();
			}
			// 根据stockId 查询股票信息
			FuStockAccount fuStockAccount = fuStockAccountService.get(Long.parseLong(stockId));
			if(null == fuStockAccount) {
				obj.put("is_success", 0);
				obj.put("message", stockId + "股票账号不存在");
				log.info(obj.toString());
				return obj.toString();
			}
			if("3.0.0".equals(version)) {
				fuStockAccount.setSalesDept(salesDept);
//				fuStockAccount.setAccountType(Integer.parseInt(accountType));
				fuStockAccount.setCapitalAccount(capitalAccount);
				fuStockAccount.setCapitalPassword(new String(Base64.encode(capitalPassword.getBytes())));
				fuStockAccount.setExamineStatus(3); // 准备接单
				fuStockAccount.setState(0); // 准备接单
				fuStockAccount.setUpdateTime(new Date());
				fuStockAccountService.save(fuStockAccount);
				
				obj.put("is_success", 1);
				obj.put("message", "完善信息成功");
			}else {
				obj.put("is_success", 0);
				obj.put("message", "version 版本号错误");
			}
		}catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "服务器正在更新, 请稍后再试");
			log.error(e);
			log.info(obj.toString());
		}
		log.info(obj.toString());
		return obj.toString();
	}


	// 根据账号查询这个账号的具体信息
	public String getInfo(String stockId, String version) {
		JSONObject obj = new JSONObject();
		try {
			if(null == version || "".equals(version)) {
				obj.put("is_success", 0);
				obj.put("message", "缺少参数");
				System.out.println(obj.toString());
				return obj.toString();
			}
			if(null == stockId || "".equals(stockId)) {
				obj.put("is_success", 0);
				obj.put("message", "缺少参数");
				System.out.println(obj.toString() + "--->stockId");
				return obj.toString();
			}
			// 根据stockId 查询股票信息
			FuStockAccount fuStockAccount = fuStockAccountService.get(Long.parseLong(stockId));
			if(null == fuStockAccount) {
				obj.put("is_success", 0);
				obj.put("message", stockId + "股票账号不存在");
				System.out.println(obj.toString());
				return obj.toString();
			}
			/*if(4 != fuStockAccount.getExamineStatus()) { // 已经被接单
				obj.put("is_success", 0);
				obj.put("message", "账号接单中");
				System.out.println(obj.toString());
				return obj.toString();
			}*/
			if("3.0.0".equals(version)) {
				obj.put("open_equity", null == fuStockAccount.getOpenEquity() ? "" : fuStockAccount.getOpenEquity());
				FuTransaction fuTransaction = fuTransactionService.get(fuStockAccount.getTransactionId());
				obj.put("transaction_name", null == fuTransaction ? "" : fuTransaction.getName());
				obj.put("profit_model", null == fuStockAccount.getProfitModel() ? "" : fuStockAccount.getProfitModel());
				obj.put("available", null == fuStockAccount.getAvailable() ? "" : fuStockAccount.getAvailable());
				obj.put("available_split", null == fuStockAccount.getAvailableSplit() ? "" : fuStockAccount.getAvailableSplit());
				obj.put("able_money", null == fuStockAccount.getAbleMoney() ? "" : fuStockAccount.getAbleMoney());
				obj.put("able_money_split", null == fuStockAccount.getAbleMoneySplit() ? "" : fuStockAccount.getAbleMoneySplit());
				obj.put("account_type", null == fuStockAccount.getAccountType() ? "0" : fuStockAccount.getAccountType());
				obj.put("sales_dept", null == fuStockAccount.getSalesDept() ? "" : fuStockAccount.getSalesDept());
				obj.put("capital_account", null == fuStockAccount.getCapitalAccount() ? "" : fuStockAccount.getCapitalAccount());
				// 账号状态
				obj.put("status", null == fuStockAccount.getState() ? "" : fuStockAccount.getState());
				// 这个账号总共盈利
//				List<FuStockMoneyDetail> detailList = fuStockMoneyDetailService.findByStockId(fuStockAccount.getId());
				BigDecimal sumProfit = fuStockMoneyDetailService.getSumProfit(fuStockAccount.getId());
				if(null != sumProfit) {
					obj.put("profit_count", sumProfit);
				}else {
					obj.put("profit_count", 0);
				}
				obj.put("is_success", 1);
				obj.put("message", "查询账号信息成功");
				
			}else {
				obj.put("is_success", 0);
				obj.put("message", "version 版本号错误");
			}
		}catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "服务器正在更新, 请稍后再试");
		}
		System.out.println(new Date() + "-->apptradeYjbServiceImpl/getInfo");
		return obj.toString();
	}


	// 累计盈利列表
	public String getProfitList(String userId, String version) {
		JSONObject obj = new JSONObject();
		try {
			if(null == version || "".equals(version)) {
				obj.put("is_success", 0);
				obj.put("message", "缺少参数");
				System.out.println(obj.toString());
				return obj.toString();
			}
			if(null == userId || "".equals(userId)){
				obj.put("is_success", 0);
				obj.put("message", "缺少参数");
				System.out.println(obj.toString() + "--->userId");
				return obj.toString();
			}
			FuUser user = fuUserService.get(Long.parseLong(userId));
			if(null == user){
				obj.put("is_success", 0);
				obj.put("message", "用户不存在");
				System.out.println(obj.toString() + "--->userId");
				return obj.toString();
			}
			if("3.0.0".equals(version)) {
				// 根据userid 查询stock_money_detail
				List<FuStockMoneyDetail> detailList = fuStockMoneyDetailService.findDetailByUserId(user.getId(), null, null);
				if(detailList == null || detailList.size() <= 0){
					obj.put("is_success", 2);
					obj.put("profits", "[]");
					obj.put("message", "暂无盈利记录");
					System.out.println(obj);
					return obj.toString();
				}
				List<Object> list = new ArrayList<Object>();
				for (FuStockMoneyDetail fuStockMoneyDetail : detailList) {
					Map<String, Object> map = new HashMap<String, Object>();
					// 开户劵商
					map.put("open_equity", fuStockMoneyDetail.getFuStockAccount().getOpenEquity() == null ? "" : fuStockMoneyDetail.getFuStockAccount().getOpenEquity());
					map.put("partnter_account", fuStockMoneyDetail.getFuStockAccount().getPartnerAccount() == null ? "" : fuStockMoneyDetail.getFuStockAccount().getPartnerAccount());
					// 当日盈利
					map.put("now_profit", fuStockMoneyDetail.getNowProfit() == null ? 0 : fuStockMoneyDetail.getNowProfit());
					map.put("trade_time", fuStockMoneyDetail.getTradeTime() == null ? "" : DateUtil.getStrFromDate(fuStockMoneyDetail.getTradeTime(), "yyyy-MM-dd"));
					map.put("capital_account", fuStockMoneyDetail.getFuStockAccount().getCapitalAccount() == null ? "" : fuStockMoneyDetail.getFuStockAccount().getCapitalAccount());
					list.add(map);
					
				}
				obj.put("profits", list);
				obj.put("is_success", 1);
				obj.put("message", "每日盈亏详细查询成功");
				
			}else {
				obj.put("is_success", 0);
				obj.put("message", "version 版本号错误");
			}
		}catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "服务器正在更新, 请稍后再试");
		}
		System.out.println(new Date() + "-->apptradeYjbServiceImpl/getProfitList");
		return obj.toString();
	}

	// 管理费列表
	public String manageFeeList(String userId, String version) {
		JSONObject obj = new JSONObject();
		try {
			if(null == version || "".equals(version)) {
				obj.put("is_success", 0);
				obj.put("message", "缺少参数");
				System.out.println(obj.toString());
				return obj.toString();
			}
			if(null == userId || "".equals(userId)){
				obj.put("is_success", 0);
				obj.put("message", "缺少参数");
				System.out.println(obj.toString() + "--->userId");
				return obj.toString();
			}
			FuUser user = fuUserService.get(Long.parseLong(userId));
			if(null == user){
				obj.put("is_success", 0);
				obj.put("message", "用户不存在");
				System.out.println(obj.toString() + "--->userId");
				return obj.toString();
			}
			if("3.0.0".equals(version)) {
				// 累计未缴管理费
				FuStockMoneyInfo countInfo = fuStockMoneyInfoService.findStockMoneyInfoByUserId(user.getId());
				obj.put("none_fee_info", null == countInfo ? "" : null == countInfo.getNoneFeeInfo() ? 0.00 : countInfo.getNoneFeeInfo());
				obj.put("account_balance", null == countInfo ? "" : null == user.getAccountBalance() ? 0.00 : user.getAccountBalance());
				// 根据用户查询这个用户下面股票账户的管理费信息
				List<FuStockMoneyDetail> detailList = fuStockMoneyDetailService.findDetailByUserId(user.getId(), 0, 1000);
				if(null != detailList && detailList.size() > 0) {
					List<Object> list = new ArrayList<Object>();
					for (FuStockMoneyDetail detail : detailList) {
						HashMap<String, Object> map = new HashMap<String, Object>();
						map.put("open_equity", null == detail.getFuStockAccount().getOpenEquity() ? "" : detail.getFuStockAccount().getOpenEquity());
						map.put("capital_account", null == detail.getFuStockAccount().getCapitalAccount() ? "" : detail.getFuStockAccount().getCapitalAccount());
						map.put("trade_time", null == detail.getTradeTime() ? "" : DateUtil.getStrFromDate(detail.getTradeTime(), "yyyy-MM-dd"));
						// 管理费  = 每日管理费 + 
						map.put("manage_fee", detail.getManageFee().add(new BigDecimal(0)));
						list.add(map);
					}
					obj.put("is_success", 1);
					obj.put("message", "查询管理费成功");
					obj.put("manage_list", list);
				}else {
					obj.put("is_success", 1);
					obj.put("message", "暂无管理费记录");
					obj.put("manage_list", "[]");
				}
			}else {
				obj.put("is_success", 0);
				obj.put("message", "version 版本号错误");
			}
		}catch(Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "服务器正在更新, 请稍后再试");
		}
		System.out.println(new Date() + "-->apptradeYjbServiceImpl/manageFeeList");
		return obj.toString();
	}


	// 修改股票账户的状态
	public String updateState(String stockId, String version) {
		JSONObject obj = new JSONObject();
		try {
			if(null == version || "".equals(version)) {
				obj.put("is_success", 0);
				obj.put("message", "缺少参数");
				System.out.println(obj.toString());
				return obj.toString();
			}
			if("3.0.0".equals(version)) {
				if(null == stockId || "".equals(stockId)){
					obj.put("is_success", 0);
					obj.put("message", "accountId不能为空!");
					System.out.println(obj);
					return obj.toString();
				}
				FuStockAccount fuStockAccount = fuStockAccountService.get(Long.parseLong(stockId));
				if(fuStockAccount == null){
					obj.put("is_success", 0);
					obj.put("message", stockId+"的股票账号不存在!");
					System.out.println(obj);
					return obj.toString();
				}
				FuUser fuUser = fuStockAccount.getFuUser();
				if(fuUser == null){
					obj.put("is_success", 0);
					obj.put("message", fuStockAccount.getFuUser().getId()+"的用户不存在!");
					System.out.println(obj);
					return obj.toString();
				}
				
				// 根据股票账户的id 和当前时间查询 是否有这条记录
				String currentDate = DateUtil.getStrFromDate(new Date(), "yyyy-MM-dd");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
				calendar.add(Calendar.DATE, 1);
				Date time = calendar.getTime();
				String maxDate = DateUtil.getStrFromDate(time, "yyyy-MM-dd");
				StockStatusRecord ss = stockStatusRecordService.findStatusByCurrentDate(Long.parseLong(stockId), currentDate, maxDate);
				if(null != ss) {
					obj.put("is_success", 0);
					obj.put("message", "今天不可以做该操作");
					System.out.println(obj);
					return obj.toString();
				}else {
					
					//状态 0开启委托，1暂停委托，2申请开启委托中，3申请暂停委托中
					int accStatus = fuStockAccount.getState();
					//0:申请开启委托  1:申请暂停委托   3申请开启委托中  4申请暂停委托中
					int statusBefore = 0; 
					if(accStatus == 0){
						statusBefore = 1;
					}else if(accStatus == 1){
						statusBefore = 0;
					}else if(accStatus == 2){
						statusBefore = 3;
					}else if(accStatus == 3){
						statusBefore = 4;
					}
					int statusAfter = 0;
					if(accStatus == 0 && statusBefore == 1){
						statusAfter = 1;
						statusBefore = 4;
						accStatus = 3;
						// 账户当前的状态
						obj.put("operation_status_before", 4);
					}else if(accStatus == 1 && statusBefore == 0){
						statusAfter = 0;
						statusBefore = 3;
						accStatus = 2;
						// 用户当前可以操作的状态
						obj.put("operation_status_before", 3);
					}else {
						obj.put("is_success", 0);
						obj.put("message", "今日不可操作");
						System.out.println(obj);
						return obj.toString();
					}
					// 创建一个
					StockStatusRecord stockStatus = new StockStatusRecord();
					stockStatus.setChangeTime(new Date());
					stockStatus.setOperationStatus(statusBefore);
					stockStatus.setIsOperation(0);
					stockStatus.setAfterStatus(statusAfter);
					stockStatus.setFuStockAccount(fuStockAccount);
					stockStatus.setFuUser(fuUser);
					stockStatusRecordService.save(stockStatus);
					
					fuStockAccount.setState(accStatus);
					fuStockAccountService.save(fuStockAccount);
					obj.put("is_success",1);
					obj.put("message", "修改状态成功!");
				}
				
			}else {
				obj.put("is_success", 0);
				obj.put("message", "version 版本号错误");
			}
		}catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "服务器正在更新, 请稍后再试");
		}
		System.out.println(new Date() + "-->apptradeYjbServiceImpl/updateState");
		return obj.toString();
	}

	// 缴费记录列表
	public String payRecords(String userId, String version) {
		JSONObject obj = new JSONObject();
		try {
			if(null == version || "".equals(version)) {
				obj.put("is_success", 0);
				obj.put("message", "缺少参数");
				System.out.println(obj.toString());
				return obj.toString();
			}
			if(null == userId || "".equals(userId)) {
				obj.put("is_success", 0);
				obj.put("message", "缺少参数");
				System.out.println(obj.toString() + "---" + userId);
				return obj.toString();
			}
			
			FuUser fuUser = fuUserService.get(Long.parseLong(userId));
			if(null == fuUser) {
				obj.put("is_success", 0);
				obj.put("message", "该用户不存在");
				System.out.println(obj.toString());
				return obj.toString();
			}
			if("3.0.0".equals(version)) {
				// 查询出这个用户所有的缴费记录
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("userId", fuUser.getId());
				map1.put("dictionaryId", 14L);
				List<FuMoneyDetail> detailList = fuMoneyDetailService.findFuMoneyDetailByParams(map1);
				if(null != detailList && detailList.size() > 0) {
					List<Object> list = new ArrayList<Object>();
					for (FuMoneyDetail detail : detailList) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("trade_time", DateUtil.getStrFromDate(detail.getTime(), "yyyy-MM-dd"));
						map.put("money", null == detail.getMoney() ? "0.00" : detail.getMoney());
						list.add(map);
					}
					obj.put("pay_records", list);
					obj.put("is_success", 1);
					obj.put("message", "查询缴费记录成功");
				}else {
					obj.put("pay_records", "[]");
					obj.put("is_success", 2);
					obj.put("message", "暂无缴费记录");
				}
				
			}else {
				obj.put("is_success", 0);
				obj.put("message", "version 版本号错误");
			}
		}catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "服务器正在更新, 请稍后再试");
		}
		System.out.println(new Date() + "-->apptradeYjbServiceImpl/payRecords");
		return obj.toString();
	}

	// 删除审核失败的股票账号
	public String delExaminFail(String userId, String stockId, String version) {
		JSONObject obj = new JSONObject();
		try {
			if(null == version || "".equals(version)) {
				obj.put("is_success", 0);
				obj.put("message", "缺少参数");
				System.out.println(obj.toString());
				return obj.toString();
			}
			if(null == userId || "".equals(userId)) {
				obj.put("is_success", 0);
				obj.put("message", "缺少参数");
				System.out.println(obj.toString() + "---" + userId);
				return obj.toString();
			}
			
			FuUser fuUser = fuUserService.get(Long.parseLong(userId));
			if(null == fuUser) {
				obj.put("is_success", 0);
				obj.put("message", "该用户不存在");
				System.out.println(obj.toString());
				return obj.toString();
			}
			
			if(null == stockId || "".equals(stockId)) {
				obj.put("is_success", 0);
				obj.put("message", "缺少参数---->" + stockId);
				System.out.println(obj.toString());
				return obj.toString();
			}
			FuStockAccount stockAccount = fuStockAccountService.get(Long.parseLong(stockId));
			if(null == stockAccount) {
				obj.put("is_success", 0);
				obj.put("message", "股票账户不存在");
				System.out.println(obj.toString());
				return obj.toString();
			}
			if(null == stockAccount.getExamineStatus() || stockAccount.getExamineStatus() != 2){
				obj.put("is_success", 0);
				obj.put("message", "该股票账户不能删除");
				System.out.println(obj.toString());
				return obj.toString();
			}
			if("3.0.0".equals(version)) {
				fuStockAccountService.delete(stockAccount.getId());
				if(null == userId || "".equals(userId)) {
					obj.put("is_stock", 0);  // 没有可用的账户
				}else {
					int isDel = 0;
//					int examineStatus = 1;
					List<FuStockAccount> accountList = fuStockAccountService.findByUserIdAndIsDel(Long.parseLong(userId), isDel);
					if(null != accountList && accountList.size() > 0) {
						obj.put("is_stock", 1);
					}else {
						obj.put("is_stock", 0);
					}
				}
				obj.put("is_success", 1);
				obj.put("message", "删除审核失败的股票账号成功");
			}else {
				obj.put("is_success", 0);
				obj.put("message", "version 版本号错误");
			}
		}catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "服务器正在更新, 请稍后再试");
		}
		System.out.println(new Date() + "-->apptradeYjbServiceImpl/delExaminFail");
		System.out.println(obj.toString());
		return obj.toString();

	}

	// 根据stockAccount的主键查询出股票账号信息以及具体股票信息
	public String getShareInfo(String stockId, String version) {
		JSONObject obj = new JSONObject();
		if(null == version || "".equals(version)) {
			obj.put("is_success", 0);
			obj.put("message", "缺少参数");
			System.out.println(obj.toString());
			return obj.toString();
		}

		if(null == stockId || "".equals(stockId)) {
			obj.put("is_success", 0);
			obj.put("message", "缺少参数-->stockId");
			System.out.println(obj.toString());
			return obj.toString();
		}
		FuStockAccount fuStockAccount = fuStockAccountService.get(Long.parseLong(stockId));
		if(null == fuStockAccount) {
			obj.put("is_success", 0);
			obj.put("message", "股票账户不存在");
			System.out.println(obj.toString());
			return obj.toString();
		}
		if (fuStockAccount.getState() == 1) { 
			obj.put("is_success", 0);
			obj.put("message", "客户已暂停");
			System.out.println(obj.toString());
			return obj.toString();

		}else if (fuStockAccount.getState() == 3) {
			obj.put("is_success", 0);
			obj.put("message", "客户已申请暂停");
			System.out.println(obj.toString());
			return obj.toString();
		}else if (fuStockAccount.getState() == 4) {
			obj.put("is_success", 0);
			obj.put("message", "客户已删除账户");
			System.out.println(obj.toString());
			return obj.toString();
		}
		if("3.0.0".equals(version)) {
			obj.put("open_equity", null == fuStockAccount.getOpenEquity() ? "" : fuStockAccount.getOpenEquity());
			obj.put("capital_account", null == fuStockAccount.getCapitalAccount() ? "" : fuStockAccount.getCapitalAccount());
			obj.put("capital_password", null == fuStockAccount.getCapitalPassword() ? "" : new String(Base64.decode(fuStockAccount.getCapitalPassword())));
			obj.put("available", null == fuStockAccount.getAvailable() ? "0" : fuStockAccount.getAvailable());
			obj.put("able_money", null == fuStockAccount.getAbleMoney() ? "0" : fuStockAccount.getAbleMoney());
			obj.put("commission", null == fuStockAccount.getCommission() ? "" : fuStockAccount.getCommission());
			obj.put("account_type", null == fuStockAccount.getAccountType() ? "" : fuStockAccount.getAccountType());
			obj.put("sales_dept", null == fuStockAccount.getSalesDept() ? "" : fuStockAccount.getSalesDept());
			// 0:审核中  1:审核成功 2:审核失败  3:接单中 4:接单成功
			obj.put("examine_status", fuStockAccount.getExamineStatus());
			// 交易团队的是否退券状态  0: 未退券  1:退券
			obj.put("transaction_status", fuStockAccount.getTransactionStatus());
			obj.put("create_time", DateUtil.getStrFromDate(null == fuStockAccount.getUpdateTime() ? fuStockAccount.getCreateTime() : fuStockAccount.getUpdateTime(), "yyyy.MM.dd"));
			obj.put("order_time", null == fuStockAccount.getOrderTime() ? "" : DateUtil.getStrFromDate(fuStockAccount.getOrderTime(), "yyyy.MM.dd"));
			Set<StockShare> ssList = fuStockAccount.getSsList();
			if(null != ssList && ssList.size() > 0) {
				List<Object> list = new ArrayList<Object>();
				for (StockShare stockShare : ssList) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("name", null == stockShare.getName() ? "" : stockShare.getName());
					map.put("code", null == stockShare.getCode() ? "" : stockShare.getCode());
					map.put("number", stockShare.getNumber());
					list.add(map);
				}
				obj.put("ssList", list);
			}else {
				obj.put("ssList", "[]");
			}
			obj.put("is_success", 1);
			obj.put("message", "查询账户信息成功");
		}else {
			obj.put("is_success", 0);
			obj.put("message", "version 版本号错误");
		}
		System.out.println(DateUtil.getLongStrFromDate(new Date()) + "app_trade_yjb/getShareInfo.htm");
		return obj.toString();
	}


	// 查询所有可以抢的股票账户
	public String findAllRqb(String version) {
		JSONObject obj = new JSONObject();
		if(null == version || "".equals(version)) {
			obj.put("is_success", 0);
			obj.put("message", "缺少参数");
			log.info(obj.toString());
			return obj.toString();
		}
		if("3.0.0".equals(version)) {
			// 查询所有可以抢的股票
			List<FuStockAccount> accountList = fuStockAccountService.findByStatus(3, 0);
			if(null != accountList && accountList.size() > 0) {
				List<Object> list = new ArrayList<Object>();
				for (FuStockAccount fuStockAccount : accountList) {
					Map<String, Object> map = new HashMap<String, Object>();
					
					map.put("open_equity", null == fuStockAccount.getOpenEquity() ? "" : fuStockAccount.getOpenEquity());
					map.put("capital_account", null == fuStockAccount.getCapitalAccount() ? "" : fuStockAccount.getCapitalAccount()); 
					map.put("examine_status", fuStockAccount.getExamineStatus() == null ? 3 : fuStockAccount.getExamineStatus());
					map.put("available", null == fuStockAccount.getAvailable() ? "" : fuStockAccount.getAvailable());
					map.put("able_money", null == fuStockAccount.getAbleMoney() ? "" : fuStockAccount.getAbleMoney());
					map.put("create_time", DateUtil.getStrFromDate(null == fuStockAccount.getUpdateTime() ? fuStockAccount.getCreateTime() : fuStockAccount.getUpdateTime(), "yyyy.MM.dd"));
					map.put("stock_id", fuStockAccount.getId());
					list.add(map);
				}
				obj.put("is_success", 1);
				obj.put("message", "查询抢单成功");
				obj.put("orders", list);
				log.info(obj.toString());
				return obj.toString();
			}else {
				obj.put("is_success", 2);
				obj.put("message", "暂无可以抢的订单");
				obj.put("orders", "[]");
				log.info(obj.toString());
				return obj.toString();
			}
		}else {
			obj.put("is_success", 0);
			obj.put("message", "version 版本号错误");
		}
		log.info(obj.toString());
		return obj.toString();
	}

	// 抢接单中的股票账户
	public String orderRqb(String userId, String stockId, String version) {
		JSONObject obj = new JSONObject();
		if (null == version || "".equals(version)) {
			obj.put("is_success", 0);
			obj.put("message", "缺少参数");
			System.out.println(obj.toString());
			return obj.toString();
		}
		if (null == stockId || "".equals(stockId)) {
			obj.put("is_success", 0);
			obj.put("message", "缺少参数STOCKID");
			log.info(obj.toString());
			return obj.toString();
		}
		if (null == userId || "".equals(userId)){
			obj.put("is_success", 0);
			obj.put("message", "缺少参数USERID");
			log.info(obj.toString());
			return obj.toString();
		}
		FuUser fuUser = fuUserService.get(Long.parseLong(userId));
		if (null == fuUser) {
			obj.put("is_success", 0);
			obj.put("message", "用户不存在");
			log.info(obj.toString());
			return obj.toString();
		}
		Integer hhrType = fuUser.getHhrType();
		if(null == hhrType || "".equals(hhrType) || hhrType != 2) {
			obj.put("is_success", 0);
			obj.put("message", "用户不是商家");
			log.info(obj.toString());
			return obj.toString();
		}
		FuTransaction fuTransaction = fuTransactionService.findByUserId(fuUser.getId());
		if(null == fuTransaction) {
			obj.put("is_success", 0);
			obj.put("message", "当前用户不是交易团队");
			log.info(obj.toString());
			return obj.toString();
		}
		FuStockAccount fuStockAccount = fuStockAccountService.get(Long.parseLong(stockId));
		if (null == fuStockAccount) {
			obj.put("is_success", 0);
			obj.put("message", "股票账户不存在");
			log.info(obj.toString());
			return obj.toString();
		}
		if (fuStockAccount.getExamineStatus() != 3) {
			obj.put("is_success", 0);
			obj.put("message", "股票已经被抢");
			log.info(obj.toString());
			return obj.toString();
		}
		if ("3.0.0".equals(version)) {
			fuStockAccount.setTransactionId(fuTransaction.getId());
			fuStockAccount.setOrderTime(new Date());
			fuStockAccount.setExamineStatus(4);
			fuStockAccount.setTransactionStatus(0);
			fuStockAccountService.saveAccountAndRecord(fuStockAccount, fuUser, 0, fuTransaction);
			obj.put("is_success", 1);
			obj.put("message", "抢券成功");
		}else {
			obj.put("is_success", 0);
			obj.put("message", "version 版本号错误");
		}
		log.info(obj.toString());
		return obj.toString();
	}

	// 获取"我的融券"业务数据
	public String myRqb(String userId, String version) {
		JSONObject obj = new JSONObject();
		if (null == version || "".equals(version)) {
			obj.put("is_success", 0);
			obj.put("message", "缺少参数");
			System.out.println(obj.toString());
			return obj.toString();
		}
		if (null == userId || "".equals(userId)){
			obj.put("is_success", 0);
			obj.put("message", "缺少参数USERID");
			log.info(obj.toString());
			return obj.toString();
		}
		FuUser fuUser = fuUserService.get(Long.parseLong(userId));
		if (null == fuUser) {
			obj.put("is_success", 0);
			obj.put("message", "用户不存在");
			log.info(obj.toString());
			return obj.toString();
		}
		Integer hhrType = fuUser.getHhrType();
		if(null == hhrType || "".equals(hhrType) || hhrType != 2) {
			obj.put("is_success", 0);
			obj.put("message", "用户不是商家");
			log.info(obj.toString());
			return obj.toString();
		}
		FuTransaction fuTransaction = fuTransactionService.findByUserId(fuUser.getId());
		if(null == fuTransaction) {
			obj.put("is_success", 0);
			obj.put("message", "当前用户不是交易团队");
			log.info(obj.toString());
			return obj.toString();
		}

		if("3.0.0".equals(version)) {
			// 查询该交易团队下所有的融券
			List<FuStockAccount> stockList = fuStockAccountService.findByTransactionId(fuTransaction.getId());
			if (null != stockList && stockList.size() > 0) {
				List<Object> list = new ArrayList<Object>();
				for (FuStockAccount fuStockAccount : stockList) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("open_equity", null == fuStockAccount.getOpenEquity() ? "" : fuStockAccount.getOpenEquity());
					map.put("capital_account", null == fuStockAccount.getCapitalAccount() ? "" : fuStockAccount.getCapitalAccount()); 
					map.put("state", fuStockAccount.getState() == null ? 3 : fuStockAccount.getState());
					map.put("transaction_status", fuStockAccount.getTransactionStatus());
					map.put("available", null == fuStockAccount.getAvailable() ? "" : fuStockAccount.getAvailable());
					map.put("able_money", null == fuStockAccount.getAbleMoney() ? "" : fuStockAccount.getAbleMoney());
					map.put("order_time", DateUtil.getStrFromDate(fuStockAccount.getOrderTime(), "yyyy.MM.dd"));
					map.put("stock_id", fuStockAccount.getId());
					list.add(map);
				}
				// 查询记录表
				List<StockOptionRecord> optList = optionRecordService.findByUserId(fuUser.getId(), 1);
				if(null != optList && optList.size() >0) {
					for (StockOptionRecord stockOptionRecord : optList) {
						FuStockAccount fs = fuStockAccountService.get(stockOptionRecord.getStockId());
						HashMap<String, Object> map = new HashMap<String, Object>();
						map.put("open_equity", null == fs.getOpenEquity() ? "" : fs.getOpenEquity());
						map.put("capital_account", null == fs.getCapitalAccount() ? "" : fs.getCapitalAccount()); 
						map.put("state", fs.getState() == null ? 3 : fs.getState());
						map.put("transaction_status", stockOptionRecord.getIsOption()); // 0: 未退券 1:退券
						map.put("available", null == fs.getAvailable() ? "" : fs.getAvailable());
						map.put("able_money", null == fs.getAbleMoney() ? "" : fs.getAbleMoney());
						map.put("order_time", DateUtil.getStrFromDate(stockOptionRecord.getCreateTime(), "yyyy.MM.dd"));
						//map.put("is_option", stockOptionRecord.getIsOption()); // 0: 未退券 1:退券
						map.put("stock_id", fs.getId());
						list.add(map);
					}
				}
				obj.put("is_success", 1);
				obj.put("message", "查询融券成功");
				obj.put("stocks", list);
				log.info(obj.toString());
				return obj.toString();
			}else {
				obj.put("is_success", 2);
				obj.put("message", "目前没有融券,赶快去抢吧");
				obj.put("stocks", "[]");
				log.info(obj.toString());
				return obj.toString();
			}
		}else {
			obj.put("is_success", 0);
			obj.put("message", "version 版本号错误");
		}
		log.info(obj.toString());
		return obj.toString();
	}

	// 交易团队退单操作
	public String chargeback(String userId, String stockId, String version) {
		JSONObject obj = new JSONObject();
		if (null == version || "".equals(version)) {
			obj.put("is_success", 0);
			obj.put("message", "缺少参数");
			System.out.println(obj.toString());
			return obj.toString();
		}
		if (null == stockId || "".equals(stockId)) {
			obj.put("is_success", 0);
			obj.put("message", "缺少参数STOCKID");
			log.info(obj.toString());
			return obj.toString();
		}
		if (null == userId || "".equals(userId)){
			obj.put("is_success", 0);
			obj.put("message", "缺少参数USERID");
			log.info(obj.toString());
			return obj.toString();
		}
		FuUser fuUser = fuUserService.get(Long.parseLong(userId));
		if (null == fuUser) {
			obj.put("is_success", 0);
			obj.put("message", "用户不存在");
			log.info(obj.toString());
			return obj.toString();
		}
		Integer hhrType = fuUser.getHhrType();
		if(null == hhrType || "".equals(hhrType) || hhrType != 2) {
			obj.put("is_success", 0);
			obj.put("message", "用户不是商家");
			log.info(obj.toString());
			return obj.toString();
		}
		FuTransaction fuTransaction = fuTransactionService.findByUserId(fuUser.getId());
		if(null == fuTransaction) {
			obj.put("is_success", 0);
			obj.put("message", "当前用户不是交易团队");
			log.info(obj.toString());
			return obj.toString();
		}
		FuStockAccount fuStockAccount = fuStockAccountService.get(Long.parseLong(stockId));
		if (null == fuStockAccount) {
			obj.put("is_success", 0);
			obj.put("message", "股票账户不存在");
			log.info(obj.toString());
			return obj.toString();
		}
		// 判断这支股票是否在这个交易团队名下
		if (fuStockAccount.getTransactionId().longValue() != fuTransaction.getId().longValue()) {
			obj.put("is_success", 0);
			obj.put("message", "不是这个交易团队下的账户");
			log.info(obj.toString());
			return obj.toString();
		}
		
		if ("3.0.0".equals(version)) {
			// 退券
			fuStockAccount.setExamineStatus(3);
			fuStockAccount.setTransactionStatus(1);
			fuStockAccount.setUpdateTime(new Date());
			fuStockAccount.setIsPublish(0);
//			fuStockAccountService.save(fuStockAccount);
			fuStockAccountService.saveAccountAndRecord(fuStockAccount, fuUser, 1, fuTransaction);
			obj.put("is_success", 1);
			obj.put("message", "退券成功");
			log.info(obj.toString());
			return obj.toString();
		} else {
			obj.put("is_success", 0);
			obj.put("message", "version 版本号错误");
		}
		log.info(obj.toString());
		return obj.toString();
	}
}
