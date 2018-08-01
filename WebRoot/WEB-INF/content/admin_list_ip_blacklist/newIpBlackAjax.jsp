<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class="fuchen" style="width:500px;">
	<div class=" fc_top" style="width:500px;"> 
    	<b class="fl fc_top_font">${empty id?'添加':'修改'}IP黑名单</b>
        <div class="fl"></div>
    </div>
        <form id="ipBlackForm">
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">IP地址：</div>
            <div class="fl input"><input id="ipUrl" name="ip" type="text" value="${ipBlacklist.ip}" placeholder="IP地址" style="width:200px;"/></div>
            <div class="clr"></div>
        </div>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">状态：</div>
            <select id="isBlack" name="isBlack" style="padding:5px">
            	<option <c:if test="${ipBlacklist.isBlack==1}">selected</c:if> value="1">黑名单</option>
	            <option <c:if test="${ipBlacklist.isBlack==0}">selected</c:if> value="0">白名单</option>
	        </select>
            <div class="clr"></div>
        </div>
        <div class=" but" style="margin-bottom:-20px;">
        <domi:privilege url="/admin_list_ip_blacklist/saveIpBlackAjax.htm">
        <a href="javascript:void(0);" onclick="saveIpBlack();" class="sure fl">确认</a>
        </domi:privilege>
        <a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
		</form>
</div>
<script>
function saveIpBlack(){
	var ipUrl=$("#ipUrl").val();
	if(!ipUrl){
		jAlert("请填写IP地址！","提示",function(){
			$("#ipUrl").focus();
	    });
        return false;
	}
	var data = $('#ipBlackForm').serialize();
	$.post('${ctx}/admin_list_ip_blacklist/saveIpBlackAjax.htm?id=${id}',data,function(d){
        jAlert("操作成功！","提示",function(){
			location.href = location.href;
        });
	});
}
</script>
