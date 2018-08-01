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
<title>${title}－余券宝</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp"%>
<link href="../css/newwx/newwx.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="sucContain">
		<div class="containImg">
			<div class="infoList">
				<div class="listTit oranTxt"><img class="imgWidt" src="../images_new/banner.png"></img></div>
				<div class="listCircle"><img class="imgWidt" src="../images_new/wxircle.png"/></div>
				<div class="listTit lineTx bgColor">
					<!-- <span id="lineTit">超出银行利率24倍，超出余额宝利率3倍</span> -->
					<img class="imgWidt" src="${yqb.profitImage}"/>
				</div>
				<div class="listTiBtn bgColor">
					<a class="btnA" href="${yqb.agreement}"><img src="../images_new/wxtn1.png"/></a>
					<a href="${yqb.rank}"><img src="../images_new/wxn2.png"/></a>
					<a href="${yqb.moreDetail}"><img src="../images_new/wxtn3.png"/></a>
				</div>
				<div class="jiange clear"></div>
			</div>
		</div>
		<div class="footer center fotBgCol">
			<span class="spnTxt fotBgCol">累计券值：${yqb.cumulativeVale}元</span>
			<a class="aStyBig" href="${ctx}/wxyqb/apply.htm">立即申请</a>
		</div>
	</div>
</body>
<script type="text/javascript">
	//控制间隔部分高度
	$(function(){
	 var hgt=$(".footer").height();
	 $(".jiange").height(hgt);
	});
	//控制字大小	
	var p0=document.getElementById('lineTit')
	p0.style.fontSize=(document.body.clientWidth*0.04)+"px";
</script> 	
</html>