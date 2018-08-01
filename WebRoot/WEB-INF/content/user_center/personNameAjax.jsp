<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class=" fucheng" style="width:500px;">
	<form id="nameForm">
		<div class=" fucheng_title"><span>实名认证</span><div class="clr"></div></div>
	    <div class=" smrz">
	    	<div class="fl"><img src="../qihuo_images/gth_03.png" width="52" height="52"></div>
		        <div class="fl smrz_cont" style="width: 300px">为了您的投资及资金安全，请先实名认证实名信息提交后不可修改，出金银行卡必须是本人名字。如遇到问题，请在工作时间联系客服010-53320537</br></div>
				<div class="clr"></div>
		    </div>
	    <div class="form marg">
	        	<div class="fl form_font lh">用户名：</div>
	            <div class="fl input lh">${fuUser.accountName}</div>
	            <div class="clr"></div>
	    </div>
	    <div class="form marg">
	        	<div class="fl form_font lh">真实姓名：</div>
	            <div class="fl input"><input name="userName" id="userName" value="${fuUser.userName}" type="text"></div>
	            <div class="clr"></div>
	    </div>
	    <div class="form marg">
	        	<div class="fl form_font lh">身份证号：</div>
	            <div class="fl input lh"><input name="cardNumber" id="cardNumber" value="${fuUser.cardNumber}" type="text"></div>
	            <div class="clr"></div>
	    </div>
	    <div class="form marg" style="margin-left: 70px">
	    	<input id="agree" name="agree" type="checkbox" value="1">&nbsp;&nbsp;本人确认以上信息真实有效
	    </div>
	    <div style="text-align:center; margin:30px 0 20px 20px;">
	        <a href="javascript:void(0);" onclick="saveName(${empty flag?1:0});" class=" sure">${empty flag?'下一步':'确认'}</a>
	        <a href="javascript:void(0);" onclick="$.fancybox.close();" class=" cancel">取消</a>
	    </div>
    </form>
</div>
<script>
	function saveName(fg){
		var c=$("#agree").attr("checked");
		if(c!='checked'){
			$.alerts.okButton="确认";
			$.alerts.cancelButton="返回";
			jConfirm("您确认以上信息真实有效吗！","确认提示",function(res){
				if(res){
					$("#agree").attr("checked","checked");
					return false;
				}
			});
			return false;
		}
		var f=IdCardValidate($('#cardNumber').val());
		if(!f){
			$.alerts.okButton='确定';
			jAlert('身份证号码格式错误！','提示',function(){
				$("#cardNumber").focus();
			});
			return false;
		}
		var ff=isChineseChar($('#userName').val());
		if(!ff){
			$.alerts.okButton='确定';
			jAlert('真实姓名只能由2个或2个以上中文字符组成！','提示',function(){
				$("#userName").focus();
			});
			return false;
		}
		
		var data=$("#nameForm").serialize();
		$.post("${ctx}/user_center/savePersonNameAjax.htm",data,function(d){
			if(d==-2){
				$.alerts.okButton='确定';
				jAlert('请输入真实姓名！','提示',function(){
					$("#userName").focus();
				});
				return false;
			}
			if(d==-3){
				$.alerts.okButton='确定';
				jAlert('请输入身份证号码！','提示',function(){
					$("#cardNumber").focus();
				});
				return false;
			}
			if(d==-5){
				$.alerts.okButton='确定';
				jAlert('身份证已被其他用户认证，请更换！','提示',function(){
					$("#cardNumber").focus();
				});
				return false;
			}
			$.fancybox.close();
			if(fg==1){
				$.fancybox.open({
		 			href : '${ctx}/user_center/personCardAjax.htm',
		 			type : 'ajax',
		 			padding : 5 
		 		});
			}else{
				eval('${methodName}()');
			}
			
		});
	}
</script>