package com.hongwei.futures.web.action.admin_list_money_program;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuMoneyDetail;
import com.hongwei.futures.service.FuMoneyDetailService;
import com.hongwei.futures.util.DateUtil;
import com.hongwei.futures.util.ExcelUtil;
import com.hongwei.futures.util.ExportExcel;
import com.hongwei.futures.util.StringUtil;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class MoneyFlowAction extends BaseAction {
	@Autowired
	private FuMoneyDetailService fuMoneyDetailService;

	private FuAdmin admin;
	private Long adminId;
	private Long id;
	private Long programId;
	private String accountName;
	private String userName;
	private Long dictionaryId;
	private Integer status;
	private BigDecimal money1;
	private BigDecimal money2;
	private Date date1;
	private Date date2;
	private String comment;
	private Integer totalCount;

	/**
	 * 方案资金明细—查询余额和方案之间的记录
	 * 
	 * @return
	 */
	@Action("moneyFlowList")
	public String moneyFlowList() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("bigType", 2);// 方案明细
			if (id != null) {
				map.put("id", id);
			}
			if (programId != null) {
				map.put("programId", programId);
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
			if (status != null) {
				map.put("status", status);
			}
			if (!StringUtil.isBlank(comment)) {
				map.put("comment", comment);
			}
			if (money1 != null) {
				map.put("money1", money1);
			}
			if (money2 != null) {
				map.put("money2", money2);
			}
			if (date1 != null) {
				map.put("date1", date1);
			}
			if (date2 != null) {
				map.put("date2", date2);
			}
			if (totalCount == null) {
				totalCount = fuMoneyDetailService.getCount(map);
			}
			List<FuMoneyDetail> detailList = fuMoneyDetailService.findListBy(
					(this.getPageNo() - 1) * this.getPageSize(),
					this.getPageSize(), map);
			this.getActionContext().put("detailList", detailList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 待利润提取导出Excel
	 * 
	 * @return
	 */
	@Action("exportExcel")
	public String exportExcel() {
		try {
			// 获取数据库信息
			FuMoneyDetail moneyDetail = fuMoneyDetailService.get(id);

			// 直接往response的输出流中写excel
			OutputStream os = this.getHttpServletResponse().getOutputStream();
			// 获取文件名称
			String fileName = System.currentTimeMillis() + ".xls";
			// 下载格式设置
			this.getHttpServletResponse().setContentType(
					"APPLICATION/OCTET-STREAM");
			this.getHttpServletResponse().setHeader("Content-Disposition",
					"attachment; filename=\"" + fileName + "\"");

			// 选择模板文件：
			// excel模板存放位置
			String excelPath = this.getServletContext().getRealPath("/");
			String separator = System.getProperty("file.separator");
			String filename = "out_money.xls";
			if (moneyDetail.getFuDictionary().getId() == 8L
					|| moneyDetail.getFuDictionary().getId() == 29L) {
				filename = "in_money.xls";
			} else {
				filename = "out_money.xls";
			}
			excelPath = excelPath + "uploads" + separator + "attach"
					+ separator + filename;
			System.out.println(excelPath);
			InputStream is = new FileInputStream(excelPath);
			Workbook wb = Workbook.getWorkbook(is);
			WorkbookSettings settings = new WorkbookSettings();
			settings.setWriteAccess(null);
			// 通过模板得到一个可写的Workbook：
			WritableCell wc = null;

			WritableWorkbook wwb = Workbook.createWorkbook(os, wb, settings);
			// 选择模板中名称为sheet1的Sheet：
			WritableSheet ws = wwb.getSheet("sheet1");

			WritableCellFormat wcf = ExcelUtil
					.getWritableCellFormatCellFormat();
			WritableCellFormat noWCF = new WritableCellFormat();
			noWCF.setBorder(Border.ALL, BorderLineStyle.NONE);
			// 选择单元格，写入动态值，根据单元格的不同类型转换成相应类型的单元格：
			// Label lable;
			// wc = ws.getWritableCell(7, 11);
			// wc = ExcelUtil.cloneCellWithValue(7, 11, "1100019/MAG11041",
			// noWCF);
			// ws.addCell(wc);

			// 第2行填表时间
			WritableCellFormat wcf2 = new WritableCellFormat();
			wcf2.setAlignment(Alignment.RIGHT);
			wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);
			wc = ws.getWritableCell(0, 1);
			String cTime = DateUtil.getStrFromDate(moneyDetail.getTime(),
					"yyyy年MM月dd日");
			wc = ExcelUtil.cloneCellWithValue(0, 1, "填表时间：  " + cTime, wcf2); // 方案提交时间
			ws.addCell(wc);

			// 第5行
			WritableCellFormat wcf5 = new WritableCellFormat();
			wcf5.setBorder(Border.ALL, BorderLineStyle.THIN);
			wcf5.setAlignment(Alignment.CENTRE);
			wcf5.setVerticalAlignment(VerticalAlignment.CENTRE);
			// 用户姓名
			wc = ws.getWritableCell(3, 4);
			wc = ExcelUtil.cloneCellWithValue(3, 4, moneyDetail.getFuUser()
					.getUserName(), wcf5);
			ws.addCell(wc);
			// 用户性别
			wc = ws.getWritableCell(4, 4);
			String genderName = "";
			if (null != moneyDetail.getFuUser().getGender()
					&& 0 == moneyDetail.getFuUser().getGender()) {
				genderName = "男";
			}
			if (null != moneyDetail.getFuUser().getGender()
					&& 1 == moneyDetail.getFuUser().getGender()) {
				genderName = "女";
			}
			wc = ExcelUtil.cloneCellWithValue(4, 4, "性别：" + genderName, wcf5);
			ws.addCell(wc);
			// 配资比例
			wc = ws.getWritableCell(5, 4);
			wc = ExcelUtil.cloneCellWithValue(
					5,
					4,
					"配资比例："
							+ moneyDetail.getFuProgram().getSafeMoney()
									.divide(new BigDecimal(10000))
							+ ":"
							+ moneyDetail.getFuProgram().getMatchMoney()
									.divide(new BigDecimal(10000)) + "万", wcf5); // 分账户号
			ws.addCell(wc);
			// 分账户号
			wc = ws.getWritableCell(7, 4);
			wc = ExcelUtil.cloneCellWithValue(7, 4, moneyDetail.getFuProgram()
					.getTradeAccount()
					+ "（ 密码：                               ）", wcf5); // 分账户号
			ws.addCell(wc);

			// 第6行
			WritableCellFormat wcf6 = new WritableCellFormat();
			wcf6.setBorder(Border.ALL, BorderLineStyle.THIN); // 加四周边框线条
			wcf6.setAlignment(Alignment.CENTRE); // 水平居中
			wcf6.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直居中
			wc = ws.getWritableCell(3, 5);
			wc = ExcelUtil.cloneCellWithValue(3, 5, moneyDetail.getFuProgram()
					.getFuServer().getServerRealName(), wcf6); // 期货公司
			ws.addCell(wc);
			// wc = ws.getWritableCell(5, 5);
			// wc = ExcelUtil.cloneCellWithValue(5, 5,
			// moneyDetail.getFuProgram().getFuUser().getUserName(), wcf6);
			// //开户姓名
			// ws.addCell(wc);
			wc = ws.getWritableCell(7, 5);
			wc = ExcelUtil.cloneCellWithValue(7, 5, "", wcf6); // 主账户号
			ws.addCell(wc);

			// 第7行
			if (moneyDetail.getFuDictionary().getId() == Long.valueOf("32")) {// 方案结算
				WritableCellFormat wcf7 = new WritableCellFormat();
				wcf7.setBorder(Border.ALL, BorderLineStyle.THIN); // 加四周边框线条
				wcf7.setAlignment(Alignment.LEFT); // 水平居中
				wcf7.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直居中
				wc = ws.getWritableCell(1, 6);
				String str = "";
				if (moneyDetail.getFuProgram().getClearTime().getTime() < moneyDetail
						.getFuProgram().getCloseTime().getTime()) {
					str = "提前终止合同";
				} else {
					str = "到期终止合同";
				}
				wc = ExcelUtil.cloneCellWithValue(1, 6, "申请缘由       （" + str
						+ "）", wcf7);
				ws.addCell(wc);
			}

			// 第9行
			WritableCellFormat wcf9 = new WritableCellFormat();
			wcf9.setBorder(Border.ALL, BorderLineStyle.THIN); // 加四周边框线条
			wcf9.setAlignment(Alignment.LEFT); // 水平居中
			wcf9.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直居中
			wc = ws.getWritableCell(1, 8);
			wc = ExcelUtil.cloneCellWithValue(1, 8, moneyDetail.getComment(),
					wcf9); // 客户提取盈利
			ws.addCell(wc);

			// 第10行
			WritableCellFormat wcf10 = new WritableCellFormat();
			wcf10.setBorder(Border.ALL, BorderLineStyle.THIN); // 加四周边框线条
			wcf10.setAlignment(Alignment.RIGHT); // 水平居左
			wcf10.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直居中
			wc = ws.getWritableCell(0, 9);
			String admin_name = "";
			if (null != moneyDetail.getFuProgram().getFuAdmin()) {
				admin_name = moneyDetail.getFuProgram().getFuAdmin().getName();
			}
			// String checkTime = "";
			// if(null != moneyDetail.getCheckTime()){
			// checkTime = DateUtil.getStrFromDate(moneyDetail.getCheckTime(),
			// "yyyy年MM月dd日");
			// }
			wc = ExcelUtil.cloneCellWithValue(0, 9, "权属人签字："
					+ moneyDetail.getFuProgram().getComment()
					+ "               经办：" + admin_name
					+ "               核对：                              日期： "
					+ cTime, wcf10); // 强平线
			ws.addCell(wc);

			wwb.write();
			// 关闭文件
			wwb.close();
			System.out.println("导出成功");
			return null;
		} catch (Exception e) {
			System.out.println("导出失败");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 导出页面
	 * 
	 * @return
	 */
	@Action("exportExcelAjax")
	public String exportExcelAjax() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("bigType", 2);// 方案明细
			if (id != null) {
				map.put("id", id);
			}
			if (programId != null) {
				map.put("programId", programId);
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
			if (status != null) {
				map.put("status", status);
			}
			if (money1 != null) {
				map.put("money1", money1);
			}
			if (money2 != null) {
				map.put("money2", money2);
			}
			if (date1 != null) {
				map.put("date1", date1);
			}
			if (date2 != null) {
				map.put("date2", date2);
			}
			if (!StringUtil.isBlank(comment)) {
				map.put("comment", comment);
			}
			if (totalCount == null) {
				totalCount = fuMoneyDetailService.getCount(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * @throws UnsupportedEncodingException
	 *             导出数据
	 * */
	@Action("exportAllExcel")
	public String exportAllExcel() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("bigType", 2);// 方案明细
			if (id != null) {
				map.put("id", id);
			}
			if (programId != null) {
				map.put("programId", programId);
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
			if (status != null) {
				map.put("status", status);
			}
			if (money1 != null) {
				map.put("money1", money1);
			}
			if (money2 != null) {
				map.put("money2", money2);
			}
			if (date1 != null) {
				map.put("date1", date1);
			}
			if (date2 != null) {
				map.put("date2", date2);
			}
			if (!StringUtil.isBlank(comment)) {
				map.put("comment", comment);
			}
			if (totalCount == null) {
				totalCount = fuMoneyDetailService.getCount(map);
			}
			List<FuMoneyDetail> list = fuMoneyDetailService
					.findFuMoneyDetailByParams(map);
			ExportExcel exportExcel = new ExportExcel();

			String[] headData = new String[10];
			headData[0] = "明细ID";
			headData[1] = "用户名";
			headData[2] = "真实姓名";
			headData[3] = "方案ID";
			headData[4] = "众期账号";
			headData[5] = "类型";
			headData[6] = "金额";
			headData[7] = "备注";
			headData[8] = "时间";
			headData[9] = "操作";

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			DecimalFormat df = new DecimalFormat("#,###,##0.00");
			// DecimalFormat df2 = new DecimalFormat("#,###,##0.000");
			List<Map<Integer, Object>> dataSet = new ArrayList<Map<Integer, Object>>();
			FuMoneyDetail detail = null;
			for (int i = 0; i < list.size(); i++) {
				detail = list.get(i);
				Map<Integer, Object> m = new HashMap<Integer, Object>();
				m.put(0, detail.getId());
				m.put(1, detail.getFuUser().getAccountName());
				m.put(2, detail.getFuUser().getUserName());
				m.put(3, detail.getFuProgram().getId());
				m.put(4,
						String.valueOf(
								detail.getFuProgram().getFuServer().getId())
								.concat("_"
										+ String.valueOf(detail.getFuProgram()
												.getTradeAccount())));
				m.put(5, detail.getFuDictionary().getName());
				m.put(6, df.format(detail.getMoney()));
				m.put(7, detail.getComment());
				m.put(8, sdf.format(detail.getTime()));
				m.put(9, "导出Excel");
				dataSet.add(m);
			}
			exportExcel.exportToExcel(headData, dataSet, 0);
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

	public Long getDictionaryId() {
		return dictionaryId;
	}

	public void setDictionaryId(Long dictionaryId) {
		this.dictionaryId = dictionaryId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Long getProgramId() {
		return programId;
	}

	public void setProgramId(Long programId) {
		this.programId = programId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
}
