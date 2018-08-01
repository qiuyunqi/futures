<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<div class="fuchen" style="width:600px;">
	<div class=" fc_top" style="width:600px;"> 
    	<b class="fl fc_top_font">${empty id?'添加':'修改'}菜单</b>
        <div class="fl"></div>
    </div>
        <form id="purviewForm">
        <input id="id" name="id" type="hidden" value="${id}"/>
        <input id="parentId" name="parentId" type="hidden" value="${parentId}"/>
        <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">菜单名称：</div>
            <div class="fl input"><input id="name" name="name" type="text" value="${sysPurview.name}" placeholder="菜单名称" style="width:400px;"></div>
            <div class="clr"></div>
        </div>
         <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">菜单路径：</div>
            <div class="fl input"><input id="url" name="url" type="text" value="${sysPurview.url}" placeholder="菜单路径" style="width:400px;"></div>
            <div class="clr"></div>
        </div>
         <div class="form_cont form_cont0 form_cont1">
            <div class="lf_font fl">菜单备注：</div>
            <div class="fl input"><input id="remark" name="remark" type="text" value="${sysPurview.remark}" placeholder="菜单备注" style="width:400px;"></div>
            <div class="clr"></div>
        </div>
        <div class="but" style="margin-bottom:-20px;"><domi:privilege url="/admin_op_purview/savePurview.htm"><a href="javascript:void(0);" onclick="savePur();" class="sure fl">确认</a></domi:privilege><a href="javascript:void(0);" onclick="$.fancybox.close();" class="cancel fl">取消</a><div class="clr"></div></div>
		</form>
</div>
<script>
function savePur(){
	var data = $('#purviewForm').serialize();
	$.post('${ctx}/admin_op_purview/savePurview.htm',data,function(d){
		    if(d==-1){
                jAlert("请填写菜单名称","提示",function(){
	  				$("#name").focus();
	            });
                return null;
            }
            if(d==-2){
            	jAlert("请填写菜单路径","提示",function(){
	  				$("#url").focus();
	            });
                return null;
            }
            jAlert("操作成功","提示",function(){
            	window.location.reload();   
            })
	});
}
</script>