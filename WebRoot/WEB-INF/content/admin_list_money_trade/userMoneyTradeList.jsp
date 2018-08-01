<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－资金交易明细－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<c:set var="first" value="2"/>
<c:set var="second" value="4"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	<div class="rt_cont_title">用户资金进出记录<a href="${ctx}/finance_account_balance/accountBalance.htm" class="fr add">返回</a></div>
          <div class=" yhlb_title">资金进出记录</div>
            <div class="yhlb">
            <table width="100%">
		  	<tbody><tr>
		    <th>&nbsp;</th>
		    <th>明细ID</th>
		    <th>用户名</th>
		    <th>真实姓名</th>
		    <th>类型</th>
		    <th>详情</th>
		    <th>金额 </th>
		    <th>可用余额 </th>
		    <th>时间</th>
  			</tr>
		    <c:forEach items="${detailList}" var="detail" varStatus="row">
			<tr onmouseover="$(this).attr('bgcolor','#f6f6f6');" onmouseout="$(this).attr('bgcolor','');">
		    <td class="num">${row.index+1}</td>
		    <td>${detail.id}</td>
		    <td>${detail.fuUser.accountName}</td>
		    <td>${detail.fuUser.userName}</td>
		    <td>${detail.fuDictionary.name}</td>
		    <td>${detail.comment}</td>
		    <td><fmt:formatNumber value="${detail.money}" pattern="#,###,##0.00"/></td>
		    <td><fmt:formatNumber value="${detail.accountBalanceAfter}" pattern="#,###,##0.00"/></td>
		    <td><fmt:formatDate value="${detail.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		  </tr>
		  </c:forEach>
		    <c:if test="${empty detailList}">
			<tr>
				<td colspan="12">
		        	<div class=" empty0"><img src="../images_qhht/empty_03.jpg" width="93" height="63"><p class="empty0_font">您暂时还没有相关信息</p></div>
		        </td>
		  </tr>
		</c:if>
		</tbody>
		</table>
          </div>
          <div class="page">
			<domi:pagination ctx="${ctx}" pageVolume="${pageSize}"
				url="${ctx}/admin_list_money_trade/userMoneyTradeList.htm?queryUserId=${queryUserId}&time1=${time1}&time2=${time2}&totalCount=${totalCount}"
				totalNum="${totalCount}" curPageNum="${pageNo}"
				formId="pageForm">
			</domi:pagination>
		</div>
        </div>
    </div>
</div>
</body>
</html>
<script>
</script>
