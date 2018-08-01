<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－后台强平</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
<style>
.lf_font{width:150px;}
</style>
</head>
<body style=" background:#fff">
<c:set var="first" value="6"/>
<c:set var="second" value="5"/>
<div class="content">
   <div class=" rt_cont">
        <form id="parameterForm">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">强平</div>
	         <div class="" style="margin-top:20px;">
	         	<div class="form_cont tjjj">
		            <div class="lf_font fl">品种代码：</div>
		            <div class="fl input">
			            <select id="instrumentId" name="instrumentId">
				            <c:forEach var="trdParam" items="${paramList}" varStatus="status">
							    <option>${trdParam.tradeVariety}</option>
							</c:forEach>
				        </select>
		            </div>
		            <div class="clr"></div>
		        </div>
	        	<div class="form_cont tjjj">
		            <div class="lf_font fl">成交金额：</div>
		            <div class="fl input"><input name="closeMoney" type="text" value="" pattern="#0.00"/ id="val2"></div>
		            <div class="fl" style="line-height:33px;padding-left:5px;">元</div>
		            <div class="clr"></div>
	        	</div>       
		        <div class="form_cont tjjj">
		            <div class="lf_font fl">强平时间：</div>
		            <div class="fl input"><input name="closeTime" type="text"  value="" ></div>
		            <div class="fl" style="line-height:33px;padding-left:5px;color: red">(如:15:12:00)</div>
		            <div class="clr"></div>
	        	</div>
	         </div>   
         	<div style="margin:20px 0 0 140px;"><domi:privilege url="/admin_list_close_trade/closeProcess.htm"><a href="javascript:void(0);" onclick="closeProcess();" class="sure fl">强平处理</a></domi:privilege><div class="clr"></div></div>
		</div>
		</form>
    </div>
</div>
</body>
</html>
<script>
function closeProcess(){
	var val2 = $("#val2").val();
	if(isNaN(val2)){
		 sureInfo("确定","格式输入错误,请输入数字!","提示");
         return null;
	}
	var data = $('#parameterForm').serialize();
	$.post("${ctx}/admin_list_close_trade/closeProcess.htm",data,function(d){
		jAlert("强平处理成功！","提示",function(){
			location.href = location.href;
        });
	});
}
</script>
