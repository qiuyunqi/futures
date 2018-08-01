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
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－App下载帮助</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp" %>
<script src="../js_hhr/jquery-1.11.1.min.js"></script>
<style>
html{width: 100%;height:auto;}
body{width: 100%;height: 100%;background: #f5f5f5;}
        .app-wrapper{width: 100%;height: 100%;margin:0 auto;}
        .app-content{height:100%;margin:0 auto;}
        .app-content img{display:block;width:100%;}
</style>
</head>
<body>
	<div class="app-wrapper" id="apfs">
		<div class="app-content">
			<img src="../images_hhr/iosH1.png" alt="" />
		</div>
	</div>
	<div class="app-wrapper one" id="apse">
		<div class="app-content firs">
			<img src="../images_hhr/iosH2.png" alt="" />
		</div>
	</div>
	<div class="app-wrapper two" id="apth" >
		<div class="app-content sec">
			<img src="../images_hhr/iosH3.png" alt="" />
		</div>
	</div>
</body>
</html>
<script>
	$(function(){
		$("#apse").hide();
		$("#apth").hide();
		$("#apfs").bind("click",function(){
			$("#apfs").hide();
			$("#apse").show();
		});
		$("#apse").bind("click",function(){
			$("#apfs").hide();
			$("#apse").hide();
			$("#apth").show();
		});
		$("#apth").bind("click",function(){
			window.open('${ctx}/index_guide/app.htm');
		});
	})
</script>