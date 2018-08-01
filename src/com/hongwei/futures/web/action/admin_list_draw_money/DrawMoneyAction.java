package com.hongwei.futures.web.action.admin_list_draw_money;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuDrawMoney;
import com.hongwei.futures.service.FuDrawMoneyService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class DrawMoneyAction extends BaseAction{
	@Autowired
	private FuDrawMoneyService fuDrawMoneyService;
	
	private FuAdmin admin;
	private Long adminId;
	
	private Integer totalCount;
	private Long id;
	private String userName;
    private String comment;
	private Integer status;
	private BigDecimal money1;
	private BigDecimal money2;
	private Date time1;
	private Date time2;
	private Integer state;
	private String phone;
	
	/**
	 * 提款申请列表
	 * @return
	 */
	@Action("drawMoneyList")
	public String drawMoneyList(){
		try{
			Map<String,Object> map = new HashMap<String,Object>();
			if(state==null){
				state = 1;
			}
			map.put("state", state);
			if(id!=null){
				map.put("id", id);
			}
			if(!StringUtil.isBlank(userName)){
				map.put("userName", userName);
			}
			if(!StringUtil.isBlank(phone)){
				map.put("phone", phone);
			}
			if(status!=null){
				map.put("status",status);
			}
			if(!StringUtil.isBlank(comment)){
				map.put("comment",comment);
			}
			if(money1!=null){
				map.put("money1",money1);
			}
			if(money2!=null){
				map.put("money2",money2);
			}
			if(time1!=null){
				map.put("time1", time1);
			}
			if(time2!=null){
				map.put("time2", time2);
			}
			if(totalCount==null)
				totalCount = fuDrawMoneyService.getCount(map);
			List<FuDrawMoney> drawList = fuDrawMoneyService.findList((this.getPageNo()-1)*this.getPageSize(),this.getPageSize(),map);
			this.getActionContext().put("drawList", drawList);
		}catch (Exception e) {
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

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}

	public BigDecimal getMoney1() {
		return money1;
	}


	public void setMoney1(BigDecimal money1) {
		this.money1 = money1;
	}


	public BigDecimal getMoney2() {
		return money2;
	}


	public void setMoney2(BigDecimal money2) {
		this.money2 = money2;
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


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public Integer getState() {
		return state;
	}


	public void setState(Integer state) {
		this.state = state;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
