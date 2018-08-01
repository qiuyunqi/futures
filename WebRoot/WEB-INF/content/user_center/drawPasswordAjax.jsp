<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>

<div class=" fucheng" style="width:500px; ">
	<form id="drawPasswordForm">
		<div class=" fucheng_title" style="margin-bottom:30px;"><span>${empty fuUser.drawPassword?'设置':'修改'}提款密码</span><div class="clr"></div></div>
	    <c:if test="${!empty fuUser.drawPassword}">
	    	<div class="form marg0">
	        	<div class="fl form_font lh">原提款密码：</div>
	            <div class="fl input"><input name="oldDrawPwd" id="oldDrawPwd" type="password" placeholder="原提款密码"></div>
	            <div class="clr"></div>
	    	</div>
	    </c:if>
	    
	    <div class="form marg0">
	        	<div class="fl form_font lh">${empty fuUser.drawPassword?'':'新'}提款密码：</div>
	            <div class="fl input"><input name="newDrawPwd" id="newDrawPwd" type="password" placeholder="新提款密码（6位数字）"></div>
	            <div class="clr"></div>
	    </div>
	    <div class="form marg0">
	        	<div class="fl form_font lh">确认提款密码：</div>
	            <div class="fl input"><input name="reDrawPwd" id="reDrawPwd" type="password" placeholder="确认提款密码"></div>
	            <div class="clr"></div>
	    </div>
	    <div style="text-align:center; margin:30px 0 0 20px; padding-bottom:30px;">
	        <a href="javascript:void(0);" onclick="saveDrawPassword();" class=" sure">确认</a>
	        <a href="javascript:void(0);" onclick="$.fancybox.close();" class=" cancel">取消</a>
	    </div>
    </form>
</div>
<script>
	function saveDrawPassword(){
		var data=$("#drawPasswordForm").serialize();
		$.post("${ctx}/user_center/saveDrawPasswordAjax.htm",data,function(d){
			if(d==-2){
				$.alerts.okButton='确定';
				jAlert('请输入原提款密码！','提示',function(){
					$("#oldDrawPwd").focus();
				});
				return false;
			}
			if(d==-3){
				$.alerts.okButton='确定';
				jAlert('提款密码格式：6位数字组成！','提示',function(){
					$("#newDrawPwd").focus();
				});
				return false;
			}
			if(d==-4){
				$.alerts.okButton='确定';
				jAlert('请输入确认密码！','提示',function(){
					$("#reDrawPwd").focus();
				});
				return false;
			}
			if(d==-5){
				$.alerts.okButton='确定';
				jAlert('新密码和确认密码不一致！','提示',function(){
					$("#reDrawPwd").focus();
				});
				return false;
			}
			if(d==-6){
				$.alerts.okButton='确定';
				jAlert('原提款密码输入错误！','提示',function(){
					$("#oldDrawPwd").focus();
				});
				return false;
			}
			$.alerts.okButton='确定';
			jAlert('提款密码修改成功，请牢记新的提款密码！','提示',function(){
				location.href=location.href;
			});
		});
	}
</script>