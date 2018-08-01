package com.hongwei.futures.web.action.stock_money_detail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.VerticalAlignment;
import jxl.write.Alignment;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuStockMoneyDetail;
import com.hongwei.futures.model.FuStockMoneyDetailTemp;
import com.hongwei.futures.service.FuStockMoneyDetailService;
import com.hongwei.futures.service.FuStockMoneyDetailTempService;
import com.hongwei.futures.util.ExcelUtil;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
@SuppressWarnings("all")
public class StockMoneyDetailAction extends BaseAction {
	@Autowired
	private FuStockMoneyDetailService fuStockMoneyDetailService;
	@Autowired
	private FuStockMoneyDetailTempService fuStockMoneyDetailTempService;

	private FuAdmin admin;
	private Long adminId;
	private Integer totalCount;

	// file并不是指前端jsp上传过来的文件本身，而是文件上传过来存放在临时文件夹下面的文件
	private File excelfile;

	// 提交过来的file的名字
	private String excelfileFileName;

	// 提交过来的file的MIME类型
	private String excelfileContentType;

	private Long userId;
	private String phone;
	private Date time1;
	private Date time2;
	private Date tradeTime;
	// 批次号
	private String batchId;

	/**
	 * 解套者流水上传页面
	 * 
	 * @return
	 */
	@Action("detailUpload")
	public String detailUpload() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtil.isBlank(batchId)) {
				map.put("batchId", Integer.valueOf(batchId));
			}
			List<FuStockMoneyDetailTemp> batchList = fuStockMoneyDetailTempService.queryBatchList((this.getPageNo() - 1) * this.getPageSize(), this.getPageSize(), map);
			if (totalCount == null) {
				totalCount = fuStockMoneyDetailTempService.countBatchTemp(map);
			}
			this.getActionContext().put("batchList", batchList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 删除指定批次的excel记录
	 * 
	 * @return
	 */
	@Action("deleteBatch")
	public String deleteBatch() {
		try {
			if (!StringUtil.isBlank(batchId)) {
				fuStockMoneyDetailTempService.deleteByBatchId(batchId);
				write("批次流水删除成功！");
				return null;
			} else {
				write("请输入批次号！");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 文件上传
	 * 
	 * @return
	 */
	@Action("fileUpload")
	public String fileUpload() {
		try {
			String root = ServletActionContext.getServletContext().getRealPath("/uploads");
			FileInputStream fis = null;
			FileOutputStream fos = null;
			String res = "success";
			try {
				fis = new FileInputStream(excelfile);
				fos = new FileOutputStream(new File(root, excelfileFileName));
				byte[] buffer = new byte[1024];
				int length = 0;
				while ((length = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, length);
				}
				uploadExcel(excelfileFileName);
			} catch (Exception e) {
				e.printStackTrace();
				res = "error";
			} finally {
				close(fis, fos);
			}
			write(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void close(FileInputStream fis, FileOutputStream fos) {
		if (fis != null) {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (fos != null) {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 将excel中的记录保存到解套流水临时表中
	 * 
	 * @param uploadFileName
	 * @throws Exception
	 */
	public void uploadExcel(String uploadFileName) {
		try {
			String directory = "/uploads";
			String targetDirectory = ServletActionContext.getServletContext().getRealPath(directory);
			File target = new File(targetDirectory, uploadFileName);
			FileInputStream fis = new FileInputStream(target);
			Workbook wb = Workbook.getWorkbook(fis);
			Sheet sheet = wb.getSheet(0);
			fuStockMoneyDetailService.saveExcel(sheet);
			/*
			 * if("success".equals(res)){ //处理用户未缴费用
			 * //fuStockMoneyInfoService.saveNoneFee(); }
			 */
			wb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 流水处理
	 * 
	 * @return
	 */
	@Action("detailProcess")
	public String detailProcess() throws Exception {
		String res = "success";
		try {
			fuStockMoneyDetailService.saveStockProcess(admin);
			// 清空临时表
			fuStockMoneyDetailTempService.deleteByBatchId(null);
		} catch (Exception e) {
			e.printStackTrace();
			res = "error";
		}
		write(res);
		return null;
	}

	/**
	 * 解套者流水查询
	 * 
	 * @return
	 */
	@Action("detailQuery")
	public String detailQuery() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (userId != null) {
				map.put("userId", userId);
			}
			if (!StringUtil.isBlank(phone)) {
				map.put("phone", phone);
			}
			if (time1 != null) {
				map.put("time1", time1);
			}
			if (time2 != null) {
				map.put("time2", time2);
			}
			List<FuStockMoneyDetail> detailList = fuStockMoneyDetailService.queryStockMoneyDetail((this.getPageNo() - 1) * this.getPageSize(), this.getPageSize(), map);
			if (totalCount == null) {
				totalCount = fuStockMoneyDetailService.countStockMoneyDetail(map);
			}
			this.getActionContext().put("detailList", detailList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 用户解套者流水查询
	 * 
	 * @return
	 */
	@Action("userDetailQuery")
	public String userDetailQuery() {
		try {
			List<FuStockMoneyDetail> detailList = fuStockMoneyDetailService.findDetailByUser(userId);
			this.getActionContext().put("detailList", detailList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 余劵宝流水日统计
	 * 
	 * @return
	 */
	@Action("exportYqbDayStatExcel")
	public String exportYqbDayStatExcel() {
		try {
			OutputStream os = this.getHttpServletResponse().getOutputStream();
			String fileName = System.currentTimeMillis() + ".xls";
			this.getHttpServletResponse().setContentType("APPLICATION/OCTET-STREAM");
			this.getHttpServletResponse().setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			String excelPath = this.getServletContext().getRealPath("/");
			String separator = System.getProperty("file.separator");
			excelPath = excelPath + "uploads" + separator + "attach" + separator + "yqbDayStat.xls";
			InputStream is = new FileInputStream(excelPath);
			Workbook wb = Workbook.getWorkbook(is);
			WorkbookSettings settings = new WorkbookSettings();
			settings.setWriteAccess(null);
			WritableWorkbook wwb = Workbook.createWorkbook(os, wb, settings);
			WritableCell wc = null;
			WritableSheet ws = wwb.getSheet("sheet1");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMdd");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tradeTime", tradeTime);
			List<FuStockMoneyDetail> detailList = fuStockMoneyDetailService.queryStockMoneyDetail(0, Integer.MAX_VALUE, map);
			if (detailList.size() > 0) {
				for (int i = 1; i < detailList.size() + 1; i++) {
					// 日期
					WritableCellFormat wcf = new WritableCellFormat();
					wcf.setAlignment(Alignment.CENTRE);
					wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
					wc = ws.getWritableCell(0, i);
					wc = ExcelUtil.cloneCellWithValue(0, i, sdf3.format(detailList.get(i - 1).getTradeTime()), wcf);
					ws.addCell(wc);

					// 序号
					WritableCellFormat wcf2 = new WritableCellFormat();
					wcf2.setAlignment(Alignment.CENTRE);
					wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);
					wc = ws.getWritableCell(1, i);
					wc = ExcelUtil.cloneCellWithValue(1, i, i, wcf2);
					ws.addCell(wc);

					// 真实姓名
					WritableCellFormat wcf3 = new WritableCellFormat();
					wcf3.setAlignment(Alignment.CENTRE);
					wcf3.setVerticalAlignment(VerticalAlignment.CENTRE);
					wc = ws.getWritableCell(2, i);
					wc = ExcelUtil.cloneCellWithValue(2, i, detailList.get(i - 1).getFuUser().getUserName() == null ? "" : detailList.get(i - 1).getFuUser().getUserName(), wcf3);
					ws.addCell(wc);

					// 每日可用市值
					WritableCellFormat wcf4 = new WritableCellFormat();
					wcf4.setAlignment(Alignment.CENTRE);
					wcf4.setVerticalAlignment(VerticalAlignment.CENTRE);
					wc = ws.getWritableCell(3, i);
					wc = ExcelUtil.cloneCellWithValue(3, i, detailList.get(i - 1).getTotalValue() == null ? "" : detailList.get(i - 1).getTotalValue(), wcf3);
					ws.addCell(wc);

					// 每日可用资金
					WritableCellFormat wcf5 = new WritableCellFormat();
					wcf5.setAlignment(Alignment.CENTRE);
					wcf5.setVerticalAlignment(VerticalAlignment.CENTRE);
					wc = ws.getWritableCell(4, i);
					wc = ExcelUtil.cloneCellWithValue(4, i, detailList.get(i - 1).getTotalCash() == null ? BigDecimal.ZERO : detailList.get(i - 1).getTotalCash().toString(), wcf5);
					ws.addCell(wc);

					// 可用资金比
					WritableCellFormat wcf6 = new WritableCellFormat();
					wcf6.setAlignment(Alignment.RIGHT);
					wcf6.setVerticalAlignment(VerticalAlignment.CENTRE);
					wc = ws.getWritableCell(5, i);
					BigDecimal moneyPercent = detailList.get(i - 1).getTotalCash().divide(detailList.get(i - 1).getTotalCash().add(detailList.get(i - 1).getTotalValue()), 4, BigDecimal.ROUND_HALF_UP);
					wc = ExcelUtil.cloneCellWithValue(5, i, moneyPercent.multiply(new BigDecimal(100)).toString() + "%", wcf5);
					ws.addCell(wc);

					// 账户收益
					WritableCellFormat wcf7 = new WritableCellFormat();
					wcf7.setAlignment(Alignment.RIGHT);
					wcf7.setVerticalAlignment(VerticalAlignment.CENTRE);
					wc = ws.getWritableCell(6, i);
					wc = ExcelUtil.cloneCellWithValue(6, i, detailList.get(i - 1).getNowProfit() == null ? BigDecimal.ZERO : detailList.get(i - 1).getNowProfit().toString(), wcf5);
					ws.addCell(wc);

					// 周累计收益
					WritableCellFormat wcf8 = new WritableCellFormat();
					wcf8.setAlignment(Alignment.CENTRE);
					wcf8.setVerticalAlignment(VerticalAlignment.CENTRE);
					wc = ws.getWritableCell(7, i);
					wc = ExcelUtil.cloneCellWithValue(7, i, detailList.get(i - 1).getWeekProfit() == null ? BigDecimal.ZERO : detailList.get(i - 1).getWeekProfit().toString(), wcf8);
					ws.addCell(wc);

					// 收益率
					WritableCellFormat wcf9 = new WritableCellFormat();
					wcf9.setAlignment(Alignment.CENTRE);
					wcf9.setVerticalAlignment(VerticalAlignment.CENTRE);
					wc = ws.getWritableCell(8, i);
					BigDecimal syl = detailList.get(i - 1).getNowProfit().divide(detailList.get(i - 1).getTotalValue().multiply(new BigDecimal("1.15")), 4, BigDecimal.ROUND_HALF_UP);
					wc = ExcelUtil.cloneCellWithValue(8, i, syl.multiply(new BigDecimal(100)).toString() + "%", wcf9);
					ws.addCell(wc);

					// 累计收益率（周）
					WritableCellFormat wcf10 = new WritableCellFormat();
					wcf10.setAlignment(Alignment.CENTRE);
					wcf10.setVerticalAlignment(VerticalAlignment.CENTRE);
					wc = ws.getWritableCell(9, i);
					BigDecimal ljsyl = detailList.get(i - 1).getWeekProfit().divide(detailList.get(i - 1).getTotalValue().multiply(new BigDecimal("1.15")), 4, BigDecimal.ROUND_HALF_UP);
					wc = ExcelUtil.cloneCellWithValue(9, i, ljsyl.multiply(new BigDecimal(100)).toString() + "%", wcf10);
					ws.addCell(wc);

					// 成交额
					WritableCellFormat wcf11 = new WritableCellFormat();
					wcf11.setAlignment(Alignment.CENTRE);
					wcf11.setVerticalAlignment(VerticalAlignment.CENTRE);
					wc = ws.getWritableCell(10, i);
					wc = ExcelUtil.cloneCellWithValue(10, i, detailList.get(i - 1).getDayKnockdown() == null ? BigDecimal.ZERO : detailList.get(i - 1).getDayKnockdown().toString(), wcf11);
					ws.addCell(wc);

					// 成交比
					WritableCellFormat wcf12 = new WritableCellFormat();
					wcf12.setAlignment(Alignment.CENTRE);
					wcf12.setVerticalAlignment(VerticalAlignment.CENTRE);
					wc = ws.getWritableCell(11, i);
					BigDecimal cjb = detailList.get(i - 1).getDayKnockdown().divide(detailList.get(i - 1).getTotalValue(), 4, BigDecimal.ROUND_HALF_UP);
					wc = ExcelUtil.cloneCellWithValue(11, i, cjb.multiply(new BigDecimal(100)).toString() + "%", wcf12);
					ws.addCell(wc);

					// 账户状态（手工填写）

					// 账户分析（手工填写）

					// 交易团队
					WritableCellFormat wcf14 = new WritableCellFormat();
					wcf14.setAlignment(Alignment.CENTRE);
					wcf14.setVerticalAlignment(VerticalAlignment.CENTRE);
					wc = ws.getWritableCell(14, i);
					wc = ExcelUtil.cloneCellWithValue(14, i, detailList.get(i - 1).getTransaction(), wcf14);
					ws.addCell(wc);

					// 营销员
					WritableCellFormat wcf15 = new WritableCellFormat();
					wcf15.setAlignment(Alignment.CENTRE);
					wcf15.setVerticalAlignment(VerticalAlignment.CENTRE);
					wc = ws.getWritableCell(15, i);
					wc = ExcelUtil.cloneCellWithValue(15, i, detailList.get(i - 1).getSalesMan(), wcf15);
					ws.addCell(wc);

					// 与客户分成
					WritableCellFormat wcf16 = new WritableCellFormat();
					wcf16.setAlignment(Alignment.CENTRE);
					wcf16.setVerticalAlignment(VerticalAlignment.CENTRE);
					wc = ws.getWritableCell(16, i);
					wc = ExcelUtil.cloneCellWithValue(16, i, detailList.get(i - 1).getRemark(), wcf16);
					ws.addCell(wc);

					// 上交账户时间
					WritableCellFormat wcf17 = new WritableCellFormat();
					wcf17.setAlignment(Alignment.CENTRE);
					wcf17.setVerticalAlignment(VerticalAlignment.CENTRE);
					wc = ws.getWritableCell(17, i);
					wc = ExcelUtil.cloneCellWithValue(17, i, sdf2.format(detailList.get(i - 1).getCreateTime()), wcf17);
					ws.addCell(wc);

					// 与交易团队分成
					WritableCellFormat wcf18 = new WritableCellFormat();
					wcf18.setAlignment(Alignment.CENTRE);
					wcf18.setVerticalAlignment(VerticalAlignment.CENTRE);
					wc = ws.getWritableCell(18, i);
					wc = ExcelUtil.cloneCellWithValue(18, i, detailList.get(i - 1).getRemark(), wcf18);
					ws.addCell(wc);

					// 备注（手工填写）
				}
				// 总计
				// 客户人数
				WritableCellFormat wcf19 = new WritableCellFormat();
				wcf19.setAlignment(Alignment.CENTRE);
				wcf19.setVerticalAlignment(VerticalAlignment.CENTRE);
				wc = ws.getWritableCell(21, 1);
				wc = ExcelUtil.cloneCellWithValue(21, 1, detailList.size() + "", wcf19);
				ws.addCell(wc);

				List<Object[]> list1 = fuStockMoneyDetailService.findListByDate(tradeTime);// 查出当天所有人总市值
				List<Object[]> list2 = fuStockMoneyDetailService.findListByTime(tradeTime);// 查出当天有成交额的总市值和数目

				// 新增人数（手工）
				// 流失人数（手工）
				// 操作用户
				WritableCellFormat wcf20 = new WritableCellFormat();
				wcf20.setAlignment(Alignment.CENTRE);
				wcf20.setVerticalAlignment(VerticalAlignment.CENTRE);
				wc = ws.getWritableCell(21, 4);
				wc = ExcelUtil.cloneCellWithValue(21, 4, list2.get(0)[0].toString(), wcf20);
				ws.addCell(wc);

				// 未操作用户
				WritableCellFormat wcf21 = new WritableCellFormat();
				wcf21.setAlignment(Alignment.CENTRE);
				wcf21.setVerticalAlignment(VerticalAlignment.CENTRE);
				wc = ws.getWritableCell(21, 5);
				wc = ExcelUtil.cloneCellWithValue(21, 5, detailList.size() - Integer.parseInt(list2.get(0)[0].toString()), wcf21);
				ws.addCell(wc);

				// 交易账户总规模
				WritableCellFormat wcf22 = new WritableCellFormat();
				wcf22.setAlignment(Alignment.CENTRE);
				wcf22.setVerticalAlignment(VerticalAlignment.CENTRE);
				wc = ws.getWritableCell(21, 6);
				wc = ExcelUtil.cloneCellWithValue(21, 6, list2.get(0)[1], wcf22);
				ws.addCell(wc);

				// 总市值规模
				WritableCellFormat wcf23 = new WritableCellFormat();
				wcf23.setAlignment(Alignment.CENTRE);
				wcf23.setVerticalAlignment(VerticalAlignment.CENTRE);
				wc = ws.getWritableCell(21, 7);
				wc = ExcelUtil.cloneCellWithValue(21, 7, list1.get(0), wcf23);
				ws.addCell(wc);

				// 交易账户占总规模
				WritableCellFormat wcf24 = new WritableCellFormat();
				wcf24.setAlignment(Alignment.CENTRE);
				wcf24.setVerticalAlignment(VerticalAlignment.CENTRE);
				wc = ws.getWritableCell(21, 8);
				BigDecimal zzgm = new BigDecimal(list2.get(0)[1].toString()).divide(new BigDecimal(String.valueOf(list1.get(0))), 4, BigDecimal.ROUND_HALF_UP);
				wc = ExcelUtil.cloneCellWithValue(21, 8, zzgm.multiply(new BigDecimal(100)).toString() + "%", wcf24);
				ws.addCell(wc);

				// 被移除客户名称（手工）
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

	/**
	 * 余劵宝流水周统计
	 * 
	 * @return
	 */
	@Action("exportYqbWeekStatExcel")
	public String exportYqbWeekStatExcel() {
		try {
			OutputStream os = this.getHttpServletResponse().getOutputStream();
			String fileName = System.currentTimeMillis() + ".xls";
			this.getHttpServletResponse().setContentType("APPLICATION/OCTET-STREAM");
			this.getHttpServletResponse().setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			String excelPath = this.getServletContext().getRealPath("/");
			String separator = System.getProperty("file.separator");
			excelPath = excelPath + "uploads" + separator + "attach" + separator + "yqbWeekStat.xls";
			InputStream is = new FileInputStream(excelPath);
			Workbook wb = Workbook.getWorkbook(is);
			WorkbookSettings settings = new WorkbookSettings();
			settings.setWriteAccess(null);
			WritableWorkbook wwb = Workbook.createWorkbook(os, wb, settings);
			WritableCell wc = null;
			WritableSheet ws = wwb.getSheet("sheet1");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy年MM月dd日");

			String beginDate = null;
			String endDate = null;
			String date1 = null;
			String date2 = null;
			int betweenDate = 0;// 统计的日期天数
			Calendar cal1 = Calendar.getInstance();// 计算出周统计的其实日期
			int weekday = cal1.get(Calendar.DAY_OF_WEEK);
			if (weekday == 2) {// 如果是星期一
				beginDate = sdf.format(cal1.getTime());
				endDate = sdf.format(cal1.getTime());
				date1 = sdf4.format(cal1.getTime());
				date2 = sdf4.format(cal1.getTime());
				betweenDate = 1;
			} else {// 如果不是星期一
				for (int i = 1; i < 7; i++) {
					cal1.add(Calendar.DATE, -1);
					weekday = cal1.get(Calendar.DAY_OF_WEEK);
					if (weekday == 2) {
						betweenDate = i + 1;
						break;
					}
				}
				beginDate = sdf.format(cal1.getTime());
				date1 = sdf4.format(cal1.getTime());

				Calendar cal2 = Calendar.getInstance();// 计算出周统计的结束日期
				int weekday2 = cal2.get(Calendar.DAY_OF_WEEK);
				if (weekday == 1) {// 今天星期天
					cal2.add(Calendar.DATE, -2);
				} else if (weekday == 7) {// 今天星期六
					cal2.add(Calendar.DATE, -1);
				} else {// 其他
					cal2.setTime(new Date());
				}
				endDate = sdf.format(cal2.getTime());
				date2 = sdf4.format(cal2.getTime());

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("beginDate", sdf.parse(beginDate));
				map.put("endDate", sdf.parse(endDate));
				List<FuStockMoneyDetail> detailList = fuStockMoneyDetailService.queryStockMoneyDetail(0, Integer.MAX_VALUE, map);
				if (detailList.size() > 0) {
					for (int i = 1; i < detailList.size() + 1; i++) {
						// 数量
						WritableCellFormat wcf = new WritableCellFormat();
						wcf.setAlignment(Alignment.CENTRE);
						wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
						wc = ws.getWritableCell(0, i);
						wc = ExcelUtil.cloneCellWithValue(0, i, i, wcf);
						ws.addCell(wc);

						// 日期
						WritableCellFormat wcf2 = new WritableCellFormat();
						wcf2.setAlignment(Alignment.CENTRE);
						wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);
						wc = ws.getWritableCell(1, i);
						wc = ExcelUtil.cloneCellWithValue(1, i, date1 + "-" + date2, wcf2);
						ws.addCell(wc);

						// 账户名称
						WritableCellFormat wcf3 = new WritableCellFormat();
						wcf3.setAlignment(Alignment.CENTRE);
						wcf3.setVerticalAlignment(VerticalAlignment.CENTRE);
						wc = ws.getWritableCell(2, i);
						wc = ExcelUtil.cloneCellWithValue(2, i, detailList.get(i - 1).getFuUser() == null ? "" : detailList.get(i - 1).getFuUser().getUserName(), wcf3);
						ws.addCell(wc);

						/* 公共数据 */
						Map<String, Object> map2 = new HashMap<String, Object>();
						map2.put("beginDate", sdf.parse(beginDate));
						map2.put("endDate", sdf.parse(endDate));
						map2.put("dayKnockdown", BigDecimal.ZERO);
						map2.put("stockId", detailList.get(i - 1).getFuStockAccount().getId());
						List<FuStockMoneyDetail> detail2 = fuStockMoneyDetailService.queryStockMoneyDetail(0, Integer.MAX_VALUE, map2);// 得到当前账户在本周有成交额的流水

						Map<String, Object> map3 = new HashMap<String, Object>();
						map3.put("beginDate", sdf.parse(beginDate));
						map3.put("endDate", sdf.parse(endDate));
						map3.put("stockId", detailList.get(i - 1).getFuStockAccount().getId());
						List<FuStockMoneyDetail> detail3 = fuStockMoneyDetailService.queryStockMoneyDetail(0, Integer.MAX_VALUE, map3);// 得到当前账户在本周的流水

						BigDecimal tolalValue = BigDecimal.ZERO;
						for (FuStockMoneyDetail fuStockMoneyDetail : detail3) {
							tolalValue = tolalValue.add(fuStockMoneyDetail.getTotalValue());
						}
						BigDecimal totalVal = tolalValue.divide(new BigDecimal(betweenDate), 4, BigDecimal.ROUND_HALF_UP);// 总平均市值

						BigDecimal tolalProfit = BigDecimal.ZERO;// 周期内总盈亏
						for (FuStockMoneyDetail fuStockMoneyDetail : detail3) {
							tolalProfit = tolalProfit.add(fuStockMoneyDetail.getNowProfit());
						}

						// 日均盈利率
						BigDecimal rjyll = tolalProfit.divide(totalVal.multiply(new BigDecimal(detail2.size() * 1.15)), 4, BigDecimal.ROUND_HALF_UP);

						BigDecimal dayKnockdown = BigDecimal.ZERO;
						for (FuStockMoneyDetail fuStockMoneyDetail : detail3) {
							dayKnockdown = dayKnockdown.add(fuStockMoneyDetail.getDayKnockdown());
						}
						BigDecimal rjcje = dayKnockdown.divide(new BigDecimal(betweenDate), 4, BigDecimal.ROUND_HALF_UP);// 日均成交额

						/* 公共数据 */

						// 账户盈利状态
						WritableCellFormat wcf4 = new WritableCellFormat();
						wcf4.setAlignment(Alignment.CENTRE);
						wcf4.setVerticalAlignment(VerticalAlignment.CENTRE);
						wc = ws.getWritableCell(3, i);
						String status = "";
						if (rjcje.compareTo(new BigDecimal(0)) <= 0) {
							status = "未交易";
						} else if (rjyll.compareTo(new BigDecimal(0)) == -1) {
							status = "亏损";
						} else if (rjyll.compareTo(new BigDecimal(0)) == 1 && rjyll.compareTo(new BigDecimal(0.002)) == -1) {
							status = "低于正常";
						} else if (rjyll.compareTo(new BigDecimal(0.002)) == 1 || rjyll.compareTo(new BigDecimal(0.002)) == 0) {
							status = "正常";
						}
						wc = ExcelUtil.cloneCellWithValue(3, i, status, wcf3);
						ws.addCell(wc);

						// 账户总市值

						WritableCellFormat wcf5 = new WritableCellFormat();
						wcf5.setAlignment(Alignment.CENTRE);
						wcf5.setVerticalAlignment(VerticalAlignment.CENTRE);
						wc = ws.getWritableCell(4, i);
						wc = ExcelUtil.cloneCellWithValue(4, i, totalVal, wcf5);
						ws.addCell(wc);

						// 账户日均市值
						WritableCellFormat wcf6 = new WritableCellFormat();
						wcf6.setAlignment(Alignment.CENTRE);
						wcf6.setVerticalAlignment(VerticalAlignment.CENTRE);
						wc = ws.getWritableCell(5, i);
						BigDecimal rjsz = totalVal.multiply(new BigDecimal(detail2.size())).divide(new BigDecimal(betweenDate));
						wc = ExcelUtil.cloneCellWithValue(5, i, rjsz, wcf6);
						ws.addCell(wc);

						// 交易天数
						WritableCellFormat wcf7 = new WritableCellFormat();
						wcf7.setAlignment(Alignment.CENTRE);
						wcf7.setVerticalAlignment(VerticalAlignment.CENTRE);
						wc = ws.getWritableCell(6, i);
						wc = ExcelUtil.cloneCellWithValue(6, i, betweenDate, wcf7);
						ws.addCell(wc);

						// 实际交易天数
						WritableCellFormat wcf8 = new WritableCellFormat();
						wcf8.setAlignment(Alignment.CENTRE);
						wcf8.setVerticalAlignment(VerticalAlignment.CENTRE);
						wc = ws.getWritableCell(7, i);
						wc = ExcelUtil.cloneCellWithValue(7, i, detail2.size(), wcf8);
						ws.addCell(wc);

						// 账户交易期间总盈亏
						WritableCellFormat wcf9 = new WritableCellFormat();
						wcf9.setAlignment(Alignment.CENTRE);
						wcf9.setVerticalAlignment(VerticalAlignment.CENTRE);
						wc = ws.getWritableCell(8, i);
						wc = ExcelUtil.cloneCellWithValue(8, i, tolalProfit, wcf9);
						ws.addCell(wc);

						// 日均盈利率
						WritableCellFormat wcf10 = new WritableCellFormat();
						wcf10.setAlignment(Alignment.CENTRE);
						wcf10.setVerticalAlignment(VerticalAlignment.CENTRE);
						wc = ws.getWritableCell(9, i);
						wc = ExcelUtil.cloneCellWithValue(9, i, rjyll.multiply(new BigDecimal("100")) + "%", wcf10);
						ws.addCell(wc);

						// 交易团队
						WritableCellFormat wcf11 = new WritableCellFormat();
						wcf11.setAlignment(Alignment.CENTRE);
						wcf11.setVerticalAlignment(VerticalAlignment.CENTRE);
						wc = ws.getWritableCell(10, i);
						wc = ExcelUtil.cloneCellWithValue(10, i, detailList.get(i - 1).getTransaction(), wcf11);
						ws.addCell(wc);

						// 可用资金初始值
						WritableCellFormat wcf12 = new WritableCellFormat();
						wcf12.setAlignment(Alignment.CENTRE);
						wcf12.setVerticalAlignment(VerticalAlignment.CENTRE);
						wc = ws.getWritableCell(11, i);
						BigDecimal totalCash = BigDecimal.ZERO;
						for (FuStockMoneyDetail fuStockMoneyDetail : detail3) {
							totalCash = totalCash.add(fuStockMoneyDetail.getNowProfit());
						}
						BigDecimal zjcsz = totalCash.divide(new BigDecimal(betweenDate), 4, BigDecimal.ROUND_HALF_UP);
						wc = ExcelUtil.cloneCellWithValue(11, i, zjcsz, wcf12);
						ws.addCell(wc);

						// 可用资金比
						WritableCellFormat wcf13 = new WritableCellFormat();
						wcf13.setAlignment(Alignment.CENTRE);
						wcf13.setVerticalAlignment(VerticalAlignment.CENTRE);
						wc = ws.getWritableCell(12, i);
						BigDecimal kyzjb = zjcsz.divide(zjcsz.add(totalVal), 4, BigDecimal.ROUND_HALF_UP);
						wc = ExcelUtil.cloneCellWithValue(12, i, kyzjb.multiply(new BigDecimal("100")) + "%", wcf13);
						ws.addCell(wc);

						// 日均成交额
						WritableCellFormat wcf14 = new WritableCellFormat();
						wcf14.setAlignment(Alignment.CENTRE);
						wcf14.setVerticalAlignment(VerticalAlignment.CENTRE);
						wc = ws.getWritableCell(13, i);
						wc = ExcelUtil.cloneCellWithValue(13, i, rjcje, wcf14);
						ws.addCell(wc);

						// 日均成交额比
						WritableCellFormat wcf15 = new WritableCellFormat();
						wcf15.setAlignment(Alignment.CENTRE);
						wcf15.setVerticalAlignment(VerticalAlignment.CENTRE);
						wc = ws.getWritableCell(14, i);
						wc = ExcelUtil.cloneCellWithValue(14, i, rjcje.divide(totalVal, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")) + "%", wcf15);
						ws.addCell(wc);

						// 营销员
						WritableCellFormat wcf16 = new WritableCellFormat();
						wcf16.setAlignment(Alignment.CENTRE);
						wcf16.setVerticalAlignment(VerticalAlignment.CENTRE);
						wc = ws.getWritableCell(15, i);
						wc = ExcelUtil.cloneCellWithValue(15, i, detailList.get(i - 1).getSalesMan(), wcf16);
						ws.addCell(wc);

						// 分成
						WritableCellFormat wcf17 = new WritableCellFormat();
						wcf17.setAlignment(Alignment.CENTRE);
						wcf17.setVerticalAlignment(VerticalAlignment.CENTRE);
						wc = ws.getWritableCell(16, i);
						wc = ExcelUtil.cloneCellWithValue(16, i, detailList.get(i - 1).getRemark(), wcf17);
						ws.addCell(wc);

						// 账户分析（人工）
					}
				}
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

	public File getExcelfile() {
		return excelfile;
	}

	public void setExcelfile(File excelfile) {
		this.excelfile = excelfile;
	}

	public String getExcelfileFileName() {
		return excelfileFileName;
	}

	public void setExcelfileFileName(String excelfileFileName) {
		this.excelfileFileName = excelfileFileName;
	}

	public String getExcelfileContentType() {
		return excelfileContentType;
	}

	public void setExcelfileContentType(String excelfileContentType) {
		this.excelfileContentType = excelfileContentType;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public Date getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}
}
