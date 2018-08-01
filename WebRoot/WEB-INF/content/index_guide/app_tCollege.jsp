<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name=“viewport” content=“width=device-width; initial-scale=1.0”>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－T学院直播间</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/css.jsp" %>
<style>
.tcollege{width:100%;margin-top:-33px}
</style>
</head>
<body class="bodybg">
<c:set value="0" var="pg"></c:set>
<%-- <%@include file="../common/qqConsult.jsp" %>
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>
 --%>
<div class="tcollege" >
	<iframe class="video" id="ifr" src="http://www.yy.com/30865280/" frameborder=0  marginwidth=0 marginheight=0 hspace=0 vspace=0  scrolling=no width="100%" onload="setIframeHeight(this)" height="120" ></iframe></td> 
</div>
</body>
</html>
<!-- <script type="text/javascript" >   
 $(document).ready(function(){
	var height=($(document).height()+33)+"px"; //浏览器当前窗口可视区域高度
	$(".video").height(height);
})
</script> -->
<script type="text/javascript" >   
 $(document).ready(function(){
	var height=document.documentElement.clientHeight; //浏览器当前窗口可视区域高度
	var bhgt=(height+33)+"px";
	$(".video").height(bhgt);
})
</script> 