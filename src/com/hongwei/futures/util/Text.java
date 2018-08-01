package com.hongwei.futures.util;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public class Text {
	public static void main(String[] args) {
		CatchPageInfo cpi=new CatchPageInfo();
		try {
			System.out.print(cpi.getPageInfo("哈哈").toString());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
