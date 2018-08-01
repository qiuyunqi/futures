package com.hongwei.futures.web.action.wxapi;


public class WeixinUtil {

	public static String HELLO = "你好!\n[$1]";
	public static String MENU = "菜单";
	public static String TEXT_TEMPLATE = "<xml>"
			+ "<ToUserName><![CDATA[$1]]></ToUserName>"
			+ "<FromUserName><![CDATA[$2]]></FromUserName>"
			+ "<CreateTime>$3</CreateTime>"
			+ "<MsgType><![CDATA[text]]></MsgType>"
			+ "<Content><![CDATA[$4]]></Content>" + "<FuncFlag>0</FuncFlag>"
			+ "</xml>";
	public static String MUSIC_TEMPLATE = "<xml>"
			+ "<ToUserName><![CDATA[$1]]></ToUserName>"
			+ "<FromUserName><![CDATA[$2]]></FromUserName>"
			+ "<CreateTime>$3</CreateTime>"
			+ "<MsgType><![CDATA[music]]></MsgType>" + "<Music>"
			+ "<Title><![CDATA[$4]]></Title>"
			+ "<Description><![CDATA[$5]]></Description>"
			+ "<MusicUrl><![CDATA[$6]]></MusicUrl>"
			+ "<HQMusicUrl><![CDATA[$7]]></HQMusicUrl>" + "</Music>"
			+ "<FuncFlag>0</FuncFlag>" + "</xml>";
	public static String NEWS_TEMPLATE = "<xml>"
			+ "<ToUserName><![CDATA[$1]]></ToUserName>"
			+ "<FromUserName><![CDATA[$2]]></FromUserName>"
			+ "<CreateTime>$3</CreateTime>"
			+ "<MsgType><![CDATA[news]]></MsgType>"
			+ "<ArticleCount>$4</ArticleCount>" + "<Articles>" + "<item>"
			+ "<Title><![CDATA[$5]]></Title>"
			+ "<Description><![CDATA[$6]]></Description>"
			+ "<PicUrl><![CDATA[$7]]></PicUrl>" + "<Url><![CDATA[$8]]></Url>"
			+ "</item>" + "$9" + "</Articles>" + "<FuncFlag>0</FuncFlag>"
			+ "</xml>";
	public static String NEWS_ITEM_TEMPLATE = "<item>"
			+ "<Title><![CDATA[$1]]></Title>"
			+ "<Description><![CDATA[$2]]></Description>"
			+ "<PicUrl><![CDATA[$3]]></PicUrl>" + "<Url><![CDATA[$4]]></Url>"
			+ "</item>";
	public static String IMAGE_TEMPLATE="<xml>"
			+"<ToUserName><![CDATA[$1]]></ToUserName>"
			+"<FromUserName><![CDATA[$2]]></FromUserName>"
			+"<CreateTime>$3</CreateTime>"
			+"<MsgType><![CDATA[image]]></MsgType>"
			+"<Image>"
			+"<MediaId><![CDATA[$4]]></MediaId>"
			+"</Image>"
			+"</xml>";
	public static String VOICE_TEMPLATE="<xml>"
			+"<ToUserName><![CDATA[$1]]></ToUserName>"
			+"<FromUserName><![CDATA[$2]]></FromUserName>"
			+"<CreateTime>$3</CreateTime>"
			+"<MsgType><![CDATA[voice]]></MsgType>"
			+"<Voice>"
			+"<MediaId><![CDATA[$4]]></MediaId>"
			+"</Voice>"
			+"</xml>";
	public static String VIDEO_TEMPLATE="<xml>"
			+"<ToUserName><![CDATA[$1]]></ToUserName>"
			+"<FromUserName><![CDATA[$2]]></FromUserName>"
			+"<CreateTime>$3</CreateTime>"
			+"<MsgType><![CDATA[video]]></MsgType>"
			+"<Video>"
			+"<MediaId><![CDATA[$4]]></MediaId>"
			+"<ThumbMediaId><![CDATA[$5]]></ThumbMediaId>"
			+"</Video>"
			+"</xml>";
	
	public static String IMG_ROOT_URL = "http://www.vdongli.com$1";
	
}
