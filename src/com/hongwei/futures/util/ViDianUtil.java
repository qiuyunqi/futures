package com.hongwei.futures.util;

import net.sf.json.JSONObject;


public class ViDianUtil {
	
	private final static String APPKEY = "644763";
	private final static String SECRET = "936830c2a24f4d1a7703a30fb833bb4a";
	
	private final static String API_URL = "https://api.vdian.com/";
	
	@SuppressWarnings("all")
	public static JSONObject jsonToObj(Object obj) {
		JSONObject jsonData = new JSONObject().fromObject(obj);
		return jsonData;
	}
	
	public static String getToken() throws Exception{
		String url = API_URL+"token?grant_type=client_credential&appkey="+APPKEY+"&secret="+SECRET;
		String http = HttpClientUtil.getHTTP(url);

		JSONObject obj = jsonToObj(http);
		Object o = obj.getString("status");
		JSONObject jt = jsonToObj(o);
		String sc = jt.getString("status_code");
		if("0".equals(sc)) {
			Object oj = obj.getString("result");
			JSONObject re = jsonToObj(oj);
			String token = re.getString("access_token");
			return token;
		}else {
			return null;
		}
		
	}
	
	
	public static void main(String[] args) throws Exception {
		String token = getToken();
		System.out.println(token);
	}


}
