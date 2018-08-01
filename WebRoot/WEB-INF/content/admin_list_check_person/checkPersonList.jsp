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
<c:set var="second" value="6"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">用户认证<!-- <a href="#" class="fr add">添加</a> --></div>
            <div class="form">
            <form id="searchForm" action="${ctx}/admin_list_check_person/checkPersonList.htm" method="post">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tbody><tr>
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
				            <div class="lf_font fl">证件号码：</div>
				            <div class="fl input"><input id="cardNumber" name="cardNumber" value="${cardNumber}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				    	<div class="form_cont">
				            <div class="lf_font fl"></div>
				            <div class="fl"><a href="javascript:void(0);" onclick="searchInfo();" class="search">搜索</a><a href="${ctx}/admin_list_check_person/checkPersonList.htm" class="remove" style="margin-left:30px;">清空</a></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				  </tr>
				</tbody>
				</table>
			</form>
            </div>
          <div class=" yhlb_title">用户认证列表</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody>
				  <tr>
				    <th>&nbsp;</th>
				    <th>用户ID</th>
				    <th>用户名</th>
				    <th>真实姓名</th>
				    <th>证件号码 </th>
				    <th>余额</th>
				    <th>状态</th>
				    <c:if test="${empty rageConfig||!empty rageConfig.yh.yh12}">
				    <th style=" text-align:center">操作</th>
				    </c:if>
				    </tr>
				  <c:forEach items="${checkUserList}" var="user" varStatus="row">
				  		<tr onmouseover="$(this).css('background','#f6f6f6');" onmouseout="$(this).css('background','');">
						    <td align="center" class="num">${(row.index+1)+((pageNo-1)*pageSize)}</td>
						    <td style="color:#137490;cursor:pointer;" <c:if test="${empty rageConfig||!empty rageConfig.yh.yh12}">onclick="checkPerson(${user.id});"</c:if>>${user.id}</td>
						    <td>${user.accountName}</td>
						    <td style="color:#137490;cursor:pointer;" onclick="searchInfoByUser('${user.userName}');">${user.userName}</td>
						    <td>${user.cardNumber}</td>
						    <td><fmt:formatNumber value="${empty user.accountBalance?0:user.accountBalance}" pattern="#,###,##0.00"/></td>
						    <td>${user.isCheckCard==0?'未认证':user.isCheckCard==1?'待认证':user.isCheckCard==2?'已认证':'信息有误'}</td>
						    <td style="text-align:center"><div class="caozuo">
						    <domi:privilege url="/admin_op_check_person/checkDetailAjax.htm">
						    <c:if test="${user.isCheckCard==1}"><a href="javascript:void(0);" onclick="checkPerson(${user.id});">认证</a></c:if></domi:privilege></div>
						    </td>
						 </tr>
				  </c:forEach>
				  <c:if test="${empty checkUserList}">
				  <tr>
					<td colspan="8">
			        	<div class=" empty0"><img src="${ctx}/images_qhht/empty_03.jpg" width="93" height="63"><p class="empty0_font">您暂时还没有相关信息</p></div>
			        </td>
				  </tr>
				  </c:if>
				</tbody>
				</table>
          </div>
           <div class="page">
				<domi:pagination ctx="${ctx}" pageVolume="${pageSize}" url="${ctx}/admin_list_check_person/checkPersonList.htm?totalCount=${totalCount}" totalNum="${totalCount}" curPageNum="${pageNo}" formId="pageForm" >
						<domi:paramTag name="accountName" value="${accountName}"/>
						<domi:paramTag name="userName" value="${userName}"/>
						<domi:paramTag name="cardNumber" value="${cardNumber}"/>
				</domi:pagination>
			</div>
               <div class="clr"></div> 
        </div>
    </div>
</div>
</body>
</html>
<script>
function searchInfo(){
	$('#searchForm').submit();
}
function checkPerson(id){
	$.fancybox.open({
          href : '${ctx}/admin_op_check_person/checkDetailAjax.htm?id='+id,
          type : 'ajax',
          padding : 10
	});
}
function searchInfoByUser(userName){
	$("#userName").val(userName);
	searchInfo();
}
</script>
