<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－在线充值</title>
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
      <div class="czym">
        <form action="">
        	<div class="czy czfs">
        	<label class="labl">充值类型：</label><span class="zfbyl">网上充值</span>
        	</div>
        	<div class="czy leix">
        		<p class="labl">充值方式：<span class="zfbyl">银联或支付宝</span></p>
        		<ul>
        			<c:if test="${!empty rechargeConfig.zf3}">
        			<li>
        				<input class="xz0" type="radio" value="3" checked="checked" name="zf">
        				<a href="javascript:;">
							<img height="37px" src="../images_hhr/zfb.png">
						</a>
						<span>支付宝</span>
        			</li>
        			</c:if>
        			<c:if test="${!empty rechargeConfig.zf1}">
        			<li>
        				<input class="xz0" type="radio" value="1" name="zf" <c:if test="${empty rechargeConfig.zf3}">checked="checked"</c:if>>
        				<a href="javascript:;">
							<img  height="42px" src="../qihuo_images/ylcz.png">
						</a>
						<span>银联（手续费率<fmt:formatNumber value="${params.payPoundage*100}" pattern="#0.00"/>%）</span>
        			</li>
        			</c:if>
        			<li>
        			</li>
        		</ul>
        	 </div>
        	 <div class="tiji">
				<a class="bcbut cz" onclick="payType();return false;"  href="javascript:void(0);">下一步</a>
			</div>
        </form>
        </div>
      </div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<%@include file="../common/footer.jsp" %>
</body>
</html>
<link href="../css/xsUp.css" rel="stylesheet" type="text/css" />
<script>
function payType(){
   var paytype=$("input[name='zf']:checked").val();
   location.href="${ctx}/user_recharge/recharge.htm?type="+paytype;
}
</script>