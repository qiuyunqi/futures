package com.hongwei.futures.zhongqi;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.hongwei.futures.model.FuProgram;
import com.hongwei.futures.model.FuServer;
import com.hongwei.futures.model.FuUser;

@Service
public interface ZhongqiService {
	/**
	 * 查询可用资金
	 * @param investorId
	 * @return
	 */
	public BigDecimal findWithdrawQuota(FuServer fuServer,Integer investorId);
	
	/**
	 * 查询动态权益
	 * @param investorId
	 * @return
	 */
	public BigDecimal findBalance(FuServer fuServer,Integer investorId);
	
	/**
	 * 查询持仓占用保证金
	 * @param investorId
	 * @return
	 */
	public BigDecimal findCurrMargin(FuServer fuServer,Integer investorId);
	
	/**
	 * 查询风险度
	 * @return
	 */
	public BigDecimal findRisk(FuServer fuServer,Integer investorId);
	
	/**
	 * 根据id查询出入金的处理结果和信息
	 */
	public Map<String, Object> findResultByInvestorPaymentId(FuServer fuServer,Integer id);
	
	/**
	 * 根据id查询增加用户的处理结果和信息
	 */
	public Map<String, Object> findResultByInvestorAddId(FuServer fuServer,Integer id);
	
	/**
	 * 根据id查询调整组规则的处理结果和信息
	 */
	public Map<String, Object> findResultByInvestorTriggerId(FuServer fuServer,Integer id);
	
	/**
	 * 提取利润出金
	 * @param investorId
	 * @param account
	 * @return
	 */
	public Integer drawProfit(FuServer fuServer, FuProgram fuProgram, BigDecimal money);

	/**
	 * 查询可提取的利润
	 * @return
	 */
	public BigDecimal getBenefit(FuServer fuServer,FuProgram fuProgram,BigDecimal oldMatchMoney, BigDecimal oldSafeMoney);

	/**
	 * 方案结算的时候出金
	 * @return
	 */
	public List<Object> programStop(FuServer fuServer,FuProgram fuProgram,BigDecimal matchMoney,BigDecimal drawMoney);
	
	/**
	 * 增配子方案到期结算的操作(先降低风控线，再出实盘资金，再出交易账号用户自己的资金)
	 * @return
	 */
	public List<Object> sonProgramStop(FuServer fuServer,FuProgram fuProgram,FuProgram sonPro,BigDecimal matchMoney,BigDecimal drawMoney);

	/**
	 * 后台管理员审核通过方案
	 * @param fuUser
	 * @param fuProgram
	 * @param fuServer
	 * @return
	 */
	public List<Object> saveProgram(FuUser fuUser,FuProgram fuProgram,FuServer fuServer);
	
	/**
	 * 期货大赛创建账号
	 * @param fuUser
	 * @param fuProgram
	 * @param fuServer
	 * @return
	 */
	public List<Object> addUserProgramGame(FuUser fuUser,FuServer fuServer);
	
	/**
	 * 期指大赛正式开始，初始化资金为100万
	 * @return
	 */
	public Integer gameResetMoney(FuServer fuServer,Integer tradeAccount,BigDecimal addMoney);
	
	/**
	 * 增配子方案审核通过入金
	 * @param fuUser
	 * @param fuProgram
	 * @param fuServer
	 * @return
	 */
	public Integer addPaymentBySonProgram(FuUser fuUser,FuProgram fuProgram,FuProgram sonPro,FuServer fuServer);
	/**
	 * 增配子方案审核通过风控线更新
	 * @param fuUser
	 * @param fuProgram
	 * @param fuServer
	 * @return
	 */
	public Integer addTriggerBySonProgram(FuUser fuUser,FuProgram fuProgram,FuProgram sonPro,FuServer fuServer);
	/**
	 * 减配方案
	 * @param fuUser
	 * @param fuProgram
	 * @param fuServer
	 * @return
	 */
	public List<Object> saveSubProgram(FuUser fuUser,FuProgram fuProgram,FuProgram subPro,FuServer fuServer);
	/**
	 * 前台追加保证金，余额充足直接通过审核并写入众期数据库
	 * @param fuProgram
	 * @param fuServer
	 * @return
	 */
	public Integer savePayment(BigDecimal money,FuProgram fuProgram,FuServer fuServer);
}
