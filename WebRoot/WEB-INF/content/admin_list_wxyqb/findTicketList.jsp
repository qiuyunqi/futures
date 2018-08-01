<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title} -余劵宝找劵列表</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">找劵列表<%-- <domi:privilege url="/admin_op_wqq_contents/addPublishAjax.htm"><a href="javascript:void(0);" class="fr add" onclick="addPublish('')">添加</a></domi:privilege> --%></div>
            <div class="form">
            <form id="searchForm" action="${ctx}/admin_list_wxyqb/findTicketList.htm" method="post">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tbody><tr>
				  <td>
				        <div class="form_cont">
				            <div class="lf_font fl">用户名：</div>
				            <div class="fl input"><input id="userName" name="userName" value="${userName}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">标题：</div>
				            <div class="fl input"><input id="titles" name="titles" value="${titles}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				  	<td>
				        <div class="form_cont">
				            <div class="lf_font fl">描述：</div>
				            <div class="fl input"><input id="description" name="description" value="${description}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">是否删除：</div>
				            <div class="fl select"><select style="width:178px;" id="isDel" name="isDel">
				            <option value="">全部</option>
				            	<option <c:if test="${isDel==0}">selected="selected"</c:if> value="0">已删除</option>
				            	<option <c:if test="${isDel==1}">selected="selected"</c:if> value="1">未删除</option>
				            </select></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl"></div>
				            <div class="fl"><a href="javascript:void(0);" style="margin-left:30px;" onclick="searchInfo();" class="search">搜索</a><a href="${ctx}/admin_list_wxyqb/findTicketList.htm" class="remove" style="margin-left:30px;">清空</a></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				  </tr>
				</tbody></table>
			</form>
            </div>
          <div class=" yhlb_title">找劵列表</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody><tr>
				    <th>&nbsp;</th>
				    <th>姓名</th>
				    <th>标题</th>
				    <th>描述</th>
				    <th>是否删除</th>
				    <th>发布时间</th>
				    <th style="text-align:center">操作</th>
				    </tr>
				  <c:forEach items="${list}" var="publish" varStatus="row">
				  		<tr style="cursor: pointer;" onmouseover="$(this).css('background','#f6f6f6');" onmouseout="$(this).css('background','');">
						    <td align="center" class="num">${(row.index+1)+((pageNo-1)*pageSize)}</td>
						    <td>${publish.fuUser.userName}</td>
						    <td>${empty publish.title?'':publish.title}</td>
						    <td>${empty publish.description?'':publish.description}</td>
						    <td>${publish.isDel==0?'已删除':'未删除'}</td>
						    <td><fmt:formatDate value="${publish.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						 	<td style="text-align:center">
						 	<div class="caozuo">
						 	<domi:privilege url="/admin_op_wqq_contents/addPublishAjax.htm">
						 	<a href="javascript:void(0);" onclick="addPublish('${publish.id}')">修改</a>
						 	</domi:privilege>
						 	</div>
						 	</td>
						 </tr>
				  </c:forEach>
				  <c:if test="${empty list}">
				  <tr>
					<td colspan="7">
			        	<div class=" empty0"><img src="${ctx}/images_qhht/empty_03.jpg" width="93" height="63"><p class="empty0_font">您暂时还没有相关信息</p></div>
			        </td>
				  </tr>
				  </c:if>
				</tbody></table>
          </div>
           <div class="page">
				<domi:pagination ctx="${ctx}" pageVolume="${pageSize}" url="${ctx}/admin_list_wxyqb/findTicketList.htm?totalCount=${totalCount}" totalNum="${totalCount}" curPageNum="${pageNo}" formId="pageForm" >
						<domi:paramTag name="titles" value="${titles}"/>
						<domi:paramTag name="description" value="${description}"/>
						<domi:paramTag name="userName" value="${userName}"/>
						<domi:paramTag name="isDel" value="${isDel}"/>
				</domi:pagination>
			</div>
               <div class="clr"></div> 
        </div>
    </div>
</div>
</body>
</html>
<script>
function addPublish(id){
	$.fancybox.open({
        href : "${ctx}/admin_op_wxyqb/addPublishAjax.htm?id="+id,
        type : 'ajax',
        padding : 10
	});
}
function searchInfo(){
	$('#searchForm').submit();
}
</script>
