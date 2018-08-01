package com.hongwei.futures.web.action.admin_op_check_person;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.service.HhrStatService;
import com.hongwei.futures.util.MYSQLDBHelper;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminOpCheckPersonAction extends BaseAction {
	private static final long serialVersionUID = 556742394202884873L;

	@Autowired
	private FuUserService fuUserService;

	@Autowired
	private HhrStatService hhrStatService;

	private Long adminId;
	private FuAdmin admin;
	private Long id;
	private Integer flag;
	private String accountName;
	private String userName;
	private String phone;
	private String pName;
	private String pPhone;
	private Long parentId;

	/**
	 * 查看身份证认证的信息
	 * 
	 * @return
	 */
	@Action("checkDetailAjax")
	public String checkDetailAjax() {
		try {
			FuUser u = fuUserService.get(id);
			this.getActionContext().put("u", u);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 设置上层用户
	 * 
	 * @return
	 */
	@Action("parentUserList")
	public String parentUserList() {
		try {
			FuUser u = fuUserService.get(id);
			this.getActionContext().put("u", u);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 搜索用户
	 * 
	 * @return
	 */
	@Action("parentUser")
	public String parentUser() {
		try {
			JSONArray jsonArr = new JSONArray();
			Map<String, Object> map = new HashMap<String, Object>();
			List<FuUser> parentList = null;
			map.put("searchType", "1");
			pPhone = getHttpServletRequest().getParameter("pPhone");
			if (!StringUtil.isBlank(pPhone)) {
				map.put("phone", pPhone);
			}
			if (!StringUtil.isBlank(pName) || !StringUtil.isBlank(pPhone)) {
				parentList = fuUserService.findUserList((this.getPageNo() - 1)
						* this.getPageSize(), this.getPageSize(), map);
				for (int i = 0; i < parentList.size(); i++) {
					JSONObject obj = new JSONObject();
					obj.put("ID", parentList.get(i).getId() == null ? null
							: parentList.get(i).getId());
					obj.put("ACCOUNT_NAME",
							parentList.get(i).getUserName() == null ? ""
									: parentList.get(i).getAccountName());
					obj.put("USER_NAME",
							parentList.get(i).getUserName() == null ? ""
									: parentList.get(i).getUserName());
					obj.put("INVITATION_CODE",
							parentList.get(i).getInvitationCode() == null ? ""
									: parentList.get(i).getInvitationCode());
					obj.put("PHONE", parentList.get(i).getPhone());
					jsonArr.add(obj);
				}
			}
			write(jsonArr.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 保存上层用户
	 * 
	 * @return
	 */
	@Action("saveParentUserAjax")
	public String saveParentUserAjax() throws Exception {
		try {
			fuUserService.saveMoveHhr(id, parentId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 更新合伙人数量
	 * 
	 * @return
	 */
	@Action("updateHhrAjax")
	public String updateHhrAjax() {
		try {
			System.out.println("----------------更新合伙人数量, 开始时间: " + new Date()
					+ "----------------");
			// 所有用户
			List<FuUser> allList = getMemberList();
			// pid,ids
			Map<Long, Map<Long, Long>> userMap = new HashMap<Long, Map<Long, Long>>();
			for (FuUser user : allList) {
				Map<Long, Long> map = userMap.get(user.getHhrParentID());
				if (map == null) {
					map = new HashMap<Long, Long>();
					map.put(user.getId(), user.getId());
					userMap.put(user.getHhrParentID(), map);
				} else {
					map.put(user.getId(), user.getId());
				}
			}

			for (FuUser u : allList) {
				int firstMemberNum = 0, secondMemberNum = 0;
				Map<Long, Long> map = userMap.get(u.getId());
				if (map != null) {
					firstMemberNum = map.size();
					Iterator<Entry<Long, Long>> it = map.entrySet().iterator();
					while (it.hasNext()) {
						Entry<Long, Long> entry = it.next();
						secondMemberNum = secondMemberNum
								+ getMemberLowLevelNumber(entry.getKey(),
										userMap);
					}
				}
				String strSql = "update hhr_stat set firstly_partner_num=?, secondary_partner_num=? where user_id=?";
				Object[] obj = new Object[] { firstMemberNum, secondMemberNum,
						u.getId() };
				MYSQLDBHelper.executeNonQuery(strSql, obj);
			}
			System.out.println("----------------更新合伙人数量, 结束时间: " + new Date()
					+ "----------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getMemberLowLevelNumber(Long userId,
			Map<Long, Map<Long, Long>> userMap) {
		int num = 0;
		try {
			Map<Long, Long> map = userMap.get(userId);
			if (map != null) {
				num = map.size();
				Iterator<Entry<Long, Long>> it = map.entrySet().iterator();
				while (it.hasNext()) {
					Entry<Long, Long> entry = it.next();
					num = num
							+ getMemberLowLevelNumber(entry.getKey(), userMap);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}

	/***
	 * 获取合伙人列表
	 * 
	 * @return
	 */
	public List<FuUser> getMemberList() {
		List<FuUser> memberList = new ArrayList<FuUser>();
		try {
			String sql2 = "Select id, hhr_parentID, hhr_level from fu_user";
			// 第一步：查询
			ResultSet rs2 = MYSQLDBHelper.executeQuery(sql2);
			// 第二步：输出
			while (rs2.next()) {
				FuUser member = new FuUser();
				member.setId(rs2.getLong("id"));
				member.setHhrParentID(rs2.getLong("hhr_parentID"));
				member.setHhrLevel(rs2.getInt("hhr_level"));
				memberList.add(member);
			}
			// 第三步：关闭
			MYSQLDBHelper.free(rs2);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return memberList;
	}

	/**
	 * 审核认证
	 * 
	 * @return
	 */
	@Action("saveCheckDetailAjax")
	public String saveCheckDetailAjax() {
		try {
			FuUser user = fuUserService.get(id);
			user.setIsCheckCard(flag);
			if (flag == 0) { // 未认证
				user.setUserName(null);
				user.setCardNumber(null);
			}
			if (flag == 2) { // 已认证
				user.setSafeLevel(user.getSafeLevel() == null ? 1 : user
						.getSafeLevel() + 1);
			}
			fuUserService.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public FuAdmin getAdmin() {
		return admin;
	}

	public void setAdmin(FuAdmin admin) {
		this.admin = admin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
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

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpPhone() {
		return pPhone;
	}

	public void setpPhone(String pPhone) {
		this.pPhone = pPhone;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
