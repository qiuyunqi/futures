<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－后台登录日志－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
<style type="text/css">
	body{background: #efefef;}
	.pay{border:1px solid #2db1e1;border-radius:20px;padding:2px 15px;background:#2db1e1;color:#fff;font-size:11px;margin-left: 10px;}
</style>
</head>
<body style=" background:#fff">
<c:set var="first" value="8"/>
<c:set var="second" value="2"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	<div class="rt_cont_title">
    	                    后台登录日志
    	    </div>
    	  <div class="form">
	            <form id="searchForm" action="${ctx}/admin_login_log/logList.htm" method="post">
	            	<table width="70%" border="0" cellspacing="0" cellpadding="0">
					  <tbody>
						  <tr>
						    <td>
						        <div class="form_cont">
						            <div class="lf_font fl">后台用户ID：</div>
						            <div class="fl input"><input id="" name="userId" value="${userId}" type="text" placeholder=""></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						    <td>
						        <div class="form_cont">
						            <div class="lf_font fl">日志时间：</div>
						            <div class="fl input"><input name="time1" type="text" value="<fmt:formatDate value='${time1}' pattern='yyyy-MM-dd'/>" placeholder="" style="width:80px;" onclick="WdatePicker({dateFmt : 'yyyy-MM-dd'});"><i class="riqi" ></i></div>
						            <div class="fl duanxian">—</div>
						            <div class="fl input"><input name="time2" type="text" value="<fmt:formatDate value='${time2}' pattern='yyyy-MM-dd'/>" placeholder="" style="width:80px;" onclick="WdatePicker({dateFmt : 'yyyy-MM-dd'});"><i class="riqi" ></i></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						    <td>
						        <div class="form_cont">
						            <div class="lf_font fl">是否登出：</div>
						            <div class="fl checkbox"><span class="xuankang"><input name="logType" <c:if test="${logType==1}">checked="checked"</c:if> type="checkbox" value="1" style="width:13px;" id="logType"></span></div>
						        </div>
						        <div class="clr"></div>
						    </td>
						    <td>
						    	<div class="form_cont">
						            <div class="lf_font fl"></div>
						            <div class="fl"><a href="javascript:void(0);" onclick="queryLog();" class="search">查询</a><a href="${ctx}/admin_login_log/logList.htm" class="remove" style="margin-left:30px;">清空</a></div>
						            <div class="clr"></div>
						        </div>
						    </td>
						  </tr>
					</tbody></table>
				</form>
            </div>  
    	    
          <div class=" yhlb_title">后台登录日志列表</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody>
				  <tr>
				    <th>&nbsp;</th>
				    <th>后台用户ID</th>
				    <th>后台用户账号</th>
				    <th>操作类型</th>
				    <th>日志时间</th>
				  </tr>
				  <c:forEach items="${adminLoginLogList}" var="log" varStatus="row">
				  	  <tr style="cursor:pointer;" onmouseover="$(this).attr('bgcolor','#f6f6f6');" onmouseout="$(this).attr('bgcolor','');" >
					    <td align="center" class="num">${row.index+1}</td>
   					    <td>${log.fuAdmin.id}</td>
   					    <td>${log.fuAdmin.account}</td>
					    <td>${log.logType==0?'登录':log.logType==1?'登出':''}</td>
					    <td><fmt:formatDate value="${log.logTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					  </tr>
				  </c:forEach>
				</tbody>
				</table>
          </div>
           <c:if test="${empty adminLoginLogList}">
				  <div style="text-align:center;">暂无内容！</div>
		  </c:if>
		  <div class="page">
			<domi:pagination ctx="${ctx}" pageVolume="${pageSize}"
				url="${ctx}/admin_login_log/logList.htm?totalCount=${totalCount}"
				totalNum="${totalCount}" curPageNum="${pageNo}"
				formId="pageForm">
		        <domi:paramTag name="userId" value="${userId}"/>
			</domi:pagination>
		</div>
        </div>
    </div>
</div>
</body>
</html>
<script>
function queryLog(){
	$('#searchForm').submit();
}
</script>
