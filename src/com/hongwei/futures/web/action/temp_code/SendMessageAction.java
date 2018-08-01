package com.hongwei.futures.web.action.temp_code;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.hongwei.futures.util.HttpClientUtil;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("fu_common")
public class SendMessageAction extends BaseAction{

	public final static String OPERATOR_1 = "http://service.winic.org/sys_port/gateway/?";
	private static final long serialVersionUID = 8151886386104829989L;
	
	
	private String phones;	 // 以","隔开的电话号码
	private String operator; // 运营商
	private String message;  // 短信消息
	
	public String getPhones() {
		return phones;
	}

	public void setPhones(String phones) {
		this.phones = phones;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Action("sendCode")
	public String sendCode() throws Exception {
		return SUCCESS;
	}
	
	@Action("sendInfo")
	public String sendMessage() throws Exception {
		String sb = phones;
		sb = sb.replace("\r\n", ",");
		System.out.println(sb);
		String op = operator;
		System.out.println(op);
		System.out.println(message);
		if("1".equals(operator)) {
			sendSmsProgram_old(phones, message);
		}
		return null;
	}
	
	@Action("send")
	public String send() throws Exception {
		String sb = "18057183626,13501861044,13910664667,18611127273,15050376720,18768193283,13701004671,13707173794";
//		sb = sb.replace("\r\n", ",");
//		System.out.println(sb);
//		String op = operator;
//		System.out.println(op);
//		System.out.println(message);
//		if("1".equals(operator)) {
//			sendSmsProgram_old(sb, message);
//		}
		String message = "微期权第五期已到期，本期涨幅为0.59%，未能达到保本线，没有收益。感谢您的参与！欢迎致电：4000320898";
//		System.out.println(sb+"--"+ message);
		sendSmsProgram_old(sb, message);
		return null;
	}
	
	public static boolean sendSmsProgram_old(String phones, String message) {
		String msg=HttpClientUtil.postHTTP("http://service.winic.org/sys_port/gateway/?", "whkzdd", "1qaz2wsx", phones, message, "");
		String str=msg.split("/")[0];
		if(str.equals("000")){
			System.out.println(phones+"---发送成功---");
			return true;
		}else{
			return false;
		}
	}
	
	
}
