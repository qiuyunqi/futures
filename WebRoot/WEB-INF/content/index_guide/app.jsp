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
<title>${title}－App下载（ios）</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp" %>
<style>
html{width: 100%;height:auto;}
body{width: 100%;height: 100%;background: #f5f5f5;}
        .app-wrapper{width: 90%;height: 100%;margin:0 auto;}
        .app-content{height:100%;margin:0 auto;}
        .app-txt{height: auto;margin: 20% auto 0;}
        .app-txtimg{display: block;width: 25%;margin:0 auto 20%;}
        .app-txt span{font-size:2.5em;display: block;text-align: center;color: #4ab0ee;line-height: 1.5em;}
        .app-txt span:last-child{font-size:1.7em;}
        .aapbtn{  width: inherit;margin: 20% auto 0;}
        .aapbtn>a{display: block;width: auto;margin:0 auto;}
        .appimge{display: block;width:60%;margin:0 auto;}
        .beizhu{margin: 8% auto 0;font-size: 1.2em;text-align: center;display: block;  color: #666666;}
</style>
</head>
<body>
	<div class="app-wrapper">
		<div class="app-content">
			<div class="app-txt">
				<img src="../images_hhr/logohy1.png" class="app-txtimg"/>
				<span>超 级 合 伙 人</span>
				<span>因为合伙，所以改变</span>
			</div>
			<!-- <a href="http://a.app.qq.com/o/simple.jsp?pkgname=com.hhr360.partner"><img src="../images_hhr/a-1.png"/></a> -->
			<div class="aapbtn">
			<a href="http://a.app.qq.com/o/simple.jsp?pkgname=com.hhr360.partner"><img src="../images_hhr/a-2.png" class="appimge"/></a> 
			<!-- <a href="http://a.app.qq.com/o/simple.jsp?pkgname=com.hhr360.partner"><img src="../images_hhr/a-2.png" class="appimge"/></a> -->
			<span class="beizhu">通过微信扫描的页面，请在浏览器中打开并点击下载。<a href="${ctx}/index_guide/appHelp.htm" style="color:#0099CC;">如何打开？</a></span>
			</div>
			
		</div>
	</div>
</body>
</html>
