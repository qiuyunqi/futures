<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－银行转账</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/css.jsp" %>
<style>
  .bankList img{margin:20px auto 10px;display: block;}
  .bankList > span{margin-top: 10px;}
</style>
</head>
<body style="background:#004e99;">
<%@include file="../common/qqConsult.jsp" %>
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>
<%--  --%>
<div class="contain">
	<div class="downban"></div>
	<div class="mgrzx">
	<%@include file="../common/left.jsp" %>
		<div class="mgrzxr">
 			<div class="h20"><span class="centerBg">用户中心</span><span class="safeBg" style="text-indent:35px;">充值</span></div>
    		<div class="payMenu">
		    <c:if test="${!empty rechargeConfig.zf1}"><a class="payBtn" href="${ctx}/user_recharge/recharge.htm?type=1">银联充值</a></c:if>
		    <c:if test="${!empty rechargeConfig.zf3}"><a class="payBtn" href="${ctx}/user_recharge/recharge.htm?type=3">支付宝充值</a></c:if>
		    <c:if test="${!empty rechargeConfig.zf4}"><a class="payBtn borBlue" href="${ctx}/user_recharge/recharge.htm?type=4">银行转账</a></c:if>
		    <div class="clear"></div>
		    </div>
	    	<div id="one_1" class="display-b">
		        <div class="rzfbzz">
		          <div class="rzfbb fntSze colorBlu">第一步：填写付款信息</div>
		          <form id="rechargeForm">
			          <table class="fntSze margLft" cellpadding="0" cellspacing="0" border="0" width="100%">
			            <tr>
			              <th>付款金额：</th>
			              <td><input type="input" class="textput" name="money" value="<fmt:formatNumber value='${money}' pattern='#,###,###.##'/>" id="money1" style="width:250px;" placeholder="请输入付款金额"/></td>
			            </tr>
			            
			            <tr>
			              <th width="110" class="fntSze">选择银行卡：</th>
			              <td id="grselect" >
				               <select id="bank" name="bankId" style="width:250px;height:30px;" onchange="toBank(this.value);">
					             <option value="">选择银行卡</option>
					             <option value="0">添加银行卡</option>
					             <c:forEach items="${bankCardList}" var="bankCard">
					             <option value="${bankCard.id}">${bankCard.bankName} ${fn:substring(bankCard.cardNumber,0,4)}<c:forEach begin='4' end='${(fn:length(bankCard.cardNumber)-5)<0?4:fn:length(bankCard.cardNumber)-5}'>*</c:forEach>${fn:substring(bankCard.cardNumber,fn:length(bankCard.cardNumber)-4,fn:length(bankCard.cardNumber))}</option>
					             </c:forEach>
				               </select>
			              </td>
			            </tr>
			            
			            <tr>
			              <th>收款账号：</th>
			              <td>
			              	<div class="bankCard cenFnt" style="padding:0">
			              		<div id="p1" class="bankList bordr brRadius">
									 <img src="../images_new/jsBank.png"/>
									 <span>3100 1587 6150 5003 2541</span>
								</div>
				             	<div id="p2" class="bankList bordr brRadius">
									 <img src="../images_new/jsBank.png"/>
									 <span>6217 0000 1007 7384 314</span>
								</div>
								<input id="proceedsType" name="proceedsType" type="hidden" value="1"/>
							</div>
			              </td>
			            </tr>
			          </table>
		          <div class="rzfbb fntSze colorBlu">第二步：您可以通过网上银行或银行柜台向投资管理平台转账（手续费最多一笔50元）</div>
		          <div class="brRadius bordr rzfbyh margLft" style="width:80%">
			          <table cellpadding="0" cellspacing="0"  class=" smallFnt brRadius" style="width:100%">
			            <tr>
			              <th><img src="../images_hhr/zfbyh02.jpg" /></th>
			              <td>账号：3100 1587 6150 5003 2541<br />
						                开户名：小合网络科技（上海）有限公司<br />
						                开户行：中国建设银行浦东分行东方路支行</td>
			            </tr>
			            <tr>
			              <th><img src="../images_hhr/zfbyh02.jpg" /></th>
			              <td>账号：6217 0000 1007 7384 314<br />
						                开户名：唐文钊<br />
						                开户行：中国建设银行（北京长河湾支行）</td>
			            </tr>
			          </table>
			      </div>
		          <div class="rzfbyhxyb">
		          	<a href="javascript:void(0);" class="bcbut" style="margin-left:10px;" onclick="saveRecharge();return false;">提交</a>
		            <div class="clear"></div>
		          </div>
		     </form>
		   </div>
	    </div>
	    
	    <div class="downban"></div>
	    <div class="zfbListTits">
	       <div class="zfbListTit">充值记录</div>
	       <div class="zfbListTitBtns"><a class="zfbListTitBtn" href="${ctx}/user_recharge/recharge.htm?type=4&rechargeStatus=0">未审核</a><a class="zfbListTitBtn" href="${ctx}/user_recharge/recharge.htm?type=4&rechargeStatus=2">成功</a><a class="zfbListTitBtn" href="${ctx}/user_recharge/recharge.htm?type=4">全部</a></div>
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
					url="${ctx}/user_recharge/recharge.htm?type=4&totalCount=${totalCount}"
					totalNum="${totalCount}" curPageNum="${pageNo}"
					formId="pageForm">
					<domi:paramTag name="rechargeStatus" value="${rechargeStatus}"/>
			    </domi:pagination>
			</div>
	      </div>
	    </div>
	  </div>
  <div class="clear"></div>
</div>
<div class="downban"></div>
<%@include file="../common/footer.jsp" %>
</body>
</html>
<script>
//选中银行卡变色
$(function(){
	$("#p1").addClass("bankvisited");
    $(".bankCard").each(function(){
      	$(".bankList").click(function(){
	        $(this).addClass("bankvisited");
	        $(this).nextAll().removeClass("bankvisited");
	        $(this).prevAll().removeClass("bankvisited");
	        $(this).nextAll().addClass("bankList bordr brRadius");
	        $(this).prevAll().addClass("bankList bordr brRadius");
	       	if($("#p1").attr("class")=="bankList bordr brRadius bankvisited"){
	       		$("#proceedsType").val(1);
	       	}
	       	if($("#p2").attr("class")=="bankList bordr brRadius bankvisited"){
	       		$("#proceedsType").val(2);
	       	}
     	});
   	});
});

function toBank(val){
	if(val==0){
       location.href = "${ctx}/user_draw_money/bankManager.htm";
	}
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
  var data = $("#rechargeForm").serialize();
  $.alerts.okButton="确定";
  $.alerts.cancelButton="返回";
  jConfirm("确认充值？","操作提示",function(res){
		if(res){
			  $.post("${ctx}/user_recharge/saveUserRecharge.htm?type=${type}",data,function(d){
				    if(d==-6){
			        	jAlert("请选择转账银行","提示",function(){
			  				$("#bank").focus();
			            });
			            return null;
			        }
			        if(d==-3){
			             jAlert("请填写账号","提示",function(){
							$("#zh").focus();
			             });
			             return null;
			        }
			        if(d==-4){
			        	 jAlert("请填写充值金额","提示",function(){
			  				$("#money1").focus();
			               });
			             return null;
			        }
			        if(d==-5){
			        	jAlert("充值金额必须为正数","提示",function(){
			  				$("#money1").focus();
			            });
			            return null;
			        }
			        if(${type!=3&&type!=4}){
			       		 sureInfo("确定","充值申请已提交","提示");
			        }else{
			        	location.href = "${ctx}/user_recharge/bankTransferInfo.htm?type=${type}&id="+d;
			        }
			  });
		}
	});
}
</script>
