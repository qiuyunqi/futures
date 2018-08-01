package com.hongwei.futures.web.action.finance_receipt;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
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
import com.hongwei.futures.util.RMB;
import com.hongwei.futures.util.StringUtil;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class FinanceReceiptAction extends BaseAction {

	@Autowired
	private FuMoneyDetailService fuMoneyDetailService;

	private FuAdmin admin;
	private Long adminId;
	private Integer totalCount;

	private Long id;
	private Long detailType;
	private Date time1;
	private Date time2;
	private Integer state;

	@Action("receiptQuery")
	public String receiptQuery() {
		try {
			SimpleDateFormat ym = new SimpleDateFormat("yyyy-MM-dd");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("isIncome", true);
			if (state == null) {
				state = 1;
			}
			map.put("state", state);
			if (detailType != null) {
				map.put("dictionaryId", detailType);
			}
			if (time1 != null) {
				map.put("time1", ym.format(time1));
			}
			if (time2 != null) {
				map.put("time2", ym.format(time2));
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
	 * 导出收款单据
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
			excelPath = excelPath + "uploads" + separator + "attach"
					+ separator + "finance_receipt.xls";
			// System.out.println(excelPath);
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

			// 第2行填表时间和单据流水号
			WritableCellFormat wcf2 = new WritableCellFormat();
			wcf2.setAlignment(Alignment.RIGHT);
			wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);
			wc = ws.getWritableCell(0, 1);
			wc = ExcelUtil.cloneCellWithValue(
					0,
					1,
					"填表时间："
							+ (moneyDetail.getTime() == null ? "" : DateUtil
									.getStrFromDate(moneyDetail.getTime(),
											"yyyy年MM月dd日")
									+ "  单据流水号: " + moneyDetail.getId()), wcf2);
			ws.addCell(wc);

			// 第4行用户名
			WritableCellFormat wcf3 = new WritableCellFormat();
			wcf3.setAlignment(Alignment.CENTRE);
			wcf3.setVerticalAlignment(VerticalAlignment.CENTRE);
			wc = ws.getWritableCell(3, 3);
			wc = ExcelUtil.cloneCellWithValue(3, 3, moneyDetail.getFuUser()
					.getAccountName() == null ? "" : moneyDetail.getFuUser()
					.getAccountName(), wcf3);
			ws.addCell(wc);

			// 第4行真实姓名
			WritableCellFormat wcf4 = new WritableCellFormat();
			wcf4.setAlignment(Alignment.CENTRE);
			wcf4.setVerticalAlignment(VerticalAlignment.CENTRE);
			wc = ws.getWritableCell(6, 3);
			wc = ExcelUtil.cloneCellWithValue(6, 3, moneyDetail.getFuUser()
					.getUserName() == null ? "" : moneyDetail.getFuUser()
					.getUserName(), wcf4);
			ws.addCell(wc);

			// 第6行收款类型
			WritableCellFormat wcf5 = new WritableCellFormat();
			wcf5.setAlignment(Alignment.CENTRE);
			wcf5.setVerticalAlignment(VerticalAlignment.CENTRE);
			wc = ws.getWritableCell(2, 5);
			wc = ExcelUtil.cloneCellWithValue(2, 5, moneyDetail
					.getFuDictionary().getId() == null ? "" : moneyDetail
					.getFuDictionary().getId() == 12 ? "合伙人联盟收益" : moneyDetail
					.getFuDictionary().getId() == 13 ? "平台赔付" : moneyDetail
					.getFuDictionary().getId() == 14 ? "支付管理费" : "", wcf5);
			ws.addCell(wc);

			// 第8行收款人姓名
			WritableCellFormat wcf6 = new WritableCellFormat();
			wcf6.setBorder(Border.ALL, BorderLineStyle.THIN); // 加四周边框线条
			wcf6.setAlignment(Alignment.CENTRE);
			wcf6.setVerticalAlignment(VerticalAlignment.CENTRE);
			wc = ws.getWritableCell(6, 7);
			wc = ExcelUtil.cloneCellWithValue(6, 7, moneyDetail.getFuUser()
					.getUserName() == null ? "" : moneyDetail.getFuUser()
					.getUserName(), wcf6);
			ws.addCell(wc);

			// 第9行收款人开户行
			WritableCellFormat wcf7 = new WritableCellFormat();
			wcf7.setBorder(Border.ALL, BorderLineStyle.THIN); // 加四周边框线条
			wcf7.setAlignment(Alignment.CENTRE);
			wcf7.setVerticalAlignment(VerticalAlignment.CENTRE);
			wc = ws.getWritableCell(6, 8);
			wc = ExcelUtil.cloneCellWithValue(6, 8,
					moneyDetail.getFuBankCard() == null ? "" : moneyDetail
							.getFuBankCard().getBankName() == null ? ""
							: moneyDetail.getFuBankCard().getBankName(), wcf7);
			ws.addCell(wc);

			// 第10行收款人开户行账户
			WritableCellFormat wcf8 = new WritableCellFormat();
			wcf8.setBorder(Border.ALL, BorderLineStyle.THIN);
			wcf8.setAlignment(Alignment.CENTRE);
			wcf8.setVerticalAlignment(VerticalAlignment.CENTRE);
			wc = ws.getWritableCell(6, 9);
			wc = ExcelUtil
					.cloneCellWithValue(6, 9,
							moneyDetail.getFuBankCard() == null ? ""
									: moneyDetail.getFuBankCard()
											.getCardNumber() == null ? ""
											: moneyDetail.getFuBankCard()
													.getCardNumber(), wcf8);
			ws.addCell(wc);

			// 第11行金额大写
			WritableCellFormat wcf9 = new WritableCellFormat();
			wcf9.setAlignment(Alignment.LEFT);
			wcf9.setVerticalAlignment(VerticalAlignment.CENTRE);
			wc = ws.getWritableCell(1, 10);
			wc = ExcelUtil
					.cloneCellWithValue(
							1,
							10,
							"金额大写： "
									+ RMB.toBigAmt(moneyDetail.getMoney()
											.doubleValue())
									+ "（￥"
									+ StringUtil.getDecimalFormat(moneyDetail
											.getMoney()) + "）", wcf9);
			ws.addCell(wc);

			// 第12行备注
			WritableCellFormat wcf10 = new WritableCellFormat();
			wcf10.setBorder(Border.ALL, BorderLineStyle.THIN);
			wcf10.setAlignment(Alignment.LEFT);
			wcf10.setVerticalAlignment(VerticalAlignment.CENTRE);
			wc = ws.getWritableCell(0, 11);
			wc = ExcelUtil.cloneCellWithValue(0, 11,
					"备注："
							+ (moneyDetail.getComment() == null ? ""
									: moneyDetail.getComment()), wcf10); // 强平线
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

	public Long getDetailType() {
		return detailType;
	}

	public void setDetailType(Long detailType) {
		this.detailType = detailType;
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
