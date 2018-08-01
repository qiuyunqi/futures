package com.hongwei.futures.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hongwei.futures.model.FuPromote;
import com.hongwei.futures.model.FuSmsLog;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.FuUserBak;
import com.hongwei.futures.model.HhrStat;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public interface FuUserService {

	// ====================== 基本 C R U D 方法 ===========================
	public FuUser get(Long id);

	public void save(FuUser entity);

	public void saveRate(FuUser entity);

	public void delete(Long id);

	/**
	 * 根据账号查找用户
	 * 
	 * @param accountName
	 * @return
	 */
	public FuUser findUserByUserName(String userName);

	/**
	 * 根据昵称查找用户
	 * 
	 * @param nickName
	 * @return
	 */
	public FuUser findUserByNickName(String nickName);

	/**
	 * 根据token查用户登陆情况
	 * 
	 * @param token
	 * @return
	 */
	public FuUser findLoginByToken(String token);

	/**
	 * 根据电话号码查找用户
	 * 
	 * @param phone
	 * @return
	 */
	public FuUser findUserByPhone(String phone);

	/**
	 * 查询邮箱是否被占用了
	 * 
	 * @param email
	 * @return
	 */
	public FuUser findUserByEmail(String email);

	/**
	 * 用户名/手机号/邮箱都可以登录
	 * 
	 * @param accountName
	 * @return
	 */
	public FuUser findUserByAccount(String accountName);

	/**
	 * 统计用户数目
	 * 
	 * @param map
	 * @return
	 */
	public Integer countUser(Map<String, Object> map);

	/**
	 * 用户分页列表
	 * 
	 * @param i
	 * @param pageSize
	 * @param map
	 * @return
	 */
	public List<FuUser> findUserList(int i, int pageSize, Map<String, Object> map);

	/**
	 * 身份证验证
	 * 
	 * @param map
	 * @return
	 */
	public Integer countCheckUser(Map<String, Object> map);

	/**
	 * 待身份证验证人数
	 * 
	 * @param map
	 * @return
	 */
	public Integer countCheckUser2(Map<String, Object> map);

	/**
	 * 待身份证验证列表
	 * 
	 * @param j
	 * @param i
	 * @param map
	 * @return
	 */
	public List<FuUser> findCheckUserList(int i, int j, Map<String, Object> map);

	/**
	 * 邮箱绑定
	 * 
	 * @param fuUser
	 */
	public void saveCheckEmail(FuUser fuUser);

	/**
	 * 找回密码相关
	 * 
	 * @param u
	 * @param log
	 */
	public void saveReg(FuUser user, FuSmsLog log);

	/**
	 * 保存推广注册用户、更新推广用户的推广人数、新增一条推广记录
	 * 
	 * @param
	 * @return
	 */
	public void savePromoteUser(FuUser FuUserByPromote, FuUser FuUserByPromoted, FuPromote promote);

	/**
	 * 累计投资总额
	 * 
	 * @return
	 */
	public BigDecimal countTotalMoney();

	/**
	 * 累计注册总人
	 * 
	 * @return
	 */
	public int countRegister();

	/**
	 * 根据电话号码查找注册时的用户
	 * 
	 * @param phone
	 * @return
	 */
	public FuUser findUserByRegPhone(String phone);

	/**
	 * 根据帐号查找注册时的用户
	 * 
	 * @param accountName
	 * @return
	 */
	public FuUser findUserByRegAccountName(String accountName);

	/**
	 * 根据邀请码查找注册时的用户
	 * 
	 * @return
	 */
	public FuUser findUserByRegInvitationcode(String invitation_code);

	/**
	 * 根据map参数得到用户数据
	 * 
	 * @param map
	 * @return
	 */
	public Object[] findUserDataByMap(Map<String, Object> map);

	/**
	 * 根据用户id查找用户
	 * 
	 * @return
	 */
	public FuUser findFuUserById(Long userId);

	/**
	 * 查询所有邀请码
	 */
	public Integer countInvitationCode(String code);

	/**
	 * 判断用户是否已实名认证
	 * 
	 * @param userId
	 * @return
	 */
	public List<FuUser> findListByUser(Long userId);

	/**
	 * 昵称重复的用户
	 * 
	 * @param nickName
	 * @return
	 */
	public List<FuUser> findSameNickNameUser(String nickName);

	/**
	 * 根据上层id查找用户
	 * 
	 * @param userId
	 * @return
	 */
	public List<FuUser> findListByParentId(Long userId);

	/**
	 * 返回合伙人树结构数据
	 * 
	 * @return
	 */
	public String findUserTree(Long id);

	/**
	 * 查询所有用户
	 * 
	 * @return
	 */
	public List<FuUser> findAllUsers();

	/**
	 * 新用户注册
	 * 
	 * @param phone
	 *            手机号码
	 * @param password
	 *            密码
	 * @param nickName
	 *            昵称
	 * @param phoneCode
	 *            验证码
	 * @param invitationCode
	 *            邀请码
	 * @param regType
	 *            注册来源 1网站注册，2安卓手机注册，3苹果手机注册 4:微信注册
	 * @param avatar
	 *            用户头像
	 * @return
	 */
	public String saveUser(String phone, String password, String nickName, String phoneCode, String invitationCode, int regType, String avatar);

	/**
	 * 更新合伙人数量
	 * 
	 * @return
	 */
	public void updatePartnerNum(HhrStat stat);

	/**
	 * 根据微信号查找用户
	 * 
	 * @param weixinCode
	 * @return
	 */
	public FuUser findUserByWeixinCode(String weixinCode);

	/**
	 * 根据身份证号查找用户
	 * 
	 * @param cardNumber
	 * @return
	 */
	public FuUser findUserByCardNumber(String cardNumber);

	/**
	 * 根据时间查询用户余额
	 * 
	 * @return
	 */
	public List<Object[]> queryUserAccountBalanceList(int i, int pageSize, Map<String, Object> map);

	public Integer countUserAccountBalance(Map<String, Object> map);

	public FuUser findUserByRegIp(String registerIp);

	public Integer findCountByRegIp(String registerIp);

	/**
	 * 根据时间查询所有用户余额
	 * 
	 * @return
	 */
	public List<Object[]> queryAllUserAccountBalanceList(Map<String, Object> map);

	public List<String> getUserList(int i, int j);

	/**
	 * 移动合伙人
	 * 
	 * @return
	 */
	public void saveMoveHhr(Long id, Long parentId);

	/**
	 * 修改用户的信息(关于交易团队)
	 * 
	 * @param fuUser
	 *            用户对象
	 * @param hhrType
	 *            用户的类别 0:终端(普通用户) 1: 渠道 2:交易团队
	 * @param name
	 *            交易团队的名称
	 * @return
	 */
	public void updateHhrLevelInfo(FuUser fuUser, Integer hhrType, String name);

	/**
	 * 微信新用户注册
	 * 
	 * @param phone
	 *            手机号码
	 * @param password
	 *            密码
	 * @param nickName
	 *            昵称
	 * @param phoneCode
	 *            验证码
	 * @param invitationCode
	 *            邀请码
	 * @param regType
	 *            注册来源 1网站注册，2安卓手机注册，3苹果手机注册 4:微信注册 5:小合财务比赛
	 * @param avatar
	 *            用户头像
	 * @param openId
	 *            微信的openId
	 * @return
	 */
	public void saveWeiXinUser(String phone, String password, String nickName, String phoneCode, String invitationCode, int regType, String avatar, String openId);

	/**
	 * 通过微信号和手机号查询是否存在用户
	 * 
	 * @param weixinCode
	 *            微信号唯一标识符 openid
	 * @param phone
	 *            手机号码
	 * @return
	 */
	public FuUser findUserByWeiXinAndPhone(String weixinCode, String phone);

	/**
	 * 根据用户id 查询这个用户下的所有的有有效账户的个数以及信息
	 * 
	 * @param hhrParentId
	 *            上级的id
	 * @return
	 */
	public List<FuUserBak> findUserByHhrParentId(Long hhrParentId);

	public List<FuUser> findListByParentIdCount(Long userId, Integer count);

}
