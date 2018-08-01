<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class=" fucheng" style="width:500px; ">
	<form id="emailForm">
		<div class=" fucheng_title" style="margin-bottom:30px;"><span>${fuUser.isBindEmail==1?'修改':''}绑定邮箱</span><div class="clr"></div></div>
	    <div class="form marg0">
	        	<div class="fl form_font lh">${fuUser.isBindEmail==1?'新':''}邮箱：</div>
	            <div class="fl input lh"><input name="email" id="email" type="text" placeholder="输入邮箱"></div>
	            <div class="clr"></div>
	    </div>
	    <div class="form marg0">
	        	<div class="fl form_font lh">验证码：</div>
	            <div class="fl input yzm"><input name="emailCode" id="emailCode" type="text" placeholder="输入验证码"></div>
	            <a href="javascript:void();" id="msgBtn" onclick="sendMsg();" class="hqyzm0 fl">获取验证码</a>
	            <div class="clr"></div>
	    </div>
	    <div style="text-align:center; margin:30px 0 30px 20px; padding-bottom:30px;">
	        <a href="javascript:void(0);" onclick="saveEmail();" class=" sure">确认</a>
	        <a href="javascript:void(0);" onclick="$.fancybox.close();" class=" cancel">取消</a>
	    </div>
	</form>
</div>
<script>
	var sending=false;
	var start=30;
	var sub=false;
	function saveEmail(){
		if(sub)
			return;
		sub=true;
		var data=$("#emailForm").serialize();
		$.post("${ctx}/user_center/saveBindEmailAjax.htm",data,function(d){
			sub=false;
			if(d==-2){
				$.alerts.okButton='确定';
				jAlert('请输入邮箱,且注意邮箱格式！','提示',function(){
					$("#email").focus();
				});
				return false;
			}
			if(d==-3){
				$.alerts.okButton='确定';
				jAlert('请输入邮箱验证码！','提示',function(){
					$("#emailCode").focus();
				});
				return false;
			}
			if(d==-4){
				$.alerts.okButton='确定';
				jAlert('还没有获取邮箱验证！','提示',function(){
					$("#emailCode").focus();
				});
				return false;
			}
			if(d==-5){
				$.alerts.okButton='确定';
				jAlert('验证码错误！','提示',function(){
					$("#emailCode").focus();
				});
				return false;
			}
			location=location;
		});
	}
	function sendMsg(){
		if(sub)
			return;
		sub=true;
		if(sending)
			return
		var data=$('#emailForm').serialize();
		$.post('${ctx}/user_center/sendEmailCodeAjax.htm',data,function(d){
			sub=false;
			if(d==-2){
				$.alerts.okButton='确定';
				jAlert('请输入邮箱,且注意邮箱格式！','提示',function(){
					$("#email").focus();
				});
				return false;
			}
			if(d==-3){
				$.alerts.okButton='确定';
				jAlert('该邮箱已经被占用了！','提示',function(){
					$("#email").focus();
				});
				return false;
			}
			sending=true;
			setTimeout(countTime,1000);
			$('#msgBtn').css({color:'#666666'});
			$('#msgBtn').text('已发送(30)');
		});
	}
	function countTime(){
		start--;
		if(start<0){
			$('#msgBtn').css({color:'#666666'});
			$('#msgBtn').text("获取邮箱验证码");
			sending=false;
			start=30;
			return;
		}
		$('#msgBtn').text('已发送('+start+')');
		setTimeout(countTime,1000);
	}
</script>