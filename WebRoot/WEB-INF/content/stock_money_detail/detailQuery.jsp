<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－解套者流水查询－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
<style>
	.remark{
            width:130px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }
    .hidden{position: absolute;z-index: 99;background: #99CCFF;text-align:left;padding:2px;}
</style>
</head>
<body style=" background:#fff">
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	<div class="rt_cont_title">
    	                    解套者流水查询
    	                <domi:privilege url="/stock_money_detail/exportYqbWeekStatExcel.htm">
	                    <a href="javascript:void(0);" onclick="exportYqbWeekStatExcel();" class="fr add">流水周统计</a>
	                    </domi:privilege>
	                    <domi:privilege url="/stock_money_detail/exportYqbDayStatExcel.htm">
	                    <a href="javascript:void(0);" onclick="exportYqbDayStatExcel();" class="fr add" style="margin-right:10px;">流水日统计</a>
	                    <div class="form_cont fr rt_cont_date">
	                    	<div class="lf_font fl">流水统计时间：</div>
	                    	<div class="fl input"><input id="tradeTime" name="tradeTime" type="text" value="<fmt:formatDate value='${tradeTime}' pattern='yyyy-MM-dd'/>" placeholder="" style="width:80px;" onclick="WdatePicker({dateFmt : 'yyyy-MM-dd'});"><i class="riqi" ></i></div>
	                    </div>
	                    </domi:privilege>
    	    </div>
    	  <div class="form">
	            <form id="searchForm" action="${ctx}/stock_money_detail/detailQuery.htm" method="post">
	            	<table width="70%" border="0" cellspacing="0" cellpadding="0">
					  <tbody>
						  <tr>
						    <td>
						        <div class="form_cont">
						            <div class="lf_font fl">用户ID：</div>
						            <div class="fl input"><input id="" name="userId" value="${userId}" type="text" placeholder=""></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						    <td>
						        <div class="form_cont">
						            <div class="lf_font fl">手机号：</div>
						            <div class="fl input"><input id="" name="phone" value="${phone}" type="text" placeholder=""></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						    <td>
						        <div class="form_cont">
						            <div class="lf_font fl">交易时间：</div>
						            <div class="fl input"><input name="time1" type="text" value="<fmt:formatDate value='${time1}' pattern='yyyy-MM-dd'/>" placeholder="" style="width:80px;" onclick="WdatePicker({dateFmt : 'yyyy-MM-dd'});"><i class="riqi" ></i></div>
						            <div class="fl duanxian">—</div>
						            <div class="fl input"><input name="time2" type="text" value="<fmt:formatDate value='${time2}' pattern='yyyy-MM-dd'/>" placeholder="" style="width:80px;" onclick="WdatePicker({dateFmt : 'yyyy-MM-dd'});"><i class="riqi" ></i></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						    <td>
						    	<div class="form_cont">
						            <div class="lf_font fl"></div>
						            <div class="fl"><a href="javascript:void(0);" onclick="queryStockMoneyDetail();" class="search">查询</a><a href="${ctx}/stock_money_detail/detailQuery.htm" class="remove" style="margin-left:30px;">清空</a></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						    </tr>
					</tbody></table>
				</form>
            </div>  
    	    
          <div class=" yhlb_title">解套者流水</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody>
				  <tr>
				    <th>&nbsp;</th>
				    <th>用户ID</th>
				    <th>用户手机号</th>
				    <th>股票账号</th>
				    <th>用户姓名</th>
				    <th>初始净值(元)</th>
				    <th>总市值(元)</th>
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
					    <td>${detail.fuStockAccount.capitalAccount}</td>
					    <td>${detail.fuUser.userName}</td>
					    <td style=" text-align:right">${detail.beginValue}</td>
					    <td style=" text-align:right">${detail.totalValue}</td>
					    <td style=" text-align:right">${detail.nowProfit}</td>
					    <%-- <td style=" text-align:right">${detail.compensate}</td> --%>
					    <td style=" text-align:right">${detail.manageFee}</td>
					    <td style=" text-align:right">${detail.mustFee}</td>
					    <td style=" text-align:right">${detail.payFee}</td>
					    <%-- <td style=" text-align:right">${detail.quitCompen}</td> --%>
					    <td><fmt:formatDate value="${detail.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					    <td><fmt:formatDate value="${detail.tradeTime}" pattern="yyyy-MM-dd"/></td>
					    <td style=" text-align:right">
						    <ul><li class="remark">${detail.remark}</li><li class="hidden">${detail.remark}</li></ul>
					    </td>
					  </tr>
				  </c:forEach>
				</tbody>
				</table>
          </div>
           <c:if test="${empty detailList}">
				  <div style="text-align:center;">暂无内容！</div>
		  </c:if>
		  <div class="page">
			<domi:pagination ctx="${ctx}" pageVolume="${pageSize}"
				url="${ctx}/stock_money_detail/detailQuery.htm?totalCount=${totalCount}"
				totalNum="${totalCount}" curPageNum="${pageNo}"
				formId="pageForm">
		        <domi:paramTag name="userId" value="${userId}"/>
		        <domi:paramTag name="phone" value="${phone}"/>
		        <domi:paramTag name="time1" value="${time1}"/>
		        <domi:paramTag name="time2" value="${time2}"/>
			</domi:pagination>
		</div>
        </div>
    </div>
</div>
</body>
</html>
<script>
$(".hidden").hide();
$(document).ready(function(){
    $(".remark").mouseover(function(){
    		$(this).next().show();
    })
    $(".remark").mouseout(function(){
        $(".hidden").hide();
    })
});

//导出余劵宝流水日统计
function exportYqbDayStatExcel(){
	var tradeTime =$("#tradeTime").val();
	if(!tradeTime){
		jAlert("请选择流水统计日期！","提示",function(){
			$("#tradeTime").focus();
        });
		return false;
	}
	window.location.href=encodeURI('${ctx}/stock_money_detail/exportYqbDayStatExcel.htm?tradeTime='+tradeTime);
}

//导出余劵宝流水周统计
function exportYqbWeekStatExcel(){
	window.location.href=encodeURI('${ctx}/stock_money_detail/exportYqbWeekStatExcel.htm');
}

function queryStockMoneyDetail(){
	$('#searchForm').submit();
}
</script>
