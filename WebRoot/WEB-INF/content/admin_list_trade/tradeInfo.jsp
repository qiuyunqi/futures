<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－持仓－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<c:set var="first" value="6"/>
<c:set var="second" value="4"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	<div class="rt_cont_title">
    	                    持仓
    	    </div>
    	    
    	    <div class="form">
	            <form id="searchForm" action="${ctx}/admin_list_trade/tradeInfo.htm" method="post">
	            	<table width="60%" border="0" cellspacing="0" cellpadding="0">
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
						            <div class="lf_font fl"></div>
						            <div class="fl"><a href="javascript:void(0);" onclick="queryTrades();" class="search">查询</a><a href="${ctx}/admin_list_trade/tradeInfo.htm" class="remove" style="margin-left:30px;">清空</a></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						  </tr>
					</tbody></table>
				</form>
            </div> 
    	    
          <div class=" yhlb_title">持仓列表</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody>
				  <tr>
				    <th>&nbsp;</th>
				    <th>用户ID</th>
				    <th>用户名</th>
				    <th>合约编号</th>
				    <th>买卖方向</th>
				    <th>开平方向</th>
				    <th>开仓订单编号</th>
				    <th>开仓时间</th>
				    <th>开仓成交手数</th>
				    <th>开仓成交价格</th>
				    <th>开仓成交金额</th>
					<th>平仓订单编号</th>
				    <th>平仓时间</th>
				    <th>平仓成交手数</th>
				    <th>平仓成交价格</th>
				    <th>平仓成交金额</th>
				    <th>持仓状态</th>
				    <th>平仓类别</th>
				    <th>平仓盈亏</th>
				    <th>操作</th>
				  </tr>
				  <c:forEach items="${tradeList}" var="trade" varStatus="row">
					  <tr style="cursor:pointer;" onmouseover="$(this).attr('bgcolor','#f6f6f6');" onmouseout="$(this).attr('bgcolor','');" >
					    <td align="center" class="num">${row.index+1}</td>
					   	<td>${trade.fuUser.id}</td>
					    <td>${trade.fuUser.userName}</td>
					    <td>${trade.instrumentId}</td>
					    <td>${trade.direction==0?'买':'卖'}</td>
					    <td>${trade.offsetFlag==0?'开仓':'平仓'}</td>
					    <td>${trade.openOrderNum}</td>
					    <%-- <c:if test="${!empty trade.openDateTime}"><td><fmt:formatDate value="${trade.openDateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td></c:if> --%>
					    <td><fmt:formatDate value="${trade.openDateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					    <td style=" text-align:right">${trade.openVolume}手</td>
					    <td style=" text-align:right">${trade.openPrice}元</td>
					    <td style=" text-align:right">${trade.openMoney}元</td>
					    <td>${trade.closeOrderNum}</td>
					    <td><fmt:formatDate value="${trade.closeDateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					    <td style=" text-align:right">${trade.closeVolume}手</td>
					    <td style=" text-align:right">${trade.closePrice}元</td>
					    <td style=" text-align:right">${trade.closeMoney}元</td>
					    <td>${trade.state==0?'持仓中':trade.state==1?'平仓中':trade.state==2?'平仓成功':trade.state==3?'平仓失败':trade.state==4?'强平中':trade.state==5?'强平成功':trade.state==6?'强平失败':''}</td> 
					    <td>${trade.closeType==1?'用户平仓':trade.closeType==2?'系统止盈':trade.closeType==3?'系统止损':trade.closeType==4?'系统强平':trade.closeType==5?'到时终止':''}</td> 
					  	<td style=" text-align:right">${trade.closeProfit}元</td>
					 	<td>
					 	<domi:privilege url="/admin_list_trade/closeTradeAjax.htm">
					 	<c:if test="${trade.state!=2 && trade.state!=5}"><a href="javascript:void(0);" onclick="closeTrade(${trade.id });">平仓</a></c:if>
					 	</domi:privilege>
					 	</td>
					  </tr>
				  </c:forEach>
				</tbody>
				</table>
          </div>
           <c:if test="${empty tradeList}">
				  <div style="text-align:center;">暂无内容！</div>
		  </c:if>
		  <div class="page">
			<domi:pagination ctx="${ctx}" pageVolume="${pageSize}"
				url="${ctx}/admin_list_trade/tradeInfo.htm?totalCount=${totalCount}"
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
function closeTrade(id){
	$.post("${ctx}/admin_list_trade/closeTradeAjax.htm?id="+id,null,function(d){
		if(d==-2){
            sureInfo("确定","未设置产品交易参数","提示");
            return null;
        }
		if(d==-6){
            sureInfo("确定","风控处理失败","提示");
            return null;
        }
        if(d==1){
            jAlert("平仓成功","提示",function(){
			    location.href = location.href;
     	    });
        }
	});
}
function queryTrades(){
	$('#searchForm').submit();
}
</script>
