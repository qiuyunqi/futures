package com.hongwei.futures.web.action.app_manage;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuProduct;
import com.hongwei.futures.model.HhrAppTemplate;
import com.hongwei.futures.model.HhrAppVersion;
import com.hongwei.futures.model.WqqContents;
import com.hongwei.futures.service.FuProductService;
import com.hongwei.futures.service.HhrAppTemplateService;
import com.hongwei.futures.service.HhrAppVersionService;
import com.hongwei.futures.service.WqqContentsService;
import com.hongwei.futures.util.StringUtil;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AppManageAction extends BaseAction {
	private static final long serialVersionUID = 3264015101360118677L;

	@Autowired
	private HhrAppVersionService hhrAppVersionService;
	@Autowired
	private HhrAppTemplateService hhrAppTemplateService;
	@Autowired
	private FuProductService fuProductService;
	@Autowired
	private WqqContentsService wqqContentsService;

	private Long id;
	private Integer versionCode;
	private String versionName;
	private String downloadUrl;
	private String updateLog;
	private FuAdmin admin;
	private Long adminId;
	private String account;
	private String name;
	private String template;

	private Long productId;
	private Integer delStatus; // 删除
	private String productName;
	private String description;
	private String profit;
	private String profitDesc;
	private String icon;
	private Integer orderNum;

	private String adTitle;
	private String riseProfit;
	private String fallProfit;
	private String title;
	private Date endTime;
	private Long productCardId;
	private String infoHref;
	private Long contentsId;

	@Action("appManage")
	public String appManage() {
		try {
			List<HhrAppVersion> versionList = hhrAppVersionService
					.findVersionList(null);
			this.getActionContext().put("versionList", versionList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Action("newVersionAjax")
	public String newVersionAjax() {
		try {
			if (id != null) {
				HhrAppVersion version = hhrAppVersionService.get(id);
				this.getActionContext().put("version", version);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 保存版本记录
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveVersionAjax")
	public String saveVersionAjax() {
		try {
			HhrAppVersion version = hhrAppVersionService.get(id);
			if (versionCode == null) {
				write("-2");
				return null;
			}
			if (StringUtil.isBlank(versionName)) {
				write("-3");
				return null;
			}
			if (StringUtil.isBlank(downloadUrl)) {
				write("-4");
				return null;
			}
			version.setVersionCode(versionCode);
			version.setVersionName(versionName);
			version.setDownloadUrl(downloadUrl);
			version.setUpdateLog(updateLog);
			hhrAppVersionService.save(version);
			write("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Action("appTemplate")
	public String appTemplate() {
		try {
			HhrAppTemplate appTemp = hhrAppTemplateService.get(1L);
			this.getActionContext().put("appTemp", appTemp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Action("updateTemplateAjax")
	public String updateTemplateAjax() {
		try {
			HhrAppTemplate appTemp = hhrAppTemplateService.get(1L);
			this.getActionContext().put("appTemp", appTemp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Action("saveUpdateAppTemp")
	public String saveUpdateAppTemp() throws Exception {
		try {
			if (StringUtil.isBlank(template)) {
				write("-1");
				return null;
			}
			HhrAppTemplate appTemp = hhrAppTemplateService.get(1L);
			appTemp.setTemplate(template);
			appTemp.setUpdateAdmin(admin);
			appTemp.setUpdateTime(new Date());
			hhrAppTemplateService.save(appTemp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 产品列表
	 * 
	 * @return
	 * @throws Exception
	 */
	// 针对fu_producet
	@Action("productList")
	public String productList() {
		try {
			List<FuProduct> productList = fuProductService.findList();
			if (null == productList || productList.size() <= 0) {
				write("-1"); // 暂无产品
				return null;
			} else {
				this.getActionContext().put("productList", productList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 是否在前台显示产品
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("isDisplay")
	public String isDisplay() {
		try {
			if (null == productId) {
				write("-1"); // 缺少产品标识符
				return null;
			}
			if (null == delStatus) {
				write("-2"); // 缺少产品状态
				return null;
			}
			FuProduct fuProduct = fuProductService.get(productId);
			if (null == fuProduct) {
				write("-3"); // 该产品不存在
				return null;
			}
			fuProduct.setIsDelete(delStatus == 0 ? 1 : 0);
			fuProductService.save(fuProduct);
			write("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 进入添加/修改页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("toChangeOrder")
	public String toChangeOrder() {
		try {
			if (null != productId) { // 修改
				FuProduct fuProduct = fuProductService.get(productId);
				this.getActionContext().put("fuProduct", fuProduct);
			}
			// 查询微期权的期数
			List<WqqContents> wqqContents = wqqContentsService
					.findContentsByMap(0, Integer.MAX_VALUE,
							new HashMap<String, Object>());
			this.getActionContext().put("wqqContents", wqqContents);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 添加/修改顺序
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("changeOrder")
	public String changeOrder() {
		try {
			FuProduct fpd = null;
			fpd = null == productId ? new FuProduct() : fuProductService
					.get(productId);
			if (null == productName) {
				write("-1"); // 产品名称不能为空
				return null;
			}
			if (null == description) {
				write("-2"); // 产品描述不能为空
				return null;
			}
			if (null == profit) {
				write("-3"); // 产品收益不能为空
				return null;
			}
			if (null == profitDesc) {
				write("-4"); // 产品收益描述不能为空
				return null;
			}
			if (null == icon) {
				write("-5"); // 产品logo不能为空
				return null;
			}
			if (null == orderNum) {
				FuProduct fp = fuProductService.getMaxOrderNum();
				orderNum = null == fp ? 0 : fp.getOrderNum() + 1;
			}

			fpd.setName(productName);
			fpd.setDescription(description);
			fpd.setProfit(profit);
			fpd.setProfitDesc(profitDesc);
			fpd.setIcon(icon);
			fpd.setOrderNum(orderNum);

			fpd.setAdTitle(adTitle);
			fpd.setRiseProfit(riseProfit);
			fpd.setFallProfit(fallProfit);
			fpd.setTitle(title);
			fpd.setEndTime(endTime);
			fpd.setInfoHref(infoHref);
			if(null == contentsId) {
				fpd.setWqqContents(null);
			}else {
				fpd.setWqqContents(wqqContentsService.get(contentsId));
			}
			if (null == productCardId) {
				Long maxId = fuProductService.getMaxId();
				productCardId = maxId + 1;
			}

			fpd.setProductId(productCardId);
			fuProductService.save(fpd);
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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(Integer versionCode) {
		this.versionCode = versionCode;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public String getUpdateLog() {
		return updateLog;
	}

	public void setUpdateLog(String updateLog) {
		this.updateLog = updateLog;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(Integer delStatus) {
		this.delStatus = delStatus;
	}

	public HhrAppVersionService getHhrAppVersionService() {
		return hhrAppVersionService;
	}

	public void setHhrAppVersionService(
			HhrAppVersionService hhrAppVersionService) {
		this.hhrAppVersionService = hhrAppVersionService;
	}

	public HhrAppTemplateService getHhrAppTemplateService() {
		return hhrAppTemplateService;
	}

	public void setHhrAppTemplateService(
			HhrAppTemplateService hhrAppTemplateService) {
		this.hhrAppTemplateService = hhrAppTemplateService;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProfit() {
		return profit;
	}

	public void setProfit(String profit) {
		this.profit = profit;
	}

	public String getProfitDesc() {
		return profitDesc;
	}

	public void setProfitDesc(String profitDesc) {
		this.profitDesc = profitDesc;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public FuProductService getFuProductService() {
		return fuProductService;
	}

	public void setFuProductService(FuProductService fuProductService) {
		this.fuProductService = fuProductService;
	}

	public String getAdTitle() {
		return adTitle;
	}

	public void setAdTitle(String adTitle) {
		this.adTitle = adTitle;
	}

	public String getRiseProfit() {
		return riseProfit;
	}

	public void setRiseProfit(String riseProfit) {
		this.riseProfit = riseProfit;
	}

	public String getFallProfit() {
		return fallProfit;
	}

	public void setFallProfit(String fallProfit) {
		this.fallProfit = fallProfit;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getInfoHref() {
		return infoHref;
	}

	public void setInfoHref(String infoHref) {
		this.infoHref = infoHref;
	}

	public Long getProductCardId() {
		return productCardId;
	}

	public void setProductCardId(Long productCardId) {
		this.productCardId = productCardId;
	}

	public Long getContentsId() {
		return contentsId;
	}

	public void setContentsId(Long contentsId) {
		this.contentsId = contentsId;
	}

}
