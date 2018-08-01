package com.hongwei.futures.web.action.vidian;

import org.apache.struts2.convention.annotation.Action;

import com.hongwei.futures.util.HttpClientUtil;
import com.hongwei.futures.util.ViDianUtil;
import com.hongwei.futures.web.action.BaseAction;

public class ViDianAction extends BaseAction {

	private static final long serialVersionUID = -2826238977350074161L;

	/***
	 * 详情文档请查看 http://wiki.open.weidian.com/index.php?title=%E8%
	 * 8E%B7%E5%8F%96%E8%AE%A2%E5%8D%95%E5%88%97%E8%A1%A8
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("orderList")
	public String orderList() {
		try {
			String accessToken = ViDianUtil.getToken();
			if (null == accessToken) {
				write("-1");
				return null;
			}

			Integer pageNum = 1;
			Integer pageSize = 100;
			String url = "http://api.vdian.com/api?param={\"page_num\":"
					+ pageNum
					+ ",\"page_size\":"
					+ pageSize
					+ "}&public={\"method\":\"vdian.order.list.get\",\"access_token\":\""
					+ accessToken
					+ "\",\"version\":\"1.1\",\"format\":\"json\"}";
			String http = HttpClientUtil.getHTTP(url);
			write(http);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
