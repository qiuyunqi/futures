<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name=“viewport” content=“width=device-width; initial-scale=1.0”>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta name="mobile-web-app-capable" content="yes">
<meta name="google" content="notranslate">
<meta name="screen-orientation" content="portrait">
<meta name="x5-orientation" content="portrait">
<meta name="full-screen" content="yes">
<meta name="x5-fullscreen" content="true">
<meta name="browsermode" content="application">
<meta name="x5-page-mode" content="app">
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－供券详情</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp"%>
<style>
.feedYqb{background: url("../images_yqb/feedYqbAct.png") no-repeat scroll center center / 100% auto !important;}
#feedYqb{color:#fad400;}
</style>
</head>
<body class="whiteBg">
	<div class="sucContain" >
		<div class="containImg">
		        <!-- 列表 -->
		        <div>
			        <table class="havtiInfo acountIn" cellpadding="0" cellspacing="0" width="100%" border="0">
					      <tr>
					      	<td>股票账号：${account.openEquity}${account.capitalAccount}</td>
				         </tr>	
					      <tr>
					      	<td>股票市值（起初）：${account.available}元</td>
				         </tr>	
					      <tr>
					      	<td>可用资金：${account.ableMoney }元</td>
				         </tr>	
					      <tr>
					      	<td>账户佣金：${account.commission}</td>
				         </tr>	
					      <tr>
					      	<td>账户类型：
					      		<c:if test="${account.accountType == 1}">普通账户</c:if>
					      		<c:if test="${account.accountType == 2}">融资融劵</c:if>
					      		<c:if test="${account.accountType == 3}">信用账户</c:if>
					      	</td>
				         </tr>	
					      <tr>
					      	<td>收益分配：${account.availableSplit}</td>
				         </tr>	
					      <tr>
					      	<td>
					      		<table class="acTicketsInfo" cellpadding="0" cellspacing="0" width="90%" border="0">
					      			<tr>
					      				<th class="blueCol">股票名称</th>
					      				<th class="blueCol">股票代码</th>
					      				<th class="blueCol">股票数量（股）</th>
					      			</tr>
					      			<c:forEach items="${account.ssList}" var = "share">
						      			<tr>
						      				<td>${share.name }</td>
						      				<td>${share.code }</td>
						      				<td>${share.number}</td>
						      			</tr>
					      			</c:forEach>
					      		</table>
					      	</td>
				         </tr>	
				    </table>
		        </div>
		        <div class="yqbJg"></div>
		        <div class="yqbJg"></div>
				<div class="rob">
					<p>发布时间:<fmt:formatDate value="${account.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
					<a href="javascript:void(0);" class="order">抢</a>
				</div>
		</div>
	</div>
</body>
<link href="../css/wxYqb.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/js/layer/layer.js" type="text/javascript" ></script>
<script type="text/javascript">
	// 抢单
	$(".order").click(function() {
		layer.open({
			type: 2,
			content:"正在提交",
			shadeClose: false,
		});
		 $.post("${ctx}/wxyqb/order.htm", {"id": ${account.id}}, function(data) {
			 if (data == 1) {
				location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=${appid}&redirect_uri=${url}&response_type=code&scope=snsapi_userinfo&state=toOrder_${account.id}#wechat_redirect";
			 } else  if (data == 2) {
				 // 绑定手机号
				 location.href="${ctx}/wxyqb/addPhone.htm?redirectUrl=${ctx}/wxyqb/redirectOrder.htm?id="+${account.id};
			 } else if (data == 3) { // 当前用户不是交易团队
				 layer.open({
     			    content: '您不是交易团队',
     			    btn: ['确定'],
     			    shadeClose: false,
     			    yes: function(){
     			        layer.closeAll();
     			    }
     			});
			 } else if (data == 4) {
				 layer.open({
     			    content: '亲, 这只股票不存在',
     			    btn: ['确定'],
     			    shadeClose: false,
     			    yes: function(){
     			        layer.closeAll();
     			    }
     			});
				 
			 } else if (data == 5) {
				 layer.open({
     			    content: '亲, 您下手慢了',
     			    btn: ['确定'],
     			    shadeClose: false,
     			    yes: function(){
     			        layer.closeAll();
     			    }
     			});
				 
			 } else if (data == 7) {
				 layer.open({
	     			    content: '亲, 您不能抢您自己发的劵',
	     			    btn: ['确定'],
	     			    shadeClose: false,
	     			    yes: function(){
	     			        layer.closeAll();
	     			    }
	     			});
			 } else {
				 location.href = "${ctx}/wxyqb/robSuccess.htm";
			 }
		 });
	});
</script>
</html>