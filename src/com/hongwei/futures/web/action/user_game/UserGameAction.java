package com.hongwei.futures.web.action.user_game;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuGame;
import com.hongwei.futures.model.FuParameter;
import com.hongwei.futures.model.FuProgram;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuGameService;
import com.hongwei.futures.service.FuParameterService;
import com.hongwei.futures.service.FuProgramService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("fu_common")
public class UserGameAction extends BaseAction {
	@Autowired
	private FuProgramService fuProgramService;
	@Autowired
	private FuGameService fuGameService;
	@Autowired
	private FuParameterService fuParameterService;

	private FuUser fuUser;
	private Long userId;

	/**
	 * 保存参加期货大赛的信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveGameInfoAjax")
	public String saveGameInfoAjax() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DATE, 1);
			FuParameter param = fuParameterService.findParameter();
			if (param == null || param.getGameMoney() == null) {
				write("-2");
				return null;
			}
			if (fuUser.getIsCheckCard() == null || fuUser.getIsCheckCard() != 2) {
				write("-3");
				return null;
			}
			FuGame game = fuGameService.findGameByTime(sdf.parse(sdf.format(cal
					.getTime())));
			if (game == null) {
				write("-6");
				return null;
			}

			FuProgram program = fuProgramService.findGameProgram(userId,
					game.getId());
			if (program != null) {
				write("-4");
				return null;
			}
			BigDecimal safeMoney = param.getGameMoney().divide(
					new BigDecimal(10));
			if (safeMoney.compareTo(fuUser.getAccountBalance()) == 1) {
				write("-5");
				return null;
			}
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, 1);

			FuProgram pro = new FuProgram();
			pro.setCloseTime(sdf.parse(sdf.format(c.getTime())));
			pro.setCreateTime(new Date());
			pro.setCycleNum(1);
			pro.setDoubleNum(10);
			pro.setFuUser(fuUser);
			pro.setMatchMoney(param.getGameMoney());
			pro.setManageMoney(new BigDecimal(0.00));
			pro.setProgramType(3);
			pro.setTotalMatchMoney(param.getGameMoney().add(safeMoney));
			pro.setSafeMoney(safeMoney);
			pro.setTradeTime(sdf.parse(sdf.format(new Date())));
			pro.setStatus(1);
			pro.setGame(game);
			pro.setGameIncomeMonth(new BigDecimal(0.00));
			pro.setGameIncomeWeek(new BigDecimal(0.00));
			game.setJoinNum(game.getJoinNum() + 1);
			fuProgramService.saveGame(pro, game);
			write("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
}
