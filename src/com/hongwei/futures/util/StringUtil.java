package com.hongwei.futures.util;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author 充满智慧的威哥
 *
 */
public class StringUtil {
	
	/**
	 * 截取字符串
	 * @param s
	 * @param maxLength
	 * @return
	 */
	public static String interceptStr(String s, int maxLength) {
		if (isBlank(s)) {
			return "";
		}
		return s.length() > maxLength ? s.substring(0, maxLength - 1) + "..."  : s;
	}
	
	/**
	 * 判断字符串是否为空
	 * @param serverMoney
	 * @return
	 */
	public static boolean isBlank(String serverMoney) {
		if (serverMoney == null || serverMoney.trim().length() == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断字符串是否为数字字符串
	 * @param str
	 * @return
	 */
	 public static boolean isNumeric(String str){ 
		 for (int i = str.length();--i>=0;){ 
			   if (!Character.isDigit(str.charAt(i))){
			    return false; 
			   } 
		  }
		  return true; 
    }
	 
	
	/**
	 * 首字母小写
	 * 
	 * @param s String
	 * @return String
	 */
	public static String firstCharLowerCase(String s) {
		if (s == null || "".equals(s)) {
			return ("");
		}
		return s.substring(0, 1).toLowerCase() + s.substring(1);
	}

	/**
	 * 首字母大写
	 * 
	 * @param s String
	 * @return String
	 */
	public static String firstCharUpperCase(String s) {
		if (s == null || "".equals(s)) {
			return ("");
		}
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	/**
	 * aBbbCcc => a_bbb_ccc
	 * 
	 * @param property
	 * @return String
	 */
	public static String getConverColName(String property) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < property.length(); i++) { // 遍历property如果有大写字母则将大写字母转换为_加小写
			char cur = property.charAt(i);
			if (Character.isUpperCase(cur)) {
				sb.append("_");
				sb.append(Character.toLowerCase(cur));
			} else {
				sb.append(cur);
			}
		}
		return sb.toString();
	}
	
	/**
	 * a_bbb_ccc => aBbbCcc
	 * 
	 * @param property
	 * @return String
	 */
	public static String getConverColBean(String property) {
		if (isBlank(property) || property.indexOf("_") == -1) {
			return property;
		}
		StringBuffer sb = new StringBuffer();
		boolean flag = false;
		for (int i = 0; i < property.length(); i++) { // 遍历property如果有大写字母则将大写字母转换为_加小写
			char cur = property.charAt(i);
			if ('_' == cur) {
				flag = true;
				continue;
			} else {
				sb.append(flag ? Character.toUpperCase(cur) : cur);
				flag = false;
			}
		}
		return sb.toString();
	}
	
	/**
	 * 是否有中文字符
	 * 
	 * @param s
	 * @return
	 */
	public static boolean hasCn(String s) {
		if (s == null) {
			return false;
		}
		return countCn(s) > s.length();
	}

	/**
	 * 获得字符。符合中文习惯。
	 * 
	 * @param s
	 * @param length
	 * @return
	 */
	public static String getCn(String s, int len) {
		if (s == null) {
			return s;
		}
		int sl = s.length();
		if (sl <= len) {
			return s;
		}
		// 留出一个位置用于…
		len -= 1;
		int maxCount = len * 2;
		int count = 0;
		int i = 0;
		while (count < maxCount && i < sl) {
			if (s.codePointAt(i) < 256) {
				count++;
			} else {
				count += 2;
			}
			i++;
		}
		if (count > maxCount) {
			i--;
		}
		return s.substring(0, i) + "…";
	}

	/**
	 * 计算GBK编码的字符串的字节数
	 * 
	 * @param s
	 * @return
	 */
	public static int countCn(String s) {
		if (s == null) {
			return 0;
		}
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.codePointAt(i) < 256) {
				count++;
			} else {
				count += 2;
			}
		}
		return count;
	}

	/**
	 * 文本转html
	 * 
	 * @param txt
	 * @return
	 */
	public static String txt2htm(String txt) {
		if (isBlank(txt)) {
			return txt;
		}
		StringBuilder bld = new StringBuilder();
		char c;
		for (int i = 0; i < txt.length(); i++) {
			c = txt.charAt(i);
			switch (c) {
			case '&':
				bld.append("&amp;");
				break;
			case '<':
				bld.append("&lt;");
				break;
			case '>':
				bld.append("&gt;");
				break;
			case '"':
				bld.append("&quot;");
				break;
			case ' ':
				bld.append("&nbsp;");
				break;
			case '\n':
				bld.append("<br/>");
				break;
			default:
				bld.append(c);
				break;
			}
		}
		return bld.toString();
	}

	/**
	 * html转文本
	 * 
	 * @param htm
	 * @return
	 */
	public static String htm2txt(String htm) {
		if (htm == null) {
			return htm;
		}
		htm = htm.replace("&amp;", "&");
		htm = htm.replace("&lt;", "<");
		htm = htm.replace("&gt;", ">");
		htm = htm.replace("&quot;", "\"");
		htm = htm.replace("&nbsp;", " ");
		htm = htm.replace("<br/>", "\n");
		return htm;
	}

	/**
	 * 全角-->半角
	 * 
	 * @param qjStr
	 * @return
	 */
	public String Q2B(String qjStr) {
		String outStr = "";
		String Tstr = "";
		byte[] b = null;
		for (int i = 0; i < qjStr.length(); i++) {
			try {
				Tstr = qjStr.substring(i, i + 1);
				b = Tstr.getBytes("unicode");
			} catch (java.io.UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			if (b[3] == -1) {
				b[2] = (byte) (b[2] + 32);
				b[3] = 0;
				try {
					outStr = outStr + new String(b, "unicode");
				} catch (java.io.UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			} else
				outStr = outStr + Tstr;
		}
		return outStr;
	}

	public static final char[] N62_CHARS = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
			'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Y', 'Z' };
	public static final char[] N36_CHARS = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
			'x', 'y', 'z' };

	private static StringBuilder longToNBuf(long l, char[] chars) {
		int upgrade = chars.length;
		StringBuilder result = new StringBuilder();
		int last;
		while (l > 0) {
			last = (int) (l % upgrade);
			result.append(chars[last]);
			l /= upgrade;
		}
		return result;
	}

	/**
	 * 长整数转换成N62
	 * 
	 * @param l
	 * @return
	 */
	public static String longToN62(long l) {
		return longToNBuf(l, N62_CHARS).reverse().toString();
	}

	public static String longToN36(long l) {
		return longToNBuf(l, N36_CHARS).reverse().toString();
	}

	/**
	 * 长整数转换成N62
	 * 
	 * @param l
	 * @param length
	 *            如N62不足length长度，则补足0。
	 * @return
	 */
	public static String longToN62(long l, int length) {
		StringBuilder sb = longToNBuf(l, N62_CHARS);
		for (int i = sb.length(); i < length; i++) {
			sb.append('0');
		}
		return sb.reverse().toString();
	}

	public static String longToN36(long l, int length) {
		StringBuilder sb = longToNBuf(l, N36_CHARS);
		for (int i = sb.length(); i < length; i++) {
			sb.append('0');
		}
		return sb.reverse().toString();
	}

	/**
	 * N62转换成整数
	 * 
	 * @param n62
	 * @return
	 */
	public static long n62ToLong(String n62) {
		return nToLong(n62, N62_CHARS);
	}

	public static long n36ToLong(String n36) {
		return nToLong(n36, N36_CHARS);
	}

	private static long nToLong(String s, char[] chars) {
		char[] nc = s.toCharArray();
		long result = 0;
		long pow = 1;
		for (int i = nc.length - 1; i >= 0; i--, pow *= chars.length) {
			int n = findNIndex(nc[i], chars);
			result += n * pow;
		}
		return result;
	}

	private static int findNIndex(char c, char[] chars) {
		for (int i = 0; i < chars.length; i++) {
			if (c == chars[i]) {
				return i;
			}
		}
		throw new RuntimeException("N62(N36)非法字符：" + c);
	}
	/**
	 * 方法描述:把数组1,2,3转化成字符串
	 * @param integerList
	 * @return
	 */
	public static String getSplitStringByInt(List<Integer> integerList){
		if(null!=integerList&&integerList.size()!=0){
			String splitString = "";
			for(int intInstance : integerList){
				splitString += intInstance+",";			
			}
			return splitString.substring(0,splitString.length()-1);
		}else{
			return null;
		}
	}/**
	 * 方法描述:把数组1,2,3转化成字符串
	 * @param integerList
	 * @return
	 */
	public static String getSplitStringByString(List<String> StringList){
		if(null!=StringList&&StringList.size()!=0){
			String splitString = "";
			for(String stringInstance : StringList){
				splitString += stringInstance+",";			
			}
			return splitString.substring(0,splitString.length()-1);
		}else{
			return null;
		}
	}
	/**
	 * 拼装('1','2','3',...) 
	 * @param ids
	 * @return
	 */
	public static String getHqlIdStr(Object[] ids){
		StringBuffer hql=new StringBuffer();
		hql.append("(");
		for(int i=0;i<ids.length-1;i++){
			hql.append("'").append(ids[i].toString()).append("'").append(",");
		}
		hql.append("'").append(ids[ids.length-1].toString()).append("'");
		hql.append(")");
		return hql.toString();
	}
	
	public static String createBlock(Long[] dirIds) {
		if (dirIds == null || dirIds.length == 0)
			return "('')";
		StringBuilder blockStr = new StringBuilder("(");
		for (int i = 0; i < dirIds.length - 1; i++) {
			blockStr.append("'").append(dirIds[i]).append( "',");
		}
		blockStr.append("'").append(dirIds[dirIds.length - 1]).append( "')");
		return blockStr.toString();
	}
	/**
	 * 判断字符串是否在规定范围内
	 * @param str
	 * @param min
	 * @param max
	 * @return
	 */
	public static Boolean checkString(String str,int min,int max){
		if(str==null||str.trim().length()<min||str.trim().length()>max)
			return false;
		return true;
	}
	
	/**
	 * 获取距离现在的时间
	 */
	public static String getMinutes(long times) {
		long time = new Date().getTime()-times;// time 单位是 毫秒
		String res = null; // 转化成天数
		
		if (time < 60 * 60 * 1000) {
			// 先判断是不是小于 60 * 60 * 1000 也就是 小于1小时，那么显示 ： **分钟前
			res = (time / 1000 / 60) + "分钟前";
		}
		else if (time >= 60 * 60 * 1000 && time < 24 * 60 * 60 * 1000) {
			// 如果大于等于1小时 小于等于一天，那么显示 ： **小时前
			res = (time / 1000 / 60 / 60) + "小时前";
		}
		else if (time >= 24 * 60 * 60 * 1000 && time < 7 * 24 * 60 * 60 * 1000 ) {
			// 如果大于等于1小时 小于等于一天，那么显示 ： **小时前
			res = (time / 1000 / 60 / 60 / 24) + "天前";
		}
		else if (time >= 7 * 24 * 60 * 60 * 1000) {
			res = "一周前";
		}
		// 如果时间不明确或者发帖不足一分钟 ，则不显示
		else {
			res = "刚刚";
		}
		return res;
	}
	
	/**
	 * 自定义格式
	 * @param pattern
	 * @param data
	 * @return
	 */
	public static String getDecimalFormat(String pattern, Object data){
		DecimalFormat df = new DecimalFormat();
		df.applyPattern(pattern);
		return  df.format(data);
	}
	
	/**
	 * 标准金额格式输出
	 * @param data
	 * @return
	 */
	public static String getDecimalFormat(Object data){
		DecimalFormat df = new DecimalFormat();
		df.applyPattern("#,###,##0.00");
		return  df.format(data);
	}
}
