<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－资金明细</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<%@include file="../common/css.jsp" %>
<script language="javascript" src="../js_hhr/jquery-1.6.min.js"></script>
<script language="javascript" src="../js_hhr/header.js"></script>
<style>
.select0 select{margin-left:0px;}
a.fa:hover{ color:#2db1e1;text-decoration:underline;}
</style>
</head>
<body>
<%@include file="../common/qqConsult.jsp" %>
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>
<%--  --%>
<div class="hwdzh">
 <%@include file="../common/left.jsp" %> 
  <div class="mgrzxr">
    <div class="h20"></div>
    <div class="mgrrul">
      <ul>
        <li id="one1" onclick="setTab(this);" <c:if test="${empty pid}">class="mgractive"</c:if>><a href="${ctx}/user_money_detail/moneyDetailList.htm">资金明细</a></li>
        <li id="one2" onclick="setTab(this);" <c:if test="${pid==1}">class="mgractive"</c:if>><a href="${ctx}/user_money_detail/moneyDetailList.htm?pid=1">充值提款</a></li>
      </ul>
    </div>
    <div class="mgrrmain mgrrmainb">
      <div id="one_2" class="display-b">
        <div class="rzcztk">
	    <div class="hwdzhrtop">
	      <table cellpadding="0" cellspacing="0" border="0" width="100%">
	        <tr>
	          <td>账户总资金（元）<br /><span><fmt:formatNumber value="${empty fuUser.accountTotalMoney?0:fuUser.accountTotalMoney}" pattern="#,###,##0.00"/></span></td>
	          <th></th>
	          <td>风险保证金（元）<br /><span><fmt:formatNumber value="${empty fuUser.safeMoney?0:fuUser.safeMoney}" pattern="#,###,##0.00"/></span></td>
	          <th></th>
	          <td>账户余额（元）<br /><span><fmt:formatNumber value="${empty fuUser.accountBalance?0:fuUser.accountBalance}" pattern="#,###,##0.00"/></span></td>
	        </tr>
	      </table>
	    </div>
    <div class="hwdzhrtab">
      <div class="hwdtabt">
      <form id="searchForm" method="post" action="${ctx}/user_money_detail/moneyDetailList.htm?pid=${pid}">
        <div class="hwdtabt-span"><strong>资金明细</strong>本页收入<span>${incomeCount}</span>笔，共<span><fmt:formatNumber value="${empty incomeMoney?0:incomeMoney}" pattern="#,###,##0.00"/></span>元；支出<span>${spendCount}</span>笔，共<span><fmt:formatNumber value="${empty spendMoney?0:spendMoney}" pattern="#,###,##0.00"/></span>元</div>
        <div class="hwdtabts">
            <c:if test="${empty pid}">
            <select name="dictionaryId" onchange="location.href='${ctx}/user_money_detail/moneyDetailList.htm?flag=${flag}&dictionaryId='+this.value">
            	 <option value="">全部明细</option>
	   			 <c:forEach items="${dictionaries}" var="dictionar">
	             <option value="${dictionar.id}" <c:if test="${dictionaryId==dictionar.id}">selected</c:if>>${dictionar.name}</option>
            	 </c:forEach>
             </select>
            </c:if>
      		<c:if test="${!empty pid}">
            <select name="dictionaryId" onchange="location.href='${ctx}/user_money_detail/moneyDetailList.htm?flag=${flag}&pid=${pid}&dictionaryId='+this.value">
            	<option value="">全部明细</option>
            	<c:forEach items="${dictionaries}" var="dictionar">
              	<option value="${dictionar.id}" <c:if test="${dictionaryId==dictionar.id}">selected</c:if>>${dictionar.name}</option>
            	</c:forEach>
            </select>
       		</c:if>
        </div>
        <div class="hwdtabts hwdtabtt">
            <select name="flag">
            <option value="">时间</option>
            <option value="1" <c:if test="${flag==1}">selected</c:if>>近一个星期</option>
            <option value="2" <c:if test="${flag==2}">selected</c:if>>近一个月</option>
            <option value="3" <c:if test="${flag==3}">selected</c:if>>近一年</option>
            </select>
            <a href="javascript:void(0);" onclick="doSearch();"><img src="../images_hhr/wdzh03.jpg"/></a>
         </div>
        </form>
      </div>
      <div class="hwdtabbd">
        <table cellpadding="0" cellspacing="0" border="0" width="100%">
          <tr>
            <th>时间</th>
            <th>类型</th>
            <th style="text-align:right;">收入（元）</th>
            <th style="text-align:right;">支出（元）</th>
            <th style="background:none;text-align:right;">余额（元）</th>
          </tr>
          <c:forEach items="${detailList}" var="detail" varStatus="row">
          <tr>
            <td height="35" align="center" <c:if test="${row.index==fn:length(detailList)-1}">style="border-bottom:none;"</c:if>><fmt:formatDate value="${detail.time}" pattern="yyyy-MM-dd HH:mm"/></td>
            <td align="center" height="35" style="line-height:35px;<c:if test="${row.index==fn:length(detailList)-1}">border-bottom:none;</c:if>">${detail.fuDictionary.name}</td>
            <td align="right"  style="text-align:right;"<c:if test="${row.index==fn:length(detailList)-1}">style="border-bottom:none;"</c:if>><fmt:formatNumber value="${detail.isIncome?detail.money:0}" pattern="#,###,##0.00"/>&nbsp&nbsp</td>
            <td align="right"  style="text-align:right;"<c:if test="${row.index==fn:length(detailList)-1}">style="border-bottom:none;"</c:if>><fmt:formatNumber value="${detail.isIncome?0:detail.money}" pattern="#,###,##0.00"/>&nbsp&nbsp</td>
            <td align="right"  style="text-align:right;"<c:if test="${row.index==fn:length(detailList)-1}">style="border-bottom:none;"</c:if>><fmt:formatNumber value="${empty detail.accountBalanceAfter?0:detail.accountBalanceAfter}" pattern="#,###,##0.00"/>&nbsp&nbsp</td>
          </tr>
          </c:forEach>
        </table>
        <c:if test="${empty detailList}">
        <div style="text-align:center;padding:20px;">暂时没有任何内容！</div>
        </c:if>
      </div>
      <div class="page">
		<domi:pagination ctx="${ctx}" pageVolume="${pageSize}" url="${ctx}/user_money_detail/moneyDetailList.htm?totalCount=${totalCount}" totalNum="${totalCount}" curPageNum="${pageNo}" formId="pageForm">
			<domi:paramTag name="pid" value="${pid}"/>
			<domi:paramTag name="dictionaryId" value="${dictionaryId}"/>
			<domi:paramTag name="flag" value="${flag}"/>
		</domi:pagination>
	</div>
    </div>
  </div>
  <div class="clear"></div>
   </div>
  </div>
   </div>

</div>
<div class="downban"></div>
<%@include file="../common/footer.jsp" %>
</body>
</html>
<script>
function doSearch(){
	$("#searchForm").submit();
}
</script>
