package com.hongwei.futures.util;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

@SuppressWarnings("all")
public class ComParatorUtil implements Comparator{

	public int compare(Object obj1, Object obj2) {
		HashMap<String, Object> map1 = (HashMap<String, Object>) obj1;
		HashMap<String, Object> map2 = (HashMap<String, Object>) obj2;
		int flag = ((String) map1.get("trade_time")).compareTo((String) map2.get("trade_time"));
		return flag;
	}

}
