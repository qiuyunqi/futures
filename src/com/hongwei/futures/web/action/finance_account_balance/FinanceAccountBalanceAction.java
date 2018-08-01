package com.hongwei.futures.web.action.finance_account_balance;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
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
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.util.ExcelUtil;
import com.hongwei.futures.web.action.BaseAction;

@SuppressWarnings("deprecation")
@ParentPackage("admin")
public class FinanceAccountBalanceAction extends BaseAction {

	private static final long serialVersionUID = 7258844337834285864L;

	@Autowired
	private FuUserService fuUserService;

	private FuAdmin admin;
	private Long adminId;
	private Integer totalCount;

	private String beginTime;
	private String endTime;
	private String paramStr;
	private Integer state;

	@Action("accountBalance")
	public String accountBalance() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtil.isBlank(beginTime)) {
				map.put("beginTime", beginTime);
			}
			if (!StringUtil.isBlank(endTime)) {
				map.put("endTime", endTime);
			}
			if (state == null) {
				state = 1;
			}
			map.put("state", state);
			if (!StringUtil.isBlank(beginTime) || !StringUtil.isBlank(endTime)) {
				List<Object[]> userList = fuUserService
						.queryUserAccountBalanceList((this.getPageNo() - 1)
								* this.getPageSize(), this.getPageSize(), map);
				/*
				 * JSONArray arr = JSONArray.fromObject(userList); Object[]
				 * objsArr = arr.toArray(); JSONObject o = new JSONObject();
				 * List<Object> objList = new ArrayList<Object>(); for(Object
				 * obj:objsArr){ JSONArray objArr = (JSONArray)obj; Map<String,
				 * Object> objMap = new HashMap<String, Object>();
				 * objMap.put("id", objArr.getLong(0));
				 * objMap.put("account_balance", objArr.getString(3));
				 * objMap.put("account_balance_after", objArr.getString(4));
				 * objList.add(objMap); } o.put("objList", objList);
				 */
				// System.out.println("objList: "+o.toString());

				BigDecimal money = BigDecimal.ZERO;
				for (Object[] obj : userList) {
					if (obj[4] == null) {
						money = money.add(new BigDecimal(obj[3].toString()));
					} else {
						money = money.add(new BigDecimal(obj[4].toString()));
					}
				}
				if (totalCount == null) {
					totalCount = fuUserService.countUserAccountBalance(map);
				}
				this.getActionContext().put("userList", userList);
				// this.getActionContext().put("objStr", o.toString());
				this.getActionContext().put("totalBalance", money);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Action("exportExcel")
	public String exportExcel() {
		try {
			/*
			 * JSONObject o = JSONObject.fromObject(paramStr); JSONArray objsArr
			 * = (JSONArray) o.get("objList");
			 */

			OutputStream os = this.getHttpServletResponse().getOutputStream();
			String fileName = System.currentTimeMillis() + ".xls";
			this.getHttpServletResponse().setContentType(
					"APPLICATION/OCTET-STREAM");
			this.getHttpServletResponse().setHeader("Content-Disposition",
					"attachment; filename=\"" + fileName + "\"");
			String excelPath = this.getServletContext().getRealPath("/");
			String separator = System.getProperty("file.separator");
			excelPath = excelPath + "uploads" + separator + "attach"
					+ separator + "account_balance.xls";
			InputStream is = new FileInputStream(excelPath);
			Workbook wb = Workbook.getWorkbook(is);
			WorkbookSettings settings = new WorkbookSettings();
			settings.setWriteAccess(null);
			WritableWorkbook wwb = Workbook.createWorkbook(os, wb, settings);
			WritableCell wc = null;
			WritableSheet ws = wwb.getSheet("sheet1");

			// 查询数据
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtil.isBlank(beginTime)) {
				map.put("beginTime", beginTime);
			}
			if (!StringUtil.isBlank(endTime)) {
				map.put("endTime", endTime);
			}
			if (state == null) {
				state = 1;
			}
			map.put("state", state);
			List<Object[]> userList = fuUserService
					.queryAllUserAccountBalanceList(map);

			// 第二行
			WritableCellFormat dateWcf = new WritableCellFormat();
			dateWcf.setAlignment(Alignment.CENTRE);
			dateWcf.setVerticalAlignment(VerticalAlignment.CENTRE);
			wc = ws.getWritableCell(1, 1);
			wc = ExcelUtil.cloneCellWithValue(1, 1,
					beginTime + " 至 " + endTime, dateWcf);
			ws.addCell(wc);
			BigDecimal sumAccountBalance = BigDecimal.ZERO;

			for (int i = 3; i < userList.size() + 3; i++) {
				// 序号即id
				WritableCellFormat wcf = new WritableCellFormat();
				wcf.setAlignment(Alignment.CENTRE);
				wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
				wc = ws.getWritableCell(0, i);
				wc = ExcelUtil.cloneCellWithValue(0, i,
						userList.get(i - 3)[0].toString(), wcf);
				ws.addCell(wc);

				// 用户名
				WritableCellFormat wcf2 = new WritableCellFormat();
				wcf2.setAlignment(Alignment.CENTRE);
				wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);
				wc = ws.getWritableCell(1, i);
				wc = ExcelUtil.cloneCellWithValue(1, i,
						userList.get(i - 3)[1].toString(), wcf2);
				ws.addCell(wc);

				// 真实姓名
				WritableCellFormat wcf3 = new WritableCellFormat();
				wcf3.setAlignment(Alignment.CENTRE);
				wcf3.setVerticalAlignment(VerticalAlignment.CENTRE);
				wc = ws.getWritableCell(2, i);
				wc = ExcelUtil.cloneCellWithValue(
						2,
						i,
						userList.get(i - 3)[2] == null ? "" : userList
								.get(i - 3)[2].toString(), wcf3);
				ws.addCell(wc);

				// 余额
				WritableCellFormat wcf4 = new WritableCellFormat();
				wcf4.setAlignment(Alignment.RIGHT);
				wcf4.setVerticalAlignment(VerticalAlignment.CENTRE);
				wc = ws.getWritableCell(3, i);
				wc = ExcelUtil
						.cloneCellWithValue(
								3,
								i,
								userList.get(i - 3)[4] == null ? userList
										.get(i - 3)[3].toString() : userList
										.get(i - 3)[4].toString(), wcf4);
				ws.addCell(wc);

				sumAccountBalance = sumAccountBalance
						.add(userList.get(i - 3)[4] == null ? new BigDecimal(
								userList.get(i - 3)[3].toString())
								: new BigDecimal(userList.get(i - 3)[4]
										.toString()));
				// 备注暂无
				/*
				 * WritableCellFormat wcf5 = new WritableCellFormat();
				 * wcf5.setAlignment(Alignment.CENTRE);
				 * wcf5.setVerticalAlignment(VerticalAlignment.CENTRE); wc =
				 * ws.getWritableCell(1, i); wc =
				 * ExcelUtil.cloneCellWithValue(4, i,
				 * userList.get(i-3)[4]==null?, wcf5); ws.addCell(wc);
				 */
			}
			// 最后一行合计
			WritableCellFormat lastWcf = new WritableCellFormat();
			lastWcf.setAlignment(Alignment.RIGHT);
			lastWcf.setVerticalAlignment(VerticalAlignment.CENTRE);
			wc = ws.getWritableCell(0, userList.size() + 3);
			wc = ExcelUtil.cloneCellWithValue(0, userList.size() + 3, "合计：",
					lastWcf);
			ws.mergeCells(0, userList.size() + 3, 2, userList.size() + 3);
			ws.addCell(wc);

			WritableCellFormat moneyWcf = new WritableCellFormat();
			moneyWcf.setAlignment(Alignment.RIGHT);
			moneyWcf.setVerticalAlignment(VerticalAlignment.CENTRE);
			wc = ws.getWritableCell(3, userList.size() + 3);
			wc = ExcelUtil.cloneCellWithValue(3, userList.size() + 3,
					sumAccountBalance.toString(), lastWcf);
			ws.addCell(wc);

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

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getParamStr() {
		return paramStr;
	}

	public void setParamStr(String paramStr) {
		this.paramStr = paramStr;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}
