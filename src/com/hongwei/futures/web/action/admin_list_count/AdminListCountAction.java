package com.hongwei.futures.web.action.admin_list_count;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuProgram;
import com.hongwei.futures.service.FuProgramService;
import com.hongwei.futures.util.ExportExcel;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminListCountAction extends BaseAction {
	@Autowired
	private FuProgramService fuProgramService;

	private FuAdmin admin;
	private Long adminId;
	private Integer totalCount;
	private Date beginTime1;
	private Date beginTime2;
	private Date endTime1;
	private Date endTime2;
	private String userName;
	
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
	public Date getBeginTime1() {
		return beginTime1;
	}
	public void setBeginTime1(Date beginTime1) {
		this.beginTime1 = beginTime1;
	}
	public Date getBeginTime2() {
		return beginTime2;
	}
	public void setBeginTime2(Date beginTime2) {
		this.beginTime2 = beginTime2;
	}
	public Date getEndTime1() {
		return endTime1;
	}
	public void setEndTime1(Date endTime1) {
		this.endTime1 = endTime1;
	}
	public Date getEndTime2() {
		return endTime2;
	}
	public void setEndTime2(Date endTime2) {
		this.endTime2 = endTime2;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 财务报告
	 * 
	 * @return
	 */
	@Action("countList")
	public String countList() {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			if(beginTime1!=null)
				map.put("beginTime1", beginTime1);
			if(beginTime2!=null)
				map.put("beginTime2", beginTime2);
			if(endTime1!=null)
				map.put("endTime1", endTime1);
			if(endTime2!=null)
				map.put("endTime2", endTime2);
			if(!StringUtil.isBlank(userName))
				map.put("userName", userName);
			if (totalCount == null)
				totalCount = fuProgramService.countTradeInfo(map);
			List<FuProgram> countInfoList = fuProgramService.findTradeInfoList((this.getPageNo() - 1) * this.getPageSize(), this.getPageSize(),map);
			this.getActionContext().put("countInfoList", countInfoList);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 导出页面
	 * @return
	 */
	@Action("exportExcelAjax")
	public String exportExcelAjax(){
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			if(beginTime1!=null)
				map.put("beginTime1", beginTime1);
			if(beginTime2!=null)
				map.put("beginTime2", beginTime2);
			if(endTime1!=null)
				map.put("endTime1", endTime1);
			if(endTime2!=null)
				map.put("endTime2", endTime2);
			if(!StringUtil.isBlank(userName))
				map.put("userName", userName);
			if (totalCount == null)
			    totalCount = fuProgramService.countTradeInfo(map);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * @throws UnsupportedEncodingException 
	 * 导出数据
	 * */
	@Action("exportExcel")
	public String exportExcel()  {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			if(beginTime1!=null)
				map.put("beginTime1", beginTime1);
			if(beginTime2!=null)
				map.put("beginTime2", beginTime2);
			if(endTime1!=null)
				map.put("endTime1", endTime1);
			if(endTime2!=null)
				map.put("endTime2", endTime2);
			if(!StringUtil.isBlank(userName))
				map.put("userName", userName);
			if (totalCount == null)
				totalCount = fuProgramService.countTradeInfo(map);
			List<FuProgram> list = fuProgramService.findTradeInfoList2(map);
			ExportExcel exportExcel = new ExportExcel();
			
			String[] headData = new String[23];
			headData[0]="用户名";
			headData[1]="期货服务器";
			headData[2]="期货账号";
			headData[3]="交易密码";
			headData[4]="真实姓名";
			headData[5]="账户总额";
			headData[6]="公司资金";
			headData[7]="客户资金";
			headData[8]="预警线";
			headData[9]="止损线";
			headData[10]="商品隔夜";
			headData[11]="股指隔夜";
			headData[12]="开始日期";
			headData[13]="结算日期";
			headData[14]="计息天数";
			headData[15]="属性";
			headData[16]="利息";
			headData[17]="利率";
			headData[18]="股指手续费";
			headData[19]="商品手续费";
			headData[20]="上线客户";
			headData[21]="上线客户利率";
			headData[22]="手续费返佣标准";
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			DecimalFormat df = new DecimalFormat("#,###,##0.00");
			List<Map<Integer, Object>> dataSet = new ArrayList<Map<Integer, Object>>();
			FuProgram program = null;
			for(int i=0; i<list.size(); i++){
				program = list.get(i);
				Map<Integer, Object> m = new HashMap<Integer, Object>();
				m.put(0, program.getFuUser().getAccountName());
				m.put(1, program.getTradeServiceName());
				m.put(2, program.getTradeAccount());
				m.put(3, program.getTradePassword());
				m.put(4, program.getFuUser().getUserName());
				m.put(5, program.getTotalMatchMoney()==null?0.00:df.format(program.getTotalMatchMoney()));
				m.put(6, program.getMatchMoney()==null?0.00:df.format(program.getMatchMoney()));
				m.put(7, program.getSafeMoney()==null?0.00:df.format(program.getSafeMoney()));
				m.put(8, program.getWarnLine()==null?0.00:df.format(program.getWarnLine()));
				m.put(9, program.getCloseLine()==null?0.00:df.format(program.getCloseLine()));
				m.put(10, program.getOvernightGoodRate());
				m.put(11, program.getOvernightStockIndexRate());
				m.put(12, sdf.format(program.getTradeTime()));
				m.put(13, program.getCloseTime()==null?"":sdf.format(program.getCloseTime()));
				m.put(14, String.valueOf(program.getCycleNum()).concat(program.getProgramType()==1?"天":program.getProgramType()==2?"月":""));
				m.put(15, program.getProgramType()==1?"日配":program.getProgramType()==2?"月配":"期货大赛");
				m.put(16, df.format(program.getManageMoney()));
				if(program.getProgramType()==1){
					m.put(17, program.getMoneyPercent()==null?0.00:df.format(program.getMoneyPercent()));
				}
				if(program.getProgramType()==2){
					m.put(17, program.getMoneyPercent().multiply(new BigDecimal(100))==null?"0.0000%":program.getMoneyPercent().multiply(new BigDecimal(100))+"%");
				}
				m.put(18, program.getGoodsFee());
				m.put(19, program.getStockIndexFee());
				m.put(20, program.getFuUser().getRecommend()==null?"":program.getFuUser().getRecommend().getUserName());
				m.put(21, "12%");
				m.put(22, "1.1");
				dataSet.add(m);
			}
			exportExcel.exportToExcel(headData, dataSet, 0);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
