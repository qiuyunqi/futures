<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class="fuchen" style="width:700px;">
	<div class=" fc_top" style="width:700px;"> 
    	<b class="fl fc_top_font">修改APP模板</b>
        <div class="fl"></div>
    </div>
        <form id="tempForm">
        <input type="hidden" value="${tempId}"/>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">模板详情：</div>
            <div class="fl input"><input id="template" name="template" value="${appTemp.template}" type="text" style="width:300px;" placeholder="请输入模块编号，用英文逗号分隔"></div>
            <div class="clr"></div>
        </div>
        <div class=" but" style="margin-bottom:-20px;"><a href="javascript:void(0);" onclick="saveAppTemp();" class="sure fl">确认</a><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
		</form>
</div>
<script>
function saveAppTemp(){	
	var data = $('#tempForm').serialize();
	$.post('${ctx}/app_manage/saveUpdateAppTemp.htm',data,function(d){
        if(d==-1){
        	jAlert("请填写模板详情","提示",function(){
	  			$("#template").focus();
	        });
            return null;
        }
       	jAlert("保存成功！","提示",function(){
   			location.href = location.href;
        });
	});	
}
</script>
