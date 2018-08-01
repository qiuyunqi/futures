<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－管理员管理－安全配资服务平台</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/cssback.jsp" %>
<style>
.lf_font{width:150px;}
</style>
</head>
<body style=" background:#fff">
<c:set var="first" value="4"/>
<c:set var="second" value="2"/>
<div class="content">
   <div class=" rt_cont">
        <form id="parameterForm">
    	<div class="rt_cont_mat">
    	    <div class="rt_cont_title">系统参数设置</div>
         <div class="" style="margin-top:20px;">
         	<div class="form_cont tjjj">
            <div class="lf_font fl">期货大赛赠送金额：</div>
            <div class="fl input"><input name="gameMoney" type="text" placeholder="" value="<fmt:formatNumber value="${params.gameMoney}" pattern="#######"/>" id="val1"></div>
            <div class="fl" style="line-height:33px;padding-left:5px;">元</div>
            <div class="clr"></div>
        	</div>
	        <div class="form_cont tjjj">
	            <div class="lf_font fl">亏损警报线百分比：</div>
	            <div class="fl input"><input name="warnlinePercent" type="text"  value="<fmt:formatNumber value="${params.warnlinePercent*100}" pattern="##"/>" placeholder="" id="val2"></div>
	            <div class="fl" style="line-height:33px;padding-left:5px;">%</div>
	            <div class="clr"></div>
	        </div>
	        <div class="form_cont tjjj">
	            <div class="lf_font fl">亏损平仓线百分比 ：</div>
	            <div class="fl input"><input name="flatlinePercent" type="text" value="<fmt:formatNumber value="${params.flatlinePercent*100}" pattern="##"/>"  placeholder="" id="val3"></div>
	            <div class="fl" style="line-height:33px;padding-left:5px;">%</div>
	            <div class="clr"></div>
	        </div>
	        <div class="form_cont tjjj">
	            <div class="lf_font fl">获取佣金比例：</div>
	            <div class="fl input"><input name="commissionPercent" type="text"  value="<fmt:formatNumber value="${params.commissionPercent*100}" pattern="##"/>" placeholder="" id="val4"></div>
	            <div class="fl" style="line-height:33px;padding-left:5px;">%</div>
	            <div class="clr"></div>
	        </div>
	        <div class="form_cont tjjj">
	            <div class="lf_font fl">日配利息，每日每万：</div>
	            <div class="fl input"><input name="feeDay" type="text"  value="<fmt:formatNumber value="${params.feeDay}" pattern="##"/>" placeholder="" id="val9"></div>
	            <div class="fl" style="line-height:33px;padding-left:5px;">元</div>
	            <div class="clr"></div>
	        </div>
	        <div class="form_cont tjjj">
	            <div class="lf_font fl">每个月利息百分比：</div>
	            <div class="fl input"><input name="feePercent" type="text"  value="<fmt:formatNumber value="${params.feePercent*100}" pattern="#,###,##0.0000"/>" placeholder="" id="val5"></div>
	            <div class="fl" style="line-height:33px;padding-left:5px;">%</div>
	            <div class="clr"></div>
	        </div>
	        <div class="form_cont tjjj">
	            <div class="lf_font fl">三个月利息百分比：</div>
	            <div class="fl input"><input name="interestPercent" type="text"  value="<fmt:formatNumber value="${params.interestPercent*100}" pattern="#,###,##0.0000"/>" placeholder="" id="val6"></div>
	            <div class="fl" style="line-height:33px;padding-left:5px;">%</div>
	            <div class="clr"></div>
	        </div>
	        <div class="form_cont tjjj">
	            <div class="lf_font fl">短线高手操作最大数：</div>
	            <div class="fl input"><input name="shortNum" type="text"  value="${params.shortNum}" placeholder="" id="val7"></div>
	            <div class="clr"></div>
	        </div>
	        <div class="form_cont tjjj">
	            <div class="lf_font fl">趋势之王操作最大数：</div>
	            <div class="fl input"><input name="longNum" type="text"  value="${params.longNum}" placeholder="" id="val8"></div>
	            <div class="clr"></div>
	        </div>
	        <div class="form_cont tjjj">
	            <div class="lf_font fl">待审核方案过期时间：</div>
	            <div class="fl input"><input name="programDueTime" type="text"  value="${params.programDueTime}" placeholder="" id="val9" ></div>
	            <div class="fl" style="line-height:33px;padding-left:5px;">小时</div>
	            <div class="clr"></div>
	        </div>
	        <div class="form_cont tjjj">
	            <div class="lf_font fl">支付时手续费百分比：</div>
	            <div class="fl input"><input name="payPoundage" type="text"  value="<fmt:formatNumber value="${params.payPoundage*100}" pattern="#0.00"/>" placeholder="" id="val10" ></div>
	            <div class="fl" style="line-height:33px;padding-left:5px;">%</div>
	            <div class="clr"></div>
	        </div>
	        <div class="form_cont tjjj">
	            <div class="lf_font fl">合伙人分配年化系数：</div>
	            <div class="fl input"><input name="hhrPercent" type="text"  value="<fmt:formatNumber value="${params.hhrPercent*100}" pattern="#0.00"/>" placeholder="" id="val11"></div>
	            <div class="fl" style="line-height:33px;padding-left:5px;">%</div>
	            <div class="clr"></div>
	        </div>
	        <div class="form_cont tjjj">
	            <div class="lf_font fl">股票分成系数：</div>
	            <div class="fl input"><input name="stockPercent" type="text"  value="<fmt:formatNumber value="${params.stockPercent*100}" pattern="#0.00"/>" placeholder="" id="val12"></div>
	            <div class="fl" style="line-height:33px;padding-left:5px;">%</div>
	            <div class="clr"></div>
	        </div>
	        <div class="form_cont tjjj">
	            <div class="lf_font fl">同一IP每天注册上限：</div>
	            <div class="fl input"><input name="regNum" type="text"  value="${params.regNum}" placeholder="" id="val13"></div>
	            <div class="clr"></div>
	        </div>
	        <div class="form_cont tjjj">
	            <div class="lf_font fl">同一IP注册频率（分钟）：</div>
	            <div class="fl input"><input name="regInterval" type="text"  value="${params.regInterval}" placeholder="" id="val14"></div>
	            <div class="clr"></div>
	        </div>
	        <div class="form_cont tjjj">
	            <div class="lf_font fl">短信开关：</div>
	            <div class="fl select">
	            <select style="width:178px;" name="isMessage">
	            	<option <c:if test="${params.isMessage==1}">selected="selected"</c:if> value="1">启用</option>
	            	<option <c:if test="${params.isMessage==0}">selected="selected"</c:if> value="0">禁用</option>
	            </select>
	            </div>
	            <div class="clr"></div>
	        </div>
	        <div class="form_cont tjjj">
	            <div class="lf_font fl">注册验证码类型：</div>
	            <div class="fl select">
	            <select style="width:178px;" name="messageType">
	            	<option <c:if test="${params.messageType==1}">selected="selected"</c:if> value="1">语音验证码</option>
	            	<option <c:if test="${params.messageType==0}">selected="selected"</c:if> value="0">短信验证码</option>
	            </select>
	            </div>
	            <div class="clr"></div>
	        </div>
	        <div class="form_cont tjjj">
	            <div class="lf_font fl">缴费分成开关：</div>
	            <div class="fl select">
	            <select style="width:178px;" name="isPayIncome">
	            	<option <c:if test="${params.isPayIncome==1}">selected="selected"</c:if> value="1">启用</option>
	            	<option <c:if test="${params.isPayIncome==0}">selected="selected"</c:if> value="0">禁用</option>
	            </select>
	            </div>
	            <div class="clr"></div>
	        </div>
	        <div class="form_cont tjjj">
	            <div class="lf_font fl">客服邮箱地址：</div>
	            <div class="fl input"><input name="serviceEmail" type="text"  value="${params.serviceEmail}" placeholder="" id=""></div>
	            <div class="fl" style="line-height:33px;padding-left:5px;color: red">(多个邮箱之间用英文逗号分开)</div>
	            <div class="clr"></div>
	        </div>
	        <div class="form_cont tjjj">
	            <div class="lf_font fl">充值方式：</div>
	            <div class="fl input" style="border:none;">
	                <div class="fl checkbox checkbox0" style="padding:0;">
			             <span class="xuankang"><input name="zf" type="checkbox" value="1" style="width:13px;" <c:if test="${!empty rechargeConfig.zf1}">checked</c:if>></span>
			             <span class="checkb_font">银联支付</span>
			             <span class="xuankang"><input name="zf" type="checkbox" value="2" style="width:13px;" <c:if test="${!empty rechargeConfig.zf2}">checked</c:if>></span>
			             <span class="checkb_font">网银充值</span>
			             <span class="xuankang"><input name="zf" type="checkbox" value="3" style="width:13px;" <c:if test="${!empty rechargeConfig.zf3}">checked</c:if>></span>
			             <span class="checkb_font">支付宝转账</span>
			             <span class="xuankang"><input name="zf" type="checkbox" value="4" style="width:13px;" <c:if test="${!empty rechargeConfig.zf4}">checked</c:if>></span>
			             <span class="checkb_font">银行转账</span>
		             </div>
			         <p style="color:gray;padding-top:30px;font-size:12px;">(可选择多种方式)</p>
		         </div>
	            <div class="clr"></div>
	        </div>
        </div>   
         <div style="margin:20px 0 0 140px;"><domi:privilege url="/admin_op_parameter/saveParamsAjax.htm"><a href="javascript:void(0);" onclick="saveParameter();" class="sure fl">保存设置</a></domi:privilege><div class="clr"></div></div>
		</div>
		</form>
    </div>
</div>
</body>
</html>
<script>
var sub = false;
function saveParameter(){
	var zflength = 0;
	var val1 = $("#val1").val();
	var val2 = $("#val2").val();
	var val3 = $("#val3").val();
	var val4 = $("#val4").val();
	var val5 = $("#val5").val();
	var val6 = $("#val6").val();
	var val7 = $("#val7").val();
	var val8 = $("#val8").val();
	var val9 = $("#val9").val();
	var val10 = $("#val10").val();
	var val11 = $("#val11").val();
	var val12 = $("#val12").val();
	var val13 = $("#val13").val();
	var val14 = $("#val14").val();
	if(isNaN(val1)||isNaN(val2)||isNaN(val3)||isNaN(val4)||isNaN(val5)||isNaN(val6)||isNaN(val7)||isNaN(val8)||isNaN(val9)||isNaN(val10)||isNaN(val11)||isNaN(val12)||isNaN(val13)||isNaN(val14)){
		 sureInfo("确定","格式输入错误,请输入数字!","提示");
         return null;
	}
	$("input[name='zf']").each(function(i,o){
		   if($(o).prop("checked")){
			   zflength+=1;
		   }
	});
	if(zflength<1){
		sureInfo("确定","请选择至少一种支付方式!","提示");
        return null;
	}
	if(!val13 || parseInt($("#val13").val())<=0){
		jAlert("IP每天注册最大数至少为1！","提示",function(){
			$("#val13").focus();
        });
		return false;
	}
	if(!val14 || parseInt($("#val14").val())<=0){
		jAlert("IP注册时间间隔（分钟）至少为1！","提示",function(){
			$("#val14").focus();
        });
		return false;
	}
	if(sub) return;
	sub = true;
	var data = $('#parameterForm').serialize();
	$.post("${ctx}/admin_op_parameter/saveParamsAjax.htm",data,function(d){
		sub = false;
		if(d==-1){
			sureInfo("确定","抱歉，你没有操作权限!","提示");
	        return null;
		}
		if(d==-2){
			sureInfo("确定","请选择至少一种充值方式!","提示");
	        return null;
		}
		sureInfo("确定","参数设置成功!","提示");
	});
}
</script>
