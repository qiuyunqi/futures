package com.hongwei.futures.web.action.admin_list_yqb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAdmin;
import com.hongwei.futures.model.FuYjb;
import com.hongwei.futures.service.FuYjbService;
import com.hongwei.futures.util.Constants;
import com.hongwei.futures.util.FileOperate;
import com.hongwei.futures.util.StringUtil;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminListYqbAction extends BaseAction {
	@Autowired
	private FuYjbService fuYjbService;

	private Long id;
	private FuAdmin admin;
	private Long adminId;

	private String marketValue;
	private String available;
	private String productIco;
	private String agreement;
	private String rank;
	private String moreDetail;
	private String cumulativeVale;

	@Action("yqbInfo")
	public String yqbInfo() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			List<FuYjb> yqbList = fuYjbService.findYqbList(
					(this.getPageNo() - 1) * this.getPageSize(),
					this.getPageSize(), map);
			this.getActionContext().put("yqbList", yqbList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Action("newYqbAjax")
	public String newArticleAjax() {
		try {
			if (id != null) {
				FuYjb yqb = fuYjbService.get(id);
				this.getActionContext().put("yqb", yqb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Action("saveYqbAjax")
	public String saveYqbAjax() {
		try {
			if (StringUtil.isBlank(marketValue)) {
				write("-2");
				return null;
			}
			if (StringUtil.isBlank(available)) {
				write("-3");
				return null;
			}
			if (StringUtil.isBlank(cumulativeVale)) {
				write("-4");
				return null;
			}
			FuYjb yqb = null;
			if (id == null) {
				yqb = new FuYjb();
			} else {
				yqb = fuYjbService.get(id);
			}
			yqb.setMarketValue(marketValue);
			yqb.setAvailable(available);
			yqb.setCumulativeVale(cumulativeVale);
			if (!StringUtil.isBlank(agreement)) {
				yqb.setAgreement(agreement);
			}
			if (!StringUtil.isBlank(rank)) {
				yqb.setRank(rank);
			}
			if (!StringUtil.isBlank(moreDetail)) {
				yqb.setMoreDetail(moreDetail);
			}
			String newPath = "";
			if (!StringUtil.isBlank(productIco)) {
				if (this.productIco.contains(Constants.DIR_TEMP)
						&& !Constants.OSSEnable) {
					FileOperate op = new FileOperate();
					newPath = this.productIco.replace(Constants.DIR_TEMP,
							Constants.DIR_WX);
					if (!this.productIco.equals(newPath)) {
						op.moveFile(
								this.getServletContext().getRealPath(
										this.productIco),
								this.getServletContext().getRealPath(newPath));
					}
				} else {
					newPath = productIco;
				}
				yqb.setProfitImage(productIco);
			}
			fuYjbService.save(yqb);
			write("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Action("deleteYqb")
	public String deleteYqb() {
		try {
			fuYjbService.delete(id);
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

	public String getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(String marketValue) {
		this.marketValue = marketValue;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public String getProductIco() {
		return productIco;
	}

	public void setProductIco(String productIco) {
		this.productIco = productIco;
	}

	public String getAgreement() {
		return agreement;
	}

	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getMoreDetail() {
		return moreDetail;
	}

	public void setMoreDetail(String moreDetail) {
		this.moreDetail = moreDetail;
	}

	public String getCumulativeVale() {
		return cumulativeVale;
	}

	public void setCumulativeVale(String cumulativeVale) {
		this.cumulativeVale = cumulativeVale;
	}

}
