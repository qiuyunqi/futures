<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－客服消息－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<c:set var="first" value="1"/>
<c:set var="second" value="2"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">客服消息<!-- <a href="#" class="fr add">添加</a> --></div>
            <div class="form">
            <form id="searchForm" action="${ctx}/admin_list_sms_log/smsLogList.htm" method="post">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
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
				            <div class="lf_font fl">类型：</div>
				            <div class="fl select"><select style="width:178px;" id="type" name="type">
				            	<option value="">全部</option>
				            	<option <c:if test="${type==1}">selected="selected"</c:if> value="1">短信</option>
				            	<option <c:if test="${type==2}">selected="selected"</c:if> value="2">邮箱</option>
				            </select></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">状态：</div>
				            <div class="fl select"><select style="width:178px;" id="state" name="state">
				            	<option value="">全部</option>
				            	<option <c:if test="${state==0}">selected="selected"</c:if> value="0">未发送</option>
				            	<option <c:if test="${state==1}">selected="selected"</c:if> value="1">已发送</option>
				            	<option <c:if test="${state==2}">selected="selected"</c:if> value="2">发送失败</option>
				            </select></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">优先级：</div>
				            <div class="fl select"><select style="width:178px;" id="prio" name="prio">
				            	<option value="">全部</option>
				            	<option <c:if test="${prio==1}">selected="selected"</c:if> value="1">1</option>
				            	<option <c:if test="${prio==2}">selected="selected"</c:if> value="2">2</option>
				            	<option <c:if test="${prio==3}">selected="selected"</c:if> value="3">3</option>
				            </select></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				  </tr>
				  <tr>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">目的地：</div>
				            <div class="fl input"><input id="destination" name="destination" value="${destination}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">消息内容：</div>
				            <div class="fl input"><input id="content" name="content" value="${content}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">发送原因：</div>
				            <div class="fl input"><input id=reason name="reason" value="${reason}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl"></div>
				            <div class="fl"><a href="${ctx}/admin_list_sms_log/smsLogList.htm" onclick="searchInfo();" Class="search">搜索</a><a href="${ctx}/admin_list_sms_log/smsLogList.htm" Class="remove" style="margin-left:30px;">清空</a></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				  </tr>
				</tbody>
				</table>
			</form>
            </div>
          <div class=" yhlb_title">消息列表</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody><tr>
				    <th>&nbsp;</th>
				    <th>消息ID</th>
				    <th>真实姓名</th>
				    <th>目的地</th>
				    <th>原因</th>
				    <th>优先级 </th>
				    <th style="text-align:left;width:50%">内容</th>
				    <th>状态</th>
				    <th>计划/发送时间
				  </th></tr>
				  <c:forEach items="${logList}" var="log" varStatus="row">
				  		<tr onmouseover="$(this).css('background','#f6f6f6');" onmouseout="$(this).css('background','');">
						    <td align="center" class="num">${(row.index+1)+((pageNo-1)*pageSize)}</td>
						    <td style="color:#137490;cursor: pointer;">${log.id}</td>
						    <td style="color:#137490;cursor: pointer;" onclick="searchInfoByUser('${log.fuUser.userName}');">${log.fuUser.userName}</td>
						    <td>${log.destination}</td>
						    <td>${log.reason}</td>
						    <td>${log.prio}</td>
						    <td style="text-align: left;">${log.content}</td>
						    <td>${log.state==0?'未发送':log.state==1?'已发送':'发送失败'}</td>
						    <td><fmt:formatDate value="${log.planTime}" pattern="yy-MM-dd HH:mm:ss"/>/<fmt:formatDate value="${log.sendTime}" pattern="yy-MM-dd HH:mm:ss"/></td>
						 </tr>
				  </c:forEach>
				 <c:if test="${empty logList}">
				  <tr>
					<td colspan="9">
			        	<div class=" empty0"><img src="${ctx}/images_qhht/empty_03.jpg" width="93" height="63"><p class="empty0_font">您暂时还没有相关信息</p></div>
			        </td>
				  </tr>
				  </c:if>
				</tbody>
				</table>
          </div>
           <div class="page">
				<domi:pagination ctx="${ctx}" pageVolume="${pageSize}" url="${ctx}/admin_list_sms_log/smsLogList.htm?totalCount=${totalCount}" totalNum="${totalCount}" curPageNum="${pageNo}" formId="pageForm" >
						<domi:paramTag name="accountName" value="${userName}"/>
						<domi:paramTag name="type" value="${type}"/>
						<domi:paramTag name="state" value="${state}"/>
						<domi:paramTag name="prio" value="${prio}"/>
						<domi:paramTag name="destination" value="${destination}"/>
						<domi:paramTag name="content" value="${content}"/>
						<domi:paramTag name="reason" value="${reason}"/>
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
function searchInfoByUser(userName){
	$("#userName").val(userName);
	searchInfo();
}
</script>
