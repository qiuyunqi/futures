package com.hongwei.futures.util;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class VideoCatchUtil {
	
	public static String HOST_REGX="(?i)(youku.com|ku6.com|sina.com.cn|tudou.com|umiwi.com|163.com|ifeng.com|my.tv.sohu.com|qiyi.com|56.com|tv.sohu.com)";
	
	public static String getRealLink(String host,String flashvar){
		if(host.equals("youku.com"))
			return "http://player.youku.com/player.php/sid/FLASHVAR/v.swf".replace("FLASHVAR", flashvar);
		else if(host.equals("tudou.com")){
			return "http://www.tudou.com/FLASHVAR/v.swf".replace("FLASHVAR", flashvar);
		}else if(host.equals("ku6.com"))
			return "http://player.ku6.com/refer/FLASHVAR/v.swf".replace("FLASHVAR", flashvar);
		else if(host.equals("umiwi.com"))
			return "http://www.umiwi.com/video/FLASHVAR.swf".replace("FLASHVAR", flashvar);
		else if(host.equals("sina.com.cn"))
			return "http://you.video.sina.com.cn/api/sinawebApi/outplayrefer.php/vid=FLASHVAR/s.swf".replace("FLASHVAR", flashvar);
		else if(host.equals("ifeng.com"))
			return "http://v.ifeng.com/include/exterior.swf?guid=FLASHVAR&AutoPlay=false".replace("FLASHVAR", flashvar);
		else if(host.equals("my.tv.sohu.com"))
			return "http://share.vrs.sohu.com/my/v.swf&topBar=1&id=FLASHVAR".replace("FLASHVAR", flashvar);
		else if(host.equals("tv.sohu.com"))
			return "http://share.vrs.sohu.com/FLASHVAR/v.swf".replace("FLASHVAR", flashvar);
		else if(host.equals("qiyi.com"))
			return "http://player.video.qiyi.com/FLASHVAR".replace("FLASHVAR", flashvar);
		else if(host.equals("56.com"))
			return "http://player.56.com/FLASHVAR.swf".replace("FLASHVAR", flashvar);
		else
			return flashvar;
	}
	
	public static Integer getViewNum(Document doc,String host) throws Exception{
		Integer viewNum = ((Double) (Math.random() * 1000)).intValue();
		return viewNum;
	}
	
	public static String getflash(String link, String host,String content) {
		String returnString = "";
		if ("youku.com".equals(host)) {
			String regex = "id\\_(\\w+)[=.]";
			String matcher = getMatcherString(regex, link);
			if (!Common.empty(matcher)) {
				returnString = matcher;
			}
		} else if ("ku6.com".equals(host)) {
			String regex = ".*/([\\S^/]*?)\\.html";
			String matcher = getMatcherString(regex, link);
			if (!Common.empty(matcher)) {
				returnString = matcher;
			} else {
				regex = "v=(.*)";
				matcher = getMatcherString(regex, link);
				if (!Common.empty(matcher)) {
					returnString = matcher;
				}
			}
		} else if ("sina.com.cn".equals(host)) {
			String regex = "vid\\s?:\\s?\'(\\d+)\'"; //vid :'93740323'
			String matcher = getMatcherString(regex, content);
			if (!Common.empty(matcher)) {
				returnString = matcher;
				returnString=returnString.replace("/s.swf", "");
			}
		} else if ("tudou.com".equals(host)) {
			if(link.indexOf("programs")!=-1){
				String regx="http://www.tudou.com/programs/view/(.+)";
				String matcher=getMatcherString(regx,link);
				returnString=matcher;
				if(matcher.indexOf("/?")!=-1){
					returnString=matcher.substring(0,matcher.indexOf("/?"));
				}
				if(matcher.endsWith("/")){
					returnString=matcher.substring(0,matcher.length()-1);
				}
				returnString="v/"+returnString;
			}else{
//				HGye8_Gx_xE/&iid=18663645&resourceId=0_04_05_99
				String regx1="acode\\s?='([^']*)'";
				String regx2="iid:\\s?(\\d+)";
				String matcher=getMatcherString(regx1,content);
				if (!Common.empty(matcher)) {
					returnString = matcher+"/&iid=";
					matcher=getMatcherString(regx2,content);
					if (!Common.empty(matcher)) {
						returnString+=matcher+"&resourceId=0_04_05_99";
					}
					returnString="a/"+returnString;
				}else{
					String regx3="lcode\\s?=\\s?'([^']*)'";
					matcher=getMatcherString(regx3,content);
					if (!Common.empty(matcher)) {
						returnString = matcher+"/&iid=";
					}
					matcher=getMatcherString(regx2,content);
					if (!Common.empty(matcher)) {
						returnString+=matcher+"&resourceId=0_04_05_99";
					}
					returnString="l/"+returnString;
				}
				
			}
		} else if ("umiwi.com".equals(host)) {
			returnString = link.split("/")[5].substring(0,link.split("/")[5].indexOf("."));
		} else if ("163.com".equals(host)) {
			String regex = "new flashPlayer\\(\"([^\"]*?)\",\'\\d+%\',\'\\d+%\'\\);";
			String matcher = getMatcherString(regex, content);
			if (!Common.empty(matcher)) {
				returnString = matcher;
			}else{
				regex="[^p]src:\\s?'([^']*)'";
				matcher=getMatcherString(regex, content);
				if(!Common.empty(matcher)){
					returnString=matcher;
				}
			}
		} else if ("ifeng.com".equals(host)) {
			String regex = "/([^/]*?)\\.shtml";
			String matcher = getMatcherString(regex, link);
			if (!Common.empty(matcher)) {
				returnString = matcher;
			}
		} else if ("my.tv.sohu.com".equals(host)) {
			String regex ="\"id\"\\s?:\\s?\"(\\d+)\""; //"vid\\s?=\\s?'([^']*)'"; 
			String matcher = getMatcherString(regex, content);
			if (!Common.empty(matcher)) {
				returnString = matcher;
			}
		} else if ("tv.sohu.com".equals(host)) {
			String regex = "vid=\"([^\"]*)\"";
			String matcher = getMatcherString(regex, content);
			if (!Common.empty(matcher)) {
				returnString = matcher;
			}
		} else if ("qiyi.com".equals(host)) {
			String regex = "\"videoId\"\\s?:\\s?\"([^\"]*)\"";
			String matcher = getMatcherString(regex, content);
			if (!Common.empty(matcher)) {
				returnString = matcher;
			}
		} else if ("56.com".equals(host)) {
			String regex = "/([^/]+)\\.html";
			String matcher = getMatcherString(regex, link);
			if (!Common.empty(matcher)) {
				returnString = matcher;
			}
		}
		return returnString;
	}

	public static String getflashimg(String link, String host,String content) throws IOException {
		String returnString = "";
		if ("tudou.com".equals(host)) {
			String regex = "lpic\\s*=\\s*\"([^\"]*?)\"";
			String matcher = getMatcherString(regex, content);
			if (!Common.empty(matcher)) {
				returnString = matcher;
			}else{
				regex="pic:\\s?\"([^\"]*)\"";
				matcher = getMatcherString(regex, content);
				if (!Common.empty(matcher)) {
					returnString = matcher;
				}else{
					regex="pic:\\s*'([^']*)'";
					matcher = getMatcherString(regex, content);
					if (!Common.empty(matcher)) {
						returnString = matcher;
					}
				}
			}
		} else if ("youku.com".equals(host)) {
			String regex = "screenshot=([^\"]*?)\"";
			String matcher = getMatcherString(regex, content);
			if (!Common.empty(matcher)) {
				returnString = matcher;
			}
		} else if ("ku6.com".equals(host)) {
			String regex = "cover\\s*:\\s*\"([^\"]*?)\"";
			String matcher = getMatcherString(regex, content);
			if (!Common.empty(matcher)) {
				returnString = matcher;
			} else {
				regex = "\"cover\"\\s*:\\s*\"([^\"]*?)\"";
				matcher = getMatcherString(regex, content);
				if (!Common.empty(matcher)) {
					returnString = "http://" + matcher.replace("\\", "");
				}
			}
		} else if ("sina.com.cn".equals(host)) {
			String regex = "pic\\s?:\\s?\'([^']*?)\'";
			String matcher = getMatcherString(regex, content);
			if (!Common.empty(matcher)) {
				returnString = matcher;
			}
			if(returnString.equals("")){
				Document doc = Jsoup.parse(content);
				returnString =doc.select(".container2").size()>0?doc.select(".container2").first().select("img").first().attr("src"):"";
			}
		} else if ("umiwi.com".equals(host)) {
			String regex = "<meta property=\"og:image\" content=\"([^\"]*?)\" />";
			String matcher = getMatcherString(regex, content);
			if (!Common.empty(matcher)) {
				returnString = matcher;
			}
		} else if ("163.com".equals(host)) {
			Document doc = Jsoup.parse(content);
			if (doc.select(".introduceArea-img") != null
					&& doc.select(".introduceArea-img").size() > 0) {
				Element ele = doc.select(".introduceArea-img").first();
				returnString = ele.attr("src");
			}
			if (doc.select("#playerArea_contentBar") != null
					&& doc.select("#playerArea_contentBar").size() > 0) {
				Element ele = doc.select("#playerArea_contentBar").first()
						.select(".on").first().select("img").first();
				returnString = ele.attr("src");
			}
		} else if ("ifeng.com".equals(host)) {
			String regex = "\"img\": \"([^\"]*)";
			String matcher = getMatcherString(regex, content);
			if (!Common.empty(matcher)) {
				returnString = matcher;
			}
		} else if ("tv.sohu.com".equals(host)) {
			String regex = "cover=\"([^\"]*)";
			String matcher = getMatcherString(regex, content);
			if (!Common.empty(matcher)) {
				returnString = matcher;
			}
		} else if ("my.tv.sohu.com".equals(host)) {
			String regex = "\"avatar\"\\s?:\\s?\"([^\"]*)"; ;
			String matcher = getMatcherString(regex, content);
			if (!Common.empty(matcher)) {
				returnString = matcher;
			}
		} else if ("qiyi.com".equals(host)) {
			Document doc = Jsoup.parse(content);
			Element ele = doc.select(".amList_lt_con").first().select("img").first();
			returnString = ele.attr("data-lazy");
		} else if ("56.com".equals(host)) {
			String regex = "\"img\"\\s?:\\s?\"([^\"]*)";
			String matcher = getMatcherString(regex, content);
			if (!Common.empty(matcher)) {
				returnString = matcher.replace("\\", "");
			}
		}
		return returnString;
	}

	public static String getMatcherString(String regex, String input) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (matcher.find()) {
			return matcher.group(1);
		}
		return null;
	}
}
