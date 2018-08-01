package com.hongwei.futures.web.action.admin_op_draw_money;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Date;

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
import com.hongwei.futures.model.FuDrawMoney;
import com.hongwei.futures.model.FuSmsLog;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuDrawMoneyService;
import com.hongwei.futures.service.FuSmsLogService;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.util.DateUtil;
import com.hongwei.futures.util.ExcelUtil;
import com.hongwei.futures.util.RMB;
import com.hongwei.futures.util.StringUtil;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class DrawMoneyAction extends BaseAction {
	private static final long serialVersionUID = 7742615717958007391L;
	
	@Autowired
	private FuDrawMoneyService fuDrawMoneyService;
	@Autowired
	private FuUserService fuUserService;
	@Autowired
	private FuSmsLogService fuSmsLogService;

	private FuAdmin admin;
	private Long adminId;
	private Long id;
	private String comment;
	private Integer status;
	private String msg;

	/**
	 * 提款审核浮层(后台)
	 * 
	 * @return
	 */
	@Action("checkDrawMoneyAjax")
	public String checkDrawMoneyAjax() throws Exception {
		try {
			FuDrawMoney draw = fuDrawMoneyService.get(id);
			// if(draw.getStatus()==1&&admin.getId()!=draw.getFuAdmin().getId()){
			// write("-1");
			// return null;
			// }else{
			draw.setStatus(1);// 审核中
			draw.setFuAdmin(admin);
			fuDrawMoneyService.save(draw);
			FuUser user = fuUserService.get(draw.getFuUser().getId());
			this.getActionContext().put("user", user);
			this.getActionContext().put("draw", draw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
		// }
	}

	/**
	 * 取消提款审核(后台)
	 * 
	 * @return
	 */
	@Action("noCheckDrawAjax")
	public String noCheckDrawAjax() {
		try {
			FuDrawMoney draw = fuDrawMoneyService.get(id);
			draw.setStatus(0);// 待审核
			draw.setFuAdmin(null);
			fuDrawMoneyService.save(draw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 审核提款（后台）
	 * 
	 * @return
	 */
	@Action("checkDrawAjax")
	public String checkDraw() throws Exception {
		try {
			if (id != null) {
				FuDrawMoney draw = fuDrawMoneyService.get(id);
				draw.setStatus(status);// 0未审核，1审核中，2同意，3拒绝
				draw.setFuAdmin(admin);
				draw.setComment(comment);// 备注
				draw.setCheckTime(new Date());
				if (status != null && status == 2) {// 同意，就不写明细了
					FuUser fuUser = draw.getFuUser();
					fuUser.setDrawMoney((fuUser.getDrawMoney() == null ? BigDecimal.ZERO : fuUser.getDrawMoney()).add(draw.getDrawMoney()));
					fuDrawMoneyService.saveDrawMoney(draw, fuUser, null, null);
				}
				if (status != null && status == 3) {// 拒绝，就要退款到网站余额
					FuUser fuUser = draw.getFuUser();
					fuUser.setAccountBalance(fuUser.getAccountBalance().add(draw.getDrawMoney()));
					fuDrawMoneyService.saveDrawMoney(draw, fuUser, 39, true);
				}
				// 发短信
				String message = URLDecoder.decode(msg, "UTF-8");
				FuSmsLog log = new FuSmsLog();
				log.setFuUser(draw.getFuUser());
				log.setContent(message);
				log.setPrio(1);
				log.setReason("提款审核");
				log.setDestination(draw.getFuUser().getPhone());
				log.setPlanTime(new Date());
				log.setType(1);// 短信
				log.setState(0);
				fuSmsLogService.save(log);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除提款
	 * 
	 * @return
	 */
	@Action("delDrawAjax")
	public String delDraw() {
		try {
			if (id != null) {
				FuDrawMoney draw = fuDrawMoneyService.get(id);
				draw.setState(0);
				fuDrawMoneyService.save(draw);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 提款Excel
	 * 
	 * @return
	 */
	@Action("exportExcel")
	public String exportExcel() {
		try {
			// 获取数据库信息
			FuDrawMoney draw = fuDrawMoneyService.get(id);

			// 直接往response的输出流中写excel
			OutputStream os = this.getHttpServletResponse().getOutputStream();
			// 获取文件名称
			String fileName = System.currentTimeMillis() + ".xls";
			// 下载格式设置
			this.getHttpServletResponse().setContentType("APPLICATION/OCTET-STREAM");
			this.getHttpServletResponse().setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

			// 选择模板文件：
			// excel模板存放位置
			String excelPath = this.getServletContext().getRealPath("/");
			String separator = System.getProperty("file.separator");
			excelPath = excelPath + "uploads" + separator + "attach" + separator + "payMoney_sure.xls";
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

//			WritableCellFormat wcf = ExcelUtil.getWritableCellFormatCellFormat();
			WritableCellFormat noWCF = new WritableCellFormat();
			noWCF.setBorder(Border.ALL, BorderLineStyle.NONE);
			// 选择单元格，写入动态值，根据单元格的不同类型转换成相应类型的单元格：

			// 第2行填表时间
			WritableCellFormat wcf2 = new WritableCellFormat();
			wcf2.setAlignment(Alignment.RIGHT);
			wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);
			wc = ws.getWritableCell(0, 1);
			wc = ExcelUtil.cloneCellWithValue(0, 1, "填表时间：" + (draw.getCheckTime() == null ? "                      " : DateUtil.getStrFromDate(draw.getCheckTime(), "yyyy年MM月dd日")), wcf2);
			ws.addCell(wc);

			// 第56行
			WritableCellFormat wcf5 = new WritableCellFormat();
			wcf5.setBorder(Border.ALL, BorderLineStyle.THIN);
			wcf5.setAlignment(Alignment.LEFT);
			wcf5.setVerticalAlignment(VerticalAlignment.CENTRE);
			wc = ws.getWritableCell(3, 4);
			wc = ExcelUtil.cloneCellWithValue(3, 4, draw.getFuUser().getUserName(), wcf5); // 用户姓名
			ws.addCell(wc);
			wc = ws.getWritableCell(5, 4);
			wc = ExcelUtil.cloneCellWithValue(5, 4, draw.getFuUser().getPhone(), wcf5); // 用户手机号
			ws.addCell(wc);

			// 第7行
			WritableCellFormat wcf7 = new WritableCellFormat();
			wcf7.setBorder(Border.ALL, BorderLineStyle.THIN); // 加四周边框线条
			wcf7.setAlignment(Alignment.LEFT); // 水平居中
			wcf7.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直居中
			wc = ws.getWritableCell(1, 6);
			wc = ExcelUtil.cloneCellWithValue(1, 6, "提款后线上平台余额为：" + RMB.toBigAmt(draw.getFuUser().getAccountBalance().doubleValue()) + "（￥" + StringUtil.getDecimalFormat(draw.getFuUser().getAccountBalance()) + "）", wcf7); // 期货公司
			ws.addCell(wc);

			// 第8行
			WritableCellFormat wcf8 = new WritableCellFormat();
			wcf8.setBorder(Border.ALL, BorderLineStyle.THIN); // 加四周边框线条
			wcf8.setAlignment(Alignment.CENTRE); // 水平居中
			wcf8.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直居中
			wc = ws.getWritableCell(2, 7);
			wc = ExcelUtil.cloneCellWithValue(2, 7, "唐文钊", wcf8);
			ws.addCell(wc);
			wc = ws.getWritableCell(6, 7);
			wc = ExcelUtil.cloneCellWithValue(6, 7, draw.getFuUser().getUserName(), wcf8);
			ws.addCell(wc);

			// 第9行
			WritableCellFormat wcf9 = new WritableCellFormat();
			wcf9.setBorder(Border.ALL, BorderLineStyle.THIN); // 加四周边框线条
			wcf9.setAlignment(Alignment.CENTRE); // 水平居左
			wcf9.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直居中
			wc = ws.getWritableCell(2, 8);
			wc = ExcelUtil.cloneCellWithValue(2, 8, "中国建设银行（北京长河湾支行）", wcf9); // 强平线
			ws.addCell(wc);
			wc = ws.getWritableCell(6, 8);
			wc = ExcelUtil.cloneCellWithValue(6, 8, draw.getFuBankCard().getBankName() + "   " + draw.getFuBankCard().getBankAddress(), wcf9); // 强平线
			ws.addCell(wc);

			// 第10行
			WritableCellFormat wcf10 = new WritableCellFormat();
			wcf10.setBorder(Border.ALL, BorderLineStyle.THIN); // 加四周边框线条
			wcf10.setAlignment(Alignment.CENTRE); // 水平居左
			wcf10.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直居中
			wc = ws.getWritableCell(2, 9);
			wc = ExcelUtil.cloneCellWithValue(2, 9, "6217 0000 1007 7384 314", wcf10); // 强平线
			ws.addCell(wc);
			wc = ws.getWritableCell(6, 9);
			wc = ExcelUtil.cloneCellWithValue(6, 9, draw.getFuBankCard().getCardNumber(), wcf10); // 强平线
			ws.addCell(wc);

			// 第11行
			WritableCellFormat wcf11 = new WritableCellFormat();
			wcf11.setBorder(Border.ALL, BorderLineStyle.THIN); // 加四周边框线条
			wcf11.setAlignment(Alignment.LEFT); // 水平居左
			wcf11.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直居中
			wc = ws.getWritableCell(1, 10);
			wc = ExcelUtil.cloneCellWithValue(1, 10, "付款金额大、小写： " + RMB.toBigAmt(draw.getDrawMoney().doubleValue()) + "（￥" + StringUtil.getDecimalFormat(draw.getDrawMoney()) + "）", wcf11); // 强平线
			ws.addCell(wc);

			// 第12行
			WritableCellFormat wcf12 = new WritableCellFormat();
			wcf12.setBorder(Border.ALL, BorderLineStyle.THIN); // 加四周边框线条
			wcf12.setAlignment(Alignment.RIGHT); // 水平居左
			wcf12.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直居中
			wc = ws.getWritableCell(0, 11);
			wc = ExcelUtil.cloneCellWithValue(0, 11, "经办：" + (draw.getFuAdmin() == null ? "         " : draw.getFuAdmin().getName()) + "          核对：                            审批：                            财务：                            ", wcf12); // 强平线
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
