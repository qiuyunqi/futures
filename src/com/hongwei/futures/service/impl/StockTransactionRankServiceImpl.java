package com.hongwei.futures.service.impl;

import java.util.List;
import java.util.Map;

import jxl.Sheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongwei.futures.dao.FuTransactionDao;
import com.hongwei.futures.dao.StockTransactionRankDao;
import com.hongwei.futures.model.StockTransactionRank;
import com.hongwei.futures.service.StockTransactionRankService;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class StockTransactionRankServiceImpl implements StockTransactionRankService {

	@Autowired
	private StockTransactionRankDao stockTransactionRankDao;

	@Autowired
	private FuTransactionDao fuTransactionDao;

	// ====================== 基本 C R U D 方法 ===========================
	public StockTransactionRank get(Long id) {
		return stockTransactionRankDao.get(id);
	}

	public void save(StockTransactionRank entity) {
		stockTransactionRankDao.save(entity);
	}

	public void delete(Long id) {
		stockTransactionRankDao.delete(id);
	}

	public List<StockTransactionRank> getRank(int start, int pageSize) {
		return stockTransactionRankDao.getRank(start, pageSize);
	}

	@Override
	public List<StockTransactionRank> queryTransRankList(int i, int pageSize, Map<String, Object> map) {
		return stockTransactionRankDao.queryTransRankList(i, pageSize, map);
	}

	@Override
	public Integer countTransRank(Map<String, Object> map) {
		return stockTransactionRankDao.countTransRank(map);
	}

	@Override
	public void saveExcel(Sheet sheet) {
		for (int i = 1; i < sheet.getRows(); i++) {
			StockTransactionRank trans = new StockTransactionRank();
			trans.setSerialNo(new Integer(sheet.getCell(0, i).getContents()));
			trans.setTransactionName(sheet.getCell(1, i).getContents());
			trans.setMonthProfit(sheet.getCell(2, i).getContents());
			trans.setManagerScale(sheet.getCell(3, i).getContents());
			/*
			 * FuTransaction fuTrans = trans.getFuTransaction();
			 * fuTrans.setRating(new Integer(sheet.getCell(4,
			 * i).getContents())); fuTransactionDao.save(fuTrans);
			 */
			stockTransactionRankDao.save(trans);
		}
	}

}
