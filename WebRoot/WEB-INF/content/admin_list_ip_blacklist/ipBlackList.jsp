<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－留言管理－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">IP黑名单管理
    	        <domi:privilege url="/admin_list_ip_blacklist/newIpBlackAjax.htm">
    	    	<a href="javascript:void(0);" onclick="addIpBlack('');" class="fr add">添加IP黑名单</a>
    	    	</domi:privilege>
    	    </div>
            <div class="form">
            <form id="searchForm" action="${ctx}/admin_list_ip_blacklist/ipBlackList.htm" method="post">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tbody><tr>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">IP：</div>
				            <div class="fl input"><input id="ip" name="ip" value="${ip}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">状态：</div>
				            <div class="fl select"><select style="width:178px;" id=isBlack name="isBlack">
				            	<option value="">全部</option>
				            	<option <c:if test="${isBlack==0}">selected="selected"</c:if> value="0">白名单</option>
				            	<option <c:if test="${isBlack==1}">selected="selected"</c:if> value="1">黑名单</option>
				            </select></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl"></div>
				            <div class="fl"><a href="javascript:void(0);" onclick="searchInfo();" Class="search">搜索</a><a href="${ctx}/admin_list_ip_blacklist/ipBlackList.htm" Class="remove" style="margin-left:30px;">清空</a></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				  </tr>
				</tbody></table>
			</form>
            </div>
          <div class=" yhlb_title">IP黑名单列表</div>
            <div class="yhlb">
            <table width="100%">
			  <tbody><tr>
			    <th width="2%">&nbsp;</th>
			    <th width="30%">IP</th>
			    <th width="10%">状态</th>
			    <th width="10%">创建人</th>
			    <th width="15%">创建时间</th>
			    <th width="10%">更新人</th>
			    <th width="15%">更新时间</th>
			    <th style="text-align:center">操作</th>
			    </tr>
			  <c:forEach items="${IpBlackList}" var="IpBlack" varStatus="row">
			  		<tr style="cursor: pointer;" onmouseover="$(this).css('background','#f6f6f6');" onmouseout="$(this).css('background','');">
			  		<c:if test="${IpBlack.isBlack==1}">
				  		<tr style="color: red">
			  		</c:if>
				    <td class="num">${(row.index+1)+((pageNo-1)*pageSize)}</td>
				    <td>${IpBlack.ip}</td>
				    <td>${IpBlack.isBlack==1?'黑名单':'白名单'}</td>
				    <td>${IpBlack.creatAdmin.name}</td>
				    <td><fmt:formatDate value="${IpBlack.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				    <td>${IpBlack.updateAdmin.name}</td>
				    <td><fmt:formatDate value="${IpBlack.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					 <td style="text-align:center">
					 <div class="caozuo">
						 <domi:privilege url="/admin_list_ip_blacklist/newIpBlackAjax.htm">
						 	<a href="javascript:void(0);" onclick="addIpBlack(${IpBlack.id});">编辑</a>
						 </domi:privilege>
					 </div>
					 </td>
				  </tr>
			  </c:forEach>
			  <c:if test="${empty IpBlackList}">
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
				<domi:pagination ctx="${ctx}" pageVolume="${pageSize}" url="${ctx}/admin_list_ip_blacklist/ipBlackList.htm?totalCount=${totalCount}" totalNum="${totalCount}" curPageNum="${pageNo}" formId="pageForm" >
						<domi:paramTag name="ip" value="${ip}"/>
						<domi:paramTag name="isBlack" value="${isBlack}"/>
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
function addIpBlack(id){
	$.fancybox.open({
        href : '${ctx}/admin_list_ip_blacklist/newIpBlackAjax.htm?id='+id,
        type : 'ajax',
        padding : 10
	});
}
</script>
