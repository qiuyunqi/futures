package com.hongwei.futures.web.action.admin_op_wqq_contents;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.WqqContents;
import com.hongwei.futures.model.WqqOptions;
import com.hongwei.futures.service.WqqContentsService;
import com.hongwei.futures.service.WqqOptionsService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminOpWqqContentsAction extends BaseAction {
	private static final long serialVersionUID = 7359581642794729713L;
	
	@Autowired
	private WqqContentsService wqqContentsService;
	@Autowired
	private WqqOptionsService wqqOptionsService;

	private FuAdmin admin;
	private Long adminId;
	private Long id;
	private BigDecimal acceptFactor;
	private Integer guessType;
	private Long contentsId;
	private String name;
	private Integer state;
	private String OOI;
	private BigDecimal updownRegion;
	private Date beginTime;
	private Date endTime;
	private BigDecimal beginPrice;
	private BigDecimal endPrice;

	private String buyBeginTime;
	private String buyEndTime;
	private String closeBeginTime;
	private String closeEndTime;
	private String clearTime;

	private String up_dw_num;
	private String up_max_bs;
	private String up_max_zf;
	private String up_min_zf;
	private String up_dw1;
	private String up_dw2;
	private String up_dw3;
	private String up_dw4;
	private String up_dw5;
	private String up_dw6;
	private String up_dw7;
	private String up_dw8;
	private String up_dw9;
	private String up_dw10;
	private String up_dw1_val;
	private String up_dw2_val;
	private String up_dw3_val;
	private String up_dw4_val;
	private String up_dw5_val;
	private String up_dw6_val;
	private String up_dw7_val;
	private String up_dw8_val;
	private String up_dw9_val;
	private String up_dw10_val;

	private String down_dw_num;
	private String down_max_bs;
	private String down_max_zf;
	private String down_min_zf;
	private String down_dw1;
	private String down_dw2;
	private String down_dw3;
	private String down_dw4;
	private String down_dw5;
	private String down_dw6;
	private String down_dw7;
	private String down_dw8;
	private String down_dw9;
	private String down_dw10;
	private String down_dw1_val;
	private String down_dw2_val;
	private String down_dw3_val;
	private String down_dw4_val;
	private String down_dw5_val;
	private String down_dw6_val;
	private String down_dw7_val;
	private String down_dw8_val;
	private String down_dw9_val;
	private String down_dw10_val;

	/**
	 * 添加或修改微期权
	 * 
	 * @return
	 */
	@Action("addContents")
	public String addContents() {
		try {
			if (id != null) {
				WqqContents contents = wqqContentsService.get(id);
				this.getActionContext().put("contents", contents);
				JSONObject upDetail = new JSONObject();
				if (contents.getUpDetail() != null) {
					upDetail = JSONObject.fromObject(contents.getUpDetail());
					this.getActionContext().put("upDetail", upDetail);
				}
				JSONObject downDetail = new JSONObject();
				if (contents.getDownDetail() != null) {
					downDetail = JSONObject.fromObject(contents.getDownDetail());
					this.getActionContext().put("downDetail", downDetail);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 保存添加修改微期权
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveWqqContents")
	public String saveWqqContents() {
		try {
			WqqContents contents;
			if (id == null) {
				contents = new WqqContents();
				contents.setCreateAdmin(admin);
				contents.setCreateTime(new Date());
				contents.setState(0);
			} else {
				contents = wqqContentsService.get(id);
			}
			contents.setName(name);
			contents.setOOI(OOI);
			contents.setUpdownRegion(updownRegion);
			contents.setBeginTime(beginTime);
			contents.setEndTime(endTime);
			contents.setBeginPrice(beginPrice);
			contents.setEndPrice(endPrice);

			JSONObject upDetail = new JSONObject();// 看涨详情
			upDetail.put("buyBeginTime", buyBeginTime);
			upDetail.put("buyEndTime", buyEndTime);
			upDetail.put("closeBeginTime", closeBeginTime);
			upDetail.put("closeEndTime", closeEndTime);
			upDetail.put("clearTime", clearTime);
			upDetail.put("up_dw_num", up_dw_num);
			upDetail.put("up_max_bs", up_max_bs);
			upDetail.put("up_max_zf", up_max_zf);
			upDetail.put("up_min_zf", up_min_zf);
			upDetail.put("up_dw1", up_dw1);
			upDetail.put("up_dw2", up_dw2);
			upDetail.put("up_dw3", up_dw3);
			upDetail.put("up_dw4", up_dw4);
			upDetail.put("up_dw5", up_dw5);
			upDetail.put("up_dw6", up_dw6);
			upDetail.put("up_dw7", up_dw7);
			upDetail.put("up_dw8", up_dw8);
			upDetail.put("up_dw9", up_dw9);
			upDetail.put("up_dw10", up_dw10);
			upDetail.put("up_dw1_val", up_dw1_val);
			upDetail.put("up_dw2_val", up_dw2_val);
			upDetail.put("up_dw3_val", up_dw3_val);
			upDetail.put("up_dw4_val", up_dw4_val);
			upDetail.put("up_dw5_val", up_dw5_val);
			upDetail.put("up_dw6_val", up_dw6_val);
			upDetail.put("up_dw7_val", up_dw7_val);
			upDetail.put("up_dw8_val", up_dw8_val);
			upDetail.put("up_dw9_val", up_dw9_val);
			upDetail.put("up_dw10_val", up_dw10_val);

			JSONObject downDetail = new JSONObject();// 看跌详情
			downDetail.put("buyBeginTime", buyBeginTime);
			downDetail.put("buyEndTime", buyEndTime);
			downDetail.put("closeBeginTime", closeBeginTime);
			downDetail.put("closeEndTime", closeEndTime);
			downDetail.put("clearTime", clearTime);
			downDetail.put("down_dw_num", down_dw_num);
			downDetail.put("down_max_bs", down_max_bs);
			downDetail.put("down_max_zf", down_max_zf);
			downDetail.put("down_min_zf", down_min_zf);
			downDetail.put("down_dw1", down_dw1);
			downDetail.put("down_dw2", down_dw2);
			downDetail.put("down_dw3", down_dw3);
			downDetail.put("down_dw4", down_dw4);
			downDetail.put("down_dw5", down_dw5);
			downDetail.put("down_dw6", down_dw6);
			downDetail.put("down_dw7", down_dw7);
			downDetail.put("down_dw8", down_dw8);
			downDetail.put("down_dw9", down_dw9);
			downDetail.put("down_dw10", down_dw10);
			downDetail.put("down_dw1_val", down_dw1_val);
			downDetail.put("down_dw2_val", down_dw2_val);
			downDetail.put("down_dw3_val", down_dw3_val);
			downDetail.put("down_dw4_val", down_dw4_val);
			downDetail.put("down_dw5_val", down_dw5_val);
			downDetail.put("down_dw6_val", down_dw6_val);
			downDetail.put("down_dw7_val", down_dw7_val);
			downDetail.put("down_dw8_val", down_dw8_val);
			downDetail.put("down_dw9_val", down_dw9_val);
			downDetail.put("down_dw10_val", down_dw10_val);

			contents.setUpDetail(upDetail.toString());
			contents.setDownDetail(downDetail.toString());
			wqqContentsService.save(contents);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 更改微期权状态
	 * 
	 * @return
	 */
	@Action("saveUpdateState")
	public String saveUpdateState() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("state", 1);
			Integer count = wqqContentsService.countContents(map);// 得到状态为上线的微期权数目
			if (count >= 1 && state == 1) {
				write("-1");
				return null;
			}
			WqqContents contents = wqqContentsService.get(id);
			contents.setState(state);
			if (state == 1) {
				contents.setUpAdmin(admin);
				contents.setUpTime(new Date());// 上线时间
			}
			if (state == 2) {
				contents.setDownAdmin(admin);
				contents.setDownTime(new Date());// 下线时间
			}
			wqqContentsService.save(contents);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 收益承兑
	 */
	@Action("wqqFactorAjax")
	public String wqqFactorAjax() {
		try {
			this.getActionContext().put("state", state);
			this.getActionContext().put("contentsId", id);
			if (state == 3) {// 如果选中微期权期数已被承兑
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("contentsId", id);
				List<WqqOptions> list = wqqOptionsService.queryOptionsList(0, Integer.MAX_VALUE, map);// 查询这一期微期权的购买记录
				BigDecimal totalIncome = BigDecimal.ZERO;
				for (WqqOptions wqqOptions : list) {
					totalIncome = totalIncome.add(wqqOptions.getOrderIncome() == null ? BigDecimal.ZERO : wqqOptions.getOrderIncome());
				}
				this.getActionContext().put("personCount", list.size());
				this.getActionContext().put("totalIncome", totalIncome);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 保存收益承兑
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveWqqFactorAjax")
	public String saveReplyMessageAjax() {
		try {
			wqqOptionsService.saveWqqFactor(contentsId, admin, acceptFactor, guessType);
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

	public BigDecimal getAcceptFactor() {
		return acceptFactor;
	}

	public void setAcceptFactor(BigDecimal acceptFactor) {
		this.acceptFactor = acceptFactor;
	}

	public Integer getGuessType() {
		return guessType;
	}

	public void setGuessType(Integer guessType) {
		this.guessType = guessType;
	}

	public Long getContentsId() {
		return contentsId;
	}

	public void setContentsId(Long contentsId) {
		this.contentsId = contentsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getOOI() {
		return OOI;
	}

	public void setOOI(String oOI) {
		OOI = oOI;
	}

	public BigDecimal getUpdownRegion() {
		return updownRegion;
	}

	public void setUpdownRegion(BigDecimal updownRegion) {
		this.updownRegion = updownRegion;
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

	public BigDecimal getBeginPrice() {
		return beginPrice;
	}

	public void setBeginPrice(BigDecimal beginPrice) {
		this.beginPrice = beginPrice;
	}

	public BigDecimal getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(BigDecimal endPrice) {
		this.endPrice = endPrice;
	}

	public String getBuyBeginTime() {
		return buyBeginTime;
	}

	public void setBuyBeginTime(String buyBeginTime) {
		this.buyBeginTime = buyBeginTime;
	}

	public String getBuyEndTime() {
		return buyEndTime;
	}

	public void setBuyEndTime(String buyEndTime) {
		this.buyEndTime = buyEndTime;
	}

	public String getCloseBeginTime() {
		return closeBeginTime;
	}

	public void setCloseBeginTime(String closeBeginTime) {
		this.closeBeginTime = closeBeginTime;
	}

	public String getCloseEndTime() {
		return closeEndTime;
	}

	public void setCloseEndTime(String closeEndTime) {
		this.closeEndTime = closeEndTime;
	}

	public String getClearTime() {
		return clearTime;
	}

	public void setClearTime(String clearTime) {
		this.clearTime = clearTime;
	}

	public String getUp_dw_num() {
		return up_dw_num;
	}

	public void setUp_dw_num(String up_dw_num) {
		this.up_dw_num = up_dw_num;
	}

	public String getUp_max_bs() {
		return up_max_bs;
	}

	public void setUp_max_bs(String up_max_bs) {
		this.up_max_bs = up_max_bs;
	}

	public String getUp_max_zf() {
		return up_max_zf;
	}

	public void setUp_max_zf(String up_max_zf) {
		this.up_max_zf = up_max_zf;
	}

	public String getUp_min_zf() {
		return up_min_zf;
	}

	public void setUp_min_zf(String up_min_zf) {
		this.up_min_zf = up_min_zf;
	}

	public String getUp_dw1() {
		return up_dw1;
	}

	public void setUp_dw1(String up_dw1) {
		this.up_dw1 = up_dw1;
	}

	public String getUp_dw2() {
		return up_dw2;
	}

	public void setUp_dw2(String up_dw2) {
		this.up_dw2 = up_dw2;
	}

	public String getUp_dw3() {
		return up_dw3;
	}

	public void setUp_dw3(String up_dw3) {
		this.up_dw3 = up_dw3;
	}

	public String getUp_dw4() {
		return up_dw4;
	}

	public void setUp_dw4(String up_dw4) {
		this.up_dw4 = up_dw4;
	}

	public String getUp_dw5() {
		return up_dw5;
	}

	public void setUp_dw5(String up_dw5) {
		this.up_dw5 = up_dw5;
	}

	public String getUp_dw6() {
		return up_dw6;
	}

	public void setUp_dw6(String up_dw6) {
		this.up_dw6 = up_dw6;
	}

	public String getUp_dw7() {
		return up_dw7;
	}

	public void setUp_dw7(String up_dw7) {
		this.up_dw7 = up_dw7;
	}

	public String getUp_dw8() {
		return up_dw8;
	}

	public void setUp_dw8(String up_dw8) {
		this.up_dw8 = up_dw8;
	}

	public String getUp_dw9() {
		return up_dw9;
	}

	public void setUp_dw9(String up_dw9) {
		this.up_dw9 = up_dw9;
	}

	public String getUp_dw10() {
		return up_dw10;
	}

	public void setUp_dw10(String up_dw10) {
		this.up_dw10 = up_dw10;
	}

	public String getUp_dw1_val() {
		return up_dw1_val;
	}

	public void setUp_dw1_val(String up_dw1_val) {
		this.up_dw1_val = up_dw1_val;
	}

	public String getUp_dw2_val() {
		return up_dw2_val;
	}

	public void setUp_dw2_val(String up_dw2_val) {
		this.up_dw2_val = up_dw2_val;
	}

	public String getUp_dw3_val() {
		return up_dw3_val;
	}

	public void setUp_dw3_val(String up_dw3_val) {
		this.up_dw3_val = up_dw3_val;
	}

	public String getUp_dw4_val() {
		return up_dw4_val;
	}

	public void setUp_dw4_val(String up_dw4_val) {
		this.up_dw4_val = up_dw4_val;
	}

	public String getUp_dw5_val() {
		return up_dw5_val;
	}

	public void setUp_dw5_val(String up_dw5_val) {
		this.up_dw5_val = up_dw5_val;
	}

	public String getUp_dw6_val() {
		return up_dw6_val;
	}

	public void setUp_dw6_val(String up_dw6_val) {
		this.up_dw6_val = up_dw6_val;
	}

	public String getUp_dw7_val() {
		return up_dw7_val;
	}

	public void setUp_dw7_val(String up_dw7_val) {
		this.up_dw7_val = up_dw7_val;
	}

	public String getUp_dw8_val() {
		return up_dw8_val;
	}

	public void setUp_dw8_val(String up_dw8_val) {
		this.up_dw8_val = up_dw8_val;
	}

	public String getUp_dw9_val() {
		return up_dw9_val;
	}

	public void setUp_dw9_val(String up_dw9_val) {
		this.up_dw9_val = up_dw9_val;
	}

	public String getUp_dw10_val() {
		return up_dw10_val;
	}

	public void setUp_dw10_val(String up_dw10_val) {
		this.up_dw10_val = up_dw10_val;
	}

	public String getDown_dw_num() {
		return down_dw_num;
	}

	public void setDown_dw_num(String down_dw_num) {
		this.down_dw_num = down_dw_num;
	}

	public String getDown_max_bs() {
		return down_max_bs;
	}

	public void setDown_max_bs(String down_max_bs) {
		this.down_max_bs = down_max_bs;
	}

	public String getDown_max_zf() {
		return down_max_zf;
	}

	public void setDown_max_zf(String down_max_zf) {
		this.down_max_zf = down_max_zf;
	}

	public String getDown_min_zf() {
		return down_min_zf;
	}

	public void setDown_min_zf(String down_min_zf) {
		this.down_min_zf = down_min_zf;
	}

	public String getDown_dw1() {
		return down_dw1;
	}

	public void setDown_dw1(String down_dw1) {
		this.down_dw1 = down_dw1;
	}

	public String getDown_dw2() {
		return down_dw2;
	}

	public void setDown_dw2(String down_dw2) {
		this.down_dw2 = down_dw2;
	}

	public String getDown_dw3() {
		return down_dw3;
	}

	public void setDown_dw3(String down_dw3) {
		this.down_dw3 = down_dw3;
	}

	public String getDown_dw4() {
		return down_dw4;
	}

	public void setDown_dw4(String down_dw4) {
		this.down_dw4 = down_dw4;
	}

	public String getDown_dw5() {
		return down_dw5;
	}

	public void setDown_dw5(String down_dw5) {
		this.down_dw5 = down_dw5;
	}

	public String getDown_dw6() {
		return down_dw6;
	}

	public void setDown_dw6(String down_dw6) {
		this.down_dw6 = down_dw6;
	}

	public String getDown_dw7() {
		return down_dw7;
	}

	public void setDown_dw7(String down_dw7) {
		this.down_dw7 = down_dw7;
	}

	public String getDown_dw8() {
		return down_dw8;
	}

	public void setDown_dw8(String down_dw8) {
		this.down_dw8 = down_dw8;
	}

	public String getDown_dw9() {
		return down_dw9;
	}

	public void setDown_dw9(String down_dw9) {
		this.down_dw9 = down_dw9;
	}

	public String getDown_dw10() {
		return down_dw10;
	}

	public void setDown_dw10(String down_dw10) {
		this.down_dw10 = down_dw10;
	}

	public String getDown_dw1_val() {
		return down_dw1_val;
	}

	public void setDown_dw1_val(String down_dw1_val) {
		this.down_dw1_val = down_dw1_val;
	}

	public String getDown_dw2_val() {
		return down_dw2_val;
	}

	public void setDown_dw2_val(String down_dw2_val) {
		this.down_dw2_val = down_dw2_val;
	}

	public String getDown_dw3_val() {
		return down_dw3_val;
	}

	public void setDown_dw3_val(String down_dw3_val) {
		this.down_dw3_val = down_dw3_val;
	}

	public String getDown_dw4_val() {
		return down_dw4_val;
	}

	public void setDown_dw4_val(String down_dw4_val) {
		this.down_dw4_val = down_dw4_val;
	}

	public String getDown_dw5_val() {
		return down_dw5_val;
	}

	public void setDown_dw5_val(String down_dw5_val) {
		this.down_dw5_val = down_dw5_val;
	}

	public String getDown_dw6_val() {
		return down_dw6_val;
	}

	public void setDown_dw6_val(String down_dw6_val) {
		this.down_dw6_val = down_dw6_val;
	}

	public String getDown_dw7_val() {
		return down_dw7_val;
	}

	public void setDown_dw7_val(String down_dw7_val) {
		this.down_dw7_val = down_dw7_val;
	}

	public String getDown_dw8_val() {
		return down_dw8_val;
	}

	public void setDown_dw8_val(String down_dw8_val) {
		this.down_dw8_val = down_dw8_val;
	}

	public String getDown_dw9_val() {
		return down_dw9_val;
	}

	public void setDown_dw9_val(String down_dw9_val) {
		this.down_dw9_val = down_dw9_val;
	}

	public String getDown_dw10_val() {
		return down_dw10_val;
	}

	public void setDown_dw10_val(String down_dw10_val) {
		this.down_dw10_val = down_dw10_val;
	}

}
