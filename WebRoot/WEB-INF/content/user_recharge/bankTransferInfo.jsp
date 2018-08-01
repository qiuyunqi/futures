<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－银行转账信息</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/css.jsp" %>
</head>
<body>
<%@include file="../common/qqConsult.jsp" %>
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>
<%--  --%>
<div class="contain">
<div class="downban"></div>
<div class="mgrzx">
	<%@include file="../common/left.jsp" %>
    <div class="mgrzxr" >
    	<div class="h20"><span class="centerBg">用户中心</span><span class="safeBg" style="text-indent:25px;">充值</span></div>
    	<div class="payMenu">
	    <c:if test="${!empty rechargeConfig.zf1}"><a class="payBtn" href="${ctx}/user_recharge/recharge.htm?type=1">银联充值</a></c:if>
	    <c:if test="${!empty rechargeConfig.zf3}"><a class="payBtn" href="${ctx}/user_recharge/recharge.htm?type=3">支付宝充值</a></c:if>
	    <c:if test="${!empty rechargeConfig.zf4}"><a class="payBtn borBlue" href="${ctx}/user_recharge/recharge.htm?type=4">银行转账</a></c:if>
	    <div class="clear"></div>
	    </div>
        <div id="one_1" class="display-b">
        <div class="rzfbzz">
          <div class=" ny_bt">
        	<span class="pbsx"></span><span class="ny_bt_cont">您可以通过网上银行或银行柜台向投资管理平台转账（手续费最多一笔50元）</span>
          </div>
	      <div class=" zhuanzhang">
	      	<table border="0" cellspacing="0" cellpadding="0">
			  <tbody>
			  <tr>
			    <td><span><img src="../qihuo_images/zhcz_09.gif" width="170" height="52"></span></td>
			    <td width="500">
			        <p>账号：3100 1587 6150 5003 2541</p>
			        <p>开户名：小合网络科技（上海）有限公司<span style="margin-left:100px;"><a href="javascript:void(0)" onclick="window.open('https://pbsz.ebank.cmbchina.com/CmbBank_GenShell/UI/GenShellPC/Login/Login.aspx')" style="font-size:20px;color:#00CC00;">进入网银</a></span></p>
			        <p>开户行：中国建设银行浦东分行东方路支行</p>
			    </td>
			  </tr>
			  <tr>
			    <td><span><img src="../qihuo_images/zhcz_09.gif" width="170" height="52"></span></td>
			    <td width="500">
			        <p>账号：6217 0000 1007 7384 314</p>
			        <p>开户名：唐文钊<span style="margin-left:255px;"><a href="javascript:void(0)" onclick="window.open('https://ibsbjstar.ccb.com.cn/app/V5/CN/STY1/login.jsp')" style="font-size:20px;color:#00CC00;">进入网银</a></span></p>
			        <p>开户行：中国建设银行(北京长河湾支行)</p>
			    </td>
			  </tr>
			</tbody>
			</table>
	      </div>  
         <div class=" fgkf">
	    	<div class="fgkf_fl fl">
	        	<div style="margin-bottom:10px;">1、转账时金额最好有些零头（如1000.75），这样我们好确认是您的汇款</div>
	            <div>2、在用户网银转账之后，请务必保留网银转账成功时的截图，并在<b>资金或转账用途中</b><span style="color:#cc0000">备注清自己要转载投资管理平台的用户名，</span>将回单发在QQ客服，以便尽快到账！</div>
	          <a href="javascript:void(0)" onclick="window.open('http://wpa.b.qq.com/cgi/wpa.php?ln=1&key=XzkzODA0OTI0MF8yNTQxNDNfNDAwNjkyODk5OV8yXw');" class="fsgkf">发送给QQ客服</a>
	        </div>
	        <div class="fl" style=" margin:100px 0 0 10px;"><img src="../qihuo_images/yzzg_06.gif" width="50" height="38"></div>
	        <div class="fl"><img src="../qihuo_images/kzddyhzz.png" width="304" height="196"></div>
	        <div class="clr"></div>
	        <div style="margin:30px 0 30px;">
	           <input name="" type="button" value="返回首页" onclick="window.location.href='${ctx}/index_info/index.htm'" class="tijiao">
	        </div>
        </div>   
       <div style="height:100px"></div>
      </div>
    </div>
  </div>
    <div class="clr"></div>
</div>
</div>
</body>
</html>

