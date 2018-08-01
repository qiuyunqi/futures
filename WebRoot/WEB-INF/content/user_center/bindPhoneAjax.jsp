<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class=" fucheng" style="width:500px; ">
	<form id="phoneForm">
		<div class=" fucheng_title" style="margin-bottom:30px;"><span>${!empty fuUser.phone?'修改':''}绑定手机</span><div class="clr"></div></div>
	    <div class="form marg0">
	        	<div class="fl form_font lh">${!empty fuUser.phone?'新':''}手机：</div>
	            <div class="fl input lh"><input name="phone" id="phone" type="text" placeholder="输入手机号码"></div>
	            <div class="clr"></div>
	    </div>
	    <div class="form marg0">
	        	<div class="fl form_font lh">验证码：</div>
	            <div class="fl input yzm"><input name="phoneCode" id="phoneCode" type="text" placeholder="输入验证码"></div>
	            <a href="javascript:void();" id="msgBtn" onclick="sendMsg();" class="hqyzm0 fl">获取验证码</a>
	            <div class="clr"></div>
	    </div>
	    <div style="text-align:center; margin:30px 0 30px 20px; padding-bottom:30px;">
	        <a href="javascript:void(0);" onclick="savePhone();" class=" sure">确认</a>
	        <a href="javascript:void(0);" onclick="$.fancybox.close();" class=" cancel">取消</a>
	    </div>
	</form>
</div>
<script>
	var sending=false;
	var start=30;
	var sub=false;
	function savePhone(){
		if(sub)
			return;
		sub=true;
		var data=$("#phoneForm").serialize();
		$.post("${ctx}/user_center/saveBindPhoneAjax.htm",data,function(d){
			sub=false;
			if(d==-2){
				$.alerts.okButton='确定';
				jAlert('请输入手机号码,且注意手机号码格式！','提示',function(){
					$("#phone").focus();
				});
				return false;
			}
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
			location=location;
		});
	}
	function sendMsg(){
		if(sub)
			return;
		sub=true;
		if(sending)
			return
		var data=$('#phoneForm').serialize();
		$.post('${ctx}/user_center/sendMsgAjax.htm',data,function(d){
			sub=false;
			var json=eval('('+d+')');
			if(json){
				if(json.code==-2){
					$.alerts.okButton='确定';
					jAlert('请输入手机号码,且注意手机号码格式！','提示',function(){
						$("#phone").focus();
					});
					return false;
				}
				if(json.code==-3){
					$.alerts.okButton='确定';
					jAlert('该手机号码已经被占用了！','提示',function(){
						$("#phone").focus();
					});
					return false;
				}
				sending=true;
				setTimeout(countTime,1000);
				$('#msgBtn').css({color:'#ccc'});
				$('#msgBtn').text('已发送(30)');
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
			$('#msgBtn').text("获取手机验证码");
			sending=false;
			start=30;
			return;
		}
		$('#msgBtn').text('已发送('+start+')');
		setTimeout(countTime,1000);
	}
</script>