package com.hongwei.futures.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.FuUserBak;

/**
 * 
 * @description 自动生成 dao
 *
 * @author 弘威
 */
public interface FuUserDao extends BaseDao<FuUser, Long> {

	public FuUser findUserByUserName(String userName);

	public FuUser findLoginByToken(String token);

	public FuUser findUserByPhone(String phone);

	public FuUser findUserByEmail(String email);

	public FuUser findUserByAccount(String accountName);

	public Integer countUser(Map<String, Object> map);

	public List<FuUser> findUserList(int i, int pageSize,Map<String, Object> map);

	public Integer countCheckUser(Map<String, Object> map);
	
	public Integer countCheckUser2(Map<String, Object> map);

	public List<FuUser> findCheckUserList(int i, int j, Map<String, Object> map);

	public int countRegister();

	public BigDecimal countTotalMoney();

	public FuUser findUserByRegPhone(String phone);

	public FuUser findUserByRegAccountName(String accountName);
	
	public FuUser findUserByNickName(String nickName);
	
	public FuUser findUserByRegInvitationcode(String invitation_code);
	
	public Object[] findUserDataByMap(Map<String, Object> map);
	
	public Integer countInvitationCode(String code);

	public FuUser findFuUserById(Long userId);

	public List<FuUser> findListByUser(Long userId);

	public List<FuUser> findSameNickNameUser(String nickName);

	public List<FuUser> findListByParentId(Long userId);

	public String findUserTree(Long id);
	
	/**
	 * 查询所有用户
	 * @return
	 */
	public List<FuUser> findAllUsers();

	public FuUser findUserByWeixinCode(String weixinCode);

	public FuUser findUserByCardNumber(String cardNumber);

	public List<Object[]> queryUserAccountBalanceList(int i, int pageSize, Map<String, Object> map);

	public Integer countUserAccountBalance(Map<String, Object> map);

	public FuUser findUserByRegIp(String registerIp);
	
	public Integer findCountByRegIp(String registerIp);

	public List<Object[]> queryAllUserAccountBalanceList(Map<String, Object> map);

	public List<String> getUserList(int i, int j);

	/**
	 * 通过微信号和手机号查询是否存在用户
	 * @param weixinCode			微信唯一标识符 openId
	 * @param phone					手机号码
	 * @return
	 */
	public FuUser findUserByWeiXinAndPhone(String weixinCode, String phone);

	/**
	 * 根据用户id 查询这个用户下的所有的有有效账户的个数以及信息
	 * @param hhrParentId		上级的id
	 * @return
	 */
	public List<FuUserBak> findUserByHhrParentId(Long hhrParentId);

	public List<FuUser> findListByParentIdCount(Long userId, Integer count);
}

