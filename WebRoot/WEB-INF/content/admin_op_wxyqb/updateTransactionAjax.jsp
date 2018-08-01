<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<style>
.form_cont{margin-bottom:10px;}
.lf_font{width:150px;}
</style>
<div class="fuchen" style="width:600px">
		<div class=" fc_top" style="width:600px">
	    	<b class="fl fc_top_font">修改交易员信息</b>
        <div class="fl"></div>
	    </div>
	   <form id="wqqForm">
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">团队名称：</div>
            <div class="fl input"><input id="tdname" name="name" placeholder="必填" value="${transac.name}" type="text"/></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
	            <div class="lf_font fl">是否删除：</div>
		            <div class="fl select">
		            <select id="isDel" name="isDel" style="width:178px;">
		            	<option <c:if test="${transac.isDel==0}">selected="selected"</c:if> value="0">已删除</option>
					    <option <c:if test="${transac.isDel==1}">selected="selected"</c:if> value="1">未删除</option>
		            </select></div>
	            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
	            <div class="lf_font fl">状态：</div>
		            <div class="fl select">
		            <select id="isVerification" name="isVerification" style="width:178px;">
		            	<option <c:if test="${transac.isVerification==0}">selected="selected"</c:if> value="0">未审核</option>
		            	<option <c:if test="${transac.isVerification==1}">selected="selected"</c:if> value="1">通过</option>
		            	<option <c:if test="${transac.isVerification==2}">selected="selected"</c:if> value="2">拒绝</option>
		            </select></div>
	            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
	            <div class="lf_font fl">评级：</div>
		            <div class="fl select"><select style="width:178px;" id="rating" name="rating">
		            	<option <c:if test="${transac.rating==1}">selected="selected"</c:if> value="1">1星</option>
		            	<option <c:if test="${transac.rating==2}">selected="selected"</c:if> value="2">2星</option>
		            	<option <c:if test="${transac.rating==3}">selected="selected"</c:if> value="3">3星</option>
		            	<option <c:if test="${transac.rating==4}">selected="selected"</c:if> value="4">4星</option>
		            	<option <c:if test="${transac.rating==5}">selected="selected"</c:if> value="5">5星</option>
		            </select></div>
	            <div class="clr"></div>
        </div>
        <div class=" but" style="margin-bottom:-20px;"><domi:privilege url="/admin_op_wxyqb/saveUpdateTransac.htm"><a href="javascript:void(0);" onclick="saveContents();" class="sure fl">确认</a></domi:privilege><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
		</form>
</div>
<script>

function saveContents(){
	if(!$("#tdname").val()){
		jAlert("团队名称不能为空！","提示",function(){
			$("#tdname").focus();
        });
		return false;
	}
	var data=$("#wqqForm").serialize();
	$.post("${ctx}/admin_op_wxyqb/saveUpdateTransac.htm?id=${id}",data,function(d){
		jAlert("保存成功！","提示",function(){
			location.href=location.href;
		});
	});
}
</script>
