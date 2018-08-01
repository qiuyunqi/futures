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
<title>${title}－邀请好友参赛</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp"%>
<link href="../css/money.css" rel="stylesheet" type="text/css" />
<style>
@media screen and (max-width:240px){
	.minSize {font-size: 0.7rem;}
	.topBg{height: 26px;line-height: 26px;}
	.lookTop{height: 20px;line-height: 20px;}
}
@media screen and (min-width:768px){
    .containImg{margin-top: 5%;}
    .phoneNum{text-align: center;}
}
</style>
</head>
<body style="background:#ffe8a3; ">
	<div class="sucContain">
		<div class="moneyShare">
			<a href="http://service.weibo.com/share/share.php?appkey=&title=我参加了实盘炒股大赛，肯定能拿到100万冠军奖！&url=https://www.hhr360.com&pic=https://hhr360.com/images_account/share_cf_xh.png&searchPic=false&style=simple" target="_blank"><img src="../images_account/sina.png">新浪微博</a></div>
		<div class="inviteTxt">邀请有奖</br>邀请好友一起</br>参加实盘股票大赛</br>赢取丰厚奖金</div>
		<div class="monyFoot" style="margin:10% 10% 5%;">
		     <p><a class="monyBack" href="http://service.weibo.com/share/share.php?appkey=&title=我参加了实盘炒股大赛，肯定能拿到100万冠军奖！&url=https://www.hhr360.com&pic=https://hhr360.com/images_account/share_cf_xh.png&searchPic=false&style=simple" target="_blank">点击分享</a></p>
		     <a class="monyBack" href="${ctx}/cf_xh_bs/index.htm">返回参赛页</a>
		</div>
	</div>
</body>
</html>