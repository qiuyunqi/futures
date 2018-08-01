package com.hongwei.futures.util;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class SinaUtil {
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

	public static Map<String, String> map;

	public static Pattern pattern;

	static {
		map = new HashMap<String, String>();
		map.put("[带着微博去旅行]", "weitripballoon_thumb.gif");
		map.put("[玩去啦]", "weitrip_thumb.gif");
		map.put("[笑哈哈]", "lxhwahaha_thumb.gif");
		map.put("[转发]", "lxhzhuanfa_thumb.gif");
		map.put("[得意地笑]", "lxhdeyidixiao_thumb.gif");
		map.put("[moc转发]", "moczhuanfa_thumb.gif");
		map.put("[bm可爱]", "bmkeai_thumb.gif");
		map.put("[lt切克闹]", "ltqiekenao_thumb.gif");
		map.put("[xkl转圈]", "xklzhuanquan_thumb.gif");
		map.put("[ppb鼓掌]", "ppbguzhang_thumb.gif");
		map.put("[din推撞]", "dintuizhuang_thumb.gif");
		map.put("[xb压力]", "xbyali_thumb.gif");
		map.put("[草泥马]", "shenshou_thumb.gif");
		map.put("[神马]", "horse2_thumb.gif");
		map.put("[浮云]", "fuyun_thumb.gif");
		map.put("[给力]", "geili_thumb.gif");
		map.put("[围观]", "wg_thumb.gif");
		map.put("[威武]", "vw_thumb.gif");
		map.put("[熊猫]", "panda_thumb.gif");
		map.put("[兔子]", "rabbit_thumb.gif");
		map.put("[奥特曼]", "otm_thumb.gif");
		map.put("[囧]", "j_thumb.gif");
		map.put("[互粉]", "hufen_thumb.gif");
		map.put("[礼物]", "liwu_thumb.gif");
		map.put("[呵呵]", "smilea_thumb.gif");
		map.put("[嘻嘻]", "tootha_thumb.gif");
		map.put("[哈哈]", "laugh.gif");
		map.put("[可爱]", "tza_thumb.gif");
		map.put("[可怜]", "kl_thumb.gif");
		map.put("[挖鼻屎]", "kbsa_thumb.gif");
		map.put("[吃惊]", "cj_thumb.gif");
		map.put("[害羞]", "shamea_thumb.gif");
		map.put("[挤眼]", "zy_thumb.gif");
		map.put("[闭嘴]", "bz_thumb.gif");
		map.put("[鄙视]", "bs2_thumb.gif");
		map.put("[爱你]", "lovea_thumb.gif");
		map.put("[泪]", "sada_thumb.gif");
		map.put("[偷笑]", "heia_thumb.gif");
		map.put("[亲亲]", "qq_thumb.gif");
		map.put("[生病]", "sb_thumb.gif");
		map.put("[太开心]", "/mb_thumb.gif");
		map.put("[懒得理你]", "ldln_thumb.gif");
		map.put("[右哼哼]", "yhh_thumb.gif");
		map.put("[左哼哼]", "zhh_thumb.gif");
		map.put("[嘘]", "x_thumb.gif");
		map.put("[衰]", "cry.gif");
		map.put("[委屈]", "wq_thumb.gif");
		map.put("[吐]", "t_thumb.gif");
		map.put("[打哈欠]", "k_thumb.gif");
		map.put("[抱抱]", "bba_thumb.gif");
		map.put("[怒]", "angrya_thumb.gif");
		map.put("[疑问]", "yw_thumb.gif");
		map.put("[馋嘴]", "cza_thumb.gif");
		map.put("[拜拜]", "88_thumb.gif");
		map.put("[思考]", "sk_thumb.gif");
		map.put("[汗]", "sweata_thumb.gif");
		map.put("[困]", "sleepya_thumb.gif");
		map.put("[睡觉]", "sleepa_thumb.gif");
		map.put("[钱]", "money_thumb.gif");
		map.put("[失望]", "sw_thumb.gif");
		map.put("[酷]", "cool_thumb.gif");
		map.put("[花心]", "hsa_thumb.gif");
		map.put("[哼]", "hatea_thumb.gif");
		map.put("[鼓掌]", "gza_thumb.gif");
		map.put("[晕]", "dizzya_thumb.gif");
		map.put("[悲伤]", "bs_thumb.gif");
		map.put("[抓狂]", "crazya_thumb.gif");
		map.put("[黑线]", "h_thumb.gif");
		map.put("[阴险]", "yx_thumb.gif");
		map.put("[怒骂]", "nm_thumb.gif");
		map.put("[心]", "hearta_thumb.gif");
		map.put("[伤心]", "unheart.gif");
		map.put("[猪头]", "pig.gif");
		map.put("[ok]", "ok_thumb.gif");
		map.put("[耶]", "ye_thumb.gif");
		map.put("[good]", "good_thumb.gif");
		map.put("[不要]", "no_thumb.gif");
		map.put("[赞]", "z2_thumb.gif");
		map.put("[来]", "come_thumb.gif");
		map.put("[弱]", "sad_thumb.gif");
		map.put("[蜡烛]", "lazu_thumb.gif");
		map.put("[钟]", "clock_thumb.gif");
		map.put("[话筒]", "m_thumb.gif");
		map.put("[蛋糕]", "cake.gif");
		
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
