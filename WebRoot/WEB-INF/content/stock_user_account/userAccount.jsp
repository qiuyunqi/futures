<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－解套者资金账户统计－安全配资服务平台</title>
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
    	                    解套者资金账户统计
    	    </div>
    	  <div class="form">
	            <form id="searchForm" action="${ctx}/stock_user_account/userAccount.htm" method="post">
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
						            <div class="lf_font fl">股票账号：</div>
						            <div class="fl input"><input id="" name=capitalAccount value="${capitalAccount}" type="text" placeholder=""></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						    <td>
						    	<div class="form_cont">
						            <div class="lf_font fl"></div>
						            <div class="fl"><a href="javascript:void(0);" onclick="queryUserAccount();" class="search">查询</a><a href="${ctx}/stock_user_account/userAccount.htm" class="remove" style="margin-left:30px;">清空</a></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						  </tr>
					</tbody></table>
				</form>
            </div>  
    	    
          <div class=" yhlb_title">资金账户统计</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody>
				  <tr>
				    <th>&nbsp;</th>
				    <th>用户ID</th>
				    <th>用户手机号</th>
				    <th>股票账号</th>
				    <th>用户姓名</th>
				    <th>累计盈亏(元)</th>
				    <th>创建时间</th>
				    <th>更新时间</th>
				  </tr>
				  <c:forEach items="${accountList}" var="account" varStatus="row">
					  <tr style="cursor:pointer;" onmouseover="$(this).attr('bgcolor','#f6f6f6');" onmouseout="$(this).attr('bgcolor','');" >
					    <td align="center" class="num">${row.index+1}</td>
					    <td>${account.fuUser.id}</td>
					    <td>${account.fuUser.phone}</td>
					    <td>${account.fuStockAccount.capitalAccount}</td>
					    <td>${account.fuUser.userName}</td>
					    <td style=" text-align:right">${account.profitInfo}</td>
					    <td><fmt:formatDate value="${account.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					    <td><fmt:formatDate value="${account.updatetime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					  </tr>
				  </c:forEach>
				</tbody>
				</table>
          </div>
           <c:if test="${empty accountList}">
				  <div style="text-align:center;">暂无内容！</div>
		  </c:if>
		  <div class="page">
			<domi:pagination ctx="${ctx}" pageVolume="${pageSize}"
				url="${ctx}/stock_user_account/userAccount.htm?totalCount=${totalCount}"
				totalNum="${totalCount}" curPageNum="${pageNo}"
				formId="pageForm">
		        <domi:paramTag name="userId" value="${userId}"/>
		        <domi:paramTag name="phone" value="${phone}"/>
		        <domi:paramTag name="capitalAccount" value="${capitalAccount}"/>
			</domi:pagination>
		</div>
        </div>
    </div>
</div>
</body>
</html>
<script>
function queryUserAccount(){
	$('#searchForm').submit();
}
</script>
