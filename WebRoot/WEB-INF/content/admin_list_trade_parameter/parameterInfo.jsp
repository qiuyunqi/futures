<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－产品交易参数设置－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<c:set var="first" value="6"/>
<c:set var="second" value="1"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">
    	                    产品交易参数设置
                <domi:privilege url="/admin_list_trade_parameter/newTradeParameterAjax.htm">
                <a href="javascript:void(0);" onclick="addTradeParameter('');" class="fr add">添加</a>
                </domi:privilege>
    	    </div>
          <div class=" yhlb_title">产品交易参数列表</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody><tr>
				    <th>&nbsp;</th>
				    <th>产品交易品种</th>
				    <th>主账户日交易笔数</th>
				    <th>用户保证金</th>
				    <th>产品管理费</th>
				    <th>产品营销分成比例</th>
				    <th>开始交易时间</th>
				    <th>收盘时间</th>
				    <th>强平时间</th>
				    <th>涨跌幅风控线</th>
				    <th>止损风控线</th>
				    <th>止盈风控线</th>
				    <th>单用户日基础交易笔数额度</th>
				    <th>单用户日交易笔数风控系数</th>
				    <th>操作</th>
				  </tr>
				  <c:forEach items="${paramList}" var="tradeParam" varStatus="row">
					  <tr style="cursor:pointer;" onmouseover="$(this).attr('bgcolor','#f6f6f6');" onmouseout="$(this).attr('bgcolor','');" >
					    <td align="center" class="num">${row.index+1}</td>
					    <td>${tradeParam.tradeVariety}</td>
					    <td>${tradeParam.dayTradeNum}笔</td>
					    <td style=" text-align:right">${tradeParam.safeMoney}元</td>
					    <td style=" text-align:right">${tradeParam.manageMoney}元</td>
					    <td style=" text-align:right">${tradeParam.productPercent*100}%</td>
					    <td>${tradeParam.tradeTime}</td>
					    <td>${tradeParam.closeTime}</td>
					    <td>${tradeParam.breakCloseTime}</td>
					    <td style=" text-align:right">${tradeParam.riskPercent*100}%</td>
					    <td style=" text-align:right">${tradeParam.stopLossPercent*100}%</td>
					    <td style=" text-align:right">${tradeParam.stopProfitPercent*100}%</td>
					    <td style=" text-align:right">${tradeParam.dayBaseNum}笔</td>
					    <td style=" text-align:right">${tradeParam.dayBaseFactor}</td>
					 	<td>
					 	<domi:privilege url="/admin_list_trade_parameter/newTradeParameterAjax.htm">
					 	<a href="javascript:void(0);" onclick="addTradeParameter(${tradeParam.id });" >编辑</a>
					 	</domi:privilege>
					 	&nbsp;|&nbsp;
					 	<domi:privilege url="/admin_list_trade_parameter/delParam.htm">
					 	<a href="javascript:void(0);"  onclick="delParam(${tradeParam.id });">删除</a>
					 	</domi:privilege>
					 	</td>
					  </tr>
				  </c:forEach>
				</tbody>
				</table>
          </div>
           <c:if test="${empty paramList}">
				  <div style="text-align:center;">暂无内容！</div>
		  </c:if>
          <div class="page">
			<domi:pagination ctx="${ctx}" pageVolume="${pageSize}"
				url="${ctx}/admin_list_trade_parameter/parameterInfo.htm?totalCount=${totalCount}"
				totalNum="${totalCount}" curPageNum="${pageNo}"
				formId="pageForm">
		        <domi:paramTag name="account" value="${account}"/>
		        <domi:paramTag name="name" value="${name}"/>
			</domi:pagination>
		</div>
        </div>
    </div>
</div>
</body>
</html>
<script>
function addTradeParameter(id){
	$.fancybox.open({
          href : '${ctx}/admin_list_trade_parameter/newTradeParameterAjax.htm?id='+id,
          type : 'ajax',
          padding : 10
	});
}

function delParam(id){
    if (!confirm("确定删除？")) {
        window.event.returnValue = false;
        return null;
    }
    
	$.post("${ctx}/admin_list_trade_parameter/delParam.htm?id="+id,null,function(d){
		  if(d==1){
              sureInfo("确定","删除成功","提示");
              location.href = location.href;
          }
	});
}
</script>
