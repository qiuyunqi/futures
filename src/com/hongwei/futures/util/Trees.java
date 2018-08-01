package com.hongwei.futures.util;

import java.util.ArrayList;
import java.util.List;

public class Trees {

	private String id;
	private List<Trees> item;
	private String text;
	private String open;

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public List<Trees> getItem() {
		if(item==null){
			item=new ArrayList<Trees>();
		}
		return item;
	}

	public void setItem(List<Trees> item) {
		this.item = item;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
