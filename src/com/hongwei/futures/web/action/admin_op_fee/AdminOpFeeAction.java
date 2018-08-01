package com.hongwei.futures.web.action.admin_op_fee;

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
import com.hongwei.futures.model.FuDrawProfits;
import com.hongwei.futures.model.FuManageFee;
import com.hongwei.futures.model.FuProgram;
import com.hongwei.futures.model.FuSmsLog;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuDrawProfitsService;
import com.hongwei.futures.service.FuManageFeeService;
import com.hongwei.futures.service.FuSmsLogService;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.util.DateUtil;
import com.hongwei.futures.util.ExcelUtil;
import com.hongwei.futures.util.StringUtil;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminOpFeeAction extends BaseAction {
	private static final long serialVersionUID = -7320906366196939794L;
	
	@Autowired
	private FuManageFeeService fuManageFeeService;
	@Autowired
	private FuSmsLogService fuSmsLogService;
	@Autowired
	private FuUserService fuUserService;
	@Autowired
	private FuDrawProfitsService fuDrawProfitsService;

	private FuAdmin admin;
	private Long adminId;
	private Integer totalCount;
	private String comment;
	private Long id;
	private String msg;
	private Integer state;
	private BigDecimal money;

	/**
	 * 管理费导出Excel
	 * 
	 * @return
	 */
	@Action("exportExcel")
	public String exportExcel() {
		try {
			// 获取数据库管理费信息
			FuManageFee fee = fuManageFeeService.get(id);

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
					+ separator + "in_money.xls";
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
			wc = ExcelUtil.cloneCellWithValue(
					0,
					1,
					"填表时间：  "
							+ DateUtil.getStrFromDate(fee.getPayTime(),
									"yyyy年MM月dd日"), wcf2); // 方案提交时间
			ws.addCell(wc);

			// 第5行
			WritableCellFormat wcf5 = new WritableCellFormat();
			wcf5.setBorder(Border.ALL, BorderLineStyle.THIN);
			wcf5.setAlignment(Alignment.CENTRE);
			wcf5.setVerticalAlignment(VerticalAlignment.CENTRE);
			wc = ws.getWritableCell(3, 4);
			wc = ExcelUtil.cloneCellWithValue(3, 4, fee.getFuUser()
					.getUserName(), wcf5); // 用户姓名
			ws.addCell(wc);
			wc = ws.getWritableCell(4, 4);
			String genderName = "";
			if (null != fee.getFuUser().getGender()
					&& 1 == fee.getFuUser().getGender()) {
				genderName = "男";
			}
			if (null != fee.getFuUser().getGender()
					&& 0 == fee.getFuUser().getGender()) {
				genderName = "女";
			}
			wc = ExcelUtil.cloneCellWithValue(4, 4, "性别：" + genderName, wcf5); // 用户性别
			ws.addCell(wc);
			// 配资比例
			wc = ws.getWritableCell(5, 4);
			wc = ExcelUtil.cloneCellWithValue(
					5,
					4,
					"配资比例："
							+ fee.getFuProgram().getSafeMoney()
									.divide(new BigDecimal(10000))
							+ ":"
							+ fee.getFuProgram().getMatchMoney()
									.divide(new BigDecimal(10000)) + "万", wcf5);
			ws.addCell(wc);
			// 分账户号
			wc = ws.getWritableCell(7, 4);
			wc = ExcelUtil.cloneCellWithValue(7, 4, fee.getFuProgram()
					.getTradeAccount() + "（ 密码：             ）", wcf5); // 分账户号
			ws.addCell(wc);

			// 第6行
			WritableCellFormat wcf6 = new WritableCellFormat();
			wcf6.setBorder(Border.ALL, BorderLineStyle.THIN); // 加四周边框线条
			wcf6.setAlignment(Alignment.CENTRE); // 水平居中
			wcf6.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直居中
			wc = ws.getWritableCell(3, 5);
			wc = ExcelUtil.cloneCellWithValue(3, 5, fee.getFuProgram()
					.getFuServer().getServerRealName(), wcf6); // 期货公司
			ws.addCell(wc);
			// wc = ws.getWritableCell(5, 5);
			// wc = ExcelUtil.cloneCellWithValue(5, 5,
			// fee.getFuProgram().getFuUser().getUserName(), wcf6); //开户姓名
			// ws.addCell(wc);
			wc = ws.getWritableCell(7, 5);
			wc = ExcelUtil.cloneCellWithValue(7, 5, "", wcf6); // 主账户号
			ws.addCell(wc);

			// 第9行
			WritableCellFormat wcf9 = new WritableCellFormat();
			wcf9.setBorder(Border.ALL, BorderLineStyle.THIN); // 加四周边框线条
			wcf9.setAlignment(Alignment.LEFT); // 水平居中
			wcf9.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直居中
			wc = ws.getWritableCell(1, 8);
			String beginDate = DateUtil.getStrFromDate(fee.getBeginTime(),
					"yyyy年MM月dd日");
			String endDate = DateUtil.getStrFromDate(fee.getEndTime(),
					"yyyy年MM月dd日");
			BigDecimal money = new BigDecimal(0);
			if (null != fee.getMoney()) {
				money = fee.getMoney();
			}
			wc = ExcelUtil.cloneCellWithValue(1, 8, "收取客户利息 （人民币  "
					+ StringUtil.getDecimalFormat(money) + "元，日期区间      "
					+ beginDate + "--" + endDate + "）", wcf9); // 客户ID
			ws.addCell(wc);

			// 第10行
			WritableCellFormat wcf10 = new WritableCellFormat();
			wcf10.setBorder(Border.ALL, BorderLineStyle.THIN); // 加四周边框线条
			wcf10.setAlignment(Alignment.RIGHT); // 水平居左
			wcf10.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直居中
			wc = ws.getWritableCell(0, 9);
			String admin_name = "";
			if (null != fee.getFuProgram().getFuAdmin()) {
				admin_name = fee.getFuProgram().getFuAdmin().getName();
			}
			String checkTime = "";
			if (null != fee.getCheckTime()) {
				checkTime = DateUtil.getStrFromDate(fee.getCheckTime(),
						"yyyy年MM月dd日");
			}
			wc = ExcelUtil.cloneCellWithValue(0, 9, "开户经办： " + admin_name
					+ "     开户核对：                  日期： " + checkTime, wcf10); // 强平线
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
	 * 审核管理费页面（后台）
	 * 
	 * @return
	 */
	@Action("checkFeeAjax")
	public String checkFeeAjax() {
		try {
			FuManageFee fee = fuManageFeeService.get(id);
			fee.setState(1);// 审核中
			fee.setFuAdmin(admin);
			fuManageFeeService.save(fee);
			FuUser user = fuUserService.get(fee.getFuUser().getId());
			this.getActionContext().put("fee", fee);
			this.getActionContext().put("user", user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 取消管理费审核(后台)
	 * 
	 * @return
	 */
	@Action("noCheckFeeAjax")
	public String noCheckFeeAjax() {
		try {
			FuManageFee fee = fuManageFeeService.get(id);
			fee.setState(0);// 待审核
			fee.setFuAdmin(null);
			fuManageFeeService.save(fee);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 保存管理费审核结果
	 * 
	 * @return
	 */
	@Action("saveFeeAjax")
	public String saveFeeAjax() throws Exception {
		try {
			FuManageFee fee = fuManageFeeService.get(id);
			FuProgram pro = fee.getFuProgram();
			FuUser fuUser = fee.getFuUser();
			if (state == null) {
				write("-2");
				return null;
			} else if (state == 2
					&& fuUser.getAccountBalance().compareTo(money) == -1) {// 同意状态才判断用户余额是否充足
				// 产生出金申请
				FuDrawProfits profits = new FuDrawProfits();
				profits.setFuProgram(pro);
				profits.setFuUser(fuUser);
				profits.setMoney(fee.getMoney());
				profits.setRemark("账户余额不足出金续交管理费");
				profits.setState(0);// 待审核
				profits.setTime(new Date());
				profits.setType(0);
				fuDrawProfitsService.save(profits);
				write("-3");
				return null;
			} else if (fee.getState() == 2 || fee.getState() == 3) {
				write("-4");
				return null;
			} else {
				fee.setState(state);
				fee.setCheckTime(new Date());
				fee.setFuAdmin(admin);
				fee.setComment(comment);
				if (state == 2) { // 同意
					fuManageFeeService.saveManageMoney(fee);
				} else if (state == 3) { // 拒绝
					fuManageFeeService.save(fee);
					// 发送短信
					String message = URLDecoder.decode(msg, "UTF-8");
					// 保存短信信息到数据库日志表
					FuSmsLog log = new FuSmsLog();
					log.setFuUser(fee.getFuUser());
					log.setContent(message);
					log.setPrio(1);
					log.setReason("扣除管理费");
					log.setDestination(fee.getFuUser().getPhone());
					log.setPlanTime(new Date());
					log.setType(1);// 短信
					log.setState(0);
					fuSmsLogService.save(log);
				}
				return null;
			}
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

}