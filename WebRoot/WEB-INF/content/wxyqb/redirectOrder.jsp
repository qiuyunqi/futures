<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width; initial-scale=1.0">
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
<title>${title}</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp"%>
<link href="../css/wxYqb.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="sucContain">
		<div class="containImg">
			<span class="margLft">
			</span>
			<div class="infoList">
				<div class="listTitYqb"><img class="imgWidtYqb" src="../images_yqb/sucesYqb.png"/></div>
				<div class="listTitYqbs">
					<span class="matcSucSpYqb cenTxt blueCol">
						<c:if test="${isSuccess == 2}">
							 您不是交易人员
						</c:if>
						<c:if test="${isSuccess == 3}">
							 该股票股不存在
						</c:if>
						<c:if test="${isSuccess == 4}">
							 该股票已经被抢
						</c:if>
						<c:if test="${isSuccess == 5}">
							 抢劵成功
						</c:if>
					</span>
					<span class="matcSucSecYqb cenTxt blueCol">
						 点击【查看账户详情】获取账户信息
					</span>
				</div>
				<div class="matcBtnYqb bgColor cenTxt smaWid">
					<a class="againTjYqb" href="${ctx}/wxyqb/meIndex.htm">查看账户详情</a>
					<a class="bacIndYqb" href="${ctx}/wxyqb/IFindTicket.htm?userId=${sessionScope.fuUser.id}">查看我抢的券</a>
				</div>
			</div>
		</div>
		<div class="footrYqb center">
			<span class="spnTxtYqb" >客服电话：010-53320537</span>
		</div>
	</div>
</body>
</html>

	
