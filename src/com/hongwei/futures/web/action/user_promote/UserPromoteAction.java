package com.hongwei.futures.web.action.user_promote;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuPromote;
import com.hongwei.futures.model.FuPromoteVisit;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuPromoteService;
import com.hongwei.futures.service.FuPromoteVisitService;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.util.DesUtil;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("fu_common")
public class UserPromoteAction extends BaseAction {
	private static final long serialVersionUID = -7707043666179169244L;
	@Autowired
	private FuPromoteService promoteService;
	@Autowired
	private FuUserService userService;
	@Autowired
	private FuPromoteVisitService promoteVisitService;

	private FuUser fuUser;
	private Long userId;
	private Integer totalCount;

	/**
	 * 推广详情
	 * 
	 * @return
	 */
	@Action("promoteDetail")
	public String promoteDetail() {
		try {
			// 推广返佣排行榜
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("commission", true);
			List<FuUser> userList = userService.findUserList(0, 8, map);
			this.getActionContext().put("userList", userList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 推广链接
	 * 
	 * @return
	 */
	@Action("promoteLink")
	public String promoteLink() {
		try {
			this.getActionContext().put("u",
					DesUtil.encryptBasedDes(fuUser.getId().toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 访问记录
	 * 
	 * @return
	 */
	@Action("visitRecord")
	public String visitRecord() {
		try {
			if (totalCount == null) {
				totalCount = promoteVisitService.getCountByPromote(userId);
			}
			List<FuPromoteVisit> visitList = promoteVisitService.findByPromote(
					(this.getPageNo() - 1) * this.getPageSize(),
					this.getPageSize(), userId);
			this.getActionContext().put("visitList", visitList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 我的用户
	 * 
	 * @return
	 */
	@Action("myUsers")
	public String myUsers() {
		try {
			if (totalCount == null) {
				totalCount = promoteService.getCountByPromote(userId);
			}
			List<FuPromote> promoteList = promoteService.findByPromote(
					(this.getPageNo() - 1) * this.getPageSize(),
					this.getPageSize(), userId);
			this.getActionContext().put("promoteList", promoteList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
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

}
