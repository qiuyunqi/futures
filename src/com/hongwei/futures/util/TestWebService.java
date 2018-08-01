package com.hongwei.futures.util;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.codehaus.xfire.client.Client;

import com.cloopen.rest.sdk.utils.encoder.BASE64Encoder;

public class TestWebService {
	/**
	 * 登陆
	 */
	public void login() {
		String wsdl = "http://localhost:8080/services/AppWebService?wsdl";
		try {
			Client client = new Client(new URL(wsdl));
			System.out.println("开始调用");
			Object[] result = client.invoke("login", new Object[] {"15271943588", "e10adc3949ba59abbe56e057f20f883e"});
			for (Object o : result) {
				System.out.println(o);
			}
			System.out.println("结束调用");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 注册
	 */
	public void register() {
		String wsdl = "http://localhost:8080/services/AppWebService?wsdl";
		try {
			Client client = new Client(new URL(wsdl));
			System.out.println("开始调用");
			Object[] result = client.invoke("register", new Object[] {"15271943511","123456","omg","123456789012"});
			for (Object o : result) {
				System.out.println(o);
			}
			System.out.println("结束调用");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 主界面
	 */
	public void index() {
		String wsdl = "http://localhost:8080/services/AppWebService?wsdl";
		try {
			Client client = new Client(new URL(wsdl));
			System.out.println("开始调用");
			Object[] result = client.invoke("index", new Object[] {257, "967542", "15271943580"});
			for (Object o : result) {
				System.out.println(o);
			}
			System.out.println("结束调用");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取用户合伙人列表
	 */
	public void partners() {
		String wsdl = "http://localhost:8080/services/AppWebService?wsdl";
		try {
			Client client = new Client(new URL(wsdl));
			System.out.println("开始调用");
			Object[] result = client.invoke("partners", new Object[] {257});
			for (Object o : result) {
				System.out.println(o);
			}
			System.out.println("结束调用");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改密码
	 */
	public void updatePwd() {
		String wsdl = "http://localhost:8080/services/AppWebService?wsdl";
		try {
			Client client = new Client(new URL(wsdl));
			System.out.println("开始调用");
			Object[] result = client.invoke("updatePwd", new Object[] {"15271943580", "e10adc3949ba59abbe56e057f20f883e", "967542"});
			for (Object o : result) {
				System.out.println(o);
			}
			System.out.println("结束调用");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改密码发送手机验证码
	 */
	public void updatePwdPhoneCode() {
		String wsdl = "http://localhost:8080/services/AppWebService?wsdl";
		try {
			Client client = new Client(new URL(wsdl));
			System.out.println("开始调用");
			Object[] result = client.invoke("updatePwdPhoneCode", new Object[] {"15271943580"});
			for (Object o : result) {
				System.out.println(o);
			}
			System.out.println("结束调用");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 用户注册发送手机验证码
	 */
	public void regPhoneCode() {
		String wsdl = "http://localhost:8080/services/AppWebService?wsdl";
		try {
			Client client = new Client(new URL(wsdl));
			System.out.println("开始调用");
			Object[] result = client.invoke("regPhoneCode", new Object[] {"18511266860"});
			for (Object o : result) {
				System.out.println(o);
			}
			System.out.println("结束调用");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 设置个人资料
	 */
	public void setUserInfo() {
		String wsdl = "http://localhost:8080/services/AppWebService?wsdl";
		try {
			Client client = new Client(new URL(wsdl));
			System.out.println("开始调用");
			File file = new File("e:\\xf.jpg");
			FileInputStream in = new FileInputStream(file);
			byte[] flieByte = new byte[Integer.parseInt(String.valueOf(file.length()))];     
			in.read(flieByte);
			in.close();				
			BASE64Encoder encode = new BASE64Encoder();
			String byteFileStr = encode.encode(flieByte);
			Object[] result = client.invoke("updateUserInfo", new Object[] {"15926291619",byteFileStr,"xf.jpg","自我介绍","昵称","163.com"});
			for (Object o : result) {
				System.out.println(o);
			}
			System.out.println("结束调用");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestWebService client = new TestWebService();
		client.setUserInfo();
		System.exit(0);
	}
}
