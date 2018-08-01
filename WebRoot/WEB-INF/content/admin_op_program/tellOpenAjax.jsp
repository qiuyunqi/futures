<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class="fuchen" style="width: 600px">
<form id="badForm">
	<div class=" fc_top" style="width: 600px">
    	<b class="fl fc_top_font">告知开户</b>
        <div class="fl"></div>
    </div>
    
    	<div class="form_cont form_cont0">
    		<div class="form_cont form_cont0">
            <div class="lf_font fl">收件人：</div>
            <div class="fl input none">${program.fuUser.phone}</div>
            <div class="clr"></div>
        </div>
    	<div class="form_cont form_cont0">
    		<div class="form_cont form_cont0">
            <div class="lf_font fl">内容：</div>
            <div class="fl textarea" style="width:295px;margin-top:10px;">
            <c:if test="${program.fuServer.clientType==1}">
            	<textarea rows="4" cols="4" id="msg">您的账户已开通。软件类型：众期资管平台；${program.fuServer.serverName}；交易账户：${program.tradeAccount}，密码是您手机号后6位，请尽快修改密码。</textarea>
			</c:if>
			<c:if test="${program.fuServer.clientType==2}">
            	<textarea rows="4" cols="4" id="msg">您的账户已开通。软件类型：博易大师－鑫管家版；交易账户：${program.tradeAccount}，密码是您手机号后6位，请尽快修改密码。</textarea>
			</c:if>
			<c:if test="${program.fuServer.clientType==3}">
            	<textarea rows="4" cols="4" id="msg">您的账户已开通。软件类型：博易大师－金牛版；交易账户：${program.tradeAccount}，密码是您手机号后6位，请尽快修改密码。</textarea>
			</c:if>
			</div>
            <div class="clr"></div>
        </div>
     <div class=" but"><a href="javascript:void(0);" onclick="sendMessage(${id}, $('#msg').val());" id="btn" class="sure fl">确认</a><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
</form>
</div>


<script>
	function sendMessage(programId, msg){
		$("#btn").text("短信发送中...");
		var smsg = encodeURI(msg, "UTF-8"); 
		$.post('${ctx}/admin_op_program/sendMsgAjax.htm?id='+programId+'&msg='+smsg,null,function(d){
			sub=false;
			$("#btn").text("确认");
			if(d==-1){
				sureInfo("确定","您没有操作权限！","提示");
				return false;
			}
			$.alerts.okButton="确定";
			jAlert("短信发送成功！","提示",function(){
				location.href=location.href;
			});
		});
	}
</script>
