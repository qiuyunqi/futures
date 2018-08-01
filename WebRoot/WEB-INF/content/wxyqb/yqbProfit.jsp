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
	<div class="sucContain" style="height:100%;">
		<div class="containImg">
			<div class="infoList">
				<div class="addTit oranTxt cenTxt sureInfo"><span class="blok cenTxt tdyTit coloPin">今日收益(日)</span><span class="blok coloPin bigSiz cenTxt">${dayProfit}</span></div>
				<div class="leijiPri oranTxt cenTxt coloPin sureInfo">累计盈亏:${totalProfit}元</div>
				<div class="infoLi addUse">
				 	<div class="addUall"><span>股票账户</span><span class="addUsers">+添加账户</span></div>
				</div>
				<!-- 证券列表 -->
				<c:forEach items="${fsaList}" var="fsa">
				<div class="profitList bgWhite ">
					<div class="addUse coloGray padHeg">${fsa.openEquity}：${fsa.capitalAccount}</div>
					<c:if test="${empty message}">
						<div class="profitLists"><span class="coloGray flatL">昨日盈利</span><span class="addUse flatR coloRed">${fsa.nowProfit}元</span></div>
					</c:if>
					<c:if test="${message}">
						<div class="profitLists"><span class="coloGray flatL message">${message}</span></div>
					</c:if>
				</div>
				<hr style="height:2px;border:none;border-bottom:1px solid #ededed;"/>
				</c:forEach>
				<div class="jiange clear"></div>
			</div>
		</div>
		<div class="footer center bgWhite">
			<span class="spnTxt coloPin">未缴费用：${noneTotalFee}元</span>
			<a class="aStyBig" href="javascript:sureInfo();">一键缴费</a>
		</div>
		
		<!-- <div class="hide fuchen">
			<div class="fucBg">
				<div class="bordRadiu">
					<div class="fucBgHead"><img class="imgWidt" src="../images_yqb/wxNumber.png"/></div>
						<div class="loadApp coloPin cenTxt">一键缴费，提现，查看更多账户详情<br/>请下载APP后进行操作</div>
						<div class="btnAlls">
						  	<a class="wxCancel">取消</a>
						  	<a class="wxSure">下载App</a>
						</div>
				</div>
			</div>
		</div> -->
	</div>
</body>
<script src="../js/layer/layer.js"/>
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
<script type="text/javascript">
 function sureInfo() {
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
 	sureInfo();
 });
 
 $(".addUsers").click(function() {
 	location.href = "${ctx}/wxyqb/index.htm";
 });
</script> 	
</html>