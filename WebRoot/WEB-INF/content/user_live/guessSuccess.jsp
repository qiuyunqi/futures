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
html{width: 100%;height:100%; }
body {font: 12px Tahoma;margin: 0px auto;width:100%;background: #fefefe;height:100%; }
.sucContain{position: absolute;width:100%;height:100%;}
.titl{background:#004e99; color:#fff;font-size:1rem;text-align: center;padding:5% 0;}
.titl>a{display: block;float:left;width:3%;margin-left:3%;}
.titl>a>img{display:block;width:100%;}
.titl>span{text-align: center; margin-left: -3%;}
.containImg{margin-top:15%;margin-left:5%;width:90%;text-align: center;}
.containImg img{display: block;width:30%;margin: 0 auto;}
.containTxt{color:#333;font-size: 18px;margin-top: 5%;color:#ff516a;}
.containTxt a{color:#0099FF;}
.footer{position: fixed;bottom: 2%;width:90%;margin-left: 5%;}
.footer img{width:100% !important;}
.pyq{width:34% !important;padding: 0 0 3.8px !important;}
#fenx{margin-top: 15%;text-align: center;}
@media screen and (max-width:240px){
	.containImg{margin-top: 5%;}
	.containImg img{width: 20%;}
	.fenx{margin-top: 10%;height: 80px;}
}
@media screen and (min-width:768px){
    .containImg{margin-top: 5%;}
    .fenx{margin-top: 10%;height: 160px;}
}
</style>
</head>
<body>
	<div class="sucContain">
		<%-- <div class="titl"><a href="${ctx}/user_live/liveGuess.htm?user_id=${user_id}"><img src="../images_czt/leftJt.png"/></a><span>提交成功</span></div> --%>
		<div class="containImg">
			<img src="../images/cztPay.png"/>
			<div class="containTxt" id="containTxt">
				<p>提交成功</p>
				<p id="containAnswer">您竞猜的答案为：<span><fmt:formatNumber value="${empty guessAnswer?0.00:guessAnswer}" pattern="#,###,##0.00"/></span></p>
			</div>
		</div>
		<div id="fenx">开奖时间：下个工作日13点</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	var p0=document.getElementById('containTxt')
	p0.style.fontSize=(document.body.clientWidth*0.06)+"px";
	
	var p1=document.getElementById('containAnswer')
	p1.style.fontSize=(document.body.clientWidth*0.05)+"px";
	
	var p2=document.getElementById('fenx')
	p2.style.fontSize=(document.body.clientWidth*0.04)+"px";
</script> 	
	
