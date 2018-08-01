<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－后台账号管理－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<c:set var="first" value="4"/>
<c:set var="second" value="3"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">
    	                    后台服务器列表
    	        <domi:privilege url="/admin_op_server/newServerAjax.htm">            
    	    	<a href="javascript:void(0);" onclick="addAdmin();" class="fr add">添加</a>
    	    	</domi:privilege>
    	    </div>
         
          <div class=" yhlb_title">服务器列表</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody><tr>
				    <th>&nbsp;</th>
				    <th>服务器别名</th>
				    <th>服务器真名</th>
				    <th>角色ID</th>
				    <th>服务器IP</th>
				    <th>服务器优先级</th>
				    <th>开始序列号</th>
				    <th>当前序列号</th>
				    <th>服务器金额</th>
				    <th>结算组</th>
				    <th>开户组</th>
				    <th>日配1-10组</th>
				    <th>月配1-10组</th>
				    <th>操作</th>
				  </tr>
				  <c:forEach items="${serverList}" var="server" varStatus="row">
				  <tr style="cursor:pointer;" onmouseover="$(this).attr('bgcolor','#f6f6f6');" onmouseout="$(this).attr('bgcolor','');" >
				    <td class="num">${row.index+1}</td>
				    <td onclick="showUpadateServer(${server.id });" style="color:#137490">${server.serverName}</td>
				    <td onclick="showUpadateServer(${server.id });" style="color:#137490">${server.serverRealName}</td>
				    <td>${server.usertypeId}</td>
				    <th>${server.serverIp}</th>
				    <td>${server.serverPriority}</td>
				    <td>${server.startNumber}</td>
				    <td>${server.nowNumber}</td>
				    <td>${server.serverMoney}</td>
				    <td>${server.clearingId}</td>
				    <td>${server.openuserId}</td>
				    <td>${server.dayId1},${server.dayId1},${server.dayId2},${server.dayId3},${server.dayId4},${server.dayId5},${server.dayId6},${server.dayId7},${server.dayId8},${server.dayId9},${server.dayId10}</td>
				    <td>${server.monthId1},${server.monthId2},${server.monthId3},${server.monthId4},${server.monthId5},${server.monthId6},${server.monthId7},${server.monthId8},${server.monthId9},${server.monthId10} </td>
				 	<td>
				 	<domi:privilege url="/admin_op_server/showUpadateServer.htm">
				 	<a href="javascript:void(0);" onclick="showUpadateServer(${server.id });" >编辑</a>
				 	</domi:privilege>
				 	&nbsp;|&nbsp;
				 	<domi:privilege url="/admin_op_server/delServerAjax.htm">
				 	<a href="javascript:void(0);"  onclick="delServer(${server.id });">删除</a>
				 	</domi:privilege>
				 	</td>
				  </tr>
				  </c:forEach>
				</tbody>
				</table>
          </div>
           <c:if test="${empty serverList}">
				  <div style="text-align:center;">暂无内容！</div>
		  </c:if>
          <div class="page">
			<domi:pagination ctx="${ctx}" pageVolume="${pageSize}"
				url="${ctx}/admin_list_admin/adminList.htm?totalCount=${totalCount}"
				totalNum="${totalCount}" curPageNum="${pageNo}"
				formId="pageForm">
		        <domi:paramTag name="account" value="${account}"/>
		        <domi:paramTag name="name" value="${name}"/>
			</domi:pagination>
		</div>
        </div>
    </div>
</div>
</body>
</html>
<script>
function doSearch(){
	$('#searchForm').submit();
}

function addAdmin(){
	$.fancybox.open({
          href : '${ctx}/admin_op_server/newServerAjax.htm',
          type : 'ajax',
          padding : 10
	});
}

function delServer(id){
    if (!confirm("确认要删除？")) {
        window.event.returnValue = false;
        return null;
    }
    
	$.post("${ctx}/admin_op_server/delServerAjax.htm?id="+id,null,function(d){
		  if(d==1){
              sureInfo("确定","删除成功","提示");
              location.href = location.href;
          }
	});
}

function showUpadateServer(id){
	$.fancybox.open({
        href : '${ctx}/admin_op_server/showUpadateServer.htm?id='+id,
        type : 'ajax',
        padding : 10
	});
}

</script>
