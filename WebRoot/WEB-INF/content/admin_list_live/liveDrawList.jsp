<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－直播抽奖列表</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">直播抽奖<domi:privilege url="/admin_op_live/updateLiveDraw.htm"><a href="javascript:void(0);" class="fr add" onclick="addLiveDraw('')">添加</a></domi:privilege></div>
            <div class="form">
            <form id="searchForm" action="${ctx}/admin_list_live/liveDrawList.htm" method="post">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tbody><tr>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">创建时间：</div>
				            <div class="fl input"><input id="time1" name="time1" value="<fmt:formatDate value="${time1}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'time2\')}', dateFmt:'yyyy-MM-dd'})" type="text" placeholder="" style="width:100px;"><i class="riqi"></i></div>
				            <div class="fl duanxian">—</div>
				            <div class="fl input"><input id="time2" name="time2" value="<fmt:formatDate value="${time2}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({minDate:'#F{$dp.$D(\'time1\')}', dateFmt:'yyyy-MM-dd'})" type="text" placeholder="" style="width:100px;"><i class="riqi"></i></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">创建人：</div>
				            <div class="fl input"><input id="createAdmin" name="createAdmin" value="${createAdmin}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl"></div>
				            <div class="fl"><a href="javascript:void(0);" style="margin-left:30px;" onclick="searchInfo();" class="search">搜索</a><a href="${ctx}/admin_list_live/liveDrawList.htm" class="remove" style="margin-left:30px;">清空</a></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				  </tr>
				</tbody></table>
			</form>
            </div>
          <div class=" yhlb_title">直播抽奖列表</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody><tr>
				    <th>&nbsp;</th>
				    <th>今日问题</th>
				    <th>昨日答案</th>
				    <th>竞猜开始时间</th>
				    <th>竞猜结束时间</th>
				    <th>创建人</th>
				    <th>创建时间</th>
				    <th>是否公布</th>
				    <th>公布时间</th>
				    <th style="text-align:center">操作</th>
				    </tr>
				  <c:forEach items="${list}" var="draw" varStatus="row">
				  		<tr style="cursor: pointer;" onmouseover="$(this).css('background','#f6f6f6');" onmouseout="$(this).css('background','');">
						    <td align="center" class="num">${(row.index+1)+((pageNo-1)*pageSize)}</td>
						    <td>${draw.question}</td>
						    <td><fmt:formatNumber value="${empty draw.answer?'':draw.answer}" pattern="#,###,##0.00"/></td>
						    <td><fmt:formatDate value="${draw.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						    <td><fmt:formatDate value="${draw.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						    <td>${draw.createAdmin.name}</td>
						    <td><fmt:formatDate value="${draw.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						    <td>${draw.isPublish==0?'未公布':'已公布'}</td>
						    <td><fmt:formatDate value="${draw.publishTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						 	<td style="text-align:center"><div class="caozuo">
						 	<domi:privilege url="/admin_op_live/updateLiveDraw.htm">
						 	<a href="javascript:void(0);" onclick="addLiveDraw('${draw.id}')">修改</a>
						 	</domi:privilege>
						 	<c:if test="${draw.isPublish==0}">
						 	<domi:privilege url="/admin_op_live/publishAnswer.htm">
						 	<a href="javascript:void(0);" onclick="publishAnswer('${draw.id}')">公布答案</a>
						 	</domi:privilege>
						 	</c:if>
						 	</div></td>
						 </tr>
				  </c:forEach>
				  <c:if test="${empty list}">
				  <tr>
					<td colspan="10">
			        	<div class=" empty0"><img src="${ctx}/images_qhht/empty_03.jpg" width="93" height="63"><p class="empty0_font">您暂时还没有相关信息</p></div>
			        </td>
				  </tr>
				  </c:if>
				</tbody></table>
          </div>
           <div class="page">
				<domi:pagination ctx="${ctx}" pageVolume="${pageSize}" url="${ctx}/admin_list_live/liveDrawList.htm?totalCount=${totalCount}" totalNum="${totalCount}" curPageNum="${pageNo}" formId="pageForm" >
						<domi:paramTag name="time" value="${time1}"/>
						<domi:paramTag name="time" value="${time2}"/>
						<domi:paramTag name="createAdmin" value="${createAdmin}"/>
				</domi:pagination>
			</div>
               <div class="clr"></div> 
        </div>
    </div>
</div>
</body>
</html>
<script>
function addLiveDraw(id){
	$.fancybox.open({
        href : "${ctx}/admin_op_live/updateLiveDraw.htm?id="+id,
        type : 'ajax',
        padding : 10
	});
}
function searchInfo(){
	$('#searchForm').submit();
}
function publishAnswer(id){
	$.fancybox.open({
        href : '${ctx}/admin_op_live/publishAnswer.htm?id='+id,
        type : 'ajax',
        padding : 10
	});
}
</script>
