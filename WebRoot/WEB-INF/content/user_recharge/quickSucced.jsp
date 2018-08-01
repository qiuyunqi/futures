<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－快捷支付成功</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp" %>
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
    <c:if test="${!empty rechargeConfig.zf1}"><a class="payBtn borBlue" href="${ctx}/user_recharge/recharge.htm?type=1">银联充值</a></c:if>
    <c:if test="${!empty rechargeConfig.zf3}"><a class="payBtn" href="${ctx}/user_recharge/recharge.htm?type=3">支付宝充值</a></c:if>
    <c:if test="${!empty rechargeConfig.zf4}"><a class="payBtn" href="${ctx}/user_recharge/recharge.htm?type=4">银行转账</a></c:if>
      <div class="clear"></div>
    </div>
    <div id="one_1" class="display-b">
         <form action="https://payment.chinapay.com/pay/TransGet" target="_blank" id="chinaForm" METHOD=POST>
			<input type=hidden name="MerId" id="MerId" value="${obj.MerId }">
			<input type=hidden name="OrdId" id="OrdId" value="${obj.OrdId }">
			<input type=hidden name="TransAmt" id="TransAmt" value="${obj.TransAmt }">
			<input type=hidden name="CuryId" id="CuryId" value="${obj.CuryId }">
			<input type=hidden name="TransDate" id="TransDate" value="${obj.TransDate }">
			<input type=hidden name="TransType" id="TransType" value="${obj.TransType }">
			<input type=hidden name="Version" id="Version" value="${obj.Version }">
			<input type=hidden name="BgRetUrl" id="BgRetUrl" value="${obj.BgRetUrl }">
			<input type=hidden name="PageRetUrl" id="PageRetUrl" value="${obj.PageRetUrl }">
			<input type=hidden name="GateId" id="GateId" value="${obj.GateId }">
			<input type=hidden name="Priv1" id="Priv1" value="${obj.Priv1 }">
			<input type=hidden name="ChkValue" id="ChkValue" value="${obj.ChkValue }">
        <div class=" ny_bt" style="height:30px;">
        	<span class="pbsx"></span><span class="ny_bt_cont">充值申请成功</span>
        </div>
      
         <div class="form czjel" style="height:30px;">
        	<div class="fp form_fontl">账户余额：</div>
            <div class="fp radio" style="line-height:18px;">
            	<span class="zhye"><fmt:formatNumber value="${empty fuUser.accountBalance?0:fuUser.accountBalance}" pattern="#,###,##0.00"/></span>元
            </div>
            <div class="clr"></div>
        </div>
        <div class="form czjel" style="height:30px;">
        	<div class="fp form_fontl">充值金额：</div>
            <div class="fp radio" >
            	<fmt:formatNumber value="${obj.TransAmt/100}" pattern="#,###,##0.00"/>元
            </div>
            <div class="clr"></div>
        </div>
        <div class="form czjel" style="height:30px;">
        	<div class="fp form_fontl">充值手续费：</div>
            <div class="fp radio">
            	<span class="zhye">${poundageMoney}</span>元
            </div>
            <div class="clr"></div>
        </div>
        <div class="form czjel" style="height:30px;" 	>
        	<div class="fp form_fontl">实际支付金额：</div>
            <div class="fp radio" >
            	<fmt:formatNumber value="${obj.TransAmt/100}" pattern="#,###,##0.00"/>元
            </div>
            <div class="clr"></div>
        </div>
        <div style="margin:30px 0 30px 20px; padding-bottom:30px;">
        <div style="margin:30px 0 30px 100px;" style="height:30px;">
            <input name="" type="submit" onclick="toPay();" value="去付款" class="tijiao">
        </div>
       </div>
      </form>
     </div>
  </div>
  </div>
  <div class="clear"></div>
</div>
</body>
<%@include file="../common/footer.jsp" %>
</html>
<script>
function toPay(){
	$.alerts.okButton="完成充值";
    $.alerts.cancelButton="充值遇到问题";
    jConfirm("请在新打开的页面上完成充值","充值提示",function(res){
  		if(res){
			location.href="${ctx}/user_recharge/recharge.htm?type=1"; 	
  		}else{
  			window.open("http://online.unionpay.com/static/page/help2/help/detail_7.html");
  		}
	});
}
</script>
