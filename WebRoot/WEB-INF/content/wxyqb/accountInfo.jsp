<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
<title>${title}－账户信息</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp"%>
</head>
<body class="whiteBg">
	<div class="sucContain" >
		<div class="containImg">
			<div class="infoList">
		        <!-- 列表 -->
		        <div>
			        <table class="havtiInfo acountIn" cellpadding="0" cellspacing="0" width="100%" border="0">
					      <tr>
					      	<td>资金账号：<span class="qGcol">${stockAccount.isPayMargin==1?stockAccount.capitalAccount: '缴纳保证金后显示'}</span></td>
				         </tr>	
				         <tr>
					      	<td>交易密码：<span class="qGcol">${stockAccount.isPayMargin==1?capitalPassword: '缴纳保证金后显示'}</span></td>
				         </tr>	
					      <tr>
					      	<td>股票市值（起初）：<fmt:formatNumber value="${empty stockAccount.available?0:stockAccount.available}" pattern="#,###,##0.00"/>元</td>
				         </tr>	
					      <tr>
					      	<td>可用资金：<fmt:formatNumber value="${empty stockAccount.ableMoney?0:stockAccount.ableMoney}" pattern="#,###,##0.00"/>元</td>
				         </tr>	
					      <tr>
					      	<td>账户佣金：${stockAccount.commission}</td>
				         </tr>	
					      <tr>
					      	<td>账户类型：${stockAccount.accountType==1?'普通账户':stockAccount.accountType==2?'融资融券':'信用账户'}</td>
				         </tr>	
					      <tr>
					      	<td>营业部：${stockAccount.salesDept}</td>
				         </tr>	
					      <tr>
					      	<td>
					      		<table class="acTicketsInfo" cellpadding="0" cellspacing="0" width="90%" border="0">
					      			<tr>
					      				<th class="qLcol">股票名称</th>
					      				<th class="qLcol">股票代码</th>
					      				<th class="qLcol">股票数量（股）</th>
					      			</tr>
					      			<c:forEach items="${shares}" var="share">
					      			<tr>
					      				<td>${share.name }</td>
					      				<td>${share.code }</td>
					      				<td>${share.number }</td>
					      			</tr>
					      			</c:forEach>
					      		</table>
					      	</td>
				         </tr>	
				    </table>
		        </div>
		        <div class="yqbJg" ></div>
		        <div class="yqbJg" ></div>
				<div class="backTic">
					<p>发布时间：<fmt:formatDate value="${stockAccount.createTime}" pattern="yyyy.MM.dd"/></p>
					<c:if test="${stockAccount.transactionStatus==0}">
						<a class="yHfaMi" onclick="fadeTicket(${stockAccount.id})">退券</a>
					</c:if>
					<c:if test="${stockAccount.transactionStatus==1}">
						<a class="yHfaMi" href="${ctx}/wxyqb/IFindTicket.htm?userId=${fuUser.id}">返回</a>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</body>
<link href="../css/wxYqb.css" rel="stylesheet" type="text/css" />
</html>
<script type="text/javascript">
function fadeTicket(id){
	layer.open({
	    content: "确认退劵？",
	    btn: ["确定","取消"],
	    shadeClose: false,
	    yes: function(){
			$.post("${ctx}/wxyqb/saveFadeTicket.htm?id="+id, null, function(d){
				layer.open({
				    content: "退劵成功！",
				    btn: ["确定"],
				    shadeClose: false,
				    yes: function(){
						location.href="${ctx}/wxyqb/forTicket.htm";
					}
				})
			})
		}
	})
}
</script>
