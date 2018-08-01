package com.hongwei.futures.web.action.user_options;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipayNotify;
import com.alipay.util.AlipaySubmit;
import com.hongwei.futures.model.FuUser;
import com.hongwei.futures.model.WqqOptions;
import com.hongwei.futures.service.WqqOptionsService;
import com.hongwei.futures.util.Property;
import com.hongwei.futures.web.action.BaseAction;

@ParentPackage("fu_common")
public class AliPayAction extends BaseAction {
	@Autowired
	private WqqOptionsService wqqOptionsService;

	private Long id;
	private FuUser fuUser;
	private String feeMoney;
	private String optionsId;
	private String money;

	/**
	 * 支付宝支付请求
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("saveAliPay")
	public String saveAliPay() {
		try {
			// 支付类型
			String payment_type = "1";
			// 必填，不能修改
			// 服务器异步通知页面路径
			String notify_url = Property.getProperty("WQQ_NOTIFY_URL");
			// 需http://格式的完整路径，不能加?id=123这类自定义参数

			// 页面跳转同步通知页面路径
			String return_url = Property.getProperty("WQQ_RETURN_URL");
			// 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/

			// 商户订单号
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
			String tradeno = sdf.format(new Date()) + String.valueOf((int) (Math.random() * 90 + 10));
			String out_trade_no = new String(tradeno.getBytes("ISO-8859-1"), "UTF-8");
			// 商户网站订单系统中唯一订单号，必填

			// 订单名称
			String subject = "超级合伙人微期权支付";
			// 必填

			// 付款金额
			String total_fee = new String(feeMoney.getBytes("ISO-8859-1"), "UTF-8");
			// 必填

			// 订单描述
			String body = optionsId;

			// 商品展示地址
			String show_url = new String(("http://www.hhr360.com").getBytes("ISO-8859-1"), "UTF-8");
			// 需以http://开头的完整路径，例如：http://www.商户网址.com/myorder.html

			// 防钓鱼时间戳
			String anti_phishing_key = AlipaySubmit.query_timestamp();
			// 若要使用请调用类文件submit中的query_timestamp函数

			// 客户端的IP地址
			String exter_invoke_ip = "";
			// 非局域网的外网IP地址，如：221.0.0.1

			// ////////////////////////////////////////////////////////////////////////////////

			// 把请求参数打包成数组
			Map<String, String> sParaTemp = new HashMap<String, String>();
			sParaTemp.put("service", "create_direct_pay_by_user");
			sParaTemp.put("partner", AlipayConfig.partner);
			sParaTemp.put("seller_email", AlipayConfig.seller_email);
			sParaTemp.put("_input_charset", AlipayConfig.input_charset);
			sParaTemp.put("payment_type", payment_type);
			sParaTemp.put("notify_url", notify_url);
			sParaTemp.put("return_url", return_url);
			sParaTemp.put("out_trade_no", out_trade_no);
			sParaTemp.put("subject", subject);
			sParaTemp.put("total_fee", total_fee);
			sParaTemp.put("body", body);
			sParaTemp.put("show_url", show_url);
			sParaTemp.put("anti_phishing_key", anti_phishing_key);
			sParaTemp.put("exter_invoke_ip", exter_invoke_ip);

			// 建立请求
			String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "post", "确认");
			write(sHtmlText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 同步通知的页面
	 * 
	 * @return
	 */
	@Action(value = "return_url", results = { @Result(type = "redirect", location = "/user_options/wqqSuccessPay.htm") })
	public String return_url() {
		try {
			// 获取支付宝POST过来反馈信息
			Map<String, String> params = new HashMap<String, String>();
			Map requestParams = this.getHttpServletRequest().getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				// valueStr = new String(valueStr.getBytes("ISO-8859-1"),
				// "gbk");
				params.put(name, valueStr);
			}

			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			// 商户订单号
			String out_trade_no = new String(this.getHttpServletRequest().getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

			// 支付宝交易号
			String trade_no = new String(this.getHttpServletRequest().getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

			// 交易状态
			String trade_status = new String(this.getHttpServletRequest().getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

			// 交易金额
			String total_fee = new String(this.getHttpServletRequest().getParameter("total_fee").getBytes("ISO-8859-1"), "UTF-8");

			// id
			String body = new String(this.getHttpServletRequest().getParameter("body").getBytes("ISO-8859-1"), "UTF-8");

			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			if (AlipayNotify.verify(params)) {// 验证成功
				if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
					WqqOptions options = wqqOptionsService.get(Long.valueOf(body));
					if (options.getIsPay() == 0) {
						wqqOptionsService.saveAliOption(options, out_trade_no, total_fee);
					}
				} else {
					System.out.println("支付失败---同步回调---" + new Date());
				}
			} else {// 验证失败
				System.out.println("签名验证失败---同步回调---" + new Date());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 异步通知付款状态
	 * 
	 * @return
	 */
	@Action(value = "notify_url", results = { @Result(type = "redirect", location = "/user_options/wqqSuccessPay.htm") })
	public String notify_url() {
		try {
			// 获取支付宝POST过来反馈信息
			Map<String, String> params = new HashMap<String, String>();
			Map requestParams = this.getHttpServletRequest().getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				// valueStr = new String(valueStr.getBytes("ISO-8859-1"),
				// "gbk");
				params.put(name, valueStr);
			}

			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			// 商户订单号
			String out_trade_no = new String(this.getHttpServletRequest().getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

			// 支付宝交易号
			String trade_no = new String(this.getHttpServletRequest().getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

			// 交易状态
			String trade_status = new String(this.getHttpServletRequest().getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

			// 交易金额
			String total_fee = new String(this.getHttpServletRequest().getParameter("total_fee").getBytes("ISO-8859-1"), "UTF-8");

			// id
			String body = new String(this.getHttpServletRequest().getParameter("body").getBytes("ISO-8859-1"), "UTF-8");

			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			if (AlipayNotify.verify(params)) {// 验证成功
				if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
					WqqOptions options = wqqOptionsService.get(Long.valueOf(body));
					if (options.getIsPay() == 0) {
						wqqOptionsService.saveAliOption(options, out_trade_no, total_fee);
					}
				} else {
					System.out.println("支付失败---异步回调---" + new Date());
				}
			} else {// 验证失败
				System.out.println("签名验证失败---异步回调---" + new Date());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 猜涨停支付成功
	 * 
	 * @return
	 */
	@Action("wqqSuccessPay")
	public String wqqSuccessPay() {
		try {
			this.getActionContext().put("money", money);
			this.getActionContext().put("time", new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FuUser getFuUser() {
		return fuUser;
	}

	public void setFuUser(FuUser fuUser) {
		this.fuUser = fuUser;
	}

	public String getFeeMoney() {
		return feeMoney;
	}

	public void setFeeMoney(String feeMoney) {
		this.feeMoney = feeMoney;
	}

	public String getOptionsId() {
		return optionsId;
	}

	public void setOptionsId(String optionsId) {
		this.optionsId = optionsId;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

}
