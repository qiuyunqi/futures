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
<title>${title}－购买成功</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp"%>
<style>
html{width: 100%;height:100%;overflow: hidden; }
body {font: 12px Tahoma;margin: 0px auto;width:100%;background: #fefefe;height:100%;overflow: hidden; }
.sucContain{position: absolute;width:100%;height:100%;}
.containImg{margin-top:15%;margin-left:5%;width:90%;text-align: center;}
.containImg img{display: block;width:35%;margin: 0 auto;}
.containTxt{color:#333;font-size: 22px;margin-top: 5%;color:#ff516a;}
.containTxt a{color:#0099FF;}
.footer{position: fixed;bottom: 2%;width:90%;margin-left: 5%;}
.footer img{width:100% !important;}
.pyq{width:34% !important;padding: 0 0 3.8px !important;}
.fenx{margin-top: 15%;text-align: center;}
.fenx span{color:#fdbc40;width:90%;margin-left:5%;display: block;}
.fenx span img{display:block;width:100%;}
.fenxBtn{display: block;width:100%;text-align: center;font-size:14px;}
.fenxBtn img{display: block;width:50%; margin: 0 auto;}
.fenxBtn span{color:black;}
@media screen and (max-width:240px){
	.containImg{margin-top: 5%;}
	.containImg img{width: 20%;}
	.fenx{margin-top: 10%;height: 80px;}
	.fenxBtn span{font-size: 12px;}
}
@media screen and (min-width:768px){
    .containImg{margin-top: 5%;}
    .fenx{margin-top: 10%;height: 160px;}
}
</style>
</head>
<body>
	<div class="sucContain">
		<div class="containImg">
			<img src="../images/cztPay.png"/>
			<div class="containTxt" id="containTxt">
				购买成功<a id="buy_again" onclick="_hmt.push(['_trackEvent', 'nav', 'click', 'buy_again'])" href="${ctx}/user_options/wqqIndex.htm">继续竞猜</a>
			</div>
		</div>
		<div class="fenx">
				<!-- <ul>
					<li><a class="fenxBtn"><img src="../images/cztWX.png"/><span>微信好友</span></a></li>
					<li class="pyq"><a class="fenxBtn"><img src="../images/cztPYQ.png"/><span>微信朋友圈</span></a></li>
					<li><a class="fenxBtn" href="http://v.t.sina.com.cn/share/share.php?appkey=2208944054&url=https://www.hhr360.com/user_options/cztIndex.htm&title=7天猜涨停，最高收益1.3倍&&source=xxx&sourceUrl=xxx&content=xxx"><img src="../images/sina.png"/><span>新浪</span></a></li>
				</ul> -->
				<span class="fenxIco"><img src="../images_czt/fxBtn.png"/></span>
		</div>
		</div>
		<div class="footer"><img src="../images/cztFooter.png"/></div>
	</div>
</body>
</html>
<script type="text/javascript">
	var p0=document.getElementById('containTxt')
	p0.style.fontSize=(document.body.clientWidth*0.08)+"px";
</script> 	
	
