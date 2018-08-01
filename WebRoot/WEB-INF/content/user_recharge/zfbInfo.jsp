<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－支付宝付款信息</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/css.jsp" %>
</head>
<body>
<%@include file="../common/qqConsult.jsp" %>
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>

<div class="mgrzx">
<%@include file="../common/left.jsp" %>
<div class="mgrzxr">
    <div class="mgrrmain">
      <div id="one_1" class="display-b">
        <div class="rzfbzz">
          <div class="rzfba"><img src="../images_hhr/zfbzz01.jpg" /><a href="javascript:void(0)">支付宝转账</a></div>
          <div class="rzfbb">确认付款信息</div>
          <table cellpadding="0" cellspacing="0" border="0" width="100%">
            <tbody>
			  <tr>
			    <td width="150" align="right">收款人支付宝帐户：</td>
			    <td ><span id="link_txt">whkzdd@163.com</span> <a href="javascript:void(0);" class="fuzhi" data-clipboard-target="link_txt"  id="link_clip_button">复制</a><span style="color:#c20300;margin-left:20px;display:none;" id="success">复制成功！</span></td>
			  </tr>
			  <tr>
			    <td align="right">账户名称：</td>
			    <td>武汉坤洲大德投资管理有限公司</td>
			  </tr>
			</tbody>
          </table>
          <div class="rzfbts">
          <table border="0" cellspacing="0" cellpadding="0" width="100%">
			  <tbody>
			  <tr>
			    <td width="150" align="right">您的支付宝账号：</td>
			    <td>${recharge.rechargeAccount}</td>
			  </tr>
			  <tr>
			    <td align="right">转入金额：</td>
			    <td><b style=" color:#f28208"><fmt:formatNumber value="${empty recharge.rechargeMoney?0:recharge.rechargeMoney}" pattern="#,###,##0.00"/></b>元
			    <%--<a href="#" class="xgje">修改金额</a>--%>
			    </td>
			  </tr>
			  </tbody>
            </table>
            </div>
        </div>
        <div class=" sys fl">
        	<div class="">手机支付宝扫一扫，快速转账</div>
            <div class="sys_evm"><img src="../qihuo_images/kzddzfb.png" width="499" height="92"></div>
        </div>
        <div class=" sys fl">
        	<form action="https://shenghuo.alipay.com/send/payment/fill.htm" onsubmit=" document.charset='gbk'" method="post" target="_blank">
		    <input name="optEmail" type="hidden" value="whkzdd@163.com" />
		    <input name="payAmount" type="hidden" value="${empty recharge.rechargeMoney?0:recharge.rechargeMoney}" />
		    <input name="title" type="hidden" value="账户充值" placeholder="付款说明" />
        	<div style="margin-left:50px;">去支付宝网站付款</div>
            <div class="sys_evm"><input name="pay" type="image" value="给我付款" src="../qihuo_images/ewm_05.gif" style="width:174px;height:174px;border:14px solid #fff;"/></div>
		    </form>
        </div>
        <div class="clr"></div>
       <div style="margin:60px 0 0 45px;">
   	   <p>到账时间如需马上到账或长时间未到账，可拨打客服电话：010-53320537，工作时间：8:30-17:00</p>
	      <div class=" dzsj">
	          <ul>
	            <li>8:30-17:00(30分钟内到账)</li>
	            <li>其他时间（下个工作时间）</li>
	          </ul>
	     </div>
      </div>
    </div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<div class="downban"></div>
<%@include file="../common/footer.jsp" %>
</body>
</html>
<script>
var clip = new ZeroClipboard(document.getElementById("link_clip_button"), {
	  moviePath:"${ctx}/js/ZeroClipboard.swf",
	  hoverClass:"zeroclipboard-is-hover"
});
	clip.on( 'complete', function(client, args) {
	 $('#success').show();
	 setTimeout("$('#success').hide()",2000);
});
</script>
