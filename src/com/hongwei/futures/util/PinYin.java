package com.hongwei.futures.util;


//import net.sourceforge.pinyin4j.PinyinHelper;
//import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
//import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
//import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
//import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

/**
 * pingyin操作类
 * @author 充满智慧的威哥
 *
 */
public class PinYin {
	
//	/**
//	 * 得到单个字符的pingyin形式
//	 * @param c
//	 * @return
//	 * @throws Exception
//	 */
//	public static List<String> getSpell(char c) throws Exception {
//		List<String> list = new ArrayList<String>();
//		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
//		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 小写
//		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 没有音调数字
//		format.setVCharType(HanyuPinyinVCharType.WITH_V);
//		
//		String [] result = new String[]{};
//		if (java.lang.Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) {
//			result = PinyinHelper.toHanyuPinyinStringArray(c, format);
//			//去重复项
//			for (String str : result) {
//				if (list.indexOf(str) == -1) {
//					list.add(str);
//				}
//			}
//		} else {
//			list.add(String.valueOf(c));
//		}
//		return list;
//	}
//	
//	public static String getSpell(String str) throws Exception {
//		int iLength = str.toCharArray().length;
//		String [][] strArray = new String[iLength][];
//		char[] clist = str.toCharArray();
//		//组建二维数组
//		for (int i = 0; i < clist.length; i++) {
//			List<String> slist = getSpell(clist[i]);
//			int slength = slist.size();
//			strArray[i] = new String[slength];
//			if (slength == 0) {
//				strArray[i][0] = clist[i] + "";
//			} else if (slength == 1) {
//				strArray[i][0] = slist.get(0);
//			} else {
//				for (int j = 0; j < slist.size(); j++) {
//					strArray[i][j] = slist.get(j);
//				}
//			}
//		}
//		
//		//二维数组排列组合
//		StringBuilder result = new StringBuilder();
//		int max = 1;
//		for (int i = 0; i < strArray.length; i++) {
//			max *= strArray[i].length;
//		}
//		for (int i = 0; i < max; i++) {
//			String s = "";
//			int temp = 1; // 注意这个temp的用法。
//			for (int j = 0; j < strArray.length; j++) {
//				temp *= strArray[j].length;
//				String tempStr = strArray[j][i / (max / temp) % strArray[j].length];
//				s += tempStr;
//			}
//			result.append(",").append(s);
//		}
//		return result.toString();
//	}
//
//	public static void main(String[] args) {
//		try {
////			String s = getSpell("共a");
////			System.out.println(s);
//			
//			System.out.println(getSpell("漆"));
//		} catch (Exception e) {
//			e.printStackTrace();
//		} 
//	}
}
