<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－待利润提取－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<c:set var="first" value="3"/>
<c:set var="second" value="4"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">待利润提取<!-- <a href="#" class="fr add">添加</a> --></div>
            <div class="form">
            <form id="searchForm" action="${ctx}/admin_list_draw_profits/drawProfitsList.htm" method="post">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tbody><tr>
				  	
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">明细ID：</div>
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
				            <div class="lf_font fl">方案ID：</div>
				            <div class="fl input"><input id="programId" name="programId" value="${programId}" type="text" placeholder=""></div>
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
				  </tr>
				  <tr>
				  	<td>
				        <div class="form_cont">
				            <div class="lf_font fl">状态：</div>
				            <div class="fl select"><select style="width:178px;" id="state" name="state">
				            	<option value="">全部</option>
				            	<option <c:if test="${state==0}">selected="selected"</c:if> value="0">待审批</option>
				            	<option <c:if test="${state==1}">selected="selected"</c:if> value="1">审核中</option>
				            	<option <c:if test="${state==2}">selected="selected"</c:if> value="2">同意</option>
				            	<option <c:if test="${state==3}">selected="selected"</c:if> value="3">拒绝</option>
				            </select></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">金额：</div>
				            <div class="fl input"><input id="money1" name="money1" value="${money1}" type="text" placeholder="" style="width:65px;"></div>
				            <div class="fl duanxian">—</div>
				            <div class="fl input"><input id="money2" name="money2" value="${money2}" type="text" placeholder="" style="width:65px;"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">时间：</div>
				            <div class="fl input"><input id="time1" name="time1" value="${time1}" value="<fmt:formatDate value="${time1}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'time2\')}', dateFmt:'yyyy-MM-dd'})" type="text" placeholder="" style="width:100px;"><i class="riqi"></i></div>
				            <div class="fl duanxian">—</div>
				            <div class="fl input"><input id="time2" name="time2" value="${time2}" value="<fmt:formatDate value="${time2}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({minDate:'#F{$dp.$D(\'time1\')}', dateFmt:'yyyy-MM-dd'})" type="text" placeholder="" style="width:100px;"><i class="riqi"></i></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl"></div>
				            <div class="fl"><a href="javascript:void(0);" onclick="searchInfo();" class="search">搜索</a><a href="${ctx}/admin_list_draw_profits/drawProfitsList.htm" class="remove" style="margin-left:30px;">清空</a></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				  </tr>
				</tbody></table>
			</form>
            </div>
          <div class=" yhlb_title">待利润提取列表</div>
            <div class="yhlb">
            	 <table width="100%">
				  <tbody><tr>
				    <th>&nbsp;</th>
				    <th>明细ID</th>
				    <th>用户名</th>
				    <th>真实姓名</th>
				    <th>方案ID</th>
				    <th>众期账号</th>
				    <th>类型</th>
				    <th>金额 </th>
				    <th>备注</th>
				    <th>状态</th>
				    <th>审核人</th>
				    <th width="20%">审核备注</th>
				    <th>提取时间/审核时间</th>
				    <th style=" text-align:center">操作</th>
				    </tr>
				  <c:forEach items="${drawList}" var="draw" varStatus="row">
				  		<tr style="cursor:pointer;" onmouseover="$(this).css('background','#f6f6f6');" onmouseout="$(this).css('background','');">
						    <td align="center" class="num">${(row.index+1)+((pageNo-1)*pageSize)}</td>
						    <td style="color:#137490" <c:if test="${draw.state!=1}">onclick="checkInfo(${draw.id});"</c:if> >${draw.id}</td>
						    <td>${draw.fuUser.accountName}</td>
						    <td style="color:#137490;cursor:pointer;" onclick="searchInfoByUser('${draw.fuUser.userName}');">${draw.fuUser.userName}</td>
						    <td style="color:#137490;cursor:pointer;" onclick="window.location='${ctx}/admin_op_program/programListDetail.htm?id=${draw.fuProgram.id}'">${draw.fuProgram.id}</td>
						    <td>${draw.fuProgram.fuServer.id}_${draw.fuProgram.tradeAccount}</td>
						    <td>${draw.fuProgram.programType==1?'短线高手':'趋势之王'}</td>
						    <td><fmt:formatNumber value="${empty draw.money?0:draw.money}" pattern="#,###,##0.00"/></td>
						    <td>${draw.remark}</td>
						    <td>${draw.state==0?'待审核':draw.state==1?'审核中':draw.state==2?'同意':'拒绝'}</td>
						    <td>${empty draw.fuAdmin?'----':draw.fuAdmin.name}</td>
						    <td>${draw.comment}</td>
						    <td><fmt:formatDate value="${draw.time}" pattern="yy-MM-dd HH:mm:ss"/>/<fmt:formatDate value="${draw.checkTime}" pattern="yy-MM-dd HH:mm:ss"/>${empty draw.checkTime?'----':''}</td>
						 	<td style="text-align:center">
						 	<div class=" caozuo">
						 	<domi:privilege url="/admin_op_draw_profits/checkDrawProfitsAjax.htm">
						 	<c:if test="${draw.state==0}"><a href="javascript:void(0);" onclick="checkInfo(${draw.id});">审核</a></c:if>
						 	<c:if test="${draw.state==1&&admin==draw.fuAdmin}"><a href="javascript:void(0);" onclick="checkInfo(${draw.id});">审核</a></c:if>
						 	<a href="${ctx}/admin_op_draw_profits/exportExcel.htm?id=${draw.id}">导出Excel</a>
						 	</domi:privilege>
						 	</div>
						 	</td>
						 </tr>
				  </c:forEach>
				  <c:if test="${empty drawList}">
				  <tr>
					<td colspan="14">
			        	<div class=" empty0"><img src="${ctx}/images_qhht/empty_03.jpg" width="93" height="63"><p class="empty0_font">您暂时还没有相关信息</p></div>
			        </td>
				  </tr>
				  </c:if>
				</tbody></table>
          </div>
           <div class="page">
				<domi:pagination ctx="${ctx}" pageVolume="${pageSize}" url="${ctx}/admin_list_draw_profits/drawProfitsList.htm?totalCount=${totalCount}" totalNum="${totalCount}" curPageNum="${pageNo}" formId="pageForm" >
						<domi:paramTag name="detailId" value="${detailId}"/>
						<domi:paramTag name="accountName" value="${accountName}"/>
						<domi:paramTag name="userName" value="${userName}"/>
						<domi:paramTag name="programId" value="${programId}"/>
						<domi:paramTag name="state" value="${state}"/>
						<domi:paramTag name="money1" value="${money1}"/>
						<domi:paramTag name="money2" value="${money2}"/>
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
		sureInfo("确定","输入明细ID必须为数字","提示");
		return false;
	}
	if(isNaN($("#programId").val())){
		sureInfo("确定","输入方案ID必须为数字","提示");
		return false;
	}
	if(isNaN($("#money1").val())||isNaN($("#money2").val())){
		sureInfo("确定","输入金额必须为数字","提示");
		return false;
	}
	$('#searchForm').submit();
}
function checkInfo(id){
	$.fancybox.open({
        href : '${ctx}/admin_op_draw_profits/checkDrawProfitsAjax.htm?id='+id,
        type : 'ajax',
        padding : 10
	});
}
function searchInfoByUser(userName){
	$("#userName").val(userName);
	searchInfo();
}
</script>
