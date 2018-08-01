package com.hongwei.futures.web.action.admin_op_live;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.LiveDraw;
import com.hongwei.futures.service.LiveDrawService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminOpLiveAction extends BaseAction {
	@Autowired
	private LiveDrawService liveDrawService;

	private FuAdmin admin;
	private Long adminId;
	private Long id;
	private BigDecimal money;
	private String question;
	private BigDecimal answer;
	private Date beginTime;
	private Date endTime;

	/**
	 * 添加或修改抽奖信息
	 * 
	 * @return
	 */
	@Action("updateLiveDraw")
	public String updateLiveDraw() {
		try {
			if (id != null) {
				LiveDraw liveDraw = liveDrawService.get(id);
				this.getActionContext().put("liveDraw", liveDraw);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 保存直播抽奖信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveLiveDraw")
	public String saveLiveDraw() {
		try {
			LiveDraw liveDraw;
			if (id == null) {
				liveDraw = new LiveDraw();
				liveDraw.setCreateAdmin(admin);
				liveDraw.setCreateTime(new Date());
				liveDraw.setIsPublish(0);
			} else {
				liveDraw = liveDrawService.get(id);
			}
			liveDraw.setQuestion(question);
			liveDraw.setBeginTime(beginTime);
			liveDraw.setEndTime(endTime);
			liveDrawService.save(liveDraw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 公布答案页面
	 * 
	 * @return
	 */
	@Action("publishAnswer")
	public String publishAnswer() {
		try {
			this.getActionContext().put("id", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 公布直播抽奖答案
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("savePublishAnswer")
	public String savePublishAnswer() {
		try {
			liveDrawService.savePublishAnswer(id, answer, money);
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

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public BigDecimal getAnswer() {
		return answer;
	}

	public void setAnswer(BigDecimal answer) {
		this.answer = answer;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

}
