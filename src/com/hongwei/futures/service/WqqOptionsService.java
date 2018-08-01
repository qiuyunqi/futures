package com.hongwei.futures.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.WqqOptions;

/**
 * 
 * @description 自动生成 service
 *
 */
@Service
public interface WqqOptionsService {
	
	//====================== 基本 C R U D 方法  ===========================
	public WqqOptions get(Long id) ;
	
	public void save(WqqOptions entity) ;
	
	public void delete(Long id) ;

	public List<WqqOptions> queryOptionsList(int i, int pageSize, Map<String, Object> map);

	public Integer countOptions(Map<String, Object> map);
	
	public void saveSendCode(String phone);
	
	public void sendPwdWqq(String phone,String newPass);
	
	public void saveWqqRegister(String phone, String newPass, String invitationCode);
	
	/**
	 * 核对验证码时候正确
	 * @param code				验证码
	 * @param phone				手机号
	 * @return
	 */
	public int checkCode(String code, String phone);
	
	public void saveAliOption(WqqOptions options,String out_trade_no,String total_fee)throws Exception;

	/**
	 * 根据userId 查询用户的所有的订单
	 * @param userId   用户的id
	 * @return
	 */
	public List<WqqOptions> getInfoByUserId(Long userId);

	/**
	 *  保存期权支付（网站余额）
	 * @param user
	 * @param tradeno 			订单号
	 * @param money 			支付的金额
	 * @param guessType 		看涨跌的类型 0:跌 1:涨
	 * @param isPay 
	 */
	public void saveOptionPayWeb(FuUser user, String tradeno, BigDecimal money, int isPay, Integer guessType, Long contentsId);
	
	public void saveWqqFactor(Long contentsId,FuAdmin acceptAdmin,BigDecimal acceptFactor,Integer guessType);
	
}

