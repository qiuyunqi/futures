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
<c:set var="first" value="1"/>
<c:set var="second" value="1"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">留言管理<!-- <a href="#" class="fr add">添加</a> --></div>
            <div class="form">
            <form id="searchForm" action="${ctx}/admin_list_message/messageList.htm" method="post">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tbody><tr>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">真实姓名：</div>
				            <div class="fl input"><input id="leaveUser" name="leaveUser" value="${leaveUser}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">类型：</div>
				            <div class="fl select"><select style="width:178px;" id=type name="type">
				            	<option value="">全部</option>
				            	<option <c:if test="${type==1}">selected="selected"</c:if> value="1">App</option>
				            	<option <c:if test="${type==2}">selected="selected"</c:if> value="2">网站</option>
				            	<option <c:if test="${type==3}">selected="selected"</c:if> value="3">MOM</option>
				            	<option <c:if test="${type==4}">selected="selected"</c:if> value="4">投诉建议</option>
				            	<option <c:if test="${type==5}">selected="selected"</c:if> value="5">其他反馈</option>
				            </select></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">内容：</div>
				            <div class="fl input"><input name="content" type="content" value="${content}" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">回复人：</div>
				            <div class="fl input"><input id="replyAdmin" name="replyAdmin" value="${replyAdmin}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				  </tr>
			
				  
				  <tr>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">留言时间：</div>
				            <div class="fl input"><input id="leaveBeginTime" name="leaveBeginTime" value="<fmt:formatDate value="${leaveBeginTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'leaveEndTime\')}', dateFmt:'yyyy-MM-dd'})" type="text" placeholder="" style="width:100px;"><i class="riqi"></i></div>
				            <div class="fl duanxian">—</div>
				            <div class="fl input"><input id="leaveEndTime" name="leaveEndTime" value="<fmt:formatDate value="${leaveEndTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({minDate:'#F{$dp.$D(\'leaveBeginTime\')}', dateFmt:'yyyy-MM-dd'})" type="text" placeholder="" style="width:100px;"><i class="riqi"></i></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td colspan="2">
				        <div class="form_cont">
				            <div class="lf_font fl">回复时间：</div>
				            <div class="fl input"><input id="replyBeginTime" name="replyBeginTime" value="<fmt:formatDate value="${replyBeginTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'replyEndTime\')}', dateFmt:'yyyy-MM-dd'})" type="text" placeholder="" style="width:100px;"><i class="riqi"></i></div>
				            <div class="fl duanxian">—</div>
				            <div class="fl input"><input id="replyEndTime" name="replyEndTime" value="<fmt:formatDate value="${replyEndTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({minDate:'#F{$dp.$D(\'replyBeginTime\')}', dateFmt:'yyyy-MM-dd'})" type="text" placeholder="" style="width:100px;"><i class="riqi"></i></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				   
				    
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl"></div>
				            <div class="fl"><a href="${ctx}/admin_list_message/messageList.htm" onclick="searchInfo();" Class="search">搜索</a><a href="${ctx}/admin_list_message/messageList.htm" Class="remove" style="margin-left:30px;">清空</a></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				  </tr>
				  
				</tbody></table>
			</form>

            </div>
          <div class=" yhlb_title">留言列表</div>
            <div class="yhlb yhlb0">
            
            <table width="100%">
			  <tbody><tr>
			    <th width="2%">&nbsp;</th>
			    <th width="10%">真实姓名/时间</th>
			    <th width="28%">留言内容</th>
			    <th width="28%">回复</th>
			    <th width="11%">备注</th>
			    <th width="10%">回复人/时间 </th>
			    <th style=" text-align:center">操作</th>
			    </tr>
			  <c:forEach items="${messageList}" var="message" varStatus="row">
			  		<tr style="cursor: pointer;" onmouseover="$(this).css('background','#f6f6f6');" onmouseout="$(this).css('background','');">
				    <td class="num">${(row.index+1)+((pageNo-1)*pageSize)}</td>
				    <td onclick="searchInfoByUser('${message.fuUser.userName}');"><div class=" fenlei"><p style="color:#137490">${message.fuUser.userName}</p><p><fmt:formatDate value="${message.time}" pattern="yy-MM-dd HH:mm:ss"/></p></div></td>
				    <td><div class=" fenlei"><p>类型：${message.type==1?'App':message.type==2?'网站':message.type==3?'解套者联盟':message.type==4?'投诉建议':'其他反馈'}</p><p class="zdhh">${message.content}</p></div></td>
				    <td class="zdhh0">${message.replyContent}</td>
				    <td>${message.replyMark}</td>
				    <td><div class=" fenlei"><p>${message.fuAdmin.name}</p><p><fmt:formatDate value="${message.replyTime}" pattern="yy-MM-dd HH:mm:ss"/></p></div></td>
					 <td style="text-align:center">
					 <div class="caozuo">
						 <domi:privilege url="/admin_op_message/messageReplyAjax.htm">
						 	<a href="javascript:void(0);" onclick="messageInfo(${message.id});">回复留言</a>
						 </domi:privilege>
					 </div>
					 </td>
				  </tr>
			  		
			  </c:forEach>
			  <c:if test="${empty messageList}">
				  <tr>
					<td colspan="7">
			        	<div class=" empty0"><img src="${ctx}/images_qhht/empty_03.jpg" width="93" height="63"><p class="empty0_font">您暂时还没有相关信息</p></div>
			        </td>
				  </tr>
			  </c:if>
			</tbody>
		</table>

          </div>
           <div class="page">
				<domi:pagination ctx="${ctx}" pageVolume="${pageSize}" url="${ctx}/admin_list_message/messageList.htm?totalCount=${totalCount}" totalNum="${totalCount}" curPageNum="${pageNo}" formId="pageForm" >
						<domi:paramTag name="leaveUser" value="${leaveUser}"/>
						<domi:paramTag name="type" value="${type}"/>
						<domi:paramTag name="content" value="${content}"/>
						<domi:paramTag name="replyAdmin" value="${replyAdmin}"/>
						<domi:paramTag name="leaveBeginTime" value="${leaveBeginTime}"/>
						<domi:paramTag name="leaveEndTime" value="${leaveEndTime}"/>
						<domi:paramTag name="replyBeginTime" value="${replyBeginTime}"/>
						<domi:paramTag name="replyEndTime"  value="${replyEndTime}"/>
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
function messageInfo(id){
	$.fancybox.open({
        href : '${ctx}/admin_op_message/messageReplyAjax.htm?id='+id,
        type : 'ajax',
        padding : 10
	});
}
function searchInfoByUser(leaveUser){
	$("#leaveUser").val(leaveUser);
	searchInfo();
}
</script>
