<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－用户解套者流水－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
<style>
	.remark{
            width:130px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }
    .hidden{border:1px solid #eaeaea;position: absolute;z-index: 99;background: #99CCFF;text-align:left;}


</style>
<script></script>
</head>
<body style=" background:#fff">
<c:set var="first" value="8"/>
<c:set var="second" value="4"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	<div class="rt_cont_title">用户解套者流水<a href="${ctx}/stock_money_info/moneyInfo.htm" class="fr add">返回</a></div>
          <div class=" yhlb_title">用户解套者流水</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody>
				  <tr>
				    <th>&nbsp;</th>
				    <th>用户ID</th>
				    <th>用户手机号</th>
				    <th>用户姓名</th>
				    <th>今日盈利(元)</th>
				    <!-- <th>可索赔付(元)</th> -->
				    <th>周管理费(元)</th>
				    <th>应缴费用(元)</th>
				    <th>已缴管理费(元)</th>
				    <!-- <th>应退赔付(元)</th> -->
				    <th>创建时间</th>
				    <th>交易时间</th>
				    <th>备注</th>
				  </tr>
				  <c:forEach items="${detailList}" var="detail" varStatus="row">
					  <tr style="cursor:pointer;" onmouseover="$(this).attr('bgcolor','#f6f6f6');" onmouseout="$(this).attr('bgcolor','');" >
					    <td align="center" class="num">${row.index+1}</td>
					    <td>${detail.fuUser.id}</td>
					    <td>${detail.fuUser.phone}</td>
					    <td>${detail.fuUser.userName}</td>
					    <td style=" text-align:right">${detail.nowProfit}</td>
					    <%-- <td style=" text-align:right">${detail.compensate}</td> --%>
					    <td style=" text-align:right">${detail.manageFee}</td>
					    <td style=" text-align:right">${detail.mustFee}</td>
					    <td style=" text-align:right">${detail.payFee}</td>
					    <%-- <td style=" text-align:right">${detail.quitCompen}</td> --%>
					    <td><fmt:formatDate value="${detail.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					    <td><fmt:formatDate value="${detail.tradeTime}" pattern="yyyy-MM-dd"/></td>
					    <td style=" text-align:right"><div class="remark">${detail.remark}</div><div class="hidden">${detail.remark}</div></td>
					  </tr>
				  </c:forEach>
				</tbody>
				</table>
          </div>
           <c:if test="${empty detailList}">
				  <div style="text-align:center;">暂无内容！</div>
		  </c:if>
        </div>
    </div>
</div>
</body>
</html>
<script>
$(".hidden").hide();
$(document).ready(function(){
    $(".remark").mouseover(function(){
        $(".hidden").show();
    })

    $(".remark").mouseout(function(){
        $(".hidden").hide();
    })
});


function queryStockMoneyDetail(){
	$('#searchForm').submit();
}
</script>
