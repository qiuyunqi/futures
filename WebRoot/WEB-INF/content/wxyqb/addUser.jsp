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
<title>${title}－余券宝</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp"%>
<link href="../css/newwx/newwx.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="sucContain" style="height:100%;">
		<div class="containImg">
			<div class="infoList">
				<div class="addTit oranTxt cenTxt sureInfo"><span class="blok cenTxt tdyTit coloPin">今日收益(日)</span><span class="blok coloPin bigSiz cenTxt">00.00</span></div>
				<div class="leijiPri oranTxt cenTxt coloPin sureInfo">累计盈亏:00.00元</div>
				<div class="infoLi addUse">
				 	<div class="addUall"><span>股票账户</span><span class="addUsers">+添加账户</span></div>
					<div class="listTit bgWhite cenTxt loads">
						<img class="addUsLoad" src="../images_new/loads.png"/>
						<span class="coloPin">交易团队正在补全你的账户信息</br>请耐心等待...</span>
					</div>
				</div>
			</div>
		</div>
		<div class="footer center bgWhite">
			<span class="spnTxt coloPin">未缴费用：0.00元</span>
			<a class="aStyBig" href="javascript:alertInfo();">一键缴费</a>
		</div>
	</div>
<script src="${ctx}/js/layer/layer.js" type="text/javascript" ></script>

<script type="text/javascript">
function alertInfo() {
	layer.open({
	    content: '一键缴费、查询详情请下载APP',
	    btn: ['下载APP', '取消'],
	    shadeClose: false,
	    yes: function(){
	        location.href = "https://hhr360.com/app";
	    }
	});
}
 
$(".sureInfo").click(function() {
	alertInfo();
});
 
$(".addUsers").click(function() {
	location.href = "${ctx}/wxyqb/index.htm";
});
</script>
</body>

</html>