<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class="fuchen" style="width: 600px">
<form id="msgForm">
		<div class=" fc_top" style="width: 600px">
	    	<b class="fl fc_top_font">发送短信</b>
	        <div class="fl"></div>
	    </div>
	    <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">大赛编号：</div>
            <div class="fl textarea">
            <select id="numSelect" style="width:80px;">
            	<option value="1">1期</option>
            	<option value="2">2期</option>
            	<option value="3">3期</option>
            	<option value="4">4期</option>
            	<option value="5">5期</option>
            </select>
            </div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">短信内容：</div>
            <div class="fl textarea">
            <textarea id="content" cols="8" rows="6">您报名的“小合杯”股指模拟大赛现已开赛，请不要忘记参赛哟。如需帮助，请拨打客服电话咨询010-53320537</textarea>
            </div>
            <div class="clr"></div>
        </div>
     	<div class=" but"><a href="javascript:void(0);" onclick="saveInfo($('#content').val());" class="sure fl">确认</a><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
</form>
</div>
<script>
function saveInfo(cont){
	jConfirm("确定发送短信？","操作提示",function(res){
		if(res){
			var data=$("#msgForm").serialize();
			var content = encodeURI(cont, "UTF-8"); 
			var competitionNum=$("#numSelect").val();
			$.post("${ctx}/admin_op_game/saveSendGameMsgAjax.htm?content="+content+"&competitionNum="+competitionNum,data,function(d){
				if(d==-1){
					sureInfo("确定","请输入短信内容！","提示");
					return false;
				}
				jAlert("短信发送成功！","提示",function(){
					location.href=location.href;
				});
			});
		}
	});
}
</script>
