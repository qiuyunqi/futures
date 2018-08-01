package com.hongwei.futures.util;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.codehaus.xfire.client.Client;

public class DesUtil {
	private static Log log = LogFactory.getLog(DesUtil.class);

	/**
	 * DES算法密钥
	 */
	private static final byte[] DES_KEY = { 21, 1, -110, 82, -32, -85, -128, -65 };

	private static final byte[] iv = { 0x12, 0x34, 0x56, 0x78, (byte) 0x90, (byte) 0xab, (byte) 0xcd, (byte) 0xef };

	/**
	 * 接口标签解密处理
	 */
	public static String webserviceSignVerify(String cryptData) {
		String result = "success";
		if (cryptData == null || "".equals(cryptData)) {
			String userAgentStr = ServletActionContext.getRequest().getHeader("User-Agent");
			if (userAgentStr.indexOf("iOS") == -1) {
				result = "缺少数字签名";
			}
		} else {
			try {
				// 从配置文件读取
				InputStream in = Client.class.getClassLoader().getResourceAsStream("application.properties");
				Properties properties = new Properties();
				properties.load(in);
				cryptData = java.net.URLDecoder.decode(cryptData, "UTF-8");
				// 解密标签
				String decryptedData = decryptSign(cryptData, properties.getProperty("DES_STRING"));
				// 渠道标识
				String client = decryptedData.substring(0, 3);
				// 接口密码
				String password = decryptedData.substring(3, 35);
				// 时间戳
				String timestamp = decryptedData.substring(35);
				if (client == null || "".equals(client)) {
					result = "缺少渠道标识";
				} else if (password == null || "".equals(password)) {
					result = "缺少接口密码";
				} else if (timestamp == null || "".equals(timestamp)) {
					result = "缺少时间戳";
				} else {
					if (properties.getProperty("CLIENT_ANDROID").equals(client)) {
						if (!properties.getProperty("MD5_ANDROID").equals(password)) {
							result = "Android渠道接口密码错误";
						}
					} else if (properties.getProperty("CLIENT_IOS").equals(client)) {
						if (!properties.getProperty("MD5_IOS").equals(password)) {
							result = "iOS渠道接口密码错误";
						}
					} else if (properties.getProperty("CLIENT_PC").equals(client)) {
						if (!properties.getProperty("MD5_PC").equals(password)) {
							result = "PC渠道接口密码错误";
						}
					} else if (properties.getProperty("CLIENT_WEIXIN").equals(client)) {
						if (!properties.getProperty("MD5_WEIXIN").equals(password)) {
							result = "微信渠道接口密码错误";
						}
					} else {
						try {
							SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
							Date stampDate = format.parse(timestamp);
							Date nowDate = new Date();
							// 时间间隔5分钟之内
							if (Math.abs(nowDate.getTime() - stampDate.getTime()) < 300000) {
								result = "调用过于频繁";
							}
						} catch (Exception e) {
							// e.printStackTrace();
							System.out.println(result);
						}
					}
				}
			} catch (Exception e) {
				// e.printStackTrace();
				result = "非法数字签名";
				System.out.println(result);
			}
		}
		return result;
	}

	/**
	 * 前台签名解密处理
	 */
	public static String webSignVerify(String cryptData) {
		String result = "success";
		if (cryptData == null || "".equals(cryptData)) {
			String userAgentStr = ServletActionContext.getRequest().getHeader("User-Agent");
			if (userAgentStr.indexOf("iOS") == -1) {
				result = "缺少数字签名";
			}
		} else {
			try {
				// 从配置文件读取
				InputStream in = Client.class.getClassLoader().getResourceAsStream("application.properties");
				Properties properties = new Properties();
				properties.load(in);
				cryptData = java.net.URLDecoder.decode(cryptData, "UTF-8");
				// 解密标签
				String decryptedData = decryptSign(cryptData, properties.getProperty("DES_STRING"));
				// 渠道标识
				String client = decryptedData.substring(0, 3);
				// 接口密码
				String password = decryptedData.substring(3, 35);
				// 时间戳
				String timestamp = decryptedData.substring(35, 53);
				// iplong
				String iplong = decryptedData.substring(53);
				if (client == null || "".equals(client)) {
					result = "缺少渠道标识";
				} else if (password == null || "".equals(password)) {
					result = "缺少接口密码";
				} else if (timestamp == null || "".equals(timestamp)) {
					result = "缺少时间戳";
				} else if (iplong == null || "".equals(iplong)) {
					result = "缺少IP";
				} else {
					if (properties.getProperty("CLIENT_ANDROID").equals(client)) {
						if (!properties.getProperty("MD5_ANDROID").equals(password)) {
							result = "Android渠道接口密码错误";
						}
					} else if (properties.getProperty("CLIENT_IOS").equals(client)) {
						if (!properties.getProperty("MD5_IOS").equals(password)) {
							result = "iOS渠道接口密码错误";
						}
					} else if (properties.getProperty("CLIENT_PC").equals(client)) {
						if (!properties.getProperty("MD5_PC").equals(password)) {
							result = "PC渠道接口密码错误";
						}
					} else if (properties.getProperty("CLIENT_WEIXIN").equals(client)) {
						if (!properties.getProperty("MD5_WEIXIN").equals(password)) {
							result = "微信渠道接口密码错误";
						}
					} else {
						try {
							SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
							Date stampDate = format.parse(timestamp);
							Date nowDate = new Date();
							// 时间间隔1秒之内
							if (Math.abs(nowDate.getTime() - stampDate.getTime()) < 1000) {
								result = "调用过于频繁";
							}
							// 时间超过5分钟
							if (Math.abs(nowDate.getTime() - stampDate.getTime()) > 300000) {
								result = "签名过期";
							}
						} catch (Exception e) {
							// e.printStackTrace();
							System.out.println(result);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				result = "非法数字签名";
				System.out.println(result);
			}
		}
		return result;
	}

	/**
	 * 解密签名后获得IP段的Long
	 */
	public static String ipToLong(String cryptData) {
		String iplong = null;
		try {
			// 从配置文件读取
			InputStream in = Client.class.getClassLoader().getResourceAsStream("application.properties");
			Properties properties = new Properties();
			properties.load(in);
			cryptData = java.net.URLDecoder.decode(cryptData, "UTF-8");
			// 解密标签
			String decryptedData = decryptSign(cryptData, properties.getProperty("DES_STRING"));
			// 渠道标识
			String client = decryptedData.substring(0, 3);
			// 接口密码
			String password = decryptedData.substring(3, 35);
			// 时间戳
			String timestamp = decryptedData.substring(35, 53);
			// iplong
			iplong = decryptedData.substring(53);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return iplong;
	}

	/**
	 * 加密方法
	 */
	public static String encryptSign(String data, String _key) {
		String encryptedData = null;
		try {
			DESKeySpec deskey = new DESKeySpec(_key.getBytes());
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(deskey);
			// 加密对象
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			IvParameterSpec ivp = new IvParameterSpec(DesUtil.iv);
			cipher.init(Cipher.ENCRYPT_MODE, key, ivp);
			// 加密，并把字节数组编码成字符串
			encryptedData = new sun.misc.BASE64Encoder().encode(cipher.doFinal(data.getBytes()));
		} catch (Exception e) {
			throw new RuntimeException("加密错误，错误信息：", e);
		}
		return encryptedData;
	}

	/**
	 * 解密方法
	 */
	public static String decryptSign(String cryptData, String _key) {
		String decryptedData = null;
		try {
			DESKeySpec deskey = new DESKeySpec(_key.getBytes());
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(deskey);
			// 解密对象
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			IvParameterSpec ivp = new IvParameterSpec(DesUtil.iv);
			cipher.init(Cipher.DECRYPT_MODE, key, ivp);
			// 把字符串解码为字节数组，并解密
			decryptedData = new String(cipher.doFinal(new sun.misc.BASE64Decoder().decodeBuffer(cryptData)));
		} catch (Exception e) {
			throw new RuntimeException("解密错误，错误信息：", e);
		}
		return decryptedData;
	}

	/**
	 * 数据加密，算法（DES）
	 * 
	 * @param data
	 *            要进行加密的数据
	 * @return 加密后的数据
	 */
	public static String encryptBasedDes(String data) {
		String encryptedData = null;
		try {
			// DES算法要求有一个可信任的随机数源
			SecureRandom sr = new SecureRandom();
			DESKeySpec deskey = new DESKeySpec(DES_KEY);
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(deskey);
			// 加密对象
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key, sr);
			// 加密，并把字节数组编码成字符串
			encryptedData = new sun.misc.BASE64Encoder().encode(cipher.doFinal(data.getBytes()));
		} catch (Exception e) {
			// log.error("加密错误，错误信息：", e);
			throw new RuntimeException("加密错误，错误信息：", e);
		}
		return encryptedData;
	}

	/**
	 * 数据解密，算法（DES）
	 * 
	 * @param cryptData
	 *            加密数据
	 * @return 解密后的数据
	 */
	public static String decryptBasedDes(String cryptData) {
		String decryptedData = null;
		try {
			// DES算法要求有一个可信任的随机数源
			SecureRandom sr = new SecureRandom();
			DESKeySpec deskey = new DESKeySpec(DES_KEY);
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(deskey);
			// 解密对象
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key, sr);
			// 把字符串解码为字节数组，并解密
			decryptedData = new String(cipher.doFinal(new sun.misc.BASE64Decoder().decodeBuffer(cryptData)));
		} catch (Exception e) {
			// log.error("解密错误，错误信息：", e);
			throw new RuntimeException("解密错误，错误信息：", e);
		}
		return decryptedData;
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		log.info("测试info_log");
		log.error("测试error_log");
	}

}
