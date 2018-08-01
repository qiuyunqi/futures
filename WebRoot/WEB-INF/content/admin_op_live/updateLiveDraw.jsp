<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class="fuchen" style="width: 600px">
		<div class=" fc_top" style="width: 600px">
	    	<b class="fl fc_top_font">${empty id?'添加':'修改'}抽奖信息</b>
        <div class="fl"></div>
	    </div>
	   <form id="liveForm">
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">今日问题：</div>
            <div class="fl textarea"><textarea id="question" placeholder="必填" style="height:100px;">${liveDraw.question}</textarea></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">竞猜开始时间：</div>
            <div class="fl input"><input id="beginTime" name="beginTime" value="<fmt:formatDate value="${liveDraw.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}', dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" placeholder="" style="width:150px;"><i class="riqi"></i></div>
            <div class="clr"></div>
        </div>
         <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">竞猜结束时间：</div>
            <div class="fl input"><input id="endTime" name="endTime" value="<fmt:formatDate value="${liveDraw.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}', dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" placeholder="" style="width:150px;"><i class="riqi"></i></div>
            <div class="clr"></div>
        </div>
        <div class=" but" style="margin-bottom:-20px;"><domi:privilege url="/admin_op_live/saveLiveDraw.htm"><a href="javascript:void(0);" onclick="saveLive($('#question').val());" class="sure fl">确认</a></domi:privilege><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
		</form>
</div>
<script>
function saveLive(question){
	if(!$("#question").val()){
		jAlert("今日问题不能为空！","提示",function(){
			$("#question").focus();
        });
		return false;
	}
	if(!$("#beginTime").val()){
		jAlert("竞猜开始时间不能为空！","提示",function(){
			$("#beginTime").focus();
        });
		return false;
	}
	if(!$("#endTime").val()){
		jAlert("竞猜结束时间不能为空！","提示",function(){
			$("#endTime").focus();
        });
		return false;
	}
	var data=$("#liveForm").serialize();
	$.post("${ctx}/admin_op_live/saveLiveDraw.htm?id=${id}&question="+question,data,function(d){
		jAlert("抽奖信息保存成功！","提示",function(){
			location.href=location.href;
		});
	});
}
</script>
