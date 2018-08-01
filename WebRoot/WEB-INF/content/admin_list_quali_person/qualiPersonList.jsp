<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－资格认证－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<c:set var="first" value="2"/>
<c:set var="second" value="12"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">资格认证</div>
            <div class="form">
            <form id="searchForm" action="${ctx}/admin_list_quali_person/qualiPersonList.htm" method="post">
            	<table width="50%" border="0" cellspacing="0" cellpadding="0">
				  <tbody><tr>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">真实姓名：</div>
				            <div class="fl input"><input id="userName" name="userName" value="${userName}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				    	<div class="form_cont">
				            <div class="lf_font fl">资格证号：</div>
				            <div class="fl input"><input id="qualiNum" name="qualiNum" value="${qualiNum}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				    	<div class="form_cont">
				            <div class="lf_font fl"></div>
				            <div class="fl"><a href="javascript:void(0);" onclick="doSearch();" class="search">搜索</a><a href="${ctx}/admin_list_quali_person/qualiPersonList.htm" class="remove" style="margin-left:30px;">清空</a></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				  </tr>
				</tbody></table>
			</form>

            </div>
          <div class=" yhlb_title">资格证书</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody><tr>
				    <th>&nbsp;</th>
				    <th>用户ID</th>
				    <th>真实姓名</th>
				    <th>资格证号 </th>
				    <th>状态</th>
				    <th style=" text-align:center">操作</th>
				    </tr>
				  <c:forEach items="${qualiList}" var="quali" varStatus="row">
				  		<tr style="cursor: pointer;" onmouseover="$(this).css('background','#f6f6f6');" onmouseout="$(this).css('background','');">
						    <td align="center" class="num">${(row.index+1)+((pageNo-1)*pageSize)}</td>
						    <td>${quali.fuUser.id}</td>
						    <td style="color:#137490;cursor:pointer;" onclick="searchInfo('${quali.fuUser.userName}');">${quali.userName}</td>
						    <td>${quali.qualiNum}</td>
						    <td>${quali.isChecked==0?'未认证':quali.isChecked==1?'待认证':quali.isChecked==2?'已认证':'其它状态'}</td>
						    <td style="text-align:center">
						    <domi:privilege url="/admin_list_quali_person/qualiPersonAjax.htm">
						    	<div class=" caozuo"><a href="javascript:void(0);" onclick="qualiPerson(${quali.id});">认证</a></div>
					    	</domi:privilege>
						    </td>
						 </tr>
				  </c:forEach>
				  <c:if test="${empty qualiList}">
				  <tr>
					<td colspan="6">
			        	<div class=" empty0"><img src="${ctx}/images_qhht/empty_03.jpg" width="93" height="63"><p class="empty0_font">您暂时还没有相关信息</p></div>
			        </td>
				  </tr>
				  </c:if>
				</tbody></table>
          </div>
           <div class="page">
				<domi:pagination ctx="${ctx}" pageVolume="${pageSize}" url="${ctx}/admin_list_quali_person/qualiPersonList.htm?totalCount=${totalCount}" totalNum="${totalCount}" curPageNum="${pageNo}" formId="pageForm" >
					<domi:paramTag name="userName" value="${userName}"/>
					<domi:paramTag name="qualiNum" value="${qualiNum}"/>
				</domi:pagination>
		   </div>
               <div class="clr"></div> 
        </div>
    </div>
</div>
</body>
</html>
<script>
function doSearch(){
	$('#searchForm').submit();
}
function searchInfo(userName){
	$("#userName").val(userName);
	doSearch();
}
function qualiPerson(id){
	$.fancybox.open({
          href : '${ctx}/admin_list_quali_person/qualiPersonAjax.htm?id='+id,
          type : 'ajax',
          padding : 10
	});
}
</script>
