package com.hongwei.futures.task;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuGame;
import com.hongwei.futures.model.FuParameter;
import com.hongwei.futures.service.FuGameService;
import com.hongwei.futures.service.FuParameterService;

public class GameUtil extends TimerTask{
	@Autowired
	private FuGameService fuGameService;
	@Autowired
	private FuParameterService fuParameterService;
	
	/**
	 * 每个月的1号0点创建期货大赛
	 */
	@Override
	public void run() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 1);
		try {
			FuGame gm = fuGameService.findGameByTime(sdf.parse(sdf.format(calendar.getTime())));
			if(gm==null){
				FuGame game=new FuGame();
				FuParameter param=fuParameterService.findParameter();
				game.setGameTime(sdf.parse(sdf.format(calendar.getTime())));
				game.setJoinNum(0);
				if(param.getGameMoney()==null)
					game.setGameMoney(new BigDecimal(0.00));
				else
					game.setGameMoney(param.getGameMoney());
				fuGameService.save(game);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
			
	}
	
	
	
}
