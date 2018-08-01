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
<title>${title}－给劵</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp"%>
<style>
.findYqb{background: url("../images_yqb/findYqbAct.png") no-repeat scroll center center / 100% auto !important;}
#findYqb{color:#fad400;}
</style>
</head>
<body class="whiteBg">
	<div class="sucContain" >
		<div class="containImg">
			<div class="infoList">
		        <!-- 详情 -->
		        <div class="ticketInfo">
			        <h3 class="blueCol siz">${publish.title}</h3>
		        </div>
				<div class="findTksBy">
					<p>发布时间：<fmt:formatDate value="${publish.createTime}" pattern="yyyyMMdd HH:mm:ss"/></p>
					<p>发布人：${nickName}</p>
					<p>评级：<c:forEach begin="1" end="${rating}" ><img src="../images_yqb/star.png"/></c:forEach></p>
					<div class="findTk blueCol">
						${publish.description}
					</div>
				</div>
				<c:if test="${publish.isDel==1 && userId>0}">
					<a class="giveTcks" href="javascript:void(0);" onclick="giveTicket()">给券</a>
				</c:if>
				<c:if test="${publish.isDel==0 || userId==0}">
					<a class="giveTcks" href="${ctx}/wxyqb/findTicket.htm">返回</a>
				</c:if>
			</div>
		</div>
	</div>
	<form id="giveTicketForm" action="${ctx}/wxyqb/giveTicket.htm" method="post">
		<input id="userId" name="userId" type="hidden" value=""/>
		<input id="publishUserId" name="publishUserId" type="hidden" value=""/>
	</form>
</body>
<link href="../css/wxYqb.css" rel="stylesheet" type="text/css" />
</html>
<script type="text/javascript">
function giveTicket(){
	$.post("${ctx}/wxyqb/giveTicket.htm",{userId: ${userId}, publishUserId: ${publishUserId}},function(data){
		if(data==-1){
			layer.open({
 			    content: "请勿给劵自己！",
 			    btn: ["确定"],
 			    shadeClose: false,
 			    yes: function(){
 			        layer.closeAll();
 			    }
 			});
			return false;
		}
		$("#userId").val(${userId});
		$("#publishUserId").val(${publishUserId});
		$("#giveTicketForm").submit();
	})
} 
</script>