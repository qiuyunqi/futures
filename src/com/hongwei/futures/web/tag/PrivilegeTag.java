package com.hongwei.futures.web.tag;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.jsp.tagext.TagSupport;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.SysPurview;
import com.hongwei.futures.util.StrutsUtil;

public class PrivilegeTag extends TagSupport {

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public int doStartTag() {
		boolean result = false;
		FuAdmin admin = (FuAdmin) StrutsUtil.getHttpSession().getAttribute("admin");
		List<SysPurview> priviList = (List<SysPurview>) StrutsUtil.getHttpSession().getAttribute("priviList");
		if (null != admin) {
			if (admin.getType() == 1) {
				result = true;
			} else {
				int pos = url.indexOf("?");
				if (pos > -1) {
					url = url.substring(0, pos);
				}
				Collection<String> privilegeUrls = new ArrayList<String>();
				for (SysPurview p : priviList) {
					privilegeUrls.add(p.getUrl());
				}
				if (!privilegeUrls.contains(url)) {

				} else {
					for (String pUrl : privilegeUrls) {
						if (url.equals(pUrl)) {
							result = true;
						}
					}
				}
			}
		}
		return result ? EVAL_BODY_INCLUDE : SKIP_BODY;
	}
}
