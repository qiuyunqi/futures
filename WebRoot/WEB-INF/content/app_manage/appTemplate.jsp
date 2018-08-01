<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－模板设置</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<c:set var="first" value="5"/>
<c:set var="second" value="2"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">APP管理</div>            
            <div class=" yhlb_title">模板设置</div>
	            <div class="yhlb">
	            	 <table width="100%">
						  <tbody>
							  <tr>
							    <th>&nbsp;</th>
							    <th>模板详情</th>
							    <th>更改人</th>
							    <th>更改时间</th>
							    <th style=" text-align:center">操作</th>
							  </tr>
						  		<tr style="cursor: pointer;" onmouseover="$(this).css('background','#f6f6f6');" onmouseout="$(this).css('background','');">
								    <td align="center" class="num">1</td>
								    <td>${appTemp.template}</td>
								    <td>${appTemp.updateAdmin.name}</td>
								    <td><fmt:formatDate value="${appTemp.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								    <td style="text-align:center">
					    				<div class=" caozuo">
					    					<domi:privilege url="/app_manage/updateTemplateAjax.htm">
					    					<a href="javascript:void(0);" onclick="updateAppTemp();">修改</a>
					    					</domi:privilege>
					    				</div>
				    				</td>
								 </tr>
						</tbody>
					</table>
	          </div>
	          
            <div class="clr"></div> 
        </div>
    </div>
</div>
</body>
</html>
<script>
function updateAppTemp(){
	$.fancybox.open({
          href : '${ctx}/app_manage/updateTemplateAjax.htm',
          type : 'ajax',
          padding : 10
	});
}
</script>
