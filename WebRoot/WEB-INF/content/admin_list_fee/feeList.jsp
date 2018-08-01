<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－管理费审核－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
</head>
<body style=" background:#fff">
<c:set var="first" value="3"/>
<c:set var="second" value="2"/>
<div class="content">
    <div class=" rt_cont">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">管理费审核<!-- <a href="javascript:void(0);" onclick="exportInfo();" class="fr add">导出</a> --></div>
            <div class="form">
            <form id="searchForm" action="${ctx}/admin_list_fee/feeList.htm" method="post">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tbody><tr>
				  	<td>
				        <div class="form_cont">
				            <div class="lf_font fl">方案ID：</div>
				            <div class="fl input"><input id="programId" name="programId" value="${programId}" type="text" placeholder=""></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">用户ID：</div>
				            <div class="fl input"><input id="userId" name="userId" value="${userId}" type="text" placeholder=""></div>
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
				  </tr>
				  <tr>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">付费周期：</div>
				            <div class="fl input"><input id="payCycle1" name="payCycle1" value="${payCycle1}" type="text" placeholder="" style="width:65px;"></div>
				            <div class="fl duanxian">—</div>
				            <div class="fl input"><input id="payCycle2" name="payCycle2" value="${payCycle2}" type="text" placeholder="" style="width:65px;"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">付费金额：</div>
				            <div class="fl input"><input id="money1" name="money1" value="${money1}" type="text" placeholder="" style="width:65px;"></div>
				            <div class="fl duanxian">—</div>
				            <div class="fl input"><input id="money2" name="money2" value="${money2}" type="text" placeholder="" style="width:65px;"></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl">付费时间：</div>
				            <div class="fl input"><input id="payTime1" name="payTime1" value="<fmt:formatDate value="${payTime1}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'payTime2\')}', dateFmt:'yyyy-MM-dd'})" type="text" placeholder="" style="width:100px;"><i class="riqi"></i></div>
				            <div class="fl duanxian">—</div>
				            <div class="fl input"><input id="payTime2" name="payTime2" value="<fmt:formatDate value="${payTime2}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({minDate:'#F{$dp.$D(\'payTime1\')}', dateFmt:'yyyy-MM-dd'})" type="text" placeholder="" style="width:100px;"><i class="riqi"></i></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				    <td>
				        <div class="form_cont">
				            <div class="lf_font fl"></div>
				            <div class="fl"><a href="javascript:void(0);" onclick="searchInfo();" class="search">搜索</a><a href="${ctx}/admin_list_fee/feeList.htm" class="remove" style="margin-left:30px;">清空</a></div>
				            <div class="clr"></div>
				        </div>
				    </td>
				  </tr>
				</tbody></table>
			</form>

            </div>
          <div class=" yhlb_title">管理费审核列表</div>
            <div class="yhlb">
            
            	 <table width="100%">
				  <tbody><tr>
				    <th>&nbsp;</th>
				    <th>方案ID</th>
				    <th>用户ID</th>
				    <th>用户名</th>
				    <th>真实姓名</th>
				    <th>众期账号</th>
				    <th>产生途径</th>
				    <th>付费周期</th>
				    <th>付费金额 </th>
				    <th>账户余额</th>
				    <th>状态</th>
				    <th>审核人</th>
				    <th width="20%">审核备注</th>
				    <th>付费时间</br>审核时间</th>
				    <th style=" text-align:center">操作</th>
				    </tr>
				  <s:iterator value="feeList" var="fee" status="row">
				  		<tr onmouseover="$(this).css('background','#f6f6f6');" onmouseout="$(this).css('background','');">
						    <td align="center" class="num">${(row.index+1)+((pageNo-1)*pageSize)}</td>
						    <td style="color:#137490;cursor: pointer;"  <c:if test="${fee.state!=1}">onclick="checkInfo(${fee.id});"</c:if>>${fee.fuProgram.id}</td>
						    <td>${fee.fuUser.id}</td>
						    <td>${fee.fuUser.accountName}</td>
						    <td style="color:#137490;cursor:pointer;" onclick="searchInfoByUser('${fee.fuUser.userName}');">${fee.fuUser.userName}</td>
						    <td>${fee.fuProgram.fuServer.id}_${fee.fuProgram.tradeAccount}</td>
						    <td>${fee.feeType}</td>
						    <td><fmt:formatDate value="${fee.beginTime}" pattern="MM月dd日"/> 至 <fmt:formatDate value="${fee.endTime}" pattern="MM月dd日"/></td>
						    <td><fmt:formatNumber value="${empty fee.money?0:fee.money}" pattern="#,###,##0.00"/></td>
						    <td><fmt:formatNumber value="${empty fee.accountBalance?0:fee.accountBalance}" pattern="#,###,##0.00"/></td>
						    <td>${fee.state==0?'待审核':fee.state==1?'审核中':fee.state==2?'同意':'拒绝'}</td>
						    <td>${empty fee.fuAdmin?'----':fee.fuAdmin.name}</td>
						    <td>${fee.comment}</td>
						    <td><fmt:formatDate value="${fee.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/></br><fmt:formatDate value="${fee.checkTime}" pattern="yyyy-MM-dd HH:mm:ss"/>${empty fee.checkTime?'----':''}</td>
						 	<td style="text-align:center">
						 	<div class=" caozuo">
						 	<domi:privilege url="/admin_op_fee/checkFeeAjax.htm">
						 	<c:if test="${fee.state==0}"><a href="javascript:void(0);" onclick="checkInfo(${fee.id});">审核</a></c:if>
						 	<c:if test="${fee.state==1&&admin==fee.fuAdmin}"><a href="javascript:void(0);" onclick="checkInfo(${fee.id});">审核</a></c:if>
						 	</domi:privilege>
						 	<domi:privilege url="/admin_op_fee/exportExcel.htm">
						 	<a href="${ctx}/admin_op_fee/exportExcel.htm?id=%{id}">导出Excel</a>
						 	</domi:privilege>
						 	</div>
						 	</td>
						 </tr>
				  </s:iterator>
				  <c:if test="${empty feeList}">
				  <tr>
					<td colspan="15">
			        	<div class=" empty0"><img src="${ctx}/images_qhht/empty_03.jpg" width="93" height="63"><p class="empty0_font">您暂时还没有相关信息</p></div>
			        </td>
				  </tr>
				  </c:if>
				</tbody></table>
          </div>
           <div class="page">
				<domi:pagination ctx="${ctx}" pageVolume="${pageSize}" url="${ctx}/admin_list_fee/feeList.htm?totalCount=${totalCount}" totalNum="${totalCount}" curPageNum="${pageNo}" formId="pageForm" >
						<domi:paramTag name="programId" value="${programId}"/>
						<domi:paramTag name="userId" value="${userId}"/>
						<domi:paramTag name="accountName" value="${accountName}"/>
						<domi:paramTag name="userName" value="${userName}"/>
						<domi:paramTag name="payCycle1" value="${payCycle1}"/>
						<domi:paramTag name="payCycle2" value="${payCycle2}"/>
						<domi:paramTag name="money1" value="${money1}"/>
						<domi:paramTag name="money2" value="${money2}"/>
						<domi:paramTag name="payTime1" value="${payTime1}"/>
						<domi:paramTag name="payTime2" value="${payTime2}"/>
				</domi:pagination>
			</div>
               <div class="clr"></div> 
            
        </div>
    </div>
</div>
</body>
</html>
<script>
//导出
/* function exportInfo(){
    var programId=$("#programId").val();
	var userId=$("#userId").val();
	var accountName=$("#accountName").val();
	var userName=$("#userName").val();
    var payCycle1=$("#payCycle1").val();
    var payCycle2=$("#payCycle2").val();
    var money1=$("#money1").val();
    var money2=$("#money2").val();
    var payTime1=$("#payTime1").val();
    var payTime2=$("#payTime2").val();
	$.fancybox.open({
			href : '${ctx}/admin_list_fee/exportExcelAjax.htm?programId='+programId+'&userId='+userId
			+'&accountName='+accountName+'&userName='+userName+'&payCycle1='+payCycle1+'&payCycle2='+payCycle2
			+'&money1='+money1+'&money2='+money2+'&payTime1='+payTime1+'&payTime2='+payTime2,
			type : 'ajax'
	});
} */

function checkInfo(id){
	$.fancybox.open({
        href : '${ctx}/admin_op_fee/checkFeeAjax.htm?id='+id,
        type : 'ajax',
        padding : 10
	});
}
function exportExcel(id){
	$.fancybox.open({
        href : '${ctx}/admin_op_fee/exportExcelAjax.htm?id='+id,
        type : 'ajax',
        padding : 10
	});
}
function searchInfo(){
	if(isNaN($("#programId").val())){
		sureInfo("确定","输入方案ID必须为数字","提示");
		return false;
	}
	if(isNaN($("#userId").val())){
		sureInfo("确定","输入用户ID必须为数字","提示");
		return false;
	}
	if(isNaN($("#payCycle1").val())||isNaN($("#payCycle2").val())){
		sureInfo("确定","输入付费周期必须为数字","提示");
		return false;
	}
	if(isNaN($("#money1").val())||isNaN($("#money2").val())){
		sureInfo("确定","输入付费金额必须为数字","提示");
		return false;
	}
	$('#searchForm').submit();
}
function searchInfoByUser(userName){
	$("#userName").val(userName);
	searchInfo();
}
</script>
