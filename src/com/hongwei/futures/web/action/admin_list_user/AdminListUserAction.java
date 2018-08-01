package com.hongwei.futures.web.action.admin_list_user;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuTransaction;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuAdminService;
import com.hongwei.futures.service.FuTransactionService;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.util.CommonUtils;
import com.hongwei.futures.util.DateUtil;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminListUserAction extends BaseAction {
	private static final long serialVersionUID = -3783526408908828292L;
	@Autowired
	private FuUserService fuUserService;
	@Autowired
	private FuAdminService fuAdminService;
	@Autowired
	private FuTransactionService fuTransactionService;

	private FuAdmin admin;
	private Long adminId;
	private Long id;
	private Integer totalCount;
	private Long userId;
	private String accountName;
	private String userName;
	private String phone;
	private String cardNumber;
	private String email;
	private BigDecimal matchMoney1;
	private BigDecimal matchMoney2;
	private BigDecimal safeMoney1;
	private BigDecimal safeMoney2;
	private String invitationCode;
	private Integer searchType;// 模糊查询null和0，精确查询1
	private Integer isAcrossCabin;
	private String oldPwd;
	private String newPwd;
	private String rePwd;
	private String time1;
	private String time2;
	private Date time3;
	private Date time4;
	private Integer state;
	private String registerIp;
	private Integer hhrType;
	private String newPassword;
	private String rePassword;
	private String name; // 交易团队的名称 只有hhrType == 2的时候才会有值
	private Integer qidaRank;

	/**
	 * 用户列表
	 * 
	 * @return
	 */
	@Action("userList")
	public String userList() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (searchType == null)
				searchType = 1;
			map.put("searchType", searchType);
			if (userId != null) {
				map.put("userId", userId);// 用户id查询
			}
			if (!StringUtil.isBlank(accountName)) {
				map.put("accountName", accountName);// 用户名查询
			}
			if (!StringUtil.isBlank(userName)) {
				map.put("userName", userName);// 真实名字查询
			}
			if (!StringUtil.isBlank(phone)) {
				map.put("phone", phone);// 电话号码查询
			}
			if (!StringUtil.isBlank(cardNumber)) {
				map.put("cardNumber", cardNumber);// 身份证号码查询
			}
			if (!StringUtil.isBlank(email)) {
				map.put("email", email);// 邮箱查询
			}
			if (state != null) {
				map.put("state", state);
			}
			if (!StringUtil.isBlank(registerIp)) {
				map.put("registerIp", registerIp);
			}
			if (matchMoney1 != null) {
				map.put("matchMoney1", matchMoney1);
			}
			if (matchMoney2 != null) {
				map.put("matchMoney2", matchMoney2);
			}
			if (safeMoney1 != null) {
				map.put("safeMoney1", safeMoney1);
			}
			if (safeMoney2 != null) {
				map.put("safeMoney2", safeMoney2);
			}
			if (!StringUtil.isBlank(time1)) {
				map.put("time1", DateUtil.getDateFromString(time1, "yyyy-MM-dd"));
			}
			if (!StringUtil.isBlank(time2)) {
				map.put("time2", DateUtil.getDateFromString(time2, "yyyy-MM-dd"));
			}
			if (time3 != null) {
				map.put("time3", time3);
			}
			if (time4 != null) {
				map.put("time4", time4);
			}
			if (!StringUtil.isBlank(invitationCode)) {
				map.put("invitationCode", invitationCode);// 用户名查询
			}
			if (totalCount == null)
				totalCount = fuUserService.countUser(map);
			List<FuUser> userList = fuUserService.findUserList((this.getPageNo() - 1) * this.getPageSize(), this.getPageSize(), map);
			this.getActionContext().put("userList", userList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 查看用户信息
	 * 
	 * @return
	 */
	@Action("userDetail")
	public String userDetail() {
		try {
			FuUser u = fuUserService.get(id);
			FuUser parentUser = fuUserService.get(u.getHhrParentID());
			FuUser loginUser = fuUserService.get(id);
			List<FuUser> parentList = new ArrayList<FuUser>();
			while (loginUser.getHhrParentID() != null && loginUser.getHhrParentID() != 0) {
				FuUser parent_user = fuUserService.get(loginUser.getHhrParentID());
				parentList.add(parent_user);
				loginUser = parent_user;
			}
			FuTransaction transaction = fuTransactionService.findByUserId(u.getId());
			this.getActionContext().put("transaction", transaction);
			this.getActionContext().put("u", u);
			this.getActionContext().put("parentUser", parentUser);
			this.getActionContext().put("parentList", parentList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 设置用户是否穿仓
	 * 
	 * @return
	 */
	@Action("saveIsAcrossCabinAjax")
	public String saveIsAcrossCabinAjax() {
		try {
			FuUser u = fuUserService.get(id);
			u.setIsAcrossCabin(isAcrossCabin);
			fuUserService.save(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 设置用户是否启用
	 * 
	 * @return
	 */
	@Action("saveSateAjax")
	public String saveSateAjax() {
		try {
			FuUser u = fuUserService.get(id);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("phone", u.getPhone());
			if (state == 1) {// 如果是启用，就要判断是否有其他使用这个手机号的账户状态为启用了
				List<FuUser> list = fuUserService.findUserList(0, Integer.MAX_VALUE, map);
				if (list.size() > 0) {
					for (FuUser fuUser : list) {
						if (fuUser.getState() == 1) {
							write("-1");
							return null;
						}
					}
				}
			}
			u.setState(state);
			fuUserService.save(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 修改管理员密码
	 * 
	 * @return
	 */
	@Action("editPasswordAjax")
	public String editPasswordAjax() {
		return SUCCESS;
	}

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
			if (StringUtil.isBlank(rePwd)) {
				write("-4");// 请输入确认密码
				return null;
			}
			if (!newPwd.equals(rePwd)) {
				write("-5");// 新密码和确认密码不一致
				return null;
			}
			if (!admin.getPassword().equals(CommonUtils.getMd5(oldPwd))) {
				write("-6");// 旧密码输入错误
				return null;
			}
			admin.setPassword(CommonUtils.getMd5(newPwd));
			fuAdminService.save(admin);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

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
	 * 重置用户密码
	 * 
	 * @return
	 */
	@Action("resetUserPassword")
	public String resetUserPassword() {
		this.getActionContext().put("fuUser", fuUserService.get(id));
		return SUCCESS;
	}

	/**
	 * 保存重置用户登录密码
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveUserPasswordAjax")
	public String saveUserPasswordAjax() {
		try {
			if (StringUtil.isBlank(rePassword)) {
				write("-4");// 请输入确认密码
				return null;
			}
			if (!newPassword.equals(rePassword)) {
				write("-5");// 新密码和确认密码不一致
				return null;
			}
			FuUser user = fuUserService.get(userId);
			user.setPassword(CommonUtils.getMd5(newPassword));
			fuUserService.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 保存奇答身份
	 * 
	 * @return
	 */
	@Action("saveQidaRankAjax")
	public String saveQidaRankAjax() {
		try {
			FuUser u = fuUserService.get(id);
			u.setQidaRank(qidaRank);
			fuUserService.save(u);
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getSearchType() {
		return searchType;
	}

	public void setSearchType(Integer searchType) {
		this.searchType = searchType;
	}

	public BigDecimal getMatchMoney1() {
		return matchMoney1;
	}

	public void setMatchMoney1(BigDecimal matchMoney1) {
		this.matchMoney1 = matchMoney1;
	}

	public BigDecimal getMatchMoney2() {
		return matchMoney2;
	}

	public void setMatchMoney2(BigDecimal matchMoney2) {
		this.matchMoney2 = matchMoney2;
	}

	public BigDecimal getSafeMoney1() {
		return safeMoney1;
	}

	public void setSafeMoney1(BigDecimal safeMoney1) {
		this.safeMoney1 = safeMoney1;
	}

	public BigDecimal getSafeMoney2() {
		return safeMoney2;
	}

	public void setSafeMoney2(BigDecimal safeMoney2) {
		this.safeMoney2 = safeMoney2;
	}

	public String getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}

	public Integer getIsAcrossCabin() {
		return isAcrossCabin;
	}

	public void setIsAcrossCabin(Integer isAcrossCabin) {
		this.isAcrossCabin = isAcrossCabin;
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

	public String getTime1() {
		return time1;
	}

	public void setTime1(String time1) {
		this.time1 = time1;
	}

	public String getTime2() {
		return time2;
	}

	public void setTime2(String time2) {
		this.time2 = time2;
	}

	public Date getTime3() {
		return time3;
	}

	public void setTime3(Date time3) {
		this.time3 = time3;
	}

	public Date getTime4() {
		return time4;
	}

	public void setTime4(Date time4) {
		this.time4 = time4;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getRegisterIp() {
		return registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
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

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public Integer getQidaRank() {
		return qidaRank;
	}

	public void setQidaRank(Integer qidaRank) {
		this.qidaRank = qidaRank;
	}
}
