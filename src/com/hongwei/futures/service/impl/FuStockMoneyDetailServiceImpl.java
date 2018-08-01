package com.hongwei.futures.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongwei.futures.dao.FuStockAccountDao;
import com.hongwei.futures.dao.FuStockMoneyDetailDao;
import com.hongwei.futures.dao.FuStockMoneyDetailTempDao;
import com.hongwei.futures.dao.FuStockMoneyInfoDao;
import com.hongwei.futures.dao.FuStockUserAccountDao;
import com.hongwei.futures.dao.FuUserDao;
import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuStockAccount;
import com.hongwei.futures.model.FuStockMoneyDetail;
import com.hongwei.futures.model.FuStockMoneyDetailTemp;
import com.hongwei.futures.model.FuStockMoneyInfo;
import com.hongwei.futures.model.FuStockUserAccount;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuStockMoneyDetailService;
import com.hongwei.futures.util.MoneyDetailUtil;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class FuStockMoneyDetailServiceImpl implements FuStockMoneyDetailService {
	@Autowired
	private FuStockMoneyDetailTempDao fuStockMoneyDetailTempDao;
	@Autowired
	private FuStockMoneyDetailDao fuStockMoneyDetailDao;
	@Autowired
	private FuStockAccountDao fuStockAccountDao;
	@Autowired
	private FuStockMoneyInfoDao fuStockMoneyInfoDao;
	@Autowired
	private FuStockUserAccountDao fuStockUserAccountDao;
	@Autowired
	private FuUserDao fuUserDao;
	@Autowired
	private MoneyDetailUtil moneyDetailUtil;

	// ====================== 基本 C R U D 方法 ===========================
	public FuStockMoneyDetail get(Long id) {
		return fuStockMoneyDetailDao.get(id);
	}

	public void save(FuStockMoneyDetail entity) {
		fuStockMoneyDetailDao.save(entity);
	}

	public void delete(Long id) {
		fuStockMoneyDetailDao.delete(id);
	}

	/**
	 * 根据账号信息id查询该账号的详细信息
	 * 
	 * @param stockId
	 * @author han
	 */
	public FuStockMoneyDetail findDetailByStockId(Long stockId) {
		return fuStockMoneyDetailDao.findMoneyDetailByAccountId(stockId);
	}

	/**
	 * 根据userid 查询stock_money_detail 列表
	 * 
	 * @param userId
	 * @param num
	 *            从那条开始
	 * @param pageSize
	 *            每页的大小
	 * @return
	 * @author han
	 */
	public List<FuStockMoneyDetail> findDetailByUserId(Long userId, Integer num, Integer pageSize) {
		if (num == null || pageSize == null) {
			num = 0;
			pageSize = 100;
		}
		return fuStockMoneyDetailDao.findDetailByUserId(userId, num, pageSize);
	}

	public FuStockMoneyDetail findMoneyDetailByAccount(Long stockId) {
		return fuStockMoneyDetailDao.findMoneyDetailByAccount(stockId);
	}

	public void saveExcel(Sheet sheet) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		int maxBatchNum = fuStockMoneyDetailTempDao.findMaxBatchNumber();
		// 遍历excel文档
		for (int i = 1; i < sheet.getRows(); i++) {
			FuStockMoneyDetailTemp stockMoneyDetailTemp = new FuStockMoneyDetailTemp();
			stockMoneyDetailTemp.setTotalValue(new BigDecimal(sheet.getCell(3, i).getContents()));
			stockMoneyDetailTemp.setTradetime(sdf.parse(sheet.getCell(6, i).getContents()));
			stockMoneyDetailTemp.setPhone(sheet.getCell(7, i).getContents());
			stockMoneyDetailTemp.setCapitalAccount(sheet.getCell(8, i).getContents());
			stockMoneyDetailTemp.setBeginValue(new BigDecimal(sheet.getCell(9, i).getContents()));
			stockMoneyDetailTemp.setNowProfit(new BigDecimal(sheet.getCell(12, i).getContents()));
			stockMoneyDetailTemp.setManageFee(new BigDecimal(sheet.getCell(20, i).getContents()));
			stockMoneyDetailTemp.setRemark(sheet.getCell(22, i).getContents());
			stockMoneyDetailTemp.setBatchNum(maxBatchNum);
			stockMoneyDetailTemp.setFuUser(fuUserDao.findUserByPhone(sheet.getCell(7, i).getContents()));
			stockMoneyDetailTemp.setFuStockAccount(fuStockAccountDao.findStockByCapitalAccount(sheet.getCell(8, i).getContents()));

			stockMoneyDetailTemp.setTotalCash(new BigDecimal(sheet.getCell(4, i).getContents()));
			stockMoneyDetailTemp.setWeekProfit(new BigDecimal(sheet.getCell(13, i).getContents()));
			stockMoneyDetailTemp.setTransaction(sheet.getCell(21, i).getContents());
			stockMoneyDetailTemp.setAyerValue(new BigDecimal(sheet.getCell(10, i).getContents()));
			stockMoneyDetailTemp.setSalesMan(sheet.getCell(5, i).getContents());
			stockMoneyDetailTemp.setDayKnockdown(new BigDecimal(sheet.getCell(15, i).getContents()));
			fuStockMoneyDetailTempDao.save(stockMoneyDetailTemp);
		}
	}

	@Override
	public void saveStockProcess(FuAdmin admin) throws Exception {
		Map<String, Map<String, Object>> excelRows = new HashMap<String, Map<String, Object>>();
		// 数据来源
		List<FuStockMoneyDetailTemp> tempList = fuStockMoneyDetailTempDao.findAll();
		for (FuStockMoneyDetailTemp temp : tempList) {
			// 日期
			Date tradetime = temp.getTradetime();
			// 用户
			FuUser fuUser = temp.getFuUser();
			// 资金账户
			// FuStockAccount stockAccount =
			// fuStockAccountDao.findStockByCapitalAccount(temp.getFuStockAccount().getCapitalAccount());
			FuStockAccount stockAccount = temp.getFuStockAccount();
			// 今日盈利
			BigDecimal nowProfit = temp.getNowProfit();
			// 管理费
			BigDecimal manageFee = temp.getManageFee();
			// 备注
			String remark = temp.getRemark();
			// 总市值
			BigDecimal totalValue = temp.getTotalValue();
			// 初始净值
			BigDecimal beginValue = temp.getBeginValue();

			BigDecimal totalCash = temp.getTotalCash();
			BigDecimal weekProfit = temp.getWeekProfit();
			BigDecimal ayerValue = temp.getAyerValue();
			String transaction = temp.getTransaction();
			String salesMan = temp.getSalesMan();
			BigDecimal dayKnockdown = temp.getDayKnockdown();

			FuStockMoneyDetail moneyDetail = null;
			// 用户和资金账户同时存在
			if (fuUser != null && stockAccount != null) {
				moneyDetail = new FuStockMoneyDetail();
				moneyDetail.setTradeTime(tradetime);
				moneyDetail.setFuUser(fuUser);
				moneyDetail.setFuStockAccount(stockAccount);
				moneyDetail.setNowProfit(nowProfit);
				moneyDetail.setManageFee(manageFee);
				moneyDetail.setPayFee(BigDecimal.ZERO);
				moneyDetail.setCreateTime(new Date());
				moneyDetail.setCreateAdmin(admin);
				moneyDetail.setRemark(remark);
				moneyDetail.setTotalValue(totalValue);
				moneyDetail.setBeginValue(beginValue);

				moneyDetail.setTotalCash(totalCash);
				moneyDetail.setWeekProfit(weekProfit);
				moneyDetail.setTransaction(transaction);
				moneyDetail.setAyerValue(ayerValue);
				moneyDetail.setSalesMan(salesMan);
				moneyDetail.setDayKnockdown(dayKnockdown);
				fuStockMoneyDetailDao.save(moneyDetail);

				// 中间表
				FuStockUserAccount stockUserAccount = fuStockUserAccountDao.findByUserAndStock(fuUser.getId(), stockAccount.getId());
				if (stockUserAccount == null) {
					stockUserAccount = new FuStockUserAccount();
					stockUserAccount.setFuUser(fuUser);
					stockUserAccount.setFuStockAccount(stockAccount);
					stockUserAccount.setProfitInfo(nowProfit);
					stockUserAccount.setMustFeeInfo(manageFee.compareTo(BigDecimal.ZERO) == 1 ? manageFee : BigDecimal.ZERO);
					stockUserAccount.setCreatetime(new Date());
				} else {
					stockUserAccount.setProfitInfo(stockUserAccount.getProfitInfo() == null ? nowProfit : stockUserAccount.getProfitInfo().add(nowProfit));
					if (manageFee.compareTo(BigDecimal.ZERO) == 1) {
						stockUserAccount.setMustFeeInfo(stockUserAccount.getMustFeeInfo() == null ? manageFee : stockUserAccount.getMustFeeInfo().add(manageFee));
					}
					stockUserAccount.setUpdatetime(new Date());
				}
				fuStockUserAccountDao.save(stockUserAccount);

				// 同一个用户合并多个资金账户记录
				String phone = fuUser.getPhone();
				if (manageFee.compareTo(BigDecimal.ZERO) == -1) {
					// 平台赔付明细
					moneyDetailUtil.saveNewFuMoneyDetail(fuUser, null, null, null, 13, BigDecimal.ZERO.subtract(manageFee), fuUser.getAccountBalance().subtract(manageFee), true);
					fuUser.setAccountBalance(fuUser.getAccountBalance().subtract(manageFee));
					fuUserDao.save(fuUser);
					manageFee = BigDecimal.ZERO;
				}
				if (excelRows.get(phone) == null) {
					Map<String, Object> rowMap = new HashMap<String, Object>();
					rowMap.put("profit_info", nowProfit);
					rowMap.put("must_fee_info", manageFee);
					rowMap.put("money_detail", moneyDetail);
					excelRows.put(phone, rowMap);
				} else {
					Map<String, Object> rowMap = excelRows.get(phone);
					rowMap.put("profit_info", new BigDecimal(rowMap.get("profit_info").toString()).add(nowProfit));
					rowMap.put("must_fee_info", new BigDecimal(rowMap.get("must_fee_info").toString()).add(manageFee));
					rowMap.put("money_detail", moneyDetail);
				}
			}
		}
		// 处理主表
		for (Map.Entry<String, Map<String, Object>> entry : excelRows.entrySet()) {
			String phone = entry.getKey();
			Map<String, Object> rowMap = entry.getValue();
			FuUser fuUser = fuUserDao.findUserByPhone(phone);
			BigDecimal profitInfo = new BigDecimal(rowMap.get("profit_info").toString());
			BigDecimal mustFeeInfo = new BigDecimal(rowMap.get("must_fee_info").toString());
			FuStockMoneyInfo moneyInfo = fuStockMoneyInfoDao.findStockMoneyInfoByUserId(fuUser.getId());
			FuStockMoneyDetail moneyDetail = (FuStockMoneyDetail) rowMap.get("money_detail");
			if (moneyInfo == null) {
				moneyInfo = new FuStockMoneyInfo();
				moneyInfo.setFuUser(fuUser);
				moneyInfo.setProfitInfo(profitInfo);
				moneyInfo.setMustFeeInfo(mustFeeInfo);
				moneyInfo.setPayFeeInfo(BigDecimal.ZERO);
				moneyInfo.setNoneFeeInfo(mustFeeInfo);
				moneyInfo.setCreatetime(new Date());
			} else {
				moneyInfo.setProfitInfo(moneyInfo.getProfitInfo().add(profitInfo));
				moneyInfo.setMustFeeInfo(moneyInfo.getMustFeeInfo().add(mustFeeInfo));
				moneyInfo.setNoneFeeInfo(moneyInfo.getNoneFeeInfo().add(mustFeeInfo));
				moneyInfo.setUpdatetime(new Date());
			}
			fuStockMoneyInfoDao.save(moneyInfo);
			fuStockMoneyDetailDao.save(moneyDetail);
		}
	}

	@Override
	public List<FuStockMoneyDetail> queryStockMoneyDetail(int current, int pageNum, Map<String, Object> map) {
		return fuStockMoneyDetailDao.queryStockMoneyDetail(current, pageNum, map);
	}

	@Override
	public Integer countStockMoneyDetail(Map<String, Object> map) {
		return fuStockMoneyDetailDao.countStockMoneyDetail(map);
	}

	@Override
	public List<FuStockMoneyDetail> findDetailByUser(Long userId) {
		return fuStockMoneyDetailDao.findDetailByUser(userId);
	}

	@Override
	public List<Object[]> findListByUser(Long userId) {
		return fuStockMoneyDetailDao.findListByUser(userId);
	}

	// 根据股票id 查询这只股票的所有详细记录
	public List<FuStockMoneyDetail> findByStockId(Long stockId) {
		return fuStockMoneyDetailDao.findByStockId(stockId);
	}

	// 根据股票id 查询这只股票的收益总和
	public BigDecimal getSumProfit(Long stockId) {
		return fuStockMoneyDetailDao.getSumProfit(stockId);
	}

	// 根据用户id和股票账号id 查询 这个股票最新5天的数据
	public List<FuStockMoneyDetail> findByUserIdAndStockId(Long userId, Long stockId) {
		return fuStockMoneyDetailDao.findByUserIdAndStockId(userId, stockId);
	}

	// 根据股票id 查询这只股票的所有详细记录(分页版本)
	public List<FuStockMoneyDetail> findByStockId(Long stockId, Long detailId) {
		return fuStockMoneyDetailDao.findByStockId(stockId, detailId);
	}

	public List<Object[]> findListByDate(Date createTime) {
		return fuStockMoneyDetailDao.findListByDate(createTime);
	}

	public List<Object[]> findListByTime(Date createTime) {
		return fuStockMoneyDetailDao.findListByTime(createTime);
	}

}
