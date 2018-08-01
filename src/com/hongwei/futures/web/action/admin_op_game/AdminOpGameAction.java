package com.hongwei.futures.web.action.admin_op_game;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuGame;
import com.hongwei.futures.model.FuProgram;
import com.hongwei.futures.model.FuSmsLog;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuGameService;
import com.hongwei.futures.service.FuProgramService;
import com.hongwei.futures.service.FuSmsLogService;
import com.hongwei.futures.util.StringUtil;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminOpGameAction extends BaseAction {
	@Autowired
	private FuProgramService fuProgramService;
	@Autowired
	private FuGameService fuGameService;
	@Autowired
	private FuSmsLogService fuSmsLogService;

	private FuAdmin admin;
	private Long adminId;
	private BigDecimal gameIncomeWeek;
	private BigDecimal gameIncomeMonth;
	private Long id;
	private String content;
	private Integer competitionNum;

	/**
	 * 设置盈利
	 * 
	 * @return
	 */
	@Action("inComeInfoAjax")
	public String inComeInfoAjax() {
		try {
			FuProgram pro = fuProgramService.get(id);
			this.getActionContext().put("pro", pro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 保存盈利设置信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveInComeInfoAjax")
	public String saveInComeInfoAjax() {
		try {
			if (gameIncomeWeek == null) {
				write("-2");
				return null;
			}
			if (gameIncomeMonth == null) {
				write("-3");
				return null;
			}
			FuProgram pro = fuProgramService.get(id);
			pro.setGameIncomeWeek(gameIncomeWeek);
			pro.setGameIncomeMonth(gameIncomeMonth);
			fuProgramService.save(pro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 设置大赛短信
	 * 
	 * @return
	 */
	@Action("gameMessageAjax")
	public String gameMessageAjax() {
		return SUCCESS;
	}

	/**
	 * 发送大赛短信
	 * 
	 * @return
	 */
	@Action("saveSendGameMsgAjax")
	public String saveSendGameMsgAjax() {
		try {
			if (StringUtil.isBlank(content)) {
				write("-1");// 请输入短息内容
				return null;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("competitionNum", competitionNum);
			List<FuGame> list = fuGameService.findGameProgramList(0, Integer.MAX_VALUE, map);
			for (int i = 0; i < list.size(); i++) {
				FuGame fuGame = list.get(i);
				FuUser fuUser = fuGame.getFuUser();

				String message = URLDecoder.decode(content, "UTF-8");
				FuSmsLog log = new FuSmsLog();
				log.setFuUser(fuUser);
				log.setContent(message);
				log.setPrio(1);
				log.setReason("期指大赛通知");
				log.setDestination(fuUser.getPhone());
				log.setPlanTime(new Date());
				log.setType(1);// 短信
				log.setState(0);
				fuSmsLogService.save(log);
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

	public BigDecimal getGameIncomeWeek() {
		return gameIncomeWeek;
	}

	public void setGameIncomeWeek(BigDecimal gameIncomeWeek) {
		this.gameIncomeWeek = gameIncomeWeek;
	}

	public BigDecimal getGameIncomeMonth() {
		return gameIncomeMonth;
	}

	public void setGameIncomeMonth(BigDecimal gameIncomeMonth) {
		this.gameIncomeMonth = gameIncomeMonth;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getCompetitionNum() {
		return competitionNum;
	}

	public void setCompetitionNum(Integer competitionNum) {
		this.competitionNum = competitionNum;
	}

}
