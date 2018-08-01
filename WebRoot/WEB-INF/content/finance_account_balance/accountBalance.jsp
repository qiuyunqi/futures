<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－账户余额查询－安全配资服务平台</title>
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
<c:set var="first" value="8"/>
<c:set var="second" value="4"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	<div class="rt_cont_title">
    	                    账户余额查询
    	    </div>
    	  <div class="form">
	            <form id="searchForm" action="${ctx}/finance_account_balance/accountBalance.htm" method="post">
	            	<table width="50%" border="0" cellspacing="0" cellpadding="0">
					  <tbody>
						  <tr>
						    <td>
						        <div class="form_cont">
						            <div class="lf_font fl">查询日期：</div>
						            <div class="fl input"><input name="beginTime" type="text" value="${beginTime}" placeholder="" style="width:80px;" onclick="WdatePicker({dateFmt : 'yyyy-MM-dd'});" id="beginTime"><i class="riqi" ></i></div>
						            <div class="fl duanxian">—</div>
						            <div class="fl input"><input name="endTime" type="text" value="${endTime}" placeholder="" style="width:80px;" onclick="WdatePicker({dateFmt : 'yyyy-MM-dd'});" id="endTime"><i class="riqi" ></i></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						    <td>
						        <div class="form_cont">
						            <div class="lf_font fl ">用户是否禁用：</div>
						            <div class="fl checkbox"><span class="xuankang"><input name="state" <c:if test="${state==0}">checked="checked"</c:if> type="checkbox" value="0" style="width:13px;" id="state"></span></div>
						        </div>
						        <div class="clr"></div>
						    </td>
						    <td>
						    	<div class="form_cont">
						            <div class="lf_font fl"></div>
						            <div class="fl">
							            <a href="javascript:void(0);" onclick="queryAccountBalance();" class="search">查询</a>
							            <a href="${ctx}/finance_account_balance/accountBalance.htm" class="remove" style="margin-left:30px;">清空</a>
							            <c:if test="${!empty userList}">
							            	<a href="javascript:void(0);" onclick="exportExcel();" class="search" style="margin-left:30px;">导出</a>
							            </c:if>
						            </div>
						            <div class="clr"></div>
						        </div>
						    </td>
						  </tr>
					</tbody></table>
				</form>
            </div>  
    	    
          <div class=" yhlb_title">账户余额记录</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody>
				  <tr>
				    <th>&nbsp;</th>
				    <th>用户名</th>
				    <th>真实姓名</th>
				    <th>余额(元)</th>
				    <th>备注</th>
				    <th>用户状态</th>
				  </tr>
				  <c:forEach items="${userList}" var="user" varStatus="row">
					  <tr style="cursor:pointer;" onmouseover="$(this).attr('bgcolor','#f6f6f6');" onmouseout="$(this).attr('bgcolor','');" >
					    <td align="center" class="num">${row.index+1}</td>
					    <td style="color:#137490;cursor:pointer;" onclick="userMoneyTradeList('${user[0]}');">${user[1]}</td>
					    <td>${user[2]}</td>
					    <td>${user[4]==null?user[3]:user[4]}</td>
					    <td></td>
					    <td>${user[5]==0?'已禁用':user[5]==1?'未禁用':''}</td>
					  </tr>
				  </c:forEach>
				</tbody>
				</table>
          </div>
          <c:if test="${empty userList}">
				  <div style="text-align:center;">暂无内容！</div>
		  </c:if>
          <c:if test="${!empty userList}"><div class="hgrzxtzh">用户余额：<span><fmt:formatNumber value="${totalBalance}" pattern="#,###,##0.00"/></span>元</div></c:if>
		  <div class="page">
			<domi:pagination ctx="${ctx}" pageVolume="${pageSize}"
				url="${ctx}/finance_account_balance/accountBalance.htm?totalCount=${totalCount}"
				totalNum="${totalCount}" curPageNum="${pageNo}"
				formId="pageForm">
		        <domi:paramTag name="beginTime" value="${beginTime}"/>
		        <domi:paramTag name="endTime" value="${endTime}"/>
		        <domi:paramTag name="state" value="${state}"/>
			</domi:pagination>
		</div>
        </div>
    </div>
</div>
</body>
</html>
<script>
function exportExcel(){
	var beginTime = $('#beginTime').val();
	var endTime = $('#endTime').val();
	window.location.href='${ctx}/finance_account_balance/exportExcel.htm?beginTime=' + beginTime+'&endTime='+endTime;
}
function queryAccountBalance(){
	$('#searchForm').submit();
}
function userMoneyTradeList(id){
	var begin = $('#beginTime').val();
	var end = $('#endTime').val();
	window.location="${ctx}/admin_list_money_trade/userMoneyTradeList.htm?queryUserId="+id+"&beginTime="+begin+"&endTime="+end;
}
</script>
