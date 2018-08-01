<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="head">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="../common/meta.jsp" %>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<title>${title}－账户登录</title>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/css.jsp" %>
<link href="${ctx}/css/qihuo.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/formValidator-4.0.1.js"></script>
<script type="text/javascript" src="${ctx}/js/formValidatorRegex.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/style/validator.css"></link>
<link  href="${ctx}/css/top_hrr.css" rel="stylesheet" type="text/css" />
<c:set var="jsptype" value="userLogin"/>
<style>
.onShow{
	font-size: 12px;
	margin-left:5px;
	width: 250px;
}
.onFocus{
	font-size: 12px;
	margin-left:5px;
	width: 250px;
}
.onCorrect{
	font-size: 12px;
	margin-left:5px;
	width: 250px;
}
.onLoad{
	font-size: 12px;
	margin-left:5px;
	width: 250px;
}
.onError{
	font-size: 12px;
	margin-left:5px;
	width: 250px;
}
body{background: #ededed;}
</style>
</head>
<body>
<%@include file="../common/qqConsult.jsp" %>
<%@include file="../common/top.jsp" %>
<%@include file="../common/userTop.jsp" %>
<form id="loginForm" name="loginForm">
<div class=" mfzc_bg">
	<div class="mfzc_bg_cont">
	<div class=" mfzc_matter">
	  		<div class="userReg"><a class="userRegZc" href="${ctx}/login">登录</a><a class="logi" href="${ctx}/register" style="margin-left:-5px;">邀请注册</a></div>
	  		<div class="biaodan zhuce_tbl">
	            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tbody>
					    <tr>
					      <td>
					        <span class="userRegTitl">手机号码</span><div class="fl wbk"><input name="accountName" id="accountName" value="${accountName}" type="text" placeholder="用户名/手机号码" /></div>
					        <div style="font-size: 12px" class="onShow" id="accountNameTip" ></div>
					      </td>
					    </tr>
					    <tr>
					      <td>
					        <span class="userRegTitl">登录密码</span><div class="fl wbk"><input name="password" id="password" value="${password}" type="password" placeholder="密码" /></div>
					        <div style="font-size: 12px" class="onShow" id="passwordTip" ></div>
					       </td>
					    </tr>
					    <tr>
					      <td style="padding:0"><div class=" dl_cont0" style="margin:10px 0 0 90px;">
					        	<input style="float:left;" name="rememberAccount" type="checkbox" <c:if test="${!empty accountName}">checked="checked"</c:if> value="1"><span style="margin-right:20px;display:block;height:13px;line-height:13px;float:left;">记住账号</span>
					            <!-- <input name="rememberPwd" type="checkbox" <c:if test="${!empty password}">checked="checked"</c:if> value="1"><span>记住密码</span> -->
					            <a style=" color:#b30000;margin-left: 10px;display:block;height:13px;line-height:13px;" href="${ctx}/user_login/findPwd.htm">忘记密码</a>
					        </div></td>
					      <td></td>
					    </tr>
					  </tbody>
					</table>
	
	            </div>
	            <input type="submit" id="login" onclick="toLogin();return false;" class="nextReg" value="登录" >
	            <div class="clr"></div>
	            <div id="load" class="loginLoad-One"><img src="../images_hhr/loading.gif"/></div>
	</div>
    </div>
</div>
</form>
<div class="downban"></div>
<%@include file="../common/footer.jsp" %>
</body>
</html>
<script type="text/javascript">
//网页内按下回车触发
$(document).keypress(function(e) { 
     // 回车键事件 
     if(e.keyCode == 13) { 
  	   $("#login").click(); 
     } 
}); 


$(document).ready(function(){
	$.formValidator.initConfig({formID:"loginForm",debug:false,submitOnce:true,
		onError:function(msg,obj,errorlist){
		},
		submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
	});
	
		$("#accountName").formValidator({onShow:"请输入账号或手机号或邮箱",onFocus:"可以用账号或手机号或邮箱登录",onCorrect:"该账号存在"})
			.inputValidator({min:1,onError:"账号不能为空,请确认"})
			    .ajaxValidator({
				dataType : "text",
				async : true,
				url : "${ctx}/user_login/checkAllAccouontAjax.htm",
				success : function(data){
					var json=eval("("+data+")");
					
		            if( json.code==1 ) return true;
					return false;
				},
				buttons: $("#button"),
				error: function(jqXHR, textStatus, errorThrown){alert("服务器没有返回数据，可能服务器忙，请重试"+errorThrown);},
				onError : "该账号不存在，请重新确认",
				onWait : "正在对账号进行合法性校验，请稍候..."
			}).defaultPassed();
	$("#accountNameTip").removeClass("onCorrect").addClass("onShow").html("请输入账号或手机号或邮箱");
	$("#password").formValidator({onShow:"请输入密码",onFocus:"至少1个长度",onCorrect:"密码合法"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false,emptyError:"密码两边不能有空符号"},onError:"密码不能为空,请确认"});
	
});


$("#load").hide();
function toLogin(){
	var data=$("#loginForm").serialize();
	$.post("${ctx}/user_login/doLoginAjax.htm",data,function(d){
		var json=eval("("+d+")");
		if(json.code==-1){
			jAlert("对不起，您的网络异常，如有疑问，请联系客服！","提示",function(){
	        });
			return false;
		}	
		if(json.code==-2){
			$("#accountName").blur();
			return null;
		}
		if(json.code==-3){
			$("#password").blur();
			return null;
		}
		if(json.code==-4){
			$("#accountName").blur();
			return null;
		}
		if(json.code==-5){
			$("#passwordTip").attr("class","onError").html("密码错误");
			return null;
		}
		if(json.code==1){
			if('${loginBackUrl}'){
				location.href='${loginBackUrl}';
			}else{
			    $("#load").show();
				var jsIframe = document.createElement("iframe");
                jsIframe.style.display = "none";//把jsIframe隐藏起来
                document.body.appendChild(jsIframe);
                with(window.frames[window.frames.length - 1]){
	                document.open();
	                document.write(json.ucsynlogin); //执行JS代码
	                document.close();
                }
                window.setTimeout(function(){location.href="${ctx}/user_center/centerIndex.htm"},1000);
			}
		}
	});
}
</script>
