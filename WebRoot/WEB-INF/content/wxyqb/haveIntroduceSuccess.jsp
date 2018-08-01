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
<title>${title}－供劵发布成功</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp"%>
<link href="../css/wxYqb.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="sucContain">
		<div class="containImg">
			<div class="infoList">
				<div class="listTitYqb"><img class="imgWidtYqb" src="../images_yqb/sucesYqb.png"/></div>
				<div class="listTitYqbs">
					<span class="matcSucSpYqb cenTxt blueCol">您的券已发布到供券市场</span>
					<span class="matcSucSecYqb cenTxt blueCol">请等待接单</span>
				</div>
				<div class="matcBtnYqb cenTxt">
					<a class="backMark" href="${ctx}/wxyqb/forTicket.htm">返回供券市场</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

	
