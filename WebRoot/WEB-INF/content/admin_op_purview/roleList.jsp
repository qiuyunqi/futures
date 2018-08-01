<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－角色管理</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/cssback.jsp" %>
<style type="text/css">
.bg{
background-color:#808080;
} 
</style>
</head>
<body style="background:#fff">
<c:set var="first" value="7"/>
<c:set var="second" value="3"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">角色管理</div>            
            <div class=" yhlb_title">角色管理</div>
	            <div class="yhlb">
				
				<!-- 编辑栏  -->
				<div id="tb" style="padding:5px 5px;">
					<ul>
						<li style="float:left;margin-left:10px;"><domi:privilege url="/admin_op_purview/addRole.htm"><img src="../images/add.png"/><a href="javascript:void(0);" onclick="addRole();">新增</a></domi:privilege></li>
						<li style="float:left;margin-left:10px;"><domi:privilege url="/admin_op_purview/editRole.htm"><img src="../images/edit.png"/><a href="javascript:void(0);" onclick="editRole();">编辑</a></domi:privilege></li>
						<li style="float:left;margin-left:10px;"><domi:privilege url="/admin_op_purview/delRoleAjax.htm"><img src="../images/del.png"/><a href="javascript:void(0);" onclick="delRole();">删除</a></domi:privilege></li>
						<li style="float:left;margin-left:10px;"><domi:privilege url="/admin_op_purview/setPurview.htm"><img src="../images/edit.png"/><a href="javascript:void(0);" onclick="setMenu();">设置权限</a></domi:privilege></li>
					</ul>
				</div>
				
				<!-- 数据表格 -->
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tbody>
						<tr>
							<th>&nbsp;</th>
							<th field='name' sortable='true' width='100'>角色名称</th>
							<th field='description' width='200' align='right'>描述</th>
							<th field='createTimeFormat' width='150' align='center'>创建时间</th>				
						</tr>
						<c:forEach items="${roleList}" var="role" varStatus="row">
						<tr name="roleList">
							<input type="hidden" value="${role.id}"/>
							<td align="center" class="num" width="3%">${(row.index+1)}</td>
							<td field='name' sortable='true' width="10%">${role.roleName}</td>
							<td field='description' align='right' width="70%">${role.roleDesc}</td>
							<td field='createTimeFormat' align='center' width="17%"><fmt:formatDate value="${role.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>				
						</tr>
						</c:forEach>
					</tbody>
				</table>
	          </div>
            <div class="clr"></div> 
        </div>
    </div>
</div>
</body>
</html>
<script type="text/javaScript">
$("tr[name='roleList']").click(function(){
	$("tr[name='roleList']").removeClass();
  	$(this).addClass("bg");
});

function addRole(){
	$.fancybox.open({
          href : '${ctx}/admin_op_purview/addRole.htm',
          type : 'ajax',
          padding : 10
	});
}

function editRole(){
	var roleId=0;
	$("tr[name='roleList']").each(function(){
		if($(this).attr("class")=="bg"){
			roleId=$(this).children("input").val();
		}
	});
	if(roleId==0){
		jAlert("请选择角色！","提示",function(){
        });
        return null;
	}
	$.fancybox.open({
          href : '${ctx}/admin_op_purview/editRole.htm?roleId='+roleId,
          type : 'ajax',
          padding : 10
	});
}

function delRole(){
	var roleId=0;
	$("tr[name='roleList']").each(function(){
		if($(this).attr("class")=="bg"){
			roleId=$(this).children("input").val();
		}
	});
	if(roleId==0){
		jAlert("请选择角色！","提示",function(){
        });
        return null;
	}
	jConfirm("确认删除该角色？","操作提示",function(res){
		if(res){
			$.post('${ctx}/admin_op_purview/delRoleAjax.htm?roleId='+roleId,null,function(d){
				if(d==-1){
					jAlert("请先删除当前角色的下级角色","提示",function(){});
                	return null;
				}
				jAlert("角色删除成功","提示",function(){
					location.href=location.href;
        		});
			});
		}
	});
}


function setMenu(){
	var roleId=0;
	$("tr[name='roleList']").each(function(){
		if($(this).attr("class")=="bg"){
			roleId=$(this).children("input").val();
		}
	});
	if(roleId==0){
		jAlert("请选择角色！","提示",function(){
        });
        return null;
	}
	$.fancybox.open({
          href : '${ctx}/admin_op_purview/setPurview.htm?roleId='+roleId,
          type : 'ajax',
          padding : 10
	});
}
</script>
