<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0, user-scalable=no" name="viewport"/>
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
<title>${title}－余券宝温馨提示</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp"%>
<link href="../css/wxYqb.css" rel="stylesheet" type="text/css" />
<style>
.feedYqb{background: url("../images_yqb/feedYqbAct.png") no-repeat scroll center center / 100% auto !important;}
#feedYqb{color:#fad400;}
.matcSucSpYqb{font-size:1.2rem !important;}
</style>
</head>
<body>
	<div class="sucContain">
		<div class="containImg">
			<div class="infoList">
				<div class="listTitYqb"><img class="imgWidtYqb" src="../images_yqb/tip.png"/></div>
				<div class="listTitYqbs">
					<span class="matcSucSpYqb cenTxt blueCol">温馨提示</span>
					<span class="matcSucSecYqb cenTxt blueCol">
						<c:if test="${isSuccess == 3}">
							正在审核您的交易员申请, 请等待
						</c:if>
					</span>
				</div>
			</div>
		</div>
		<div class="footrYqb center">
			<span class="spnTxtYqb" >客服电话：010-53320537</span>
		</div>
	</div>
	<%@include file="../wxyqb/footer_yqb.jsp" %>
</body>
</html>

	
