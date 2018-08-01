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
<title>${title}－我的排名</title>
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
<body style="background:#ffe7a7; ">
	<div class="sucContain">
		<!-- 收益begin -->
		<div class="bboxTop">
			<div class="bboxTopTit minSize"><span class="topBg">收益排行</span></div>
			<div class="containImg">
			<table class="list" cellpadding="0" cellspacing="0" width="100%" border="0">
			      <tr>
			        <th class="center">您的排名</th>
			        <th class="center">用户</th>
			        <th class="center">收益率</th>
			       </tr>
			       <tr>
			       	<td><div class="phoneNum"><i>${rank}</i><span></span></div></td>
			       	<td><div class="listRgt">${userName}</div></td>
			       	<td><div class="listRgt">${profit}%</div></td>
			       </tr>
			       <tr>
			       	<c:if test="${disparity == 1}">
				       	<td colspan="3"><div class="pdUp">大神，啥都别说了，您是收益第一名！</div></td>
			       	</c:if>
			       	<c:if test="${disparity != 1}">
				       	<td colspan="3"><div class="pdUp">您的收益落后第一名<span>${disparity}%</span>，请继续加油哦~</div></td>
			       	</c:if>
			       </tr>
			 </table>
		</div>
		</div>
		<!-- 推荐begin -->
		<div class="bboxTop">
			<div class="bboxTopTit minSize"><span class="topBg">推荐人奖</span></div>
			<div class="containImg">
				<table class="list" cellpadding="0" cellspacing="0" width="100%" border="0">
				     <tr>
				        <th class="center">您的排名</th>
				        <th class="center">用户</th>
				        <th class="center">推荐人数</th>
				      </tr>
				      <tr>
				       	<td><div class="phoneNum"><i>${recommendRank}</i><span></span></div></td>
				       	<td><div class="listRgt">${userName}</div></td>
				       	<td><div class="listRgt">${recommendNum}</div></td>
				      </tr>
				      <tr>
				      	<c:if test="${recommendDisparity == 1}">
					       	<td colspan="3"><div class="pdUp">大神，啥都别说了，您推荐的好友最多啦！</div></td>
				      	</c:if>
				      	<c:if test="${recommendDisparity != 1}">
					       	<td colspan="3"><div class="pdUp">您落后第一名<span>${recommendDisparity }</span>个推荐数，请继续加油哦~</div></td>
				      	</c:if>
				      </tr>
				 </table>
			</div>
		</div>
		<!--推荐end -->
		<!-- 幸运奖begin -->
		<div class="bboxTop">
			<div class="bboxTopTit minSize"><span class="topBg">&nbsp;幸运奖&nbsp;</span></div>
			<c:if test="${isLucky == 0}">
				<div class="luckyBody clear">很遗憾您未获得“幸运奖”，以后还会有机会哦~</div>
			</c:if>
			<c:if test="${isLucky == 1}">
				<div class="luckyBody clear">没错, 您就是那个最幸运的人, 恭喜您获取到幸运奖</div>
			</c:if>
		</div>
	
		<!-- 幸运end -->
		<input type="button" id="login" class="monyNext" value="邀请好友参赛" onclick="location='${ctx}/cf_xh_bs/inviteFriend.htm'";>
		<div class="monyFoot" style="margin:1% 10% 5%;">
		     <a class="monyBack" href="${ctx}/cf_xh_bs/index.htm">返回参赛页</a>
		</div>
	</div>
</body>
</html>