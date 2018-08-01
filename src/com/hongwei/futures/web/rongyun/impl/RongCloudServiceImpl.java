package com.hongwei.futures.web.rongyun.impl;

import io.rong.ApiHttpClient;
import io.rong.models.FormatType;
import io.rong.models.SdkHttpResult;

import java.io.InputStream;
import java.util.Properties;

import net.sf.json.JSONObject;

import org.codehaus.xfire.client.Client;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.web.rongyun.RongCloudService;


public class RongCloudServiceImpl implements RongCloudService {

	@Autowired
	private FuUserService fuUserService;
	@Override
	public String getToken(String user_id, String userName, String portraitUri, String sign) {
//		String result = DesUtil.webserviceSignVerify(sign);
//		String result = "success";
		JSONObject obj = new JSONObject();
		try {
//			if("success".equals(result)){
				// 先判断这个用户是不是已经有了ry_token
				if(null == user_id || "".equals(user_id)){
					obj.put("is_success", 0);
					obj.put("message", "user_id不能为空");
					System.out.println(obj.toString());
					return obj.toString();
				}
				FuUser user = fuUserService.findFuUserById(Long.parseLong(user_id));
				if(user == null){
					obj.put("is_success", 0);
					obj.put("message", "没有这个用户!");
					System.out.println(obj.toString());
					return obj.toString();
				}
				if(user.getRyToken() != null){
					obj.put("is_success", 1);
					obj.put("token", user.getRyToken());
					obj.put("message", "本地获取即时聊天token成功");
					System.out.println(obj.toString());
					return obj.toString();
				}
				InputStream in = Client.class.getClassLoader().getResourceAsStream("application.properties");
				Properties properties = new Properties();
				properties.load(in);
				String key = properties.getProperty("RONG_APP_KEY");
				String secret = properties.getProperty("RONG_APP_SECRET");
				SdkHttpResult rs = null;
				rs = ApiHttpClient.getToken(key, secret, user_id, userName, portraitUri, FormatType.json);
				System.out.println("gettoken=" + rs);
				//{"code":"200","result":{"code":200,"userId":"286","token":"/kTm8sI/xAuUC8sPCx+2UEJEAW1GHeaD6PoXa0HuLIz2vc7IjUEPu1abtA+EqhsFO16hunQL0HvB2xtjBuwj8w=="}}
				String result2 = rs.getResult();
				org.json.JSONObject json = new org.json.JSONObject(result2);
				Object code =  json.get("code");
				if("200".equals(code.toString())){ // 返回的是成功
					Object object = json.get("token");
					obj.put("is_success", 1);
					obj.put("token", object);
					user.setRyToken((String) object);
					// 保存这个用户的融云token
					fuUserService.save(user);
					obj.put("message", "获取即时聊天token成功");
				}else {
					obj.put("is_success", 0);
					obj.put("message", "失败聊天code"+code);
				}
				return obj.toString();
//			}else{
//				obj.put("is_success", 0);
//				obj.put("message", result);
//			}
		}  catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "invalid request");
		}
		System.err.println(obj.toString());
		return obj.toString();
	}

	// 刷新用户信息
	public String refresh(String user_id, String userName, String portraitUri, String sign) {
//		String result = DesUtil.webserviceSignVerify(sign);
		JSONObject obj = new JSONObject();
		try {
//			if("success".equals(result)){
				InputStream in = Client.class.getClassLoader().getResourceAsStream("application.properties");
				Properties properties = new Properties();
				properties.load(in);
				String key = properties.getProperty("RONG_APP_KEY");
				String secret = properties.getProperty("RONG_APP_SECRET");
				
				SdkHttpResult rs = null;
				rs = ApiHttpClient.refreshUser(key, secret, user_id, userName, portraitUri, FormatType.json);
				JSONObject jsonObject = JSONObject.fromObject(rs);
				String code = (String)jsonObject.get("code");
				if("200".equals(code)){ // 返回的是成功
					obj.put("is_success", 1);
					obj.put("message", "用户聊天信息修改成功");
				}else {
					obj.put("is_success", 0);
					obj.put("message", "用户聊天失败code"+code);
				}
				return obj.toString();
//			}else {
//				obj.put("is_success", 0);
//				obj.put("message", result);
//			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "invalid request");
		}
		return obj.toString();
	}

}
