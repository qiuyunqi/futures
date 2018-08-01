package com.hongwei.futures.util;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import chinapay.PrivateKey;
import chinapay.SecureLink;

import com.hongwei.futures.model.FuRecharge;

public class ChinaPayUtil {
	public static String querySend(FuRecharge recharge) {
		String MerKeyPath = ServletActionContext.getServletContext().getRealPath(Property.getProperty("MERCHANT_KEY_PATH"));
		String query_url = Property.getProperty("CHINAPAY_QueryUrl");
		String Version = Property.getProperty("CHINAPAY_Version");

		// 查询订单数据准备
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		NumberFormat ndf1 = new DecimalFormat("0000000");
		// NumberFormat ndf2=new DecimalFormat("000000000000");
		String MerId = Property.getProperty("CHINAPAY_MERCHANT_NO");
		String OrderId = Property.getProperty("CHINAPAY_OrderHead") + ndf1.format(recharge.getId());
		String TransDate = sdf.format(new Date());
		String TransType = Property.getProperty("CHINAPAY_TransType");
		String Resv = Property.getProperty("CHINAPAY_Priv1");
		String ChkValue = null;

		boolean buildOK = false;
		int KeyUsage = 0;
		PrivateKey key = new PrivateKey();
		try {
			buildOK = key.buildKey(MerId, KeyUsage, MerKeyPath);
			if (!buildOK) {
				System.out.println("build error!");
				return "false";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}

		try {
			SecureLink sl = new SecureLink(key);
			System.out.println(MerId + TransDate + OrderId + TransType);
			ChkValue = sl.Sign(MerId + TransDate + OrderId + TransType);
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}

		QueryBean query = new QueryBean();
		query.setMerId(MerId);
		query.setOrdId(OrderId);
		query.setTransDate(TransDate);
		query.setTransType(TransType);
		query.setVersion(Version);
		query.setResv(Resv);
		query.setChkValue(ChkValue);

		String httpType = "SSL";
		String timeOut = "60000";
		String res = sendHttpMsg(query_url, query.toString(), httpType, timeOut);
		System.out.println(res);
		/**
		 * 在收到报文之后，可对报文数据进行验签，此处忽略。 具体验签方法可参看商户手册中单笔查询的验签处理。
		 * 
		 */
		// 验签
		HashMap<String, String> returnMap = new HashMap<String, String>();
		String[] retSplit = res.split("&");
		for (int i = 0; i < retSplit.length; i++) {
			int eqaulIndex = retSplit[i].indexOf("=");
			String keyName = retSplit[i].substring(0, eqaulIndex);
			String value = retSplit[i].substring(eqaulIndex + 1);
			returnMap.put(keyName, value);
		}
		String pubKeyPath = ServletActionContext.getServletContext().getRealPath(Property.getProperty("PUBLIC_KEY_PATH"));
		boolean verifyRet = verify(Version, returnMap, pubKeyPath);
		res = "签名验证结果=[" + verifyRet + "] 单笔查询返回=[" + res + "]";
		return res;
	}

	public static boolean verify(String version, Map<String, String> map, String pubKeyPath) {
		StringBuffer sbf = new StringBuffer();
		sbf.append(map.get("merid")).append(map.get("orderno")).append(map.get("amount")).append(map.get("currencycode")).append(map.get("transdate")).append(map.get("transtype"))
				.append(map.get("status"));
		// sbf.append(map.get("cpdate"))
		// .append(map.get("cpseqid"));
		String chkValue = map.get("checkvalue");

		boolean buildOK = false;
		int KeyUsage = 0;
		PrivateKey key = new PrivateKey();
		try {
			buildOK = key.buildKey("999999999999999", KeyUsage, pubKeyPath);
		} catch (Exception e) {
		}
		if (!buildOK) {
			System.out.println("verify build error!");
		}
		try {
			SecureLink sl = new SecureLink(key);
			System.out.println("verify plain=[" + sbf.toString() + "]");
			return sl.verifyAuthToken(sbf.toString(), chkValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 发送http post报文，并且接受响应信息
	 * 
	 * @param strMsg
	 *            需要发送的交易报文,格式遵循httppost参数格式
	 * @return String 服务器返回响应报文,如果处理失败，返回空字符串
	 */
	public static String sendHttpMsg(String URL, String strMsg, String httpType, String timeOut) {
		String returnMsg = "";
		CPHttpConnection httpSend = null;
		if (httpType.equals("SSL")) {
			httpSend = new HttpSSL(URL, timeOut);
		} else {
			httpSend = new Http(URL, timeOut);
		}
		// 设置获得响应结果的限制
		httpSend.setLenType(0);
		// 设置字符编码
		httpSend.setMsgEncoding("GBK");
		int returnCode = httpSend.sendMsg(strMsg);
		if (returnCode == 1) {
			try {
				returnMsg = new String(httpSend.getReceiveData(), "GBK").trim();
				System.out.println("接收到响应报文,returnMsg=[" + returnMsg + "]");
			} catch (UnsupportedEncodingException e) {
				System.out.println("[getReceiveData Error!]");
			}
		} else {
			System.out.println(new StringBuffer("报文处理失败,失败代码=[").append(returnCode).toString());
		}
		return returnMsg;
	}
}
