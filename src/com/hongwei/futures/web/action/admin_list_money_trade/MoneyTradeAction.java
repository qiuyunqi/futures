package com.hongwei.futures.web.action.admin_list_money_trade;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.VerticalAlignment;
import jxl.write.Alignment;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuDictionary;
import com.hongwei.futures.model.FuMoneyDetail;
import com.hongwei.futures.service.FuDictionaryService;
import com.hongwei.futures.service.FuMoneyDetailService;
import com.hongwei.futures.util.ExcelUtil;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
@SuppressWarnings("all")
public class MoneyTradeAction extends BaseAction {
	private static final long serialVersionUID = 3710462102161739139L;
	@Autowired
	private FuMoneyDetailService fuMoneyDetailService;
	@Autowired
	private FuDictionaryService fuDictionaryService;

	private FuAdmin admin;
	private Long adminId;
	private Long id;
	private String accountName;
	private String userName;
	private Long dictionaryId;
	private BigDecimal money1;
	private BigDecimal money2;
	private Date date1;
	private Date date2;
	private String comment;
	private Integer totalCount;
	private Long queryUserId;
	private String time1;
	private String time2;
	private Integer state;

	/**
	 * 资金交易明细—查询用户与余额之间的变动记录
	 * 
	 * @return
	 */
	@Action("moneyTradeList")
	public String moneyTradeList() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (state == null) {
				state = 1;
			}
			map.put("state", state);
			if (id != null) {
				map.put("id", id);
			}
			if (dictionaryId != null) {
				map.put("dictionaryId", dictionaryId);
			}
			if (!StringUtil.isBlank(accountName)) {
				map.put("accountName", accountName);
			}
			if (!StringUtil.isBlank(userName)) {
				map.put("userName", userName);
			}
			if (!StringUtil.isBlank(comment)) {
				map.put("comment", comment);
			}
			if (money1 != null)
				map.put("money1", money1);
			if (money2 != null)
				map.put("money2", money2);
			if (date1 != null) {
				map.put("date1", date1);
			}
			if (date2 != null) {
				map.put("date2", date2);
			}
			map.put("isEnabled", 1);
			if (totalCount == null) {
				totalCount = fuMoneyDetailService.getCount(map);
			}
			List<FuMoneyDetail> detailList = fuMoneyDetailService.findListBy((this.getPageNo() - 1) * this.getPageSize(), this.getPageSize(), map);
			List<FuDictionary> dictionaries = fuDictionaryService.findListByMap(0, Integer.MAX_VALUE, map);
			this.getActionContext().put("dictionaries", dictionaries);
			this.getActionContext().put("detailList", detailList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Action("userMoneyTradeList")
	public String userMoneyTradeList() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (queryUserId != null) {
				map.put("queryUserId", queryUserId);
				this.getActionContext().put("queryUserId", queryUserId);
			}
			if (!StringUtil.isBlank(time1)) {
				map.put("time1", time1);
				this.getActionContext().put("time1", time1);
			}
			if (!StringUtil.isBlank(time2)) {
				map.put("time2", time2);
				this.getActionContext().put("time2", time2);
			}
			if (totalCount == null) {
				totalCount = fuMoneyDetailService.getCount(map);
			}
			List<FuMoneyDetail> detailList = fuMoneyDetailService.findListBy((this.getPageNo() - 1) * this.getPageSize(), this.getPageSize(), map);
			this.getActionContext().put("detailList", detailList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Action("exportExcel")
	public String exportExcel() {
		try {
			OutputStream os = this.getHttpServletResponse().getOutputStream();
			String fileName = System.currentTimeMillis() + ".xls";
			this.getHttpServletResponse().setContentType("APPLICATION/OCTET-STREAM");
			this.getHttpServletResponse().setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			String excelPath = this.getServletContext().getRealPath("/");
			String separator = System.getProperty("file.separator");
			excelPath = excelPath + "uploads" + separator + "attach" + separator + "money_detail.xls";
			InputStream is = new FileInputStream(excelPath);
			Workbook wb = Workbook.getWorkbook(is);
			WorkbookSettings settings = new WorkbookSettings();
			settings.setWriteAccess(null);
			WritableWorkbook wwb = Workbook.createWorkbook(os, wb, settings);
			WritableCell wc = null;
			WritableSheet ws = wwb.getSheet("sheet1");

			// 查询数据
			Map<String, Object> map = new HashMap<String, Object>();
			if (state == null) {
				state = 1;
			}
			map.put("state", state);
			if (id != null) {
				map.put("id", id);
			}
			if (dictionaryId != null) {
				map.put("dictionaryId", dictionaryId);
			}
			if (!StringUtil.isBlank(accountName)) {
				map.put("accountName", accountName);
			}
			if (!StringUtil.isBlank(userName)) {
				map.put("userName", new String(userName.getBytes("ISO8859-1"), "UTF-8"));
			}
			if (!StringUtil.isBlank(comment)) {
				map.put("comment", new String(comment.getBytes("ISO8859-1"), "UTF-8"));
			}
			if (money1 != null)
				map.put("money1", money1);
			if (money2 != null)
				map.put("money2", money2);
			if (date1 != null) {
				map.put("date1", date1);
			}
			if (date2 != null) {
				map.put("date2", date2);
			}
			List<FuMoneyDetail> detailList = fuMoneyDetailService.findFuMoneyDetailByParams(map);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			// 第二行
			WritableCellFormat dateWcf = new WritableCellFormat();
			dateWcf.setAlignment(Alignment.CENTRE);
			dateWcf.setVerticalAlignment(VerticalAlignment.CENTRE);
			wc = ws.getWritableCell(1, 1);
			wc = ExcelUtil.cloneCellWithValue(1, 1, (date1 != null ? sdf.format(date1) : "") + " 至 " + (date2 != null ? sdf.format(date2) : ""), dateWcf);
			ws.addCell(wc);

			for (int i = 3; i < detailList.size() + 3; i++) {
				// 序号即id
				WritableCellFormat wcf = new WritableCellFormat();
				wcf.setAlignment(Alignment.CENTRE);
				wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
				wc = ws.getWritableCell(0, i);
				wc = ExcelUtil.cloneCellWithValue(0, i, detailList.get(i - 3).getId().toString(), wcf);
				ws.addCell(wc);

				// 用户名
				WritableCellFormat wcf2 = new WritableCellFormat();
				wcf2.setAlignment(Alignment.CENTRE);
				wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);
				wc = ws.getWritableCell(1, i);
				wc = ExcelUtil.cloneCellWithValue(1, i, detailList.get(i - 3).getFuUser().getAccountName(), wcf2);
				ws.addCell(wc);

				// 真实姓名
				WritableCellFormat wcf3 = new WritableCellFormat();
				wcf3.setAlignment(Alignment.CENTRE);
				wcf3.setVerticalAlignment(VerticalAlignment.CENTRE);
				wc = ws.getWritableCell(2, i);
				wc = ExcelUtil.cloneCellWithValue(2, i, detailList.get(i - 3).getFuUser().getUserName() == null ? "" : detailList.get(i - 3).getFuUser().getUserName(), wcf3);
				ws.addCell(wc);

				// 类型
				WritableCellFormat wcf4 = new WritableCellFormat();
				wcf4.setAlignment(Alignment.CENTRE);
				wcf4.setVerticalAlignment(VerticalAlignment.CENTRE);
				wc = ws.getWritableCell(3, i);
				wc = ExcelUtil.cloneCellWithValue(3, i, detailList.get(i - 3).getFuDictionary().getName(), wcf3);
				ws.addCell(wc);

				// 详情
				WritableCellFormat wcf5 = new WritableCellFormat();
				wcf5.setAlignment(Alignment.CENTRE);
				wcf5.setVerticalAlignment(VerticalAlignment.CENTRE);
				wc = ws.getWritableCell(4, i);
				wc = ExcelUtil.cloneCellWithValue(4, i, detailList.get(i - 3).getComment(), wcf5);
				ws.addCell(wc);

				// 金额
				WritableCellFormat wcf6 = new WritableCellFormat();
				wcf6.setAlignment(Alignment.RIGHT);
				wcf6.setVerticalAlignment(VerticalAlignment.CENTRE);
				wc = ws.getWritableCell(5, i);
				wc = ExcelUtil.cloneCellWithValue(5, i, detailList.get(i - 3).getMoney().toString(), wcf5);
				ws.addCell(wc);

				// 可用金额
				WritableCellFormat wcf7 = new WritableCellFormat();
				wcf7.setAlignment(Alignment.RIGHT);
				wcf7.setVerticalAlignment(VerticalAlignment.CENTRE);
				wc = ws.getWritableCell(6, i);
				wc = ExcelUtil.cloneCellWithValue(6, i, detailList.get(i - 3).getAccountBalanceAfter().toString(), wcf5);
				ws.addCell(wc);

				// 时间
				WritableCellFormat wcf8 = new WritableCellFormat();
				wcf8.setAlignment(Alignment.CENTRE);
				wcf8.setVerticalAlignment(VerticalAlignment.CENTRE);
				wc = ws.getWritableCell(7, i);
				wc = ExcelUtil.cloneCellWithValue(7, i, sdf2.format(detailList.get(i - 3).getTime()), wcf8);
				ws.addCell(wc);
			}
			wwb.write();
			wwb.close();
			System.out.println("导出成功");
			return null;
		} catch (Exception e) {
			System.out.println("导出失败");
			e.printStackTrace();
			return null;
		}
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getDictionaryId() {
		return dictionaryId;
	}

	public void setDictionaryId(Long dictionaryId) {
		this.dictionaryId = dictionaryId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Long getQueryUserId() {
		return queryUserId;
	}

	public void setQueryUserId(Long queryUserId) {
		this.queryUserId = queryUserId;
	}

	public String getTime1() {
		return time1;
	}

	public void setTime1(String time1) {
		this.time1 = time1;
	}

	public String getTime2() {
		return time2;
	}

	public void setTime2(String time2) {
		this.time2 = time2;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}
