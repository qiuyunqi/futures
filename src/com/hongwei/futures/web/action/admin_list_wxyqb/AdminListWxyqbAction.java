package com.hongwei.futures.web.action.admin_list_wxyqb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuTransaction;
import com.hongwei.futures.model.StockPublish;
import com.hongwei.futures.model.StockTransactionRank;
import com.hongwei.futures.model.StockYjbRank;
import com.hongwei.futures.service.FuTransactionService;
import com.hongwei.futures.service.StockPublishService;
import com.hongwei.futures.service.StockTransactionRankService;
import com.hongwei.futures.service.StockYjbRankService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminListWxyqbAction extends BaseAction {
	private static final long serialVersionUID = -6261811263359398276L;
	private static final Log logger = LogFactory.getLog(AdminListWxyqbAction.class);

	@Autowired
	private FuTransactionService fuTransactionService;
	@Autowired
	private StockYjbRankService stockYjbRankService;
	@Autowired
	private StockTransactionRankService stockTransactionRankService;
	@Autowired
	private StockPublishService stockPublishService;

	private FuAdmin admin;
	private Integer totalCount;
	private Long id;
	private String code;
	private String stockName;
	private String monthProfit;
	private String removes;
	private String transactionName;
	private String managerScale;
	private Long fuTransId;
	private Integer serialNo;
	private Integer heat;
	private String userName;
	private String name;
	private Integer isVerification;
	private Integer rating;

	// file并不是指前端jsp上传过来的文件本身，而是文件上传过来存放在临时文件夹下面的文件
	private File excelfile;

	// 提交过来的file的名字
	private String excelfileFileName;

	// 提交过来的file的MIME类型
	private String excelfileContentType;

	private String title;
	private String description;
	private Integer isDel;
	private String titles;

	/**
	 * 余券宝龙虎榜后台
	 * 
	 * @return
	 */
	@Action("yqbRank")
	public String yqbRank() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtil.isBlank(code)) {
				map.put("code", code);
			}
			if (!StringUtil.isBlank(stockName)) {
				map.put("stockName", stockName);
			}
			if (!StringUtil.isBlank(transactionName)) {
				map.put("transactionName", transactionName);
			}
			List<StockYjbRank> yqbRankList = stockYjbRankService.queryYqbRankList((this.getPageNo() - 1) * this.getPageSize(), this.getPageSize(), map);
			if (totalCount == null) {
				totalCount = stockYjbRankService.countYqbRank(map);
			}
			this.getActionContext().put("yqbRankList", yqbRankList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 添加余券宝收益记录
	 * 
	 * @return
	 */
	@Action("newYqbRankAjax")
	public String newYqbRankAjax() {
		try {
			if (id != null) {
				StockYjbRank rank = stockYjbRankService.get(id);
				this.getActionContext().put("rank", rank);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 保存余券宝收益记录
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveYqbRankAjax")
	public String saveYqbRankAjax() {
		try {
			StockYjbRank rank = new StockYjbRank();
			if (id != null) {
				rank = stockYjbRankService.get(id);
			}
			if (StringUtil.isBlank(code)) {
				write("-1");
				return null;
			}
			if (StringUtil.isBlank(stockName)) {
				write("-2");
				return null;
			}
			if (StringUtil.isBlank(monthProfit)) {
				write("-3");
				return null;
			}
			if (StringUtil.isBlank(removes)) {
				write("-4");
				return null;
			}
			if (StringUtil.isBlank(transactionName)) {
				write("-5");
				return null;
			}
			rank.setCode(code);
			rank.setStockName(stockName);
			rank.setMonthProfit(monthProfit);
			rank.setRemoves(removes);
			rank.setTransactionName(transactionName);
			rank.setSerialNo(serialNo);
			rank.setHeat(heat);
			stockYjbRankService.save(rank);
			write("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除余券宝收益记录
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("delRank")
	public String delRank() {
		try {
			stockYjbRankService.delete(id);
			write("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 交易员收益榜后台
	 * 
	 * @return
	 */
	@Action("transRank")
	public String transRank() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtil.isBlank(transactionName)) {
				map.put("transactionName", transactionName);
			}
			if (rating != null) {
				map.put("rating", rating);
			}
			List<StockTransactionRank> transRankList = stockTransactionRankService.queryTransRankList((this.getPageNo() - 1) * this.getPageSize(), this.getPageSize(), map);
			if (totalCount == null) {
				totalCount = stockTransactionRankService.countTransRank(map);
			}
			this.getActionContext().put("transRankList", transRankList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 添加交易员收益记录
	 * 
	 * @return
	 */
	@Action("newTransRankAjax")
	public String newTransRankAjax() {
		try {
			if (id != null) {
				StockTransactionRank trans = stockTransactionRankService.get(id);
				this.getActionContext().put("trans", trans);
			}
			List<FuTransaction> transList = fuTransactionService.findAllTrans();
			this.getActionContext().put("transList", transList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 保存交易员收益记录
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveTransRankAjax")
	public String saveTransRankAjax() {
		try {
			StockTransactionRank trans = new StockTransactionRank();
			FuTransaction fuTrans = fuTransactionService.get(fuTransId);
			fuTrans.setRating(rating);
			fuTransactionService.save(fuTrans);
			if (id != null) {
				trans = stockTransactionRankService.get(id);
			}
			if (StringUtil.isBlank(transactionName)) {
				write("-2");
				return null;
			}
			if (StringUtil.isBlank(monthProfit)) {
				write("-3");
				return null;
			}
			if (StringUtil.isBlank(managerScale)) {
				write("-4");
				return null;
			}
			if (rating == null) {
				write("-5");
				return null;
			}
			trans.setTransactionName(transactionName);
			trans.setMonthProfit(monthProfit);
			trans.setManagerScale(managerScale);
			trans.setFuTransaction(fuTrans);
			trans.setSerialNo(serialNo);
			stockTransactionRankService.save(trans);
			write("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除交易员收益记录
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("delTransRank")
	public String delTransRank() {
		try {
			stockTransactionRankService.delete(id);
			write("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 余券宝收益上传
	 * 
	 * @return
	 */
	@Action("yqbRankUpload")
	public String yqbRankUpload() {
		String res = null;
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			String root = ServletActionContext.getServletContext().getRealPath("/uploads");
			res = "success";
			fis = new FileInputStream(excelfile);
			fos = new FileOutputStream(new File(root, excelfileFileName));
			byte[] buffer = new byte[1024];
			int length = 0;
			while ((length = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, length);
			}
			uploadExcel(excelfileFileName, "yqb");
			write(res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(fis, fos);
		}
		return null;
	}

	/**
	 * 交易员收益上传
	 * 
	 * @return
	 */
	@Action("transRankUpload")
	public String transRankUpload() {
		String res = null;
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			String root = ServletActionContext.getServletContext().getRealPath("/uploads");
			res = "success";
			fis = new FileInputStream(excelfile);
			fos = new FileOutputStream(new File(root, excelfileFileName));
			byte[] buffer = new byte[1024];
			int length = 0;
			while ((length = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, length);
			}
			uploadExcel(excelfileFileName, "trans");
			write(res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(fis, fos);
		}
		return null;
	}

	private void close(FileInputStream fis, FileOutputStream fos) {
		try {
			if (fis != null) {
				fis.close();
			}
			if (fos != null) {
				fos.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存excel
	 * 
	 * @param uploadFileName
	 * @throws Exception
	 */
	public void uploadExcel(String uploadFileName, String type) {
		try {
			String directory = "/uploads";
			String targetDirectory = ServletActionContext.getServletContext().getRealPath(directory);
			File target = new File(targetDirectory, uploadFileName);
			FileInputStream fis = new FileInputStream(target);
			Workbook wb = Workbook.getWorkbook(fis);
			Sheet sheet = wb.getSheet(0);
			if (type == "yqb") {
				stockYjbRankService.saveExcel(sheet);
			} else if (type == "trans") {
				stockTransactionRankService.saveExcel(sheet);
			}
			wb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 余劵宝收益龙虎榜数据清空
	 * 
	 * @return
	 */
	@Action("yqbRankReset")
	public String yqbRankReset() {
		try {
			stockYjbRankService.saveYqbRankReset();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 交易员列表
	 * 
	 * @return
	 */
	@Action("stockTransactionList")
	public String stockTransactionList() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtil.isBlank(userName)) {
				map.put("userName", userName);
			}
			if (!StringUtil.isBlank(name)) {
				map.put("name", name);
			}
			if (null != isVerification) {
				map.put("isVerification", isVerification);
			}
			if (null != rating) {
				map.put("rating", rating);
			}
			if (totalCount == null)
				totalCount = fuTransactionService.countTransaction(map);
			List<FuTransaction> list = fuTransactionService.findTransactionByMap((this.getPageNo() - 1) * this.getPageSize(), this.getPageSize(), map);
			this.getActionContext().put("list", list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return SUCCESS;
	}

	/**
	 * 找劵列表
	 * 
	 * @return
	 */
	@Action("findTicketList")
	public String findTicketList() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtil.isBlank(userName)) {
				map.put("userName", userName);
			}
			if (!StringUtil.isBlank(titles)) {
				map.put("titles", titles);
			}
			if (!StringUtil.isBlank(description)) {
				map.put("description", description);
			}
			if (null != isDel) {
				map.put("isDel", isDel);
			}
			if (totalCount == null)
				totalCount = stockPublishService.getCount(map);
			List<StockPublish> list = stockPublishService.findPublishByMap((this.getPageNo() - 1) * this.getPageSize(), this.getPageSize(), map);
			this.getActionContext().put("list", list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return SUCCESS;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIsVerification() {
		return isVerification;
	}

	public void setIsVerification(Integer isVerification) {
		this.isVerification = isVerification;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public FuAdmin getAdmin() {
		return admin;
	}

	public void setAdmin(FuAdmin admin) {
		this.admin = admin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public String getMonthProfit() {
		return monthProfit;
	}

	public void setMonthProfit(String monthProfit) {
		this.monthProfit = monthProfit;
	}

	public String getRemoves() {
		return removes;
	}

	public void setRemoves(String removes) {
		this.removes = removes;
	}

	public String getTransactionName() {
		return transactionName;
	}

	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}

	public String getManagerScale() {
		return managerScale;
	}

	public void setManagerScale(String managerScale) {
		this.managerScale = managerScale;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public String getTitles() {
		return titles;
	}

	public void setTitles(String titles) {
		this.titles = titles;
	}

	public Long getFuTransId() {
		return fuTransId;
	}

	public void setFuTransId(Long fuTransId) {
		this.fuTransId = fuTransId;
	}

	public Integer getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getHeat() {
		return heat;
	}

	public void setHeat(Integer heat) {
		this.heat = heat;
	}
}
