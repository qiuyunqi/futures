package com.hongwei.futures.web.action.user_center;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuBankCard;
import com.hongwei.futures.model.FuDictionary;
import com.hongwei.futures.model.FuMoneyDetail;
import com.hongwei.futures.model.FuParameter;
import com.hongwei.futures.model.FuProgram;
import com.hongwei.futures.model.FuSmsLog;
import com.hongwei.futures.model.FuStockAccount;
import com.hongwei.futures.model.FuStockMoneyDetail;
import com.hongwei.futures.model.FuStockMoneyInfo;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.HhrOrgPerson;
import com.hongwei.futures.model.HhrQualiRegister;
import com.hongwei.futures.model.LiveLotteryInfo;
import com.hongwei.futures.model.SysCity;
import com.hongwei.futures.model.SysProvince;
import com.hongwei.futures.service.FuBankCardService;
import com.hongwei.futures.service.FuDictionaryService;
import com.hongwei.futures.service.FuMoneyDetailService;
import com.hongwei.futures.service.FuParameterService;
import com.hongwei.futures.service.FuProgramService;
import com.hongwei.futures.service.FuSmsLogService;
import com.hongwei.futures.service.FuStockAccountService;
import com.hongwei.futures.service.FuStockMoneyDetailService;
import com.hongwei.futures.service.FuStockMoneyInfoService;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.service.HhrOrgPersonService;
import com.hongwei.futures.service.HhrQualiRegisterService;
import com.hongwei.futures.service.LotteryInfoService;
import com.hongwei.futures.service.QidaScoreService;
import com.hongwei.futures.service.SysCityService;
import com.hongwei.futures.service.SysProvinceService;
import com.hongwei.futures.util.CommonUtils;
import com.hongwei.futures.util.Constants;
import com.hongwei.futures.util.FileOperate;
import com.hongwei.futures.util.MailEngine;
import com.hongwei.futures.util.RegexChk;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("fu_common")
public class UserCenterAction extends BaseAction {
	private static final long serialVersionUID = 4461248584146438759L;

	@Autowired
	private HhrQualiRegisterService hhrQualiRegisterService;
	@Autowired
	private FuBankCardService fuBankCardService;
	@Autowired
	private FuUserService fuUserService;
	@Autowired
	private FuProgramService fuProgramService;
	@Autowired
	private SysProvinceService sysProvinceService;
	@Autowired
	private SysCityService sysCityService;
	@Autowired
	private MailEngine mailEngine;
	@Autowired
	private FuParameterService parameterService;
	@Autowired
	private HhrOrgPersonService hhrOrgPersonService;
	@Autowired
	private FuSmsLogService fuSmsLogService;
	@Autowired
	private FuDictionaryService fuDictionaryService;
	@Autowired
	private FuMoneyDetailService fuMoneyDetailService;
	@Autowired
	private FuStockAccountService fuStockAccountService;
	@Autowired
	private FuStockMoneyDetailService fuStockMoneyDetailService;
	@Autowired
	private FuStockMoneyInfoService fuStockMoneyInfoService;
	@Autowired
	private LotteryInfoService lotteryInfoService;
	@Autowired
	private QidaScoreService qidaScoreService;

	private FuUser fuUser;
	private HhrQualiRegister qualiRegister;
	private Long id;
	private Long userId;
	private Integer totalCount;
	private Long provinceId;
	private Long cityId;
	private BigDecimal salary;
	private String positionName;
	private String businessType;
	private Integer maxDegree;
	private String liveAddress;
	private Integer isMarriage;// 0暂不填写，1未婚，2已婚，3离异，4丧偶
	private Integer gender;
	private String cardBeforePic;
	private String cardBehindPic;
	private String cardHandPic;
	private String qualiPic;
	private String qualiNum;
	private Integer type;
	private String email;
	private String emailCode;
	private String oldPwd;
	private String newPwd;
	private String rePwd;
	private String cardNumber;
	private String userName;
	private String nickName;
	private String phone;
	private String phoneCode;
	private String oldDrawPwd;
	private String newDrawPwd;
	private String reDrawPwd;
	private Integer flag;
	private String methodName;
	private Integer hhrType;
	private Long pid;
	private Long dictionaryId;
	private BigDecimal score;
	private String accountName;

	private String name; // 交易团队的名称 只有hhrType == 2的时候才会有值

	/**
	 * 个人中心首页
	 * 
	 * @return
	 * @throws ParseException
	 */
	@Action("centerIndex")
	public String centerIndex() {
		try {
			// 判断用户是否已绑定银行卡
			List<FuBankCard> bankcardList = fuBankCardService.findListByUser(userId);
			this.getActionContext().put("bankcardList", bankcardList);

			// 判断用户是否已实名认证
			List<FuUser> checkedUserList = fuUserService.findListByUser(userId);
			this.getActionContext().put("checkedUserList", checkedUserList);

			// 判断用户是否认证从业资格
			List<HhrQualiRegister> qualiUserList = hhrQualiRegisterService.findQualiByUser(userId, null);
			this.getActionContext().put("qualiUserList", qualiUserList);

			// 系统参数
			FuParameter params = parameterService.findParameter();
			this.getActionContext().put("params", params);

			// 日配
			List<FuProgram> shortList = fuProgramService.findToTradeByUser(userId, 1);
			this.getActionContext().put("shortList", shortList);

			// 短线进行天数
			List<Integer> dayList = new ArrayList<Integer>();
			Calendar calendar = Calendar.getInstance();

			for (int i = 0; shortList != null && i < shortList.size(); i++) {
				Calendar c = Calendar.getInstance();
				c.setTime(shortList.get(i).getTradeTime());
				int d = 0;
				while (c.getTime().getTime() <= calendar.getTime().getTime()) {
					int weekday = c.get(Calendar.DAY_OF_WEEK);
					c.set(Calendar.DATE, c.get(Calendar.DATE) + 1);
					if (weekday == 1 || weekday == 7) {
						continue;
					} else {
						d = d + 1;
					}
				}
				dayList.add(d);
			}
			this.getActionContext().put("dayList", dayList);
			// 月配
			List<FuProgram> longList = fuProgramService.findToTradeByUser(userId, 2);
			this.getActionContext().put("longList", longList);
			// 已结束方案
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", fuUser == null ? 0L : fuUser.getId());
			List<FuProgram> overList = fuProgramService.findOverProgramList(map);
			this.getActionContext().put("overList", overList);

			// 资金明细部分数据
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("userId", userId);
			if (pid != null) {
				map2.put("pid", pid);
			}
			if (dictionaryId != null) {
				map2.put("dictionaryId", dictionaryId);
			}
			map2.put("isEnabled", 1);
			if (flag != null) {// 时间筛选
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar c = Calendar.getInstance();
				if (flag == 1) {// 近一个星期
					c.add(Calendar.WEEK_OF_MONTH, -1);
					map2.put("date1", sdf.parse(sdf.format(c.getTime())));
				}
				if (flag == 2) {// 近一个月
					c.add(Calendar.MONTH, -1);
					map2.put("date1", sdf.parse(sdf.format(c.getTime())));
				}
				if (flag == 3) {// 近一年
					c.add(Calendar.YEAR, -1);
					map2.put("date1", sdf.parse(sdf.format(c.getTime())));
				}
				map2.put("date2", new Date());
			}
			if (totalCount == null) {
				totalCount = fuMoneyDetailService.getCount(map2);
			}
			List<FuMoneyDetail> detailList = fuMoneyDetailService.findListBy((this.getPageNo() - 1) * this.getPageSize(), this.getPageSize(), map2);
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
			List<FuDictionary> dictionaries = fuDictionaryService.findListByMap(0, Integer.MAX_VALUE, map2);
			this.getActionContext().put("dictionaries", dictionaries);
			this.getActionContext().put("detailList", detailList);
			this.getActionContext().put("incomeCount", incomeCount);
			this.getActionContext().put("spendCount", spendCount);
			this.getActionContext().put("incomeMoney", incomeMoney);
			this.getActionContext().put("spendMoney", spendMoney);
			this.getActionContext().put("jsptype", "moneyDetailList");

			// 昨日盈亏和累计盈亏
			Map<String, Object> map3 = new HashMap<String, Object>();
			map3.put("userId", fuUser == null ? 0L : fuUser.getId());
			map3.put("isDel", 0);
			// 查询当前用户的所有未删除的股票账户
			List<Object> stockList = new ArrayList<Object>();
			List<FuStockAccount> stockAccounts = fuStockAccountService.findAccountByMap(0, Integer.MAX_VALUE, map3);
			// 当前用户昨日总盈利
			BigDecimal dayProfits = BigDecimal.ZERO;
			if (stockAccounts.size() > 0) {
				for (FuStockAccount fuStockAccount : stockAccounts) {
					FuStockMoneyDetail stockMoneyDetail = fuStockMoneyDetailService.findMoneyDetailByAccount(fuStockAccount.getId());
					List<Object> objList = new ArrayList<Object>();
					objList.add(fuStockAccount.getId());
					objList.add(fuStockAccount.getOpenEquity());
					objList.add(fuStockAccount.getCapitalAccount());
					objList.add(stockMoneyDetail == null ? BigDecimal.ZERO : stockMoneyDetail.getNowProfit() == null ? BigDecimal.ZERO : stockMoneyDetail.getNowProfit());
					objList.add(stockMoneyDetail == null ? BigDecimal.ZERO : stockMoneyDetail.getManageFee() == null ? BigDecimal.ZERO : stockMoneyDetail.getManageFee());
					objList.add(fuStockAccount.getState());// 当前账户的状态
					stockList.add(objList);
					dayProfits = dayProfits.add((stockMoneyDetail == null ? BigDecimal.ZERO : stockMoneyDetail.getNowProfit() == null ? BigDecimal.ZERO : stockMoneyDetail.getNowProfit()));
				}
			}
			// 查询当前用户的资金统计结果
			FuStockMoneyInfo fuStockMoneyInfo = fuStockMoneyInfoService.findMoneyInfoByMap(map3);
			this.getActionContext().put("dayProfits", dayProfits);
			this.getActionContext().put("fuStockMoneyInfo", fuStockMoneyInfo);
			this.getActionContext().put("stockList", stockList);

			this.getActionContext().put("jsptype", "centerIndex");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

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
			List<FuMoneyDetail> detailList = fuMoneyDetailService.findListBy((this.getPageNo() - 1) * this.getPageSize(), this.getPageSize(), map);
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
			List<FuDictionary> dictionaries = fuDictionaryService.findListByMap(0, Integer.MAX_VALUE, map);
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

	/**
	 * 个人信息
	 * 
	 * @return
	 */
	@Action("personInfo")
	public String personInfo() {
		try {
			List<SysProvince> provinceList = sysProvinceService.findAllProvince();
			this.getActionContext().put("provinceList", provinceList);
			List<SysCity> cityList = sysCityService.findCityByProvince(fuUser.getProvinceId());
			this.getActionContext().put("cityList", cityList);
			this.getActionContext().put("jsptype", "personInfo");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 积分商城
	 * 
	 * @return
	 */
	@Action("mall")
	public String mall() {
		this.getActionContext().put("jsptype", "mall");
		return SUCCESS;
	}

	/**
	 * 编辑个人信息
	 * 
	 * @return
	 */
	/*
	 * @Action("editPerson") public String editPerson(){ List<SysProvince> provinceList = sysProvinceService.findAllProvince(); this.getActionContext().put("provinceList", provinceList); return
	 * SUCCESS; }
	 */
	/**
	 * 保存合伙人类别
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("savePersonHhrTypeAjax")
	public String savePersonHhrTypeAjax() {
		try {
			JSONObject obj = new JSONObject();
			if (null == id || "".equals(id)) {
				obj.put("is_success", 0);
				obj.put("message", "id获取失败");
				write(obj.toString());
				return null;
			}
			FuUser fuUser = fuUserService.get(id);
			if (null == fuUser) {
				obj.put("is_success", 0);
				obj.put("message", "用户不存在");
				write(obj.toString());
				return null;
			}
			fuUserService.updateHhrLevelInfo(fuUser, hhrType, name);
			obj.put("is_success", 1);
			obj.put("message", "保存成功");
			write(obj.toString());
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 保存个人信息编辑
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("savePersonInfoAjax")
	public String savePersonInfoAjax() {
		try {
			// fuUser.setHhrType(hhrType);
			fuUser.setIsMarriage(isMarriage);
			fuUser.setProvinceId(provinceId);
			if (provinceId != null) {
				fuUser.setProvinceName(sysProvinceService.get(provinceId).getProvinceName());
			} else {
				fuUser.setProvinceName(null);
			}
			fuUser.setCityId(cityId);
			if (cityId != null) {
				fuUser.setCityName(sysCityService.get(cityId).getCityName());
			} else {
				fuUser.setCityName(null);
			}
			fuUser.setLiveAddress(liveAddress);
			fuUser.setBusinessType(businessType);
			fuUser.setPositionName(positionName);
			fuUser.setMaxDegree(maxDegree);
			if (salary != null) {
				fuUser.setSalary(salary);
			} else {
				fuUser.setSalary(null);
			}

			if (nickName != null) {
				FuUser sameNick = fuUserService.findUserByNickName(nickName);
				if (null != sameNick) {
					write("-1");
					return null;
				} else {
					fuUser.setNickName(nickName);
				}
			}
			fuUser.setGender(gender);
			fuUserService.save(fuUser);
			write("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 安全信息
	 * 
	 * @return
	 */
	@Action("safeInfo")
	public String safeInfo() {
		this.getActionContext().put("jsptype", "safeInfo");
		return SUCCESS;
	}

	/**
	 * 资格认证
	 * 
	 * @return
	 */
	@Action("userQuali")
	public String userQuali() {
		try {
			List<HhrQualiRegister> qualiUserList = hhrQualiRegisterService.findQualiByUser(userId, "all");
			this.getActionContext().put("qualiUserList", qualiUserList);
			this.getActionContext().put("jsptype", "userQuali");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 资格认证页面
	 * 
	 * @return
	 */
	@Action("userQualiInfo")
	public String userQualiInfo() {
		// List<HhrQualiRegister> qualiUserList =
		// hhrQualiRegisterService.findQualiByUser(userId);
		// this.getActionContext().put("qualiUserList", qualiUserList);
		// this.getActionContext().put("jsptype", "userQuali");
		try {
			if (id != null) {
				HhrQualiRegister quali = hhrQualiRegisterService.get(id);
				this.getActionContext().put("quali", quali);
			}
			this.getActionContext().put("jsptype", "userQualiInfo");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 保存资格认证信息
	 * 
	 * @return
	 */
	@Action("saveQualiInfoAjax")
	public String saveQualiInfoAjax() {
		try {
			if (StringUtil.isBlank(qualiNum)) {
				write("-2");
				return null;
			}
			List<HhrQualiRegister> qualiedList = hhrQualiRegisterService.countByUserAndType(userId, type);
			List<HhrQualiRegister> qualiNumList = hhrQualiRegisterService.countByQualiNum(qualiNum);
			List<HhrOrgPerson> webList = hhrOrgPersonService.countByUserAndCer(fuUser.getUserName(), qualiNum);
			HhrQualiRegister quali = null;

			if (id != null) {
				quali = hhrQualiRegisterService.get(id);
				if (qualiedList.size() > 1) {
					write("-3");
					return null;
				}
				if (qualiNumList.size() > 1) {
					write("-4");
					return null;
				}
			} else {
				quali = new HhrQualiRegister();
				if (qualiedList.size() > 0) {
					write("-3");
					return null;
				}
				if (qualiNumList.size() > 0) {
					write("-4");
					return null;
				}
			}

			if (webList.size() == 0) {
				write("-5");
				return null;
			}

			quali.setFuUser(fuUser);
			quali.setUserName(fuUser.getUserName());
			quali.setQualiNum(qualiNum);
			quali.setCreateDate(new Date());
			quali.setIsChecked(2);
			quali.setType(type);

			String newPath = "";
			if (!StringUtil.isBlank(qualiPic)) {
				if (this.qualiPic.contains(Constants.DIR_TEMP) && !Constants.OSSEnable) {
					FileOperate op = new FileOperate();
					newPath = this.qualiPic.replace(Constants.DIR_TEMP, Constants.DIR_WX);
					if (!this.qualiPic.equals(newPath))
						op.moveFile(this.getServletContext().getRealPath(this.qualiPic), this.getServletContext().getRealPath(newPath));
				} else {
					newPath = qualiPic;
				}
				quali.setQualiPic(newPath);
			}
			hhrQualiRegisterService.save(quali);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除资格证
	 * 
	 * @return
	 */
	@Action("delQualiAjax")
	public String delQualiAjax() {
		try {
			hhrQualiRegisterService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 实名（身份证）认证页面
	 * 
	 * @return
	 */
	@Action("personCardAjax")
	public String personCardAjax() {
		return SUCCESS;
	}

	@Action("personNameAjax")
	public String personNameAjax() {
		return SUCCESS;
	}

	/**
	 * 提交实名认证的信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("savePersonNameAjax")
	public String savePersonNameAjax() {
		try {
			if (StringUtil.isBlank(userName)) {
				write("-2");// 请输入真实姓名
				return null;
			}
			if (StringUtil.isBlank(cardNumber)) {
				write("-3");// 请输入身份证号码
				return null;
			}
			// 如果一个身份证号码已经被某个手机号认证，那么就不得拿来在其他手机号下认证
			FuUser user = fuUserService.findUserByCardNumber(cardNumber);
			if (user != null) {
				write("-5");// 请更换身份证号码
				return null;
			}
			fuUser.setUserName(userName);
			fuUser.setCardNumber(cardNumber);
			if (fuUser.getIsCheckCard() == null || fuUser.getIsCheckCard() == 0) {
				fuUser.setSafeLevel(fuUser.getSafeLevel() + 1);
			}
			fuUser.setIsCheckCard(1);// 待认证
			fuUserService.save(fuUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Action("savePersonCardAjax")
	public String savePersonCardAjax() {
		/*
		 * if(StringUtil.isBlank(cardBeforePic)){ write("-2");//请上传身份证正面照 return null; } if(StringUtil.isBlank(cardBehindPic)){ write("-3"); //请上传身份证背面照 return null; }
		 * if(StringUtil.isBlank(cardHandPic)){ write("-4"); //请上传身份证手持照 return null; }
		 */
		try {
			String newPath = "";
			if (!StringUtil.isBlank(cardBeforePic)) {
				if (this.cardBeforePic.contains(Constants.DIR_TEMP) && !Constants.OSSEnable) {
					FileOperate op = new FileOperate();
					newPath = this.cardBeforePic.replace(Constants.DIR_TEMP, Constants.DIR_WX);
					if (!this.cardBeforePic.equals(newPath))
						op.moveFile(this.getServletContext().getRealPath(this.cardBeforePic), this.getServletContext().getRealPath(newPath));
				} else {
					newPath = cardBeforePic;
				}
				fuUser.setCardBeforePic(newPath);
			}

			if (!StringUtil.isBlank(cardBehindPic)) {
				if (this.cardBehindPic.contains(Constants.DIR_TEMP) && !Constants.OSSEnable) {
					FileOperate op = new FileOperate();
					newPath = this.cardBehindPic.replace(Constants.DIR_TEMP, Constants.DIR_WX);
					if (!this.cardBehindPic.equals(newPath))
						op.moveFile(this.getServletContext().getRealPath(this.cardBehindPic), this.getServletContext().getRealPath(newPath));
				} else {
					newPath = cardBehindPic;
				}
				fuUser.setCardBehindPic(newPath);
			}
			if (!StringUtil.isBlank(cardHandPic)) {
				if (this.cardHandPic.contains(Constants.DIR_TEMP) && !Constants.OSSEnable) {
					FileOperate op = new FileOperate();
					newPath = this.cardHandPic.replace(Constants.DIR_TEMP, Constants.DIR_WX);
					if (!this.cardHandPic.equals(newPath))
						op.moveFile(this.getServletContext().getRealPath(this.cardHandPic), this.getServletContext().getRealPath(newPath));
				} else {
					newPath = cardHandPic;
				}
				fuUser.setCardHandPic(newPath);
			}
			fuUser.setIsCheckCard(1);// 待认证
			fuUserService.save(fuUser);
			fuSmsLogService.saveSendServiceEmail("用户认证", "用户" + fuUser.getUserName() + "申请认证");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 邮箱绑定
	 * 
	 * @return
	 */
	@Action("bindEmailAjax")
	public String bindEmailAjax() {
		return SUCCESS;
	}

	/**
	 * 发送email验证码
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("sendEmailCodeAjax")
	public String sendEmailCodeAjax() {
		try {
			if (!RegexChk.checkEmail(email)) {
				write("-2");// 请输入邮箱,且注意邮箱格式
				return null;
			}
			FuUser u = fuUserService.findUserByEmail(email);
			if (u != null && !u.getId().toString().equals(userId.toString())) {
				write("-3");// 改邮箱已经被占用了
				return null;
			}
			DecimalFormat format = new DecimalFormat("000000");
			String code = format.format(Math.random() * 999999);
			String[] emailAddresses = new String[1];
			emailAddresses[0] = email;
			mailEngine.sendMessage(emailAddresses, "资产管理平台<service@hhr360.com>", fuUser.getAccountName() + "您好，请完成Email验证，您可以在验证页面输入以下验证码：" + code, fuUser.getAccountName() + "您好，请完成电子邮箱验证：" + code,
					null, null);
			// fuUser.setEmail(email);
			fuUser.setEmailCode(code);
			fuUserService.saveCheckEmail(fuUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 完成邮箱绑定信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveBindEmailAjax")
	public String saveBindEmailAjax() {
		try {
			if (!RegexChk.checkEmail(email)) {
				write("-2");// 请输入邮箱,且注意邮箱格式
				return null;
			}
			if (StringUtil.isBlank(emailCode)) {
				write("-3");// 请输入邮箱验证码
				return null;
			}
			if (StringUtil.isBlank(fuUser.getEmailCode())) {
				write("-4");// 还没有获取邮箱验证
				return null;
			}
			if (!fuUser.getEmailCode().equals(emailCode)) {
				write("-5"); // 验证码错误
				return null;
			}
			fuUser.setEmail(email);
			if (fuUser.getIsBindEmail() == null || fuUser.getIsBindEmail() == 0) {
				fuUser.setSafeLevel(fuUser.getSafeLevel() + 1);
			}
			fuUser.setIsBindEmail(1);
			fuUserService.save(fuUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 发送手机验证码
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("sendMsgAjax")
	public String sendMsgAjax() {
		try {
			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			if (!RegexChk.checkCellPhone(phone)) {// 输入手机
				obj.put("code", -2);
				write(obj.toString());
				return null;
			}
			FuUser u = fuUserService.get(userId);
			DecimalFormat format = new DecimalFormat("000000");
			String code = format.format(Math.random() * 999999);
			u.setPhoneCode(code);
			// 验证码过期时间（1个小时）
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.HOUR_OF_DAY, 1);
			u.setPhoneCodeTime(cal.getTime());

			FuSmsLog log = new FuSmsLog();
			log.setContent("找回提款密码验证码：" + code + "，为了保护您的账号安全，验证短信请勿转发给其他人。");
			log.setPrio(1);
			log.setReason("找回提款密码");
			log.setDestination(phone);
			log.setPlanTime(new Date());
			log.setType(1);// 短信
			log.setState(0);
			fuUserService.saveReg(u, log);
			obj.put("code", 1);
			write(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	@Action("editPasswordAjax")
	public String editPasswordAjax() {
		return SUCCESS;
	}

	/**
	 * 发送修改登录密码的手机验证码
	 * 
	 * @return
	 * @throws Exception
	 */
	/*
	 * @Action("sendLoginMsgAjax") public String sendLoginMsgAjax() throws Exception{ JSONObject obj=new JSONObject(); obj.put("error", 0); FuUser u = fuUserService.get(fuUser.getId()); DecimalFormat
	 * format = new DecimalFormat("000000"); String code = format.format(Math.random() * 999999); u.setPhoneCode(code); //验证码过期时间（1个小时） Calendar cal = Calendar.getInstance();
	 * cal.add(Calendar.HOUR_OF_DAY, 1); u.setPhoneCodeTime(cal.getTime());
	 * 
	 * FuSmsLog log = new FuSmsLog(); log.setContent("修改登录密码验证码："+code+"，为了保护您的账号安全，验证短信请勿转发给其他人。"); log.setPrio(1); log.setReason("修改登录密码"); log.setDestination(phone); log.setPlanTime(new Date());
	 * log.setType(1);//短信 log.setState(0); fuUserService.saveReg(u,log); obj.put("code", 1); write(obj.toString()); return null; }
	 */
	/**
	 * 保存密码修改
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("savePasswordAjax")
	public String savePasswordAjax() {
		try {
			if (StringUtil.isBlank(oldPwd)) {
				write("-2");// 请输入旧密码
				return null;
			}
			if (!RegexChk.checkUserPwd(newPwd)) {
				write("-3"); // 6位以上数字，字母
				return null;
			}
			if (StringUtil.isBlank(rePwd)) {
				write("-4");// 请输入确认密码
				return null;
			}
			if (!newPwd.equals(rePwd)) {
				write("-5");// 新密码和确认密码不一致
				return null;
			}
			if (!fuUser.getPassword().equals(CommonUtils.getMd5(oldPwd))) {
				write("-6");// 旧密码输入错误
				return null;
			}
			fuUser.setPassword(CommonUtils.getMd5(newPwd));
			fuUserService.save(fuUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		/*
		 * if(StringUtil.isBlank(phoneCode)){ write("-3");//请输入手机验证码 return null; } if(StringUtil.isBlank(fuUser.getPhoneCode())){ write("-4");//还没有获取手机验证 return null; }
		 * if(fuUser.getPhoneCode()==null|| !fuUser.getPhoneCode().equals(phoneCode)){ write("-5"); //验证码错误 return null; } if(!RegexChk.checkUserPwd(newPwd)){ write("-6"); //6位以上数字，字母 return null; }
		 * if(StringUtil.isBlank(rePwd)){ write("-7");//请输入确认密码 return null; } if(!newPwd.equals(rePwd)){ write("-8");//新密码和确认密码不一致 return null; } if(fuUser.getPhoneCode()!=null
		 * &&fuUser.getPhoneCode().equals(phoneCode )&&fuUser.getPhoneCodeTime().before(new Date())){ write("-9"); //验证码已过期 return null; } fuUser.setPassword(CommonUtils.getMd5(newPwd));
		 * fuUserService.save(fuUser); return null;
		 */
	}

	/**
	 * 设置修改提款密码
	 */
	@Action("drawPasswordAjax")
	public String drawPasswordAjax() {
		return SUCCESS;
	}

	/**
	 * 保存提款密码信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveDrawPasswordAjax")
	public String saveDrawPasswordAjax() {
		try {
			if (fuUser.getDrawPassword() != null && StringUtil.isBlank(oldDrawPwd)) {
				write("-2");// 请输入旧密码
				return null;
			}
			if (!RegexChk.checkDrawPwd(newDrawPwd)) {
				write("-3"); // 6位纯数字
				return null;
			}
			if (StringUtil.isBlank(reDrawPwd)) {
				write("-4");// 请输入确认密码
				return null;
			}
			if (!newDrawPwd.equals(reDrawPwd)) {
				write("-5");// 新密码和确认密码不一致
				return null;
			}
			if (fuUser.getDrawPassword() != null && !fuUser.getDrawPassword().equals(CommonUtils.getMd5(oldDrawPwd))) {
				write("-6");// 旧密码输入错误
				return null;
			}
			if (fuUser.getDrawPassword() == null || fuUser.getDrawPassword().equals("")) {
				fuUser.setSafeLevel(fuUser.getSafeLevel() + 1);
			}
			fuUser.setDrawPassword(CommonUtils.getMd5(newDrawPwd));
			fuUserService.save(fuUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 忘记提款密码
	 * 
	 * @return
	 */
	@Action("findDrawPwdAjax")
	public String findDrawPwdAjax() {
		return SUCCESS;
	}

	@Action("saveFindDrawPwdAjax")
	public String saveFindDrawPwdAjax() {
		try {
			if (!RegexChk.checkCellPhone(phone)) {
				write("-2");// 请输入手机号码,且注意手机号码格式
				return null;
			}
			if (StringUtil.isBlank(phoneCode)) {
				write("-3");// 请输入手机验证码
				return null;
			}
			if (StringUtil.isBlank(fuUser.getPhoneCode())) {
				write("-4");// 还没有获取手机验证
				return null;
			}
			if (fuUser.getPhoneCode() == null || !fuUser.getPhoneCode().equals(phoneCode)) {
				write("-5"); // 验证码错误
				return null;
			}
			if (fuUser.getPhoneCode() != null && fuUser.getPhoneCode().equals(phoneCode) && fuUser.getPhoneCodeTime().before(new Date())) {
				write("-9"); // 验证码已过期
				return null;
			}
			if (!RegexChk.checkDrawPwd(newDrawPwd)) {
				write("-6"); // 6位纯数字
				return null;
			}
			if (StringUtil.isBlank(reDrawPwd)) {
				write("-7");// 请输入确认密码
				return null;
			}
			if (!newDrawPwd.equals(reDrawPwd)) {
				write("-8");// 新密码和确认密码不一致
				return null;
			}
			fuUser.setDrawPassword(CommonUtils.getMd5(newDrawPwd));
			fuUserService.save(fuUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据省份级联城市
	 * 
	 * @return
	 */
	@Action("selectCityAjax")
	public String selectCityAjax() {
		try {
			List<SysCity> cityList = sysCityService.findCityByProvince(provinceId);
			this.getActionContext().put("cityList", cityList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 视频教程
	 * 
	 * @return
	 */
	@Action("video")
	public String video() {
		return SUCCESS;
	}

	/**
	 * 查询个人中心的抽奖记录
	 * 
	 * @return
	 */
	@Action("prizeManage")
	public String findLottery() {
		HttpServletRequest request = this.getHttpServletRequest();
		FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
		// 查询自己的中奖记录
		List<LiveLotteryInfo> infoList = lotteryInfoService.findLottery((this.getPageNo() - 1) * this.getPageSize(), this.getPageSize(), fuUser, 1);
		Integer count = lotteryInfoService.getCount(fuUser, 1);
		request.setAttribute("infoList", infoList);
		request.setAttribute("totalCount", count);
		request.setAttribute("pageNo", this.getPageNo());
		this.getActionContext().put("jsptype", "prizeManage");
		return SUCCESS;
	}

	/**
	 * 领取虚拟奖品
	 * 
	 * @return
	 */
	@Action("prizeTake")
	public String prizeTake() {
		try {
			HttpServletRequest request = this.getHttpServletRequest();
			FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
			// 增加积分
			fuUser.setQidaIntegral((fuUser.getQidaIntegral() == null ? BigDecimal.ZERO : fuUser.getQidaIntegral()).add(score));
			fuUserService.save(fuUser);

			// 积分明细
			qidaScoreService.saveScoreByQd(fuUser, 60, score, BigDecimal.ZERO, fuUser.getQidaIntegral(), 1);

			// 状态设为已领取
			LiveLotteryInfo info = lotteryInfoService.get(id);
			info.setIsReceive(1);
			lotteryInfoService.save(info);
			write("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 积分管理页面
	 * 
	 * @return
	 */
	@Action("creditManage")
	public String creditManage() {
		HttpServletRequest request = this.getHttpServletRequest();
		FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
		request.getSession().setAttribute("fuUser", fuUser);
		this.getActionContext().put("jsptype", "creditManage");
		return SUCCESS;
	}

	/**
	 * 检测用户名
	 * 
	 * @return
	 */
	@Action("checkUser")
	public String checkUser() {
		try {
			FuUser user = fuUserService.findUserByAccount(accountName);
			if (user == null) {
				write("-1");
				return null;
			}
			HttpServletRequest request = this.getHttpServletRequest();
			FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
			if (fuUser.getAccountName().equals(accountName)) {
				write("-2");
				return null;
			}
			write("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 赠送积分
	 * 
	 * @return
	 */
	@Action("giveScore")
	public String giveScore() {
		try {
			HttpServletRequest request = this.getHttpServletRequest();
			FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
			if (fuUser == null) {
				write("-1");
				return null;
			}
			if (fuUser.getAccountName().equals(accountName)) {
				write("-2");
				return null;
			}

			// 减少积分
			fuUser.setQidaIntegral((fuUser.getQidaIntegral() == null ? BigDecimal.ZERO : fuUser.getQidaIntegral()).subtract(score));
			fuUserService.save(fuUser);

			// 赠送积分明细
			qidaScoreService.saveScoreByQd(fuUser, 58, score, BigDecimal.ZERO, fuUser.getQidaIntegral(), 0);

			// 获赠用户
			FuUser user = fuUserService.findUserByAccount(accountName);

			// 增加积分
			user.setQidaIntegral((user.getQidaIntegral() == null ? BigDecimal.ZERO : user.getQidaIntegral()).add(score));
			fuUserService.save(user);

			// 获赠积分明细
			qidaScoreService.saveScoreByQd(user, 59, score, BigDecimal.ZERO, user.getQidaIntegral(), 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public Integer getMaxDegree() {
		return maxDegree;
	}

	public void setMaxDegree(Integer maxDegree) {
		this.maxDegree = maxDegree;
	}

	public String getLiveAddress() {
		return liveAddress;
	}

	public void setLiveAddress(String liveAddress) {
		this.liveAddress = liveAddress;
	}

	public Integer getIsMarriage() {
		return isMarriage;
	}

	public void setIsMarriage(Integer isMarriage) {
		this.isMarriage = isMarriage;
	}

	public String getCardBeforePic() {
		return cardBeforePic;
	}

	public void setCardBeforePic(String cardBeforePic) {
		this.cardBeforePic = cardBeforePic;
	}

	public String getCardBehindPic() {
		return cardBehindPic;
	}

	public void setCardBehindPic(String cardBehindPic) {
		this.cardBehindPic = cardBehindPic;
	}

	public String getCardHandPic() {
		return cardHandPic;
	}

	public void setCardHandPic(String cardHandPic) {
		this.cardHandPic = cardHandPic;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailCode() {
		return emailCode;
	}

	public void setEmailCode(String emailCode) {
		this.emailCode = emailCode;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String getRePwd() {
		return rePwd;
	}

	public void setRePwd(String rePwd) {
		this.rePwd = rePwd;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	public String getOldDrawPwd() {
		return oldDrawPwd;
	}

	public void setOldDrawPwd(String oldDrawPwd) {
		this.oldDrawPwd = oldDrawPwd;
	}

	public String getNewDrawPwd() {
		return newDrawPwd;
	}

	public void setNewDrawPwd(String newDrawPwd) {
		this.newDrawPwd = newDrawPwd;
	}

	public String getReDrawPwd() {
		return reDrawPwd;
	}

	public void setReDrawPwd(String reDrawPwd) {
		this.reDrawPwd = reDrawPwd;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public HhrQualiRegister getQualiRegister() {
		return qualiRegister;
	}

	public void setQualiRegister(HhrQualiRegister qualiRegister) {
		this.qualiRegister = qualiRegister;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQualiPic() {
		return qualiPic;
	}

	public void setQualiPic(String qualiPic) {
		this.qualiPic = qualiPic;
	}

	public String getQualiNum() {
		return qualiNum;
	}

	public void setQualiNum(String qualiNum) {
		this.qualiNum = qualiNum;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getHhrType() {
		return hhrType;
	}

	public void setHhrType(Integer hhrType) {
		this.hhrType = hhrType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

}
