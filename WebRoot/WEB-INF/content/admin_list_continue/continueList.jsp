<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－续约记录－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<c:set var="first" value="3"/>
<c:set var="second" value="6"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">续约记录<!-- <a href="#" class="fr add">添加</a> --></div>
            <div class="form">
            <form id="searchForm" action="${ctx}/admin_list_continue/continueList.htm" method="post">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tbody><tr>
				  	
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">续约ID：</div>
				            <div class="fl input"><input id="detailId" name="detailId" value="${detailId}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">用户名：</div>
				            <div class="fl input"><input id="accountName" name="accountName" value="${accountName}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">真实姓名：</div>
				            <div class="fl input"><input id="userName" name="userName" value="${userName}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">方案ID：</div>
				            <div class="fl input"><input id="programId" name="programId" value="${programId}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				  </tr>
				  <tr>
				  	<td>
				        <div class="form_cont">
				            <div class="lf_font fl">续约周期：</div>
				            <div class="fl input"><input id="cycle1" name="cycle1" value="${cycle1}" type="text" placeholder="" style="width:65px;"></div>
				            <div class="fl duanxian">—</div>
				            <div class="fl input"><input id="cycle2" name="cycle2" value="${cycle2}" type="text" placeholder="" style="width:65px;"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				    	<div class="form_cont">
				            <div class="lf_font fl">续约结果：</div>
				            <div class="fl select"><select style="width:178px;" id="result" name="result">
				            	<option value="">全部</option>
				            	<option <c:if test="${result==0}">selected="selected"</c:if> value="0">待审核</option>
				            	<option <c:if test="${result==1}">selected="selected"</c:if> value="1">审核中</option>
				            	<option <c:if test="${result==2}">selected="selected"</c:if> value="2">同意</option>
				            	<option <c:if test="${result==3}">selected="selected"</c:if> value="3">拒绝</option>
				            </select></div>
				            <div class="clr"></div>
				        </div>
				        
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">申请时间：</div>
				            <div class="fl input"><input id="time1" name="time1" value="${time1}" value="<fmt:formatDate value="${time1}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'time2\')}', dateFmt:'yyyy-MM-dd'})" type="text" placeholder="" style="width:100px;"><i class="riqi"></i></div>
				            <div class="fl duanxian">—</div>
				            <div class="fl input"><input id="time2" name="time2" value="${time2}" value="<fmt:formatDate value="${time2}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({minDate:'#F{$dp.$D(\'time1\')}', dateFmt:'yyyy-MM-dd'})" type="text" placeholder="" style="width:100px;"><i class="riqi"></i></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl"></div>
				            <div class="fl"><a href="javascript:void(0);" onclick="searchInfo();" class="search">搜索</a><a href="${ctx}/admin_list_continue/continueList.htm" class="remove" style="margin-left:30px;">清空</a></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				  </tr>
				</tbody></table>
			</form>

            </div>
          <div class=" yhlb_title">续约记录列表</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody><tr>
				    <th>&nbsp;</th>
				    <th>续约ID</th>
				    <th>用户名</th>
				    <th>真实姓名</th>
				    <th>方案ID</th>
				    <th>众期账号</th>
				    <th>续约周期</th>
				    <th>续约结果</th>
				    <th>审核人</th>
				    <th width="20%">审核说明</th>
				    <th>申请时间/审核时间</th>
				    <th style=" text-align:center">操作</th>
				    </tr>
				  <c:forEach items="${continueList}" var="con" varStatus="row">
				  		<tr style="cursor: pointer;" onmouseover="$(this).css('background','#f6f6f6');" onmouseout="$(this).css('background','');">
						    <td align="center" class="num">${(row.index+1)+((pageNo-1)*pageSize)}</td>
						    <td style="color:#137490"<domi:privilege url="/admin_op_continue/checkContinueAjax.htm"><c:if test="${con.result<1}">onclick="checkInfo(${con.id});"</c:if></domi:privilege> >${con.id}</td>
						    <td>${con.fuUser.accountName}</td>
						    <td style="color:#137490;cursor:pointer;" onclick="searchInfoByUser('${con.fuUser.userName}');">${con.fuUser.userName}</td>
						    <td style="color:#137490;cursor:pointer;" onclick="window.location='${ctx}/admin_op_program/programListDetail.htm?id=${con.fuProgram.id}'">${con.fuProgram.id}</td>
						    <td>${con.fuProgram.fuServer.id}_${con.fuProgram.tradeAccount}</td>
						    <td>${con.cycle}${con.fuProgram.programType==1?'天':'个月'}</td>
						    <td>${con.result==0?'待审核':con.result==1?'审核中':con.result==2?'同意':'拒绝'}</td>
						    <td>${empty con.fuAdmin?'----':con.fuAdmin.name}</td>
						    <td>${con.comment}</td>
						    <td><fmt:formatDate value="${con.time}" pattern="yy-MM-dd HH:mm:ss"/>/<fmt:formatDate value="${con.checkTime}" pattern="yy-MM-dd HH:mm:ss"/>${empty con.checkTime?'----':''}</td>
						 	<domi:privilege url="/admin_op_continue/checkContinueAjax.htm">
						 	<td style="text-align:center">
						 	<div class=" caozuo">
						 	<c:if test="${con.result==0}"><a href="javascript:void(0);" onclick="checkInfo(${con.id});">审核</a></c:if>
						 	<c:if test="${con.result==1&&admin==con.fuAdmin}"><a href="javascript:void(0);" onclick="checkInfo(${con.id});">审核</a></c:if>
						 	</div>
						 	</td>
						 	</domi:privilege>
						 </tr>
				  </c:forEach>
				  <c:if test="${empty continueList}">
				  <tr>
					<td colspan="12">
			        	<div class=" empty0"><img src="${ctx}/images_qhht/empty_03.jpg" width="93" height="63"><p class="empty0_font">您暂时还没有相关信息</p></div>
			        </td>
				  </tr>
				  </c:if>
				  
				</tbody></table>

          </div>
            
           <div class="page">
				<domi:pagination ctx="${ctx}" pageVolume="${pageSize}" url="${ctx}/admin_list_continue/continueList.htm?totalCount=${totalCount}" totalNum="${totalCount}" curPageNum="${pageNo}" formId="pageForm" >
						<domi:paramTag name="detailId" value="${detailId}"/>
						<domi:paramTag name="accountName" value="${accountName}"/>
						<domi:paramTag name="userName" value="${userName}"/>
						<domi:paramTag name="programId" value="${programId}"/>
						<domi:paramTag name="result" value="${result}"/>
						<domi:paramTag name="cycle1" value="${cycle1}"/>
						<domi:paramTag name="cycle2" value="${cycle2}"/>
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
function searchInfo(){
	if(isNaN($("#detailId").val())){
		sureInfo("确定","输入续约ID必须为数字","提示");
		return false;
	}
	if(isNaN($("#programId").val())){
		sureInfo("确定","输入方案ID必须为数字","提示");
		return false;
	}
	if(isNaN($("#cycle1").val())||isNaN($("#cycle2").val())){
		sureInfo("确定","输入续约周期必须为数字","提示");
		return false;
	}
	$('#searchForm').submit();
}
function checkInfo(id){
	$.fancybox.open({
        href : '${ctx}/admin_op_continue/checkContinueAjax.htm?id='+id,
        type : 'ajax',
        padding : 10
	});
}
function searchInfoByUser(userName){
	$("#userName").val(userName);
	searchInfo();
}
</script>
