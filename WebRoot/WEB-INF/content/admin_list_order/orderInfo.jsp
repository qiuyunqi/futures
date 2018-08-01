<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－订单－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<c:set var="first" value="6"/>
<c:set var="second" value="2"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">
    	                    订单<domi:privilege url="/admin_list_order/newOrderAjax.htm"><a href="javascript:void(0);" onclick="addOrder('');" class="fr add">下单</a></domi:privilege>
    	    </div>
    	    <div class="form">
	            <form id="searchForm" action="${ctx}/admin_list_order/orderInfo.htm" method="post">
	            	<table width="80%" border="0" cellspacing="0" cellpadding="0">
					  <tbody>
						  <tr>
						    <td>
						        <div class="form_cont">
						            <div class="lf_font fl">用户ID：</div>
						            <div class="fl input"><input id="" name="accountUserId" value="${accountUserId}" type="text" placeholder=""></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						    <td>
						        <div class="form_cont">
						            <div class="lf_font fl">用户名：</div>
						            <div class="fl input"><input id="" name="accountUserName" value="${accountUserName}" type="text" placeholder=""></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						    <td>
						        <div class="form_cont">
						            <div class="lf_font fl">订单号：</div>
						            <div class="fl input"><input id="" name="orderNum" value="${orderNum}" type="text" placeholder=""></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						    <td>
						    	<div class="form_cont">
						            <div class="lf_font fl"></div>
						            <div class="fl"><a href="javascript:void(0);" onclick="queryOrders();" class="search">查询</a><a href="${ctx}/admin_list_order/orderInfo.htm" class="remove" style="margin-left:30px;">清空</a></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						  </tr>
					</tbody></table>
				</form>
            </div>  
    	    
          <div class=" yhlb_title">订单列表</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody>
				  <tr>
				    <th>&nbsp;</th>
				    <th>用户ID</th>
				    <th>用户名</th>
				    <th>订单编号</th>
				    <th>合约编号</th>
				    <th>买卖方向</th>
				    <th>开平方向</th>
				    <th>成交手数</th>
				    <th>成交价格</th>
				    <th>成交金额</th>
				    <th>下单时间</th>
				    <th>成交时间</th>
				    <th>订单状态</th>
				  </tr>
				  <c:forEach items="${orderList}" var="order" varStatus="row">
					  <tr style="cursor:pointer;" onmouseover="$(this).attr('bgcolor','#f6f6f6');" onmouseout="$(this).attr('bgcolor','');" >
					    <td align="center" class="num">${row.index+1}</td>
					    <td>${order.fuUser.id}</td>
					    <td>${order.fuUser.userName}</td>
					    <td>${order.orderNum}</td>
					    <td>${order.instrumentId}</td>
					    <td>${order.direction==0?'买':'卖'}</td>
					    <td>${order.offsetFlag==0?'开仓':'平仓'}</td>
					    <td style=" text-align:right">${order.volume}手</td>
					    <td style=" text-align:right">${order.price}元</td>
					    <td style=" text-align:right">${order.money}元</td>
					    <%-- <c:if test="${!empty order.tradeDateTime}"><td><fmt:formatDate value="${order.tradeDateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td></c:if> --%>
					    <td><fmt:formatDate value="${order.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					    <td><fmt:formatDate value="${order.tradeDateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					    <td>${order.state==0?'待成交':order.state==1?'报单成功':order.state==2?'已成交':order.state==3?'异常':''}</td> 
					  </tr>
				  </c:forEach>
				</tbody>
				</table>
          </div>
           <c:if test="${empty orderList}">
				  <div style="text-align:center;">暂无内容！</div>
		  </c:if>		  
          <div class="page">
			<domi:pagination ctx="${ctx}" pageVolume="${pageSize}"
				url="${ctx}/admin_list_order/orderInfo.htm?totalCount=${totalCount}"
				totalNum="${totalCount}" curPageNum="${pageNo}"
				formId="pageForm">
		        <domi:paramTag name="accountUserId" value="${accountUserId}"/>
		        <domi:paramTag name="accountUserName" value="${accountUserName}"/>
			</domi:pagination>
		</div>
        </div>
    </div>
</div>
</body>
</html>
<script>
function addOrder(id){
	$.fancybox.open({
          href : '${ctx}/admin_list_order/newOrderAjax.htm',
          type : 'ajax',
          padding : 10
	});
}

function queryOrders(){
	$('#searchForm').submit();
}

</script>
