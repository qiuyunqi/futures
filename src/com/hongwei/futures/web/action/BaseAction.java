package com.hongwei.futures.web.action;

import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.hongwei.futures.util.Constants;
import com.hongwei.futures.util.Trees;
import com.hongwei.futures.util.WebContent;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction extends ActionSupport {

	protected final Log log = LogFactory.getLog(getClass());
	private static final long serialVersionUID = 4363572172849229047L;

	private int pageNo = 0;
	private int pageSize = Constants.DEFAULT_PAGE_SIZE;
	// 没有权限
	public static final String NOPERMIT = "nopermission";
	// 不存在
	public static final String NOTEXIST = "not_exist";
	// 系统关闭
	public static final String SYSCLOSED = "sys_close";
	// 未激活
	public static final String UNCHECKED = "unchecked";
	// 被屏蔽
	public static final String BLACK = "black";
	// 后台未登录
	public static final String NOLOGIN = "no_login";
	// 当前被选择的用户ID
	protected Long usrId;
	// 学校ID
	protected Long schId;
	// 权限
	protected Integer permission;
	// 新手
	protected Boolean newHand;
	// 用户引导的key
	protected String tipKey;
	// 用户编辑器里面上传的图片
	protected String[] upImgs;

	public String getShortUrl(String url_long) throws Exception {
		String apiKey = "3779875927";// 要修改这里的key再测试哦
		String apiUrl = "http://api.t.sina.com.cn/short_url/shorten.json?source=$1&url_long=$2";
		String response = WebContent.getContent(apiUrl.replace("$1", apiKey)
				.replace("$2", URLEncoder.encode(url_long, "utf-8")), "utf-8");
		JSONArray array=JSONArray.fromObject(response);
		return array.getJSONObject(0).getString("url_short");
	}

	/**
	 * 获取树节点
	 * 
	 * @param id
	 * @param name
	 * @param open
	 * @param hasSub
	 * @return
	 */
	protected Trees getTreeNode(String id, String name, String open,
			Boolean hasSub) {
		Trees tree = new Trees();
		tree.setId(id);
		tree.setText(name);
		tree.setOpen(open);
		if (hasSub) {
			Trees tmp = new Trees();
			tmp.setId("-1");
			tree.getItem().add(tmp);
		}
		return tree;
	}

	/**
	 * 
	 * 获得Session
	 */
	public Map<String, Object> getSession() {
		return ActionContext.getContext().getSession();
	}

	/**
	 * 
	 * 获得ActionContext
	 */
	public ActionContext getActionContext() {
		return ActionContext.getContext();
	}

	/**
	 * 获得HttpServletRequest
	 */
	public HttpServletRequest getHttpServletRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 获得HttpServletResponse
	 */
	public HttpServletResponse getHttpServletResponse() {
		return ServletActionContext.getResponse();
	}

	public HttpSession getHttpSession() {
		return getHttpServletRequest().getSession();
	}

	/**
	 * 获得ServletContext
	 */
	public ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}

	/**
	 * 输出字符串
	 * 
	 * @param message
	 * @throws Exception
	 */
	protected void write(String message) throws Exception {
		getHttpServletResponse().setContentType("text/html");
		getHttpServletResponse().setCharacterEncoding("utf-8");
		getHttpServletResponse().getWriter().write(message);
		getHttpServletResponse().getWriter().flush();
	}
	
	/**
	 * 输出字符串
	 * 
	 * @param message
	 * @throws Exception
	 */
	protected void writeJson(Object obj) throws Exception {
		getHttpServletResponse().setContentType("text/json; charset=utf-8");
		getHttpServletResponse().setHeader("Cache-Control", "no-cache"); //取消浏览器缓存
		PrintWriter out = getHttpServletResponse().getWriter();
		out.print(obj);
		out.flush();
		out.close();
	}

	// ============================= getter and setter
	// =================================
	public int getPageNo() {
		return Math.max(1, pageNo);
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPermission(Integer permission) {
		this.permission = permission;
	}

	public Integer getPermission() {
		return permission;
	}

	public void setUsrId(Long usrId) {
		this.usrId = usrId;
	}

	public Long getUsrId() {
		return usrId;
	}

	public void setNewHand(Boolean newHand) {
		this.newHand = newHand;
	}

	public Boolean getNewHand() {
		return newHand;
	}

	public void setTipKey(String tipKey) {
		this.tipKey = tipKey;
	}

	public String getTipKey() {
		return tipKey;
	}

	public void setUpImgs(String[] upImgs) {
		this.upImgs = upImgs;
	}

	public String[] getUpImgs() {
		return upImgs;
	}

	public void setSchId(Long schId) {
		this.schId = schId;
	}

	public Long getSchId() {
		return schId;
	}
}
