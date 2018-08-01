<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class="fuchen" style="width: 600px">
		<div class=" fc_top" style="width: 600px">
	    	<b class="fl fc_top_font">公布抽奖答案</b>
        <div class="fl"></div>
	    </div>
	   <form id="liveForm">
	   	<div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">发奖金额：</div>
            <div class="fl input"><input type="text" id="money" name="money" placeholder="必填" /></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">昨日答案：</div>
            <div class="fl input"><input type="text" id="answer" name="answer" placeholder="必填" /></div>
            <div class="clr"></div>
        </div>
        <div class=" but" style="margin-bottom:-20px;"><domi:privilege url="/admin_op_live/savePublishAnswer.htm"><a href="javascript:void(0);" onclick="saveLive();" class="sure fl">确认</a></domi:privilege><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
		</form>
</div>
<script>
function saveLive(){
	if(isNaN($("#money").val()) || parseInt($("#money").val())<0 || !$("#money").val()){
		jAlert("发奖金额只能是正数！","提示",function(){
			$("#money").focus();
        });
		return false;
	}
	if(isNaN($("#answer").val()) || parseInt($("#answer").val())<0 || !$("#answer").val()){
		jAlert("昨日答案只能是正数！","提示",function(){
			$("#answer").focus();
        });
		return false;
	}
	var data=$("#liveForm").serialize();
	$.post("${ctx}/admin_op_live/savePublishAnswer.htm?id="+${id},data,function(d){
		jAlert("公布抽奖答案成功！","提示",function(){
			location.href=location.href;
		});
	});
}
</script>
