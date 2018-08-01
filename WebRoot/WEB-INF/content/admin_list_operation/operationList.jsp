<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－不良信用记录－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
<style>
	.remark{
			width:200px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }
    .hidden{position: absolute;z-index: 99;background: #99CCFF;text-align:left;padding:2px;}
</style>
</head>
<body style=" background:#fff">
<c:set var="first" value="3"/>
<c:set var="second" value="7"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">管理员操作记录</div>
            <div class="form">
            <form id="searchForm" action="${ctx}/admin_list_operation/operationList.htm" method="post">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tbody><tr>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">主模块：</div>
				            <div class="fl input"><input id="modelMain" name="modelMain" value="${modelMain}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">子模块：</div>
				            <div class="fl input"><input id="modelSub" name="modelSub" value="${modelSub}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">操作ID：</div>
				            <div class="fl input"><input id="opId" name="opId" value="${opId}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				  </tr>
				  <tr>
				  	<td>
				        <div class="form_cont">
				            <div class="lf_font fl">操作类型：</div>
				            <div class="fl select"><select style="width:178px;" id="operation" name="operation">
				            	<option value="">全部</option>
				            	<option value="1">新增</option>
				            	<option value="2">修改</option>
				            	<option value="3">删除</option>
				            </select></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">时间：</div>
				            <div class="fl input"><input id="time1" name="time1" value="<fmt:formatDate value="${time1}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'time2\')}', dateFmt:'yyyy-MM-dd'})" type="text" placeholder="" style="width:100px;"><i class="riqi"></i></div>
				            <div class="fl duanxian">—</div>
				            <div class="fl input"><input id="time2" name="time2" value="<fmt:formatDate value="${time2}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({minDate:'#F{$dp.$D(\'time1\')}', dateFmt:'yyyy-MM-dd'})" type="text" placeholder="" style="width:100px;"><i class="riqi"></i></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl"></div>
				            <div class="fl"><a href="javascript:void(0);" onclick="searchInfo();" class="search">搜索</a><a href="${ctx}/admin_list_operation/operationList.htm" class="remove" style="margin-left:30px;">清空</a></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				  </tr>
				</tbody></table>
			</form>
            </div>
          <div class=" yhlb_title">管理员操作记录列表</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody>
				  <tr>
				    <th>&nbsp;</th>
				    <th>主模块</th>
				    <th>子模块</th>
				    <th>操作类型</th>
				    <th>操作ID</th>
				    <th width="200">修改前内容</th>
				    <th width="200">修改后内容</th>
				    <th>操作人</th>
				    <th>操作时间 </th>
				  </tr>
				  <c:forEach items="${operationList}" var="operation" varStatus="row">
				  		<tr style="cursor: pointer;" onmouseover="$(this).css('background','#f6f6f6');" onmouseout="$(this).css('background','');">
						    <td align="center" class="num">${(row.index+1)+((pageNo-1)*pageSize)}</td>
						    <td>${operation.modelMain}</td>
						    <td>${operation.modelSub}</td>
						    <td>${operation.operation==1?'新增':operation.operation==2?'修改':'删除'}</td>
						    <td>${operation.opId}</td>
						    <td width="200" class="tdhgt">
						    	<ul><li class="remark">${operation.beforeContent}</li><li class="hidden">${operation.beforeContent}</li></ul>
						    </td>
						    <td width="200" class="tdhgt">
						    	<ul><li class="remark"> ${operation.afterContent}</li><li class="hidden"> ${operation.afterContent}</li></ul>
						    </td>
						    <td>${operation.fuAdmin.name}</td>
						    <td><fmt:formatDate value="${operation.updateTime}" pattern="yy-MM-dd HH:mm:ss"/></td>
						 </tr>
				  </c:forEach>
				 <c:if test="${empty operationList}">
				  <tr>
					<td colspan="9">
			        	<div class=" empty0"><img src="${ctx}/images_qhht/empty_03.jpg" width="93" height="63"><p class="empty0_font">您暂时还没有相关信息</p></div>
			        </td>
				  </tr>
				  </c:if>
				</tbody></table>
          </div>
           <div class="page">
				<domi:pagination ctx="${ctx}" pageVolume="${pageSize}" url="${ctx}/admin_list_operation/operationList.htm?totalCount=${totalCount}" totalNum="${totalCount}" curPageNum="${pageNo}" formId="pageForm" >
						<domi:paramTag name="modelMain" value="${modelMain}"/>
						<domi:paramTag name="modelSub" value="${modelSub}"/>
						<domi:paramTag name="opId" value="${opId}"/>
						<domi:paramTag name="operation" value="${operation}"/>
						<domi:paramTag name="time1" value="${time1}"/>
						<domi:paramTag name="time2" value="${time2}"/>
				</domi:pagination>
			</div>
               <div class="clr"></div> 
        </div>
    </div>
</div>
</body>
</html>
<script>
$(".hidden").hide();
$(document).ready(function(){
    $(".remark").mouseover(function(){
    		$(this).next().show();
    })
    $(".remark").mouseout(function(){
        $(".hidden").hide();
    })
});

function searchInfo(){
	if(isNaN($("#opId").val())){
		sureInfo("确定","输入操作ID必须为数字","提示");
		return false;
	}
	$('#searchForm').submit();
}
</script>
