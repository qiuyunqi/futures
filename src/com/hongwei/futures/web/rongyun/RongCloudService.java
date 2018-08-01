package com.hongwei.futures.web.rongyun;

public interface RongCloudService {

	public String  getToken(String user_id, String userName, String portraitUri, String sign);

	// 刷新用户信息
	public String refresh(String user_id, String userName, String portraitUri,
			String sign);

}
