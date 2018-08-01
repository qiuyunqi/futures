package com.hongwei.futures.web.tag;

import javax.servlet.jsp.tagext.TagSupport;



public class PageParameterTag extends TagSupport{
	private String name;
	private String value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public int doEndTag(){
		PaginationTag paginationTag=(PaginationTag)TagSupport.findAncestorWithClass(this, PaginationTag.class);
		TagParameter tempParam=new TagParameter();
		tempParam.setName(name);
		tempParam.setValue(value);
		paginationTag.getTagParams().add(tempParam);
		return EVAL_PAGE;
	}
}
