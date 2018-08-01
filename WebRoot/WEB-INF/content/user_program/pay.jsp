<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp" %>
<link href="../css/pay_hrr.css" rel="stylesheet" type="text/css" />
</head>
<body>
<c:if test="${empty fuUser}">
<c:redirect url="${ctx}/user_login/userLogin.htm"></c:redirect>
</c:if>
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>
<%@include file="../common/qqConsult.jsp" %>
 <form id="rechargeForm">
	<div class="wrapper">
		<div class="upp">
			<img class="weix" src="../images_hhr/weida.png">
		        <div class="toptit">
		            <div class="toptit lft">
		                <ul>
		                    <li>正在使用支付宝进行交易</li>
		                    <li>充值中</li>
		                    <li>客户昵称：<span>${fuUser.nickName}</span></li>
		                </ul>
		            </div>
		            <div class="toptit rig">
		                <span id="payMoney"><fmt:formatNumber value="${money}" pattern="#,###,##0.00"/></span> 元
		                <input type="hidden" id="proId" value="${program.id}"/>
		            </div>
		        </div>
		</div>
       
        <div class="downbd">
            <div class="dowbd dwone">
                <label> 
                    <input id="balance" type="checkbox" checked="checked" onclick="changeBal()"> 
                                                            账户余额： <span id="balanceMoney"><fmt:formatNumber value="${fuUser.accountBalance}" pattern="#,###,##0.00"/></span> 元
                </label>
            </div>
            <div class="dowbd dwtwo">
                <label>
                    <input type="radio" name="paytype" value="3" checked="checked" onchange="changType(this.value)"> 
                    <img src="../images_hhr/zfbzz01.jpg">
                </label>
                <label class="sr-only lr" for="IptName" >支付宝账号：</label>
       			<input type="text" id="IptName" class="form-control" placeholder="请输入您的账号">
            	<a id="zfb" href="javascript:void(0)">支付 <span id="money3"></span> 元</a>
            </div>
            <div class="dowbd dwthr">
                <label>
                    <input type="radio" name="paytype" value="4" onchange="changType(this.value)">
                </label>
                <label class="sr-only lt" for="Iptbank" >转账银行：</label>
       			 <input type="text" id="Iptbank" class="form-control" placeholder="请输入转账银行" disabled="disabled">
       			 <label class="sr-only lt" for="IptPaw" >付款账号：</label>
       			 <input type="text" id="IptPaw" class="form-control" placeholder="请输入您的付款账号" onkeyup="value=value.replace(/[^0-9]/g,'')" onpaste="value=value.replace(/[^0-9]/g,'')" oncontextmenu = "value=value.replace(/[^0-9]/g,'')">
           		 <a id="yhzz" href="javascript:void(0)">支付 <span id="money4"></span> 元</a>
            </div>
            <table class="rzfbyh" width="90%" cellspacing="0" cellpadding="0" border="0" style="margin-left:30px;">
                <tbody>
                <tr>
                    <th>
                        <img src="../images_hhr/zfbyh01.jpg">
                    </th>
                    <td>账号：127906754610206<br>
                                                                        开户名：武汉坤州大德投资管理有限公司<span style="margin-left:100px;"><a href="javascript:void(0)" </span><br>
                                                                        开户行：招商银行股份有限公司武汉首义支行
                    </td>
                </tr>
                <tr>
                    <th>
                        <img src="../images_hhr/zfbyh02.jpg">
                    </th>
                    <td>账号：6217 0000 1007 7384 314<br>
                                                                        开户名：唐文钊<span style="margin-left:275px;"><a href="javascript:void(0)" </span><br>
                                                                       开户行：中国建设银行（北京长河湾支行）
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <a href="javascript:void(0)" onclick="saveRecharge();return false;" class="bcbut hp">确认付款</a>
    </div>
    </form>
<%@include file="../common/footer.jsp" %>
</body>
</html>
<script type="text/javascript">
$("#yhzz").hide();
var payMoney=parseInt(numeral().unformat($("#payMoney").text()));
var balanceMoney=parseInt(numeral().unformat($("#balanceMoney").text()));
var realMoney =payMoney - balanceMoney;
$("#money3").text(numeral(realMoney).format('0,0.00'));
var paytype;
changType(3);


function changeBal(){
	paytype=$('input[name="paytype"]:checked').val();
	//未选中余额支付
	if($("#balance").attr("checked")==undefined){
		$("#money3").text(numeral(payMoney).format('0,0.00'));
		$("#money4").text(numeral(payMoney).format('0,0.00'));
		if(paytype==3){
			$("#yhzz").hide();
			$("#zfb").show();
		}else{
			$("#yhzz").show();
			$("#zfb").hide();
		}
	}
	//选中余额支付
	if($("#balance").attr("checked")=="checked"){
		$("#money3").text(numeral(realMoney).format('0,0.00'));
		$("#money4").text(numeral(realMoney).format('0,0.00'));
		if(paytype==3){
			$("#yhzz").hide();
			$("#zfb").show();
		}else{
			$("#yhzz").show();
			$("#zfb").hide();
		}
	}
}


function changType(type){
	if(type==3){
		//未选中余额支付
		if($("#balance").attr("checked")==undefined){
			$("#money3").text(numeral(payMoney).format('0,0.00'));
			$("#money4").text(numeral(payMoney).format('0,0.00'));
		}
		//选中余额支付
		if($("#balance").attr("checked")=="checked"){
			$("#money3").text(numeral(realMoney).format('0,0.00'));
			$("#money4").text(numeral(realMoney).format('0,0.00'));
		}
		$("#yhzz").hide();
		$("#zfb").show();
		$("#Iptbank").attr("disabled", "disabled");//设为只读
		$("#IptPaw").attr("disabled", "disabled"); //设为只读
		$("#IptName").removeAttr("disabled");//取消只读的设置
	}else if(type==4){
		//未选中余额支付
		if($("#balance").attr("checked")==undefined){
			$("#money3").text(numeral(payMoney).format('0,0.00'));
			$("#money4").text(numeral(payMoney).format('0,0.00'));
		}
		//选中余额支付
		if($("#balance").attr("checked")=="checked"){
			$("#money3").text(numeral(realMoney).format('0,0.00'));
			$("#money4").text(numeral(realMoney).format('0,0.00'));
		}
		$("#yhzz").show();
		$("#zfb").hide();
		$("#IptName").attr("disabled","disabled");//设为只读
		$("#IptPaw").removeAttr("disabled");//取消只读的设置
		$("#Iptbank").removeAttr("disabled");//取消只读的设置
	}
}


function saveRecharge(){
	  var ptype=$('input[name="paytype"]:checked').val();
	  var data = $("#rechargeForm").serialize();
	  if(ptype==3){
		  var account=$("#IptName").val();
		  var money=parseInt(numeral().unformat($("#money3").text()));
		  var bank="";
	  }else if(ptype==4){
		  var account=$("#IptPaw").val();
		  var money=parseInt(numeral().unformat($("#money4").text()));
		  var bank=$("#Iptbank").val();
	  }
	  $.post("${ctx}/user_program/saveRechargeAjax2.htm?type="+ptype+"&bank="+bank+"&account="+account+"&money="+money,data,function(d){
	        if(ptype==3){
	        	if(!$("#IptName").val()){
		             jAlert("请填写支付宝账号","提示",function(){
						$("#IptName").focus();
		             });
		             return null;
	        	}
	        }
	        if(ptype==4){
	        	if(!$("#Iptbank").val()){
		             jAlert("请填写转账银行","提示",function(){
						$("#Iptbank").focus();
		             });
		             return null;
	        	}
	        	if(!$("#IptPaw").val()){
		             jAlert("请填写付款账号","提示",function(){
						$("#IptPaw").focus();
		             });
		             return null;
	        	}
	        }
        	jAlert("充值申请已提交","提示",function(){
        		location.href="${ctx}/user_program/programSuccessAjax.htm?Pay=ok&id="+$("#proId").val();
            });
	  });
	}
</script>
