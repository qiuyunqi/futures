package com.hongwei.futures.web.action.admin_list_dictionary;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuDictionary;
import com.hongwei.futures.service.FuDictionaryService;
import com.hongwei.futures.util.StringUtil;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminListDictionaryAction extends BaseAction{
	
	private FuAdmin admin;
	private Long adminId;
	private Integer totalCount;
	
	private Long id;
	private Long pid;
	private String dictionaryName;
	private Integer isEnabled;
	
	@Autowired
	private FuDictionaryService dictionaryService;
	
	/**
	 * 组织字典树形结构
	 * @return
	 * @throws Exception
	 */
	@Action("dicTree")
	public String dicTree() {
		try {
			String id = getHttpServletRequest().getParameter("id");
			Long longId = 0L;
			if(id == null) {
				longId = 0L;
			}else{
				longId = Long.valueOf(id);
			}
			String res = dictionaryService.findDictionaryTree(longId);
			if(res == null) {
				res = "";
			}
			write(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 新增修改树节点
	 * @return
	 * @throws Exception
	 */
	@Action("commitDictionaryAjax")
	public String commitDictionaryAjax()  {
		try{
			FuDictionary dictionary = new FuDictionary();
			if(id!=null){
				dictionary = dictionaryService.get(id);
			}
			if (StringUtil.isBlank(dictionaryName)) {
				write("-2");
				return null;
			}
			dictionary.setIsEnabled(isEnabled);
			dictionary.setName(dictionaryName);
			dictionary.setPid(pid);
			dictionaryService.save(dictionary);
			write("1");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 删除树节点
	 * @return
	 * @throws Exception
	 */
	@Action("delDictionary")
	public String delDictionary() {
		try{
			dictionaryService.delete(id);
			write("1");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 修改字典启用状态
	 * @return
	 * @throws Exception
	 */
	@Action("saveDictionaryAjax")
	public String saveDictionaryAjax()  {
		try{
			FuDictionary dictionary = dictionaryService.get(id);
			dictionary.setIsEnabled(isEnabled);
			dictionaryService.save(dictionary);
			write("1");
		}catch (Exception e) {
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

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getDictionaryName() {
		return dictionaryName;
	}

	public void setDictionaryName(String dictionaryName) {
		this.dictionaryName = dictionaryName;
	}

	public Integer getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Integer isEnabled) {
		this.isEnabled = isEnabled;
	}

}
