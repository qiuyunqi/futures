package com.hongwei.futures.service.impl;

import java.util.List;
import java.util.Map;

import jxl.Sheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongwei.futures.dao.StockYjbRankDao;
import com.hongwei.futures.model.StockYjbRank;
import com.hongwei.futures.service.StockYjbRankService;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class StockYjbRankServiceImpl implements StockYjbRankService {

	@Autowired
	private StockYjbRankDao stockYjbRankDao;

	// ====================== 基本 C R U D 方法 ===========================
	public StockYjbRank get(Long id) {
		return stockYjbRankDao.get(id);
	}

	public void save(StockYjbRank entity) {
		stockYjbRankDao.save(entity);
	}

	public void delete(Long id) {
		stockYjbRankDao.delete(id);
	}

	public List<StockYjbRank> getRank(int start, int pageSize) {
		return stockYjbRankDao.getRank(start, pageSize);
	}

	// 查询所有的股票名称并组成一个JSON字符串
	public String getJsonList() {
		return stockYjbRankDao.getJsonList();
	}

	// 根据id 查询数据
	public StockYjbRank getById(Long id) {
		return stockYjbRankDao.get(id);
	}

	@Override
	public List<StockYjbRank> queryYqbRankList(int i, int pageSize, Map<String, Object> map) {
		return stockYjbRankDao.queryYqbRankList(i, pageSize, map);
	}

	@Override
	public Integer countYqbRank(Map<String, Object> map) {
		return stockYjbRankDao.countYqbRank(map);
	}

	@Override
	public void saveExcel(Sheet sheet) throws Exception {
		for (int i = 1; i < sheet.getRows(); i++) {
			StockYjbRank yqbRank = new StockYjbRank();
			yqbRank.setSerialNo(new Integer(sheet.getCell(0, i).getContents()));
			yqbRank.setCode(sheet.getCell(1, i).getContents());
			yqbRank.setStockName(sheet.getCell(2, i).getContents());
			yqbRank.setMonthProfit(sheet.getCell(3, i).getContents());
			yqbRank.setHeat(new Integer(sheet.getCell(4, i).getContents()));
			yqbRank.setTransactionName(sheet.getCell(5, i).getContents());
			stockYjbRankDao.save(yqbRank);
		}
	}

	// 模糊查询
	public List<StockYjbRank> getSearcher(String query) {
		return stockYjbRankDao.getSearcher(query);
	}

	public void saveYqbRankReset() {
		stockYjbRankDao.saveYqbRankReset();
	}

}
