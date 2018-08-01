<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－推广管理</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
<style>
.lf_font{width:150px;}
</style>
</head>
<body style=" background:#fff">
<c:set var="first" value="4"/>
<c:set var="second" value="4"/>
<div class="content">
   <div class=" rt_cont">
        <form id="parameterForm">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">推广参数设置</div>
	         <div class="" style="margin-top:20px;">
	         	<div class="form_cont tjjj">
		            <div class="lf_font fl">发钱总金额：</div>
		            <div class="fl input"><input name="totalMoney" type="text" placeholder="" value="<fmt:formatNumber value="${promote.totalMoney}" pattern="#######"/>" id="val1"></div>
		            <div class="fl" style="line-height:33px;padding-left:5px;">元</div>
		            <div class="clr"></div>     
	        	</div>
	        
	        	<div class="form_cont tjjj">
		            <div class="lf_font fl">红包范围：</div>
		            <div class="fl input"><input name="lineMoney" type="text"  placeholder="" value="${promote.lineMoney}" id="val2"></div>
		            <!-- <div class="fl" style="line-height:33px;padding-left:5px;">元</div> -->
		            <div class="clr"></div>
	        	</div>       
	         
		        <div class="form_cont tjjj">
		            <div class="lf_font fl">是否开启活动：</div>
		            <div class="fl input" style="border:none;">
		                <div class="fl checkbox checkbox0" style="padding:0;">
		               	    <span class="xuankang"><input name="isOpen" type="checkbox" value="" style="width:13px;" <c:if test="${promote.isOpen==1}">checked</c:if> id="xz"></span>
			            </div>
			         </div>
		            <div class="clr"></div>
		        </div>
	         </div>   
         	<div style="margin:20px 0 0 140px;">
         	<domi:privilege url="/admin_list_promote/saveParamsAjax.htm">
         	<a href="javascript:void(0);" onclick="saveParameter();" class="sure fl">保存设置</a>
         	</domi:privilege>
         	<div class="clr"></div></div>
		</div>
		</form>
    </div>
</div>
</body>
</html>
<script>
function saveParameter(){
	var val1 = $("#val1").val();
	//var val2 = $("#val2").val();
	var xz = $('#xz').is(':checked')==true?"1":"0";
	if(isNaN(val1)){
		 sureInfo("确定","格式输入错误,请输入数字!","提示");
         return null;
	}
	
	var data = $('#parameterForm').serialize();
	$.post("${ctx}/admin_list_promote/saveParamsAjax.htm?xz="+xz,data,function(d){
		jAlert("参数设置成功！","提示",function(){
			location.href = location.href;
        });
	});
}
</script>
