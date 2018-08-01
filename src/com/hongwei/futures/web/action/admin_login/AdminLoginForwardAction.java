package com.hongwei.futures.web.action.admin_login;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.AdminLoginLog;
import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.SysRole;
import com.hongwei.futures.service.AdminLoginLogService;
import com.hongwei.futures.service.FuAddMarginService;
import com.hongwei.futures.service.FuAdminService;
import com.hongwei.futures.service.FuBadCreditService;
import com.hongwei.futures.service.FuCommissionService;
import com.hongwei.futures.service.FuContinueContractService;
import com.hongwei.futures.service.FuDrawMoneyService;
import com.hongwei.futures.service.FuDrawProfitsService;
import com.hongwei.futures.service.FuManageFeeService;
import com.hongwei.futures.service.FuMessageService;
import com.hongwei.futures.service.FuProgramService;
import com.hongwei.futures.service.FuRechargeService;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.service.HhrQualiRegisterService;
import com.hongwei.futures.service.SysRoleService;
import com.hongwei.futures.util.WebUtil;
import com.hongwei.futures.web.action.BaseAction;
import com.opensymphony.xwork2.ActionContext;

@ParentPackage("admin")
public class AdminLoginForwardAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8280244663987546332L;
	@Autowired
	private AdminLoginLogService adminLoginLogService;
	@Autowired
	private FuMessageService fuMessageService;
	@Autowired
	private FuDrawMoneyService drawMoneyService;
	@Autowired
	private FuRechargeService rechargeService;
	@Autowired
	private FuCommissionService commissionService;
	@Autowired
	private FuManageFeeService fuManageFeeService;
	@Autowired
	private FuAddMarginService fuAddMarginService;
	@Autowired
	private FuDrawProfitsService drawProfitsService;
	@Autowired
	private FuContinueContractService fuContinueContractService;
	@Autowired
	private FuProgramService fuProgramService;
	@Autowired
	private FuUserService fuUserService;
	@Autowired
	private HhrQualiRegisterService qualiService;
	@Autowired
	private FuBadCreditService fuBadCreditService;
	@Autowired
	private FuAdminService fuAdminService;
	@Autowired
	private SysRoleService sysRoleService;

	private FuAdmin admin;
	private Long adminId;
	private Integer first;

	/**
	 * 中间页面
	 * 
	 * @return
	 */
	@Action("adminForward")
	public String adminForward() {
		return SUCCESS;
	}

	/**
	 * 后台首页
	 * 
	 * @return
	 */
	@Action(value = "indexHome", results = { @Result(name = "toIndex", location = "/admin_login/adminLogin.htm", type = "redirect") })
	public String indexHome() {
		try {
			admin = (FuAdmin) ActionContext.getContext().getSession().get("admin");
			if (null != admin) {
				List<SysRole> roles = sysRoleService.findRoleListByRoleId(admin.getId());
				String roleName = "";
				if (roles.size() > 0) {
					for (SysRole sysRole : roles) {
						roleName = roleName + sysRole.getRoleName() + "，";// 将该管理员的所有角色拼接起来用逗号分隔
					}
					roleName = roleName.substring(0, roleName.length() - 1);
				}
				this.getActionContext().put("roleName", roleName);
				return SUCCESS;
			} else {
				return "toIndex";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 统计方法
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("staticRemindAjax")
	public String staticRemind() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			HashMap<String, Object> map1 = new HashMap<String, Object>();
			JSONObject obj = new JSONObject();
			if (first == 1) {
				// 留言未回复的
				map.put("noReply", true);
				int messageCount = fuMessageService.countAllMessage(map);
				obj.put("messageCount", messageCount);
			}
			if (first == 2) {
				// 提款待审核
				map.put("status", 0);
				int drawMoneyCount = drawMoneyService.getCount(map);
				obj.put("drawMoneyCount", drawMoneyCount);
				// 充值审核
				map.put("rechargeStatus", 0);
				int rechargeCount = rechargeService.getCount(map);
				obj.put("rechargeCount", rechargeCount);
				// 佣金兑换
				map.put("status", 0);
				int exchangeCount = commissionService.getCount(map);
				obj.put("exchangeCount", exchangeCount);
				// 用户认证
				int identityCount = fuUserService.countCheckUser2(map);
				obj.put("identityCount", identityCount);
				// 资格认证
				int qualiCount = qualiService.countQualiPerson(map);
				obj.put("qualiCount", qualiCount);
			}
			if (first == 3) {
				// 方案管理
				map.put("status", 0);
				int programCount = fuProgramService.countWaitProgram(map);
				obj.put("programCount", programCount);
				// 管理费审核
				map1.put("state", 0);
				int feeCount = fuManageFeeService.countFee(map1);
				obj.put("feeCount", feeCount);
				// 待追加保证金
				int safeMoneyCount = fuAddMarginService.countSafeMoney(map1);
				obj.put("safeMoneyCount", safeMoneyCount);
				// 待利润提取
				int drawProfitsCount = drawProfitsService.countDrawProfits(map1);
				obj.put("drawProfitsCount", drawProfitsCount);
				// 续约记录
				map.put("result", 0);
				int continueCount = fuContinueContractService.countContinue(map);
				obj.put("continueCount", continueCount);
				// 待结算方案
				map.put("status", 4);
				int overProgramCount = fuProgramService.countProgram(map);
				obj.put("overProgramCount", overProgramCount);
				// 不良信用记录
				int badCreditCount = fuBadCreditService.countBadCredit(map1);
				obj.put("badCreditCount", badCreditCount);
			}
			write(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 退出登录
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("logoutAjax")
	public String logoutAjax() {
		try {
			String token = WebUtil.getCookieByName(getHttpServletRequest(), "admin_token");
			if (null != token && !"".equals(token)) {
				FuAdmin admin = fuAdminService.findLoginByToken(token);
				if (admin != null) {
					admin.setLoginToken("");
					fuAdminService.save(admin);
					//创建登出日志
					AdminLoginLog log = new AdminLoginLog();
					log.setFuAdmin(admin);
					log.setLogType(1);
					log.setLogTime(new Date());
					adminLoginLogService.save(log);
					write("-1");// 退出成功
				} else {
					write("-2");// 请先登录
				}
			}
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

	public Integer getFirst() {
		return first;
	}

	public void setFirst(Integer first) {
		this.first = first;
	}
}
