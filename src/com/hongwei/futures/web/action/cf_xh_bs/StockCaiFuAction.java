package com.hongwei.futures.web.action.cf_xh_bs;

import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuSmsLog;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.MatchXhCaiFu;
import com.hongwei.futures.service.FuSmsLogService;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.service.MatchXhCaiFuService;
import com.hongwei.futures.util.IP4;
import com.hongwei.futures.web.action.BaseAction;

/**
 * 财富小合实盘炒股大赛
 * 
 * @author han
 */
@ParentPackage("fu_common")
public class StockCaiFuAction extends BaseAction {
	private static final long serialVersionUID = 6186703915257511264L;
	private static final Log logger = LogFactory.getLog(StockCaiFuAction.class);

	@Autowired
	private FuSmsLogService fuSmsLogService;
	@Autowired
	private FuUserService fuUserService;
	@Autowired
	private MatchXhCaiFuService matchXhCaiFuService;
	
	private String phone;
	private String code;

	/**
	 * 小合财富证券首页
	 * @return
	 */
	@Action("index")
	public String index() {
		// 收益牛人前7名
		List<MatchXhCaiFu> mxcList = matchXhCaiFuService.findMax(0, 7);
		for (MatchXhCaiFu matchXhCaiFu : mxcList) {
			String subStr = matchXhCaiFu.getPhone().substring(3, 8);
			matchXhCaiFu.setPhone(matchXhCaiFu.getPhone().replace(subStr, "*****"));
		}
		// 幸运奖前10名
		List<MatchXhCaiFu> luckyList = matchXhCaiFuService.findLucky(0, 10);
		for (MatchXhCaiFu matchXhCaiFu : luckyList) {
			String subStr = matchXhCaiFu.getPhone().substring(3, 8);
			matchXhCaiFu.setPhone(matchXhCaiFu.getPhone().replace(subStr, "*****"));
		}
		// 推荐人奖4名
		List<MatchXhCaiFu> recommendList = matchXhCaiFuService.findRecommend(0, 4);
		for (MatchXhCaiFu matchXhCaiFu : recommendList) {
			String subStr = matchXhCaiFu.getPhone().substring(3, 8);
			matchXhCaiFu.setPhone(matchXhCaiFu.getPhone().replace(subStr, "*****"));
		}
		this.getHttpServletRequest().setAttribute("mxcList", mxcList);
		this.getHttpServletRequest().setAttribute("luckyList", luckyList);
		this.getHttpServletRequest().setAttribute("recommendList", recommendList);
		
		// 随机选择开户地址
		String[] strs = {"http://e.cfzq.com/direct/newRegisteIndex.html?appQRCodeId=33007361433709257771071", "http://e.cfzq.com/direct/newRegisteIndex.html?appQRCodeId=33002585437113257771071",
		"http://e.cfzq.com/direct/newRegisteIndex.html?appQRCodeId=33001358428515211771072","http://e.cfzq.com/direct/newRegisteIndex.html?appQRCodeId=33000360437211211771071",
		"http://e.cfzq.com/direct/newRegisteIndex.html?appQRCodeId=33005851437287257771071"};
		int random = new Random().nextInt(5);
		this.getHttpServletRequest().setAttribute("url", strs[random]);
		return SUCCESS;
	}
	
	/**
	 * 进入注册页面
	 * @return
	 */
	@Action("login")
	public String login() {
		return SUCCESS;
	}
	
	/**
	 * 风险及免责
	 * @return
	 */
	@Action("dangerRule")
	public String dangerRule() {
		return SUCCESS;
	}
	
	/**
	 * 免责声明的页面
	 * @return
	 */
	@Action("disclaimerRule")
	public String disclaimerRule() {
		return SUCCESS;
	}
	
	
	/**
	 * 发送短信
	 * @return
	 * @throws Exception
	 */
	@Action("sendCode")
	public String sendCode() throws Exception {

		// 获取IP地址
		String ip = IP4.getIP4(getHttpServletRequest());
		// 判断手机号或者是ip是否在黑名单中
		int flag = fuSmsLogService.examin(phone, ip);
		JSONObject obj = new JSONObject();
		if (flag == 0) { // false
			logger.info("请联系管理员解冻你的封印");
			write("1");
			return null;
		} else if (flag == 2) {
			logger.info("您点击过于频繁, 请稍后再试");
			write("2");
			return null;
		}
		logger.info("发送短信开始");
		DecimalFormat format = new DecimalFormat("0000");
		String code = format.format(Math.random() * 9999);
		String message = URLDecoder.decode("您的手机验证码是:" + code + "，请填入界面完成绑定手机号码。", "UTF-8");
		FuSmsLog log = new FuSmsLog();
		log.setContent(message);
		log.setPrio(1);
		log.setReason("财富小合实盘炒股大赛");
		log.setDestination(phone);
		log.setPlanTime(new Date());
		log.setType(1);// 短信
//		log.setType(4);// 语音短信
		log.setRegCode(code);
		log.setState(0);
		obj.put("message", "请求成功");
		obj.put("is_success", 1);
		fuSmsLogService.save(log);
		logger.info("发送短信结束");
		logger.info("返回信息==>" + obj.toString());
		write("3");
		return null;
	}

	/**
	 * 注册比赛的人员
	 * @return
	 * @throws Exception
	 */
	@Action("registermactch")
	public String registerMatch() throws Exception {
		if (null == phone || "".equals(phone)) {
			logger.info("请输入手机号码");
			write("1");  // 手机号码不为能
			return null;
		}
		
		if (null == code || "".equals(code)) {
			write("2");  // 验证码不能为空
			return null;
		}
		
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("destination", phone);
		FuSmsLog fuSmsLog = fuSmsLogService.findLogByMap(map);
		if (null == fuSmsLog || null == fuSmsLog.getRegCode() || "".equals(fuSmsLog.getRegCode()) ||  !fuSmsLog.getRegCode().equals(code)) {
			write("3");  // 验证码错误
			return null;
		}
		logger.info("基本验证通过");
		FuUser fuUser = fuUserService.findUserByAccount(phone);
		if (null == fuUser) { // 这个手机号码没有注册过
			logger.info("null === fuUser");
			fuUserService.saveWeiXinUser(phone, "111111", "cf_" + phone, code+"", "412117108616", 5, "", "");
			logger.info("保存fuUser成功");
			MatchXhCaiFu mxc = new MatchXhCaiFu();
			mxc.setPhone(phone);
			mxc.setProfit(0);
			mxc.setRecommendNum(0);
			mxc.setMatchTime(new Date());
			matchXhCaiFuService.save(mxc);
		} else { // 这个手机号码已经注册过
			MatchXhCaiFu mxc;
			mxc = matchXhCaiFuService.findByPhone(phone);
			if (null == mxc) {
				mxc = new MatchXhCaiFu();
				mxc.setPhone(phone);
				mxc.setProfit(0);
				mxc.setRecommendNum(0);
				mxc.setMatchTime(new Date());
				matchXhCaiFuService.save(mxc);
			} else {
				write("4");  // 你已经报过名了
				return null;
			}
		}
		write("5");	// 报名成功
		return null;
	}
	
	/**
	 * 进入股票开户页面
	 * @return
	 */
	@Action("stockAccount")
	public String stockAccount() {
		// 随机选择开户地址
		String[] strs = {"http://e.cfzq.com/direct/newRegisteIndex.html?appQRCodeId=33007361433709257771071", "http://e.cfzq.com/direct/newRegisteIndex.html?appQRCodeId=33002585437113257771071",
		"http://e.cfzq.com/direct/newRegisteIndex.html?appQRCodeId=33001358428515211771072","http://e.cfzq.com/direct/newRegisteIndex.html?appQRCodeId=33000360437211211771071",
		"http://e.cfzq.com/direct/newRegisteIndex.html?appQRCodeId=33005851437287257771071"};
		int random = new Random().nextInt(5);
		this.getHttpServletRequest().setAttribute("url", strs[random]);
		return SUCCESS;
	}
	
	/**
	 * 查询我的自己的排名
	 * @return
	 * @throws Exception
	 */
	@Action("findMyRank")
	public String findMyRank() throws Exception {
		if (null == phone || "".equals(phone)) {
			logger.info("请输入手机号码");
			write("1");  // 手机号码不为能
			return null;
		}
		
		FuUser fuUser = fuUserService.findUserByAccount(phone);
		if(null == fuUser) {
			logger.info("请先报名比赛");
			write("2");  // 请先报名比赛
			return null;
		}else {
			MatchXhCaiFu mxc = matchXhCaiFuService.findByPhone(phone);
			if (null == mxc) {
				logger.info("请先报名比赛");
				write("2");  // 请先报名比赛
				return null;
			} else {
				write("3");  // 满足查询条件
			}
		}
		return null;
	}
	
	@Action("myChart")
	public String findMyRanKAjax() {
		MatchXhCaiFu mxc = matchXhCaiFuService.findByPhone(phone);
		// 查询第一名
		List<MatchXhCaiFu> max = matchXhCaiFuService.findMax(0, 1);
		MatchXhCaiFu mxc1 = null;
		if (null != max && max.size() > 0) {
			mxc1 = max.get(0);
		}
		// 查询自己的排名
		
		// 查询收益排行
		int rank = matchXhCaiFuService.findByPhoneAndProfit(mxc.getProfit());
		this.getHttpServletRequest().setAttribute("rank", rank == 0 ? 1 : rank);
		this.getHttpServletRequest().setAttribute("userName", phone.replace(phone.substring(3, 8), "*****"));
		this.getHttpServletRequest().setAttribute("profit", mxc.getProfit());
		this.getHttpServletRequest().setAttribute("disparity", (mxc1.getProfit()-mxc.getProfit()) == 0 ? 1 : (mxc1.getProfit()-mxc.getProfit()));
		
		// 查询推荐人排行
		int recommendRank = matchXhCaiFuService.findByRecommendNum(mxc.getRecommendNum());
		this.getHttpServletRequest().setAttribute("recommendRank", recommendRank == 0 ? 1 : recommendRank);
		this.getHttpServletRequest().setAttribute("userName", phone.replace(phone.substring(3, 8), "*****"));
		this.getHttpServletRequest().setAttribute("recommendNum", mxc.getRecommendNum());
		this.getHttpServletRequest().setAttribute("recommendDisparity", (mxc1.getRecommendNum()-mxc.getRecommendNum()) == 0 ? 1 : (mxc1.getRecommendNum()-mxc.getRecommendNum()));
		// 查询幸运奖	0: 没有中 1:中了
		this.getHttpServletRequest().setAttribute("isLucky", mxc.getIsLucky());
		return SUCCESS;
		
	}

	
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}	
}