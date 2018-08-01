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
<style type="text/css">
	.left_size {padding-left: 50px; font-size: 4.4em;}
</style>
</head>
<body>
	<div class="sucContain">
		<div class="containImg">
			<div class="infoList">
				<div class="listTit oranTxt matchBg cenTxt"><img class="imgWidt" src="../images_new/match.png"/><span id="times" class="coloPin bigSiz">${time}</span><span class="coloPin left_size">S</span></div>
				<div class="listTit bgColor">
					<span class="matcSucSp cenTxt" style="color:#fff;">交易团队匹配中，请耐心等待...</span>
				</div>
				<div class="matcBtn bgColor cenTxt smaWid">
					<a class="againTj" href="${ctx}/wxyqb/index.htm">再次提交</a>
					<a class="bacInd" href="${ctx}/wxyqb/backIndex.htm">返回首页</a>
				</div>
			</div>
		</div>
		<div class="footr center">
			<span class="spnTxt" style="color:#180000;">客服电话：010-53320537</span>
		</div>
	</div>
</body>

<script type="text/javascript">

function timeCount() {
	var times = $("#times").html();
	times = times-1;
	$("#times").html(times);
	if(times == 0) {
		clearTimeout(t);
		location.href="${ctx}/wxyqb/matchSuccess.htm";
	}else {
		t = setTimeout("timeCount()",1000);
	}
}

$(function() {
	timeCount();
});
</script>
</html>