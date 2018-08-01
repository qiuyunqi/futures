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
<title>${title}－我的</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp"%>
<style>
.mineYqb{background: url("../images_yqb/mineYqbAct.png") no-repeat scroll center center / 100% auto !important;}
#mineYqb{color:#fad400;}
input[type=checkbox] {
	visibility: hidden;
}
</style>
</head>
<body class="whiteBg">
	<div class="sucContain" >
		<div class="containImg">
			<div class="infoList">
		        <!-- 列表 -->
		        <div class="meList">
		        	<table class="meInfo" cellpadding="0" cellspacing="0" width="90%" border="0">
					      <tr onclick="window.location.href='${ctx}/wxyqb/userInfo.htm'">
					      	<th>
						      		<c:if test="${empty fuUser.userAvatar}">
					            		<img class="meTx" src="../images_yqb/meTouX.png"/>
						            </c:if>
						            <c:if test="${!empty fuUser.userAvatar}">
						            	<img class="meTx" src="${fuUser.userAvatar}"/>
						            </c:if>
					      	</th>
					      	<td class="blueCol smallSize firstTd">
					      		<div class="meToxx">
					      			<span class="myName">${fuUser.nickName}</span><span>${phone}</span>
					      		</div>
					      		<img class="lftJt rghtImg" src="../images_yqb/mejtou.png"/>
					      	</td>
				         </tr>	
					      <tr>
					      	<th class="blueCol smallSize">我的供券</th>
					      	<td class="fltRgt smallSize"><a href="${ctx}/wxyqb/myForTicket.htm?userId=${fuUser.id}"><span>${toTicketNum }个账户</span><img class="lftJt" src="../images_yqb/mejtou.png"/></a></td>
				         </tr>	
					      <tr>
					      	<th class="blueCol smallSize">我找的券</th>
					      	<td class="fltRgt smallSize"><a href="${ctx}/wxyqb/IFindTicket.htm?userId=${fuUser.id}"><span>${findTicketNum }个账户</span><img class="lftJt" src="../images_yqb/mejtou.png"/></a></td>
				         </tr>	
				         <tr>
					      	<th class="blueCol smallSize">找劵管理</th>
					      	<td class="fltRgt smallSize"><a href="${ctx}/wxyqb/findTicketManager.htm?userId=${fuUser.id}"><span>${ticketManagerNum }个找劵</span><img class="lftJt" src="../images_yqb/mejtou.png"/></a></td>
				         </tr>	
					     <tr>
					      	<th class="blueCol smallSize">我的邀请</th>
					      	<td class="fltRgt smallSize"><a href="${ctx}/wxyqb/myInvitation.htm?userId=${fuUser.id}"><span>${nextcount}个好友</span><img class="lftJt" src="../images_yqb/mejtou.png"/></a></td>
				         </tr>	
					     <tr>
					      	<th class="blueCol smallSize">我的财富</th>
					      	<td class="fltRgt smallSize"><a href="${ctx}/wxyqb/moneyDetail.htm?userId=${fuUser.id}"><span>${fuUser.accountBalance}元</span><img class="lftJt" src="../images_yqb/mejtou.png"/></a></td>
				         </tr>	
					      <tr onclick="window.location.href='${ctx}/wxyqb/aboutIndex.htm'">
					      	<th class="blueCol smallSize"><a href="" class="blueCol">关于</a></th>
					      	<td class="fltRgt"><span style="visibility: hidden;">hidden</span><img class="lftJt" src="../images_yqb/mejtou.png"/></td>
				         </tr>	
				    </table>
		        </div>
			</div>
		<div class="yqbJg"></div>
		</div>
	</div>
	<%@include file="../wxyqb/footer_yqb.jsp" %>
</body>
<link href="../css/wxYqb.css" rel="stylesheet" type="text/css" />
</html>
<script>
$(function(){
    var flag = "${flag}";
    if(flag == "error"){
    	location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=${appid}&redirect_uri=${url}&response_type=code&scope=snsapi_userinfo&state=toMeIndex#wechat_redirect";
    }
    
});
</script>