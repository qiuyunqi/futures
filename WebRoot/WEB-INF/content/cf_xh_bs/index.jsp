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
<title>${title}－财富小合杯</title>
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
<body>
	<div class="sucContain">
		<div class="moyIndexBan">
			<img src="../images_account/indexBg.png" />
			<div class="moyIndexTxt textCen">
				<div class="minSize textCen">报名时间:2016.07.01-2016.10.15</div>
				<div class="minSize textCen">比赛时间:2016.08.15-2016.11.15</div>
<%-- 				<input type="button" class="indxReg minSize" value="立即报名" onclick="location='${ctx}/cf_xh_bs/login.htm'"> --%>
				<input type="button" class="indxReg minSize" value="立即报名" onclick="location='${url}'">
				<a class="minSize indxA" href="${ctx}/cf_xh_bs/gameRule.htm">点击查看比赛规则</a>
			</div>
		</div>
		<!-- 收益牛人榜begin -->
		<div class="bboxTop">
			<div class="bboxTopTit minSize"><span class="topBg">收益牛人榜（7名）</span><a class="lookTop" href="javascript:void(0);">查看我的排名</a></div>
			<div class="containImg">
			<table class="list" cellpadding="0" cellspacing="0" width="100%" border="0">
			      <tr>
			        <th class="center">排名</th>
			        <th class="center">用户</th>
			        <th class="center">收益率</th>
			       </tr>
			       <c:forEach items="${mxcList}" var="mxc" varStatus="index">
			       		
			       			<tr>
						       	<td><div class="phoneNum">
						       		<c:if test="${index.index == 0}">
						       			<i class="topList topFir"></li>
						       		</c:if>
						       		<c:if test="${index.index == 1}">
						       			<i class="topList topSec"></li>
						       		</c:if>
						       		<c:if test="${index.index == 2}">
						       			<i class="topList topThi"></li>
						       		</c:if>
						       		<c:if test="${index.index != 0 && index.index != 1 && index.index != 2}">
						       			<i>${index.index+1}</li>
						       		</c:if>
						     	<span></span></div></td>
						       	<td><div class="listRgt">${mxc.phone}</div></td>
						       	<td><div class="listRgt">${mxc.profit }%</div></td>
						    </tr>
			       </c:forEach>
			       <!-- 
			       <tr>
			       	<td><div class="phoneNum"><i class="topList topFir"></i><span></span></div></td>
			       	<td><div class="listRgt">132****5678</div></td>
			       	<td><div class="listRgt">2.50%</div></td>
			       </tr>
			       <tr>
			       	<td><div class="phoneNum"><i class="topList topSec"></i><span></span></div></td>
			       	<td><div class="listRgt">132****5678</div></td>
			       	<td><div class="listRgt">2.50%</div></td>
			       </tr>
			       <tr>
			       	<td><div class="phoneNum"><i class="topList topThi"></i><span></span></div></td>
			       	<td><div class="listRgt">132****5678</div></td>
			       	<td><div class="listRgt">2.50%</div></td>
			       </tr>
			       <tr>
			       	<td><div class="phoneNum"><i>4</i><span></span></div></td>
			       	<td><div class="listRgt">132****5678</div></td>
			       	<td><div class="listRgt">2.50%</div></td>
			       </tr> -->
			 </table>
		</div>
		</div>
		<!-- 幸运奖begin -->
		<div class="bboxTop">
			<div class="bboxTopTit minSize"><span class="topBg">&nbsp;&nbsp;&nbsp;幸运奖（10名）&nbsp;&nbsp;&nbsp;</span></div>
			<div class="containImg">
			<table class="list" cellpadding="0" cellspacing="0" width="100%" border="0">
			      <tr>
			        <th class="center">排名</th>
			        <th class="center">用户</th>
			        <th> </th>
			       </tr>
			       <c:forEach items="${luckyList}" var="lucky" varStatus="index">
			       <tr>
			       	<td><div class="phoneNum"><i>${index.index+1}</i><span></span></div></td>
			       	<td><div class="listRgt">${lucky.phone }</div></td>
			       	<td></td>
			       </tr>
			     	</c:forEach>
			 </table>
		</div>
		</div>
		<!--幸运奖end -->
		<!-- 推荐人奖begin -->
		<div class="bboxTop">
			<div class="bboxTopTit minSize"><span class="topBg">&nbsp;推荐人奖（4名）&nbsp;</span></div>
			<div class="containImg">
			<table class="list" cellpadding="0" cellspacing="0" width="100%" border="0">
			      <tr>
			        <th class="center">排名</th>
			        <th class="center">用户</th>
			        <th class="center">推荐人数</th>
			       </tr>
			        <c:forEach items="${recommendList}" var="recommend" varStatus="index">
			       			<tr>
						       	<td><div class="phoneNum">
						       		<c:if test="${index.index == 0}">
						       			<i class="topList topFir"></li>
						       		</c:if>
						       		<c:if test="${index.index == 1}">
						       			<i class="topList topSec"></li>
						       		</c:if>
						       		<c:if test="${index.index == 2}">
						       			<i class="topList topThi"></li>
						       		</c:if>
						       		<c:if test="${index.index != 0 && index.index != 1 && index.index != 2}">
						       			<i>${index.index+1}</li>
						       		</c:if>
						     	<span></span></div></td>
						       	<td><div class="listRgt">${recommend.phone}</div></td>
						       	<td><div class="listRgt">${recommend.recommendNum}</div></td>
						    </tr>
			       </c:forEach>
			      <!--  
			       <tr>
			       	<td><div class="phoneNum"><i class="topList topFir"></i><span></span></div></td>
			       	<td><div class="listRgt">132****5678</div></td>
			       	<td><div class="listRgt">2</div></td>
			       </tr>
			       <tr>
			       	<td><div class="phoneNum"><i class="topList topSec"></i><span></span></div></td>
			       	<td><div class="listRgt">132****5678</div></td>
			       	<td><div class="listRgt">2</div></td>
			       </tr>
			       <tr>
			       	<td><div class="phoneNum"><i class="topList topThi"></i><span></span></div></td>
			       	<td><div class="listRgt">132****5678</div></td>
			       	<td><div class="listRgt">2</div></td>
			       </tr>
			       <tr>
			       	<td><div class="phoneNum"><i>4</i><span></span></div></td>
			       	<td><div class="listRgt">132****5678</div></td>
			       	<td><div class="listRgt">2</div></td>
			       </tr> -->
			 </table>
		</div>
	</div>
		<!-- 推荐人奖end -->
		<div class="indexNum">
			<div class="minSize">报名参赛咨询：</br><span class="redCol">010-53320537</span>（小合网络客服热线）</div>
<!-- 			<div class="minSize">股票开户咨询：</br><span class="redCol">400-8835-316</span>（财富证券客服热线）</div> -->
			<div class="minSize">股票开户咨询：</br><span class="redCol">010-62618882，010-62617440</span>（财富证券客服热线）</div>
		</div>
		<div class="footer textCen">
			<%-- <a class="aStyBig" href="${ctx}/cf_xh_bs/login.htm">立即报名</a> --%>
			<a class="aStyBig" href="${url}">立即报名</a>
		</div>
	</div>
	<!-- 弹窗 -->
	<div class="hidBody">
		<div class="hidde" >
			<div>请输入参赛登记的手机号</br>查询您的排名</div>
			<input type='text' name='chartNum' class='chartNum' placeholder="手机号"/>
			<div style="line-height: 25px;">
				<a class="monyStoc" href="javascript:void(0);">查看我的排名</a></br>
				<a class="monyBack" href="${ctx}/cf_xh_bs/index.htm">以后再查</a>
			</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript" src="${ctx}/js/layer/layer.js"></script>
<script type="text/javascript">
var _hmt = _hmt || [];
(function() {
var hm = document.createElement("script");
hm.src = "https://hm.baidu.com/hm.js?cb76fdd0655f5b05731cbe36b6b46309";
var s = document.getElementsByTagName("script")[0]; 
s.parentNode.insertBefore(hm, s);
})();
	
	//弹窗
	$(".hidBody").hide();
	$(".lookTop").click(function(){
		$(".hidBody").show();
	});
	
	// 查看我的排名
	$(".monyStoc").click(function() {
		var $chartNum = $(".chartNum").val();
		if ($chartNum) {
			$.post("${ctx}/cf_xh_bs/findMyRank.htm", {"phone": $chartNum}, function(data){
				if (data == 1) {
					layer.open({
						content: "请输入手机号码",
						btn: ["确定"]
					});
					return null;
				} else if (data == 2) {
					layer.open({
						content: "请先报名比赛",
						btn: ["确定"]
					});
					return null;
				} else if (data == 3) {
					location.href = "${ctx}/cf_xh_bs/myChart.htm?phone="+$chartNum;
				} else {
					layer.open({
						content: "请联系客服",
						btn: ["确定"]
					});
				} 
			});
		} else {
			layer.open({
				content: "请输入手机号码",
				btn: ["确定"]
			});
		}
	});
</script>
