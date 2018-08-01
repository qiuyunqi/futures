<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－提款记录</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/css.jsp" %>
</head>
<body>
<%@include file="../common/qqConsult.jsp" %>
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>

    <div class="mgrzx">
    <%@include file="../common/left.jsp" %>
    <div class="mgrzxr">
    <div class="h20"></div>
    <div class="mgrrul">
      <ul>
        <li id="one1" onclick="setTab(this);"><a href="${ctx}/user_draw_money/drawMoney.htm">我要提款</a></li>
        <li id="one2" onclick="setTab(this);" class="mgractive"><a href="${ctx}/user_draw_money/drawMoneyList.htm">提款记录</a></li>
      </ul>
    </div>
    <div class="mgrrmain">
      <div id="one_1" class="display-b">
        <div class="rzczjl">
          <table cellpadding="0" cellspacing="0" border="0" width="100%" style="font-size:14px;color:#808080;">
	          <tr>
	             <td width="20%" height="35" align="center" bgcolor="#f8f8f8" style="border-bottom:#e3e3e3 solid 1px;">时间</td>
	             <td width="20%" height="35" align="center" bgcolor="#f8f8f8" style="border-bottom:#e3e3e3 solid 1px;">开户银行</td>
	             <td width="20%" height="35" align="center" bgcolor="#f8f8f8" style="border-bottom:#e3e3e3 solid 1px;">银行卡号</td>   
	             <td width="20%" height="35" align="center" bgcolor="#f8f8f8" style="border-bottom:#e3e3e3 solid 1px;text-align:right;">金额（元）</td>  
	             <td width="20%" height="35" align="center" bgcolor="#f8f8f8" style="border-bottom:#e3e3e3 solid 1px;">状态</td>  
	          </tr>
	           <c:forEach items="${drawList}" var="draw" varStatus="row">
	          <tr>
	            <td height="35" align="center" <c:if test="${row.index==fn:length(drawList)-1}">style="border-bottom:none;"</c:if>><fmt:formatDate value="${draw.drawTime}" pattern="yyyy-MM-dd HH:mm"/></td>
	            <td height="35" align="center" <c:if test="${row.index==fn:length(drawList)-1}">style="border-bottom:none;"</c:if>>${draw.fuBankCard.bankName}</td>
	            <td height="35" align="center" <c:if test="${row.index==fn:length(drawList)-1}">style="border-bottom:none;"</c:if>>${fn:substring(draw.fuBankCard.cardNumber,0,4)}<c:forEach begin='4' end='${fn:length(draw.fuBankCard.cardNumber)-5}'>*</c:forEach>${fn:substring(draw.fuBankCard.cardNumber,fn:length(draw.fuBankCard.cardNumber)-4,fn:length(draw.fuBankCard.cardNumber))}</td>
	            <td height="35" align="right" style="text-align: right;"<c:if test="${row.index==fn:length(drawList)-1}">style="border-bottom:none;"</c:if>><fmt:formatNumber value="${draw.drawMoney}" pattern="#,###,##0.00"/>&nbsp</td>
	            <td height="35" align="center" <c:if test="${row.index==fn:length(drawList)-1}">style="border-bottom:none;"</c:if>>${draw.status==0?'未审核':draw.status==1?'审核中':draw.status==2?'成功':'拒绝'}</td>
	          </tr>
	          </c:forEach>
          </table>
          <c:if test="${empty drawList}">
            <div style="text-align:center;padding:20px;">暂时没有任何内容！</div>
          </c:if>
        </div>
        <div class="page">
			<domi:pagination ctx="${ctx}" pageVolume="${pageSize}"
				url="${ctx}/user_draw_money/drawMoneyList.htm?totalCount=${totalCount}"
				totalNum="${totalCount}" curPageNum="${pageNo}"
				formId="pageForm">
			</domi:pagination>
		</div>
      </div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<div class="downban"></div>
<%@include file="../common/footer.jsp" %>
</body>
</html>