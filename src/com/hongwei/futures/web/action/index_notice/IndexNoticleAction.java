package com.hongwei.futures.web.action.index_notice;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuNotice;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuNoticeService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("fu_common")
public class IndexNoticleAction extends BaseAction {
	@Autowired
	private FuNoticeService fuNoticeService;

	private FuUser fuUser;
	private Long userId;
	private Long id;
	private Integer totalCount;

	/**
	 * 公告列表
	 * 
	 * @return
	 */
	@Action("noticeList")
	public String noticeList() {
		try {
			if (totalCount == null)
				totalCount = fuNoticeService.countNotice();
			List<FuNotice> noticeList = fuNoticeService.findNoticeList(
					(this.getPageNo() - 1) * this.getPageSize(),
					this.getPageSize());
			this.getActionContext().put("noticeList", noticeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 公告详情
	 * 
	 * @return
	 */
	@Action("noticeDetail")
	public String noticeDetail() {
		try {
			FuNotice notice = fuNoticeService.get(id);
			this.getActionContext().put("notice", notice);
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

}
