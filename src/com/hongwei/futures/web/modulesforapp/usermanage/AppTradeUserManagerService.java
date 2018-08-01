package com.hongwei.futures.web.modulesforapp.usermanage;

public interface AppTradeUserManagerService {

	/**
	 * 银联充值
	 * @param userId 	用户id
	 * @param sign
	 * @param version
	 * @return
	 */
	public String appUnionPayRecharge(String userId, String sign, String version);
	
	/**
	 * 查询合伙人列表
	 * @param userId		用户id
	 * @param curPage 		当前页
	 * @param pageSize		每页大小
	 * @param sign	
	 * @param version
	 * @return
	 */
	public String partnersList(String userId, String curPage, String pageSize, String sign, String version);

	/**
	 * 删除银行卡 使state = 0
	 * @param userId		用户id
	 * @param cardNum		银行卡号
	 * @param sign
	 * @param version
	 * @return
	 */
	public String deleteCard(String userId, String cardNum, String sign,
			String version);

	/**
	 * 保证全部的充实条件进行实名认证
	 * @param userId			用户id
	 * @param userName			用户的姓名
	 * @param indentityCard		身份证号
	 * @param phone				电话号码
	 * @param phoneCode			验证码
	 * @param drawPwd1			第一次的交易密码
	 * @param drawPwd2			确认的交易密码
	 * @param userCard			持卡人	
	 * @param cardNum			银行卡号
	 * @param bankName			开户银行的名称
	 * @param bankAddress		开户银行的地址
	 * @param sign		
	 * @param version
	 * @return
	 */
	
	/**
	 * 
	 * @param userId			用户id
	 * @param userName			用户的姓名
	 * @param indentityCard		身份证号
	 * @param phone				电话号码
	 * @param phoneCode			验证码
	 * @param drawPwd1			第一次的交易密码
	 * @param drawPwd2			确认的交易密码
	 * @param userCard			持卡人	
	 * @param cardNum			银行卡号
	 * @param bankName			开户银行的名称
	 * @param bankProvince		开户银行的省网点
	 * @param bankCity			开户银行的市网点
	 * @param bankBranchName		网点支行名称
	 * @param sign
	 * @param version
	 * @return
	 */
	public String bindCard(String userId, String userName,
			String indentityCard, String phone, String phoneCode,
			String drawPwd1, String drawPwd2, String userCard,
			String cardNum, String bankName, String bankProvince, String bankCity,
			String bankBranchName, String sign, String version);

	/**
	 * 实名认证第一部
	 * @param userId			用户id
	 * @param userName			用户的姓名
	 * @param indentityCard		身份证号
	 * @param phone				电话号码
	 * @param phoneCode			验证码
	 * @param sign
	 * @param version
	 * @return
	 */
	public String realCertification(String userId, String userName,
			String indentityCard, String phone, String phoneCode, String sign,
			String version);
	
	/**
	 * 判断两次交易密码是否是一致的, 并存储
	 * @param drawPwd1		第一次输入的交易密码
	 * @param drawPwd2		第二次确认的交易密码
	 * @param sign
	 * @param version
	 * @return
	 */
	public String confirmDrawPwd(String drawPwd1, String drawPwd2, String sign,
			String version);

	/**
	 * 判断用输入的交易密码是否是正确的
	 * @param userId			用户的id
	 * @param drawPwd			交易密码
	 * @param version			版本号
	 * @param sign				加密密钥
	 * @return
	 */
	public String checkDrawPwd(String userId, String drawPwd, String version,
			String sign);

	/**
	 * 根据user的主键获取用户的基本信息
	 * @param userId      用户的id
	 * @param version     版本号  
	 * @param sign        加密密钥 
	 * @return            
	 */
	public String getUserInfo(String userId, String version, String sign);

	/**
	 * 发送实名认证的验证码
	 * @param phone			手机号码
	 * @param version		版本号码
	 * @param sign			验证码
	 * @return
	 */
	public String sendRealCode(String phone, String version, String sign);

	/**
	 * 根据用查询用户的全部资产明细
	 * @param userId			用户id
	 * @param sign
	 * @return
	 */
	public String assetsAll(String userId, String curPage, String pageSize, String version, String sign);

	/**
	 * 根据用户查询用户的收入明细
	 * @param userId			用户id
	 * @param sign
	 * @return
	 */
	public String inComeDetail(String userId, String curPage, String pageSize, String version, String sign);
	/**
	 * 根据用户查询用户的支出明细
	 * @param userId			用户id
	 * @param sign
	 * @return
	 */
	public String outComeDetail(String userId, String curPage, String pageSize, String version, String sign);

	/**
	 * 用户体现现金
	 * @param userId			用户id
	 * @param drawPwd		交易密码
	 * @param cardNum		银行卡号
	 * @param money			体现的现金	
	 * @param version		版本号码
	 * @param sign
	 * @return
	 */
	public String appDrawMoney(String userId, String drawPwd, String cardNum, String money,
			String version, String sign);

	/**
	 * 添加银行卡
	 * @param userId					银行卡
	 * @param drawPwd				交易密码
	 * @param userCard				持卡人
	 * @param cardNum				银行卡号
	 * @param bankName				银行名称
	 * @param bankProvince			开户银行的省网点
	 * @param bankCity				开户银行的市网点
	 * @param bankBranchName			网点支行名称
	 * @param version
	 * @param sign
	 * @return
	 */
	public String addBindCard(String userId, String drawPwd, String userCard, String cardNum,
			String bankName, String bankProvince, String bankCity,
			String bankBranchName, String version, String sign);

	/**
	 * 根据用户的手机号码查询用户的充值记录
	 * @param phone			手机号码
	 * @param sign
	 * @param version
	 * @return
	 */
	public String appRechargeRecords(String phone, String sign, String version);

	/**
	 * 预约开户的基本信息
	 * @param sign
	 * @param version
	 * @return
	 */
	public String appointment(String sign, String version);

	/**
	 * app 体现记录
	 * @param phone
	 * @param version
	 * @param sign
	 * @return
	 */
	public String appDrawRecords(String phone, String version, String sign);
	
	//	查询全部的银行名称
	public String getAllBankName(String version, String sign);

}
