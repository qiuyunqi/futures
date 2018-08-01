<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－版本记录</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<c:set var="first" value="5"/>
<c:set var="second" value="1"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">APP管理</div>            
            <div class=" yhlb_title">版本记录</div>
	            <div class="yhlb">
	            	 <table width="100%">
						  <tbody>
							  <tr>
							    <th>&nbsp;</th>
							    <th>第几次升级</th>
							    <th>版本号</th>
							    <th>下载地址</th>
							    <th>更新信息</th>
							    <th>应用类别</th>
							    <th style=" text-align:center">操作</th>
							  </tr>
							  <c:forEach items="${versionList}" var="version" varStatus="row">
						  		<tr style="cursor: pointer;" onmouseover="$(this).css('background','#f6f6f6');" onmouseout="$(this).css('background','');">
								    <td align="center" class="num">${row.index+1}</td>
								    <td>${version.versionCode}</td>
								    <td>${version.versionName}</td>
								    <td>${version.downloadUrl}</td>
								    <td>${version.updateLog}</td>
								    <td>${version.appType==1?'Android':'iOS'}</td>
								    <td style="text-align:center">
					    				<div class=" caozuo">
					    					<domi:privilege url="/app_manage/newVersionAjax.htm">
					    					<a href="javascript:void(0);" onclick="addVersion('${version.id}');">修改</a>
					    					</domi:privilege>
					    				</div>
				    				</td>
								 </tr>
							  </c:forEach>
							  <c:if test="${empty versionList}">
				  			      <div class=" empty0"><img src="${ctx}/images_qhht/empty_03.jpg" width="93" height="63"><p class="empty0_font">没有信息</p></div>
		  					  </c:if>
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
function addVersion(id){
	$.fancybox.open({
          href : '${ctx}/app_manage/newVersionAjax.htm?id='+id,
          type : 'ajax',
          padding : 10
	});
}
</script>
