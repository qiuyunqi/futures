package com.hongwei.futures.web.action.user_promote_forward;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuPromoteVisit;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuPromoteVisitService;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.util.DesUtil;
import com.hongwei.futures.util.WebUtil;
import com.hongwei.futures.web.action.BaseAction;

public class UserPromoteForwardAction extends BaseAction {
	private static final long serialVersionUID = 1344459083694825466L;
	@Autowired
	private FuUserService userService;
	@Autowired
	private FuPromoteVisitService visitService;

	private String u;// 推广人id 加密

	/**
	 * 推广链接
	 * 
	 * @return
	 */
	@Action(value = "promoteLink", results = { @Result(name = "toIndex", location = "/index_info/index.htm", type = "redirect") })
	public String promoteLink() {
		try {
			// u存到cookies
			Long uId = Long.valueOf(DesUtil.decryptBasedDes(u));
			WebUtil.addCookie(getHttpServletResponse(), "promoteUser",
					uId.toString(), 24 * 60 * 60 * 1000);
			String IP = this.getHttpServletRequest().getRemoteAddr();
			FuUser promoteUser = userService.get(uId);
			FuPromoteVisit visit = visitService.getByIP(uId, IP);
			if (visit == null) {// IP最新访问
				visit = new FuPromoteVisit();
				visit.setPromoteId(uId);
				visit.setVisitIp(IP);
				visit.setVisitNum(1);
				visit.setVisitTime(new Date());
				promoteUser.setVisitIp((promoteUser.getVisitIp() == null ? 0
						: promoteUser.getVisitIp()) + 1);
				promoteUser.setVisitNum((promoteUser.getVisitNum() == null ? 0
						: promoteUser.getVisitNum()) + 1);
			} else {// 该IP已访问过
				visit.setVisitNum((visit.getVisitNum() == null ? 0 : visit
						.getVisitNum()) + 1);
				visit.setVisitTime(new Date());
				promoteUser.setVisitNum((promoteUser.getVisitNum() == null ? 0
						: promoteUser.getVisitNum()) + 1);
			}
			visitService.savePromoteVisit(visit, promoteUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toIndex";
	}

	public String getU() {
		return u;
	}

	public void setU(String u) {
		this.u = u;
	}
}
