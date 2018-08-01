package com.hongwei.futures.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class Common {
	
	public static boolean in_array(Object source, Object ext) {
		return in_array(source, ext, false);
	}
	
	public static boolean in_array(Object source, Object ext, boolean strict) {
		if (source == null || ext == null) {
			return false;
		}
		if (source instanceof Collection) {
			for (Object s : (Collection) source) {
				if (s.toString().equals(ext.toString())) {
					if (strict) {
						if ((s.getClass().getName().equals(ext.getClass().getName()))) {
							return true;
						}
					} else {
						return true;
					}
				}
			}
		} else {
			for (Object s : (Object[]) source) {
				if (s.toString().equals(ext.toString())) {
					if (strict) {
						if ((s.getClass().getName().equals(ext.getClass().getName()))) {
							return true;
						}
					} else {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static String getTimeOffset(Map<String, Object> sGlobal, Map<String, Object> sConfig) {
		Map<String, Object> member = (Map<String, Object>) sGlobal.get("member");
		String timeoffset = null;
		if (member != null) {
			timeoffset = (String) member.get("timeoffset");
		}
		if (Common.empty(timeoffset)) {
			timeoffset = sConfig.get("timeoffset").toString();
		}
		return timeoffset;
	}
	
	@SuppressWarnings("unchecked")
	public static void mkShare(Map<String, Object> share) {
		if (share != null && share.size() > 0) {
			Map<String, String> bodyData = Serializer.unserialize((String) share.get("body_data"), false);
			if (!empty(bodyData)) {
				Set<String> keys = bodyData.keySet();
				for (String key : keys) {
					share.put("body_template", ((String) share.get("body_template")).replace("{" + key + "}",
							bodyData.get(key)));
				}
			}
			share.put("body_data", bodyData);
		}
	}
	
	public static boolean isArray(Object obj) {
		if (obj instanceof Object[]) {
			return true;
		} else if (obj instanceof Collection) {
			return true;
		} else if (obj instanceof Map) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String implode(Object data, String separator) {
		if (data == null) {
			return "";
		}
		StringBuffer out = new StringBuffer();
		if (data instanceof Object[]) {
			boolean flag = false;
			for (Object obj : (Object[]) data) {
				if (flag) {
					out.append(separator);
				} else {
					flag = true;
				}
				out.append(obj);
			}
		} else if (data instanceof Map) {
			Map temp = (Map) data;
			Set<Object> keys = temp.keySet();
			boolean flag = false;
			for (Object key : keys) {
				if (flag) {
					out.append(separator);
				} else {
					flag = true;
				}
				out.append(temp.get(key));
			}
		} else if (data instanceof Collection) {
			boolean flag = false;
			for (Object obj : (Collection) data) {
				if (flag) {
					out.append(separator);
				} else {
					flag = true;
				}
				out.append(obj);
			}
		} else {
			return data.toString();
		}
		return out.toString();
	}
	
	public static String sImplode(Object ids) {
		return "'" + implode(ids, "','") + "'";
	}
	
	public static Map<String, Object> mkFeed(Map<Integer, String> sNames, Map<String, Object> sConfig,
			HttpServletRequest request, Map<String, Object> feed, Object actors) {
		if (feed == null || feed.size() == 0) {
			return feed;
		}
		feed.put("title_data", Serializer.unserialize((String) feed.get("title_data"), false));
		feed.put("body_data", Serializer.unserialize((String) feed.get("body_data"), false));
		List<String> searchs = new ArrayList<String>();
		List<String> replaces = new ArrayList<String>();
		if (feed.get("title_data") != null && isArray(feed.get("title_data"))) {
			Map title_data = (Map) feed.get("title_data");
			Set keys = title_data.keySet();
			for (Object key : keys) {
				searchs.add("{" + key + "}");
				replaces.add(title_data.get(key) + "");
			}
		}
		searchs.add("{actor}");
		replaces.add(empty(actors) ? "<a href=\"space.jsp?uid=" + feed.get("uid") + "\">"
				+ sNames.get(feed.get("uid")) + "</a>" : implode(actors, "、"));

		searchs.add("{app}");
		Map globalApp=(Map) request.getAttribute("globalApp");
		Map app = globalApp==null ? null : (Map)globalApp.get(feed.get("appid"));
		if (empty(app)) {
			replaces.add("");
		} else {
			replaces.add("<a href=\"" + app.get("url") + "\">" + app.get("name") + "</a>");
		}
		String title_template = (String) feed.get("title_template");
		title_template = title_template == null ? "" : title_template;
		for (int i = 0; i < replaces.size(); i++) {
			title_template = title_template.replace(searchs.get(i), replaces.get(i));
		}
		feed.put("title_template", mkTarget(title_template, sConfig));
		searchs.clear();
		replaces.clear();

		if (feed.get("body_data") != null && isArray(feed.get("body_data"))) {
			Map body_data = (Map) feed.get("body_data");
			Set keys = body_data.keySet();
			for (Object key : keys) {
				searchs.add("{" + key + "}");
				replaces.add(body_data.get(key) + "");
			}
		}
		feed.put("magic_class", "");
		if (feed.get("appid") != null && (Integer) feed.get("appid") > 0) {
			Map body_data = (Map) feed.get("body_data");
			if (body_data != null) {
				if (!empty(body_data.get("magic_color"))) {
					feed.put("magic_class", "magiccolor" + body_data.get("magic_color"));
				}
				if (!empty(body_data.get("magic_thunder"))) {
					feed.put("magic_class", "magicthunder");
				}
			}
		}

		searchs.add("{actor}");
		replaces.add("<a href=\"space.jsp?uid=" + feed.get("uid") + "\">" + feed.get("username") + "</a>");
		String body_template = (String) feed.get("body_template");
		body_template = body_template == null ? "" : body_template;
		for (int i = 0; i < replaces.size(); i++) {
			body_template = body_template.replace(searchs.get(i), replaces.get(i));
		}
		feed.put("body_template", mkTarget(body_template, sConfig));
		feed.put("body_general", mkTarget((String) feed.get("body_general"), sConfig));
		feed.put("icon_image", "image/icon/" + feed.get("icon") + ".gif");
		if (!Common.empty(sConfig.get("feedread")) && empty(feed.get("id"))) {
			Map<String, String> sCookie = (Map<String, String>) request.getAttribute("sCookie");
			String[] read_feed_ids = empty(sCookie.get("read_feed_ids")) ? null : sCookie
					.get("read_feed_ids").split(",");
			if (read_feed_ids != null && in_array(read_feed_ids, feed.get("feedid"))) {
				feed.put("style", " class=\"feedread\"");
			} else {
				feed.put("style", " onclick=\"readfeed(this, " + feed.get("feedid") + ");\"");
			}
		} else {
			feed.put("style", "");
		}
		if ((Integer) sConfig.get("feedtargetblank") > 0) {
			feed.put("target", " target=\"_blank\"");
		} else {
			feed.put("target", "");
		}
		if (in_array(new String[] {"blogid", "picid", "sid", "pid", "eventid"}, feed.get("idtype"))) {
			feed.put("showmanage", 1);
		}
		feed.put("thisapp", 0);
		if (feed.get("appid") != null
				&& (Integer) feed.get("appid") == intval(DomiHome.jchConfig.get("JC_APPID"))) {
			feed.put("thisapp", 1);
		}
		return feed;
	}
	public static int intval(String s) {
		return intval(s, 10);
	}
	
	public static int intval(String s, int radix) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		if (radix == 0) {
			radix = 10;
		} else if (radix < Character.MIN_RADIX) {
			return 0;
		} else if (radix > Character.MAX_RADIX) {
			return 0;
		}
		int result = 0;
		int i = 0, max = s.length();
		int limit;
		int multmin;
		int digit;
		boolean negative = false;
		if (s.charAt(0) == '-') {
			negative = true;
			limit = Integer.MIN_VALUE;
			i++;
		} else {
			limit = -Integer.MAX_VALUE;
		}
		if (i < max) {
			digit = Character.digit(s.charAt(i++), radix);
			if (digit < 0) {
				return 0;
			} else {
				result = -digit;
			}
		}
		multmin = limit / radix;
		while (i < max) {
			digit = Character.digit(s.charAt(i++), radix);
			if (digit < 0) {
				break;
			}
			if (result < multmin) {
				result = limit;
				break;
			}
			result *= radix;
			if (result < limit + digit) {
				result = limit;
				break;
			}
			result -= digit;
		}
		if (negative) {
			if (i > 1) {
				return result;
			} else {
				return 0;
			}
		} else {
			return -result;
		}
	}
	
	public static String mkTarget(String html, Map<String, Object> sConfig) {
		if (empty(html)) {
			return html;
		}
		if ((Integer) sConfig.get("feedtargetblank") > 0) {
			html = html.replaceAll("<a(.+?)href=([\\'\"]?)([^>\\s]+)\\2([^>]*)>",
					"<a target=\"_blank\" $1 href=\"$3\" $4>");
		}
		return html;
	}
	
	public static boolean empty(Object obj) {
		if (obj == null) {
			return true;
		} else if (obj instanceof String && (obj.equals("") || obj.equals("0"))) {
			return true;
		} else if (obj instanceof Number && ((Number) obj).doubleValue() == 0) {
			return true;
		} else if (obj instanceof Boolean && !((Boolean) obj)) {
			return true;
		} else if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
			return true;
		} else if (obj instanceof Map && ((Map) obj).isEmpty()) {
			return true;
		} else if (obj instanceof Object[] && ((Object[]) obj).length == 0) {
			return true;
		}
		return false;
	}
	
	public static String trim(String text) {
		if (text == null) {
			return "";
		}
		return text.trim();
	}
	
	public static String stripSlashes(String text) {
		if (text == null || text.equals("")) {
			return "";
		}
		StringBuffer sb = new StringBuffer(text.length());
		StringCharacterIterator iterator = new StringCharacterIterator(text);
		char character = iterator.current();
		while (character != CharacterIterator.DONE) {
			switch (character) {
				case '\'':
					sb.append("'");
					break;
				case '"':
					sb.append('"');
					break;
				case '\\':
					sb.append(iterator.next());
					break;
				default:
					sb.append(character);
					break;
			}
			character = iterator.next();
		}
		return sb.toString();
	}
	
	public static String htmlSpecialChars(String string) {
		return htmlSpecialChars(string, 1);
	}

	
	public static String htmlSpecialChars(String text, int quotestyle) {
		if (text == null || text.equals("")) {
			return "";
		}
		StringBuffer sb = new StringBuffer(text.length() * 2);
		StringCharacterIterator iterator = new StringCharacterIterator(text);
		char character = iterator.current();
		while (character != CharacterIterator.DONE) {
			switch (character) {
				case '&':
					sb.append("&amp;");
					break;
				case '<':
					sb.append("&lt;");
					break;
				case '>':
					sb.append("&gt;");
					break;
				case '"':
					if (quotestyle == 1 || quotestyle == 2) {
						sb.append("&quot;");
					} else {
						sb.append(character);
					}
					break;
				case '\'':
					if (quotestyle == 2) {
						sb.append("&#039;");
					} else {
						sb.append(character);
					}
					break;
				default:
					sb.append(character);
					break;
			}
			character = iterator.next();
		}
		return sb.toString();
	}
	
	public static Object sHtmlSpecialChars(Object obj) {
		if (obj instanceof String) {
			return htmlSpecialChars((String) obj).replaceAll(
					"&amp;((#(\\d{3,5}|x[a-fA-F0-9]{4})|[a-zA-Z][a-z0-9]{2,5});)", "&$1");
		} else if (obj instanceof Map) {
			Map temp = (Map) obj;
			Set<Object> keys = temp.keySet();
			for (Object key : keys) {
				temp.put(key, sHtmlSpecialChars(temp.get(key)));
			}
			return temp;
		} else if (obj instanceof List) {
			List temp = new ArrayList();
			for (Object str : (List) obj) {
				temp.add(sHtmlSpecialChars(str));
			}
			return temp;
		} else if (obj instanceof Object[]) {
			Object[] temp = (Object[]) obj;
			for (int i = 0; i < temp.length; i++) {
				temp[i] = sHtmlSpecialChars(temp[i]);
			}
			return temp;
		} else {
			return obj;
		}
	}

	public static Map<String, String> parseUrl(String url) {
		Map urlMap = new HashMap();
		try {
			URL u = new URL(url);

			String scheme = u.getProtocol();
			String host = u.getHost();
			int port = u.getPort() == -1 ? u.getDefaultPort() : u.getPort();
			String user = null;
			String pass = null;
			String path = u.getPath();
			String query = u.getQuery();
			String fragment = u.getRef();

			String user_password = u.getUserInfo();
			if (user_password != null && user_password.length() != 0) {
				String[] up = user_password.split(":");
				switch (up.length) {
					case 1:
						user = up[0];
						break;
					case 2:
						user = up[0];
						pass = up[1];
						break;
				}
			}
			if (host != null && host.length() != 0) {
				urlMap.put("host", host);
			}
			if (port != -1) {
				urlMap.put("port", port);
			}
			if (user != null) {
				urlMap.put("user", user);
			}
			if (pass != null) {
				urlMap.put("pass", pass);
			}
			if (path.length() != 0) {
				urlMap.put("path", path);
			}
			if (query != null) {
				urlMap.put("query", query);
			}
			if (fragment != null) {
				urlMap.put("fragment", fragment);
			}
		} catch (MalformedURLException e) {
		}
		return urlMap;
	}
	
}
