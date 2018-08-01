package com.hongwei.futures.web.action.admin_op_bad_record;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuBadCredit;
import com.hongwei.futures.model.FuProgram;
import com.hongwei.futures.model.FuServer;
import com.hongwei.futures.service.FuBadCreditService;
import com.hongwei.futures.service.FuProgramService;
import com.hongwei.futures.web.action.BaseAction;
import com.hongwei.futures.zhongqi.ZhongqiService;

@ParentPackage("admin")
public class AdminOpBadRecordAction extends BaseAction {
	@Autowired
	private FuBadCreditService fuBadCreditService;
	@Autowired
	private FuProgramService fuProgramService;
	@Autowired
	private ZhongqiService zhongqiService;

	private FuAdmin admin;
	private String detail;
	private Long id;
	private Integer state;

	/**
	 * 审核不良记录页面（后台）
	 * 
	 * @return
	 */
	@Action("checkBadRecordAjax")
	public String checkFeeAjax() {
		try {
			FuBadCredit badCredit = fuBadCreditService.get(id);
			this.getActionContext().put("badCredit", badCredit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 保存不良记录审核结果
	 * 
	 * @return
	 */
	@Action("saveBadRecordAjax")
	public String saveBadRecordAjax() {
		try {
			FuBadCredit badCredit = fuBadCreditService.get(id);
			FuProgram pro = badCredit.getFuProgram();
			FuServer fuServer = pro.getFuServer();
			BigDecimal balance = zhongqiService.findBalance(fuServer,
					pro.getTradeAccount());
			BigDecimal matchMoney = pro.getMatchMoney();
			if (pro.getAddMatchId() != null && pro.getAddMatchId() > 0) {
				FuProgram primayPro = fuProgramService.get(pro.getAddMatchId());
				matchMoney = matchMoney.add(primayPro.getMatchMoney());
			}
			BigDecimal money = matchMoney.subtract(balance);
			if (money.compareTo(BigDecimal.ZERO) == 1) {
				if (pro.getStatus() != 7) {
					fuProgramService.saveAcrossCabin(pro, money);
				}
			}
			if (badCredit.getState() > 0) {
				write("-1");
				return null;
			} else if (state == null) {
				write("-2");
				return null;
			} else if (state == 2 && money.compareTo(BigDecimal.ZERO) == 1) {
				// 查询到还是穿仓，所以无法选择已追回
				write("-3");
				return null;
			} else {
				badCredit.setState(state);
				badCredit.setDetail(detail);
				badCredit.setFuAdmin(admin);
				badCredit.setAddTime(new Date());
				fuBadCreditService.save(badCredit);

				return null;
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

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}
