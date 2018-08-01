package com.hongwei.futures.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hongwei.futures.model.FuStockMoneyDetail;

/**
 * 
 * @description 自动生成 dao
 * 
 * @author 弘威
 */
public interface FuStockMoneyDetailDao extends BaseDao<FuStockMoneyDetail, Long> {
	public FuStockMoneyDetail findMoneyDetailByAccount(Long stockId);

	/**
	 * 根据账号信息id查询该账号的详细信息
	 * 
	 * @param stockId
	 * @author han
	 */
	FuStockMoneyDetail findMoneyDetailByAccountId(Long stockId);

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
	List<FuStockMoneyDetail> findDetailByUserId(Long userId, Integer num, Integer pageSize);

	public List<FuStockMoneyDetail> queryStockMoneyDetail(int current, int pageNum, Map<String, Object> map);

	public Integer countStockMoneyDetail(Map<String, Object> map);

	public List<FuStockMoneyDetail> findDetailByUser(Long userId);

	public List<Object[]> findListByUser(Long userId);

	/**
	 * 根据股票id 查询这只股票的所有详细记录
	 * 
	 * @param stockId
	 *            股票id
	 * @return
	 */
	public List<FuStockMoneyDetail> findByStockId(Long stockId);

	/**
	 * 根据股票id 查询这只股票的收益总和
	 * 
	 * @param stockId
	 *            股票id
	 * @return
	 */
	public BigDecimal getSumProfit(Long stockId);

	/**
	 * 根据用户id和股票账号id 查询 这个股票最新5天的数据
	 * 
	 * @param userId
	 *            用户id
	 * @param stockId
	 *            股票账号id
	 * @return
	 */
	public List<FuStockMoneyDetail> findByUserIdAndStockId(Long userId, Long stockId);

	/**
	 * 根据股票id 查询这只股票的所有详细记录 (分页版本)
	 * 
	 * @param stockId
	 *            股票id
	 * @param detailId
	 *            FuStockMonetDetail 主键
	 * @return
	 */
	public List<FuStockMoneyDetail> findByStockId(Long stock, Long detailId);

	public List<Object[]> findListByDate(Date tradeTime);

	public List<Object[]> findListByTime(Date tradeTime);

}
