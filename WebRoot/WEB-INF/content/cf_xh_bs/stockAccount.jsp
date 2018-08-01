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
<title>${title}－股票开户</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp"%>
<link href="../css/money.css" rel="stylesheet" type="text/css" />
<style>
 input{border:none;background: #fff;-webkit-appearance: none;}
        input[type="button"], input[type="submit"], input[type="reset"] {
            -webkit-appearance: none;
        }
input[type=checkbox] {
	visibility: hidden;
}
@media screen and (max-width:240px){
	.userReTitl{width: 55px; font-size: 10px;}
	.myReg{width: 35%;}
}
</style>
</head>
<body >
<form id="loginForm" name="loginForm">
<div class="moneyBody">
	<div class="moneyCont">
		<div class=" moneyMatter">
		  		<div class="moneyReg">
		  			<div class="moneyRgb">
		  				<a class="moneyRegZc defaultCol minSize" href="javascript:void(0);">1.注册报名</a><a class="moneyLogi actCol minSize" href="javascript:void(0);" >2.股票开户</a>
		  			</div>
		  		</div>
		  		<div class="moneyAcout clear">
					<img src="${ctx}/images_account/accoutSuces.png"/>
				</div>
				<div class="acntTxt minSize">
					<span>成功只差一步</span>
					<span>开个股票账户</span>
					<span>让实盘说话</span>
					<span>才能名正言顺的跟大神PK</span>
				</div>
		        <input type="button" id="account" class="monyNext minSize" value="马上前往&#13;&#10;财富证券开户" onclick="location='${url}'">
		        <div class="monyFoot">
		        	 <a class="monyBack minSize" href="${ctx}/cf_xh_bs/index.htm">先不，再想想</a>
		        </div>
		</div>
    </div>
</div>
</form>
</body>
</html>
<script type="text/javascript">
	//控制字大小	
	var p0=document.getElementById('lineTit')
	p0.style.fontSize=(document.body.clientWidth*0.04)+"px";
</script> 	