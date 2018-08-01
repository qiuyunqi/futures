<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<style>
.form_cont0 .lf_font{width:180px !important;}
</style>
<div class="fuchen" style="width:600px;">
	<div class=" fc_top" style="width:600px;"> 
    	<b class="fl fc_top_font">${empty id?'新增':'修改'}字典</b>
        <div class="fl"></div>
    </div>
        <form id="dicForm">
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">名称：</div>
            <div class="fl input"><input name="dictionaryName"  type="text"  value="${dic.name}" id="dictionaryName"></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">上级类别：</div>
            <div class="fl input">
	            <select id="pid" name="pid">
		            <c:forEach var="base" items="${baseList}" varStatus="status">
					    <option <c:if test="${dic.pid==base.id}">selected="selected"</c:if> value="${base.id}">${base.name}</option>
					</c:forEach>
		        </select>
            </div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">是否启用：</div>
            <div class="fl input">
	            <select id="isEnabled" name="isEnabled">
		            <option <c:if test="${dic.isEnabled==1}">selected="selected"</c:if> value="1">是</option>
		            <option <c:if test="${dic.isEnabled==0}">selected="selected"</c:if> value="0">否</option>
		        </select>
            </div>
            <div class="clr"></div>
        </div>
        <div class=" but" style="margin-bottom:-20px;"><domi:privilege url="/admin_list_dictionary/commitDictionaryAjax.htm"><a href="javascript:void(0);" onclick="commitDictionary();" class="sure fl">确认</a></domi:privilege><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
		</form>
</div>
<script>
function commitDictionary(){ 
	var data = $('#dicForm').serialize();
	$.post('${ctx}/admin_list_dictionary/commitDictionaryAjax.htm?id=${id}',data,function(d){
		if(d==-2){
	        sureInfo("确定","请输入名称","提示");
            return null;
        }
		if(d==1){
            jAlert("保存成功","提示",function(){
			    location.href = location.href;
     	    });
        }
	});
}
</script>
