<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－用户认证管理－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<c:set var="first" value="2"/>
<c:set var="second" value="10"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">结算查询</div>
            <div class="form">
	            <form id="searchForm" action="${ctx}/admin_list_settlement/settlementQuery.htm" method="post">
	            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tbody>
						  <tr>
						    <td>
						        <div class="form_cont">
						            <div class="lf_font fl">用户名：</div>
						            <div class="fl input"><input id="accountName" name="accountName" value="${accountName}" type="text" placeholder=""></div>
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
						    <td>
						        <div class="form_cont">
						            <div class="lf_font fl">统计时间：</div>
						            <div class="fl input"><input name="time1" type="text" value="<fmt:formatDate value='${time1}' pattern='yyyy-MM-dd'/>" placeholder="" style="width:100px;" onclick="WdatePicker({dateFmt : 'yyyy-MM-dd'});"><i class="riqi" ></i></div>
						            <div class="fl duanxian">—</div>
						            <div class="fl input"><input name="time2" type="text" value="<fmt:formatDate value='${time2}' pattern='yyyy-MM-dd'/>" placeholder="" style="width:100px;" onclick="WdatePicker({dateFmt : 'yyyy-MM-dd'});"><i class="riqi" ></i></div>
						            <div class="clr"></div>
						        </div>
					    	</td>
						    <td>
						    	<div class="form_cont">
						            <div class="lf_font fl"></div>
						            <div class="fl"><a href="javascript:void(0);" onclick="queryHhrStat();" class="search">查询</a><a href="${ctx}/admin_list_settlement/settlementQuery.htm" class="remove" style="margin-left:30px;">清空</a></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						  </tr>
					</tbody></table>
				</form>
            </div>
            
	          <div class=" yhlb_title">合伙人状态列表</div>
	            <div class="yhlb">
	            	 <table width="100%">
					  <tbody>
					  <tr>
					    <th>&nbsp;</th>
					    <th>状态ID</th>
					    <th>用户ID</th>
					    <th>用户名</th>
					    <th>真实姓名</th>
					    <th>统计日期</th>
					    <th>日增收益</th>
					  </tr>
					  <c:forEach items="${hhrStatList}" var="hhrStat" varStatus="row">
				  		<tr style="cursor: pointer;" onmouseover="$(this).css('background','#f6f6f6');" onmouseout="$(this).css('background','');">
						    <td align="center" class="num">${(row.index+1)+((pageNo-1)*pageSize)}</td>
						    <td style="color:#137490">${hhrStat.id}</td>
						    <td>${hhrStat.fuUser.id}</td>
						    <td>${hhrStat.fuUser.accountName}</td>
						    <td style="color:#137490;cursor:pointer;" onclick="searchInfoByUser('${hhrStat.fuUser.userName}');">${hhrStat.fuUser.userName}</td>
   						  	<c:if test="${!empty hhrStat.statDate}"><td><fmt:formatDate value="${hhrStat.statDate}" pattern="yyyy-MM-dd"/></td></c:if>
   						  	<c:if test="${empty hhrStat.statDate}"><td></td></c:if>
						    <td><fmt:formatNumber value="${empty hhrStat.dailyIncome?0:hhrStat.dailyIncome}" pattern="#,###,##0.00"/></td>
						 </tr>
					  </c:forEach>
					  <c:if test="${empty hhrStatList}">
						  <tr>
							<td colspan="8">
					        	<div class=" empty0"><img src="${ctx}/images_qhht/empty_03.jpg" width="93" height="63"><p class="empty0_font">您暂时还没有相关信息</p></div>
					        </td>
						  </tr>
					  </c:if>
					</tbody></table>
	
	          </div>
            
           <div class="page">
				<domi:pagination ctx="${ctx}" pageVolume="${pageSize}" url="${ctx}/admin_list_settlement/settlementQuery.htm?totalCount=${totalCount}" totalNum="${totalCount}" curPageNum="${pageNo}" formId="pageForm" >
					<domi:paramTag name="accountName" value="${accountName}"/>
					<domi:paramTag name="userName" value="${userName}"/>
					<domi:paramTag name="time1" value="${time1}"/>
					<domi:paramTag name="time2" value="${time2}"/>
				</domi:pagination>
			</div>
             <div class="clr"></div> 
            
        </div>
    </div>
</div>
</body>
</html>
<script>
function queryHhrStat(){
	$('#searchForm').submit();
}
function searchInfoByUser(userName){
	$("#userName").val(userName);
	queryHhrStat();
}
</script>
