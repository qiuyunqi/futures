package com.hongwei.futures.web.action.index_guide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuArticle;
import com.hongwei.futures.model.FuDictionary;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuArticleService;
import com.hongwei.futures.service.FuDictionaryService;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("fu_common")
public class IndexGuideAction extends BaseAction {
	@Autowired
	private FuArticleService fuArticleService;
	@Autowired
	private FuDictionaryService fuDictionaryService;

	private FuUser fuUser;
	private Long userId;

	/**
	 * 新手指引
	 * 
	 * @return
	 */
	@Action("newGuide")
	public String newGuide() {
		return SUCCESS;
	}

	/**
	 * 申请流程
	 * 
	 * @return
	 */
	@Action("applyProgress")
	public String applyProgress() {
		try {
			this.getActionContext().put("jsptype", "applyProgress");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 软件下载
	 * 
	 * @return
	 */
	@Action("softwareDownload")
	public String softwareDownload() {
		try {
			this.getActionContext().put("jsptype", "softwareDownload");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 资金安全
	 * 
	 * @return
	 */
	@Action("moneySafe")
	public String moneySafe() {
		try {
			this.getActionContext().put("jsptype", "moneySafe");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 关于我们
	 * 
	 * @return
	 */
	@Action("aboutUs")
	public String aboutUs() {
		try {
			this.getActionContext().put("jsptype", "aboutUs");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 公司简介
	 * 
	 * @return
	 */
	@Action("intrCompany")
	public String intrCompany() {
		try {
			this.getActionContext().put("jsptype", "intrCompany");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 公司文化
	 * 
	 * @return
	 */
	@Action("culturecompany")
	public String culturecompany() {
		try {
			List<FuDictionary> dictionaries = fuDictionaryService.getByPid(41L, 1);// 得到帮助中心下面的字典
			List<Object> list = new ArrayList<Object>();
			if (dictionaries.size() > 0) {
				for (int i = 0; i < dictionaries.size(); i++) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("dictionaryId", dictionaries.get(i).getId());
					List<FuArticle> articlelist = fuArticleService.findHelpListByMap(map);
					List<Object> objList = new ArrayList<Object>();
					objList.add(dictionaries.get(i).getName());
					objList.add(articlelist);
					list.add(objList);
				}
			}
			this.getActionContext().put("list", list);
			this.getActionContext().put("dictionaries", dictionaries);
			this.getActionContext().put("jsptype", "culturecompany");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 关于我们
	 * 
	 * @return
	 */
	@Action("map")
	public String map() {
		try {
			this.getActionContext().put("jsptype", "map");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 期货大赛页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("qhGame")
	public String qhGame() {
		return SUCCESS;
	}

	/**
	 * 期货大赛页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("qhInformation")
	public String qhInformation() {
		return SUCCESS;
	}

	/**
	 * 解套者联盟APP推广
	 * 
	 * @return
	 */
	@Action("stockHelp")
	public String stockHelp() {
		return SUCCESS;
	}

	/**
	 * 解套者联盟网站推广
	 * 
	 * @return
	 */
	@Action("stockExpand")
	public String stockExpand() {
		try {
			this.getActionContext().put("jsptype", "stockIndex");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * app帮助中心
	 */
	@Action("appClientHelp")
	public String appClientHelp() {
		try {
			List<FuDictionary> dictionaries = fuDictionaryService.getByPid(41L, 1);// 得到帮助中心下面的字典
			List<Object> list = new ArrayList<Object>();
			if (dictionaries.size() > 0) {
				for (int i = 0; i < dictionaries.size(); i++) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("dictionaryId", dictionaries.get(i).getId());
					List<FuArticle> articlelist = fuArticleService.findHelpListByMap(map);
					List<Object> objList = new ArrayList<Object>();
					objList.add(dictionaries.get(i).getName());
					objList.add(articlelist);
					list.add(objList);
				}
			}
			this.getActionContext().put("list", list);
			this.getActionContext().put("dictionaries", dictionaries);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * T学院
	 */
	@Action("tCollege")
	public String tCollege() {
		try {
			this.getActionContext().put("jsptype", "stockIndex");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Action("agreement_hhr")
	public String agreement_hhr() {
		this.getActionContext().put("jsptype", "agreement_hhr");
		return SUCCESS;
	}

	@Action("agreement_wqq")
	public String agreement_wqq() {
		this.getActionContext().put("jsptype", "agreement_wqq");
		return SUCCESS;
	}

	@Action("agreement_yqbClear")
	public String agreement_yqbClear() {
		this.getActionContext().put("jsptype", "agreement_yqbClear");
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
}
