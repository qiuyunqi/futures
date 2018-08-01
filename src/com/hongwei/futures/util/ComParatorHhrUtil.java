package com.hongwei.futures.util;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
/**
 * 合伙人列表的排序
 * @author han
 *
 */
@SuppressWarnings("all")
public class ComParatorHhrUtil implements Comparator{

	public int compare(Object obj1, Object obj2) {
		HashMap<String, Object> map1 = (HashMap<String, Object>) obj1;
		HashMap<String, Object> map2 = (HashMap<String, Object>) obj2;
		int flag = ((Integer) map1.get("num")).compareTo((Integer) map2.get("num"));
		return flag;
	}

}
