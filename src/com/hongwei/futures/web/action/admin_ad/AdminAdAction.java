package com.hongwei.futures.web.action.admin_ad;

import java.util.HashMap;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongwei.futures.model.FuAd;
import com.hongwei.futures.model.FuProduct;
import com.hongwei.futures.service.FuAdService;
import com.hongwei.futures.service.FuProductService;
import com.hongwei.futures.util.StringUtil;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("admin")
public class AdminAdAction extends BaseAction {

	private static final long serialVersionUID = 8681907197623641145L;

	@Autowired
	private FuAdService fuAdService;
	@Autowired
	private FuProductService fuProductService;

	private Long id;
	private String imageUrl;
	private String imageAttr;
	private String hrefUrl;
	private Integer orderNum;
	private Long fuProductId;
	private Integer isDelete;
	private Integer posit;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImageAttr() {
		return imageAttr;
	}

	public void setImageAttr(String imageAttr) {
		this.imageAttr = imageAttr;
	}

	public String getHrefUrl() {
		return hrefUrl;
	}

	public void setHrefUrl(String hrefUrl) {
		this.hrefUrl = hrefUrl;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getPosit() {
		return posit;
	}

	public void setPosit(Integer posit) {
		this.posit = posit;
	}

	public Long getFuProductId() {
		return fuProductId;
	}

	public void setFuProductId(Long fuProductId) {
		this.fuProductId = fuProductId;
	}

	/**
	 * 查询询所有的广告位
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("adList")
	public String adList() throws Exception {
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			List<FuAd> adList = fuAdService.findAll((this.getPageNo() - 1) * this.getPageSize(), this.getPageSize(), map);
			for (FuAd fuAd : adList) {
				int position = fuAd.getPosition();
				if (position == 1) {
					fuAd.setPosName("首页广告位");
				} else if (position == 2) {
					fuAd.setPosName("看跌涨广告位");
				} else {
					fuAd.setPosName("未知广告位");
				}
			}
			getActionContext().put("adList", adList);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}

	}

	/**
	 * 进入修改广告位页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("newAdAjax")
	public String newAd() throws Exception {
		try {
			if (null != id) {
				FuAd ad = fuAdService.get(id);
				getActionContext().put("ad", ad);
			}
			// 查询全部的产品
			List<FuProduct> productList = fuProductService.findList();
			getActionContext().put("productList", productList);
			/*
			 * FuAd maxOrderAd = fuAdService.getMaxOrderAd();
			 * getActionContext().put("maxOrderNum", maxOrderAd.getOrderNum());
			 */
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * 修改或者添加广告
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveAdAjax")
	public String saveAd() throws Exception {
		try {
			if (StringUtil.isBlank(imageUrl)) {
				write("-2"); // 没有输入图片地址
				return null;
			}
			// 顺序
			/*
			 * if(null == orderNum) { ad.setOrderNum(orderNum); }else { //
			 * 查询最后一个广告数据 获取他的orderNum FuAd fuAd = fuAdService.getMaxOrderAd();
			 * if(null == fuAd) { ad.setOrderNum(0); }else {
			 * ad.setOrderNum(fuAd.getOrderNum()+1); } }
			 */
			if (null == orderNum) {
				FuAd fuAd = fuAdService.getMaxOrderAd();
				orderNum = null == fuAd ? 0 : (fuAd.getOrderNum() + 1);
			}
			FuAd ad = null;
			/*
			 * if(null == id) { // 添、 ad = new FuAd(); }else { ad =
			 * fuAdService.get(id); }
			 */
			ad = null == id ? new FuAd() : fuAdService.get(id);
			ad.setImageUrl(imageUrl);
			ad.setImageAttr(imageAttr);
			ad.setHrefUrl(hrefUrl);
			// 顺序
			ad.setOrderNum(orderNum);
			// 位置 1: 首页
			ad.setPosition(posit); //
			// 是否可见 0:可见 1:不可见
			ad.setIsDelete(0);
			ad.setOrderNum(orderNum);
			ad.setFuProductId(fuProductId);
			fuAdService.save(ad);
			write("1");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	/**
	 * 删除或者恢复删除 根据id和删除状态修改数据
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("delAdAjax")
	public String delAdById() throws Exception {
		try {
			if (null == id) {
				write("-2"); // id 不存在
				return null;
			}
			if (null == isDelete) {
				write("-3"); // 删除的状态码不存在
				return null;
			}

			FuAd fuAd = fuAdService.get(id);
			if (null == fuAd) {
				write("-4"); // 数据不正确
				return null;
			}
			isDelete = isDelete == 0 ? 1 : 0;
			fuAd.setIsDelete(isDelete);
			fuAdService.save(fuAd);
			write("1");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
}
