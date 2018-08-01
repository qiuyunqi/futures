<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－菜单管理</title>
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
<c:set var="second" value="2"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">菜单管理
    	    <domi:privilege url="/admin_op_purview/editPurviewAjax.htm">
    	    <a href="javascript:void(0);" onclick="addMenu('','');" class="fr add">添加主菜单</a>
    	    </domi:privilege>
    	    </div>     
    	    <div class="form">
            <form id="searchForm" action="${ctx}/admin_op_purview/purviewList.htm" method="post">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tbody><tr>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">菜单URL：</div>
				            <div class="fl input"><input id="url" name="url" value="${url}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">父级菜单：</div>
				            <div class="fl select"><select style="width:178px;" id=parentId name="parentId">
				            	<option value="">全部</option>
				            	<c:forEach items="${list}" var="purview">
				            	<option <c:if test="${parentId==purview.id}">selected="selected"</c:if> value="${purview.id}">${purview.name}</option>
				            	</c:forEach>
				            </select></div>
				            <div class="clr"></div>
				        </div>
				   </td>
				  	<td>
				        <div class="form_cont">
				            <div class="lf_font fl"></div>
				            <div class="fl"><a href="javascript:void(0);" onclick="searchInfo();" class="search">搜索</a><a href="${ctx}/admin_op_purview/purviewList.htm" class="remove" style="margin-left:30px;">清空</a></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				  </tr>
				</tbody>
				</table>
			</form>
            </div>
            <div class=" yhlb_title">菜单管理</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody><tr>
				    <th>&nbsp;</th>
				    <th>菜单ID</th>
				    <th>菜单名称</th>
				    <th>上级菜单</th>
				    <th>菜单路径</th>
				    <th>菜单备注</th>
				    <th>创建人</th>
				    <th>创建时间</th>
				    <th>修改人</th>
				    <th>修改时间</th>
				    <th style="text-align:center">操作</th>
				  </tr>
				  <c:forEach items="${purviewList}" var="purview" varStatus="row">
				  <tr style="cursor:pointer;" onmouseover="$(this).attr('bgcolor','#f6f6f6');" onmouseout="$(this).attr('bgcolor','');">
				    <td class="num">${row.index+1}</td>
				    <td>${purview.id}</td>
				    <td>${purview.name}</td>
				    <td>${purview.parentId}</td>
				    <td>${purview.url}</td>
				    <td>${purview.remark}</td>
				    <td>${purview.createAdmin.name}</td>
				    <td><fmt:formatDate value="${purview.createTime}" pattern="yy-MM-dd HH:mm:ss"/></td>
				    <td>${purview.updateAdmin.name}</td>
				    <td><fmt:formatDate value="${purview.updateTime}" pattern="yy-MM-dd HH:mm:ss"/></td>
				    <td style="text-align:center">
				    <div class=" caozuo">
				    <domi:privilege url="/admin_op_purview/editPurviewAjax.htm">
				    <a href="javascript:void(0);" onclick="addMenu('',${purview.id});">添加</a>
				    <span>|</span>
				    <a href="javascript:void(0);" onclick="addMenu(${purview.id},'');">修改</a>
				    <span>|</span>
				    </domi:privilege>
				    <domi:privilege url="/admin_op_purview/delPurview.htm">
				    <a href="javascript:void(0);" onclick="delMenu(${purview.id});">删除</a>
				    </domi:privilege>
				    </div>
				    </td>
				  </tr>
				  </c:forEach>
				   <c:if test="${empty purviewList}">
				  <tr >
					<td colspan="11">
			        	<div class=" empty0"><img src="../images_qhht/empty_03.jpg" width="93" height="63"><p class="empty0_font">您暂时还没有相关信息</p></div>
			        </td>
			      </tr>
			      </c:if>
				</tbody>
				</table>
          	</div>
          	<div class="page">
				<domi:pagination ctx="${ctx}" pageVolume="${pageSize}" url="${ctx}/admin_op_purview/purviewList.htm?totalCount=${totalCount}" totalNum="${totalCount}" curPageNum="${pageNo}" formId="pageForm">
			        <domi:paramTag name="url" value="${url}"/>
			        <domi:paramTag name="parentId" value="${parentId}"/>
				</domi:pagination>
			</div>
            <div class="clr"></div> 
        </div>
    </div>
</div>
</body>
</html>
<script type="text/javaScript">
function addMenu(id,pid){
	$.fancybox.open({
          href : "${ctx}/admin_op_purview/editPurviewAjax.htm?id="+id+"&parentId="+pid,
          type : 'ajax',
          padding : 10
	});
}

function delMenu(id){
	jConfirm("您确定要删除吗？","删除提示",function(res){
		if(res){
			$.post("${ctx}/admin_op_purview/delPurview.htm?id="+id,null,function(d){
				if(d==-1){
					jAlert("请先删除当前菜单的下级菜单","提示",function(){});
                	return null;
				}
				jAlert("删除菜单成功！","提示",function(){
	  				location.href = location.href;
	            });
			});
		}
	});
}
function searchInfo(){
	$('#searchForm').submit();
}
</script>
