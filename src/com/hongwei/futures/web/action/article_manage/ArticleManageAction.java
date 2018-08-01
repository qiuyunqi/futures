package com.hongwei.futures.web.action.article_manage;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuArticle;
import com.hongwei.futures.model.FuDictionary;
import com.hongwei.futures.service.FuArticleService;
import com.hongwei.futures.service.FuDictionaryService;
import com.hongwei.futures.util.Constants;
import com.hongwei.futures.util.FileOperate;
import com.hongwei.futures.util.StringUtil;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class ArticleManageAction extends BaseAction {
	@Autowired
	private FuArticleService fuArticleService;
	@Autowired
	private FuDictionaryService fuDictionaryService;

	private Long id;
	private Integer totalCount;
	private FuAdmin admin;
	private Long adminId;
	private String productIco;
	private String title;
	private String hrefUrl;
	private String content;
	private Long dictionaryId;

	/**
	 * app文章管理
	 * 
	 * @return
	 */
	@Action("articleInfo")
	public String articleInfo() {
		try {
			List<FuArticle> articleList = fuArticleService.findArticleList((this.getPageNo() - 1) * this.getPageSize(), this.getPageSize());
			if (totalCount == null) {
				totalCount = fuArticleService.countArticle();
			}
			this.getActionContext().put("articleList", articleList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 修改新建app文章
	 * 
	 * @return
	 */
	@Action("newArticleAjax")
	public String newArticleAjax() {
		try {
			if (id != null) {
				FuArticle article = fuArticleService.get(id);
				this.getActionContext().put("article", article);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 保存app文章
	 * 
	 * @return
	 */
	@Action("saveArticleAjax")
	public String saveArticleAjax() {
		try {
			if (StringUtil.isBlank(title)) {
				write("-2");
				return null;
			}
			if (StringUtil.isBlank(hrefUrl)) {
				write("-3");
				return null;
			}
			if (StringUtil.isBlank(content)) {
				write("-4");
				return null;
			}
			content = new String(content.getBytes("ISO8859-1"), "UTF-8");
			FuArticle article = new FuArticle();
			if (id == null) {
				article.setTime(new Date());
				article.setFuAdmin(admin);
				article.setState(1);
			} else {
				article = fuArticleService.get(id);
			}
			article.setTitle(title);
			article.setHrefUrl(hrefUrl);
			article.setContent(content);
			article.setFuDictionary(fuDictionaryService.get(40L));
			String newPath = "";
			if (!StringUtil.isBlank(productIco)) {
				if (this.productIco.contains(Constants.DIR_TEMP) && !Constants.OSSEnable) {
					FileOperate op = new FileOperate();
					newPath = this.productIco.replace(Constants.DIR_TEMP, Constants.DIR_WX);
					if (!this.productIco.equals(newPath)) {
						op.moveFile(this.getServletContext().getRealPath(this.productIco), this.getServletContext().getRealPath(newPath));
					}
				} else {
					newPath = productIco;
				}
				article.setPic(productIco);
			}
			fuArticleService.save(article);
			write("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除app文章
	 * 
	 * @return
	 */
	@Action("deleteArticle")
	public String delParam() {
		try {
			fuArticleService.delete(id);
			write("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 客服帮助中心管理
	 * 
	 * @return
	 */
	@Action("articleHelpInfo")
	public String articleHelpInfo() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (totalCount == null) {
				totalCount = fuArticleService.getCount(map);
			}
			List<FuArticle> articleList = fuArticleService.findHelpList((this.getPageNo() - 1) * this.getPageSize(), this.getPageSize(), map);
			this.getActionContext().put("articleList", articleList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 修改新建帮助中心
	 * 
	 * @return
	 */
	@Action("newArticleHelp")
	public String newArticleHelp() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pid", 41L);
			List<FuDictionary> dictionaries = fuDictionaryService.findListByMap(0, Integer.MAX_VALUE, map);
			this.getActionContext().put("dictionaries", dictionaries);
			if (id != null) {
				FuArticle article = fuArticleService.get(id);
				this.getActionContext().put("article", article);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 保存帮助中心内容
	 * 
	 * @return
	 */
	@Action("saveArticleHelpAjax")
	public String saveArticleHelpAjax() {
		try {
			if (StringUtil.isBlank(title)) {
				write("-1");
				return null;
			}
			if (StringUtil.isBlank(content)) {
				write("-2");
				return null;
			}
			FuArticle article = new FuArticle();
			if (id == null) {
				article.setTime(new Date());
				article.setFuAdmin(admin);
				article.setState(1);
			} else {
				article = fuArticleService.get(id);
			}
			content = content.substring(0, content.length() - 2);
			article.setTitle(title);
			article.setContent(content);
			article.setFuDictionary(fuDictionaryService.get(dictionaryId));
			fuArticleService.save(article);
			write("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public FuAdmin getAdmin() {
		return admin;
	}

	public void setAdmin(FuAdmin admin) {
		this.admin = admin;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public String getProductIco() {
		return productIco;
	}

	public void setProductIco(String productIco) {
		this.productIco = productIco;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHrefUrl() {
		return hrefUrl;
	}

	public void setHrefUrl(String hrefUrl) {
		this.hrefUrl = hrefUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getDictionaryId() {
		return dictionaryId;
	}

	public void setDictionaryId(Long dictionaryId) {
		this.dictionaryId = dictionaryId;
	}

}
