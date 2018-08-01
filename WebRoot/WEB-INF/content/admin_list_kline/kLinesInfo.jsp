<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－K线记录维护－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<c:set var="first" value="6"/>
<c:set var="second" value="3"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">
    	        K线记录维护<domi:privilege url="/admin_list_kline/newKlineAjax.htm"><a href="javascript:void(0);" onclick="addKline('');" class="fr add">添加</a></domi:privilege>
    	    </div>
    	    
    	  <div class="form">
	            <form id="searchForm" action="${ctx}/admin_list_kline/kLinesInfo.htm" method="post">
	            	<table width="60%" border="0" cellspacing="0" cellpadding="0">
					  <tbody>
						  <tr>
						    <td>
						        <div class="form_cont">
						            <div class="lf_font fl">日期：</div>
						            <div class="fl input"><input id="tradingDay" name="tradingDay" value="${tradingDay}" type="text" placeholder=""></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						    <td>
						        <div class="form_cont">
						            <div class="lf_font fl">合约编号：</div>
						            <div class="fl input"><input id="instrumentId" name="instrumentId" value="${instrumentId}" type="text" placeholder=""></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						    <td>
						    	<div class="form_cont">
						            <div class="lf_font fl"></div>
						            <div class="fl"><a href="javascript:void(0);" onclick="queryKlines();" class="search">查询</a><a href="${ctx}/admin_list_kline/kLinesInfo.htm" class="remove" style="margin-left:30px;">清空</a></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						  </tr>
					</tbody></table>
				</form>
            </div>  
    	    
          <div class=" yhlb_title">K线记录列表</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody><tr>
				    <th>&nbsp;</th>
				    <th>日期</th>
				    <th>合约编号</th>
				    <th>最高价</th>
				    <th>最低价</th>
				    <th>开盘价</th>
				    <th>收盘价</th>
				    <th>操作</th>
				  </tr>
				  <c:forEach items="${klineList}" var="kline" varStatus="row">
					  <tr style="cursor:pointer;" onmouseover="$(this).attr('bgcolor','#f6f6f6');" onmouseout="$(this).attr('bgcolor','');" >
					    <td align="center" class="num">${row.index+1}</td>
					    <td>${kline.tradingDay}</td>
					    <td>${kline.instrumentId}</td>
					    <td><fmt:formatNumber value="${empty kline.highestPrice?0:kline.highestPrice}" pattern="#,###,##0.00"/>元</td>
					    <td><fmt:formatNumber value="${empty kline.lowestPrice?0:kline.lowestPrice}" pattern="#,###,##0.00"/>元</td>
					    <td><fmt:formatNumber value="${empty kline.openPrice?0:kline.openPrice}" pattern="#,###,##0.00"/>元</td>
					    <td><fmt:formatNumber value="${empty kline.closePrice?0:kline.closePrice}" pattern="#,###,##0.00"/>元</td>
					 	<td><domi:privilege url="/admin_list_kline/newKlineAjax.htm"><a href="javascript:void(0);" onclick="addKline(${kline.id });" >编辑</a></domi:privilege>&nbsp;|&nbsp;<domi:privilege url="/admin_list_kline/delKline.htm"><a href="javascript:void(0);"  onclick="delKline(${kline.id });">删除</a></domi:privilege></td>
					  </tr>
				  </c:forEach>
				</tbody>
				</table>
          </div>
           <c:if test="${empty klineList}">
				  <div style="text-align:center;">暂无内容！</div>
		  </c:if>
          <div class="page">
			<domi:pagination ctx="${ctx}" pageVolume="${pageSize}"
				url="${ctx}/admin_list_kline/kLinesInfo.htm?totalCount=${totalCount}"
				totalNum="${totalCount}" curPageNum="${pageNo}"
				formId="pageForm">
		        <domi:paramTag name="tradingDay" value="${tradingDay}"/>
		        <domi:paramTag name="instrumentId" value="${instrumentId}"/>
			</domi:pagination>
		</div>
        </div>
    </div>
</div>
</body>
</html>
<script>
function queryKlines(){
	$('#searchForm').submit();
}

function addKline(id){
	$.fancybox.open({
          href : '${ctx}/admin_list_kline/newKlineAjax.htm?id='+id,
          type : 'ajax',
          padding : 10
	});
}

function delKline(id){
    if (!confirm("确定删除？")) {
        window.event.returnValue = false;
        return null;
    }
    
	$.post("${ctx}/admin_list_kline/delKline.htm?id="+id,null,function(d){
        if(d==1){
            sureInfo("确定","删除成功","提示");
            location.href = location.href;
        }
	});
} 
</script>
