package com.hongwei.futures.web.filter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.hongwei.futures.util.StringUtil;

@SuppressWarnings("serial")
public class DomainNameFilter extends HttpServlet implements Filter{

    protected FilterConfig filterConfig = null; //创建一个过滤器配置对象
    
	/**
	 * 初始化
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig; //初始化过滤器的配置
	}
    
    /**
     * 执行
     */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest=(HttpServletRequest)request;
		String req_uri=httpServletRequest.getRequestURL().toString();
		if(httpServletRequest.getRequestURI().equals("/")){
			String regx="http://([^\\.]*)\\.csia\\.cc/";
			Pattern p=Pattern.compile(regx);
			Matcher m=p.matcher(req_uri);
			if(m.find()){
				String domin=m.group(1);
				if(!StringUtil.isBlank(domin)){
					if(!domin.equalsIgnoreCase("weixin")&&!domin.equalsIgnoreCase("www")&&!domin.equalsIgnoreCase("mail")){
						httpServletRequest.getRequestDispatcher("/coffee_house_index/houseDomain.htm?domainName="+domin).forward(request, response);
						return;
					}
				}
			}
		}
		chain.doFilter(request, response);
	}
	
	
	/**
    * 销毁
    */
    @Override
	public void destroy() {
          this.filterConfig = null;
    }
    

}
