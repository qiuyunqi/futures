package com.hongwei.futures.web.action.admin_list_live;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.LiveDraw;
import com.hongwei.futures.model.LiveGuess;
import com.hongwei.futures.service.LiveDrawService;
import com.hongwei.futures.service.LiveGuessService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminListLiveAction extends BaseAction {
	@Autowired
	private LiveDrawService liveDrawService;
	@Autowired
	private LiveGuessService liveGuessService;

	private FuAdmin admin;
	private String createAdmin;
	private Integer totalCount;
	private Date time1;
	private Date time2;
	private Integer isWinning;
	private String userName;

	/**
	 * 直播抽奖后台列表
	 * 
	 * @return
	 * @throws ParseException
	 */
	@Action("liveDrawList")
	public String liveDrawList() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (time1 != null)
				map.put("time1", time1);
			if (time2 != null)
				map.put("time2", time2);
			if (!StringUtil.isBlank(createAdmin)) {
				map.put("createAdmin", createAdmin);
			}
			if (totalCount == null)
				totalCount = liveDrawService.countLive(map);
			List<LiveDraw> list = liveDrawService.findLiveDrawList(
					(this.getPageNo() - 1) * this.getPageSize(),
					this.getPageSize(), map);
			this.getActionContext().put("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 直播抽奖前台列表
	 * 
	 * @return
	 * @throws ParseException
	 */
	@Action("liveGuessList")
	public String liveGuessList() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (isWinning != null)
				map.put("isWinning", isWinning);
			if (!StringUtil.isBlank(userName)) {
				map.put("userName", userName);
			}
			if (totalCount == null)
				totalCount = liveGuessService.countLiveGuess(map);
			List<LiveGuess> list = liveGuessService.findLiveGuessList(
					(this.getPageNo() - 1) * this.getPageSize(),
					this.getPageSize(), map);
			this.getActionContext().put("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public FuAdmin getAdmin() {
		return admin;
	}

	public void setAdmin(FuAdmin admin) {
		this.admin = admin;
	}

	public String getCreateAdmin() {
		return createAdmin;
	}

	public void setCreateAdmin(String createAdmin) {
		this.createAdmin = createAdmin;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Date getTime1() {
		return time1;
	}

	public void setTime1(Date time1) {
		this.time1 = time1;
	}

	public Date getTime2() {
		return time2;
	}

	public void setTime2(Date time2) {
		this.time2 = time2;
	}

	public Integer getIsWinning() {
		return isWinning;
	}

	public void setIsWinning(Integer isWinning) {
		this.isWinning = isWinning;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
