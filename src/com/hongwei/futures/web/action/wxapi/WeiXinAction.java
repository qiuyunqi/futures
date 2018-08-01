package com.hongwei.futures.web.action.wxapi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;

import com.hongwei.futures.util.SHA1;
import com.hongwei.futures.util.WebContent;
import com.hongwei.futures.web.action.BaseAction;
import com.sun.xml.internal.messaging.saaj.util.Base64;

@ParentPackage("convention-default")
public class WeiXinAction extends BaseAction {

	private String openId;

	private Long clientId;
	private String type;

	private String token;
	private String signature;
	private String timestamp;
	private String nonce;
	private String echostr;
	private boolean logEnable = true;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Action("valid")
	public String responseMsg() {
		try {
			if (token != null && checkSignature()) {
				boolean focused = false;
				if (focused) {// 微信账号与接口绑定的时候执行的代码
					write(echostr);
					return null;
				}
				BufferedReader br = new BufferedReader(new InputStreamReader(
						this.getHttpServletRequest().getInputStream(), "utf-8"));
				String line = null;
				StringBuilder sb = new StringBuilder();
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				if (sb.length() > 0) {// 收到消息
					log(sb.toString());
					Document document = DocumentHelper.parseText(sb.toString());
					String fromUsername = document.selectSingleNode(
							"/xml/FromUserName[1]").getText();
					String toUsername = document.selectSingleNode(
							"/xml/ToUserName[1]").getText();
					String recMsgType = document.selectSingleNode(
							"/xml/MsgType[1]").getText();
					if (recMsgType.equals("event")) {// 事件消息包括关注，取消关注，菜单点击
						String event = document.selectSingleNode(
								"/xml/Event[1]").getText();
						if (event.equals("subscribe")) {// 关注
						} else if (event.equals("unsubscribe")) {// 取消关注
						} else if (event.equals("CLICK")) {
						} else if (event.equals("SCAN")) {
						} else if (event.equals("LOCATION")) {
							String lat = document
									.selectSingleNode("/xml/Latitude[1]")
									.getText().trim();
							String lng = document
									.selectSingleNode("/xml/Longitude[1]")
									.getText().trim();
							if (!lat.startsWith("0.000000")
									&& !lng.startsWith("0.000000")) {
								Double dlat = Double.parseDouble(lat);
								Double dlng = Double.parseDouble(lng);
								DecimalFormat df = new DecimalFormat(
										"#.################");
								WebContent content = new WebContent();
								String latlng = content
										.getOneHtml("http://api.map.baidu.com/ag/coord/convert?from=2&to=4&x="
												+ df.format(dlng)
												+ "&y="
												+ df.format(dlat));
								JSONObject latlngJson = JSONObject
										.fromObject(latlng);
								lat = Base64.base64Decode(latlngJson
										.getString("y"));
								lng = Base64.base64Decode(latlngJson
										.getString("x"));
								dlat = Double.parseDouble(lat);
								dlng = Double.parseDouble(lng);
							}
						}
					} else if (recMsgType.equals("text")) {// 文本消息 关键词回复
						String keyword = document
								.selectSingleNode("/xml/Content[1]").getText()
								.trim();
					} else if (recMsgType.equals("image")) {// 图片消息
						String picUrl = document
								.selectSingleNode("/xml/PicUrl[1]").getText()
								.trim();
						String mediaId = document
								.selectSingleNode("/xml/MediaId[1]").getText()
								.trim();
					} else if (recMsgType.equals("location")) {// 位置消息
						String lat = document
								.selectSingleNode("/xml/Location_X[1]")
								.getText().trim();
						String lng = document
								.selectSingleNode("/xml/Location_Y[1]")
								.getText().trim();
						String scale = document
								.selectSingleNode("/xml/Scale[1]").getText()
								.trim();
						String label = document
								.selectSingleNode("/xml/Label[1]").getText()
								.trim();
						JSONObject obj = new JSONObject();
						Double dlat = Double.parseDouble(lat);
						Double dlng = Double.parseDouble(lng);
						DecimalFormat df = new DecimalFormat(
								"#.################");
						WebContent content = new WebContent();
						String latlng = content
								.getOneHtml("http://api.map.baidu.com/ag/coord/convert?from=2&to=4&x="
										+ df.format(dlng)
										+ "&y="
										+ df.format(dlat));
						JSONObject latlngJson = JSONObject.fromObject(latlng);
						lat = Base64.base64Decode(latlngJson.getString("y"));
						lng = Base64.base64Decode(latlngJson.getString("x"));
						obj.put("scale", scale);
						obj.put("label", label);
						obj.put("lat", lat);
						obj.put("lng", lng);
						dlat = Double.parseDouble(lat);
						dlng = Double.parseDouble(lng);
					} else if (recMsgType.equals("link")) {// 链接消息
						String title = document
								.selectSingleNode("/xml/Title[1]").getText()
								.trim();
						String description = document
								.selectSingleNode("/xml/Description[1]")
								.getText().trim();
						String url = document.selectSingleNode("/xml/Url[1]")
								.getText().trim();
						// 提醒消息
					} else if (recMsgType.equals("voice")) {// 声音消息
						String mediaId = document
								.selectSingleNode("/xml/MediaId[1]").getText()
								.trim();
						String format = document
								.selectSingleNode("/xml/Format[1]").getText()
								.trim();
					} else if (recMsgType.equals("video")) {// 视频消息
						String mediaId = document
								.selectSingleNode("/xml/MediaId[1]").getText()
								.trim();
						String thumb = document
								.selectSingleNode("/xml/ThumbMediaId[1]")
								.getText().trim();
					}
				}
			}
		} catch (Exception e) {
			if (logEnable)
				e.printStackTrace();
		}
		return null;
	}

	public String getActPic(String type) {
		return "";
	}

	private String getUrl(String url) {
		try {
			if (url.startsWith("/"))
				url = WeixinUtil.IMG_ROOT_URL.replace("$1", url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	private void log(Object msg) {
		if (logEnable)
			System.out.println(msg.toString());
	}

	/**
	 * 坤哥看这里,这个方法负责输出到到微信 输出返回结果
	 * 
	 * @param fromUsername
	 * @param toUsername
	 * @param obj
	 *            json 格式 {type:"text",text:""} 文本回复的json格式
	 *            {type:"article",title:"",pic:"",url:"",desc:""} 单图文回复的json格式
	 *            {type:"music",title:"",desc:"",url:"",hurl:""}语音回复的格式
	 * @throws Exception
	 */
	private void showResult(String fromUsername, String toUsername,
			JSONObject obj) {
		try {
			if (obj == null)
				return;
			long time = new Date().getTime();
			String result = "";
			if (obj.getString("type").equals("text")) {
				result = WeixinUtil.TEXT_TEMPLATE.replace("$1", fromUsername)
						.replace("$2", toUsername)
						.replace("$3", String.valueOf(time))
						.replace("$4", obj.getString("text"));
			}
			if (obj.getString("type").equals("music")) {
				result = WeixinUtil.MUSIC_TEMPLATE.replace("$1", fromUsername)
						.replace("$2", toUsername)
						.replace("$3", String.valueOf(time))
						.replace("$4", obj.getString("title"))
						.replace("$5", obj.getString("desc"))
						.replace("$6", getUrl(obj.getString("url")))
						.replace("$7", getUrl(obj.getString("hurl")));
			}
			if (obj.getString("type").equals("article")) {
				JSONArray array = obj.containsKey("article") ? obj
						.getJSONArray("article") : new JSONArray();
				result = WeixinUtil.NEWS_TEMPLATE.replace("$1", fromUsername)
						.replace("$2", toUsername)
						.replace("$3", String.valueOf(time))
						.replace("$4", String.valueOf(array.size() + 1))
						.replace("$5", obj.getString("title"))
						.replace("$6", obj.getString("desc"))
						.replace("$7", getUrl(obj.getString("pic")))
						.replace("$8", getUrl(obj.getString("url")));
				String more = "";
				if (array.size() > 0) {
					for (int i = 0; i < array.size(); i++) {
						JSONObject item = array.getJSONObject(i);
						more += WeixinUtil.NEWS_ITEM_TEMPLATE
								.replace("$1", item.getString("title"))
								.replace("$2", "")
								.replace("$3", getUrl(item.getString("pic")))
								.replace("$4", getUrl(item.getString("url")));
					}
				}
				result = result.replace("$9", more);
			}
			write(result);
			log(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean checkSignature() {
		String[] array = { token, timestamp, nonce };
		Arrays.sort(array);
		String tmpStr = "";
		for (int i = 0; i < array.length; i++) {
			tmpStr += array[i];
		}
		SHA1 sha1 = new SHA1();
		tmpStr = sha1.getDigestOfString(tmpStr.getBytes());

		if (tmpStr.equalsIgnoreCase(signature)) {
			return true;
		} else {
			return false;
		}
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getSignature() {
		return signature;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getNonce() {
		return nonce;
	}

	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}

	public String getEchostr() {
		return echostr;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
