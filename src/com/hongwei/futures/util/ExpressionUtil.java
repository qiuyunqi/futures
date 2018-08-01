package com.hongwei.futures.util;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ExpressionUtil {
	/**
	 * 对spanableString进行正则判断，如果符合要求，则以表情图片代替
	 * 
	 * @param context
	 * @param spannableString
	 * @param patten
	 * @param start
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws NumberFormatException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */

	public static Map<String, Integer> map;

	public static Pattern pattern;

	static {
		map = new HashMap<String, Integer>();
		map.put("/微笑", 0);
		map.put("/撇嘴", 1);
		map.put("/色", 2);
		map.put("/发呆", 3);
		map.put("/得意", 4);
		map.put("/流泪", 5);
		map.put("/害羞", 6);
		map.put("/闭嘴", 7);
		map.put("/睡", 8);
		map.put("/大哭", 9);
		map.put("/尴尬", 10);
		map.put("/发怒", 11);
		map.put("/调皮", 12);
		map.put("/呲牙", 13);
		map.put("/惊讶", 14);
		map.put("/难过", 15);
		map.put("/酷", 16);
		map.put("/冷汗", 17);
		map.put("/抓狂", 18);
		map.put("/吐", 19);
		map.put("/偷笑", 20);
		map.put("/可爱", 21);
		map.put("/白眼", 22);
		map.put("/傲慢", 23);
		map.put("/饥饿", 24);
		map.put("/困", 25);
		map.put("/惊恐", 26);
		map.put("/流汗", 27);
		map.put("/憨笑", 28);
		map.put("/大兵", 29);
		map.put("/奋斗", 30);
		map.put("/咒骂", 31);
		map.put("/疑问", 32);
		map.put("/嘘", 33);
		map.put("/晕", 34);
		map.put("/折磨", 35);
		map.put("/衰", 36);
		map.put("/骷髅", 37);
		map.put("/敲打", 38);
		map.put("/再见", 39);
		map.put("/擦汗", 40);
		map.put("/抠鼻", 41);
		map.put("/鼓掌", 42);
		map.put("/糗大了", 43);
		map.put("/坏笑", 44);
		map.put("/左哼哼", 45);
		map.put("/右哼哼", 46);
		map.put("/哈欠", 47);
		map.put("/鄙视", 48);
		map.put("/委屈", 49);
		map.put("/快哭了", 50);
		map.put("/阴险", 51);
		map.put("/亲亲", 52);
		map.put("/吓", 53);
		map.put("/可怜", 54);
		map.put("/菜刀", 55);
		map.put("/西瓜", 56);
		map.put("/啤酒", 57);
		map.put("/篮球", 58);
		map.put("/乒乓", 59);
		map.put("/咖啡", 60);
		map.put("/饭", 61);
		map.put("/猪头", 62);
		map.put("/玫瑰", 63);
		map.put("/凋谢", 64);
		map.put("/示爱", 65);
		map.put("/爱心", 66);
		map.put("/心碎", 67);
		map.put("/蛋糕", 68);
		map.put("/闪电", 69);
		map.put("/炸弹", 70);
		map.put("/刀", 71);
		map.put("/足球", 72);
		map.put("/瓢虫", 73);
		map.put("/便便", 74);
		map.put("/月亮", 75);
		map.put("/太阳", 76);
		map.put("/礼物", 77);
		map.put("/拥抱", 78);
		map.put("/强", 79);
		map.put("/弱", 80);
		map.put("/握手", 81);
		map.put("/胜利", 82);
		map.put("/抱拳", 83);
		map.put("/勾引", 84);
		map.put("/拳头", 85);
		map.put("/差劲", 86);
		map.put("/爱你", 87);
		map.put("/不", 88);
		map.put("/OK", 89);
		StringBuffer regx = new StringBuffer("(");
		for (String key : map.keySet()) {
			regx.append(key).append("|");
		}
		regx.deleteCharAt(regx.length() - 1);
		regx.append(")");
		pattern = Pattern.compile(regx.toString());
	}
	
	public static void main(String[] args){
		DecimalFormat fmt=new DecimalFormat("000");
		for(String key:map.keySet()){
			System.out.println("<key>$1</key>".replace("$1", key));
			System.out.println("<string>smiley_$1</string>".replace("$1", fmt.format(map.get(key))));
		}
	}


}
