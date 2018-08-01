package com.hongwei.futures.web.action.admin_op_recharge;

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
import com.hongwei.futures.model.FuParameter;
import com.hongwei.futures.model.FuRecharge;
import com.hongwei.futures.model.FuSmsLog;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuParameterService;
import com.hongwei.futures.service.FuRechargeService;
import com.hongwei.futures.service.FuSmsLogService;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.util.DateUtil;
import com.hongwei.futures.util.ExcelUtil;
import com.hongwei.futures.util.RMB;
import com.hongwei.futures.util.StringUtil;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class RechargeAction extends BaseAction {
	private static final long serialVersionUID = 1559702441910102120L;
	@Autowired
	private FuRechargeService fuRechargeService;
	@Autowired
	private FuSmsLogService fuSmsLogService;
	@Autowired
	private FuUserService fuUserService;
	@Autowired
	private FuUserService userService;
	@Autowired
	private FuParameterService fuParameterService;

	private FuAdmin admin;
	private Long adminId;
	private Long id;
	private String money;
	private String rechargeBank;
	private String rechargeAccount;
	private String accountName;
	private String msg;
	private Integer rechargeStatus;
	private Integer type;
	private String checkRemark;

	/**
	 * 审核充值页面（后台）
	 */
	@Action("checkRechargeAjax")
	public String checkRechargeAjax() {
		try {
			FuRecharge recharge = fuRechargeService.get(id);
			recharge.setRechargeStatus(1);// 审核中
			recharge.setFuAdmin(admin);
			fuRechargeService.save(recharge);
			FuUser user = fuUserService.get(recharge.getFuUser().getId());
			this.getActionContext().put("recharge", recharge);
			this.getActionContext().put("user", user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 取消充值审核(后台)
	 * 
	 * @return
	 */
	@Action("noCheckRechargeAjax")
	public String noCheckRechargeAjax() {
		try {
			FuRecharge recharge = fuRechargeService.get(id);
			recharge.setRechargeStatus(0);// 待审核
			recharge.setFuAdmin(null);
			fuRechargeService.save(recharge);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 保存充值审核结果
	 * 
	 * @return
	 */
	@Action("saveRechargeAjaxTwo")
	public String saveRechargeAjaxTwo() {
		try {
			FuRecharge recharge = fuRechargeService.get(id);
			if (rechargeStatus == null) {
				write("-2");
				return null;
			}
			recharge.setRechargeStatus(rechargeStatus);// 0待审核，1审核中，2同意，3拒绝
			recharge.setFuAdmin(admin);
			recharge.setCheckTime(new Date());
			if (rechargeStatus == 2) {// 同意
				FuUser user = recharge.getFuUser();
				// 充值总额
				user.setRechargeMoney((user.getRechargeMoney() == null ? BigDecimal.ZERO : user.getRechargeMoney()).add(recharge.getRechargeMoney()));
				user.setAccountBalance(user.getAccountBalance().add(recharge.getRechargeMoney()));
				FuParameter params = fuParameterService.findParameter();
				if (type != 3) {// 支付宝没有手续费不返点劵
					user.setIntegral((user.getIntegral() == null ? new BigDecimal(0.00) : user.getIntegral()).add(recharge.getRechargeMoney().multiply(params.getPayPoundage())));
				}
				fuRechargeService.saveRecharge(recharge, user);
			}
			if (rechargeStatus == 3) {// 拒绝
				fuRechargeService.save(recharge);
			}
			// 发短信
			String message = URLDecoder.decode(msg, "UTF-8");
			FuSmsLog log = new FuSmsLog();
			log.setFuUser(recharge.getFuUser());
			log.setContent(message);
			log.setPrio(1);
			log.setReason("充值审核");
			log.setDestination(recharge.getFuUser().getPhone());
			log.setPlanTime(new Date());
			log.setType(1);// 短信
			log.setState(0);
			fuSmsLogService.save(log);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除充值记录
	 * 
	 * @return
	 */
	@Action("delRechargeAjax")
	public String delRecharge() {
		try {
			if (id != null) {
				FuRecharge recharge = fuRechargeService.get(id);
				recharge.setState(0);
				fuRechargeService.save(recharge);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 新增充值记录
	 * 
	 * @return
	 */
	@Action("newRechargeAjax")
	public String newRecharge() {
		return SUCCESS;
	}

	/**
	 * 后台人工添加充值
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveRechargeAjax")
	public String saveRechargeAjax() {
		try {
			FuRecharge recharge = new FuRecharge();
			if (StringUtil.isBlank(accountName)) {
				write("-2");
				return null;
			}
			FuUser user = userService.findUserByAccount(accountName);
			if (user == null) {
				write("-3");
				return null;
			}
			if (StringUtil.isBlank(money)) {
				write("-4");
				return null;
			}
			if (money == null) {
				write("-4");
				return null;
			}
			String drawM = "";
			if (money.contains(",")) {
				money = money.replace(",", "");
			}
			if (money.contains("，")) {
				money = money.replace("，", "");
			}
			drawM = money;
			if (money.contains(".")) {
				money = money.replace(".", "");
			}
			// if(!StringUtil.isNumeric(money)){
			// write("-5");
			// return null;
			// }
			recharge.setFuUser(user);
			recharge.setType(4);
			recharge.setOrderNum(String.valueOf((int) ((Math.random() * 1000000 + 100000))));// 随机产生订单号
			recharge.setRechargeAccount(rechargeAccount);
			recharge.setRechargeBank(rechargeBank);
			recharge.setRechargeMoney(new BigDecimal(drawM));
			recharge.setRechargeStatus(2);
			recharge.setRechargeTime(new Date());
			recharge.setFuAdmin(admin);
			recharge.setCheckTime(new Date());
			recharge.setState(1);
			recharge.setCheckRemark(checkRemark);
			// 充值总额
			user.setRechargeMoney((user.getRechargeMoney() == null ? BigDecimal.ZERO : user.getRechargeMoney()).add(recharge.getRechargeMoney()));
			user.setAccountBalance(user.getAccountBalance().add(recharge.getRechargeMoney()));
			fuRechargeService.saveRecharge(recharge, user);
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
			FuRecharge recharge = fuRechargeService.get(id);

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
			excelPath = excelPath + "uploads" + separator + "attach" + separator + "getMoney_sure.xls";
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
			wc = ExcelUtil.cloneCellWithValue(0, 1, "填表时间：" + (recharge.getCheckTime() == null ? "                      " : DateUtil.getStrFromDate(recharge.getCheckTime(), "yyyy年MM月dd日")), wcf2);
			ws.addCell(wc);

			// 第56行
			WritableCellFormat wcf5 = new WritableCellFormat();
			wcf5.setBorder(Border.ALL, BorderLineStyle.THIN);
			wcf5.setAlignment(Alignment.LEFT);
			wcf5.setVerticalAlignment(VerticalAlignment.CENTRE);
			wc = ws.getWritableCell(3, 4);
			wc = ExcelUtil.cloneCellWithValue(3, 4, recharge.getFuUser().getUserName(), wcf5); // 用户姓名
			ws.addCell(wc);

			// 第7行
			WritableCellFormat wcf7 = new WritableCellFormat();
			wcf7.setBorder(Border.ALL, BorderLineStyle.THIN); // 加四周边框线条
			wcf7.setAlignment(Alignment.CENTRE); // 水平居中
			wcf7.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直居中
			wc = ws.getWritableCell(2, 6);
			String str1;
			if (recharge.getType() == 1) {
				str1 = "武汉坤州大德投资管理有限公司";
			} else if (recharge.getProceedsType() != null && recharge.getProceedsType() == 1) {
				str1 = "武汉坤州大德投资管理有限公司";
			} else {
				str1 = "唐文钊";
			}
			wc = ExcelUtil.cloneCellWithValue(2, 6, str1, wcf7);
			ws.addCell(wc);
			wc = ws.getWritableCell(6, 6);
			wc = ExcelUtil.cloneCellWithValue(6, 6, recharge.getFuUser().getUserName(), wcf7);
			ws.addCell(wc);

			// 第8行
			WritableCellFormat wcf8 = new WritableCellFormat();
			wcf8.setBorder(Border.ALL, BorderLineStyle.THIN); // 加四周边框线条
			wcf8.setAlignment(Alignment.CENTRE); // 水平居左
			wcf8.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直居中
			wc = ws.getWritableCell(2, 7);
			String str2;
			if (recharge.getType() == 1) {
				str2 = "银联";
			} else if (recharge.getProceedsType() != null && recharge.getProceedsType() == 1) {
				str2 = "小合建行";
			} else {
				str2 = "中国建设银行（北京长河湾支行）";
			}
			wc = ExcelUtil.cloneCellWithValue(2, 7, str2, wcf8); // 强平线
			ws.addCell(wc);
			wc = ws.getWritableCell(6, 7);
			wc = ExcelUtil.cloneCellWithValue(6, 7, recharge.getRechargeBank(), wcf8); // 强平线
			ws.addCell(wc);

			// 第9行
			WritableCellFormat wcf9 = new WritableCellFormat();
			wcf9.setBorder(Border.ALL, BorderLineStyle.THIN); // 加四周边框线条
			wcf9.setAlignment(Alignment.CENTRE); // 水平居左
			wcf9.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直居中
			wc = ws.getWritableCell(2, 8);
			String str3;
			if (recharge.getType() == 1) {
				str3 = "";
			} else if (recharge.getProceedsType() != null && recharge.getProceedsType() == 1) {
				str3 = "3100 1587 6150 5003 2541";
			} else {
				str3 = "6217 0000 1007 7384 314";
			}
			wc = ExcelUtil.cloneCellWithValue(2, 8, str3, wcf9); // 强平线
			ws.addCell(wc);
			wc = ws.getWritableCell(6, 8);
			wc = ExcelUtil.cloneCellWithValue(6, 8, recharge.getRechargeAccount(), wcf9); // 强平线
			ws.addCell(wc);

			// 第10行
			WritableCellFormat wcf10 = new WritableCellFormat();
			wcf10.setBorder(Border.ALL, BorderLineStyle.THIN); // 加四周边框线条
			wcf10.setAlignment(Alignment.LEFT); // 水平居左
			wcf10.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直居中
			wc = ws.getWritableCell(1, 9);
			wc = ExcelUtil.cloneCellWithValue(1, 9, "金额大写： " + RMB.toBigAmt(recharge.getRechargeMoney().doubleValue()) + "（￥" + StringUtil.getDecimalFormat(recharge.getRechargeMoney()) + "）", wcf10); // 强平线
			ws.addCell(wc);

			// 第11行
			WritableCellFormat wcf11 = new WritableCellFormat();
			wcf11.setBorder(Border.ALL, BorderLineStyle.THIN); // 加四周边框线条
			wcf11.setAlignment(Alignment.RIGHT); // 水平居左
			wcf11.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直居中
			wc = ws.getWritableCell(0, 10);
			wc = ExcelUtil.cloneCellWithValue(0, 10, "权属人签字：                   经办：" + (recharge.getFuAdmin() == null ? "         " : recharge.getFuAdmin().getName()) + "          核对：                            日期：" + (recharge.getCheckTime() == null ? "                      " : DateUtil.getStrFromDate(recharge.getCheckTime(), "yyyy年MM月dd日")), wcf11); // 强平线
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

	public String getRechargeBank() {
		return rechargeBank;
	}

	public void setRechargeBank(String rechargeBank) {
		this.rechargeBank = rechargeBank;
	}

	public String getRechargeAccount() {
		return rechargeAccount;
	}

	public void setRechargeAccount(String rechargeAccount) {
		this.rechargeAccount = rechargeAccount;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getRechargeStatus() {
		return rechargeStatus;
	}

	public void setRechargeStatus(Integer rechargeStatus) {
		this.rechargeStatus = rechargeStatus;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCheckRemark() {
		return checkRemark;
	}

	public void setCheckRemark(String checkRemark) {
		this.checkRemark = checkRemark;
	}

}
