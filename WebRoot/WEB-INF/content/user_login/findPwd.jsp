<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－找回密码</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/css.jsp" %>
<link href="${ctx}/css/qihuo.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/formValidator-4.0.1.js" charset="UTF-8"></script>
<script type="text/javascript" src="${ctx}/js/formValidatorRegex.js" charset="UTF-8"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/style/validator.css"></link>
<link  href="${ctx}/css/top_hrr.css" rel="stylesheet" type="text/css" />
<style type="">
.onShow{
	font-size: 12px;
	width: 250px;
}
.onFocus{
	font-size: 12px;
	width: 250px;
}
.onCorrect{
	font-size: 12px;
	width: 250px;
}
.onLoad{
	font-size: 12px;
	width: 250px;
}
.onError{
	font-size: 12px;
	width: 250px;
}
body{background: #ededed;}
</style>
</head>
<body>
<%@include file="../common/qqConsult.jsp" %>
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>
<form id="pwdForm" method="post">
<div class=" mfzc_bg" id="div1">
	<div class="mfzc_bg_cont">
	    <div class=" mfzc_matter">
	      <div class="userReg"><a class="userRegZc" href="${ctx}/login">重置密码</a><a class="logi" href="${ctx}/login" style="margin-left:-5px;">登录</a></div>
          <div class="biaodan zhuce_tbl" id="div2">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tbody>
				    <tr>
				      <td>
				         <span class="userRegTitl">手机号码</span><div class="fl wbk"><input name="phone" id="phone" type="text" placeholder="手机号"></div>
				         <div class="onShow" id="phoneTip"></div>
				      </td>
				    </tr>
				    <tr>
				      <td>
				        <span class="userRegTitl">手机验证码</span><div class="fl wbk wbk0"><input name="phoneCode" id="phoneCode" type="text" placeholder="验证码"></div><div class="fl hqyzm"><a href="javascript:void(0);" id="msgBtn" onclick="sendMsg();">获取短信验证码</a></div><div class="clr"></div>
				        <div class="onShow" id="phoneCodeTip"></div>
				      </td>
				    </tr>
				    <tr>
				      <td>
				         <span class="userRegTitl">新密码</span><div class="fl wbk"><input name="newPwd" id="newPwd" type="password" placeholder="新密码"></div>
				         <div class="biaodan_font onShow" id="newPwdTip">6-16位数字和字母组成</div>
				      </td>
				    </tr>
				    <tr>
				      <td>
				        <span class="userRegTitl">再次输入密码</span><div class="fl wbk"><input name="repassword" id="repassword" type="password" placeholder="确认密码"></div>
				        <div class="biaodan_font onShow" id="repasswordTip">再次输入密码</div>
				      </td>
				    </tr>
				  </tbody>
				</table>
				<input type="submit" class="nextReg" onclick="toNext(2);return false;" value="重置密码" >
            </div>
            
			<div id="div3" style=" width:349px; margin:130px auto;display: none;">
			    <div class="fl"><img src="${ctx}/qihuo_images/cg_03.png" width="70" height="70"></div>
			    <div class="fl zccg">
			        <p style=" font-size:32px;">密码设置成功</p>
			        <p>您可以使用新密码登录了</p>
			    </div>
			    <div class="clr"></div>
			</div>
        </div>
     </div>
</div>     
</form>
<div class="downban"></div>
<%@include file="../common/footer.jsp" %>
</body>
</html>
<script>
$(document).ready(function(){
	$.formValidator.initConfig({formID:"regForm",debug:false,submitOnce:true,
		onError:function(msg,obj,errorlist){
		},
		submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
	});
	$("#phone").formValidator({onShow:"请再次输入手机号码",onFocus:"手机号码的格式必须正确",onCorrect:"手机号码格式正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"手机号两边不能有空符号"},onError:"手机号不能为空,请确认"}).regexValidator({regExp:"^[1][0-9]{10}$",onError:"你输入的手机号码格式不正确"});
	$("#phoneCode").formValidator({onShow:"请输入手机验证码",onFocus:"输入您获取的手机验证码",onCorrect:"手机验证码已填"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"手机验证码两边不能有空符号"},onError:"手机验证码不能为空,请确认"});
	$("#newPwd").formValidator({onShow:"请输入新密码",onFocus:"不能为空由6-16位数字和字母组成",onCorrect:"密码格式正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"密码两边不能有空符号"},onError:"密码不能为空,请确认"}).regexValidator({regExp:"^[a-zA-Z0-9]{6,16}$",onError:"你输入的密码格式不正确"});;
	$("#repassword").formValidator({onShow:"请再次输入密码",onFocus:"两次密码必须一致",onCorrect:"密码一致"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"重复密码两边不能有空符号"},onError:"重复密码不能为空,请确认"}).compareValidator({desID:"newPwd",operateor:"=",onError:"2次密码不一致,请确认"});
	
	
});
</script>
<script>
	var sending=false;
	var start=30;
	var sub=false;
	function toNext(stepNo){
		if(sub)
			return;
		sub=true;
		var data=$("#pwdForm").serialize();
		$.post("${ctx}/user_login/findPwdAjax.htm?stepNo="+stepNo,data,function(d){
			sub=false;
			var json=eval('('+d+')');

			if(json.code=='-2'){
				$("#phone").blur();
				return;
			}
			if(json.code=='-3'){
				$("#phoneTip").attr("class","onError").html("该手机号对应的用户不存在");
				return;
			}
			if(json.code=='-4'){
				$("#phoneCodeTip").attr("class","onError").html("验证码错误，或者还没有获取手机验证码");
				return;
			}
			
			if(json.code=='-5'){
				$("#newPwd").blur();
				return false;
			}
			if(json.code=='-6'){
				$("#repassword").blur();
				return false;
			}
			if(json.code=='-7'){
				$("#phoneCodeTip").attr("class","onError").html("验证码已过期");
				return;
			}
			if(stepNo==1){
				$("#div1").hide();
				$("#div2").show();
			}
			if(stepNo==2){
				$("#div2").hide();
				$("#div3").show();
			}
		});
	}
	function sendMsg(){
		if(sub)
			return;
		sub=true;
		if(sending)
			return
		var data=$('#pwdForm').serialize();
		$.post('${ctx}/user_login/sendPwdMsgAjax.htm',data,function(d){
			sub=false;
			var json=eval('('+d+')');
			if(json){
				if(json.code==-2){
					$("#phone").blur();
					return;
				}
				if(json.code==-3){
					$("#phoneTip").attr("class","onError").html("该手机号对应的用户不存在");
					return;
				}
				sending=true;
				setTimeout(countTime,1000);
				$('#msgBtn').css({color:'#ccc'});
				$('#msgBtn').text('已发送(30)');
			}
		});
	}
	function countTime(){
		start--;
		if(start<0){
			$('#msgBtn').css({color:'#fff'});
			$('#msgBtn').text("短信验证码");
			sending=false;
			start=30;
			return;
		}
		$('#msgBtn').text('已发送('+start+')');
		setTimeout(countTime,1000);
	}
</script>
