<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－注册</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp" %>
<link href="${ctx}/css/qihuo.css" rel="stylesheet" type="text/css" />
<c:set var="jsptype" value="userReg"/>
<style type="text/css">
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
<form id="regForm" method="post">
<div class=" mfzc_bg" id="div1">
	<div class="mfzc_bg_cont">
	  <div class=" mfzc_matter">
	  		<div class="userReg"><a class="logi" href="${ctx}/login">登录</a><a class="userRegZc" href="${ctx}/register" style="margin-left:-5px;">邀请注册</a></div>
            <div class="biaodan zhuce_tbl">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tbody>
				    <tr>
				      <td>
				        <span class="userRegTitl">手机号码</span><div class="fl wbk"><input name="phone" id="phone" type="text" placeholder="手机号" onblur="checkPhone(this.value)"></div>
				        <div class="biaodan_font onShow" id="phoneTip"></div>
				      </td>
				    </tr>
				    <tr>
				      <td>
				        <span class="userRegTitl">昵称</span><div class="fl wbk"><input name="nickName" id="nickName" type="text" placeholder="昵称（3到15位）"></div>
				        <div class="onShow" id="nickNameTip">请输入昵称</div>
				      </td>
				    </tr>
				    <tr>
				      <td>
				         <span class="userRegTitl">登录密码</span><div class="fl wbk"><input name="password" id="password" type="password" placeholder="密码（6到20位）"></div>
				         <div class="fl biaodan_font onShow" id="passwordTip"></div>
				      </td>
				    </tr>
				    <tr>
				      <td>
				         <span class="userRegTitl">确认密码</span><div class="fl wbk"><input name="repassword" id="repassword" type="password" placeholder="再输入一次密码"></div>
				         <div class="fl biaodan_font onShow" id="repasswordTip">再次输入密码</div>
				      </td>
				    </tr>
				    <tr>
				      <td>
				         <span class="userRegTitl">邀请码</span><div class="fl wbk"><input name="invitationCode" id="invitationCode" type="text" placeholder="邀请码或邀请人手机号"></div>
				         <div class="onShow" id="invitationCodeTip">请输入邀请码</div>
				      </td>
				    </tr>
				    <tr>
				      <td>
				         <span class="userRegTitl">手机验证码</span><div class="fl wbk wbk0"><input name="phoneCode" id="phoneCode" type="text" placeholder="手机验证码"></div><div class="fl hqyzm"><a href="javascript:void(0);" id="hqyzm" onclick="sendMsg();">获取${messageType}验证码</a></div>
				         <div class="onShow" id="phoneCodeTip"></div>
				      </td>
				    </tr>
				  </tbody>
				</table>
            </div>
            <input id="messageType" type="hidden" value="${messageType}"/>
            <input id="sign" name="sign" type="hidden" value="${sign}"/>
            <div style="margin-left:90px;"><input id="isAgree" name="isAgree" type="checkbox" value="1" checked="checked"/> 我已阅读并同意<a href="javascript:void(0);" onclick="window.open ('${ctx}/agreement_login.jsp', 'newwindow', 'height=600, width=930, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no')" class="jkxy">《超级合伙人联盟服务协议》</a></div>
            <a href="javascript:void(0);" onclick="toNext();" class="nextReg">提交注册</a>
      </div>
      <div class="zhuce_erweima">
        <img src="../images_new/erweimaCode.png"/>
        <span class="zcerweimaF">没有【超级合伙人】邀请码？</span>
        <span class="zcerweimaT">扫描关注微信公共账号获取邀请码</span>
      </div>
     </div>  
	</div> 
	
<div class=" mfzc_bg" id="div3" style="display: none;">
		<div class="mfzc_bg_cont">
	      <div class=" mfzc_matter" style=" margin-bottom:0;margin-top:50px;">
       	   <div style=" width:630px; margin:0 auto">
           	<div class="fl"><img src="${ctx}/qihuo_images/cg_03.png" width="70" height="70" /></div>
             <div class="fl zccg">
             	<p style=" font-size:24px;">恭喜您注册成功</p>
             	<p id="invita">请牢记您的邀请码：<span style="color:red;"></span>，您的论坛账号为手机号，密码为手机号末6位。</p>
                <p>新的投资旅程现已开始，您现在可以</p>
             </div>
             <div class="clr"></div>
           </div>
           <div class=" fhsy">
            <a href="${ctx}/index_info/index.htm" class=" sure" style="margin-right:20px">返回首页</a>
            <a href="${ctx}/user_center/centerIndex.htm" class=" sure" style="margin-right:20px">个人中心</a>
           </div>
      </div>
        </div>  
      </div>	
</form>
<div class="downban"></div>
<%@include file="../common/footer.jsp" %>
</body>
</html>
<script type="text/javascript" src="${ctx}/js/formValidator-4.0.1.js" charset="UTF-8"></script>
<script type="text/javascript" src="${ctx}/js/formValidatorRegex.js" charset="UTF-8"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/style/validator.css"></link>
<link  href="${ctx}/css/top_hrr.css" rel="stylesheet" type="text/css" />
<script>
$("#div1").show();
$("#div3").hide();
$(document).ready(function(){
	$.formValidator.initConfig({formID:"regForm",debug:false,submitOnce:true,
		onError:function(msg,obj,errorlist){
		},
		submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
	});
	$("#nickName").formValidator({onShow:"请输入昵称",onFocus:"由3-15位字母,数字,下划线或中文（占两位）",onCorrect:"昵称可用"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"昵称两边不能有空符号"},onError:"昵称不能为空,请确认"})
	.regexValidator({regExp:"^[a-zA-Z\u4e00-\u9fa5][a-zA-Z0-9_\u4e00-\u9fa5]{1,13}[a-zA-Z0-9\u4e00-\u9fa5]$",onError:"昵称格式不正确，请输入3-15位字母，数字，下划线或中文（占两位）,且不能以下划线和数字开头，不能以下划线结尾"})
	.ajaxValidator({          
		dataType : "text",
		async : true,
		url : "${ctx}/user_login/checkNickAjax.htm",
		success : function(data){
			var json=eval("("+data+")");
            if( json.code==-3 ) return true;
			return false;
		},
		buttons: $("#button"),
		error: function(jqXHR, textStatus, errorThrown){alert("服务器没有返回数据，可能服务器忙，请重试"+errorThrown);},
		onError : "该昵称已经被占用，请重新输入",
		onWait : "正在对昵称进行合法性校验，请稍候..."
	}).defaultPassed();
	$("#nickNameTip").removeClass("onCorrect").addClass("onShow").html("请输入昵称");
	$("#password").formValidator({onShow:"请输入密码",onFocus:"不能为空由6-16位数字和字母组成",onCorrect:"密码格式正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"密码两边不能有空符号"},onError:"密码不能为空,请确认"}).regexValidator({regExp:"^[a-zA-Z0-9]{6,16}$",onError:"你输入的密码格式不正确"});
	$("#repassword").formValidator({onShow:"请再次输入密码",onFocus:"两次密码必须一致",onCorrect:"密码一致"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"重复密码两边不能有空符号"},onError:"重复密码不能为空,请确认"}).compareValidator({desID:"password",operateor:"=",onError:"2次密码不一致,请确认"});
	$("#invitationCode").formValidator({onShow:"请输入邀请码",onFocus:"不能为空由11~12位数字组成",onCorrect:"邀请码格式正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"邀请码两边不能有空符号"},onError:"邀请码不能为空,请确认"}).regexValidator({regExp:"^[0-9]{11,12}$",onError:"你输入的邀请码格式不正确"});
	$("#phone").formValidator({onShow:"请输入手机号码",onFocus:"手机号码的格式必须正确",onCorrect:"手机号码格式正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"手机号两边不能有空符号"},onError:"手机号不能为空,请确认"}).regexValidator({regExp:"^[1][0-9]{10}$",onError:"你输入的手机号码格式不正确"});
	$("#phoneCode").formValidator({onShow:"请输入手机验证码",onFocus:"输入您获取的手机验证码",onCorrect:"手机验证码已填"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"手机验证码两边不能有空符号"},onError:"手机验证码不能为空,请确认"});
});

	//验证手机号
	function checkPhone(phone){
		var sign=$("#sign").val();
		$.post("${ctx}/user_login/checkRegPhone.htm?phone="+phone+"&sign="+sign,null,function(d){
			var json=eval('('+d+')');
			if(json){
				if(json.code==0){
					$("#phoneTip").attr("class","onError").html(json.result);
					return false;
				}
				if(json.code==-1){
					$("#phoneTip").attr("class","onError").html("该手机号已经注册");
					return false;
				}
			}
		});
	}
	
	//判断手机号、昵称是否存在，不存在则弹出验证码图片窗口
	var sending=false;
	var start=60;
	function sendMsg(){
		var data=$("#regForm").serialize();
		$.post("${ctx}/user_login/sendMsgCheck.htm",data,function(d){
			var json=eval('('+d+')');
			if(json){
				if(json.code==0){
					$("#phoneCodeTip").attr("class","onError").html(json.result);
					return false;
				}
				if(json.code==-1){
					jAlert("对不起，您的网络异常，如有疑问，请联系客服！","提示",function(){});
					return false;
				}	
				if(json.code==-2){
					$("#phone").blur();
					return;
				}
				if(json.code==-3){
					$("#phoneTip").attr("class","onError").html("该手机号已经注册");
					return;
				}
				if(json.code==-4){
					$("#nickNameTip").attr("class","onError").html("该昵称已经被占用，请重新输入");
					return;
				}	
				if(json.code==-5){
					$("#invitationCode").blur();
					return;
				}
				if(json.code==-6){
					$("#invitationCodeTip").attr("class","onError").html("该邀请码不存在");
					return;
				}
				if(json.code==-7){
					$("#invitationCodeTip").attr("class","onError").html("该邀请人手机号不存在");
					return;
				}			
				if(json.code=="ok"){
					var phone=$("#phone").val();
					var messageType=$("#messageType").val();
					$.fancybox.open({
				          href : "${ctx}/user_login/verification.htm?phone="+phone+"&messageType="+messageType,
				          type : 'ajax',
				          padding : 10
					});
				}
			}
		});
	}
	
	function toNext(){
		var f=$("#isAgree").attr("checked");
		if(f!='checked'){
			$.fancybox.open({
	 			href : '${ctx}/user_login/agreeInfoAjax.htm',
	 			type : 'ajax',
	 			padding : 5, 
	 		});
			return false;
		} 
		var data=$("#regForm").serialize();
		$.post("${ctx}/user_login/doRegAjax.htm",data,function(d){
			var json=eval('('+d+')');
			if(json){	
				if(json.code==0){
					jAlert("对不起，"+json.result+"，如有疑问，请联系客服！","提示",function(){
			        });
					return false;
				}	
				if(json.code==-1){
					jAlert("对不起，您的网络异常，如有疑问，请联系客服！","提示",function(){
			        });
					return false;
				}	
				if(json.code==-13){
					jAlert("您的IP已经达到今日的注册上限，注册用户上限为"+json.RegNum+"！","提示",function(){
			        });
					return false;
				}	
				if(json.code==-14){
					jAlert("您的IP注册过于频繁，注册间隔时间为"+json.RegInterval+"分钟！","提示",function(){
			        });
					return false;
				}		
				if(json.code==-2){
					$("#nickName").blur();
					return;
				}
				if(json.code==-3){
					$("#password").blur();
					return;
				}
				if(json.code==-4){
					$("#repassword").blur();
					return;
				}
				if(json.code==-5){
					$("#phone").blur();
					return;
				}
				if(json.code==-6){
					$("#phoneCodeTip").attr("class","onError").html("还没有获取手机验证码");
					return;
				}
				if(json.code==-7){
					$("#phoneCode").blur();
					return;
				}
				if(json.code==-8){
					$("#phoneCodeTip").attr("class","onError").html("手机验证码输入错误，或者手机号已经被注册");
					return;
				}
				if(json.code==-9){
					$("#phoneCodeTip").attr("class","onError").html("手机验证码已过期");
					return;
				}
				if(json.code==-10){
					$("#invitationCode").blur();
					return;
				}
				if(json.code==-11){
					$("#invitationCodeTip").attr("class","onError").html("该邀请码不存在");
					return;
				}
				if(json.code==-12){
					$("#invitationCodeTip").attr("class","onError").html("该邀请人手机号不存在");
					return;
				}
				$("#div1").hide();
				$("#div3").show();
				$("#invita span").append(json.invitaCode);
			}
		});
	}
	
	
</script>
