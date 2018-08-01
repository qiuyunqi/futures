package com.hongwei.futures.web.action.user_live;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.LiveDraw;
import com.hongwei.futures.model.LiveGuess;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.service.LiveDrawService;
import com.hongwei.futures.service.LiveGuessService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("fu_common")
public class UserLiveAction extends BaseAction {
	private static final long serialVersionUID = 270203279261923107L;
	
	@Autowired
	private LiveGuessService liveGuessService;
	@Autowired
	private LiveDrawService liveDrawService;
	@Autowired
	private FuUserService fuUserService;

	private Long user_id;
	private BigDecimal guessAnswer;
	private Long liveDrawId;

	@Action("liveGuess")
	public String liveGuess() {
		try {
			int flag = 1;
			Map<String, Object> map = new HashMap<String, Object>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			map.put("time3", sdf.format(new Date()));
			map.put("time4", sdf.format(new Date()));
			List<LiveDraw> listDraw = liveDrawService.findLiveDrawList(0, Integer.MAX_VALUE, map);
			if (listDraw.size() > 0) {
				LiveDraw draw = listDraw.get(0);// 今日抽奖
				Calendar cale = Calendar.getInstance();
				// 如果当前时间早于抽奖开始时间或者晚于抽奖结束时间
				if (cale.getTime().getTime() < draw.getBeginTime().getTime() || cale.getTime().getTime() > draw.getEndTime().getTime()) {
					flag = 0;
				}
				this.getActionContext().put("draw", draw);
			}
			this.getActionContext().put("flag", flag);
			this.getActionContext().put("user_id", user_id);

			List<LiveDraw> list = liveDrawService.findLiveDrawList(0, Integer.MAX_VALUE, new HashMap<String, Object>());
			if (list.size() > 1) {
				LiveDraw drawLast = null;
				if (listDraw.size() <= 0) {
					drawLast = list.get(0);// 上一期抽奖,如果今日还没开奖，那上一期就是最新一条数据
				} else {
					drawLast = list.get(1);// 上一期抽奖,如果今日已开奖，那上一期就是倒数第二条数据
				}
				this.getActionContext().put("drawLast", drawLast);
				this.getActionContext().put("money", drawLast.getMoney().divide(new BigDecimal(10)));

				List<LiveGuess> listGuess = liveGuessService.findAwardsUser(drawLast.getId(), drawLast.getAnswer());// 根据抽奖id和答案金额查询出最接近正确答案的10条记录，作为中奖用户
				this.getActionContext().put("listGuess", listGuess);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 保存直播竞猜的结果
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveLiveGuess")
	public String saveLiveGuess() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_id", user_id);
			map.put("liveDrawId", liveDrawId);
			List<LiveGuess> list = liveGuessService.findLiveGuessList(0, Integer.MAX_VALUE, map);
			if (list.size() > 0) {
				write("-1");
				return null;
			}
			LiveGuess guess = new LiveGuess();
			guess.setFuUser(fuUserService.get(user_id));
			guess.setGuessAnswer(guessAnswer);
			guess.setGuessTime(new Date());
			guess.setIsWinning(0);
			guess.setLiveDraw(liveDrawService.get(liveDrawId));
			liveGuessService.save(guess);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 保存直播竞猜的结果
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("guessSuccess")
	public String guessSuccess() {
		try {
			this.getActionContext().put("guessAnswer", guessAnswer);
			this.getActionContext().put("user_id", user_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public BigDecimal getGuessAnswer() {
		return guessAnswer;
	}

	public void setGuessAnswer(BigDecimal guessAnswer) {
		this.guessAnswer = guessAnswer;
	}

	public Long getLiveDrawId() {
		return liveDrawId;
	}

	public void setLiveDrawId(Long liveDrawId) {
		this.liveDrawId = liveDrawId;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

}
