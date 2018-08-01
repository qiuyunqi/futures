package com.hongwei.futures.web.modulesforapp.basic.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import net.sf.json.JSONObject;

import com.hongwei.futures.model.FuAd;
import com.hongwei.futures.model.FuArticle;
import com.hongwei.futures.model.FuProduct;
import com.hongwei.futures.model.FuStockAccount;
import com.hongwei.futures.model.FuTransaction;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.service.FuAdService;
import com.hongwei.futures.service.FuArticleService;
import com.hongwei.futures.service.FuProductService;
import com.hongwei.futures.service.FuStockAccountService;
import com.hongwei.futures.service.FuTransactionService;
import com.hongwei.futures.service.FuUserService;
import com.hongwei.futures.util.DateUtil;
import com.hongwei.futures.web.modulesforapp.basic.AppTradeBasicService;

public class AppTradeBasicServiceImpl implements AppTradeBasicService {
	private static final Log log = LogFactory.getLog(AppTradeBasicServiceImpl.class);
	@Autowired
	private FuProductService fuProductService;
	@Autowired
	private FuAdService fuAdService;
	@Autowired
	private FuArticleService fuArticleService;
	@Autowired
	private FuStockAccountService fuStockAccountService;
	@Autowired
	private FuTransactionService fuTransactionService;
	@Autowired
	private FuUserService fuUserService;
	
	public String getProductList(String sign, String userId, String version) {
		JSONObject obj = new JSONObject();
		try {
			if (null == version || "".equals(version)) {
				obj.put("is_success", 0);
				obj.put("message", "版本号不能输入为空");
				return obj.toString();
			}
			
			// 查询首页的产品
			List<FuProduct> productList = fuProductService.getList();
			// 查询轮播图上传
			int position = 1; // 这个广告的位置 1: 首页
			List<FuAd> adList = fuAdService.getList(position);
			
			List<Object> ads = new ArrayList<Object>();
			if(null != adList && adList.size() > 0) {
				for (FuAd fuAd : adList) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("href_url", fuAd.getHrefUrl() == null ? "" : fuAd.getHrefUrl());
					map.put("image_url", fuAd.getImageUrl() == null ? "" : fuAd.getImageUrl());
					map.put("image_attr", fuAd.getImageAttr() == null ? "" : fuAd.getImageAttr());
					map.put("product_id", null == fuAd.getFuProductId() ? "" : fuAd.getFuProductId());
					ads.add(map);
				}
			}else {
				obj.put("ads", "[]");
				obj.put("is_success", 2);
				obj.put("message", "没有查询到数据");
			}
			if (null != productList && productList.size() > 0) {
				// 查询这个用户是否有已经审核成功的 并可用的账号
				if(!"2.0.0".equals(version)) {
					if(null == userId || "".equals(userId)) {
						obj.put("is_stock", 0);  // 没有可用的账户
						obj.put("is_transaction", 0); // 不是交易团队
					}else {
						int isDel = 0;
//						int examineStatus = 1;
						List<FuStockAccount> accountList = fuStockAccountService.findByUserIdAndIsDel(Long.parseLong(userId), isDel);
						if(null != accountList && accountList.size() > 0) {
							obj.put("is_stock", 1);
						}else {
							obj.put("is_stock", 0);
						}
					}
				}
				FuTransaction fuTransaction = null;
				FuUser fuUser = null;
				if(null != userId && !"".equals(userId)) {
					fuTransaction = fuTransactionService.findByUserId(Long.parseLong(userId));
					if(null == fuTransaction) {
						obj.put("is_transaction", 0);
					}else {
						obj.put("is_transaction", 1); // 是交易团队
					}
					fuUser = fuUserService.get(Long.parseLong(userId));
				}
				
				List<Object> products = new ArrayList<Object>();
				for (FuProduct product : productList) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					if ("2.0.0".equals(version)) {
						if(product.getProductId() != 5) {
							map.put("product_id", product.getProductId() == null ? "" : product.getProductId());
							map.put("icon", product.getIcon() == null ? "" : product.getIcon());
							map.put("name", product.getName() == null ? "" : product.getName());
							map.put("description", product.getDescription() == null ? "" : product.getDescription());
							map.put("profit", product.getProfit() == null ? "" : product.getProfit());
							map.put("profit_desc", product.getProfitDesc() == null ? "" : product.getProfitDesc());
							map.put("info_href", product.getInfoHref() == null ? "" : product.getInfoHref());
							products.add(map);
						}
					}else if("3.0.0".equals(version)) {
						if((null == fuUser || null == fuUser.getHhrType() || fuUser.getHhrType() != 3) && null == fuTransaction) { // 非销售的人员  -->11  不是交易团队 --> 没有id=10的数据
							if(product.getProductId() != 1 && product.getId() != 10 && product.getId() != 11) {
								map.put("product_id", product.getProductId() == null ? "" : product.getProductId());
								map.put("icon", product.getIcon() == null ? "" : product.getIcon());
								map.put("name", product.getName() == null ? "" : product.getName());
								map.put("description", product.getDescription() == null ? "" : product.getDescription());
								map.put("profit", product.getProfit() == null ? "" : product.getProfit());
								map.put("profit_desc", product.getProfitDesc() == null ? "" : product.getProfitDesc());
								map.put("info_href", product.getInfoHref() == null ? "" : product.getInfoHref());
								products.add(map);
							}
						} else if(fuUser.getHhrType() == 3) {
							if(product.getProductId() != 1 && product.getId() != 10) {
								map.put("product_id", product.getProductId() == null ? "" : product.getProductId());
								map.put("icon", product.getIcon() == null ? "" : product.getIcon());
								map.put("name", product.getName() == null ? "" : product.getName());
								map.put("description", product.getDescription() == null ? "" : product.getDescription());
								map.put("profit", product.getProfit() == null ? "" : product.getProfit());
								map.put("profit_desc", product.getProfitDesc() == null ? "" : product.getProfitDesc());
								map.put("info_href", product.getInfoHref() == null ? "" : product.getInfoHref());
								products.add(map);
							}
						}else if(null != fuTransaction) {
							if(product.getProductId() != 1 && product.getId() != 11) {
								map.put("product_id", product.getProductId() == null ? "" : product.getProductId());
								map.put("icon", product.getIcon() == null ? "" : product.getIcon());
								map.put("name", product.getName() == null ? "" : product.getName());
								map.put("description", product.getDescription() == null ? "" : product.getDescription());
								map.put("profit", product.getProfit() == null ? "" : product.getProfit());
								map.put("profit_desc", product.getProfitDesc() == null ? "" : product.getProfitDesc());
								map.put("info_href", product.getInfoHref() == null ? "" : product.getInfoHref());
								products.add(map);
							}
						}
					}
				}
				obj.put("products", products);
				obj.put("ads", ads);
				obj.put("message", "查询首页成功");
				obj.put("is_success", 1);
			} else {
				obj.put("products", "[]");
			}

		} catch (Exception e) {
			e.printStackTrace();
			obj.put("is_success", 0);
			obj.put("message", "系统更新中, 请等下重试");
			log.error(obj.toString());
		}
		log.info(obj.toString());
		return obj.toString();
	}

	// 发现 文章
	public String getArticleList(String articleId, String currentPage, String pageSize, String sign, String version) {
		JSONObject obj = new JSONObject();
			try{
				if(null == version || "".equals(version)){
					obj.put("is_success", 0);
					obj.put("message", "版本号不能输入为空");
					return obj.toString();
				}
				if("2.0.0".equals(version)){
					int curPage = 0;
					int pageNum = 0;
					if(null == currentPage || "".equals(currentPage)){
						curPage = 0;
					}else {
						curPage = Integer.parseInt(currentPage);
					}
					if(null == pageSize || "".equals(pageSize)){
						pageNum = 10;
					}else {
						pageNum = Integer.parseInt(pageSize);
					}
					// 查询文章列表 
					List<FuArticle> articleList = fuArticleService.findList(articleId, curPage, pageNum);
					if(null != articleList && articleList.size() > 0){
						List<Object> articles = new ArrayList<Object>();
						for (FuArticle article : articleList) {
							HashMap<String, Object> map = new HashMap<String, Object>();
							map.put("article_id", article.getId());
							map.put("title", article.getTitle() == null ? "" : article.getTitle());
							map.put("date", article.getTime() == null ? "" : DateUtil.getStrFromDate(article.getTime(), "yyyy-MM-dd"));
							map.put("image_url", article.getPic() == null ? "" : article.getPic());
							map.put("a_url", article.getHrefUrl() == null ? "" : article.getHrefUrl());
							articles.add(map);
						}
						obj.put("articles", articles);
						obj.put("is_success", 1);
						obj.put("message", "查询文章列表成功!");
					}else {
						obj.put("articles", "[]");
						obj.put("is_success", 2);
						obj.put("message", "查询文章列表成功!");
					}
					
				}
			}catch (Exception e) {
				e.printStackTrace();
				obj.put("is_success", 0);
				obj.put("message", "系统更新中, 请等下重试");
			}
//		}else {
//			obj.put("is_success", 0);
//			obj.put("message", result);
//		}
		System.out.println(new Date() + "app_trade_basic/getArticleList");
		return obj.toString();
	}

}
