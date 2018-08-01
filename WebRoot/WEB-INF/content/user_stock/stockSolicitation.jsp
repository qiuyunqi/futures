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
<title>${title}－余券宝APP宣传</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp" %>
<style>
	.contaer{padding-bottom: 0px !important;width:100%;}
	.contaier{position: absolute;width:100%;height:auto;}
	.contaier{position: absolute;width:100%;height:auto;}
	.contaier img{display: block;width:100%;}
	input[type=”button”], input[type=”submit”], input[type=”reset”] {-webkit-appearance: none;}
	.stockTxt{background: #ff7371;color:#fff;font-size: 25px;border:1px solid #ff7371;text-align: center;}
	.stockTxt p:first-child{margin-bottom:2%;}
	.stockTxt p{padding: 1% 0;}
	.stockTxt span{padding:1%;border:1px solid #fff;border-radius:5px;margin:0 1%;}
</style>
</head>
<body>
<div class="contaers">
			<div class="contaier">
				<img src="../images_yqb/yuquanbao1.png"/>
				<div id="stockTxt" class="stockTxt"><p>已参与市值<span>97590629.46</span>元</p><p>已创造收益<span>3415672</span>元</p></div>
				<img src="../images_yqb/yuquanbao3.png"/>
				<img src="../images_yqb/yuquanbao4.png"/>
				<img src="../images_yqb/yuquanbao5.png"/>
				<img src="../images_yqb/yuquanbao6.png"/>
			</div>
		</div>
</body>
</html>
<script type="text/javascript">
	var p0=document.getElementById('stockTxt')
	p0.style.fontSize=(document.body.clientWidth*0.05)+"px";
</script> 	
