package com.hongwei.futures.web.action.admin_list_settlement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.HhrStat;
import com.hongwei.futures.model.HhrStatTemp;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.service.HhrStatDetailService;
import com.hongwei.futures.service.HhrStatService;
import com.hongwei.futures.service.HhrStatTempService;
import com.hongwei.futures.web.action.BaseAction;

/* @author 熊枫
 * 2015-05-05
 * */
@ParentPackage("admin")
public class AdminListSettlementAction extends BaseAction {
	private static final long serialVersionUID = 7497699043609152764L;

	@Autowired
	private FuUserService fuUserService;

	@Autowired
	private HhrStatService hhrStatService;

	@Autowired
	private HhrStatDetailService hhrStatDetailService;

	@Autowired
	private HhrStatTempService hhrStatTempService;

	private FuAdmin admin;

	// file并不是指前端jsp上传过来的文件本身，而是文件上传过来存放在临时文件夹下面的文件
	private File excelfile;

	// 提交过来的file的名字
	private String excelfileFileName;

	// 提交过来的file的MIME类型
	private String excelfileContentType;

	// 用户名
	private String accountName;

	// 真实姓名
	private String userName;

	// 总记录数
	private Integer totalCount;

	private Date time1;// 发起时间
	private Date time2;

	// 批次号
	private String batchNum;

	/**
	 * 结算处理
	 * 
	 * @return
	 */
	@Action("settlementList")
	public String settlementList() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtil.isBlank(batchNum)) {
				map.put("batchNum", Integer.valueOf(batchNum));
			}
			List<HhrStatTemp> batchList = hhrStatTempService
					.queryHhrStatTempList(
							(this.getPageNo() - 1) * this.getPageSize(),
							this.getPageSize(), map);
			if (totalCount == null) {
				totalCount = hhrStatTempService.countHhrStatTemp(map);
			}
			this.getActionContext().put("batchList", batchList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 结算查询
	 * 
	 * @return
	 */
	@Action("settlementQuery")
	public String settlementQuery() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtil.isBlank(accountName)) {
				map.put("accountName", accountName);
			}
			if (!StringUtil.isBlank(userName)) {
				map.put("userName", userName);
			}
			if (time1 != null) {
				map.put("time1", time1);
			}
			if (time2 != null) {
				map.put("time2", time2);
			}
			List<HhrStat> hhrStatList = hhrStatService.queryHhrStatList(
					(this.getPageNo() - 1) * this.getPageSize(),
					this.getPageSize(), map);
			if (totalCount == null) {
				totalCount = hhrStatService.countHhrStat(map);
			}
			this.getActionContext().put("hhrStatList", hhrStatList);
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
			if (!StringUtil.isBlank(batchNum)) {
				hhrStatTempService.deleteByBatchId(batchNum);
				write("批次删除成功！");
			} else {
				write("请输入要删除的批次号！");
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
		String root = ServletActionContext.getServletContext().getRealPath(
				"/uploads");
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(excelfile);
			fos = new FileOutputStream(new File(root, excelfileFileName));
			byte[] buffer = new byte[1024];
			int length = 0;
			while ((length = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, length);
			}
			// 将excel中的每行记录保存到hhr_stat_temp中供查看确认
			uploadExcel(excelfileFileName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(fis, fos);
		}
		return null;
	}

	/**
	 * 结算处理
	 * 
	 * @return
	 */
	@Action("doSettlement")
	public String doSettlement() {
		try {
			List<Map<String, Object>> tempList = hhrStatTempService
					.findAllTemps();
			for (int i = 0; i < tempList.size(); i++) {
				Long userId = Long.valueOf(tempList.get(i).get("user_id")
						.toString());
				BigDecimal money = new BigDecimal(tempList.get(i).get("money")
						.toString());
				hhrStatService.updateHhrIncome(userId, money, 2, null);
			}
			// 结算完成后, 清空hhr_stat_temp表
			hhrStatTempService.deleteByBatchId(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 关闭文件输入输出流
	 */
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
	 * 从Excel文件中读取数据, 将excel中的每行记录保存到hhr_stat_temp中供查看确认
	 * 
	 * @param uploadFileName
	 */
	public void uploadExcel(String uploadFileName) {
		String directory = "/uploads";
		String targetDirectory = ServletActionContext.getServletContext()
				.getRealPath(directory);
		File target = new File(targetDirectory, uploadFileName);
		try {
			FileInputStream fis = new FileInputStream(target);
			try {
				Workbook wb = Workbook.getWorkbook(fis);
				Sheet sheet = wb.getSheet(0);
				int maxBatch = hhrStatTempService.findMaxBatchNumber();
				for (int i = 1; i < sheet.getRows(); i++) {
					Long userId = Long.valueOf(sheet.getCell(0, i)
							.getContents());
					Double money = Double.valueOf(sheet.getCell(2, i)
							.getContents());
					FuUser fuUser = fuUserService.get(userId);
					HhrStatTemp hhrStatTemp = new HhrStatTemp();
					hhrStatTemp.setFuUser(fuUser);
					hhrStatTemp.setMoney(new BigDecimal(money * 10000));
					hhrStatTemp.setCreateDate(new Date());
					hhrStatTemp.setBatchNum(maxBatch);
					hhrStatTempService.save(hhrStatTemp);
				}
				wb.close();
			} catch (BiffException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public FuAdmin getAdmin() {
		return admin;
	}

	public void setAdmin(FuAdmin admin) {
		this.admin = admin;
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

	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}
}
