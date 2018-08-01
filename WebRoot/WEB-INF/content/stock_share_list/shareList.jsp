<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－股票查询－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	<div class="rt_cont_title">
    	                    股票查询
    	    </div>
    	  <div class="form">
	            <form id="searchForm" action="${ctx}/stock_share_list/shareList.htm" method="post">
	            	<table width="70%" border="0" cellspacing="0" cellpadding="0">
					  <tbody>
						  <tr>
						    <td>
						        <div class="form_cont">
						            <div class="lf_font fl">股票名称：</div>
						            <div class="fl input"><input id="" name="shareName" value="${shareName}" type="text" placeholder=""></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						    <td>
						        <div class="form_cont">
						            <div class="lf_font fl">股票代码：</div>
						            <div class="fl input"><input id="" name="shareCode" value="${shareCode}" type="text" placeholder=""></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						    <td>
						        <div class="form_cont">
						            <div class="lf_font fl">资金账号：</div>
						            <div class="fl input"><input id="" name="capitalAccount" value="${capitalAccount}" type="text" placeholder=""></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						    <td>
						    	<div class="form_cont">
						            <div class="lf_font fl"></div>
						            <div class="fl"><a href="javascript:void(0);" onclick="queryStockShare();" class="search">查询</a><a href="${ctx}/stock_share_list/shareList.htm" class="remove" style="margin-left:30px;">清空</a></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						  </tr>
					</tbody></table>
				</form>
            </div>  
    	    
          <div class=" yhlb_title">股票列表</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody>
				  <tr>
				    <th>&nbsp;</th>
				    <th>股票名称</th>
				    <th>股票代码</th>
				    <th>股票数量</th>
				    <th>用户姓名</th>
				    <th>资金账号</th>
				  </tr>
				  <c:forEach items="${shareList}" var="share" varStatus="row">
					  <tr style="cursor:pointer;" onmouseover="$(this).attr('bgcolor','#f6f6f6');" onmouseout="$(this).attr('bgcolor','');" >
					    <td align="center" class="num">${row.index+1}</td>
					    <td>${share.name}</td>
					    <td>${share.code}</td>
					    <td>${share.number}</td>
					    <td>${share.fuStockAccount.openUser}</td>
					    <td>${share.fuStockAccount.capitalAccount}</td>
					  </tr>
				  </c:forEach>
				</tbody>
				</table>
          </div>
          <c:if test="${empty shareList}">
				  <div style="text-align:center;">暂无内容！</div>
		  </c:if>
		  <div class="page">
			<domi:pagination ctx="${ctx}" pageVolume="${pageSize}"
				url="${ctx}/stock_share_list/shareList.htm?totalCount=${totalCount}"
				totalNum="${totalCount}" curPageNum="${pageNo}"
				formId="pageForm">
		        <domi:paramTag name="shareName" value="${shareName}"/>
		        <domi:paramTag name="shareCode" value="${shareCode}"/>
		        <domi:paramTag name="capitalAccount" value="${capitalAccount}"/>
			</domi:pagination>
		</div>
        </div>
    </div>
</div>
</body>
</html>
<script>
function queryStockShare(){
	$('#searchForm').submit();
}
</script>
