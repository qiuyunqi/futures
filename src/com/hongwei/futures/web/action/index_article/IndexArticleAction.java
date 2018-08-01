package com.hongwei.futures.web.action.index_article;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuArticle;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuArticleService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("fu_common")
public class IndexArticleAction extends BaseAction {
	@Autowired
	private FuArticleService fuArticleService;

	private FuUser fuUser;
	private Long userId;
	private Long id;
	private Integer totalCount;

	/**
	 * 文章列表
	 * 
	 * @return
	 */
	@Action("articleList")
	public String articleList() {
		try {
			if (totalCount == null)
				totalCount = fuArticleService.countArticle();
			List<FuArticle> articleList = fuArticleService.findArticleList(
					(this.getPageNo() - 1) * this.getPageSize(),
					this.getPageSize());
			this.getActionContext().put("articleList", articleList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 查看文章
	 * 
	 * @return
	 */
	@Action("articleDetail")
	public String articleDetail() {
		try {
			FuArticle article = fuArticleService.get(id);
			this.getActionContext().put("article", article);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public FuUser getFuUser() {
		return fuUser;
	}

	public void setFuUser(FuUser fuUser) {
		this.fuUser = fuUser;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

}
