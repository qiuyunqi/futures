package com.hongwei.futures.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.hongwei.futures.model.FuAddMargin;
import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuDrawProfits;
import com.hongwei.futures.model.FuGame;
import com.hongwei.futures.model.FuProgram;
import com.hongwei.futures.model.FuProgramUp;
import com.hongwei.futures.model.FuRate;
import com.hongwei.futures.model.FuServer;
import com.hongwei.futures.model.FuUser;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface FuProgramService {
	
	//====================== 基本 C R U D 方法  ===========================
	public FuProgram get(Long id) ;
	
	public void save(FuProgram entity) ;
	
	public void delete(Long id) ;
	
	public List<FuProgram> findOpenProgramByParams();
	
	/**
	 * 根据方案类型和用户id查找即将进行的和正在进行的方案
	 * @param userId
	 * @param status
	 * @return
	 */
	public List<FuProgram> findToTradeByUser(Long userId, int programType);
	
	public List<FuProgram> findProgramIsAcrossCabin();
	
	public Integer countProgramByUser(Map<String, Object> map);
	
	/**
	 * 查询所有过期的方案
	 * @param map
	 * @return
	 */
	public List<FuProgram> findAllDueProgram();
	/**
	 * 根据方案查找该方案的增配记录
	 */
	public List<FuProgram> findProgramUpByProgram(Long id);
	
	/**
	 * 根据方案查找该方案的有效增配子方案记录
	 */
	public List<FuProgram> findSonProgramByProgram(Long id);

	public List<FuProgram> findProgramByUser(int i, int pageSize,
			Map<String, Object> map);
	/**
	 * 关于方案添加的操作
	 * @param program
	 * @param fuUser
	 * @param ppId 
	 */
	public void saveInfo(FuProgram program, FuUser fuUser, Long ppId);
	/**
	 * 关于方案审核操作
	 * @param program
	 * @param flag
	 */
	public void saveCheck(FuProgram program, int flag, FuAdmin admin, Long serverId);
	/**
	 * 系统所有正在交易的方案
	 * @return
	 */
	public List<FuProgram> findAllTradeProgram();
	/**
	 * 系统所有正在交易的增配子方案
	 * @return
	 */
	public List<FuProgram> findAllTradeSonProgram();
	/**
	 * 系统所有正在交易的(日配/月配)方案
	 * @param map
	 * @return
	 */
	public List<FuProgram> findAllTradeProgram(Map<String, Object> map);
	
	/**
	 * 前台结算方案操作
	 * @param pro
	 */
	public String saveOverProgramByBefore(FuProgram pro);
	/**
	 * 结算主方案操作,传入子方案对象（盘后后台任务开始结算）
	 */
	public String saveOverProgramByTask(FuProgram pro);
	/**
	 * 结算主方案操作,传入子方案对象（后台管理员手动结算）
	 * @param pro
	 */
	public String saveOverProgramByAfter(FuProgram pro,FuAdmin fuAdmin);
	
	/**
	 * 结算到期增配子方案操作（任务）
	 */
	public String saveOverSonProgram(FuProgram pro);
	/**
	 * 线下结算到期增配子方案操作（后台）
	 */
	public String saveOfflineOverSonProgram(FuProgram pro,FuAdmin fuAdmin);
	/**
	 * 线下结算主方案操作,传入子方案对象（后台）
	 */
	public String saveOverProgramOffline(FuProgram pro,FuAdmin fuAdmin);
	
	/**
	 * 追加保证金
	 * @param pro
	 * @param money
	 */
	public void saveAddConfirmMoney(FuProgram pro, BigDecimal money);
	/**
	 * 前台手动续约
	 * @param pro
	 * @param cycleNum
	 * @param money
	 */
	public void saveProgramContinue(FuProgram pro, Integer cycleNum, BigDecimal money,String introduce);
	/**
	 * 日配自动扣除方案周期内下个工作日的管理费
	 */
	public void saveDayManageMoney(FuProgram pro, Integer cycleNum, BigDecimal money);
	/**
	 * 月配自动扣除方案周期内下个月的管理费
	 */
	public void saveMonthManageMoney(FuProgram pro, Integer cycleNum, BigDecimal money);
	/**
	 * 提前利润
	 * @param pro
	 * @param money
	 */
	public String saveDrowMoney(FuProgram pro, BigDecimal money);
	/**
	 * 方案数目统计
	 * @param map
	 * @return
	 */
	public Integer countProgram(Map<String, Object> map);
	/**
	 * 方案分页列表
	 * @param i
	 * @param pageSize
	 * @param map
	 * @return
	 */
	public List<FuProgram> findProgramList(int i, int pageSize,
			Map<String, Object> map);
	
	public Integer countWaitProgram(Map<String, Object> map);

	public List<FuProgram> findWaitProgramList(int i, int pageSize, Map<String, Object> map);
	/**
	 * 根据条件查询方案集合
	 * @param map
	 */
	public List<FuProgram> findProgramByParams(Map<String, Object> map);
	/**
	 * 查用户是否已经参加了某个比赛
	 * @param userId
	 * @param gameId
	 * @return
	 */
	public FuProgram findGameProgram(Long userId, Long gameId);
	/**
	 * 参加期货大赛
	 * @param pro
	 * @param game
	 */
	public void saveGame(FuProgram pro, FuGame game);
	/**
	 * 期货大赛周排名，和月排名
	 * @param gameId 
	 * @param flag
	 * @return
	 */
	public List<FuProgram> findGameOrder(Long gameId, int flag);
	/**
	 * 期货大赛查询
	 * @param map
	 * @return
	 */
	public Integer countGameProgram(Map<String, Object> map);
	/**
	 * 期货大赛查询列表
	 * @param i
	 * @param pageSize
	 * @param map
	 * @return
	 */
	public List<FuProgram> findGameProgramList(int i, int pageSize,
			Map<String, Object> map);
	/**
	 * 查询当前用户的所有结束的方案（状态为5的方案）
	 * @return
	 */
	public List<FuProgram> findOverProgramList(Map<String, Object> map);

	/**
	 * 日配方案出金续约
	 * @param pro
	 * @param u 
	 * @param f
	 */
	public void saveProgramContinueByAccount(FuProgram pro, FuUser u, BigDecimal money);
	/**
	 * 待结束方案数目
	 * @param map
	 * @return
	 */
	public Integer countWaitOverProgram(Map<String, Object> map);
	/**
	 * 待结束方案列表
	 * @param i
	 * @param pageSize
	 * @param map
	 * @return
	 */
	public List<FuProgram> findWaitOverProgramList(int i, int pageSize,
			Map<String, Object> map);
	/**
	 * 线下待结算方案数目
	 * @param map
	 * @return
	 */
	public Integer countOfflineWaitOverProgram(Map<String, Object> map);
	/**
	 * 线下待结算方案列表
	 * @param i
	 * @param pageSize
	 * @param map
	 * @return
	 */
	public List<FuProgram> findOfflineWaitOverProgramList(int i, int pageSize,
			Map<String, Object> map);
	/**
	 * 已删除方案数目
	 * @param map
	 * @return
	 */
	public Integer countDeleteProgram(Map<String, Object> map);
	/**
	 * 已删除方案列表
	 * @param i
	 * @param pageSize
	 * @param map
	 * @return
	 */
	public List<FuProgram> findDeleteProgramList(int i, int pageSize,
			Map<String, Object> map);
	/**
	 * 财务报表数目
	 * @param map 
	 * @return
	 */
	public Integer countTradeInfo(Map<String, Object> map);
	/**
	 * 财务报表分页列表
	 * @param i
	 * @param pageSize
	 * @param map 
	 * @return
	 */
	public List<FuProgram> findTradeInfoList(int i, int pageSize, Map<String, Object> map);
	/**
	 * 财务报表根据条件筛选得到的所有数据
	 * @param map 
	 * @return
	 */
	public List<FuProgram> findTradeInfoList2(Map<String, Object> map);
	/**
	 * 查询所有正在交易的方案
	 * @return
	 */
	public List<FuProgram> findAllTradeInfoList();
	/**
	 * 降级信息
	 * @param u
	 * @param pro
	 */
	public void saveDownInfo(FuUser u, FuProgram pro);
	/**
	 * 查询指定交易账户旧方案的配资总额(未结束的)
	 * 暂时停用
	 * @param tradeAccount
	 * @return
	 */
	public Long findCountMatchMoneyByTrade(Integer tradeAccount);
	/**
	 * 保存方案升级记录信息
	 * @param program
	 * @param programUp
	 * @param fuUser 
	 */
	public void saveProgramUp(FuProgram program, FuProgramUp programUp, FuUser fuUser);
	/**
	 * 方案升级
	 * @param program
	 * @param programUp
	 */
	public void saveProgramUpInfo(FuProgram program, FuProgramUp programUp);
	/**
	 * 自动续约
	 */
	public void saveProgramAutoContinue(FuProgram pro, Integer cycleNum,BigDecimal money, String introduce);
	/**
	 * 根据用户查询方案数目
	 * @param map
	 * @return
	 */
	public Integer countProgramByUser2(Map<String, Object> map);
	/**
	 * 保存减配方案
	 */
	public FuProgram saveSubProgramAjax(FuRate param,FuProgram primaryPro,BigDecimal safeMoney,BigDecimal managerMoney,BigDecimal matchMoney,int num);
	/**
	 * 追加保证金的处理结果任务
	 */
	public void saveAddMarginTask(FuAddMargin addMargin,FuUser fuUser,FuProgram pro);
	/**
	 * 提取利润的处理结果任务
	 */
	public void saveDrawProfitsTask(FuDrawProfits profits,FuUser fuUser,FuProgram pro);
	/**
	 * 结算方案的处理结果任务
	 */
	public void saveCloseProgramTask(FuServer fuServer,FuUser fuUser,FuProgram pro);
	/**
	 * 后台同意开户的处理结果任务
	 */
	public void saveCheckProgramTask(FuUser fuUser,FuProgram program);
	/**
	 * 减配的处理结果任务
	 */
	public void saveCheckSubProgramTask(FuUser fuUser,FuProgram primaryPro,FuProgram subProgram);
	
	/**
	 * 根据日期查询交易中的方案
	 * @param queryDate
	 * @return
	 */
	public List<FuProgram> findProgramsByDate(String queryDate);
	
	/**
	 * 保存期货大赛方案
	 * @param param
	 * @param fuUser
	 */
	public void saveProgramGame(FuRate param,FuUser fuUser);
	
	/**
	 * 查询用户方案
	 * @param userId
	 * @return
	 */
	public List<FuProgram> findToTradeByUser(Long userId);
	/**
	 * 保存已穿仓的方案
	 * @param pro
	 */
	public void saveAcrossCabin(FuProgram pro,BigDecimal money);
	/**
	 * 报名参加 第二期
	 * @param param
	 * @param user
	 */
	public void saveGame(FuRate param, FuUser user, Integer competitionNum);
}

