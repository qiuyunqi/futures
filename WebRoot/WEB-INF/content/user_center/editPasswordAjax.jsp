<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class=" fucheng" style="width:500px; ">
	<form id="passwordForm">
		<div class=" fucheng_title" style="margin-bottom:30px;"><span>修改登录密码</span><div class="clr"></div></div>
	    
	    <div class="form marg0">
	        	<div class="fl form_font lh">原登录密码：</div>
	            <div class="fl input"><input name="oldPwd" id="oldPwd" type="password" placeholder="输入原登录密码"></div>
	            <div class="clr"></div>
	    </div>
	    <div class="form marg0">
	        	<div class="fl form_font lh">新登录密码：</div>
	            <div class="fl input"><input name="newPwd" id="newPwd" type="password" placeholder="输入新登录密码"></div>
	            <div class="clr"></div>
	    </div>
	    <div class="form marg0">
	        	<div class="fl form_font lh">确认登录密码：</div>
	            <div class="fl input"><input name="rePwd" id="rePwd" type="password" placeholder="确认登录密码"></div>
	            <div class="clr"></div>
	    </div>
	    <div style="text-align:center; margin:30px 0 0 20px; padding-bottom:30px;">
	        <a href="javascript:void(0);" onclick="savePassword();" class=" sure">确认</a>
	        <a href="javascript:void(0);" onclick="$.fancybox.close();" class=" cancel">取消</a>
	    </div>
    </form>
</div>
<script>
	function savePassword(){
		var data=$("#passwordForm").serialize();
		$.post("${ctx}/user_center/savePasswordAjax.htm",data,function(d){
			if(d==-2){
				$.alerts.okButton='确定';
				jAlert('请输入原登录密码！','提示',function(){
					$("#oldPwd").focus();
				});
				return false;
			}
			if(d==-3){
				$.alerts.okButton='确定';
				jAlert('新登录密码格式：6位以上数字，字母组成！','提示',function(){
					$("#newPwd").focus();
				});
				return false;
			}
			if(d==-4){
				$.alerts.okButton='确定';
				jAlert('请输入确认密码！','提示',function(){
					$("#rePwd").focus();
				});
				return false;
			}
			if(d==-5){
				$.alerts.okButton='确定';
				jAlert('新密码和确认密码不一致！','提示',function(){
					$("#rePwd").focus();
				});
				return false;
			}
			if(d==-6){
				$.alerts.okButton='确定';
				jAlert('原登录密码输入错误！','提示',function(){
					$("#oldPwd").focus();
				});
				return false;
			}
			$.alerts.okButton='确定';
			jAlert('登陆密码修改成功，请牢记新的登陆密码！','提示',function(){
				location.href=location.href;
			});
		});
	}
</script>
<%-- <div class=" fucheng" style="width:500px; ">
	<form id="passwordForm">
		<div class=" fucheng_title" style="margin-bottom:30px;"><span>修改登录密码</span><div class="clr"></div></div>
	    <div class="form marg0">
	        	<div class="fl form_font lh">手机号码：</div>
	            <div class="fl input lh"><input name="phone" id="phone" value="${fuUser.phone}" type="text" placeholder="手机号码" readonly="readonly"></div>
	            <div class="clr"></div>
	    </div>
	    <div class="form marg0">
	        	<div class="fl form_font lh">验证码：</div>
	            <div class="fl input yzm"><input name="phoneCode" id="phoneCode" type="text" placeholder="验证码"></div>
	            <a href="javascript:void();" id="msgBtn" onclick="sendMsg();" class="hqyzm0 fl">获取验证码</a>
	            <div class="clr"></div>
	    </div>
	    <div class="form marg0">
	        	<div class="fl form_font lh">新登录密码：</div>
	            <div class="fl input lh"><input name="newPwd" id="newPwd" type="password" placeholder="输入新登录密码"></div>
	            <div class="clr"></div>
	    </div>
	    <div class="form marg0">
	        	<div class="fl form_font lh">确认登录密码：</div>
	            <div class="fl input lh"><input name="rePwd" id="rePwd" type="password" placeholder="确认登录密码"></div>
	            <div class="clr"></div>
	    </div>
	    <div style="text-align:center; margin:30px 0 0 20px; padding-bottom:30px;">
	        <a href="javascript:void(0);" onclick="savePassword();" class=" sure">确认</a>
	        <a href="javascript:void(0);" onclick="$.fancybox.close();" class=" cancel">取消</a>
	    </div>
    </form>
</div>
<script>
	function savePassword(){
		var data=$("#passwordForm").serialize();
		$.post("${ctx}/user_center/savePasswordAjax.htm",data,function(d){
			if(d==-3){
				$.alerts.okButton='确定';
				jAlert('请输入手机验证码！','提示',function(){
					$("#phoneCode").focus();
				});
				return false;
			}
			if(d==-4){
				$.alerts.okButton='确定';
				jAlert('还没有获取手机验证！','提示',function(){
					$("#phoneCode").focus();
				});
				return false;
			}
			if(d==-5){
				$.alerts.okButton='确定';
				jAlert('验证码错误！','提示',function(){
					$("#phoneCode").focus();
				});
				return false;
			}
			if(d==-6){
				$.alerts.okButton='确定';
				jAlert('新登录密码格式：6位以上数字字母组成！','提示',function(){
					$("#newPwd").focus();
				});
				return false;
			}
			if(d==-7){
				$.alerts.okButton='确定';
				jAlert('请输入确认密码！','提示',function(){
					$("#rePwd").focus();
				});
				return false;
			}
			if(d==-8){
				$.alerts.okButton='确定';
				jAlert('新密码和确认密码不一致！','提示',function(){
					$("#rePwd").focus();
				});
				return false;
			}
			if(d==-9){
				$.alerts.okButton='确定';
				jAlert('验证码已过期！','提示',function(){
					$("#phoneCode").focus();
				});
				return false;
			}
			$.alerts.okButton='确定';
			jAlert('登陆密码修改成功，请牢记新的登陆密码！','提示',function(){
				location.href=location.href;
			});
		});
	}
	
	function sendMsg(){
		var data=$("#passwordForm").serialize();
		$.post("${ctx}/user_center/sendLoginMsgAjax.htm",data,function(d){
			var json=eval('('+d+')');
			if(json){
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
			$('#msgBtn').text("获取验证码");
			$("#msgBtn").attr("onclick", "sendMsg();");
			start=30;
			return;
		}
		$('#msgBtn').text('已发送('+start+')');
		setTimeout(countTime,1000);
		if(start>0){
			$("#msgBtn").attr("onclick", "");
		}
	}
</script> --%>