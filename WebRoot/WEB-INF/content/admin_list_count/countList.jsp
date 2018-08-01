<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－财务报告－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<c:set var="first" value="3"/>
<c:set var="second" value="8"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">财务报告<domi:privilege url="/admin_list_count/exportExcelAjax.htm"><a href="javascript:void(0);" onclick="exportInfo();" class="fr add">导出</a></domi:privilege></div>
    	    <div class="form">
                <form id="searchForm" action="/admin_list_count/countList.htm" method="post">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tbody>
				  <tr>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">开始日期：</div>
				            <div class="fl input"><input name="beginTime1" id="beginTime1" type="text" value="<fmt:formatDate value="${beginTime1}" pattern="yyyy-MM-dd"/>" placeholder="" style="width:100px;" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'beginTime2\')}', dateFmt:'yyyy-MM-dd'});"><i class="riqi"></i></div>
				            <div class="fl duanxian">—</div>
				            <div class="fl input"><input name="beginTime2" id="beginTime2" type="text" value="<fmt:formatDate value="${beginTime2}" pattern="yyyy-MM-dd"/>" placeholder="" style="width:100px;" onclick="WdatePicker({minDate:'#F{$dp.$D(\'beginTime1\')}', dateFmt:'yyyy-MM-dd'});"><i class="riqi"></i></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				     <td>
				        <div class="form_cont">
				            <div class="lf_font fl">结束日期：</div>
				            <div class="fl input"><input name="endTime1" id="endTime1" type="text" value="<fmt:formatDate value="${endTime1}" pattern="yyyy-MM-dd"/>" placeholder="" style="width:100px;" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endTime2\')}', dateFmt:'yyyy-MM-dd'});"><i class="riqi"></i></div>
				            <div class="fl duanxian">—</div>
				            <div class="fl input"><input name="endTime2" id="endTime2" type="text" value="<fmt:formatDate value="${endTime2}" pattern="yyyy-MM-dd"/>" placeholder="" style="width:100px;" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endTime1\')}', dateFmt:'yyyy-MM-dd'});"><i class="riqi"></i></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">真实姓名：</div>
				            <div class="fl input"><input id="userName" name="userName" value="${userName}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td colspan="2">
				        <div class="form_cont">
				            <div class="lf_font fl"></div>
				            <div class="fl"><a href="javascript:void(0);" onclick="doSearch();" class="search">搜索</a><a href="/admin_list_count/countList.htm" class="remove" style="margin-left:30px;">清空</a></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				  </tr>
				</tbody>
				</table>
			</form>
            </div>
          <div class=" yhlb_title">财务报告列表</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody>
				  <tr>
				    <th>&nbsp;</th>
				    <th>用户名</th>
				    <th>期货服务器</th>
				    <th>期货账号</th>
				    <th>交易密码</th>
				    <th>真实姓名 </th>
				    <th>账户总额</th>
				    <th>公司资金</th>
				    <th>客户资金</th>
				    <th>预警线</th>
				    <th>止损线</th>
				    <th>商品隔夜</th>
				    <th>股指隔夜</th>
				    <th>开始日期</th>
				    <th>结算日期</th>
				    <th>计息天数</th>
				    <th>属性</th>
				    <th>利息</th>
				    <th>利率</th>
				    <th>商品手续费</th>
				    <th>股指手续费</th>
				    <th>上线客户</th>
				    <th>上线客户利率</th>
				    <th>手续费返佣标准</th>
				    </tr>
				  <c:forEach items="${countInfoList}" var="info" varStatus="row">
				  		<tr style="cursor: pointer;" onmouseover="$(this).css('background','#f6f6f6');" onmouseout="$(this).css('background','');">
						    <td align="center" class="num">${(row.index+1)+((pageNo-1)*pageSize)}</td>
						    <td>${info.fuUser.accountName}</td>
						    <td>${info.tradeServiceName}</td>
						    <td>${info.tradeAccount}</td>
						    <td>${info.tradePassword}</td>
						    <td style="color:#137490;cursor:pointer;" onclick="searchInfoByUser('${info.fuUser.userName}');">${info.fuUser.userName}</td>
						    <td><fmt:formatNumber value="${info.totalMatchMoney}" pattern="#,###,##0.00"/></td>
						    <td><fmt:formatNumber value="${info.matchMoney}" pattern="#,###,##0.00"/></td>
						    <td><fmt:formatNumber value="${info.safeMoney}" pattern="#,###,##0.00"/></td>
						    <td><fmt:formatNumber value="${info.warnLine}" pattern="#,###,##0.00"/></td>
						    <td><fmt:formatNumber value="${info.closeLine}" pattern="#,###,##0.00"/></td>
						    <td>${info.overnightGoodRate}</td>
						    <td>${info.overnightStockIndexRate}</td>
						    <td><fmt:formatDate value="${info.tradeTime}" pattern="yyyy-MM-dd"/></td>
						    <td><fmt:formatDate value="${info.closeTime}" pattern="yyyy-MM-dd"/></td>
						    <td>${info.cycleNum}${info.programType==1?'天':info.programType==2?'月':''}</td>
						    <td>${info.programType==1?'日配':info.programType==2?'月配':'期货大赛'}</td>
						    <td><fmt:formatNumber value="${info.manageMoney}" pattern="#,###,##0.00"/></td>
						    <td><c:if test="${info.programType==1}">${info.moneyPercent}</c:if><c:if test="${info.programType==2}">${info.moneyPercent*100}%</c:if></td>
						    <td>${info.goodsFee}</td>
						    <td>${info.stockIndexFee}</td>
						    <td>${info.fuUser.recommend.userName}</td>
						    <td>12%</td>
						    <td>1.1</td>
						 </tr>
				  </c:forEach>
				 <c:if test="${empty countInfoList}">
				  <tr>
					<td colspan="24">
			        	<div class=" empty0"><img src="${ctx}/images_qhht/empty_03.jpg" width="93" height="63"><p class="empty0_font">您暂时还没有相关信息</p></div>
			        </td>
				  </tr>
				  </c:if>
				</tbody>
				</table>
          </div>
           <div class="page">
				<domi:pagination ctx="${ctx}" pageVolume="${pageSize}" url="${ctx}/admin_list_count/countList.htm?totalCount=${totalCount}" totalNum="${totalCount}" curPageNum="${pageNo}" formId="pageForm" >
					<domi:paramTag name="userName" value="${userName}"/>
					<domi:paramTag name="beginTime1" value="${beginTime1}"/>
					<domi:paramTag name="beginTime2" value="${beginTime2}"/>
					<domi:paramTag name="endTime1" value="${endTime1}"/>
					<domi:paramTag name="endTime2" value="${endTime2}"/>
				</domi:pagination>
				<input id="totalCount" name="totalCount" type="hidden" value="${totalCount}"/>
			</div>
            <div class="clr"></div> 
        </div>
    </div>
</div>
</body>
</html>
<script>
//导出
function exportInfo(){
	var begintime1=$("#beginTime1").val();
	var begintime2=$("#beginTime2").val();
	var endtime1=$("#endTime1").val();
	var endtime2=$("#endTime2").val();
    var userName=$("#userName").val();
    var totalcount=$("#totalCount").val();
	$.fancybox.open({
			href : '${ctx}/admin_list_count/exportExcelAjax.htm?beginTime1='+begintime1+'&beginTime2='+begintime2
			+'&endTime1='+endtime1+'&endTime2='+endtime2+'&userName='+userName+'&totalCount='+totalcount,
			type : 'ajax'
	});
}
function doSearch(){
	$('#searchForm').submit();
}
function searchInfoByUser(userName){
	$("#userName").val(userName);
	doSearch();
}
</script>
