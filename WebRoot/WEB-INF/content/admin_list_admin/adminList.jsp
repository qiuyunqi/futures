<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－管理员管理－安全配资服务平台</title>
<%@include file="../common/cssback.jsp" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
</head>
<body style=" background:#fff">
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">
    	                     后台账号列表
    	    	<domi:privilege url="/admin_op_admin/newAdminAjax.htm"><a href="javascript:void(0);" onclick="addAdmin('','');" class="fr add">添加</a></domi:privilege>
    	    </div>
            <div class="form">
                <form id="searchForm" action="${ctx}/admin_list_admin/adminList.htm" method="post">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tbody><tr>
					    <td>
					        <div class="form_cont">
					            <div class="lf_font fl">账号：</div>
					            <div class="fl input"><input name="account" type="text" placeholder=""></div>
					            <div class="clr"></div>
					        </div>
					    </td>
					    <td>
					        <div class="form_cont">
					            <div class="lf_font fl">真实姓名：</div>
					            <div class="fl input"><input name="name" type="text" placeholder=""></div>
					            <div class="clr"></div>
					        </div>
					    </td>
					    <td>
					        <div class="form_cont">
					            <div class="lf_font fl"></div>
					            <div class="fl"><a href="javascript:void(0);" onclick="doSearch();" class="search">搜索</a><a href="${ctx}/admin_list_admin/adminList.htm" class="remove" style="margin-left:30px;">清空</a></div>
					            <div class="clr"></div>
					        </div>
					    </td>
					  </tr>
					</tbody>
				</table>
				</form>
            </div>
          <div class=" yhlb_title">账号列表</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody><tr>
				    <th>&nbsp;</th>
				    <th>账号</th>
				    <th>姓名</th>
				    <th>类型</th>
				    <th>职位</th>
				    <th>角色</th>
				    <th>注册时间/最后登录</th>
				    <th style=" text-align:center">操作</th>
				  </tr>
				  <c:forEach items="${adminList}" var="admin" varStatus="row">
				  <tr style="cursor:pointer;" onmouseover="$(this).attr('bgcolor','#f6f6f6');" onmouseout="$(this).attr('bgcolor','');">
				    <td class="num">${row.index+1}</td>
				    <td style="color:#137490">${admin[1]}</td>
				    <td>${admin[2]}</td>
				    <td>${admin[3]==0?'普通管理员':'超级管理员'}</td>
				    <td>${admin[4]}</td>
				    <td>${admin[7]}</td>
				    <td><fmt:formatDate value="${admin[5]}" pattern="yy-MM-dd"/>/<fmt:formatDate value="${admin[6]}" pattern="yy-MM-dd"/></td>
				    <td style="text-align:center">
				    <div class=" caozuo">
				    <domi:privilege url="/admin_op_admin/newAdminAjax.htm"><a href="javascript:void(0);" onclick="addAdmin('${admin[0]}','${admin[7]}');">修改</a></domi:privilege>
				    <span>|</span><domi:privilege url="/admin_op_admin/delAdminAjax.htm"><a href="javascript:void(0);" onclick="delAdmin('${admin[0]}');">删除</a></domi:privilege>
				    </div>
				    </td>
				  </tr>
				  </c:forEach>
				   <c:if test="${empty adminList}">
				  <tr >
					<td colspan="8">
			        	<div class=" empty0"><img src="../images_qhht/empty_03.jpg" width="93" height="63"><p class="empty0_font">您暂时还没有相关信息</p></div>
			        </td>
			      </tr>
			      </c:if>
				</tbody>
				</table>
          </div>
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

function addAdmin(id,rolename){
	$.fancybox.open({
          href : "${ctx}/admin_op_admin/newAdminAjax.htm?id="+id+"&rolename="+rolename,
          type : 'ajax',
          padding : 10
	});
}

function delAdmin(id){
	jConfirm("您确定要删除吗？","删除提示",function(res){
		if(res){
			$.get('${ctx}/admin_op_admin/delAdminAjax.htm?id='+id,null,function(d){
			   if(d==-1){
	                sureInfo("确定","您没有操作权限","提示");
	                return null;
	            }
				location.href = location.href;
			})
		}
	});
}
</script>
