package com.hongwei.futures.util;

import java.io.IOException;
import java.net.URLEncoder;

import net.sf.json.JSONObject;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

public class CatchPageInfo {
	public JSONObject getPageInfo(String inputStr) throws ClientProtocolException, IOException{
		JSONObject jsonObject = new JSONObject();
		HttpGet get = new HttpGet("http://www.simsimi.com/talk.htm");
		get.setHeader("Host","www.simsimi.com");
		HttpResponse response = getHttpClient().execute(get);
		String cookies="";
		if(response.getEntity()!=null){
			Header[] headers = response.getHeaders("Set-Cookie");
			for(int i=0;i<headers.length;i++){
				if(headers[i].getValue().contains("JSESSIONID")){
					cookies = headers[i].getValue().split(";")[0];
				}
			}
		}
		
		inputStr=URLEncoder.encode(URLEncoder.encode(inputStr));
		
		HttpGet httpGet = new HttpGet("http://www.simsimi.com/func/req?msg="+inputStr+"&lc=zh");
		httpGet.setHeader("Host","www.simsimi.com");
		httpGet.setHeader("Cookie",cookies);
		httpGet.setHeader("Referer","http://www.simsimi.com/talk.htm");
		
		HttpResponse res = getHttpClient().execute(httpGet);
		if(res.getEntity()!=null){
			Header[] headers = res.getHeaders("Set-Cookie");
			for(int i=0;i<headers.length;i++){
				if(headers[i].getValue().contains("JSESSIONID")){
					cookies = headers[i].getValue().split(";")[0];
				}
			}
		}
		jsonObject=JSONObject.fromObject(EntityUtils.toString(res.getEntity()));
		return jsonObject;
	}
	
	/**
	 * 获得httpclient实例
	 * 
	 * @return
	 */
	private HttpClient getHttpClient() {
		HttpParams params = new BasicHttpParams();
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setUseExpectContinue(params, true);
		HttpProtocolParams
				.setUserAgent(
						params,
						"Mozilla/5.0(Linux;U;Android 2.2.1;en-us;Nexus One Build.FRG83) AppleWebKit/553.1(KHTML,like Gecko) Version/4.0 Mobile Safari/533.1");
		return new DefaultHttpClient(params);
	}
}
