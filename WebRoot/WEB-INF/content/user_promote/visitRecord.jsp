<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－访问记录</title>
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
            	<li><a href="${ctx}/user_promote/promoteDetail.htm">推广详情</a></li>
                <li><a href="${ctx}/user_promote/promoteLink.htm">推广链接</a></li>
                <li class="yxz"><a href="${ctx}/user_promote/visitRecord.htm">访问记录</a></li>
                <li><a href="${ctx}/user_promote/myUsers.htm">我的用户</a></li>
            </ul>
            <div class="clr"></div>
        </div>
        <div class=" ny_bt">
        	<span class="pbsx"></span><span class="ny_bt_cont">访问IP共${fuUser.visitIp}个，访问次数共${fuUser.visitNum}次</span>

            <div class="fr" style="color:#5f5f5f">下线用户有效期3年</div>
            <div class="clr"></div>
        </div>
        <div class="grglzh_tab" style="margin:13px 20px 30px">
          <table border="0" cellspacing="0" cellpadding="0" width="100%">
          <tbody><tr>
            <td width="110" height="35" align="center" bgcolor="#f8f8f8" style="border-bottom:#e3e3e3 solid 1px;">访问时间</td>
            <td width="82" height="35" align="center" bgcolor="#f8f8f8" style="border-bottom:#e3e3e3 solid 1px;">访问IP</td>
             <td width="82" height="35" align="center" bgcolor="#f8f8f8" style="border-bottom:#e3e3e3 solid 1px;">访问次数</td>   
          </tr>
           <c:forEach items="${visitList}" var="visit" varStatus="row">
          <tr>
            <td height="35" align="center" <c:if test="${row.index==fn:length(visitList)-1}">style="border-bottom:none;"</c:if>><fmt:formatDate value="${visit.visitTime}" pattern="yyyy-MM-dd HH:mm"/></td>
            <td height="35" align="center" <c:if test="${row.index==fn:length(visitList)-1}">style="border-bottom:none;"</c:if>>${visit.visitIp}</td>
            <td height="35" align="center" <c:if test="${row.index==fn:length(visitList)-1}">style="border-bottom:none;"</c:if>>${visit.visitNum}</td>
          </tr>
          </c:forEach>
        </tbody>
        </table>
        <c:if test="${empty visitList}">
        <div style="text-align:center;padding:20px;">暂时没有任何内容！</div>
        </c:if>
        </div>
        <div class="page">
							<domi:pagination ctx="${ctx}" pageVolume="${pageSize}"
								url="${ctx}/user_promote/visitRecord.htm?totalCount=${totalCount}"
								totalNum="${totalCount}" curPageNum="${pageNo}"
								formId="pageForm">
							</domi:pagination>
		</div>
    </div>
    <div class="clr"></div>
</div>
</body>
</html>
<script>

</script>
