<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<style>
.form_cont{margin-bottom:10px;}
.lf_font{width:150px;}
</style>
<div class="fuchen" style="width:600px">
		<div class=" fc_top" style="width:600px">
	    	<b class="fl fc_top_font">修改找劵信息</b>
        <div class="fl"></div>
	    </div>
	   <form id="wqqForm">
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">标题：</div>
            <div class="fl input"><input id="title" name="title" placeholder="必填" value="${stockPublish.title}" type="text" style="width:278px;"/></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">描述：</div>
            <div class="fl textarea"><textarea id="descrip" name="description" cols="" rows="7">${stockPublish.description}</textarea></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
	            <div class="lf_font fl">是否删除：</div>
		            <div class="fl select">
		            <select id="isDel" name="isDel" style="width:178px;">
		            	<option <c:if test="${stockPublish.isDel==0}">selected="selected"</c:if> value="0">已删除</option>
					    <option <c:if test="${stockPublish.isDel==1}">selected="selected"</c:if> value="1">未删除</option>
		            </select></div>
	            <div class="clr"></div>
        </div>
        <div class=" but" style="margin-bottom:-20px;"><domi:privilege url="/admin_op_wxyqb/saveAddPublish.htm"><a href="javascript:void(0);" onclick="saveContents();" class="sure fl">确认</a></domi:privilege><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
		</form>
</div>
<script>
function saveContents(){
	if(!$("#title").val()){
		jAlert("标题不能为空！","提示",function(){
			$("#title").focus();
        });
		return false;
	}
	if(!$("#descrip").val()){
		jAlert("描述不能为空！","提示",function(){
			$("#descrip").focus();
        });
		return false;
	}
	var data=$("#wqqForm").serialize();
	$.post("${ctx}/admin_op_wxyqb/saveAddPublish.htm?id=${id}",data,function(d){
		jAlert("保存成功！","提示",function(){
			location.href=location.href;
		});
	});
}
</script>
