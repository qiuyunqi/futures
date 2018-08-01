<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－推广详情</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/css.jsp" %>
</head>
<body style=" background:#f2f2f2">
<%@include file="../common/userTop.jsp" %>
<div class="center">
    <c:set var="pg" value="9"></c:set>
	<%@include file="../common/left.jsp" %>
	<div class="fl rt_cont">
    	<div class="rt_cont_title">客服电话：010-53320537&nbsp;&nbsp;&nbsp;&nbsp;工作时间：8:30-17:00</div>
       <div class=" dxgs">
        	<ul>
            	<li class="yxz"><a href="#">推广详情</a></li>
                <li><a href="${ctx}/user_promote/promoteLink.htm">推广链接</a></li>
                <li><a href="${ctx}/user_promote/visitRecord.htm">访问记录</a></li>
                <li ><a href="${ctx}/user_promote/myUsers.htm">我的用户</a></li>
            </ul>
            <div class="clr"></div>
        </div>
       <div class=" tgxq">
       		<table border="0" cellspacing="0" cellpadding="0">
  <tbody><tr>
    <td>当前佣金：<span style=" font-weight:600;color:#f28208;"><fmt:formatNumber value="${empty fuUser.commissionTotal?0:fuUser.commissionTotal}" pattern="#,###,##0.00"/></span>元</td>
    <td>下线用户：<span style=" font-weight:600;color:#f28208;">${fuUser.extendPersonNum}</span>个</td>
    <td>访问次数：<span style=" font-weight:600;color:#f28208;">${fuUser.visitNum}</span>次</td>
    <td>短线高手佣金：服务费的<span style=" font-weight:600;color:#f28208;">14%</span></td>
  </tr>
  <tr>
    <td>已兑佣金：<span style=" font-weight:600;color:#f28208;"><fmt:formatNumber value="${empty fuUser.exchangeMoney?0:fuUser.exchangeMoney}" pattern="#,###,##0.00"/></span>元</td>
    <td>借贷用户：<span style=" font-weight:600;color:#f28208;">${fuUser.borrowPersonNum}</span>个</td>
    <td>访问IP：<span style=" font-weight:600;color:#f28208;">${fuUser.visitIp}</span>次</td>
    <td style=" text-align:right">配资佣金：服务费的<span style=" font-weight:600;color:#f28208;">14%</span></td>
  </tr>
</tbody></table>
       </div>
      <div class="fl tgxq0">
      	  <b>什么是坤州大德在线推广</b>
          <p>坤州大德在线推广是一种按用户申请资金赚取佣金的推广平台，您可以通过朋友、QQ、微信、微博、博客、论坛或在自己的网站上进行推广，所有通过您的推广链接访问过来的用户，注册后都将成为您的下线用户，而当这些用户在本站配资时，您就可以赚取服务费14%的佣金。</p>
          <b>做推广到底能赚多少钱</b>
          <p>坤州大德在线推广平台到目前为止，已向所有推广员发放了几千万元的佣金，是目前站长最赚钱的方式之一。推广平台有几位做的比较好的推广员，他们经过一年多的努力，现在每月佣金收入就有十来万元。</p>
          <b>佣金怎么结算</b>
          <p>您的用户每发生一笔配资，您就立马获得一笔佣金，当您的佣金到达10元时，您就可以随时兑换并取出，当然也可以在本站进行配资。</p>
      </div>
      <div class="fl tgyjb">
      	   <div class="tgyjb_title">推广佣金榜</div>
           <div class="tgyjb_cont">
           		<ul>
           		    <c:forEach items="${userList}" var="user" varStatus="row">
                	<li><span class="pm" id="teshu">${row.index+1}</span><span>${fn:substring(user.userName,0,fn:length(user.userName)-1)}*</span><span class="fr jq"><fmt:formatNumber value="${empty user.commissionTotal?0:user.commissionTotal}" pattern="#,###,##0.00"/>元</span><div class="clr"></div></li>
                	</c:forEach>
                </ul>
           </div>
      </div>
      <div class="clr"></div>	  
      <div style="margin:0px 0 0 30px;"><img src="../qihuo_images/tgxq0_03.gif" width="759" height="623"></div>
  </div>
    <div class="clr"></div>
</div>
</body>
</html>
<script>

</script>
