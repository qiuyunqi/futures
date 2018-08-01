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
<title>${title}－关于</title>
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
				<div class="aboutHead blueCol">
					<img src="../images_yqb/aboutLogo.png"/>
					<p class="aboutTX">超级合伙人</p>
					<p class="smallSize">让人脉帮你理财，做有态度的金融服务！</p>
				</div>
		        <!-- 列表 -->
		        <div class="meList aqbot">
		         	<a class="bohaoTd" href="tel:010-53320537">010-53320537</a>
		        	<table class="aboutInfo" cellpadding="0" cellspacing="0" width="98%" border="0">
					      <tr onclick="window.location.href='http://www.hhr360.com/app'">
					      	<th><img class="aboutTx" src="../images_yqb/aboutDl.png"/></th>
					      	<td class="blueCol siz firstTd">
					      		下载超级合伙人APP
					      	</td>
					      	<td class="blueCol smallSize aboutJt">
					      		<img class="lftJt" src="../images_yqb/mejtou.png"/>
					      	</td>
				         </tr>	
					     <tr onclick="wcheat();">
					      	<th><img class="aboutTx" src="../images_yqb/wechat.png"/></th>
					      	<td>
					      		<span class="blueCol siz blok">微信公众号</span>
					      		<span class="gryCol sSmalSiz">点击关注公众账号(hhr-360)</span>
					      	</td>
					      	<td class="blueCol smallSize aboutJt">
					      		<img class="lftJt" src="../images_yqb/mejtou.png"/>
					      	</td>
				         </tr>
					      <tr onclick="window.location.href='http://www.sobot.com/chat/h5/index.html?sysNum=6870ddb5b067480b8d3e7fa80dec3520&source=1'">
					      	<th><img class="aboutTx" src="../images_yqb/kefu.png"/></th>
					      	<td class="blueCol siz firstTd">
					      		在线客服
					      	</td>
					      	<td class="blueCol smallSize aboutJt">
					      		<img class="lftJt" src="../images_yqb/mejtou.png"/>
					      	</td>
				         </tr>	
				         <tr class="bohaoHid">
					      	<th><img class="aboutTx" src="../images_yqb/kfNum.png"/></th>
					      	<td>
					      		<span class="blueCol siz blok">客服电话</span>
					      		<span class="gryCol sSmalSiz">010-53320537</span>
					      	</td>
					      	<td class="blueCol smallSize aboutJt">
					      		<img class="lftJt" src="../images_yqb/mejtou.png"/>
					      	</td>
				         </tr>
				         <tr onclick="window.location.href='http://j.map.baidu.com/UhN_B'">
					      	<th><img class="aboutTx" src="../images_yqb/address.png"/></th>
					      	<td>
					      		<span class="blueCol siz blok">公司地址</span>
					      		<span class="gryCol sSmalSiz">北京市海淀区高粱桥斜街28号</span>
					      	</td>
					      	<td class="blueCol smallSize aboutJt">
					      		<img class="lftJt" src="../images_yqb/mejtou.png"/>
					      	</td>
				         </tr>
				         <tr onclick="window.location.href='https://www.hhr360.com'">
					      	<th><img class="aboutTx" src="../images_yqb/logo2.png"/></th>
					      	<td>
					      		<span class="blueCol siz blok">超级合伙人官网</span>
					      		<span class="gryCol sSmalSiz">www.hhr360.com</span>
					      	</td>
					      	<td class="blueCol smallSize aboutJt">
					      		<img class="lftJt" src="../images_yqb/mejtou.png"/>
					      	</td>
				         </tr>
				         <tr onclick="window.location.href='https://www.hhr360.com/index_guide/agreement_hhr.htm'">
					      	<th><img class="aboutTx" src="../images_yqb/agreLogo.png"/></th>
					      	<td>
					      		<span class="blueCol siz blok">服务协议</span>
					      		<span class="gryCol sSmalSiz">超级合伙人版权说明，请仔细阅读</span>
					      	</td>
					      	<td class="blueCol smallSize aboutJt">
					      		<img class="lftJt" src="../images_yqb/mejtou.png"/>
					      	</td>
				         </tr>
				    </table>
		        </div>
			</div>
		<div class="yqbJg"></div>
		</div>
	</div>
	<!-- 弹出二维码 -->
	<div class="hideNew fuchen" style="display: none;">
			<a class="noSur">x</a>
			<div class="wxEWM">
				<img src="../images_yqb/wxcode.png">
			</div>
		</div>
</body>
<link href="../css/wxYqb.css" rel="stylesheet" type="text/css" />
</html>
<script>
	function wcheat(){
		$(".fuchen").show();
	}
	
	$(".noSur").click(function(){
		$(".fuchen").hide();
	});
</script>