package com.hongwei.futures.web.ws;

import java.math.BigDecimal;
import java.util.Map;



public interface AppWebService {
	/**
	 * app登陆
	 * @param phone
	 * @param password
	 * @return
	 */
	public String login(String phone, String password, String sign);
	/**
	 * app注册
	 * @param phone
	 * @param password
	 * @param account_name
	 * @param invitation_code
	 * @param phoneCode					session中存储的手机号码和验证码  格式 : 手机号#验证码   137#10086
	 * @param code						接收到的验证码
	 * @return
	 */
	public String register(String phone, String password, String nickName, String invitation_code, String phoneCode, String code, String avatar, String avatarStr,String sign);
	/**
	 * app主界面
	 * @param user_id
	 * @param weixin_code
	 * @return
	 */
	public String index(Long user_id, String weixin_code, String sign);
	/**
	 * app修改密码
	 * @param phone
	 * @param newpwd
	 * @param phone_code
	 * @return
	 */
	public String updatePwd(String phone, String newpwd, String phone_code, String sign);
	/**
	 * app获取用户合伙人列表
	 * @param user_id
	 * @return
	 */
	public String partners(Long user_id, String sign);
	/**
	 * app修改密码发送手机验证码
	 * @param phone
	 * @return
	 */
	public String updatePwdPhoneCode(String phone, String sign);
	/**
	 * app注册发送短信验证码
	 * @param phone
	 * @return
	 */
	public String regPhoneCode(String phone, String sign);
	/**
	 * app修改提款密码
	 * @param phone
	 * @param newDrawPwd
	 * @param phone_code
	 * @return
	 */
	public String updateDrawPwd(String phone, String new_drawpwd, String phone_code, String sign);
	
	/**
	 * 修改提款密码发送短信验证码
	 * @param phone
	 */
	public String updateDrawPwdPhoneCode(String phone, String sign);
	
	/**
	 * app设置个人资料
	 * @param phone
	 * @param fileByteStr
	 * @param user_avatar
	 * @param introduction
	 * @param nick_name
	 * @param email
	 * @return
	 */
	public String updateUserInfo(String phone, String nick_name, Integer gender, Integer is_marriage, Long province_id, Long city_id, String live_address, 
			Integer max_degree, String business_type, String position_name, BigDecimal salary, String fileByteStr, String user_avatar, String introduction,  
			String email, String sign);

	/**
	 * app实名认证
	 * @param phone
	 * @param user_name
	 * @param card_number
	 * @param beforeByteStr 
	 * @param card_before_pic
	 * @param behindByteStr 
	 * @param card_behind_pic
	 * @return
	 */
	public String certificateUser(String phone, String user_name, String card_number, String beforeByteStr, String card_before_pic, String behindByteStr, String card_behind_pic, String handByteStr, String card_hand_pic, String sign);

	/**
	 * app银行卡绑定
	 * @param phone
	 * @param account_name
	 * @param bank_name
	 * @param card_number
	 * @param bank_province
	 * @param bank_city
	 * @param bank_branch_name
	 * @param draw_password
	 * @return
	 */
	public String bindingCard(String phone, String account_name, String bank_name, String card_number, String bank_province, String bank_city, String bank_branch_name, String draw_password, String sign);

	/**
	 * app资格证登记
	 * @param phone
	 * @param user_name
	 * @param quali_num
	 * @param qualiByteStr
	 * @param quali_pic
	 * @return
	 */
	public String qualificationRegister(String phone, String user_name, String quali_num, String qualiByteStr, String quali_pic, String quali_type, String sign);
	
	/**
	 * app实名认证查询
	 * @param phone
	 * @return
	 */
	public String queryCerti(String phone, String sign);
	
	/**
	 * app银行卡绑定查询
	 * @param phone
	 * @return
	 */
	public String queryBankCard(String phone, String sign);
	
	/**
	 * app资格证查询
	 * @param phone
	 * @return
	 */
	public String queryQualiNum(String phone, String sign);
	
	/**
	 * app升级信息查询
	 * @return
	 */
	public String appUpgradeQuery();
	
	/**
	 * app下载
	 * @return
	 */
	public String appDownload();
	
	/**
	 * app快速注册
	 * @param phone
	 * @param invitation_code
	 * @param reg_type
	 * @return
	 */
	public String fastRegister(String phone, String invitation_code, String reg_type, String sign);
	
	/**
	 * app方案开户
	 * @param phone
	 * @param match_money
	 * @param num
	 * @param programType
	 * @param tradeTimeType
	 * @param cycleNum
	 * @param integral
	 * @return
	 */
	public String appSaveProgram(String phone, String match_money, int num, int programType, int tradeTimeType, int cycleNum, String integral, String sign);
	
	/**
	 * app追加保证金
	 * @param phone
	 * @param programId
	 * @param money
	 * @return
	 */
	public String appAddSafeMoney(String phone, Long programId, String money, String sign);
	
	/**
	 * app查询可提利润
	 * @param programId
	 * @return
	 */
	public String queryDrawBenefits(Long programId, String sign);
	
	/**
	 * app提取利润(出金)
	 * @param programId
	 * @param money
	 * @return
	 */
	public String appDrawBenefits(Long programId, String money, String sign);
	
	/**
	 * app充值
	 * @param phone
	 * @param bankId
	 * @param money
	 * @param fileByteStr
	 * @param recharge_pic
	 * @return
	 */
	public String appRechargeMoney(String phone, String bankId, String money, String fileByteStr, String recharge_pic, String recharge_id, String sign);
	
	/**
	 * app充值记录
	 * @param phone
	 * @return
	 */
	public String appRechargeRecords(String phone, String sign);
	
	/**
	 * app提现
	 * @param phone
	 * @param bankId
	 * @param drawPassword
	 * @param phoneCode
	 * @param money
	 * @return
	 */
	public String appDrawMoney(String phone, String bankId, String drawPassword, String phoneCode, String money, String sign);
	
	/**
	 * app提现记录
	 * @param phone
	 * @return
	 */
	public String appDrawRecords(String phone, String sign);
	
	/**
	 * app提现发送手机验证码
	 * @param phone
	 * @return
	 */
	public String appDrawMoneyPhoneCode(String phone, String sign);
	
	/**
	 * app在线留言
	 * @param phone
	 * @param type
	 * @param content
	 * @return
	 */
	public String appMessageInfo(String phone, Integer type, String content, String sign);
	
	/**
	 * app账户余额和可用积分
	 * @param phone
	 * @return
	 */
	public String appAccountInfo(String phone, String sign);

	/**
	 * app续约
	 * @param phone
	 * @param programId
	 * @param cycleNum
	 * @return
	 */
	public String appRenewal(String phone, Long programId, Integer cycleNum, String sign);
	
	/**
	 * app注册发钱
	 * @param phone
	 * @return
	 */
	public String appRegisterIncome(String phone, String sign);
	
	/**
	 * app查询用户相关费率
	 * @param phone
	 * @return
	 */
	public String appQueryUserRate(String phone, String sign);
	
	/**
	 * app查询方案
	 * @param phone
	 * @return
	 */
	public String appQueryUserPrograms(String phone, String sign);
	
	/**
	 * app关闭方案
	 * @param programId
	 * @return
	 */
	public String appCloseProgram(Long programId, String sign);
	
	/**
	 * app删除方案
	 * @param programId
	 * @return
	 */
	public String appDeleteProgram(Long programId, String sign);
	
	/**
	 * app绑定微信号
	 * @param phone
	 * @param password
	 * @param weixinCode
	 * @return
	 */
	public String appBindWeixinCode(String phone, String password, String weixinCode, String type, String sign);
	
	/**
	 * 查询省份和城市信息
	 * @param sign
	 * @return
	 */
	public String appQueryProvinceAndCity(String sign);
	
	/**
	 * 绑定邮箱发送验证码
	 * @param phone
	 * @param email
	 * @param sign
	 * @return
	 */
	public String bindEmailCode(String phone, String email, String sign);
	
	/**
	 * app查询交易中的方案
	 * @param queryDate
	 * @param sign
	 * @return
	 */
	public String appQueryActivatedPrograms(String queryDate, String sign);
	
	/**
	 * 绑定邮箱
	 * @param phone
	 * @param email
	 * @param emailCode
	 * @param sign
	 * @return
	 */
	public String bindEmail(String phone, String email, String emailCode, String sign);
	
	/**
	 * 比赛数据统计
	 * @param queryDate
	 * @param sign
	 * @return
	 */
	public String queryGameInfo(String queryDate, String sign);
	
	/**
	 * 根据手机号统计比赛数据
	 * @param phone
	 * @param sign
	 * @return
	 */
	public String queryGameInfoByPhone(String phone, String sign);
	
	/**
	 * 注册发钱活动是否结束
	 * @param sign
	 * @return
	 */
	public String queryRegisterActivity(String sign);
	
	/**
	 * 查询用户留言记录
	 * @param user_id
	 * @param sign
	 * @return
	 */
	public String queryUserMessage(Long user_id, String sign);
	/**
	 * 查询手机端银联支付的tn
	 * @param money
	 * @return
	 */
	public String queryChinaPayTN(String money, String sign);
	/**
	 * 修改合伙人备注名
	 * @param 
	 * @return
	 */
	public String updateRemarkName(Long user_id,Long relevance_id, String remarkName, String sign);
	/**
	 * 检测电话是否已经被注册
	 * @param phone
	 * @param sign
	 * @return
	 */
	public String isRegister(String phone, String sign);
	/**
	 * 检测用户是否报名了该次比赛
	 * @return
	 * @throws Exception
	 */
	public String isRegistration(String phone, String gameId, String sign);
	/**
	 * 报名参赛
	 * @return
	 * @throws Exception
	 */
	public String saveGame(String phone, String gameId, String competitionNum, String sign);
	
	/**
	 * 单独发送验证码
	 * @param ip 
	 * @param phone
	 * @param sign
	 * @return
	 */
	public String sendCode(String ip, String phone, String sign);
	/**
	 * 查询这个用户下所有的开户账户 以及今日最新的盈利
	 * @param userId
	 * @param sign
	 * @return
	 */
	public String accountList(String userId, String sign);
	/**
	 * 每日盈亏流水
	 * @param userId  用户唯一标识符
	 * @param sign
	 * @return
	 */
	public String profitOrLossDetail(String userId, String sign);
	
	/**
	 * 应缴明细
	 * @param userId   用户唯一标识符
	 * @param sign
	 * @return
	 */
	public String payAbleDetail(String userId, String sign);
	/**
	 * 未缴费用记录明细
	 * @param userId   用户唯一标识符
	 * @param sign
	 * @return
	 */
	public String noPayAbleDateil(String userId, String sign);
	/**
	 * 根据账号id查询账号的基本信息
	 * @param stockId
	 * @param sign
	 * @return
	 */
	public String accountDatil(String stockId, String sign);
	/**
	 * 根据stockId 设置idDel的状态值为0
	 * @param stockId
	 * @param isDel
	 * @param sign 
	 */
	public String delAccount(String stockId, String isDel, String sign);
	/**
	 * 添加一个账户信息
	 * @param userId					用户id 必须是实名制的用户
	 * @param openUser                  开户姓名
	 * @param openEquity      			开户券商
	 * @param salesDept					营业部
	 * @param capitalAccount			开户账户
	 * @param capitalPassword  			开户密码
	 * @param partnerAccount			账户类型
	 * @param accountType				账户类型    1:普通账户  2:融资账户   3:信用账户
	 * @param sign
	 * @return
	 */
	public String addAccountInfo(String userId, String openUser,
			String openEquity, String salesDept, String capitalAccount,
			String capitalPassword, String partnerAccount, String accountType, String sign);
	/**
	 * 根据用查询用户的全部资产明细
	 * @param user_id			用户id
	 * @param sign
	 * @return
	 */
	public String assetsAll(String user_id, String sign);
	/**
	 * 根据用户查询用户的收入明细
	 * @param user_id			用户id
	 * @param sign
	 * @return
	 */
	public String inComeDetail(String user_id, String sign);
	/**
	 * 根据用户查询用户的支出明细
	 * @param user_id
	 * @param sign
	 * @return
	 */
	public String outComeDetail(String user_id, String sign);
	
	/**
	 * 添加一个用户改变状态记录
	 * @param accountId							股票账户表的id
//	 * @param accountStatus						账号当前的状态
//	 * @param operationStatusBefore				用户当前可以操作的状态
//	 * @param userId							用户的id
//	 * @param stockStatusId 					股票记录信息表的id		
	 * @param sign								加密密钥
	 * @return
	 */
	public String addStatusRecord(String accountId, String sign);
	/**
	 * 验证验证码和邀请码是否存在
	 * @param phone				手机号码
	 * @param invitationCode	邀请码
	 * @param sign
	 * @return
	 */
	public String isPhoneAndInvitationCode(String phone, String invitationCode, String sign);
	/**
	 * 检查手机号码和验证码是否匹配 (注册)
	 * @param phone
	 * @param phoneCode	用户输入的验证码
	 * @param code 		在session中的验证码
	 * @param sign
	 * @return
	 */
	public String isCheckCode(String phone, String phoneCode, String sign);
	
	/**
	 * 手机端支付宝支付通知
	 * @param phone
	 * @param sign
	 * @param out_trade_no
	 * @param trade_no
	 * @param trade_status
	 * @param total_fee
	 * @return
	 */
	public String notify_url(String user_id, String out_trade_no, String trade_no, String trade_status, String total_fee, Map requestParams);
		
}
