<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－支付宝充值</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/css.jsp" %>
</head>
<body style="background:#004e99;">
<%@include file="../common/qqConsult.jsp" %> 
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>
<%--  --%>
<div class="contain">
	<div class="downban"></div>
	<div class="mgrzx">
	<%-- <%@include file="../common/left.jsp" %> --%>
		<%@include file="../common/left.jsp" %>
		<div class="mgrzxr">
		    <div class="h20"><span class="centerBg">用户中心</span><span class="safeBg" style="text-indent:35px;">充值</span></div>
		    <div class="payMenu">
		    <c:if test="${!empty rechargeConfig.zf1}"><a class="payBtn" href="${ctx}/user_recharge/recharge.htm?type=1">银联充值</a></c:if>
		    <c:if test="${!empty rechargeConfig.zf3}"><a class="payBtn borBlue" href="${ctx}/user_recharge/recharge.htm?type=3">支付宝充值</a></c:if>
		    <c:if test="${!empty rechargeConfig.zf4}"><a class="payBtn" href="${ctx}/user_recharge/recharge.htm?type=4">银行转账</a></c:if>
		    <div class="clear"></div>
		    </div>
		      <div class="mgrrmain">
		      <div id="one_1" class="display-b">
		        <div class="rzfbzz">
		          <div class="rzfba"><img src="../images_hhr/zfbzz01.jpg" /><a href="javascript:void(0)">支付宝转账</a></div>
		          <form id="rechargeForm">
		          <table cellpadding="0" cellspacing="0" border="0" width="100%">
		            <tr>
		              <th width="100">充值金额：</th>
		              <td><input type="input" class="textput" name="money" value="<fmt:formatNumber value='${money}' pattern='#,###,##0.00'/>" id="money1" onkeyup="toChinese();" placeholder="请输入充值金额"/></td>
		            </tr>
		            <tr>
		              <th></th>
		              <td><a href="javascript:void(0)" onclick="saveRecharge()" class="bcbut">提交</a></td>
		            </tr>
		          </table>
		          </form>
		        </div>
		      </div>
		    </div>
		    <div class="downban"></div>
		    <div class="zfbListTits">
		       <div class="zfbListTit">充值记录</div>
		       <div class="zfbListTitBtns"><a class="zfbListTitBtn" href="${ctx}/user_recharge/recharge.htm?type=3&rechargeStatus=0">未审核</a><a class="zfbListTitBtn" href="${ctx}/user_recharge/recharge.htm?type=3&rechargeStatus=2">成功</a><a class="zfbListTitBtn" href="${ctx}/user_recharge/recharge.htm?type=3">全部</a></div>
		    </div>
		    <div class="mgrrmain">
		      <div id="one_1" class="display-b">
		        <div class="rzczjl" style="padding-bottom:0;">
		          <table cellpadding="0" cellspacing="0" border="0" width="100%" style="font-size:14px;color:#808080;">
			          <tr>
			             <td width="20%" height="35" align="center" bgcolor="#f8f8f8">时间</td>
			             <td width="20%" height="35" align="center" bgcolor="#f8f8f8">订单号</td>
			             <td width="20%" height="35" align="center" bgcolor="#f8f8f8">充值方式</td>   
			             <td width="20%" height="35" align="center" bgcolor="#f8f8f8" style="text-align: right;">金额（元）</td>  
			             <td width="20%" height="35" align="center" bgcolor="#f8f8f8">状态</td>  
			          </tr>
			           <c:forEach items="${rechargeList}" var="recharge" varStatus="row">
			          <tr>
			            <td height="35" align="center" <c:if test="${row.index==fn:length(rechargeList)-1}">style="border-bottom:none;"</c:if>><fmt:formatDate value="${recharge.rechargeTime}" pattern="yyyy-MM-dd HH:mm"/></td>
			            <td height="35" align="center" <c:if test="${row.index==fn:length(rechargeList)-1}">border-bottom:none;</c:if>">${recharge.orderNum}</td>
			            <td height="35" align="center" <c:if test="${row.index==fn:length(rechargeList)-1}">style="border-bottom:none;"</c:if>>${recharge.type==1?'银联支付':recharge.type==2?'网银充值':recharge.type==3?'支付宝':'银行转账'}</td>
			            <td height="35" align="right" style="text-align: right;"<c:if test="${row.index==fn:length(rechargeList)-1}">style="border-bottom:none;"</c:if>><fmt:formatNumber value="${recharge.rechargeMoney}" pattern="#,###,##0.00"/>&nbsp</td>
			            <%-- <c:if test="${recharge.type<3}">
			            <td height="35" align="center" <c:if test="${row.index==fn:length(rechargeList)-1}">style="border-bottom:none;"</c:if>><c:if test="${recharge.payStatus==0}"><a href="${ctx }/user_recharge/quickSucced.htm?id=${recharge.id}">未审核</a></c:if><c:if test="${recharge.payStatus==1}">成功</c:if></td>
			            </c:if>
			            <c:if test="${recharge.type>=3}"> --%>
		                <td height="35" align="center" <c:if test="${row.index==fn:length(rechargeList)-1}">style="border-bottom:none;"</c:if>>${recharge.rechargeStatus==0?'未审核':recharge.rechargeStatus==1?'审核中':recharge.rechargeStatus==2?'成功':'拒绝'}</td>
		                <%-- </c:if> --%>
			          </tr>
			          </c:forEach>
		          </table>
		          <c:if test="${empty rechargeList}">
		            <div style="text-align:center;padding:20px;">暂时没有任何内容！</div>
		          </c:if>
		        </div>
		        
		         <div class="page">
					<domi:pagination ctx="${ctx}" pageVolume="${pageSize}"
						url="${ctx}/user_recharge/recharge.htm?type=3&totalCount=${totalCount}"
						totalNum="${totalCount}" curPageNum="${pageNo}"
						formId="pageForm">
						<domi:paramTag name="rechargeStatus" value="${rechargeStatus}"/>
				    </domi:pagination>
				</div>
		      </div>
		    </div>
		    <div class="downban"></div>
		    </div>
	  <div class="clear"></div>
	</div>
</div>
</div>
<%@include file="../common/footer.jsp" %>
</body>
</html>
<script>
/** 
 * 将数值四舍五入(保留2位小数)后格式化成金额形式 
 * 
 * @param num 数值(Number或者String) 
 * @return 金额格式的字符串,如'1,234,567.45' 
 * @type String 
 */  
function formatCurrency(num) {  
    num = num.toString().replace(/\$|\,/g,'');  
    if(isNaN(num))  
    num = "0";  
    sign = (num == (num = Math.abs(num)));  
    num = Math.floor(num*100+0.50000000001);  
    cents = num%100;  
    num = Math.floor(num/100).toString();  
    if(cents<10)  
    cents = "0" + cents;  
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)  
    num = num.substring(0,num.length-(4*i+3))+','+  
    num.substring(num.length-(4*i+3));  
    return (((sign)?'':'-') + num + '.' + cents);  
} 

function toChinese(){
    var payPoundage=$("#payPoundage").val();
	var money=$("#money1").val();
	$("#poundageMoney").text(formatCurrency(money*payPoundage));
}


function saveRecharge(){
	var sr=$("#money1").val();
	if(!sr){
		jAlert('请填写充值金额！','提示',function(){
			$("#money1").focus();
		});
		return false;
	}
	if(isNaN(sr)){
		$.alerts.okButton='确定';
		jAlert('充值金额必须为正数！','提示',function(){
			$("#money1").val("");
			$("#money1").focus();
		});
		return false;
	}
	//var poundageMoney=$("#poundageMoney").text();
	//var data = $("#rechargeForm").serialize();
	var feeMoney=$("#money1").val();
   	window.open("${ctx}/user_recharge/saveAliPay.htm?feeMoney="+feeMoney);
   	$.alerts.okButton="完成充值";
    $.alerts.cancelButton="充值遇到问题";
    jConfirm("请在新打开的页面上完成充值","充值提示",function(res){
  		if(res){
  			location.href="${ctx}/user_recharge/recharge.htm?type=3"; 	
  		}else{
  			window.open("https://cshall.alipay.com/lab/cateQuestion.htm?cateId=237699&pcateId=237698");
  		}
	});
}
</script>
