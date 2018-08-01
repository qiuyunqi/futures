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
<title>${title}－收益Top10</title>
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
.containImg{width:100%;text-align: center;font-size:1.2em;}
.list th{border-bottom:1px solid #dfdfdf;padding:3%;}
.list td{height:3em;}
.footer{position: fixed;bottom: 0;width:100%;background:#ededed;padding:2% 0;font-size: 1.2em; }
.center{text-align: center;margin:0 !important;}
.left{float:left;}
.orange{color:#f96900;}
.phoneNum{position: relative;margin-left: 10%;width: 90%;height: 3em;line-height: 3em;border-bottom:1px solid #dfdfdf;text-align: left;}
.phoneNum .topList{display: block;width:1.4em;float: left;height: 3em;margin-right:1em;}
.phoneNum .topNum{display: block;float: left;font-style: normal;border-radius: 1.4em;color: white;font-style: normal;height: 1.4em;line-height: 1.4em;list-style-type: none;text-align: center;width: 1.4em;background: #d2d2d2;margin-top:0.8em;margin-right:1em;}
.topFir{background: rgba(0, 0, 0, 0) url("../images_yqb/top1.png") no-repeat scroll center center / 100% auto;}
.topSec{background: rgba(0, 0, 0, 0) url("../images_yqb/top2.png") no-repeat scroll center center / 100% auto;}
.topThi{background: rgba(0, 0, 0, 0) url("../images_yqb/top3.png") no-repeat scroll center center / 100% auto;}
.listRgt{width:90%;margin-right:10%;height:3em;line-height: 3em;border-bottom:1px solid #dfdfdf;}
.bandown{height:3em;}
@media screen and (max-width:240px){
	.containImg{margin-top: 5%;font-size:1em;}
	.containImg img{width: 20%;}
}
@media screen and (min-width:768px){
    .containImg{margin-top: 5%;}
    .phoneNum{text-align: center;}
}
</style>
</head>
<body>
	<div class="sucContain">
		<div class="containImg">
			<table class="list" cellpadding="0" cellspacing="0" width="100%" border="0">
			      <tr>
			        <th class="center">用户</th>
			        <th class="center">收益（元）</th>
			       </tr>
			       <tr>
			       	<td><div class="phoneNum"><i class="topList topFir"></i><span><c:if test="${list[0]!=null}">${fn:substring(list[0].userName,0,3)}<c:forEach begin='3' end='7'>*</c:forEach>${fn:substring(list[0].userName,7,11)}</c:if></span></div></td>
			       	<td class="orange"><div class="listRgt"><c:if test="${list[0]!=null}"><fmt:formatNumber value="${empty list[0].getMoney?'0.00':list[0].getMoney}" pattern="#,###,##0.00"/></c:if></div></td>
			       </tr>
			       <tr>
			       	<td><div class="phoneNum"><i class="topList topSec"></i><span><c:if test="${list[1]!=null}">${fn:substring(list[1].userName,0,3)}<c:forEach begin='3' end='7'>*</c:forEach>${fn:substring(list[1].userName,7,11)}</c:if></span></div></td>
			       	<td class="orange"><div class="listRgt"><c:if test="${list[1]!=null}"><fmt:formatNumber value="${empty list[1].getMoney?'0.00':list[1].getMoney}" pattern="#,###,##0.00"/></c:if></div></td>
			       </tr>
			       <tr>
			       	<td><div class="phoneNum"><i class="topList topThi"></i><span><c:if test="${list[2]!=null}">${fn:substring(list[2].userName,0,3)}<c:forEach begin='3' end='7'>*</c:forEach>${fn:substring(list[2].userName,7,11)}</c:if></span></div></td>
			       	<td class="orange"><div class="listRgt"><c:if test="${list[2]!=null}"><fmt:formatNumber value="${empty list[2].getMoney?'0.00':list[2].getMoney}" pattern="#,###,##0.00"/></c:if></div></td>
			       </tr>
			       <tr>
			       	<td><div class="phoneNum"><i class="topNum">4</i><span><c:if test="${list[3]!=null}">${fn:substring(list[3].userName,0,3)}<c:forEach begin='3' end='7'>*</c:forEach>${fn:substring(list[3].userName,7,11)}</c:if></span></div></td>
			       	<td class="orange"><div class="listRgt"><c:if test="${list[3]!=null}"><fmt:formatNumber value="${empty list[3].getMoney?'0.00':list[3].getMoney}" pattern="#,###,##0.00"/></c:if></div></td>
			       </tr>
			       <tr>
			       	<td><div class="phoneNum"><i class="topNum">5</i><span><c:if test="${list[4]!=null}">${fn:substring(list[4].userName,0,3)}<c:forEach begin='3' end='7'>*</c:forEach>${fn:substring(list[4].userName,7,11)}</c:if></span></div></td>
			       	<td class="orange"><div class="listRgt"><c:if test="${list[4]!=null}"><fmt:formatNumber value="${empty list[4].getMoney?'0.00':list[4].getMoney}" pattern="#,###,##0.00"/></c:if></div></td>
			       </tr>
			       <tr>
			       	<td><div class="phoneNum"><i class="topNum">6</i><span><c:if test="${list[5]!=null}">${fn:substring(list[5].userName,0,3)}<c:forEach begin='3' end='7'>*</c:forEach>${fn:substring(list[5].userName,7,11)}</c:if></span></div></td>
			       	<td class="orange"><div class="listRgt"><c:if test="${list[5]!=null}"><fmt:formatNumber value="${empty list[5].getMoney?'0.00':list[5].getMoney}" pattern="#,###,##0.00"/></c:if></div></td>
			       </tr>
			       <tr>
			       	<td><div class="phoneNum"><i class="topNum">7</i><span><c:if test="${list[6]!=null}">${fn:substring(list[6].userName,0,3)}<c:forEach begin='3' end='7'>*</c:forEach>${fn:substring(list[6].userName,7,11)}</c:if></span></div></td>
			       	<td class="orange"><div class="listRgt"><c:if test="${list[6]!=null}"><fmt:formatNumber value="${empty list[6].getMoney?'0.00':list[6].getMoney}" pattern="#,###,##0.00"/></c:if></div></td>
			       </tr>
			       <tr>
			       	<td><div class="phoneNum"><i class="topNum">8</i><span><c:if test="${list[7]!=null}">${fn:substring(list[7].userName,0,3)}<c:forEach begin='3' end='7'>*</c:forEach>${fn:substring(list[7].userName,7,11)}</c:if></span></div></td>
			       	<td class="orange"><div class="listRgt"><c:if test="${list[7]!=null}"><fmt:formatNumber value="${empty list[7].getMoney?'0.00':list[7].getMoney}" pattern="#,###,##0.00"/></c:if></div></td>
			       </tr>
			       <tr>
			       	<td><div class="phoneNum"><i class="topNum">9</i><span><c:if test="${list[8]!=null}">${fn:substring(list[8].userName,0,3)}<c:forEach begin='3' end='7'>*</c:forEach>${fn:substring(list[8].userName,7,11)}</c:if></span></div></td>
			       	<td class="orange"><div class="listRgt"><c:if test="${list[8]!=null}"><fmt:formatNumber value="${empty list[8].getMoney?'0.00':list[8].getMoney}" pattern="#,###,##0.00"/></c:if></div></td>
			       </tr>
			       <tr>
			       	<td><div class="phoneNum"><i class="topNum">10</i><span><c:if test="${list[9]!=null}">${fn:substring(list[9].userName,0,3)}<c:forEach begin='3' end='7'>*</c:forEach>${fn:substring(list[9].userName,7,11)}</c:if></span></div></td>
			       	<td class="orange"><div class="listRgt"><c:if test="${list[9]!=null}"><fmt:formatNumber value="${empty list[9].getMoney?'0.00':list[9].getMoney}" pattern="#,###,##0.00"/></c:if></div></td>
			       </tr>
			 </table>
		</div>
		<div class="bandown"></div>
		<div class="footer center">
			截止时间：<fmt:formatDate value="${now}" pattern="yyyy.MM.dd"/>
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
	
