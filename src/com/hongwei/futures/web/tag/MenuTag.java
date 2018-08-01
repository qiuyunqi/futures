package com.hongwei.futures.web.tag;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.jsp.tagext.TagSupport;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.SysPurview;
import com.hongwei.futures.util.StrutsUtil;


public class MenuTag extends TagSupport{
	
	private String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int doStartTag(){
		boolean result = false;  
		FuAdmin admin = (FuAdmin) StrutsUtil.getHttpSession().getAttribute("admin");
		if(admin.getType()==1){
			result = true;
		}else{
			Collection<String> privilegeNames = new ArrayList<String>();
			List<SysPurview> priviList = (List<SysPurview>) StrutsUtil.getHttpSession().getAttribute("priviList");
			if(priviList != null && priviList.size()!=0){
				for(SysPurview p:priviList){
					privilegeNames.add(p.getName());
				}
				if (!privilegeNames.contains(name)) {
					
				} else {
					for(String pName:privilegeNames){
						if(name.equals(pName)){
							result = true;
						}
					}
				}
			}
		}
        return result ? EVAL_BODY_INCLUDE : SKIP_BODY;  
	}
}
