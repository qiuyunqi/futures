<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class="fuchen" style="width:500px;">
	<div class=" fc_top" style="width:500px;"> 
    	<b class="fl fc_top_font">重置用户登录密码</b>
        <div class="fl"></div>
    </div>
        <form id="passwordForm">
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">新登录密码：</div>
            <input name="userId" id="userId" type="hidden" value="${fuUser.id}"/>
            <div class="fl input"><input name="newPassword" id="newPassword" type="password" placeholder="输入新登录密码"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">确认登录密码：</div>
            <div class="fl input"><input name="rePassword" id="rePassword" type="password" placeholder="确认登录密码"></div>
            <div class="clr"></div>
        </div>
        <div class=" but" style="margin-bottom:-20px;">
        <domi:privilege url="/admin_list_user/saveUserPasswordAjax.htm">
        <a href="javascript:void(0);" onclick="saveResetPassword();" class="sure fl">确认</a>
        </domi:privilege>
        <a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
		</form>
</div>
<script>
function saveResetPassword(){
	var newPwd = $("#newPassword").val();
	var reg = /^(?=.*[A-Z])(?=.*\d)(?=.*[~!@#$%^&*()_+`\-={}:";'<>?,.\/]).{6,16}$/;  
	var flag = reg.test(newPwd);  
	if(flag == false){  
		$.alerts.okButton='确定'; 
		jAlert('新登录密码格式：6~16位，至少包含一个大写字母和一个特殊字符！','提示',function(){
			$("#newPassword").focus();
		});       
		return false;     
	}
	var data=$("#passwordForm").serialize();
	$.post("${ctx}/admin_list_user/saveUserPasswordAjax.htm",data,function(d){
		if(d==-4){
			$.alerts.okButton='确定';
			jAlert('请输入确认密码！','提示',function(){
				$("#rePassword").focus();
			});
			return false;
		}
		if(d==-5){
			$.alerts.okButton='确定';
			jAlert('新密码和确认密码不一致！','提示',function(){
				$("#rePassword").focus();
			});
			return false;
		}
		$.alerts.okButton='确定';
		jAlert('登录密码重置成功，请牢记新的登录密码！','提示',function(){
			location.href=location.href;
		});
	});
}
</script>
