<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－我的用户</title>
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
                <li><a href="${ctx}/user_promote/visitRecord.htm">访问记录</a></li>
                <li class="yxz"><a href="${ctx}/user_promote/myUsers.htm">我的用户</a></li>
            </ul>
            <div class="clr"></div>
        </div>
        
        <div class=" ny_bt">
        	<span class="pbsx"></span><span class="ny_bt_cont">下线用户共${fuUser.extendPersonNum}个，配资用户共${fuUser.borrowPersonNum }个 </span>
        </div>
        <div class="grglzh_tab" style="margin:13px 20px 0px">
          <table border="0" cellspacing="0" cellpadding="0" width="100%">
          <tbody><tr>
            <td height="35" style="border-bottom:#e3e3e3 solid 1px;" align="center" bgcolor="#f8f8f8" width="99">用户名 </td>
            <td style="border-bottom:#e3e3e3 solid 1px;" align="right" bgcolor="#f8f8f8" width="97">当前配资(元) </td>
            <td style="border-bottom:#e3e3e3 solid 1px;" align="center" bgcolor="#f8f8f8" width="110">访问IP  </td>
            <td style="border-bottom:#e3e3e3 solid 1px;" align="center" bgcolor="#f8f8f8" width="82">注册时间</td>
          </tr>
          <c:forEach items="${promoteList}" var="promote" varStatus="row">
          <tr>
            <td height="35" align="center" <c:if test="${row.index==fn:length(promoteList)-1}">style="border-bottom:none;"</c:if>>${promote.fuUserByPromotedId.accountName}</td>
            <td align="right" height="35" style=" line-height:35px;<c:if test="${row.index==fn:length(promoteList)-1}">border-bottom:none;</c:if>"><fmt:formatNumber value="${empty promote.fuUserByPromotedId.matchMoney?0:promote.fuUserByPromotedId.matchMoney}" pattern="#,###,##0.00"/></td>
            <td align="center" <c:if test="${row.index==fn:length(promoteList)-1}">style="border-bottom:none;"</c:if>>${promote.fuUserByPromotedId.registerIp}</td>
            <td align="center" <c:if test="${row.index==fn:length(promoteList)-1}">style="border-bottom:none;"</c:if>><fmt:formatDate value="${promote.fuUserByPromotedId.registerTime}" pattern="yyyy-MM-dd HH:mm"/></td>
          </tr>
          </c:forEach>
        </tbody>
        </table>
          <c:if test="${empty promoteList}">
        <div style="text-align:center;padding:20px;">暂时没有任何内容！</div>
        </c:if>
        </div> 
         <div class="page">
							<domi:pagination ctx="${ctx}" pageVolume="${pageSize}"
								url="${ctx}/user_promote/myUsers.htm?totalCount=${totalCount}"
								totalNum="${totalCount}" curPageNum="${pageNo}"
								formId="pageForm">
							</domi:pagination>
		</div>
        <div class="clr"></div>
    </div>
    <div class="clr"></div>
</div>
</body>
</html>
<script>

</script>
